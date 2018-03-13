package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TPrdConfig 
 * The corresponding mapping table is t_prd_config 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdConfigs", query = "select myTPrdConfig from TPrdConfig myTPrdConfig"),
		@NamedQuery(name = "CountTPrdConfigs", query = "select Count(c) from TPrdConfig c"),
		@NamedQuery(name = "FindTPrdConfigByTPrdHierType", query = "select myTPrdConfig from TPrdConfig myTPrdConfig where myTPrdConfig.hierTypeId = ?1 "),
		@NamedQuery(name = "CountTPrdConfigsByTPrdHierType", query = "select Count(myTPrdConfig) from TPrdConfig myTPrdConfig where myTPrdConfig.hierTypeId = ?1 ") })
@Table(name = "t_prd_config", uniqueConstraints = @UniqueConstraint(columnNames = { "prd_config_id" }))
public class TPrdConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @generated
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_config_id", nullable = false, length = 255)
	private Integer prdConfigId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "prd_config_name", nullable = true, length = 75)
	private String prdConfigName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "prd_config_desc", nullable = true, length = 200)
	private String prdConfigDesc;

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
	
	@Column(name = "hier_type_id", nullable = true, length = 255)
	private Integer hierTypeId;

	@Column(name = "org_id", nullable = true, length = 255)
	private Integer orgId;

	/*@ManyToOne
	@JoinColumn(name = "hier_type_id", referencedColumnName = "hier_type_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TPrdHierType tPrdHierType;*/

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdConfig")
	@NotAudited
	private Set<TPrdBussUnit> tPrdBussUnitss;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdConfig")
	private Set<TPrdHierConfig> tPrdHierConfigss;*/

	/**
	 *
	 * @generated
	 */
	public TPrdConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdConfigId(final Integer prdConfigId) {
		this.prdConfigId = prdConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrdConfigId() {
		return this.prdConfigId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdConfigName(final String prdConfigName) {
		this.prdConfigName = prdConfigName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdConfigName() {
		return this.prdConfigName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdConfigDesc(final String prdConfigDesc) {
		this.prdConfigDesc = prdConfigDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPrdConfigDesc() {
		return this.prdConfigDesc;
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
	/*public TPrdHierType getTPrdHierType() {
		return this.tPrdHierType;
	}*/

	/**
	 * 
	 * @generated
	 */
//	public void setTPrdHierType(final TPrdHierType tPrdHierType) {
//		this.tPrdHierType = tPrdHierType;
//
//	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdBussUnit> getTPrdBussUnitss() {
		return this.tPrdBussUnitss;
	}

	public Integer getHierTypeId() {
		return hierTypeId;
	}

	public void setHierTypeId(Integer hierTypeId) {
		this.hierTypeId = hierTypeId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdBussUnitss(final Set<TPrdBussUnit> tPrdBussUnitss) {
		this.tPrdBussUnitss = tPrdBussUnitss;
	}

	/**
	 * 
	 * @generated
	 */
	/*public Set<TPrdHierConfig> getTPrdHierConfigss() {
		return this.tPrdHierConfigss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdHierConfigss(final Set<TPrdHierConfig> tPrdHierConfigss) {
		this.tPrdHierConfigss = tPrdHierConfigss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdConfig that) {
		setPrdConfigId(that.getPrdConfigId());
		setPrdConfigName(that.getPrdConfigName());
		setPrdConfigDesc(that.getPrdConfigDesc());
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
		result = prime * result + ((prdConfigId == null) ? 0 : prdConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdConfigId=[").append(prdConfigId).append("] ");
		buffer.append("prdConfigName=[").append(prdConfigName).append("] ");
		buffer.append("prdConfigDesc=[").append(prdConfigDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("hierTypeId=[").append(hierTypeId).append("] ");
		buffer.append("orgId=[").append(orgId).append("] ");

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
		final TPrdConfig other = (TPrdConfig) obj;
		if (prdConfigId == null) {
			if (other.prdConfigId != null) {
				return false;
			}
		} else if (!prdConfigId.equals(other.prdConfigId)) {
			return false;
		}
		return true;
	}
}
