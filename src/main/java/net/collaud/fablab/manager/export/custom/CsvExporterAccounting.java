package net.collaud.fablab.manager.export.custom;

import net.collaud.fablab.manager.export.*;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.type.HistoryEntryType;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;

/**
 *
 * @author Gaetan Collaud
 * @param <T>
 */
@Slf4j
public class CsvExporterAccounting extends CsvExporter<HistoryEntry>{
	
	public static final String FIELD_TO_OVERRIDE = "amount";

	public CsvExporterAccounting() {
		super(HistoryEntry.class);
	}

	@Override
	public String postHeader(String header){
		return header.replace(FIELD_TO_OVERRIDE, "credit"+FIELD_SEPARATOR+"debit");
	}

	@Override
	protected String getFieldValue(HistoryEntry obj, Field f) {
		String parsed = super.getFieldValue(obj, f);
		if(f.getAnnotation(CsvField.class).headerName().equals(FIELD_TO_OVERRIDE)){
			Double v = obj.getAmount();
			if(obj.getType()==HistoryEntryType.USAGE || obj.getType()==HistoryEntryType.SUBSCRIPTION){
				return FIELD_SEPARATOR+Double.toString(-v);
			}else{
				return parsed+FIELD_SEPARATOR;
			}
		}
		return parsed;
	}

	

	

}
