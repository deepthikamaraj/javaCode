package com.cognizant.opserv.sp.model.cr;

import java.io.Serializable;
import java.util.Date;

import com.cognizant.opserv.sp.common.ChangeRequestApproverType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.model.BaseModel;

/**
 * ****************************************************************************.
 *
 * @class ChangeRequestApproverDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 08/08/2016
 * ***************************************************************************
 */
public class ChangeRequestApproverDetails extends BaseModel implements Serializable{


	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2134011409161181125L;
	
	/** The chng req id. */
	private long chngReqId;
	
	/** The appr sales hier id. */
	private long apprSalesHierId;
	
	/** The appr sales pos id. */
	private long apprSalesPosId;
	
	/** The change request status type. */
	private ChangeRequestStatusType changeRequestStatusType;	
	
	/** The change request approver type. */
	private ChangeRequestApproverType changeRequestApproverType;
	
	/** The dlg sales pos id. */
	private long dlgSalesPosId;
	
	/** The dlg sales hier id. */
	private long dlgSalesHierId;
	
	/** The target appr flag. */
	private Character targetApprFlag;
	
	/** The action dt tm. */
	private Date actionDtTm;
	
	/** The action by. */
	private Integer actionBy;
	
	/** The comments. */
	private String comments;
	
	/**
	 * Gets the chng req id.
	 *
	 * @return the chng req id
	 */
	public long getChngReqId() {
		return chngReqId;
	}
	
	/**
	 * Sets the chng req id.
	 *
	 * @param chngReqId the new chng req id
	 */
	public void setChngReqId(long chngReqId) {
		this.chngReqId = chngReqId;
	}
	
	/**
	 * Gets the appr sales hier id.
	 *
	 * @return the appr sales hier id
	 */
	public long getApprSalesHierId() {
		return apprSalesHierId;
	}
	
	/**
	 * Sets the appr sales hier id.
	 *
	 * @param apprSalesHierId the new appr sales hier id
	 */
	public void setApprSalesHierId(long apprSalesHierId) {
		this.apprSalesHierId = apprSalesHierId;
	}
	
	/**
	 * Gets the appr sales pos id.
	 *
	 * @return the appr sales pos id
	 */
	public long getApprSalesPosId() {
		return apprSalesPosId;
	}
	
	/**
	 * Sets the appr sales pos id.
	 *
	 * @param apprSalesPosId the new appr sales pos id
	 */
	public void setApprSalesPosId(long apprSalesPosId) {
		this.apprSalesPosId = apprSalesPosId;
	}
	
	/**
	 * Gets the change request status type.
	 *
	 * @return the change request status type
	 */
	public ChangeRequestStatusType getChangeRequestStatusType() {
		return changeRequestStatusType;
	}
	
	/**
	 * Sets the change request status type.
	 *
	 * @param changeRequestStatusType the new change request status type
	 */
	public void setChangeRequestStatusType(
			ChangeRequestStatusType changeRequestStatusType) {
		this.changeRequestStatusType = changeRequestStatusType;
	}
	
	/**
	 * Gets the change request approver type.
	 *
	 * @return the change request approver type
	 */
	public ChangeRequestApproverType getChangeRequestApproverType() {
		return changeRequestApproverType;
	}
	
	/**
	 * Sets the change request approver type.
	 *
	 * @param changeRequestApproverType the new change request approver type
	 */
	public void setChangeRequestApproverType(
			ChangeRequestApproverType changeRequestApproverType) {
		this.changeRequestApproverType = changeRequestApproverType;
	}
	
	/**
	 * Gets the dlg sales pos id.
	 *
	 * @return the dlg sales pos id
	 */
	public long getDlgSalesPosId() {
		return dlgSalesPosId;
	}
	
	/**
	 * Sets the dlg sales pos id.
	 *
	 * @param dlgSalesPosId the new dlg sales pos id
	 */
	public void setDlgSalesPosId(long dlgSalesPosId) {
		this.dlgSalesPosId = dlgSalesPosId;
	}
	
	/**
	 * Gets the dlg sales hier id.
	 *
	 * @return the dlg sales hier id
	 */
	public long getDlgSalesHierId() {
		return dlgSalesHierId;
	}
	
	/**
	 * Sets the dlg sales hier id.
	 *
	 * @param dlgSalesHierId the new dlg sales hier id
	 */
	public void setDlgSalesHierId(long dlgSalesHierId) {
		this.dlgSalesHierId = dlgSalesHierId;
	}
	
	/**
	 * Gets the target appr flag.
	 *
	 * @return the target appr flag
	 */
	public Character getTargetApprFlag() {
		return targetApprFlag;
	}
	
	/**
	 * Sets the target appr flag.
	 *
	 * @param targetApprFlag the new target appr flag
	 */
	public void setTargetApprFlag(Character targetApprFlag) {
		this.targetApprFlag = targetApprFlag;
	}
	
	/**
	 * Gets the action dt tm.
	 *
	 * @return the action dt tm
	 */
	public Date getActionDtTm() {
		return actionDtTm;
	}
	
	/**
	 * Sets the action dt tm.
	 *
	 * @param actionDtTm the new action dt tm
	 */
	public void setActionDtTm(Date actionDtTm) {
		this.actionDtTm = actionDtTm;
	}
	
	/**
	 * Gets the action by.
	 *
	 * @return the action by
	 */
	public Integer getActionBy() {
		return actionBy;
	}
	
	/**
	 * Sets the action by.
	 *
	 * @param actionBy the new action by
	 */
	public void setActionBy(Integer actionBy) {
		this.actionBy = actionBy;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
