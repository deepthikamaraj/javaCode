package com.cognizant.opserv.sp.core.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TRptSched 
 * The corresponding mapping table is t_rpt_sched 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptScheds", query = "select myTRptSched from TRptSched myTRptSched"),
		@NamedQuery(name = "CountTRptScheds", query = "Select Count(c) from TRptSched c"),
		@NamedQuery(name = "FindTRptSchedByTRptFreq", query = "select myTRptSched from TRptSched myTRptSched where myTRptSched.rptFreqId = ?1 "),
		@NamedQuery(name = "CountTRptSchedsByTRptFreq", query = "select Count(*) from TRptSched myTRptSched where myTRptSched.rptFreqId = ?1 "),
		@NamedQuery(name = "FindTRptSchedByTRptConfig", query = "select myTRptSched from TRptSched myTRptSched where myTRptSched.tRptConfig = ?1 "),
		@NamedQuery(name = "CountTRptSchedsByTRptConfig", query = "select Count(*) from TRptSched myTRptSched where myTRptSched.tRptConfig = ?1 ") })
@Table(name = "t_rpt_sched")
public class TRptSched implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_sched_id", nullable = false, length = 255)
	private Integer tRptSchedId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "freq_interval", nullable = false, length = 255)
	private Integer freqInterval;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "start_dt", nullable = true, length = 10)
	private Date startDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "end_dt", nullable = true, length = 10)
	private Date endDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "start_tm", nullable = true, length = 10)
	private Date startTm;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "end_tm", nullable = true, length = 10)
	private Date endTm;

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
	@Length(max = 50)
	@Column(name = "sched_name", nullable = true, length = 50)
	private String schedName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "sched_desc", nullable = true, length = 50)
	private String schedDesc;

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

	/**
	 * 
	 * @generated
	 */
	@Column(name = "freq_relative_interval", nullable = true, length = 255)
	private Integer freqRelativeInterval;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "freq_sub_interval", nullable = true, length = 255)
	private Integer freqSubInterval;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "freq_sub_interval_type", nullable = true, length = 255)
	private Integer freqSubIntervalType;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "recur_factor", nullable = true, length = 255)
	private Integer recurFactor;

	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_freq_id", referencedColumnName = "rpt_freq_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TRptFreq tRptFreq;*/
	
	@Column(name = "rpt_freq_id", nullable = true, length = 255)
	private Integer rptFreqId;

	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumn(name = "rpt_config_id", referencedColumnName = "rpt_config_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@NotAudited
	private TRptConfig tRptConfig;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptSched")
	@NotAudited
	private Set<TRptSchedExecution> tRptSchedExecutionss;

	/**
	 *
	 * @generated
	 */
	public TRptSched() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRptSchedId(final Integer tRptSchedId) {
		this.tRptSchedId = tRptSchedId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getTRptSchedId() {
		return this.tRptSchedId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFreqInterval(final Integer freqInterval) {
		this.freqInterval = freqInterval;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFreqInterval() {
		return this.freqInterval;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStartDt(final Date startDt) {
		if (startDt != null) {
			this.startDt = (Date) startDt.clone();
		} else {
			this.startDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getStartDt() {
		if (this.startDt != null) {
			return (Date) this.startDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEndDt(final Date endDt) {
		if (endDt != null) {
			this.endDt = (Date) endDt.clone();
		} else {
			this.endDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEndDt() {
		if (this.endDt != null) {
			return (Date) this.endDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setStartTm(final Date startTm) {
		if (startTm != null) {
			this.startTm = (Date) startTm.clone();
		} else {
			this.startTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getStartTm() {
		if (this.startTm != null) {
			return (Date) this.startTm.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEndTm(final Date endTm) {
		if (endTm != null) {
			this.endTm = (Date) endTm.clone();
		} else {
			this.endTm = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEndTm() {
		if (this.endTm != null) {
			return (Date) this.endTm.clone();
		} else {
			return null;
		}
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

	public void setSchedName(final String schedName) {
		this.schedName = schedName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSchedName() {
		return this.schedName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSchedDesc(final String schedDesc) {
		this.schedDesc = schedDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSchedDesc() {
		return this.schedDesc;
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

	public void setFreqRelativeInterval(final Integer freqRelativeInterval) {
		this.freqRelativeInterval = freqRelativeInterval;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFreqRelativeInterval() {
		return this.freqRelativeInterval;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFreqSubInterval(final Integer freqSubInterval) {
		this.freqSubInterval = freqSubInterval;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFreqSubInterval() {
		return this.freqSubInterval;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFreqSubIntervalType(final Integer freqSubIntervalType) {
		this.freqSubIntervalType = freqSubIntervalType;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getFreqSubIntervalType() {
		return this.freqSubIntervalType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRecurFactor(final Integer recurFactor) {
		this.recurFactor = recurFactor;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRecurFactor() {
		return this.recurFactor;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TRptFreq getTRptFreq() {
		return this.tRptFreq;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptFreq(final TRptFreq tRptFreq) {
		this.tRptFreq = tRptFreq;

	}*/

	/**
	 * 
	 * @generated
	 */
	public TRptConfig getTRptConfig() {
		return this.tRptConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfig(final TRptConfig tRptConfig) {
		this.tRptConfig = tRptConfig;
		/*if (this.tRptConfig != null && this.tRptConfig.getRptConfigId() != null) {

			this.tRptSchedId.setRptConfigId(this.tRptConfig.getRptConfigId());

		}*/

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

	public Integer getRptFreqId() {
		return rptFreqId;
	}

	public void setRptFreqId(Integer rptFreqId) {
		this.rptFreqId = rptFreqId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptSched that) {
		setTRptSchedId(that.getTRptSchedId());
		setFreqInterval(that.getFreqInterval());
		setStartDt(that.getStartDt());
		setEndDt(that.getEndDt());
		setStartTm(that.getStartTm());
		setEndTm(that.getEndTm());
		setActiveFlag(that.getActiveFlag());
		setSchedName(that.getSchedName());
		setSchedDesc(that.getSchedDesc());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
		setFreqRelativeInterval(that.getFreqRelativeInterval());
		setFreqSubInterval(that.getFreqSubInterval());
		setFreqSubIntervalType(that.getFreqSubIntervalType());
		setRecurFactor(that.getRecurFactor());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tRptSchedId == null) ? 0 : tRptSchedId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRptSchedId=[").append(tRptSchedId).append("] ");
		buffer.append("freqInterval=[").append(freqInterval).append("] ");
		buffer.append("startDt=[").append(startDt).append("] ");
		buffer.append("endDt=[").append(endDt).append("] ");
		buffer.append("startTm=[").append(startTm).append("] ");
		buffer.append("endTm=[").append(endTm).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("schedName=[").append(schedName).append("] ");
		buffer.append("schedDesc=[").append(schedDesc).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("freqRelativeInterval=[").append(freqRelativeInterval).append("] ");
		buffer.append("freqSubInterval=[").append(freqSubInterval).append("] ");
		buffer.append("freqSubIntervalType=[").append(freqSubIntervalType).append("] ");
		buffer.append("recurFactor=[").append(recurFactor).append("] ");

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
		final TRptSched other = (TRptSched) obj;
		if (tRptSchedId == null) {
			if (other.tRptSchedId != null) {
				return false;
			}
		} else if (!tRptSchedId.equals(other.tRptSchedId)) {
			return false;
		}
		return true;
	}
}
