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

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRptConfigStatus 
 * The corresponding mapping table is t_rpt_config_status 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptConfigStatuss", query = "select myTRptConfigStatus from TRptConfigStatus myTRptConfigStatus"),
		@NamedQuery(name = "CountTRptConfigStatuss", query = "Select Count(c) from TRptConfigStatus c"),
		@NamedQuery(name = "FindTRptConfigStatusByTRptConfigStatus", query = "select myTRptConfigStatus from TRptConfigStatus myTRptConfigStatus where myTRptConfigStatus.tRptConfigStatusId.rptConfigStatusId = ?1 "),
		@NamedQuery(name = "CountTRptConfigStatussByTRptConfigStatus", query = "select Count(myTRptConfigStatus) from TRptConfigStatus myTRptConfigStatus where myTRptConfigStatus.tRptConfigStatusId.rptConfigStatusId = ?1 ") })
@Table(name = "t_rpt_config_status", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_config_status_id" }))
public class TRptConfigStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_config_status_id", nullable = false, length = 255)
	private Integer rptConfigStatusId;*/

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRptConfigStatusId tRptConfigStatusId;
	
	
	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "rpt_config_status_name", nullable = false, length = 30)
	private String rptConfigStatusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "rpt_config_status_desc", nullable = true, length = 50)
	private String rptConfigStatusDesc;

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

	/*@ManyToOne
	@JoinColumn(name = "rpt_config_status_id", referencedColumnName = "rpt_config_status_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRptConfigStatus tRptConfigStatus;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfigStatus")
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private Set<TRptConfigStatus> tRptConfigStatusess;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfigStatus")
	private Set<TRptTUsrRptPref> tRptTUsrRptPrefss;
*/
/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfigStatus")
	@NotAudited
	private Set<TRptConfig> tRptConfigss;
*/
	/**
	 *
	 * @generated
	 */
	public TRptConfigStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	public TRptConfigStatusId gettRptConfigStatusId() {
		return tRptConfigStatusId;
	}

	public void settRptConfigStatusId(TRptConfigStatusId tRptConfigStatusId) {
		this.tRptConfigStatusId = tRptConfigStatusId;
	}

	/*public void setRptConfigStatusId(final Integer rptConfigStatusId) {
		this.rptConfigStatusId = rptConfigStatusId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getRptConfigStatusId() {
		return this.rptConfigStatusId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setRptConfigStatusName(final String rptConfigStatusName) {
		this.rptConfigStatusName = rptConfigStatusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptConfigStatusName() {
		return this.rptConfigStatusName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptConfigStatusDesc(final String rptConfigStatusDesc) {
		this.rptConfigStatusDesc = rptConfigStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptConfigStatusDesc() {
		return this.rptConfigStatusDesc;
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

	@Override
	public String toString() {
		return "TRptConfigStatus [tRptConfigStatusId=" + tRptConfigStatusId
				+ ", rptConfigStatusName=" + rptConfigStatusName
				+ ", rptConfigStatusDesc=" + rptConfigStatusDesc
				+ ", activeFlag=" + activeFlag + ", createdBy=" + createdBy
				+ ", createDt=" + createDt + ", updatedBy=" + updatedBy
				+ ", updateDate=" + updateDate + ", tenantId=" + tenantId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((tRptConfigStatusId == null) ? 0 : tRptConfigStatusId
						.hashCode());
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
		TRptConfigStatus other = (TRptConfigStatus) obj;
		if (tRptConfigStatusId == null) {
			if (other.tRptConfigStatusId != null)
				return false;
		} else if (!tRptConfigStatusId.equals(other.tRptConfigStatusId))
			return false;
		return true;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TRptConfigStatus getTRptConfigStatus() {
		return this.tRptConfigStatus;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptConfigStatus(final TRptConfigStatus tRptConfigStatus) {
		this.tRptConfigStatus = tRptConfigStatus;

	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TRptConfigStatus> getTRptConfigStatusess() {
		return this.tRptConfigStatusess;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptConfigStatusess(final Set<TRptConfigStatus> tRptConfigStatusess) {
		this.tRptConfigStatusess = tRptConfigStatusess;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TRptTUsrRptPref> getTRptTUsrRptPrefss() {
		return this.tRptTUsrRptPrefss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptTUsrRptPrefss(final Set<TRptTUsrRptPref> tRptTUsrRptPrefss) {
		this.tRptTUsrRptPrefss = tRptTUsrRptPrefss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TRptConfig> getTRptConfigss() {
		return this.tRptConfigss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptConfigss(final Set<TRptConfig> tRptConfigss) {
		this.tRptConfigss = tRptConfigss;
	}*/

	
	

	
}
