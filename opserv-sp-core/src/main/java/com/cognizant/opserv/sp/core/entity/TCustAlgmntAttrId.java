package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCustAlgmntAttrId Pojo. 
 *
 */
@Embeddable
public class TCustAlgmntAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;
	@NotNull
	@Column(name = "cust_algmnt_id", nullable = false, length = 255)
	private Long custAlgmntId;

	/**
	 *
	 * @generated
	 */
	/*public TCustAlgmntAttrId() {
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
	public void setCustAlgmntId(final Long custAlgmntId) {
		this.custAlgmntId = custAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCustAlgmntId() {
		return this.custAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCustAlgmntAttrId)) {
			return false;
		}
		TCustAlgmntAttrId castOther = (TCustAlgmntAttrId) other;
		return (this.attrId.equals(castOther.attrId)) &&

		(this.custAlgmntId.equals(castOther.custAlgmntId))

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
		result = prime * result + ((custAlgmntId == null) ? 0 : custAlgmntId.hashCode());
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
		buffer.append("custAlgmntId=[").append(custAlgmntId).append("] ");

		return buffer.toString();
	}
}
