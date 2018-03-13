package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptStatusTypeId Pojo. 
 *
 */
@Embeddable
public class TRptStatusTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_status_type_id", nullable = false, length = 255)
	private Integer rptStatusTypeId;

	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TRptStatusTypeId() {
	}

	public Integer getRptStatusTypeId() {
		return rptStatusTypeId;
	}

	public void setRptStatusTypeId(Integer rptStatusTypeId) {
		this.rptStatusTypeId = rptStatusTypeId;
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
				+ ((rptStatusTypeId == null) ? 0 : rptStatusTypeId.hashCode());
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
		TRptStatusTypeId other = (TRptStatusTypeId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (rptStatusTypeId == null) {
			if (other.rptStatusTypeId != null)
				return false;
		} else if (!rptStatusTypeId.equals(other.rptStatusTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptStatusTypeId [rptStatusTypeId=" + rptStatusTypeId
				+ ", localeId=" + localeId + "]";
	}	
}

