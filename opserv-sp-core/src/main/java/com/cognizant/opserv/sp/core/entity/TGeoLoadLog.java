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
 * JPA class representing TGeoLoadLog 
 * The corresponding mapping table is t_geo_load_log 
 */

@Entity
@NamedQueries({
	@NamedQuery(name = "FindAllTGeoLoadLogs", query = "select myTGeoLoadLog from TGeoLoadLog myTGeoLoadLog"),
	@NamedQuery(name = "CountTGeoLoadLogs", query = "Select Count(c) from TGeoLoadLog c"),
	@NamedQuery(name = "FindTGeoLoadLogByTGeoLoad", query = "select myTGeoLoadLog from TGeoLoadLog myTGeoLoadLog where myTGeoLoadLog.tGeoLoad = ?1 "),
	@NamedQuery(name = "CountTGeoLoadLogsByTGeoLoad", query = "select Count(myTGeoLoadLog) from TGeoLoadLog myTGeoLoadLog where myTGeoLoadLog.tGeoLoad = ?1 ")
})
@Table(name = "t_geo_load_log", uniqueConstraints = @UniqueConstraint(columnNames = { "log_id" }))
public class TGeoLoadLog implements Serializable {
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
	@Column(name = "exec_start_dt_tm", nullable = true, length = 19)
	private Date execStartDtTm;

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
	@Length(max = 150)
	@Column(name = "log_file_name", nullable = true, length = 150)
	private String logFileName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "log_loc", nullable = true, length = 200)
	private String logLoc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "exec_end_dt_tm", nullable = true, length = 19)
	private Date execEndDtTm;

	@ManyToOne
	@JoinColumn(name = "ds_id", referencedColumnName = "ds_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TGeoLoad tGeoLoad;

	/**
	 *
	 * @generated
	 */
	public TGeoLoadLog() {
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

	public void setExecStartDtTm(final Date execStartDtTm) {
		if (execStartDtTm != null) {
			this.execStartDtTm = (Date) execStartDtTm.clone();
		} else {
			this.execStartDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecStartDtTm() {
		if (this.execStartDtTm != null) {
			return (Date) this.execStartDtTm.clone();
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

	public void setExecEndDtTm(final Date execEndDtTm) {
		if (execEndDtTm != null) {
			this.execEndDtTm = (Date) execEndDtTm.clone();
		} else {
			this.execEndDtTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getExecEndDtTm() {
		if (this.execEndDtTm != null) {
			return (Date) this.execEndDtTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public TGeoLoad getTGeoLoad() {
		return this.tGeoLoad;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTGeoLoad(final TGeoLoad tGeoLoad) {
		this.tGeoLoad = tGeoLoad;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TGeoLoadLog that) {
		setLogId(that.getLogId());
		setExecStartDtTm(that.getExecStartDtTm());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setTenantId(that.getTenantId());
		setLogFileName(that.getLogFileName());
		setLogLoc(that.getLogLoc());
		setExecEndDtTm(that.getExecEndDtTm());
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
		buffer.append("execStartDtTm=[").append(execStartDtTm).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("logFileName=[").append(logFileName).append("] ");
		buffer.append("logLoc=[").append(logLoc).append("] ");
		buffer.append("execEndDtTm=[").append(execEndDtTm).append("] ");

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
		final TGeoLoadLog other = (TGeoLoadLog) obj;
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
