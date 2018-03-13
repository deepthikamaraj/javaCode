package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAttrDataType 
 * The corresponding mapping table is t_attr_data_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAttrDataTypes", query = "select myTAttrDataType from TAttrDataType myTAttrDataType"),
		@NamedQuery(name = "CountTAttrDataTypes", query = "Select Count(c) from TAttrDataType c"),
		@NamedQuery(name = "FindTAttrDataTypesByTAttrDataTypeId", query = "Select myTAttrDataType from TAttrDataType myTAttrDataType where myTAttrDataType.tAttrDataTypeId.attrDataTypeId = ?1 and myTAttrDataType.tAttrDataTypeId.localeId like ?2"),
		@NamedQuery(name = "FindTAttrDataTypesByLocaleId", query = "select myTAttrDataType.tAttrDataTypeId.attrDataTypeId, myTAttrDataType.dataTypeName " +
				" from TAttrDataType myTAttrDataType where myTAttrDataType.tAttrDataTypeId.localeId = ?1 and myTAttrDataType.activeFlag = ?2 ")
		})
@Table(name = "t_attr_data_type", uniqueConstraints = @UniqueConstraint(columnNames = { "attr_data_type_id" }))
public class TAttrDataType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAttrDataTypeId tAttrDataTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "data_type_name", nullable = true, length = 50)
	private String dataTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "data_type_desc", nullable = true, length = 500)
	private String dataTypeDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDataType")
	private Set<TAttr> tAttrss;
*/
	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAttrDataType")
	private Set<TValType> tValTypess;*/

	/**
	 *
	 * @generated
	 */
	public TAttrDataType() {
	}

	/**
	 * 
	 * @generated
	 */
	public TAttrDataTypeId gettAttrDataTypeId() {
		return tAttrDataTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void settAttrDataTypeId(TAttrDataTypeId tAttrDataTypeId) {
		this.tAttrDataTypeId = tAttrDataTypeId;
	}
	
	/**
	 * 
	 * @generated
	 */
	public void setDataTypeName(final String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDataTypeName() {
		return this.dataTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataTypeDesc(final String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDataTypeDesc() {
		return this.dataTypeDesc;
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
	/*public Set<TAttr> getTAttrss() {
		return this.tAttrss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTAttrss(final Set<TAttr> tAttrss) {
		this.tAttrss = tAttrss;
	}
*/
	/**
	 * 
	 * @generated
	 */
	/*public Set<TValType> getTValTypess() {
		return this.tValTypess;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTValTypess(final Set<TValType> tValTypess) {
		this.tValTypess = tValTypess;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAttrDataType that) {
		settAttrDataTypeId(that.gettAttrDataTypeId());
		setDataTypeName(that.getDataTypeName());
		setDataTypeDesc(that.getDataTypeDesc());
		setActiveFlag(that.getActiveFlag());
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAttrDataTypeId=[").append(tAttrDataTypeId).append("] ");
		buffer.append("dataTypeName=[").append(dataTypeName).append("] ");
		buffer.append("dataTypeDesc=[").append(dataTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tAttrDataTypeId == null) ? 0 : tAttrDataTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TAttrDataType other = (TAttrDataType) obj;
		if (tAttrDataTypeId == null) {
			if (other.tAttrDataTypeId != null)
				return false;
		} else if (!tAttrDataTypeId.equals(other.tAttrDataTypeId))
			return false;
		return true;
	}

}
