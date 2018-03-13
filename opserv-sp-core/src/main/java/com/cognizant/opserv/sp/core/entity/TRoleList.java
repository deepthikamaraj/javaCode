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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRoleList 
 * The corresponding mapping table is t_role_list 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRoleLists", query = "select myTRoleList from TRoleList myTRoleList"),
		@NamedQuery(name = "CountTRoleLists", query = "Select Count(c) from TRoleList c"),
		@NamedQuery(name = "FindTRoleListByTRoleList", query = "select myTRoleList from TRoleList myTRoleList where myTRoleList.tRoleList = ?1 "),
		@NamedQuery(name = "CountTRoleListsByTRoleList", query = "select Count(myTRoleList) from TRoleList myTRoleList where myTRoleList.tRoleList = ?1 "),
		@NamedQuery(name = "FindTRoleListByMaxId",query = "select Max(myTRoleList.roleId) from TRoleList myTRoleList where myTRoleList.activeFlag = ?1 and myTRoleList.tenantId = ?2"),
		@NamedQuery(name = "FindTRoleListByMaxRoleId",query = "select Max(myTRoleList.roleId) from TRoleList myTRoleList where myTRoleList.tenantId = ?1" ),
		@NamedQuery(name = "FindRoleIdAndRoleName",query = "select myTRoleList.roleId,myTRoleList.roleName from TRoleList myTRoleList where myTRoleList.activeFlag = ?1 and myTRoleList.tenantId = ?2")})
@Table(name = "t_role_list", uniqueConstraints = @UniqueConstraint(columnNames = { "role_id" }))
public class TRoleList implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id", nullable = false, length = 255)
	private Integer roleId;

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
	@Length(max = 150)
	@Column(name = "role_desc", nullable = true, length = 150)
	private String roleDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "def_role_flag", nullable = true, length = 1)
	private Character defRoleFlag;

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
	@Temporal(TemporalType.DATE)
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Temporal(TemporalType.DATE)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	@Column(name = "prn_role_id", nullable = true, length = 255)
	private Integer prnRoleId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_role_id", referencedColumnName = "role_id", nullable = true, insertable = false, updatable = false)
	@Valid
	@NotAudited
	private TRoleList tRoleList;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRoleList")
	@NotAudited
	private Set<TRoleList> tRoleListss;

	/**
	 *
	 * @generated
	 */
	public TRoleList() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setRoleId(final Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getRoleId() {
		return this.roleId;
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

	public void setDefRoleFlag(final Character defRoleFlag) {
		this.defRoleFlag = defRoleFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDefRoleFlag() {
		return this.defRoleFlag;
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

	public Integer getPrnRoleId() {
		return prnRoleId;
	}

	public void setPrnRoleId(Integer prnRoleId) {
		this.prnRoleId = prnRoleId;
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
	public Set<TRoleList> getTRoleListss() {
		return this.tRoleListss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRoleListss(final Set<TRoleList> tRoleListss) {
		this.tRoleListss = tRoleListss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRoleList that) {
		setRoleId(that.getRoleId());
		setRoleName(that.getRoleName());
		setRoleDesc(that.getRoleDesc());
		setDefRoleFlag(that.getDefRoleFlag());
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
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("roleId=[").append(roleId).append("] ");
		buffer.append("roleName=[").append(roleName).append("] ");
		buffer.append("roleDesc=[").append(roleDesc).append("] ");
		buffer.append("defRoleFlag=[").append(defRoleFlag).append("] ");
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
		final TRoleList other = (TRoleList) obj;
		if (roleId == null) {
			if (other.roleId != null) {
				return false;
			}
		} else if (!roleId.equals(other.roleId)) {
			return false;
		}
		return true;
	}
}
