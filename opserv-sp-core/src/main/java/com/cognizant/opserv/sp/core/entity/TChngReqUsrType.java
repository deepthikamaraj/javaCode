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
 * JPA class representing TChngReqUsrType 
 * The corresponding mapping table is t_chng_req_usr_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqUsrTypes", query = "select myTChngReqUsrType from TChngReqUsrType myTChngReqUsrType "),
		@NamedQuery(name = "FindAllTChngReqUsrTypesByTenant", query = "select myTChngReqUsrType from TChngReqUsrType myTChngReqUsrType where myTChngReqUsrType.tenantId =?1"),
		@NamedQuery(name = "CountTChngReqUsrTypes", query = "Select Count(c) from TChngReqUsrType c"),
		@NamedQuery(name = "FindTUsrDesc", query = "Select myTChngReqUsrType.usrTypeDesc from TChngReqUsrType myTChngReqUsrType where myTChngReqUsrType.tenantId = ?1 and myTChngReqUsrType.tChngReqUsrTypeId.localeId = ?2 and myTChngReqUsrType.tChngReqUsrTypeId.usrTypeId= ?3")	 
})
@Table(name = "t_chng_req_usr_type")
public class TChngReqUsrType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqUsrTypeId tChngReqUsrTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "usr_type_name", nullable = true, length = 20)
	private String usrTypeName;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "usr_type_desc", nullable = false, length = 20)
	private String usrTypeDesc;

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
	public TChngReqUsrType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqUsrTypeId(final TChngReqUsrTypeId tChngReqUsrTypeId) {
		this.tChngReqUsrTypeId = tChngReqUsrTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqUsrTypeId getTChngReqUsrTypeId() {
		return this.tChngReqUsrTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUsrTypeName(final String usrTypeName) {
		this.usrTypeName = usrTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getUsrTypeName() {
		return this.usrTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setUsrTypeDesc(final String usrTypeDesc) {
		this.usrTypeDesc = usrTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getUsrTypeDesc() {
		return this.usrTypeDesc;
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
	public void copy(final TChngReqUsrType that) {
		setTChngReqUsrTypeId(that.getTChngReqUsrTypeId());
		setUsrTypeName(that.getUsrTypeName());
		setUsrTypeDesc(that.getUsrTypeDesc());
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
		result = prime * result + ((tChngReqUsrTypeId == null) ? 0 : tChngReqUsrTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqUsrTypeId=[").append(tChngReqUsrTypeId).append("] ");
		buffer.append("usrTypeName=[").append(usrTypeName).append("] ");
		buffer.append("usrTypeDesc=[").append(usrTypeDesc).append("] ");
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
		final TChngReqUsrType other = (TChngReqUsrType) obj;
		if (tChngReqUsrTypeId == null) {
			if (other.tChngReqUsrTypeId != null) {
				return false;
			}
		} else if (!tChngReqUsrTypeId.equals(other.tChngReqUsrTypeId)) {
			return false;
		}
		return true;
	}
}
