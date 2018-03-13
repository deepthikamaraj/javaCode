package com.cognizant.opserv.sp.core.common;

/**
 * The Class QueryConstants.
 */
/******************************************************************************
 * @class Name:	QueryConstants to QueryConstants
 * @author 		Cognizant Technology Solutions
 * @version 	OpServ 1.0
 * @since		12/11/2014
 *****************************************************************************/
public class QueryConstants {

	/** The Constant FINDTALGMNTST. */
	public static final String FINDTALGMNTST = "select myTAlgmntSalesTeam from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where " +
			" myTAlgmnt.algmntId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmnt.tBussUnit.bussUnitId = myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntSalesTeam.activeFlag = 'Y' and myTAlgmnt.tBussUnit.bussUnitId = myTBussUnit.bussUnitId" +
			" and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntStatus.tAlgmntStatusId.statusId = myTAlgmnt.algmntStatusId " +
			" and myTSalesTeam.salesTeamName like ?1 and myTAlgmnt.algmntName like ?2 and myTAlgmntStatus.tAlgmntStatusId.statusId = ?3 and myTBussUnit.bussUnitName like ?4 and myTAlgmntSalesTeam.tenantId=?5 and myTAlgmntStatus.tAlgmntStatusId.localeId = ?6";
	
	/** The Constant FINDALLMETRICS. */
	public static final String FINDALLMETRICS = "select myTMtr.mtrId,myTMtr.mtrName,myTMtrCategory.mtrCategoryCd,myTMtr.enforceFlag,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTMtr.dataDotFlag,myTMtr.visibleFlag,myTAlgmnt.algmntName,myTAlgmnt.effStartDt from TMtr myTMtr,TMtrCategory myTMtrCategory,TAlgmntSalesTeam myTAlgmntSalesTeam,TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TBussUnit myTBussUnit,TAlgmntStatus myTAlgmntStatus where myTMtr.tenantId=myTMtrCategory.tenantId AND myTMtr.tenantId=myTAlgmntSalesTeam.tenantId AND myTMtr.tenantId=myTAlgmnt.tenantId AND myTMtr.tenantId=myTSalesTeam.tenantId AND myTMtr.tenantId=myTBussUnit.tenantId AND myTMtr.tenantId=myTAlgmntStatus.tenantId AND myTMtr.mtrCategoryId=myTMtrCategory.tMtrCategoryId.mtrCategoryId and myTMtr.activeFlag = ?1 and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTMtr.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?2 AND myTAlgmnt.algmntName like ?3 AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?4 AND myTBussUnit.bussUnitName like ?5 AND myTMtr.tenantId = ?6 AND myTAlgmntStatus.tAlgmntStatusId.localeId = ?7";
	
	/** The Constant FINDALIGNMENTS. */
	public static final String FINDALIGNMENTS = "select myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesTeam.tSalesTeamId.salesTeamId,myTAlgmnt.algmntId,myTAlgmnt.algmntName,myTSalesTeam.salesTeamName,myTAlgmntStatus.statusDesc,myTBussUnit.bussUnitName from TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TAlgmntStatus myTAlgmntStatus,TBussUnit myTBussUnit,TAlgmntSalesTeam myTAlgmntSalesTeam where myTAlgmntSalesTeam.tenantId=myTAlgmnt.tenantId and myTAlgmntSalesTeam.tenantId= myTSalesTeam.tenantId and myTAlgmntSalesTeam.tenantId=myTAlgmntStatus.tenantId and myTAlgmntSalesTeam.tenantId=  myTBussUnit.tenantId and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?1 AND myTAlgmnt.algmntName like ?2 AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?3 AND myTBussUnit.bussUnitName like ?4 AND myTAlgmntSalesTeam.tenantId = ?5 and myTAlgmntStatus.tAlgmntStatusId.localeId = ?6 and myTAlgmntSalesTeam.activeFlag = ?7";
	
	/** The Constant FINDCUSTOMERS. */
	public static final String FINDCUSTOMERS = "select myTCust.custId,myTCust.custCd,myTCustCategory.categoryName,myTCustType.typeName,myTCust.custName,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTSalesPos.tSalesPosId.salesHierId,myTSalesPos.tSalesPosId.salesPosId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt from TCust myTCust,TCustType myTCustType,TCustCategory myTCustCategory,TSalesPos myTSalesPos,TCustAlgmnt myTCustAlgmnt where myTCust.tenantId = myTCustType.tenantId and myTCust.tenantId = myTCustCategory.tenantId and myTCust.tenantId = myTCustAlgmnt.tenantId and myTCust.tenantId = myTSalesPos.tenantId and myTCust.custTypeId=myTCustType.tCustTypeId.custTypeId and myTCust.categoryId=myTCustCategory.tCustCategoryId.custCategoryId and myTCustAlgmnt.tCust.custId=myTCust.custId and myTCustAlgmnt.salesPosId = myTSalesPos.tSalesPosId.salesPosId and myTCustAlgmnt.salesHierId = myTSalesPos.tSalesPosId.salesHierId and myTCustAlgmnt.activeFlag = 'Y' and myTCustCategory.tCustCategoryId.custCategoryId = ?1 and myTCustType.tCustTypeId.custTypeId = ?2 and myTCust.custCd like ?3 and myTCust.custName like ?4 and myTSalesPos.tSalesPosId.salesPosId = ?5 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = ?6 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId = ?7 and myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId = ?8 and myTSalesPos.tSalesPosId.salesHierId =?9 and myTCust.activeFlag = ?10 and myTCust.tenantId = ?11";
	
	/** The Constant FINDTCUSTALGMNTS. */
	public static final String FINDTCUSTALGMNTS = "select myTCust.custId,myTCust.custCd,myTCustCategory.categoryName,myTCustType.typeName,myTCust.custName,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTSalesPos.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTSalesPos.tSalesPosId.salesHierId,myTSalesPos.tSalesPosId.salesPosId,myTCustAlgmnt.effStartDt,myTCustAlgmnt.effEndDt "
			+ "from TCust myTCust,TCustType myTCustType,TCustCategory myTCustCategory,TCustAlgmnt myTCustAlgmnt "
			+ ",TSalesPos myTSalesPos,TAlgmntSalesTeam myTAlgmntSalesTeam, TAlgmntBussRule myTAlgmntBussRule "
			+ "where myTCust.custTypeId=myTCustType.tCustTypeId.custTypeId and myTCust.categoryId=myTCustCategory.tCustCategoryId.custCategoryId "
			+ "and myTCust.custId = myTCustAlgmnt.tCust.custId "
			+ "and myTCustAlgmnt.salesPosId=myTSalesPos.tSalesPosId.salesPosId "
			+ "and myTCustAlgmnt.salesHierId=myTSalesPos.tSalesPosId.salesHierId "
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
			+ "and myTAlgmntBussRule.tBussRuleConfig.tBussRuleConfigId.bussRuleConfigId=1 and myTAlgmntBussRule.value='Y' and myTAlgmntBussRule.activeFlag='Y' "
			+ "and myTCustAlgmnt.activeFlag = 'Y' "
			+ "and myTCust.activeFlag = ?1 and myTCustCategory.categoryName = ?2 and myTCustType.typeName = ?3 and myTCust.custCd like ?4 and myTCust.custName like ?5 and myTCust.custId IN ?6 and myTCustAlgmnt.tenantId = ?7";
	
	/** The Constant FINDALLFLEXIRULES. */
	public static final String FINDALLFLEXIRULES = "select myTFlexiRule.flexiRuleId,myTFlexiRule.ruleName,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,myTAlgmnt.algmntName,myTBussUnit.bussUnitName,myTSalesTeam.salesTeamName from TFlexiRule myTFlexiRule,TAlgmntSalesTeam myTAlgmntSalesTeam,TAlgmnt myTAlgmnt,TSalesTeam myTSalesTeam,TBussUnit myTBussUnit,TAlgmntStatus myTAlgmntStatus where myTFlexiRule.tenantId = myTAlgmntSalesTeam.tenantId AND myTFlexiRule.tenantId=myTAlgmnt.tenantId AND myTFlexiRule.tenantId=myTSalesTeam.tenantId AND myTFlexiRule.tenantId=myTBussUnit.tenantId AND myTFlexiRule.tenantId=myTAlgmntStatus.tenantId and myTFlexiRule.activeFlag = ?1 " +
    		"and myTAlgmnt.algmntStatusId = myTAlgmntStatus.tAlgmntStatusId.statusId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTSalesTeam.tSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTSalesTeam.tSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTAlgmnt.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId=myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId = myTFlexiRule.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId and myTAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId=myTBussUnit.bussUnitId  and myTSalesTeam.salesTeamName like ?2 " +
    		"AND myTAlgmnt.algmntName like ?3 " +
    		"AND myTAlgmntStatus.tAlgmntStatusId.statusId = ?4 " +
    		"AND myTBussUnit.bussUnitName like ?5 " +
    		"AND myTFlexiRule.tenantId = ?6 AND myTAlgmntStatus.tAlgmntStatusId.localeId = ?7";
	
	/** The Constant FINDTUSERS. */
	public static final String FINDTUSERS = "select myTUsr from TUsr myTUsr , TUsrBussUnit myTUsrBussUnit where myTUsr.loginName like ?1 and myTUsr.tenantId = ?2";
	
	/** The Constant FINDTUSERROLESFORHM. */
	public static final String FINDTUSERROLESFORHM = "select myTUsrRole from TUsr myTUsr,TUsrRole myTUsrRole , TUsrBussUnit myTUsrBussUnit  where myTUsrRole.tUsrRoleId.usrId=myTUsr.usrId and myTUsr.usrId=myTUsrBussUnit.tUsrBussUnitId.usrId and myTUsrRole.tUsrRoleId.roleId =?1 and myTUsrRole.tUsr.tenantId = ?2";
	
	/** The Constant FINDTUSERROLESFORADMIN. */
	public static final String FINDTUSERROLESFORADMIN = "select myTUsrRole from TUsr myTUsr,TUsrRole myTUsrRole  where myTUsrRole.tUsrRoleId.usrId=myTUsr.usrId and myTUsrRole.tUsrRoleId.roleId =?1 and myTUsrRole.tUsr.tenantId = ?2";
	
	/** The Constant FINDTEMPLATE. */
	public static final String FINDTEMPLATE = "select myTBussObjTmpl from TBussObjTmpl myTBussObjTmpl"; 
	
	//Query to find Customers - FF Affiliations
	/** The Constant FINDCUSTSFORAFF. */
	public static final String FINDCUSTSFORAFF = "select distinct myTCust.custId, myTCust.custName, myTCust.custCd, myTCust FROM TCust myTCust ";
	
	/** The Constant FINDALGMNTDETAILS. */
	public static final String FINDALGMNTDETAILS="select myTEmpAlgmnt.empAlgmntId,myTAlgmnt.algmntId,myTAlgmnt.algmntName,myTEmpAlgmnt.effStartDt,myTEmpAlgmnt.effEndDt,myTSalesPos.tSalesPosId.salesPosId,myTSalesPos.posName,myTSalesPos.posCd,myTAlgmntStatus.tAlgmntStatusId.statusId,myTAlgmntStatus.statusDesc,myTAllocType.allocTypeDesc"+
" from TEmpAlgmnt myTEmpAlgmnt,TAlgmnt myTAlgmnt,TSalesPos myTSalesPos, TBussUnit myTBussUnit,TAlgmntStatus myTAlgmntStatus,TAllocType myTAllocType where " +
                                  "myTEmpAlgmnt.algmntId=myTAlgmnt.algmntId and " +
                                  "myTEmpAlgmnt.tSalesPos.tSalesPosId.salesPosId= myTSalesPos.tSalesPosId.salesPosId and "+
                                  "myTEmpAlgmnt.tSalesPos.tSalesPosId.salesHierId= myTSalesPos.tSalesPosId.salesHierId and "+
                                  "myTEmpAlgmnt.bussUnitId=myTBussUnit.bussUnitId and "+
                                  "myTAlgmnt.algmntStatusId=myTAlgmntStatus.tAlgmntStatusId.statusId and "+
                                  "myTAllocType.tAllocTypeId.allocTypeId=myTEmpAlgmnt.allocTypeId and "+
                                  "myTEmpAlgmnt.tenantId=myTSalesPos.tenantId and "+
                                  "myTEmpAlgmnt.tenantId=myTBussUnit.tenantId and "+
                                  "myTEmpAlgmnt.tenantId=myTAlgmntStatus.tenantId and "+
                                  "myTEmpAlgmnt.tPers.staffId=?1 and "+
                                  "myTEmpAlgmnt.tenantId=?2 and "+
                                  "myTAlgmntStatus.tAlgmntStatusId.localeId=?3";
	
	/** The Constant FINDEMPSFORASSIGNMENT. */
	public static final String FINDEMPSFORASSIGNMENT="select distinct myTPers.staffId, myTPers.empName, myTPers.firstName,  myTPers.middleName, " +
			"myTPers.lastName, myTPers.clientId, myTPers.statusId, myTPers.empDt from TPers myTPers ";
	
	public static final String ALIGMENT_SEARCH_JOIN = "select al.algmntId,al.algmntName,bu.bussUnitId,bu.bussUnitName,st.tSalesTeamId.salesTeamId,st.salesTeamName,al.algmntStatusId,al.effEndDt,al.effStartDt from TAlgmntSalesTeam albust INNER JOIN albust.tAlgmnt al INNER JOIN albust.tSalesTeam st INNER JOIN st.tBussUnit bu";
	
	public static final String ALIGMENT_SEARCH_SEARCH_LIKE = "where al.algmntName like ?1 and bu.bussUnitName like ?2 and st.salesTeamName like ?3 and al.algmntStatusId = ?4 and albust.tenantId=?5 and albust.activeFlag=st.activeFlag and albust.activeFlag=bu.activeFlag and albust.activeFlag='Y'";
	
	public static final String ALIGMENT_SEARCH_SEARCH_USER_IN = "where al.algmntName like ?1 and bu.bussUnitId IN ?2 and st.tSalesTeamId.salesTeamId IN ?3 and al.algmntStatusId = ?4 and albust.tenantId=?5 and albust.activeFlag=st.activeFlag and albust.activeFlag=bu.activeFlag and albust.activeFlag='Y'";
	
	public static final String ALIGMENT_SEARCH_SEARCH_GROUP_BY = "group by al.algmntId,bu.bussUnitId,st.tSalesTeamId.salesTeamId order by al.algmntName,bu.bussUnitName,st.salesTeamName";
	
	public static final String ALIGMENT_IS_AFFILIATED = "select algaff.tAlgmntSalesTeam.tAlgmnt.algmntId,algaff.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId,algaff.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId from TCustAlgmntAff algaff where algaff.tenantId = ?1 and algaff.activeFlag = 'Y' group by algaff.tAlgmntSalesTeam.tAlgmnt.algmntId,algaff.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.bussUnitId,algaff.tAlgmntSalesTeam.tSalesTeam.tSalesTeamId.salesTeamId";
	
	public static final String ALIGMENT_CR_CONFIG = "select cr.alignmentId,cr.bussUnitId,cr.salesTeamId,count(cr.alignmentId) from TChngReqConfig cr where cr.tenantId = ?1 and activeFlag = 'Y' group by cr.alignmentId,cr.bussUnitId,cr.salesTeamId";
	
	public static final String ALIGMENT_BUS_RULE_CONFIG = "select br.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,br.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,br.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId,count(br.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId) from TAlgmntBussRule br where br.tenantId = ?1 and br.activeFlag = 'Y' group by br.tAlgmntSalesTeam.tAlgmntSalesTeamId.algmntId,br.tAlgmntSalesTeam.tAlgmntSalesTeamId.bussUnitId,br.tAlgmntSalesTeam.tAlgmntSalesTeamId.salesTeamId";
	
	public static final String ALIGMENT_SEARCH_END_DATE = "where albust.tenantId=?1 and (albust.effEndDt >= ?2  OR albust.effEndDt IS NULL ) and albust.activeFlag=st.activeFlag and albust.activeFlag=bu.activeFlag and albust.activeFlag='Y'";

	public static final String ALIGMENT_SEARCH_END_DATE_USER_IN = "where bu.bussUnitId IN ?1 and st.tSalesTeamId.salesTeamId IN ?2 and albust.tenantId=?3 and (albust.effEndDt >= ?4  OR albust.effEndDt IS NULL ) and albust.activeFlag=st.activeFlag and albust.activeFlag=bu.activeFlag and albust.activeFlag='Y'";
	
	public static final String AFF_CUST_ALGMNT_TEMP_TABLE = "CREATE TABLE `TEMPTABLE` (`active_flag` char(1) DEFAULT NULL,`eff_start_dt` date NOT NULL,`eff_end_dt` date DEFAULT NULL,`cust_algmnt_id` bigint(20) NOT NULL,`cust_id` int(11) NOT NULL,`created_by` int(11) NOT NULL,`create_dt` datetime NOT NULL,`updated_by` int(11) DEFAULT NULL,`update_dt` datetime DEFAULT NULL,`tenant_id` smallint(6) NOT NULL,`sales_pos_id` bigint(20) NOT NULL,`sales_hier_id` bigint(20) NOT NULL,`algmnt_id` bigint(20) NOT NULL,`buss_unit_id` bigint(20) NOT NULL,`sales_team_id` bigint(20) NOT NULL,`aff_flag` char(1) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
}
