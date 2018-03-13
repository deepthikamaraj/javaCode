package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TChngReqNoteTypeId Pojo. 
 *
 */
@Embeddable
public class TChngReqNoteTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "chng_req_config_id", nullable = false, length = 255)
	private Integer chngReqConfigId;
	@NotNull
	@Column(name = "note_type_id", nullable = false, length = 255)
	private Integer noteTypeId;

	/**
	 *
	 * @generated
	 */
	public TChngReqNoteTypeId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setChngReqConfigId(final Integer chngReqConfigId) {
		this.chngReqConfigId = chngReqConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getChngReqConfigId() {
		return this.chngReqConfigId;
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
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TChngReqNoteTypeId)) {
			return false;
		}
		TChngReqNoteTypeId castOther = (TChngReqNoteTypeId) other;
		return (this.chngReqConfigId.equals(castOther.chngReqConfigId)) &&

		(this.noteTypeId.equals(castOther.noteTypeId))

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
		result = prime * result + ((chngReqConfigId == null) ? 0 : chngReqConfigId.hashCode());
		result = prime * result + ((noteTypeId == null) ? 0 : noteTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqConfigId=[").append(chngReqConfigId).append("] ");
		buffer.append("noteTypeId=[").append(noteTypeId).append("] ");

		return buffer.toString();
	}
}
