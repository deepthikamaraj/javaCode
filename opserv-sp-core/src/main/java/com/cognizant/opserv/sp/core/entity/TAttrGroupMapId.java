package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/** 
 * The primary key class for the TAttrGroupMapId Pojo. 
 *
 */
@Embeddable
public class TAttrGroupMapId implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "attr_group_id", nullable = false, length = 255)
	/*@ManyToOne
	@JoinColumn(name = "attr_group_id", referencedColumnName = "attr_group_id", nullable = false, insertable = true, updatable = true)
	@Valid*/
	private Integer attrGroupId;
	//private TAttrGroup tAttrGroup;
	@NotNull
	@Column(name = "attr_id", nullable = false, length = 255)
/*	@ManyToOne
	@JoinColumn(name = "attr_id", referencedColumnName = "attr_id", nullable = false, insertable = true, updatable = true)
	@Valid*/
	private Long attrId;
	//private TAttrDef tAttrDef;

	/**
	 *
	 * @generated
	 */
	public TAttrGroupMapId() {
	}

	/**
	 * 
	 * @generated
	 */
	public void setAttrGroupId(final Integer attrGroupId) {
		this.attrGroupId = attrGroupId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAttrGroupId() {
		return this.attrGroupId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setAttrId(final Long attrId) {
		this.attrId = attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAttrId() {
		return this.attrId;
	}

	/**
	 * 
	 * @generated
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TAttrGroupMapId)) {
			return false;
		}
		TAttrGroupMapId castOther = (TAttrGroupMapId) other;
		return (this.attrGroupId.equals(castOther.attrGroupId)) &&

		(this.attrId.equals(castOther.attrId))

		;

	}

	/*public TAttrGroup gettAttrGroup() {
		return tAttrGroup;
	}

	@Override
	public String toString() {
		return "TAttrGroupMapId [tAttrGroup=" + tAttrGroup + ", tAttrDef=" + tAttrDef
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tAttrDef == null) ? 0 : tAttrDef.hashCode());
		result = prime * result
				+ ((tAttrGroup == null) ? 0 : tAttrGroup.hashCode());
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
		TAttrGroupMapId other = (TAttrGroupMapId) obj;
		if (tAttrDef == null) {
			if (other.tAttrDef != null)
				return false;
		} else if (!tAttrDef.equals(other.tAttrDef))
			return false;
		if (tAttrGroup == null) {
			if (other.tAttrGroup != null)
				return false;
		} else if (!tAttrGroup.equals(other.tAttrGroup))
			return false;
		return true;
	}

	public void settAttrGroup(TAttrGroup tAttrGroup) {
		this.tAttrGroup = tAttrGroup;
	}

	public TAttrDef gettAttrDef() {
		return tAttrDef;
	}

	public void settAttrDef(TAttrDef tAttrDef) {
		this.tAttrDef = tAttrDef;
	}*/

	
	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrGroupId == null) ? 0 : attrGroupId.hashCode());
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

		buffer.append("attrGroupId=[").append(attrGroupId).append("] ");
		buffer.append("attrId=[").append(attrId).append("] ");

		return buffer.toString();
	}
}
