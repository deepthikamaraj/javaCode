package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TSalesPosGeoUnit 
 * The corresponding mapping table is t_sales_pos_geo_unit 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPosGeoUnits", query = "select myTSalesPosGeoUnit from TSalesPosGeoUnit myTSalesPosGeoUnit"),
		@NamedQuery(name = "CountTSalesPosGeoUnits", query = "Select Count(c) from TSalesPosGeoUnit c") ,
		@NamedQuery(name = "FindTSalesPosGeoUnitList", query = "Select myTSalesPosGeoUnit from TSalesPosGeoUnit myTSalesPosGeoUnit where myTSalesPosGeoUnit.algmntId = ?1 and myTSalesPosGeoUnit.bussUnitId = ?2  and myTSalesPosGeoUnit.salesTeamId = ?3 and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId  = ?4  and myTSalesPosGeoUnit.tenantId = ?5 and myTSalesPosGeoUnit.activeFlag = 'Y' "),
		@NamedQuery(name = "findZipByGeoId", query =   "select ttza.postalCd  from  TSalesPosGeoUnit tspgu, TSalesPos tsp, TTerrZipAlgmnt ttza"+
													    " where tspgu.tSalesPosGeoUnitId.salesPosId = tsp.salesPosId"+
													    " and tsp.salesPosId   = ttza.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId"+
													    " and tspgu.tSalesPosGeoUnitId.salesHierId =tsp.tAlgmntSalesHier.salesHierId"+
													    " and tsp.tAlgmntSalesHier.salesHierId   = ttza.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId"+
													    " and tspgu.algmntId = tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId"+
													    " and tspgu.bussUnitId = tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId"+
													    " and tspgu.salesTeamId = tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId"+
													    " and tspgu.algmntId = ?1"+
													    " and tspgu.bussUnitId =?2"+
													    " and tspgu.salesTeamId = ?3"+
													    " and tspgu.tenantId = tsp.tenantId"+
													    " and tsp.tenantId = ttza.tenantId"+
													    " and tspgu.tenantId = ?7"+
													    " and tsp.salesPosId = ?5 and tsp.tAlgmntSalesHier.salesHierId = ?4"+
													    " and tspgu.tSalesPosGeoUnitId.geoId=ttza.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId"+
													    " and ttza.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?6 and ttza.activeFlag = 'Y'"),
		 @NamedQuery(name = "findGeoIdFrSP", query = "Select myTSalesPosGeoUnit.tSalesPosGeoUnitId.geoId from TSalesPosGeoUnit myTSalesPosGeoUnit " +
		 											"where myTSalesPosGeoUnit.algmntId = ?1 and myTSalesPosGeoUnit.bussUnitId = ?2  and myTSalesPosGeoUnit.salesTeamId = ?3" +
		 											" and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?4 and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId  = ?5" +
		 											" and myTSalesPosGeoUnit.tenantId = ?6 and myTSalesPosGeoUnit.activeFlag = 'Y'"),
		@NamedQuery(name = "FindTSalesPosGeoUnitListForCopy", query = "Select myTSalesPosGeoUnit from TSalesPosGeoUnit myTSalesPosGeoUnit where myTSalesPosGeoUnit.algmntId = ?1 and myTSalesPosGeoUnit.bussUnitId = ?2  and myTSalesPosGeoUnit.salesTeamId = ?3 and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId in (?4)  and myTSalesPosGeoUnit.tenantId = ?5 and myTSalesPosGeoUnit.activeFlag = 'Y' "),		 											
		@NamedQuery(name = "findMaxGeoIdFrSP", query = "Select max(myTSalesPosGeoUnit.tSalesPosGeoUnitId.geoId) from TSalesPosGeoUnit myTSalesPosGeoUnit " +
					"where myTSalesPosGeoUnit.tenantId = ?1 and myTSalesPosGeoUnit.activeFlag = 'Y'"),											    
		})

@Table(name = "t_sales_pos_geo_unit")
public class TSalesPosGeoUnit implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TSalesPosGeoUnitId tSalesPosGeoUnitId;

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

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPosGeoUnit")
	private Set<TTerrZipAlgmnt> tTerrZipAlgmntss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPosGeoUnit")
	private Set<TSalesPosGeoUnitHist> tSalesPosGeoUnitHistss;

	/**
	 *
	 * @generated
	 */
	public TSalesPosGeoUnit() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setTSalesPosGeoUnitId(final TSalesPosGeoUnitId tSalesPosGeoUnitId) {
		this.tSalesPosGeoUnitId = tSalesPosGeoUnitId;
	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPosGeoUnitId getTSalesPosGeoUnitId() {
		return this.tSalesPosGeoUnitId;
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
	public Set<TTerrZipAlgmnt> getTTerrZipAlgmntss() {
		return this.tTerrZipAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTTerrZipAlgmntss(final Set<TTerrZipAlgmnt> tTerrZipAlgmntss) {
		this.tTerrZipAlgmntss = tTerrZipAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesPosGeoUnitHist> getTSalesPosGeoUnitHistss() {
		return this.tSalesPosGeoUnitHistss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosGeoUnitHistss(final Set<TSalesPosGeoUnitHist> tSalesPosGeoUnitHistss) {
		this.tSalesPosGeoUnitHistss = tSalesPosGeoUnitHistss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPosGeoUnit that) {
		setTSalesPosGeoUnitId(that.getTSalesPosGeoUnitId());
		setActiveFlag(that.getActiveFlag());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
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
		result = prime * result + ((tSalesPosGeoUnitId == null) ? 0 : tSalesPosGeoUnitId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tSalesPosGeoUnitId=[").append(tSalesPosGeoUnitId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");
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
		final TSalesPosGeoUnit other = (TSalesPosGeoUnit) obj;
		if (tSalesPosGeoUnitId == null) {
			if (other.tSalesPosGeoUnitId != null) {
				return false;
			}
		} else if (!tSalesPosGeoUnitId.equals(other.tSalesPosGeoUnitId)) {
			return false;
		}
		return true;
	}
}
