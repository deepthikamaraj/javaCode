package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptConfigStatusId Pojo. 
 *
 */
@Embeddable
public class TRptConfigStatusId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_config_status_id", nullable = false, length = 255)
	private Integer rptConfigStatusId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TRptConfigStatusId() {
	}

	public Integer getRptConfigStatusId() {
		return rptConfigStatusId;
	}

	public void setRptConfigStatusId(Integer rptConfigStatusId) {
		this.rptConfigStatusId = rptConfigStatusId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
		result = prime
				* result
				+ ((rptConfigStatusId == null) ? 0 : rptConfigStatusId
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TRptConfigStatusId other = (TRptConfigStatusId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (rptConfigStatusId == null) {
			if (other.rptConfigStatusId != null)
				return false;
		} else if (!rptConfigStatusId.equals(other.rptConfigStatusId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptConfigStatusId [rptConfigStatusId=" + rptConfigStatusId
				+ ", localeId=" + localeId + "]";
	}

	
}

