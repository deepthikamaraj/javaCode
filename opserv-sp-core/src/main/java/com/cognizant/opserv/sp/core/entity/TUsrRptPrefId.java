package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TUsrRptPrefId Pojo. 
 *
 */
@Embeddable
public class TUsrRptPrefId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "staff_id", nullable = false, length = 255)
	private Integer staffId;
	
	//@GenericGenerator(name="helio",strategy="hilo")
	//@GeneratedValue(generator="helio")
	@NotNull
	@Column(name = "pref_id", nullable = false, length = 255)
	private Integer prefId;

	/**
	 * 
	 * @generated
	 */
	public void setStaffId(final Integer staffId) {
		this.staffId = staffId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStaffId() {
		return this.staffId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setPrefId(final Integer prefId) {
		this.prefId = prefId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrefId() {
		return this.prefId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TUsrRptPrefId)) {
			return false;
		}
		TUsrRptPrefId castOther = (TUsrRptPrefId) other;
		return (this.staffId.equals(castOther.staffId)) &&

		(this.prefId.equals(castOther.prefId))

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
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		result = prime * result + ((prefId == null) ? 0 : prefId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("staffId=[").append(staffId).append("] ");
		buffer.append("prefId=[").append(prefId).append("] ");

		return buffer.toString();
	}
}
