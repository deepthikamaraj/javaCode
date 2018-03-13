package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

/** 
 * JPA class representing TPrdAlgmnt 
 * The corresponding mapping table is t_prd_algmnt 
 */

@Entity
@Audited
@NamedQueries({ @NamedQuery(name = "FindAllTPrdAlgmnts", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt"),
	@NamedQuery(name = "FindTPrdAlgmntsDts", query = "select c from TPrdAlgmnt c where  c.tPrd.prdId = ?1 AND c.salesPosId =?2  AND c.algmntId = ?3 AND c.bussUnitId = ?4 AND c.salesTeamId = ?5 AND c.salesHierId = ?6 AND c.tenantId = ?7 AND c.activeFlag='Y'"),
	@NamedQuery(name = "FetchTPrdAlgmntsByJoinQuery", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt INNER JOIN myTPrdAlgmnt.tSalesPos pos WHERE myTPrdAlgmnt.salesPosId=pos.salesPosId and myTPrdAlgmnt.salesHierId=pos.tAlgmntSalesHier.salesHierId AND myTPrdAlgmnt.activeFlag='Y' AND myTPrdAlgmnt.tenantId=?1 AND (myTPrdAlgmnt.effEndDt<=?2 OR(pos.effEndDt<=?3 OR pos.effEndDt IS NULL)) OR pos.activeFlag='N'" ),
	@NamedQuery(name = "FindTPrdAlgmntsForUnAssign", query = "Select c from TPrdAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.activeFlag =?7 AND c.tPrd.prdId = ?8"),
	@NamedQuery(name = "FindTPrdAlgmntsByPrdId", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tPrd.prdId=?1"),
	@NamedQuery(name = "CountTPrdAlgmntsTenant", query = "Select Count(c) from TPrdAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.tPrd.prdId = ?7 AND c.activeFlag='Y'"),
	@NamedQuery(name = "DeleteTPrdAlgmntsTenant", query = "update TPrdAlgmnt c set c.activeFlag = 'N' , c.updateDt=?1, c.updatedBy=?2,c.effEndDt=?3 where c.tenantId = ?4 AND c.salesPosId =?5 AND c.salesHierId = ?6 AND c.algmntId = ?7 AND c.bussUnitId = ?8 AND c.salesTeamId = ?9 AND c.tPrd.prdId = ?10"),
	@NamedQuery(name = "FindTPrdAlgmntByTenantId", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tenantId = ?1 "),
	@NamedQuery(name = "CountTPrdAlgmnts", query = "Select Count(c) from TPrdAlgmnt c"),
	@NamedQuery(name = "FindTPrdAlgmntByTPrd", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tPrd = ?1 "),
    @NamedQuery(name = "FindTPrdAlgmntByJoinQuery", query = "select myTAlgmntTmpl.tBussObjTmpl.tmplId,myTAlgmnt.algmntName,myTBussUnit.bussUnitName,myTSalesTeam.salesTeamName from TPrdAlgmnt myTPrdAlgmnt,TAlgmnt myTAlgmnt,TAlgmntTmpl myTAlgmntTmpl,TBussObjTmpl myTBussObjTmpl,TBussUnit myTBussUnit,TSalesTeam myTSalesTeam where myTPrdAlgmnt.tPrd.prdId = ?1 AND myTBussObjTmpl.tBussObj.bussObjId = ?2 AND myTBussObjTmpl.tmplId = myTAlgmntTmpl.tBussObjTmpl.tmplId AND myTPrdAlgmnt.algmntId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId AND myTPrdAlgmnt.bussUnitId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId AND myTPrdAlgmnt.bussUnitId = myTBussUnit.bussUnitId AND myTPrdAlgmnt.salesTeamId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId AND myTPrdAlgmnt.bussUnitId= myTSalesTeam.tSalesTeamId.bussUnitId AND myTPrdAlgmnt.salesTeamId = myTSalesTeam.tSalesTeamId.salesTeamId AND myTAlgmnt.algmntId = myTPrdAlgmnt.algmntId AND myTPrdAlgmnt.tenantId = ?3"),
    @NamedQuery(name = "FindTPrdAlgmntBySalesPosId", query = "select ca"+
            " from TPrdAlgmnt ca "+
            " where ca.salesPosId  = ?1 "+
            "and ca.algmntId = ?2 "+
            "and ca.bussUnitId = ?3 "+
            "and ca.salesTeamId = ?4 "+
            "and ca.tenantId = ?5 "+
            "and (ca.effEndDt IS NULL OR ca.effEndDt >= ?6)"+
            "and ca.activeFlag = ?7" ),

    @NamedQuery(name = "findProductsWithAlignmentInfoByList", query = "select ca"+
                    " from TPrdAlgmnt ca "+
                    " where ca.salesPosId  = ?1 "+
                    "and ca.tPrd.prdId in ?2 "+
                    "and ca.tenantId = ?3"),
   @NamedQuery(name = "findProductsWithAlignmentInfoByPrdIdList", query = "select ca"+
                            " from TPrdAlgmnt ca "+
                            "where ca.tPrd.prdId in ?1 "+
                            "and ca.tenantId = ?2"),
	@NamedQuery(name = "CountTPrdAlgmntsByTPrd", query = "select Count(*) from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tPrd = ?1 "),
	@NamedQuery(name = "FindTPrdAlgmntsForUnAssignSP", query = "Select p from TPrdAlgmnt p where p.salesPosId =?1 AND p.salesHierId = ?2 AND p.algmntId = ?3 AND p.bussUnitId = ?4 AND p.salesTeamId = ?5 AND p.activeFlag = ?6 AND p.tenantId = ?7 "),
	@NamedQuery(name = "FindTPrdAlgmntsForProductSearch", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt, TPrd myTPrd where  myTPrdAlgmnt.tPrd.prdId = myTPrd.prdId  AND myTPrdAlgmnt.algmntId = ?1 AND myTPrdAlgmnt.bussUnitId = ?2 AND myTPrdAlgmnt.salesTeamId = ?3  AND myTPrdAlgmnt.salesPosId = ?4 AND myTPrdAlgmnt.salesHierId = ?5 AND  myTPrdAlgmnt.tPrd.prdName like ?6 AND myTPrdAlgmnt.tenantId = ?7 AND myTPrdAlgmnt.activeFlag = ?8 "),
	@NamedQuery(name = "FindCountOfPrdForSPandChild", query = "Select Count(distinct c.tPrd.prdId) from TPrdAlgmnt c where c.tenantId = ?1 AND c.salesPosId in (?2)  AND c.algmntId = ?3 AND c.bussUnitId = ?4 AND c.salesTeamId = ?5 AND c.activeFlag =?6 AND (c.effEndDt >= ?7 or c.effEndDt IS NULL)" ),
	@NamedQuery(name = "FindCountOfPrdForSP", query = "Select Count(distinct c.tPrd.prdId) from TPrdAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.activeFlag =?4 AND (c.effEndDt >= ?5 or c.effEndDt IS NULL)" ),
	//Added By 407986 To Fetch Assigned Products For the SP
	@NamedQuery(name = "FetchAssignedProducts", query = "select myTPrdAlgmnt.tPrd.prdId  from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tPrd.prdId  in ?1 and myTPrdAlgmnt.salesPosId = ?2 and myTPrdAlgmnt.salesHierId = ?3 and  myTPrdAlgmnt.tenantId = ?4 and myTPrdAlgmnt.activeFlag = 'Y' " ),
	@NamedQuery(name = "FetchPrdAlgmntsFrRemoveCr", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt "
			+ "WHERE myTPrdAlgmnt.prdAlgmntId in ?1 AND myTPrdAlgmnt.tenantId =?2 and myTPrdAlgmnt.effEndDt<=?3"),
	@NamedQuery(name = "FindTPrdAlgmntsForUnAssignSPCount", query = "Select count(p) from TPrdAlgmnt p where p.salesPosId =?1 AND p.salesHierId = ?2 AND p.algmntId = ?3 AND p.bussUnitId = ?4 AND p.salesTeamId = ?5 AND p.activeFlag = ?6 AND p.tenantId = ?7 "),
	@NamedQuery(name = "GetAssignPrd", query = "select myTPrdAlgmnt.tPrd.prdId , myTPrdAlgmnt.effStartDt , myTPrdAlgmnt.effEndDt FROM TPrdAlgmnt myTPrdAlgmnt" +
		" WHERE myTPrdAlgmnt.tPrd.prdId IN ?1 AND " +
		"myTPrdAlgmnt.algmntId = ?2  AND " +
		"myTPrdAlgmnt.bussUnitId = ?3 AND " +
		"myTPrdAlgmnt.salesTeamId = ?4 AND "+ 
		"myTPrdAlgmnt.salesPosId = ?5 AND " +
		"myTPrdAlgmnt.tenantId = ?6 AND "+
		"myTPrdAlgmnt.activeFlag = 'Y'"),
		@NamedQuery(name = "GetAssignPrdByPrdId", query = "select myTPrdAlgmnt FROM TPrdAlgmnt myTPrdAlgmnt" +
				" WHERE myTPrdAlgmnt.tPrd.prdId = ?1 AND " +
				"myTPrdAlgmnt.tenantId = ?2 AND "+
				"myTPrdAlgmnt.activeFlag = 'Y'"),
				
		@NamedQuery(name = "getPrdAlListForSP", query = "select myTPrdAlgmnt from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.salesPosId = ?1 "
				+ " and myTPrdAlgmnt.salesHierId = ?2 and  myTPrdAlgmnt.algmntId = ?3 and  myTPrdAlgmnt.bussUnitId = ?4 and  myTPrdAlgmnt.salesTeamId = ?5 "
				+ " and myTPrdAlgmnt.tenantId = ?7 and myTPrdAlgmnt.tPrd.prdId IN ?6 and myTPrdAlgmnt.activeFlag = 'Y' "),
				
		@NamedQuery(name = "getProductNamesForSalesPosition", query = "select myTPrd.prdName from TPrdAlgmnt myTPrdAlgmnt,TPrd myTPrd where " 
				+ " myTPrdAlgmnt.tPrd.prdId = myTPrd.prdId and myTPrdAlgmnt.salesPosId = ?1 "
				+ " and myTPrdAlgmnt.salesHierId = ?2 "
				+ " and (myTPrdAlgmnt.effEndDt IS NULL OR myTPrdAlgmnt.effEndDt >= ?3) and myTPrdAlgmnt.activeFlag='Y' and myTPrdAlgmnt.tenantId = ?4"),
		@NamedQuery(name = "findTPrdAlgmntByTPrdAndSPId", query = "select myTPrdAlgmnt.prdAlgmntId from TPrdAlgmnt myTPrdAlgmnt where myTPrdAlgmnt.tPrd.prdId = ?1 and myTPrdAlgmnt.salesPosId = ?2"),	

@NamedQuery(name = "fetchCountOfPrdForSalesPositions", query = "Select Count(c.tPrd.prdId) from TPrdAlgmnt c where c.tenantId = ?1 AND c.salesPosId IN ?2 AND c.activeFlag = 'Y' AND (c.effEndDt >= ?3 or c.effEndDt IS NULL) and (c.effStartDt <= ?3)" ),
@NamedQuery(name = "getProductNamesForSalesPositions", query = "select myTPrd.prdName from TPrdAlgmnt myTPrdAlgmnt,TPrd myTPrd where " 
		+ " myTPrdAlgmnt.tPrd.prdId = myTPrd.prdId and myTPrdAlgmnt.salesPosId in ?1 "
		+ " and (myTPrdAlgmnt.effEndDt IS NULL OR myTPrdAlgmnt.effEndDt >= ?2) and myTPrdAlgmnt.activeFlag='Y' and myTPrdAlgmnt.tenantId = ?3 and (myTPrdAlgmnt.effStartDt <= ?2)"),

})

@Table(name = "t_prd_algmnt", uniqueConstraints = @UniqueConstraint(columnNames = { "prd_algmnt_id" }))
public class TPrdAlgmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "prd_algmnt_id", nullable = false, length = 255)
	private Long prdAlgmntId;

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
	@Length(max = 10)
	@Column(name = "wtg", nullable = true, length = 10)
	private String wtg;

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
	@NotNull
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;

	/**
	 * 
	 * @generated
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "prd_id", referencedColumnName = "prd_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TPrd tPrd;

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

/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdAlgmnt")
	private Set<TPrdAlgmntHist> tPrdAlgmntHistss;*/

/*	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdAlgmnt")
	private Set<TCustPrdAlgmnt> tCustPrdAlgmntss;*/

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tPrdAlgmnt")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TPrdAlgmntAttr> tPrdAlgmntAttrss;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false) 
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TSalesPos tSalesPos;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tPrdAlgmnt")
	@NotAudited
	private Set<TCustPrdAlgmnt> tCustPrdAlgmnts;


	/**
	 *
	 * @generated
	 */
	public TPrdAlgmnt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrdAlgmntId(final Long prdAlgmntId) {
		this.prdAlgmntId = prdAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getPrdAlgmntId() {
		return this.prdAlgmntId;
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

	public void setWtg(final String wtg) {
		this.wtg = wtg;
	}

	/**
	 * 
	 * @generated
	 */
	public String getWtg() {
		return this.wtg;
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

	public void setSalesPosId(final Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getSalesPosId() {
		return this.salesPosId;
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
	public TPrd gettPrd() {
		return tPrd;
	}

	/**
	 * 
	 * @generated
	 */
	public void settPrd(TPrd tPrd) {
		this.tPrd = tPrd;
	}

	/**
	 * 
	 * @generated
	 */
/*	public void setTPrdAlgmntHistss(final Set<TPrdAlgmntHist> tPrdAlgmntHistss) {
		this.tPrdAlgmntHistss = tPrdAlgmntHistss;
	}
*/
	/**
	 * 
	 * @generated
	 */
/*	public Set<TCustPrdAlgmnt> getTCustPrdAlgmntss() {
		return this.tCustPrdAlgmntss;
	}
*/
	/**
	 * 
	 * @generated
	 */
/*	public void setTCustPrdAlgmntss(final Set<TCustPrdAlgmnt> tCustPrdAlgmntss) {
		this.tCustPrdAlgmntss = tCustPrdAlgmntss;
	}
*/

/**
	 * 
	 * @generated
	 */
	public Set<TPrdAlgmntAttr> getTPrdAlgmntAttrss() {
		return this.tPrdAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTPrdAlgmntAttrss(final Set<TPrdAlgmntAttr> tPrdAlgmntAttrss) {
		this.tPrdAlgmntAttrss = tPrdAlgmntAttrss;
	}
	

	public Set<TCustPrdAlgmnt> getTCustPrdAlgmnts() {
		return tCustPrdAlgmnts;
	}

	public void setTCustPrdAlgmnts(Set<TCustPrdAlgmnt> tCustPrdAlgmnts) {
		this.tCustPrdAlgmnts = tCustPrdAlgmnts;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TPrdAlgmnt that) {
		setPrdAlgmntId(that.getPrdAlgmntId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setWtg(that.getWtg());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());		
		setSalesPosId(that.getSalesPosId());
		setSalesHierId(that.getSalesHierId());
		setAlgmntId(that.getAlgmntId());
		setBussUnitId(that.getBussUnitId());
		setSalesTeamId(that.getSalesTeamId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((prdAlgmntId == null) ? 0 : prdAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("prdAlgmntId=[").append(prdAlgmntId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("wtg=[").append(wtg).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");		
		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("salesHierId=[").append(salesHierId).append("] ");
		buffer.append("algmntId=[").append(algmntId).append("] ");
		buffer.append("bussUnitId=[").append(bussUnitId).append("] ");
		buffer.append("salesTeamId=[").append(salesTeamId).append("] ");

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
		final TPrdAlgmnt other = (TPrdAlgmnt) obj;
		if (prdAlgmntId == null) {
			if (other.prdAlgmntId != null) {
				return false;
			}
		} else if (!prdAlgmntId.equals(other.prdAlgmntId)) {
			return false;
		}
		return true;
	}

	public TSalesPos gettSalesPos() {
		return tSalesPos;
	}

	public void settSalesPos(TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
	}
}