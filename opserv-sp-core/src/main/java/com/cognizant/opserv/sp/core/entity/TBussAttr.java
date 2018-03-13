package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TBussAttr 
 * The corresponding mapping table is t_buss_attr 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussAttrs", query = "select myTBussAttr from TBussAttr myTBussAttr"),
		@NamedQuery(name = "CountTBussAttrs", query = "Select Count(c) from TBussAttr c"),
		@NamedQuery(name = "FindTBussAttrByTBussEntity", query = "select myTBussAttr from TBussAttr myTBussAttr where myTBussAttr.tBussEntity = ?1 "),
		@NamedQuery(name = "CountTBussAttrsByTBussEntity", query = "select Count(myTBussAttr) from TBussAttr myTBussAttr where myTBussAttr.tBussEntity = ?1 "),
		@NamedQuery(name = "FindTBussAttrByTBussObj", query = "select myTBussAttr from TBussAttr myTBussAttr where myTBussAttr.tBussObj = ?1 "),
		@NamedQuery(name = "CountTBussAttrsByTBussObj", query = "select Count(myTBussAttr) from TBussAttr myTBussAttr where myTBussAttr.tBussObj = ?1 ") })
@Table(name = "t_buss_attr", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_id" }))
public class TBussAttr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@NotEmpty
	@Column(name = "attr_id", nullable = false, length = 20)
	private String attrId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 100)
	@Column(name = "attr_name", nullable = false, length = 100)
	private String attrName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name = "attr_alias_name", nullable = false, length = 200)
	private String attrAliasName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "attr_data_type", nullable = true, length = 20)
	private String attrDataType;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	@Length(max = 200)
	@Column(name = "prn_obj_name", nullable = true, length = 200)
	private String prnObjName;

	@ManyToOne
	@JoinColumn(name = "entity_id", referencedColumnName = "entity_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TBussEntity tBussEntity;

	@ManyToOne
	@JoinColumn(name = "buss_obj_id", referencedColumnName = "buss_obj_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TBussObj tBussObj;
	
	@Length(max = 200)
	@Column(name = "view_type", nullable = true, length = 200)
	private String viewType;
	
	@NotNull
	@Column(name = "is_sortable", nullable = false, length = 1)
	private Character isSortable;
	
	@NotNull
	@Column(name = "is_searchable", nullable = false, length = 1)
	private Character isSearchable;
	
	@Id
	@NotEmpty
	@Column(name = "order", nullable = false, length = 20)
	private Integer order;
	
	

	/**
	 *
	 * @generated
	 */
	public TBussAttr() {
	}
	
	/**
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the isSortable
	 */
	public Character getIsSortable() {
		return isSortable;
	}

	/**
	 * @param isSortable the isSortable to set
	 */
	public void setIsSortable(Character isSortable) {
		this.isSortable = isSortable;
	}

	/**
	 * @return the isSearchable
	 */
	public Character getIsSearchable() {
		return isSearchable;
	}

	/**
	 * @param isSearchable the isSearchable to set
	 */
	public void setIsSearchable(Character isSearchable) {
		this.isSearchable = isSearchable;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
	

	/**
	 * 
	 * @generated
	 */

	public void setAttrId(final String attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrId() {
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

	public void setAttrAliasName(final String attrAliasName) {
		this.attrAliasName = attrAliasName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrAliasName() {
		return this.attrAliasName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttrDataType(final String attrDataType) {
		this.attrDataType = attrDataType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttrDataType() {
		return this.attrDataType;
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

	public void setPrnObjName(final String prnObjName) {
		this.prnObjName = prnObjName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrnObjName() {
		return this.prnObjName;
	}

	/**
	 * 
	 * @generated
	 */
	public TBussEntity getTBussEntity() {
		return this.tBussEntity;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussEntity(final TBussEntity tBussEntity) {
		this.tBussEntity = tBussEntity;

	}

	/**
	 * 
	 * @generated
	 */
	public TBussObj getTBussObj() {
		return this.tBussObj;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussObj(final TBussObj tBussObj) {
		this.tBussObj = tBussObj;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussAttr that) {
		setAttrId(that.getAttrId());
		setAttrName(that.getAttrName());
		setAttrAliasName(that.getAttrAliasName());
		setAttrDataType(that.getAttrDataType());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPrnObjName(that.getPrnObjName());
		setOrder(that.getOrder());
		setIsSearchable(that.getIsSearchable());
		setIsSortable(that.getIsSortable());
		setViewType(that.getViewType());
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TBussAttr [attrId=" + attrId + ", attrName=" + attrName
				+ ", attrAliasName=" + attrAliasName + ", attrDataType="
				+ attrDataType + ", createdBy=" + createdBy + ", createDt="
				+ createDt + ", updatedBy=" + updatedBy + ", updateDt="
				+ updateDt + ", tenantId=" + tenantId + ", prnObjName="
				+ prnObjName + ", tBussEntity=" + tBussEntity + ", tBussObj="
				+ tBussObj + ", viewType=" + viewType + ", isSortable="
				+ isSortable + ", isSearchable=" + isSearchable + ", order="
				+ order + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
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
		final TBussAttr other = (TBussAttr) obj;
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
