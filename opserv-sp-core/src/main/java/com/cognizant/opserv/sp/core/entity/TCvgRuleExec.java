package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TCvgRuleExec 
 * The corresponding mapping table is t_cvg_rule_exec 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRuleExecs", query = "select myTCvgRuleExec from TCvgRuleExec myTCvgRuleExec"),
		@NamedQuery(name = "FindTCvgRuleExecDts", query = "select myTCvgRuleExec from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.tenantId=?1 and myTCvgRuleExec.tCvgRuleSched.txnId = ?2 AND myTCvgRuleExec.ruleId = ?3"),
		@NamedQuery(name = "CountTCvgRuleExecs", query = "Select Count(c) from TCvgRuleExec c"),
		@NamedQuery(name = "FindTCvgRuleExecByTExecutionStatus", query = "select myTCvgRuleExec from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.tExecutionStatus = ?1 "),
		@NamedQuery(name = "CountTCvgRuleExecsByTExecutionStatus", query = "select Count(myTCvgRuleExec) from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.tExecutionStatus = ?1 "),
		@NamedQuery(name = "FindTCvgRuleExecByTCvgRuleSched", query = "select myTCvgRuleExec from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.tCvgRuleSched = ?1 "),
		@NamedQuery(name = "FindTCvgRuleExecByRuleIdAndStatus", query = "select myTCvgRuleExec from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.ruleId = ?1 "),
		
		@NamedQuery(name = "CountTCvgRuleExecsByTCvgRuleSched", query = "select Count(myTCvgRuleExec) from TCvgRuleExec myTCvgRuleExec where myTCvgRuleExec.tCvgRuleSched = ?1 "),
		
		@NamedQuery(name = "FindTCvgRuleExecByOrdername", query = "SELECT max(mytcvgruleexe.updateDt) as update_dt, mytcvgruleorder.orderName,mytcvgruleexe.ruleId FROM TCvgRuleExec mytcvgruleexe, TCvgRuleSched mytcvgrulesched, TCvgRuleOrder mytcvgruleorder WHERE  mytcvgruleexe.tCvgRuleSched.txnId=mytcvgrulesched.txnId AND mytcvgruleexe.tCvgRuleSched.tCvgRuleOrder.orderId =mytcvgruleorder.orderId  AND mytcvgruleexe.tenantId = ?1 AND mytcvgruleexe.ruleId=?2 AND mytcvgruleexe.tExecutionStatus.executionStatusId =4 GROUP BY mytcvgruleexe.ruleId"),

		@NamedQuery(name = "FindTCvgRuleExecByRuleName", query = "  SELECT myTCvgRule, myTRuleType.ruleTypeDesc, myTBussFunction.bussFunctionName FROM TCvgRule myTCvgRule,TBussFunction myTBussFunction,TRuleType myTRuleType WHERE myTBussFunction.bussFunctionId=myTCvgRule.tBussFunction.bussFunctionId AND myTRuleType.tRuleTypeId.ruleTypeId=myTcvgRule.ruleTypeId AND myTCvgRule.tenantId = ?1  AND myTCvgRule.ruleName = ?2 GROUP BY  myTCvgRule.ruleId"),
		
		@NamedQuery(name="FindTCvgRulesByOrderId", query = "   SELECT DISTINCT(myTCvgRule), myTRuleType.ruleTypeDesc, myTBussFunction.bussFunctionName  FROM TCvgRuleOrder myTCvgRuleOrder, TCvgRuleOpt myTCvgRuleOpt, TCvgRuleSched myTCvgRuleSched,TCvgRule myTCvgRule,TBussFunction myTBussFunction,TRuleType myTRuleType WHERE myTBussFunction.bussFunctionId=myTCvgRule.tBussFunction.bussFunctionId AND myTRuleType.tRuleTypeId.ruleTypeId=myTcvgRule.ruleTypeId AND myTCvgRuleOpt.tCvgRuleOrder.orderId = myTCvgRuleOrder.orderId AND myTCvgRuleSched.tCvgRuleOrder.orderId = myTCvgRuleOpt.tCvgRuleOrder.orderId  AND myTCvgRule.ruleId =  myTCvgRuleOpt.tCvgRule.ruleId AND myTCvgRuleOrder.orderId = ?1 AND myTCvgRule.activeFlag='Y' AND myTCvgRuleOrder.tenantId =?2"),

       @NamedQuery(name="findTCvgRuleByExecStatusIdBasedOnOrderId", query = "select mytcvgruleexe.tExecutionStatus.executionStatusId from TCvgRuleExec mytcvgruleexe where mytcvgruleexe.tCvgRuleSched.txnId In(select mytcvgrulesched.txnId from TCvgRuleSched mytcvgrulesched,TCvgRuleOrder mytcvgruleorder where mytcvgruleexe.tCvgRuleSched.tCvgRuleOrder.orderId =mytcvgruleorder.orderId AND myTCvgRuleOrder.tenantId =?1 AND myTCvgRuleOrder.orderId = ?2 AND myTCvgRuleOrder.activeFlag=?3 AND mytcvgrulesched.activeFlag=?4)")
		
	
		
   

})

        
@Table(name = "t_cvg_rule_exec", uniqueConstraints = @UniqueConstraint(columnNames = { "cvg_rule_exec_id" }))
public class TCvgRuleExec implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cvg_rule_exec_id", nullable = false, length = 255)
	private Integer cvgRuleExecId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "exec_dt_tm", nullable = true, length = 19)
	private Date execDtTm;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "failure_reason", nullable = true, length = 20)
	private String failureReason;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "rule_id", nullable = false, length = 255)
	private Integer ruleId;

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
	@JoinColumn(name = "exec_status_id", referencedColumnName = "execution_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TExecutionStatus tExecutionStatus;

	@ManyToOne
	@JoinColumn(name = "txn_id", referencedColumnName = "txn_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TCvgRuleSched tCvgRuleSched;
	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "qry_stmt", nullable = true, length = 3000)
	private String qryStmt;

	/**
	 *
	 * @generated
	 */
	public TCvgRuleExec() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCvgRuleExecId(final Integer cvgRuleExecId) {
		this.cvgRuleExecId = cvgRuleExecId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCvgRuleExecId() {
		return this.cvgRuleExecId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecDtTm(final Date execDtTm) {
		if (execDtTm != null) {
			this.execDtTm = (Date) execDtTm.clone();
		} else {
			this.execDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecDtTm() {
		if (this.execDtTm != null) {
			return (Date) this.execDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setFailureReason(final String failureReason) {
		this.failureReason = failureReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFailureReason() {
		return this.failureReason;
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
	public TExecutionStatus getTExecutionStatus() {
		return this.tExecutionStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTExecutionStatus(final TExecutionStatus tExecutionStatus) {
		this.tExecutionStatus = tExecutionStatus;

	}

	/**
	 * 
	 * @generated
	 */
	public TCvgRuleSched getTCvgRuleSched() {
		return this.tCvgRuleSched;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleSched(final TCvgRuleSched tCvgRuleSched) {
		this.tCvgRuleSched = tCvgRuleSched;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRuleExec that) {
		setCvgRuleExecId(that.getCvgRuleExecId());
		setExecDtTm(that.getExecDtTm());
		setFailureReason(that.getFailureReason());
		setRuleId(that.getRuleId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setQryStmt(that.getQryStmt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((cvgRuleExecId == null) ? 0 : cvgRuleExecId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {
		return "TCvgRuleExec [cvgRuleExecId=" + cvgRuleExecId + ", execDtTm="
				+ execDtTm + ", failureReason=" + failureReason + ", ruleId="
				+ ruleId + ", createdBy=" + createdBy + ", createDt="
				+ createDt + ", updatedBy=" + updatedBy + ", updateDt="
				+ updateDt + ", tenantId=" + tenantId + ", tExecutionStatus="
				+ tExecutionStatus + ", tCvgRuleSched=" + tCvgRuleSched
				+ ", qryStmt=" + qryStmt + "]";
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
		final TCvgRuleExec other = (TCvgRuleExec) obj;
		if (cvgRuleExecId == null) {
			if (other.cvgRuleExecId != null) {
				return false;
			}
		} else if (!cvgRuleExecId.equals(other.cvgRuleExecId)) {
			return false;
		}
		return true;
	}
		/**
		 * @return the qryStmt
		 */
		public String getQryStmt() {
			return qryStmt;
		}
	
		/**
		 * @param qryStmt the qryStmt to set
		 */
		public void setQryStmt(String qryStmt) {
			this.qryStmt = qryStmt;
		}
}
