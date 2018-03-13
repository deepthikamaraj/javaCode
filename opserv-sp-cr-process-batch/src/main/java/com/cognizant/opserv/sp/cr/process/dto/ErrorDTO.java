package com.cognizant.opserv.sp.cr.process.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author 426111
 *
 */
public class ErrorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5447177881542613318L;
	
	
	private ErrorCode errorCode;
	private	Map<String,Object> params = new HashMap<String,Object>();
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public void addParam(String key, Object value) {
		params.put(key,value);
	}
	
}
