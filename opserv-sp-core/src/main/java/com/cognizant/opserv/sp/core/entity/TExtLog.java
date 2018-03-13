package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TExtLog 
 * The corresponding mapping table is t_ext_log 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTExtLogs", query = "select myTExtLog from TExtLog myTExtLog"),
		@NamedQuery(name = "CountTExtLogs", query = "Select Count(c) from TExtLog c"),
		@NamedQuery(name = "FindTExtLogByTDsStg", query = "select myTExtLog from TExtLog myTExtLog where myTExtLog.tDsStg = ?1 "),
		@NamedQuery(name = "CountTExtLogsByTDsStg", query = "select Count(myTExtLog) from TExtLog myTExtLog where myTExtLog.tDsStg = ?1 ") })
@Table(name = "t_ext_log", uniqueConstraints = @UniqueConstraint(columnNames = { "log_id" }))
public class TExtLog implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "log_id", nullable = false, length = 255)
	private Long logId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "execution_dt_tm", nullable = false, length = 19)
	private Date executionDtTm;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "log_file_name", nullable = true, length = 50)
	private String logFileName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "log_loc", nullable = true, length = 200)
	private String logLoc;

	@ManyToOne
	@JoinColumn(name = "ds_id", referencedColumnName = "ds_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TDsStg tDsStg;

	/**
	 *
	 * @generated
	 */
	public TExtLog() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setLogId(final Long logId) {
		this.logId = logId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getLogId() {
		return this.logId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExecutionDtTm(final Date executionDtTm) {
		if (executionDtTm != null) {
			this.executionDtTm = (Date) executionDtTm.clone();
		} else {
			this.executionDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecutionDtTm() {
		if (this.executionDtTm != null) {
			return (Date) this.executionDtTm.clone();
		} else {
			return null;
		}
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

	public void setLogFileName(final String logFileName) {
		this.logFileName = logFileName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLogFileName() {
		return this.logFileName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLogLoc(final String logLoc) {
		this.logLoc = logLoc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLogLoc() {
		return this.logLoc;
	}

	/**
	 * 
	 * @generated
	 */
	public TDsStg getTDsStg() {
		return this.tDsStg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsStg(final TDsStg tDsStg) {
		this.tDsStg = tDsStg;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TExtLog that) {
		setLogId(that.getLogId());
		setExecutionDtTm(that.getExecutionDtTm());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setTenantId(that.getTenantId());
		setLogFileName(that.getLogFileName());
		setLogLoc(that.getLogLoc());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((logId == null) ? 0 : logId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("logId=[").append(logId).append("] ");
		buffer.append("executionDtTm=[").append(executionDtTm).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("logFileName=[").append(logFileName).append("] ");
		buffer.append("logLoc=[").append(logLoc).append("] ");

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
		final TExtLog other = (TExtLog) obj;
		if (logId == null) {
			if (other.logId != null) {
				return false;
			}
		} else if (!logId.equals(other.logId)) {
			return false;
		}
		return true;
	}
}
