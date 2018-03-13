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
 * JPA class representing TRptTargetRecipient 
 * The corresponding mapping table is t_rpt_target_recipient 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptTargetRecipients", query = "select myTRptTargetRecipient from TRptTargetRecipient myTRptTargetRecipient"),
		@NamedQuery(name = "CountTRptTargetRecipients", query = "Select Count(c) from TRptTargetRecipient c"),
		@NamedQuery(name = "FindTRptTargetRecipientByTPers", query = "select myTRptTargetRecipient from TRptTargetRecipient myTRptTargetRecipient where myTRptTargetRecipient.tPers = ?1 "),
		@NamedQuery(name = "CountTRptTargetRecipientsByTPers", query = "select Count(*) from TRptTargetRecipient myTRptTargetRecipient where myTRptTargetRecipient.tPers = ?1 "),
		@NamedQuery(name = "FindTRptTargetRecipientByTRptConfig", query = "select myTRptTargetRecipient from TRptTargetRecipient myTRptTargetRecipient where myTRptTargetRecipient.tRptConfig = ?1 "),
		@NamedQuery(name = "CountTRptTargetRecipientsByTRptConfig", query = "select Count(*) from TRptTargetRecipient myTRptTargetRecipient where myTRptTargetRecipient.tRptConfig = ?1 ") ,
		@NamedQuery(name = "FindTargetRecipientsByConfigId", query = "select myTRptTargetRecipient.tRptTargetRecipientId.staffId from TRptTargetRecipient myTRptTargetRecipient where myTRptTargetRecipient.tRptTargetRecipientId.rptConfigId = ?1 and myTRptTargetRecipient.tenantId = ?2 ") })
@Table(name = "t_rpt_target_recipient")
public class TRptTargetRecipient implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	@Audited
	private TRptTargetRecipientId tRptTargetRecipientId;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

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

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited
	private TRptConfig tRptConfig;

	/**
	 *
	 * @generated
	 */
	public TRptTargetRecipient() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptTargetRecipientId(final TRptTargetRecipientId tRptTargetRecipientId) {
		this.tRptTargetRecipientId = tRptTargetRecipientId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRptTargetRecipientId getTRptTargetRecipientId() {
		return this.tRptTargetRecipientId;
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

			this.tRptTargetRecipientId.setStaffId(this.tPers.getStaffId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TRptConfig getTRptConfig() {
		return this.tRptConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfig(final TRptConfig tRptConfig) {
		this.tRptConfig = tRptConfig;
		if (this.tRptConfig != null && this.tRptConfig.getRptConfigId() != null) {

			this.tRptTargetRecipientId.setRptConfigId(this.tRptConfig.getRptConfigId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptTargetRecipient that) {
		setTRptTargetRecipientId(that.getTRptTargetRecipientId());
		setActiveFlag(that.getActiveFlag());
		setTenantId(that.getTenantId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tRptTargetRecipientId == null) ? 0 : tRptTargetRecipientId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptTargetRecipientId=[").append(tRptTargetRecipientId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");

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
		final TRptTargetRecipient other = (TRptTargetRecipient) obj;
		if (tRptTargetRecipientId == null) {
			if (other.tRptTargetRecipientId != null) {
				return false;
			}
		} else if (!tRptTargetRecipientId.equals(other.tRptTargetRecipientId)) {
			return false;
		}
		return true;
	}
}
