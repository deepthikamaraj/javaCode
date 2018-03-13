package com.cognizant.opserv.sp.rest.ajax;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * ****************************************************************************.
 *
 * @class AjaxResponseUtil
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class AjaxResponseUtil {

	
	/**
	 * The Constructor.
	 *
	 * @Constructor : Default constructor.
	 */
	 private AjaxResponseUtil(){
		
	}
	
	/**
	 * Gets the service status.
	 *
	 * @param statusCode holds - status code
	 * @param message holds - message
	 * @return serviceStatus
	 * @Method getServiceStatus - to get the error statusCode and message.
	 */
	public static ServiceStatus getServiceStatus(AjaxStatusCode statusCode,
			String message) {
		//ServiceStatus serviceStatus = new ServiceStatus(statusCode, message);
		return new ServiceStatus(statusCode, message);
	}

	/**
	 * Convert object to json.
	 *
	 * @param object holds - object
	 * @return - string
	 * @Method convertObjectToJson - Convert object to json.
	 */
	public static String convertObjectToJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		OutputStream out = new ByteArrayOutputStream();
		String json = null;
		try {
			mapper.writeValue(out, object);
			final byte[] data = ((ByteArrayOutputStream) out).toByteArray();
			json = new String(data, "UTF-8");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return json;

	}
	
	/**
	 * Convert object to json.
	 *
	 * @param object holds - object
	 * @return - string
	 * @Method convertObjectToJson - Convert object to json.
	 */
	public static String convertObjectToPrettyJson(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		OutputStream out = new ByteArrayOutputStream();
		String json = null;
		try {
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
			mapper.writeValue(out, object);
			final byte[] data = ((ByteArrayOutputStream) out).toByteArray();
			json = new String(data, "UTF-8");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return json;

	}	
	
	/**
	 * Checks if is null or empty.
	 *
	 * @param value holds - value
	 * @return boolean
	 * @Method isNullOrEmpty - Checks if is null or empty.
	 */
	public static boolean isNullOrEmpty(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			String stringValue = (String) value;
			if (stringValue.isEmpty()) {
				return true;
			}
		}
		return false;
	}

}
