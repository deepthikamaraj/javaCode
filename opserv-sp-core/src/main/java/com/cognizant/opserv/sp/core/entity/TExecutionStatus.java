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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TExecutionStatus 
 * The corresponding mapping table is t_execution_status 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTExecutionStatuss", query = "select myTExecutionStatus from TExecutionStatus myTExecutionStatus"),
		@NamedQuery(name = "FindAllTExecutionStatusById", query = "select myTExecutionStatus from TExecutionStatus myTExecutionStatus where myTExecutionStatus.executionStatusId =?1 and myTExecutionStatus.tenantId =?2"),
		@NamedQuery(name = "CountTExecutionStatuss", query = "Select Count(c) from TExecutionStatus c") })
@Table(name = "t_execution_status", uniqueConstraints = @UniqueConstraint(columnNames = { "execution_status_id" }))
public class TExecutionStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "execution_status_id", nullable = false, length = 255)
	private Integer executionStatusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "status_desc", nullable = false, length = 20)
	private String statusDesc;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "active_flag", nullable = false, length = 20)
	private String activeFlag;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Integer tenantId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tExecutionStatus")
	private Set<TRptSchedExecution> tRptSchedExecutionss;

	/**
	 *
	 * @generated
	 */
	public TExecutionStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecutionStatusId(final Integer executionStatusId) {
		this.executionStatusId = executionStatusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getExecutionStatusId() {
		return this.executionStatusId;
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

	public void setActiveFlag(final String activeFlag) {
		this.activeFlag = activeFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public String getActiveFlag() {
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

	public void setTenantId(final Integer tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTenantId() {
		return this.tenantId;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptSchedExecution> getTRptSchedExecutionss() {
		return this.tRptSchedExecutionss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptSchedExecutionss(final Set<TRptSchedExecution> tRptSchedExecutionss) {
		this.tRptSchedExecutionss = tRptSchedExecutionss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TExecutionStatus that) {
		setExecutionStatusId(that.getExecutionStatusId());
		setStatusDesc(that.getStatusDesc());
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
		result = prime * result + ((executionStatusId == null) ? 0 : executionStatusId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("executionStatusId=[").append(executionStatusId).append("] ");
		buffer.append("statusDesc=[").append(statusDesc).append("] ");
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
		final TExecutionStatus other = (TExecutionStatus) obj;
		if (executionStatusId == null) {
			if (other.executionStatusId != null) {
				return false;
			}
		} else if (!executionStatusId.equals(other.executionStatusId)) {
			return false;
		}
		return true;
	}
}
