package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TPrdHierConfigId Pojo. 
 *
 */
@Embeddable
public class TPrdHierConfigId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prd_config_id", nullable = false, length = 255)
	private Integer prdConfigId;
	@NotNull
	@Column(name = "hier_level_id", nullable = false, length = 255)
	private Integer hierLevelId;

	/**
	 *
	 * @generated
	 */
	public TPrdHierConfigId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrdConfigId(final Integer prdConfigId) {
		this.prdConfigId = prdConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrdConfigId() {
		return this.prdConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setHierLevelId(final Integer hierLevelId) {
		this.hierLevelId = hierLevelId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getHierLevelId() {
		return this.hierLevelId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPrdHierConfigId)) {
			return false;
		}
		TPrdHierConfigId castOther = (TPrdHierConfigId) other;
		return (this.prdConfigId.equals(castOther.prdConfigId)) &&

		(this.hierLevelId.equals(castOther.hierLevelId))

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
		result = prime * result + ((prdConfigId == null) ? 0 : prdConfigId.hashCode());
		result = prime * result + ((hierLevelId == null) ? 0 : hierLevelId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdConfigId=[").append(prdConfigId).append("] ");
		buffer.append("hierLevelId=[").append(hierLevelId).append("] ");

		return buffer.toString();
	}
}
