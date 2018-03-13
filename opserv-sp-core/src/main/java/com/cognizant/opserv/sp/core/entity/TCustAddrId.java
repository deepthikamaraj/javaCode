package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/** 
 * The primary key class for the TCustAddrId Pojo. 
 *
 */
@Embeddable
public class TCustAddrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/*@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;
	@NotNull
	@Column(name = "addr_id", nullable = false, length = 255)
	private Integer addrId;*/

	/**
	 *
	 * @generated
	 */
	/*public TCustAddrId() {
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getCustId() {
		return this.custId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setAddrId(final Integer addrId) {
		this.addrId = addrId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getAddrId() {
		return this.addrId;
	}

	*//**
	 * 
	 * @generated
	 */
	/*public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustAddrId)) {
			return false;
		}
		TCustAddrId castOther = (TCustAddrId) other;
		return (this.custId.equals(castOther.custId)) &&

		(this.addrId.equals(castOther.addrId))

		;

	}*/

	/**
	 * @generated
	 * 
	 */
	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		result = prime * result + ((addrId == null) ? 0 : addrId.hashCode());
		return result;
	}
*/
	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	/*public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("addrId=[").append(addrId).append("] ");

		return buffer.toString();
	}*/
}
