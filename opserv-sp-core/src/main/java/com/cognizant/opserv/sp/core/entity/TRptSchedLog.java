package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TRptSchedLog 
 * The corresponding mapping table is t_rpt_sched_log 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptSchedLogs", query = "select myTRptSchedLog from TRptSchedLog myTRptSchedLog"),
		@NamedQuery(name = "FindAllTRptSchedLogsById", query = "select myTRptSchedLog from TRptSchedLog myTRptSchedLog where myTRptSchedLog.rptSchedId =?1 and myTRptSchedLog.tenantId = ?2"),
		@NamedQuery(name = "CountTRptSchedLogs", query = "Select Count(c) from TRptSchedLog c") })
@Table(name = "t_rpt_sched_log", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_sched_id" }))
public class TRptSchedLog implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@Column(name = "rpt_sched_id", nullable = false, length = 255)
	private Integer rptSchedId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "last_execution_dt", nullable = true, length = 19)
	private Date lastExecutionDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "next_execution_dt", nullable = true, length = 19)
	private Date nextExecutionDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "execution_cnt", nullable = true, length = 255)
	private Integer executionCnt;

	/**
	 * 
	 * @generated
	 */
	@ManyToOne
	@JoinColumn(name = "execution_status_id", referencedColumnName = "execution_status_id", nullable = false, insertable = true, updatable = true)
	@Valid	
	/*@NotNull
	@Column(name = "execution_status_id", nullable = false, length = 255, insertable = true, updatable = true)*/
	private TExecutionStatus executionStatusId;

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
	public TRptSchedLog() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptSchedId(final Integer rptSchedId) {
		this.rptSchedId = rptSchedId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptSchedId() {
		return this.rptSchedId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLastExecutionDt(final Date lastExecutionDt) {
		if (lastExecutionDt != null) {
			this.lastExecutionDt = (Date) lastExecutionDt.clone();
		} else {
			this.lastExecutionDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getLastExecutionDt() {
		if (this.lastExecutionDt != null) {
			return (Date) this.lastExecutionDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setNextExecutionDt(final Date nextExecutionDt) {
		if (nextExecutionDt != null) {
			this.nextExecutionDt = (Date) nextExecutionDt.clone();
		} else {
			this.nextExecutionDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getNextExecutionDt() {
		if (this.nextExecutionDt != null) {
			return (Date) this.nextExecutionDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecutionCnt(final Integer executionCnt) {
		this.executionCnt = executionCnt;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getExecutionCnt() {
		return this.executionCnt;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecutionStatusId(final TExecutionStatus executionStatusId) {
		this.executionStatusId = executionStatusId;
	}

	/**
	 * 
	 * @generated
	 */
	public TExecutionStatus getExecutionStatusId() {
		return this.executionStatusId;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptSchedLog that) {
		setRptSchedId(that.getRptSchedId());
		setLastExecutionDt(that.getLastExecutionDt());
		setNextExecutionDt(that.getNextExecutionDt());
		setExecutionCnt(that.getExecutionCnt());
		setExecutionStatusId(that.getExecutionStatusId());
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
		result = prime * result + ((rptSchedId == null) ? 0 : rptSchedId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rptSchedId=[").append(rptSchedId).append("] ");
		buffer.append("lastExecutionDt=[").append(lastExecutionDt).append("] ");
		buffer.append("nextExecutionDt=[").append(nextExecutionDt).append("] ");
		buffer.append("executionCnt=[").append(executionCnt).append("] ");
		buffer.append("executionStatusId=[").append(executionStatusId).append("] ");
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
		final TRptSchedLog other = (TRptSchedLog) obj;
		if (rptSchedId == null) {
			if (other.rptSchedId != null) {
				return false;
			}
		} else if (!rptSchedId.equals(other.rptSchedId)) {
			return false;
		}
		return true;
	}
}
