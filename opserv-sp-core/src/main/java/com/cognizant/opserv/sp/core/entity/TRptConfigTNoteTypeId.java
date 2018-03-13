package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;

/** 
 * The primary key class for the TRptConfigTNoteTypeId Pojo. 
 *
 */
@Embeddable
@Audited
public class TRptConfigTNoteTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "note_type_id", nullable = false, length = 255)
	private Integer noteTypeId;
	@NotNull
	@Column(name = "rpt_config_id", nullable = false, length = 255)
	private Integer rptConfigId;

	/**
	 *
	 * @generated
	 */
	public TRptConfigTNoteTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setNoteTypeId(final Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getNoteTypeId() {
		return this.noteTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRptConfigId(final Integer rptConfigId) {
		this.rptConfigId = rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptConfigId() {
		return this.rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TRptConfigTNoteTypeId)) {
			return false;
		}
		TRptConfigTNoteTypeId castOther = (TRptConfigTNoteTypeId) other;
		return (this.noteTypeId.equals(castOther.noteTypeId)) &&

		(this.rptConfigId.equals(castOther.rptConfigId))

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
		result = prime * result + ((noteTypeId == null) ? 0 : noteTypeId.hashCode());
		result = prime * result + ((rptConfigId == null) ? 0 : rptConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("noteTypeId=[").append(noteTypeId).append("] ");
		buffer.append("rptConfigId=[").append(rptConfigId).append("] ");

		return buffer.toString();
	}
}
