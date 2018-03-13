package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TUsrMtrPref 
 * The corresponding mapping table is t_usr_mtr_pref 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "getMtrActiveFlag",query="select DISTINCT myTUsrMtrPref.mtrId from TUsrMtrPref myTUsrMtrPref where myTUsrMtrPref.roleId = ?1 and myTUsrMtrPref.usrId = ?2 and myTUsrMtrPref.salesPosId = ?3 and myTUsrMtrPref.salesHierId =?4 and myTUsrMtrPref.tenantId =?5 and myTUsrMtrPref.mtrId IN ?6 and myTUsrMtrPref.activeFlag ='Y'"),		
		@NamedQuery(name = "InactivateUsrMtrPrefsForSP",query="UPDATE TUsrMtrPref p set p.activeFlag='N' WHERE p.usrId = ?1 and p.roleId = ?2 and p.salesPosId =?3 and p.tenantId =?4 "),
		@NamedQuery(name = "GetUsrMtrPrefIdsByUsrRolSp",query="select DISTINCT myTUsrMtrPref.mtrId from TUsrMtrPref myTUsrMtrPref where myTUsrMtrPref.usrId = ?1 and myTUsrMtrPref.roleId = ?2 and myTUsrMtrPref.salesPosId = ?3 and myTUsrMtrPref.tenantId =?4 and myTUsrMtrPref.activeFlag ='Y'"),
		@NamedQuery(name = "GetUsrMtrPrefIdPrimaryIdsByUsrRolSp",query="select DISTINCT myTUsrMtrPref.mtrId,myTUsrMtrPref.seqNumber,myTUsrMtrPref.activeFlag from TUsrMtrPref myTUsrMtrPref where myTUsrMtrPref.usrId = ?1 and myTUsrMtrPref.roleId = ?2 and myTUsrMtrPref.salesPosId = ?3 and myTUsrMtrPref.tenantId =?4")
	   })

@Table(name = "t_usr_mtr_pref", uniqueConstraints = @UniqueConstraint(columnNames = { "seq_num" }))
public class TUsrMtrPref implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_num", nullable = false, length = 255)
	private Long seqNumber;
	
	@NotNull
	@Column(name = "role_id", nullable = false, length = 255)
	private Integer roleId;
	
	@NotNull
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;
	
	@NotNull
	@Column(name = "usr_id", nullable = false, length = 255)
	private Integer usrId;
	
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDt;

	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	@Column(name = "update_dt", nullable = true, length = 19)
	private Date updateDt;

	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	public TUsrMtrPref() {
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

	public void setTenantId(final Short tenantId) {
		this.tenantId = tenantId;
	}
	
	public Short getTenantId() {
		return this.tenantId;
	}

	public Long getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Long seqNumber) {
		this.seqNumber = seqNumber;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMtrId() {
		return mtrId;
	}

	public void setMtrId(Integer mtrId) {
		this.mtrId = mtrId;
	}

	public Integer getUsrId() {
		return usrId;
	}

	public void setUsrId(Integer usrId) {
		this.usrId = usrId;
	}

	public Long getSalesHierId() {
		return salesHierId;
	}

	public void setSalesHierId(Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	public Long getSalesPosId() {
		return salesPosId;
	}

	public void setSalesPosId(Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((seqNumber == null) ? 0 : seqNumber.hashCode());
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
		TUsrMtrPref other = (TUsrMtrPref) obj;
		if (seqNumber == null) {
			if (other.seqNumber != null)
				return false;
		} else if (!seqNumber.equals(other.seqNumber))
			return false;
		return true;
	}
	
	
}
