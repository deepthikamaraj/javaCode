package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TMtrAttrId Pojo. 
 *
 */
@Embeddable
public class TMtrAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;

	/**
	 *
	 * @generated
	 */
	public TMtrAttrId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setMtrId(final Integer mtrId) {
		this.mtrId = mtrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrId() {
		return this.mtrId;
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
		if (!(other instanceof TMtrAttrId)) {
			return false;
		}
		TMtrAttrId castOther = (TMtrAttrId) other;
		return (this.mtrId.equals(castOther.mtrId)) &&

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
		result = prime * result + ((mtrId == null) ? 0 : mtrId.hashCode());
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

		buffer.append("mtrId=[").append(mtrId).append("] ");
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
