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
 * JPA class representing TMtrValueType 
 * The corresponding mapping table is t_mtr_value_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrValueTypes", query = "select myTMtrValueType from TMtrValueType myTMtrValueType"),
		@NamedQuery(name = "CountTMtrValueTypes", query = "Select Count(c) from TMtrValueType c") })
@Table(name = "t_mtr_value_type")
public class TMtrValueType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TMtrValueTypeId tMtrValueTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 40)
	@Column(name = "type_name", nullable = false, length = 40)
	private String typeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "type_desc", nullable = true, length = 75)
	private String typeDesc;

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
	@Length(max = 30)
	@Column(name = "type_cd", nullable = true, length = 30)
	private String typeCd;

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
	public TMtrValueType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTMtrValueTypeId(final TMtrValueTypeId tMtrValueTypeId) {
		this.tMtrValueTypeId = tMtrValueTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TMtrValueTypeId getTMtrValueTypeId() {
		return this.tMtrValueTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTypeDesc(final String typeDesc) {
		this.typeDesc = typeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeDesc() {
		return this.typeDesc;
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

	public void setTypeCd(final String typeCd) {
		this.typeCd = typeCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getTypeCd() {
		return this.typeCd;
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
	public void copy(final TMtrValueType that) {
		setTMtrValueTypeId(that.getTMtrValueTypeId());
		setTypeName(that.getTypeName());
		setTypeDesc(that.getTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setTypeCd(that.getTypeCd());
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
		result = prime * result + ((tMtrValueTypeId == null) ? 0 : tMtrValueTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tMtrValueTypeId=[").append(tMtrValueTypeId).append("] ");
		buffer.append("typeName=[").append(typeName).append("] ");
		buffer.append("typeDesc=[").append(typeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("typeCd=[").append(typeCd).append("] ");
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
		final TMtrValueType other = (TMtrValueType) obj;
		if (tMtrValueTypeId == null) {
			if (other.tMtrValueTypeId != null) {
				return false;
			}
		} else if (!tMtrValueTypeId.equals(other.tMtrValueTypeId)) {
			return false;
		}
		return true;
	}
}
