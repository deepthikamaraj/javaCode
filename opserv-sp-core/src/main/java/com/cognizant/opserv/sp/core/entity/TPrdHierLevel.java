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
 * JPA class representing TPrdHierLevel 
 * The corresponding mapping table is t_prd_hier_level 
 */


@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTPrdHierLevels", query = "select myTPrdHierLevel from TPrdHierLevel myTPrdHierLevel"),
		@NamedQuery(name = "CountTPrdHierLevels", query = "Select Count(c) from TPrdHierLevel c"),
		@NamedQuery(name = "FindTPrdHierLevelByTPrdHierLevel", query = "select myTPrdHierLevel from TPrdHierLevel myTPrdHierLevel where myTPrdHierLevel.tPrdHierLevel = ?1 "),
		@NamedQuery(name = "CountTPrdHierLevelsByTPrdHierLevel", query = "select Count(myTPrdHierLevel) from TPrdHierLevel myTPrdHierLevel where myTPrdHierLevel.tPrdHierLevel = ?1 "),
		@NamedQuery(name = "FindTPrdHierLevelByMaxOfHierLevelId",query = "select Max(myTPrdHierLevel.hierLevelId) from TPrdHierLevel myTPrdHierLevel where myTPrdHierLevel.activeFlag = ?1 and myTPrdHierLevel.tenantId = ?2"),
		@NamedQuery(name = "FindMaxOfHierLevelId",query = "select Max(myTPrdHierLevel.hierLevelId) from TPrdHierLevel myTPrdHierLevel where  myTPrdHierLevel.tenantId = ?1")})
@Table(name = "t_prd_hier_level", uniqueConstraints = @UniqueConstraint(columnNames = { "hier_level_id" }))
public class TPrdHierLevel implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hier_level_id", nullable = false, length = 255)
	private Integer hierLevelId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "hier_level_name", nullable = false, length = 50)
	private String hierLevelName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "hier_level_desc", nullable = true, length = 100)
	private String hierLevelDesc;

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
	@Column(name = "eff_start_dt", nullable = false, length = 10)
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
	
	/**
	 * 
	 * @generated
	 */	
	@Column(name = "prn_hier_level_id", nullable = true, length = 255)
	private Integer prnHierLevelId;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_hier_level_id", referencedColumnName = "hier_level_id", nullable = true, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TPrdHierLevel tPrdHierLevel;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHierLevel")
	@NotAudited
	private Set<TPrdHierLevel> tPrdHierLevelss;

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdHierLevel")
	private Set<TPrdHierConfig> tPrdHierConfigss;*/

	/**
	 *
	 * @generated
	 */
	public TPrdHierLevel() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierLevelId(final Integer hierLevelId) {
		this.hierLevelId = hierLevelId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getHierLevelId() {
		return this.hierLevelId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierLevelName(final String hierLevelName) {
		this.hierLevelName = hierLevelName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierLevelName() {
		return this.hierLevelName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setHierLevelDesc(final String hierLevelDesc) {
		this.hierLevelDesc = hierLevelDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierLevelDesc() {
		return this.hierLevelDesc;
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

	public Integer getPrnHierLevelId() {
		return prnHierLevelId;
	}

	public void setPrnHierLevelId(Integer prnHierLevelId) {
		this.prnHierLevelId = prnHierLevelId;
	}

	/**
	 * 
	 * @generated
	 */
	public TPrdHierLevel getTPrdHierLevel() {
		return this.tPrdHierLevel;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierLevel(final TPrdHierLevel tPrdHierLevel) {
		this.tPrdHierLevel = tPrdHierLevel;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TPrdHierLevel> getTPrdHierLevelss() {
		return this.tPrdHierLevelss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdHierLevelss(final Set<TPrdHierLevel> tPrdHierLevelss) {
		this.tPrdHierLevelss = tPrdHierLevelss;
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
	public void copy(final TPrdHierLevel that) {
		setHierLevelId(that.getHierLevelId());
		setHierLevelName(that.getHierLevelName());
		setHierLevelDesc(that.getHierLevelDesc());
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
		result = prime * result + ((hierLevelId == null) ? 0 : hierLevelId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("hierLevelId=[").append(hierLevelId).append("] ");
		buffer.append("hierLevelName=[").append(hierLevelName).append("] ");
		buffer.append("hierLevelDesc=[").append(hierLevelDesc).append("] ");
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
		final TPrdHierLevel other = (TPrdHierLevel) obj;
		if (hierLevelId == null) {
			if (other.hierLevelId != null) {
				return false;
			}
		} else if (!hierLevelId.equals(other.hierLevelId)) {
			return false;
		}
		return true;
	}
}
