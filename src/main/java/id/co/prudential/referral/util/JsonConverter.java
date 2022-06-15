package id.co.prudential.referral.util;

import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	private final static Logger logger = Logger.getLogger(JsonConverter.class);

	public static Object convertStrToPojo(String str, Class<?> pojoClass) throws Exception {
		Object result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			result = mapper.readValue(str, pojoClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return result;
	}

	public static String convertPojoToStr(Object pojoObj) throws Exception {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			StringWriter writer = new StringWriter();
			mapper.writeValue(writer, pojoObj);
			result = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public static <T> Object parse(Object obj, Class<T> clazz) {
		Object returnObj = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			return (T) mapper.readValue((String) obj, clazz);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnObj;
	}
}
