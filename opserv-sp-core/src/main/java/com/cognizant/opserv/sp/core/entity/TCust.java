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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * JPA class representing TCust The corresponding mapping table is t_cust
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTCusts", query = "select myTCust from TCust myTCust"),
		@NamedQuery(name = "FindAllTCustsFromCity", query = "select distinct myTCust from TCust myTCust,TCustAddr myTCustAddr,TAddrType myTAddrType "
				+ " where myTCust.custId = myTCustAddr.custId and myTAddrType.tAddrTypeId.addrTypeId = myTCustAddr.addrTypeId and myTCustAddr.city = ?1 and myTAddrType.tAddrTypeId.addrTypeId = ?2 and myTCustAddr.activeFlag = ?3 "),
		@NamedQuery(name = "FindAllTCustsFromPostalCd", query = "select distinct myTCust from TCust myTCust,TCustAddr myTCustAddr,TAddrType myTAddrType  "
				+ " where myTCust.custId = myTCustAddr.custId and myTAddrType.tAddrTypeId.addrTypeId = myTCustAddr.addrTypeId  and myTCustAddr.postalCd = ?1 and myTAddrType.tAddrTypeId.addrTypeId = ?2 and myTCustAddr.activeFlag = ?3 "),
		@NamedQuery(name = "FindAllTCustAddrsFromCid", query = "select myTCustAddr from TCust myTCust,TCustAddr myTCustAddr,TAddrType myTAddrType where "
				+ " myTCustAddr.tCust.custId = myTCust.custId and myTAddrType.tAddrTypeId.addrTypeId = myTCustAddr.addrTypeId and myTCust.custId = ?1 and myTAddrType.tAddrTypeId.addrTypeId = ?2 and myTCustAddr.activeFlag = ?3 "),
		@NamedQuery(name = "FindAllTCusttypesFromCid", query = "select myTCustType from TCust myTCust,TCustType myTCustType "
				+ " where myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and  myTCust.custId = ?1 "),
				@NamedQuery(name = "FindAllTCusttypesFromCidFrGIS", query = "select myTCustType from TCust myTCust,TCustType myTCustType "
						+ " where myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and  myTCust.custId = ?1 and myTCustType.tCustTypeId.localeId = ?3 and myTCustType.tenantId = ?2"),
		@NamedQuery(name = "FindAllTPrtTypesFromCid", query = "select myTPrtType from TCust myTCust,TPrtType myTPrtType "
				+ " where myTCust.prtTypeId = myTPrtType.tPrtTypeId.prtTypeId and myTCust.custId = ?1 "),
		@NamedQuery(name = "FindAllCustFromPrioritydesc", query = "select myTCust from TCust myTCust,TPrtType myTPrtType "
				+ " where myTCust.prtTypeId = myTPrtType.tPrtTypeId.prtTypeId and myTPrtType.prtTypeDesc = ?1 "),
		@NamedQuery(name = "FindAllTCustFromType", query = "select myTCust from TCust myTCust,TCustType myTCustType "
				+ " where myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and  myTCustType.typeName = ?1 "),
		@NamedQuery(name = "CountTCusts", query = "Select Count(c) from TCust c"),
		@NamedQuery(name = "FindTCustByTCustType", query = "select myTCust from TCust myTCust where myTCust.custTypeId = ?1 "),
		@NamedQuery(name = "CountTCustsByTCustType", query = "select Count(myTCust) from TCust myTCust where myTCust.custTypeId = ?1 "),
		@NamedQuery(name = "FindTCustByTPrtType", query = "select myTCust from TCust myTCust where myTCust.prtTypeId = ?1 "),
		@NamedQuery(name = "CountTCustsByTPrtType", query = "select Count(myTCust) from TCust myTCust where myTCust.prtTypeId = ?1 "),
		@NamedQuery(name = "FindTCustByTCustCategory", query = "select myTCust from TCust myTCust where myTCust.categoryId = ?1 "),
		@NamedQuery(name = "CountTCustsByTCustCategory", query = "select Count(myTCust) from TCust myTCust where myTCust.categoryId = ?1 "),
		@NamedQuery(name = "FindCustomersByZip", query = "select myTCust.custId,myTCust.custName,myTCustAddr.postalCd from TCust myTCust,TCustAddr myTCustAddr  where myTCust.custId = myTCustAddr.custId and myTCustAddr.prAddrFlag ='Y' and myTCustAddr.postalCd= ?1"),
		@NamedQuery(name = "findCustomers", query = "select myTCust.custId,myTCust.custCd,myTCustCategory.categoryName,myTCustType.typeName,myTCust.custName,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTSalesPos.tAlgmntSalesHier.salesHierId,myTSalesPos.salesPosId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt "
				+ "from TCust myTCust,TCustType myTCustType,TCustCategory myTCustCategory,TSalesPos myTSalesPos,TCustAlgmnt myTCustAlgmnt where myTCust.tenantId = myTCustType.tenantId and myTCust.tenantId = myTCustCategory.tenantId and myTCust.tenantId = myTCustAlgmnt.tenantId and myTCust.tenantId = myTSalesPos.tenantId and myTCust.custTypeId=myTCustType.tCustTypeId.custTypeId and myTCust.categoryId=myTCustCategory.tCustCategoryId.custCategoryId and myTCustAlgmnt.tCust.custId=myTCust.custId and myTCustAlgmnt.salesPosId = myTSalesPos.salesPosId and myTCustAlgmnt.salesHierId = myTSalesPos.tAlgmntSalesHier.salesHierId and myTCustAlgmnt.activeFlag = 'Y' and myTCustCategory.tCustCategoryId.custCategoryId = ?1 and myTCustType.tCustTypeId.custTypeId = ?2 and myTCust.custCd like ?3 and myTCust.custName like ?4 and myTSalesPos.salesPosId = ?5 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?6 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?7 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?8 and myTSalesPos.tAlgmntSalesHier.salesHierId =?9 and myTCust.activeFlag = ?10 and myTCust.tenantId = ?11"),
        @NamedQuery(name = "FindCustomerToSP", query = "Select tc from TCustAlgmnt c, TCust tc where tc.custId = c.tCust.custId and c.tenantId = ?1 AND c.algmntId = ?2 AND c.bussUnitId = ?3 AND c.salesTeamId = ?4 AND c.activeFlag = ?5 AND c.salesPosId = ?6 AND c.salesHierId = ?7 AND tc.custName like ?8 "),
		/*@NamedQuery(name = "FindRecentlyAddedCustomerToSP", query = "Select myCust.custId, myCust.custCd,myCust.custName, myTCustAlgmnt.custAlgmntId, myTCustAlgmnt.effStartDt, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName " 
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				//+ "and myCust.custId = myTCustAddr.custId "
				//+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?2 and myTCustAlgmnt.bussUnitId = ?3 and  myTCustAlgmnt.salesTeamId = ?4 and "
				+ "myTCustAlgmnt.salesPosId =?5 and myTCustAlgmnt.salesHierId = ?6 and myTCustAlgmnt.tenantId = ?1 and "
				+ "(myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag = 'Y' and "
				+ "myTCustType.tCustTypeId.localeId = ?8 and "
				//+ "myTCustAddr.prAddrFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?8 ORDER By myTCustAlgmnt.effStartDt DESC"),*/
        @NamedQuery(name = "FindRecentlyAddedCustomerToSP", query = "Select myCust.custId, myCust.custCd,myCust.custName, myTCustAlgmnt.custAlgmntId, myTCustAlgmnt.effStartDt, myCust.custFirstName, myCust.custMiddleName, myCust.custLastName " 
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myTCustAlgmnt.algmntId = ?2 and myTCustAlgmnt.bussUnitId = ?3 and  myTCustAlgmnt.salesTeamId = ?4  "
				+ "and myTCustAlgmnt.salesPosId =?5 and myTCustAlgmnt.salesHierId = ?6 and myTCustAlgmnt.tenantId = ?1  "
				+ "and (myTCustAlgmnt.effEndDt >= ?7 or myTCustAlgmnt.effEndDt IS null) and myTCustAlgmnt.activeFlag = 'Y'  "
				+ " ORDER By myTCustAlgmnt.createDt DESC,myTCustAlgmnt.effStartDt DESC"),
        
		
		/*@NamedQuery(name = "FindRecentlyAddedCustomerToSP", query = "Select myCust " 
				+ "FROM TCustAlgmnt myTCustAlgmnt, TCust myCust, TCustCategory myTCustCategory,TCustType myTCustType,TCustAddr myTCustAddr,TCustAlgmntChngReqDet myTCustAlgmntChngReqDet "
				+ "WHERE myTCustAlgmnt.tCust.custId = myCust.custId "
				+ "and myTCustAlgmnt.tenantId = myCust.tenantId "
				+ "and myCust.categoryId = myTCustCategory.tCustCategoryId.custCategoryId "
				+ "and myCust.tenantId  = myTCustCategory.tenantId " 
				+ "and myCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				+ "and myCust.tenantId = myTCustType.tenantId "
				+ "and myCust.custId = myTCustAddr.custId "
				+ "and myCust.tenantId = myTCustAddr.tenantId "
				+ "and myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId = myTCustAlgmnt.custAlgmntId "
				+ "and myTCustAlgmnt.algmntId = ?3 and myTCustAlgmnt.bussUnitId = ?4 and  myTCustAlgmnt.salesTeamId = ?5 and "
				+ "myTCustAlgmnt.salesPosId in (?2) and myTCustAlgmnt.tenantId = ?1 and "
				+ "(myTCustAlgmnt.effEndDt >= ?6 or myTCustAlgmnt.effEndDt IS null) and (myTCustAlgmnt.activeFlag ='Y' or myTCustAlgmntChngReqDet.statusId in (?8)) and "
				+ "myTCustType.tCustTypeId.localeId = ?7 and "
				+ "myTCustAddr.prAddrFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and "
				+ "myTCustCategory.tCustCategoryId.localeId = ?7 GROUP BY myTCustAlgmntChngReqDet.tCustAlgmnt.custAlgmntId ORDER By myTCustAlgmnt.effStartDt DESC"),*/	
				
		@NamedQuery(name = "getTargetedCustomersForAff", query = "select myTCust.custId,myTCust.custName,myTCustType.typeName,myTCustAlgmnt.prtTypeId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt, "
														 + " myTCustAddr.streetName,myTCustAddr.city,myTCustAddr.state,myTCustAddr.cntry,myTCustAddr.postalCd,myTCustAlgmnt.custAlgmntId,myTCust.custCd,myTCust.custFirstName, myTCust.custMiddleName, myTCust.custLastName  "
														 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
														 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
														 + " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId"
														 + " and myTCust.activeFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' "	
														 + " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
														 + " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6  and myTCustType.tCustTypeId.localeId = ?7"
		// 												 + " and myTCustAddr.addrTypeId = myTAddrType.addrTypeId and myTAddrType.localeId = myTCustType.localeId and myTCustType.localeId = myTPrtType.localeId and myTCustType.localeId = ?7  "
		),
		@NamedQuery(name = "getTargetedCustomersForNonAff", query = "select myTCust.custId,myTCust.custName,myTCustType.typeName,myTCustAlgmnt.prtTypeId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt, "
														 + " myTCustAddr.streetName,myTCustAddr.city,myTCustAddr.state,myTCustAddr.cntry,myTCustAddr.postalCd,myTCustAlgmnt.custAlgmntId,myTCust.custCd,myTCust.custFirstName, myTCust.custMiddleName, myTCust.custLastName  "
														 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
														 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
														 + " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
														 + " and myTCust.activeFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' "	
														 + " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
														 + " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and myTCustType.tCustTypeId.localeId = ?7"
// 												 + " and myTCustAddr.addrTypeId = myTAddrType.addrTypeId and myTAddrType.localeId = myTCustType.localeId and myTCustType.localeId = myTPrtType.localeId and myTCustType.localeId = ?7  "
				),
				@NamedQuery(name = "getTargetedCustomersForNonAffFromCustEdit", query = "select myTCust.custId,myTCust.custName,myTCustType.typeName,myTCust.prtTypeId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt, "
						 + " myTCustAddr.streetName,myTCustAddr.city,myTCustAddr.state,myTCustAddr.cntry,myTCustAddr.postalCd,myTCustAlgmnt.custAlgmntId,myTCust.custCd,myTCust.custFirstName, myTCust.custMiddleName, myTCust.custLastName  "
						 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
						 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
						 + " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
						 + " and myTCust.activeFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' and myTCustAddr.activeFlag = 'Y' "	
						 + " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
						 + " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6  and myTCustType.tCustTypeId.localeId = ?7"
//												 + " and myTCustAddr.addrTypeId = myTAddrType.addrTypeId and myTAddrType.localeId = myTCustType.localeId and myTCustType.localeId = myTPrtType.localeId and myTCustType.localeId = ?7  "
				),
		@NamedQuery(name = "findTargetCustomerForRemoval", query = "select myTCustAlgmnt from TCust myTCust,TCustAlgmnt myTCustAlgmnt "
				+ " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTcust.tenantId = myTCustAlgmnt.tenantId "
				//+ " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and myTCust.prtTypeId = myTPrtType.prtTypeId "
				+ " and myTCust.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' "
				+ " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
				+ " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and myTCustAlgmnt.custAlgmntId = ?7"),
		@NamedQuery(name = "searchTargetedCustomer", query = "select myTCust.custId,myTCust.custName,myTCustType.typeName,myTPrtType.prtTypeDesc,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt, "
						+ " myTCustAddr.streetName,myTCustAddr.city,myTCustAddr.state,myTCustAddr.cntry,myTCustAddr.postalCd,myTCustAlgmnt.custAlgmntId,myTCust.custCd "
						+ " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TPrtType myTPrtType, TAddrType myTAddrType,TCustAddr myTCustAddr "
						+ " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTcust.tenantId = myTCustAlgmnt.tenantId "
						+ " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId  "
						+ " and myTCust.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' and myTCustAddr.activeFlag = 'Y' "
						+ " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
						+ " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and (myTCust.custName like ?7 or myTCust.custCd like ?8 )"),
		@NamedQuery(name = "FindCustomerByTententID", query = "Select tc from  TCust tc where tc.tenantId=?1 and tc.activeFlag='Y' "),
		@NamedQuery(name = "FindAllTCustsByIDS", query = "select myTCust from TCust myTCust where myTCust.custId IN ?1 and myTCust.activeFlag = 'Y'"),
		@NamedQuery(name = "getTargetedCustomersForNonAffCount", query = "select count(myTCust.custId) "
				 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
				 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
				 + " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId "
				 + " and myTCust.activeFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' "	
				 + " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
				 + " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6 and myTCustType.tCustTypeId.localeId = ?7"),
	   @NamedQuery(name = "getTargetedCustomersForAffCount", query = "select count(myTCust.custId) "
				 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
				 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
				 + " and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId"
				 + " and myTCust.activeFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and myTCustAlgmnt.targetFlag = 'Y' and myTCustAddr.custId = myTCust.custId and myTCustAddr.prAddrFlag = 'Y' "	
				 + " and myTCustAlgmnt.algmntId = ?1 and myTCustAlgmnt.bussUnitId = ?2 and myTCustAlgmnt.salesTeamId = ?3 "
				 + " and myTCustAlgmnt.salesHierId = ?4 and myTCustAlgmnt.salesPosId = ?5 and myTCustAlgmnt.tenantId = ?6  and myTCustType.tCustTypeId.localeId = ?7"),
	  @NamedQuery(name = "getCustomersByCustAlgmntIds", query = "select myTCust.custId,myTCust.custName,myTCustType.typeName,myTCust.prtTypeId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt, "
				 + " myTCustAddr.streetName,myTCustAddr.city,myTCustAddr.state,myTCustAddr.cntry,myTCustAddr.postalCd,myTCustAlgmnt.custAlgmntId,myTCust.custCd,myTCust.custFirstName, myTCust.custMiddleName, myTCust.custLastName  "
				 + " from TCust myTCust,TCustAlgmnt myTCustAlgmnt,TCustType myTCustType,TCustAddr myTCustAddr "
				 + " where myTCust.custId = myTCustAlgmnt.tCust.custId and myTCustAddr.custId = myTCust.custId and myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and  myTCust.activeFlag = myTCustAlgmnt.activeFlag and myTCust.tenantId = myTCustAlgmnt.tenantId "
				 + "  and myTCustAlgmnt.custAlgmntId IN ?1 and myTCust.activeFlag = 'Y' and myTCustAddr.activeFlag = 'Y' and myTCustAddr.prAddrFlag = 'Y' and myTCustAlgmnt.tenantId = ?2  and myTCustType.tCustTypeId.localeId = ?3"),
		@NamedQuery(name = "FindAllTCusttypesForAllCid", query = "select distinct myTCustType from TCust myTCust,TCustType myTCustType "
							+ " where myTCust.custTypeId = myTCustType.tCustTypeId.custTypeId and  myTCust.custId IN (?1) " +
							" and myTCust.tenantId = myTCustType.tenantId and myTCust.tenantId = ?2"),
		@NamedQuery(name = "FindAllTPrtTypesForAllCid", query = "select distinct myTPrtType from TCust myTCust,TPrtType myTPrtType "
									+ " where myTCust.prtTypeId = myTPrtType.tPrtTypeId.prtTypeId and myTCust.custId IN (?1)  " +
									" and myTCust.tenantId = myTPrtType.tenantId and myTCust.tenantId = ?2"),
		@NamedQuery(name = "FindAllTPrtTypesFromCidForTier", query = "select myTPrtType from TCustAlgmnt myTCust,TPrtType myTPrtType "
											+ " where myTCust.prtTypeId = myTPrtType.tPrtTypeId.prtTypeId and myTCust.custAlgmntId = ?1 "),
		@NamedQuery(name = "GetCustomerByCustCode", query = "Select tc from  TCust tc where tc.tenantId=?1 and tc.custCd=?2 and tc.activeFlag='Y' "),
		@NamedQuery(name = "GetCustData", query = "select c.custId, c.custName, c.custCd, c.custFirstName, c.custMiddleName, " +
				"c.custLastName,  c.stateLicId, c.deaId, c.comments, c.custTypeId," +
				" c.prtTypeId, c.categoryId , ct.typeName, ct.colorCd " +
				" from TCust c ,TCustType ct WHERE ct.tCustTypeId.custTypeId  = c.custTypeId AND ct.tCustTypeId.localeId = ?1 " +
				" AND c.tenantId = ct.tenantId AND c.custId = ?2 AND c.tenantId = ?3 "),
		@NamedQuery(name = "GetCustBasicData", query = "select c.custId, c.custCd, c.custName,  c.custFirstName, c.custMiddleName, " +
				" c.custLastName, c.custTypeId, ct.typeName, ct.colorCd " +
				" from TCust c , TCustType ct WHERE c.custTypeId = ct.tCustTypeId.custTypeId AND ct.tCustTypeId.localeId = ?1 " +
				" AND c.tenantId = ct.tenantId AND c.custId = ?2 AND c.tenantId = ?3 ")	,
		@NamedQuery(name = "GetCustomersBasicData", query = "select c.custId, c.custCd, c.custName,  c.custFirstName, c.custMiddleName, " +
				" c.custLastName, c.custTypeId, ct.typeName, ct.colorCd " +
				" from TCust c , TCustType ct WHERE c.custTypeId = ct.tCustTypeId.custTypeId AND ct.tCustTypeId.localeId = ?1 " +
				" AND c.tenantId = ct.tenantId AND c.custId IN ?2 AND c.tenantId = ?3 "),
		@NamedQuery(name = "FindNamesOfAllTCustsByIDS", query = "select myTCust.custId, myTCust.custCd, myTCust.custName," +
				" myTCust.custFirstName, myTCust.custMiddleName,myTCust.custLastName,myTCust.categoryId," +
				"myTCust.custTypeId,myTCust.prtTypeId from TCust myTCust where " +
				"myTCust.custId IN ?1  and myTCust.tenantId = ?2 and myTCust.activeFlag = 'Y'"),
		@NamedQuery(name = "GetCustomerByCustCodeCount", query = "Select Count(tc) from  TCust tc where tc.tenantId=?1 and tc.custCd=?2 and tc.activeFlag='Y' "),
		@NamedQuery(name = "fetchCustNameFrCustIds" , query = "SELECT mytCust.custName from TCust mytCust where mytCust.custId IN (?1) and mytCust.tenantId = ?2 ")
				
	
})
		
@Table(name = "t_cust", uniqueConstraints = @UniqueConstraint(columnNames = { "cust_id" }))
public class TCust implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cust_id", nullable = false, length = 255)
	private Integer custId;

	@Column(name = "category_id", nullable = false, length = 255)
	private Integer categoryId;

	@Column(name = "cust_type_id", nullable = false, length = 255)
	private Integer custTypeId;

	@Column(name = "prt_type_id", nullable = false, length = 255)
	private Integer prtTypeId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "cust_name", nullable = true, length = 200)
	private String custName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "cust_first_name", nullable = true, length = 75)
	private String custFirstName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "cust_middle_name", nullable = true, length = 75)
	private String custMiddleName;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 75)
	@Column(name = "cust_last_name", nullable = true, length = 75)
	private String custLastName;

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
	@Length(max = 50)
	@Column(name = "state_lic_id", nullable = true, length = 50)
	private String stateLicId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "dea_id", nullable = true, length = 50)
	private String deaId;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 300)
	@Column(name = "comments", nullable = true, length = 300)
	private String comments;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "created_by", nullable = false, length = 255, updatable = false)
	private Integer createdBy;

	/**
	 * 
	 * @generated
	 */
	@NotNull
	@Column(name = "create_dt", nullable = false, length = 19, updatable = false)
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
	@NotEmpty
	@Length(max = 20)
	@Column(name = "cust_cd", nullable = false, length = 20)
	private String custCd;
	
	/**
	 * 
	 * @generated
	 */
	@Length(max = 25)
	@Column(name = "name_pfx", nullable = true, length = 25)
	private String namePfx;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 25)
	@Column(name = "name_sfx", nullable = true, length = 25)
	private String nameSfx;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_1", nullable = true, length = 1000)
	private String attr1;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_2", nullable = true, length = 1000)
	private String attr2;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_3", nullable = true, length = 1000)
	private String attr3;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_4", nullable = true, length = 1000)
	private String attr4;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_5", nullable = true, length = 1000)
	private String attr5;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_6", nullable = true, length = 1000)
	private String attr6;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_7", nullable = true, length = 1000)
	private String attr7;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_8", nullable = true, length = 1000)
	private String attr8;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_9", nullable = true, length = 1000)
	private String attr9;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_10", nullable = true, length = 1000)
	private String attr10;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_11", nullable = true, length = 1000)
	private String attr11;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 1000)
	@Column(name = "attr_12", nullable = true, length = 1000)
	private String attr12;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_13", nullable = true, length = 500)
	private String attr13;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_14", nullable = true, length = 500)
	private String attr14;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_15", nullable = true, length = 500)
	private String attr15;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_16", nullable = true, length = 500)
	private String attr16;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_17", nullable = true, length = 500)
	private String attr17;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_18", nullable = true, length = 500)
	private String attr18;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_19", nullable = true, length = 500)
	private String attr19;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 500)
	@Column(name = "attr_20", nullable = true, length = 500)
	private String attr20;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_21", nullable = true, length = 200)
	private String attr21;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_22", nullable = true, length = 200)
	private String attr22;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_23", nullable = true, length = 200)
	private String attr23;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_24", nullable = true, length = 200)
	private String attr24;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_25", nullable = true, length = 200)
	private String attr25;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_26", nullable = true, length = 200)
	private String attr26;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_27", nullable = true, length = 200)
	private String attr27;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_28", nullable = true, length = 200)
	private String attr28;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_29", nullable = true, length = 200)
	private String attr29;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_30", nullable = true, length = 200)
	private String attr30;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_31", nullable = true, length = 200)
	private String attr31;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_32", nullable = true, length = 200)
	private String attr32;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_33", nullable = true, length = 200)
	private String attr33;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_34", nullable = true, length = 200)
	private String attr34;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 200)
	@Column(name = "attr_35", nullable = true, length = 200)
	private String attr35;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_36", nullable = true, length = 100)
	private String attr36;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_37", nullable = true, length = 100)
	private String attr37;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_38", nullable = true, length = 100)
	private String attr38;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_39", nullable = true, length = 100)
	private String attr39;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_40", nullable = true, length = 100)
	private String attr40;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_41", nullable = true, length = 100)
	private String attr41;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_42", nullable = true, length = 100)
	private String attr42;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_43", nullable = true, length = 100)
	private String attr43;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_44", nullable = true, length = 100)
	private String attr44;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 100)
	@Column(name = "attr_45", nullable = true, length = 100)
	private String attr45;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_46", nullable = true, length = 50)
	private String attr46;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_47", nullable = true, length = 50)
	private String attr47;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_48", nullable = true, length = 50)
	private String attr48;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_49", nullable = true, length = 50)
	private String attr49;

	/**
	 * 
	 * @generated
	 */
	@Length(max = 50)
	@Column(name = "attr_50", nullable = true, length = 50)
	private String attr50;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCust", fetch=FetchType.LAZY )
	//@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCustAddr> tCustAddrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCust", fetch=FetchType.LAZY)
	//@Fetch(FetchMode.SUBSELECT)
	@NotAudited
	private Set<TCustAlgmnt> tCustAlgmntss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCust", fetch=FetchType.LAZY)
	@NotAudited
	private Set<TCustContact> tCustContactss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "tCust")
	@NotAudited
	private Set<TCustAttr> tCustAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustByAffCustId")
	@NotAudited
	private Set<TCustAff> tCustAffsForAffCustIds;


	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCustByCustId")
	@NotAudited
	private Set<TCustAff> tCustAffsForCustIds;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tCust")
	@NotAudited
	private Set<TCustPrdSales> tCustPrdss;

	
	@Column(name = "ext_attr", nullable = true, length = 255)
	private byte[] custExtAttrs;

	/**
	 * 
	 * @generated
	 */

	public byte[] getCustExtAttrs() {
		return custExtAttrs;
	}

	public void setCustExtAttrs(byte[] custExtAttrs) {
		this.custExtAttrs = custExtAttrs;
	}

	/**
	 * 
	 * @generated
	 */
	/*public TCust() {
	}*/

	/**
	 * 
	 * @generated
	 */

	public void setCustId(final Integer custId) {
		this.custId = custId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCustId() {
		return this.custId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustName(final String custName) {
		this.custName = custName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustFirstName(final String custFirstName) {
		this.custFirstName = custFirstName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustFirstName() {
		return this.custFirstName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustMiddleName(final String custMiddleName) {
		this.custMiddleName = custMiddleName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustMiddleName() {
		return this.custMiddleName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCustLastName(final String custLastName) {
		this.custLastName = custLastName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustLastName() {
		return this.custLastName;
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

	public void setStateLicId(final String stateLicId) {
		this.stateLicId = stateLicId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getStateLicId() {
		return this.stateLicId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDeaId(final String deaId) {
		this.deaId = deaId;
	}

	/**
	 * 
	 * @generated
	 */
	public String getDeaId() {
		return this.deaId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setComments(final String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @generated
	 */
	public String getComments() {
		return this.comments;
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
			Date x=null;
			this.createDt =x;
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
			this.updateDt =x;
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

	public void setCustCd(final String custCd) {
		this.custCd = custCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getCustCd() {
		return this.custCd;
	}

	/**
	 * 
	 * @generated
	 */
	/*
	 * public TCustAffType getTCustAffType() { return this.tCustAffType; }
	 */
	/**
	 * 
	 * @generated
	 */
	/*
	 * public void setTCustAffType(final TCustAffType tCustAffType) {
	 * this.tCustAffType = tCustAffType;
	 * 
	 * }
	 */

	/**
	 * 
	 * @generated
	 */
	/*
	 * public TPrtType getTPrtType() { return this.tPrtType; }
	 */
	/**
	 * 
	 * @generated
	 */
	/*
	 * public void setTPrtType(final TPrtType tPrtType) { this.tPrtType =
	 * tPrtType;
	 * 
	 * }
	 */

	/**
	 * 
	 * @generated
	 */
	/*
	 * public TCustCategory getTCustCategory() { return this.tCustCategory; }
	 */

	/**
	 * 
	 * @generated
	 */
	/*
	 * public void setTCustCategory(final TCustCategory tCustCategory) {
	 * this.tCustCategory = tCustCategory;
	 * 
	 * }
	 */
	
	/**
	 * 
	 * @generated
	 */
	public Set<TCustAttr> getTCustAttrss() {
		return this.tCustAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAttrss(final Set<TCustAttr> tCustAttrss) {
		this.tCustAttrss = tCustAttrss;
	}

	public Integer getCustTypeId() {
		return custTypeId;
	}

	public void setCustTypeId(Integer custTypeId) {
		this.custTypeId = custTypeId;
	}

	public Integer getPrtTypeId() {
		return prtTypeId;
	}

	public void setPrtTypeId(Integer prtTypeId) {
		this.prtTypeId = prtTypeId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNamePfx(final String namePfx) {
		this.namePfx = namePfx;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNamePfx() {
		return this.namePfx;
	}

	/**
	 * 
	 * @generated
	 */

	public void setNameSfx(final String nameSfx) {
		this.nameSfx = nameSfx;
	}

	/**
	 * 
	 * @generated
	 */
	public String getNameSfx() {
		return this.nameSfx;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr1(final String attr1) {
		this.attr1 = attr1;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr1() {
		return this.attr1;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr2(final String attr2) {
		this.attr2 = attr2;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr2() {
		return this.attr2;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr3(final String attr3) {
		this.attr3 = attr3;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr3() {
		return this.attr3;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr4(final String attr4) {
		this.attr4 = attr4;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr4() {
		return this.attr4;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr5(final String attr5) {
		this.attr5 = attr5;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr5() {
		return this.attr5;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr6(final String attr6) {
		this.attr6 = attr6;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr6() {
		return this.attr6;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr7(final String attr7) {
		this.attr7 = attr7;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr7() {
		return this.attr7;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr8(final String attr8) {
		this.attr8 = attr8;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr8() {
		return this.attr8;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr9(final String attr9) {
		this.attr9 = attr9;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr9() {
		return this.attr9;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr10(final String attr10) {
		this.attr10 = attr10;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr10() {
		return this.attr10;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr11(final String attr11) {
		this.attr11 = attr11;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr11() {
		return this.attr11;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr12(final String attr12) {
		this.attr12 = attr12;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr12() {
		return this.attr12;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr13(final String attr13) {
		this.attr13 = attr13;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr13() {
		return this.attr13;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr14(final String attr14) {
		this.attr14 = attr14;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr14() {
		return this.attr14;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr15(final String attr15) {
		this.attr15 = attr15;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr15() {
		return this.attr15;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr16(final String attr16) {
		this.attr16 = attr16;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr16() {
		return this.attr16;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr17(final String attr17) {
		this.attr17 = attr17;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr17() {
		return this.attr17;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr18(final String attr18) {
		this.attr18 = attr18;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr18() {
		return this.attr18;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr19(final String attr19) {
		this.attr19 = attr19;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr19() {
		return this.attr19;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr20(final String attr20) {
		this.attr20 = attr20;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr20() {
		return this.attr20;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr21(final String attr21) {
		this.attr21 = attr21;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr21() {
		return this.attr21;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr22(final String attr22) {
		this.attr22 = attr22;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr22() {
		return this.attr22;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr23(final String attr23) {
		this.attr23 = attr23;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr23() {
		return this.attr23;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr24(final String attr24) {
		this.attr24 = attr24;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr24() {
		return this.attr24;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr25(final String attr25) {
		this.attr25 = attr25;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr25() {
		return this.attr25;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr26(final String attr26) {
		this.attr26 = attr26;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr26() {
		return this.attr26;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr27(final String attr27) {
		this.attr27 = attr27;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr27() {
		return this.attr27;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr28(final String attr28) {
		this.attr28 = attr28;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr28() {
		return this.attr28;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr29(final String attr29) {
		this.attr29 = attr29;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr29() {
		return this.attr29;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr30(final String attr30) {
		this.attr30 = attr30;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr30() {
		return this.attr30;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr31(final String attr31) {
		this.attr31 = attr31;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr31() {
		return this.attr31;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr32(final String attr32) {
		this.attr32 = attr32;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr32() {
		return this.attr32;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr33(final String attr33) {
		this.attr33 = attr33;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr33() {
		return this.attr33;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr34(final String attr34) {
		this.attr34 = attr34;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr34() {
		return this.attr34;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr35(final String attr35) {
		this.attr35 = attr35;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr35() {
		return this.attr35;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr36(final String attr36) {
		this.attr36 = attr36;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr36() {
		return this.attr36;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr37(final String attr37) {
		this.attr37 = attr37;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr37() {
		return this.attr37;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr38(final String attr38) {
		this.attr38 = attr38;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr38() {
		return this.attr38;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr39(final String attr39) {
		this.attr39 = attr39;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr39() {
		return this.attr39;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr40(final String attr40) {
		this.attr40 = attr40;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr40() {
		return this.attr40;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr41(final String attr41) {
		this.attr41 = attr41;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr41() {
		return this.attr41;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr42(final String attr42) {
		this.attr42 = attr42;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr42() {
		return this.attr42;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr43(final String attr43) {
		this.attr43 = attr43;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr43() {
		return this.attr43;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr44(final String attr44) {
		this.attr44 = attr44;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr44() {
		return this.attr44;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr45(final String attr45) {
		this.attr45 = attr45;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr45() {
		return this.attr45;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr46(final String attr46) {
		this.attr46 = attr46;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr46() {
		return this.attr46;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr47(final String attr47) {
		this.attr47 = attr47;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr47() {
		return this.attr47;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr48(final String attr48) {
		this.attr48 = attr48;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr48() {
		return this.attr48;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr49(final String attr49) {
		this.attr49 = attr49;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr49() {
		return this.attr49;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAttr50(final String attr50) {
		this.attr50 = attr50;
	}

	/**
	 * 
	 * @generated
	 */
	public String getAttr50() {
		return this.attr50;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAddr> getTCustAddrss() {
		return this.tCustAddrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAddrss(final Set<TCustAddr> tCustAddrss) {
		this.tCustAddrss = tCustAddrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAff> getTCustAffsForAffCustIds() {
		return this.tCustAffsForAffCustIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAffsForAffCustIds(final Set<TCustAff> tCustAffsForAffCustIds) {
		this.tCustAffsForAffCustIds = tCustAffsForAffCustIds;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAlgmnt> getTCustAlgmntss() {
		return this.tCustAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAlgmntss(final Set<TCustAlgmnt> tCustAlgmntss) {
		this.tCustAlgmntss = tCustAlgmntss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustAff> getTCustAffsForCustIds() {
		return this.tCustAffsForCustIds;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustAffsForCustIds(final Set<TCustAff> tCustAffsForCustIds) {
		this.tCustAffsForCustIds = tCustAffsForCustIds;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustPrdSales> getTCustPrdss() {
		return this.tCustPrdss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustPrdss(final Set<TCustPrdSales> tCustPrdss) {
		this.tCustPrdss = tCustPrdss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TCustContact> getTCustContactss() {
		return this.tCustContactss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTCustContactss(final Set<TCustContact> tCustContactss) {
		this.tCustContactss = tCustContactss;
	}
	

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TCust that) {
		setCustId(that.getCustId());
		setCustName(that.getCustName());
		setCustFirstName(that.getCustFirstName());
		setCustMiddleName(that.getCustMiddleName());
		setCustLastName(that.getCustLastName());
		setActiveFlag(that.getActiveFlag());
		setStateLicId(that.getStateLicId());
		setDeaId(that.getDeaId());
		setComments(that.getComments());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setCustCd(that.getCustCd());
		setCustTypeId(that.getCustTypeId());
		setPrtTypeId(that.getPrtTypeId());
		setCategoryId(that.getCategoryId());
		setNamePfx(that.getNamePfx());
		setNameSfx(that.getNameSfx());
		setAttr1(that.getAttr1());
		setAttr2(that.getAttr2());
		setAttr3(that.getAttr3());
		setAttr4(that.getAttr4());
		setAttr5(that.getAttr5());
		setAttr6(that.getAttr6());
		setAttr7(that.getAttr7());
		setAttr8(that.getAttr8());
		setAttr9(that.getAttr9());
		setAttr10(that.getAttr10());
		setAttr11(that.getAttr11());
		setAttr12(that.getAttr12());
		setAttr13(that.getAttr13());
		setAttr14(that.getAttr14());
		setAttr15(that.getAttr15());
		setAttr16(that.getAttr16());
		setAttr17(that.getAttr17());
		setAttr18(that.getAttr18());
		setAttr19(that.getAttr19());
		setAttr20(that.getAttr20());
		setAttr21(that.getAttr21());
		setAttr22(that.getAttr22());
		setAttr23(that.getAttr23());
		setAttr24(that.getAttr24());
		setAttr25(that.getAttr25());
		setAttr26(that.getAttr26());
		setAttr27(that.getAttr27());
		setAttr28(that.getAttr28());
		setAttr29(that.getAttr29());
		setAttr30(that.getAttr30());
		setAttr31(that.getAttr31());
		setAttr32(that.getAttr32());
		setAttr33(that.getAttr33());
		setAttr34(that.getAttr34());
		setAttr35(that.getAttr35());
		setAttr36(that.getAttr36());
		setAttr37(that.getAttr37());
		setAttr38(that.getAttr38());
		setAttr39(that.getAttr39());
		setAttr40(that.getAttr40());
		setAttr41(that.getAttr41());
		setAttr42(that.getAttr42());
		setAttr43(that.getAttr43());
		setAttr44(that.getAttr44());
		setAttr45(that.getAttr45());
		setAttr46(that.getAttr46());
		setAttr47(that.getAttr47());
		setAttr48(that.getAttr48());
		setAttr49(that.getAttr49());
		setAttr50(that.getAttr50());
		setStatusId(that.getStatusId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("custId=[").append(custId).append("] ");
		buffer.append("custName=[").append(custName).append("] ");
		buffer.append("custFirstName=[").append(custFirstName).append("] ");
		buffer.append("custMiddleName=[").append(custMiddleName).append("] ");
		buffer.append("custLastName=[").append(custLastName).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("stateLicId=[").append(stateLicId).append("] ");
		buffer.append("deaId=[").append(deaId).append("] ");
		buffer.append("comments=[").append(comments).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("custCd=[").append(custCd).append("] ");
		buffer.append("custTypeId=[").append(custTypeId).append("] ");
		buffer.append("prtTypeId=[").append(prtTypeId).append("] ");
		buffer.append("categoryId=[").append(categoryId).append("] ");
		buffer.append("namePfx=[").append(namePfx).append("] ");
		buffer.append("nameSfx=[").append(nameSfx).append("] ");
		buffer.append("attr1=[").append(attr1).append("] ");
		buffer.append("attr2=[").append(attr2).append("] ");
		buffer.append("attr3=[").append(attr3).append("] ");
		buffer.append("attr4=[").append(attr4).append("] ");
		buffer.append("attr5=[").append(attr5).append("] ");
		buffer.append("attr6=[").append(attr6).append("] ");
		buffer.append("attr7=[").append(attr7).append("] ");
		buffer.append("attr8=[").append(attr8).append("] ");
		buffer.append("attr9=[").append(attr9).append("] ");
		buffer.append("attr10=[").append(attr10).append("] ");
		buffer.append("attr11=[").append(attr11).append("] ");
		buffer.append("attr12=[").append(attr12).append("] ");
		buffer.append("attr13=[").append(attr13).append("] ");
		buffer.append("attr14=[").append(attr14).append("] ");
		buffer.append("attr15=[").append(attr15).append("] ");
		buffer.append("attr16=[").append(attr16).append("] ");
		buffer.append("attr17=[").append(attr17).append("] ");
		buffer.append("attr18=[").append(attr18).append("] ");
		buffer.append("attr19=[").append(attr19).append("] ");
		buffer.append("attr20=[").append(attr20).append("] ");
		buffer.append("attr21=[").append(attr21).append("] ");
		buffer.append("attr22=[").append(attr22).append("] ");
		buffer.append("attr23=[").append(attr23).append("] ");
		buffer.append("attr24=[").append(attr24).append("] ");
		buffer.append("attr25=[").append(attr25).append("] ");
		buffer.append("attr26=[").append(attr26).append("] ");
		buffer.append("attr27=[").append(attr27).append("] ");
		buffer.append("attr28=[").append(attr28).append("] ");
		buffer.append("attr29=[").append(attr29).append("] ");
		buffer.append("attr30=[").append(attr30).append("] ");
		buffer.append("attr31=[").append(attr31).append("] ");
		buffer.append("attr32=[").append(attr32).append("] ");
		buffer.append("attr33=[").append(attr33).append("] ");
		buffer.append("attr34=[").append(attr34).append("] ");
		buffer.append("attr35=[").append(attr35).append("] ");
		buffer.append("attr36=[").append(attr36).append("] ");
		buffer.append("attr37=[").append(attr37).append("] ");
		buffer.append("attr38=[").append(attr38).append("] ");
		buffer.append("attr39=[").append(attr39).append("] ");
		buffer.append("attr40=[").append(attr40).append("] ");
		buffer.append("attr41=[").append(attr41).append("] ");
		buffer.append("attr42=[").append(attr42).append("] ");
		buffer.append("attr43=[").append(attr43).append("] ");
		buffer.append("attr44=[").append(attr44).append("] ");
		buffer.append("attr45=[").append(attr45).append("] ");
		buffer.append("attr46=[").append(attr46).append("] ");
		buffer.append("attr47=[").append(attr47).append("] ");
		buffer.append("attr48=[").append(attr48).append("] ");
		buffer.append("attr49=[").append(attr49).append("] ");
		buffer.append("attr50=[").append(attr50).append("] ");
		buffer.append("statusId=[").append(statusId).append("] ");
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
		final TCust other = (TCust) obj;
		if (custId == null) {
			if (other.custId != null) {
				return false;
			}
		} else if (!custId.equals(other.custId)) {
			return false;
		}
		return true;
	}
}
