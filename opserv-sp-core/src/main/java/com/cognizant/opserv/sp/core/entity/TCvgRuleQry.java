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

/** 
 * JPA class representing TCvgRuleQry 
 * The corresponding mapping table is t_cvg_rule_qry 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgRuleQrys", query = "select myTCvgRuleQry from TCvgRuleQry myTCvgRuleQry"),
		@NamedQuery(name = "CountTCvgRuleQrys", query = "Select Count(c) from TCvgRuleQry c"),
		@NamedQuery(name = "FindTCvgRuleQryByTCvgRuleSched", query = "select myTCvgRuleQry from TCvgRuleQry myTCvgRuleQry where myTCvgRuleQry.tCvgRuleSched = ?1 "),
		@NamedQuery(name = "CountTCvgRuleQrysByTCvgRuleSched", query = "select Count(*) from TCvgRuleQry myTCvgRuleQry where myTCvgRuleQry.tCvgRuleSched = ?1 "),
		@NamedQuery(name = "FindTCvgRuleQryByIds", query = "select myTCvgRuleQry from TCvgRuleQry myTCvgRuleQry where myTCvgRuleQry.tCvgRuleQryId.txnId = ?1 AND myTCvgRuleQry.tCvgRuleQryId.ruleId=?2 AND myTCvgRuleQry.tenantId=?3"),})
@Table(name = "t_cvg_rule_qry")
public class TCvgRuleQry implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCvgRuleQryId tCvgRuleQryId;

	/**
	 * 
	 * @generated
	 */
	//@Length(max = 255)
	@Column(name = "qry_stmt", nullable = true, length = 10000)
	private String qryStmt;

	/**
	 * 
	 * @generated
	 */
	//@Length(max = 255)
	@Column(name = "aff_qry_stmt", nullable = true, length = 10000)
	private String affQryStmt;

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
	@Column(name = "aff_hier_level_id", nullable = true, length = 255)
	private Integer affHierLevelId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "aff_type_id", nullable = true, length = 255)
	private Integer affTypeId;

	@ManyToOne
	@JoinColumn(name = "txn_id", referencedColumnName = "txn_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCvgRuleSched tCvgRuleSched;

	/**
	 *
	 * @generated
	 */
	public TCvgRuleQry() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCvgRuleQryId(final TCvgRuleQryId tCvgRuleQryId) {
		this.tCvgRuleQryId = tCvgRuleQryId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCvgRuleQryId getTCvgRuleQryId() {
		return this.tCvgRuleQryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setQryStmt(final String qryStmt) {
		this.qryStmt = qryStmt;
	}

	/**
	 * 
	 * @generated
	 */
	public String getQryStmt() {
		return this.qryStmt;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffQryStmt(final String affQryStmt) {
		this.affQryStmt = affQryStmt;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAffQryStmt() {
		return this.affQryStmt;
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

	public void setAffHierLevelId(final Integer affHierLevelId) {
		this.affHierLevelId = affHierLevelId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAffHierLevelId() {
		return this.affHierLevelId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffTypeId(final Integer affTypeId) {
		this.affTypeId = affTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAffTypeId() {
		return this.affTypeId;
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
		if (this.tCvgRuleSched != null && this.tCvgRuleSched.getTxnId() != null) {

			this.tCvgRuleQryId.setTxnId(this.tCvgRuleSched.getTxnId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgRuleQry that) {
		setTCvgRuleQryId(that.getTCvgRuleQryId());
		setQryStmt(that.getQryStmt());
		setAffQryStmt(that.getAffQryStmt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setAffHierLevelId(that.getAffHierLevelId());
		setAffTypeId(that.getAffTypeId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tCvgRuleQryId == null) ? 0 : tCvgRuleQryId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCvgRuleQryId=[").append(tCvgRuleQryId).append("] ");
		buffer.append("qryStmt=[").append(qryStmt).append("] ");
		buffer.append("affQryStmt=[").append(affQryStmt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("affHierLevelId=[").append(affHierLevelId).append("] ");
		buffer.append("affTypeId=[").append(affTypeId).append("] ");

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
		final TCvgRuleQry other = (TCvgRuleQry) obj;
		if (tCvgRuleQryId == null) {
			if (other.tCvgRuleQryId != null) {
				return false;
			}
		} else if (!tCvgRuleQryId.equals(other.tCvgRuleQryId)) {
			return false;
		}
		return true;
	}
}
