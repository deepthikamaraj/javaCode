package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/** 
 * JPA class representing TCvgRuleOpt 
 * The corresponding mapping table is t_cvg_rule_opt 
 */

/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRuleOpts", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt"),
		@NamedQuery(name = "FindAllTCvgRuleOptsById", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOptId = ?1 and myTCvgRuleOpt.tenantId = ?2"),
		@NamedQuery(name = "CountTCvgRuleOpts", query = "Select Count(c) from TCvgRuleOpt c"),
		
		@NamedQuery(name = "FindTCvgRuleOptByTCvgRuleOrder", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOrder = ?1"
		//, hints = {
				//@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				//@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		//}
				),
		
		/*@NamedQuery(name = "FindTCvgRuleOptByTCvgRuleOrder", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOrder = ?1 "),*/
		
		@NamedQuery(name = "FindTCvgRuleOptByOrderId", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOptId.orderId = ?1"
		//, hints = {
			//	@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				//@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		//}
				),
		/*@NamedQuery(name = "FindTCvgRuleOptByOrderId", query = "select myTCvgRuleOpt from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOptId.orderId = ?1 "),*/
		@NamedQuery(name = "CountTCvgRuleOptsByTCvgRuleOrder", query = "select Count(*) from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOrder = ?1 "),
		@NamedQuery(name = "getListOfRuleIdsByOrderId", query = "select Obj.tCvgRule.ruleId,Obj.tCvgRule.ruleName from TCvgRuleOpt Obj where obj.tCvgRuleOrder.orderId = ?1 and obj.tenantId =?2 order by updateDt"),
 @NamedQuery(name = "countOfRuleIdbBasedOnOrderId", query = "select count(myTCvgRuleOpt.tCvgRule.ruleId) from TCvgRuleOpt myTCvgRuleOpt where myTCvgRuleOpt.tCvgRuleOrder.orderId=?1 AND myTCvgRuleOpt.tenantId=?2")
})
@Table(name = "t_cvg_rule_opt")
public class TCvgRuleOpt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCvgRuleOptId tCvgRuleOptId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "order_seq", nullable = true, length = 255)
	private Integer orderSeq;

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
	//@Length(max = 1000)
	@Column(name = "rule_expr", nullable = true, length = 10000)
	private String ruleExpr;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TCvgRuleOrder tCvgRuleOrder;

	
	@ManyToOne
	@JoinColumn(name = "rule_id", referencedColumnName = "rule_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TCvgRule tCvgRule;
	
	/**
	 *
	 * @generated
	 */
	public TCvgRuleOpt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCvgRuleOptId(final TCvgRuleOptId tCvgRuleOptId) {
		this.tCvgRuleOptId = tCvgRuleOptId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCvgRuleOptId getTCvgRuleOptId() {
		return this.tCvgRuleOptId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderSeq(final Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrderSeq() {
		return this.orderSeq;
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
	public TCvgRule getTCvgRule() {
		return this.tCvgRule;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRule(final TCvgRule tCvgRule) {
		this.tCvgRule = tCvgRule;
		if (this.tCvgRule != null && this.tCvgRule.getRuleId() != null) {

			this.tCvgRuleOptId.setOrderId(this.tCvgRule.getRuleId());

		}

	}

	public TCvgRuleOrder getTCvgRuleOrder() {
		return this.tCvgRuleOrder;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCvgRuleOrder(final TCvgRuleOrder tCvgRuleOrder) {
		this.tCvgRuleOrder = tCvgRuleOrder;
		if (this.tCvgRuleOrder != null && this.tCvgRuleOrder.getOrderId() != null) {

			this.tCvgRuleOptId.setOrderId(this.tCvgRuleOrder.getOrderId());

		}

	}

	
	
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRuleOpt that) {
		setTCvgRuleOptId(that.getTCvgRuleOptId());
		setOrderSeq(that.getOrderSeq());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setRuleExpr(that.getRuleExpr());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCvgRuleOptId == null) ? 0 : tCvgRuleOptId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCvgRuleOptId=[").append(tCvgRuleOptId).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("ruleExpr=[").append(ruleExpr).append("] ");

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
		final TCvgRuleOpt other = (TCvgRuleOpt) obj;
		if (tCvgRuleOptId == null) {
			if (other.tCvgRuleOptId != null) {
				return false;
			}
		} else if (!tCvgRuleOptId.equals(other.tCvgRuleOptId)) {
			return false;
		}
		return true;
	}
}
