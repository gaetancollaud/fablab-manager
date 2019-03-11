package net.collaud.fablab.manager.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import java.io.IOException;
import java.time.Instant;
import java.util.function.Function;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class HibernateAwareObjectMapper extends ObjectMapper {

	public HibernateAwareObjectMapper() {
		addMixIn(Object.class, IgnoreHibernatePropertiesInJackson.class);
		registerModule(new Hibernate5Module());
		final Jackson8Module dateModule = new Jackson8Module();
		dateModule.addLongSerializer(Instant.class, (i) -> i.getEpochSecond() * 1000);
		dateModule.addLongDeserializer(Instant.class, (s) -> Instant.ofEpochMilli(s));
		registerModule(dateModule);
	}



	public class Jackson8Module extends SimpleModule {

		public <T> void addStringSerializer(Class<T> cls, Function<T, String> serializeFunction) {
			JsonSerializer<T> jsonSerializer = new JsonSerializer<T>() {
				@Override
				public void serialize(T t, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
					String val = serializeFunction.apply(t);
					jgen.writeString(val);
				}
			};
			addSerializer(cls, jsonSerializer);
		}

		public <T> void addStringDeserializer(Class<T> clazz, Function<String, T> deserializeFunction) {
			addDeserializer(clazz, new JsonDeserializer<T>() {
				@Override
				public T deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
					return deserializeFunction.apply(jp.readValueAs(String.class));
				}
			});
		}
		
		public <T> void addLongSerializer(Class<T> cls, Function<T, Long> serializeFunction) {
			JsonSerializer<T> jsonSerializer = new JsonSerializer<T>() {
				@Override
				public void serialize(T t, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
					Long val = serializeFunction.apply(t);
					jgen.writeNumber(val);
				}
			};
			addSerializer(cls, jsonSerializer);
		}

		public <T> void addLongDeserializer(Class<T> clazz, Function<Long, T> deserializeFunction) {
			addDeserializer(clazz, new JsonDeserializer<T>() {
				@Override
				public T deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
					return deserializeFunction.apply(jp.readValueAs(Long.class));
				}
			});
		}
		
		

	}


	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private abstract class IgnoreHibernatePropertiesInJackson{ }
}
