package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TBussWkflwWndw 
 * The corresponding mapping table is t_buss_wkflw_wndw 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussWkflwWndws", query = "select myTBussWkflwWndw from TBussWkflwWndw myTBussWkflwWndw"),
		@NamedQuery(name = "CountTBussWkflwWndws", query = "Select Count(c) from TBussWkflwWndw c"),
		@NamedQuery(name = "FindTBussWkflwWndwByTAlgmntSalesTeam", query = "select myTBussWkflwWndw from TBussWkflwWndw myTBussWkflwWndw where myTBussWkflwWndw.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "CountTBussWkflwWndwsByTAlgmntSalesTeam", query = "select Count(myTBussWkflwWndw) from TBussWkflwWndw myTBussWkflwWndw where myTBussWkflwWndw.tAlgmntSalesTeam = ?1 ") })
@Table(name = "t_buss_wkflw_wndw", uniqueConstraints = @UniqueConstraint(columnNames = { "wkflw_wndw_id" }))
public class TBussWkflwWndw implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "wkflw_wndw_id", nullable = false, length = 255)
	private Integer wkflwWndwId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "start_dt", nullable = false, length = 10)
	private Date startDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "end_dt", nullable = false, length = 10)
	private Date endDt;

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
	@NotNull
	@Column(name = "wkflw_id", nullable = false, length = 255)
	private Integer wkflwId;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	/**
	 *
	 * @generated
	 */
	public TBussWkflwWndw() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setWkflwWndwId(final Integer wkflwWndwId) {
		this.wkflwWndwId = wkflwWndwId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getWkflwWndwId() {
		return this.wkflwWndwId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStartDt(final Date startDt) {
		if (startDt != null) {
			this.startDt = (Date) startDt.clone();
		} else {
			this.startDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getStartDt() {
		if (this.startDt != null) {
			return (Date) this.startDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEndDt(final Date endDt) {
		if (endDt != null) {
			this.endDt = (Date) endDt.clone();
		} else {
			this.endDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEndDt() {
		if (this.endDt != null) {
			return (Date) this.endDt.clone();
		} else {
			return null;
		}
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

	public void setWkflwId(final Integer wkflwId) {
		this.wkflwId = wkflwId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getWkflwId() {
		return this.wkflwId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return this.tAlgmntSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussWkflwWndw that) {
		setWkflwWndwId(that.getWkflwWndwId());
		setStartDt(that.getStartDt());
		setEndDt(that.getEndDt());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setWkflwId(that.getWkflwId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((wkflwWndwId == null) ? 0 : wkflwWndwId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("wkflwWndwId=[").append(wkflwWndwId).append("] ");
		buffer.append("startDt=[").append(startDt).append("] ");
		buffer.append("endDt=[").append(endDt).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("wkflwId=[").append(wkflwId).append("] ");

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
		final TBussWkflwWndw other = (TBussWkflwWndw) obj;
		if (wkflwWndwId == null) {
			if (other.wkflwWndwId != null) {
				return false;
			}
		} else if (!wkflwWndwId.equals(other.wkflwWndwId)) {
			return false;
		}
		return true;
	}
}
