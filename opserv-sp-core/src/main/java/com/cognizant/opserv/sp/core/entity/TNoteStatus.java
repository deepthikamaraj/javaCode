package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TNoteStatus 
 * The corresponding mapping table is t_note_status 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTNoteStatuss", query = "select myTNoteStatus from TNoteStatus myTNoteStatus"),
		@NamedQuery(name = "CountTNoteStatuss", query = "Select Count(c) from TNoteStatus c") })
@Table(name = "t_note_status", uniqueConstraints = @UniqueConstraint(columnNames = { "note_status_id" }))
public class TNoteStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TNoteStatusId tNoteStatusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "note_status_name", nullable = false, length = 20)
	private String noteStatusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "note_status_desc", nullable = true, length = 50)
	private String noteStatusDesc;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
	private Date createDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tNoteStatus")
	private Set<TRptRecipient> tRptRecipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tNoteStatus")
	private Set<TCommReceipient> tCommReceipientss;*/

	/**
	 *
	 * @generated
	 */
	public TNoteStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setNoteStatusId(final Integer noteStatusId) {
		this.noteStatusId = noteStatusId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Integer getNoteStatusId() {
		return this.noteStatusId;
	}*/

	
	public TNoteStatusId getTNoteStatusId() {
		return tNoteStatusId;
	}

	public void setTNoteStatusId(TNoteStatusId tNoteStatusId) {
		this.tNoteStatusId = tNoteStatusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNoteStatusName(final String noteStatusName) {
		this.noteStatusName = noteStatusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNoteStatusName() {
		return this.noteStatusName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNoteStatusDesc(final String noteStatusDesc) {
		this.noteStatusDesc = noteStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNoteStatusDesc() {
		return this.noteStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActiveFlag(final Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreatedBy(final Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCreateDt(final Date createDt) {
		if (createDt != null) {
			this.createDt = (Date) createDt.clone();
		} else {
			this.createDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt != null) {
			return (Date) this.createDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdatedBy(final Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
		} else {
			return null;
		}
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
	/*public Set<TRptRecipient> getTRptRecipientss() {
		return this.tRptRecipientss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptRecipientss(final Set<TRptRecipient> tRptRecipientss) {
		this.tRptRecipientss = tRptRecipientss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TCommReceipient> getTCommReceipientss() {
		return this.tCommReceipientss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCommReceipientss(final Set<TCommReceipient> tCommReceipientss) {
		this.tCommReceipientss = tCommReceipientss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TNoteStatus that) {
		//setNoteStatusId(that.getNoteStatusId());
		setTNoteStatusId(that.getTNoteStatusId());
		setNoteStatusName(that.getNoteStatusName());
		setNoteStatusDesc(that.getNoteStatusDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
	}
	
	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tNoteStatusId=[").append(tNoteStatusId).append("] ");
		buffer.append("noteStatusName=[").append(noteStatusName).append("] ");
		buffer.append("noteStatusDesc=[").append(noteStatusDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tNoteStatusId == null) ? 0 : tNoteStatusId.hashCode());
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
		TNoteStatus other = (TNoteStatus) obj;
		if (tNoteStatusId == null) {
			if (other.tNoteStatusId != null)
				return false;
		} else if (!tNoteStatusId.equals(other.tNoteStatusId))
			return false;
		return true;
	}

	
}
