package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

/** 
 * The primary key class for the TRptTargetRecipientId Pojo. 
 *
 */
@Embeddable
@Audited
public class TRptTargetRecipientId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_config_id", nullable = false, length = 255)
	private Integer rptConfigId;
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	/**
	 *
	 * @generated
	 */
	public TRptTargetRecipientId() {
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
	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStaffId() {
		return this.staffId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRptTargetRecipientId)) {
			return false;
		}
		TRptTargetRecipientId castOther = (TRptTargetRecipientId) other;
		return (this.rptConfigId.equals(castOther.rptConfigId)) &&

		(this.staffId.equals(castOther.staffId))

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
		result = prime * result + ((rptConfigId == null) ? 0 : rptConfigId.hashCode());
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rptConfigId=[").append(rptConfigId).append("] ");
		buffer.append("staffId=[").append(staffId).append("] ");

		return buffer.toString();
	}
}
