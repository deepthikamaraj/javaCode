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
 * JPA class representing TCvgAffType 
 * The corresponding mapping table is t_cvg_aff_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCvgAffTypes", query = "select myTCvgAffType from TCvgAffType myTCvgAffType"),
		@NamedQuery(name = "FetchTCvgAffTypesByTenantIdLocalId", query = "select myTCvgAffType from TCvgAffType myTCvgAffType where myTCvgAffType.tenantId=?1 AND tCvgAffTypeId.localeId=?2 AND myTCvgAffType.activeFlag='Y'"),
		@NamedQuery(name = "CountTCvgAffTypes", query = "Select Count(c) from TCvgAffType c") })
@Table(name = "t_cvg_aff_type")
public class TCvgAffType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TCvgAffTypeId tCvgAffTypeId;
	/**
	 * 
	 * @generated
	 *//*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aff_type_id", nullable = false, length = 255)
	private Integer cvgAffTypeId;*/

	/**
	 * @return the tCvgAffTypeId
	 */
	public TCvgAffTypeId gettCvgAffTypeId() {
		return tCvgAffTypeId;
	}

	/**
	 * @param tCvgAffTypeId the tCvgAffTypeId to set
	 */
	public void settCvgAffTypeId(TCvgAffTypeId tCvgAffTypeId) {
		this.tCvgAffTypeId = tCvgAffTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "aff_type_name", nullable = false, length = 20)
	private String affTypeName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "aff_type_desc", nullable = false, length = 75)
	private String affTypeDesc;

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
	public TCvgAffType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffTypeName(final String affTypeName) {
		this.affTypeName = affTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAffTypeName() {
		return this.affTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffTypeDesc(final String affTypeDesc) {
		this.affTypeDesc = affTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAffTypeDesc() {
		return this.affTypeDesc;
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
	 * @return the cvgAffTypeId
	 *//*
	public Integer getCvgAffTypeId() {
		return cvgAffTypeId;
	}

	*//**
	 * @param cvgAffTypeId the cvgAffTypeId to set
	 *//*
	public void setCvgAffTypeId(Integer cvgAffTypeId) {
		this.cvgAffTypeId = cvgAffTypeId;
	}
*/
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCvgAffType that) {
		settCvgAffTypeId(that.gettCvgAffTypeId());
		setAffTypeName(that.getAffTypeName());
		setAffTypeDesc(that.getAffTypeDesc());
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
				+ ((affTypeDesc == null) ? 0 : affTypeDesc.hashCode());
		result = prime * result
				+ ((affTypeName == null) ? 0 : affTypeName.hashCode());
		result = prime * result
				+ ((createDt == null) ? 0 : createDt.hashCode());
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((tCvgAffTypeId == null) ? 0 : tCvgAffTypeId.hashCode());
		result = prime * result
				+ ((tenantId == null) ? 0 : tenantId.hashCode());
		result = prime * result
				+ ((updateDt == null) ? 0 : updateDt.hashCode());
		result = prime * result
				+ ((updatedBy == null) ? 0 : updatedBy.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tCvgAffTypeId=[").append(tCvgAffTypeId).append("] ");
		buffer.append("affTypeName=[").append(affTypeName).append("] ");
		buffer.append("affTypeDesc=[").append(affTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");

		return buffer.toString();
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
		TCvgAffType other = (TCvgAffType) obj;
		if (activeFlag == null) {
			if (other.activeFlag != null)
				return false;
		} else if (!activeFlag.equals(other.activeFlag))
			return false;
		if (affTypeDesc == null) {
			if (other.affTypeDesc != null)
				return false;
		} else if (!affTypeDesc.equals(other.affTypeDesc))
			return false;
		if (affTypeName == null) {
			if (other.affTypeName != null)
				return false;
		} else if (!affTypeName.equals(other.affTypeName))
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
		if (tCvgAffTypeId == null) {
			if (other.tCvgAffTypeId != null)
				return false;
		} else if (!tCvgAffTypeId.equals(other.tCvgAffTypeId))
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
}
