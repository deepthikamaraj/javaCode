package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TRptType 
 * The corresponding mapping table is t_rpt_type 
 */

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = "FindAllTRptTypes", query = "select myTRptType from TRptType myTRptType"),
		@NamedQuery(name = "CountTRptTypes", query = "Select Count(c) from TRptType c") })
@Table(name = "t_rpt_type", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_type_id" }))
public class TRptType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
/*	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_type_id", nullable = false, length = 255)
	private Integer rptTypeId;*/
	
	@EmbeddedId
	@Valid
	private TRptTypeId tRptTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "rpt_type_name", nullable = false, length = 20)
	private String rptTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "rpt_type_desc", nullable = true, length = 50)
	private String rptTypeDesc;

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
/*
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptType")
	@NotAudited
	private Set<TRptConfig> tRptConfigss;*/

	/**
	 *
	 * @generated
	 */
	public TRptType() {
	}

	/**
	 * 
	 * @generated
	 */

	/*public void setRptTypeId(final Integer rptTypeId) {
		this.rptTypeId = rptTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getRptTypeId() {
		return this.rptTypeId;
	}*/

	public TRptTypeId gettRptTypeId() {
		return tRptTypeId;
	}

	public void settRptTypeId(TRptTypeId tRptTypeId) {
		this.tRptTypeId = tRptTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptTypeName(final String rptTypeName) {
		this.rptTypeName = rptTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptTypeName() {
		return this.rptTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptTypeDesc(final String rptTypeDesc) {
		this.rptTypeDesc = rptTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptTypeDesc() {
		return this.rptTypeDesc;
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
	/*public Set<TRptConfig> getTRptConfigss() {
		return this.tRptConfigss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptConfigss(final Set<TRptConfig> tRptConfigss) {
		this.tRptConfigss = tRptConfigss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptType that) {
		//setRptTypeId(that.getRptTypeId());
		settRptTypeId(that.gettRptTypeId());
		setRptTypeName(that.getRptTypeName());
		setRptTypeDesc(that.getRptTypeDesc());
		setActiveFlag(that.getActiveFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDate(that.getUpdateDate());
		setTenantId(that.getTenantId());
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tRptTypeId == null) ? 0 : tRptTypeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TRptType other = (TRptType) obj;
		if (tRptTypeId == null) {
			if (other.tRptTypeId != null)
				return false;
		} else if (!tRptTypeId.equals(other.tRptTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptType [tRptTypeId=" + tRptTypeId + ", rptTypeName="
				+ rptTypeName + ", rptTypeDesc=" + rptTypeDesc
				+ ", activeFlag=" + activeFlag + ", createdBy=" + createdBy
				+ ", createDt=" + createDt + ", updatedBy=" + updatedBy
				+ ", updateDate=" + updateDate + ", tenantId=" + tenantId
				+ "]";
	}

	
}
