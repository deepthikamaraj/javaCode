package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCommPublisherId Pojo. 
 *
 */
@Embeddable
public class TCommPublisherId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "comm_id", nullable = false, length = 255)
	private Long commId;
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	/**
	 *
	 * @generated
	 */
	public TCommPublisherId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setCommId(final Long commId) {
		this.commId = commId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCommId() {
		return this.commId;
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
		if (!(other instanceof TCommPublisherId)) {
			return false;
		}
		TCommPublisherId castOther = (TCommPublisherId) other;
		return (this.commId.equals(castOther.commId)) &&

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
		result = prime * result + ((commId == null) ? 0 : commId.hashCode());
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

		buffer.append("commId=[").append(commId).append("] ");
		buffer.append("staffId=[").append(staffId).append("] ");

		return buffer.toString();
	}
}
