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
 * JPA class representing TNoteType 
 * The corresponding mapping table is t_note_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTNoteTypes", query = "select myTNoteType from TNoteType myTNoteType"),
	@NamedQuery(name = "FindAllTNoteTypesByTenant", query = "select myTNoteType from TNoteType myTNoteType where myTNoteType.tenantId =?1"),
		@NamedQuery(name = "CountTNoteTypes", query = "Select Count(c) from TNoteType c"),
		@NamedQuery(name = "FindTNoteTypesByNoteTypeId", query = "Select c from TNoteType c where c.tNoteTypeId.noteTypeId =?1 and c.tenantId =?2")})
@Table(name = "t_note_type", uniqueConstraints = @UniqueConstraint(columnNames = { "note_type_id" }))
public class TNoteType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
    
    /**
	 * 
	 * @generated
	 */

    @EmbeddedId
	@Valid
	private TNoteTypeId tNoteTypeId;

	/**
	 * 
	 * @generated
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_type_id", nullable = false, length = 255)
	private Integer noteTypeId;*/

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "note_type_name", nullable = false, length = 20)
	private String noteTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "note_type_desc", nullable = true, length = 50)
	private String noteTypeDesc;

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

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tNoteType")
	private Set<TCommNoteType> tCommNoteTypess;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tNoteType")
	private Set<TRptConfigTNoteType> tRptConfigTNoteTypess;
*/
	/**
	 *
	 * @generated
	 */
	public TNoteType() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setNoteTypeId(final Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getNoteTypeId() {
		return this.noteTypeId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setNoteTypeName(final String noteTypeName) {
		this.noteTypeName = noteTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNoteTypeName() {
		return this.noteTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNoteTypeDesc(final String noteTypeDesc) {
		this.noteTypeDesc = noteTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNoteTypeDesc() {
		return this.noteTypeDesc;
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
	/*public Set<TCommNoteType> getTCommNoteTypess() {
		return this.tCommNoteTypess;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCommNoteTypess(final Set<TCommNoteType> tCommNoteTypess) {
		this.tCommNoteTypess = tCommNoteTypess;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TRptConfigTNoteType> getTRptConfigTNoteTypess() {
		return this.tRptConfigTNoteTypess;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptConfigTNoteTypess(final Set<TRptConfigTNoteType> tRptConfigTNoteTypess) {
		this.tRptConfigTNoteTypess = tRptConfigTNoteTypess;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TNoteType that) {
		//setNoteTypeId(that.getNoteTypeId());
		setNoteTypeName(that.getNoteTypeName());
		setNoteTypeDesc(that.getNoteTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((gettNoteTypeId() == null) ? 0 : gettNoteTypeId().hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tNoteTypeId=[").append(gettNoteTypeId()).append("] ");
		buffer.append("noteTypeName=[").append(noteTypeName).append("] ");
		buffer.append("noteTypeDesc=[").append(noteTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TNoteType other = (TNoteType) obj;
			if (gettNoteTypeId() == null) {
			if (other.gettNoteTypeId() != null) {
				return false;
			}
		} else if (!gettNoteTypeId().equals(other.gettNoteTypeId())) {
			return false;
		}
		return true;
	}


	public TNoteTypeId gettNoteTypeId() {
		return tNoteTypeId;
	}


	public void settNoteTypeId(TNoteTypeId tNoteTypeId) {
		this.tNoteTypeId = tNoteTypeId;
	}
}
