package com.cognizant.opserv.sp.model;

import java.util.HashMap;
import java.util.Map;
/**
 * ****************************************************************************.
 *
 * @class ViewRowData
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ViewRowData {

	/** values - stores column data  */
	private Map<String, String> values;


	/** tenantCode - stores tenant code  */
	private String tenantCode;

	/**
	 * 
	 * @param field
	 * @param value
	 */
	public void addFieldData(String field, String value) {

		if (values == null) {
			values = new HashMap<String, String>();
		}
		values.put(field, value);
	}

	/**
	 * 
	 * @param field
	 * @return
	 */
	public String getFieldData(String field) {
		return (values != null && values.containsKey(field)) ? values
				.get(field) : null;
	}	

	/**
	 * gets tenantCode
	 * @return tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * sets tenantCode
	 * @param tenantCode
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	
	
	/**
	 * @return the values
	 */
	public Map<String, String> getValues() {
		return values;
	}
	
	@Override
	public String toString() {
		return "BaseObjectView [values=" + values + "]";
	}
}