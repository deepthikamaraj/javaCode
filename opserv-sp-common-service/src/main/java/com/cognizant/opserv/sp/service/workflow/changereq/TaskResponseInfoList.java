/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.workflow.changereq;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/**
 * The Class TaskResponseInfoList.
 */
@JsonIgnoreProperties(ignoreUnknown= true)
public class TaskResponseInfoList {

	/** The data. */
	private List<TaskResponseInfo> data;

	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<TaskResponseInfo> getData() {
		return data;
	}


	/**
	 * Sets the data.
	 *
	 * @param data the data
	 */
	public void setData(List<TaskResponseInfo> data) {
		this.data = data;
	}
	
	
	
}
