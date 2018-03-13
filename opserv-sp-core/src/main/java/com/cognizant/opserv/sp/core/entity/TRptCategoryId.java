package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRptCategoryId Pojo. 
 *
 */
@Embeddable
public class TRptCategoryId implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@NotNull
	@Column(name = "rpt_category_id", nullable = false, length = 255)
	private Integer rptCategoryId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;
	
	public TRptCategoryId(){		
	}

	public Integer getRptCategoryId() {
		return rptCategoryId;
	}

	public void setRptCategoryId(Integer rptCategoryId) {
		this.rptCategoryId = rptCategoryId;
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
				+ ((rptCategoryId == null) ? 0 : rptCategoryId.hashCode());
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
		TRptCategoryId other = (TRptCategoryId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (rptCategoryId == null) {
			if (other.rptCategoryId != null)
				return false;
		} else if (!rptCategoryId.equals(other.rptCategoryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptCategoryId [rptCategoryId=" + rptCategoryId + ", localeId="
				+ localeId + "]";
	}
}
