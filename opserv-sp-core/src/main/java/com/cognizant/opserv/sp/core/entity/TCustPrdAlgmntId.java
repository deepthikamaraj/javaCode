package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustPrdAlgmntId Pojo. 
 *
 */
@Embeddable
public class TCustPrdAlgmntId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_algmnt_id", nullable = false, length = 255)
	private Long prdAlgmntId;
	@NotNull
	@Column(name = "cust_algmnt_id", nullable = false, length = 255)
	private Long custAlgmntId;

	/**
	 *
	 * @generated
	 */
/*	public TCustPrdAlgmntId() {
	}*/

	/**
	 * 
	 * @generated
	 */
	public void setPrdAlgmntId(final Long prdAlgmntId) {
		this.prdAlgmntId = prdAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdAlgmntId() {
		return this.prdAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setCustAlgmntId(final Long custAlgmntId) {
		this.custAlgmntId = custAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCustAlgmntId() {
		return this.custAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustPrdAlgmntId)) {
			return false;
		}
		TCustPrdAlgmntId castOther = (TCustPrdAlgmntId) other;
		return (this.prdAlgmntId.equals(castOther.prdAlgmntId)) &&

		(this.custAlgmntId.equals(castOther.custAlgmntId))

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
		result = prime * result + ((prdAlgmntId == null) ? 0 : prdAlgmntId.hashCode());
		result = prime * result + ((custAlgmntId == null) ? 0 : custAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdAlgmntId=[").append(prdAlgmntId).append("] ");
		buffer.append("custAlgmntId=[").append(custAlgmntId).append("] ");

		return buffer.toString();
	}
}
