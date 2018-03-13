package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPrdBussUnitId Pojo. 
 *
 */
@Embeddable
public class TPrdBussUnitId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_config_id", nullable = false, length = 255)
	private Integer prdConfigId;
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 *
	 * @generated
	 */
	public TPrdBussUnitId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrdConfigId(final Integer prdConfigId) {
		this.prdConfigId = prdConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrdConfigId() {
		return this.prdConfigId;
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
		if (!(other instanceof TPrdBussUnitId)) {
			return false;
		}
		TPrdBussUnitId castOther = (TPrdBussUnitId) other;
		return (this.prdConfigId.equals(castOther.prdConfigId)) &&

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
		result = prime * result + ((prdConfigId == null) ? 0 : prdConfigId.hashCode());
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

		buffer.append("prdConfigId=[").append(prdConfigId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");

		return buffer.toString();
	}
}
