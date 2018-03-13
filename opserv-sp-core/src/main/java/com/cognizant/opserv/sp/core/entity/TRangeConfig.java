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

/** 
 * JPA class representing TRangeConfig 
 * The corresponding mapping table is t_range_config 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRangeConfigs", query = "select myTRangeConfig from TRangeConfig myTRangeConfig"),
		@NamedQuery(name = "FindTRangeConfigByLimits", query = "select myTRangeConfig from TRangeConfig myTRangeConfig where myTRangeConfig.lowLim = ?1 AND myTRangeConfig.upLim = ?2 AND myTRangeConfig.tenantId = ?3"),
		@NamedQuery(name = "FindTRangeConfigByTenant", query = "select myTRangeConfig from TRangeConfig myTRangeConfig where myTRangeConfig.lowLim is NULL AND myTRangeConfig.upLim is NULL AND myTRangeConfig.tenantId = ?1"),
		@NamedQuery(name = "FindTRangeConfig", query = "select myTRangeConfig from TRangeConfig myTRangeConfig where myTRangeConfig.tenantId = ?1"),
		@NamedQuery(name = "CountTRangeConfigs", query = "Select Count(c) from TRangeConfig c") })
@Table(name = "t_range_config", uniqueConstraints = @UniqueConstraint(columnNames = { "range_config_id" }))
public class TRangeConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "range_config_id", nullable = false, length = 255)
	private Integer rangeConfigId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "low_lim", nullable = false, length = 255)
	private Integer lowLim;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "up_lim", nullable = false, length = 255)
	private Integer upLim;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "num_level", nullable = false, length = 255)
	private Integer numLevel;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRangeConfig")
	private Set<TValueRange> tValueRangess;

	/**
	 *
	 * @generated
	 */
	public TRangeConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRangeConfigId(final Integer rangeConfigId) {
		this.rangeConfigId = rangeConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRangeConfigId() {
		return this.rangeConfigId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLowLim(final Integer lowLim) {
		this.lowLim = lowLim;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getLowLim() {
		return this.lowLim;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUpLim(final Integer upLim) {
		this.upLim = upLim;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getUpLim() {
		return this.upLim;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNumLevel(final Integer numLevel) {
		this.numLevel = numLevel;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getNumLevel() {
		return this.numLevel;
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
	public Set<TValueRange> getTValueRangess() {
		return this.tValueRangess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTValueRangess(final Set<TValueRange> tValueRangess) {
		this.tValueRangess = tValueRangess;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRangeConfig that) {
		setRangeConfigId(that.getRangeConfigId());
		setLowLim(that.getLowLim());
		setUpLim(that.getUpLim());
		setNumLevel(that.getNumLevel());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((rangeConfigId == null) ? 0 : rangeConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("rangeConfigId=[").append(rangeConfigId).append("] ");
		buffer.append("lowLim=[").append(lowLim).append("] ");
		buffer.append("upLim=[").append(upLim).append("] ");
		buffer.append("numLevel=[").append(numLevel).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TRangeConfig other = (TRangeConfig) obj;
		if (rangeConfigId == null) {
			if (other.rangeConfigId != null) {
				return false;
			}
		} else if (!rangeConfigId.equals(other.rangeConfigId)) {
			return false;
		}
		return true;
	}
}
