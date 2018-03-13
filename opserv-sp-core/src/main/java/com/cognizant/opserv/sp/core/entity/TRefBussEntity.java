package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TRefBussEntity 
 * The corresponding mapping table is t_ref_buss_entity 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTRefBussEntitys", query = "select myTRefBussEntity from TRefBussEntity myTRefBussEntity"),
		@NamedQuery(name = "CountTRefBussEntitys", query = "Select Count(c) from TRefBussEntity c"),
		@NamedQuery(name = "FindTRefBussEntityByTBussEntityByEntityId", query = "select myTRefBussEntity from TRefBussEntity myTRefBussEntity where myTRefBussEntity.tBussEntityByEntityId = ?1 "),
		@NamedQuery(name = "CountTRefBussEntitysByTBussEntityByEntityId", query = "select Count(*) from TRefBussEntity myTRefBussEntity where myTRefBussEntity.tBussEntityByEntityId = ?1 "),
		@NamedQuery(name = "FindTRefBussEntityByTBussEntityByPrnEntityId", query = "select myTRefBussEntity from TRefBussEntity myTRefBussEntity where myTRefBussEntity.tBussEntityByPrnEntityId = ?1 "),
		@NamedQuery(name = "CountTRefBussEntitysByTBussEntityByPrnEntityId", query = "select Count(*) from TRefBussEntity myTRefBussEntity where myTRefBussEntity.tBussEntityByPrnEntityId = ?1 "),
		@NamedQuery(name = "getTRefBussEntitysByEntityId", query = "select myTRefBussEntity.tRefBussEntityId.prnEntityId from TRefBussEntity myTRefBussEntity where myTRefBussEntity.tRefBussEntityId.entityId = ?1 and myTRefBussEntity.tenantId =?2")
		})
@Table(name = "t_ref_buss_entity")
public class TRefBussEntity implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TRefBussEntityId tRefBussEntityId;

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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "entity_id", referencedColumnName = "entity_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussEntity tBussEntityByEntityId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "prn_entity_id", referencedColumnName = "entity_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussEntity tBussEntityByPrnEntityId;

	/**
	 *
	 * @generated
	 */
	public TRefBussEntity() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTRefBussEntityId(final TRefBussEntityId tRefBussEntityId) {
		this.tRefBussEntityId = tRefBussEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public TRefBussEntityId getTRefBussEntityId() {
		return this.tRefBussEntityId;
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
	public TBussEntity getTBussEntityByEntityId() {
		return this.tBussEntityByEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussEntityByEntityId(final TBussEntity tBussEntityByEntityId) {
		this.tBussEntityByEntityId = tBussEntityByEntityId;
		if (this.tBussEntityByEntityId != null && this.tBussEntityByEntityId.getEntityId() != null) {

			this.tRefBussEntityId.setEntityId(this.tBussEntityByEntityId.getEntityId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TBussEntity getTBussEntityByPrnEntityId() {
		return this.tBussEntityByPrnEntityId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussEntityByPrnEntityId(final TBussEntity tBussEntityByPrnEntityId) {
		this.tBussEntityByPrnEntityId = tBussEntityByPrnEntityId;
		if (this.tBussEntityByPrnEntityId != null && this.tBussEntityByPrnEntityId.getEntityId() != null) {

			this.tRefBussEntityId.setPrnEntityId(this.tBussEntityByPrnEntityId.getEntityId());

		}

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRefBussEntity that) {
		setTRefBussEntityId(that.getTRefBussEntityId());
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
		result = prime * result + ((tRefBussEntityId == null) ? 0 : tRefBussEntityId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tRefBussEntityId=[").append(tRefBussEntityId).append("] ");
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
		final TRefBussEntity other = (TRefBussEntity) obj;
		if (tRefBussEntityId == null) {
			if (other.tRefBussEntityId != null) {
				return false;
			}
		} else if (!tRefBussEntityId.equals(other.tRefBussEntityId)) {
			return false;
		}
		return true;
	}
}
