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
 * JPA class representing TRptFreq 
 * The corresponding mapping table is t_rpt_freq 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTRptFreqs", query = "select myTRptFreq from TRptFreq myTRptFreq"),
		@NamedQuery(name = "CountTRptFreqs", query = "Select Count(c) from TRptFreq c") })
@Table(name = "t_rpt_freq", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_freq_id" }))
public class TRptFreq implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
/*	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_freq_id", nullable = false, length = 255)
	private Integer rptFreqId;*/
	@EmbeddedId
	@Valid
	private TRptFreqId tRptFreqId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "freq_type", nullable = false, length = 20)
	private String freqType;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 1)
	private Character activeFlag;

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

/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptFreq")
	private Set<TRptSched> tRptSchedss;
*/
	/**
	 *
	 * @generated
	 */
	public TRptFreq() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setRptFreqId(final Integer rptFreqId) {
		this.rptFreqId = rptFreqId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getRptFreqId() {
		return this.rptFreqId;
	}*/

	public TRptFreqId gettRptFreqId() {
		return tRptFreqId;
	}

	public void settRptFreqId(TRptFreqId tRptFreqId) {
		this.tRptFreqId = tRptFreqId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setFreqType(final String freqType) {
		this.freqType = freqType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getFreqType() {
		return this.freqType;
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
	/*public Set<TRptSched> getTRptSchedss() {
		return this.tRptSchedss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptSchedss(final Set<TRptSched> tRptSchedss) {
		this.tRptSchedss = tRptSchedss;
	}*/

	@Override
	public String toString() {
		return "TRptFreq [tRptFreqId=" + tRptFreqId + ", freqType=" + freqType
				+ ", activeFlag=" + activeFlag + ", createdBy=" + createdBy
				+ ", createDt=" + createDt + ", updatedBy=" + updatedBy
				+ ", updateDate=" + updateDate + ", tenantId=" + tenantId +"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tRptFreqId == null) ? 0 : tRptFreqId.hashCode());
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
		TRptFreq other = (TRptFreq) obj;
		if (tRptFreqId == null) {
			if (other.tRptFreqId != null)
				return false;
		} else if (!tRptFreqId.equals(other.tRptFreqId))
			return false;
		return true;
	}

	
}
