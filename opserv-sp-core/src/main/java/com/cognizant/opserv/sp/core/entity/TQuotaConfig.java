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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TQuotaConfig 
 * The corresponding mapping table is t_quota_config 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTQuotaConfigs", query = "select myTQuotaConfig from TQuotaConfig myTQuotaConfig"),
		@NamedQuery(name = "CountTQuotaConfigs", query = "Select Count(c) from TQuotaConfig c") ,
		@NamedQuery(name = "FindAllTQuotaConfigNames", query = "select myTQuotaConfig.quotaName from TQuotaConfig myTQuotaConfig where myTQuotaConfig.tenantId=?1"),
		@NamedQuery(name = "FindAllTQuotaConfigNamesExceptGivenId", query = "select myTQuotaConfig.quotaName from TQuotaConfig myTQuotaConfig where myTQuotaConfig.tenantId=?1 and myTQuotaConfig.confId<>?2"),
		@NamedQuery(name = "FindAllTQuotaConfigsForALBUST", query = "select myTQuotaConfig from TQuotaConfig myTQuotaConfig where myTQuotaConfig.algmntId=?1 and myTQuotaConfig.bussUnitId=?2 and myTQuotaConfig.salesTeamId=?3 "),
		@NamedQuery(name = "FindAllTQuotaConfigsForALBUSTExceptGivenConfId", query = "select myTQuotaConfig from TQuotaConfig myTQuotaConfig where myTQuotaConfig.algmntId=?1 and myTQuotaConfig.bussUnitId=?2 and myTQuotaConfig.salesTeamId=?3 and myTQuotaConfig.confId<>?4 "),
		@NamedQuery(name = "FindQuotaType", query = "select myTQuotaConfig.quotaType from TQuotaConfig myTQuotaConfig where myTQuotaConfig.confId =?1")})
@Table(name = "t_quota_config", uniqueConstraints = @UniqueConstraint(columnNames = { "conf_id" }))
public class TQuotaConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "conf_id", nullable = false, length = 255)
	private Long confId;
	
	@Length(max = 50)
	@Column(name = "quota_name", nullable = false, length = 50)
	private String quotaName;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "quota_type", nullable = true, length = 45)
	private String quotaType;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "refinement_type", nullable = true, length = 45)
	private String refinementType;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "rolldown_opt", nullable = true, length = 45)
	private String rolldownOpt;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 45)
	@Column(name = "threshold_type", nullable = true, length = 45)
	private String thresholdType;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "threshold_value", nullable = true, length = 255)
	private Integer thresholdValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "month_multi_factor", nullable = true, length = 255)
	private Integer monthMultiFactor;

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
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "quota_start_date", nullable = true, length = 19)
	private Date quotaStartDate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "quota_end_date", nullable = true, length = 19)
	private Date quotaEndDate;
	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "refinemnt_strt_dt", nullable = true, length = 19)
	private Date refinementStartDate;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "refinemnt_end_dt", nullable = true, length = 19)
	private Date refinementEndDate;
	
	
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;
	
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="tQuotaConfig")
	@Fetch(FetchMode.SUBSELECT)
	private Set<TQuotaHierConf> tQuotaHierConfs;

	/**
	 *
	 * @generated
	 */
	public TQuotaConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setConfId(final Long confId) {
		this.confId = confId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getConfId() {
		return this.confId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamId() {
		return this.salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setQuotaType(final String quotaType) {
		this.quotaType = quotaType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getQuotaType() {
		return this.quotaType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRefinementType(final String refinementType) {
		this.refinementType = refinementType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRefinementType() {
		return this.refinementType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRolldownOpt(final String rolldownOpt) {
		this.rolldownOpt = rolldownOpt;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRolldownOpt() {
		return this.rolldownOpt;
	}

	/**
	 * 
	 * @generated
	 */

	public void setThresholdType(final String thresholdType) {
		this.thresholdType = thresholdType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getThresholdType() {
		return this.thresholdType;
	}

	/**
	 * 
	 * @generated
	 */

	public void setThresholdValue(final Integer thresholdValue) {
		this.thresholdValue = thresholdValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getThresholdValue() {
		return this.thresholdValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMonthMultiFactor(final Integer monthMultiFactor) {
		this.monthMultiFactor = monthMultiFactor;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMonthMultiFactor() {
		return this.monthMultiFactor;
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

	public String getQuotaName() {
		return quotaName;
	}

	public void setQuotaName(String quotaName) {
		this.quotaName = quotaName;
	}

	public Date getQuotaStartDate() {
		return quotaStartDate;
	}

	public void setQuotaStartDate(Date quotaStartDate) {
		this.quotaStartDate = quotaStartDate;
	}

	public Date getQuotaEndDate() {
		return quotaEndDate;
	}

	public void setQuotaEndDate(Date quotaEndDate) {
		this.quotaEndDate = quotaEndDate;
	}

	public Date getRefinementStartDate() {
		return refinementStartDate;
	}

	public void setRefinementStartDate(Date refinementStartDate) {
		this.refinementStartDate = refinementStartDate;
	}

	public Date getRefinementEndDate() {
		return refinementEndDate;
	}

	public void setRefinementEndDate(Date refinementEndDate) {
		this.refinementEndDate = refinementEndDate;
	}

	
	public Short getTenantId() {
		return tenantId;
	}

	public void setTenantId(Short tenantId) {
		this.tenantId = tenantId;
	}

	public Set<TQuotaHierConf> getTQuotaHierConfs() {
		return tQuotaHierConfs;
	}

	public void setTQuotaHierConfs(Set<TQuotaHierConf> tQuotaHierConfs) {
		this.tQuotaHierConfs = tQuotaHierConfs;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TQuotaConfig that) {
		setConfId(that.getConfId());
		setQuotaName(that.getQuotaName());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
		setQuotaType(that.getQuotaType());
		setRefinementType(that.getRefinementType());
		setRolldownOpt(that.getRolldownOpt());
		setThresholdType(that.getThresholdType());
		setThresholdValue(that.getThresholdValue());
		setMonthMultiFactor(that.getMonthMultiFactor());
		setCreatedBy(that.getCreatedBy());
		setCreatedDt(that.getCreatedDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdatedDt(that.getUpdatedDt());
		setQuotaStartDate(that.getQuotaStartDate());
		setQuotaEndDate(that.getQuotaEndDate());
		setRefinementStartDate(that.getRefinementStartDate());
		setRefinementEndDate(that.getRefinementEndDate());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((confId == null) ? 0 : confId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("confId=[").append(confId).append("] ");
		buffer.append("quotaName=[").append(quotaName).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("quotaType=[").append(quotaType).append("] ");
		buffer.append("refinementType=[").append(refinementType).append("] ");
		buffer.append("rolldownOpt=[").append(rolldownOpt).append("] ");
		buffer.append("thresholdType=[").append(thresholdType).append("] ");
		buffer.append("thresholdValue=[").append(thresholdValue).append("] ");
		buffer.append("monthMultiFactor=[").append(monthMultiFactor).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createdDt=[").append(createdDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updatedDt=[").append(updatedDt).append("] ");
		buffer.append("quotaStartDate=[").append(quotaStartDate).append("] ");
		buffer.append("quotaEndDate=[").append(quotaEndDate).append("] ");
		buffer.append("refinementStartDate=[").append(refinementStartDate).append("] ");
		buffer.append("refinementEndDate=[").append(refinementEndDate).append("] ");
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
		final TQuotaConfig other = (TQuotaConfig) obj;
		if (confId == null) {
			if (other.confId != null) {
				return false;
			}
		} else if (!confId.equals(other.confId)) {
			return false;
		}
		return true;
	}
}
