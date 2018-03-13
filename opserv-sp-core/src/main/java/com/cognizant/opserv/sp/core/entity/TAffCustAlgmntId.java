package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TAffCustAlgmntId Pojo. 
 *
 */
@Embeddable
public class TAffCustAlgmntId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "aff_cust_algmnt_id", nullable = false, length = 255)
	private Long affCustAlgmntId;
	@NotNull
	@Column(name = "cust_algmnt_id", nullable = false, length = 255)
	private Long custAlgmntId;

	/**
	 *
	 * @generated
	 */
	public TAffCustAlgmntId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setAffCustAlgmntId(final Long affCustAlgmntId) {
		this.affCustAlgmntId = affCustAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAffCustAlgmntId() {
		return this.affCustAlgmntId;
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
		if (!(other instanceof TAffCustAlgmntId)) {
			return false;
		}
		TAffCustAlgmntId castOther = (TAffCustAlgmntId) other;
		return (this.affCustAlgmntId.equals(castOther.affCustAlgmntId)) &&

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
		result = prime * result + ((affCustAlgmntId == null) ? 0 : affCustAlgmntId.hashCode());
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

		buffer.append("affCustAlgmntId=[").append(affCustAlgmntId).append("] ");
		buffer.append("custAlgmntId=[").append(custAlgmntId).append("] ");

		return buffer.toString();
	}
}
