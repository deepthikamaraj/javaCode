package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TEmpAlgmntAttrId Pojo. 
 *
 */
@Embeddable
public class TEmpAlgmntAttrId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "emp_algmnt_id", nullable = false, length = 225)
	private Long empAlgmntId;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
	private Long attrId;

	/**
	 *
	 * @generated
	 */
	public TEmpAlgmntAttrId() {
	}

	

	public Long getEmpAlgmntId() {
		return empAlgmntId;
	}



	public void setEmpAlgmntId(Long empAlgmntId) {
		this.empAlgmntId = empAlgmntId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TEmpAlgmntAttrId)) {
			return false;
		}
		TEmpAlgmntAttrId castOther = (TEmpAlgmntAttrId) other;
		return (this.empAlgmntId.equals(castOther.empAlgmntId)) &&

		(this.attrId.equals(castOther.attrId))

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
		result = prime * result + ((empAlgmntId == null) ? 0 : empAlgmntId.hashCode());
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("empAlgmntId=[").append(empAlgmntId).append("] ");
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
