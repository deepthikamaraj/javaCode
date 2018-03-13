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
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TChngReqAppr 
 * The corresponding mapping table is t_chng_req_appr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqApprs", query = "select myTChngReqAppr from TChngReqAppr myTChngReqAppr"),
		@NamedQuery(name = "fetchCRApproversDetails", query = "select myTChngReqAppr from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND myTChngReqAppr.tenantId = ?2 AND myTChngReqAppr.activeFlag='Y'"),
		@NamedQuery(name = "CountTChngReqApprs", query = "Select Count(c) from TChngReqAppr c"),
		@NamedQuery(name = "CountTChngReqApprsBySPandStatus", query = "select Count(myTChngReqAppr) from TChngReqAppr myTChngReqAppr where myTChngReqAppr.apprSalesPosId in (?1) and myTChngReqAppr.statusId = ?2 and myTChngReqAppr.tenantId = ?3 and myTChngReqAppr.activeFlag='Y' "),
		@NamedQuery(name = "FindTChngReqApprByTChngReq", query = "select myTChngReqAppr from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND  myTChngReqAppr.apprSalesHierId= ?2 AND myTChngReqAppr.apprSalesPosId= ?3 AND  myTChngReqAppr.targetApprFlag= ?4 AND myTChngReqAppr.tenantId = ?5 AND  myTChngReqAppr.activeFlag='Y' "),
		@NamedQuery(name = "FindTChngReqApprForSP", query = "select myTChngReqAppr from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND  myTChngReqAppr.apprSalesHierId= ?2 AND myTChngReqAppr.apprSalesPosId= ?3 AND myTChngReqAppr.targetApprFlag= ?4 AND myTChngReqAppr.tenantId = ?5 AND  myTChngReqAppr.activeFlag='Y' "),
		@NamedQuery(name = "CountTChngReqApprsByTChngReq", query = "select Count(*) from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq = ?1 "),
		@NamedQuery(name = "CountTChngReqApprsByStatus", query = "SELECT COUNT(DISTINCT myTChngReqAppr.apprSalesPosId) FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.tChngReq.chngReqId=?1 AND myTChngReqAppr.statusId =?2  AND  myTChngReqAppr.tenantId = ?3 AND myTChngReqAppr.activeFlag='Y' "),
		@NamedQuery(name = "getStatusForCR", query = "SELECT COUNT(myTChngReqAppr.apprSalesPosId) FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.tChngReq.chngReqId=?1 AND myTChngReqAppr.statusId !=?2 AND  myTChngReqAppr.tenantId = ?3 AND myTChngReqAppr.activeFlag='Y' "),
		@NamedQuery(name = "fetchApprDetailId", query = "SELECT myTChngReqAppr FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.tChngReq.chngReqId = ?1 and myTChngReqAppr.statusId = ?2 and myTChngReqAppr.targetApprFlag=?3 and myTChngReqAppr.activeFlag='Y' and myTChngReqAppr.tenantId = ?4 "),
	    @NamedQuery(name = "getDetailsByTargetFlag", query = "select tcra.tChngReq.chngReqId,(select COUNT(DISTINCT tcra1.tChngReq.chngReqId) from TChngReqAppr tcra1 where tcra1.apprSalesPosId = tcra.apprSalesPosId and tcra1.apprSalesHierId = tcra.apprSalesHierId and tcra1.targetApprFlag = 'Y' and tcra1.tChngReq.chngReqId = tcra.tChngReq.chngReqId and tcra1.activeFlag = tcra.activeFlag and tcra1.statusId != ?5 and tcra1.tenantId = tcra.tenantId ) from TChngReqAppr tcra where tcra.apprSalesPosId = ?1 and tcra.apprSalesHierId = ?2 and  tcra.tChngReq.chngReqId = ?3 and tcra.activeFlag='Y' and  tcra.targetApprFlag='N' and  tcra.tenantId = ?4 "),
		@NamedQuery(name = "fetchInActiveAppr", query = "SELECT myTChngReqAppr FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.tChngReq.chngReqId = ?1 AND  myTChngReqAppr.statusId = ?2 and myTChngReqAppr.apprSalesHierId != ?3 and myTChngReqAppr.apprSalesPosId != ?4 and  myTChngReqAppr.tenantId = ?5 "),
//		@NamedQuery(name = "getAffTFlag", query = "SELECT myTChngReqAppr.targetApprFlag,myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.apprSalesHierId= ?1 AND myTChngReqAppr.apprSalesPosId= ?2 and myTChngReqAppr.tChngReqApprId.tChngReq.chngReqId = ?3 AND  myTChngReqAppr.statusId = ?4 and  myTChngReqAppr.tenantId = ?5 ORDER BY myTChngReqAppr.targetApprFlag"),
		@NamedQuery(name = "ReqForApprCount", query = "select COUNT(DISTINCT tcra.tChngReq.chngReqId) from TChngReqAppr tcra  where tcra.apprSalesPosId=?1 and tcra.apprSalesHierId=?2 and tcra.statusId in ?3 and tcra.tenantId=?4 and tcra.activeFlag='Y'"),
		@NamedQuery(name = "updateTchngReqApprFlag", query = "Update TChngReqAppr myTChngReqAppr set myTChngReqAppr.activeFlag='N' where myTChngReqAppr.tChngReq.chngReqId in ?1"),
//        @NamedQuery(name = "fetchApprTransCount", query = "SELECT count(DISTINCT myTChngReqAppr.tChngReqDetail.tChngReqDetailId.chngReqDetailId) ,myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId FROM TChngReqAppr myTChngReqAppr WHERE  myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId IN ?1 AND myTChngReqAppr.statusId = ?2 AND myTChngReqAppr.apprSalesPosId = ?3 AND myTChngReqAppr.apprSalesHierId = ?4 AND myTChngReqAppr.tenantId = ?5 AND myTChngReqAppr.activeFlag = 'Y' AND myTChngReqAppr.targetApprFlag = ( SELECT min(tcra.targetApprFlag) FROM TChngReqAppr  tcra " +
//        		" WHERE   myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId=tcra.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId AND myTChngReqAppr.statusId = tcra.statusId AND myTChngReqAppr.apprSalesPosId = tcra.apprSalesPosId AND myTChngReqAppr.apprSalesHierId = tcra.apprSalesHierId AND myTChngReqAppr.tenantId = tcra.tenantId AND myTChngReqAppr.activeFlag = tcra.activeFlag order by tcra.targetApprFlag,tcra.updateDt desc  ) GROUP BY myTChngReqAppr.tChngReqDetail.tChngReqDetailId.tChngReq.chngReqId "),
		/* Added By 407986 */
		@NamedQuery(name = "GetMaxApprIdByCRId", query = "select Max(myTChngReqAppr.apprId)  from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 and myTChngReqAppr.tenantId = ?2") ,
		@NamedQuery(name = "FetchTChngReqApprByTChngReq", query = "select myTChngReqAppr from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND  myTChngReqAppr.tenantId = ?2 and myTChngReqAppr.activeFlag='Y' ORDER BY myTChngReqAppr.targetApprFlag "),
		@NamedQuery(name = "fetchPACRS", query = "SELECT myTChngReqAppr FROM TChngReqAppr myTChngReqAppr  where myTChngReqAppr.tChngReq.chngReqId = ?1 AND  myTChngReqAppr.statusId = ?2 and  myTChngReqAppr.tenantId = ?3 and myTChngReqAppr.activeFlag = 'Y' "),
		/*
		 * Addition by 409793 starts
		 */
		@NamedQuery(name = "FindTargetApprSalesPosID", query = "select distinct myTChngReqAppr.apprSalesPosId from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND myTChngReqAppr.targetApprFlag = 'Y' order by myTChngReqAppr.apprId"),
		@NamedQuery(name = "FindSourceApprSalesPosID", query = "select distinct myTChngReqAppr.apprSalesPosId from TChngReqAppr myTChngReqAppr where myTChngReqAppr.tChngReq.chngReqId = ?1 AND myTChngReqAppr.targetApprFlag = 'N'and myTChngReqAppr.tenantId = ?2 and myTChngReqAppr.activeFlag='Y' order by myTChngReqAppr.apprId"),
		@NamedQuery(name = "FindApprSalesPosID", query = "SELECT distinct (myTChngReqAppr.apprSalesPosId),myTChngReqAppr.targetApprFlag FROM TChngReqAppr myTChngReqAppr WHERE myTChngReqAppr.tChngReq.chngReqId = ?1 and myTChngReqAppr.tenantId = ?2 and myTChngReqAppr.activeFlag='Y' GROUP BY myTChngReqAppr.targetApprFlag, myTChngReqAppr.apprSalesHierId ORDER BY myTChngReqAppr.targetApprFlag ASC,myTChngReqAppr.apprSalesHierId DESC"),
		@NamedQuery(name = "FindTargetApprStatusId", query = "select  myTChngReqAppr.statusId from TChngReqAppr myTChngReqAppr WHERE myTChngReqAppr.tChngReq.chngReqId = ?1 AND myTChngReqAppr.targetApprFlag =?2 AND myTChngReqAppr.tenantId = ?3"),
		@NamedQuery(name = "FindApproverComments", query = "select  myTChngReqAppr.comments from TChngReqAppr myTChngReqAppr WHERE myTChngReqAppr.tChngReq.chngReqId = ?1 AND myTChngReqAppr.targetApprFlag =?2 AND myTChngReqAppr.tenantId = ?3")
		
		/*
 * Addition by 409793 ends
 */

})
@Table(name = "t_chng_req_appr")
public class TChngReqAppr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appr_id", nullable = false, length = 255)
	private Integer apprId;

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

	@Column(name = "appr_type_id", nullable = false, length = 255)
	private Integer apprTypeId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "dlg_sales_pos_id", nullable = true, length = 255)
	private Long dlgSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "dlg_sales_hier_id", nullable = true, length = 255)
	private Long dlgSalesHierId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "target_appr_flag", nullable = true, length = 1)
	private Character targetApprFlag;

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
	
	@Version
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
	@Length(max = 500)
	@Column(name = "comments", nullable = true, length = 500)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;
	
	
/*	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "appr_sales_pos_id", referencedColumnName = "sales_pos_id", nullable = true, insertable = false, updatable = false),
		@JoinColumn(name = "appr_sales_hier_id", referencedColumnName = "sales_hier_id", nullable = true, insertable = false, updatable = false) })
	@Valid
	private TSalesPos tApprSalesPos;*/
	
	/**
	 * 
	 * @generated
	 */
/*	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "dlg_sales_pos_id", referencedColumnName = "sales_pos_id", nullable = true, insertable = false, updatable = false),
		@JoinColumn(name = "dlg_sales_hier_id", referencedColumnName = "sales_hier_id", nullable = true, insertable = false, updatable = false) })
	@Valid
	private TSalesPos tDlgSalesPos;*/
	
	
	/**
	 * 
	 * @generated
	 */
	
	@ManyToOne
	@JoinColumn(name = "chng_req_id", referencedColumnName = "chng_req_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReq tChngReq;
	

	/**
	 *
	 * @generated
	 */
	public TChngReqAppr() {
	}

	/**
	 * Gets the appr id.
	 *
	 * @return the appr id
	 */
	public Integer getApprId() {
		return apprId;
	}

	/**
	 * Sets the appr id.
	 *
	 * @param apprId the new appr id
	 */
	public void setApprId(Integer apprId) {
		this.apprId = apprId;
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

			this.gettChngReq().setChngReqId(this.tChngReq.getChngReqId());

		}

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

	public void setApprTypeId(final Integer apprTypeId) {
		this.apprTypeId = apprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getApprTypeId() {
		return this.apprTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDlgSalesPosId(final Long dlgSalesPosId) {
		this.dlgSalesPosId = dlgSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getDlgSalesPosId() {
		return this.dlgSalesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDlgSalesHierId(final Long dlgSalesHierId) {
		this.dlgSalesHierId = dlgSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getDlgSalesHierId() {
		return this.dlgSalesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTargetApprFlag(final Character targetApprFlag) {
		this.targetApprFlag = targetApprFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getTargetApprFlag() {
		return this.targetApprFlag;
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
/*
	public TSalesPos gettApprSalesPos() {
		return tApprSalesPos;
	}
	*//**
	 * 
	 * @generated
	 *//*

	public void settApprSalesPos(TSalesPos tApprSalesPos) {
		this.tApprSalesPos = tApprSalesPos;
	}
	*//**
	 * 
	 * @generated
	 *//*

	public TSalesPos gettDlgSalesPos() {
		return tDlgSalesPos;
	}
	*//**
	 * 
	 * @generated
	 *//*

	public void settDlgSalesPos(TSalesPos tDlgSalesPos) {
		this.tDlgSalesPos = tDlgSalesPos;
	}*/

	public TChngReq gettChngReq() {
		return tChngReq;
	}

	public void settChngReq(TChngReq tChngReq) {
		this.tChngReq = tChngReq;
	}


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqAppr that) {
		setApprId(that.getApprId());
		setApprSalesHierId(that.getApprSalesHierId());
		setApprSalesPosId(that.getApprSalesPosId());
		setStatusId(that.getStatusId());
		setApprTypeId(that.getApprTypeId());
		setDlgSalesPosId(that.getDlgSalesPosId());
		setDlgSalesHierId(that.getDlgSalesHierId());
		setTargetApprFlag(that.getTargetApprFlag());
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

	public Character getActiveFlag() {
		return activeFlag;
	}





	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}





	/**
	 * @generated
	 * 
	 */
	

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("apprId=[").append(apprId).append("] ");
		buffer.append("apprSalesHierId=[").append(apprSalesHierId).append("] ");
		buffer.append("apprSalesPosId=[").append(apprSalesPosId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("apprTypeId=[").append(apprTypeId).append("] ");
		buffer.append("dlgSalesPosId=[").append(dlgSalesPosId).append("] ");
		buffer.append("dlgSalesHierId=[").append(dlgSalesHierId).append("] ");
		buffer.append("targetApprFlag=[").append(targetApprFlag).append("] ");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actionBy == null) ? 0 : actionBy.hashCode());
		result = prime * result
				+ ((actionDtTm == null) ? 0 : actionDtTm.hashCode());
		result = prime * result
				+ ((activeFlag == null) ? 0 : activeFlag.hashCode());
		result = prime * result + ((apprId == null) ? 0 : apprId.hashCode());
		result = prime * result
				+ ((apprSalesHierId == null) ? 0 : apprSalesHierId.hashCode());
		result = prime * result
				+ ((apprSalesPosId == null) ? 0 : apprSalesPosId.hashCode());
		result = prime * result
				+ ((apprTypeId == null) ? 0 : apprTypeId.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((dlgSalesHierId == null) ? 0 : dlgSalesHierId.hashCode());
		result = prime * result
				+ ((dlgSalesPosId == null) ? 0 : dlgSalesPosId.hashCode());
		result = prime * result
				+ ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result
				+ ((tChngReq == null) ? 0 : tChngReq.hashCode());
		result = prime * result
				+ ((targetApprFlag == null) ? 0 : targetApprFlag.hashCode());
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result
				+ ((updateDt == null) ? 0 : updateDt.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TChngReqAppr other = (TChngReqAppr) obj;
		if (actionBy == null) {
			if (other.actionBy != null)
				return false;
		} else if (!actionBy.equals(other.actionBy))
			return false;
		if (actionDtTm == null) {
			if (other.actionDtTm != null)
				return false;
		} else if (!actionDtTm.equals(other.actionDtTm))
			return false;
		if (activeFlag == null) {
			if (other.activeFlag != null)
				return false;
		} else if (!activeFlag.equals(other.activeFlag))
			return false;
		if (apprId == null) {
			if (other.apprId != null)
				return false;
		} else if (!apprId.equals(other.apprId))
			return false;
		if (apprSalesHierId == null) {
			if (other.apprSalesHierId != null)
				return false;
		} else if (!apprSalesHierId.equals(other.apprSalesHierId))
			return false;
		if (apprSalesPosId == null) {
			if (other.apprSalesPosId != null)
				return false;
		} else if (!apprSalesPosId.equals(other.apprSalesPosId))
			return false;
		if (apprTypeId == null) {
			if (other.apprTypeId != null)
				return false;
		} else if (!apprTypeId.equals(other.apprTypeId))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (createDt == null) {
			if (other.createDt != null)
				return false;
		} else if (!createDt.equals(other.createDt))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (dlgSalesHierId == null) {
			if (other.dlgSalesHierId != null)
				return false;
		} else if (!dlgSalesHierId.equals(other.dlgSalesHierId))
			return false;
		if (dlgSalesPosId == null) {
			if (other.dlgSalesPosId != null)
				return false;
		} else if (!dlgSalesPosId.equals(other.dlgSalesPosId))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		if (tChngReq == null) {
			if (other.tChngReq != null)
				return false;
		} else if (!tChngReq.equals(other.tChngReq))
			return false;
		if (targetApprFlag == null) {
			if (other.targetApprFlag != null)
				return false;
		} else if (!targetApprFlag.equals(other.targetApprFlag))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		if (updateDt == null) {
			if (other.updateDt != null)
				return false;
		} else if (!updateDt.equals(other.updateDt))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}
}
