package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCountryDivId Pojo. 
 *
 */
@Embeddable
public class TCountryDivId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "div_id", nullable = false, length = 255)
	private Integer divId;
	@NotNull
	@Column(name = "country_id", nullable = false, length = 255)
	private Integer countryId;

	/**
	 *
	 * @generated
	 */
	public TCountryDivId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setDivId(final Integer divId) {
		this.divId = divId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDivId() {
		return this.divId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setCountryId(final Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCountryId() {
		return this.countryId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCountryDivId)) {
			return false;
		}
		TCountryDivId castOther = (TCountryDivId) other;
		return (this.divId.equals(castOther.divId)) &&

		(this.countryId.equals(castOther.countryId))

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
		result = prime * result + ((divId == null) ? 0 : divId.hashCode());
		result = prime * result + ((countryId == null) ? 0 : countryId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("divId=[").append(divId).append("] ");
		buffer.append("countryId=[").append(countryId).append("] ");

		return buffer.toString();
	}
}
