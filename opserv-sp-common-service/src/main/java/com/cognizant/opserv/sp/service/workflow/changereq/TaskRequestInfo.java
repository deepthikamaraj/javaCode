/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class TaskRequestInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestInfo {

	/** The assignee. */
	private String assignee;
	
	/** The include process variables. */
	private Boolean includeProcessVariables;
	
	/** The include task local variables. */
	private Boolean includeTaskLocalVariables;
	
	/** The tenant id. */
	private String tenantId;
	
	/** The start. */
	private Integer start;
	
	/** The size. */
	private Integer size;
	
	/** The sort. */
	private String sort;
	
	/** The order. */
	private String order;
	
	/** The process instance variables. */
	private List<ProcessInstanceVariable> processInstanceVariables;

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
	 * Gets the include process variables.
	 *
	 * @return the include process variables
	 */
	public Boolean getIncludeProcessVariables() {
		return includeProcessVariables;
	}

	/**
	 * Sets the include process variables.
	 *
	 * @param includeProcessVariables the include process variables
	 */
	public void setIncludeProcessVariables(Boolean includeProcessVariables) {
		this.includeProcessVariables = includeProcessVariables;
	}

	/**
	 * Gets the include task local variables.
	 *
	 * @return the include task local variables
	 */
	public Boolean getIncludeTaskLocalVariables() {
		return includeTaskLocalVariables;
	}

	/**
	 * Sets the include task local variables.
	 *
	 * @param includeTaskLocalVariables the include task local variables
	 */
	public void setIncludeTaskLocalVariables(Boolean includeTaskLocalVariables) {
		this.includeTaskLocalVariables = includeTaskLocalVariables;
	}

	/**
	 * Gets the start.
	 *
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * Sets the start.
	 *
	 * @param start the start
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the size
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * Gets the sort.
	 *
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * Sets the sort.
	 *
	 * @param sort the sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * Gets the process instance variables.
	 *
	 * @return the process instance variables
	 */
	public List<ProcessInstanceVariable> getProcessInstanceVariables() {
		return processInstanceVariables;
	}

	/**
	 * Sets the process instance variables.
	 *
	 * @param processInstanceVariables the process instance variables
	 */
	public void setProcessInstanceVariables(
			List<ProcessInstanceVariable> processInstanceVariables) {
		this.processInstanceVariables = processInstanceVariables;
	}

}
