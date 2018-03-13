package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TCommStatus 
 * The corresponding mapping table is t_comm_status 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCommStatuss", query = "select myTCommStatus from TCommStatus myTCommStatus"),
		@NamedQuery(name = "CountTCommStatuss", query = "Select Count(c) from TCommStatus c") })
@Table(name = "t_comm_status", uniqueConstraints = @UniqueConstraint(columnNames = { "comm_status_id" }))
public class TCommStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comm_status_id", nullable = false, length = 255)
	private Integer commStatusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "comm_status_name", nullable = false, length = 20)
	private String commStatusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "comm_status_desc", nullable = true, length = 50)
	private String commStatusDesc;

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
	@Column(name = "created_by", nullable = true, length = 255)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "create_dt", nullable = true, length = 19)
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
	private Date updateDate;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCommStatus")
	@NotAudited
	private Set<TComm> tCommss;

	/**
	 *
	 * @generated
	 */
	public TCommStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommStatusId(final Integer commStatusId) {
		this.commStatusId = commStatusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCommStatusId() {
		return this.commStatusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommStatusName(final String commStatusName) {
		this.commStatusName = commStatusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommStatusName() {
		return this.commStatusName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommStatusDesc(final String commStatusDesc) {
		this.commStatusDesc = commStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommStatusDesc() {
		return this.commStatusDesc;
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

	public void setUpdateDate(final Date updateDate) {
		if (updateDate != null) {
			this.updateDate = (Date) updateDate.clone();
		} else {
			this.updateDate = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDate() {
		if (this.updateDate != null) {
			return (Date) this.updateDate.clone();
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
	public Set<TComm> getTCommss() {
		return this.tCommss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommss(final Set<TComm> tCommss) {
		this.tCommss = tCommss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCommStatus that) {
		setCommStatusId(that.getCommStatusId());
		setCommStatusName(that.getCommStatusName());
		setCommStatusDesc(that.getCommStatusDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
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
		result = prime * result + ((commStatusId == null) ? 0 : commStatusId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("commStatusId=[").append(commStatusId).append("] ");
		buffer.append("commStatusName=[").append(commStatusName).append("] ");
		buffer.append("commStatusDesc=[").append(commStatusDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDate=[").append(updateDate).append("] ");
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
		final TCommStatus other = (TCommStatus) obj;
		if (commStatusId == null) {
			if (other.commStatusId != null) {
				return false;
			}
		} else if (!commStatusId.equals(other.commStatusId)) {
			return false;
		}
		return true;
	}
}
