package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TRecipientAttrId Pojo. 
 *
 */
@Embeddable
public class TAttrDataTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "attr_data_type_id", nullable = false, length = 255)
	private Integer attrDataTypeId;
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TAttrDataTypeId() {
	}

	public Integer getAttrDataTypeId() {
		return attrDataTypeId;
	}

	public void setAttrDataTypeId(Integer attrDataTypeId) {
		this.attrDataTypeId = attrDataTypeId;
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
				+ ((attrDataTypeId == null) ? 0 : attrDataTypeId.hashCode());
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
		TAttrDataTypeId other = (TAttrDataTypeId) obj;
		if (attrDataTypeId == null) {
			if (other.attrDataTypeId != null)
				return false;
		} else if (!attrDataTypeId.equals(other.attrDataTypeId))
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
		return "TAttrDataTypeId [attrDataTypeId=" + attrDataTypeId
				+ ", localeId=" + localeId + "]";
	}

	
	
}

