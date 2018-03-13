/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;



/**
 * The Class TaskResponseInfo.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class TaskResponseInfo {

	/** The assignee. */
	private String assignee;

	
	/** The id. */
	private String id;

	/** The tenant id. */
	private String tenantId;

	/** The variables. */
	private List<ProcessVariable> variables;

	/** The process variables. */
	private Map<ProcessVariableEnum, Object> processVariables;

	
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
	 * Gets the assignee.
	 *
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	
	/**
	 * Sets the assignee.
	 *
	 * @param assignee the assignee
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this.id = id;
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

	/**
	 * Gets the process variables.
	 *
	 * @return the process variables
	 */
	public Map<ProcessVariableEnum, Object> getProcessVariables() {
		return processVariables;
	}

	/**
	 * Sets the process variables.
	 *
	 * @param processVariables the process variables
	 */
	public void setProcessVariables(
			Map<ProcessVariableEnum, Object> processVariables) {
		this.processVariables = processVariables;
	}

}
