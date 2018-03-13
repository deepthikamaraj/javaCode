package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TCustCategoryId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(name = "category_id", nullable = false, length = 255)
	private Integer custCategoryId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public Integer getCustCategoryId() {
		return custCategoryId;
	}

	@Override
	public String toString() {
		return "TCustCategoryId [custCategoryId=" + custCategoryId
				+ ", localeId=" + localeId + "]";
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custCategoryId == null) ? 0 : custCategoryId.hashCode());
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
		TCustCategoryId other = (TCustCategoryId) obj;
		if (custCategoryId == null) {
			if (other.custCategoryId != null)
				return false;
		} else if (!custCategoryId.equals(other.custCategoryId))
			return false;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		return true;
	}

	public void setCustCategoryId(Integer custCategoryId) {
		this.custCategoryId = custCategoryId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	/*public TCustCategoryId(){
		
	}*/

}
