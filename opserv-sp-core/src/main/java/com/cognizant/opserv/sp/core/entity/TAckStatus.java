package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.persistence.QueryHint;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAckStatus 
 * The corresponding mapping table is t_ack_status 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTAckStatuss", query = "select myTAckStatus from TAckStatus myTAckStatus"),
		@NamedQuery(name = "CountTAckStatuss", query = "Select Count(c) from TAckStatus c"),
		@NamedQuery(name = "findTAckStatusname", query = "Select c.ackStatusName,c.ackStatusDesc from TAckStatus c where c.tAckStatusId.ackStatusId =?1 and c.tAckStatusId.localeId =?2"),
		@NamedQuery(name = "FindAllActiveTAckStatus", query = "Select c.tAckStatusId.ackStatusId,c.ackStatusName from TAckStatus c " +
				" where c.tAckStatusId.localeId = ?1 and c.tenantId= ?2 and c.activeFlag = 'Y' ",
				hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache"),
	
		})
})
@Table(name = "t_ack_status", uniqueConstraints = @UniqueConstraint(columnNames = { "ack_status_id" }))
public class TAckStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAckStatusId tAckStatusId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "ack_status_name", nullable = true, length = 20)
	private String ackStatusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "ack_status_desc", nullable = true, length = 50)
	private String ackStatusDesc;

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
	
	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAckStatus")
	private Set<TRptRecipient> tRptRecipientss;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAckStatus")
	private Set<TCommReceipient> tCommReceipientss;*/

	/**
	 *
	 * @generated
	 */
	public TAckStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setAckStatusId(final Integer ackStatusId) {
		this.ackStatusId = ackStatusId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Integer getAckStatusId() {
		return this.ackStatusId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setAckStatusName(final String ackStatusName) {
		this.ackStatusName = ackStatusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAckStatusName() {
		return this.ackStatusName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAckStatusDesc(final String ackStatusDesc) {
		this.ackStatusDesc = ackStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAckStatusDesc() {
		return this.ackStatusDesc;
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
	 * 
	 * @generated
	 */
	/*public Set<TCommReceipient> getTCommReceipientss() {
		return this.tCommReceipientss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTCommReceipientss(final Set<TCommReceipient> tCommReceipientss) {
		this.tCommReceipientss = tCommReceipientss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAckStatus that) {
		//setAckStatusId(that.getAckStatusId());
		setTAckStatusId(that.getTAckStatusId());
		setAckStatusName(that.getAckStatusName());
		setAckStatusDesc(that.getAckStatusDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("ackStatusId=[").append(tAckStatusId).append("] ");
		buffer.append("ackStatusName=[").append(ackStatusName).append("] ");
		buffer.append("ackStatusDesc=[").append(ackStatusDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}

	public TAckStatusId getTAckStatusId() {
		return tAckStatusId;
	}

	public void setTAckStatusId(TAckStatusId tAckStatusId) {
		this.tAckStatusId = tAckStatusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tAckStatusId == null) ? 0 : tAckStatusId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TAckStatus other = (TAckStatus) obj;
		if (tAckStatusId == null) {
			if (other.tAckStatusId != null)
				return false;
		} else if (!tAckStatusId.equals(other.tAckStatusId))
			return false;
		return true;
	}
	
}
