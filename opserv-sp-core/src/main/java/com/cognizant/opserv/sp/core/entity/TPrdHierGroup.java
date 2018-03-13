package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TPrdHierGroup 
 * The corresponding mapping table is t_prd_hier_group 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdHierGroups", query = "select myTPrdHierGroup from TPrdHierGroup myTPrdHierGroup"),
		@NamedQuery(name = "CountTPrdHierGroups", query = "Select Count(c) from TPrdHierGroup c"),
		@NamedQuery(name = "FindTPrdHierGroupByTPrdHierConfig", query = "select myTPrdHierGroup from TPrdHierGroup myTPrdHierGroup where myTPrdHierGroup.hierLevelId = ?1 and myTPrdHierGroup.prdConfigId = ?2"),
		@NamedQuery(name = "FindTPrdHierGroupByTPrdHierLevelId", query = "select myTPrdHierGroup from TPrdHierGroup myTPrdHierGroup where myTPrdHierGroup.hierLevelId = ?1 "),		
		@NamedQuery(name = "CountTPrdHierGroupsByTPrdHierConfig", query = "select Count(myTPrdHierGroup) from TPrdHierGroup myTPrdHierGroup where myTPrdHierGroup.hierLevelId = ?1 and myTPrdHierGroup.prdConfigId = ?2") 
		})
@Table(name = "t_prd_hier_group", uniqueConstraints = @UniqueConstraint(columnNames = { "group_id" }))
public class TPrdHierGroup implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_id", nullable = false, length = 255)
	private Integer groupId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 200)
	@Column(name = "group_name", nullable = false, length = 200)
	private String groupName;

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

	
	@Column(name = "prn_group_id", nullable = true, length = 255)
	private Integer prnGroupId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_group_id", referencedColumnName = "group_id", nullable = true, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPrdHierGroup tPrdHierGroup;
	
	@Column(name = "prd_config_id", nullable = true, length = 255)
	private Integer prdConfigId;
	
	@Column(name = "hier_level_id", nullable = true, length = 255)
	private Integer hierLevelId;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "prd_config_id", referencedColumnName = "prd_config_id", nullable = true, insertable = true, updatable = true),
			@JoinColumn(name = "hier_level_id", referencedColumnName = "hier_level_id", nullable = true, insertable = true, updatable = true) })
	@Valid
	private TPrdHierConfig tPrdHierConfig;*/

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHierGroup")
	@NotAudited
	private Set<TPrdHier> tPrdHierss;

	/**
	 *
	 * @generated
	 */
	public TPrdHierGroup() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGroupId(final Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGroupId() {
		return this.groupId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getGroupName() {
		return this.groupName;
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

	public Integer getPrdConfigId() {
		return prdConfigId;
	}

	public void setPrdConfigId(Integer prdConfigId) {
		this.prdConfigId = prdConfigId;
	}

	public Integer getHierLevelId() {
		return hierLevelId;
	}

	public void setHierLevelId(Integer hierLevelId) {
		this.hierLevelId = hierLevelId;
	}

	public Integer getPrnGroupId() {
		return prnGroupId;
	}

	public void setPrnGroupId(Integer prnGroupId) {
		this.prnGroupId = prnGroupId;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TPrdHierConfig getTPrdHierConfig() {
		return this.tPrdHierConfig;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTPrdHierConfig(final TPrdHierConfig tPrdHierConfig) {
		this.tPrdHierConfig = tPrdHierConfig;

	}*/

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdHier> getTPrdHierss() {
		return this.tPrdHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierss(final Set<TPrdHier> tPrdHierss) {
		this.tPrdHierss = tPrdHierss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdHierGroup that) {
		setGroupId(that.getGroupId());
		setGroupName(that.getGroupName());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setPrnGroupId(that.getPrnGroupId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("groupId=[").append(groupId).append("] ");
		buffer.append("groupName=[").append(groupName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("prnGroupId=[").append(prnGroupId).append("] ");

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
		final TPrdHierGroup other = (TPrdHierGroup) obj;
		if (groupId == null) {
			if (other.groupId != null) {
				return false;
			}
		} else if (!groupId.equals(other.groupId)) {
			return false;
		}
		return true;
	}
}
