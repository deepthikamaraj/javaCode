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
 * JPA class representing TCustCallPlanChngReqDet The corresponding mapping
 * table is t_cust_call_plan_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustCallPlanChngReqDets", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet"),
		@NamedQuery(name = "CountTCustCallPlanChngReqDets", query = "Select Count(c) from TCustCallPlanChngReqDet c"),
//		@NamedQuery(name = "FindTCustCallPlanChngReqDetByTChngReqDetail", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTCustCallPlanChngReqDetsByTChngReqDetail", query = "select Count(*) from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail = ?1 "),
		@NamedQuery(name = "FindTCustCallPlanChngReqDetByTCustCallPlan", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlan = ?1 "),
		@NamedQuery(name = "CountTCustCallPlanChngReqDetsByTCustCallPlan", query = "select Count(*) from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlan = ?1 "),
//		@NamedQuery(name = "getCallPlanDetailStatusByDetailId", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqId = ?1 AND myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId = ?2 AND myTCustCallPlanChngReqDet.tenantId= ?3 "),
		@NamedQuery(name = "getCallPlanDetailStatus", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.chngReqId = ?1 AND  myTCustCallPlanChngReqDet.tenantId= ?2 "),
//		@NamedQuery(name = "fetchCallPlanReqDetails", query = "select myTCustCallPlanChngReqDet.reqDetails from TCustCallPlanChngReqDet  myTCustCallPlanChngReqDet  where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId = ?1 and myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId = ?2 and myTCustCallPlanChngReqDet.tCustCallPlan.custCallPlanId=?3 and  myTCustCallPlanChngReqDet.tenantId = ?4 "),

		@NamedQuery(name = "GetCustomerCallPlanReqDetails", query = "select myTCustCallPlanChngReqDet.reqDetails from TCustCallPlanChngReqDet  myTCustCallPlanChngReqDet  where myTCustCallPlanChngReqDet.tChngReq.chngReqId = ?1 and myTCustCallPlanChngReqDet.tenantId = ?2 "),
		@NamedQuery(name = "GetCustCallPlanChangeReqDtl", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.custCallPlanId = ?1 and myTCustCallPlanChngReqDet.tenantId = ?2 and myTCustCallPlanChngReqDet.statusId IN (1,2) "),
		/**
		 * 
		 * @author 409793 to fetch customer id, if CR for Edit Call Plan is
		 *         there on same SP for pending submission OR approval status
		 *         and Customer is present in that CR.
		 */
		@NamedQuery(name = "CheckForCustLockedInCallPlan", query = "SELECT myTCust.custId,myTChngReq.statusId,myTCust.custCd FROM TChngReq myTChngReq,TCustCallPlanChngReqDet myTCustCallPlanChngReqDet,TCustCallPlan myTCustCallPlan,TCust myTCust WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTCustCallPlanChngReqDet.tChngReq.chngReqId AND myTCustCallPlanChngReqDet.tCustCallPlan.custCallPlanId =myTCustCallPlan.custCallPlanId AND myTCustCallPlan.custId IN ?5 AND myTCust.custId = myTCustCallPlan.custId AND myTCustCallPlanChngReqDet.tenantId = ?6 and myTChngReq.activeFlag='Y'"),

		// Added By 407986 To Fetch All the Locked Call Plans
		@NamedQuery(name = "FetchLockedCustCallPlan", query = "select myTCustCallPlanChngReqDet from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet where myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.custCallPlanId IN ?1  and myTCustCallPlanChngReqDet.statusId IN ?2 and myTCustCallPlanChngReqDet.tenantId = ?3") ,
		// Added By 407986 To Fetch All the Locked Customers For the Call Plan
		@NamedQuery(name = "FetchLockedCustByCallPlanId", query = "select myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.custCallPlanId,myTCustCallPlanChngReqDet.statusId,myTCustCallPlan.custId,myTCust.custCd from TCustCallPlanChngReqDet myTCustCallPlanChngReqDet,TCustCallPlan myTCustCallPlan,TCust myTCust where  myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.custCallPlanId = myTCustCallPlan.custCallPlanId and myTCustCallPlan.custId = myTCust.custId and myTCustCallPlanChngReqDet.tCustCallPlanChngReqDetId.custCallPlanId IN ?1  and myTCustCallPlanChngReqDet.statusId IN ?2 and myTCustCallPlanChngReqDet.tenantId = ?3  group by myTCustCallPlan.custId " ) })
@Table(name = "t_cust_call_plan_chng_req_det")
public class TCustCallPlanChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;


	@Column(name = "req_detail", nullable = true, length = 255)
	private String reqDetails;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TChngReq tChngReq;

	@ManyToOne
	@JoinColumn(name = "cust_call_plan_id", referencedColumnName = "cust_call_plan_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustCallPlan tCustCallPlan;
	
	@ManyToOne
	@JoinColumn(name = "chng_req_br_id", referencedColumnName = "chng_req_br_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TChngReqBussReason tChngReqBussReason;

	public String getReqDetails() {
		return reqDetails;
	}

	public void setReqDetails(String reqDetails) {
		this.reqDetails = reqDetails;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustCallPlanChngReqDet() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCustCallPlanChngReqDetId(
			final TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId) {
		this.tCustCallPlanChngReqDetId = tCustCallPlanChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustCallPlanChngReqDetId getTCustCallPlanChngReqDetId() {
		return this.tCustCallPlanChngReqDetId;
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

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
	public TCustCallPlan getTCustCallPlan() {
		return this.tCustCallPlan;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustCallPlan(final TCustCallPlan tCustCallPlan) {
		this.tCustCallPlan = tCustCallPlan;
		if (this.tCustCallPlan != null
				&& this.tCustCallPlan.getCustCallPlanId() != null) {

			this.tCustCallPlanChngReqDetId.setCustCallPlanId(this.tCustCallPlan
					.getCustCallPlanId());

		}

	}

	
	public TChngReq getTChngReq() {
		return tChngReq;
	}

	public void setTChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
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
	public void copy(final TCustCallPlanChngReqDet that) {
		setTCustCallPlanChngReqDetId(that.getTCustCallPlanChngReqDetId());
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
	/*
	 * @Override public int hashCode() { int prime = 31; int result = 1; result
	 * = prime * result + ((tCustCallPlanChngReqDetId == null) ? 0 :
	 * tCustCallPlanChngReqDetId.hashCode()); return result; }
	 */

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustCallPlanChngReqDetId=[")
				.append(tCustCallPlanChngReqDetId).append("] ");
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
	/*
	 * @Override public boolean equals(final Object obj) {
	 * 
	 * if (this == obj) { return true; } if (obj == null) { return false; } if
	 * (getClass() != obj.getClass()) { return false; } final
	 * TCustCallPlanChngReqDet other = (TCustCallPlanChngReqDet) obj; if
	 * (tCustCallPlanChngReqDetId == null) { if (other.tCustCallPlanChngReqDetId
	 * != null) { return false; } } else if
	 * (!tCustCallPlanChngReqDetId.equals(other.tCustCallPlanChngReqDetId)) {
	 * return false; } return true; }
	 */
}
