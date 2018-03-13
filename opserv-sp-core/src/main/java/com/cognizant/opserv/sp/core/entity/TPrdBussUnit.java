package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TPrdBussUnit 
 * The corresponding mapping table is t_prd_buss_unit 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdBussUnits", query = "select myTPrdBussUnit from TPrdBussUnit myTPrdBussUnit"),
		@NamedQuery(name = "CountTPrdBussUnits", query = "Select Count(c) from TPrdBussUnit c"),
		@NamedQuery(name = "FindTPrdBussUnitByTPrdConfig", query = "select myTPrdBussUnit from TPrdBussUnit myTPrdBussUnit where myTPrdBussUnit.tPrdConfig = ?1 "),
		@NamedQuery(name = "CountTPrdBussUnitsByTPrdConfig", query = "select Count(*) from TPrdBussUnit myTPrdBussUnit where myTPrdBussUnit.tPrdConfig = ?1 "),
		@NamedQuery(name = "FindTPrdBussUnitByTBussUnit", query = "select myTPrdBussUnit from TPrdBussUnit myTPrdBussUnit where myTPrdBussUnit.tBussUnit = ?1 "),
		@NamedQuery(name = "CountTPrdBussUnitsByTBussUnit", query = "select Count(*) from TPrdBussUnit myTPrdBussUnit where myTPrdBussUnit.tBussUnit = ?1 ") })
@Table(name = "t_prd_buss_unit")
public class TPrdBussUnit implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrdBussUnitId tPrdBussUnitId;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prd_config_id", referencedColumnName = "prd_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdConfig tPrdConfig;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussUnit tBussUnit;

	/**
	 *
	 * @generated
	 */
	public TPrdBussUnit() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrdBussUnitId(final TPrdBussUnitId tPrdBussUnitId) {
		this.tPrdBussUnitId = tPrdBussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdBussUnitId getTPrdBussUnitId() {
		return this.tPrdBussUnitId;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
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
	public TPrdConfig getTPrdConfig() {
		return this.tPrdConfig;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdConfig(final TPrdConfig tPrdConfig) {
		this.tPrdConfig = tPrdConfig;
		if (this.tPrdConfig != null && this.tPrdConfig.getPrdConfigId() != null) {

			this.tPrdBussUnitId.setPrdConfigId(this.tPrdConfig.getPrdConfigId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TBussUnit getTBussUnit() {
		return this.tBussUnit;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussUnit(final TBussUnit tBussUnit) {
		this.tBussUnit = tBussUnit;
		if (this.tBussUnit != null && this.tBussUnit.getBussUnitId() != null) {

			this.tPrdBussUnitId.setBussUnitId(this.tBussUnit.getBussUnitId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdBussUnit that) {
		setTPrdBussUnitId(that.getTPrdBussUnitId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((tPrdBussUnitId == null) ? 0 : tPrdBussUnitId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrdBussUnitId=[").append(tPrdBussUnitId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TPrdBussUnit other = (TPrdBussUnit) obj;
		if (tPrdBussUnitId == null) {
			if (other.tPrdBussUnitId != null) {
				return false;
			}
		} else if (!tPrdBussUnitId.equals(other.tPrdBussUnitId)) {
			return false;
		}
		return true;
	}
}
