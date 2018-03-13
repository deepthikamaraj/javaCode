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
 * JPA class representing TAppConfig 
 * The corresponding mapping table is t_app_config 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAppConfigs", query = "select myTAppConfig from TAppConfig myTAppConfig"),
		@NamedQuery(name = "CountTAppConfigs", query = "Select Count(c) from TAppConfig c"),
		@NamedQuery(name = "FindTAppConfigByTOrg", query = "select myTAppConfig from TAppConfig myTAppConfig where myTAppConfig.tOrg = ?1 "),
		@NamedQuery(name = "CountTAppConfigsByTOrg", query = "select Count(myTAppConfig) from TAppConfig myTAppConfig where myTAppConfig.tOrg = ?1 "),
		@NamedQuery(name = "FindDefaultCountryID", query = "select myTAppConfig from TAppConfig myTAppConfig where myTAppConfig.tenantId = ?1 ") 
		 })
@Table(name = "t_app_config", uniqueConstraints = @UniqueConstraint(columnNames = { "org_id" }))
public class TAppConfig implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "org_id", nullable = false, length = 255)
	private Integer orgId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "sess_tmout", nullable = true, length = 255)
	private Integer sessTmout;

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
	
	@NotNull
	@Column(name = "def_country_id", nullable = true, length = 255)
	private Integer defCountryId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@ManyToOne
	@JoinColumn(name = "org_id", referencedColumnName = "org_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TOrg tOrg;

	/**
	 *
	 * @generated
	 */
	public TAppConfig() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setOrgId(final Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getOrgId() {
		return this.orgId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSessTmout(final Integer sessTmout) {
		this.sessTmout = sessTmout;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSessTmout() {
		return this.sessTmout;
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
	
	public Integer getDefCountryId() {
		return defCountryId;
	}

	public void setDefCountryId(Integer defCountryId) {
		this.defCountryId = defCountryId;
	}

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
	public TOrg getTOrg() {
		return this.tOrg;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrg(final TOrg tOrg) {
		this.tOrg = tOrg;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAppConfig that) {
		setOrgId(that.getOrgId());
		setSessTmout(that.getSessTmout());
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
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("orgId=[").append(orgId).append("] ");
		buffer.append("sessTmout=[").append(sessTmout).append("] ");
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
		final TAppConfig other = (TAppConfig) obj;
		if (orgId == null) {
			if (other.orgId != null) {
				return false;
			}
		} else if (!orgId.equals(other.orgId)) {
			return false;
		}
		return true;
	}
}
