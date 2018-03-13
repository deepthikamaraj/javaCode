package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TSalesPosChngReqDet The corresponding mapping table is
 * t_sales_pos_chng_req_det
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosChngReqDets", query = "select myTSalesPosChngReqDet from TSalesPosChngReqDet myTSalesPosChngReqDet"),
		@NamedQuery(name = "CountTSalesPosChngReqDets", query = "Select Count(c) from TSalesPosChngReqDet c"),
//		@NamedQuery(name = "FindTSalesPosChngReqDetByTChngReqDetail", query = "select myTSalesPosChngReqDet from TSalesPosChngReqDet myTSalesPosChngReqDet where myTSalesPosChngReqDet.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "CountTSalesPosChngReqDetsByTChngReqDetail", query = "select Count(myTSalesPosChngReqDet) from TSalesPosChngReqDet myTSalesPosChngReqDet where myTSalesPosChngReqDet.tChngReqDetail = ?1 "),
//		@NamedQuery(name = "getSalesPositionDetailedView", query = "select myTSalesPosChngReqDet.reqDetails from TSalesPosChngReqDet myTSalesPosChngReqDet where myTSalesPosChngReqDet.tChngReq.chngReqId = ?1 and  myTSalesPosChngReqDet.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2 "
//				+ "and  myTSalesPosChngReqDet.spChngReqId=?3 and myTSalesPosChngReqDet.tenantId=?4"),
//		@NamedQuery(name = "getSPDetailStatusByDetailId", query = "select myTSalesPosChngReqDet from TSalesPosChngReqDet myTSalesPosChngReqDet where myTSalesPosChngReqDet.tChngReq.chngReqId = ?1 and  myTSalesPosChngReqDet.tChngReqDetail.tChngReqDetailId.chngReqDetailId=?2 and  myTSalesPosChngReqDet.tenantId=?3"),
		@NamedQuery(name = "getSPDetailStatus", query = "select myTSalesPosChngReqDet from TSalesPosChngReqDet myTSalesPosChngReqDet where myTSalesPosChngReqDet.tChngReq.chngReqId = ?1 and  myTSalesPosChngReqDet.tenantId=?2"),
		@NamedQuery(name = "GetSalePositionReqDetails", query = "select myTSalesPosChngReqDet.reqDetails from TSalesPosChngReqDet myTSalesPosChngReqDet  where myTSalesPosChngReqDet.tChngReq.chngReqId = ?1 and myTSalesPosChngReqDet.tenantId = ?2 "),
		@NamedQuery(name = "CheckForSPLocked", query = "select count(myTSalesPosChngReqDet) from TSalesPosChngReqDet  myTSalesPosChngReqDet , TChngReq myTChngReq  where  myTSalesPosChngReqDet.salesPosId = ?1 and myTSalesPosChngReqDet.salesHierId = ?2 and myTSalesPosChngReqDet.tChngReq.chngReqId = myTChngReq.chngReqId and myTChngReq.triggerId in ?3 and myTChngReq.statusId in ?4 and  myTSalesPosChngReqDet.tenantId =?5 and myTChngReq.activeFlag='Y'"),
		@NamedQuery(name = "CheckForSPLockedForZip", query = "select myTSalesPosChngReqDet.salesPosId ,myTSalesPosChngReqDet.statusId, myTSalesPos.posName from TSalesPosChngReqDet  myTSalesPosChngReqDet , TChngReq myTChngReq ,TSalesPos myTSalesPos where  myTSalesPosChngReqDet.salesPosId in ?1 and myTSalesPosChngReqDet.salesHierId in ?2 and myTSalesPosChngReqDet.tChngReq.chngReqId = myTChngReq.chngReqId and myTSalesPos.salesPosId = myTSalesPosChngReqDet.salesPosId  and myTChngReq.triggerId in ?3 and myTChngReq.statusId in ?4 and  myTSalesPosChngReqDet.tenantId =?5 and myTChngReq.activeFlag='Y'") })
@Table(name = "t_sales_pos_chng_req_det", uniqueConstraints = @UniqueConstraint(columnNames = { "sp_chng_req_id" }))
public class TSalesPosChngReqDet implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sp_chng_req_id", nullable = false, length = 255)
	private Long spChngReqId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "status_id", nullable = false, length = 255)
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
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReq tChngReq;
	/**
	 * 
	 * @generated
	 */
	public TSalesPosChngReqDet() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSpChngReqId(final Long spChngReqId) {
		this.spChngReqId = spChngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSpChngReqId() {
		return this.spChngReqId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
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

	public String getReqDetails() {
		return reqDetails;
	}

	public void setReqDetails(String reqDetails) {
		this.reqDetails = reqDetails;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosChngReqDet that) {
		setSpChngReqId(that.getSpChngReqId());
		setSalesHierId(that.getSalesHierId());
		setSalesPosId(that.getSalesPosId());
		setStatusId(that.getStatusId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setComments(getComments());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((spChngReqId == null) ? 0 : spChngReqId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("spChngReqId=[").append(spChngReqId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
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
		final TSalesPosChngReqDet other = (TSalesPosChngReqDet) obj;
		if (spChngReqId == null) {
			if (other.spChngReqId != null) {
				return false;
			}
		} else if (!spChngReqId.equals(other.spChngReqId)) {
			return false;
		}
		return true;
	}
}
