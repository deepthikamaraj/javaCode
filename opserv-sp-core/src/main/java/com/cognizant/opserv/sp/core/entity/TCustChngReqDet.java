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
 * JPA class representing TCustChngReqDet 
 * The corresponding mapping table is t_cust_chng_req_det 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustChngReqDets", query = "select myTCustChngReqDet from TCustChngReqDet myTCustChngReqDet"),
		@NamedQuery(name = "CountTCustChngReqDets", query = "Select Count(c) from TCustChngReqDet c"),
//		@NamedQuery(name = "FindTCustChngReqDetByTChngReqDetail", query = "select myTCustChngReqDet from TCustChngReqDet myTCustChngReqDet where myTCustChngReqDet.tCustChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTCustChngReqDetsByTChngReqDetail", query = "select Count(*) from TCustChngReqDet myTCustChngReqDet where myTCustChngReqDet.tCustChngReqDetId.tChngReqDetail = ?1 "),
		@NamedQuery(name = "FindTCustChngReqDetByTCust", query = "select myTCustChngReqDet from TCustChngReqDet myTCustChngReqDet where myTCustChngReqDet.tCust = ?1 "),
		@NamedQuery(name = "CountTCustChngReqDetsByTCust", query = "select Count(*) from TCustChngReqDet myTCustChngReqDet where myTCustChngReqDet.tCust = ?1 ") })
@Table(name = "t_cust_chng_req_det")
public class TCustChngReqDet implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustChngReqDetId tCustChngReqDetId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = true, length = 255)
	private Integer statusId;

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
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	
	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TChngReq tChngReq;

	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCust tCust;

	@ManyToOne
	@JoinColumn(name = "chng_req_br_id", referencedColumnName = "chng_req_br_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TChngReqBussReason tChngReqBussReason;
	/**
	 *
	 * @generated
	 */
	public TCustChngReqDet() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCustChngReqDetId(final TCustChngReqDetId tCustChngReqDetId) {
		this.tCustChngReqDetId = tCustChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustChngReqDetId getTCustChngReqDetId() {
		return this.tCustChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStatusId() {
		return this.statusId;
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
	public TCust getTCust() {
		return this.tCust;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCust(final TCust tCust) {
		this.tCust = tCust;
		if (this.tCust != null && this.tCust.getCustId() != null) {

			this.tCustChngReqDetId.setCustId(this.tCust.getCustId());

		}

	}
	

	public TChngReq getTChngReq() {
		return tChngReq;
	}

	public void setTChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
	}
	
	
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the t chng req buss reason.
	 *
	 * @return the t chng req buss reason
	 */
	public TChngReqBussReason getTChngReqBussReason() {
		return tChngReqBussReason;
	}

	/**
	 * Sets the t chng req buss reason.
	 *
	 * @param tChngReqBussReason the new t chng req buss reason
	 */
	public void setTChngReqBussReason(TChngReqBussReason tChngReqBussReason) {
		this.tChngReqBussReason = tChngReqBussReason;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustChngReqDet that) {
		setTCustChngReqDetId(that.getTCustChngReqDetId());
		setStatusId(that.getStatusId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setComments(that.getComments());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCustChngReqDetId == null) ? 0 : tCustChngReqDetId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustChngReqDetId=[").append(tCustChngReqDetId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
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
		final TCustChngReqDet other = (TCustChngReqDet) obj;
		if (tCustChngReqDetId == null) {
			if (other.tCustChngReqDetId != null) {
				return false;
			}
		} else if (!tCustChngReqDetId.equals(other.tCustChngReqDetId)) {
			return false;
		}
		return true;
	}
}
