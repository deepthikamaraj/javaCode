package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * JPA class representing TCustCallPlan The corresponding mapping table is
 * t_cust_call_plan
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCustCallPlans", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan"),		
		@NamedQuery(name = "CountTCustCallPlans", query = "Select Count(c) from TCustCallPlan c"),
		@NamedQuery(name = "FindPlannedCustomerCallPlan", query = "Select myTCustCallPlan from TCustCallPlan myTCustCallPlan,TCustAlgmnt myTCustAlgmnt where  myTCustCallPlan.tCustAlgmnt.custAlgmntId=myTCustAlgmnt.custAlgmntId and  myTCustCallPlan.tCallPlanType.callPlanTypeId = ?1" +
				" and myTCustCallPlan.tCustAlgmnt.custAlgmntId = ?2 and myTCustCallPlan.tenantId = ?3 and myTCustCallPlan.tCustAlgmnt.targetFlag='Y' and myTCustCallPlan.tCustAlgmnt.activeFlag='Y' and myTCustCallPlan.activeFlag='Y'"),
	//	@NamedQuery(name = "FindTCustCallPlansByCustAlgmmntId", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCustAlgmnt.custAlgmntId = ?1 and myTCustCallPlan.tCustAlgmnt.tCust.custId =?2 and myTCustCallPlan.tenantId = ?3 and myTCustCallPlan.activeFlag='Y'"),
		@NamedQuery(name = "FindTCustCallPlanByTCustAlgmnt", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCustAlgmnt = ?1 and  myTCustCallPlan.tenantId =?2 and myTCustCallPlan.activeFlag = ?3"),
		@NamedQuery(name = "CountTCustCallPlansByTCustAlgmnt", query = "select Count(myTCustCallPlan) from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCustAlgmnt = ?1 "),
		@NamedQuery(name = "FindTCustCallPlanByTCallPlanType", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCallPlanType = ?1 "),		
		@NamedQuery(name = "FindCustomerCallPlanByJointStmt", query = "select myTCustCallPlan.plannedCalls, myTCustCallPlan.custId, myTCust.custName, myTCustCategory.categoryName, myTCustCallPlan.custCallPlanId, myTCustCallPlan " +
				" from TCustCallPlan myTCustCallPlan, TCust myTCust, TCustCategory myTCustCategory, TCallPlanType myTCallPlanType, TCustAlgmnt myTCustAlgmnt, TPrtType myTPrtType " +
				" where myTCustAlgmnt.salesPosId=?1 " +
				" AND myTCustAlgmnt.salesHierId=?2 " +
				" AND myTCustCallPlan.tenantId=?3 " +
				" AND myTCustCallPlan.tCallPlanType.callPlanTypeDesc=?4 " +
				" AND myTCustCallPlan.tCallPlanType.callPlanTypeId=myTCallPlanType.callPlanTypeId " +
				" AND myTCustCallPlan.tCustAlgmnt.custAlgmntId=myTCustAlgmnt.custAlgmntId " +
				" AND myTCustAlgmnt.tCust.custId=myTCust.custId " +
				" AND myTCust.categoryId=myTCustCategory.tCustCategoryId.custCategoryId "),
		@NamedQuery(name = "CountTCustCallPlansByTCallPlanType", query = "select Count(myTCustCallPlan) from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCallPlanType = ?1 "),
			@NamedQuery(name = "FetchCountOfcallPlan", query = "select Count(myTCustCallPlan) " +
				" from TCustCallPlan myTCustCallPlan, TCallPlanType myTCallPlanType, TCustAlgmnt myTCustAlgmnt " +
			    "where myTCustCallPlan.tCallPlanType.callPlanTypeId = myTCallPlanType.callPlanTypeId  " +
				" AND myTCustAlgmnt.custAlgmntId = myTCustCallPlan.tCustAlgmnt.custAlgmntId " +
				" AND myTCustCallPlan.tCallPlanType.callPlanTypeId=2" +
				" AND myTCustAlgmnt.targetFlag ='Y' "+
				" AND myTCustAlgmnt.algmntId=?1 " +
				" AND myTCustAlgmnt.bussUnitId=?2 " +
				" AND myTCustAlgmnt.salesTeamId=?3 " +
				" AND myTCustAlgmnt.salesPosId=?4 " +
				" AND myTCustAlgmnt.salesHierId=?5 " +
				" AND myTCustCallPlan.tenantId=?6 " +
				" AND myTCustAlgmnt.activeFlag='Y' " +
				" AND myTCustCallPlan.activeFlag='Y' " +
				"AND (myTCustCallPlan.effEndDt >= ?7 or myTCustCallPlan.effEndDt IS NULL) AND (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS NULL) "),
		@NamedQuery(name = "getCustCallPlanId" ,query = "select myTCustCallPlan.custCallPlanId from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustCallPlan myTCustCallPlan where myTCust.custId = myTCustAlgmnt.tCust.custId  and myTCustAlgmnt.tCust.custId = ?1 and myTCustAlgmnt.algmntId = ?2 and myTCustAlgmnt.bussUnitId = ?3 and myTCustAlgmnt.salesTeamId = ?4 and myTCustAlgmnt.salesHierId = ?5 and myTCustAlgmnt.salesPosId = ?6 and myTCust.tenantId =  myTCustAlgmnt.tenantId and myTCustAlgmnt.tenantId = ?7 and myTCustCallPlan.tCallPlanType.callPlanTypeId = ?8 and myTCustCallPlan.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId"),
		@NamedQuery(name = "FindTCustCallPlansByCustAffId", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custAffId = ?1 and myTCustCallPlan.activeFlag = ?2 and  myTCustCallPlan.tenantId = ?3"),
		@NamedQuery(name = "FindTCustCallPlansByCustAffIdAndCustId", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custId = ?1 and myTCustCallPlan.custAffId = ?2 and myTCustCallPlan.activeFlag = ?3 and myTCustCallPlan.tCallPlanType.callPlanTypeId = ?4  and  myTCustCallPlan.tenantId = ?5 "),
		@NamedQuery(name = "FindTCustCallPlanByTCustAlgmntObj", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCustAlgmnt = ?1 and  myTCustCallPlan.tenantId =?2"),
		@NamedQuery(name = "getTCustCall", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custId = ?1 and myTCustCallPlan.tCustAlgmnt.algmntId = ?2 and  myTCustCallPlan.tCustAlgmnt.bussUnitId  =?3 and  myTCustCallPlan.tCustAlgmnt.salesTeamId = ?4 and myTCustCallPlan.tenantId = ?5  and  myTCustCallPlan.activeFlag = ?6 and  myTCustCallPlan.tCallPlanType.callPlanTypeId=?7 and myTCustCallPlan.tCustAlgmnt.custAlgmntId = ?8"),
		@NamedQuery(name = "getTCustCallAff", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custId = ?1 and myTCustCallPlan.tCustAlgmnt.algmntId = ?2 and  myTCustCallPlan.tCustAlgmnt.bussUnitId  =?3 and  myTCustCallPlan.tCustAlgmnt.salesTeamId = ?4 and myTCustCallPlan.tenantId = ?5  and  myTCustCallPlan.activeFlag = ?6 and  myTCustCallPlan.tCallPlanType.callPlanTypeId=?7 and myTCustCallPlan.tCustAlgmnt.custAlgmntId = ?8  and myTCustCallPlan.custAffId = ?9"),
		@NamedQuery(name = "getCommittedCallPlanForCustAlgmnt", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan,TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.custAlgmntId IN ?1 and myTCustAlgmnt.custAlgmntId = myTCustCallPlan.tCustAlgmnt.custAlgmntId and myTCustCallPlan.tCallPlanType.callPlanTypeId = ?2  and myTCustCallPlan.tenantId =  myTCustAlgmnt.tenantId and myTCustAlgmnt.tenantId = ?3 and myTCustCallPlan.activeFlag='Y' and myTCustAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "FindActiveCustCallPlansByCustAlgmntId", query = "select myTCustCallPlan from TCustCallPlan myTCustCallPlan where myTCustCallPlan.tCustAlgmnt.custAlgmntId IN (?1) and myTCustCallPlan.tenantId = ?2 and myTCustCallPlan.activeFlag = 'Y' "),
		@NamedQuery(name = "getTCustCallPLCALS", query = "select myTCustCallPlan.custCallPlanId,myTCustCallPlan.plannedCalls,myTCustCallPlan.custId,myTCustCallPlan.tCustAlgmnt.custAlgmntId,myTCustCallPlan.custAffId from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custId IN ?1 and myTCustCallPlan.tCustAlgmnt.algmntId = ?2 and  myTCustCallPlan.tCustAlgmnt.bussUnitId  =?3 and  myTCustCallPlan.tCustAlgmnt.salesTeamId = ?4 and myTCustCallPlan.tenantId = ?5  and  myTCustCallPlan.activeFlag = ?6 and  myTCustCallPlan.tCallPlanType.callPlanTypeId=?7 and myTCustCallPlan.tCustAlgmnt.custAlgmntId IN ?8"),
		@NamedQuery(name = "getTCustCallAffPLCALS", query = "select myTCustCallPlan.custCallPlanId,myTCustCallPlan.plannedCalls,myTCustCallPlan.custId,myTCustCallPlan.tCustAlgmnt.custAlgmntId,myTCustCallPlan.custAffId from TCustCallPlan myTCustCallPlan where myTCustCallPlan.custId IN ?1 and myTCustCallPlan.tCustAlgmnt.algmntId = ?2 and  myTCustCallPlan.tCustAlgmnt.bussUnitId  =?3 and  myTCustCallPlan.tCustAlgmnt.salesTeamId = ?4 and myTCustCallPlan.tenantId = ?5  and  myTCustCallPlan.activeFlag = ?6 and  myTCustCallPlan.tCallPlanType.callPlanTypeId=?7 and myTCustCallPlan.tCustAlgmnt.custAlgmntId IN ?8  and myTCustCallPlan.custAffId IN ?9"),
		@NamedQuery(name = "getTCustCallDIRInfo", query = "select custalgmnt.custAlgmntId,caltype.callPlanTypeId,myTCustCallPlan.custAffId from TCustCallPlan myTCustCallPlan INNER JOIN myTCustCallPlan.tCustAlgmnt custalgmnt INNER JOIN myTCustCallPlan.tCallPlanType caltype INNER JOIN myTCustCallPlan.tCallDirss calDirs where custalgmnt.custAlgmntId IN ?1 and caltype.callPlanTypeId = ?2 and myTCustCallPlan.tenantId = ?3 and myTCustCallPlan.activeFlag='Y' and custalgmnt.activeFlag='Y' and calDirs.activeFlag='Y'"),
		@NamedQuery(name = "getTCustCallPRDInfo", query = "select custalgmnt.custAlgmntId,caltype.callPlanTypeId,myTCustCallPlan.custAffId from TCustCallPlan myTCustCallPlan INNER JOIN myTCustCallPlan.tCustAlgmnt custalgmnt INNER JOIN myTCustCallPlan.tCallPlanType caltype INNER JOIN myTCustCallPlan.tCallPlanPrdss calPrds where custalgmnt.custAlgmntId IN ?1 and caltype.callPlanTypeId = ?2 and myTCustCallPlan.tenantId = ?3 and myTCustCallPlan.activeFlag='Y' and custalgmnt.activeFlag='Y' and calPrds.activeFlag='Y'"),
		@NamedQuery(name = "GetCallDirInfoByCustCalPlanId", query = "select dir.numCalls,prd.tCallDir.callDirId,dir.tCallDirConfig.callDirConfigId,tprd.prdId,tprd.prdName,prd.tCallDirPrdId.prdPrtTypeId,wt.prdWtg from TCustCallPlan plan, TCallDir dir,TCallDirPrd prd, TCallDirConfig cofig, TCallDirPrdWt wt, TPrd tprd where dir.tCustCallPlan.custCallPlanId = plan.custCallPlanId and plan.activeFlag = dir.activeFlag and dir.activeFlag = prd.activeFlag and dir.callDirId = prd.tCallDir.callDirId and dir.activeFlag = cofig.activeFlag and dir.tCallDirConfig.callDirConfigId = cofig.callDirConfigId and cofig.activeFlag = wt.activeFlag and cofig.callDirConfigId = wt.tCallDirPrdWtId.tCallDirConfig.callDirConfigId and prd.activeFlag = tprd.activeFlag and tprd.prdId = prd.prdId and plan.activeFlag = 'Y' and prd.tCallDirPrdId.prdPrtTypeId = wt.tCallDirPrdWtId.prdPrtTypeId and plan.custCallPlanId IN ?1 group by plan.tCustAlgmnt.custAlgmntId,prd.tCallDir.callDirId,prd.tCallDirPrdId.prdPrtTypeId"),
		@NamedQuery(name = "GetProdForCallDirInfoByCustCalPlanId", query = "select planprd.callPlanPrdId,planprd.prdId,prd.prdName,calplan.tCallPlanType.callPlanTypeId,planprd.plannedCalls,calplan.custCallPlanId from TCustCallPlan calplan,TCallPlanPrd planprd,TPrd prd where calplan.activeFlag = planprd.activeFlag and calplan.custCallPlanId = planprd.tCustCallPlan.custCallPlanId and planprd.activeFlag = prd.activeFlag and planprd.prdId = prd.prdId and calplan.activeFlag = 'Y' and calplan.custCallPlanId IN ?1"),	
	
		@NamedQuery(name ="getPlannedCallsForTiers",query="SELECT custalgmnt.prtTypeId,sum(custcallplan.plannedCalls) FROM TCust cust,TCustAlgmnt custalgmnt,TCustCallPlan custcallplan,TCustCallPlanChngReqDet custcallplanchngreqdet,TChngReq chngreq WHERE cust.custId = custalgmnt.tCust.custId AND custcallplan.tCustAlgmnt.custAlgmntId = custalgmnt.custAlgmntId AND custcallplanchngreqdet.tCustCallPlan.custCallPlanId = custcallplan.custCallPlanId  AND chngreq.chngReqId = custcallplanchngreqdet.tChngReq.chngReqId AND chngreq.statusId = ?1 AND custcallplanchngreqdet.statusId =?2 AND custalgmnt.salesPosId= ?3 AND custalgmnt.targetFlag = 'Y' GROUP BY custalgmnt.prtTypeId ORDER BY custalgmnt.prtTypeId ASC"),
		
		@NamedQuery(name ="getCustomerCallPlanDetails", query="select custcallplan from TCustCallPlan custcallplan,TCustAlgmnt custalgmnt" +
				" where custAlgmnt.tCust.custId = custcallplan.custId and custcallplan.custId=?1 and custcallplan.tCustAlgmnt.salesPosId=?2 and custcallplan.tCustAlgmnt.salesHierId=?3 and custcallplan.tCustAlgmnt.algmntId=?4 and custcallplan.tCustAlgmnt.bussUnitId=?5 and custcallplan.tCustAlgmnt.salesTeamId =?6 and custcallplan.tenantId=?7 " +
				" and custcallplan.activeFlag = 'Y' and custAlgmnt.targetFlag='Y' group by custcallplan.tCallPlanType.callPlanTypeId"),
				
		@NamedQuery(name ="findTCustCallPlansByCustId", query="select custcallplan from TCustCallPlan custcallplan,TCustAlgmnt custalgmnt" +
				" where custAlgmnt.tCust.custId = custcallplan.custId and custcallplan.custId= ?1 and custcallplan.tCustAlgmnt.custAlgmntId = custalgmnt.custAlgmntId and " +
				"custcallplan.tCustAlgmnt.salesPosId= ?2 and custcallplan.tCustAlgmnt.salesHierId= ?3 and custcallplan.tenantId=custalgmnt.tenantId " +
				" and custcallplan.activeFlag = 'Y' and custAlgmnt.activeFlag = 'Y' and custAlgmnt.targetFlag='Y' "),
			
	@NamedQuery(name ="FindCallPlanByCustAlId", query="select custcallplan from TCustCallPlan custcallplan where custcallplan.tCustAlgmnt.custAlgmntId = ?1 and custcallplan.tenantId=?2 "),
				
	@NamedQuery(name ="FindCallPlanByCustAlIdForEdit", query="select custcallplan from TCustCallPlan custcallplan where custcallplan.tCustAlgmnt.custAlgmntId = ?1  and custcallplan.tenantId=?2 and custcallplan.tCallPlanType.callPlanTypeId=2")
		
		
})
@Table(name = "t_cust_call_plan", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_call_plan_id" }))
public class TCustCallPlan implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_call_plan_id", nullable = false, length = 255)
	private Integer custCallPlanId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "planned_calls", nullable = true, length = 255)
	private Short plannedCalls;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	/**
	 * 
	 * @generated
	 *//*
	@NotNull
	@Column(name = "prt_type_id", nullable = false, length = 255)
	private Integer prtTypeId;*/

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;

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

	@Transient
	private String customerName;
	
	@Transient
	private String categoryType;

	@Column(name = "cust_aff_id", nullable = true, length = 255)
	private Integer custAffId;
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCustAlgmnt tCustAlgmnt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "call_plan_type_id", referencedColumnName = "call_plan_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCallPlanType tCallPlanType;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCustCallPlan")
	@Fetch(FetchMode.SUBSELECT)
	private Set<TCallDir> tCallDirss;
	
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCustCallPlan")
	private Set<TCallPlanPrd> tCallPlanPrdss;
	
	

	/**
	 * 
	 * @generated
	 */
	/*public TCustCallPlan() {
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setCustCallPlanId(final Integer custCallPlanId) {
		this.custCallPlanId = custCallPlanId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustCallPlanId() {
		return this.custCallPlanId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPlannedCalls(final Short plannedCalls) {
		this.plannedCalls = plannedCalls;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getPlannedCalls() {
		return this.plannedCalls;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt == null) {
			Date x=null;
			this.effStartDt = x;
			
		} else {
			this.effStartDt = (Date) effStartDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt == null) {
			return null;
		} else {
			
			return (Date) this.effStartDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt == null) {
			Date x=null;
			this.effEndDt = x;
		} else {
			
			this.effEndDt = (Date) effEndDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt == null) {
			return null;
		} else {
		
			return (Date) this.effEndDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 *//*

	public void setPrtTypeId(final Integer prtTypeId) {
		this.prtTypeId = prtTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getPrtTypeId() {
		return this.prtTypeId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustId() {
		return this.custId;
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
		if (createDt == null) {
			Date x=null;
			this.createDt = x;
			
		} else {
			this.createDt = (Date) createDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt == null) {
			return null;

		} else {
			return (Date) this.createDt.clone();
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
		if (updateDt == null) {
			Date x=null;
			this.updateDt = x;
		} else {
			
			this.updateDt = (Date) updateDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt == null) {
			return null;
		} else {
			
			return (Date) this.updateDt.clone();
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

	}

	/**
	 * 
	 * @generated
	 */
	public TCallPlanType getTCallPlanType() {
		return this.tCallPlanType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallPlanType(final TCallPlanType tCallPlanType) {
		this.tCallPlanType = tCallPlanType;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCallDir> getTCallDirss() {
		return this.tCallDirss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallDirss(final Set<TCallDir> tCallDirss) {
		this.tCallDirss = tCallDirss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCallPlanPrd> getTCallPlanPrdss() {
		return this.tCallPlanPrdss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallPlanPrdss(final Set<TCallPlanPrd> tCallPlanPrdss) {
		this.tCallPlanPrdss = tCallPlanPrdss;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getCustAffId() {
		return custAffId;
	}

	public void setCustAffId(Integer custAffId) {
		this.custAffId = custAffId;
	}

	/**
	 * Gets the status id.
	 *
	 * @return the status id
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * Sets the status id.
	 *
	 * @param statusId the new status id
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustCallPlan that) {
		setCustCallPlanId(that.getCustCallPlanId());
		setPlannedCalls(that.getPlannedCalls());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		//setPrtTypeId(that.getPrtTypeId());
		setCustId(that.getCustId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setCustAffId(that.getCustAffId());
		setStatusId(that.getStatusId());
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
				+ ((custCallPlanId == null) ? 0 : custCallPlanId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custCallPlanId=[").append(custCallPlanId).append("] ");
		buffer.append("plannedCalls=[").append(plannedCalls).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		//buffer.append("prtTypeId=[").append(prtTypeId).append("] ");
		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("custAffId=[").append(custAffId).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
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
		final TCustCallPlan other = (TCustCallPlan) obj;
		if (custCallPlanId == null) {
			if (other.custCallPlanId != null) {
				return false;
			}
		} else if (!custCallPlanId.equals(other.custCallPlanId)) {
			return false;
		}
		return true;
	}
}
