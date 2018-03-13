package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

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

/** 
 * JPA class representing TValType 
 * The corresponding mapping table is t_val_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTValTypes", query = "select myTValType from TValType myTValType"),
		@NamedQuery(name = "CountTValTypes", query = "Select Count(c) from TValType c"),
		@NamedQuery(name = "FindTValTypeByTAttrDataType", query = "select myTValType from TValType myTValType where myTValType.attrDataTypeId = ?1 "),
		@NamedQuery(name = "CountTValTypesByTAttrDataType", query = "select Count(myTValType) from TValType myTValType where myTValType.attrDataTypeId = ?1 ") })
@Table(name = "t_val_type", uniqueConstraints = @UniqueConstraint(columnNames = { "val_type_id" }))
public class TValType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "val_type_id", nullable = false, length = 255)
	private Integer valTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "val_name", nullable = true, length = 50)
	private String valName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "val_desc", nullable = true, length = 100)
	private String valDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	/*@ManyToOne
	@JoinColumn(name = "attr_data_type_id", referencedColumnName = "attr_data_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TAttrDataType tAttrDataType;*/
	
	
	@NotNull
	@Column(name = "locale_id", nullable = false, length = 20)
	private String localeId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "attr_data_type_id", nullable = true, length = 1)
	private Integer attrDataTypeId;
	
	/**
	 *
	 * @generated
	 */
	public TValType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setValTypeId(final Integer valTypeId) {
		this.valTypeId = valTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getValTypeId() {
		return this.valTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValName(final String valName) {
		this.valName = valName;
	}

	public String getLocaleId() {
		return localeId;
	}

	public void setLocaleId(String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getValName() {
		return this.valName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setValDesc(final String valDesc) {
		this.valDesc = valDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getValDesc() {
		return this.valDesc;
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
	public Integer getAttrDataTypeId() {
		return attrDataTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setAttrDataTypeId(Integer attrDataTypeId) {
		this.attrDataTypeId = attrDataTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TAttrDataType getTAttrDataType() {
		return this.tAttrDataType;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTAttrDataType(final TAttrDataType tAttrDataType) {
		this.tAttrDataType = tAttrDataType;

	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TValType that) {
		setValTypeId(that.getValTypeId());
		setValName(that.getValName());
		setValDesc(that.getValDesc());
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
		result = prime * result + ((valTypeId == null) ? 0 : valTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("valTypeId=[").append(valTypeId).append("] ");
		buffer.append("valName=[").append(valName).append("] ");
		buffer.append("valDesc=[").append(valDesc).append("] ");
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
		final TValType other = (TValType) obj;
		if (valTypeId == null) {
			if (other.valTypeId != null) {
				return false;
			}
		} else if (!valTypeId.equals(other.valTypeId)) {
			return false;
		}
		return true;
	}
}
