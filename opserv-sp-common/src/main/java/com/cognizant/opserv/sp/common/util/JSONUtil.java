package com.cognizant.opserv.sp.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class JSONUtil to JSONUtil
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class JSONUtil {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(JSONUtil.class);
	/**
	 * Instantiates a new jSON util.
	 */
	private JSONUtil(){
		
	}

	/**
	 * To json.
	 *
	 * @param obj the obj
	 * @return the string
	 */
	public static String toJSON(Object obj) {
		StringWriter writer = null; 
		ObjectMapper mapper = new ObjectMapper();
		try {	
			writer = new StringWriter();
			mapper.writeValue(writer, obj);
		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		}

		return writer.toString();
	}

	/**
	 * To object list.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param clz the clz
	 * @return the list
	 */
	public static <T extends Object> List<T> toObjectList(String json,
			Class<T> clz) {
		ObjectMapper mapper = new ObjectMapper();

		List<T> list;
		try {
			list = mapper.readValue(json,
					TypeFactory.collectionType(List.class, clz));
			return list;
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	/**
	 * To object.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @return the t
	 */
	public static <T extends Object> T toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();		
			try {
				return mapper.readValue(json, new TypeReference<T>() {
				});
			} catch (JsonParseException e) {
				LOGGER.error(e.getMessage());
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			}
		
		return null;
	}
	
    /**
     * To object.
     *
     * @param <T> the generic type
     * @param json the json
     * @param clz the clz
     * @return the t
     */
    public static <T extends Object> T toObject(String json, Class<T> clz) {
	ObjectMapper mapper = new ObjectMapper();	
	    try {
			return mapper.readValue(json, clz);
		} catch (JsonParseException e) {
			LOGGER.error(e.getMessage());
			
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage());
			
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
	
	return null;
    }
    
    /**
     * To obj.
     *
     * @param <T> the generic type
     * @param json the json
     * @param clz the clz
     * @return the t
     */
    public static <T extends Object> T toObj(String json,TypeReference<T> clz) {
    	ObjectMapper mapper = new ObjectMapper();
    	
    	    try {
				return mapper.readValue(json, clz);
			} catch (JsonParseException e) {
				
				LOGGER.error(e.getMessage());
			} catch (JsonMappingException e) {
				
				LOGGER.error(e.getMessage());
			} catch (IOException e) {
				
				LOGGER.error(e.getMessage());
			}
    	
    	return null;
    }
}
