package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustContactId Pojo. 
 *
 */
@Embeddable
public class TCustContactId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	@NotNull
	@Column(name = "cust_contact_id", nullable = false, length = 255)
	private Integer custContactId;

	/**
	 *
	 * @generated
	 */
	/*public TCustContactId() {
	}
*/
	/**
	 * 
	 * @generated
	 */
	public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustId() {
		return this.custId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setCustContactId(final Integer custContactId) {
		this.custContactId = custContactId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustContactId() {
		return this.custContactId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustContactId)) {
			return false;
		}
		TCustContactId castOther = (TCustContactId) other;
		return (this.custId.equals(castOther.custId)) &&

		(this.custContactId.equals(castOther.custContactId))

		;

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((custContactId == null) ? 0 : custContactId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("custContactId=[").append(custContactId).append("] ");

		return buffer.toString();
	}
}
