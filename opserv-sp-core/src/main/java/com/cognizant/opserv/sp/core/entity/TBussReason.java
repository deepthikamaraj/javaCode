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
 * JPA class representing TBussReason 
 * The corresponding mapping table is t_buss_reason 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTBussReasons", query = "select myTBussReason from TBussReason myTBussReason"),
		@NamedQuery(name = "CountTBussReasons", query = "Select Count(c) from TBussReason c"),
		@NamedQuery(name = "FindTBussReasonBytrType", query = "select myTBussReason, myTChngReqBussReason.chngType, myTChngReqBussReason.chngReqBrId from TBussReason myTBussReason, TChngReqBussReason myTChngReqBussReason " + 
		"where myTChngReqBussReason.tBussReason.bussReasonId = myTBussReason.bussReasonId and myTChngReqBussReason.algmntId = ?1 " + 
				"and myTChngReqBussReason.bussUnitId = ?2 and myTChngReqBussReason.salesTeamId = ?3 and myTChngReqBussReason.tChngReqTrigger.triggerId = ?4 " + 
		"and myTChngReqBussReason.categoryId =?5 and myTBussReason.activeFlag = 'Y' and myTChngReqBussReason.activeFlag = 'Y' " + 
				"and myTChngReqBussReason.tenantId = ?6 order by myTBussReason.bussReasonId") })
@Table(name = "t_buss_reason", uniqueConstraints = @UniqueConstraint(columnNames = { "buss_reason_id" }))
public class TBussReason implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "buss_reason_id", nullable = false, length = 255)
	private Integer bussReasonId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 30)
	@Column(name = "reason_cd", nullable = false, length = 30)
	private String reasonCd;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "reason", nullable = false, length = 50)
	private String reason;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "reason_desc", nullable = true, length = 75)
	private String reasonDesc;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "active_flag", nullable = true, length = 1)
	private Character activeFlag;

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
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255)
	private Integer createdBy;

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
	@Column(name = "updated_by", nullable = true, length = 255)
	private Integer updatedBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tBussReason")
	private Set<TChngReqBussReason> tChngReqBussReasonss;

	/**
	 *
	 * @generated
	 */
	public TBussReason() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussReasonId(final Integer bussReasonId) {
		this.bussReasonId = bussReasonId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getBussReasonId() {
		return this.bussReasonId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReasonCd(final String reasonCd) {
		this.reasonCd = reasonCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getReasonCd() {
		return this.reasonCd;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReason(final String reason) {
		this.reason = reason;
	}

	/**
	 * 
	 * @generated
	 */
	public String getReason() {
		return this.reason;
	}

	/**
	 * 
	 * @generated
	 */

	public void setReasonDesc(final String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}

	/**
	 * 
	 * @generated
	 */
	public String getReasonDesc() {
		return this.reasonDesc;
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
	public Set<TChngReqBussReason> getTChngReqBussReasonss() {
		return this.tChngReqBussReasonss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqBussReasonss(final Set<TChngReqBussReason> tChngReqBussReasonss) {
		this.tChngReqBussReasonss = tChngReqBussReasonss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TBussReason that) {
		setBussReasonId(that.getBussReasonId());
		setReasonCd(that.getReasonCd());
		setReason(that.getReason());
		setReasonDesc(that.getReasonDesc());
		setActiveFlag(that.getActiveFlag());
		setCreateDt(that.getCreateDt());
		setCreatedBy(that.getCreatedBy());
		setUpdateDt(that.getUpdateDt());
		setUpdatedBy(that.getUpdatedBy());
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
		result = prime * result + ((bussReasonId == null) ? 0 : bussReasonId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("bussReasonId=[").append(bussReasonId).append("] ");
		buffer.append("reasonCd=[").append(reasonCd).append("] ");
		buffer.append("reason=[").append(reason).append("] ");
		buffer.append("reasonDesc=[").append(reasonDesc).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
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
		final TBussReason other = (TBussReason) obj;
		if (bussReasonId == null) {
			if (other.bussReasonId != null) {
				return false;
			}
		} else if (!bussReasonId.equals(other.bussReasonId)) {
			return false;
		}
		return true;
	}
}