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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAttrRule 
 * The corresponding mapping table is t_attr_rule 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrRules", query = "select myTAttrRule from TAttrRule myTAttrRule"),
		@NamedQuery(name = "CountTAttrRules", query = "Select Count(c) from TAttrRule c"),
		@NamedQuery(name = "FindTAttrRuleByTAttrDef", query = "select myTAttrRule from TAttrRule myTAttrRule where myTAttrRule.tAttrDef = ?1 "),
		@NamedQuery(name = "CountTAttrRulesByTAttrDef", query = "select Count(myTAttrRule) from TAttrRule myTAttrRule where myTAttrRule.tAttrDef = ?1 ") })
@Table(name = "t_attr_rule", uniqueConstraints = @UniqueConstraint(columnNames = { "rule_id" }))
public class TAttrRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rule_id", nullable = false, length = 11)
	private Integer ruleId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "min_value", nullable = true, length = 50)
	private String minValue;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "max_value", nullable = true, length = 50)
	private String maxValue;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "rule_expr", nullable = true, length = 300)
	private String ruleExpr;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "val_type_id", nullable = true, length = 255)
	private Integer valTypeId;

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
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAttrDef tAttrDef;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrRule")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TValMsg> tValMsgss;
	
	
	/**
	 *
	 * @generated
	 */
	public TAttrRule() {
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

	public void setMinValue(final String minValue) {
		this.minValue = minValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMinValue() {
		return this.minValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMaxValue(final String maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMaxValue() {
		return this.maxValue;
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

	public void setValTypeId(final Integer valTypeId) {
		this.valTypeId = valTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getValTypeId() {
		return this.valTypeId;
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
	public TAttrDef getTAttrDef() {
		return this.tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrDef(final TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TValMsg> getTValMsgss() {
		return this.tValMsgss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTValMsgss(final Set<TValMsg> tValMsgss) {
		this.tValMsgss = tValMsgss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttrRule that) {
		setRuleId(that.getRuleId());
		setMinValue(that.getMinValue());
		setMaxValue(that.getMaxValue());
		setRuleExpr(that.getRuleExpr());
		setValTypeId(that.getValTypeId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("ruleId=[").append(ruleId).append("] ");
		buffer.append("minValue=[").append(minValue).append("] ");
		buffer.append("maxValue=[").append(maxValue).append("] ");
		buffer.append("ruleExpr=[").append(ruleExpr).append("] ");
	//	buffer.append("tValMsgss=[").append(tValMsgss).append("] ");
		buffer.append("valTypeId=[").append(valTypeId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tAttrDef == null) ? 0 : tAttrDef.hashCode());
		result = prime * result
				+ ((valTypeId == null) ? 0 : valTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TAttrRule other = (TAttrRule) obj;
		if (tAttrDef == null) {
			if (other.tAttrDef != null)
				return false;
		} else if (!tAttrDef.equals(other.tAttrDef))
			return false;
		if (valTypeId == null) {
			if (other.valTypeId != null)
				return false;
		} else if (!valTypeId.equals(other.valTypeId))
			return false;
		return true;
	}

}
