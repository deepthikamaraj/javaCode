package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TMtrValMsg 
 * The corresponding mapping table is t_mtr_val_msg 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrValMsgs", query = "select myTMtrValMsg from TMtrValMsg myTMtrValMsg"),
		@NamedQuery(name = "CountTMtrValMsgs", query = "Select Count(c) from TMtrValMsg c"),
		@NamedQuery(name = "FindTMtrValMsgByTMtr", query = "select myTMtrValMsg from TMtrValMsg myTMtrValMsg where myTMtrValMsg.tMtr = ?1 "),
		@NamedQuery(name = "CountTMtrValMsgsByTMtr", query = "select Count(*) from TMtrValMsg myTMtrValMsg where myTMtrValMsg.tMtr = ?1 "),
		@NamedQuery(name = "findTMtrValMsgsByMtrId", query = "select myTMtrValMsg.valMsg from TMtrValMsg myTMtrValMsg where myTMtrValMsg.tMtr.mtrId = ?1 and myTMtrValMsg.tenantId =?2") })
@Table(name = "t_mtr_val_msg")
public class TMtrValMsg implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TMtrValMsgId tMtrValMsgId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 400)
	@Column(name = "val_msg", nullable = false, length = 400)
	private String valMsg;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "mtr_id", referencedColumnName = "mtr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMtr tMtr;

	/**
	 *
	 * @generated
	 */
	public TMtrValMsg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTMtrValMsgId(final TMtrValMsgId tMtrValMsgId) {
		this.tMtrValMsgId = tMtrValMsgId;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtrValMsgId getTMtrValMsgId() {
		return this.tMtrValMsgId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValMsg(final String valMsg) {
		this.valMsg = valMsg;
	}

	/**
	 * 
	 * @generated
	 */
	public String getValMsg() {
		return this.valMsg;
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
	public TMtr getTMtr() {
		return this.tMtr;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtr(final TMtr tMtr) {
		this.tMtr = tMtr;
		if (this.tMtr != null && this.tMtr.getMtrId() != null) {

			this.tMtrValMsgId.setMtrId(this.tMtr.getMtrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtrValMsg that) {
		setTMtrValMsgId(that.getTMtrValMsgId());
		setValMsg(that.getValMsg());
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
		result = prime * result + ((tMtrValMsgId == null) ? 0 : tMtrValMsgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tMtrValMsgId=[").append(tMtrValMsgId).append("] ");
		buffer.append("valMsg=[").append(valMsg).append("] ");
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
		final TMtrValMsg other = (TMtrValMsg) obj;
		if (tMtrValMsgId == null) {
			if (other.tMtrValMsgId != null) {
				return false;
			}
		} else if (!tMtrValMsgId.equals(other.tMtrValMsgId)) {
			return false;
		}
		return true;
	}
}
