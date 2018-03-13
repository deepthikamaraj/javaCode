package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TChngReqNoteType 
 * The corresponding mapping table is t_chng_req_note_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqNoteTypes", query = "select myTChngReqNoteType from TChngReqNoteType myTChngReqNoteType"),
		@NamedQuery(name = "CountTChngReqNoteTypes", query = "Select Count(c) from TChngReqNoteType c"),
		@NamedQuery(name = "FetchNoteTypeStatus", query = "select myTChngReqNoteType.activeFlag ,myTChngReqNoteType.tChngReqNoteTypeId.noteTypeId  from TChngReqNoteType myTChngReqNoteType where myTChngReqNoteType.tChngReqNoteTypeId.noteTypeId IN ?1 and myTChngReqNoteType.tChngReqNoteTypeId.chngReqConfigId =?2 and myTChngReqNoteType.tenantId =?3 "),
		@NamedQuery(name = "findTChngReqNoteTypeByCRConfigId", query = "Select c from TChngReqNoteType c where c.tChngReqNoteTypeId.chngReqConfigId =?1 and c.tenantId =?2")
})
@Table(name = "t_chng_req_note_type")
public class TChngReqNoteType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqNoteTypeId tChngReqNoteTypeId;

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
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
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
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 *
	 * @generated
	 */
	public TChngReqNoteType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqNoteTypeId(final TChngReqNoteTypeId tChngReqNoteTypeId) {
		this.tChngReqNoteTypeId = tChngReqNoteTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqNoteTypeId getTChngReqNoteTypeId() {
		return this.tChngReqNoteTypeId;
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

	public void setUpdateDt(final Date updateDt) {
		if (updateDt != null) {
			this.updateDt = (Date) updateDt.clone();
		} else {
			this.updateDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt != null) {
			return (Date) this.updateDt.clone();
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqNoteType that) {
		setTChngReqNoteTypeId(that.getTChngReqNoteTypeId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
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
		result = prime * result + ((tChngReqNoteTypeId == null) ? 0 : tChngReqNoteTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqNoteTypeId=[").append(tChngReqNoteTypeId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
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
		final TChngReqNoteType other = (TChngReqNoteType) obj;
		if (tChngReqNoteTypeId == null) {
			if (other.tChngReqNoteTypeId != null) {
				return false;
			}
		} else if (!tChngReqNoteTypeId.equals(other.tChngReqNoteTypeId)) {
			return false;
		}
		return true;
	}
}
