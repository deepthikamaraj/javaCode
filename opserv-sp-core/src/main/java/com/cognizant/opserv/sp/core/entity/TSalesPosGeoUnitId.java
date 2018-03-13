package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TSalesPosGeoUnitId Pojo. 
 *
 */
@Embeddable
public class TSalesPosGeoUnitId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	@NotNull
	@Column(name = "geo_id", nullable = false, length = 255)
	private Integer geoId;

	/**
	 *
	 * @generated
	 */
	public TSalesPosGeoUnitId() {
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
	public void setGeoId(final Integer geoId) {
		this.geoId = geoId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoId() {
		return this.geoId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TSalesPosGeoUnitId)) {
			return false;
		}
		TSalesPosGeoUnitId castOther = (TSalesPosGeoUnitId) other;
		return (this.salesHierId.equals(castOther.salesHierId)) &&

		(this.salesPosId.equals(castOther.salesPosId)) &&

		(this.geoId.equals(castOther.geoId))

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
		result = prime * result + ((salesPosId == null) ? 0 : salesPosId.hashCode());
		result = prime * result + ((geoId == null) ? 0 : geoId.hashCode());
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
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("geoId=[").append(geoId).append("] ");

		return buffer.toString();
	}
}
