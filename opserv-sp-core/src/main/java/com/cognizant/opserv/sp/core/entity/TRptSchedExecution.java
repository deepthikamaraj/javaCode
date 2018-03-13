package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TRptSchedExecution 
 * The corresponding mapping table is t_rpt_sched_execution 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRptSchedExecutions", query = "select myTRptSchedExecution from TRptSchedExecution myTRptSchedExecution"),
		@NamedQuery(name = "CountTRptSchedExecutions", query = "Select Count(c) from TRptSchedExecution c"),
		@NamedQuery(name = "FindTRptSchedExecutionByTExecutionStatus", query = "select myTRptSchedExecution from TRptSchedExecution myTRptSchedExecution where myTRptSchedExecution.tExecutionStatus = ?1 "),
		@NamedQuery(name = "CountTRptSchedExecutionsByTExecutionStatus", query = "select Count(myTRptSchedExecution) from TRptSchedExecution myTRptSchedExecution where myTRptSchedExecution.tExecutionStatus = ?1 "),
		@NamedQuery(name = "FindTRptSchedExecutionByTRptSched", query = "select myTRptSchedExecution from TRptSchedExecution myTRptSchedExecution where myTRptSchedExecution.tRptSched = ?1 "),
		@NamedQuery(name = "CountTRptSchedExecutionsByTRptSched", query = "select Count(myTRptSchedExecution) from TRptSchedExecution myTRptSchedExecution where myTRptSchedExecution.tRptSched = ?1 ") })
@Table(name = "t_rpt_sched_execution", uniqueConstraints = @UniqueConstraint(columnNames = { "sched_execution_id" }))
public class TRptSchedExecution implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sched_execution_id", nullable = false, length = 255)
	private Integer schedExecutionId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "execution_dt", nullable = false, length = 10)
	private Date executionDt;

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
	@Length(max = 200)
	@Column(name = "failure_reason", nullable = true, length = 200)
	private String failureReason;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "execution_status_id", referencedColumnName = "execution_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TExecutionStatus tExecutionStatus;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_sched_id", referencedColumnName = "rpt_sched_id", nullable = false, insertable = true, updatable = true)
	/*@JoinColumns({
			@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "rpt_sched_id", referencedColumnName = "rpt_sched_id", nullable = false, insertable = true, updatable = true) })*/
	@Valid
	@NotAudited
	private TRptSched tRptSched;

	/**
	 *
	 * @generated
	 */
	public TRptSchedExecution() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSchedExecutionId(final Integer schedExecutionId) {
		this.schedExecutionId = schedExecutionId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSchedExecutionId() {
		return this.schedExecutionId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecutionDt(final Date executionDt) {
		if (executionDt != null) {
			this.executionDt = (Date) executionDt.clone();
		} else {
			this.executionDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecutionDt() {
		if (this.executionDt != null) {
			return (Date) this.executionDt.clone();
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

	public void setFailureReason(final String failureReason) {
		this.failureReason = failureReason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFailureReason() {
		return this.failureReason;
	}

	/**
	 * 
	 * @generated
	 */
	public TExecutionStatus getTExecutionStatus() {
		return this.tExecutionStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTExecutionStatus(final TExecutionStatus tExecutionStatus) {
		this.tExecutionStatus = tExecutionStatus;

	}

	/**
	 * 
	 * @generated
	 */
	public TRptSched getTRptSched() {
		return this.tRptSched;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptSched(final TRptSched tRptSched) {
		this.tRptSched = tRptSched;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptSchedExecution that) {
		setSchedExecutionId(that.getSchedExecutionId());
		setExecutionDt(that.getExecutionDt());
		setTenantId(that.getTenantId());
		setFailureReason(that.getFailureReason());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((schedExecutionId == null) ? 0 : schedExecutionId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("schedExecutionId=[").append(schedExecutionId).append("] ");
		buffer.append("executionDt=[").append(executionDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("failureReason=[").append(failureReason).append("] ");

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
		final TRptSchedExecution other = (TRptSchedExecution) obj;
		if (schedExecutionId == null) {
			if (other.schedExecutionId != null) {
				return false;
			}
		} else if (!schedExecutionId.equals(other.schedExecutionId)) {
			return false;
		}
		return true;
	}
}
