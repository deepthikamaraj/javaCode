package com.cognizant.opserv.sp.core.entity;




import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * The primary key class for the TMtrValueTypeId Pojo. 
 *
 */
@Embeddable
public class TMtrValueTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "mtr_value_type_id", nullable = false, length = 255)
	private Integer mtrValueTypeId;
	@NotEmpty
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 *
	 * @generated
	 */
	public TMtrValueTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setMtrValueTypeId(final Integer mtrValueTypeId) {
		this.mtrValueTypeId = mtrValueTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrValueTypeId() {
		return this.mtrValueTypeId;
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
		if (!(other instanceof TMtrValueTypeId)) {
			return false;
		}
		TMtrValueTypeId castOther = (TMtrValueTypeId) other;
		return (this.mtrValueTypeId.equals(castOther.mtrValueTypeId)) &&

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
		result = prime * result + ((mtrValueTypeId == null) ? 0 : mtrValueTypeId.hashCode());
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

		buffer.append("mtrValueTypeId=[").append(mtrValueTypeId).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

		return buffer.toString();
	}
}
