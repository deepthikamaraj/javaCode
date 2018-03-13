package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TPrdPrtTypeId  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@NotNull
	@Column(name = "prd_prt_type_id", nullable = false, length = 255)
	private Integer prdPrtTypeId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public TPrdPrtTypeId(){
		
	}

	public Integer getPrdPrtTypeId() {
		return prdPrtTypeId;
	}

	public void setPrdPrtTypeId(Integer prdPrtTypeId) {
		this.prdPrtTypeId = prdPrtTypeId;
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
				+ ((prdPrtTypeId == null) ? 0 : prdPrtTypeId.hashCode());
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
		TPrdPrtTypeId other = (TPrdPrtTypeId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (prdPrtTypeId == null) {
			if (other.prdPrtTypeId != null)
				return false;
		} else if (!prdPrtTypeId.equals(other.prdPrtTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TPrdPrtTypeId [prdPrtTypeId=" + prdPrtTypeId + ", localeId="
				+ localeId + "]";
	}
	
	
}
