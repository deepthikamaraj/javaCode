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

/** 
 * JPA class representing TChngReqBussReason 
 * The corresponding mapping table is t_chng_req_buss_reason 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTChngReqBussReasons", query = "select myTChngReqBussReason from TChngReqBussReason myTChngReqBussReason"),
		@NamedQuery(name = "CountTChngReqBussReasons", query = "Select Count(c) from TChngReqBussReason c"),
		@NamedQuery(name = "FindTChngReqBussReasonByTChngReqTrigger", query = "select myTChngReqBussReason from TChngReqBussReason myTChngReqBussReason where myTChngReqBussReason.tChngReqTrigger = ?1 "),
		@NamedQuery(name = "CountTChngReqBussReasonsByTChngReqTrigger", query = "select Count(myTChngReqBussReason) from TChngReqBussReason myTChngReqBussReason where myTChngReqBussReason.tChngReqTrigger = ?1 "),
		@NamedQuery(name = "FindTChngReqBussReasonByTBussReason", query = "select myTChngReqBussReason from TChngReqBussReason myTChngReqBussReason where myTChngReqBussReason.tBussReason = ?1 "),
		@NamedQuery(name = "CountTChngReqBussReasonsByTBussReason", query = "select Count(myTChngReqBussReason) from TChngReqBussReason myTChngReqBussReason where myTChngReqBussReason.tBussReason = ?1 ") })
@Table(name = "t_chng_req_buss_reason", uniqueConstraints = @UniqueConstraint(columnNames = { "chng_req_br_id" }))
public class TChngReqBussReason implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "chng_req_br_id", nullable = false, length = 255)
	private Long chngReqBrId;

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
	@Column(name = "def_flag", nullable = false, length = 1)
	private Character defFlag;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "algmnt_id", nullable = false, length = 255)
	private Long algmntId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "buss_unit_id", nullable = false, length = 255)
	private Long bussUnitId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_team_id", nullable = false, length = 255)
	private Long salesTeamId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "category_id", nullable = true, length = 255)
	private Integer categoryId;

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
	@Length(max = 15)
	@Column(name = "chng_type", nullable = true, length = 15)
	private String chngType;

	@ManyToOne
	@JoinColumn(name = "trigger_id", referencedColumnName = "trigger_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TChngReqTrigger tChngReqTrigger;

	@ManyToOne
	@JoinColumn(name = "buss_reason_id", referencedColumnName = "buss_reason_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TBussReason tBussReason;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TCustAffChngReqDet> tCustAffChngReqDets;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TCustAlgmntChngReqDet> tCustAlgmntChngReqDets;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TCustCallPlanChngReqDet> tCustCallPlanChngReqDets;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TCustChngReqDet> tCustChngReqDets;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDets;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tChngReqBussReason")
	private Set<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDets;
	
	/**
	 *
	 * @generated
	 */
	public TChngReqBussReason() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setChngReqBrId(final Long chngReqBrId) {
		this.chngReqBrId = chngReqBrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getChngReqBrId() {
		return this.chngReqBrId;
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

	public void setDefFlag(final Character defFlag) {
		this.defFlag = defFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDefFlag() {
		return this.defFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAlgmntId(final Long algmntId) {
		this.algmntId = algmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getAlgmntId() {
		return this.algmntId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setBussUnitId(final Long bussUnitId) {
		this.bussUnitId = bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getBussUnitId() {
		return this.bussUnitId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamId(final Long salesTeamId) {
		this.salesTeamId = salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamId() {
		return this.salesTeamId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCategoryId(final Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCategoryId() {
		return this.categoryId;
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

	public void setChngType(final String chngType) {
		this.chngType = chngType;
	}

	/**
	 * 
	 * @generated
	 */
	public String getChngType() {
		return this.chngType;
	}

	/**
	 * 
	 * @generated
	 */
	public TChngReqTrigger getTChngReqTrigger() {
		return this.tChngReqTrigger;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTChngReqTrigger(final TChngReqTrigger tChngReqTrigger) {
		this.tChngReqTrigger = tChngReqTrigger;

	}

	/**
	 * 
	 * @generated
	 */
	public TBussReason getTBussReason() {
		return this.tBussReason;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussReason(final TBussReason tBussReason) {
		this.tBussReason = tBussReason;

	}

	
	/**
	 * Gets the t cust aff chng req dets.
	 *
	 * @return the t cust aff chng req dets
	 */
	public Set<TCustAffChngReqDet> getTCustAffChngReqDets() {
		return tCustAffChngReqDets;
	}

	/**
	 * Sets the t cust aff chng req dets.
	 *
	 * @param tCustAffChngReqDets the new t cust aff chng req dets
	 */
	public void setTCustAffChngReqDets(Set<TCustAffChngReqDet> tCustAffChngReqDets) {
		this.tCustAffChngReqDets = tCustAffChngReqDets;
	}

	/**
	 * Gets the t cust algmnt chng req dets.
	 *
	 * @return the t cust algmnt chng req dets
	 */
	public Set<TCustAlgmntChngReqDet> getTCustAlgmntChngReqDets() {
		return tCustAlgmntChngReqDets;
	}

	/**
	 * Sets the t cust algmnt chng req dets.
	 *
	 * @param tCustAlgmntChngReqDets the new t cust algmnt chng req dets
	 */
	public void setTCustAlgmntChngReqDets(
			Set<TCustAlgmntChngReqDet> tCustAlgmntChngReqDets) {
		this.tCustAlgmntChngReqDets = tCustAlgmntChngReqDets;
	}

	/**
	 * Gets the t cust call plan chng req dets.
	 *
	 * @return the t cust call plan chng req dets
	 */
	public Set<TCustCallPlanChngReqDet> getTCustCallPlanChngReqDets() {
		return tCustCallPlanChngReqDets;
	}

	/**
	 * Sets the t cust call plan chng req dets.
	 *
	 * @param tCustCallPlanChngReqDets the new t cust call plan chng req dets
	 */
	public void setTCustCallPlanChngReqDets(
			Set<TCustCallPlanChngReqDet> tCustCallPlanChngReqDets) {
		this.tCustCallPlanChngReqDets = tCustCallPlanChngReqDets;
	}

	/**
	 * Gets the t cust chng req dets.
	 *
	 * @return the t cust chng req dets
	 */
	public Set<TCustChngReqDet> getTCustChngReqDets() {
		return tCustChngReqDets;
	}

	/**
	 * Sets the t cust chng req dets.
	 *
	 * @param tCustChngReqDets the new t cust chng req dets
	 */
	public void setTCustChngReqDets(Set<TCustChngReqDet> tCustChngReqDets) {
		this.tCustChngReqDets = tCustChngReqDets;
	}

	/**
	 * Gets the t prd algmnt chng req dets.
	 *
	 * @return the t prd algmnt chng req dets
	 */
	public Set<TPrdAlgmntChngReqDet> getTPrdAlgmntChngReqDets() {
		return tPrdAlgmntChngReqDets;
	}

	/**
	 * Sets the t prd algmnt chng req dets.
	 *
	 * @param tPrdAlgmntChngReqDets the new t prd algmnt chng req dets
	 */
	public void setTPrdAlgmntChngReqDets(
			Set<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDets) {
		this.tPrdAlgmntChngReqDets = tPrdAlgmntChngReqDets;
	}

	/**
	 * Gets the t terr zip algmnt chng req dets.
	 *
	 * @return the t terr zip algmnt chng req dets
	 */
	public Set<TTerrZipAlgmntChngReqDet> getTTerrZipAlgmntChngReqDets() {
		return tTerrZipAlgmntChngReqDets;
	}

	/**
	 * Sets the t terr zip algmnt chng req dets.
	 *
	 * @param tTerrZipAlgmntChngReqDets the new t terr zip algmnt chng req dets
	 */
	public void setTTerrZipAlgmntChngReqDets(
			Set<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDets) {
		this.tTerrZipAlgmntChngReqDets = tTerrZipAlgmntChngReqDets;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TChngReqBussReason that) {
		setChngReqBrId(that.getChngReqBrId());
		setActiveFlag(that.getActiveFlag());
		setDefFlag(that.getDefFlag());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
		setCategoryId(that.getCategoryId());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setChngType(that.getChngType());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((chngReqBrId == null) ? 0 : chngReqBrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("chngReqBrId=[").append(chngReqBrId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("defFlag=[").append(defFlag).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
		buffer.append("categoryId=[").append(categoryId).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("chngType=[").append(chngType).append("] ");

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
		final TChngReqBussReason other = (TChngReqBussReason) obj;
		if (chngReqBrId == null) {
			if (other.chngReqBrId != null) {
				return false;
			}
		} else if (!chngReqBrId.equals(other.chngReqBrId)) {
			return false;
		}
		return true;
	}
}