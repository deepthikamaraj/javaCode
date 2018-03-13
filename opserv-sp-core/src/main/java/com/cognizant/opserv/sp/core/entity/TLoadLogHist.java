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
 * JPA class representing TLoadLogHist 
 * The corresponding mapping table is t_load_log_hist 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTLoadLogHists", query = "select myTLoadLogHist from TLoadLogHist myTLoadLogHist"),
		@NamedQuery(name = "CountTLoadLogHists", query = "Select Count(c) from TLoadLogHist c"),
		@NamedQuery(name = "FindTLoadLogHistByTLoadLog", query = "select myTLoadLogHist from TLoadLogHist myTLoadLogHist where myTLoadLogHist.tLoadLog = ?1 "),
		@NamedQuery(name = "CountTLoadLogHistsByTLoadLog", query = "select Count(myTLoadLogHist) from TLoadLogHist myTLoadLogHist where myTLoadLogHist.tLoadLog = ?1 ") })
@Table(name = "t_load_log_hist", uniqueConstraints = @UniqueConstraint(columnNames = { "rev_id" }))
public class TLoadLogHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rev_id", nullable = false, length = 255)
	private Integer revId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "execution_dt_tm", nullable = true, length = 19)
	private Date executionDtTm;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "execution_status", nullable = true, length = 20)
	private String executionStatus;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "error_number", nullable = true, length = 255)
	private Integer errorNumber;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "error_desc", nullable = true, length = 20)
	private String errorDesc;

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
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "log_id", referencedColumnName = "log_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TLoadLog tLoadLog;

	/**
	 *
	 * @generated
	 */
	public TLoadLogHist() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRevId(final Integer revId) {
		this.revId = revId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRevId() {
		return this.revId;
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

	public void setExecutionStatus(final String executionStatus) {
		this.executionStatus = executionStatus;
	}

	/**
	 * 
	 * @generated
	 */
	public String getExecutionStatus() {
		return this.executionStatus;
	}

	/**
	 * 
	 * @generated
	 */

	public void setErrorNumber(final Integer errorNumber) {
		this.errorNumber = errorNumber;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getErrorNumber() {
		return this.errorNumber;
	}

	/**
	 * 
	 * @generated
	 */

	public void setErrorDesc(final String errorDesc) {
		this.errorDesc = errorDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getErrorDesc() {
		return this.errorDesc;
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
	public TLoadLog getTLoadLog() {
		return this.tLoadLog;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTLoadLog(final TLoadLog tLoadLog) {
		this.tLoadLog = tLoadLog;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TLoadLogHist that) {
		setRevId(that.getRevId());
		setExecutionDtTm(that.getExecutionDtTm());
		setExecutionStatus(that.getExecutionStatus());
		setErrorNumber(that.getErrorNumber());
		setErrorDesc(that.getErrorDesc());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
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
		result = prime * result + ((revId == null) ? 0 : revId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("revId=[").append(revId).append("] ");
		buffer.append("executionDtTm=[").append(executionDtTm).append("] ");
		buffer.append("executionStatus=[").append(executionStatus).append("] ");
		buffer.append("errorNumber=[").append(errorNumber).append("] ");
		buffer.append("errorDesc=[").append(errorDesc).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
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
		final TLoadLogHist other = (TLoadLogHist) obj;
		if (revId == null) {
			if (other.revId != null) {
				return false;
			}
		} else if (!revId.equals(other.revId)) {
			return false;
		}
		return true;
	}
}
