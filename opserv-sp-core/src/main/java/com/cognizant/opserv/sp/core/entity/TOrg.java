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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TOrg 
 * The corresponding mapping table is t_org 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTOrgs", query = "select myTOrg from TOrg myTOrg"),
		@NamedQuery(name = "FindOrgByTenantId", query = "select myTOrg from TOrg myTOrg where myTOrg.tenantId = ?"),
		@NamedQuery(name = "CountTOrgs", query = "Select Count(c) from TOrg c") })
@Table(name = "t_org", uniqueConstraints = @UniqueConstraint(columnNames = { "org_id" }))
public class TOrg implements Serializable {
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
	@NotEmpty
	@Length(max = 100)
	@Column(name = "org_name", nullable = false, length = 100)
	private String orgName;

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
	private Date updateDt;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrg")
	private Set<TOrgRole> tOrgRoless;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrg")
	private Set<TBussUnit> tBussUnitss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrg")
	private Set<TBussRuleConfig> tBussRuleConfigss;

	@ManyToOne
	@JoinColumn(name = "prn_org_id", referencedColumnName = "org_id", nullable = true, insertable = true, updatable = true)
	@Valid
	private TOrg tOrg;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tOrg")
	private Set<TOrg> tOrgss;
	/**
	 *
	 * @generated
	 */
	public TOrg() {
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

	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getOrgName() {
		return this.orgName;
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
	public Set<TOrgRole> getTOrgRoless() {
		return this.tOrgRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgRoless(final Set<TOrgRole> tOrgRoless) {
		this.tOrgRoless = tOrgRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TBussUnit> getTBussUnitss() {
		return this.tBussUnitss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussUnitss(final Set<TBussUnit> tBussUnitss) {
		this.tBussUnitss = tBussUnitss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TBussRuleConfig> getTBussRuleConfigss() {
		return this.tBussRuleConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussRuleConfigss(final Set<TBussRuleConfig> tBussRuleConfigss) {
		this.tBussRuleConfigss = tBussRuleConfigss;
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
	 * 
	 * @generated
	 */
	public Set<TOrg> getTOrgss() {
		return this.tOrgss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgss(final Set<TOrg> tOrgss) {
		this.tOrgss = tOrgss;
	}
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TOrg that) {
		setOrgId(that.getOrgId());
		setOrgName(that.getOrgName());
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
		buffer.append("orgName=[").append(orgName).append("] ");
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
		final TOrg other = (TOrg) obj;
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
