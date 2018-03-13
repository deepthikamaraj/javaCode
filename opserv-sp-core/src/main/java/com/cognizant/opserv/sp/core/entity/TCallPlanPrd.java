package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TCallPlanPrd 
 * The corresponding mapping table is t_call_plan_prd 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallPlanPrds", query = "select myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd"),
		@NamedQuery(name = "CountTCallPlanPrds", query = "Select Count(c) from TCallPlanPrd c"),
		@NamedQuery(name = "FindCallPlanProdFromCustAlignment", query = "Select myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd,TCustCallPlan myTCustCallPlan,TCustAlgmnt myTCustAlgmnt,TCallPlanType myTCallPlanType where  myTCustCallPlan.tCallPlanType.callPlanTypeId=myTCallPlanType.callPlanTypeId AND myTCallPlanPrd.tCustCallPlan.custCallPlanId=myTCustCallPlan.custCallPlanId AND myTCustCallPlan.tCustAlgmnt.custAlgmntId=myTCustAlgmnt.custAlgmntId AND myTCustCallPlan.tCustAlgmnt.custAlgmntId=?1" +
				" AND myTCustCallPlan.tCallPlanType.callPlanTypeDesc=?2 AND myTCallPlanPrd.prdId=?3 and myTCallPlanPrd.tenantId =?4 "),
		@NamedQuery(name = "findTCallPlanPrdByTCustCallPlanFromFetch", query = "select myTCallPlanPrd.prdId,myTPrd.prdDesc,myTPrd.prdName,myTCallPlanPrd.plannedCalls,myTCallPlanPrd.tCustCallPlan.custCallPlanId,myTCallPlanPrd.callPlanPrdId,myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd,TPrd myTPrd, TCustCallPlan myTCustCallPlan " +
				" where myTCallPlanPrd.tCustCallPlan.custCallPlanId = myTCustCallPlan.custCallPlanId and myTCallPlanPrd.prdId = myTPrd.prdId" +
				" and myTCallPlanPrd.tCustCallPlan.custCallPlanId = ?1 and  myTCallPlanPrd.tenantId = ?2  order by myTCallPlanPrd.callPlanPrdId"),
		@NamedQuery(name = "FindTCallPlanPrdByTCustPrdAlgmnt", query = "select myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd where myTCallPlanPrd.tCustPrdAlgmnt = ?1 "),
		@NamedQuery(name = "CountTCallPlanPrdsByTCustPrdAlgmnt", query = "select Count(myTCallPlanPrd) from TCallPlanPrd myTCallPlanPrd where myTCallPlanPrd.tCustPrdAlgmnt = ?1 "),
		@NamedQuery(name = "FindTCallPlanPrdByTCustCallPlan", query = "select myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd where myTCallPlanPrd.tCustCallPlan = ?1 "),
		@NamedQuery(name= "findTCallPlanPrdByJointStmt", query="select myTCallPlanPrd.plannedCalls, myTPrd.prdName,myTPrd.prdCd  from  TCallPlanPrd myTCallPlanPrd, TPrd myTPrd, TCustPrdAlgmnt myTCustPrdAlgmnt, TPrdAlgmnt myTPrdAlgmnt, TCustAlgmnt myTCustAlgmnt where myTPrdAlgmnt.salesPosId=?1 AND myTCallPlanPrd.tenantId=?2 AND  myTCustAlgmnt.custAlgmntId=myTCustPrdAlgmnt.tCustAlgmnt.custAlgmntId AND myTCustPrdAlgmnt.tCustAlgmnt.custAlgmntId=myTCallPlanPrd.tCustPrdAlgmnt.tCustAlgmnt.custAlgmntId AND myTPrdAlgmnt.prdAlgmntId=myTCustPrdAlgmnt.tCustPrdAlgmntId.prdAlgmntId AND myTCustPrdAlgmnt.tCustPrdAlgmntId.prdAlgmntId=myTCallPlanPrd.tCustPrdAlgmnt.tCustPrdAlgmntId.prdAlgmntId AND myTCallPlanPrd.prdId=myTPrd.prdId"), 
		@NamedQuery(name = "CountTCallPlanPrdsByTCustCallPlan", query = "select Count(myTCallPlanPrd) from TCallPlanPrd myTCallPlanPrd where myTCallPlanPrd.tCustCallPlan = ?1 "),
		@NamedQuery(name = "getCallPlanPrdsByPrdId", query = "select myTCallPlanPrd from TCallPlanPrd myTCallPlanPrd where myTCallPlanPrd.prdId = ?1 ")})
@Table(name = "t_call_plan_prd", uniqueConstraints = @UniqueConstraint(columnNames = { "call_plan_prd_id" }))
public class TCallPlanPrd implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_plan_prd_id", nullable = false, length = 255)
	private Integer callPlanPrdId;

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
	@NotNull
	@Column(name = "prd_id", nullable = false, length = 255)
	private Long prdId;


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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "prd_algmnt_id", referencedColumnName = "prd_algmnt_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TCustPrdAlgmnt tCustPrdAlgmnt;

	@ManyToOne
	@JoinColumn(name = "cust_call_plan_id", referencedColumnName = "cust_call_plan_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCustCallPlan tCustCallPlan;

/*	*//**
	 *
	 * @generated
	 *//*
	public TCallPlanPrd() {
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setCallPlanPrdId(final Integer callPlanPrdId) {
		this.callPlanPrdId = callPlanPrdId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCallPlanPrdId() {
		return this.callPlanPrdId;
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

	public void setPrdId(final Long prdId) {
		this.prdId = prdId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdId() {
		return this.prdId;
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
	public TCustPrdAlgmnt getTCustPrdAlgmnt() {
		return this.tCustPrdAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustPrdAlgmnt(final TCustPrdAlgmnt tCustPrdAlgmnt) {
		this.tCustPrdAlgmnt = tCustPrdAlgmnt;

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

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallPlanPrd that) {
		setCallPlanPrdId(that.getCallPlanPrdId());
		setPlannedCalls(that.getPlannedCalls());
		setActiveFlag(that.getActiveFlag());
		setPrdId(that.getPrdId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((callPlanPrdId == null) ? 0 : callPlanPrdId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("callPlanPrdId=[+callPlanPrdId+] ");
		buffer.append("plannedCalls=["+plannedCalls+"] ");
		buffer.append("activeFlag=["+activeFlag+"] ");
		buffer.append("prdId=["+prdId+"] ");
		buffer.append("createdBy=["+createdBy+"] ");
		buffer.append("createDt=["+createDt+"] ");
		buffer.append("updatedBy=["+updatedBy+"] ");
		buffer.append("updateDt=["+updateDt+"] ");
		buffer.append("tenantId=["+tenantId+"] ");

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
		final TCallPlanPrd other = (TCallPlanPrd) obj;
		if (callPlanPrdId == null) {
			if (other.callPlanPrdId != null) {
				return false;
			}
		} else if (!callPlanPrdId.equals(other.callPlanPrdId)) {
			return false;
		}
		return true;
	}
}
