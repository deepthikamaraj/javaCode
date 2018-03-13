package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPrdAlgmntAttrId Pojo. 
 *
 */
@Embeddable
public class TPrdAlgmntAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_algmnt_id", nullable = false, length = 255)
	private Long prdAlgmntId;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;

	/**
	 *
	 * @generated
	 */
	public TPrdAlgmntAttrId() {
	}

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
	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAttrId() {
		return this.attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPrdAlgmntAttrId)) {
			return false;
		}
		TPrdAlgmntAttrId castOther = (TPrdAlgmntAttrId) other;
		return (this.prdAlgmntId.equals(castOther.prdAlgmntId)) &&

		(this.attrId.equals(castOther.attrId))

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
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
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
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
