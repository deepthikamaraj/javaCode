package com.cognizant.opserv.sp.model;

import java.io.Serializable;

public class SalesOrgRole extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7717677136422520184L;
	
	
	private String description;
	
	private Long parentSalesOrgRoleId;

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
	 * @return the parentSalesOrgRole
	 */
	public Long getParentSalesOrgRoleId() {
		return parentSalesOrgRoleId;
	}
	
	/**
	 * @param parentSalesOrgRole
	 *            the parentSalesOrgRole to set
	 */
	public void setParentSalesOrgRoleId(Long parentSalesOrgRoleId) {
		this.parentSalesOrgRoleId = parentSalesOrgRoleId;
	}

}
