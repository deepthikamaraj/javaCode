package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TPrdHierType 
 * The corresponding mapping table is t_prd_hier_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdHierTypes", query = "select myTPrdHierType from TPrdHierType myTPrdHierType"),
		@NamedQuery(name = "CountTPrdHierTypes", query = "Select Count(c) from TPrdHierType c") })
@Table(name = "t_prd_hier_type", uniqueConstraints = @UniqueConstraint(columnNames = { "hier_type_id" }))
public class TPrdHierType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hier_type_id", nullable = false, length = 255)
	private Integer hierTypeId;*/
	
	@EmbeddedId
	@Valid
	private TPrdHierTypeId tPrdHierTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "hier_type_desc", nullable = false, length = 50)
	private String hierTypeDesc;
	

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
	@NotEmpty
	@Length(max = 50)
	@Column(name = "hier_type_name", nullable = false, length = 50)
	private String hierTypeName;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHierType")
	private Set<TPrdConfig> tPrdConfigss;*/

	/**
	 *
	 * @generated
	 */
	public TPrdHierType() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setHierTypeId(final Integer hierTypeId) {
		this.hierTypeId = hierTypeId;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Integer getHierTypeId() {
		return this.hierTypeId;
	}*/

	public TPrdHierTypeId getTPrdHierTypeId() {
		return tPrdHierTypeId;
	}

	public void setTPrdHierTypeId(TPrdHierTypeId tPrdHierTypeId) {
		this.tPrdHierTypeId = tPrdHierTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierTypeDesc(final String hierTypeDesc) {
		this.hierTypeDesc = hierTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierTypeDesc() {
		return this.hierTypeDesc;
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
	/*public Set<TPrdConfig> getTPrdConfigss() {
		return this.tPrdConfigss;
	}*/

	public String getHierTypeName() {
		return hierTypeName;
	}

	public void setHierTypeName(String hierTypeName) {
		this.hierTypeName = hierTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdConfigss(final Set<TPrdConfig> tPrdConfigss) {
		this.tPrdConfigss = tPrdConfigss;
	}
*/
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdHierType that) {
		setTPrdHierTypeId(that.getTPrdHierTypeId());
		setHierTypeDesc(that.getHierTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setHierTypeName(that.getHierTypeName());
	}
	
	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();
		buffer.append("tPrdHierTypeId=[").append(tPrdHierTypeId).append("] ");
		buffer.append("hierTypeDesc=[").append(hierTypeDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("hierTypeName=[").append(hierTypeName).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tPrdHierTypeId == null) ? 0 : tPrdHierTypeId.hashCode());
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
		TPrdHierType other = (TPrdHierType) obj;
		if (tPrdHierTypeId == null) {
			if (other.tPrdHierTypeId != null)
				return false;
		} else if (!tPrdHierTypeId.equals(other.tPrdHierTypeId))
			return false;
		return true;
	}

	
}
