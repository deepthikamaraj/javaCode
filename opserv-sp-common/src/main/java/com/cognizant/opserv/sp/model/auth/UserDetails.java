/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.model.auth;

import java.io.Serializable;

/**
 * ****************************************************************************
 * 
 * @class UserDetails
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class UserDetails implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6664879408235276983L;

	/** userId - user id. */
	private Integer userId;

	/** userName - user name. */
	private String userName;

	/** isValidUser - is valid user. */
	private boolean isValidUser;

	/** staffId - staff id. */
	private Integer staffId;

	/** firstName - first name. */
	private String firstName;

	/** middleName - middle name. */
	private String middleName;

	/** lastName - last name. */
	private String lastName;

	/** tenantId - tenant id. */
	private Short tenantId;

	/** tenantCode - tenant Code */
	private String tenantCode;

	/** localeId - locale id. */
	private String localeId;

	/** roleId - role id. */
	private Integer roleId;

	/** roleName - role Name. */
	private String roleName;

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the isValidUser
	 */
	public boolean isValidUser() {
		return isValidUser;
	}

	/**
	 * @param isValidUser
	 *            the isValidUser to set
	 */
	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}

	/**
	 * @return the staffId
	 */
	public Integer getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId
	 *            the staffId to set
	 */
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the tenantId
	 */
	public Short getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * @param tenantCode
	 *            the tenantCode to set
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**
	 * @return the localeId
	 */
	public String getLocaleId() {
		return localeId;
	}

	/**
	 * @param localeId
	 *            the localeId to set
	 */
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
