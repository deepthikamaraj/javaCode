package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TOrgRole 
 * The corresponding mapping table is t_org_role 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTOrgRoles", query = "select myTOrgRole from TOrgRole myTOrgRole"),
		@NamedQuery(name = "CountTOrgRoles", query = "Select Count(c) from TOrgRole c"),
		@NamedQuery(name = "FindTOrgRoleByTOrg", query = "select myTOrgRole from TOrgRole myTOrgRole where myTOrgRole.tOrg = ?1 "),
		@NamedQuery(name = "CountTOrgRolesByTOrg", query = "select Count(myTOrgRole) from TOrgRole myTOrgRole where myTOrgRole.tOrg = ?1 "),
		@NamedQuery(name = "FindTOrgRoleByTOrgRole", query = "select myTOrgRole from TOrgRole myTOrgRole where myTOrgRole.tOrgRole = ?1 "),
		@NamedQuery(name = "CountTOrgRolesByTOrgRole", query = "select Count(myTOrgRole) from TOrgRole myTOrgRole where myTOrgRole.tOrgRole = ?1 "),
		@NamedQuery(name = "FindTOrgRoleByTRoleList", query = "select myTOrgRole from TOrgRole myTOrgRole where myTOrgRole.tRoleList = ?1 "),
		@NamedQuery(name = "CountTOrgRolesByTRoleList", query = "select Count(myTOrgRole) from TOrgRole myTOrgRole where myTOrgRole.tRoleList = ?1 "),
		@NamedQuery(name = "FindRoleIdOrgRoleIdAndRoleName", query = "select myTOrgRole.orgRoleId,myTOrgRole.roleName from TOrgRole myTOrgRole where myTOrgRole.activeFlag = ?1 and myTOrgRole.tenantId = ?2"),
		@NamedQuery(name = "FindUnMappedRoleIdOrgRoleIdAndRoleName", query = "select myTOrgRole.orgRoleId,myTOrgRole.roleName from TOrgRole myTOrgRole where myTOrgRole.orgRoleId not in (select algmntSalesRole.tAlgmntSalesRoleId.orgRoleId from TAlgmntSalesRole algmntSalesRole where algmntSalesRole.activeFlag='Y') and myTOrgRole.activeFlag = ?1 and myTOrgRole.tenantId = ?2")
})
@Table(name = "t_org_role", uniqueConstraints = @UniqueConstraint(columnNames = { "org_role_id" }))
public class TOrgRole implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@Column(name = "org_role_id", nullable = false, length = 255)
	private Integer orgRoleId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 100)
	@Column(name = "role_name", nullable = false, length = 100)
	private String roleName;

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
	@Length(max = 150)
	@Column(name = "role_desc", nullable = true, length = 150)
	private String roleDesc;

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
	@Column(name = "prn_org_role_id", nullable = true, length = 255)
	private Integer prnOrgRoleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TOrg tOrg;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_org_role_id", referencedColumnName = "org_role_id", nullable = true, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TOrgRole tOrgRole;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "org_role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TRoleList tRoleList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrgRole")
	@NotAudited
	private Set<TOrgRole> tOrgRoless;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrgRole")
	@NotAudited
	private Set<TAlgmntSalesRole> tAlgmntSalesRoless;
	
	
	/**
	 *
	 * @generated
	 */
	public TOrgRole() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrgRoleId(final Integer orgRoleId) {
		this.orgRoleId = orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrgRoleId() {
		return this.orgRoleId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRoleName() {
		return this.roleName;
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

	public void setRoleDesc(final String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRoleDesc() {
		return this.roleDesc;
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

	public Integer getPrnOrgRoleId() {
		return prnOrgRoleId;
	}

	public void setPrnOrgRoleId(Integer prnOrgRoleId) {
		this.prnOrgRoleId = prnOrgRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public TOrg getTOrg() {
		return this.tOrg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrg(final TOrg tOrg) {
		this.tOrg = tOrg;

	}

	/**
	 * 
	 * @generated
	 */
	public TOrgRole getTOrgRole() {
		return this.tOrgRole;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgRole(final TOrgRole tOrgRole) {
		this.tOrgRole = tOrgRole;

	}

	/**
	 * 
	 * @generated
	 */
	public TRoleList getTRoleList() {
		return this.tRoleList;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRoleList(final TRoleList tRoleList) {
		this.tRoleList = tRoleList;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TOrgRole> getTOrgRoless() {
		return this.tOrgRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgRoless(final Set<TOrgRole> tOrgRoless) {
		this.tOrgRoless = tOrgRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntSalesRole> getTAlgmntSalesRoless() {
		return this.tAlgmntSalesRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesRoless(final Set<TAlgmntSalesRole> tAlgmntSalesRoless) {
		this.tAlgmntSalesRoless = tAlgmntSalesRoless;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TOrgRole that) {
		setOrgRoleId(that.getOrgRoleId());
		setRoleName(that.getRoleName());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setRoleDesc(that.getRoleDesc());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((orgRoleId == null) ? 0 : orgRoleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orgRoleId=[").append(orgRoleId).append("] ");
		buffer.append("roleName=[").append(roleName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("roleDesc=[").append(roleDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");

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
		final TOrgRole other = (TOrgRole) obj;
		if (orgRoleId == null) {
			if (other.orgRoleId != null) {
				return false;
			}
		} else if (!orgRoleId.equals(other.orgRoleId)) {
			return false;
		}
		return true;
	}
	
}
