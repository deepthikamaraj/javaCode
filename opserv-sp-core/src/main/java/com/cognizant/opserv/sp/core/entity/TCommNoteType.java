package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/** 
 * JPA class representing TCommNoteType 
 * The corresponding mapping table is t_comm_note_type 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCommNoteTypes", query = "select myTCommNoteType from TCommNoteType myTCommNoteType"),
		@NamedQuery(name = "CountTCommNoteTypes", query = "Select Count(c) from TCommNoteType c"),
		@NamedQuery(name = "FindTCommNoteTypeByCommID", query = "select myTCommNoteType from TCommNoteType myTCommNoteType where myTCommNoteType.tCommNoteTypeId.commId = ?1 and myTCommNoteType.tenantId = ?2"),
	   /*@NamedQuery(name = "FindTCommNoteTypeByTNoteType", query = "select myTCommNoteType from TCommNoteType myTCommNoteType where myTCommNoteType.tNoteType = ?1 "),
		@NamedQuery(name = "CountTCommNoteTypesByTNoteType", query = "select Count(*) from TCommNoteType myTCommNoteType where myTCommNoteType.tNoteType = ?1 "),*/
		@NamedQuery(name = "FindTCommNoteTypeByTComm", query = "select myTCommNoteType from TCommNoteType myTCommNoteType where myTCommNoteType.tComm = ?1 "),
		@NamedQuery(name = "FindTCommNoteTypeByTCommAndNoteType", query = "select myTCommNoteType from TCommNoteType myTCommNoteType where myTCommNoteType.tComm.validityStartDt = ?1 and myTCommNoteType.tCommNoteTypeId.noteTypeId = ?2 and myTCommNoteType.tComm.tCommStatus.commStatusId = ?3"),//Email
		@NamedQuery(name = "CountTCommNoteTypesByTComm", query = "select Count(*) from TCommNoteType myTCommNoteType where myTCommNoteType.tComm = ?1 ") })
@Table(name = "t_comm_note_type")
public class TCommNoteType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCommNoteTypeId tCommNoteTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;
	
	
	
/*
	@ManyToOne
	@JoinColumn(name = "note_type_id", referencedColumnName = "note_type_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TNoteType tNoteType;*/

	public Character getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	@ManyToOne
	@JoinColumn(name = "comm_id", referencedColumnName = "comm_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TComm tComm;

	/**
	 *
	 * @generated
	 */
	public TCommNoteType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCommNoteTypeId(final TCommNoteTypeId tCommNoteTypeId) {
		this.tCommNoteTypeId = tCommNoteTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCommNoteTypeId getTCommNoteTypeId() {
		return this.tCommNoteTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTenantId(final Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TNoteType getTNoteType() {
		return this.tNoteType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTNoteType(final TNoteType tNoteType) {
		this.tNoteType = tNoteType;
		if (this.tNoteType != null && this.tNoteType.getNoteTypeId() != null) {

			this.tCommNoteTypeId.setNoteTypeId(this.tNoteType.getNoteTypeId());

		}

	}*/

	/**
	 * 
	 * @generated
	 */
	public TComm getTComm() {
		return this.tComm;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTComm(final TComm tComm) {
		this.tComm = tComm;
		if (this.tComm != null && this.tComm.getCommId() != null) {

			this.tCommNoteTypeId.setCommId(this.tComm.getCommId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommNoteType that) {
		setTCommNoteTypeId(that.getTCommNoteTypeId());
		setTenantId(that.getTenantId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCommNoteTypeId == null) ? 0 : tCommNoteTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCommNoteTypeId=[").append(tCommNoteTypeId).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public boolean equals(final Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TCommNoteType other = (TCommNoteType) obj;
		if (tCommNoteTypeId == null) {
			if (other.tCommNoteTypeId != null) {
				return false;
			}
		} else if (!tCommNoteTypeId.equals(other.tCommNoteTypeId)) {
			return false;
		}
		return true;
	}
}
