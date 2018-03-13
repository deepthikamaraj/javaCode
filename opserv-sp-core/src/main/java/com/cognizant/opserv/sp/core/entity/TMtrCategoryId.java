package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TMtrCategoryId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@NotNull
	@Column(name = "mtr_category_id", nullable = false, length = 255)
	private Integer mtrCategoryId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public TMtrCategoryId(){
		
	}
		
	public Integer getMtrCategoryId() {
		return mtrCategoryId;
	}

	public void setMtrCategoryId(Integer mtrCategoryId) {
		this.mtrCategoryId = mtrCategoryId;
	}

	
	
	@Override
	public String toString() {
		return "TNoteTypeId [noteTypeId=" + mtrCategoryId + ", locale=" + localeId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result
				+ ((mtrCategoryId == null) ? 0 : mtrCategoryId.hashCode());
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
		TMtrCategoryId other = (TMtrCategoryId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (mtrCategoryId == null) {
			if (other.mtrCategoryId != null)
				return false;
		} else if (!mtrCategoryId.equals(other.mtrCategoryId))
			return false;
		return true;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
	
	
}

