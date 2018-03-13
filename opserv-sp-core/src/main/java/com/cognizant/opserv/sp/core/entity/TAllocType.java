package com.cognizant.opserv.sp.core.entity;

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
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAllocType 
 * The corresponding mapping table is t_alloc_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTAllocTypes", query = "select myTAllocType from TAllocType myTAllocType"),
		@NamedQuery(name = "CountTAllocTypes", query = "Select Count(c) from TAllocType c"),
		@NamedQuery(name = "FindTAllocTypesByTenantAndLocale",query = "select myTAllocType.tAllocTypeId.allocTypeId," +
				" myTAllocType.allocTypeCd from TAllocType myTAllocType where myTAllocType.tenantId = ?1 and " +
				" myTAllocType.tAllocTypeId.localeId = ?2 and myTAllocType.activeFlag = 'Y' ")})
@Table(name = "t_alloc_type")
public class TAllocType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAllocTypeId tAllocTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 10)
	@Column(name = "alloc_type_cd", nullable = true, length = 10)
	private String allocTypeCd;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "alloc_type_desc", nullable = false, length = 30)
	private String allocTypeDesc;

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
     *  tEmpAlgmnts - 
     */
   /*@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tAllocType")
   // @Fetch(value = FetchMode.SUBSELECT)
    private Set<TEmpAlgmnt> tEmpAlgmnts ;
   */
   
	public TAllocTypeId gettAllocTypeId() {
		return tAllocTypeId;
	}

	public void settAllocTypeId(TAllocTypeId tAllocTypeId) {
		this.tAllocTypeId = tAllocTypeId;
	}

	/*public Set<TEmpAlgmnt> gettEmpAlgmnts() {
		return tEmpAlgmnts;
	}

	public void settEmpAlgmnts(Set<TEmpAlgmnt> tEmpAlgmnts) {
		this.tEmpAlgmnts = tEmpAlgmnts;
	}*/

	/**
	 *
	 * @generated
	 */
	public TAllocType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTAllocTypeId(final TAllocTypeId tAllocTypeId) {
		this.tAllocTypeId = tAllocTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAllocTypeId getTAllocTypeId() {
		return this.tAllocTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAllocTypeCd(final String allocTypeCd) {
		this.allocTypeCd = allocTypeCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAllocTypeCd() {
		return this.allocTypeCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAllocTypeDesc(final String allocTypeDesc) {
		this.allocTypeDesc = allocTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAllocTypeDesc() {
		return this.allocTypeDesc;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAllocType that) {
		setTAllocTypeId(that.getTAllocTypeId());
		setAllocTypeCd(that.getAllocTypeCd());
		setAllocTypeDesc(that.getAllocTypeDesc());
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
		result = prime * result + ((tAllocTypeId == null) ? 0 : tAllocTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAllocTypeId=[").append(tAllocTypeId).append("] ");
		buffer.append("allocTypeCd=[").append(allocTypeCd).append("] ");
		buffer.append("allocTypeDesc=[").append(allocTypeDesc).append("] ");
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
		final TAllocType other = (TAllocType) obj;
		if (tAllocTypeId == null) {
			if (other.tAllocTypeId != null) {
				return false;
			}
		} else if (!tAllocTypeId.equals(other.tAllocTypeId)) {
			return false;
		}
		return true;
	}
}
