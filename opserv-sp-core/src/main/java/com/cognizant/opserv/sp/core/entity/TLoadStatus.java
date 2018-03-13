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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TLoadStatus 
 * The corresponding mapping table is t_load_status 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTLoadStatuss", query = "select myTLoadStatus from TLoadStatus myTLoadStatus"),
		@NamedQuery(name = "CountTLoadStatuss", query = "Select Count(c) from TLoadStatus c") })
@Table(name = "t_load_status", uniqueConstraints = @UniqueConstraint(columnNames = { "status_id" }))
public class TLoadStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "status_name", nullable = false, length = 30)
	private String statusName;

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
	@Length(max = 20)
	@Column(name = "status_desc", nullable = true, length = 20)
	private String statusDesc;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "locale_id", nullable = true, length = 20)
	private String localeId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tLoadStatus")
	private Set<TTerrZipLoad> tTerrZipLoadss;

	/**
	 *
	 * @generated
	 */
	public TLoadStatus() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusId(final Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getStatusId() {
		return this.statusId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStatusName() {
		return this.statusName;
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

	public void setStatusDesc(final String statusDesc) {
		this.statusDesc = statusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStatusDesc() {
		return this.statusDesc;
	}

	/**
	 * 
	 * @generated
	 */

	public void setLocaleId(final String localeId) {
		this.localeId = localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getLocaleId() {
		return this.localeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TTerrZipLoad> getTTerrZipLoadss() {
		return this.tTerrZipLoadss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTerrZipLoadss(final Set<TTerrZipLoad> tTerrZipLoadss) {
		this.tTerrZipLoadss = tTerrZipLoadss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TLoadStatus that) {
		setStatusId(that.getStatusId());
		setStatusName(that.getStatusName());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setStatusDesc(that.getStatusDesc());
		setLocaleId(that.getLocaleId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("statusName=[").append(statusName).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("statusDesc=[").append(statusDesc).append("] ");
		buffer.append("localeId=[").append(localeId).append("] ");

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
		final TLoadStatus other = (TLoadStatus) obj;
		if (statusId == null) {
			if (other.statusId != null) {
				return false;
			}
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		return true;
	}
}
