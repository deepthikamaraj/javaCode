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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAlgmntStatus 
 * The corresponding mapping table is t_algmnt_status 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntStatuss", query = "select myTAlgmntStatus from TAlgmntStatus myTAlgmntStatus"),
		@NamedQuery(name = "CountTAlgmntStatuss", query = "Select Count(c) from TAlgmntStatus c"),
		@NamedQuery(name = "FindTAlgmntStatus", query = "select myTAlgmntStatus from TAlgmntStatus myTAlgmntStatus where myTAlgmntStatus.tenantId = ?1 and myTAlgmntStatus.tAlgmntStatusId.localeId = ?2 and myTAlgmntStatus.activeFlag = ?3")
		})
@Table(name = "t_algmnt_status", uniqueConstraints = @UniqueConstraint(columnNames = { "status_id" }))
public class TAlgmntStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAlgmntStatusId tAlgmntStatusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "status_desc", nullable = false, length = 50)
	private String statusDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

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

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntStatus")
	private Set<TAlgmnt> tAlgmntss;*/

	/**
	 *
	 * @generated
	 */
	public TAlgmntStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Integer getStatusId() {
		return this.statusId;
	}*/
	
	public TAlgmntStatusId getTAlgmntStatusId() {
		return tAlgmntStatusId;
	}

	public void setTAlgmntStatusId(TAlgmntStatusId tAlgmntStatusId) {
		this.tAlgmntStatusId = tAlgmntStatusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusDesc(final String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	/**
	 * 
	 * @generated
	 */
	public String getStatusDesc() {
		return this.statusDesc;
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
	/*public Set<TAlgmnt> getTAlgmntss() {
		return this.tAlgmntss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTAlgmntss(final Set<TAlgmnt> tAlgmntss) {
		this.tAlgmntss = tAlgmntss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntStatus that) {
		//setStatusId(that.getStatusId());
		setStatusDesc(that.getStatusDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}
	
	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAlgmntStatusId=[").append(tAlgmntStatusId).append("] ");
		buffer.append("statusDesc=[").append(statusDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tAlgmntStatusId == null) ? 0 : tAlgmntStatusId.hashCode());
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
		TAlgmntStatus other = (TAlgmntStatus) obj;
		if (tAlgmntStatusId == null) {
			if (other.tAlgmntStatusId != null)
				return false;
		} else if (!tAlgmntStatusId.equals(other.tAlgmntStatusId))
			return false;
		return true;
	}
	
	
	
}
