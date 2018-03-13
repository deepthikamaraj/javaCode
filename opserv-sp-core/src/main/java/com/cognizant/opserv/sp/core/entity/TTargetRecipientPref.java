package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TTargetRecipientPref 
 * The corresponding mapping table is t_target_recipient_pref 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTTargetRecipientPrefs", query = "select myTTargetRecipientPref from TTargetRecipientPref myTTargetRecipientPref"),
		@NamedQuery(name = "CountTTargetRecipientPrefs", query = "Select Count(c) from TTargetRecipientPref c"),
		@NamedQuery(name = "FindTTargetRecipientPrefByTPers", query = "select myTTargetRecipientPref from TTargetRecipientPref myTTargetRecipientPref where myTTargetRecipientPref.tPers = ?1 "),
		@NamedQuery(name = "CountTTargetRecipientPrefsByTPers", query = "select Count(myTTargetRecipientPref) from TTargetRecipientPref myTTargetRecipientPref where myTTargetRecipientPref.tPers = ?1 "),
		@NamedQuery(name = "FindTTargetRecipientPrefByTPubType", query = "select myTTargetRecipientPref from TTargetRecipientPref myTTargetRecipientPref where myTTargetRecipientPref.tPubType = ?1 "),
		@NamedQuery(name = "FindTTargetRecipientPrefByHistory", query = "select myTTargetRecipientPref from TTargetRecipientPref myTTargetRecipientPref where myTTargetRecipientPref.createdBy=?1 and myTTargetRecipientPref.activeFlag=?2 AND myTTargetRecipientPref.srchCritEndDt >= ?3 " ),
		@NamedQuery(name = "CountTTargetRecipientPrefsByTPubType", query = "select Count(myTTargetRecipientPref) from TTargetRecipientPref myTTargetRecipientPref where myTTargetRecipientPref.tPubType = ?1 ") })
@Table(name = "t_target_recipient_pref", uniqueConstraints = @UniqueConstraint(columnNames = { "recipient_pref_id" }))
public class TTargetRecipientPref implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recipient_pref_id", nullable = false, length = 255)
	private Integer recipientPrefId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "srch_pref_name", nullable = true, length = 50)
	private String srchPrefName;
	
	@Column(name = "staff_id", nullable = false, length = 255,insertable=false,updatable=false)
	private Integer staffId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "srch_pref_desc", nullable = true, length = 255)
	private String srchPrefDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "dt_period_id", nullable = true, length = 1)
	private Character dtPeriodId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "srch_crit_start_dt", nullable = true, length = 10)
	private Date srchCritStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "srch_crit_end_dt", nullable = true, length = 10)
	private Date srchCritEndDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "active_flag", nullable = false, length = 255)
	private Integer activeFlag;

	
	@Column(name = "pub_type_id", nullable = false, length = 255,insertable=false,updatable=false)
	private Integer pubTypeId;
	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
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
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tTargetRecipientPref")
	@NotAudited
	private Set<TRptConfig> tRptConfigss;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPers tPers;

	@ManyToOne
	@JoinColumn(name = "pub_type_id", referencedColumnName = "pub_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPubType tPubType;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tTargetRecipientPref")
	@NotAudited
	private Set<TRptPublisher> tRptPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tTargetRecipientPref")
	@NotAudited
	private Set<TCommPublisher> tCommPublisherss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tTargetRecipientPref")
	//@Audited
	@NotAudited
	private Set<TRecipientAttr> tRecipientAttrss;
	
	/**
	 *
	 * @generated
	 */
	public TTargetRecipientPref() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRecipientPrefId(final Integer recipientPrefId) {
		this.recipientPrefId = recipientPrefId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRecipientPrefId() {
		return this.recipientPrefId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSrchPrefName(final String srchPrefName) {
		this.srchPrefName = srchPrefName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSrchPrefName() {
		return this.srchPrefName;
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setSrchPrefDesc(final Integer srchPrefDesc) {
		this.srchPrefDesc = srchPrefDesc;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getSrchPrefDesc() {
		return this.srchPrefDesc;
	}
*/
	/**
	 * 
	 * @generated
	 */

	/*public void setDtPeriodId(final Character dtPeriodId) {
		this.dtPeriodId = dtPeriodId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Character getDtPeriodId() {
		return this.dtPeriodId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setSrchCritStartDt(final Date srchCritStartDt) {
		if (srchCritStartDt != null) {
			this.srchCritStartDt = (Date) srchCritStartDt.clone();
		} else {
			this.srchCritStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSrchCritStartDt() {
		if (this.srchCritStartDt != null) {
			return (Date) this.srchCritStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setSrchCritEndDt(final Date srchCritEndDt) {
		if (srchCritEndDt != null) {
			this.srchCritEndDt = (Date) srchCritEndDt.clone();
		} else {
			this.srchCritEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getSrchCritEndDt() {
		if (this.srchCritEndDt != null) {
			return (Date) this.srchCritEndDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setActiveFlag(final Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getActiveFlag() {
		
		return this.activeFlag;
	}*/

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
	 * 
	 * @generated
	 */
	public Set<TRptConfig> getTRptConfigss() {
		return this.tRptConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfigss(final Set<TRptConfig> tRptConfigss) {
		this.tRptConfigss = tRptConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public TPers getTPers() {
		return this.tPers;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;

	}

	/**
	 * 
	 * @generated
	 */
	public TPubType getTPubType() {
		return this.tPubType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPubType(final TPubType tPubType) {
		this.tPubType = tPubType;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptPublisher> getTRptPublisherss() {
		return this.tRptPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptPublisherss(final Set<TRptPublisher> tRptPublisherss) {
		this.tRptPublisherss = tRptPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCommPublisher> getTCommPublisherss() {
		return this.tCommPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommPublisherss(final Set<TCommPublisher> tCommPublisherss) {
		this.tCommPublisherss = tCommPublisherss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRecipientAttr> getTRecipientAttrss() {
		return this.tRecipientAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRecipientAttrss(final Set<TRecipientAttr> tRecipientAttrss) {
		this.tRecipientAttrss = tRecipientAttrss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TTargetRecipientPref that) {
		setRecipientPrefId(that.getRecipientPrefId());
		setSrchPrefName(that.getSrchPrefName());
		//setSrchPrefDesc(that.getSrchPrefDesc());
		//setDtPeriodId(that.getDtPeriodId());
		setSrchCritStartDt(that.getSrchCritStartDt());
		setSrchCritEndDt(that.getSrchCritEndDt());
	//	setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((recipientPrefId == null) ? 0 : recipientPrefId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("recipientPrefId=[").append(recipientPrefId).append("] ");
		buffer.append("srchPrefName=[").append(srchPrefName).append("] ");
		buffer.append("srchPrefDesc=[").append(getSrchPrefDesc()).append("] ");
		buffer.append("dtPeriodId=[").append(getDtPeriodId()).append("] ");
		buffer.append("srchCritStartDt=[").append(srchCritStartDt).append("] ");
		buffer.append("srchCritEndDt=[").append(srchCritEndDt).append("] ");
		buffer.append("activeFlag=[").append(getActiveFlag()).append("] ");
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
		final TTargetRecipientPref other = (TTargetRecipientPref) obj;
		if (recipientPrefId == null) {
			if (other.recipientPrefId != null) {
				return false;
			}
		} else if (!recipientPrefId.equals(other.recipientPrefId)) {
			return false;
		}
		return true;
	}

	public Character getDtPeriodId() {
		return dtPeriodId;
	}

	public void setDtPeriodId(Character dtPeriodId) {
		this.dtPeriodId = dtPeriodId;
	}

	

	public String getSrchPrefDesc() {
		return srchPrefDesc;
	}

	public void setSrchPrefDesc(String srchPrefDesc) {
		this.srchPrefDesc = srchPrefDesc;
	}

	public Integer getPubTypeId() {
		return pubTypeId;
	}

	public void setPubTypeId(Integer pubTypeId) {
		this.pubTypeId = pubTypeId;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
}
