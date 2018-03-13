package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TCommNoteTypeId Pojo. 
 *
 */
@Embeddable
public class TCommNoteTypeId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "note_type_id", nullable = false, length = 255)
	private Integer noteTypeId;
	@NotNull
	@Column(name = "comm_id", nullable = false, length = 255)
	private Long commId;

	/**
	 *
	 * @generated
	 */
	public TCommNoteTypeId() {
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
	public void setCommId(final Long commId) {
		this.commId = commId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCommId() {
		return this.commId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TCommNoteTypeId)) {
			return false;
		}
		TCommNoteTypeId castOther = (TCommNoteTypeId) other;
		return (this.noteTypeId.equals(castOther.noteTypeId)) &&

		(this.commId.equals(castOther.commId))

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
		result = prime * result + ((commId == null) ? 0 : commId.hashCode());
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
		buffer.append("commId=[").append(commId).append("] ");

		return buffer.toString();
	}
}
