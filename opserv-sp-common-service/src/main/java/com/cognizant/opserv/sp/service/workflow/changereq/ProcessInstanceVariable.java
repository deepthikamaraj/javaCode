package com.cognizant.opserv.sp.service.workflow.changereq;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class ProcessInstanceVariable.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class ProcessInstanceVariable {
	
	/** The name. */
	private String name;
	
	/** The value. */
	private Object value;

	/** The operation. */
	private String operation;
	
	/** The type. */
	private String type;

	
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
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}


	/**
	 * Sets the operation.
	 *
	 * @param operation the operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}


	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param type the type
	 */
	public void setType(String type) {
		this.type = type;
	}

}
