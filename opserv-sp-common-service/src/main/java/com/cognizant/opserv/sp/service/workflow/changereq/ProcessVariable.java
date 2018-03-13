/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class ProcessVariable.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class ProcessVariable {

	/** The name. */
	private String name;
	
	/** The value. */
	private Object value;
	
	/** The value url. */
	private Object valueUrl;

	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Sets the name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	
	/**
	 * Sets the value.
	 *
	 * @param value the value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the value url.
	 *
	 * @return the value url
	 */
	public Object getValueUrl() {
		return valueUrl;
	}

	/**
	 * Sets the value url.
	 *
	 * @param valueUrl the value url
	 */
	public void setValueUrl(Object valueUrl) {
		this.valueUrl = valueUrl;
	}

}
