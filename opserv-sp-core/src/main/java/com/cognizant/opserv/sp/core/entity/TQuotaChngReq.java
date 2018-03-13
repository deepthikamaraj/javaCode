package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TQuotaChngReq 
 * The corresponding mapping table is t_quota_chng_req 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaChngReqs", query = "select myTQuotaChngReq from TQuotaChngReq myTQuotaChngReq"),
		@NamedQuery(name = "CountTQuotaChngReqs", query = "Select Count(c) from TQuotaChngReq c"),
		@NamedQuery(name = "myQuotaRequestCount", query = "select count(myTQuotaChngReq) from TQuotaChngReq myTQuotaChngReq where myTQuotaChngReq.reqSalesPosId in ?1 and myTQuotaChngReq.tenantId = ?2 and myTQuotaChngReq.activeFlag='Y'"),
		@NamedQuery(name = "FindQuotaCRReqSPEndDt", query = "select myTSalesPos.effEndDt from TQuotaChngReq myTQuotaChngReq,TSalesPos myTSalesPos where myTQuotaChngReq.quotaChngReqId = ?1 and myTQuotaChngReq.tenantId= ?2 and myTQuotaChngReq.reqSalesPosId = myTSalesPos.salesPosId and myTQuotaChngReq.reqSalesHierId =myTSalesPos.tAlgmntSalesHier.salesHierId and myTSalesPos.tenantId= ?2") ,


})
@Table(name = "t_quota_chng_req", uniqueConstraints = @UniqueConstraint(columnNames = { "quota_chng_req_id" }))
public class TQuotaChngReq implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "quota_chng_req_id", nullable = false, length = 255)
	private Long quotaChngReqId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "quota_config_id", nullable = false, length = 255)
	private Long quotaConfigId;


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
	@Column(name = "submit_dt_tm", nullable = true, length = 19)
	private Date submitDtTm;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "req_sales_pos_id", nullable = false, length = 255)
	private Long reqSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "req_sales_hier_id", nullable = false, length = 255)
	private Long reqSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "submitted_by", nullable = true, length = 255)
	private Integer submittedBy;

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
	@Column(name = "action_by", nullable = true, length = 255)
	private Integer actionBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "action_dt_tm", nullable = true, length = 19)
	private Date actionDtTm;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "buss_reason", nullable = true, length = 500)
	private String bussReason;

	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 400)
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "last_sales_pos_id", nullable = true, length = 255)
	private Long lastSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "last_sales_hier_id", nullable = true, length = 255)
	private Long lastSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "chng_req_num", nullable = false, length = 255)
	private Long chngReqNum;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 400)
	@Column(name = "req_sales_pos_code", nullable = true, length = 400)
	private String reqSalesPosCode;
	/**
	 * 
	 * @generated
	 */
	@Length(max = 400)
	@Column(name = "req_sales_pos_name", nullable = true, length = 400)
	private String reqSalesPosName;
	
	
	
	/**
	 *
	 * @generated
	 */
	public TQuotaChngReq() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setQuotaChngReqId(final Long quotaChngReqId) {
		this.quotaChngReqId = quotaChngReqId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getQuotaChngReqId() {
		return this.quotaChngReqId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setQuotaConfigId(final Long quotaConfigId) {
		this.quotaConfigId = quotaConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getQuotaConfigId() {
		return this.quotaConfigId;
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

	public void setSubmitDtTm(final Date submitDtTm) {
		if (submitDtTm != null) {
			this.submitDtTm = (Date) submitDtTm.clone();
		} else {
			this.submitDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSubmitDtTm() {
		if (this.submitDtTm != null) {
			return (Date) this.submitDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setReqSalesPosId(final Long reqSalesPosId) {
		this.reqSalesPosId = reqSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getReqSalesPosId() {
		return this.reqSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReqSalesHierId(final Long reqSalesHierId) {
		this.reqSalesHierId = reqSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getReqSalesHierId() {
		return this.reqSalesHierId;
	}

	

	/**
	 * 
	 * @generated
	 */

	public void setSubmittedBy(final Integer submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSubmittedBy() {
		return this.submittedBy;
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

	public void setActionBy(final Integer actionBy) {
		this.actionBy = actionBy;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getActionBy() {
		return this.actionBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setActionDtTm(final Date actionDtTm) {
		if (actionDtTm != null) {
			this.actionDtTm = (Date) actionDtTm.clone();
		} else {
			this.actionDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getActionDtTm() {
		if (this.actionDtTm != null) {
			return (Date) this.actionDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussReason(final String bussReason) {
		this.bussReason = bussReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getBussReason() {
		return this.bussReason;
	}

	

	/**
	 * 
	 * @generated
	 */

	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @generated
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLastSalesPosId(final Long lastSalesPosId) {
		this.lastSalesPosId = lastSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getLastSalesPosId() {
		return this.lastSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLastSalesHierId(final Long lastSalesHierId) {
		this.lastSalesHierId = lastSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getLastSalesHierId() {
		return this.lastSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setChngReqNum(final Long chngReqNum) {
		this.chngReqNum = chngReqNum;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getChngReqNum() {
		return this.chngReqNum;
	}

	
	/**
	 * 
	 * @generated
	 */
	public String getReqSalesPosCode() {
		return reqSalesPosCode;
	}
	/**
	 * 
	 * @generated
	 */
	public void setReqSalesPosCode(String reqSalesPosCode) {
		this.reqSalesPosCode = reqSalesPosCode;
	}
	/**
	 * 
	 * @generated
	 */
	public String getReqSalesPosName() {
		return reqSalesPosName;
	}
	/**
	 * 
	 * @generated
	 */
	public void setReqSalesPosName(String reqSalesPosName) {
		this.reqSalesPosName = reqSalesPosName;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaChngReq that) {
		setQuotaChngReqId(that.getQuotaChngReqId());
		setQuotaConfigId(that.getQuotaConfigId());
		setStatusId(that.getStatusId());
		setSubmitDtTm(that.getSubmitDtTm());
		setReqSalesPosId(that.getReqSalesPosId());
		setReqSalesHierId(that.getReqSalesHierId());
		setSubmittedBy(that.getSubmittedBy());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActionBy(that.getActionBy());
		setActionDtTm(that.getActionDtTm());
		setBussReason(that.getBussReason());
		setComments(that.getComments());
		setLastSalesPosId(that.getLastSalesPosId());
		setLastSalesHierId(that.getLastSalesHierId());
		setChngReqNum(that.getChngReqNum());
		setReqSalesPosCode(that.getReqSalesPosCode());
		setReqSalesPosName(that.getReqSalesPosName());
		
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((quotaChngReqId == null) ? 0 : quotaChngReqId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("quotaChngReqId=[").append(quotaChngReqId).append("] ");
		buffer.append("quotaConfigId=[").append(quotaConfigId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("submitDtTm=[").append(submitDtTm).append("] ");
		buffer.append("reqSalesPosId=[").append(reqSalesPosId).append("] ");
		buffer.append("reqSalesHierId=[").append(reqSalesHierId).append("] ");
		buffer.append("submittedBy=[").append(submittedBy).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("actionBy=[").append(actionBy).append("] ");
		buffer.append("actionDtTm=[").append(actionDtTm).append("] ");
		buffer.append("bussReason=[").append(bussReason).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("lastSalesPosId=[").append(lastSalesPosId).append("] ");
		buffer.append("lastSalesHierId=[").append(lastSalesHierId).append("] ");
		buffer.append("chngReqNum=[").append(chngReqNum).append("] ");
		buffer.append("reqSalesPosCode=[").append(reqSalesPosCode).append("] ");
		buffer.append("reqSalesPosName=[").append(reqSalesPosName).append("] ");
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
		final TQuotaChngReq other = (TQuotaChngReq) obj;
		if (quotaChngReqId == null) {
			if (other.quotaChngReqId != null) {
				return false;
			}
		} else if (!quotaChngReqId.equals(other.quotaChngReqId)) {
			return false;
		}
		return true;
	}
}
