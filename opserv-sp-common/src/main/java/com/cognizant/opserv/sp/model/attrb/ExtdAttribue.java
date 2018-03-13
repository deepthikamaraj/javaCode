package com.cognizant.opserv.sp.model.attrb;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class ExtdAttribue
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ExtdAttribue extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7285616347503913354L;

	private String value;

	private AttributeDefinition definition;

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the definition
	 */
	public AttributeDefinition getDefinition() {
		return definition;
	}

	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(AttributeDefinition definition) {
		this.definition = definition;
	}

	
}
