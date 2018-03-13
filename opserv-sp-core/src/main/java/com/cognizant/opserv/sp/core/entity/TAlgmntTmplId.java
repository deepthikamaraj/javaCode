package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TAlgmntTmplId Pojo. 
 *
 */
@Embeddable
public class TAlgmntTmplId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "tmpl_id", nullable = false, length = 255)
	private Integer tmplId;
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 *
	 * @generated
	 */
	public TAlgmntTmplId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setTmplId(final Integer tmplId) {
		this.tmplId = tmplId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTmplId() {
		return this.tmplId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAlgmntTmplId)) {
			return false;
		}
		TAlgmntTmplId castOther = (TAlgmntTmplId) other;
		return (this.tmplId.equals(castOther.tmplId)) &&

		(this.algmntId.equals(castOther.algmntId))

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
		result = prime * result + ((tmplId == null) ? 0 : tmplId.hashCode());
		result = prime * result + ((algmntId == null) ? 0 : algmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tmplId=[").append(tmplId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");

		return buffer.toString();
	}
}
