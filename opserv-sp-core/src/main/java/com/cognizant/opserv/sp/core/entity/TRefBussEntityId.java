package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRefBussEntityId Pojo. 
 *
 */
@Embeddable
public class TRefBussEntityId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "prn_entity_id", nullable = false, length = 255)
	private Integer prnEntityId;
	@NotNull
	@Column(name = "entity_id", nullable = false, length = 255)
	private Integer entityId;

	/**
	 *
	 * @generated
	 */
	public TRefBussEntityId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrnEntityId(final Integer prnEntityId) {
		this.prnEntityId = prnEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrnEntityId() {
		return this.prnEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setEntityId(final Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getEntityId() {
		return this.entityId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRefBussEntityId)) {
			return false;
		}
		TRefBussEntityId castOther = (TRefBussEntityId) other;
		return (this.prnEntityId.equals(castOther.prnEntityId)) &&

		(this.entityId.equals(castOther.entityId))

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
		result = prime * result + ((prnEntityId == null) ? 0 : prnEntityId.hashCode());
		result = prime * result + ((entityId == null) ? 0 : entityId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prnEntityId=[").append(prnEntityId).append("] ");
		buffer.append("entityId=[").append(entityId).append("] ");

		return buffer.toString();
	}
}
