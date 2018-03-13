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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TCvgRuleSched 
 * The corresponding mapping table is t_cvg_rule_sched 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRuleScheds", query = "select myTCvgRuleSched from TCvgRuleSched myTCvgRuleSched"),
		@NamedQuery(name = "CountTCvgRuleScheds", query = "Select Count(c) from TCvgRuleSched c"),
		@NamedQuery(name = "FindTCvgRuleSchedByTCvgRuleOrder", query = "select myTCvgRuleSched from TCvgRuleSched myTCvgRuleSched where myTCvgRuleSched.tCvgRuleOrder = ?1 "),
		
		@NamedQuery(name = "FindTCvgRuleSchedByTxnId", query = "select myTCvgRuleSched from TCvgRuleSched myTCvgRuleSched where myTCvgRuleSched.txnId = ?1 AND  myTCvgRuleSched.tenantId=?2 AND myTCvgRuleSched.activeFlag='Y'"),
		@NamedQuery(name = "FindTCvgRuleSchedByTenantId", query = "select myTCvgRuleSched from TCvgRuleSched myTCvgRuleSched where myTCvgRuleSched.tenantId=?2 AND myTCvgRuleSched.activeFlag='Y'"),
		@NamedQuery(name = "CountTCvgRuleSchedsByTCvgRuleOrder", query = "select Count(myTCvgRuleSched) from TCvgRuleSched myTCvgRuleSched where myTCvgRuleSched.tCvgRuleOrder = ?1 "),
		@NamedQuery(name = "UpdateTCvgSchedBySalesPosId", query = "update TCvgRuleSched myTCvgRuleSched set myTCvgRuleSched.activeFlag = 'N',myTCvgRuleSched.updateDt=?1,myTCvgRuleSched.updatedBy =?2 where myTCvgRuleSched.tenantId = ?3 AND myTCvgRuleSched.tSalesPos.salesPosId =?4"),

        @NamedQuery(name = "getOrderIdsBySalesPosId", query = "select obj.tCvgRuleOrder.orderId from TCvgRuleSched obj where obj.tSalesPos.salesPosId =?1 and obj.tenantId=?2 AND obj.activeFlag='Y'"),

        @NamedQuery(name="FindTCvgTxnIdCount", query="Select count(myTCvgRuleSched.txnId) from  TCvgRuleSched myTCvgRuleSched, TCvgRuleOrder myTCvgRuleOrder where myTCvgRuleSched.tCvgRuleOrder.orderId=myTCvgRuleOrder.orderId AND myTCvgRuleOrder.tenantId=?1 AND myTCvgRuleOrder.orderId=?2 AND myTCvgRuleOrder.activeFlag=?3 AND myTCvgRuleSched.activeFlag=?4" ),
        @NamedQuery(name = "UpdateTCvgSchedByOderId", query = "update TCvgRuleSched myTCvgRuleSched set myTCvgRuleSched.activeFlag = ?1,myTCvgRuleSched.updateDt=?2,myTCvgRuleSched.updatedBy =?3 where myTCvgRuleSched.tenantId = ?4 AND myTCvgRuleSched.tCvgRuleOrder.orderId =?5 AND myTCvgRuleSched.txnId IN ?6"),
})  
@Table(name = "t_cvg_rule_sched", uniqueConstraints = @UniqueConstraint(columnNames = { "txn_id" }))
public class TCvgRuleSched implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "txn_id", nullable = false, length = 255)
	private Integer txnId;

	@ManyToOne
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TSalesPos tSalesPos;

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

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCvgRuleOrder tCvgRuleOrder;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCvgRuleSched")
	@NotAudited
	private Set<TCvgRuleExec> tCvgRuleExecss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCvgRuleSched")
	@NotAudited
	private Set<TCvgRuleQry> tCvgRuleQriess;
	/**
	 *
	 * @generated
	 */
	public TCvgRuleSched() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTxnId(final Integer txnId) {
		this.txnId = txnId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTxnId() {
		return this.txnId;
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

	

	public Character getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Character activeFlag) {
		this.activeFlag = activeFlag;
	}

	public TSalesPos gettSalesPos() {
		return tSalesPos;
	}

	public void settSalesPos(TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
	}

	/**
	 * 
	 * @generated
	 */
	public TCvgRuleOrder getTCvgRuleOrder() {
		return this.tCvgRuleOrder;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleOrder(final TCvgRuleOrder tCvgRuleOrder) {
		this.tCvgRuleOrder = tCvgRuleOrder;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCvgRuleExec> getTCvgRuleExecss() {
		return this.tCvgRuleExecss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleExecss(final Set<TCvgRuleExec> tCvgRuleExecss) {
		this.tCvgRuleExecss = tCvgRuleExecss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRuleSched that) {
		setTxnId(that.getTxnId());		
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
		result = prime * result + ((txnId == null) ? 0 : txnId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("txnId=[").append(txnId).append("] ");		
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
		final TCvgRuleSched other = (TCvgRuleSched) obj;
		if (txnId == null) {
			if (other.txnId != null) {
				return false;
			}
		} else if (!txnId.equals(other.txnId)) {
			return false;
		}
		return true;
	}
}
