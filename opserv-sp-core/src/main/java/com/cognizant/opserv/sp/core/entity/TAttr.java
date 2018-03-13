package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAttr 
 * The corresponding mapping table is t_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrs", query = "select myTAttr from TAttr myTAttr"),
		@NamedQuery(name = "CountTAttrs", query = "Select Count(c) from TAttr c"),
		//@NamedQuery(name = "FindTAttrByTAttrGroup", query = "select myTAttr from TAttr myTAttr where myTAttr.tAttrGroup = ?1 "),
		//@NamedQuery(name = "CountTAttrsByTAttrGroup", query = "select Count(myTAttr) from TAttr myTAttr where myTAttr.tAttrGroup = ?1 "),
		//@NamedQuery(name = "FindTAttrByTAttrDataType", query = "select myTAttr from TAttr myTAttr where myTAttr.attrDataTypeId = ?1 "),
		//@NamedQuery(name = "CountTAttrsByTAttrDataType", query = "select Count(myTAttr) from TAttr myTAttr where myTAttr.attrDataTypeId = ?1 ") 
		})
@Table(name = "t_attr", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_id" }))
public class TAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attr_id", nullable = false, length = 255)
	private Integer attrId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "attr_name", nullable = false, length = 75)
	private String attrName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 250)
	@Column(name = "attr_desc", nullable = true, length = 250)
	private String attrDesc;

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
	@Length(max = 50)
	@Column(name = "lookup_table", nullable = true, length = 50)
	private String lookupTable;

	/*@ManyToOne
	@JoinColumn(name = "attr_group_id", referencedColumnName = "attr_group_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TAttrGroup tAttrGroup;*/

	/**
	 *
	 * @generated
	 */
	public TAttr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrId(final Integer attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAttrId() {
		return this.attrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrName(final String attrName) {
		this.attrName = attrName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrDesc(final String attrDesc) {
		this.attrDesc = attrDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrDesc() {
		return this.attrDesc;
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

	public void setLookupTable(final String lookupTable) {
		this.lookupTable = lookupTable;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLookupTable() {
		return this.lookupTable;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TAttrGroup getTAttrGroup() {
		return this.tAttrGroup;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTAttrGroup(final TAttrGroup tAttrGroup) {
		this.tAttrGroup = tAttrGroup;

	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttr that) {
		setAttrId(that.getAttrId());
		setAttrName(that.getAttrName());
		setAttrDesc(that.getAttrDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setLookupTable(that.getLookupTable());		
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((attrId == null) ? 0 : attrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("attrId=[").append(attrId).append("] ");
		buffer.append("attrName=[").append(attrName).append("] ");
		buffer.append("attrDesc=[").append(attrDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("lookupTable=[").append(lookupTable).append("] ");		
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
		final TAttr other = (TAttr) obj;
		if (attrId == null) {
			if (other.attrId != null) {
				return false;
			}
		} else if (!attrId.equals(other.attrId)) {
			return false;
		}
		return true;
	}
}
