package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * JPA class representing TWkflwStatus The corresponding mapping table is
 * t_wkflw_status
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTWkflwStatuss", query = "select myTWkflwStatus from TWkflwStatus myTWkflwStatus order by myTWkflwStatus.tenantId,myTWkflwStatus.tWkflwStatusId.localeId"),
		@NamedQuery(name = "CountTWkflwStatuss", query = "Select Count(c) from TWkflwStatus c"),
		@NamedQuery(name = "FindAllTWkflwStatusByTenantID", query = "select myTWkflwStatus from TWkflwStatus myTWkflwStatus where "
				+ "myTWkflwStatus.tWkflwStatusId.localeId = 'en_US' OR myTWkflwStatus.tWkflwStatusId.localeId = 'en_us' "
				+ "order by myTWkflwStatus.tenantId"),
	     @NamedQuery(name = "FindAllTWkflwStatus", query = "select myTWkflwStatus from TWkflwStatus myTWkflwStatus where myTWkflwStatus.tenantId = ?1 AND  myTWkflwStatus.tWkflwStatusId.localeId= ?2 ",
				hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		@NamedQuery(name = "FindAllTWkflwStatusByLoggedTenantId", query = "select myTWkflwStatus from TWkflwStatus myTWkflwStatus where "
				+ "myTWkflwStatus.tWkflwStatusId.localeId = 'en_US' OR myTWkflwStatus.tWkflwStatusId.localeId = 'en_us' "
				+ "and myTWkflwStatus.tenantId =?1") })
@Table(name = "t_wkflw_status", uniqueConstraints = @UniqueConstraint(columnNames = { "status_id" }))
public class TWkflwStatus implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	private TWkflwStatusId tWkflwStatusId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "status_name", nullable = false, length = 50)
	private String statusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "status_desc", nullable = true, length = 75)
	private String statusDesc;

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

	/*
	 * @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
	 * CascadeType.MERGE }, mappedBy = "tWkflwStatus") private Set<TSalesPos>
	 * tSalesPosess;
	 */

	/*
	 * @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
	 * CascadeType.MERGE }, mappedBy = "tWkflwStatus") private Set<TCustAff>
	 * tCustAffss;
	 */

	/**
	 * 
	 * @generated
	 */
	public TWkflwStatus() {
	}

	/**
	 * 
	 * @generated
	 */

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
	/*
	 * public Set<TSalesPos> getTSalesPosess() { return this.tSalesPosess; }
	 *//**
	 * 
	 * @generated
	 */
	/*
	 * public void setTSalesPosess(final Set<TSalesPos> tSalesPosess) {
	 * this.tSalesPosess = tSalesPosess; }
	 */

	/*    *//**
	 * 
	 * @generated
	 */
	/*
	 * public Set<TCustAff> getTCustAffss() { return this.tCustAffss; }
	 *//**
	 * 
	 * @generated
	 */
	/*
	 * public void setTCustAffss(final Set<TCustAff> tCustAffss) {
	 * this.tCustAffss = tCustAffss; }
	 */

	public TWkflwStatusId gettWkflwStatusId() {
		return tWkflwStatusId;
	}

	public void settWkflwStatusId(TWkflwStatusId tWkflwStatusId) {
		this.tWkflwStatusId = tWkflwStatusId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TWkflwStatus that) {
		settWkflwStatusId(that.tWkflwStatusId);
		setStatusName(that.getStatusName());
		setStatusDesc(that.getStatusDesc());
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
		result = prime * result
				+ ((tWkflwStatusId == null) ? 0 : tWkflwStatusId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("statusId=[").append(tWkflwStatusId).append("] ");
		buffer.append("statusName=[").append(statusName).append("] ");
		buffer.append("statusDesc=[").append(statusDesc).append("] ");
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
		final TWkflwStatus other = (TWkflwStatus) obj;
		if (tWkflwStatusId == null) {
			if (other.tWkflwStatusId != null) {
				return false;
			}
		} else if (!tWkflwStatusId.equals(other.tWkflwStatusId)) {
			return false;
		}
		return true;
	}
}
