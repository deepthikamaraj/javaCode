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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TEmpAlgmntAttr 
 * The corresponding mapping table is t_emp_algmnt_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTEmpAlgmntAttrs", query = "select myTEmpAlgmntAttr from TEmpAlgmntAttr myTEmpAlgmntAttr"),
		@NamedQuery(name = "CountTEmpAlgmntAttrs", query = "Select Count(c) from TEmpAlgmntAttr c"),
		@NamedQuery(name = "FindTEmpAlgmntAttrByTEmpAlgmnt", query = "select myTEmpAlgmntAttr from TEmpAlgmntAttr myTEmpAlgmntAttr where myTEmpAlgmntAttr.tEmpAlgmnt = ?1 "),
		@NamedQuery(name = "CountTEmpAlgmntAttrsByTEmpAlgmnt", query = "select Count(*) from TEmpAlgmntAttr myTEmpAlgmntAttr where myTEmpAlgmntAttr.tEmpAlgmnt = ?1 "),
		@NamedQuery(name = "FindTEmpAlgmntAttrByTAttrDef", query = "select myTEmpAlgmntAttr from TEmpAlgmntAttr myTEmpAlgmntAttr where myTEmpAlgmntAttr.tAttrDef = ?1 "),
		@NamedQuery(name = "CountTEmpAlgmntAttrsByTAttrDef", query = "select Count(*) from TEmpAlgmntAttr myTEmpAlgmntAttr where myTEmpAlgmntAttr.tAttrDef = ?1 ") })
@Table(name = "t_emp_algmnt_attr")
public class TEmpAlgmntAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TEmpAlgmntAttrId tEmpAlgmntAttrId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 3000)
	@Column(name = "attr_value", nullable = true, length = 3000)
	private String attrValue;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "emp_algmnt_id", referencedColumnName = "emp_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TEmpAlgmnt tEmpAlgmnt;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	public TEmpAlgmntAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTEmpAlgmntAttrId(final TEmpAlgmntAttrId tEmpAlgmntAttrId) {
		this.tEmpAlgmntAttrId = tEmpAlgmntAttrId;
	}

	/**
	 * 
	 * @generated
	 */
	public TEmpAlgmntAttrId getTEmpAlgmntAttrId() {
		return this.tEmpAlgmntAttrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrValue(final String attrValue) {
		this.attrValue = attrValue;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrValue() {
		return this.attrValue;
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
	public TEmpAlgmnt getTEmpAlgmnt() {
		return this.tEmpAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTEmpAlgmnt(final TEmpAlgmnt tEmpAlgmnt) {
		this.tEmpAlgmnt = tEmpAlgmnt;
		if (this.tEmpAlgmnt != null && this.tEmpAlgmnt.getEmpAlgmntId() != null) {

			this.tEmpAlgmntAttrId.setEmpAlgmntId(this.tEmpAlgmnt.getEmpAlgmntId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TAttrDef getTAttrDef() {
		return this.tAttrDef;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAttrDef(final TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
		if (this.tAttrDef != null && this.tAttrDef.getAttrId() != null) {

			this.tEmpAlgmntAttrId.setAttrId(this.tAttrDef.getAttrId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TEmpAlgmntAttr that) {
		setTEmpAlgmntAttrId(that.getTEmpAlgmntAttrId());
		setAttrValue(that.getAttrValue());
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
		result = prime * result + ((tEmpAlgmntAttrId == null) ? 0 : tEmpAlgmntAttrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tEmpAlgmntAttrId=[").append(tEmpAlgmntAttrId).append("] ");
		buffer.append("attrValue=[").append(attrValue).append("] ");
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
		final TEmpAlgmntAttr other = (TEmpAlgmntAttr) obj;
		if (tEmpAlgmntAttrId == null) {
			if (other.tEmpAlgmntAttrId != null) {
				return false;
			}
		} else if (!tEmpAlgmntAttrId.equals(other.tEmpAlgmntAttrId)) {
			return false;
		}
		return true;
	}
}
