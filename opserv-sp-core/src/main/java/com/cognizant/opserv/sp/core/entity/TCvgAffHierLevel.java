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
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TCvgAffHierLevel 
 * The corresponding mapping table is t_cvg_aff_hier_level 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgAffHierLevels", query = "select myTCvgAffHierLevel from TCvgAffHierLevel myTCvgAffHierLevel"),
		@NamedQuery(name = "FetchTCvgAffHierLevelsByTntIdLocaleId", query = "select myTCvgAffHierLevel from TCvgAffHierLevel myTCvgAffHierLevel where myTCvgAffHierLevel.tenantId=?1 AND tCvgAffHierLevelId.localeId=?2 AND myTCvgAffHierLevel.activeFlag='Y'"),
		@NamedQuery(name = "CountTCvgAffHierLevels", query = "Select Count(c) from TCvgAffHierLevel c") })
@Table(name = "t_cvg_aff_hier_level")
public class TCvgAffHierLevel implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCvgAffHierLevelId tCvgAffHierLevelId;
	

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "hier_level_name", nullable = false, length = 20)
	private String hierLevelName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "hier_level_desc", nullable = false, length = 50)
	private String hierLevelDesc;

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

	/**
	 *
	 * @generated
	 */
	public TCvgAffHierLevel() {
	}

	

	/**
	 * 
	 * @generated
	 */

	public void setHierLevelName(final String hierLevelName) {
		this.hierLevelName = hierLevelName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierLevelName() {
		return this.hierLevelName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierLevelDesc(final String hierLevelDesc) {
		this.hierLevelDesc = hierLevelDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierLevelDesc() {
		return this.hierLevelDesc;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgAffHierLevel that) {
		settCvgAffHierLevelId(that.gettCvgAffHierLevelId());
		setHierLevelName(that.getHierLevelName());
		setHierLevelDesc(that.getHierLevelDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activeFlag == null) ? 0 : activeFlag.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((hierLevelDesc == null) ? 0 : hierLevelDesc.hashCode());
		result = prime * result
				+ ((hierLevelName == null) ? 0 : hierLevelName.hashCode());
		result = prime
				* result
				+ ((tCvgAffHierLevelId == null) ? 0 : tCvgAffHierLevelId
						.hashCode());
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result
				+ ((updateDt == null) ? 0 : updateDt.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TCvgAffHierLevel [tCvgAffHierLevelId=" + tCvgAffHierLevelId
				+ ", hierLevelName=" + hierLevelName + ", hierLevelDesc="
				+ hierLevelDesc + ", activeFlag=" + activeFlag + ", createdBy="
				+ createdBy + ", createDt=" + createDt + ", updatedBy="
				+ updatedBy + ", updateDt=" + updateDt + ", tenantId="
				+ tenantId + ", getHierLevelName()=" + getHierLevelName()
				+ ", getHierLevelDesc()=" + getHierLevelDesc()
				+ ", getActiveFlag()=" + getActiveFlag() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreateDt()=" + getCreateDt()
				+ ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdateDt()="
				+ getUpdateDt() + ", getTenantId()=" + getTenantId()
				+ ", hashCode()=" + hashCode() + ", gettCvgAffHierLevelId()="
				+ gettCvgAffHierLevelId() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TCvgAffHierLevel other = (TCvgAffHierLevel) obj;
		if (activeFlag == null) {
			if (other.activeFlag != null)
				return false;
		} else if (!activeFlag.equals(other.activeFlag))
			return false;
		if (createDt == null) {
			if (other.createDt != null)
				return false;
		} else if (!createDt.equals(other.createDt))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (hierLevelDesc == null) {
			if (other.hierLevelDesc != null)
				return false;
		} else if (!hierLevelDesc.equals(other.hierLevelDesc))
			return false;
		if (hierLevelName == null) {
			if (other.hierLevelName != null)
				return false;
		} else if (!hierLevelName.equals(other.hierLevelName))
			return false;
		if (tCvgAffHierLevelId == null) {
			if (other.tCvgAffHierLevelId != null)
				return false;
		} else if (!tCvgAffHierLevelId.equals(other.tCvgAffHierLevelId))
			return false;
		if (tenantId == null) {
			if (other.tenantId != null)
				return false;
		} else if (!tenantId.equals(other.tenantId))
			return false;
		if (updateDt == null) {
			if (other.updateDt != null)
				return false;
		} else if (!updateDt.equals(other.updateDt))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		return true;
	}



	/**
	 * @return the tCvgAffHierLevelId
	 */
	public TCvgAffHierLevelId gettCvgAffHierLevelId() {
		return tCvgAffHierLevelId;
	}



	/**
	 * @param tCvgAffHierLevelId the tCvgAffHierLevelId to set
	 */
	public void settCvgAffHierLevelId(TCvgAffHierLevelId tCvgAffHierLevelId) {
		this.tCvgAffHierLevelId = tCvgAffHierLevelId;
	}
}
