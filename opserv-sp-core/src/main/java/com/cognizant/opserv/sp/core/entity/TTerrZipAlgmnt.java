package com.cognizant.opserv.sp.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import org.hibernate.validator.constraints.Length;


/** 
 * JPA class representing TTerrZipAlgmnt 
 * The corresponding mapping table is t_terr_zip_algmnt 
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "FindAllTTerrZipAlgmnts", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt"),
		@NamedQuery(name = "CountTTerrZipAlgmnts", query = "Select Count(c) from TTerrZipAlgmnt c"),
		@NamedQuery(name = "FindTTerrZipAlgmntByTSalesPosGeoUnit", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit = ?1 "),
		@NamedQuery(name = "CountTTerrZipAlgmntsByTSalesPosGeoUnit", query = "select Count(myTTerrZipAlgmnt) from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit = ?1 "),
		@NamedQuery(name = "FindTTerrZipAlgmntByTSalesPos", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos = ?1 "),
		//@NamedQuery(name = "FindTTZAForAssignedFlagByPostalCd", query = "select myTTerrZipAlgmnt.assignedFlag from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd = ?1 "),
		@NamedQuery(name = "getTTsalesPOS", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd = ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "getSalesPosId", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd IN ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "FindTTerrZipAlgmntByIds", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?1 AND myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?2 AND myTTerrZipAlgmnt.tenantId=?3 AND myTTerrZipAlgmnt.activeFlag = 'y' "),
		@NamedQuery(name = "FindTTerrZipAlgmntByPostalCd", query = "select myTTerrZipAlgmnt  from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd = ?1 "),
		@NamedQuery(name = "FindTTerrZipAlgmntBySalesHier", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = ?1 AND myTTerrZipAlgmnt.tSalesPos.salesPosId = ?2 "),
		@NamedQuery(name = "FindTTerrZipAlgmntByTenantId", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId = ?1  AND myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = ?2 AND myTTerrZipAlgmnt.tenantId=?3"),
		@NamedQuery(name = "FindCountOfTTerZipAlgmntForSP", query = "select Count(DISTINCT myTTerrZipAlgmnt.postalCd) from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId  = ?1  AND myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = ?2 AND myTTerrZipAlgmnt.tenantId=?3 AND (myTTerrZipAlgmnt.effEndDt >= ?4 OR myTTerrZipAlgmnt.effEndDt IS NULL) AND myTTerrZipAlgmnt.activeFlag = ?5 AND myTTerrZipAlgmnt.assignedFlag = 'Y' "),
		@NamedQuery(name = "FindCountOfTTerZipAlgmntForSPAndChild", query = "select Count(distinct myTTerrZipAlgmnt.postalCd) from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId  in( ?1) AND myTTerrZipAlgmnt.tenantId=?2 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "CountTTerrZipAlgmntsByTSalesPos", query = "select Count(*) from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos = ?1 "),
		@NamedQuery(name = "getTTerrZipAlgmnt", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 and myTTerrZipAlgmnt.activeFlag='Y'"),
		@NamedQuery(name = "getTTerrZipAlgmntForComb", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 "),
		@NamedQuery(name = "FindSalesPosByPostalCode", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.algmntId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.bussUnitId = ?2 and myTTerrZipAlgmnt.tSalesPosGeoUnit.salesTeamId = ?3 and myTTerrZipAlgmnt.postalCd IN ?4 and  myTTerrZipAlgmnt.tenantId = ?5 and myTTerrZipAlgmnt.activeFlag='Y' AND myTTerrZipAlgmnt.assignedFlag ='Y'"),
		@NamedQuery(name = "FindTTerrZipAlgmntbyGeoID", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?2 and myTTerrZipAlgmnt.postalCd = ?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?4 and myTTerrZipAlgmnt.tenantId = ?5 "),
		@NamedQuery(name = "findTTerrZipAlgmntBySpShIdList", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId in ?1 AND myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId in ?2 AND myTTerrZipAlgmnt.tenantId=?3 AND myTTerrZipAlgmnt.activeFlag = 'Y' "),
		@NamedQuery(name = "FindzipCodesByparentID", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.algmntId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.bussUnitId = ?2 and myTTerrZipAlgmnt.tSalesPosGeoUnit.salesTeamId = ?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId IN ?4 and  myTTerrZipAlgmnt.tenantId = ?5 and myTTerrZipAlgmnt.activeFlag='Y'"),
		@NamedQuery(name = "getTTsalesPOSForHeatMap", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd IN ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "getTTsalesPOSFrDataDots", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd IN ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' "),
		@NamedQuery(name = "GetPostalCodesForALBUSTSPS", query = "select DISTINCT myTTerrZipAlgmnt.postalCd from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.algmntId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.bussUnitId = ?2 and myTTerrZipAlgmnt.tSalesPosGeoUnit.salesTeamId = ?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId IN ?4 and  myTTerrZipAlgmnt.tenantId = ?5 and myTTerrZipAlgmnt.activeFlag='Y'")	,
		@NamedQuery(name = "getActiveTTerrZipAlgmnt", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?4 AND myTTerrZipAlgmnt.tenantId =?5 "),
		@NamedQuery(name = "getActiveTTerrZipAlgmntByZipCode", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd = ?1 and myTTerrZipAlgmnt.tenantId =?2 "),
		@NamedQuery(name = "getTTerrZipAlgmntsByTSalesPosCount", query = "select count(myTTerrZipAlgmnt) from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?2 and myTTerrZipAlgmnt.tenantId = ?3 "),
			@NamedQuery(name = "FindAllPostalCodeAssignedFrSp", query = "select myTTerrZipAlgmnt.postalCd from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos.salesPosId = ?1 AND myTTerrZipAlgmnt.tenantId = ?2 AND  myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = ?3 "),
		@NamedQuery(name = "getActiveZipCodeFrSP", query = "select ttza.postalCd,ttza.tSalesPos.salesPosId,ttza.tSalesPos.tAlgmntSalesHier.salesHierId,ttza.tSalesPos.posName, ttza.tSalesPos.posCd from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getActiveZipCodeFrSPOnSrch", query = "select ttza.postalCd,ttza.tSalesPos.salesPosId,ttza.tSalesPos.tAlgmntSalesHier.salesHierId,ttza.tSalesPos.posName, ttza.tSalesPos.posCd from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.postalCd like (?4) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getActiveTTerrZipAlgmntGeoId", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 and myTTerrZipAlgmnt.activeFlag = 'Y' and myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getTTerrZipAlgmntGeoId", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?5"),
		@NamedQuery(name = "getActiveZipCodeFrSPCount", query = "select count(ttza.postalCd) from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getCheckFrBricksInTargetSP", query = "select ttza.postalCd from TTerrZipAlgmnt ttza where ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?1 and ttza.tSalesPos.salesPosId IN ?2 and ttza.postalCd IN ?3 and ttza.tenantId =?4 and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getActiveZipCodeFrSPCountOnSrch", query = "select count(ttza.postalCd) from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.postalCd like (?4) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "fetchShareTerrSPFrBrick", query ="select myTTerrZipAlgmnt.postalCd,myTSalesPos.posName,myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId, myTTerrZipAlgmnt.tSalesPos.salesPosId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId, myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId  from TTerrZipAlgmnt myTTerrZipAlgmnt , TSalesPos myTSalesPos Where myTTerrZipAlgmnt.postalCd = ?1 AND myTTerrZipAlgmnt.tenantId =?2 AND myTTerrZipAlgmnt.tSalesPos.salesPosId = myTSalesPos.salesPosId " +
						   " AND myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = myTSalesPos.tAlgmntSalesHier.salesHierId AND myTTerrZipAlgmnt.assignedFlag ='Y' AND myTTerrZipAlgmnt.activeFlag= 'Y' AND myTSalesPos.activeFlag ='Y' "),
		@NamedQuery(name = "FindCountOfTTerZipAlgmntForSPAssociatedBricks", query = "select Count(*) from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId  = ?1 AND myTTerrZipAlgmnt.tSalesPos.tAlgmntSalesHier.salesHierId = ?2 AND myTTerrZipAlgmnt.tenantId=?3 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getActiveBrickPostalCd", query = "select DISTINCT myTTerrZipAlgmnt.postalCd from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.postalCd IN ?1 and myTTerrZipAlgmnt.tenantId = ?2 and myTTerrZipAlgmnt.activeFlag='Y' "),
		//for wave 2
		@NamedQuery(name = "getActiveZipCodeCountForSPOnSrch", query = "select count(ttza.postalCd) from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.tSalesPos.posName like (?4) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),		
		@NamedQuery(name = "getActiveBrickCodesForSPOnSrch", query = "select ttza.postalCd,ttza.tSalesPos.salesPosId,ttza.tSalesPos.tAlgmntSalesHier.salesHierId,ttza.tSalesPos.posName, ttza.tSalesPos.posCd from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.tSalesPos.posName like (?4) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getActiveBrickCodesForBoth", query = "select ttza.postalCd,ttza.tSalesPos.salesPosId,ttza.tSalesPos.tAlgmntSalesHier.salesHierId,ttza.tSalesPos.posName, ttza.tSalesPos.posCd from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.tSalesPos.posName like (?4) and ttza.postalCd like (?5) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "getActiveZipCodeCountForBoth", query = "select count(ttza.postalCd) from TTerrZipAlgmnt ttza where ttza.tSalesPos.salesPosId IN ?1 and ttza.tSalesPos.tAlgmntSalesHier.salesHierId IN ?2 and ttza.tenantId =?3 and ttza.tSalesPos.posName like (?4) and ttza.postalCd like (?5) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
		@NamedQuery(name = "fetchTTerrZipAlgmntDetails", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId=?4 and myTTerrZipAlgmnt.tenantId =?4 and myTTerrZipAlgmnt.activeFlag='N' and myTTerrZipAlgmnt.assignedFlag='N'"),
		
		@NamedQuery(name = "FindCountOfTTerZipAlgmntForAlBuSt", query = "select Count(*) from TTerrZipAlgmnt myTTerrZipAlgmnt " +
				"where myTTerrZipAlgmnt.tSalesPos.salesPosId In (?1)  AND myTTerrZipAlgmnt.tenantId = ?2"),
				
				
		@NamedQuery(name = "FindCountOfTTerZipAlgmntForAlBuSt1", query = " select Count(*) from TTerrZipAlgmnt myTTerrZipAlgmnt, TSalesPos myTSalesPos " +
				"where  myTTerrZipAlgmnt.tSalesPos.salesPosId = myTSalesPos.salesPosId " +
				" AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1" +
				" AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 " +
				" AND myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3" +
				" AND myTTerrZipAlgmnt.activeFlag = 'Y'" +
				" AND myTSalesPos.tenantId = ?4"),
				
		@NamedQuery(name = "getActiveTTerrZipAlgmntByAlBUSTZip", query = " select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt , TSalesPos myTSalesPos " +
				"where myTTerrZipAlgmnt.tSalesPos.salesPosId = myTSalesPos.salesPosId  "+
				"AND myTTerrZipAlgmnt.tSalesPosGeoUnit.algmntId = ?1 AND myTTerrZipAlgmnt.tSalesPosGeoUnit.bussUnitId = ?2 and myTTerrZipAlgmnt.tSalesPosGeoUnit.salesTeamId = ?3 " +
				"AND myTTerrZipAlgmnt.postalCd = ?4 AND myTTerrZipAlgmnt.activeFlag = 'Y' AND myTTerrZipAlgmnt.assignedFlag = 'Y' " + 
				"AND myTSalesPos.tenantId = ?5"),
		
		@NamedQuery(name = "getAllPostalCodes", query = "select ttza from TTerrZipAlgmnt ttza where ttza.tSalesPosGeoUnit.algmntId = ?1 and ttza.tSalesPosGeoUnit.bussUnitId = ?2  and ttza.tSalesPosGeoUnit.salesTeamId = ?3" +
			" and ttza.tenantId =?4 and ttza.postalCd like (?5) and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' "),
				
	    @NamedQuery(name = "getAllGeographyAlignments", query = "select ttza from TTerrZipAlgmnt ttza where ttza.tSalesPos.tAlgmntSalesHier.salesHierId  = ?1 " +
					"and ttza.tSalesPos.salesPosId= ?2 " +
					"and ttza.tenantId = ?3 and ttza.activeFlag = 'Y' and ttza.assignedFlag ='Y' ")	,
		@NamedQuery(name = "FindTTerZipAlgmntForSP", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId  IN( ?1) AND myTTerrZipAlgmnt.postalCd = ?2 AND myTTerrZipAlgmnt.tenantId=?3 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "fetchTTerrZipAlgmntByChangeRequest", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?1 and myTTerrZipAlgmnt.postalCd = ?2 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?3 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?4 and myTTerrZipAlgmnt.tenantId = ?5 "),
		@NamedQuery(name = "FindTTerrZipAlgmntsByPostalCd", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = ?2 and myTTerrZipAlgmnt.postalCd = ?3 and myTTerrZipAlgmnt.tenantId = ?4 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getActiveTTerrZipAlgmntByGeoId", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = ?5 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getZipCountForSpList", query = "select Count(myTTerrZipAlgmnt.postalCd) from TTerrZipAlgmnt myTTerrZipAlgmnt where   myTTerrZipAlgmnt.tSalesPos.salesPosId  IN ?1 AND myTTerrZipAlgmnt.tenantId=?3 AND (myTTerrZipAlgmnt.effEndDt >= ?2 OR myTTerrZipAlgmnt.effEndDt IS NULL) AND myTTerrZipAlgmnt.activeFlag = 'Y' AND myTTerrZipAlgmnt.assignedFlag = 'Y' and myTTerrZipAlgmnt.effStartDt <= ?2"),
		@NamedQuery(name = "getActiveAssFrZip", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = ?1 and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId =?2 and myTTerrZipAlgmnt.postalCd =?3 and myTTerrZipAlgmnt.tenantId =?4 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getGeoAlignmntBySPnPostalcode", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos.salesPosId = ?1 AND myTTerrZipAlgmnt.postalCd = ?2 and myTTerrZipAlgmnt.tenantId =?3 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'"),
		@NamedQuery(name = "getGeoAlignmntByMltpleCd", query = "select myTTerrZipAlgmnt from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPos.salesPosId = ?1 AND myTTerrZipAlgmnt.postalCd = ?2 and myTTerrZipAlgmnt.tenantId =?3 AND myTTerrZipAlgmnt.geoZipId = ?4 "),
		@NamedQuery(name = "getActiveSpsFrZip", query = "select myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId from TTerrZipAlgmnt myTTerrZipAlgmnt where myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId IN ?1 and myTTerrZipAlgmnt.postalCd =?2 and myTTerrZipAlgmnt.tenantId =?3 AND myTTerrZipAlgmnt.activeFlag='Y' AND  myTTerrZipAlgmnt.assignedFlag = 'Y'")
})
@Table(name = "t_terr_zip_algmnt", uniqueConstraints = @UniqueConstraint(columnNames = { "geo_zip_id" }))
public class TTerrZipAlgmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_zip_id", nullable = false, length = 255)
	private Integer geoZipId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "assigned_flag", nullable = true, length = 1)
	private Character assignedFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "point_zip_flag", nullable = true, length = 1)
	private Character pointZipFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "country_id", nullable = true, length = 255)
	private Integer countryId;

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
	@Length(max = 20)
	@Column(name = "postal_cd", nullable = true, length = 20)
	private String postalCd;

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
	@Column(name = "tenant_id", nullable = false, length = 255)
	private Short tenantId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "status_id", nullable = false, length = 255)
	private Integer statusId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_hier_id", referencedColumnName = "sales_hier_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "geo_id", referencedColumnName = "geo_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	private TSalesPosGeoUnit tSalesPosGeoUnit;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "sales_pos_id", referencedColumnName = "sales_pos_id", nullable = false, insertable = false, updatable = false)
	@Valid
	private TSalesPos tSalesPos;
	
	
	
	/*@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tTerrZipAlgmnt")
	private Set<TGeoMtrValue> tGeoMtrValuess;*/
	
	
	/**
	 *
	 * @generated
	 */
	public TTerrZipAlgmnt() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setGeoZipId(final Integer geoZipId) {
		this.geoZipId = geoZipId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getGeoZipId() {
		return this.geoZipId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setAssignedFlag(final Character assignedFlag) {
		this.assignedFlag = assignedFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getAssignedFlag() {
		return this.assignedFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPointZipFlag(final Character pointZipFlag) {
		this.pointZipFlag = pointZipFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getPointZipFlag() {
		return this.pointZipFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setCountryId(final Integer countryId) {
		this.countryId = countryId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getCountryId() {
		return this.countryId;
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

	public void setPostalCd(final String postalCd) {
		this.postalCd = postalCd;
	}

	/**
	 * 
	 * @generated
	 */
	public String getPostalCd() {
		return this.postalCd;
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
	public TSalesPosGeoUnit getTSalesPosGeoUnit() {
		return this.tSalesPosGeoUnit;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPosGeoUnit(final TSalesPosGeoUnit tSalesPosGeoUnit) {
		this.tSalesPosGeoUnit = tSalesPosGeoUnit;

	}

	/**
	 * 
	 * @generated
	 */
	public TSalesPos getTSalesPos() {
		return this.tSalesPos;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTSalesPos(final TSalesPos tSalesPos) {
		this.tSalesPos = tSalesPos;
		/*if (this.tSalesPos != null && this.tSalesPos.getTSalesPosId() != null) {

			this.tTerrZipAlgmntId.setSalesHierId(this.tSalesPos.getTSalesPosId().getSalesHierId());

		}
		if (this.tSalesPos != null && this.tSalesPos.getTSalesPosId() != null) {

			this.tTerrZipAlgmntId.setSalesPosId(this.tSalesPos.getTSalesPosId().getSalesPosId());

		}*/

	}

//	public Set<TGeoMtrValue> gettGeoMtrValuess() {
//		return this.tGeoMtrValuess;
//
//	}
//
//	public void settGeoMtrValuess(final Set<TGeoMtrValue> tGeoMtrValuess) {
//		this.tGeoMtrValuess = tGeoMtrValuess;
//	}
//	
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
	public void copy(final TTerrZipAlgmnt that) {
		setGeoZipId(that.getGeoZipId());
		setAssignedFlag(that.getAssignedFlag());
		setPointZipFlag(that.getPointZipFlag());
		setCountryId(that.getCountryId());
		setActiveFlag(that.getActiveFlag());
		setPostalCd(that.getPostalCd());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
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
		result = prime * result + ((geoZipId == null) ? 0 : geoZipId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("geoZipId=[").append(geoZipId).append("] ");
		buffer.append("assignedFlag=[").append(assignedFlag).append("] ");
		buffer.append("pointZipFlag=[").append(pointZipFlag).append("] ");
		buffer.append("countryId=[").append(countryId).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("postalCd=[").append(postalCd).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
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
		final TTerrZipAlgmnt other = (TTerrZipAlgmnt) obj;
		if (geoZipId == null) {
			if (other.geoZipId != null) {
				return false;
			}
		} else if (!geoZipId.equals(other.geoZipId)) {
			return false;
		}
		return true;
	}
}
