package com.cognizant.opserv.sp.model;

import java.io.Serializable;
/**
 * ****************************************************************************.
 *
 * @class SalesOrgHierarchy
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class SalesOrgHierarchy extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2292080687521766970L;

	private String description;

	private short level;

	private String colorCode;
	
	private String orgRole;

	private SalesOrgHierarchy parentHierarchy;
	
	private boolean assignZipFlag;
	
	private boolean moveSpFlag;

	
	public boolean isAssignZipFlag() {
		return assignZipFlag;
	}

	public void setAssignZipFlag(boolean assignZipFlag) {
		this.assignZipFlag = assignZipFlag;
	}

	public boolean isMoveSpFlag() {
		return moveSpFlag;
	}

	public void setMoveSpFlag(boolean moveSpFlag) {
		this.moveSpFlag = moveSpFlag;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the level
	 */
	public short getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(short level) {
		this.level = level;
	}

	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode
	 *            the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	/**
	 * @return the parentHierarchy
	 */
	public SalesOrgHierarchy getParentHierarchy() {
		return parentHierarchy;
	}

	/**
	 * @param parentHierarchy
	 *            the parentHierarchy to set
	 */
	public void setParentHierarchy(SalesOrgHierarchy parentHierarchy) {
		this.parentHierarchy = parentHierarchy;
	}

	
	public String getOrgRole() {
		return orgRole;
	}

	public void setOrgRole(String orgRole) {
		this.orgRole = orgRole;
	}
	
}
