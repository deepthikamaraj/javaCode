package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class TNoteTypeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@NotNull
	@Column(name = "note_type_id", nullable = false, length = 255)
	private Integer noteTypeId;

	@NotEmpty
	@Column(name="locale_id", nullable=false, length=20)
	private String localeId;

	public TNoteTypeId(){
		
	}
		
	public Integer getNoteTypeId() {
		return noteTypeId;
	}

	public void setNoteTypeId(Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}

	
	
	@Override
	public String toString() {
		return "TNoteTypeId [noteTypeId=" + noteTypeId + ", locale=" + localeId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localeId == null) ? 0 : localeId.hashCode());
		result = prime * result
				+ ((noteTypeId == null) ? 0 : noteTypeId.hashCode());
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
		TNoteTypeId other = (TNoteTypeId) obj;
		if (localeId == null) {
			if (other.localeId != null)
				return false;
		} else if (!localeId.equals(other.localeId))
			return false;
		if (noteTypeId == null) {
			if (other.noteTypeId != null)
				return false;
		} else if (!noteTypeId.equals(other.noteTypeId))
			return false;
		return true;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}
	
	
}
