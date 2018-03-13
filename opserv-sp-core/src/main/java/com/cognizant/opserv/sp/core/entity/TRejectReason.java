package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRejectReason 
 * The corresponding mapping table is t_reject_reason 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRejectReasons", query = "select myTRejectReason from TRejectReason myTRejectReason"),
		@NamedQuery(name = "CountTRejectReasons", query = "Select Count(c) from TRejectReason c") })
@Table(name = "t_reject_reason", uniqueConstraints = @UniqueConstraint(columnNames = { "reject_reason_id" }))
public class TRejectReason implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reject_reason_id", nullable = false, length = 255)
	private Integer rejectReasonId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 10)
	@Column(name = "reject_reason_cd", nullable = false, length = 10)
	private String rejectReasonCd;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "reject_reason_desc", nullable = false, length = 50)
	private String rejectReasonDesc;

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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRejectReason")
	private Set<TRptRecipient> tRptRecipientss;*/

	/**
	 *
	 * @generated
	 */
	public TRejectReason() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRejectReasonId(final Integer rejectReasonId) {
		this.rejectReasonId = rejectReasonId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRejectReasonId() {
		return this.rejectReasonId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRejectReasonCd(final String rejectReasonCd) {
		this.rejectReasonCd = rejectReasonCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRejectReasonCd() {
		return this.rejectReasonCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRejectReasonDesc(final String rejectReasonDesc) {
		this.rejectReasonDesc = rejectReasonDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRejectReasonDesc() {
		return this.rejectReasonDesc;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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
	/*public Set<TRptRecipient> getTRptRecipientss() {
		return this.tRptRecipientss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptRecipientss(final Set<TRptRecipient> tRptRecipientss) {
		this.tRptRecipientss = tRptRecipientss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRejectReason that) {
		setRejectReasonId(that.getRejectReasonId());
		setRejectReasonCd(that.getRejectReasonCd());
		setRejectReasonDesc(that.getRejectReasonDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((rejectReasonId == null) ? 0 : rejectReasonId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rejectReasonId=[").append(rejectReasonId).append("] ");
		buffer.append("rejectReasonCd=[").append(rejectReasonCd).append("] ");
		buffer.append("rejectReasonDesc=[").append(rejectReasonDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TRejectReason other = (TRejectReason) obj;
		if (rejectReasonId == null) {
			if (other.rejectReasonId != null) {
				return false;
			}
		} else if (!rejectReasonId.equals(other.rejectReasonId)) {
			return false;
		}
		return true;
	}
}
