package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TValueRange 
 * The corresponding mapping table is t_value_range 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTValueRanges", query = "select myTValueRange from TValueRange myTValueRange"),
		@NamedQuery(name = "CountTValueRanges", query = "Select Count(c) from TValueRange c"),
		@NamedQuery(name = "FindTValueRangeByTRangeConfig", query = "select myTValueRange from TValueRange myTValueRange where myTValueRange.tRangeConfig = ?1 "),
		@NamedQuery(name = "FindTValueRangeById", query = "select myTValueRange from TValueRange myTValueRange where myTValueRange.tValueRangeId.rangeConfigId = ?1 "),
		@NamedQuery(name = "FindTValueRangeByIdAndLevel", query = "select myTValueRange from TValueRange myTValueRange where myTValueRange.tValueRangeId.rangeConfigId = ?1 AND myTValueRange.tValueRangeId.levelId = ?2 AND myTValueRange.tenantId = ?3 "),
		@NamedQuery(name = "CountTValueRangesByTRangeConfig", query = "select Count(*) from TValueRange myTValueRange where myTValueRange.tRangeConfig = ?1 ") })
@Table(name = "t_value_range")
public class TValueRange implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TValueRangeId tValueRangeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "color_cd", nullable = false, length = 30)
	private String colorCd;

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

	@ManyToOne
	@JoinColumn(name = "range_config_id", referencedColumnName = "range_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TRangeConfig tRangeConfig;

	/**
	 *
	 * @generated
	 */
	public TValueRange() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTValueRangeId(final TValueRangeId tValueRangeId) {
		this.tValueRangeId = tValueRangeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TValueRangeId getTValueRangeId() {
		return this.tValueRangeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setColorCd(final String colorCd) {
		this.colorCd = colorCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getColorCd() {
		return this.colorCd;
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
	public TRangeConfig getTRangeConfig() {
		return this.tRangeConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRangeConfig(final TRangeConfig tRangeConfig) {
		this.tRangeConfig = tRangeConfig;
		if (this.tRangeConfig != null && this.tRangeConfig.getRangeConfigId() != null) {

			this.tValueRangeId.setRangeConfigId(this.tRangeConfig.getRangeConfigId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TValueRange that) {
		setTValueRangeId(that.getTValueRangeId());
		setColorCd(that.getColorCd());
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
		result = prime * result + ((tValueRangeId == null) ? 0 : tValueRangeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tValueRangeId=[").append(tValueRangeId).append("] ");
		buffer.append("colorCd=[").append(colorCd).append("] ");
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
		final TValueRange other = (TValueRange) obj;
		if (tValueRangeId == null) {
			if (other.tValueRangeId != null) {
				return false;
			}
		} else if (!tValueRangeId.equals(other.tValueRangeId)) {
			return false;
		}
		return true;
	}
}
