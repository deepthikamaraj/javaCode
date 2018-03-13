package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TWdgId Pojo. 
 *
 */
@Embeddable
public class TWdgId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;
	@NotNull
	@Column(name = "wdg_id", nullable = false, length = 255)
	private Integer wdgId;

	/**
	 *
	 * @generated
	 */
	public TWdgId() {
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
	public void setWdgId(final Integer wdgId) {
		this.wdgId = wdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getWdgId() {
		return this.wdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TWdgId)) {
			return false;
		}
		TWdgId castOther = (TWdgId) other;
		return this.localeId.equals(castOther.localeId) &&	this.wdgId.equals(castOther.wdgId);

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
		result = prime * result + ((wdgId == null) ? 0 : wdgId.hashCode());
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
		buffer.append("wdgId=[").append(wdgId).append("] ");

		return buffer.toString();
	}
}
