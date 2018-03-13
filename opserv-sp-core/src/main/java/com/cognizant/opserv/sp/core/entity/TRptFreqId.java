package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptFreqId Pojo. 
 *
 */
@Embeddable
public class TRptFreqId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rpt_freq_id", nullable = false, length = 255)
	private Integer rptFreqId;
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TRptFreqId() {
	}

	public Integer getRptFreqId() {
		return rptFreqId;
	}

	public void setRptFreqId(Integer rptFreqId) {
		this.rptFreqId = rptFreqId;
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
				+ ((rptFreqId == null) ? 0 : rptFreqId.hashCode());
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
		TRptFreqId other = (TRptFreqId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (rptFreqId == null) {
			if (other.rptFreqId != null)
				return false;
		} else if (!rptFreqId.equals(other.rptFreqId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptFreqId [rptFreqId=" + rptFreqId + ", localeId=" + localeId
				+ "]";
	}
	
}

