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

/**
 * JPA class representing TCustAlgmnt The corresponding mapping table is
 * t_cust_algmnt
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCustAlgmnts", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt"),
		@NamedQuery(name = "FindAllTCustAlgmntsInbetweenDatesForStart", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt,TCust myTCust where myTCustAlgmnt.effStartDt between ?1 and ?2 "
				+ " and myTCustAlgmnt.tCust.custId = myTCust.custId and myTCustAlgmnt.tCust.custId = ?3 and myTCustAlgmnt.tenantId = ?4 and myTCustAlgmnt.activeFlag = ?5" +
				" and myTCustAlgmnt.algmntId = ?6 and myTCustAlgmnt.bussUnitId = ?7 and  myTCustAlgmnt.salesTeamId = ?8 "),
		@NamedQuery(name = "FindAllTCustAlgmntsInbetweenDatesForEnd", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt,TCust myTCust where myTCustAlgmnt.effEndDt between ?1 and ?2 "
				+ " and myTCustAlgmnt.tCust.custId = myTCust.custId and myTCustAlgmnt.tCust.custId = ?3 and myTCustAlgmnt.tenantId = ?4 and myTCustAlgmnt.activeFlag = ?5 " +
				"and myTCustAlgmnt.algmntId = ?6 and myTCustAlgmnt.bussUnitId = ?7 and  myTCustAlgmnt.salesTeamId = ?8 "),
		@NamedQuery(name = "FindAllTCustAlgmntsFromCustId", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt,TCust myTCust where myTCustAlgmnt.tCust.custId = myTCust.custId "
				+ " and myTCust.custId = ?1 and myTCustAlgmnt.tenantId=?2 and (myTCustAlgmnt.effEndDt >= ?3 or myTCustAlgmnt.effEndDt IS NULL) and  myTCustAlgmnt.activeFlag = ?4"),
		@NamedQuery(name = "CountTCustAlgmnts", query = "Select Count(c) from TCustAlgmnt c"),
		@NamedQuery(name = "FindTCustAlgmntByTCust", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust = ?1 "),
			@NamedQuery(name = "CountTCustAlgmntsTenant", query = "Select Count(c) from TCustAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.tCust.custId = ?7  AND c.activeFlag='Y'"),
		//Query to fetch active alignments in given SP for CVG
		@NamedQuery(name = "FetchActAlgmntsALBUST", query = "Select c.custAlgmntId from TCustAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.tCust.custId = ?7  AND c.activeFlag='Y'"),
		@NamedQuery(name = "FetchAllAlgmntsALBUST", query = "Select c.custAlgmntId from TCustAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.tCust.custId = ?7"),
		@NamedQuery(name = "FindTCustAlgmntByJoinQuery", query = "select myTAlgmntTmpl.tBussObjTmpl.tmplId,myTAlgmnt.algmntName,myTBussUnit.bussUnitName,myTSalesTeam.salesTeamName from TCustAlgmnt myTCustAlgmnt,TAlgmnt myTAlgmnt,TAlgmntTmpl myTAlgmntTmpl,TBussObjTmpl myTBussObjTmpl,TBussUnit myTBussUnit,TSalesTeam myTSalesTeam where myTCustAlgmnt.tCust.custId = ?1 AND myTBussObjTmpl.tBussObj.bussObjId = ?2 AND myTBussObjTmpl.tmplId = myTAlgmntTmpl.tBussObjTmpl.tmplId AND myTCustAlgmnt.algmntId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId AND myTCustAlgmnt.bussUnitId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId AND myTCustAlgmnt.bussUnitId = myTBussUnit.bussUnitId AND myTCustAlgmnt.salesTeamId = myTAlgmntTmpl.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId AND myTCustAlgmnt.salesTeamId = myTSalesTeam.tSalesTeamId.salesTeamId AND myTCustAlgmnt.bussUnitId= myTSalesTeam.tSalesTeamId.bussUnitId AND myTAlgmnt.algmntId = myTCustAlgmnt.algmntId AND myTCustAlgmnt.tenantId = ?3"),
		@NamedQuery(name = "CountTCustAlgmntsByTCust", query = "select Count(myTCustAlgmnt) from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust = ?1 "),
		@NamedQuery(name = "FindTCustAlgmnts", query = "select myTCust.custId,myTCust.custCd,myTCustCategory.categoryName,myTCustType.typeName,myTCust.custName,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.salesPosId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt "
				+ "from TCust myTCust,TCustType myTCustType,TCustCategory myTCustCategory,TCustAlgmnt myTCustAlgmnt "
				+ ",TSalesPos myTSalesPos,TAlgmntSalesTeam myTAlgmntSalesTeam, TAlgmntBussRule myTAlgmntBussRule "
				+ "where myTCust.custTypeId=myTCustType.tCustTypeId.custTypeId and myTCust.categoryId=myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myTCust.custId = myTCustAlgmnt.tCust.custId "
				+ "and myTCustAlgmnt.salesPosId=myTSalesPos.salesPosId "
				+ "and myTCustAlgmnt.salesHierId=myTSalesPos.tAlgmntSalesHier.salesHierId "
				+ "and myTSalesPos.tAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmnt.algmntId "
				+ "and myTSalesPos.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId=myTAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId "
				+ "and myTSalesPos.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId=myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId "
				+ "and myTAlgmntBussRule.tAlgmntSalesTeam.tAlgmnt.algmntId=myTAlgmntSalesTeam.tAlgmnt.algmntId "
				+ "and myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId=myTAlgmntSalesTeam.tSalesTeam.tBussUnit.bussUnitId "
				+ "and myTAlgmntBussRule.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId=myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId "
				+ " and myTCustAlgmnt.tenantId=myTCust.tenantId "
				+ "and myTCustAlgmnt.tenantId=myTCustCategory.tenantId "
				+ "and myTCustAlgmnt.tenantId=myTCustType.tenantId "
				+ "and myTCustAlgmnt.tenantId=myTSalesPos.tenantId "
				+ "and myTCustAlgmnt.tenantId=myTAlgmntSalesTeam.tenantId "
				+ "and myTCustAlgmnt.tenantId=myTAlgmntBussRule.tenantId "
				+ "and myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId=1 and myTAlgmntBussRule.value='Y'and myTAlgmntBussRule.activeFlag='Y' "
				+ "and myTCustAlgmnt.activeFlag = 'Y' "
				+ "and myTCust.activeFlag = ?1 and myTCustCategory.categoryName = ?2 and myTCustType.typeName = ?3 and myTCust.custCd like ?4 and myTCust.custName like ?5 and myTCust.custId IN ?6 and myTCustAlgmnt.tenantId = ?7"),
		@NamedQuery(name = "FindTCustAlgmnts2", query = "select myTCustAlgmnt.tCust.custId from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.salesPosId != ?1 and myTCustAlgmnt.salesHierId != ?2 and myTCustAlgmnt.algmntId = ?3 and myTCustAlgmnt.salesTeamId = ?4 and myTCustAlgmnt.bussUnitId = ?5"),
		@NamedQuery(name = "FindTCustAlgmnts3", query = "select myTCust.custId from TCust myTCust where myTCust.custId NOT IN(select myTCustAlgmnt.tCust.custId from TCustAlgmnt myTCustAlgmnt)"),
		@NamedQuery(name = "FindAllTCustAlgmntsBySales", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.salesPosId =?1 AND myTCustAlgmnt.bussUnitId =?2 AND myTCustAlgmnt.algmntId = ?3"),
		@NamedQuery(name = "FindTCustPlannedCallCount", query = "select  Count(ca) from TCustAlgmnt ca, TCustCallPlan ccp where ca.bussUnitId=?1 and ca.algmntId=?2 and ca.salesTeamId=?3 and ca.tenantId=?4 and ca.custAlgmntId=ccp.tCustAlgmnt.custAlgmntId and ccp.tCallPlanType.callPlanTypeId=2 and ccp.activeFlag = 'Y' and ca.activeFlag = 'Y' "),
		@NamedQuery(name = "FindTCustAlgmntsForUnAssign", query = "Select c from TCustAlgmnt c where c.tenantId = ?1 AND c.salesPosId =?2 AND c.salesHierId = ?3 AND c.algmntId = ?4 AND c.bussUnitId = ?5 AND c.salesTeamId = ?6 AND c.activeFlag = ?7 AND c.tCust.custId = ?8"),
		@NamedQuery(name = "FindTCustAlgmntsForUnAssignSP", query = "Select c from TCustAlgmnt c where c.salesPosId =?1 AND c.salesHierId = ?2 AND c.algmntId = ?3 AND c.bussUnitId = ?4 AND c.salesTeamId = ?5 AND c.activeFlag = ?6 AND c.tenantId = ?7 "),
		@NamedQuery(name = "FindTCustAlgmntByALBUST", query = "Select c from TCustAlgmnt c where c.tenantId = ?1 AND c.algmntId = ?2 AND c.bussUnitId = ?3 AND c.salesTeamId = ?4 AND c.activeFlag = ?5 AND c.tCust.custId = ?6"),
		@NamedQuery(name = "FindTCustAlgmntsForCustomerSearch", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.typeName,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
				+ "myTCustAddr.streetName, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postalCd, myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName "
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 and "
				+ "myTCustAlgmnt.salesHierId = ?4 and  myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and "
				+ "(myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag = ?8 and "
				+ "myTCustAlgmnt.tCust.custName like ?9 and "
				+ "myTCustType.tCustTypeId.localeId = ?10 and "
				+ "myTCustAddr.prAddrFlag = ?11 and myTCustAddr.activeFlag = ?8 and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?10"),
		@NamedQuery(name = "FindTCustAlgmntsForPopUp", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tCust.custId =?6 and myTCustAlgmnt.tenantId = ?7"),
		@NamedQuery(name = "FindActPropCustomers", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and  (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS NULL) and myTCustAlgmnt.activeFlag = ?8"),
		@NamedQuery(name = "FindCountOfTCustAlgmntandChild", query = "select count(obj.tCust.custId) FROM TCustAlgmnt obj "
                + "where obj.algmntId = ?3 and obj.bussUnitId = ?4 and  obj.salesTeamId = ?5 "
                + "and  obj.salesPosId in (?2) and obj.tenantId = ?1 "
                + "and (obj.effEndDt >= ?6 or obj.effEndDt IS null) and (obj.activeFlag ='Y') "),
		
		@NamedQuery(name = "fetchAssignedActPropCust", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.typeName,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
				+ "myTCustAddr.streetName, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postalCd, myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag, myTCustAlgmnt.prtTypeId, myTCustAlgmnt.implFlag , myTCustAlgmnt.affFlag "
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 and "
				+ "myTCustAlgmnt.salesHierId = ?4 and  myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and "
				+ "(myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag =?8) and "
				+ "myTCustType.tCustTypeId.localeId = ?9 and "
				+ "myTCustAddr.prAddrFlag = ?10 and myTCustAddr.activeFlag = ?11 and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?12"),
		
		@NamedQuery(name = "FindCustDtlsByCustAlgmntId", query = "select myTCustAlgmnt.activeFlag, myTCustAlgmnt.targetFlag, myTCustAlgmnt.custAlgmntId, "
				+ "myTCustAlgmnt.effStartDt, myTCustAlgmnt.effEndDt, myTSalesPos.posName, myTSalesPos.effStartDt, myTSalesPos.effEndDt, myCust.custName, "
				+ "myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myCust.custId, myCust.custCd, myTCustCategory.tCustCategoryId.custCategoryId, "
				+ " myTCustCategory.categoryName, myTCustAlgmnt.prtTypeId "
				+ "FROM TCustAlgmnt myTCustAlgmnt,TCust myCust, TCustCategory myTCustCategory, TSalesPos myTSalesPos "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myTCustCategory.tCustCategoryId.custCategoryId = myCust.categoryId "
				+ "and myTCustCategory.tenantId = myCust.tenantId "
				+ "and myTSalesPos.activeFlag = myTCustAlgmnt.activeFlag "
				+ "and myTSalesPos.tenantId = myTCustAlgmnt.tenantId "
				+ "and myTCustAlgmnt.custAlgmntId IN ?1 and myTSalesPos.tAlgmntSalesHier.salesHierId = ?2 and  myTSalesPos.salesPosId = ?3 "
				+ "and myTCustCategory.tCustCategoryId.localeId = ?4 "),
				
				@NamedQuery(name = "FindCountOfTCustAlgmnt", query = "Select Count(distinct myTCustAlgmnt.tCust.custId) " 
						+ "FROM TCustAlgmnt myTCustAlgmnt "
						+ "WHERE myTCustAlgmnt.salesHierId = ?2 and  myTCustAlgmnt.salesPosId = ?1 and myTCustAlgmnt.tenantId = ?5 and "
						+ "(myTCustAlgmnt.effEndDt >= ?3 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag = ?4 and myTCustAlgmnt.targetFlag = 'Y'"),
				
				
		//Added By 407986 To Fetch All the Assigned Customers
		
		@NamedQuery(name = "FetchAssignedCustomers", query = "select myTCustAlgmnt.tCust.custId  from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId  in ?1 and myTCustAlgmnt.salesPosId = ?2 and myTCustAlgmnt.salesHierId = ?3 and  myTCustAlgmnt.tenantId = ?4 and myTCustAlgmnt.activeFlag = 'Y' " ),
		
		//Query to fetch active alignments in given SP
		@NamedQuery(name = "FetchActAlgmntsFromCustIdAndSP", query = "Select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId = ?1 AND myTCustAlgmnt.algmntId =?2" +
				" AND  myTCustAlgmnt.bussUnitId = ?3 AND myTCustAlgmnt.salesTeamId = ?4 AND myTCustAlgmnt.salesPosId = ?5 " +
				"AND myTCustAlgmnt.salesHierId = ?6 AND myTCustAlgmnt.activeFlag = ?7 AND myTCustAlgmnt.effStartDt <= ?8" +
				" AND (myTCustAlgmnt.effEndDt >= ?9 or myTCustAlgmnt.effEndDt IS NULL) and myTCustAlgmnt.tenantId = ?10 "),	
		//Query to fetch active alignments in given ST
		@NamedQuery(name = "FetchActAlgmntsFromCustIdAndST", query = "Select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId = ?1 AND myTCustAlgmnt.algmntId =?2" +
				" AND  myTCustAlgmnt.bussUnitId = ?3 AND myTCustAlgmnt.salesTeamId = ?4 " +
				"AND myTCustAlgmnt.activeFlag = ?5 AND myTCustAlgmnt.effStartDt <= ?6" +
				" AND (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS NULL) and myTCustAlgmnt.tenantId = ?8 "),
				
				//Query to fetch Customers count based on search
				@NamedQuery(name = "FindCountofTCustAlgmntsForCustomerSearch", query = "select Count(c) from TCustAlgmnt c, TCust tc where  c.tCust.custId = tc.custId  AND c.algmntId = ?1 AND c.bussUnitId = ?2 AND c.salesTeamId = ?3  AND c.salesPosId = ?4 AND c.salesHierId = ?5 AND  c.tCust.custName like ?6 AND c.tenantId = ?7 AND c.activeFlag = ?8 "),
				
				//Query to fetch active alignments in given ST
		@NamedQuery(name = "FetchActAlgmntsFromCustId", query = "Select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId = ?1 " +
				"AND myTCustAlgmnt.activeFlag = ?2 AND myTCustAlgmnt.effStartDt <= ?3" +
				" AND (myTCustAlgmnt.effEndDt >= ?4 or myTCustAlgmnt.effEndDt IS NULL) and myTCustAlgmnt.tenantId = ?5 "),
		
		@NamedQuery(name = "FetchAllAssignmentsForCustomer", query = "Select ta.algmntName,tb.bussUnitName,tst.salesTeamName,tsp.posName,tc.effStartDt,tc.effEndDt,tcust.custName,tc.algmntId,tc.bussUnitId,tc.salesTeamId" +
				" from TCustAlgmnt tc,TAlgmnt ta,TBussUnit tb,TSalesTeam tst,TSalesPos tsp,TCust tcust where" +
				" tc.tCust.custId = ?1 AND tc.activeFlag = ?2" + 
				" AND (tc.effEndDt >= ?3 or tc.effEndDt IS NULL)" +
				" AND tc.algmntId = ta.algmntId AND tc.bussUnitId = ta.tBussUnit.bussUnitId AND tc.tenantId = ta.tenantId" + 
				" AND tc.bussUnitId = tb.bussUnitId AND tc.tenantId = tb.tenantId" +
				" AND tc.bussUnitId = tst.tSalesTeamId.bussUnitId AND tc.salesTeamId =tst.tSalesTeamId.salesTeamId  AND tc.tenantId=tst.tenantId" +
				" AND tc.algmntId=tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId AND tc.bussUnitId=tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId AND tc.salesTeamId=tsp.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId" +
				" AND tc.salesHierId=tsp.tAlgmntSalesHier.salesHierId AND tc.salesPosId=tsp.salesPosId AND tc.tenantId=tsp.tenantId" +
				" AND tc.tCust.custId=tCust.custId AND tc.tenantId=tCust.tenantId"),
		 
		@NamedQuery(name = "fetchCountOfAllAssignedActPropCust", query = "select myTCustAlgmnt.custAlgmntId FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 "
				+ "and myTCustAlgmnt.salesHierId = ?4 and  myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 "
				+ "and (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag =?8) "
				+ "and myTCustType.tCustTypeId.localeId = ?9 "
				+ "and myTCustAddr.prAddrFlag = ?10 and myTCustAddr.activeFlag = ?11 "
				+ "and myTCustCategory.tCustCategoryId.localeId = ?12 "),
		@NamedQuery(name = "assignedToSpCheckFrGIS", query = "select myTCustAlgmnt.custAlgmntId FROM TCustAlgmnt myTCustAlgmnt "
						+ "WHERE myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 "
						+ "and myTCustAlgmnt.salesHierId = ?4 and  myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 "
						+ "and (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag =?8 and myTCustAlgmnt.tCust.custId = ?9 "),
						
		@NamedQuery(name = "fetchAssignedActPropCustFrSpList", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.typeName,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
				+ "myTCustAddr.streetName, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postalCd, myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag "
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 and "
				+ "myTCustAlgmnt.salesPosId in ?4 and myTCustAlgmnt.tenantId = ?5 and "
				+ "(myTCustAlgmnt.effEndDt >= ?6 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag =?7) and "
				+ "myTCustType.tCustTypeId.localeId = ?8 and "
				+ "myTCustAddr.prAddrFlag = ?9 and myTCustAddr.activeFlag = ?10 and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?11"),
		@NamedQuery(name = "fetchCustomersFrUnassign", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt "
						+ "WHERE myTCustAlgmnt.custAlgmntId in ?1 AND myTCustAlgmnt.effEndDt <=?2"),	
				
		@NamedQuery(name = "fetchCustAssFrGIS", query = "select  tsp.posName, tsp.posCd,tsp.salesPosId,tsp.tAlgmntSalesHier.salesHierId" +
	                                                       " from  TCust mycust,TCustAlgmnt myTcustAlg, TSalesPos tsp"+
	                                                       " where mycust.custId= ?1"+
	                                                       " and   mycust.activeFlag='Y'"+
	                                                       " and myTcustAlg.activeFlag = 'Y'" +
	                                                       " and myTcustAlg.effStartDt <= ?2 and (myTcustAlg.effEndDt >= ?2 or myTcustAlg.effEndDt IS null)" + 
	                                                       " and myTcustAlg.tenantId = ?3" +
	                                                       " and myTcustAlg.tCust.custId = mycust.custId" +
	                                                       " and myTcustAlg.tenantId = mycust.tenantId" +
	                                                       " and myTcustAlg.salesPosId=tsp.salesPosId" +
	                                                       " and myTcustAlg.salesHierId=tsp.tAlgmntSalesHier.salesHierId" +
	                                                       " and myTcustAlg.tenantId=tsp.tenantId"),
	 @NamedQuery(name = "fetchAssignedActPropCustFrGIS", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.typeName,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
	                                               				+ "myTCustAddr.streetName, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postalCd, myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag "
	                                               				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
	                                               				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
	                                               				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
	                                               				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
	                                               				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
	                                               				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
	                                               				+ "and myCust.tenantId = myTCustType.tenantId "
	                                               				+ "and myCust.custId = myTCustAddr.custId "
	                                               				+ "and myCust.tenantId = myTCustAddr.tenantId "
	                                               				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 and "
	                                               				+ "myTCustAlgmnt.salesHierId in ?4 and  myTCustAlgmnt.salesPosId in ?5 and myTCustAlgmnt.tenantId = ?6 and "
	                                               				+ "(myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag =?8) and "
	                                               				+ "myTCustType.tCustTypeId.localeId = ?9 and "
	                                               				+ "myTCustAddr.prAddrFlag = ?10 and myTCustAddr.activeFlag = ?11 and "
	                                               				+ "myTCustCategory.tCustCategoryId.localeId = ?12"),              
		@NamedQuery(name = "FetchTCustAlgmntsByJoinQuery", query = "select myTCustAlgmnt, tpos.effEndDt from TCustAlgmnt myTCustAlgmnt, TSalesPos tpos WHERE myTCustAlgmnt.salesPosId=tpos.salesPosId and myTCustAlgmnt.salesHierId=tpos.tAlgmntSalesHier.salesHierId AND myTCustAlgmnt.activeFlag='Y' AND myTCustAlgmnt.tenantId=?1 AND (myTCustAlgmnt.effEndDt<=?2 OR tpos.effEndDt<=?3 OR tpos.activeFlag='N')" ),
		@NamedQuery(name = "fetchTCustAlgmnt", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tCust.custId =?6 and myTCustAlgmnt.tenantId = ?7 AND myTCustAlgmnt.activeFlag='Y' "),
		// QUERY TO FETCH ACTIVE CUSTOMERS FOR THE SP
		@NamedQuery(name = "FindTCustAlgmntActiveSP", query = "select myTCustAlgmnt.tCust.custId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt from TCustAlgmnt myTCustAlgmnt" +
				" where myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3" +
				" and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tCust.custId in (?6)" +
				" and myTCustAlgmnt.tenantId = ?7 AND myTCustAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "getAllAssignmentsForCustIdList", query = "Select tc.tCust.custId, tc.custAlgmntId from TCustAlgmnt tc where tc.tCust.custId IN ?1 AND tc.activeFlag = ?2 AND (tc.effEndDt >= ?3 or tc.effEndDt IS NULL)"),
		@NamedQuery(name = "fetchAssignedActPropCustFrGrid", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.typeName,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
				+ "myTCustAddr.streetName, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postalCd, myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag, myTCustAlgmnt.prtTypeId "
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3 and "
				+ "myTCustAlgmnt.salesHierId = ?4 and  myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and "
				+ "(myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag =?8) and "
				+ "myTCustType.tCustTypeId.localeId = ?9 and "
				+ "myTCustAddr.prAddrFlag = ?10 and myTCustAddr.activeFlag = ?11 and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?12 ORDER BY myTCustAddr.postalCd"),
		//Query to fetch customer assignments
		@NamedQuery(name = "FetchActAlgmntsFromCustIds", query = "Select myTCustAlgmnt.custAlgmntId, myTCustAlgmnt.tCust.custId, " +
				" myTCustAlgmnt.salesPosId , myTCustAlgmnt.salesHierId , myTCustAlgmnt.effStartDt, myTCustAlgmnt.effEndDt " +
				" from TCustAlgmnt myTCustAlgmnt where " +
				" myTCustAlgmnt.tCust.custId IN ?1 AND myTCustAlgmnt.algmntId = ?2 " +
				" AND myTCustAlgmnt.bussUnitId = ?3 AND myTCustAlgmnt.salesTeamId = ?4 " +
				" AND myTCustAlgmnt.activeFlag = ?5 AND myTCustAlgmnt.tenantId = ?6 "),
		@NamedQuery(name = "FindTCustAlgmntsForUnAssignSPCount", query = "Select count(c) from TCustAlgmnt c where c.salesPosId =?1 AND c.salesHierId = ?2 AND c.algmntId = ?3 AND c.bussUnitId = ?4 AND c.salesTeamId = ?5 AND c.activeFlag = ?6 AND c.tenantId = ?7 "),
		@NamedQuery(name = "GetAssignCust", query = "select myTCustAlgmnt.tCust.custId , myTCustAlgmnt.effStartDt , myTCustAlgmnt.effEndDt ,myTCustAlgmnt.implFlag FROM TCustAlgmnt myTCustAlgmnt" +
				" WHERE myTCustAlgmnt.tCust.custId IN ?1 AND " +
				"myTCustAlgmnt.algmntId = ?2  AND " +
				"myTCustAlgmnt.bussUnitId = ?3 AND " +
				"myTCustAlgmnt.salesTeamId = ?4 AND "+ 
				"myTCustAlgmnt.salesPosId = ?5 AND " +
				"myTCustAlgmnt.tenantId = ?6 AND "+
				"myTCustAlgmnt.activeFlag = 'Y'"),
	@NamedQuery(name = "FindAllTCustAlgmntsByIDS", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt where " +
						"myTCustAlgmnt.custAlgmntId = ?1  and myTCustAlgmnt.tenantId = ?2 and myTCustAlgmnt.activeFlag = 'Y'"),
	@NamedQuery(name = "GetAssignSPbyCustId", query = "select myTCustAlgmnt FROM TCustAlgmnt myTCustAlgmnt" +
								" WHERE myTCustAlgmnt.tCust.custId = ?1 AND " +
								"myTCustAlgmnt.tenantId = ?2 AND "+
								"myTCustAlgmnt.activeFlag = 'Y'"),
	
	// newly added for secondary address validation			
			@NamedQuery(name = "fetchAssignedActPropCustWithNoAddr", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.tCustTypeId.custTypeId,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
			+ "myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag, myTCustAlgmnt.prtTypeId, myTCustAlgmnt.implFlag, myTCustAlgmnt.affFlag,myCust.categoryId,myCust.custTypeId,myCust.stateLicId,myCust.deaId, "
			+ "myCust.comments,myCust.namePfx,myCust.nameSfx,myTCustAlgmnt.statusId "
			+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType "
			+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
			+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
			+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
			+ "and myCust.tenantId  = myTCustCategory.tenantId " 
			+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
			+ "and myCust.tenantId = myTCustType.tenantId "
			/*+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3  "*/
			+ "and myTCustAlgmnt.salesHierId = ?1 and  myTCustAlgmnt.salesPosId = ?2 and myTCustAlgmnt.tenantId = ?3 and "
			+ "(myTCustAlgmnt.effEndDt >= ?4 or myTCustAlgmnt.effEndDt IS null) AND myTCustAlgmnt.activeFlag = 'Y' "
			+ " and myTCustType.tCustTypeId.localeId = ?5 and "				
			+ "myTCustCategory.tCustCategoryId.localeId = ?5  ") ,
			
			
	// newly added for secondary address validation		
	@NamedQuery(name = "fetchCountOfAllAssignedActCustNoAddr", query = "select count(obj.custAlgmntId) FROM TCustAlgmnt obj, " +
			"TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType "
			+ " WHERE obj.tCust.custId = myCust.custId "
			+ "and obj.tenantId = myCust.tenantId "
			+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
			+ "and myCust.tenantId  = myTCustCategory.tenantId " 
			+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
			+ "and myCust.tenantId = myTCustType.tenantId "
			+ "and obj.algmntId = ?1 and obj.bussUnitId = ?2 and  obj.salesTeamId = ?3 "
			+ "and obj.salesHierId = ?4 and  obj.salesPosId = ?5 and obj.tenantId = ?6 "
			+ "and (obj.effEndDt >= ?7 or obj.effEndDt IS null) and obj.activeFlag ='Y'  "
			+ "and myTCustType.tCustTypeId.localeId = ?8 "
			+ "and myTCustCategory.tCustCategoryId.localeId = ?9"),
			
	@NamedQuery(name = "FetchInactiveCustomerWithPendingCR", query = "select myCust.custName,myCust.custId,myCust.custCd,myTCustType.tCustTypeId.custTypeId,myCust.prtTypeId,myTCustAlgmnt.custAlgmntId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt,myTCustAlgmnt.targetFlag, "
			+ "myTCustType.iconCd, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName, myTCustAlgmnt.activeFlag, myTCustAlgmnt.prtTypeId, myTCustAlgmnt.implFlag, myTCustAlgmnt.affFlag,myCust.categoryId,myCust.custTypeId,myCust.stateLicId,myCust.deaId, "
			+ "myCust.comments,myCust.namePfx,myCust.nameSfx,myTCustAlgmnt.statusId "
			+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAlgmntChngReqDet chng "
			+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
			+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
			+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
			+ "and myCust.tenantId  = myTCustCategory.tenantId " 
			+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
			+ "and myCust.tenantId = myTCustType.tenantId AND chng.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId "
			/*+ "and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and  myTCustAlgmnt.salesTeamId = ?3  "*/
			+ "and myTCustAlgmnt.salesHierId = ?1 and  myTCustAlgmnt.salesPosId = ?2 and myTCustAlgmnt.tenantId = ?3  "
			+ "and myTCustAlgmnt.activeFlag = 'N' AND chng.statusId IN (?4) "
			+ "and myTCustType.tCustTypeId.localeId = ?5 and "				
			+ "myTCustCategory.tCustCategoryId.localeId = ?5 GROUP BY myTCustAlgmnt.custAlgmntId" ) ,
			
			
			
	// newly added for secondary address validation		
	@NamedQuery(name = "FetchCountOfInactiveCustomerWithPendingCR", query = "select count(obj.custAlgmntId) FROM TCustAlgmnt obj, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAlgmntChngReqDet chng "
			+ " WHERE obj.tCust.custId = myCust.custId "
			+ "and obj.tenantId = myCust.tenantId "
			+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
			+ "and myCust.tenantId  = myTCustCategory.tenantId " 
			+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
			+ "and myCust.tenantId = myTCustType.tenantId AND chng.tCustAlgmnt.custAlgmntId = obj.custAlgmntId "
			+ "and obj.algmntId = ?1 and obj.bussUnitId = ?2 and  obj.salesTeamId = ?3 "
			+ "and obj.salesHierId = ?4 and  obj.salesPosId = ?5 and obj.tenantId = ?6 "
			+ "and obj.activeFlag ='N' AND chng.statusId IN (?7)  "
			+ "and myTCustType.tCustTypeId.localeId = ?8 "
			+ "and myTCustCategory.tCustCategoryId.localeId = ?9 GROUP BY obj.custAlgmntId "),
			
	@NamedQuery(name ="getCustomerAlignmentFrCustId", query = "select mytCustAlgmnt from TCustAlgmnt mytCustAlgmnt where mytCustAlgmnt.tCust.custId=?1 and mytCustAlgmnt.tenantId=?2 and (mytCustAlgmnt.effEndDt >= ?3 or mytCustAlgmnt.effEndDt IS null) and mytCustAlgmnt.activeFlag='Y'"),
@NamedQuery(name = "getCustomerAlignmentFrCustIdWithBuId", query = "select myTCustAlgmnt from TCustAlgmnt myTCustAlgmnt,TCust myTCust where myTCustAlgmnt.tCust.custId = myTCust.custId "
				+ " and myTCust.custId = ?1 and mytCustAlgmnt.bussUnitId=?2 and myTCustAlgmnt.tenantId=?3 and (myTCustAlgmnt.effEndDt >= ?4 or myTCustAlgmnt.effEndDt IS NULL) and  myTCustAlgmnt.activeFlag = ?5"),		

		@NamedQuery(name = "getCountGeoAlgdCust", query = "select  mycustAddr.postalCd,count(myTcustAlg.tCust.custId) from TCustAddr mycustAddr,TCustAlgmnt myTcustAlg"
				+ " where myTcustAlg.tCust.custId=mycustAddr.custId"
				+ " and myTcustAlg.tenantId = mycustAddr.tenantId"
				+ " and myTcustAlg.salesPosId = ?1"
				+ " and myTcustAlg.salesHierId = ?2"
				+ " and mycustAddr.postalCd IN (?3)"
				+ " and mycustAddr.tenantId =?4"
				+ " and mycustAddr.activeFlag = 'Y'"
				+ " and myTcustAlg.activeFlag = 'Y'"
				+ " and myTcustAlg.geoAlgmntFlag = 'Y'"),

		@NamedQuery(name = "getCountCompElgCust", query = "select  mycustAddr.postalCd,count(myTcustAlg.tCust.custId) from TCustAddr mycustAddr,TCustAlgmnt myTcustAlg"
				+ " where myTcustAlg.tCust.custId=mycustAddr.custId"
				+ " and myTcustAlg.tenantId = mycustAddr.tenantId"
				+ " and myTcustAlg.salesPosId = ?1"
				+ " and myTcustAlg.salesHierId = ?2"
				+ " and mycustAddr.postalCd IN (?3)"
				+ " and mycustAddr.tenantId =?4"
				+ " and mycustAddr.activeFlag = 'Y'"
				+ " and myTcustAlg.activeFlag = 'Y'"),
				
		@NamedQuery(name = "updateTargetFlagOfCust", query = "Update TCustAlgmnt myTCustAlgmnt set myTCustAlgmnt.targetFlag='Y' ,myTCustAlgmnt.updateDt = ?1 ,myTCustAlgmnt.updatedBy = ?2" +
				" where myTCustAlgmnt.custAlgmntId = ?3 and myTCustAlgmnt.tenantId = ?4"),
		@NamedQuery(name ="findNonGeoCustAlignmentIdBySalesPostion", query = "select mytCustAlgmnt from TCustAlgmnt mytCustAlgmnt, TCustAddr myTCustAddr where mytCustAlgmnt.tCust.custId=myTCustAddr.tCust.custId and myTCustAddr.prAddrFlag='Y' and mytCustAlgmnt.salesPosId = ?1 and mytCustAlgmnt.geoAlgmntFlag = 'Y' and mytCustAlgmnt.activeFlag='Y' and myTCustAddr.activeFlag='Y' and mytCustAlgmnt.tenantId = myTCustAddr.tenantId and myTCustAddr.postalCd not in (select myTTerrZipAlgmnt.postalCd from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?1 and myTTerrZipAlgmnt.activeFlag='Y' and myTTerrZipAlgmnt.tenantId = ?2)"),
		@NamedQuery(name ="findGeoCustAlignmentIdBySalesPostion", query = "select mytCustAlgmnt from TCustAlgmnt mytCustAlgmnt, TCustAddr myTCustAddr where mytCustAlgmnt.tCust.custId=myTCustAddr.tCust.custId and myTCustAddr.prAddrFlag='Y' and mytCustAlgmnt.salesPosId = ?1 and mytCustAlgmnt.geoAlgmntFlag = 'N' and mytCustAlgmnt.activeFlag='Y' and myTCustAddr.activeFlag='Y' and mytCustAlgmnt.tenantId = myTCustAddr.tenantId and myTCustAddr.postalCd in (select myTTerrZipAlgmnt.postalCd from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?1 and myTTerrZipAlgmnt.activeFlag='Y' and myTTerrZipAlgmnt.tenantId = ?2)"),
		@NamedQuery(name = "updateEffEndDateFrCust", query = "Update TCustAlgmnt myTCustAlgmnt set myTCustAlgmnt.effEndDt= ?1, myTCustAlgmnt.activeFlag = ?2, myTCustAlgmnt.updateDt = ?3, myTCustAlgmnt.updatedBy = ?5" +
				" where myTCustAlgmnt.custAlgmntId = ?4 and myTCustAlgmnt.tenantId = ?5"),
		@NamedQuery(name = "getCountGeoAndNonGeoAlgdCustFrSp", query = "select  Count(DISTINCT myTcustAlg.tCust.custId) from TCustAlgmnt myTcustAlg"
				+ " where myTcustAlg.salesPosId = ?1"
				+ " and myTcustAlg.salesHierId = ?2"
				+ " and myTcustAlg.tenantId =?3"
				+ "	and (myTcustAlg.effEndDt > ?4 or myTcustAlg.effEndDt IS NULL)"
				+ " and myTcustAlg.activeFlag = 'Y'"
				+ " and myTcustAlg.geoAlgmntFlag = ?5" 
				+ " and myTcustAlg.targetFlag = 'Y'"),
		@NamedQuery(name = "fetchCustAlgmntIdfromCustIdAndSPId", query = "Select c.custAlgmntId from TCustAlgmnt c where c.tCust.custId = ?1 AND c.salesPosId =?2"),
		@NamedQuery(name = "countTCustAlgmntStatus", query = "Select count(myTCustAlgmnt) from TCustAlgmnt myTCustAlgmnt where myTCustAlgmnt.tCust.custId = ?1 AND myTCustAlgmnt.algmntId = ?2 AND" +
				" myTCustAlgmnt.bussUnitId = ?3 ANd myTCustAlgmnt.salesTeamId = ?4 AND (myTCustAlgmnt.effEndDt > ?5 or myTCustAlgmnt.effEndDt IS NULL) AND  myTCustAlgmnt.tenantId =?6 AND myTCustAlgmnt.statusId IN (1,2)" +
				" AND myTCustAlgmnt.activeFlag = 'Y' "),
				
		//Queries for DashBoard		
		@NamedQuery(name = "FindCountOfTCustAlgmntForSpList", query = "Select Count(myTCustAlgmnt.tCust.custId) " 
				+ "FROM TCustAlgmnt myTCustAlgmnt "
				+ "WHERE myTCustAlgmnt.salesPosId IN ?1 and myTCustAlgmnt.tenantId = ?2 and "
				+ "(myTCustAlgmnt.effEndDt >= ?3 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and (myTCustAlgmnt.effStartDt <= ?3)"),
						
		@NamedQuery(name = "getCountGeoAndNonGeoAlgdCustFrSpList", query = "select  Count(myTcustAlg.tCust.custId) from TCustAlgmnt myTcustAlg"
				+ " where myTcustAlg.salesPosId IN ?1"
				+ " and myTcustAlg.tenantId =?2"
				+ "	and (myTcustAlg.effEndDt > ?3 or myTcustAlg.effEndDt IS NULL)"
				+ " and myTcustAlg.activeFlag = 'Y'"
				+ " and myTcustAlg.geoAlgmntFlag = ?4" 
				+ " and myTcustAlg.targetFlag = 'Y'"
				+ " and (myTcustAlg.effStartDt <= ?3)"),
				
		@NamedQuery(name = "FindTCustAlgmntFrCustIdList", query = "Select c from TCustAlgmnt c where c.algmntId = ?1 AND c.bussUnitId = ?2 " +
						"AND c.salesTeamId = ?3 AND c.salesPosId =?4 AND c.salesHierId = ?5 AND c.activeFlag = 'Y' AND c.tCust.custId IN (?6) AND (c.effEndDt >= ?7 or c.effEndDt IS NULL) AND c.tenantId = ?8 "),
						
						
		@NamedQuery(name = "FindAssignedCustFrSp", query = "Select tCustAlgmnt.tSalesPos from TCustAlgmnt tCustAlgmnt where tCustAlgmnt.tCust.custId = ?1 " +
				"AND tCustAlgmnt.salesPosId in (?2)  AND " +
				"(tCustAlgmnt.effEndDt >= ?3 or tCustAlgmnt.effEndDt IS NULL) AND tCustAlgmnt.tenantId = ?4 " +
				" AND tCustAlgmnt.activeFlag = 'Y'"),
		
		@NamedQuery(name = "updateTCustAlgmntToLock", query = "Update TCustAlgmnt tCustAlgmnt Set tCustAlgmnt.statusId = ?1 , tCustAlgmnt.updatedBy = ?2 ,tCustAlgmnt.updateDt = ?3 " +
				"Where tCustAlgmnt.custAlgmntId = ?4 and tCustAlgmnt.activeFlag = 'Y' and tCustAlgmnt.statusId != 2) "),
				
		@NamedQuery(name = "updateTCustAlgmntToUnLock", query = "Update TCustAlgmnt tCustAlgmnt Set tCustAlgmnt.statusId = ?1 , tCustAlgmnt.updatedBy = ?2 ,tCustAlgmnt.updateDt = ?3 " +
				"Where tCustAlgmnt.custAlgmntId IN (?4) and tCustAlgmnt.activeFlag = 'Y' and tCustAlgmnt.statusId != 3")
						
	
				
})
@Table(name = "t_cust_algmnt", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_algmnt_id" }))
public class TCustAlgmnt implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_algmnt_id", nullable = false, length = 255)
	private Long custAlgmntId;

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
	@Column(name = "aff_flag", nullable = true, length = 1)
	private Character affFlag;
	
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TSalesPos tSalesPos;	
	
	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "sales_hier_id", nullable = false, length = 255)
	private Long salesHierId;
	
	@Column(name = "prt_type_id", nullable = true, length = 255)
	private Integer prtTypeId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "target_flag", nullable = true, length = 1)
	private Character targetFlag;

	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "impl_flag", nullable = true, length = 1)
	private Character implFlag;

	
	public Character getTargetFlag() {
		return targetFlag;
	}

	public void setTargetFlag(Character targetFlag) {
		this.targetFlag = targetFlag;
	}

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
	@Column(name = "geo_algmnt_flag", nullable = true, length = 1)
	private Character geoAlgmntFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "comp_algmnt_flag", nullable = true, length = 1)
	private Character compAlgmntFlag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", nullable = false, insertable = true, updatable = true)
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TCust tCust;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCustAlgmnt")
	@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCustAlgmntAttr> tCustAlgmntAttrss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCustAlgmnt")
	@NotAudited
	private Set<TCustPrdAlgmnt> tCustPrdAlgmnts;

	/**
	 * 
	 * @generated
	 */
	/*public TCustAlgmnt() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setCustAlgmntId(final Long custAlgmntId) {
		this.custAlgmntId = custAlgmntId;
	}

	/**
	 * 
	 * @generated
	 */
	public Long getCustAlgmntId() {
		return this.custAlgmntId;
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
	public Character getAffFlag() {
		return this.affFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAffFlag(final Character affFlag) {
		this.affFlag = affFlag;
	}
	
	/**
	 * 
	 * @generated
	 */

	public void setEffStartDt(final Date effStartDt) {
		if (effStartDt == null) {
			Date x=null;
			this.effStartDt =x;
			
		} else {
			this.effStartDt = (Date) effStartDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffStartDt() {
		if (this.effStartDt == null) {
			return null;
		} else {
			
			return (Date) this.effStartDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */

	public void setEffEndDt(final Date effEndDt) {
		if (effEndDt == null) {
			Date x=null;
			this.effEndDt = x;
		} else {
			
			this.effEndDt = (Date) effEndDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getEffEndDt() {
		if (this.effEndDt == null) {
			return null;
		} else {
			
			return (Date) this.effEndDt.clone();
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

	public void setCreateDt(final Date createDt) {
		if (createDt == null) {
			this.createDt = null;
		} else {
			
			this.createDt = (Date) createDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getCreateDt() {
		if (this.createDt == null) {
			return null;
		} else {
			
			return (Date) this.createDt.clone();
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
		if (updateDt == null) {
			Date x=null;
			this.updateDt = x;
			
		} else {
			this.updateDt = (Date) updateDt.clone();
		}
	}

	/**
	 * 
	 * @generated
	 */
	public Date getUpdateDt() {
		if (this.updateDt == null) {
			return null;
		} else {
			
			return (Date) this.updateDt.clone();
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
	
	public Integer getPrtTypeId() {
		return prtTypeId;
	}
	public void setPrtTypeId(Integer prtTypeId) {
		this.prtTypeId = prtTypeId;
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
	public TCust getTCust() {
		return this.tCust;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCust(final TCust tCust) {
		this.tCust = tCust;

	}


	/**
	 * 
	 * @generated
	 */

	public void setCompAlgmntFlag(final Character compAlgmntFlag) {
		this.compAlgmntFlag = compAlgmntFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getCompAlgmntFlag() {
		return this.compAlgmntFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAlgmntAttr> getTCustAlgmntAttrss() {
		return this.tCustAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntAttrss(
			final Set<TCustAlgmntAttr> tCustAlgmntAttrss) {
		this.tCustAlgmntAttrss = tCustAlgmntAttrss;
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoAlgmntFlag(final Character geoAlgmntFlag) {
		this.geoAlgmntFlag = geoAlgmntFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getGeoAlgmntFlag() {
		return this.geoAlgmntFlag;
	}
	
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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
	public void copy(final TCustAlgmnt that) {
		setCustAlgmntId(that.getCustAlgmntId());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		setAffFlag(that.getAffFlag());
		setGeoAlgmntFlag(that.getGeoAlgmntFlag());
		setStatusId(that.getStatusId());
		setCompAlgmntFlag(that.getCompAlgmntFlag());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result
				+ ((custAlgmntId == null) ? 0 : custAlgmntId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custAlgmntId=[").append(custAlgmntId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
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
		buffer.append("affFlag=[").append(affFlag).append("] ");
		buffer.append("geoAlgmntFlag=[").append(geoAlgmntFlag).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
		buffer.append("compAlgmntFlag=[").append(compAlgmntFlag).append("] ");
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
		final TCustAlgmnt other = (TCustAlgmnt) obj;
		if (custAlgmntId == null) {
			if (other.custAlgmntId != null) {
				return false;
			}
		} else if (!custAlgmntId.equals(other.custAlgmntId)) {
			return false;
		}
		return true;
	}

/**
	 * @return the implFlag
	 */
	public Character getImplFlag() {
		return implFlag;
	}

	/**
	 * @param implFlag the implFlag to set
	 */
	public void setImplFlag(Character implFlag) {
		this.implFlag = implFlag;
	}

	public TSalesPos gettSalesPos() {
		return tSalesPos;
	}

	public void settSalesPos(TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
	}
	
	
}
