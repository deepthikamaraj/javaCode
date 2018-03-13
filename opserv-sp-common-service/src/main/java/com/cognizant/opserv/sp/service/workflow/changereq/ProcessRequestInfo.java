/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class ProcessRequestInfo.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class ProcessRequestInfo implements Serializable {

	/** The Constant serialVersionUID. */
	
	private static final long serialVersionUID = 1L;

	/** The process definition key. */
	private String processDefinitionKey;
	
	/** The business key. */
	private String businessKey;

	/** The tenant id. */
	private String tenantId;

	/** The variables. */
	private List<ProcessVariable> variables;

	
	/**
	 * The Constructor.
	 */
	public ProcessRequestInfo() {
		super();
	}

	
	
	/**
	 * Gets the business key.
	 *
	 * @return the business key
	 */
	public String getBusinessKey() {
		return businessKey;
	}


	
	/**
	 * Sets the business key.
	 *
	 * @param businessKey the business key
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}


	
	/**
	 * Gets the process definition key.
	 *
	 * @return the process definition key
	 */
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	
	/**
	 * Sets the process definition key.
	 *
	 * @param processDefinitionKey the process definition key
	 */
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	
	/**
	 * Gets the tenant id.
	 *
	 * @return the tenant id
	 */
	public String getTenantId() {
		return tenantId;
	}


	/**
	 * Sets the tenant id.
	 *
	 * @param tenantId the tenant id
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	
	/**
	 * Gets the variables.
	 *
	 * @return the variables
	 */
	public List<ProcessVariable> getVariables() {
		return variables;
	}

	
	/**
	 * Sets the variables.
	 *
	 * @param variables the variables
	 */
	public void setVariables(List<ProcessVariable> variables) {
		this.variables = variables;
	}

	

}
