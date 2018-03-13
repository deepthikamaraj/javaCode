package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TSalesPosTypeId Pojo. 
 *
 */
@Embeddable
public class TSalesPosTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;
	@NotNull
	@Column(name = "sales_pos_type_id", nullable = false, length = 255)
	private Integer salesPosTypeId;

	/**
	 *
	 * @generated
	 */
	public TSalesPosTypeId() {
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
	public void setSalesPosTypeId(final Integer salesPosTypeId) {
		this.salesPosTypeId = salesPosTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSalesPosTypeId() {
		return this.salesPosTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TSalesPosTypeId)) {
			return false;
		}
		TSalesPosTypeId castOther = (TSalesPosTypeId) other;
		return (this.localeId.equals(castOther.localeId)) &&

		(this.salesPosTypeId.equals(castOther.salesPosTypeId))

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
		result = prime * result + ((salesPosTypeId == null) ? 0 : salesPosTypeId.hashCode());
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
		buffer.append("salesPosTypeId=[").append(salesPosTypeId).append("] ");

		return buffer.toString();
	}
}
