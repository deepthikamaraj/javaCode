package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TEmpAttrId Pojo. 
 *
 */
@Embeddable
public class TEmpAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;
	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;

	/**
	 *
	 * @generated
	 */
	public TEmpAttrId() {
	}

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
		if (!(other instanceof TEmpAttrId)) {
			return false;
		}
		TEmpAttrId castOther = (TEmpAttrId) other;
		return (this.attrId.equals(castOther.attrId)) &&

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
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
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

		buffer.append("attrId=[").append(attrId).append("] ");
		buffer.append("staffId=[").append(staffId).append("] ");

		return buffer.toString();
	}
}
