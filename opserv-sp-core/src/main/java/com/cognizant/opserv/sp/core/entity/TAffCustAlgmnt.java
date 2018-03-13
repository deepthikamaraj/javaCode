package com.cognizant.opserv.sp.core.entity;

import com.cognizant.opserv.sp.core.entity.TAffCustAlgmntId;
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

/** 
 * JPA class representing TAffCustAlgmnt 
 * The corresponding mapping table is t_aff_cust_algmnt 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAffCustAlgmnts", query = "select myTAffCustAlgmnt from TAffCustAlgmnt myTAffCustAlgmnt"),
		@NamedQuery(name = "CountTAffCustAlgmnts", query = "Select Count(c) from TAffCustAlgmnt c"),
		@NamedQuery(name = "FindTAffCustAlgmntByTCustAlgmntByAffCustAlgmntId", query = "select myTAffCustAlgmnt from TAffCustAlgmnt myTAffCustAlgmnt where myTAffCustAlgmnt.tCustAlgmntByAffCustAlgmntId = ?1 "),
		@NamedQuery(name = "CountTAffCustAlgmntsByTCustAlgmntByAffCustAlgmntId", query = "select Count(*) from TAffCustAlgmnt myTAffCustAlgmnt where myTAffCustAlgmnt.tCustAlgmntByAffCustAlgmntId = ?1 "),
		@NamedQuery(name = "FindTAffCustAlgmntByTCustAlgmntByCustAlgmntId", query = "select myTAffCustAlgmnt from TAffCustAlgmnt myTAffCustAlgmnt where myTAffCustAlgmnt.tCustAlgmntByCustAlgmntId = ?1 "),
		@NamedQuery(name = "GetAffCustAlgmntIdsByAlgmntIds", query = "select myTAffCustAlgmnt.tAffCustAlgmntId.affCustAlgmntId from TAffCustAlgmnt myTAffCustAlgmnt where myTAffCustAlgmnt.tAffCustAlgmntId.custAlgmntId IN ?1 and myTAffCustAlgmnt.tenantId=?2 and myTAffCustAlgmnt.activeFlag='Y'"),
		@NamedQuery(name = "GetAffCustAlgmntsByAlgmntIds", query = "select myTAffCustAlgmnt from TAffCustAlgmnt myTAffCustAlgmnt where myTAffCustAlgmnt.tAffCustAlgmntId.affCustAlgmntId IN ?1 and myTAffCustAlgmnt.tenantId=?2 and myTAffCustAlgmnt.activeFlag=?3")
		})
@Table(name = "t_aff_cust_algmnt")
public class TAffCustAlgmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAffCustAlgmntId tAffCustAlgmntId;

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

	@ManyToOne
	@JoinColumn(name = "aff_cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustAlgmnt tCustAlgmntByAffCustAlgmntId;

	@ManyToOne
	@JoinColumn(name = "cust_algmnt_id", referencedColumnName = "cust_algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TCustAlgmnt tCustAlgmntByCustAlgmntId;

	/**
	 *
	 * @generated
	 */
	public TAffCustAlgmnt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTAffCustAlgmntId(final TAffCustAlgmntId tAffCustAlgmntId) {
		this.tAffCustAlgmntId = tAffCustAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAffCustAlgmntId getTAffCustAlgmntId() {
		return this.tAffCustAlgmntId;
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
	public TCustAlgmnt getTCustAlgmntByAffCustAlgmntId() {
		return this.tCustAlgmntByAffCustAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntByAffCustAlgmntId(final TCustAlgmnt tCustAlgmntByAffCustAlgmntId) {
		this.tCustAlgmntByAffCustAlgmntId = tCustAlgmntByAffCustAlgmntId;
		if (this.tCustAlgmntByAffCustAlgmntId != null && this.tCustAlgmntByAffCustAlgmntId.getCustAlgmntId() != null) {

			this.tAffCustAlgmntId.setAffCustAlgmntId(this.tCustAlgmntByAffCustAlgmntId.getCustAlgmntId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TCustAlgmnt getTCustAlgmntByCustAlgmntId() {
		return this.tCustAlgmntByCustAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntByCustAlgmntId(final TCustAlgmnt tCustAlgmntByCustAlgmntId) {
		this.tCustAlgmntByCustAlgmntId = tCustAlgmntByCustAlgmntId;
		if (this.tCustAlgmntByCustAlgmntId != null && this.tCustAlgmntByCustAlgmntId.getCustAlgmntId() != null) {

			this.tAffCustAlgmntId.setCustAlgmntId(this.tCustAlgmntByCustAlgmntId.getCustAlgmntId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAffCustAlgmnt that) {
		setTAffCustAlgmntId(that.getTAffCustAlgmntId());
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
		result = prime * result + ((tAffCustAlgmntId == null) ? 0 : tAffCustAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAffCustAlgmntId=[").append(tAffCustAlgmntId).append("] ");
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
		final TAffCustAlgmnt other = (TAffCustAlgmnt) obj;
		if (tAffCustAlgmntId == null) {
			if (other.tAffCustAlgmntId != null) {
				return false;
			}
		} else if (!tAffCustAlgmntId.equals(other.tAffCustAlgmntId)) {
			return false;
		}
		return true;
	}
}
