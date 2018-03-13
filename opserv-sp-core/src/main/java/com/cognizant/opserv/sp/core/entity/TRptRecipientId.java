package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

/** 
 * The primary key class for the TRptRecipientId Pojo. 
 *
 */
@Embeddable
@Audited
public class TRptRecipientId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_id", nullable = false, length = 255)
	private Integer rptId;
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	/**
	 *
	 * @generated
	 */
	public TRptRecipientId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setRptId(final Integer rptId) {
		this.rptId = rptId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptId() {
		return this.rptId;
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
		if (!(other instanceof TRptRecipientId)) {
			return false;
		}
		TRptRecipientId castOther = (TRptRecipientId) other;
		return (this.rptId.equals(castOther.rptId)) &&

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
		result = prime * result + ((rptId == null) ? 0 : rptId.hashCode());
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

		buffer.append("rptId=[").append(rptId).append("] ");
		buffer.append("staffId=[").append(staffId).append("] ");

		return buffer.toString();
	}
}
