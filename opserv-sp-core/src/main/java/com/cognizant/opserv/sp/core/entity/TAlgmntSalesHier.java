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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TAlgmntSalesHier 
 * The corresponding mapping table is t_algmnt_sales_hier 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTAlgmntSalesHiers", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier"),
		@NamedQuery(name = "CountTAlgmntSalesHiers", query = "Select Count(c) from TAlgmntSalesHier c"),
		@NamedQuery(name = "FindTAlgmntSalesHierByTAlgmntSalesTeam", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam = ?1 and myTAlgmntSalesHier.activeFlag = 'Y'"),
		@NamedQuery(name = "FindHierNamesByIDs", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTAlgmntSalesHier.tenantId =?4 "),
		@NamedQuery(name = "FindHierName", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTAlgmntSalesHier.salesHierId=?4 and myTAlgmntSalesHier.tenantId =?5 "),
		@NamedQuery(name = "DeleteTAlgmntSalesHierTenant", query = "delete from TAlgmntSalesHier c where c.tenantId = ?1 AND c.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId =?2 AND c.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3"),
		@NamedQuery(name = "CountTAlgmntSalesHiersByTAlgmntSalesTeam", query = "select Count(myTAlgmntSalesHier) from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTAlgmntSalesHierByTOrgSalesHier", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tOrgSalesHier = ?1 "),
		@NamedQuery(name = "CountTAlgmntSalesHiersByTOrgSalesHier", query = "select Count(myTAlgmntSalesHier) from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tOrgSalesHier = ?1 "),
		@NamedQuery(name = "FindTAlgmntSalesHiersByTBussUnitAndTeam", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId = ?1 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId=?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId=?3 and myTAlgmntSalesHier.tenantId=?4 and myTAlgmntSalesHier.activeFlag = 'Y' order by myTAlgmntSalesHier.prnSaleHierId"),
		@NamedQuery(name = "FindTAlgmntSalesHierByTAlgmntID", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTAlgmntSalesHier.tenantId=?4 and myTAlgmntSalesHier.activeFlag = 'Y' "),
			
		
		@NamedQuery(name = "findTAlgSalesHier", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTAlgmntSalesHier.tOrgSalesHier.orgSalesHierId=?4 and myTAlgmntSalesHier.tenantId=?5"),
		@NamedQuery(name = "fetchTAlgmntHierByBussID", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where  myTAlgmntSalesHier.tenantId=?1 and myTAlgmntSalesHier.effEndDt >=?2 and myTAlgmntSalesHier.activeFlag = 'Y' GROUP BY myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId"),
		@NamedQuery(name = "FindTAlgmntSalesHierByPrnSalesHier", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.prnSaleHierId = ?1 and myTAlgmntSalesHier.tenantId = ?2 and myTAlgmntSalesHier.activeFlag='Y' "),
		@NamedQuery(name = "FindTAlgmntSalesHierByPrnandAlgmntIdSalesHier", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.prnSaleHierId = ?1 and myTAlgmntSalesHier.tenantId = ?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 and myTAlgmntSalesHier.activeFlag='Y' "),
		@NamedQuery(name = "FindPrnSalesHierByTBussUnitAndTeam", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId = ?1 AND myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId=?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId=?3 and myTAlgmntSalesHier.tenantId=?4 and myTAlgmntSalesHier.activeFlag = 'Y' and myTAlgmntSalesHier.prnSaleHierId is null"),
		@NamedQuery(name = "FindHierNamesByALBUST", query = "select myTAlgmntSalesHier from TAlgmntSalesHier myTAlgmntSalesHier where " +
				"myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND " +
				"myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND " +
				"myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and " +
				"myTAlgmntSalesHier.activeFlag=?4 and myTAlgmntSalesHier.effStartDt <= ?5 and " +
				"(myTAlgmntSalesHier.effEndDt >= ?6 or myTAlgmntSalesHier.effEndDt is null ) and " +
				"myTAlgmntSalesHier.tenantId =?7 "),
		@NamedQuery(name = "FindTAlgmntSalesHierByPrnandAlgmntIdSalesHierCount", query = "select count(myTAlgmntSalesHier) from TAlgmntSalesHier myTAlgmntSalesHier where myTAlgmntSalesHier.prnSaleHierId = ?1 and myTAlgmntSalesHier.tenantId = ?2 and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 and myTAlgmntSalesHier.activeFlag='Y' "),
		@NamedQuery(name = "FetchActHierNamesByALBUST", query = "select myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId," +
				" myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId, myTAlgmntSalesHier.salesHierId,myTAlgmntSalesHier.prnSaleHierId, myTAlgmntSalesHier.hierName " +
				" from TAlgmntSalesHier myTAlgmntSalesHier inner join " +
				" myTAlgmntSalesHier.tAlgmntSalesTeam myTAlgmntSalesTeam " +
				" where (myTAlgmntSalesTeam.effEndDt is null or myTAlgmntSalesTeam.effEndDt >= ?1 ) AND myTAlgmntSalesTeam.tenantId = ?2 " +
				" and myTAlgmntSalesHier.activeFlag = ?3 and ( myTAlgmntSalesHier.effEndDt is null or myTAlgmntSalesHier.effEndDt >= ?1 ) ")
	})
@Table(name = "t_algmnt_sales_hier", uniqueConstraints = @UniqueConstraint(columnNames = { "sales_hier_id" }))
public class TAlgmntSalesHier implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
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
	@Column(name = "eff_start_dt", nullable = false, length = 10)
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
	@NotEmpty
	@Length(max = 100)
	@Column(name = "hier_name", nullable = false, length = 100)
	private String hierName;

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

	@Column(name = "assign_zip_flag", nullable = true, length = 1)
	private Character assignZipFlag;
	
	@Column(name = "move_sp_flag", nullable = true, length = 1)
	private Character moveSPFlag;
	
	@Column(name = "prn_sales_hier_id",  nullable = false, length = 255)
	private Long prnSaleHierId;
	
	
	@Column(name = "hier_level", nullable = false, length = 255)
	private Short hierLevel;
	
	
    
	
	
	
	
	
	public Short getHierLevel() {
		return hierLevel;
	}

	public void setHierLevel(Short hierLevel) {
		this.hierLevel = hierLevel;
	}

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	@ManyToOne
	@JoinColumn(name = "org_sales_hier_id", referencedColumnName = "org_sales_hier_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TOrgSalesHier tOrgSalesHier;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesHier")
	private Set<TSalesTeamMirror> tSalesTeamMirrorss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesHier")
	private Set<TAlgmntSalesRole> tAlgmntSalesRoless;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesHier")
	private Set<TSalesPos> tSalesPosess;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tAlgmntSalesHier")
	private Set<TMtrConfig> tMtrConfigss;

	/**
	 *
	 * @generated
	 */
	public TAlgmntSalesHier() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setSalesHierId(final Long salesHierId) {
		this.salesHierId = salesHierId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesHierId() {
		return this.salesHierId;
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

	public void setHierName(final String hierName) {
		this.hierName = hierName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getHierName() {
		return this.hierName;
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

	public Character getAssignZipFlag() {
		return assignZipFlag;
	}

	public void setAssignZipFlag(Character assignZipFlag) {
		this.assignZipFlag = assignZipFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public TAlgmntSalesTeam getTAlgmntSalesTeam() {
		return this.tAlgmntSalesTeam;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesTeam(final TAlgmntSalesTeam tAlgmntSalesTeam) {
		this.tAlgmntSalesTeam = tAlgmntSalesTeam;

	}

	/**
	 * 
	 * @generated
	 */
	public TOrgSalesHier getTOrgSalesHier() {
		return this.tOrgSalesHier;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTOrgSalesHier(final TOrgSalesHier tOrgSalesHier) {
		this.tOrgSalesHier = tOrgSalesHier;

	}

	/**
	 * 
	 * @generated
	 */
	public Set<TSalesTeamMirror> getTSalesTeamMirrorss() {
		return this.tSalesTeamMirrorss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesTeamMirrorss(final Set<TSalesTeamMirror> tSalesTeamMirrorss) {
		this.tSalesTeamMirrorss = tSalesTeamMirrorss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TAlgmntSalesRole> getTAlgmntSalesRoless() {
		return this.tAlgmntSalesRoless;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTAlgmntSalesRoless(final Set<TAlgmntSalesRole> tAlgmntSalesRoless) {
		this.tAlgmntSalesRoless = tAlgmntSalesRoless;
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
	
	public Set<TMtrConfig> getTMtrConfigss() {
		return this.tMtrConfigss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrConfigss(final Set<TMtrConfig> tMtrConfigss) {
		this.tMtrConfigss = tMtrConfigss;
	}
	
	
	public Character getMoveSPFlag() {
		return moveSPFlag;
	}

	public void setMoveSPFlag(Character moveSPFlag) {
		this.moveSPFlag = moveSPFlag;
	}
	
	public Long getPrnSaleHierId() {
		return prnSaleHierId;
	}

	public void setPrnSaleHierId(Long prnSaleHierId) {
		this.prnSaleHierId = prnSaleHierId;
	}


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TAlgmntSalesHier that) {
		setSalesHierId(that.getSalesHierId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setHierName(that.getHierName());
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
		result = prime * result + ((salesHierId == null) ? 0 : salesHierId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("hierName=[").append(hierName).append("] ");
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
		final TAlgmntSalesHier other = (TAlgmntSalesHier) obj;
		if (salesHierId == null) {
			if (other.salesHierId != null) {
				return false;
			}
		} else if (!salesHierId.equals(other.salesHierId)) {
			return false;
		}
		return true;
	}
}
