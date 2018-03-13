package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussRuleConfig 
 * The corresponding mapping table is t_buss_rule_config 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussRuleConfigs", query = "select myTBussRuleConfig from TBussRuleConfig myTBussRuleConfig"),
		@NamedQuery(name = "CountTBussRuleConfigs", query = "Select Count(c) from TBussRuleConfig c"),
		@NamedQuery(name = "FindTBussRuleConfigByTOrg", query = "select myTBussRuleConfig from TBussRuleConfig myTBussRuleConfig where myTBussRuleConfig.tOrg = ?1 "),
		@NamedQuery(name = "FindTBussRuleConfigByRuleName", query = "select myTBussRuleConfig from TBussRuleConfig myTBussRuleConfig where myTBussRuleConfig.ruleName = ?1 "),
		@NamedQuery(name = "CountTBussRuleConfigsByTOrg", query = "select Count(*) from TBussRuleConfig myTBussRuleConfig where myTBussRuleConfig.tOrg = ?1 "),
		@NamedQuery(name = "FindAllTBussRuleConfigsByFlag", query = "select myTBussRuleConfig from TBussRuleConfig myTBussRuleConfig where activeFlag = ?1")
})

@Table(name = "t_buss_rule_config")
public class TBussRuleConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TBussRuleConfigId tBussRuleConfigId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "rule_desc", nullable = true, length = 75)
	private String ruleDesc;

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
	@NotEmpty
	@Length(max = 50)
	@Column(name = "rule_name", nullable = false, length = 50)
	private String ruleName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "allowed_value", nullable = true, length = 300)
	private String allowedValue;

	@ManyToOne
	@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TOrg tOrg;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussRuleConfig")
	private Set<TAlgmntBussRule> tAlgmntBussRuless;

	/**
	 *
	 * @generated
	 */
	public TBussRuleConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTBussRuleConfigId(final TBussRuleConfigId tBussRuleConfigId) {
		this.tBussRuleConfigId = tBussRuleConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public TBussRuleConfigId getTBussRuleConfigId() {
		return this.tBussRuleConfigId;
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

	public void setAllowedValue(final String allowedValue) {
		this.allowedValue = allowedValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAllowedValue() {
		return this.allowedValue;
	}

	/**
	 * 
	 * @generated
	 */
	public TOrg getTOrg() {
		return this.tOrg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrg(final TOrg tOrg) {
		this.tOrg = tOrg;
		if (this.tOrg != null && this.tOrg.getOrgId() != null) {

			this.tBussRuleConfigId.setOrgId(this.tOrg.getOrgId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntBussRule> getTAlgmntBussRuless() {
		return this.tAlgmntBussRuless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntBussRuless(final Set<TAlgmntBussRule> tAlgmntBussRuless) {
		this.tAlgmntBussRuless = tAlgmntBussRuless;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussRuleConfig that) {
		setTBussRuleConfigId(that.getTBussRuleConfigId());
		setRuleDesc(that.getRuleDesc());
		setActiveFlag(that.getActiveFlag());
		setRuleName(that.getRuleName());
		setAllowedValue(that.getAllowedValue());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tBussRuleConfigId == null) ? 0 : tBussRuleConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tBussRuleConfigId=[").append(tBussRuleConfigId).append("] ");
		buffer.append("ruleDesc=[").append(ruleDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("ruleName=[").append(ruleName).append("] ");
		buffer.append("allowedValue=[").append(allowedValue).append("] ");

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
		final TBussRuleConfig other = (TBussRuleConfig) obj;
		if (tBussRuleConfigId == null) {
			if (other.tBussRuleConfigId != null) {
				return false;
			}
		} else if (!tBussRuleConfigId.equals(other.tBussRuleConfigId)) {
			return false;
		}
		return true;
	}
}
