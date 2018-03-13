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
 * JPA class representing TCustAffChngReqDet The corresponding mapping table is
 * t_cust_aff_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAffChngReqDets", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet"),
		@NamedQuery(name = "CountTCustAffChngReqDets", query = "Select Count(c) from TCustAffChngReqDet c"),
		@NamedQuery(name = "FindTCustAffChngReqDetByTCustAff", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAff = ?1 "),
		@NamedQuery(name = "CountTCustAffChngReqDetsByTCustAff", query = "select Count(*) from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAff = ?1 "),
//		@NamedQuery(name = "FindTCustAffChngReqDetByTChngReqDetail", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTCustAffChngReqDetsByTChngReqDetail", query = "select Count(*) from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "getCustAffDetailedView", query = "SELECT myTCustAffChngReqDet.reqDetails FROM TCustAffChngReqDet myTCustAffChngReqDet  WHERE myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId =?1 and  myTCustAffChngReqDet.tenantId=?2 and myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId IN (?3) and myTCustAffChngReqDet.tCustAffChngReqDetId.custAffId IN (?4) "),
//		@NamedQuery(name = "getCustAffDetailStatusByDetailId", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId = ?1 and myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId= ?2 and  myTCustAffChngReqDet.tenantId=?3 "),
//		@NamedQuery(name = "GetCustAffReqDetails", query = "select myTCustAffChngReqDet.reqDetails  from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId = ?1 and myTCustAffChngReqDet.tCustAffChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId= ?2 and myTCustAffChngReqDet.tCustAffChngReqDetId.custAffId=?3"),
		@NamedQuery(name = "GetCustAffCnt", query = "select Count(*),myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId  from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId in ?1 and  myTCustAffChngReqDet.tenantId=?2 GROUP BY myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId "),
		@NamedQuery(name = "getCustAffDetailStatus", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId = ?1 and  myTCustAffChngReqDet.tenantId=?2 "),
		@NamedQuery(name = "FindTCustAffChngReqDetByCustAffId", query = "select myTCustAffChngReqDet from TCustAffChngReqDet myTCustAffChngReqDet where myTCustAffChngReqDet.tCustAffChngReqDetId.custAffId=?1 and myTCustAffChngReqDet.tenantId=?2 "),
		@NamedQuery(name = "GetCustomerAffiliationReqDetails", query = "select myTCustAffChngReqDet.reqDetails from TCustAffChngReqDet myTCustAffChngReqDet  where myTCustAffChngReqDet.tCustAffChngReqDetId.chngReqId = ?1 and myTCustAffChngReqDet.tenantId = ?2 "),
		
		/**
		 * 
		 * @author 409793 to fetch customer id, if CR forAdd/Remove Customer
		 *         Affiliation is there on same SP for pending submission OR
		 *         approval status and Customer is present in that CR.
		 */
		@NamedQuery(name = "CheckForCustLockedInCustAff", query = "SELECT myTCustAff.tCustByCustId.custId,myTChngReq.statusId,myTCustAff.tCustByCustId.custCd FROM TChngReq myTChngReq,TCustAffChngReqDet myTCustAffChngReqDet,TCustAff myTCustAff WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTCustAffChngReqDet.tChngReq.chngReqId AND myTCustAffChngReqDet.tCustAff.custAffId = myTCustAff.custAffId AND myTCustAff.tCustByCustId.custId IN ?5 AND myTCustAffChngReqDet.tenantId = ?6 and myTChngReq.activeFlag='Y'")

})
@Table(name = "t_cust_aff_chng_req_det")
public class TCustAffChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustAffChngReqDetId tCustAffChngReqDetId;

	

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

	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "req_detail", nullable = true, length = 255)
	private String reqDetails;

	
	

	/**
	 * 
	 * @generated
	 */
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;
	

	@ManyToOne
	@JoinColumn(name = "cust_aff_id", referencedColumnName = "cust_aff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustAff tCustAff;

	@ManyToOne
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TCustAlgmnt tCustAlgmnt;

	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TChngReq tChngReq;
	
	@ManyToOne
	@JoinColumn(name = "chng_req_br_id", referencedColumnName = "chng_req_br_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TChngReqBussReason tChngReqBussReason;
	/**
	 * 
	 * @generated
	 */
	public TCustAffChngReqDet() {
	}
	
	public String getReqDetails() {
		return reqDetails;
	}

	public void setReqDetails(String reqDetails) {
		this.reqDetails = reqDetails;
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

	public void setTCustAffChngReqDetId(
			final TCustAffChngReqDetId tCustAffChngReqDetId) {
		this.tCustAffChngReqDetId = tCustAffChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustAffChngReqDetId getTCustAffChngReqDetId() {
		return this.tCustAffChngReqDetId;
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
	public TCustAff getTCustAff() {
		return this.tCustAff;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAff(final TCustAff tCustAff) {
		this.tCustAff = tCustAff;
		if (this.tCustAff != null && this.tCustAff.getCustAffId() != null) {

			this.tCustAffChngReqDetId
					.setCustAffId(this.tCustAff.getCustAffId());

		}

	}
	
	
	public TCustAlgmnt getTCustAlgmnt() {
		return tCustAlgmnt;
	}

	public void setTCustAlgmnt(TCustAlgmnt tCustAlgmnt) {
		this.tCustAlgmnt = tCustAlgmnt;
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
	public void copy(final TCustAffChngReqDet that) {
		setTCustAffChngReqDetId(that.getTCustAffChngReqDetId());
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
	 * = prime * result + ((tCustAffChngReqDetId == null) ? 0 :
	 * tCustAffChngReqDetId.hashCode()); return result; }
	 */

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	/*
	 * public String toString() {
	 * 
	 * final StringBuilder buffer = new StringBuilder();
	 * 
	 * buffer.append("tCustAffChngReqDetId=[").append(tCustAffChngReqDetId).append
	 * ("] "); buffer.append("statusId=[").append(statusId).append("] ");
	 * buffer.append("createdBy=[").append(createdBy).append("] ");
	 * buffer.append("createDt=[").append(createDt).append("] ");
	 * buffer.append("updatedBy=[").append(updatedBy).append("] ");
	 * buffer.append("updateDt=[").append(updateDt).append("] ");
	 * buffer.append("tenantId=[").append(tenantId).append("] ");
	 * 
	 * return buffer.toString(); }
	 */

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
		final TCustAffChngReqDet other = (TCustAffChngReqDet) obj;
		if (tCustAffChngReqDetId == null) {
			if (other.tCustAffChngReqDetId != null) {
				return false;
			}
		} else if (!tCustAffChngReqDetId.equals(other.tCustAffChngReqDetId)) {
			return false;
		}
		return true;
	}
}
