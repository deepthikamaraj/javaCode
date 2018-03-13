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
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRptConfig 
 * The corresponding mapping table is t_rpt_config 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptConfigs", query = "select myTRptConfig from TRptConfig myTRptConfig"),
		@NamedQuery(name = "FindAllTRptConfigById", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.rptConfigId = ?1 and myTRptConfig.tenantId = ?2"),
		@NamedQuery(name = "CountTRptConfigs", query = "Select Count(c) from TRptConfig c"),
		@NamedQuery(name = "FindTRptConfigByTRptCategory", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.rptCategoryId = ?1 "),
		@NamedQuery(name = "CountTRptConfigsByTRptCategory", query = "select Count(myTRptConfig) from TRptConfig myTRptConfig where myTRptConfig.rptCategoryId = ?1 "),
		@NamedQuery(name = "FindTRptConfigByTRptType", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.rptTypeId = ?1 "),
		@NamedQuery(name = "CountTRptConfigsByTRptType", query = "select Count(myTRptConfig) from TRptConfig myTRptConfig where myTRptConfig.rptTypeId = ?1 "),
		@NamedQuery(name = "FindTRptConfigByTRptConfigStatus", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.rptConfigStatusId = ?1 "),
		@NamedQuery(name = "CountTRptConfigsByTRptConfigStatus", query = "select Count(myTRptConfig) from TRptConfig myTRptConfig where myTRptConfig.rptConfigStatusId = ?1 "),
		//added for recipient prefID
		@NamedQuery(name = "FindTRptConfigByTTargetRecipientPref", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "CountTRptConfigsByTTargetRecipientPref", query = "select Count(myTRptConfig) from TRptConfig myTRptConfig where myTRptConfig.tTargetRecipientPref = ?1 "),
		@NamedQuery(name = "FindTRptConfigByTRptTypeAndConfigStatus", query = "select myTRptConfig from TRptConfig myTRptConfig where myTRptConfig.rptTypeId = ?1 and myTRptConfig.rptConfigStatusId = ?2 and myTRptConfig.tenantId = ?3"),})
@Table(name = "t_rpt_config", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_config_id" }))
public class TRptConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_config_id", nullable = false, length = 255)
	private Integer rptConfigId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "rpt_name", nullable = false, length = 75)
	private String rptName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "ack_required", nullable = true, length = 1)
	private Character ackRequired;

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
	
	@ManyToOne
	@JoinColumn(name = "recipient_pref_id", referencedColumnName = "recipient_pref_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TTargetRecipientPref tTargetRecipientPref;
	

	@NotNull
	@Column(name = "rpt_category_id", nullable = false, length = 255)
	private Integer rptCategoryId;

	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_category_id", referencedColumnName = "rpt_category_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRptCategory tRptCategory;*/

	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_type_id", referencedColumnName = "rpt_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRptType tRptType;*/
	
	@NotNull
	@Column(name = "rpt_type_id", nullable = false, length = 255)
	private Integer rptTypeId;

/*	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "rpt_config_status_id", referencedColumnName = "rpt_config_status_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRptConfigStatus tRptConfigStatus;
*/
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "rpt_config_status_id", nullable = false, length = 255)
	private Integer rptConfigStatusId;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfig")
	@NotAudited
	private Set<TRptTargetRecipient> tRptTargetRecipientss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfig")
	@NotAudited 
	private Set<TRptSched> tRptSchedss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfig")
	@NotAudited
	private Set<TRptDataSource> tRptDataSourcess;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptConfig")
	@NotAudited
	private Set<TRptConfigTNoteType> tRptConfigTNoteTypess;

	/**
	 *
	 * @generated
	 */
	public TRptConfig() {
	}

	public Integer getRptConfigStatusId() {
		return rptConfigStatusId;
	}

	public void setRptConfigStatusId(Integer rptConfigStatusId) {
		this.rptConfigStatusId = rptConfigStatusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptConfigId(final Integer rptConfigId) {
		this.rptConfigId = rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRptConfigId() {
		return this.rptConfigId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptName(final String rptName) {
		this.rptName = rptName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptName() {
		return this.rptName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAckRequired(final Character ackRequired) {
		this.ackRequired = ackRequired;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getAckRequired() {
		return this.ackRequired;
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
	
	public Integer getRptCategoryId() {
		return rptCategoryId;
	}

	public void setRptCategoryId(Integer rptCategoryId) {
		this.rptCategoryId = rptCategoryId;
	}

	/**
	 * 
	 * @generated
	 */
	public TTargetRecipientPref getTTargetRecipientPref() {
		return this.tTargetRecipientPref;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTargetRecipientPref(final TTargetRecipientPref tTargetRecipientPref) {
		this.tTargetRecipientPref = tTargetRecipientPref;

	}

	/**
	 * 
	 * @generated
	 */
	/*public TRptCategory getTRptCategory() {
		return this.tRptCategory;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptCategory(final TRptCategory tRptCategory) {
		this.tRptCategory = tRptCategory;

	}*/

	/**
	 * 
	 * @generated
	 */
	/*public TRptType getTRptType() {
		return this.tRptType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptType(final TRptType tRptType) {
		this.tRptType = tRptType;

	}*/

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
	public Set<TRptTargetRecipient> getTRptTargetRecipientss() {
		return this.tRptTargetRecipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptTargetRecipientss(final Set<TRptTargetRecipient> tRptTargetRecipientss) {
		this.tRptTargetRecipientss = tRptTargetRecipientss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptSched> getTRptSchedss() {
		return this.tRptSchedss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptSchedss(final Set<TRptSched> tRptSchedss) {
		this.tRptSchedss = tRptSchedss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptDataSource> getTRptDataSourcess() {
		return this.tRptDataSourcess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptDataSourcess(final Set<TRptDataSource> tRptDataSourcess) {
		this.tRptDataSourcess = tRptDataSourcess;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TRptConfigTNoteType> getTRptConfigTNoteTypess() {
		return this.tRptConfigTNoteTypess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRptConfigTNoteTypess(final Set<TRptConfigTNoteType> tRptConfigTNoteTypess) {
		this.tRptConfigTNoteTypess = tRptConfigTNoteTypess;
	}

	public Integer getRptTypeId() {
		return rptTypeId;
	}

	public void setRptTypeId(Integer rptTypeId) {
		this.rptTypeId = rptTypeId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptConfig that) {
		setRptConfigId(that.getRptConfigId());
		setRptName(that.getRptName());
		setAckRequired(that.getAckRequired());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((rptConfigId == null) ? 0 : rptConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rptConfigId=[").append(rptConfigId).append("] ");
		buffer.append("rptName=[").append(rptName).append("] ");
		buffer.append("ackRequired=[").append(ackRequired).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TRptConfig other = (TRptConfig) obj;
		if (rptConfigId == null) {
			if (other.rptConfigId != null) {
				return false;
			}
		} else if (!rptConfigId.equals(other.rptConfigId)) {
			return false;
		}
		return true;
	}
}
