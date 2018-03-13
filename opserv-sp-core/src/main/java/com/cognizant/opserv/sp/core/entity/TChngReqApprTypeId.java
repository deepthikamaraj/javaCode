package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TChngReqApprTypeId Pojo. 
 *
 */
@Embeddable
public class TChngReqApprTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;
	@NotNull
	@Column(name = "appr_type_id", nullable = false, length = 255)
	private Integer apprTypeId;

	/**
	 *
	 * @generated
	 */
	public TChngReqApprTypeId() {
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
	public void setApprTypeId(final Integer apprTypeId) {
		this.apprTypeId = apprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getApprTypeId() {
		return this.apprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqApprTypeId)) {
			return false;
		}
		TChngReqApprTypeId castOther = (TChngReqApprTypeId) other;
		return (this.localeId.equals(castOther.localeId)) &&

		(this.apprTypeId.equals(castOther.apprTypeId))

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
		result = prime * result + ((apprTypeId == null) ? 0 : apprTypeId.hashCode());
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
		buffer.append("apprTypeId=[").append(apprTypeId).append("] ");

		return buffer.toString();
	}
}
