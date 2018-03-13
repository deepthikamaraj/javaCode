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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TChngReqApprType 
 * The corresponding mapping table is t_chng_req_appr_type 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqApprTypes", query = "select myTChngReqApprType from TChngReqApprType myTChngReqApprType"),
		@NamedQuery(name = "CountTChngReqApprTypes", query = "Select Count(c) from TChngReqApprType c"),
		@NamedQuery(name = "FindAllTCRApprType", query = "select myTChngReqApprType from TChngReqApprType myTChngReqApprType where myTChngReqApprType.tenantId = ?1 AND  myTChngReqApprType.tChngReqApprTypeId.localeId= ?2 ",
				hints = {
				@QueryHint(name = "org.hibernate.cacheable", value = "true"),
				@QueryHint(name = "org.hibernate.cacheRegion", value = "query.namedQueryCache")

		}),
		@NamedQuery(name = "findTChngReqApprTypeByChangeReqConfigPrapprtypeid", query = "Select myTChngReqApprType.apprTypeDesc from TChngReqApprType myTChngReqApprType where myTChngReqApprType.tenantId = ?1  and myTChngReqApprType.tChngReqApprTypeId.apprTypeId =?2"),
		@NamedQuery(name = "findApprTypes", query = "Select myTChngReqApprType.tChngReqApprTypeId.apprTypeId, myTChngReqApprType.apprTypeDesc from TChngReqApprType myTChngReqApprType where myTChngReqApprType.tenantId = ?1  and myTChngReqApprType.tChngReqApprTypeId.localeId =?2 and myTChngReqApprType.activeFlag ='Y'")
		})
@Table(name = "t_chng_req_appr_type")
public class TChngReqApprType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TChngReqApprTypeId tChngReqApprTypeId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "appr_type_cd", nullable = false, length = 20)
	private String apprTypeCd;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 20)
	@Column(name = "appr_type_name", nullable = false, length = 20)
	private String apprTypeName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "appr_type_desc", nullable = true, length = 50)
	private String apprTypeDesc;

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
	public TChngReqApprType() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTChngReqApprTypeId(final TChngReqApprTypeId tChngReqApprTypeId) {
		this.tChngReqApprTypeId = tChngReqApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqApprTypeId getTChngReqApprTypeId() {
		return this.tChngReqApprTypeId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprTypeCd(final String apprTypeCd) {
		this.apprTypeCd = apprTypeCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getApprTypeCd() {
		return this.apprTypeCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprTypeName(final String apprTypeName) {
		this.apprTypeName = apprTypeName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getApprTypeName() {
		return this.apprTypeName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setApprTypeDesc(final String apprTypeDesc) {
		this.apprTypeDesc = apprTypeDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getApprTypeDesc() {
		return this.apprTypeDesc;
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
	public void copy(final TChngReqApprType that) {
		setTChngReqApprTypeId(that.getTChngReqApprTypeId());
		setApprTypeCd(that.getApprTypeCd());
		setApprTypeName(that.getApprTypeName());
		setApprTypeDesc(that.getApprTypeDesc());
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
		result = prime * result + ((tChngReqApprTypeId == null) ? 0 : tChngReqApprTypeId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tChngReqApprTypeId=[").append(tChngReqApprTypeId).append("] ");
		buffer.append("apprTypeCd=[").append(apprTypeCd).append("] ");
		buffer.append("apprTypeName=[").append(apprTypeName).append("] ");
		buffer.append("apprTypeDesc=[").append(apprTypeDesc).append("] ");
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
		final TChngReqApprType other = (TChngReqApprType) obj;
		if (tChngReqApprTypeId == null) {
			if (other.tChngReqApprTypeId != null) {
				return false;
			}
		} else if (!tChngReqApprTypeId.equals(other.tChngReqApprTypeId)) {
			return false;
		}
		return true;
	}
}
