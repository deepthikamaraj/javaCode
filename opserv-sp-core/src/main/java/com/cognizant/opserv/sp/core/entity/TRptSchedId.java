package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptSchedId Pojo. 
 *
 */
@Embeddable
public class TRptSchedId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@NotNull	
	@Column(name = "rpt_sched_id", nullable = false, length = 255)
	private Integer rptSchedId;
	
	@NotNull
	@Column(name = "rpt_config_id", nullable = false, length = 255)
	private Integer rptConfigId;

	/**
	 *
	 * @generated
	 */
	public TRptSchedId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setRptSchedId(final Integer rptSchedId) {
		this.rptSchedId = rptSchedId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptSchedId() {
		return this.rptSchedId;
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
		if (!(other instanceof TRptSchedId)) {
			return false;
		}
		TRptSchedId castOther = (TRptSchedId) other;
		return (this.rptSchedId.equals(castOther.rptSchedId))&&
(this.rptConfigId.equals(castOther.rptConfigId));

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rptSchedId == null) ? 0 : rptSchedId.hashCode());
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

		buffer.append("rptSchedId=[").append(rptSchedId).append("] ");
		buffer.append("rptConfigId=[").append(rptConfigId).append("] ");

		return buffer.toString();
	}
}
