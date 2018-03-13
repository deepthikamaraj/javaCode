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
 * JPA class representing TPrtType 
 * The corresponding mapping table is t_prt_type 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTPrtTypes", query = "select myTPrtType from TPrtType myTPrtType"),
		@NamedQuery(name = "CountTPrtTypes", query = "Select Count(c) from TPrtType c"),
		@NamedQuery(name = "FindActiveTypesForLocale", query = "Select myTPrtType.tPrtTypeId.prtTypeId,myTPrtType.prtTypeName from TPrtType myTPrtType where " +
				" myTPrtType.activeFlag='Y' and myTPrtType.tPrtTypeId.localeId=?1 and myTPrtType.tenantId=?2"),
		@NamedQuery(name = "FindPrtNameForLocale", query = "Select myTPrtType.prtTypeName from TPrtType myTPrtType where myTPrtType.tPrtTypeId.prtTypeId=?1 " +
				"and myTPrtType.tPrtTypeId.localeId=?2 "),
		@NamedQuery(name = "FindActiveTypesWithDesc", query = "Select myTPrtType.tPrtTypeId.prtTypeId from TPrtType myTPrtType where " +
						"myTPrtType.activeFlag='Y' and myTPrtType.prtTypeDesc like ?1 and myTPrtType.tPrtTypeId.localeId=?2 and myTPrtType.tenantId=?3"),
})
@Table(name = "t_prt_type")
public class TPrtType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrtTypeId tPrtTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 30)
	@Column(name = "prt_type_desc", nullable = true, length = 30)
	private String prtTypeDesc;

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
	
	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "prt_type_name", nullable = false, length = 30)
	private String prtTypeName;
	
/*
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrtType")
	private Set<TCust> tCustss;*/

	/**
	 *
	 * @generated
	 */
	public TPrtType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrtTypeId(final TPrtTypeId tPrtTypeId) {
		this.tPrtTypeId = tPrtTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrtTypeId getTPrtTypeId() {
		return this.tPrtTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrtTypeDesc(final String prtTypeDesc) {
		this.prtTypeDesc = prtTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrtTypeDesc() {
		return this.prtTypeDesc;
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

	public void setPrtTypeName(final String prtTypeName) {
		this.prtTypeName = prtTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrtTypeName() {
		return this.prtTypeName;
	}
	/**
	 * 
	 * @generated
	 *//*
	public Set<TCust> getTCustss() {
		return this.tCustss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTCustss(final Set<TCust> tCustss) {
		this.tCustss = tCustss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrtType that) {
		setTPrtTypeId(that.getTPrtTypeId());
		setPrtTypeDesc(that.getPrtTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPrtTypeName(that.getPrtTypeName());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tPrtTypeId == null) ? 0 : tPrtTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrtTypeId=[").append(tPrtTypeId).append("] ");
		buffer.append("prtTypeDesc=[").append(prtTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("prtTypeName=[").append(prtTypeName).append("] ");

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
		final TPrtType other = (TPrtType) obj;
		if (tPrtTypeId == null) {
			if (other.tPrtTypeId != null) {
				return false;
			}
		} else if (!tPrtTypeId.equals(other.tPrtTypeId)) {
			return false;
		}
		return true;
	}
}
