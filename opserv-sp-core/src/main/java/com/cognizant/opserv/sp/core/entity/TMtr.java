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

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/** 
 * JPA class representing TMtr 
 * The corresponding mapping table is t_mtr 
 */

@Entity
@Audited
@NamedQueries({
		@NamedQuery(name = "FindAllTMtrs", query = "select myTMtr from TMtr myTMtr"),
		@NamedQuery(name = "FindMetricsForWorkLoad", query = "select myTMtr.mtrName,myTSalesPosMtrValue.mtrValue from TMtr myTMtr, TSalesPosMtrValue myTSalesPosMtrValue " +
				" where myTMtr.mtrId = myTSalesPosMtrValue.mtrId and myTSalesPosMtrValue.salesPosId = ?1 "+
				 "and myTMtr.tenantId = ?2 "),
				@NamedQuery(name = "FindMetricsForHeat", query = "select myTGeoMtrValue.postalCd,myTGeoMtrValue.mtrValue from TMtr myTMtr,TGeoMtrValue myTGeoMtrValue, TTerrZipAlgmnt myTTerrZipAlgmnt, TSalesPosGeoUnit myTSalesPosGeoUnit, TSalesPos myTSalesPos where " +
						" myTMtr.mtrId = myTGeoMtrValue.mtrId " +
						" and myTTerrZipAlgmnt.postalCd = myTGeoMtrValue.postalCd "+
						" and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTGeoMtrValue.salesHierId "+
						" and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTGeoMtrValue.salesPosId "+
						" and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId "+
						" and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId "+
						" and myTSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId "+
						" and myTSalesPos.tAlgmntSalesHier.salesHierId = myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId "+
						" and myTSalesPos.salesPosId = myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId "+
						" and myTMtr.mtrName = ?1" +
						" and myTGeoMtrValue.salesPosId = ?2 " +
						" and myTMtr.dataDotFlag = ?3 " +
						" and myTMtr.tenantId = ?4  "),				
				            @NamedQuery(name = "FindMetricsandCountFromSalesAandZip", query = "select myTMtr.mtrName,myTGeoMtrValue.mtrValue from TMtr myTMtr,TSalesPosMtrValue myTSalesPosMtrValue,TGeoMtrValue myTGeoMtrValue,TTerrZipAlgmnt myTTerrZipAlgmnt " +
							" where myTTerrZipAlgmnt.postalCd = myTGeoMtrValue.postalCd  " +
							" and  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTGeoMtrValue.salesHierId " +
							" and  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTGeoMtrValue.salesPosId " +
							" and  myTGeoMtrValue.mtrId = myTMtr.mtrId " +
							" and  myTMtr.mtrId = myTSalesPosMtrValue.mtrId " +
							" and myTGeoMtrValue.salesPosId = ?1 "+
							" and myTGeoMtrValue.postalCd = ?2 " +
							" and myTMtr.tenantId = ?3 order by myTMtr.updateDt DESC "),
							   @NamedQuery(name = "FindMetricsandCountFromSalesPosHierAndZip", query = "select myTMtr.mtrName,myTGeoMtrValue.mtrValue from TMtr myTMtr,TSalesPosMtrValue myTSalesPosMtrValue,TGeoMtrValue myTGeoMtrValue,TTerrZipAlgmnt myTTerrZipAlgmnt " +
							" where myTTerrZipAlgmnt.postalCd = myTGeoMtrValue.postalCd  " +
							" and  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTGeoMtrValue.salesHierId " +
							" and  myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTGeoMtrValue.salesPosId " +
							" and  myTGeoMtrValue.mtrId = myTMtr.mtrId and  myTGeoMtrValue.mtrId = ?3" +
							" and  myTMtr.mtrId = myTSalesPosMtrValue.mtrId " +
							" and myTGeoMtrValue.salesPosId = ?1 "+
							" and myTGeoMtrValue.postalCd = ?2 " +
							" and myTMtr.tenantId = ?4 and myTMtr.dataDotFlag= 'Y' and myTMtr.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC "),					
							@NamedQuery(name = "FindValueFromSalesAandZipandName", query = "select myTGeoMtrValue.mtrValue,myTTerrZipAlgmnt.postalCd from TMtr myTMtr,TGeoMtrValue myTGeoMtrValue,TTerrZipAlgmnt myTTerrZipAlgmnt " +
                                                              " where myTTerrZipAlgmnt.postalCd = myTGeoMtrValue.postalCd  " +
                                                              " and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTGeoMtrValue.salesHierId " +
                                                              " and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTGeoMtrValue.salesPosId " +
                                                              " and myTGeoMtrValue.mtrId = myTMtr.mtrId " +
                                                              " and myTGeoMtrValue.salesPosId = ?1 "+
                                                              " and myTGeoMtrValue.postalCd = ?2 "+
                                                              " and myTMtr.mtrName = ?3 " + 
                                                              " and myTMtr.tenantId = ?4 order by myTGeoMtrValue.updateDt desc"),

						@NamedQuery(name = "FindOnlyMetricsForHeat", query = "select distinct myTMtr.mtrName from TMtr myTMtr,TGeoMtrValue myTGeoMtrValue,TTerrZipAlgmnt myTTerrZipAlgmnt,TSalesPosGeoUnit myTSalesPosGeoUnit, TSalesPos myTSalesPos where " +
                                        " myTMtr.mtrId = myTGeoMtrValue.mtrId" +
                                        " and myTTerrZipAlgmnt.postalCd = myTGeoMtrValue.postalCd "+
                                        " and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTGeoMtrValue.salesHierId "+
                                        " and myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTGeoMtrValue.salesPosId "+
                                        " and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId "+
                                        " and myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId "+
                                        " and myTSalesPosGeoUnit.tSalesPosGeoUnitId.geoId = myTTerrZipAlgmnt.tSalesPosGeoUnit.tSalesPosGeoUnitId.geoId "+
                                        " and myTSalesPos.tAlgmntSalesHier.salesHierId = myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesHierId "+
                                        " and myTSalesPos.salesPosId = myTSalesPosGeoUnit.tSalesPosGeoUnitId.salesPosId "+
                                        " and myTGeoMtrValue.salesPosId = ?1 " +
                                        " and myTMtr.dataDotFlag = ?2 " +
                                        " and myTMtr.tenantId = ?3 "),	
		@NamedQuery(name = "FindHeatMetricsForSalesAlignMent", query = "select myTMtr from TMtr myTMtr,TAlgmntSalesTeam myTAlgmntSalesTeam,TSalesTeam myTSalesTeam,TAlgmnt myTAlgmnt,TAlgmntSalesHier myTAlgmntSalesHier,TOrgSalesHier myTOrgSalesHier where myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId " +
				"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId  " +
				"and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmnt.algmntId " +
				"and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId " +
				"and myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTSalesTeam.tSalesTeamId.salesTeamId " +
				"and myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTSalesTeam.tSalesTeamId.bussUnitId " +
				"and myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId "+
				"and myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId "+
				"and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmnt.algmntId "+	
				"and myTAlgmntSalesHier.tOrgSalesHier.orgSalesHierId = myTOrgSalesHier.orgSalesHierId "+
				"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = ?1 " +
				"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?2 " +
				"and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?3 " +
				"and myTMtr.dataDotFlag = ?4 " +
				"and myTAlgmntSalesHier.tOrgSalesHier.hierName = ?5 "),
		@NamedQuery(name = "FindWorkLoadMetricsForSalesAlignMent", query = "select myTMtr from TMtr myTMtr,TAlgmntSalesTeam myTAlgmntSalesTeam,TSalesTeam myTSalesTeam,TAlgmnt myTAlgmnt,TAlgmntSalesHier myTAlgmntSalesHier,TOrgSalesHier myTOrgSalesHier where myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId " +
						"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId  " +
						"and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmnt.algmntId " +
						"and myTAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmnt.algmntId " +
						"and myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTSalesTeam.tSalesTeamId.salesTeamId " +
						"and myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTSalesTeam.tSalesTeamId.bussUnitId " +
						"and myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId "+
						"and myTAlgmntSalesHier.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = myTAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId "+
						"and myTAlgmntSalesHier.tAlgmntSalesTeam.tAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmnt.algmntId "+	
						"and myTAlgmntSalesHier.tOrgSalesHier.orgSalesHierId = myTOrgSalesHier.orgSalesHierId "+
						"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = ?1 " +
						"and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?2 " +
						"and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?3 " +						
						"and myTAlgmntSalesHier.tOrgSalesHier.hierName = ?4 "),		
			@NamedQuery(name = "FindMetricsForCatAlBUSt", query = "select myTMtr from TMtr myTMtr, TMtrConfig myTMtrConfig where "
					+ "myTMtrConfig.tMtr.mtrId = myTMtr.mtrId "	
					+ "and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = ?1 "
					+ "and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?2 "
					+ "and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?3 "
	                + "and myTMtr.mtrCategoryId = ?4 "
	                + "and myTMtr.tenantId = ?5 "
                    + "and myTMtrConfig.tAlgmntSalesHier.salesHierId = ?6 "
	                + "and myTMtr.activeFlag='Y' and myTMtrConfig.activeFlag='Y' "),
                    
             @NamedQuery(name = "FindMetricsForAlBUSt", query = "select myTMtr from TMtr myTMtr,TMtrConfig myTMtrConfig where "
                    + "myTMtrConfig.tMtr.mtrId = myTMtr.mtrId "	
					+ "and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId = ?1 "
					+ "and myTMtr.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId = ?2 "
					+ "and myTMtr.tAlgmntSalesTeam.tAlgmnt.algmntId = ?3 "
	                + "and myTMtr.tenantId = ?4 "
					+ "and myTMtrConfig.tAlgmntSalesHier.salesHierId = ?5 " 
					+ "and myTMtr.activeFlag='Y' and myTMtrConfig.activeFlag='Y' "),
					
		@NamedQuery(name = "CountTMtrs", query = "Select Count(c) from TMtr c"),
		@NamedQuery(name = "FindTMtrByTMtrCategory", query = "select myTMtr from TMtr myTMtr where myTMtr.mtrCategoryId = ?1 "),
		@NamedQuery(name = "CountTMtrsByTMtrCategory", query = "select Count(myTMtr) from TMtr myTMtr where myTMtr.mtrCategoryId = ?1 "),
		@NamedQuery(name = "FindTMtrByTAlgmntSalesTeam", query = "select myTMtr from TMtr myTMtr where myTMtr.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindTMtrByIDs", query = "select myTMtr from TMtr myTMtr, TMtrConfig myTMtrConfig where myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTMtr.mtrId = myTMtrConfig.tMtrConfigId.mtrId AND myTMtrConfig.tMtrConfigId.salesHierId = ?4  AND myTMtr.tenantId = ?5 AND  myTMtr.activeFlag = 'Y' AND myTMtrConfig.activeFlag = 'Y' AND myTMtr.dataDotFlag = 'Y' order by myTMtr.updateDt DESC,myTMtr.mtrName"),
		@NamedQuery(name = "FindTMtrByABSIDs", query = "select myTMtr from TMtr myTMtr where myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3  AND myTMtr.activeFlag = 'Y' "),
		@NamedQuery(name = "FindTMtrByCtgID", query = "select myTMtr from TMtr myTMtr,TMtrConfig myTMtrConfig where myTMtr.mtrCategoryId = ?1 AND myTMtr.mtrId = myTMtrConfig.tMtrConfigId.mtrId AND myTMtrConfig.tMtrConfigId.salesHierId = ?2 AND myTMtr.tenantId = ?3 AND myTMtrConfig.activeFlag = 'Y' AND myTMtr.activeFlag = 'Y' AND myTMtr.dataDotFlag= 'Y' ORDER BY myTMtr.updateDt DESC, myTMtr.mtrName  "),
		@NamedQuery(name = "CountTMtrsByTAlgmntSalesTeam", query = "select Count(myTMtr) from TMtr myTMtr where myTMtr.tAlgmntSalesTeam = ?1 "),
		@NamedQuery(name = "FindAllMetrics", query = "select myTMtr.mtrId,myTMtr.mtrName,myTMtrCategory.mtrCategoryCd,myTMtr.enforceFlag,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTMtr.dataDotFlag,myTMtr.visibleFlag from TMtr myTMtr,TMtrCategory myTMtrCategory,TAlgmntSalesTeam myTAlgmntSalesTeam,TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TBussUnit myTBussUnit,TAlgmntStatus myTAlgmntStatus where myTMtr.tenantId=myTMtrCategory.tenantId AND myTMtr.tenantId=myTAlgmntSalesTeam.tenantId AND myTMtr.tenantId=myTAlgmnt.tenantId AND myTMtr.tenantId=myTSalesTeam.tenantId AND myTMtr.tenantId=myTBussUnit.tenantId AND myTMtr.tenantId=myTAlgmntStatus.tenantId AND myTMtr.mtrCategoryId=myTMtrCategory.tMtrCategoryId.mtrCategoryId and myTMtr.activeFlag = ?1 and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?2 AND myTAlgmnt.algmntName like ?3 AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?4 AND myTBussUnit.bussUnitName like ?5 AND myTMtr.tenantId = ?6"),
		@NamedQuery(name = "FindTMtrByMtrVal", query = "select myTMtr from TMtr myTMtr where myTMtr.mtrId =?1 "),
		@NamedQuery(name = "FindTMtrByAlgmntID", query = "select myTMtr from TMtr myTMtr where myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and myTMtr.activeFlag='Y'"),
		@NamedQuery(name = "FindTMtrByTenantCtgId", query = "select myTMtr from TMtr myTMtr where myTMtr.mtrCategoryId = ?1 AND myTMtr.tenantId = ?2 AND myTMtr.activeFlag = 'Y' ORDER BY myTMtr.mtrName"),
		@NamedQuery(name = "GetTMtrByCtgIDALBUST", query = "select myTMtr from TMtr myTMtr inner join myTMtr.tAlgmntSalesTeam albust inner join albust.tSalesTeam st inner join albust.tAlgmnt alg where myTMtr.mtrCategoryId = ?1 AND myTMtr.tenantId = ?2 AND myTMtr.activeFlag = 'Y' and st.tSalesTeamId.bussUnitId=?3 and st.tSalesTeamId.salesTeamId=?4 and alg.algmntId=?5 ORDER BY myTMtr.updateDt DESC, myTMtr.mtrName"),
		@NamedQuery(name = "findAllTMtrsFrGIS", query = "select myTMtr from TMtr myTMtr, TMtrConfig myTMtrConfig where myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2 AND myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 AND myTMtr.mtrId = myTMtrConfig.tMtrConfigId.mtrId AND myTMtrConfig.tMtrConfigId.salesHierId = ?4  AND myTMtr.tenantId = ?5 AND  myTMtr.activeFlag = 'Y' AND myTMtrConfig.activeFlag = 'Y' order by myTMtr.updateDt DESC,myTMtr.mtrName"),
		@NamedQuery(name = "GetTMtrCatByALBUSTSH", query = "select cat.mtrCategoryId,cat.mtrCategoryCd,cat.mtrCategoryDesc,cat.tMtrCategoryId.localeId,mtr.mtrId,mtr.mtrName from TMtrCategory cat,TMtr mtr,TMtrConfig conf where mtr.mtrCategoryId = cat.mtrCategoryId and cat.activeFlag = mtr.activeFlag and conf.tMtrConfigId.mtrId = mtr.mtrId and mtr.activeFlag = conf.activeFlag and mtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?1 and mtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?2  and mtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?3 and cat.tMtrCategoryId.localeId = ?4 and mtr.tenantId = ?5 and mtr.activeFlag = 'Y' and  conf.tMtrConfigId.salesHierId = ?6 ORDER BY cat.mtrCategoryDesc,mtr.mtrName"),
		  @NamedQuery(name = "FindMetricsandCountFromSalesPosHierAndZipFrMtrList", query = "select myTMtr.mtrName,myTGeoMtrValue.mtrValue, myTMtr.mtrId  from TMtr myTMtr,TGeoMtrValue myTGeoMtrValue " +
					" where myTGeoMtrValue.mtrId = myTMtr.mtrId and  myTGeoMtrValue.mtrId IN ?3" +
					" and myTGeoMtrValue.salesPosId = ?1 "+
					" and myTGeoMtrValue.postalCd = ?2 " +
					" and myTMtr.tenantId = ?4 and myTMtr.dataDotFlag= 'Y' and myTMtr.activeFlag = 'Y' order by myTGeoMtrValue.updateDt DESC "),
		@NamedQuery(name = "GetMetricInfoByALBUSTCate", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue from TMtr mtr inner join mtr.tAlgmntSalesTeam alst inner join mtr.tMtrConfigss confg where alst.tAlgmntSalesTeamId.algmntId = ?1 and alst.tAlgmntSalesTeamId.bussUnitId = ?2 and alst.tAlgmntSalesTeamId.salesTeamId = ?3 and confg.tMtrConfigId.salesHierId = ?4 and mtr.mtrCategoryId = ?5 and mtr.tenantId = ?6 and mtr.activeFlag = confg.activeFlag and mtr.activeFlag = 'Y' group by mtr.mtrId order by mtr.mtrName"),
		@NamedQuery(name = "GetMetricInfoByALBUST", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue,mtr.uomId,mtr.dataDotFlag from TMtr mtr inner join mtr.tAlgmntSalesTeam alst inner join mtr.tMtrConfigss confg where alst.tAlgmntSalesTeamId.algmntId = ?1 and alst.tAlgmntSalesTeamId.bussUnitId = ?2 and alst.tAlgmntSalesTeamId.salesTeamId = ?3 and confg.tMtrConfigId.salesHierId = ?4 and mtr.tenantId = ?5 and mtr.activeFlag = confg.activeFlag and mtr.activeFlag = 'Y' group by mtr.mtrId order by mtr.mtrName"),
		@NamedQuery(name = "GetMetricInfoByALBUSTExprType", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue,expr.mtrExpr,confg.childRollupFlag,mtr.enforceFlag,mtr.uomId from TMtr mtr inner join mtr.tAlgmntSalesTeam alst inner join mtr.tMtrConfigss confg inner join mtr.tMtrExprss expr inner join confg.tMtrExecConfigss execConfg where alst.tAlgmntSalesTeamId.algmntId = ?1 and alst.tAlgmntSalesTeamId.bussUnitId = ?2 and alst.tAlgmntSalesTeamId.salesTeamId = ?3 and confg.tMtrConfigId.salesHierId = ?4 and expr.exprType IN ?5 and execConfg.tMtrTrigger.triggerId = ?6 and mtr.tenantId = ?7 and mtr.activeFlag=execConfg.activeFlag and mtr.activeFlag = confg.activeFlag and mtr.activeFlag = 'Y' group by mtr.mtrId order by mtr.mtrName"),
		@NamedQuery(name = "FindTMtrByMtrId", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue,mtr.uomId,mtr.enforceFlag from TMtr mtr where mtr.mtrId =?1 and mtr.tenantId = ?2 "),
		@NamedQuery(name = "FindTMtrByMtrIds", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue,mtr.uomId,mtr.enforceFlag from TMtr mtr where mtr.mtrId IN ?1 and mtr.tenantId = ?2  and mtr.activeFlag = 'Y' "),
		@NamedQuery(name = "GetMetricInfoByAL", query = "select mtr.mtrId,mtr.mtrName,mtr.minValue,mtr.maxValue,mtr.uomId,mtr.dataDotFlag from TMtr mtr inner join mtr.tAlgmntSalesTeam alst inner join mtr.tMtrConfigss confg where alst.tAlgmntSalesTeamId.algmntId = ?1 and mtr.tenantId = ?2 and mtr.activeFlag = confg.activeFlag and mtr.activeFlag = 'Y' group by mtr.mtrId order by mtr.mtrName"),
		})
@Table(name = "t_mtr", uniqueConstraints = @UniqueConstraint(columnNames = { "mtr_id" }))
public class TMtr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @generated
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mtr_id", nullable = false, length = 255)
	private Integer mtrId;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "enforce_flag", nullable = true, length = 1)
	private Character enforceFlag;

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
	@Column(name = "min_value", nullable = true, length = 255)
	private Float minValue;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "max_value", nullable = true, length = 255)
	private Float maxValue;

	/**
	 * 
	 * @generated
	 */
	@NotEmpty
	@Length(max = 50)
	@Column(name = "mtr_name", nullable = false, length = 50)
	private String mtrName;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "data_dot_flag", nullable = true, length = 1)
	private Character dataDotFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "prec", nullable = true, length = 255)
	private Integer prec;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "visible_flag", nullable = true, length = 1)
	private Character visibleFlag;

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
	
	@NotNull
	@Column(name = "mtr_category_id", nullable = false, length = 255)
	private Integer mtrCategoryId;
	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "mtr_type", nullable = true, length = 1)
	private Character mtrType;
	
	
	/**
	 * 
	 * @generated
	 */
	@Column(name = "comp_mtr_flag", nullable = true, length = 1)
	private Character compMtrFlag;

	/**
	 * 
	 * @generated
	 */
	@Column(name = "uom_id", nullable = true, length = 255)
	private Integer uomId;
/*	@ManyToOne
	@JoinColumn(name = "mtr_category_id", referencedColumnName = "mtr_category_id", nullable = false, insertable = true, updatable = true)
	@Valid
	private TMtrCategory tMtrCategory;*/

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "algmnt_id", referencedColumnName = "algmnt_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "buss_unit_id", referencedColumnName = "buss_unit_id", nullable = false, insertable = true, updatable = true),
			@JoinColumn(name = "sales_team_id", referencedColumnName = "sales_team_id", nullable = false, insertable = true, updatable = true) })
	@Valid
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	private TAlgmntSalesTeam tAlgmntSalesTeam;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtr")
	//@Audited
	@NotAudited
	private Set<TMtrValMsg> tMtrValMsgss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtr")
	@NotAudited
	private Set<TMtrAttr> tMtrAttrss;

	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtr")
	@NotAudited
	private Set<TMtrExpr> tMtrExprss;
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "tMtr")
	@NotAudited
	private Set<TMtrConfig> tMtrConfigss;

	/**
	 *
	 * @generated
	 */
	public TMtr() {
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrId(final Integer mtrId) {
		this.mtrId = mtrId;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getMtrId() {
		return this.mtrId;
	}

	/**
	 * 
	 * @generated
	 */

	public void setEnforceFlag(final Character enforceFlag) {
		this.enforceFlag = enforceFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getEnforceFlag() {
		return this.enforceFlag;
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

	public Integer getMtrCategoryId() {
		return mtrCategoryId;
	}

	public void setMtrCategoryId(Integer mtrCategoryId) {
		this.mtrCategoryId = mtrCategoryId;
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

	public void setMinValue(final Float minValue) {
		this.minValue = minValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getMinValue() {
		return this.minValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMaxValue(final Float maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * 
	 * @generated
	 */
	public Float getMaxValue() {
		return this.maxValue;
	}

	/**
	 * 
	 * @generated
	 */

	public void setMtrName(final String mtrName) {
		this.mtrName = mtrName;
	}

	/**
	 * 
	 * @generated
	 */
	public String getMtrName() {
		return this.mtrName;
	}

	/**
	 * 
	 * @generated
	 */

	public void setDataDotFlag(final Character dataDotFlag) {
		this.dataDotFlag = dataDotFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getDataDotFlag() {
		return this.dataDotFlag;
	}

	/**
	 * 
	 * @generated
	 */

	public void setPrec(final Integer prec) {
		this.prec = prec;
	}

	/**
	 * 
	 * @generated
	 */
	public Integer getPrec() {
		return this.prec;
	}

	/**
	 * 
	 * @generated
	 */

	public void setVisibleFlag(final Character visibleFlag) {
		this.visibleFlag = visibleFlag;
	}

	/**
	 * 
	 * @generated
	 */
	public Character getVisibleFlag() {
		return this.visibleFlag;
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
/*	public TMtrCategory getTMtrCategory() {
		return this.tMtrCategory;
	}

	*//**
	 * 
	 * @generated
	 *//*
	public void setTMtrCategory(final TMtrCategory tMtrCategory) {
		this.tMtrCategory = tMtrCategory;

	}*/

	public Character getMtrType() {
		return mtrType;
	}

	public void setMtrType(Character mtrType) {
		this.mtrType = mtrType;
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
	public Set<TMtrValMsg> getTMtrValMsgss() {
		return this.tMtrValMsgss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrValMsgss(final Set<TMtrValMsg> tMtrValMsgss) {
		this.tMtrValMsgss = tMtrValMsgss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TMtrAttr> getTMtrAttrss() {
		return this.tMtrAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrAttrss(final Set<TMtrAttr> tMtrAttrss) {
		this.tMtrAttrss = tMtrAttrss;
	}

	/**
	 * 
	 * @generated
	 */
	public Set<TMtrExpr> getTMtrExprss() {
		return this.tMtrExprss;
	}

	/**
	 * 
	 * @generated
	 */
	public void setTMtrExprss(final Set<TMtrExpr> tMtrExprss) {
		this.tMtrExprss = tMtrExprss;
	}
	
	public Set<TMtrConfig> getTMtrConfigss() {
		return this.tMtrConfigss;
	}

	public void setTMtrConfigss(final Set<TMtrConfig> tMtrConfigss) {
		this.tMtrConfigss = tMtrConfigss;
	}

	
	public Character getCompMtrFlag() {
		return compMtrFlag;
	}

	public void setCompMtrFlag(Character compMtrFlag) {
		this.compMtrFlag = compMtrFlag;
	}

	public Integer getUomId() {
		return uomId;
	}

	public void setUomId(Integer uomId) {
		this.uomId = uomId;
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 * 
	 * @generated
	 */
	public void copy(final TMtr that) {
		setMtrId(that.getMtrId());
		setEnforceFlag(that.getEnforceFlag());
		setActiveFlag(that.getActiveFlag());
		setEffStartDt(that.getEffStartDt());
		setEffEndDt(that.getEffEndDt());
		setMinValue(that.getMinValue());
		setMaxValue(that.getMaxValue());
		setMtrName(that.getMtrName());
		setDataDotFlag(that.getDataDotFlag());
		setPrec(that.getPrec());
		setVisibleFlag(that.getVisibleFlag());
		setCreatedBy(that.getCreatedBy());
		setCreateDt(that.getCreateDt());
		setUpdatedBy(that.getUpdatedBy());
		setUpdateDt(that.getUpdateDt());
		setTenantId(that.getTenantId());
		setMtrType(that.getMtrType());
		setCompMtrFlag(that.getCompMtrFlag());
		setUomId(that.getUomId());
	}

	/**
	 * @generated
	 * 
	 */
	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((mtrId == null) ? 0 : mtrId.hashCode());
		return result;
	}

	/**
	 * Returns a textual representation of a bean.
	 * 
	 * @generated
	 */
	public String toString() {

		final StringBuilder buffer = new StringBuilder();

		buffer.append("mtrId=[").append(mtrId).append("] ");
		buffer.append("enforceFlag=[").append(enforceFlag).append("] ");
		buffer.append("activeFlag=[").append(activeFlag).append("] ");
		buffer.append("effStartDt=[").append(effStartDt).append("] ");
		buffer.append("effEndDt=[").append(effEndDt).append("] ");
		buffer.append("minValue=[").append(minValue).append("] ");
		buffer.append("maxValue=[").append(maxValue).append("] ");
		buffer.append("mtrName=[").append(mtrName).append("] ");
		buffer.append("dataDotFlag=[").append(dataDotFlag).append("] ");
		buffer.append("prec=[").append(prec).append("] ");
		buffer.append("visibleFlag=[").append(visibleFlag).append("] ");
		buffer.append("createdBy=[").append(createdBy).append("] ");
		buffer.append("createDt=[").append(createDt).append("] ");
		buffer.append("updatedBy=[").append(updatedBy).append("] ");
		buffer.append("updateDt=[").append(updateDt).append("] ");
		buffer.append("tenantId=[").append(tenantId).append("] ");
		buffer.append("compMtrFlag=[").append(compMtrFlag).append("] ");
		buffer.append("uomId=[").append(uomId).append("] ");
		buffer.append("mtrType=[").append(mtrType).append("]");

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
		final TMtr other = (TMtr) obj;
		if (mtrId == null) {
			if (other.mtrId != null) {
				return false;
			}
		} else if (!mtrId.equals(other.mtrId)) {
			return false;
		}
		return true;
	}

/*	public TMtrCategoryId gettMtrCategoryId() {
		return tMtrCategoryId;
	}


	public void settMtrCategoryId(TMtrCategoryId tMtrCategoryId) {
		this.tMtrCategoryId = tMtrCategoryId;
	}*/
	
}




