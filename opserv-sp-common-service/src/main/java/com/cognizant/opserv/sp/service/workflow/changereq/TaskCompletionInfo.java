/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class TaskCompletionInfo.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class TaskCompletionInfo {
	
	/** The action. */
	private String action ; 
	
	/** The variables. */
	private List<ProcessVariable> variables;
	
	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Sets the action.
	 *
	 * @param action the action
	 */
	public void setAction(String action) {
		this.action = action;
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
