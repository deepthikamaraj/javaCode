package com.cognizant.opserv.sp.model;

import java.io.Serializable;

public class CRNotificationMessage implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 188197299762016753L;
	
	private String tenantCode;
	private Short tenantId;
	private Long chngReqId;
	
	/** The action.
	 * 	CommonConstants.CR_ACTION_SUBMIT= "submit";
	 *  CommonConstants.CR_ACTION_APPROVE= "approve";
		CommonConstants.CR_ACTION_REJECT= "reject";
	 *  */
	private String action;
	
	public String getTenantCode() {
		return tenantCode;
	}
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}
	public Short getTenantId() {
		return tenantId;
	}
	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}
	public Long getChngReqId() {
		return chngReqId;
	}
	public void setChngReqId(Long chngReqId) {
		this.chngReqId = chngReqId;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
