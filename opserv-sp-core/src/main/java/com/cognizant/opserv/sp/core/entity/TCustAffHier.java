package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/** 
 * JPA class representing TCustAffHier 
 * The corresponding mapping table is t_cust_aff_hier 
 */
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAffHiers", query = "select myTCustAffHier from TCustAffHier myTCustAffHier"),
		@NamedQuery(name = "CountTCustAffHiers", query = "Select Count(c) from TCustAffHier c"),
		@NamedQuery(name = "FindTCustAffHierByTCustAlgmntAffByAffTypeId", query = "select myTCustAffHier from TCustAffHier myTCustAffHier where myTCustAffHier.tCustAlgmntAffByAffTypeId = ?1 "),
		@NamedQuery(name = "CountTCustAffHiersByTCustAlgmntAffByAffTypeId", query = "select Count(myTCustAffHier) from TCustAffHier myTCustAffHier where myTCustAffHier.tCustAlgmntAffByAffTypeId = ?1 "),
		@NamedQuery(name = "FindTCustAffHierByTCustAlgmntAffByPrnAffTypeId", query = "select myTCustAffHier from TCustAffHier myTCustAffHier where myTCustAffHier.tCustAlgmntAffByPrnAffTypeId = ?1 "),
		@NamedQuery(name = "CountTCustAffHiersByTCustAlgmntAffByPrnAffTypeId", query = "select Count(myTCustAffHier) from TCustAffHier myTCustAffHier where myTCustAffHier.tCustAlgmntAffByPrnAffTypeId = ?1 ") })
@Table(name = "t_cust_aff_hier", uniqueConstraints = @UniqueConstraint(columnNames = { "aff_hier_id" }))
public class TCustAffHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aff_hier_id", nullable = false, length = 255)
	private Integer affHierId;

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
	@JoinColumn(name = "aff_type_id", referencedColumnName = "aff_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCustAlgmntAff tCustAlgmntAffByAffTypeId;

	@ManyToOne
	@JoinColumn(name = "prn_aff_type_id", referencedColumnName = "aff_type_id", nullable = true, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCustAlgmntAff tCustAlgmntAffByPrnAffTypeId;

	/**
	 *
	 * @generated
	 */
	public TCustAffHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffHierId(final Integer affHierId) {
		this.affHierId = affHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAffHierId() {
		return this.affHierId;
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
	public TCustAlgmntAff getTCustAlgmntAffByAffTypeId() {
		return this.tCustAlgmntAffByAffTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntAffByAffTypeId(final TCustAlgmntAff tCustAlgmntAffByAffTypeId) {
		this.tCustAlgmntAffByAffTypeId = tCustAlgmntAffByAffTypeId;

	}

	/**
	 * 
	 * @generated
	 */
	public TCustAlgmntAff getTCustAlgmntAffByPrnAffTypeId() {
		return this.tCustAlgmntAffByPrnAffTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntAffByPrnAffTypeId(final TCustAlgmntAff tCustAlgmntAffByPrnAffTypeId) {
		this.tCustAlgmntAffByPrnAffTypeId = tCustAlgmntAffByPrnAffTypeId;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCustAffHier that) {
		setAffHierId(that.getAffHierId());
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
		result = prime * result + ((affHierId == null) ? 0 : affHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("affHierId=[").append(affHierId).append("] ");
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
		final TCustAffHier other = (TCustAffHier) obj;
		if (affHierId == null) {
			if (other.affHierId != null) {
				return false;
			}
		} else if (!affHierId.equals(other.affHierId)) {
			return false;
		}
		return true;
	}
}
