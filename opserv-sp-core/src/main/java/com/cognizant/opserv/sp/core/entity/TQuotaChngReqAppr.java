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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TQuotaChngReqAppr 
 * The corresponding mapping table is t_quota_chng_req_appr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaChngReqApprs", query = "select myTQuotaChngReqAppr from TQuotaChngReqAppr myTQuotaChngReqAppr"),
		@NamedQuery(name = "CountTQuotaChngReqApprs", query = "Select Count(c) from TQuotaChngReqAppr c"),
	    /*	Added by 419350*/
		@NamedQuery(name = "FetchTQuotaChngReqApprById", query = "select myTQuotaChngReqAppr from TQuotaChngReqAppr myTQuotaChngReqAppr where myTQuotaChngReqAppr.tQuotaChngReqApprId.quotaChngReqId = ?1 AND  myTQuotaChngReqAppr.tenantId = ?2 and myTQuotaChngReqAppr.activeFlag='Y'"),
		@NamedQuery(name = "fetchQuotaReqForApprCount", query = "select COUNT(DISTINCT myTQuotaChngReqAppr.tQuotaChngReqApprId.quotaChngReqId ) from TQuotaChngReqAppr myTQuotaChngReqAppr  where myTQuotaChngReqAppr.apprSalesPosId=?1 and myTQuotaChngReqAppr.apprSalesHierId=?2 and myTQuotaChngReqAppr.statusId in ?3 and myTQuotaChngReqAppr.tenantId=?4 and myTQuotaChngReqAppr.activeFlag='Y'"),
		@NamedQuery(name = "FindQuotaApprSalesPosID", query = "SELECT distinct (myTQuotaChngReqAppr.apprSalesPosId) FROM TQuotaChngReqAppr myTQuotaChngReqAppr WHERE myTQuotaChngReqAppr.tQuotaChngReqApprId.quotaChngReqId = ?1 and myTQuotaChngReqAppr.tenantId = ?2 and myTQuotaChngReqAppr.activeFlag='Y' GROUP BY  myTQuotaChngReqAppr.apprSalesHierId ORDER BY myTQuotaChngReqAppr.apprSalesHierId DESC")

		/*	end*/
		
		
})
@Table(name = "t_quota_chng_req_appr")
public class TQuotaChngReqAppr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TQuotaChngReqApprId tQuotaChngReqApprId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "appr_sales_hier_id", nullable = true, length = 255)
	private Long apprSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "appr_sales_pos_id", nullable = true, length = 255)
	private Long apprSalesPosId;

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
	@Column(name = "action_dt_tm", nullable = false, length = 19)
	private Date actionDtTm;

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
	@Length(max = 400)
	@Column(name = "comments", nullable = true, length = 400)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	/**
	 *
	 * @generated
	 */
	public TQuotaChngReqAppr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTQuotaChngReqApprId(final TQuotaChngReqApprId tQuotaChngReqApprId) {
		this.tQuotaChngReqApprId = tQuotaChngReqApprId;
	}

	/**
	 * 
	 * @generated
	 */
	public TQuotaChngReqApprId getTQuotaChngReqApprId() {
		return this.tQuotaChngReqApprId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprSalesHierId(final Long apprSalesHierId) {
		this.apprSalesHierId = apprSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getApprSalesHierId() {
		return this.apprSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprSalesPosId(final Long apprSalesPosId) {
		this.apprSalesPosId = apprSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getApprSalesPosId() {
		return this.apprSalesPosId;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaChngReqAppr that) {
		setTQuotaChngReqApprId(that.getTQuotaChngReqApprId());
		setApprSalesHierId(that.getApprSalesHierId());
		setApprSalesPosId(that.getApprSalesPosId());
		setStatusId(that.getStatusId());
		setActionDtTm(that.getActionDtTm());
		setActionBy(that.getActionBy());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setComments(that.getComments());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tQuotaChngReqApprId == null) ? 0 : tQuotaChngReqApprId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tQuotaChngReqApprId=[").append(tQuotaChngReqApprId).append("] ");
		buffer.append("apprSalesHierId=[").append(apprSalesHierId).append("] ");
		buffer.append("apprSalesPosId=[").append(apprSalesPosId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("actionDtTm=[").append(actionDtTm).append("] ");
		buffer.append("actionBy=[").append(actionBy).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

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
		final TQuotaChngReqAppr other = (TQuotaChngReqAppr) obj;
		if (tQuotaChngReqApprId == null) {
			if (other.tQuotaChngReqApprId != null) {
				return false;
			}
		} else if (!tQuotaChngReqApprId.equals(other.tQuotaChngReqApprId)) {
			return false;
		}
		return true;
	}
}
