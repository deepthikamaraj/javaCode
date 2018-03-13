package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TAddrTypeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "addr_type_id", nullable = false, length = 255)
	private Integer addrTypeId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public TAddrTypeId(){
		
	}

	public Integer getAddrTypeId() {
		return addrTypeId;
	}

	public void setAddrTypeId(Integer addrTypeId) {
		this.addrTypeId = addrTypeId;
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
				+ ((addrTypeId == null) ? 0 : addrTypeId.hashCode());
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
		TAddrTypeId other = (TAddrTypeId) obj;
		if (addrTypeId == null) {
			if (other.addrTypeId != null)
				return false;
		} else if (!addrTypeId.equals(other.addrTypeId))
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
		return "TAddrTypeId [addrTypeId=" + addrTypeId + ", localeId="
				+ localeId + "]";
	}
	
	

}
