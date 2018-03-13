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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TSalesPos 
 * The corresponding mapping table is t_sales_pos 
 */
/*@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable(value = true)*/
@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTSalesPoss", query = "select myTSalesPos from TSalesPos myTSalesPos"),		
		@NamedQuery(name = "CountTSalesPoss", query = "Select Count(c) from TSalesPos c"),
		/*@NamedQuery(name = "FindSalesPositionActive", query = "select myTSalesPos.posCd, myTSalesPos.salesPosId, myTSalesPos.tAlgmntSalesHier.salesHierId, myTSalesPos.posName, myTSalesPos.effEndDt,  myTSalesPos.effStartDt, myTSalesPos.jobShareFlag, myTEmpAlgmnt.orgRoleId, myTEmpAlgmnt.tAlgmntSalesRole.roleName "+
		                                              " from TSalesPos myTSalesPos ,TEmpAlgmnt myTEmpAlgmnt "+
		                                              " where myTSalesPos.salesPosId = myTEmpAlgmnt.salesPosId "+
		                                              " AND myTSalesPos.tAlgmntSalesHier.salesHierId = myTEmpAlgmnt.salesHierId "+
		                                              " AND myTEmpAlgmnt.tPers.staffId=?1 AND myTSalesPos.salesPosId = ?2 "+
		                                              " AND myTSalesPos.activeFlag =?3 "+
		                                              " AND myTEmpAlgmnt.activeFlag=?3 AND ((myTSalesPos.effStartDt<=?4 AND (myTSalesPos.effEndDt >?4 OR myTSalesPos.effEndDt IS NULL )) OR(myTSalesPos.effStartDt>?4)) "+
		                                              " AND ((myTEmpAlgmnt.tAlgmntSalesRole.effStartDt<=?4 AND (myTEmpAlgmnt.tAlgmntSalesRole.effEndDt >?4 OR myTEmpAlgmnt.tAlgmntSalesRole.effEndDt IS NULL )) OR(myTEmpAlgmnt.tAlgmntSalesRole.effStartDt>?4)) "+
		                                              " AND myTSalesPos.tenantId = ?5 AND myTEmpAlgmnt.tenantId=?5"),*/
		/*@NamedQuery(name = "FindSalesPositionActiveChild", query = "select myTSalesPos.posCd, myTSalesPos.salesPosId, myTSalesPos.tAlgmntSalesHier.salesHierId, myTSalesPos.posName, myTSalesPos.effEndDt,  myTSalesPos.effStartDt, myTSalesPos.jobShareFlag,  myTAlgmntSalesRole.roleName"+
				                                              " from TSalesPos myTSalesPos, TAlgmntSalesRole myTAlgmntSalesRole "+
				                                              " where  myTSalesPos.salesPosId = ?1 "+
				                                              " AND myTSalesPos.tAlgmntSalesHier.salesHierId = ?2 AND myTSalesPos.activeFlag =?3 "+
				                                              " AND ((myTSalesPos.effStartDt<=?4 AND (myTSalesPos.effEndDt >?4 OR myTSalesPos.effEndDt IS NULL )) OR(myTSalesPos.effStartDt>?4)) "+
				                                              " AND myTSalesPos.tenantId = ?5  AND myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId = ?2 AND myTAlgmntSalesRole.tenantId = ?5 " +
				                                              " AND  myTSalesPos.tAlgmntSalesHier.salesHierId =  myTAlgmntSalesRole.tAlgmntSalesRoleId.salesHierId "),  */                                            
		@NamedQuery(name = "FindTSalesPosByTAlgmntSalesHier", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "CountTSalesPossByTAlgmntSalesHier", query = "select Count(*) from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesHier = ?1 "),
		@NamedQuery(name = "FindTSalesPosByTAlgmntSalesTeam", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTSalesPosByIds", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesHier.salesHierId = ?1 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4  order by myTSalesPos.posName"),
		@NamedQuery(name = "CountTSalesPossByTAlgmntSalesTeam", query = "select Count(*) from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesTeam = ?1 "),
		//@NamedQuery(name = "FindTSalesPosByTWkflwStatus", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.tWkflwStatus = ?1 "),		
		//@NamedQuery(name = "FindTSalesPosByPodTitle", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.podTitle =?1 AND myTSalesPos.tenantId =?2"),
		@NamedQuery(name = "FindTSalesPosBySalesHierId", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.posName =?1 AND myTSalesPos.tAlgmntSalesHier.salesHierId =?2"),
		//@NamedQuery(name = "CountTSalesPossByTWkflwStatus", query = "select Count(*) from TSalesPos myTSalesPos where myTSalesPos.tWkflwStatus = ?1 "),
		@NamedQuery(name = "FindMaxSalesPosId", query = "select Max(myTSalesPos.salesPosId) from TSalesPos myTSalesPos where myTSalesPos.tenantId=?1"),
		@NamedQuery(name = "FindTSalesPosById", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 "),
		@NamedQuery(name = "FindTSalesPosByIdandTenantId", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 "+
						   " and myTSalesPos.tenantId = ?2 "),
	@NamedQuery(name = "FetchTSalPosHierId", query = "select myTSalesPos.prnSalesPosId,myTSalesPos.prnSalesHierId from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 AND myTSalesPos.tAlgmntSalesHier.salesHierId =?2 AND myTSalesPos.tenantId=?3"),
		@NamedQuery(name = "FindTSalPosHierId", query = "select myTSalesPos.prnSalesPosId,myTSalesPos.prnSalesHierId from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 AND myTSalesPos.tAlgmntSalesHier.salesHierId =?2"),
		@NamedQuery(name = "FindChildrenBySalPosId", query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.posName,myTSalesPos.activeFlag ,myTSalesPos.effStartDt,myTSalesPos.effEndDt from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.tenantId = ?2 AND myTSalesPos.activeFlag='Y' "),
		//@NamedQuery(name = "FindTSalesPosByPod", query = "select myTSalesPos.podTitle from TSalesPos myTSalesPos where myTSalesPos.podTitle = ?1 AND myTSalesPos.tenantId = ?2 "),
		@NamedQuery(name = "createTreeQuery", query = "select myTSalesPos from TSalesPos myTSalesPos "+
													  " where myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 "+
													  " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 "+
													  " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 "+
													  " and myTSalesPos.tenantId = ?4 and myTSalesPos.activeFlag = 'Y'"+
													  " order by myTSalesPos.prnSalesHierId,myTSalesPos.prnSalesPosId "
				),
		@NamedQuery(name = "getTSalesPosDtlNew", query = "select myTSalesPos from TSalesPos myTSalesPos "+
						  " where myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 "+
						  " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 "+
						  " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 "+
						  " and myTSalesPos.tAlgmntSalesHier.salesHierId = ?4 "+
						  " and myTSalesPos.activeFlag = 'Y' and myTSalesPos.tenantId = ?5 "
				),
		@NamedQuery(name = "findTSalesPositionBySalesPosId", query= "select myTSalesPos from TSalesPos myTSalesPos "+
						   											" where myTSalesPos.salesPosId = ?1 and myTSalesPos.activeFlag = 'Y' "+
						   											" and myTSalesPos.tenantId = ?2 "),
				
				
		/*@NamedQuery(name = "findTSalesPositionAndEmpAlgmnt", query= "select myTSalesPos.posCd, myTSalesPos.salesPosId, myTSalesPos.tAlgmntSalesHier.salesHierId, myTSalesPos.posName, myTSalesPos.effEndDt,  myTSalesPos.effStartDt, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId, myTEmpAlgmnt.orgRoleId, myTEmpAlgmnt.tAlgmntSalesRole.roleName "+
                        "from TSalesPos myTSalesPos, TEmpAlgmnt myTEmpAlgmnt where myTSalesPos.salesPosId=myTEmpAlgmnt.salesPosId AND  myTSalesPos.tAlgmntSalesHier.salesHierId = myTEmpAlgmnt.salesHierId AND myTEmpAlgmnt.tPers.staffId =?1 AND ((myTEmpAlgmnt.effStartDt<= ?2 AND (myTEmpAlgmnt.effEndDt >= ?2 OR myTEmpAlgmnt.effEndDt IS NULL)) OR myTEmpAlgmnt.effStartDt > ?2)  AND   myTEmpAlgmnt.activeFlag= ?7  AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 AND   myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 AND   myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 "+
                        "AND   myTSalesPos.tenantId = ?6 AND   myTSalesPos.activeFlag=?7 AND ((myTSalesPos.effStartDt <= ?2 AND( myTSalesPos.effEndDt  >= ?2 OR myTSalesPos.effEndDt IS NULL)) OR myTSalesPos.effStartDt> ?2) "+
						"AND ((myTEmpAlgmnt.tAlgmntSalesRole.effStartDt<=?2 AND (myTEmpAlgmnt.tAlgmntSalesRole.effEndDt >?2 OR myTEmpAlgmnt.tAlgmntSalesRole.effEndDt IS NULL )) OR(myTEmpAlgmnt.tAlgmntSalesRole.effStartDt>?2)) "
			   ),*/
			   @NamedQuery(name = "fetchChildSalesPositionForSP", query = "select myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId, myTSalesPos.salesPosId, myTSalesPos.tAlgmntSalesHier.salesHierId, myTSalesPos.posName, myTSalesPos.prnSalesPosId, "+
			                                                              "myTSalesPos.prnSalesHierId from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId = ?1 AND myTSalesPos.prnSalesHierId = ?2 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 AND myTSalesPos.activeFlag = 'Y' AND (myTSalesPos.effEndDt >= ?6 OR myTSalesPos.effEndDt is null) AND myTSalesPos.tenantId = ?7  "),
				@NamedQuery(name = "findTSalesPositionBySalesHierId", query= "select myTSalesPos from TSalesPos myTSalesPos "+
						 " where myTSalesPos.tAlgmntSalesHier.salesHierId = ?1 and myTSalesPos.activeFlag = 'Y' "+
						 " and myTSalesPos.tenantId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 "+
						 " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 order by myTSalesPos.posName "
),

	@NamedQuery(name = "findTSalesPositionBySalesHierIdFrMtr", query= "select myTSalesPos from TSalesPos myTSalesPos "+
						 " where myTSalesPos.tAlgmntSalesHier.salesHierId = ?1 and myTSalesPos.activeFlag = 'Y' "+
						 " and myTSalesPos.tenantId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 "+
						 " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 order by myTSalesPos.posName "
),

@NamedQuery(name = "FindTSalesPositionByPrnSalesHerId", query= "select c from TSalesPos c  where c.prnSalesHierId = ?1 and c.activeFlag = 'Y' "+
								" and c.tenantId = ?2  "),
		@NamedQuery(name = "FindChildrenBySPHId", query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.posName " +
				"from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesHier.salesHierId IN ?1 and myTSalesPos.activeFlag = 'Y' and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId = ?3"),
		@NamedQuery(name = "searchChildren", query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.prnSalesPosId," +
				"myTSalesPos.posName from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and (myTSalesPos.posName like ?2 or myTSalesPos.posCd like ?3)" +
				" and myTSalesPos.tenantId = ?4 and myTSalesPos.activeFlag = 'Y'"),
		@NamedQuery(name = "SPFindChildrenBySalPosId", query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.prnSalesPosId," +
				"myTSalesPos.posName from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.tenantId = ?2 and myTSalesPos.activeFlag = 'Y'"),
		@NamedQuery(name = "MaxOfTSalesPoss", query = "select max(myTSalesPos.salesPosId) from TSalesPos myTSalesPos "),
		@NamedQuery(name = "UniqueChildsalesHierID", query = "select distinct myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos where " +
				"myTSalesPos.prnSalesHierId IN (?1) and myTSalesPos.tenantId = ?2"),
				@NamedQuery(name = "findActiveSalesPositionsByalbust",query = "select myTSalesPos from TSalesPos myTSalesPos, TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId  " +
						" and myTSalesPos.salesPosId =?1 and myTSalesPos.tAlgmntSalesHier.salesHierId=?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 and myTSalesPos.tenantId =?6"),
						
						@NamedQuery(name = "findSalesPositionTreeDetails",query = "select myTSalesPos.posName, myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.salesPosId from TSalesPos myTSalesPos where myTSalesPos.salesPosId in ?1  and myTSalesPos.tenantId =?2"),				
						
		@NamedQuery(name = "findChildSalesPositionsBySPId",query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId =?1 and myTSalesPos.prnSalesHierId=?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 and myTSalesPos.tenantId =?6"),		
			@NamedQuery(name = "getSpIdByPosCd",query = "select myTSalesPos.salesPosId from TSalesPos myTSalesPos where myTSalesPos.posCd= ?1 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?3 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?4 and myTSalesPos.tAlgmntSalesHier.salesHierId <= ?5 and myTSalesPos.salesPosId != ?6 and myTSalesPos.tenantId = ?7 "),
			@NamedQuery(name = "FindActiveChildrenBySalPosId",query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.posName,myTSalesPos.activeFlag from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.activeFlag = 'Y' and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId = ?3"),
			@NamedQuery(name = "FindSPIdByPOSName",query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos where myTSalesPos.posName=?1 and myTSalesPos.tenantId = ?2"),
			@NamedQuery(name = "FindSPIdByPOSCode",query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos where myTSalesPos.posCd=?1 and myTSalesPos.tenantId = ?2"),
			@NamedQuery(name = "FetchTSalPosHier", query = "select myTSalesPos.prnSalesPosId,myTSalesPos.prnSalesHierId,myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 AND myTSalesPos.tAlgmntSalesHier.salesHierId =?2 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId=?3 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=?4 AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=?5 AND myTSalesPos.tenantId=?6 "),
			@NamedQuery(name = "findActiveChildSalesPositionsBySPId",query ="select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.posName from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId = ?1 and myTSalesPos.prnSalesHierId = ?2 and myTSalesPos.activeFlag = 'Y' and (myTSalesPos.effEndDt >?3 OR myTSalesPos.effEndDt IS NULL) and myTSalesPos.tenantId = ?4 "),
			@NamedQuery(name = "findActiveSalesPosNSalesHierIDByALBUST", query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos "
				+ " where myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 "
				+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 "
				+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 "
				+ " and myTSalesPos.tenantId = ?4 and myTSalesPos.activeFlag = 'Y'"),
			@NamedQuery(name = "GetSalesPosMetricCal",query ="select pos.salesPosId,pos.posName,mtrv.mtrId,mtr.mtrName,mtrv.mtrValue from TSalesPos pos left join pos.tSalesPosMtrValues mtrv left join mtrv.tMtrs mtr where pos.prnSalesPosId = ?1 and pos.prnSalesHierId = ?2 and pos.activeFlag = 'Y' and (pos.effEndDt >?3 OR pos.effEndDt IS NULL) and pos.tenantId = ?4 and (mtrv.activeFlag = 'Y' or mtrv.activeFlag is NULL) and (mtr.activeFlag = 'Y' or mtr.activeFlag is NULL) order by mtrv.createDt desc"),
			@NamedQuery(name = "GetCompareSalesPosMetricCal",query ="select pos.salesPosId,pos.posName,mtrv.mtrId,mtr.mtrName,mtrv.mtrValue from TSalesPos pos left join pos.tSalesPosMtrValues mtrv left join mtrv.tMtrs mtr where pos.salesPosId in ?1 and pos.tenantId = ?2 and (mtrv.activeFlag = 'Y' or mtrv.activeFlag is NULL) and (mtr.activeFlag = 'Y' or mtr.activeFlag is NULL) order by mtrv.createDt desc"),
			@NamedQuery(name = "FindActiveChildrenForReportsBySalPosIds",query = "select myTSalesPos.salesPosId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.posName,myTSalesPos.posCd, myTSalesPos.prnSalesPosId, myTSalesPos.rootHierId, myTSalesPos.tAlgmntSalesHier.hierName, myTSalesPos.prnSalesHierId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId  from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.activeFlag = 'Y' and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId = ?3"),
			@NamedQuery(name = "FindAllActiveTSalesPoss", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId=?1 and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId=?3 and  myTSalesPos.activeFlag='Y'"),
			@NamedQuery(name = "GetActiveCDSBySalPosId",query = "select myTSalesPos.salesPosId,myTSalesPos.posCd from TSalesPos myTSalesPos where myTSalesPos.salesPosId IN ?1 and myTSalesPos.activeFlag = 'Y' and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId = ?3"),
			@NamedQuery(name = "FindAllInActiveTSalesPoss", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.effEndDt <?1 and myTSalesPos.tenantId=?2 and  myTSalesPos.activeFlag=?3"),
			@NamedQuery(name = "FindSpNameByALBUST",query = "select myTSalesPos.posName from TSalesPos myTSalesPos, TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId  " +
					" and myTSalesPos.salesPosId =?1 and myTSalesPos.tAlgmntSalesHier.salesHierId=?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 and myTSalesPos.tenantId =?6"),
			@NamedQuery(name = "findTSalesPosBySalesHierIdFrGoTo", query= "select myTSalesPos from TSalesPos myTSalesPos "+
							 " where myTSalesPos.tAlgmntSalesHier.salesHierId = ?1 and myTSalesPos.activeFlag = 'Y' "+
							 " and myTSalesPos.tenantId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 "+
							 " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 and (myTSalesPos.prnSalesPosId = ?6 or myTSalesPos.salesPosId = ?6) order by myTSalesPos.posName "),
			@NamedQuery(name = "findSalesPositionBySalesPosId", query= "select myTSalesPos from TSalesPos myTSalesPos "+
								" where myTSalesPos.salesPosId = ?1  and myTSalesPos.tenantId = ?2  "),
			@NamedQuery(name = "FindSPDetails", query= "select myTSalesPos.posName, myTSalesPos.posCd, myTSalesPos.effEndDt from TSalesPos myTSalesPos "+
										" where myTSalesPos.salesPosId = ?1  and myTSalesPos.tenantId = ?2  "),
			@NamedQuery(name = "FindSPParentDetails", query= "select myTSalesPos.salesPosId,myTSalesPos.posName, myTSalesPos.posCd from TSalesPos myTSalesPos "+
												" where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.activeFlag = 'Y'" +
												" and (myTSalesPos.effEndDt >?2 OR myTSalesPos.effEndDt IS NULL ) and myTSalesPos.tenantId = ?3 "),
			@NamedQuery(name = "findTSalesPosBySPId", query= "select myTSalesPos from TSalesPos myTSalesPos "+
			   									" where myTSalesPos.salesPosId = ?1   and myTSalesPos.tenantId = ?2 "),
			@NamedQuery(name = "FindTSalesPosEffDates", query = "select myTSalesPos.effStartDt , myTSalesPos.effEndDt from TSalesPos myTSalesPos " +
							" where myTSalesPos.tAlgmntSalesHier.salesHierId = ?1  and myTSalesPos.salesPosId = ?2 "+
							" and myTSalesPos.tenantId = ?3 "),
							
			@NamedQuery(name = "findPrnSalesHierIdByALBUST", query = "select myTSalesPos.tAlgmntSalesHier.salesHierId from TSalesPos myTSalesPos "
									+ " where myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 "
									+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 "
									+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 "
									+ " and myTSalesPos.tenantId = ?4 and myTSalesPos.prnSalesPosId is null and myTSalesPos.activeFlag = 'Y'"),
			@NamedQuery(name = "FindFullSPDetails", query= "select child.salesPosId,child.tAlgmntSalesHier.salesHierId,child.posName,child.posCd,child.effStartDt,child.effEndDt,child.updateDt,child.updatedBy,child.activeFlag,child.jobShareFlag,child.prnSalesPosId,child.prnSalesHierId,child.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,child.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,child.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId from TSalesPos child where child.salesPosId = ?1 and child.tenantId = ?2"),
			@NamedQuery(name = "GetSPNameAndParentSPId", query= "select myTSalesPos.posName, myTSalesPos.prnSalesPosId from TSalesPos myTSalesPos  where myTSalesPos.salesPosId = ?1  and myTSalesPos.tenantId = ?2  "),
			@NamedQuery(name = "FindTSalesPositionByPrnSalesHerIdCount", query= "select count(c) from TSalesPos c  where c.prnSalesHierId = ?1 and c.activeFlag = 'Y' and c.tenantId = ?2  "),
			@NamedQuery(name = "FindActiveSPForActAlgmnt", query = "select sp.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, sp.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId," +
					" sp.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId, sp.salesPosId, " +
					" sp.tAlgmntSalesHier.salesHierId, sp.posName,sp.posCd,  " +
					" ash.hierName, sp.prnSalesPosId,  sp.prnSalesHierId, sp.effStartDt, sp.effEndDt, ash.effEndDt, ash.effStartDt " +
					" from TSalesPos sp inner join sp.tAlgmntSalesHier ash " +
					" inner join sp.tAlgmntSalesTeam ast " +
					" WHERE (ast.effEndDt is null or ast.effEndDt >= ?1 )  AND ast.tenantId =sp.tenantId " +
					" AND (sp.effEndDt is null or sp.effEndDt >= ?1 ) AND sp.tenantId = ?2 "),
			@NamedQuery(name = "FindSpNameBySPId",query = "select myTSalesPos.posName from TSalesPos myTSalesPos where  " +
					" myTSalesPos.salesPosId =?1 and myTSalesPos.tAlgmntSalesHier.salesHierId=?2 and myTSalesPos.tenantId =?3"),
			@NamedQuery(name = "FindSpNameAndDates",query = "select c.posName,c.effStartDt,c.effEndDt from TSalesPos c where  " +
							" c.salesPosId =?1 and c.tAlgmntSalesHier.salesHierId=?2 and c.tenantId =?3"),
			@NamedQuery(name = "createSPTreeQuery", query = "select myTSalesPos from TSalesPos myTSalesPos "+
									  " where myTSalesPos.salesPosId = ?1 "+
									  " and myTSalesPos.tAlgmntSalesHier.salesHierId = ?2 "+
									  " and myTSalesPos.tenantId = ?3 and myTSalesPos.activeFlag = 'Y'"+
									  " order by myTSalesPos.prnSalesHierId,myTSalesPos.prnSalesPosId "
									),
			@NamedQuery(name = "GetAllSPByName", query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.posName like ?1 and myTSalesPos.tenantId =?2 and myTSalesPos.activeFlag = 'Y'" 
											+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?3 "
											+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?4 "
											+ " and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?5 "), 	
											
			@NamedQuery(name = "fetchAllSalesPositionsByAl",query = "select myTSalesPos from TSalesPos myTSalesPos, TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId  " +
													" and myTSalesPos.activeFlag = 'Y' and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTSalesPos.tenantId =?4 "),
				
			@NamedQuery(name = "getMirroredSalesPositionsBySP",query = "select myTSalesPos from TSalesPos myTSalesPos where myTSalesPos.activeFlag = 'Y' and myTSalesPos.salesPosId = ?1 and myTSalesPos.tenantId =?2 "),
			@NamedQuery(name = "getSalesPosDetails", query = "select myTSalesPos from TTerrZipAlgmnt myTTerrZipAlgmnt, TSalesPos myTSalesPos where myTSalesPos.salesPosId=myTTerrZipAlgmnt.tSalesPos.salesPosId  and  myTTerrZipAlgmnt.postalCd = ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' and myTTerrZipAlgmnt.assignedFlag='Y'"),
			@NamedQuery(name = "checkIfActiveSP",query = "select count(myTSalesPos) from TSalesPos myTSalesPos where myTSalesPos.activeFlag = 'Y' and myTSalesPos.salesPosId = ?1 and myTSalesPos.tenantId =?2 "),
			@NamedQuery(name = "countOfBaseSPFrST",query = "select count(myTSalesPos) from TSalesPos myTSalesPos where myTSalesPos.activeFlag = 'Y' and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and myTSalesPos.tenantId = ?4 and myTSalesPos.salesPosTypeId = 1 AND myTSalesPos.tMirType.mirTypeId != 1"),
			@NamedQuery(name = "getChildSPs",query = "select myTSalesPos.salesPosId from TSalesPos myTSalesPos where myTSalesPos.prnSalesPosId IN ?1 and myTSalesPos.activeFlag = 'Y' and myTSalesPos.tenantId = ?2 "),
			@NamedQuery(name = "fetchSpNamesFrSpIds" , query = "select myTSalesPos.posName from TSalesPos myTSalesPos where myTSalesPos.salesPosId IN ?1 and myTSalesPos.tenantId = ?2 "),
			@NamedQuery(name = "findTSalesPositionById" , query = "select myTSalesPos.salesPosTypeId,myTSalesPos.tMirType.mirTypeId from TSalesPos myTSalesPos where myTSalesPos.salesPosId = ?1 and myTSalesPos.tenantId = ?2"),
			@NamedQuery(name = "findSPNameAndCodeForEmp" , query = "select myTSalesPos.posName,myTSalesPos.posCd from TSalesPos myTSalesPos,TEmpAlgmnt myTEmpAlgmnt WHERE myTEmpAlgmnt.salesPosId=myTSalesPos.salesPosId  AND myTEmpAlgmnt.tPers.staffId = ?1 and myTSalesPos.tenantId = ?2")
			})
@Table(name = "t_sales_pos")
public class TSalesPos implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_pos_id", nullable = false, length = 255)
	private Long salesPosId;
	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 75)
	@Column(name = "pos_name", nullable = false, length = 75)
	private String posName;

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
	@Length(max = 20)
	@Column(name = "pos_cd", nullable = true, length = 20)
	private String posCd;

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
	/*@Column(name = "shape_polygon", nullable = true, length = 255)
	private byte[] shapePolygon;*/

	/**
	 * 
	 * @generated
	 */

	@Column(name = "prn_sales_pos_id", nullable = true, length = 255)
	private Long prnSalesPosId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prn_sales_hier_id", nullable = true, length = 255)
	private Long prnSalesHierId;
	
	@Column(name = "root_hier_id", nullable = true, length = 255)
	private Long rootHierId;
	
	@Column(name = "ext_attr", nullable = true, length = 255)
	private byte[] sptExtAttrs; 
	
	public byte[] getSptExtAttrs() {
		return sptExtAttrs;
	}

	public void setSptExtAttrs(byte[] sptExtAttrs) {
		this.sptExtAttrs = sptExtAttrs;
	} 
	
	/**
	 * 
	 * @generated
	 */
	/*@Length(max = 75)
	@Column(name = "pod_title", nullable = true, length = 75)
	private String podTitle;*/
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "job_share_flag", nullable = true, length = 1)
	private Character jobShareFlag;

	@ManyToOne
	@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = false, updatable = false)
	//@Valid
	@NotAudited
	private TAlgmntSalesHier tAlgmntSalesHier;
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_pos_type_id", nullable = false, length = 255)
	private Integer salesPosTypeId;

	@ManyToOne
	@JoinColumn(name = "mir_type_id", referencedColumnName = "mir_type_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TMirType tMirType;

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	//@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	/*@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = true, insertable = true, updatable = true)
	//@Valid
   @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TWkflwStatus tWkflwStatus;*/

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPos")
   @NotAudited
	private Set<TTerrZipAlgmnt> tTerrZipAlgmntss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPos")
	@NotAudited
	private Set<TCvgRuleSched> tCvgRuleScheds;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPos")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TSalesPosAttr> tSalesPosAttrss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tSalesPos")	
	@NotAudited	
	private Set<TSalesPosMtrValue> tSalesPosMtrValues;
	
	@ManyToOne
	@JoinColumn(name = "prn_sales_pos_id", referencedColumnName = "sales_pos_id", nullable = true, insertable = false, updatable = false)
	//@Valid
	@NotAudited
	private TSalesPos tSalesPos;
	
	
	

	/**
	 *
	 * @generated
	 */
	public TSalesPos() {
	}

	
	public Long getSalesPosId() {
		return salesPosId;
	}

	public void setSalesPosId(Long salesPosId) {
		this.salesPosId = salesPosId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPosName(final String posName) {
		this.posName = posName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPosName() {
		return this.posName;
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

	public void setPosCd(final String posCd) {
		this.posCd = posCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPosCd() {
		return this.posCd;
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

	/*public void setShapePolygon(final byte[] shapePolygon) {
		this.shapePolygon = shapePolygon;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public byte[] getShapePolygon() {
		return this.shapePolygon;
	}*/

	/**
	 * 
	 * @generated
	 */

	/*public void setPodTitle(final String podTitle) {
		this.podTitle = podTitle;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public String getPodTitle() {
		return this.podTitle;
	}*/
	
	public Character getJobShareFlag() {
		return jobShareFlag;
	}
	public void setJobShareFlag(Character jobShareFlag) {
		this.jobShareFlag = jobShareFlag;
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
	public void setTAlgmntSalesHier(TAlgmntSalesHier tAlgmntSalesHier) {
		this.tAlgmntSalesHier = tAlgmntSalesHier;
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
	/*public TWkflwStatus getTWkflwStatus() {
		return this.tWkflwStatus;

	 * 
	 * @generated
	 *//*
	public void setTWkflwStatus(final TWkflwStatus tWkflwStatus) {
		this.tWkflwStatus = tWkflwStatus;

	}
*/
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

	public TSalesPos gettSalesPos() {
		return tSalesPos;
	}

	public void settSalesPos(TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
	}

	public Long getPrnSalesPosId() {
		return prnSalesPosId;
	}

	public void setPrnSalesPosId(Long prnSalesPosId) {
		this.prnSalesPosId = prnSalesPosId;
	}

	public Long getPrnSalesHierId() {
		return prnSalesHierId;
	}

	public void setPrnSalesHierId(Long prnSalesHierId) {
		this.prnSalesHierId = prnSalesHierId;
	}

	

	public Long getRootHierId() {
		return rootHierId;
	}

	public void setRootHierId(Long rootHierId) {
		this.rootHierId = rootHierId;
	}

	public Set<TCvgRuleSched> gettCvgRuleScheds() {
		return tCvgRuleScheds;
	}

	public void settCvgRuleScheds(Set<TCvgRuleSched> tCvgRuleScheds) {
		this.tCvgRuleScheds = tCvgRuleScheds;
	}
	/**
	 * 
	 * @generated
	 */
	public Set<TSalesPosAttr> getTSalesPosAttrss() {
		return this.tSalesPosAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosAttrss(final Set<TSalesPosAttr> tSalesPosAttrss) {
		this.tSalesPosAttrss = tSalesPosAttrss;
	}
	
	/**
	 * 
	 * @generated
	 */

	public void setSalesPosTypeId(final Integer salesPosTypeId) {
		this.salesPosTypeId = salesPosTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getSalesPosTypeId() {
		return this.salesPosTypeId;
	}

	/**
	 * 
	 * @generated
	 */
	public TMirType getTMirType() {
		return this.tMirType;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMirType(final TMirType tMirType) {
		this.tMirType = tMirType;

	}


	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TSalesPos that) {
		setSalesPosId(that.getSalesPosId());
		setPosName(that.getPosName());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setPosCd(that.getPosCd());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		//setShapePolygon(that.getShapePolygon());
		//setPodTitle(that.getPodTitle());
		setJobShareFlag(that.getJobShareFlag());
		setSalesPosTypeId(that.getSalesPosTypeId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((salesPosId == null) ? 0 : salesPosId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("salesPosId=[").append(salesPosId).append("] ");
		buffer.append("posName=[").append(posName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("posCd=[").append(posCd).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		//buffer.append("shapePolygon=[").append(shapePolygon).append("] ");
		//buffer.append("podTitle=[").append(podTitle).append("] ");
		buffer.append("jobShareFlag=[").append(jobShareFlag).append("] ");
		buffer.append("salesPosTypeId=[").append(salesPosTypeId).append("] ");
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
		final TSalesPos other = (TSalesPos) obj;
		if (salesPosId == null) {
			if (other.salesPosId != null) {
				return false;
			}
		} else if (!salesPosId.equals(other.salesPosId)) {
			return false;
		}
		return true;
	}
}
