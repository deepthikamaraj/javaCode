package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TCvgAffHierLevelId Pojo. 
 *
 */
@Embeddable
public class TCvgAffHierLevelId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "aff_hier_level_id", nullable = false, length = 255)
	private Integer affHierLevelId;
	
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	
	
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
	public TCvgAffHierLevelId() {
	}

	/**
	 * @return the affHierLevelId
	 */
	public Integer getAffHierLevelId() {
		return affHierLevelId;
	}

	/**
	 * @param affHierLevelId the affHierLevelId to set
	 */
	public void setAffHierLevelId(Integer affHierLevelId) {
		this.affHierLevelId = affHierLevelId;
	}

	
}
