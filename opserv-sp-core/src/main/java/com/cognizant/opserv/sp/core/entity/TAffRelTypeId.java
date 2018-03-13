package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TAffRelTypeId Pojo. 
 *
 */
@Embeddable
public class TAffRelTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "rel_type_id", nullable = false, length = 255)
	private Integer relTypeId;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TAffRelTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setRelTypeId(final Integer relTypeId) {
		this.relTypeId = relTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRelTypeId() {
		return this.relTypeId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAffRelTypeId)) {
			return false;
		}
		TAffRelTypeId castOther = (TAffRelTypeId) other;
		return (this.relTypeId.equals(castOther.relTypeId)) &&

		(this.localeId.equals(castOther.localeId))

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
		result = prime * result + ((relTypeId == null) ? 0 : relTypeId.hashCode());
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("relTypeId=[").append(relTypeId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
