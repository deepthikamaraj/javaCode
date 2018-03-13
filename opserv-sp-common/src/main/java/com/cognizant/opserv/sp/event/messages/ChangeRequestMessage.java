package com.cognizant.opserv.sp.event.messages;

import java.io.Serializable;

import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public class ChangeRequestMessage  implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

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
	 * userDetails
	 */
	private UserDetails userDetails;
	
	/**
	 * salesPostion
	 */
	private SalesPosition salesPostion;
	
	/**
	 * comments
	 */
	private String comments;

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
	 * @return the userDetails
	 */
	public UserDetails getUserDetails() {
		return userDetails;
	}

	/**
	 * @param userDetails the userDetails to set
	 */
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	/**
	 * @return the salesPostion
	 */
	public SalesPosition getSalesPostion() {
		return salesPostion;
	}

	/**
	 * @param salesPostion the salesPostion to set
	 */
	public void setSalesPostion(SalesPosition salesPostion) {
		this.salesPostion = salesPostion;
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

	public Long getSalesPosID() {
		return salesPosID;
	}

	public void setSalesPosID(Long salesPosID) {
		this.salesPosID = salesPosID;
	}

	public Long getSalesHierID() {
		return salesHierID;
	}

	public void setSalesHierID(Long salesHierID) {
		this.salesHierID = salesHierID;
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
