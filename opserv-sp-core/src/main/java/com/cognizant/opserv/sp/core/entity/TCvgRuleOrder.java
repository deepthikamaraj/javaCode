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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCvgRuleOrder 
 * The corresponding mapping table is t_cvg_rule_order 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRuleOrders", query = "select myTCvgRuleOrder from TCvgRuleOrder myTCvgRuleOrder"),
		@NamedQuery(name = "FindAllTCvgRuleOrdersById", query = "select myTCvgRuleOrder from TCvgRuleOrder myTCvgRuleOrder where myTCvgRuleOrder.orderId = ?1 and myTCvgRuleOrder.tenantId = ?2"),
		@NamedQuery(name = "FindAllOrderByJoinStmt", query = "select myTCvgRule from TCvgRule myTCvgRule, TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOrder.orderId = ?1 AND myTCvgRuleOpt.tenantId = ?2  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId"),
		@NamedQuery(name="FindStatusOfOrderByJoinStmt", query = " select myTExecutionStatus.statusDesc,myTCvgRule.ruleId FROM TExecutionStatus myTExecutionStatus, TCvgRule myTCvgRule,TCvgRuleOpt myTCvgRuleOpt WHERE myTCvgRule.tenantId=?1 AND myTExecutionStatus.executionStatusId IN (SELECT myTCvgRuleExec.tExecutionStatus.executionStatusId FROM TCvgRuleExec myTCvgRuleExec, TCvgRuleSched myTCvgRuleSched WHERE myTCvgRuleExec.tCvgRuleSched.txnId = myTCvgRuleSched.txnId AND myTCvgRuleSched.tCvgRuleOrder.orderId = ?2)  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId AND  myTCvgRuleOpt.tCvgRuleOrder.orderId = ?3"),
		
		/*@NamedQuery(name="FindTCvgRuleExecByDiffRulenames", query = "   SELECT myTCvgRule.ruleName, myTCvgRule.ruleDesc, myTCvgRuleOrder.orderId FROM TCvgRuleOrder myTCvgRuleOrder, TCvgRuleOpt myTCvgRuleOpt, TCvgRuleSched myTCvgRuleSched, TCvgRuleExec myTCvgRuleExec, TCvgRule myTCvgRule WHERE     myTCvgRuleOpt.tCvgRuleOrder.orderId = myTCvgRuleOrder.orderId AND myTCvgRuleExec.tCvgRuleSched.txnId = myTCvgRuleSched.txnId  AND myTCvgRuleSched.tCvgRuleOrder.orderId = myTCvgRuleOpt.tCvgRuleOrder.orderId  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId  AND myTCvgRuleExec.tExecutionStatus.executionStatusId = ?1  AND myTCvgRuleSched.activeFlag='Y'   AND myTCvgRuleExec.tenantId =?2 GROUP BY myTCvgRule.ruleId"),
		*/
		
			
		@NamedQuery(name="FindTCvgRuleExecByExenameAndUpdateDdate", query = "   SELECT   max(myTCvgRuleExec.updateDt), myTCvgRuleOrder.orderName FROM TCvgRuleOrder myTCvgRuleOrder,  TCvgRuleOpt myTCvgRuleOpt,  TCvgRuleSched myTCvgRuleSched, TCvgRuleExec myTCvgRuleExec,  TCvgRule myTCvgRule  WHERE     myTCvgRuleOrder.orderId = ?1  AND myTCvgRuleExec.tenantId =?2 AND    myTCvgRuleOpt.tCvgRuleOrder.orderId = myTCvgRuleOrder.orderId  AND myTCvgRuleExec.tCvgRuleSched.txnId = myTCvgRuleSched.txnId  AND myTCvgRuleSched.tCvgRuleOrder.orderId = myTCvgRuleOpt.tCvgRuleOrder.orderId  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId  AND myTCvgRuleSched.activeFlag='Y' GROUP BY myTCvgRuleOrder.orderName"),
		
		@NamedQuery(name="FindRuleIdsByJoinStmt", query = " select myTCvgRule.ruleId FROM TCvgRule myTCvgRule,TCvgRuleOpt myTCvgRuleOpt WHERE myTCvgRule.tenantId=?1 AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId AND  myTCvgRuleOpt.tCvgRuleOrder.orderId = ?2"),
		@NamedQuery(name = "FindOrdDtlsByOrderName", query = "select ruleOrder.orderName from TCvgRuleOrder ruleOrder where ruleOrder.orderName = ?1 AND ruleOrder.tenantId= ?2 "),
		@NamedQuery(name = "CountTCvgRuleOrders", query = "Select Count(c) from TCvgRuleOrder c") })
@Table(name = "t_cvg_rule_order", uniqueConstraints = @UniqueConstraint(columnNames = { "order_id" }))
public class TCvgRuleOrder implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", nullable = false, length = 255)
	private Integer orderId;

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
	@Length(max = 50)
	@Column(unique=true, name = "order_name", nullable = true, length = 50)
	private String orderName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "order_desc", nullable = true, length = 200)
	private String orderDesc;

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
	
	@NotNull
	@Column(name = "status_id", nullable = false, length = 1)
	private Integer statusId;
	
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCvgRuleOrder")
	@NotAudited
	private Set<TCvgRuleOpt> tCvgRuleOptss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCvgRuleOrder")
	@NotAudited
	private Set<TCvgRuleSched> tCvgRuleSchedss;

	/**
	 *
	 * @generated
	 */
	public TCvgRuleOrder() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderId(final Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrderId() {
		return this.orderId;
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

	public void setOrderName(final String orderName) {
		this.orderName = orderName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getOrderName() {
		return this.orderName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderDesc(final String orderDesc) {
		this.orderDesc = orderDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getOrderDesc() {
		return this.orderDesc;
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
	public Set<TCvgRuleOpt> getTCvgRuleOptss() {
		return this.tCvgRuleOptss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleOptss(final Set<TCvgRuleOpt> tCvgRuleOptss) {
		this.tCvgRuleOptss = tCvgRuleOptss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCvgRuleSched> getTCvgRuleSchedss() {
		return this.tCvgRuleSchedss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleSchedss(final Set<TCvgRuleSched> tCvgRuleSchedss) {
		this.tCvgRuleSchedss = tCvgRuleSchedss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRuleOrder that) {
		setOrderId(that.getOrderId());
		setActiveFlag(that.getActiveFlag());
		setOrderName(that.getOrderName());
		setOrderDesc(that.getOrderDesc());
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
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orderId=[").append(orderId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("orderName=[").append(orderName).append("] ");
		buffer.append("orderDesc=[").append(orderDesc).append("] ");
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
		final TCvgRuleOrder other = (TCvgRuleOrder) obj;
		if (orderId == null) {
			if (other.orderId != null) {
				return false;
			}
		} else if (!orderId.equals(other.orderId)) {
			return false;
		}
		return true;
	}
}
