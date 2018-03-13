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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * JPA class representing TCallDirConfig The corresponding mapping table is
 * t_call_dir_config
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallDirConfigs", query = "select myTCallDirConfig from TCallDirConfig myTCallDirConfig"),
		@NamedQuery(name = "CountTCallDirConfigs", query = "Select Count(c) from TCallDirConfig c"),
		@NamedQuery(name = "FindTCallDirConfigByTCallPlanConfig", query = "select myTCallDirConfig from TCallDirConfig myTCallDirConfig where myTCallDirConfig.tCallPlanConfig = ?1 "),
		@NamedQuery(name = "CountTCallDirConfigsByTCallPlanConfig", query = "select Count(myTCallDirConfig) from TCallDirConfig myTCallDirConfig where myTCallDirConfig.tCallPlanConfig = ?1 "),
		@NamedQuery(name = "FindTCallDirConfig", query = "select myTCallDirConfig from TCallDirConfig myTCallDirConfig where myTCallDirConfig.tCallPlanConfig.callPlanConfigId = ?1 and myTCallDirConfig.prdCnt = ?2 and myTCallDirConfig.activeFlag = 'Y' and myTCallDirConfig.tenantId = ?3  ") })
@Table(name = "t_call_dir_config", uniqueConstraints = @UniqueConstraint(columnNames = { "call_dir_config_id" }))
public class TCallDirConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_dir_config_id", nullable = false, length = 255)
	private Integer callDirConfigId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "prd_cnt", nullable = false, length = 255)
	private Integer prdCnt;

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
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

	@ManyToOne
	@JoinColumn(name = "call_plan_config_id", referencedColumnName = "call_plan_config_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TCallPlanConfig tCallPlanConfig;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCallDirPrdWtId.tCallDirConfig")
	private Set<TCallDirPrdWt> tCallDirPrdWts;

	/**
	 * 
	 * @generated
	 */
/*	public TCallDirConfig() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setCallDirConfigId(final Integer callDirConfigId) {
		this.callDirConfigId = callDirConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCallDirConfigId() {
		return this.callDirConfigId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdCnt(final Integer prdCnt) {
		this.prdCnt = prdCnt;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrdCnt() {
		return this.prdCnt;
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
	public TCallPlanConfig getTCallPlanConfig() {
		return this.tCallPlanConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCallPlanConfig(final TCallPlanConfig tCallPlanConfig) {
		this.tCallPlanConfig = tCallPlanConfig;

	}

	public Set<TCallDirPrdWt> gettCallDirPrdWts() {
		return tCallDirPrdWts;
	}

	public void settCallDirPrdWts(Set<TCallDirPrdWt> tCallDirPrdWts) {
		this.tCallDirPrdWts = tCallDirPrdWts;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallDirConfig that) {
		setCallDirConfigId(that.getCallDirConfigId());
		setPrdCnt(that.getPrdCnt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
	}

	// /**
	// * @generated
	// *
	// */
	// @Override
	// public int hashCode() {
	// int prime = 31;
	// int result = 1;
	// result = prime * result + ((callDirConfigId == null) ? 0 :
	// callDirConfigId.hashCode())+(int)Math.random();
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
	// buffer.append("callDirConfigId=[").append(callDirConfigId).append("] ");
	// buffer.append("prdCnt=[").append(prdCnt).append("] ");
	// buffer.append("createdBy=[").append(createdBy).append("] ");
	// buffer.append("createDt=[").append(createDt).append("] ");
	// buffer.append("updatedBy=[").append(updatedBy).append("] ");
	// buffer.append("updateDt=[").append(updateDt).append("] ");
	// buffer.append("tenantId=[").append(tenantId).append("] ");
	// buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
	// final TCallDirConfig other = (TCallDirConfig) obj;
	// if (callDirConfigId == null) {
	// if (other.callDirConfigId != null) {
	// return false;
	// }
	// } else if (!callDirConfigId.equals(other.callDirConfigId)) {
	// return false;
	// }
	// return true;
	// }
}
