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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TFlexiRule 
 * The corresponding mapping table is t_flexi_rule 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTFlexiRules", query = "select myTFlexiRule from TFlexiRule myTFlexiRule"),
		@NamedQuery(name = "CountTFlexiRules", query = "Select Count(c) from TFlexiRule c"),
		@NamedQuery(name = "FindTFlexiRuleByTAlgmntSalesTeam", query = "select myTFlexiRule from TFlexiRule myTFlexiRule where myTFlexiRule.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTFlexiRulesByTAlgmntSalesTeam", query = "select Count(myTFlexiRule) from TFlexiRule myTFlexiRule where myTFlexiRule.tAlgmntSalesTeam = ?1 "),
        @NamedQuery(name = "FindAllFlexiRules", query = "select myTFlexiRule.flexiRuleId,myTFlexiRule.ruleName,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTAlgmnt.algmntName,myTBussUnit.bussUnitName,myTSalesTeam.salesTeamName from TFlexiRule myTFlexiRule,TAlgmntSalesTeam myTAlgmntSalesTeam,TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TBussUnit myTBussUnit,TAlgmntStatus myTAlgmntStatus where myTFlexiRule.tenantId = myTAlgmntSalesTeam.tenantId AND myTFlexiRule.tenantId=myTAlgmnt.tenantId AND myTFlexiRule.tenantId=myTSalesTeam.tenantId AND myTFlexiRule.tenantId=myTBussUnit.tenantId AND myTFlexiRule.tenantId=myTAlgmntStatus.tenantId and myTFlexiRule.activeFlag = ?1 " +
        		"and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?2 " +
        		"AND myTAlgmnt.algmntName like ?3 " +
        		"AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?4 " +
        		"AND myTBussUnit.bussUnitName like ?5 " +
        		"AND myTFlexiRule.tenantId = ?6") })

@Table(name = "t_flexi_rule", uniqueConstraints = @UniqueConstraint(columnNames = { "flexi_rule_id" }))
public class TFlexiRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "flexi_rule_id", nullable = false, length = 255)
	private Integer flexiRuleId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "rule_name", nullable = false, length = 50)
	private String ruleName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "rule_desc", nullable = true, length = 200)
	private String ruleDesc;

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
	@NotEmpty
//	@Length(max = 255)
	@Column(name = "rule_expr", nullable = false, length = 255)
	private String ruleExpr;

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
	public TFlexiRule() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setFlexiRuleId(final Integer flexiRuleId) {
		this.flexiRuleId = flexiRuleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFlexiRuleId() {
		return this.flexiRuleId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRuleName(final String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRuleDesc(final String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRuleDesc() {
		return this.ruleDesc;
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

	public void setRuleExpr(final String ruleExpr) {
		this.ruleExpr = ruleExpr;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRuleExpr() {
		return this.ruleExpr;
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
	public void copy(final TFlexiRule that) {
		setFlexiRuleId(that.getFlexiRuleId());
		setRuleName(that.getRuleName());
		setRuleDesc(that.getRuleDesc());
		setActiveFlag(that.getActiveFlag());
		setRuleExpr(that.getRuleExpr());
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
		result = prime * result + ((flexiRuleId == null) ? 0 : flexiRuleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("flexiRuleId=[").append(flexiRuleId).append("] ");
		buffer.append("ruleName=[").append(ruleName).append("] ");
		buffer.append("ruleDesc=[").append(ruleDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("ruleExpr=[").append(ruleExpr).append("] ");
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
		final TFlexiRule other = (TFlexiRule) obj;
		if (flexiRuleId == null) {
			if (other.flexiRuleId != null) {
				return false;
			}
		} else if (!flexiRuleId.equals(other.flexiRuleId)) {
			return false;
		}
		return true;
	}
}
