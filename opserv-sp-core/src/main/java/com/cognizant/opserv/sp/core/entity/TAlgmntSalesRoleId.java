package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TAlgmntSalesRoleId Pojo. 
 *
 */
@Embeddable
public class TAlgmntSalesRoleId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	@NotNull
	@Column(name = "org_role_id", nullable = false, length = 255)
	private Integer orgRoleId;

	/**
	 *
	 * @generated
	 */
	public TAlgmntSalesRoleId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setOrgRoleId(final Integer orgRoleId) {
		this.orgRoleId = orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrgRoleId() {
		return this.orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAlgmntSalesRoleId)) {
			return false;
		}
		TAlgmntSalesRoleId castOther = (TAlgmntSalesRoleId) other;
		return (this.salesHierId.equals(castOther.salesHierId)) &&

		(this.orgRoleId.equals(castOther.orgRoleId))

		;

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salesHierId == null) ? 0 : salesHierId.hashCode());
		result = prime * result + ((orgRoleId == null) ? 0 : orgRoleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("orgRoleId=[").append(orgRoleId).append("] ");

		return buffer.toString();
	}
}
