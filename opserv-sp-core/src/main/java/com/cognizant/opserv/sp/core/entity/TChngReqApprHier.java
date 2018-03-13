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

/** 
 * JPA class representing TChngReqApprHier 
 * The corresponding mapping table is t_chng_req_appr_hier 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqApprHiers", query = "select myTChngReqApprHier from TChngReqApprHier myTChngReqApprHier"),
		@NamedQuery(name = "CountTChngReqApprHiers", query = "Select Count(c) from TChngReqApprHier c") ,
		@NamedQuery(name = "FindPartialSalesHierarchyIds", query = "select myTChngReqApprHier.tChngReqApprHierId.apprSalesHierId from TChngReqApprHier myTChngReqApprHier where myTChngReqApprHier.tChngReqApprHierId.chngReqConfigId =?1  and myTChngReqApprHier.tenantId =?2 "),
		@NamedQuery(name = "FindSalesHierarchyIds", query = "select myTChngReqApprHier from " +
				"TChngReqApprHier myTChngReqApprHier where myTChngReqApprHier.tChngReqApprHierId.chngReqConfigId = ?1 and " +
				"myTChngReqApprHier.tenantId =?2 "),
		@NamedQuery(name = "FindActivSalesHier", query = "select myTChngReqApprHier.tChngReqApprHierId.apprSalesHierId from " +
				"TChngReqApprHier myTChngReqApprHier where myTChngReqApprHier.tChngReqApprHierId.chngReqConfigId = ?1 and " +
				"myTChngReqApprHier.activeFlag =?2 and myTChngReqApprHier.tenantId =?3 ")		
})
@Table(name = "t_chng_req_appr_hier")
public class TChngReqApprHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqApprHierId tChngReqApprHierId;

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
	public TChngReqApprHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqApprHierId(final TChngReqApprHierId tChngReqApprHierId) {
		this.tChngReqApprHierId = tChngReqApprHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqApprHierId getTChngReqApprHierId() {
		return this.tChngReqApprHierId;
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
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqApprHier that) {
		setTChngReqApprHierId(that.getTChngReqApprHierId());
		setActiveFlag(that.getActiveFlag());
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
		result = prime * result + ((tChngReqApprHierId == null) ? 0 : tChngReqApprHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqApprHierId=[").append(tChngReqApprHierId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
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
		final TChngReqApprHier other = (TChngReqApprHier) obj;
		if (tChngReqApprHierId == null) {
			if (other.tChngReqApprHierId != null) {
				return false;
			}
		} else if (!tChngReqApprHierId.equals(other.tChngReqApprHierId)) {
			return false;
		}
		return true;
	}
}
