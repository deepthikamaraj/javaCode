package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRuleType 
 * The corresponding mapping table is t_rule_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTRuleTypes", query = "select myTRuleType from TRuleType myTRuleType"),
		@NamedQuery(name = "CountTRuleTypes", query = "Select Count(c) from TRuleType c") })
@Table(name = "t_rule_type", uniqueConstraints = @UniqueConstraint(columnNames = { "rule_type_id" }))
public class TRuleType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRuleTypeId tRuleTypeId;
	
	

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "rule_type_desc", nullable = false, length = 20)
	private String ruleTypeDesc;

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
	
/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRuleType")
	private Set<TCvgRule> tCvgRule;
	
	
	public Set<TCvgRule> getTCvgRules() {
		return this.tCvgRule;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCvgRule(final Set<TCvgRule> tCvgRule) {
		this.tCvgRule = tCvgRule;
	}*/

	
	public TRuleType() {
	}

	

	/**
	 * 
	 * @generated
	 */

	public void setRuleTypeDesc(final String ruleTypeDesc) {
		this.ruleTypeDesc = ruleTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRuleTypeDesc() {
		return this.ruleTypeDesc;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRuleType that) {
		settRuleTypeId(that.gettRuleTypeId());
		setRuleTypeDesc(that.getRuleTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeFlag == null) ? 0 : activeFlag.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((ruleTypeDesc == null) ? 0 : ruleTypeDesc.hashCode());
		result = prime * result
				+ ((tRuleTypeId == null) ? 0 : tRuleTypeId.hashCode());
		result = prime * result
				+ ((updateDt == null) ? 0 : updateDt.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TRuleType [tRuleTypeId=" + tRuleTypeId + ", ruleTypeDesc="
				+ ruleTypeDesc + ", activeFlag=" + activeFlag + ", createdBy="
				+ createdBy + ", createDt=" + createDt + ", updatedBy="
				+ updatedBy + ", updateDt=" + updateDt + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TRuleType other = (TRuleType) obj;
		if (activeFlag == null) {
			if (other.activeFlag != null)
				return false;
		} else if (!activeFlag.equals(other.activeFlag))
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
		if (ruleTypeDesc == null) {
			if (other.ruleTypeDesc != null)
				return false;
		} else if (!ruleTypeDesc.equals(other.ruleTypeDesc))
			return false;
		if (tRuleTypeId == null) {
			if (other.tRuleTypeId != null)
				return false;
		} else if (!tRuleTypeId.equals(other.tRuleTypeId))
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



	/**
	 * @return the tRuleTypeId
	 */
	public TRuleTypeId gettRuleTypeId() {
		return tRuleTypeId;
	}



	/**
	 * @param tRuleTypeId the tRuleTypeId to set
	 */
	public void settRuleTypeId(TRuleTypeId tRuleTypeId) {
		this.tRuleTypeId = tRuleTypeId;
	}

	
}
