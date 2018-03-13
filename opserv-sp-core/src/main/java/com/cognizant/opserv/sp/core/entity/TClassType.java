package com.cognizant.opserv.sp.core.entity;

import com.cognizant.opserv.sp.core.entity.TClassTypeId;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TClassType 
 * The corresponding mapping table is t_class_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTClassTypes", query = "select myTClassType from TClassType myTClassType"),
		@NamedQuery(name = "CountTClassTypes", query = "Select Count(c) from TClassType c") })
@Table(name = "t_class_type")
public class TClassType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TClassTypeId tClassTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "class_type_name", nullable = true, length = 75)
	private String classTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "class_type_cd", nullable = true, length = 20)
	private String classTypeCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "class_type_desc", nullable = true, length = 100)
	private String classTypeDesc;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tClassType")
	private Set<TClassValue> tClassValuess;

	/**
	 *
	 * @generated
	 */
	public TClassType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTClassTypeId(final TClassTypeId tClassTypeId) {
		this.tClassTypeId = tClassTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TClassTypeId getTClassTypeId() {
		return this.tClassTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setClassTypeName(final String classTypeName) {
		this.classTypeName = classTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getClassTypeName() {
		return this.classTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setClassTypeCd(final String classTypeCd) {
		this.classTypeCd = classTypeCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getClassTypeCd() {
		return this.classTypeCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setClassTypeDesc(final String classTypeDesc) {
		this.classTypeDesc = classTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getClassTypeDesc() {
		return this.classTypeDesc;
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
	public Set<TClassValue> getTClassValuess() {
		return this.tClassValuess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTClassValuess(final Set<TClassValue> tClassValuess) {
		this.tClassValuess = tClassValuess;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TClassType that) {
		setTClassTypeId(that.getTClassTypeId());
		setClassTypeName(that.getClassTypeName());
		setClassTypeCd(that.getClassTypeCd());
		setClassTypeDesc(that.getClassTypeDesc());
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
		result = prime * result + ((tClassTypeId == null) ? 0 : tClassTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tClassTypeId=[").append(tClassTypeId).append("] ");
		buffer.append("classTypeName=[").append(classTypeName).append("] ");
		buffer.append("classTypeCd=[").append(classTypeCd).append("] ");
		buffer.append("classTypeDesc=[").append(classTypeDesc).append("] ");
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
		final TClassType other = (TClassType) obj;
		if (tClassTypeId == null) {
			if (other.tClassTypeId != null) {
				return false;
			}
		} else if (!tClassTypeId.equals(other.tClassTypeId)) {
			return false;
		}
		return true;
	}
}
