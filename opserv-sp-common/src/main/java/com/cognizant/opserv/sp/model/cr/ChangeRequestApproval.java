package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;
import java.util.Date;

import com.cognizant.opserv.sp.common.ChangeRequestApprovalType;
import com.cognizant.opserv.sp.common.ChangeRequestApproverType;
import com.cognizant.opserv.sp.model.BaseModel;
import com.cognizant.opserv.sp.model.SalesPosition;
/**
 * ****************************************************************************.
 *
 * @class ChangeRequestApproval
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ChangeRequestApproval extends BaseModel implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	

	/**
	 * 
	 */
	private SalesPosition approvalSalesPosition;
	/**
	 * 
	 */
	private ChangeRequestApprovalType actionTaken;
	/**
	 * 
	 */
	private ChangeRequestApproverType approverType;
	/**
	 * 
	 */
	private SalesPosition delegateSalesPosition;

	/**
	 * 
	 */
	private boolean istargetApproved;
	/**
	 * 
	 */
	private Date actionTimeStamp;
	/**
	 * 
	 */
	private String comments;
	
	/**
	 * @return the approvalSalesPosition
	 */
	public SalesPosition getApprovalSalesPosition() {
		return approvalSalesPosition;
	}
	/**
	 * @param approvalSalesPosition the approvalSalesPosition to set
	 */
	public void setApprovalSalesPosition(SalesPosition approvalSalesPosition) {
		this.approvalSalesPosition = approvalSalesPosition;
	}
	/**
	 * @return the actionTaken
	 */
	public ChangeRequestApprovalType getActionTaken() {
		return actionTaken;
	}
	/**
	 * @param actionTaken the actionTaken to set
	 */
	public void setActionTaken(ChangeRequestApprovalType actionTaken) {
		this.actionTaken = actionTaken;
	}
	/**
	 * @return the approverType
	 */
	public ChangeRequestApproverType getApproverType() {
		return approverType;
	}
	/**
	 * @param approverType the approverType to set
	 */
	public void setApproverType(ChangeRequestApproverType approverType) {
		this.approverType = approverType;
	}
	/**
	 * @return the delegateSalesPosition
	 */
	public SalesPosition getDelegateSalesPosition() {
		return delegateSalesPosition;
	}
	/**
	 * @param delegateSalesPosition the delegateSalesPosition to set
	 */
	public void setDelegateSalesPosition(SalesPosition delegateSalesPosition) {
		this.delegateSalesPosition = delegateSalesPosition;
	}
	/**
	 * @return the istargetApproved
	 */
	public boolean isIstargetApproved() {
		return istargetApproved;
	}
	/**
	 * @param istargetApproved the istargetApproved to set
	 */
	public void setIstargetApproved(boolean istargetApproved) {
		this.istargetApproved = istargetApproved;
	}
	/**
	 * @return the actionTimeStamp
	 */
	public Date getActionTimeStamp() {
		return actionTimeStamp;
	}
	/**
	 * @param actionTimeStamp the actionTimeStamp to set
	 */
	public void setActionTimeStamp(Date actionTimeStamp) {
		this.actionTimeStamp = actionTimeStamp;
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


}
