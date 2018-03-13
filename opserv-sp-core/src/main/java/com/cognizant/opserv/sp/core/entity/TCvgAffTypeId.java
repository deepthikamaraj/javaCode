package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TCvgAffTypeId Pojo. 
 *
 */
@Embeddable
public class TCvgAffTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "aff_type_id", nullable = false, length = 255)
	private Integer cvgAffTypeId;
	
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	
	/**
	 * @return the cvgAffTypeId
	 */
	public Integer getCvgAffTypeId() {
		return cvgAffTypeId;
	}

	/**
	 * @param cvgAffTypeId the cvgAffTypeId to set
	 */
	public void setCvgAffTypeId(Integer cvgAffTypeId) {
		this.cvgAffTypeId = cvgAffTypeId;
	}

	/**
	 * @return the localeId
	 */
	public String getLocaleId() {
		return localeId;
	}

	/**
	 * @param localeId the localeId to set
	 */
	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	/**
	 *
	 * @generated
	 */
	public TCvgAffTypeId() {
	}

	
}
