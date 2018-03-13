package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TCustTypeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "cust_type_id", nullable = false, length = 255)
	private Integer custTypeId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	/*public TCustTypeId(){
		
	}*/

	public Integer getCustTypeId() {
		return custTypeId;
	}

	public void setCustTypeId(Integer custTypeId) {
		this.custTypeId = custTypeId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	@Override
	public int hashCode() {
	 int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custTypeId == null) ? 0 : custTypeId.hashCode());
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
		TCustTypeId other = (TCustTypeId) obj;
		if (custTypeId == null) {
			if (other.custTypeId != null)
				return false;
		} else if (!custTypeId.equals(other.custTypeId))
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
		return "TCustTypeId [custTypeId=" + custTypeId + ", localeId="
				+ localeId + "]";
	}

}
