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
 * JPA class representing TCallPlanType 
 * The corresponding mapping table is t_call_plan_type 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTCallPlanTypes", query = "select myTCallPlanType from TCallPlanType myTCallPlanType"),
		@NamedQuery(name = "CountTCallPlanTypes", query = "Select Count(c) from TCallPlanType c") })
@Table(name = "t_call_plan_type", uniqueConstraints = @UniqueConstraint(columnNames = { "call_plan_type_id" }))
public class TCallPlanType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "call_plan_type_id", nullable = false, length = 255)
	private Short callPlanTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "call_plan_type_desc", nullable = false, length = 50)
	private String callPlanTypeDesc;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCallPlanType")
	private Set<TCustCallPlan> tCustCallPlanss;

	/**
	 *
	 * @generated
//	 */
//	public TCallPlanType() {
//	}

	/**
	 * 
	 * @generated
	 */

	public void setCallPlanTypeId(final Short callPlanTypeId) {
		this.callPlanTypeId = callPlanTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Short getCallPlanTypeId() {
		return this.callPlanTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCallPlanTypeDesc(final String callPlanTypeDesc) {
		this.callPlanTypeDesc = callPlanTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCallPlanTypeDesc() {
		return this.callPlanTypeDesc;
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
		if (createDt == null) {
			Date x=null;
			this.createDt = x;
		} else {
			this.createDt = (Date) createDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt == null) {
			return null;
		} else {
			
			return (Date) this.createDt.clone();
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
		if (updateDt == null) {
			Date x=null;
			this.updateDt = x;
		} else {
			
			this.updateDt = (Date) updateDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt == null) {
			return null;
		} else {
			
			return (Date) this.updateDt.clone();
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
	public Set<TCustCallPlan> getTCustCallPlanss() {
		return this.tCustCallPlanss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustCallPlanss(final Set<TCustCallPlan> tCustCallPlanss) {
		this.tCustCallPlanss = tCustCallPlanss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCallPlanType that) {
		setCallPlanTypeId(that.getCallPlanTypeId());
		setCallPlanTypeDesc(that.getCallPlanTypeDesc());
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
		result = prime * result + ((callPlanTypeId == null) ? 0 : callPlanTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("callPlanTypeId=["+callPlanTypeId+"] ");
		buffer.append("callPlanTypeDesc=["+callPlanTypeDesc+"] ");
		buffer.append("activeFlag=["+activeFlag+"] ");
		buffer.append("createdBy=["+createdBy+"] ");
		buffer.append("createDt=["+createDt+"] ");
		buffer.append("updatedBy=["+updatedBy+"] ");
		buffer.append("updateDt=["+updateDt+"] ");
		buffer.append("tenantId=["+tenantId+"] ");

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
		final TCallPlanType other = (TCallPlanType) obj;
		if (callPlanTypeId == null) {
			if (other.callPlanTypeId != null) {
				return false;
			}
		} else if (!callPlanTypeId.equals(other.callPlanTypeId)) {
			return false;
		}
		return true;
	}
}
