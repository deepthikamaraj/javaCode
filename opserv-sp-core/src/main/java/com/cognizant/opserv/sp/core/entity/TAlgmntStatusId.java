package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TAlgmntStatusId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;
	
	/**
	 *
	 * @generated
	 */
	public TAlgmntStatusId() {
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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
				+ ((statusId == null) ? 0 : statusId.hashCode());
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
		TAlgmntStatusId other = (TAlgmntStatusId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		return true;
	}

}
