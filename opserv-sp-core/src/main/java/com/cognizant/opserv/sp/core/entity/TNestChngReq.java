package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

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

/** 
 * JPA class representing TNestChngReq 
 * The corresponding mapping table is t_nest_chng_req 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTNestChngReqs", query = "select myTNestChngReq from TNestChngReq myTNestChngReq"),
		@NamedQuery(name = "CountTNestChngReqs", query = "Select Count(c) from TNestChngReq c"),
		@NamedQuery(name = "FindTNestChngReqByTChngReqTriggerByTriggerId", query = "select myTNestChngReq from TNestChngReq myTNestChngReq where myTNestChngReq.tChngReqTriggerByTriggerId = ?1 "),
		@NamedQuery(name = "CountTNestChngReqsByTChngReqTriggerByTriggerId", query = "select Count(*) from TNestChngReq myTNestChngReq where myTNestChngReq.tChngReqTriggerByTriggerId = ?1 "),
		@NamedQuery(name = "FindTNestChngReqByTChngReqTriggerByNstTriggerId", query = "select myTNestChngReq from TNestChngReq myTNestChngReq where myTNestChngReq.tChngReqTriggerByNstTriggerId = ?1 "),
		@NamedQuery(name = "CountTNestChngReqsByTChngReqTriggerByNstTriggerId", query = "select Count(*) from TNestChngReq myTNestChngReq where myTNestChngReq.tChngReqTriggerByNstTriggerId = ?1 ") })
@Table(name = "t_nest_chng_req")
public class TNestChngReq implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TNestChngReqId tNestChngReqId;

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

	@ManyToOne
	@JoinColumn(name = "trigger_id", referencedColumnName = "trigger_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TChngReqTrigger tChngReqTriggerByTriggerId;

	@ManyToOne
	@JoinColumn(name = "nst_trigger_id", referencedColumnName = "trigger_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TChngReqTrigger tChngReqTriggerByNstTriggerId;

	/**
	 *
	 * @generated
	 */
	public TNestChngReq() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTNestChngReqId(final TNestChngReqId tNestChngReqId) {
		this.tNestChngReqId = tNestChngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public TNestChngReqId getTNestChngReqId() {
		return this.tNestChngReqId;
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
	 * 
	 * @generated
	 */
	public TChngReqTrigger getTChngReqTriggerByTriggerId() {
		return this.tChngReqTriggerByTriggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqTriggerByTriggerId(final TChngReqTrigger tChngReqTriggerByTriggerId) {
		this.tChngReqTriggerByTriggerId = tChngReqTriggerByTriggerId;
		if (this.tChngReqTriggerByTriggerId != null && this.tChngReqTriggerByTriggerId.getTriggerId() != null) {

			this.tNestChngReqId.setTriggerId(this.tChngReqTriggerByTriggerId.getTriggerId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqTrigger getTChngReqTriggerByNstTriggerId() {
		return this.tChngReqTriggerByNstTriggerId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqTriggerByNstTriggerId(final TChngReqTrigger tChngReqTriggerByNstTriggerId) {
		this.tChngReqTriggerByNstTriggerId = tChngReqTriggerByNstTriggerId;
		if (this.tChngReqTriggerByNstTriggerId != null && this.tChngReqTriggerByNstTriggerId.getTriggerId() != null) {

			this.tNestChngReqId.setNstTriggerId(this.tChngReqTriggerByNstTriggerId.getTriggerId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TNestChngReq that) {
		setTNestChngReqId(that.getTNestChngReqId());
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
		result = prime * result + ((tNestChngReqId == null) ? 0 : tNestChngReqId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tNestChngReqId=[").append(tNestChngReqId).append("] ");
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
		final TNestChngReq other = (TNestChngReq) obj;
		if (tNestChngReqId == null) {
			if (other.tNestChngReqId != null) {
				return false;
			}
		} else if (!tNestChngReqId.equals(other.tNestChngReqId)) {
			return false;
		}
		return true;
	}
}
