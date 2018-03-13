package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TTerrZipAlgmntId Pojo. 
 *
 */
@Embeddable
public class TTerrZipAlgmntId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	@NotEmpty
	@Column(name = "postal_cd", nullable = false, length = 20)
	private String postalCd;


	/**
	 *
	 * @generated
	 */
	public TTerrZipAlgmntId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setPostalCd(final String postalCd) {
		this.postalCd = postalCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCd() {
		return this.postalCd;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TTerrZipAlgmntId)) {
			return false;
		}
		TTerrZipAlgmntId castOther = (TTerrZipAlgmntId) other;
		return (this.postalCd.equals(castOther.postalCd)) &&

		(this.salesHierId.equals(castOther.salesHierId)) &&

		(this.salesPosId.equals(castOther.salesPosId))

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
		result = prime * result + ((postalCd == null) ? 0 : postalCd.hashCode());
		result = prime * result + ((salesHierId == null) ? 0 : salesHierId.hashCode());
		result = prime * result + ((salesPosId == null) ? 0 : salesPosId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");

		return buffer.toString();
	}
}
