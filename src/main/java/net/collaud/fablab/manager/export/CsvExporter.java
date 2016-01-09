package net.collaud.fablab.manager.export;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Gaetan Collaud
 */
@Slf4j
public class CsvExporter<T> {

	public static final String FIELD_SEPARATOR = ",";
	public static final String FIELD_SEPARATOR_REPLACE = ";";
	public static final String LINE_SEPARATOR = "\n";
	public static final String ESCAPE_CHAR = "\"";

	private final Map<Field, CsvField> fields;
	private final CsvExport classAnnotation;
	private final StringBuilder builder;

	public CsvExporter(Class<T> type) {
		fields = new LinkedHashMap<>();
		builder = new StringBuilder();
		classAnnotation = Optional.ofNullable(type.getAnnotation(CsvExport.class))
				.orElseThrow(() -> new IllegalArgumentException("Class " + type.getSimpleName() + " has no CsvExport annotation"));
		Arrays.stream(type.getDeclaredFields())
				.filter(f -> f.getAnnotation(CsvField.class) != null)
				.forEach(f -> fields.put(f, f.getAnnotation(CsvField.class)));
	}
	
	protected String postHeader(String header){
		return header;
	}

	public CsvExporter writeHeader() {
		return writeLine(postHeader(fields.values().stream()
				.map(f -> f.headerName())
				.reduce(reduce())
				.orElse("")));
	}

	public CsvExporter writeRow(T obj) {
		return writeLine(fields.keySet().stream()
				.map(f -> getFieldValue(obj, f))
				.reduce(reduce())
				.orElse(""));
	}

	protected BinaryOperator<String> reduce() {
		return (l, r) ->l + FIELD_SEPARATOR + r ;
	}

	@Override
	public String toString() {
		return builder.toString();
	}

	public String getFileName() {
		return classAnnotation.fileName();
	}

	protected String getFieldValue(T obj, Field f) {
		try {
			f.setAccessible(true);
			return Optional.ofNullable(f.get(obj))
					.map(v -> (v instanceof CsvToString) ? ((CsvToString)v).CsvToString() : v.toString())
					.map(v-> v.replace(FIELD_SEPARATOR, FIELD_SEPARATOR_REPLACE))
					.map(v -> v.replace("\r\n", "\t"))
					.map(v -> v.replace("\n", "\t"))
					.map(v -> v.replace("\r", "\t"))
					.orElse("");
		} catch (IllegalAccessException ex) {
			log.error("Cannot access field " + f.getName(), ex);
			return "";
		}
	}

	protected CsvExporter writeLine(String l) {
		builder.append(l);
		builder.append(LINE_SEPARATOR);
		return this;
	}

}
