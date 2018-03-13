package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TBussObjAssoc 
 * The corresponding mapping table is t_buss_obj_assoc 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussObjAssocs", query = "select myTBussObjAssoc from TBussObjAssoc myTBussObjAssoc"),
		@NamedQuery(name = "CountTBussObjAssocs", query = "Select Count(c) from TBussObjAssoc c"),
		@NamedQuery(name = "FindTBussObjAssocByTBussFunction", query = "select myTBussObjAssoc from TBussObjAssoc myTBussObjAssoc where myTBussObjAssoc.tBussFunction = ?1 "),
		@NamedQuery(name = "CountTBussObjAssocsByTBussFunction", query = "select Count(*) from TBussObjAssoc myTBussObjAssoc where myTBussObjAssoc.tBussFunction = ?1 "),
		@NamedQuery(name = "FindTBussObjAssocByTBussObj", query = "select myTBussObjAssoc from TBussObjAssoc myTBussObjAssoc where myTBussObjAssoc.tBussObj = ?1 "),
		@NamedQuery(name = "CountTBussObjAssocsByTBussObj", query = "select Count(*) from TBussObjAssoc myTBussObjAssoc where myTBussObjAssoc.tBussObj = ?1 ") })
@Table(name = "t_buss_obj_assoc")
public class TBussObjAssoc implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TBussObjAssocId tBussObjAssocId;

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
	@JoinColumn(name = "buss_function_id", referencedColumnName = "buss_function_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussFunction tBussFunction;

	@ManyToOne
	@JoinColumn(name = "buss_obj_id", referencedColumnName = "buss_obj_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussObj tBussObj;

	/**
	 *
	 * @generated
	 */
	public TBussObjAssoc() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTBussObjAssocId(final TBussObjAssocId tBussObjAssocId) {
		this.tBussObjAssocId = tBussObjAssocId;
	}

	/**
	 * 
	 * @generated
	 */
	public TBussObjAssocId getTBussObjAssocId() {
		return this.tBussObjAssocId;
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
	public TBussFunction getTBussFunction() {
		return this.tBussFunction;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussFunction(final TBussFunction tBussFunction) {
		this.tBussFunction = tBussFunction;
		if (this.tBussFunction != null && this.tBussFunction.getBussFunctionId() != null) {

			this.tBussObjAssocId.setBussFunctionId(this.tBussFunction.getBussFunctionId());

		}

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
		if (this.tBussObj != null && this.tBussObj.getBussObjId() != null) {

			this.tBussObjAssocId.setBussObjId(this.tBussObj.getBussObjId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussObjAssoc that) {
		setTBussObjAssocId(that.getTBussObjAssocId());
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
		result = prime * result + ((tBussObjAssocId == null) ? 0 : tBussObjAssocId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tBussObjAssocId=[").append(tBussObjAssocId).append("] ");
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
		final TBussObjAssoc other = (TBussObjAssoc) obj;
		if (tBussObjAssocId == null) {
			if (other.tBussObjAssocId != null) {
				return false;
			}
		} else if (!tBussObjAssocId.equals(other.tBussObjAssocId)) {
			return false;
		}
		return true;
	}
}
