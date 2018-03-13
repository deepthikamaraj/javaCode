package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustAttrId Pojo. 
 *
 */
@Embeddable
public class TCustAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;

	/**
	 *
	 * @generated
	 */
	/*public TCustAttrId() {
	}*/

	/**
	 * 
	 * @generated
	 */
	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAttrId() {
		return this.attrId;
	}

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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustAttrId)) {
			return false;
		}
		TCustAttrId castOther = (TCustAttrId) other;
		return (this.attrId.equals(castOther.attrId)) &&

		(this.custId.equals(castOther.custId))

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
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("attrId=[").append(attrId).append("] ");
		buffer.append("custId=[").append(custId).append("] ");

		return buffer.toString();
	}
}
