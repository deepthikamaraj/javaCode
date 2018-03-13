package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAffLoad 
 * The corresponding mapping table is t_aff_load 
 */

@Entity
@NamedQueries({ @NamedQuery(name = "FindAllTAffLoads", query = "select myTAffLoad from TAffLoad myTAffLoad"),
		@NamedQuery(name = "CountTAffLoads", query = "Select Count(c) from TAffLoad c"),
		@NamedQuery(name = "GetParentAffiliatedCustIdsByCustId", query = "select tAffLoad.affId,tAffLoad.custId from TAffLoad tAffLoad where tAffLoad.affCustId=?1 and tAffLoad.tenantId=?2 and tAffLoad.activeFlag=?3"),
		@NamedQuery(name = "GetChildAffiliatedCustIdsByCustId", query = "select tAffLoad.affId,tAffLoad.affCustId from TAffLoad tAffLoad where tAffLoad.custId=?1 and tAffLoad.tenantId=?2 and tAffLoad.activeFlag=?3"),
		@NamedQuery(name = "checkTCustAffHeirFrCustList", query = " select myTAffLoad.custId, myTAffLoad.affCustId ,myTAffLoad.activeFlag from TAffLoad myTAffLoad where (myTAffLoad.custId IN (?1) OR myTAffLoad.affCustId IN (?1))" +
				"  and myTAffLoad.tenantId = ?2 and myTAffLoad.activeFlag = 'Y' ")
		})
@Table(name = "t_aff_load", uniqueConstraints = @UniqueConstraint(columnNames = { "aff_id" }))
public class TAffLoad implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "aff_id", nullable = false, length = 255)
	private Long affId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "aff_cust_id", nullable = true, length = 255)
	private Integer affCustId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "cust_cd", nullable = false, length = 20)
	private String custCd;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 20)
	@Column(name = "aff_cust_cd", nullable = true, length = 20)
	private String affCustCd;

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
	public TAffLoad() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffId(final Long affId) {
		this.affId = affId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAffId() {
		return this.affId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustId() {
		return this.custId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffCustId(final Integer affCustId) {
		this.affCustId = affCustId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getAffCustId() {
		return this.affCustId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustCd(final String custCd) {
		this.custCd = custCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustCd() {
		return this.custCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffCustCd(final String affCustCd) {
		this.affCustCd = affCustCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAffCustCd() {
		return this.affCustCd;
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
	public void copy(final TAffLoad that) {
		setAffId(that.getAffId());
		setCustId(that.getCustId());
		setAffCustId(that.getAffCustId());
		setCustCd(that.getCustCd());
		setAffCustCd(that.getAffCustCd());
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
		result = prime * result + ((affId == null) ? 0 : affId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("affId=[").append(affId).append("] ");
		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("affCustId=[").append(affCustId).append("] ");
		buffer.append("custCd=[").append(custCd).append("] ");
		buffer.append("affCustCd=[").append(affCustCd).append("] ");
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
		final TAffLoad other = (TAffLoad) obj;
		if (affId == null) {
			if (other.affId != null) {
				return false;
			}
		} else if (!affId.equals(other.affId)) {
			return false;
		}
		return true;
	}
}
