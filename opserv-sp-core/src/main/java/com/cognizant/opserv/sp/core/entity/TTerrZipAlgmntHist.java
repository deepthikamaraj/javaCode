package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TTerrZipAlgmntHist 
 * The corresponding mapping table is t_terr_zip_algmnt_hist 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTTerrZipAlgmntHists", query = "select myTTerrZipAlgmntHist from TTerrZipAlgmntHist myTTerrZipAlgmntHist"),
		@NamedQuery(name = "CountTTerrZipAlgmntHists", query = "Select Count(c) from TTerrZipAlgmntHist c") })
@Table(name = "t_terr_zip_algmnt_hist")
public class TTerrZipAlgmntHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TTerrZipAlgmntHistId tTerrZipAlgmntHistId;

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
	@Column(name = "assigned_flag", nullable = true, length = 1)
	private Character assignedFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "point_zip_flag", nullable = true, length = 1)
	private Character pointZipFlag;

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
	@Column(name = "sales_hier_id", nullable = true, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "postal_cd", nullable = true, length = 20)
	private String postalCd;

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
	@Column(name = "sales_pos_id", nullable = true, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "geo_id", nullable = true, length = 255)
	private Integer geoId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "rev_type", nullable = true, length = 20)
	private String revType;

	/**
	 *
	 * @generated
	 */
	public TTerrZipAlgmntHist() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTTerrZipAlgmntHistId(final TTerrZipAlgmntHistId tTerrZipAlgmntHistId) {
		this.tTerrZipAlgmntHistId = tTerrZipAlgmntHistId;
	}

	/**
	 * 
	 * @generated
	 */
	public TTerrZipAlgmntHistId getTTerrZipAlgmntHistId() {
		return this.tTerrZipAlgmntHistId;
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

	public void setAssignedFlag(final Character assignedFlag) {
		this.assignedFlag = assignedFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getAssignedFlag() {
		return this.assignedFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPointZipFlag(final Character pointZipFlag) {
		this.pointZipFlag = pointZipFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPointZipFlag() {
		return this.pointZipFlag;
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

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPostalCd(final String postalCd) {
		this.postalCd = postalCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCd() {
		return this.postalCd;
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

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoId(final Integer geoId) {
		this.geoId = geoId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoId() {
		return this.geoId;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TTerrZipAlgmntHist that) {
		setTTerrZipAlgmntHistId(that.getTTerrZipAlgmntHistId());
		setTimestamp(that.getTimestamp());
		setAssignedFlag(that.getAssignedFlag());
		setPointZipFlag(that.getPointZipFlag());
		setActiveFlag(that.getActiveFlag());
		setSalesHierId(that.getSalesHierId());
		setPostalCd(that.getPostalCd());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setSalesPosId(that.getSalesPosId());
		setGeoId(that.getGeoId());
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
		result = prime * result + ((tTerrZipAlgmntHistId == null) ? 0 : tTerrZipAlgmntHistId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tTerrZipAlgmntHistId=[").append(tTerrZipAlgmntHistId).append("] ");
		buffer.append("timestamp=[").append(timestamp).append("] ");
		buffer.append("assignedFlag=[").append(assignedFlag).append("] ");
		buffer.append("pointZipFlag=[").append(pointZipFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("geoId=[").append(geoId).append("] ");
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
		final TTerrZipAlgmntHist other = (TTerrZipAlgmntHist) obj;
		if (tTerrZipAlgmntHistId == null) {
			if (other.tTerrZipAlgmntHistId != null) {
				return false;
			}
		} else if (!tTerrZipAlgmntHistId.equals(other.tTerrZipAlgmntHistId)) {
			return false;
		}
		return true;
	}
}
