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
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TQuotaConfigLoad 
 * The corresponding mapping table is t_quota_config_load 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaConfigLoads", query = "select myTQuotaConfigLoad from TQuotaConfigLoad myTQuotaConfigLoad"),
		@NamedQuery(name = "CountTQuotaConfigLoads", query = "Select Count(c) from TQuotaConfigLoad c") })
@Table(name = "t_quota_config_load", uniqueConstraints = @UniqueConstraint(columnNames = { "load_id" }))
public class TQuotaConfigLoad implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "load_id", nullable = false, length = 255)
	private Long loadId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "config_id", nullable = true, length = 255)
	private Long configId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "ds_inst_id", nullable = true, length = 255)
	private Long dsInstId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "status", nullable = true, length = 45)
	private String status;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "load_log", nullable = true, length = 200)
	private String loadLog;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "submitted_by", nullable = true, length = 100)
	private String submittedBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "submitted_dt", nullable = true, length = 19)
	private Date submittedDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "completed_dt", nullable = true, length = 19)
	private Date completedDt;

	/**
	 *
	 * @generated
	 */
	public TQuotaConfigLoad() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setLoadId(final Long loadId) {
		this.loadId = loadId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getLoadId() {
		return this.loadId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfigId(final Long configId) {
		this.configId = configId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getConfigId() {
		return this.configId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsInstId(final Long dsInstId) {
		this.dsInstId = dsInstId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getDsInstId() {
		return this.dsInstId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLoadLog(final String loadLog) {
		this.loadLog = loadLog;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLoadLog() {
		return this.loadLog;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSubmittedBy(final String submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSubmittedBy() {
		return this.submittedBy;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSubmittedDt(final Date submittedDt) {
		if (submittedDt != null) {
			this.submittedDt = (Date) submittedDt.clone();
		} else {
			this.submittedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSubmittedDt() {
		if (this.submittedDt != null) {
			return (Date) this.submittedDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setCompletedDt(final Date completedDt) {
		if (completedDt != null) {
			this.completedDt = (Date) completedDt.clone();
		} else {
			this.completedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCompletedDt() {
		if (this.completedDt != null) {
			return (Date) this.completedDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaConfigLoad that) {
		setLoadId(that.getLoadId());
		setConfigId(that.getConfigId());
		setDsInstId(that.getDsInstId());
		setStatus(that.getStatus());
		setLoadLog(that.getLoadLog());
		setSubmittedBy(that.getSubmittedBy());
		setSubmittedDt(that.getSubmittedDt());
		setCompletedDt(that.getCompletedDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((loadId == null) ? 0 : loadId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("loadId=[").append(loadId).append("] ");
		buffer.append("configId=[").append(configId).append("] ");
		buffer.append("dsInstId=[").append(dsInstId).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("loadLog=[").append(loadLog).append("] ");
		buffer.append("submittedBy=[").append(submittedBy).append("] ");
		buffer.append("submittedDt=[").append(submittedDt).append("] ");
		buffer.append("completedDt=[").append(completedDt).append("] ");

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
		final TQuotaConfigLoad other = (TQuotaConfigLoad) obj;
		if (loadId == null) {
			if (other.loadId != null) {
				return false;
			}
		} else if (!loadId.equals(other.loadId)) {
			return false;
		}
		return true;
	}
}
