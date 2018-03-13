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
 * JPA class representing TCommType 
 * The corresponding mapping table is t_comm_type 
 */

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = "FindAllTCommTypes", query = "select myTCommType from TCommType myTCommType"),
		@NamedQuery(name = "CountTCommTypes", query = "Select Count(c) from TCommType c") })
@Table(name = "t_comm_type", uniqueConstraints = @UniqueConstraint(columnNames = { "comm_type_id" }))
public class TCommType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comm_type_id", nullable = false, length = 255)
	private Integer commTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "comm_type_name", nullable = false, length = 20)
	private String commTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "comm_type_desc", nullable = true, length = 75)
	private String commTypeDesc;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCommType")
	@NotAudited
	private Set<TCommConfig> tCommConfigss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCommType")
	@NotAudited
	private Set<TComm> tCommss;

	/**
	 *
	 * @generated
	 */
	public TCommType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommTypeId(final Integer commTypeId) {
		this.commTypeId = commTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCommTypeId() {
		return this.commTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommTypeName(final String commTypeName) {
		this.commTypeName = commTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommTypeName() {
		return this.commTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCommTypeDesc(final String commTypeDesc) {
		this.commTypeDesc = commTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCommTypeDesc() {
		return this.commTypeDesc;
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
	public Set<TCommConfig> getTCommConfigss() {
		return this.tCommConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCommConfigss(final Set<TCommConfig> tCommConfigss) {
		this.tCommConfigss = tCommConfigss;
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
	public void copy(final TCommType that) {
		setCommTypeId(that.getCommTypeId());
		setCommTypeName(that.getCommTypeName());
		setCommTypeDesc(that.getCommTypeDesc());
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
		result = prime * result + ((commTypeId == null) ? 0 : commTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("commTypeId=[").append(commTypeId).append("] ");
		buffer.append("commTypeName=[").append(commTypeName).append("] ");
		buffer.append("commTypeDesc=[").append(commTypeDesc).append("] ");
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
		final TCommType other = (TCommType) obj;
		if (commTypeId == null) {
			if (other.commTypeId != null) {
				return false;
			}
		} else if (!commTypeId.equals(other.commTypeId)) {
			return false;
		}
		return true;
	}
}
