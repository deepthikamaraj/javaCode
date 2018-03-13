package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TClassTypeId Pojo. 
 *
 */
@Embeddable
public class TClassTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;
	@NotNull
	@Column(name = "class_type_id", nullable = false, length = 255)
	private Integer classTypeId;

	/**
	 *
	 * @generated
	 */
	public TClassTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLocaleId() {
		return this.localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setClassTypeId(final Integer classTypeId) {
		this.classTypeId = classTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getClassTypeId() {
		return this.classTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TClassTypeId)) {
			return false;
		}
		TClassTypeId castOther = (TClassTypeId) other;
		return (this.localeId.equals(castOther.localeId)) &&

		(this.classTypeId.equals(castOther.classTypeId))

		;

	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result + ((classTypeId == null) ? 0 : classTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("localeId=[").append(localeId).append("] ");
		buffer.append("classTypeId=[").append(classTypeId).append("] ");

		return buffer.toString();
	}
}
