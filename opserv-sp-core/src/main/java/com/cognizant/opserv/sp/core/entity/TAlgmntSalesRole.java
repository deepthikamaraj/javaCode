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

import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TAlgmntSalesRole 
 * The corresponding mapping table is t_algmnt_sales_role 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntSalesRoles", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole"),
		@NamedQuery(name = "FindAllTAlgmntSalesRoleWithTenant", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tenantId = ?1 AND myTAlgmntSalesRole.algmntId =?2 AND myTAlgmntSalesRole.bussUnitId = ?3 AND myTAlgmntSalesRole.salesTeamId = ?4 AND myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId = ?5"),
		@NamedQuery(name = "CountTAlgmntSalesRoles", query = "Select Count(c) from TAlgmntSalesRole c"),
		   @NamedQuery(name = "FindRoleDetails", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId = ?1 AND myTAlgmntSalesRole.roleName = ?2 AND myTAlgmntSalesRole.tenantId = ?3"),
	   // @NamedQuery(name = "FindTAlgmntSalesRoleByTPers", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tPers = ?1 "),
		//@NamedQuery(name = "CountTAlgmntSalesRolesByTPers", query = "select Count(*) from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tPers = ?1 "),
		@NamedQuery(name = "FindTAlgmntSalesRoleByTAlgmntSalesHier", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "CountTAlgmntSalesRolesByTAlgmntSalesHier", query = "select Count(*) from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "FindTAlgmntSalesRoleByTOrgRole", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tOrgRole = ?1 "),
		@NamedQuery(name = "CountTAlgmntSalesRolesByTOrgRole", query = "select Count(*) from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tOrgRole = ?1 "),
		@NamedQuery(name = "getTAlignmentSalesRoleIdBySalesHierId", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole where myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId = ?1 AND myTAlgmntSalesRole.tenantId =?2 "),
		@NamedQuery(name = "FindActiveRolesInSalesAlgmntRole", query = "select mytalgmntsalesrole.tAlgmntSalesRoleId.orgRoleId,mytalgmntsalesrole.roleName from TAlgmntSalesRole mytalgmntsalesrole where mytalgmntsalesrole.tAlgmntSalesRoleId.orgRoleId IN (select mytalgmntsalesrole.tOrgRole.orgRoleId from mytalgmntsalesrole.tOrgRole where mytalgmntsalesrole.tOrgRole.activeFlag = 'Y') and mytalgmntsalesrole.activeFlag='Y' and myTAlgmntSalesRole.tenantId=?1 "),
		//@NamedQuery(name = "FindActiveMappedRolesInSalesAlgmntRole", query = "select count(*) from TAlgmntSalesRole role join role.tEmpAlgmntss emp where role.tAlgmntSalesRoleId.orgRoleId =?1 and role.tenantId =?2 and emp.activeFlag = role.activeFlag and emp.activeFlag = 'Y'"),
		@NamedQuery(name = "FindActiveMappedRolesInAlgmntSalesRole", query = "select count(*) from TAlgmntSalesRole role where role.tAlgmntSalesRoleId.orgRoleId =?1 and role.tenantId =?2 and  role.activeFlag = 'Y'"),
		@NamedQuery(name = "FindActiveTAlgmntSalesRole", query = "select myTAlgmntSalesRole from TAlgmntSalesRole myTAlgmntSalesRole " +
				"where myTAlgmntSalesRole.algmntId = ?1 AND myTAlgmntSalesRole.bussUnitId = ?2 AND myTAlgmntSalesRole.salesTeamId = ?3 AND myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId = ?4 " +
				"AND myTAlgmntSalesRole.tenantId = ?5 AND myTAlgmntSalesRole.activeFlag = ?6 ")
})
@Table(name = "t_algmnt_sales_role")
public class TAlgmntSalesRole implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAlgmntSalesRoleId tAlgmntSalesRoleId;

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
	@Length(max = 100)
	@Column(name = "role_name", nullable = true, length = 100)
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
	/*@Column(name = "sys_role_id", nullable = true, length = 255)
	private Integer sysRoleId;*/

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

	/*@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TPers tPers;*/
	
	@ManyToOne
	@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAlgmntSalesHier tAlgmntSalesHier;

	@ManyToOne
	@JoinColumn(name = "org_role_id", referencedColumnName = "org_role_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TOrgRole tOrgRole;
	
		/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesRole")
	private Set<TEmpAlgmnt> tEmpAlgmntss;
*/
	/**
	 *
	 * @generated
	 */
	public TAlgmntSalesRole() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTAlgmntSalesRoleId(final TAlgmntSalesRoleId tAlgmntSalesRoleId) {
		this.tAlgmntSalesRoleId = tAlgmntSalesRoleId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesRoleId getTAlgmntSalesRoleId() {
		return this.tAlgmntSalesRoleId;
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

	/*public void setSysRoleId(final Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getSysRoleId() {
		return this.sysRoleId;
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */

	public Long getSalesTeamId() {
		return this.salesTeamId;
	}

	/*public TPers getTPers() {
		return this.tPers;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTPers(final TPers tPers) {
		this.tPers = tPers;
		}*/
	/**
	 * 
	 * @generated
	 */
	
	public TAlgmntSalesHier getTAlgmntSalesHier() {
		return this.tAlgmntSalesHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesHier(final TAlgmntSalesHier tAlgmntSalesHier) {
		this.tAlgmntSalesHier = tAlgmntSalesHier;
		if (this.tAlgmntSalesHier != null && this.tAlgmntSalesHier.getSalesHierId() != null) {

			this.tAlgmntSalesRoleId.setSalesHierId(this.tAlgmntSalesHier.getSalesHierId());

		}

	}
	/**
	 * 
	 * @generated
	 */
	/*public Set<TEmpAlgmnt> getTEmpAlgmntss() {
		return this.tEmpAlgmntss;
	}*/
	/**
	 * 
	 * @generated
	 */
	/*public void setTEmpAlgmntss(final Set<TEmpAlgmnt> tEmpAlgmntss) {
		this.tEmpAlgmntss = tEmpAlgmntss;
	}*/


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
		if (this.tOrgRole != null && this.tOrgRole.getOrgRoleId() != null) {

			this.tAlgmntSalesRoleId.setOrgRoleId(this.tOrgRole.getOrgRoleId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntSalesRole that) {
		setTAlgmntSalesRoleId(that.getTAlgmntSalesRoleId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setRoleName(that.getRoleName());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		//setSysRoleId(that.getSysRoleId());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tAlgmntSalesRoleId == null) ? 0 : tAlgmntSalesRoleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAlgmntSalesRoleId=[").append(tAlgmntSalesRoleId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("roleName=[").append(roleName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		//buffer.append("sysRoleId=[").append(sysRoleId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");

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
		final TAlgmntSalesRole other = (TAlgmntSalesRole) obj;
		if (tAlgmntSalesRoleId == null) {
			if (other.tAlgmntSalesRoleId != null) {
				return false;
			}
		} else if (!tAlgmntSalesRoleId.equals(other.tAlgmntSalesRoleId)) {
			return false;
		}
		return true;
	}
}
