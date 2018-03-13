package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TSalesTeamMirror 
 * The corresponding mapping table is t_sales_team_mirror 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesTeamMirrors", query = "select myTSalesTeamMirror from TSalesTeamMirror myTSalesTeamMirror"),
		@NamedQuery(name = "CountTSalesTeamMirrors", query = "Select Count(c) from TSalesTeamMirror c"),
		@NamedQuery(name = "FindTSalesTeamMirrorByTAlgmntSalesTeamByTSalesTeamMirrorIbfk3", query = "select myTSalesTeamMirror from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesTeamByTSalesTeamMirrorIbfk3 = ?1 "),
		@NamedQuery(name = "CountTSalesTeamMirrorsByTAlgmntSalesTeamByTSalesTeamMirrorIbfk3", query = "select Count(myTSalesTeamMirror) from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesTeamByTSalesTeamMirrorIbfk3 = ?1 "),
		@NamedQuery(name = "FindTSalesTeamMirrorByTAlgmntSalesHier", query = "select myTSalesTeamMirror from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "CountTSalesTeamMirrorsByTAlgmntSalesHier", query = "select Count(myTSalesTeamMirror) from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "FindTSalesTeamMirrorByTAlgmntSalesTeamByTSalesTeamMirrorIbfk2", query = "select myTSalesTeamMirror from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesTeamByTSalesTeamMirrorIbfk2 = ?1 "),
		@NamedQuery(name = "CountTSalesTeamMirrorsByTAlgmntSalesTeamByTSalesTeamMirrorIbfk2", query = "select Count(myTSalesTeamMirror) from TSalesTeamMirror myTSalesTeamMirror where myTSalesTeamMirror.tAlgmntSalesTeamByTSalesTeamMirrorIbfk2 = ?1 ") })
@Table(name = "t_sales_team_mirror", uniqueConstraints = @UniqueConstraint(columnNames = { "sales_team_mir_id" }))
public class TSalesTeamMirror implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_team_mir_id", nullable = false, length = 255)
	private Long salesTeamMirId;

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
	@Column(name = "eff_start_dt", nullable = true, length = 10)
	private Date effStartDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "eff_end_dt", nullable = true, length = 10)
	private Date effEndDt;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "mirror_type", nullable = true, length = 1)
	private Character mirrorType;

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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "mir_algmt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "mir_buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "mir_sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeamByTSalesTeamMirrorIbfk3;

	@ManyToOne
	@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TAlgmntSalesHier tAlgmntSalesHier;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeamByTSalesTeamMirrorIbfk2;

	/**
	 *
	 * @generated
	 */
	public TSalesTeamMirror() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamMirId(final Long salesTeamMirId) {
		this.salesTeamMirId = salesTeamMirId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesTeamMirId() {
		return this.salesTeamMirId;
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

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt != null) {
			this.effStartDt = (Date) effStartDt.clone();
		} else {
			this.effStartDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt != null) {
			return (Date) this.effStartDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt != null) {
			this.effEndDt = (Date) effEndDt.clone();
		} else {
			this.effEndDt = null;
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt != null) {
			return (Date) this.effEndDt.clone();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setMirrorType(final Character mirrorType) {
		this.mirrorType = mirrorType;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getMirrorType() {
		return this.mirrorType;
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
	public TAlgmntSalesTeam getTAlgmntSalesTeamByTSalesTeamMirrorIbfk3() {
		return this.tAlgmntSalesTeamByTSalesTeamMirrorIbfk3;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeamByTSalesTeamMirrorIbfk3(
			final TAlgmntSalesTeam tAlgmntSalesTeamByTSalesTeamMirrorIbfk3) {
		this.tAlgmntSalesTeamByTSalesTeamMirrorIbfk3 = tAlgmntSalesTeamByTSalesTeamMirrorIbfk3;

	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesHier getTAlgmntSalesHier() {
		return this.tAlgmntSalesHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesHier(final TAlgmntSalesHier tAlgmntSalesHier) {
		this.tAlgmntSalesHier = tAlgmntSalesHier;

	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeamByTSalesTeamMirrorIbfk2() {
		return this.tAlgmntSalesTeamByTSalesTeamMirrorIbfk2;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeamByTSalesTeamMirrorIbfk2(
			final TAlgmntSalesTeam tAlgmntSalesTeamByTSalesTeamMirrorIbfk2) {
		this.tAlgmntSalesTeamByTSalesTeamMirrorIbfk2 = tAlgmntSalesTeamByTSalesTeamMirrorIbfk2;

	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesTeamMirror that) {
		setSalesTeamMirId(that.getSalesTeamMirId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setMirrorType(that.getMirrorType());
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
		result = prime * result + ((salesTeamMirId == null) ? 0 : salesTeamMirId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesTeamMirId=[").append(salesTeamMirId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("mirrorType=[").append(mirrorType).append("] ");
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
		final TSalesTeamMirror other = (TSalesTeamMirror) obj;
		if (salesTeamMirId == null) {
			if (other.salesTeamMirId != null) {
				return false;
			}
		} else if (!salesTeamMirId.equals(other.salesTeamMirId)) {
			return false;
		}
		return true;
	}
}
