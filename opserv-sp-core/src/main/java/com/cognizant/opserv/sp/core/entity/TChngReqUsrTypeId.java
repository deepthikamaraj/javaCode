package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TChngReqUsrTypeId Pojo. 
 *
 */
@Embeddable
public class TChngReqUsrTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "usr_type_id", nullable = false, length = 255)
	private Integer usrTypeId;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TChngReqUsrTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setUsrTypeId(final Integer usrTypeId) {
		this.usrTypeId = usrTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUsrTypeId() {
		return this.usrTypeId;
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
		if (!(other instanceof TChngReqUsrTypeId)) {
			return false;
		}
		TChngReqUsrTypeId castOther = (TChngReqUsrTypeId) other;
		return (this.usrTypeId.equals(castOther.usrTypeId)) &&

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
		result = prime * result + ((usrTypeId == null) ? 0 : usrTypeId.hashCode());
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

		buffer.append("usrTypeId=[").append(usrTypeId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
