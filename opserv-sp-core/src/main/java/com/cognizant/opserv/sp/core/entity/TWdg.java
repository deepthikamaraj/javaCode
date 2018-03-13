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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TWdg 
 * The corresponding mapping table is t_wdg 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTWdgs", query = "select myTWdg from TWdg myTWdg"),
		@NamedQuery(name = "CountTWdgs", query = "Select Count(c) from TWdg c"),
		@NamedQuery(name = "FindAllTWdgsbyWdgId", query = "select myTWdg from TWdg myTWdg where myTwdg.tWdgId.wdgId IN (?1) and myTwdg.tenantId=?2")})
@Table(name = "t_wdg")
public class TWdg implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TWdgId tWdgId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "wdg_name", nullable = false, length = 30)
	private String wdgName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "wdg_desc", nullable = true, length = 200)
	private String wdgDesc;

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
	public TWdg() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTWdgId(final TWdgId tWdgId) {
		this.tWdgId = tWdgId;
	}

	/**
	 * 
	 * @generated
	 */
	public TWdgId getTWdgId() {
		return this.tWdgId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setWdgName(final String wdgName) {
		this.wdgName = wdgName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getWdgName() {
		return this.wdgName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setWdgDesc(final String wdgDesc) {
		this.wdgDesc = wdgDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getWdgDesc() {
		return this.wdgDesc;
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
	public void copy(final TWdg that) {
		setTWdgId(that.getTWdgId());
		setWdgName(that.getWdgName());
		setWdgDesc(that.getWdgDesc());
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
		result = prime * result + ((tWdgId == null) ? 0 : tWdgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tWdgId=[").append(tWdgId).append("] ");
		buffer.append("wdgName=[").append(wdgName).append("] ");
		buffer.append("wdgDesc=[").append(wdgDesc).append("] ");
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
		final TWdg other = (TWdg) obj;
		if (tWdgId == null) {
			if (other.tWdgId != null) {
				return false;
			}
		} else if (!tWdgId.equals(other.tWdgId)) {
			return false;
		}
		return true;
	}
}
