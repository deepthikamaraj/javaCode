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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * JPA class representing TCvgRule The corresponding mapping table is t_cvg_rule
 */

/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRules", query = "select myTCvgRule from TCvgRule myTCvgRule"),
		@NamedQuery(name = "FindAllTCvgRulesByRuleIdTentantId", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleId=?1 AND myTCvgRule.tenantId=?2"),
		@NamedQuery(name = "FindRuleByRuleName", query = "select myTCvgRule.ruleName from TCvgRule myTCvgRule where myTCvgRule.ruleName = ?1 AND myTCvgRule.tenantId= ?2 "),
		@NamedQuery(name = "CountTCvgRules", query = "Select Count(c) from TCvgRule c"),
		@NamedQuery(name = "FindTCvgRuleByTBussFunction", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.tBussFunction = ?1 "),
		@NamedQuery(name = "CountTCvgRulesByTBussFunction", query = "select Count(myTCvgRule) from TCvgRule myTCvgRule where myTCvgRule.tBussFunction = ?1 "),
		
		/*@NamedQuery(name = "FindTCvgRuleByTRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.tRuleType = ?1", hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),*/
		@NamedQuery(name = "FindTCvgRulesSortByCreDt", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.activeFlag='Y' AND myTCvgRule.tenantId = ?1 ORDER BY createDt DESC"),
		/*@NamedQuery(name = "FindTCvgRuleByTRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.tRuleType = ?1 "),*/
		@NamedQuery(name = "FindTCvgRulesByMultiParamsWithoutBussFunForView", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.ruleTypeId=?3 AND myTCvgRule.tenantId= ?4 AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsWithoutBussFunForViewForAllRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.tenantId= ?3 AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsForView", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.ruleTypeId=?3 AND myTCvgRule.tBussFunction.bussFunctionId = ?4 AND myTCvgRule.tenantId= ?5 AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsForViewForAllRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.tBussFunction.bussFunctionId = ?3 AND myTCvgRule.tenantId= ?4 AND myTCvgRule.activeFlag='Y'"),
		
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsWithoutBussFun", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.algmntId=?3 AND myTCvgRule.bussUnitId=?4 AND myTCvgRule.salesTeamId=?5 AND myTCvgRule.ruleTypeId=?6 AND myTCvgRule.tenantId= ?7  AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsWithoutBussFunForAllRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.algmntId=?3 AND myTCvgRule.bussUnitId=?4 AND myTCvgRule.salesTeamId=?5 AND myTCvgRule.tenantId= ?6  AND myTCvgRule.activeFlag='Y'"),
		
			
		@NamedQuery(name = "FindTCvgRulesByMultiParams", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.algmntId=?3 AND myTCvgRule.bussUnitId=?4 AND myTCvgRule.salesTeamId=?5 AND myTCvgRule.ruleTypeId=?6 AND myTCvgRule.tBussFunction.bussFunctionId = ?7 AND myTCvgRule.tenantId= ?8  AND myTCvgRule.activeFlag='Y'"),
	
		@NamedQuery(name = "FindTCvgRulesByMultiParamsForAllRuleType", query = "select myTCvgRule from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1 AND myTCvgRule.ruleDesc LIKE ?2 AND myTCvgRule.algmntId=?3 AND myTCvgRule.bussUnitId=?4 AND myTCvgRule.salesTeamId=?5 AND myTCvgRule.tBussFunction.bussFunctionId = ?6 AND myTCvgRule.tenantId= ?7  AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name = "FindTCvgRulesByMultiParamsWithoutAlBuSt", query = "select myTCvgRule.ruleName, myTCvgRule.ruleExpr from TCvgRule myTCvgRule where myTCvgRule.ruleName LIKE ?1   AND myTCvgRule.tenantId= ?2  AND myTCvgRule.activeFlag='Y'"),
		
		@NamedQuery(name="FindTCvgRuleExecByDiffRulenames", query = "   SELECT myTCvgRule, myTCvgRuleOrder.orderId,myTCvgRuleOrder.orderName FROM TCvgRuleOrder myTCvgRuleOrder, TCvgRuleOpt myTCvgRuleOpt, TCvgRuleSched myTCvgRuleSched,TCvgRule myTCvgRule WHERE myTCvgRuleOpt.tCvgRuleOrder.orderId = myTCvgRuleOrder.orderId AND myTCvgRuleSched.tCvgRuleOrder.orderId = myTCvgRuleOpt.tCvgRuleOrder.orderId  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId AND myTCvgRuleSched.activeFlag='Y' AND myTCvgRule.tenantId =?1 GROUP BY myTCvgRule.ruleId"),
		@NamedQuery(name="FindTCvgRuleExecWithExecDetails", query = "   SELECT myTCvgRule, myTCvgRuleOrder.orderId,myTCvgRuleOrder.orderName,myTCvgRuleOrder.updateDt,myTCvgRuleOrder.activeFlag FROM TCvgRuleOrder myTCvgRuleOrder, TCvgRuleOpt myTCvgRuleOpt, TCvgRuleSched myTCvgRuleSched,TCvgRule myTCvgRule WHERE myTCvgRuleOpt.tCvgRuleOrder.orderId = myTCvgRuleOrder.orderId AND myTCvgRuleSched.tCvgRuleOrder.orderId = myTCvgRuleOpt.tCvgRuleOrder.orderId  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId AND myTCvgRuleOrder.activeFlag=?2 AND myTCvgRuleOrder.orderName LIKE ?1 AND myTCvgRule.activeFlag='Y' AND myTCvgRuleOrder.tenantId =?3 GROUP BY myTCvgRuleOrder.orderId") })
		/*@NamedQuery(name = "CountTCvgRulesByTRuleType", query = "select Count(myTCvgRule) from TCvgRule myTCvgRule where myTCvgRule.tRuleType = ?1 ") })*/
@Table(name = "t_cvg_rule", uniqueConstraints = @UniqueConstraint(columnNames = { "rule_id" }))
public class TCvgRule implements Serializable {
	// default serial version id, required for serializable classes.
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
	@Column(name = "rule_desc", nullable = true, length = 200)
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
	@Length(max = 20)
	@Column(name = "rule_name", nullable = false, length = 20)
	private String ruleName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "rule_expr", nullable = true, length = 3000)
	private String ruleExpr;
	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "aff_rule_expr", nullable = true, length = 3000)
	private String affRuleExpr;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255, updatable = false)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
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
	
	@NotNull
	@Column(name = "aff_flag", nullable = false, length = 1)
	private Character affFlag;
	
	/*@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "aff_type_id", referencedColumnName = "aff_type_id", nullable = true, insertable = true, updatable = true)})
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCvgAffType tCvgAffType;*/
	
	
	@Column(name = "aff_type_id", nullable = true, length = 255)
	private Integer cvgAffTypeId;
	
	
	/*@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "aff_hier_level_id", referencedColumnName = "aff_hier_level_id", nullable = false, insertable = false, updatable = false) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCvgAffHierLevel tCvgHierLevel;*/
	
	
	
	@Column(name = "aff_hier_level_id", nullable = true, length = 255)
	private Integer affHierLevelId;
	

	@Transient
	private String statusDesc;
	
	@Transient
	private String orderName;
	
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * @return the algmntId
	 */
	public Long getAlgmntId() {
		return algmntId;
	}

	/**
	 * @param algmntId the algmntId to set
	 */
	public void setAlgmntId(Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * @return the bussUnitId
	 */
	public Long getBussUnitId() {
		return bussUnitId;
	}

	/**
	 * @param bussUnitId the bussUnitId to set
	 */
	public void setBussUnitId(Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * @return the salesTeamId
	 */
	public Long getSalesTeamId() {
		return salesTeamId;
	}

	/**
	 * @param salesTeamId the salesTeamId to set
	 */
	public void setSalesTeamId(Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;
	
	
	@ManyToOne
	@JoinColumn(name = "buss_function_id", referencedColumnName = "buss_function_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TBussFunction tBussFunction;

	@Column(name = "rule_type_id",nullable = false, insertable = true, updatable = true)
	private Integer ruleTypeId;

	/*@ManyToOne
	@JoinColumn(name = "rule_type_id", referencedColumnName = "rule_type_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRuleType tRuleType;
	
	public TRuleType gettRuleType() {
		return tRuleType;
	}

	public void settRuleType(TRuleType tRuleType) {
		this.tRuleType = tRuleType;
	}*/

	public Set<TCvgRuleOpt> gettCvgRuleOptss() {
		return tCvgRuleOptss;
	}

	public void settCvgRuleOptss(Set<TCvgRuleOpt> tCvgRuleOptss) {
		this.tCvgRuleOptss = tCvgRuleOptss;
	}

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCvgRule")
	@NotAudited
	private Set<TCvgRuleOpt> tCvgRuleOptss;

	/**
	 * 
	 * @generated
	 */
	public TCvgRule() {
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
	public TBussFunction getTBussFunction() {
		return this.tBussFunction;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussFunction(final TBussFunction tBussFunction) {
		this.tBussFunction = tBussFunction;

	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRuleTypeId() {
		return ruleTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setRuleTypeId(Integer ruleTypeId) {
		this.ruleTypeId = ruleTypeId;
	}
	
	
	public String getStatusDesc() {
		return statusDesc;
	}
	
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	/**
	 * 
	 * @generated
	 */
	/*public Set<TCvgRuleOpt> getTCvgRuleOptss() {
		return this.tCvgRuleOptss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCvgRuleOptss(final Set<TCvgRuleOpt> tCvgRuleOptss) {
		this.tCvgRuleOptss = tCvgRuleOptss;
	}*/

	

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRule that) {
		setRuleId(that.getRuleId());
		setRuleDesc(that.getRuleDesc());
		setActiveFlag(that.getActiveFlag());
		setRuleName(that.getRuleName());
		setRuleExpr(that.getRuleExpr());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setRuleTypeId(that.getRuleTypeId());
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
		buffer.append("ruleDesc=[").append(ruleDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("ruleName=[").append(ruleName).append("] ");
		buffer.append("ruleExpr=[").append(ruleExpr).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("ruleTypeId=[").append(ruleTypeId).append("] ");

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
		final TCvgRule other = (TCvgRule) obj;
		if (ruleId == null) {
			if (other.ruleId != null) {
				return false;
			}
		} else if (!ruleId.equals(other.ruleId)) {
			return false;
		}
		return true;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Character getAffFlag() {
		return affFlag;
	}

	public void setAffFlag(Character affFlag) {
		this.affFlag = affFlag;
	}

	
	
	/**
	 * @return the affRuleExpr
	 */
	public String getAffRuleExpr() {
		return affRuleExpr;
	}

	/**
	 * @param affRuleExpr the affRuleExpr to set
	 */
	public void setAffRuleExpr(String affRuleExpr) {
		this.affRuleExpr = affRuleExpr;
	}

	/**
	 * @return the cvgAffTypeId
	 */
	public Integer getCvgAffTypeId() {
		return cvgAffTypeId;
	}

	/**
	 * @param cvgAffTypeId the cvgAffTypeId to set
	 */
	public void setCvgAffTypeId(Integer cvgAffTypeId) {
		this.cvgAffTypeId = cvgAffTypeId;
	}

	/**
	 * @return the affHierLevelId
	 */
	public Integer getAffHierLevelId() {
		return affHierLevelId;
	}

	/**
	 * @param affHierLevelId the affHierLevelId to set
	 */
	public void setAffHierLevelId(Integer affHierLevelId) {
		this.affHierLevelId = affHierLevelId;
	}
	
	
}
