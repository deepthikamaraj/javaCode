package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TMtrExecConfig 
 * The corresponding mapping table is t_mtr_exec_config 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrExecConfigs", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig"),
		@NamedQuery(name = "CountTMtrExecConfigs", query = "Select Count(c) from TMtrExecConfig c"),
		@NamedQuery(name = "FindTMtrExecConfigByTMtrConfig", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrConfig = ?1 "),
		@NamedQuery(name = "CountTMtrExecConfigsByTMtrConfig", query = "select Count(myTMtrExecConfig) from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrConfig = ?1 "),
		@NamedQuery(name = "FindTMtrExecConfigByTMtrTrigger", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrTrigger = ?1 "),
		@NamedQuery(name = "CountTMtrExecConfigsByTMtrTrigger", query = "select Count(myTMtrExecConfig) from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrTrigger = ?1 "),
		@NamedQuery(name = "FindTMtrExecConfigByMtrId", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.activeFlag = 'Y' and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = ?1"),
		@NamedQuery(name = "getTMtrExecConfigs", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig, TMtrConfig myTMtrConfig, TMtr myTMtr where myTMtrExecConfig.tMtrTrigger.triggerId = ?1 and myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId = ?2 and myTMtrExecConfig.tenantId = ?3 and myTMtrConfig.tAlgmntSalesHier.salesHierId = myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = myTMtrConfig.tMtrConfigId.mtrId and myTMtrConfig.tMtrConfigId.mtrId = myTMtr.mtrId  and myTMtrExecConfig.activeFlag = 'Y' and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?4 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?5 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?6 and myTMtrExecConfig.tenantId = myTMtrConfig.tenantId and myTMtrConfig.tenantId = myTMtr.tenantId"),
		@NamedQuery(name = "getExecutionConfiguration", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tenantId =?1 "),
		@NamedQuery(name = "getConfigIdForTrigger", query = "select myTMtrExecConfig.configId from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrConfig.tMtr.mtrId = ?1 and myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId = ?2 and myTMtrExecConfig.tMtrTrigger.triggerId = ?3 "),
		@NamedQuery(name = "getTMtrExecConfigsForTrigger", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where myTMtrExecConfig.tMtrConfig.tMtr.mtrId = ?1 and myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId = ?2 and myTMtrExecConfig.tMtrTrigger.triggerId = ?3 "),
		@NamedQuery(name = "FindAllTMtrExecConfigByMtrId", query = "select myTMtrExecConfig from TMtrExecConfig myTMtrExecConfig where  myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = ?1 and myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId = ?2"),
		@NamedQuery(name = "FindMetricExecutionConfiguration", query = "select myTMtrTrigger.featureId,myTMtrTrigger.tFeatureType.typeId,myTMtrExecConfig.tMtrTrigger.triggerId,myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId,myTMtrExpr.exprId,myTMtrExpr.exprType,myTMtr.mtrName,myTMtr.minValue,myTMtr.maxValue,myTMtr.enforceFlag,myTMtrConfig.tMtrConfigId.salesHierId,	" +
				" myTMtrExecConfig.configId,myTMtrConfig.childRollupFlag, myTMtrExpr.mtrExpr, msg.valMsg, myTMtr.dataDotFlag, myTMtr.prec,myTMtr.uomId " +
				" from TMtr myTMtr, TMtrExpr myTMtrExpr , TMtrConfig myTMtrConfig , TMtrExecConfig myTMtrExecConfig , TMtrTrigger myTMtrTrigger  , TMtrValMsg msg" +
				" where myTMtrTrigger.triggerId = myTMtrExecConfig.tMtrTrigger.triggerId and myTMtrExecConfig.tMtrConfig.tMtrConfigId.salesHierId = myTMtrConfig.tMtrConfigId.salesHierId " +
				" and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = myTMtrConfig.tMtrConfigId.mtrId and myTMtrConfig.tMtrConfigId.mtrId = myTMtr.mtrId and myTMtrExpr.tMtr.mtrId = myTMtr.mtrId" +
				" and myTMtrTrigger.activeFlag = myTMtrExecConfig.activeFlag and myTMtrExecConfig.activeFlag = myTMtrConfig.activeFlag and myTMtrConfig.activeFlag = myTMtr.activeFlag and msg.tMtrValMsgId.mtrId=myTMtr.mtrId" +
				" and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 " +
				" and myTMtrTrigger.tenantId = myTMtrExecConfig.tenantId and myTMtrExecConfig.tenantId = myTMtrConfig.tenantId and myTMtrConfig.tenantId = myTMtr.tenantId and myTMtr.tenantId = myTMtrExpr.tenantId" +
				" and myTMtrExecConfig.activeFlag= 'Y' and myTMtrExecConfig.tMtrConfig.tMtrConfigId.salesHierId = ?4 and myTMtrTrigger.triggerId IN ?5 and myTMtr.tenantId = ?6" +
				" GROUP BY myTMtrTrigger.featureId,myTMtrTrigger.tFeatureType.typeId,myTMtrExecConfig.tMtrTrigger.triggerId,myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId,myTMtrExpr.exprId " +
				" ORDER BY myTMtrExecConfig.tMtrTrigger.triggerId,myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId,myTMtrExpr.exprId "),
		@NamedQuery(name = "GetMetricExecConfig", query = "select myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId, myTMtrExecConfig.configId,myTMtrConfig.childRollupFlag, myTMtrExpr.mtrExpr, msg.valMsg, myTMtr.dataDotFlag, myTMtr.prec " +
				" from TMtr myTMtr, TMtrExpr myTMtrExpr , TMtrConfig myTMtrConfig , TMtrExecConfig myTMtrExecConfig , TMtrTrigger myTMtrTrigger  , TMtrValMsg msg" +
				" where myTMtrTrigger.triggerId = myTMtrExecConfig.tMtrTrigger.triggerId and myTMtrExecConfig.tMtrConfig.tMtrConfigId.salesHierId = myTMtrConfig.tMtrConfigId.salesHierId " +
				" and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = myTMtrConfig.tMtrConfigId.mtrId and myTMtrConfig.tMtrConfigId.mtrId = myTMtr.mtrId and myTMtrExpr.tMtr.mtrId = myTMtr.mtrId" +
				" and myTMtrTrigger.activeFlag = myTMtrExecConfig.activeFlag and myTMtrExecConfig.activeFlag = myTMtrConfig.activeFlag and myTMtrConfig.activeFlag = myTMtr.activeFlag and msg.tMtrValMsgId.mtrId=myTMtr.mtrId" +
				" and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 " +
				" and myTMtrTrigger.tenantId = myTMtrExecConfig.tenantId and myTMtrExecConfig.tenantId = myTMtrConfig.tenantId and myTMtrConfig.tenantId = myTMtr.tenantId and myTMtr.tenantId = myTMtrExpr.tenantId" +
				" and myTMtrExecConfig.activeFlag= 'Y' and myTMtrExecConfig.tMtrConfig.tMtrConfigId.salesHierId = ?4 and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId IN ?5 and myTMtrTrigger.triggerId IN ?6 and myTMtr.tenantId = ?7 " +
				" GROUP BY myTMtrTrigger.featureId,myTMtrTrigger.tFeatureType.typeId,myTMtrExecConfig.tMtrTrigger.triggerId,myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId,myTMtrExpr.exprId " +
				" ORDER BY myTMtrExecConfig.tMtrTrigger.triggerId,myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId,myTMtrExpr.exprId "),
		@NamedQuery(name = "FindAllTMtrExecConfigByALBUST", query = "select myTMtr.mtrId, myTMtr.mtrName, myTMtr.minValue, myTMtr.maxValue, myTMtr.uomId, " +
				" myTMtr.enforceFlag, myTMtrExecConfig.tMtrTrigger.triggerId, myTMtrConfig.tMtrConfigId.salesHierId " +
				" from TMtrExecConfig myTMtrExecConfig " +
				" inner join myTMtrExecConfig.tMtrConfig myTMtrConfig " +
				" inner join myTMtrConfig.tMtr myTMtr " +
				" where myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
				" and myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTMtrExecConfig.tenantId = ?4 " +
				" and myTMtrExecConfig.activeFlag = 'Y' and myTMtrConfig.tAlgmntSalesHier.salesHierId = myTMtrExecConfig.tMtrConfig.tAlgmntSalesHier.salesHierId " +
				" and myTMtrExecConfig.tMtrConfig.tMtrConfigId.mtrId = myTMtrConfig.tMtrConfigId.mtrId " +
				" and myTMtrConfig.tMtrConfigId.mtrId = myTMtr.mtrId and myTMtrExecConfig.tenantId = myTMtrConfig.tenantId and myTMtrConfig.tenantId = myTMtr.tenantId"),
		})
@Table(name = "t_mtr_exec_config", uniqueConstraints = @UniqueConstraint(columnNames = { "config_id" }))
public class TMtrExecConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "config_id", nullable = false, length = 255)
	private Integer configId;

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
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "mtr_id", referencedColumnName = "mtr_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMtrConfig tMtrConfig;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trigger_id", referencedColumnName = "trigger_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMtrTrigger tMtrTrigger;

	/**
	 *
	 * @generated
	 */
	public TMtrExecConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfigId(final Integer configId) {
		this.configId = configId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getConfigId() {
		return this.configId;
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
	public TMtrConfig getTMtrConfig() {
		return this.tMtrConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrConfig(final TMtrConfig tMtrConfig) {
		this.tMtrConfig = tMtrConfig;

	}

	/**
	 * 
	 * @generated
	 */
	public TMtrTrigger getTMtrTrigger() {
		return this.tMtrTrigger;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrTrigger(final TMtrTrigger tMtrTrigger) {
		this.tMtrTrigger = tMtrTrigger;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrExecConfig that) {
		setConfigId(that.getConfigId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
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
		result = prime * result + ((configId == null) ? 0 : configId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("configId=[").append(configId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
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
		final TMtrExecConfig other = (TMtrExecConfig) obj;
		if (configId == null) {
			if (other.configId != null) {
				return false;
			}
		} else if (!configId.equals(other.configId)) {
			return false;
		}
		return true;
	}
}
