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
 * JPA class representing TCustAlgmntChngReqDet The corresponding mapping table
 * is t_cust_algmnt_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAlgmntChngReqDets", query = "select myTCustAlgmntChngReqDet from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet"),
		@NamedQuery(name = "FindAllTCustAlgmntChngReqDetsByCustAlgmnt", query = "select myTCustAlgmntChngReqDet.statusId from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = ?1 AND myTCustAlgmntChngReqDet.statusId IN (?2,?3)"),
		@NamedQuery(name = "CountTCustAlgmntChngReqDets", query = "Select Count(c) from TCustAlgmntChngReqDet c"),
		@NamedQuery(name = "FindTCustAlgmntChngReqDetByTCustAlgmnt", query = "select myTCustAlgmntChngReqDet from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmnt = ?1 "),
		@NamedQuery(name = "CountTCustAlgmntChngReqDetsByTCustAlgmnt", query = "select Count(*) from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmnt = ?1 "),
//		@NamedQuery(name = "FindTCustAlgmntChngReqDetByTChngReqDetail", query = "select myTCustAlgmntChngReqDet from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTCustAlgmntChngReqDetsByTChngReqDetail", query = "select Count(*) from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "getCustomerDetailedView", query = "select myTCustAlgmntChngReqDet.reqDetails from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and "
//				+ " myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2 and myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.custAlgmntId=?3 and myTCustAlgmntChngReqDet.tenantId=?4"),
//		@NamedQuery(name = "getCustDetailStatusByDetailId", query = "select myTCustAlgmntChngReqDet from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=?1 and "
//				+ " myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2  and myTCustAlgmntChngReqDet.tenantId=?3"),
		@NamedQuery(name = "getCustDetailStatus", query = "select myTCustAlgmntChngReqDet from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.chngReqId=?1 and myTCustAlgmntChngReqDet.tenantId=?2"),
		@NamedQuery(name = "GetCustomerCRStatus", query = "select myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId, myTCustAlgmntChngReqDet.statusId from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId IN ?1 and myTCustAlgmntChngReqDet.statusId IN ?2 and myTCustAlgmntChngReqDet.tenantId = ?3"),

		@NamedQuery(name = "GetCustomerReqDetails", query = "select myTCustAlgmntChngReqDet.reqDetails from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet  where myTCustAlgmntChngReqDet.tCustAlgmntChngReqDetId.chngReqId = ?1 and myTCustAlgmntChngReqDet.tenantId = ?2 "),
		/**
		 * 
		 * @author 409793 to fetch Customer pending submission/approval CR
		 *         customer id
		 */
		@NamedQuery(name = "CheckForCustLocked", query = "SELECT myTCustAlgmnt.tCust.custId,myTChngReq.statusId,myTCustAlgmnt.tCust.custCd FROM TChngReq myTChngReq,TCustAlgmntChngReqDet myTCustAlgmntChngReqDet,TCustAlgmnt myTCustAlgmnt WHERE myTChngReq.reqSalesPosId IN ?1 AND myTChngReq.reqSalesHierId IN ?2 AND myTChngReq.triggerId IN ?3 AND myTChngReq.statusId IN ?4 AND myTChngReq.chngReqId = myTCustAlgmntChngReqDet.tChngReq.chngReqId AND myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId AND myTCustAlgmnt.tCust.custId IN ?5 AND myTChngReq.tenantId = ?6 and myTChngReq.activeFlag='Y' and myTCustAlgmnt.activeFlag='Y' "),

		/**
		 * 
		 * @author 409793 to fetch Customer pending submission/approval CR
		 *         customer ids and customer Global Attribute has been modified
		 */
		@NamedQuery(name = "CheckForCustGlobalAttrLocked", query = "SELECT myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId,myTChngReq.statusId FROM TCustAlgmntChngReqDet myTCustAlgmntChngReqDet JOIN myTCustAlgmntChngReqDet.tChngReq myTChngReq JOIN myTCustAlgmntChngReqDet.tCustAlgmnt myTCustAlgmnt WHERE myTChngReq.triggerId IN ?1 AND myTChngReq.statusId IN ?2 AND myTChngReq.chngReqId = myTCustAlgmntChngReqDet.tChngReq.chngReqId AND myTCustAlgmntChngReqDet.custAttrChngFlg = 'Y' AND myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId AND myTCustAlgmnt.tCust.custId IN ?3 AND myTChngReq.tenantId = ?4 and myTChngReq.activeFlag='Y' and myTCustAlgmnt.activeFlag='Y'"),
		@NamedQuery(name = "getCustInfoByAlgnmtId", query = "select myTCust.custId,myTCust.custCd,myTCustAlgmnt.custAlgmntId from TCust myTCust JOIN myTCust.tCustAlgmntss myTCustAlgmnt where myTCust.custId = myTCustAlgmnt.tCust.custId and myTCustAlgmnt.custAlgmntId IN ?1 and myTCustAlgmnt.tenantId=1"),
		/* Added By 407986 For Zip Locking */

		@NamedQuery(name = "CheckForCustZipLocked", query = "SELECT myTCustAlgmnt.tCust.custId, myTChngReq.statusId,myTCustAlgmnt.tCust.custCd FROM TChngReq myTChngReq,TCustAlgmntChngReqDet myTCustAlgmntChngReqDet ,TTerrZipAlgmntChngReqDet myTTerrZipAlgmntChngReqDet ,TCustAlgmnt myTCustAlgmnt, TCust myTCust WHERE myTChngReq.statusId IN ?1 AND myTChngReq.chngReqId = myTTerrZipAlgmntChngReqDet.tChngReq.chngReqId AND myTChngReq.chngReqId = myTCustAlgmntChngReqDet.tChngReq.chngReqId AND myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId AND myTCustAlgmnt.tCust.custId = myTCust.custId AND  myTCust.custId IN ?2  AND myTChngReq.tenantId = ?3 and myTChngReq.activeFlag='Y'"),
		@NamedQuery(name = "CheckForCustLockedForZipMovement", query = "SELECT myTCust.custId ,myTChngReq.statusId,myTCust.custCd FROM TChngReq myTChngReq ,TCustAlgmntChngReqDet myTCustAlgmntChngReqDet,TCustAlgmnt myTCustAlgmnt,TCust myTCust WHERE myTCust.custId= myTCustAlgmnt.tCust.custId AND myTCustAlgmnt.custAlgmntId = myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId AND myTChngReq.chngReqId = myTCustAlgmntChngReqDet.tChngReq.chngReqId  AND myTCust.custId IN ?1 AND myTChngReq.statusId IN ?2 AND myTChngReq.triggerId IN ?3 AND myTChngReq.tenantId = ?4 and myTChngReq.activeFlag='Y'"),
		@NamedQuery(name = "updateCustAlgCRDetStatus", query = "Update TCustAlgmntChngReqDet myTCustAlgmntChngReqDet set myTCustAlgmntChngReqDet.statusId = ?1, myTCustAlgmntChngReqDet.updateDt = ?2 ,myTCustAlgmntChngReqDet.updatedBy = ?3 where myTCustAlgmntChngReqDet.tChngReq.chngReqId = ?4 and myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = ?5 and myTCustAlgmntChngReqDet.tenantId = ?6 "),
		
		/** Changes For Update Chnage Request Status -- start **/
		@NamedQuery(name = "GetAllCrIdInTCustAffChngReqDets", query = "select myTCustAlgmntChngReqDet.tChngReq.chngReqId from TCustAlgmntChngReqDet myTCustAlgmntChngReqDet where myTCustAlgmntChngReqDet.tenantId = ?1 ")
		/** Changes For Update Chnage Request Status -- end **/
		})
@Table(name = "t_cust_algmnt_chng_req_det")
public class TCustAlgmntChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId;

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

	@Column(name = "req_detail", nullable = false, length = 255)
	private String reqDetails;

	@Column(name = "cust_attr_chng_flag", nullable = true, length = 1)
	private Character custAttrChngFlg;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;
	
	/** The algmnt flag. */
	@Column(name = "algmnt_flag", nullable = false, length = 1)
	private Character algmntFlag;
	
	@ManyToOne
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = false, updatable = false)
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
	
	public Character getCustAttrChngFlg() {
		return custAttrChngFlg;
	}

	public void setCustAttrChngFlg(Character custAttrChngFlg) {
		this.custAttrChngFlg = custAttrChngFlg;
	}

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
	public TCustAlgmntChngReqDet() {
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

	public void setTCustAlgmntChngReqDetId(
			final TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId) {
		this.tCustAlgmntChngReqDetId = tCustAlgmntChngReqDetId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCustAlgmntChngReqDetId getTCustAlgmntChngReqDetId() {
		return this.tCustAlgmntChngReqDetId;
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
	public TCustAlgmnt getTCustAlgmnt() {
		return this.tCustAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmnt(final TCustAlgmnt tCustAlgmnt) {
		this.tCustAlgmnt = tCustAlgmnt;
		if (this.tCustAlgmnt != null
				&& this.tCustAlgmnt.getCustAlgmntId() != null) {

			this.tCustAlgmntChngReqDetId.setCustAlgmntId(this.tCustAlgmnt
					.getCustAlgmntId());

		}

	}
	
	

	public TCustAlgmnt gettCustAlgmnt() {
		return tCustAlgmnt;
	}

	public void settCustAlgmnt(TCustAlgmnt tCustAlgmnt) {
		this.tCustAlgmnt = tCustAlgmnt;
	}

	public TChngReq getTChngReq() {
		return tChngReq;
	}

	public void setTChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
	}

	public Character getAlgmntFlag() {
		return algmntFlag;
	}

	public void setAlgmntFlag(Character algmntFlag) {
		this.algmntFlag = algmntFlag;
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
	public void copy(final TCustAlgmntChngReqDet that) {
		setTCustAlgmntChngReqDetId(that.getTCustAlgmntChngReqDetId());
		setStatusId(that.getStatusId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setComments(that.getComments());
		setAlgmntFlag(that.getAlgmntFlag());
	}

	/**
	 * @generated
	 * 
	 */
	/*
	 * @Override public int hashCode() { int prime = 31; int result = 1; result
	 * = prime * result + ((tCustAlgmntChngReqDetId == null) ? 0 :
	 * tCustAlgmntChngReqDetId.hashCode()); return result; }
	 */

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	
	 public String toString() {
	
	final StringBuilder buffer = new StringBuilder();

		buffer.append("tCustAlgmntChngReqDetId=[").append(tCustAlgmntChngReqDetId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("custAttrChngFlg=[").append(custAttrChngFlg).append("] ");
		buffer.append("reqDetails=[").append(reqDetails).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("algmntFlag=[").append(algmntFlag).append("] ");
		
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
		final TCustAlgmntChngReqDet other = (TCustAlgmntChngReqDet) obj;
		if (tCustAlgmntChngReqDetId == null) {
			if (other.tCustAlgmntChngReqDetId != null) {
				return false;
			}
		} else if (!tCustAlgmntChngReqDetId
				.equals(other.tCustAlgmntChngReqDetId)) {
			return false;
		}
		return true;
	}
}
