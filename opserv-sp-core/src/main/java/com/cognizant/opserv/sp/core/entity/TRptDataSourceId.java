package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptDataSourceId Pojo. 
 *
 */
@Embeddable
public class TRptDataSourceId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "doc_location_id", nullable = false, length = 255)
	private Integer docLocationId;
	@NotNull
	@Column(name = "rpt_config_id", nullable = false, length = 255)
	private Integer rptConfigId;

	/**
	 *
	 * @generated
	 */
	public TRptDataSourceId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setDocLocationId(final Integer docLocationId) {
		this.docLocationId = docLocationId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDocLocationId() {
		return this.docLocationId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRptConfigId(final Integer rptConfigId) {
		this.rptConfigId = rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptConfigId() {
		return this.rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRptDataSourceId)) {
			return false;
		}
		TRptDataSourceId castOther = (TRptDataSourceId) other;
		return (this.docLocationId.equals(castOther.docLocationId)) &&

		(this.rptConfigId.equals(castOther.rptConfigId))

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
		result = prime * result + ((docLocationId == null) ? 0 : docLocationId.hashCode());
		result = prime * result + ((rptConfigId == null) ? 0 : rptConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("docLocationId=[").append(docLocationId).append("] ");
		buffer.append("rptConfigId=[").append(rptConfigId).append("] ");

		return buffer.toString();
	}
}
