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

import org.hibernate.envers.Audited;

/** 
 * JPA class representing TPrdHierConfig 
 * The corresponding mapping table is t_prd_hier_config 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdHierConfigs", query = "select myTPrdHierConfig from TPrdHierConfig myTPrdHierConfig"),
		@NamedQuery(name = "CountTPrdHierConfigs", query = "Select Count(c) from TPrdHierConfig c"),
		@NamedQuery(name = "FindTPrdHierConfigByTPrdConfig", query = "select myTPrdHierConfig from TPrdHierConfig myTPrdHierConfig where myTPrdHierConfig.tPrdHierConfigId.prdConfigId = ?1 "),
		@NamedQuery(name = "CountTPrdHierConfigsByTPrdConfig", query = "select Count(*) from TPrdHierConfig myTPrdHierConfig where myTPrdHierConfig.tPrdHierConfigId.prdConfigId = ?1 "),
		@NamedQuery(name = "FindTPrdHierConfigByTPrdHierLevel", query = "select myTPrdHierConfig from TPrdHierConfig myTPrdHierConfig where myTPrdHierConfig.tPrdHierConfigId.hierLevelId = ?1 "),
		@NamedQuery(name = "CountTPrdHierConfigsByTPrdHierLevel", query = "select Count(*) from TPrdHierConfig myTPrdHierConfig where myTPrdHierConfig.tPrdHierConfigId.hierLevelId = ?1 "),
		@NamedQuery(name = "FindTPrdHierConfigByHierLevelID", query = "select myTPrdHierConfig from TPrdHierConfig myTPrdHierConfig where myTPrdHierConfig.tPrdHierConfigId.hierLevelId = ?1 and myTPrdHierConfig.tenantId= ?2")})
@Table(name = "t_prd_hier_config")
public class TPrdHierConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TPrdHierConfigId tPrdHierConfigId;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

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

	/*@ManyToOne
	@JoinColumn(name = "prd_config_id", referencedColumnName = "prd_config_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdConfig tPrdConfig;*/

	/*@ManyToOne
	@JoinColumn(name = "hier_level_id", referencedColumnName = "hier_level_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TPrdHierLevel tPrdHierLevel;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHierConfig")
	private Set<TPrdHierGroup> tPrdHierGroupss;*/

	/**
	 *
	 * @generated
	 */
	public TPrdHierConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTPrdHierConfigId(final TPrdHierConfigId tPrdHierConfigId) {
		this.tPrdHierConfigId = tPrdHierConfigId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdHierConfigId getTPrdHierConfigId() {
		return this.tPrdHierConfigId;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
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
	/*public TPrdConfig getTPrdConfig() {
		return this.tPrdConfig;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdConfig(final TPrdConfig tPrdConfig) {
		this.tPrdConfig = tPrdConfig;
		if (this.tPrdConfig != null && this.tPrdConfig.getPrdConfigId() != null) {

			this.tPrdHierConfigId.setPrdConfigId(this.tPrdConfig.getPrdConfigId());

		}

	}*/

	/**
	 * 
	 * @generated
	 */
	/*public TPrdHierLevel getTPrdHierLevel() {
		return this.tPrdHierLevel;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdHierLevel(final TPrdHierLevel tPrdHierLevel) {
		this.tPrdHierLevel = tPrdHierLevel;
		if (this.tPrdHierLevel != null && this.tPrdHierLevel.getHierLevelId() != null) {

			this.tPrdHierConfigId.setHierLevelId(this.tPrdHierLevel.getHierLevelId());

		}

	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TPrdHierGroup> getTPrdHierGroupss() {
		return this.tPrdHierGroupss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdHierGroupss(final Set<TPrdHierGroup> tPrdHierGroupss) {
		this.tPrdHierGroupss = tPrdHierGroupss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdHierConfig that) {
		setTPrdHierConfigId(that.getTPrdHierConfigId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((tPrdHierConfigId == null) ? 0 : tPrdHierConfigId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tPrdHierConfigId=[").append(tPrdHierConfigId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TPrdHierConfig other = (TPrdHierConfig) obj;
		if (tPrdHierConfigId == null) {
			if (other.tPrdHierConfigId != null) {
				return false;
			}
		} else if (!tPrdHierConfigId.equals(other.tPrdHierConfigId)) {
			return false;
		}
		return true;
	}
}
