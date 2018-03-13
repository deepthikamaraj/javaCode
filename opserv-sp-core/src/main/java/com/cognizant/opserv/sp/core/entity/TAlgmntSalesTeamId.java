package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TAlgmntSalesTeamId Pojo. 
 *
 */
@Embeddable
public class TAlgmntSalesTeamId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 *
	 * @generated
	 */
	public TAlgmntSalesTeamId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamId() {
		return this.salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAlgmntSalesTeamId)) {
			return false;
		}
		TAlgmntSalesTeamId castOther = (TAlgmntSalesTeamId) other;
		return (this.salesTeamId.equals(castOther.salesTeamId)) &&

		(this.algmntId.equals(castOther.algmntId)) &&

		(this.bussUnitId.equals(castOther.bussUnitId))

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
		result = prime * result + ((salesTeamId == null) ? 0 : salesTeamId.hashCode());
		result = prime * result + ((algmntId == null) ? 0 : algmntId.hashCode());
		result = prime * result + ((bussUnitId == null) ? 0 : bussUnitId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");

		return buffer.toString();
	}
}
