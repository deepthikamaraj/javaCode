package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TPrdAlgmntId Pojo. 
 *
 */
@Embeddable
public class TPrdAlgmntId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_algmnt_id", nullable = false, length = 255)
	private Long prdAlgmntId;
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	@NotEmpty
	@Column(name = "prd_id", nullable = false, length = 50)
	private String prdId;

	/**
	 *
	 * @generated
	 */
	public TPrdAlgmntId() {
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
	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
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
	public void setPrdId(final String prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdId() {
		return this.prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPrdAlgmntId)) {
			return false;
		}
		TPrdAlgmntId castOther = (TPrdAlgmntId) other;
		return (this.prdAlgmntId.equals(castOther.prdAlgmntId)) &&

		(this.salesPosId.equals(castOther.salesPosId)) &&

		(this.salesHierId.equals(castOther.salesHierId)) &&

		(this.prdId.equals(castOther.prdId))

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
		result = prime * result + ((salesPosId == null) ? 0 : salesPosId.hashCode());
		result = prime * result + ((salesHierId == null) ? 0 : salesHierId.hashCode());
		result = prime * result + ((prdId == null) ? 0 : prdId.hashCode());
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
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("prdId=[").append(prdId).append("] ");

		return buffer.toString();
	}
}
