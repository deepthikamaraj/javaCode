package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TNoteStatusId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "note_status_id", nullable = false, length = 255)
	private Integer noteStatusId;
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 255)
	private String localeId;
	
	public TNoteStatusId(){		
	}

	public Integer getNoteStatusId() {
		return noteStatusId;
	}

	public void setNoteStatusId(Integer noteStatusId) {
		this.noteStatusId = noteStatusId;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result
				+ ((noteStatusId == null) ? 0 : noteStatusId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TNoteStatusId other = (TNoteStatusId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (noteStatusId == null) {
			if (other.noteStatusId != null)
				return false;
		} else if (!noteStatusId.equals(other.noteStatusId))
			return false;
		return true;
	}
	

}
