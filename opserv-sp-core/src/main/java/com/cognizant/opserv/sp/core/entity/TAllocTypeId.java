package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TAllocTypeId Pojo. 
 *
 */
@Embeddable
public class TAllocTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "alloc_type_id", nullable = false, length = 255)
	private Integer allocTypeId;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TAllocTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setAllocTypeId(final Integer allocTypeId) {
		this.allocTypeId = allocTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAllocTypeId() {
		return this.allocTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLocaleId() {
		return this.localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAllocTypeId)) {
			return false;
		}
		TAllocTypeId castOther = (TAllocTypeId) other;
		return (this.allocTypeId.equals(castOther.allocTypeId)) &&

		(this.localeId.equals(castOther.localeId))

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
		result = prime * result + ((allocTypeId == null) ? 0 : allocTypeId.hashCode());
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("allocTypeId=[").append(allocTypeId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
