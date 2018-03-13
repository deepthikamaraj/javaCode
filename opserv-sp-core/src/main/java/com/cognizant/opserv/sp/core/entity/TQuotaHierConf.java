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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * JPA class representing TQuotaHierConf The corresponding mapping table is
 * t_quota_hier_conf
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaHierConfs", query = "select myTQuotaHierConf from TQuotaHierConf myTQuotaHierConf"),
		@NamedQuery(name = "CountTQuotaHierConfs", query = "Select Count(c) from TQuotaHierConf c"),
		@NamedQuery(name = "FindNextLevelRfnmntStrtDT", query = "select tqhc.startDate from TQuotaHierConf tqhc "
				+ "where tqhc.tQuotaConfig.confId = ?1 and tqhc.salesHierId = ?2"),
		@NamedQuery(name = "FindSameLevelRfnmntEndDT", query = "select tqhc.endDate from TQuotaHierConf tqhc "
				+ "where tqhc.tQuotaConfig.confId = ?1 and tqhc.salesHierId = ?2") })
@Table(name = "t_quota_hier_conf", uniqueConstraints = @UniqueConstraint(columnNames = { "conf_hier_id" }))
public class TQuotaHierConf implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "conf_hier_id", nullable = false, length = 255)
	private Long confHierId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = true, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "threshold_value", nullable = true, length = 45)
	private String thresholdValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "start_date", nullable = true, length = 19)
	private Date startDate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "end_date", nullable = true, length = 19)
	private Date endDate;

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
	@Column(name = "created_dt", nullable = false, length = 19)
	private Date createdDt;

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
	@Column(name = "updated_dt", nullable = true, length = 19)
	private Date updatedDt;

	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "conf_id")
	private TQuotaConfig tQuotaConfig;

	/**
	 * 
	 * @generated
	 */
	public TQuotaHierConf() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfHierId(final Long confHierId) {
		this.confHierId = confHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getConfHierId() {
		return this.confHierId;
	}

	public TQuotaConfig getTQuotaConfig() {
		return tQuotaConfig;
	}

	public void setTQuotaConfig(TQuotaConfig tQuotaConfig) {
		this.tQuotaConfig = tQuotaConfig;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setThresholdValue(final String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getThresholdValue() {
		return this.thresholdValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStartDate(final Date startDate) {
		if (startDate != null) {
			this.startDate = (Date) startDate.clone();
		} else {
			this.startDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getStartDate() {
		if (this.startDate != null) {
			return (Date) this.startDate.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEndDate(final Date endDate) {
		if (endDate != null) {
			this.endDate = (Date) endDate.clone();
		} else {
			this.endDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEndDate() {
		if (this.endDate != null) {
			return (Date) this.endDate.clone();
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

	public void setCreatedDt(final Date createdDt) {
		if (createdDt != null) {
			this.createdDt = (Date) createdDt.clone();
		} else {
			this.createdDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreatedDt() {
		if (this.createdDt != null) {
			return (Date) this.createdDt.clone();
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

	public void setUpdatedDt(final Date updatedDt) {
		if (updatedDt != null) {
			this.updatedDt = (Date) updatedDt.clone();
		} else {
			this.updatedDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdatedDt() {
		if (this.updatedDt != null) {
			return (Date) this.updatedDt.clone();
		} else {
			return null;
		}
	}

	public Short getTenantId() {
		return tenantId;
	}

	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaHierConf that) {
		setConfHierId(that.getConfHierId());
		setTQuotaConfig(that.getTQuotaConfig());
		setSalesHierId(that.getSalesHierId());
		setThresholdValue(that.getThresholdValue());
		setStartDate(that.getStartDate());
		setEndDate(that.getEndDate());
		setCreatedBy(that.getCreatedBy());
		setCreatedDt(that.getCreatedDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDt(that.getUpdatedDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confHierId == null) ? 0 : confHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("confHierId=[").append(confHierId).append("] ");
		buffer.append("confId=[").append(tQuotaConfig.getConfId()).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("thresholdValue=[").append(thresholdValue).append("] ");
		buffer.append("startDate=[").append(startDate).append("] ");
		buffer.append("endDate=[").append(endDate).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdDt=[").append(createdDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updatedDt=[").append(updatedDt).append("] ");

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
		final TQuotaHierConf other = (TQuotaHierConf) obj;
		if (confHierId == null || other.confHierId == null) {
			return false;
		} else if (!confHierId.equals(other.confHierId)) {
			return false;
		}
		return true;
	}
}
