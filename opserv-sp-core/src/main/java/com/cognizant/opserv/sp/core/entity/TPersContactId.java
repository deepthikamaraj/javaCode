package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPersContactId Pojo. 
 *
 */
@Embeddable
public class TPersContactId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;
	@NotNull
	@Column(name = "pers_contact_id", nullable = false, length = 255)
	private Integer persContactId;

	/**
	 *
	 * @generated
	 */
	public TPersContactId() {
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
	public void setPersContactId(final Integer persContactId) {
		this.persContactId = persContactId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPersContactId() {
		return this.persContactId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPersContactId)) {
			return false;
		}
		TPersContactId castOther = (TPersContactId) other;
		return (this.staffId.equals(castOther.staffId)) &&

		(this.persContactId.equals(castOther.persContactId))

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
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		result = prime * result + ((persContactId == null) ? 0 : persContactId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("staffId=[").append(staffId).append("] ");
		buffer.append("persContactId=[").append(persContactId).append("] ");

		return buffer.toString();
	}
}
