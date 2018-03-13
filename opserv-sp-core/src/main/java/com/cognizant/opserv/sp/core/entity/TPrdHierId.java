package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPrdHierId Pojo. 
 *
 */
@Embeddable
public class TPrdHierId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "group_id", nullable = false, length = 255)
	private Integer groupId;
	@NotNull
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;
	/**
	 *
	 * @generated
	 */
	public TPrdHierId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setGroupId(final Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGroupId() {
		return this.groupId;
	}



	public Long getPrdId() {
		return prdId;
	}

	public void setPrdId(Long prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPrdHierId)) {
			return false;
		}
		TPrdHierId castOther = (TPrdHierId) other;
		return (this.groupId.equals(castOther.groupId)) &&

		(this.prdId.equals(castOther.prdId))

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
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((prdId == null) ? 0 : prdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("groupId=[").append(groupId).append("] ");
		buffer.append("prdId=[").append(prdId).append("] ");

		return buffer.toString();
	}
}
