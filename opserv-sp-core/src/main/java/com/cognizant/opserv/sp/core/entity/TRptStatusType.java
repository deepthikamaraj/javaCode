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
 * JPA class representing TRptStatusType 
 * The corresponding mapping table is t_rpt_status_type 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTRptStatusTypes", query = "select myTRptStatusType from TRptStatusType myTRptStatusType"),
		@NamedQuery(name = "FindAllTRptStatusTypesById", query = "select myTRptStatusType from TRptStatusType myTRptStatusType where myTRptStatusType.tRptStatusTypeId.rptStatusTypeId = ?1 and myTRptStatusType.tenantId = ?2"),
		@NamedQuery(name = "CountTRptStatusTypes", query = "Select Count(c) from TRptStatusType c") })
@Table(name = "t_rpt_status_type", uniqueConstraints = @UniqueConstraint(columnNames = { "rpt_status_type_id" }))
public class TRptStatusType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rpt_status_type_id", nullable = false, length = 255)
	private Integer rptStatusTypeId;*/

	@EmbeddedId
	@Valid
	private TRptStatusTypeId tRptStatusTypeId;
	
	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 40)
	@Column(name = "rpt_status_name", nullable = false, length = 40)
	private String rptStatusName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "rpt_status_desc", nullable = true, length = 50)
	private String rptStatusDesc;

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

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptStatusType")
	private Set<TRptTUsrRptPref> tRptTUsrRptPrefss;*/

	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tRptStatusType")
	@NotAudited
	private Set<TRpt> tRptss;*/

	/**
	 *
	 * @generated
	 */
	public TRptStatusType() {
	}

	/**
	 * 
	 * @generated
	 */

	public TRptStatusTypeId gettRptStatusTypeId() {
		return tRptStatusTypeId;
	}

	public void settRptStatusTypeId(TRptStatusTypeId tRptStatusTypeId) {
		this.tRptStatusTypeId = tRptStatusTypeId;
	}

	/*public void setRptStatusTypeId(final Integer rptStatusTypeId) {
		this.rptStatusTypeId = rptStatusTypeId;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public Integer getRptStatusTypeId() {
		return this.rptStatusTypeId;
	}
*/
	/**
	 * 
	 * @generated
	 */

	public void setRptStatusName(final String rptStatusName) {
		this.rptStatusName = rptStatusName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptStatusName() {
		return this.rptStatusName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setRptStatusDesc(final String rptStatusDesc) {
		this.rptStatusDesc = rptStatusDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getRptStatusDesc() {
		return this.rptStatusDesc;
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
	/*public Set<TRptTUsrRptPref> getTRptTUsrRptPrefss() {
		return this.tRptTUsrRptPrefss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public void setTRptTUsrRptPrefss(final Set<TRptTUsrRptPref> tRptTUsrRptPrefss) {
		this.tRptTUsrRptPrefss = tRptTUsrRptPrefss;
	}*/

	/**
	 * 
	 * @generated
	 */
	/*public Set<TRpt> getTRptss() {
		return this.tRptss;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTRptss(final Set<TRpt> tRptss) {
		this.tRptss = tRptss;
	}*/

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TRptStatusType that) {
		//setRptStatusTypeId(that.getRptStatusTypeId());
		settRptStatusTypeId(that.gettRptStatusTypeId());
		setRptStatusName(that.getRptStatusName());
		setRptStatusDesc(that.getRptStatusDesc());
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
		result = prime
				* result
				+ ((tRptStatusTypeId == null) ? 0 : tRptStatusTypeId.hashCode());
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
		TRptStatusType other = (TRptStatusType) obj;
		if (tRptStatusTypeId == null) {
			if (other.tRptStatusTypeId != null)
				return false;
		} else if (!tRptStatusTypeId.equals(other.tRptStatusTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TRptStatusType [tRptStatusTypeId=" + tRptStatusTypeId
				+ ", rptStatusName=" + rptStatusName + ", rptStatusDesc="
				+ rptStatusDesc + ", activeFlag=" + activeFlag + ", createdBy="
				+ createdBy + ", createDt=" + createDt + ", updatedBy="
				+ updatedBy + ", updateDate=" + updateDate + ", tenantId="
				+ tenantId + "]";
	}

}
