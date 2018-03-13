package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TUsrWdg 
 * The corresponding mapping table is t_usr_wdg 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTUsrWdgs", query = "select myTUsrWdg from TUsrWdg myTUsrWdg"),
		@NamedQuery(name = "CountTUsrWdgs", query = "Select Count(c) from TUsrWdg c"),
		@NamedQuery(name = "FindTUsrWdgByTRoleWdg", query = "select myTUsrWdg from TUsrWdg myTUsrWdg where myTUsrWdg.tRoleWdg = ?1 "),
		@NamedQuery(name = "CountTUsrWdgsByTRoleWdg", query = "select Count(*) from TUsrWdg myTUsrWdg where myTUsrWdg.tRoleWdg = ?1 "),
		//@NamedQuery(name = "FindTUsrWdgByTUsrRole", query = "select myTUsrWdg from TUsrWdg myTUsrWdg where myTUsrWdg.tUsrRole = ?1 "),
		//@NamedQuery(name = "CountTUsrWdgsByTUsrRole", query = "select Count(*) from TUsrWdg myTUsrWdg where myTUsrWdg.tUsrRole = ?1 "),
		@NamedQuery(name = "FindTUsrWdgByTUsrRoleIdAndUsrId", query = "select myTUsrWdg from TUsrWdg myTUsrWdg where myTUsrWdg.tUsrWdgId.usrId= ?1 AND myTUsrWdg.tUsrWdgId.roleId =?2 AND myTUsrWdg.tenantId=?3 "),
		@NamedQuery(name = "FindTUsrWdgByTUsrRoleIdAndUsrIdAndWdgId", query = "select myTUsrWdg from TUsrWdg myTUsrWdg where myTUsrWdg.tUsrWdgId.usrId= ?1 AND myTUsrWdg.tUsrWdgId.roleId =?2 AND myTUsrWdg.tenantId=?3 AND myTUsrWdg.tUsrWdgId.wdgId=?4 ")
		
})
@Table(name = "t_usr_wdg")
public class TUsrWdg implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TUsrWdgId tUsrWdgId;

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
	@Column(name = "order_seq", nullable = true, length = 255)
	private Integer orderSeq;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "wdg_id", referencedColumnName = "wdg_id", nullable = false, insertable = false, updatable = false) })
	@Valid
	private TRoleWdg tRoleWdg;


	/**
	 *
	 * @generated
	 */
	public TUsrWdg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTUsrWdgId(final TUsrWdgId tUsrWdgId) {
		this.tUsrWdgId = tUsrWdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public TUsrWdgId getTUsrWdgId() {
		return this.tUsrWdgId;
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
	public TRoleWdg getTRoleWdg() {
		return this.tRoleWdg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTRoleWdg(final TRoleWdg tRoleWdg) {
		this.tRoleWdg = tRoleWdg;
		if (this.tRoleWdg != null && this.tRoleWdg.getTRoleWdgId() != null) {

			this.tUsrWdgId.setRoleId(this.tRoleWdg.getTRoleWdgId().getRoleId());

		}
		if (this.tRoleWdg != null && this.tRoleWdg.getTRoleWdgId() != null) {

			this.tUsrWdgId.setWdgId(this.tRoleWdg.getTRoleWdgId().getWdgId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	

	/**
	 * 
	 * @generated
	 */


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TUsrWdg that) {
		setTUsrWdgId(that.getTUsrWdgId());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setOrderSeq(that.getOrderSeq());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tUsrWdgId == null) ? 0 : tUsrWdgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tUsrWdgId=[").append(tUsrWdgId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("orderSeq=[").append(orderSeq).append("] ");

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
		final TUsrWdg other = (TUsrWdg) obj;
		if (tUsrWdgId == null) {
			if (other.tUsrWdgId != null) {
				return false;
			}
		} else if (!tUsrWdgId.equals(other.tUsrWdgId)) {
			return false;
		}
		return true;
	}
}
