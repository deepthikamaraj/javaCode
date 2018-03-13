package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TPrdAlgmntChngReqDet The corresponding mapping table
 * is t_prd_algmnt_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdAlgmntChngReqDets", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet"),
		@NamedQuery(name = "CountTPrdAlgmntChngReqDets", query = "Select Count(c) from TPrdAlgmntChngReqDet c"),
		@NamedQuery(name = "FindTPrdAlgmntChngReqDetByTPrdAlgmnt", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmnt = ?1 "),
		@NamedQuery(name = "CountTPrdAlgmntChngReqDetsByTPrdAlgmnt", query = "select Count(*) from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmnt = ?1 "),
//		@NamedQuery(name = "FindTPrdAlgmntChngReqDetByTChngReqDetail", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTPrdAlgmntChngReqDetsByTChngReqDetail", query = "select Count(*) from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "getProductDetailedView", query = "select myTPrdAlgmntChngReqDet.reqDetails from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqId = ?1 and  myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2 "
//				+ "and  myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.prdAlgmntId=?3 and myTPrdAlgmntChngReqDet.tenantId=?4"),
//		@NamedQuery(name = "getPrdDetailStatusByDetailId", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqId = ?1 and  myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2 and myTPrdAlgmntChngReqDet.tenantId=?3"),
		@NamedQuery(name = "getPrdDetailStatus", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.chngReqId = ?1 and  myTPrdAlgmntChngReqDet.tenantId=?2"),
		@NamedQuery(name = "getTPrdAlgmntChngReqDetsByPrdAlgmntId", query = "select myTPrdAlgmntChngReqDet from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.prdAlgmntId=?1 AND myTPrdAlgmntChngReqDet.statusId IN(1,2)"),
		@NamedQuery(name = "GetProductReqDetails", query = "select myTPrdAlgmntChngReqDet.reqDetails from TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet  where myTPrdAlgmntChngReqDet.tPrdAlgmntChngReqDetId.chngReqId = ?1 and myTPrdAlgmntChngReqDet.tenantId = ?2 "),
		/**
		 * @author 409793 to fetch product add/edit/removal pending
		 *         submission/approval CR product id for the logged in SP.
		 */
		@NamedQuery(name = "CheckForPrdLocked", query = "SELECT myTPrdAlgmnt.tPrd.prdId,myTChngReq.statusId,myTPrdAlgmnt.tPrd.prdCd FROM TChngReq myTChngReq,TPrdAlgmntChngReqDet myTPrdAlgmntChngReqDet,TPrdAlgmnt myTPrdAlgmnt WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTPrdAlgmntChngReqDet.tChngReq.chngReqId AND myTPrdAlgmntChngReqDet.tPrdAlgmnt.prdAlgmntId = myTPrdAlgmnt.prdAlgmntId AND myTPrdAlgmnt.tPrd.prdId IN ?5 AND myTPrdAlgmnt.tenantId = ?6 and myTChngReq.activeFlag='Y'"),
		/**
		 * 
		 * @author 409793 to fetch edit call plan pending submission/approval CR
		 *         product id for the logged in SP.
		 */
		@NamedQuery(name = "CheckForPrdInCallPlan", query = "SELECT tPrd.prdId,myTChngReq.statusId,tPrd.prdCd "
				+ "FROM TChngReq myTChngReq,TCustCallPlanChngReqDet myTCustCallPlanChngReqDet,"
				+ "TCustCallPlan myTCustCallPlan,TCallPlanPrd myTCallPlanPrd,TPrd tPrd"
				+ " WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND"
				+ " myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTCustCallPlanChngReqDet.tChngReq.chngReqId AND"
				+ " myTCustCallPlanChngReqDet.tCustCallPlan.custCallPlanId = myTCustCallPlan.custCallPlanId AND"
				+ " myTCustCallPlan.custCallPlanId = myTCallPlanPrd.tCustCallPlan.custCallPlanId AND"
				+ " myTCallPlanPrd.prdId IN ?5 AND myTCallPlanPrd.prdId = tPrd.prdId AND myTCallPlanPrd.tenantId = ?6 and myTChngReq.activeFlag='Y'"),
		/**
		 * 
		 * @author 409793 to fetch edit call plan pending submission/approval CR
		 *         product id and product is part of Call Direction for the
		 *         logged in SP.
		 */
		@NamedQuery(name = "CheckForPrdInCallDir", query = "SELECT tPrd.prdId,myTChngReq.statusId,tPrd.prdCd "
				+ "FROM TChngReq myTChngReq,TCustCallPlanChngReqDet myTCustCallPlanChngReqDet,"
				+ "TCustCallPlan myTCustCallPlan,TCallDir myTCallDir,TCallDirPrd myTCallDirPrd,TPrd tPrd"
				+ " WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND"
				+ " myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTCustCallPlanChngReqDet.tChngReq.chngReqId AND"
				+ " myTCustCallPlanChngReqDet.tCustCallPlan.custCallPlanId = myTCustCallPlan.custCallPlanId AND"
				+ " myTCustCallPlan.custCallPlanId = myTCallDir.tCustCallPlan.custCallPlanId AND"
				+ " myTCallDir.callDirId = myTCallDirPrd.tCallDir.callDirId AND"
				+ " myTCallDirPrd.prdId IN ?5 AND myTCallDirPrd.prdId= tPrd.prdId AND myTCallDirPrd.tenantId = ?6 and myTChngReq.activeFlag='Y'") })
@Table(name = "t_prd_algmnt_chng_req_det")
public class TPrdAlgmntChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId;

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
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_algmnt_id", referencedColumnName = "prd_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdAlgmnt tPrdAlgmnt;

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
	@Column(name = "req_detail", nullable = true, length = 255)
	private String reqDetails;



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
	public TPrdAlgmntChngReqDet() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrdAlgmntChngReqDetId(
			final TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId) {
		this.tPrdAlgmntChngReqDetId = tPrdAlgmntChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdAlgmntChngReqDetId getTPrdAlgmntChngReqDetId() {
		return this.tPrdAlgmntChngReqDetId;
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
	public TPrdAlgmnt getTPrdAlgmnt() {
		return this.tPrdAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAlgmnt(final TPrdAlgmnt tPrdAlgmnt) {
		this.tPrdAlgmnt = tPrdAlgmnt;
		if (this.tPrdAlgmnt != null && this.tPrdAlgmnt.getPrdAlgmntId() != null) {

			this.tPrdAlgmntChngReqDetId.setPrdAlgmntId(this.tPrdAlgmnt
					.getPrdAlgmntId());

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
	public void copy(final TPrdAlgmntChngReqDet that) {
		setTPrdAlgmntChngReqDetId(that.getTPrdAlgmntChngReqDetId());
		setStatusId(that.getStatusId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setComments(comments);
	}

	/**
	 * @generated
	 * 
	 */
	/*
	 * @Override public int hashCode() { int prime = 31; int result = 1; result
	 * = prime * result + ((tPrdAlgmntChngReqDetId == null) ? 0 :
	 * tPrdAlgmntChngReqDetId.hashCode()); return result; }
	 */

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrdAlgmntChngReqDetId=[")
				.append(tPrdAlgmntChngReqDetId).append("] ");
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
	 * TPrdAlgmntChngReqDet other = (TPrdAlgmntChngReqDet) obj; if
	 * (tPrdAlgmntChngReqDetId == null) { if (other.tPrdAlgmntChngReqDetId !=
	 * null) { return false; } } else if
	 * (!tPrdAlgmntChngReqDetId.equals(other.tPrdAlgmntChngReqDetId)) { return
	 * false; } return true; }
	 */
}
