package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TMtrValMsgId Pojo. 
 *
 */
@Embeddable
public class TMtrValMsgId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TMtrValMsgId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setMtrId(final Integer mtrId) {
		this.mtrId = mtrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrId() {
		return this.mtrId;
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
		if (!(other instanceof TMtrValMsgId)) {
			return false;
		}
		TMtrValMsgId castOther = (TMtrValMsgId) other;
		return (this.mtrId.equals(castOther.mtrId)) &&

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
		result = prime * result + ((mtrId == null) ? 0 : mtrId.hashCode());
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

		buffer.append("mtrId=[").append(mtrId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
