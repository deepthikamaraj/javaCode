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

/** 
 * JPA class representing TDsMirror 
 * The corresponding mapping table is t_ds_mirror 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTDsMirrors", query = "select myTDsMirror from TDsMirror myTDsMirror"),
		@NamedQuery(name = "CountTDsMirrors", query = "Select Count(c) from TDsMirror c"),
		@NamedQuery(name = "FindTDsMirrorByTDsStgByDsId", query = "select myTDsMirror from TDsMirror myTDsMirror where myTDsMirror.tDsStgByDsId = ?1 "),
		@NamedQuery(name = "CountTDsMirrorsByTDsStgByDsId", query = "select Count(myTDsMirror) from TDsMirror myTDsMirror where myTDsMirror.tDsStgByDsId = ?1 "),
		@NamedQuery(name = "FindTDsMirrorByTDsStgByMirrorDsId", query = "select myTDsMirror from TDsMirror myTDsMirror where myTDsMirror.tDsStgByMirrorDsId = ?1 "),
		@NamedQuery(name = "CountTDsMirrorsByTDsStgByMirrorDsId", query = "select Count(myTDsMirror) from TDsMirror myTDsMirror where myTDsMirror.tDsStgByMirrorDsId = ?1 ") })
@Table(name = "t_ds_mirror", uniqueConstraints = @UniqueConstraint(columnNames = { "ds_mirror_id" }))
public class TDsMirror implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ds_mirror_id", nullable = false, length = 255)
	private Integer dsMirrorId;

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
	@JoinColumn(name = "ds_id", referencedColumnName = "ds_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TDsStg tDsStgByDsId;

	@ManyToOne
	@JoinColumn(name = "mirror_ds_id", referencedColumnName = "ds_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TDsStg tDsStgByMirrorDsId;

	/**
	 *
	 * @generated
	 */
	public TDsMirror() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setDsMirrorId(final Integer dsMirrorId) {
		this.dsMirrorId = dsMirrorId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getDsMirrorId() {
		return this.dsMirrorId;
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
	public TDsStg getTDsStgByDsId() {
		return this.tDsStgByDsId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsStgByDsId(final TDsStg tDsStgByDsId) {
		this.tDsStgByDsId = tDsStgByDsId;

	}

	/**
	 * 
	 * @generated
	 */
	public TDsStg getTDsStgByMirrorDsId() {
		return this.tDsStgByMirrorDsId;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTDsStgByMirrorDsId(final TDsStg tDsStgByMirrorDsId) {
		this.tDsStgByMirrorDsId = tDsStgByMirrorDsId;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TDsMirror that) {
		setDsMirrorId(that.getDsMirrorId());
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
		result = prime * result + ((dsMirrorId == null) ? 0 : dsMirrorId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("dsMirrorId=[").append(dsMirrorId).append("] ");
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
		final TDsMirror other = (TDsMirror) obj;
		if (dsMirrorId == null) {
			if (other.dsMirrorId != null) {
				return false;
			}
		} else if (!dsMirrorId.equals(other.dsMirrorId)) {
			return false;
		}
		return true;
	}
}
