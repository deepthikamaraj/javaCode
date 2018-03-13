package com.cognizant.opserv.activiti.vo;

import java.io.Serializable;

public class ChangeRequestDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6918189175846864074L;

	/**
	 * tenantId
	 */
	private Short userId;
	
	/**
	 * tenantCode
	 */
	private String tenantCode;
	
	/**
	 * tenantId
	 */
	private Short tenantId;
	
	/**
	 *  chngReqID
	 */
	private Long chngReqID;
	
	/**
	 *  salesPosID
	 */
	private Long salesPosID;
	
	/**
	 *  salesHierID
	 */
	private Long salesHierID;
	
	/**
	 * comments
	 */
	private String comments;

	/**
	 * @return the userId
	 */
	public Short getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Short userId) {
		this.userId = userId;
	}

	/**
	 * @return the tenantId
	 */
	public Short getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the chngReqID
	 */
	public Long getChngReqID() {
		return chngReqID;
	}

	/**
	 * @param chngReqID the chngReqID to set
	 */
	public void setChngReqID(Long chngReqID) {
		this.chngReqID = chngReqID;
	}

	/**
	 * @return the salesPosID
	 */
	public Long getSalesPosID() {
		return salesPosID;
	}

	/**
	 * @param salesPosID the salesPosID to set
	 */
	public void setSalesPosID(Long salesPosID) {
		this.salesPosID = salesPosID;
	}

	/**
	 * @return the salesHierID
	 */
	public Long getSalesHierID() {
		return salesHierID;
	}

	/**
	 * @param salesHierID the salesHierID to set
	 */
	public void setSalesHierID(Long salesHierID) {
		this.salesHierID = salesHierID;
	}

	/**
	 * @return the tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * @param tenantCode the tenantCode to set
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
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
