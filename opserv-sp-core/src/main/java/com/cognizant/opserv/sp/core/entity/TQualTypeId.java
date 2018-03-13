package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TQualTypeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "qual_id", nullable = false, length = 255)
	private Integer qualId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	@Override
	public String toString() {
		return "TQualTypeId [qualId=" + qualId + ", localeId=" + localeId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result + ((qualId == null) ? 0 : qualId.hashCode());
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
		TQualTypeId other = (TQualTypeId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (qualId == null) {
			if (other.qualId != null)
				return false;
		} else if (!qualId.equals(other.qualId))
			return false;
		return true;
	}

	public Integer getQualId() {
		return qualId;
	}

	public void setQualId(Integer qualId) {
		this.qualId = qualId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	public TQualTypeId(){
		
	}
	
}
