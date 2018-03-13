package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TPrtTypeId Pojo. 
 *
 */
@Embeddable
public class TPrtTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;
	@NotNull
	@Column(name = "prt_type_id", nullable = false, length = 255)
	private Integer prtTypeId;

	/**
	 *
	 * @generated
	 */
	public TPrtTypeId() {
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
	public void setPrtTypeId(final Integer prtTypeId) {
		this.prtTypeId = prtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrtTypeId() {
		return this.prtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TPrtTypeId)) {
			return false;
		}
		TPrtTypeId castOther = (TPrtTypeId) other;
		return (this.localeId.equals(castOther.localeId)) &&

		(this.prtTypeId.equals(castOther.prtTypeId))

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
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result + ((prtTypeId == null) ? 0 : prtTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("localeId=[").append(localeId).append("] ");
		buffer.append("prtTypeId=[").append(prtTypeId).append("] ");

		return buffer.toString();
	}
}
