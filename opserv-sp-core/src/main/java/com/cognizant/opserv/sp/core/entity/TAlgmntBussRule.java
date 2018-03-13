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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAlgmntBussRule 
 * The corresponding mapping table is t_algmnt_buss_rule 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntBussRules", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule"),
		@NamedQuery(name = "FindCustomerSharingOption", query = "select distinct myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule,TAlgmntSalesTeam myTAlgmntSalesTeam,TCustAlgmnt myTCustAlgmnt,TBussRuleConfig myTBussRuleConfig where " +
				" myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId " +
				" and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId " +
				" and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId " +
				" and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTCustAlgmnt.algmntId " +
				" and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTCustAlgmnt.bussUnitId " +
				" and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTCustAlgmnt.salesTeamId " +
				" and myTBussRuleConfig.tBussRuleConfigId.orgId = myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.orgId " +
				" and myTBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId " +
				" and myTCustAlgmnt.algmntId = ?1 " +
				" and myTCustAlgmnt.bussUnitId = ?2 "+
				" and myTCustAlgmnt.salesTeamId = ?3 " +
				" and myTCustAlgmnt.tenantId = ?4 " 
				),
		@NamedQuery(name = "FindBusinessRule", query = "select myTAlgmntBussRule.value from TAlgmntBussRule myTAlgmntBussRule  where myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmnt.algmntId=?1 AND myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId=?2 AND myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId=?3 AND myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId=?4 AND myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.orgId=?5 AND myTAlgmntBussRule.tenantId=?6 and myTAlgmntBussRule.activeFlag='Y' "),
		@NamedQuery(name="FindBusinessRuleValue", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule JOIN FETCH myTAlgmntBussRule.tBussRuleConfig myTBussRuleConfig where myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = myTBussRuleConfig.tBussRuleConfigId.bussRuleConfigId and myTBussRuleConfig.ruleDesc = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmnt.algmntId=?2 AND myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId=?3 AND myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId=?4 and myTAlgmntBussRule.tenantId=?5"),
		@NamedQuery(name = "CountTAlgmntBussRules", query = "Select Count(c) from TAlgmntBussRule c"),
		@NamedQuery(name = "FindTAlgmntBussRuleByTBussRuleConfig", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where myTAlgmntBussRule.tBussRuleConfig = ?1 "),
		@NamedQuery(name = "CountTAlgmntBussRulesByTBussRuleConfig", query = "select Count(myTAlgmntBussRule) from TAlgmntBussRule myTAlgmntBussRule where myTAlgmntBussRule.tBussRuleConfig = ?1 "),
		@NamedQuery(name = "FindTAlgmntBussRuleByTAlgmntSalesTeam", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where myTAlgmntBussRule.tAlgmntSalesTeam = ?1 "),
		//@NamedQuery(name = "FindTAlgmntBussRuleByTAlgmntSalesTeamRuleConfig", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4"),
		@NamedQuery(name = "FindTAlgmntBussRuleByTAlgmntSalesTeamRuleConfig",query ="select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule, TAlgmntSalesTeam mytAlgmntSalesTeam, TBussRuleConfig mytBussRuleConfig where myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = mytBussRuleConfig.tBussRuleConfigId.bussRuleConfigId and mytAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?1 and mytAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?2 and  mytAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId= ?3 and mytBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4"), 
		@NamedQuery(name = "CountTAlgmntBussRulesByTAlgmntSalesTeam", query = "select Count(myTAlgmntBussRule) from TAlgmntBussRule myTAlgmntBussRule where myTAlgmntBussRule.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindValuebyAlignInfoAndRuleName", query = "select myTAlgmntBussRule.value, myTAlgmntBussRule.effEndDt from TAlgmntBussRule myTAlgmntBussRule where" +
				" myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
				"and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTAlgmntBussRule.tBussRuleConfig.ruleName = ?4 and myTAlgmntBussRule.activeFlag = 'Y'" +
				" and myTAlgmntBussRule.tBussRuleConfig.activeFlag = 'Y'"),
				@NamedQuery(name = "FindAllTAlgmntBussRulesByTenantId", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where tenantId = ?1 and activeFlag = ?2"),
				@NamedQuery(name = "FindAllTAlgmntBussRulesByAlignId", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where tenantId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and activeFlag = ?5"),
	    @NamedQuery(name = "getRuleByALBUSTRuleID", query = " select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule, TAlgmntSalesTeam mytAlgmntSalesTeam, TBussRuleConfig mytBussRuleConfig where " +
	    		"myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and " +
	    		"myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and " +
	    		"myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = mytAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and " +
	    		"myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = mytBussRuleConfig.tBussRuleConfigId.bussRuleConfigId and " +
	    		" mytAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId =?1 and mytAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId= ?2 and " +
	    		"mytAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and mytBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4 and " +
	    		" myTAlgmntBussRule.tenantId = ?5"),
	    @NamedQuery(name = "FindValuebyAlignInfoAndRuleConfigId", query = "select myTAlgmntBussRule.value from TAlgmntBussRule myTAlgmntBussRule where" +
	    		" myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
	    		"and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and " +
	    		"myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4 and myTAlgmntBussRule.tenantId = ?5 " +
	    		"and myTAlgmntBussRule.activeFlag = 'Y' and myTAlgmntBussRule.tBussRuleConfig.activeFlag = 'Y'"),
		
		 @NamedQuery(name = "FindValuebyAlignInfoAndRuleConfigIdFrBricks", query = "select myTAlgmntBussRule.value from TAlgmntBussRule myTAlgmntBussRule where" +
		    		" myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
		    		"and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and " +
		    		"myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4 and myTAlgmntBussRule.tenantId = ?5 " +
		    		"and myTAlgmntBussRule.activeFlag = 'Y' and myTAlgmntBussRule.tBussRuleConfig.activeFlag = 'Y'"),
		@NamedQuery(name = "FethSecondaryAddressFlag", query = "select myTAlgmntBussRule.value from TAlgmntBussRule myTAlgmntBussRule where" +
	    		" myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
	    		"and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3"+
	    		" and myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId = ?4 and myTAlgmntBussRule.tenantId = ?5 " +
	    		"and myTAlgmntBussRule.activeFlag = 'Y'"),
	  @NamedQuery(name = "FindAllTAlgmntBussRulesByALBUST", query = "select myTAlgmntBussRule from TAlgmntBussRule myTAlgmntBussRule where tenantId = ?1 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and activeFlag = ?5")
})
@Table(name = "t_algmnt_buss_rule", uniqueConstraints = @UniqueConstraint(columnNames = { "rule_id" }))
public class TAlgmntBussRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rule_id", nullable = false, length = 255)
	private Integer ruleId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "value", nullable = true, length = 200)
	private String value;

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
	@Column(name = "eff_start_dt", nullable = false, length = 10)
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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "buss_rule_config_id", referencedColumnName = "buss_rule_config_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TBussRuleConfig tBussRuleConfig;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	/**
	 *
	 * @generated
	 */
	public TAlgmntBussRule() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRuleId(final Integer ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRuleId() {
		return this.ruleId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * 
	 * @generated
	 */
	public String getValue() {
		return this.value;
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
	public TBussRuleConfig getTBussRuleConfig() {
		return this.tBussRuleConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussRuleConfig(final TBussRuleConfig tBussRuleConfig) {
		this.tBussRuleConfig = tBussRuleConfig;

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

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntBussRule that) {
		setRuleId(that.getRuleId());
		setValue(that.getValue());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("ruleId=[").append(ruleId).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TAlgmntBussRule other = (TAlgmntBussRule) obj;
		if (ruleId == null) {
			if (other.ruleId != null) {
				return false;
			}
		} else if (!ruleId.equals(other.ruleId)) {
			return false;
		}
		return true;
	}
}
