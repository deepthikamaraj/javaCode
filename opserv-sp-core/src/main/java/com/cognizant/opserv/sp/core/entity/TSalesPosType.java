package com.cognizant.opserv.sp.core.entity;

import com.cognizant.opserv.sp.core.entity.TSalesPosTypeId;
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
 * JPA class representing TSalesPosType 
 * The corresponding mapping table is t_sales_pos_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosTypes", query = "select myTSalesPosType from TSalesPosType myTSalesPosType"),
		@NamedQuery(name = "CountTSalesPosTypes", query = "Select Count(c) from TSalesPosType c") })
@Table(name = "t_sales_pos_type")
public class TSalesPosType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesPosTypeId tSalesPosTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 15)
	@Column(name = "type_cd", nullable = true, length = 15)
	private String typeCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 40)
	@Column(name = "type_name", nullable = true, length = 40)
	private String typeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "type_desc", nullable = true, length = 50)
	private String typeDesc;

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
	@Column(name = "tenant_id", nullable = true, length = 255)
	private Short tenantId;

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
	public TSalesPosType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesPosTypeId(final TSalesPosTypeId tSalesPosTypeId) {
		this.tSalesPosTypeId = tSalesPosTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosTypeId getTSalesPosTypeId() {
		return this.tSalesPosTypeId;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosType that) {
		setTSalesPosTypeId(that.getTSalesPosTypeId());
		setTypeCd(that.getTypeCd());
		setTypeName(that.getTypeName());
		setTypeDesc(that.getTypeDesc());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tSalesPosTypeId == null) ? 0 : tSalesPosTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesPosTypeId=[").append(tSalesPosTypeId).append("] ");
		buffer.append("typeCd=[").append(typeCd).append("] ");
		buffer.append("typeName=[").append(typeName).append("] ");
		buffer.append("typeDesc=[").append(typeDesc).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

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
		final TSalesPosType other = (TSalesPosType) obj;
		if (tSalesPosTypeId == null) {
			if (other.tSalesPosTypeId != null) {
				return false;
			}
		} else if (!tSalesPosTypeId.equals(other.tSalesPosTypeId)) {
			return false;
		}
		return true;
	}
}
