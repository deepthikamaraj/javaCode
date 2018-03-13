package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class TEmpTypeId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "emp_type_id", nullable = false, length = 255)
	private Integer empTypeId;
	
	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public Integer getEmpTypeId() {
		return empTypeId;
	}

	public void setEmpTypeId(Integer empTypeId) {
		this.empTypeId = empTypeId;
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
				+ ((empTypeId == null) ? 0 : empTypeId.hashCode());
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
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
		TEmpTypeId other = (TEmpTypeId) obj;
		if (empTypeId == null) {
			if (other.empTypeId != null)
				return false;
		} else if (!empTypeId.equals(other.empTypeId))
			return false;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TEmpTypeId [empTypeId=" + empTypeId + ", localeId=" + localeId
				+ "]";
	}

	public TEmpTypeId(){
		
	}
}
