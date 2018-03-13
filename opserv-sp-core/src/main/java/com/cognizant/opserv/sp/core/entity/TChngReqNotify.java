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
 * JPA class representing TChngReqNotify 
 * The corresponding mapping table is t_chng_req_notify 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqNotifys", query = "select myTChngReqNotify from TChngReqNotify myTChngReqNotify"),
		@NamedQuery(name = "CountTChngReqNotifys", query = "Select Count(c) from TChngReqNotify c"),
		@NamedQuery(name = "FindTChngReqNotifyByTChngReq", query = "select myTChngReqNotify from TChngReqNotify myTChngReqNotify where myTChngReqNotify.tChngReq = ?1 "),
		@NamedQuery(name = "CountTChngReqNotifysByTChngReq", query = "select Count(*) from TChngReqNotify myTChngReqNotify where myTChngReqNotify.tChngReq = ?1 ") ,
		
		/*Added By 407986 To find All SalesPositionBy CR Id*/
		@NamedQuery(name = "FindAllSalesPositionByCRId", query = "select myTChngReqNotify.tChngReqNotifyId.noteSalesPosId from TChngReqNotify myTChngReqNotify where myTChngReqNotify.tChngReqNotifyId.chngReqId = ?1 AND myTChngReqNotify.tenantId = ?2 ")
})
@Table(name = "t_chng_req_notify")
public class TChngReqNotify implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqNotifyId tChngReqNotifyId;

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
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = false, updatable = false)
	//@Valid
	private TChngReq tChngReq;

	/**
	 *
	 * @generated
	 */
	public TChngReqNotify() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqNotifyId(final TChngReqNotifyId tChngReqNotifyId) {
		this.tChngReqNotifyId = tChngReqNotifyId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqNotifyId getTChngReqNotifyId() {
		return this.tChngReqNotifyId;
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
			return null ;
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
	public TChngReq getTChngReq() {
		return this.tChngReq;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReq(final TChngReq tChngReq) {
		this.tChngReq = tChngReq;
		if (this.tChngReq != null && this.tChngReq.getChngReqId() != null) {

			this.tChngReqNotifyId.setChngReqId(this.tChngReq.getChngReqId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqNotify that) {
		setTChngReqNotifyId(that.getTChngReqNotifyId());
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
		result = prime * result + ((tChngReqNotifyId == null) ? 0 : tChngReqNotifyId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqNotifyId=[").append(tChngReqNotifyId).append("] ");
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
		final TChngReqNotify other = (TChngReqNotify) obj;
		if (tChngReqNotifyId == null) {
			if (other.tChngReqNotifyId != null) {
				return false;
			}
		} else if (!tChngReqNotifyId.equals(other.tChngReqNotifyId)) {
			return false;
		}
		return true;
	}
}
