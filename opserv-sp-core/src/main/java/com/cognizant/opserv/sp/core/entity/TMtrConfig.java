package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TMtrConfig 
 * The corresponding mapping table is t_mtr_config 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrConfigs", query = "select myTMtrConfig from TMtrConfig myTMtrConfig"),
		@NamedQuery(name = "CountTMtrConfigs", query = "Select Count(c) from TMtrConfig c"),
		@NamedQuery(name = "FindTMtrConfigByTAlgmntSalesHier", query = "select myTMtrConfig from TMtrConfig myTMtrConfig where myTMtrConfig.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "CountTMtrConfigsByTAlgmntSalesHier", query = "select Count(*) from TMtrConfig myTMtrConfig where myTMtrConfig.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "FindTMtrConfigByTMtr", query = "select myTMtrConfig from TMtrConfig myTMtrConfig where myTMtrConfig.tMtr = ?1 "),
		@NamedQuery(name = "FindTMtrConfigBySalesHierId", query = "select myTMtrConfig from TMtrConfig myTMtrConfig where myTMtrConfig.tMtrConfigId.salesHierId = ?1 AND myTMtrConfig.tenantId = ?2 AND myTMtrConfig.activeFlag = 'Y' "),
		@NamedQuery(name = "CountTMtrConfigsBuMtrIdAndSalesHierId", query = "select Count(*) from TMtrConfig myTMtrConfig where myTMtrConfig.tMtr.mtrId = ?1 AND myTMtrConfig.tAlgmntSalesHier.salesHierId = ?2 "),
		@NamedQuery(name = "CountTMtrConfigsByTMtr", query = "select Count(*) from TMtrConfig myTMtrConfig where myTMtrConfig.tMtr = ?1 "),
		@NamedQuery(name = "getTMtrConfigsByTMetrics", query = "select myTMtrConfig from TMtrConfig myTMtrConfig,TMtr myTMtr where myTMtrConfig.tMtr.mtrCategoryId = ?1 and myTMtrConfig.tMtrConfigId.salesHierId = ?2 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?3 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId= ?4 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?5 AND myTMtrConfig.tenantId = ?6 AND myTMtrConfig.tMtr.mtrId = myTMtr.mtrId"),
		@NamedQuery(name = "getTMtrConfigsByTMetricsWithoutCat", query = "select myTMtrConfig from TMtrConfig myTMtrConfig,TMtr myTMtr where myTMtrConfig.tMtrConfigId.salesHierId = ?1 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?2 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId= ?3 and myTMtrConfig.tMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?4 AND myTMtrConfig.tenantId = ?5 AND myTMtrConfig.tMtr.mtrId = myTMtr.mtrId"),
		@NamedQuery(name = "FindTMtrConfigByMtrID", query = "select myTMtrConfig from TMtrConfig myTMtrConfig where myTMtrConfig.tMtrConfigId.mtrId = ?1 "),
		@NamedQuery(name = "GetCofiguredHierarchiesByMtrIDs", query = "select myTMtrConfig.tMtrConfigId.salesHierId,myTMtrConfig.tMtrConfigId.mtrId from TMtrConfig myTMtrConfig where myTMtrConfig.tMtrConfigId.mtrId IN ?1 and myTMtrConfig.tMtrConfigId.salesHierId NOT IN ?2 and myTMtrConfig.activeFlag = 'Y'"),
		@NamedQuery(name = "GetCofiguredHierarchiesTriggersByMtrIDs", query = "select confg.tMtrConfigId.mtrId,confg.tMtrConfigId.salesHierId,confg.childRollupFlag,orghier.orgSalesHierId,trig.triggerId from TMtrConfig confg inner join confg.tAlgmntSalesHier hier inner join hier.tOrgSalesHier orghier inner join confg.tMtrExecConfigss execonfg inner join execonfg.tMtrTrigger trig where confg.tMtrConfigId.mtrId in ?1 and confg.tenantId = ?2 and confg.activeFlag = 'Y' and trig.activeFlag = confg.activeFlag and execonfg.activeFlag = confg.activeFlag")
})
@Table(name = "t_mtr_config")
public class TMtrConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TMtrConfigId tMtrConfigId;

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
	@Column(name = "child_rollup_flag", nullable = true, length = 1)
	private Character childRollupFlag;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesHier tAlgmntSalesHier;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtrConfig")
	@Audited
	private Set<TMtrExecConfig> tMtrExecConfigss;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "mtr_id", referencedColumnName = "mtr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMtr tMtr;

	/**
	 *
	 * @generated
	 */
	public TMtrConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTMtrConfigId(final TMtrConfigId tMtrConfigId) {
		this.tMtrConfigId = tMtrConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtrConfigId getTMtrConfigId() {
		return this.tMtrConfigId;
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

	public void setChildRollupFlag(final Character childRollupFlag) {
		this.childRollupFlag = childRollupFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getChildRollupFlag() {
		return this.childRollupFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
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
	public TAlgmntSalesHier getTAlgmntSalesHier() {
		return this.tAlgmntSalesHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesHier(final TAlgmntSalesHier tAlgmntSalesHier) {
		this.tAlgmntSalesHier = tAlgmntSalesHier;
		if (this.tAlgmntSalesHier != null && this.tAlgmntSalesHier.getSalesHierId() != null) {

			this.tMtrConfigId.setSalesHierId(this.tAlgmntSalesHier.getSalesHierId());

		}

	}
	
	public Set<TMtrExecConfig> getTMtrExecConfigss() {
		return this.tMtrExecConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExecConfigss(final Set<TMtrExecConfig> tMtrExecConfigss) {
		this.tMtrExecConfigss = tMtrExecConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtr getTMtr() {
		return this.tMtr;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtr(final TMtr tMtr) {
		this.tMtr = tMtr;
		if (this.tMtr != null && this.tMtr.getMtrId() != null) {

			this.tMtrConfigId.setMtrId(this.tMtr.getMtrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrConfig that) {
		setTMtrConfigId(that.getTMtrConfigId());
		setActiveFlag(that.getActiveFlag());
		setChildRollupFlag(that.getChildRollupFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((tMtrConfigId == null) ? 0 : tMtrConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tMtrConfigId=[").append(tMtrConfigId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("childRollupFlag=[").append(childRollupFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

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
		final TMtrConfig other = (TMtrConfig) obj;
		if (tMtrConfigId == null) {
			if (other.tMtrConfigId != null) {
				return false;
			}
		} else if (!tMtrConfigId.equals(other.tMtrConfigId)) {
			return false;
		}
		return true;
	}
}
