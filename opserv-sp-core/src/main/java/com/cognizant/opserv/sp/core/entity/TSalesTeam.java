package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TSalesTeam 
 * The corresponding mapping table is t_sales_team 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesTeams", query = "select myTSalesTeam from TSalesTeam myTSalesTeam"),
		@NamedQuery(name = "CountTSalesTeams", query = "Select Count(c) from TSalesTeam c"),
		@NamedQuery(name = "FindTSalesTeamByTBussUnit", query = "select myTSalesTeam from TSalesTeam myTSalesTeam where myTSalesTeam.tBussUnit = ?1 "),
		@NamedQuery(name = "MaxTSalesTeamsByTBussUnit", query = "select Max(myTSalesTeam.tSalesTeamId.salesTeamId) from TSalesTeam myTSalesTeam where myTSalesTeam.tSalesTeamId.bussUnitId = ?1 "),
		@NamedQuery(name = "CountTSalesTeamsByTBussUnit", query = "select Count(*) from TSalesTeam myTSalesTeam where myTSalesTeam.tBussUnit = ?1 ") ,
		@NamedQuery(name = "FindSalesTeamsByTBussUnitId", query = "select myTSalesTeam  from TSalesTeam myTSalesTeam where myTSalesTeam.tBussUnit.bussUnitId = ?1 and myTSalesTeam.tenantId = ?2") })
@Table(name = "t_sales_team")
public class TSalesTeam implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesTeamId tSalesTeamId;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "sales_team_name", nullable = false, length = 75)
	private String salesTeamName;

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
	@Column(name = "delete_flag", nullable = true, length = 1)
	private Character deleteFlag;

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
	@Length(max = 50)
	@Column(name = "ext_sales_team_id", nullable = true, length = 50)
	private String extSalesTeamId;
	
	@ManyToOne
	@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TBussUnit tBussUnit;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesTeam")
	private Set<TAlgmntSalesTeam> tAlgmntSalesTeamss;

	/**
	 *
	 * @generated
	 */
	public TSalesTeam() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesTeamId(final TSalesTeamId tSalesTeamId) {
		this.tSalesTeamId = tSalesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesTeamId getTSalesTeamId() {
		return this.tSalesTeamId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesTeamName(final String salesTeamName) {
		this.salesTeamName = salesTeamName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getSalesTeamName() {
		return this.salesTeamName;
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

	public void setDeleteFlag(final Character deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDeleteFlag() {
		return this.deleteFlag;
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
	public TBussUnit getTBussUnit() {
		return this.tBussUnit;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTBussUnit(final TBussUnit tBussUnit) {
		this.tBussUnit = tBussUnit;
		if (this.tBussUnit != null && this.tBussUnit.getBussUnitId() != null) {

			this.tSalesTeamId.setBussUnitId(this.tBussUnit.getBussUnitId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntSalesTeam> getTAlgmntSalesTeamss() {
		return this.tAlgmntSalesTeamss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeamss(final Set<TAlgmntSalesTeam> tAlgmntSalesTeamss) {
		this.tAlgmntSalesTeamss = tAlgmntSalesTeamss;
	}

	/**
	 * 
	 * @generated
	 */

	public void setExtSalesTeamId(final String extSalesTeamId) {
		this.extSalesTeamId = extSalesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getExtSalesTeamId() {
		return this.extSalesTeamId;
	}
	
	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesTeam that) {
		setTSalesTeamId(that.getTSalesTeamId());
		setSalesTeamName(that.getSalesTeamName());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setDeleteFlag(that.getDeleteFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setExtSalesTeamId(that.getExtSalesTeamId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((tSalesTeamId == null) ? 0 : tSalesTeamId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesTeamId=[").append(tSalesTeamId).append("] ");
		buffer.append("salesTeamName=[").append(salesTeamName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("deleteFlag=[").append(deleteFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("extSalesTeamId=[").append(extSalesTeamId).append("] ");

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
		final TSalesTeam other = (TSalesTeam) obj;
		if (tSalesTeamId == null) {
			if (other.tSalesTeamId != null) {
				return false;
			}
		} else if (!tSalesTeamId.equals(other.tSalesTeamId)) {
			return false;
		}
		return true;
	}
}
