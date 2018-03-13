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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TValMsg 
 * The corresponding mapping table is t_val_msg 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTValMsgs", query = "select myTValMsg from TValMsg myTValMsg"),
		@NamedQuery(name = "CountTValMsgs", query = "Select Count(c) from TValMsg c"),
		@NamedQuery(name = "FindTValMsgByTAttrRule", query = "select myTValMsg from TValMsg myTValMsg where myTValMsg.tAttrRule = ?1 "),
		@NamedQuery(name = "CountTValMsgsByTAttrRule", query = "select Count(*) from TValMsg myTValMsg where myTValMsg.tAttrRule = ?1 ") })
@Table(name = "t_val_msg")
public class TValMsg implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TValMsgId tValMsgId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 300)
	@Column(name = "err_msg", nullable = false, length = 300)
	private String errMsg;

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
	@JoinColumn(name = "rule_id", referencedColumnName = "rule_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TAttrRule tAttrRule;

	/**
	 *
	 * @generated
	 */
	public TValMsg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTValMsgId(final TValMsgId tValMsgId) {
		this.tValMsgId = tValMsgId;
	}

	/**
	 * 
	 * @generated
	 */
	public TValMsgId getTValMsgId() {
		return this.tValMsgId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setErrMsg(final String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 
	 * @generated
	 */
	public String getErrMsg() {
		return this.errMsg;
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
	public TAttrRule getTAttrRule() {
		return this.tAttrRule;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrRule(final TAttrRule tAttrRule) {
		this.tAttrRule = tAttrRule;
		if (this.tAttrRule != null && this.tAttrRule.getRuleId() != null) {

			this.tValMsgId.setRuleId(tAttrRule.getRuleId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TValMsg that) {
		setTValMsgId(that.getTValMsgId());
		setErrMsg(that.getErrMsg());
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
		result = prime * result + ((tValMsgId == null) ? 0 : tValMsgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tValMsgId=[").append(tValMsgId).append("] ");
		buffer.append("errMsg=[").append(errMsg).append("] ");
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
		final TValMsg other = (TValMsg) obj;
		if (tValMsgId == null) {
			if (other.tValMsgId != null) {
				return false;
			}
		} else if (!tValMsgId.equals(other.tValMsgId)) {
			return false;
		}
		return true;
	}
}
