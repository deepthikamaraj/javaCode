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
 * JPA class representing TCommTargetRecipient 
 * The corresponding mapping table is t_comm_target_recipient 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCommTargetRecipients", query = "select myTCommTargetRecipient from TCommTargetRecipient myTCommTargetRecipient"),
		@NamedQuery(name = "CountTCommTargetRecipients", query = "Select Count(c) from TCommTargetRecipient c"),
		@NamedQuery(name = "FindTCommTargetRecipientByTPers", query = "select myTCommTargetRecipient from TCommTargetRecipient myTCommTargetRecipient where myTCommTargetRecipient.tPers = ?1 "),
		@NamedQuery(name = "CountTCommTargetRecipientsByTPers", query = "select Count(*) from TCommTargetRecipient myTCommTargetRecipient where myTCommTargetRecipient.tPers = ?1 "),
		@NamedQuery(name = "FindTCommTargetRecipientByTComm", query = "select myTCommTargetRecipient from TCommTargetRecipient myTCommTargetRecipient where myTCommTargetRecipient.tComm = ?1 "),
		@NamedQuery(name = "FindTCommTargetRecipientByStatusDate", query = "select myTCommTargetRecipient from TCommTargetRecipient myTCommTargetRecipient where myTCommTargetRecipient.tComm.tCommStatus.commStatusId = ?1 " +
																	  " AND myTCommTargetRecipient.tCommTargetRecipientId.staffId=?2 AND myTCommTargetRecipient.tComm.validityStartDt<=?3 AND myTCommTargetRecipient.tComm.validityEndDt>=?4" +
																	  " AND myTCommTargetRecipient.tComm.tCommType.commTypeId=?5"),
		@NamedQuery(name = "CountTCommTargetRecipientsByTComm", query = "select Count(*) from TCommTargetRecipient myTCommTargetRecipient where myTCommTargetRecipient.tComm = ?1 ")
		})
@Table(name = "t_comm_target_recipient")
public class TCommTargetRecipient implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCommTargetRecipientId tCommTargetRecipientId;

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
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	@Column(name = "tenant_id", nullable = true, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "comm_id", referencedColumnName = "comm_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TComm tComm;

	/**
	 *
	 * @generated
	 */
	public TCommTargetRecipient() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTCommTargetRecipientId(final TCommTargetRecipientId tCommTargetRecipientId) {
		this.tCommTargetRecipientId = tCommTargetRecipientId;
	}

	/**
	 * 
	 * @generated
	 */
	public TCommTargetRecipientId getTCommTargetRecipientId() {
		return this.tCommTargetRecipientId;
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
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		if (this.tPers != null && this.tPers.getStaffId() != null) {

			this.tCommTargetRecipientId.setStaffId(this.tPers.getStaffId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TComm getTComm() {
		return this.tComm;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTComm(final TComm tComm) {
		this.tComm = tComm;
		if (this.tComm != null && this.tComm.getCommId() != null) {

			this.tCommTargetRecipientId.setCommId(this.tComm.getCommId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommTargetRecipient that) {
		setTCommTargetRecipientId(that.getTCommTargetRecipientId());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((tCommTargetRecipientId == null) ? 0 : tCommTargetRecipientId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCommTargetRecipientId=[").append(tCommTargetRecipientId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TCommTargetRecipient other = (TCommTargetRecipient) obj;
		if (tCommTargetRecipientId == null) {
			if (other.tCommTargetRecipientId != null) {
				return false;
			}
		} else if (!tCommTargetRecipientId.equals(other.tCommTargetRecipientId)) {
			return false;
		}
		return true;
	}
}
