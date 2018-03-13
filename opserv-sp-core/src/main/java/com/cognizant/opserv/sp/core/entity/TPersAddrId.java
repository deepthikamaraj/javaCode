package com.cognizant.opserv.sp.core.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPersAddrId Pojo. 
 *
 */
@Embeddable
public class TPersAddrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;
	@NotNull
	@Column(name = "addr_id", nullable = false, length = 255)
	private Integer addrId;

	/**
	 *
	 * @generated
	 */
	public TPersAddrId() {
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
	public void setAddrId(final Integer addrId) {
		this.addrId = addrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAddrId() {
		return this.addrId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPersAddrId)) {
			return false;
		}
		TPersAddrId castOther = (TPersAddrId) other;
		return (this.staffId.equals(castOther.staffId)) &&

		(this.addrId.equals(castOther.addrId))

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
		result = prime * result + ((addrId == null) ? 0 : addrId.hashCode());
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
		buffer.append("addrId=[").append(addrId).append("] ");

		return buffer.toString();
	}
}
