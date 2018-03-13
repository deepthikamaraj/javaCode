package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TSalesPosGeoUnitHist 
 * The corresponding mapping table is t_sales_pos_geo_unit_hist 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosGeoUnitHists", query = "select myTSalesPosGeoUnitHist from TSalesPosGeoUnitHist myTSalesPosGeoUnitHist"),
		@NamedQuery(name = "CountTSalesPosGeoUnitHists", query = "Select Count(c) from TSalesPosGeoUnitHist c"),
		@NamedQuery(name = "FindTSalesPosGeoUnitHistByTSalesPosGeoUnit", query = "select myTSalesPosGeoUnitHist from TSalesPosGeoUnitHist myTSalesPosGeoUnitHist where myTSalesPosGeoUnitHist.tSalesPosGeoUnit = ?1 "),
		@NamedQuery(name = "CountTSalesPosGeoUnitHistsByTSalesPosGeoUnit", query = "select Count(*) from TSalesPosGeoUnitHist myTSalesPosGeoUnitHist where myTSalesPosGeoUnitHist.tSalesPosGeoUnit = ?1 ") })
@Table(name = "t_sales_pos_geo_unit_hist")
public class TSalesPosGeoUnitHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "timestamp", nullable = true, length = 19)
	private Date timestamp;

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

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "rev_type", nullable = true, length = 20)
	private String revType;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "geo_id", referencedColumnName = "geo_id", nullable = false, insertable = false, updatable = false) })
	@Valid
	private TSalesPosGeoUnit tSalesPosGeoUnit;

	/**
	 *
	 * @generated
	 */
	public TSalesPosGeoUnitHist() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesPosGeoUnitHistId(final TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId) {
		this.tSalesPosGeoUnitHistId = tSalesPosGeoUnitHistId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosGeoUnitHistId getTSalesPosGeoUnitHistId() {
		return this.tSalesPosGeoUnitHistId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTimestamp(final Date timestamp) {
		if (timestamp != null) {
			this.timestamp = (Date) timestamp.clone();
		} else {
			this.timestamp = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getTimestamp() {
		if (this.timestamp != null) {
			return (Date) this.timestamp.clone();
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

	public void setRevType(final String revType) {
		this.revType = revType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRevType() {
		return this.revType;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosGeoUnit getTSalesPosGeoUnit() {
		return this.tSalesPosGeoUnit;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosGeoUnit(final TSalesPosGeoUnit tSalesPosGeoUnit) {
		this.tSalesPosGeoUnit = tSalesPosGeoUnit;
		if (this.tSalesPosGeoUnit != null && this.tSalesPosGeoUnit.getTSalesPosGeoUnitId() != null) {

			this.tSalesPosGeoUnitHistId.setSalesPosId(this.tSalesPosGeoUnit.getTSalesPosGeoUnitId().getSalesPosId());

		}
		if (this.tSalesPosGeoUnit != null && this.tSalesPosGeoUnit.getTSalesPosGeoUnitId() != null) {

			this.tSalesPosGeoUnitHistId.setGeoId(this.tSalesPosGeoUnit.getTSalesPosGeoUnitId().getGeoId());

		}
		if (this.tSalesPosGeoUnit != null && this.tSalesPosGeoUnit.getTSalesPosGeoUnitId() != null) {

			this.tSalesPosGeoUnitHistId.setSalesHierId(this.tSalesPosGeoUnit.getTSalesPosGeoUnitId().getSalesHierId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosGeoUnitHist that) {
		setTSalesPosGeoUnitHistId(that.getTSalesPosGeoUnitHistId());
		setTimestamp(that.getTimestamp());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setRevType(that.getRevType());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tSalesPosGeoUnitHistId == null) ? 0 : tSalesPosGeoUnitHistId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesPosGeoUnitHistId=[").append(tSalesPosGeoUnitHistId).append("] ");
		buffer.append("timestamp=[").append(timestamp).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("revType=[").append(revType).append("] ");

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
		final TSalesPosGeoUnitHist other = (TSalesPosGeoUnitHist) obj;
		if (tSalesPosGeoUnitHistId == null) {
			if (other.tSalesPosGeoUnitHistId != null) {
				return false;
			}
		} else if (!tSalesPosGeoUnitHistId.equals(other.tSalesPosGeoUnitHistId)) {
			return false;
		}
		return true;
	}
}
