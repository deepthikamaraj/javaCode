package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TClassValue 
 * The corresponding mapping table is t_class_value 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTClassValues", query = "select myTClassValue from TClassValue myTClassValue"),
		@NamedQuery(name = "CountTClassValues", query = "Select Count(c) from TClassValue c"),
		@NamedQuery(name = "FindTClassValueByTClassType", query = "select myTClassValue from TClassValue myTClassValue where myTClassValue.tClassType = ?1 "),
		@NamedQuery(name = "CountTClassValuesByTClassType", query = "select Count(myTClassValue) from TClassValue myTClassValue where myTClassValue.tClassType = ?1 ") })
@Table(name = "t_class_value", uniqueConstraints = @UniqueConstraint(columnNames = { "class_value_id" }))
public class TClassValue implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "class_value_id", nullable = false, length = 255)
	private Integer classValueId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "class_value", nullable = true, length = 200)
	private String classValue;

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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "class_type_id", referencedColumnName = "class_type_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "locale_id", referencedColumnName = "locale_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TClassType tClassType;

	/**
	 *
	 * @generated
	 */
	public TClassValue() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setClassValueId(final Integer classValueId) {
		this.classValueId = classValueId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getClassValueId() {
		return this.classValueId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setClassValue(final String classValue) {
		this.classValue = classValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getClassValue() {
		return this.classValue;
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
	public TClassType getTClassType() {
		return this.tClassType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTClassType(final TClassType tClassType) {
		this.tClassType = tClassType;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TClassValue that) {
		setClassValueId(that.getClassValueId());
		setClassValue(that.getClassValue());
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
		result = prime * result + ((classValueId == null) ? 0 : classValueId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("classValueId=[").append(classValueId).append("] ");
		buffer.append("classValue=[").append(classValue).append("] ");
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
		final TClassValue other = (TClassValue) obj;
		if (classValueId == null) {
			if (other.classValueId != null) {
				return false;
			}
		} else if (!classValueId.equals(other.classValueId)) {
			return false;
		}
		return true;
	}
}
