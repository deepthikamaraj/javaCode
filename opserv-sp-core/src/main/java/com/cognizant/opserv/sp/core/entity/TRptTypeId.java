package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptTypeId Pojo. 
 *
 */
@Embeddable
public class TRptTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_type_id", nullable = false, length = 255)
	private Integer rptTypeId;

	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TRptTypeId() {
	}

	public Integer getRptTypeId() {
		return rptTypeId;
	}

	public void setRptTypeId(Integer rptTypeId) {
		this.rptTypeId = rptTypeId;
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
		result = prime * result
				+ ((rptTypeId == null) ? 0 : rptTypeId.hashCode());
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
		TRptTypeId other = (TRptTypeId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (rptTypeId == null) {
			if (other.rptTypeId != null)
				return false;
		} else if (!rptTypeId.equals(other.rptTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptTypeId [rptTypeId=" + rptTypeId + ", localeId=" + localeId
				+ "]";
	}

	
}

