package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TCallPlanConfig The corresponding mapping table is
 * t_call_plan_config
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallPlanConfigs", query = "select myTCallPlanConfig from TCallPlanConfig myTCallPlanConfig"),
		@NamedQuery(name = "CountTCallPlanConfigs", query = "Select Count(c) from TCallPlanConfig c"),
		@NamedQuery(name = "updateTCallPlanConfigActiveFlag", query = "UPDATE TCallPlanConfig myTCallPlanConfig SET myTCallPlanConfig.activeFlag= ?1 WHERE myTCallPlanConfig.callPlanConfigId= ?2"),
		@NamedQuery(name = "FindTCallPlanConfigByTAlgmntSalesTeam", query = "select myTCallPlanConfig from TCallPlanConfig myTCallPlanConfig where myTCallPlanConfig.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTCallPlanConfigsByTAlgmntSalesTeam", query = "select Count(myTCallPlanConfig) from TCallPlanConfig myTCallPlanConfig where myTCallPlanConfig.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "findStatusOfCallPlan", query = "select myTCallPlanConfig.effStartDt,myTCallPlanConfig.effEndDt,myTCallPlanConfig.callDirChangeFlag " +
				  " from TSalesPos myTSalesPos, TCallPlanConfig myTCallPlanConfig "+
				  " where myTSalesPos.tenantId = myTCallPlanConfig.tenantId "+
				  " AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId "+
				  " AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId "+
				  " AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId "+
				  " AND myTSalesPos.salesPosId = ?1 "+
				  " AND myTSalesPos.tAlgmntSalesHier.salesHierId= ?2 "+
				  " AND myTSalesPos.tenantId = ?3 "),
	   @NamedQuery(name = "FindTCallPlanConfigsByAlBuSt", query = "select myTCallPlanConfig from TCallPlanConfig myTCallPlanConfig where myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId=?1 AND myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=?2 AND myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=?3 AND myTCallPlanConfig.tenantId=?4  AND myTCallPlanConfig.activeFlag='Y'"),
	@NamedQuery(name = "FindTCallPlanConfigsByFlag", query = "select myTCallPlanConfig from TCallPlanConfig myTCallPlanConfig where myTCallPlanConfig.affiliationFlag=?1 AND myTCallPlanConfig.callDirChangeFlag=?2 AND myTCallPlanConfig.tenantId=?3  AND myTCallPlanConfig.activeFlag='Y'"),
	@NamedQuery(name = "FindActiveTCallPlanConfigsByAlBuSt", query = "select myTCallPlanConfig.callDirChangeFlag from TCallPlanConfig myTCallPlanConfig " +
			" where myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId= ?1 AND myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId= ?2 AND myTCallPlanConfig.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId= ?3 " +
			" AND myTCallPlanConfig.tenantId= ?4  AND (myTCallPlanConfig.activeFlag= ?5 OR myTCallPlanConfig.activeFlag= ?6) "),
		

})
@Table(name = "t_call_plan_config", uniqueConstraints = @UniqueConstraint(columnNames = { "call_plan_config_id" }))
public class TCallPlanConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_plan_config_id", nullable = false, length = 255)
	private Integer callPlanConfigId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	@Column(name = "aff_flag", nullable = false, length = 1)
	private Character affiliationFlag;

	@Column(name = "call_dir_change_flag", nullable = false, length = 1)
	private Character callDirChangeFlag;

	@Column(name = "prd_split_flag", nullable = false, length = 1)
	private Character productSpiltFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "max_prd_cnt", nullable = true, length = 255)
	private Short maxPrdCnt;

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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

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
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCallPlanConfig")
	private Set<TCallDirConfig> tCallDirConfigs;
/*
	*//**
	 *
	 * @generated
	 *//*
	public TCallPlanConfig() {
	}
*/
	public Character getAffiliationFlag() {
		return affiliationFlag;
	}

	public void setAffiliationFlag(Character affiliationFlag) {
		this.affiliationFlag = affiliationFlag;
	}

	public Character getCallDirChangeFlag() {
		return callDirChangeFlag;
	}

	public void setCallDirChangeFlag(Character callDirChangeFlag) {
		this.callDirChangeFlag = callDirChangeFlag;
	}

	public Character getProductSpiltFlag() {
		return productSpiltFlag;
	}

	public void setProductSpiltFlag(Character productSpiltFlag) {
		this.productSpiltFlag = productSpiltFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCallPlanConfigId(final Integer callPlanConfigId) {
		this.callPlanConfigId = callPlanConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCallPlanConfigId() {
		return this.callPlanConfigId;
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

	public void setMaxPrdCnt(final Short maxPrdCnt) {
		this.maxPrdCnt = maxPrdCnt;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getMaxPrdCnt() {
		return this.maxPrdCnt;
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
			this.effEndDt = null;
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
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return this.tAlgmntSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;

	}

	public Set<TCallDirConfig> gettCallDirConfigs() {
		return tCallDirConfigs;
	}

	public void settCallDirConfigs(Set<TCallDirConfig> tCallDirConfigs) {
		this.tCallDirConfigs = tCallDirConfigs;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallPlanConfig that) {
		setCallPlanConfigId(that.getCallPlanConfigId());
		setActiveFlag(that.getActiveFlag());
		setMaxPrdCnt(that.getMaxPrdCnt());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
	}

	public Short getTenantId() {
		return tenantId;
	}

	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	

	// /**
	// * @generated
	// *
	// */
	// @Override
	// public int hashCode() {
	// int prime = 31;
	// int result = 1;
	// result = prime * result + ((callPlanConfigId == null) ? 0 :
	// callPlanConfigId.hashCode());
	// return result;
	// }
	//
	// /**
	// * Returns a textual representation of a bean.
	// *
	// * @generated
	// */
	// public String toString() {
	//
	// final StringBuilder buffer = new StringBuilder();
	//
	// buffer.append("callPlanConfigId=[").append(callPlanConfigId).append("] ");
	// buffer.append("activeFlag=[").append(activeFlag).append("] ");
	// buffer.append("maxPrdCnt=[").append(maxPrdCnt).append("] ");
	// buffer.append("effStartDt=[").append(effStartDt).append("] ");
	// buffer.append("effEndDt=[").append(effEndDt).append("] ");
	//
	// return buffer.toString();
	// }
	//
	// /**
	// * @generated
	// *
	// */
	// @Override
	// public boolean equals(final Object obj) {
	//
	// if (this == obj) {
	// return true;
	// }
	// if (obj == null) {
	// return false;
	// }
	// if (getClass() != obj.getClass()) {
	// return false;
	// }
	// final TCallPlanConfig other = (TCallPlanConfig) obj;
	// if (callPlanConfigId == null) {
	// if (other.callPlanConfigId != null) {
	// return false;
	// }
	// } else if (!callPlanConfigId.equals(other.callPlanConfigId)) {
	// return false;
	// }
	// return true;
	// }
}
