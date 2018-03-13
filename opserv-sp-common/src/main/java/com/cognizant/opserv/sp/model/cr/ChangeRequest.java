/**
 * 
 */
package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesPosition;

/**
 * ****************************************************************************.
 *
 * @class ChangeRequest
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ChangeRequest extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8958917445238584987L;
	
	/**
	 * 
	 */
	private ChangeRequestDefinition crDefinition;
	/**
	 * 
	 */
	private SalesPosition raisedSalesPosition;
	/**
	 * 
	 */
	private SalesPosition requestedSalesPosition;
	/**
	 * 
	 */
	private ChangeRequestStatusType status;
	/**
	 * 
	 */
	private String businessReason;
	/**
	 * 
	 */
	private String comments;
	
	/**
	 * 
	 */
	private List<ChangeRequestLineItem> lineItems;
	
	/**
	 * @return the crDefinition
	 */
	public ChangeRequestDefinition getCrDefinition() {
		return crDefinition;
	}
	/**
	 * @param crDefinition the crDefinition to set
	 */
	public void setCrDefinition(ChangeRequestDefinition crDefinition) {
		this.crDefinition = crDefinition;
	}
	/**
	 * @return the raisedSalesPosition
	 */
	public SalesPosition getRaisedSalesPosition() {
		return raisedSalesPosition;
	}
	/**
	 * @param raisedSalesPosition the raisedSalesPosition to set
	 */
	public void setRaisedSalesPosition(SalesPosition raisedSalesPosition) {
		this.raisedSalesPosition = raisedSalesPosition;
	}
	/**
	 * @return the status
	 */
	public ChangeRequestStatusType getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ChangeRequestStatusType status) {
		this.status = status;
	}
	/**
	 * @return the requestedSalesPosition
	 */
	public SalesPosition getRequestedSalesPosition() {
		return requestedSalesPosition;
	}
	/**
	 * @param requestedSalesPosition the requestedSalesPosition to set
	 */
	public void setRequestedSalesPosition(SalesPosition requestedSalesPosition) {
		this.requestedSalesPosition = requestedSalesPosition;
	}
	/**
	 * @return the businessReason
	 */
	public String getBusinessReason() {
		return businessReason;
	}
	/**
	 * @param businessReason the businessReason to set
	 */
	public void setBusinessReason(String businessReason) {
		this.businessReason = businessReason;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the lineItems
	 */
	public List<ChangeRequestLineItem> getLineItems() {
		return lineItems;
	}
	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<ChangeRequestLineItem> lineItems) {
		this.lineItems = lineItems;
	}

	

}
