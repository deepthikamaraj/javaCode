package com.cognizant.opserv.sp.core.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TRoleWdg 
 * The corresponding mapping table is t_role_wdg 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRoleWdgs", query = "select myTRoleWdg from TRoleWdg myTRoleWdg"),
		@NamedQuery(name = "CountTRoleWdgs", query = "Select Count(c) from TRoleWdg c"),
		@NamedQuery(name = "FindTRoleWdgByTRole", query = "select myTRoleWdg from TRoleWdg myTRoleWdg where myTRoleWdg.tRoleWdgId = ?1 "),
		@NamedQuery(name = "CountTRoleWdgsByTRole", query = "select Count(*) from TRoleWdg myTRoleWdg where myTRoleWdg.tRoleWdgId = ?1 "),		
		@NamedQuery(name = "FindRoleWids", query = "select myTRoleWdg from TRoleWdg myTRoleWdg where myTRoleWdg.tRoleWdgId.roleId = ?1 and myTRoleWdg.tenantId=?2 "),
		//@NamedQuery(name = "GetMandatoryInfo", query = "select  myTRoleWdg from TRoleWdg myTRoleWdg where myTRoleWdg IN (?1) "),
		@NamedQuery(name = "GetMandatoryInfo", query = "select  myTRoleWdg from TRoleWdg myTRoleWdg where myTRoleWdg.activeFlag=?1 "+
															"AND myTRoleWdg.tenantId=?2 "+
															"AND myTRoleWdg.tRoleWdgId.roleId=?3 ")
})
@Table(name = "t_role_wdg")
public class TRoleWdg implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRoleWdgId tRoleWdgId;

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
	@Column(name = "mandatory_flag", nullable = true, length = 1)
	private Character mandatoryFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "order_seq", nullable = true, length = 255)
	private Integer orderSeq;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "def_flag", nullable = true, length = 1)
	private Character defFlag;
	
	
	
	
	

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRoleWdg")
	private Set<TUsrWdg> tUsrWdgss;

	/**
	 *
	 * @generated
	 */
	public TRoleWdg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRoleWdgId(final TRoleWdgId tRoleWdgId) {
		this.tRoleWdgId = tRoleWdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRoleWdgId getTRoleWdgId() {
		return this.tRoleWdgId;
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

	public void setMandatoryFlag(final Character mandatoryFlag) {
		this.mandatoryFlag = mandatoryFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getMandatoryFlag() {
		return this.mandatoryFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrderSeq(final Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrderSeq() {
		return this.orderSeq;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDefFlag(final Character defFlag) {
		this.defFlag = defFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDefFlag() {
		return this.defFlag;
	}

	
	/**
	 * 
	 * @generated
	 */
	public Set<TUsrWdg> getTUsrWdgss() {
		return this.tUsrWdgss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTUsrWdgss(final Set<TUsrWdg> tUsrWdgss) {
		this.tUsrWdgss = tUsrWdgss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRoleWdg that) {
		setTRoleWdgId(that.getTRoleWdgId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setMandatoryFlag(that.getMandatoryFlag());
		setOrderSeq(that.getOrderSeq());
		setDefFlag(that.getDefFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tRoleWdgId == null) ? 0 : tRoleWdgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRoleWdgId=[").append(tRoleWdgId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("mandatoryFlag=[").append(mandatoryFlag).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");
		buffer.append("defFlag=[").append(defFlag).append("] ");

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
		final TRoleWdg other = (TRoleWdg) obj;
		if (tRoleWdgId == null) {
			if (other.tRoleWdgId != null) {
				return false;
			}
		} else if (!tRoleWdgId.equals(other.tRoleWdgId)) {
			return false;
		}
		return true;
	}
}
