package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TMtrConfigId Pojo. 
 *
 */
@Embeddable
public class TMtrConfigId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	@NotNull
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;

	/**
	 *
	 * @generated
	 */
	public TMtrConfigId() {
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TMtrConfigId)) {
			return false;
		}
		TMtrConfigId castOther = (TMtrConfigId) other;
		return (this.salesHierId.equals(castOther.salesHierId)) &&

		(this.mtrId.equals(castOther.mtrId))

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
		result = prime * result + ((mtrId == null) ? 0 : mtrId.hashCode());
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
		buffer.append("mtrId=[").append(mtrId).append("] ");

		return buffer.toString();
	}
}
