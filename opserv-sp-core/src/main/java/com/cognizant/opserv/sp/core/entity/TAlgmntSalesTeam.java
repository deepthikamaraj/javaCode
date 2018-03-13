package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** 
 * JPA class representing TAlgmntSalesTeam 
 * The corresponding mapping table is t_algmnt_sales_team 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntSalesTeams", query = "select myTAlgmntSalesTeam from TAlgmntSalesTeam myTAlgmntSalesTeam"),
		@NamedQuery(name = "CountTAlgmntSalesTeams", query = "Select Count(c) from TAlgmntSalesTeam c"),
		@NamedQuery(name = "FindTAlgmntSalesTeamByTSalesTeam", query = "select myTAlgmntSalesTeam from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tSalesTeam = ?1 "),		
		@NamedQuery(name = "FindTAlgmntSalesTeamByTSalesTeamBU", query = "select myTAlgmntSalesTeam from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?1 AND myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 "),		
		@NamedQuery(name = "CountTAlgmntSalesTeamsByTSalesTeam", query = "select Count(*) from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tSalesTeam = ?1 "),
		@NamedQuery(name = "FindTAlgmntSalesTeamByTAlgmnt", query = "select myTAlgmntSalesTeam from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmnt = ?1 AND myTAlgmntSalesTeam.activeFlag='Y' "),
		@NamedQuery(name = "FindActiveAlgmnts", query = "select myTAlgmntSalesTeam from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tenantId=?1 AND myTAlgmntSalesTeam.activeFlag=?2 AND (myTAlgmntSalesTeam.effEndDt >= ?3  OR myTAlgmntSalesTeam.effEndDt IS NULL )"),
		@NamedQuery(name = "CountTAlgmntSalesTeamsByTAlgmnt", query = "select Count(*) from TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmnt = ?1 "),
		//@NamedQuery(name = "FindTAlgmntSalesTeamBySearch", query = "select distinct(myTAlgmntSalesTeam) from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTAlgmntStatus.tAlgmntStatusId.statusId = ?3 and myTBussUnit.bussUnitName like ?4 and myTAlgmntSalesTeam.tenantId=?5"),
		@NamedQuery(name = "FetchAlgmntActive" , query="select distinct(myTAlgmntSalesTeam) from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTBussUnit.bussUnitName like ?3 and myTAlgmntSalesTeam.tenantId=?4  AND myTAlgmnt.algmntStatusId=?5 AND myTAlgmntSalesTeam.tAlgmnt.effStartDt<=current_date AND ( myTAlgmntSalesTeam.tAlgmnt.effEndDt >=current_date OR myTAlgmntSalesTeam.tAlgmnt.effEndDt IS NULL) "),
		@NamedQuery(name = "FetchAlgmntInActive",query= "select distinct(myTAlgmntSalesTeam) from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTBussUnit.bussUnitName like ?3 and myTAlgmntSalesTeam.tenantId=?4 AND myTAlgmnt.algmntStatusId=?5 AND myTAlgmntSalesTeam.tAlgmnt.effEndDt < current_date"),
		@NamedQuery(name = "FetchAlgmntPropos",query= "select distinct(myTAlgmntSalesTeam) from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTBussUnit.bussUnitName like ?3 and myTAlgmntSalesTeam.tenantId=?4  AND myTAlgmnt.algmntStatusId=?5  AND myTAlgmntSalesTeam.tAlgmnt.effStartDt >current_date"),
		@NamedQuery(name = "FetchAlgmntSuspended",query= "select distinct(myTAlgmntSalesTeam) from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTBussUnit.bussUnitName like ?3 and myTAlgmntSalesTeam.tenantId=?4  AND myTAlgmnt.algmntStatusId=?5  AND myTAlgmntSalesTeam.tAlgmnt.effStartDt >current_date"),
		
		@NamedQuery(name = "FindAlignmentsByAlBuStNames", query = "SELECT myTAlgmnt.algmntName, myTBussUnit.bussUnitName, myTSalesTeam.salesTeamName FROM TAlgmnt myTAlgmnt , TBussUnit myTBussUnit , TSalesTeam myTSalesTeam  WHERE  myTAlgmnt.tBussUnit.bussUnitId = myTBussUnit.bussUnitId AND myTBussUnit.bussUnitId =myTSalesTeam.tBussUnit.bussUnitId AND myTAlgmnt.tenantId =myTBussUnit.tenantId AND myTSalesTeam.tenantId = myTBussUnit.tenantId AND  myTAlgmnt.algmntId = ?1 AND myTAlgmnt.tBussUnit.bussUnitId = ?2 AND myTSalesTeam.tSalesTeamId.salesTeamId = ?3  AND myTAlgmnt.tenantId=?4  AND myTBussUnit.activeFlag = 'Y' AND myTSalesTeam.activeFlag = 'Y'"),
		
		@NamedQuery(name = "FetchActiveAlgmntsData",query= "select ast.tAlgmntSalesTeamId.algmntId, ast.tAlgmntSalesTeamId.bussUnitId, ast.tAlgmntSalesTeamId.salesTeamId, " +
				" alg.algmntName, bu.bussUnitName, bu.bussUnitCode, st.salesTeamName, " +
				" alg.effStartDt,alg.effEndDt, ast.effEndDt, bu.effendDt, st.effEndDt " +
				" from TAlgmntSalesTeam ast inner join ast.tSalesTeam st inner join ast.tAlgmnt alg  " +
				"inner join st.tBussUnit bu where ( ast.effEndDt is null or ast.effEndDt >= ?1 )  AND ast.tenantId = ?2 AND ast.activeFlag = 'Y' ")	
	})
@Table(name = "t_algmnt_sales_team")
public class TAlgmntSalesTeam implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@EmbeddedId
	@Valid
	private TAlgmntSalesTeamId tAlgmntSalesTeamId;

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

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = false, updatable = false) })
	@Valid
	private TSalesTeam tSalesTeam;

	@ManyToOne
	@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TAlgmnt tAlgmnt;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeamByTSalesTeamMirrorIbfk3")
	private Set<TSalesTeamMirror> tSalesTeamMirrorsForTSalesTeamMirrorIbfk3s;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeamByTSalesTeamMirrorIbfk2")
	private Set<TSalesTeamMirror> tSalesTeamMirrorsForTSalesTeamMirrorIbfk2s;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeam")
	private Set<TAlgmntSalesHier> tAlgmntSalesHierss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeam")
	private Set<TSalesPos> tSalesPosess;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeam")
	private Set<TAlgmntBussRule> tAlgmntBussRuless;

    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeam")
	private Set<TMtr> tMtrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tAlgmntSalesTeam")
	private Set<TCallPlanConfig> tCallPlanConfigs;

	/**
	 *
	 * @generated
	 */
	public TAlgmntSalesTeam() {
	}

	public Set<TCallPlanConfig> gettCallPlanConfigs() {
		return tCallPlanConfigs;
	}

	public void settCallPlanConfigs(Set<TCallPlanConfig> tCallPlanConfigs) {
		this.tCallPlanConfigs = tCallPlanConfigs;
	}

	/**
	 * 
	 * @generated
	 */

	public void setTAlgmntSalesTeamId(final TAlgmntSalesTeamId tAlgmntSalesTeamId) {
		this.tAlgmntSalesTeamId = tAlgmntSalesTeamId;
	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeamId getTAlgmntSalesTeamId() {
		return this.tAlgmntSalesTeamId;
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
	 * 
	 * @generated
	 */
	public Set<TMtr> getTMtrss() {
		return this.tMtrss;
	}
	
	public TSalesTeam getTSalesTeam() {
		return this.tSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesTeam(final TSalesTeam tSalesTeam) {
		this.tSalesTeam = tSalesTeam;
		if (this.tSalesTeam != null && this.tSalesTeam.getTSalesTeamId() != null) {

			this.tAlgmntSalesTeamId.setSalesTeamId(this.tSalesTeam.getTSalesTeamId().getSalesTeamId());

		}
		if (this.tSalesTeam != null && this.tSalesTeam.getTSalesTeamId() != null) {

			this.tAlgmntSalesTeamId.setBussUnitId(this.tSalesTeam.getTSalesTeamId().getBussUnitId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmnt getTAlgmnt() {
		return this.tAlgmnt;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmnt(final TAlgmnt tAlgmnt) {
		this.tAlgmnt = tAlgmnt;
		if (this.tAlgmnt != null && this.tAlgmnt.getAlgmntId() != null) {

			this.tAlgmntSalesTeamId.setAlgmntId(this.tAlgmnt.getAlgmntId());

		}

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesTeamMirror> getTSalesTeamMirrorsForTSalesTeamMirrorIbfk3s() {
		return this.tSalesTeamMirrorsForTSalesTeamMirrorIbfk3s;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesTeamMirrorsForTSalesTeamMirrorIbfk3s(
			final Set<TSalesTeamMirror> tSalesTeamMirrorsForTSalesTeamMirrorIbfk3s) {
		this.tSalesTeamMirrorsForTSalesTeamMirrorIbfk3s = tSalesTeamMirrorsForTSalesTeamMirrorIbfk3s;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesTeamMirror> getTSalesTeamMirrorsForTSalesTeamMirrorIbfk2s() {
		return this.tSalesTeamMirrorsForTSalesTeamMirrorIbfk2s;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesTeamMirrorsForTSalesTeamMirrorIbfk2s(
			final Set<TSalesTeamMirror> tSalesTeamMirrorsForTSalesTeamMirrorIbfk2s) {
		this.tSalesTeamMirrorsForTSalesTeamMirrorIbfk2s = tSalesTeamMirrorsForTSalesTeamMirrorIbfk2s;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntSalesHier> getTAlgmntSalesHierss() {
		return this.tAlgmntSalesHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesHierss(final Set<TAlgmntSalesHier> tAlgmntSalesHierss) {
		this.tAlgmntSalesHierss = tAlgmntSalesHierss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesPos> getTSalesPosess() {
		return this.tSalesPosess;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosess(final Set<TSalesPos> tSalesPosess) {
		this.tSalesPosess = tSalesPosess;
	}
	
	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntBussRule> getTAlgmntBussRuless() {
		return this.tAlgmntBussRuless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntBussRuless(final Set<TAlgmntBussRule> tAlgmntBussRuless) {
		this.tAlgmntBussRuless = tAlgmntBussRuless;
	}

	public void setTMtrss(final Set<TMtr> tMtrss) {
		this.tMtrss = tMtrss;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntSalesTeam that) {
		setTAlgmntSalesTeamId(that.getTAlgmntSalesTeamId());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((tAlgmntSalesTeamId == null) ? 0 : tAlgmntSalesTeamId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("tAlgmntSalesTeamId=[").append(tAlgmntSalesTeamId).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		final TAlgmntSalesTeam other = (TAlgmntSalesTeam) obj;
		if (tAlgmntSalesTeamId == null) {
			if (other.tAlgmntSalesTeamId != null) {
				return false;
			}
		} else if (!tAlgmntSalesTeamId.equals(other.tAlgmntSalesTeamId)) {
			return false;
		}
		return true;
	}
}
