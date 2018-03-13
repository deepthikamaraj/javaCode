package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TPrdHierTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "hier_type_id", nullable = false, length = 255)
	private Integer hierTypeId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 50)
	private String localeId;
	
	public TPrdHierTypeId(){		
	}

	public Integer getHierTypeId() {
		return hierTypeId;
	}

	public void setHierTypeId(Integer hierTypeId) {
		this.hierTypeId = hierTypeId;
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
				+ ((hierTypeId == null) ? 0 : hierTypeId.hashCode());
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
		TPrdHierTypeId other = (TPrdHierTypeId) obj;
		if (hierTypeId == null) {
			if (other.hierTypeId != null)
				return false;
		} else if (!hierTypeId.equals(other.hierTypeId))
			return false;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		return true;
	}
	
	

}
