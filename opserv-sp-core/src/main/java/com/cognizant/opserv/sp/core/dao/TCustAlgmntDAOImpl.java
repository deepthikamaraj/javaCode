package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAttr;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAlgmntDAO")
public class TCustAlgmntDAOImpl implements TCustAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAlgmntDAOImpl.class);
	// Tiles & Grid View  - Search Customers with Address without CR
	private final String fetchAssignedActPropCustFrSrchOnTiles = "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
													+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustAddr.street_name, myTCustAddr.city, "
													+"myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postal_cd, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
													+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag, myTCustAlgmnt.aff_flag FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
													+"t_cust_type myTCustType,t_cust_addr myTCustAddr WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
													+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
													+"AND myCust.tenant_id = myTCustType.tenant_id AND myCust.cust_id = myTCustAddr.cust_id AND myCust.tenant_id = myTCustAddr.tenant_id " 
													+"AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
													+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
													+ " AND ( myTCustAlgmnt.eff_end_dt >= ?7 or myTCustAlgmnt.eff_end_dt IS null ) AND myTCustAlgmnt.active_flag = 'Y' "
													+ " AND myTCustType.locale_id = ?8 AND myTCustAddr.pr_addr_flag = ?9 AND myTCustAddr.active_flag = ?10 AND myTCustCategory.locale_id = ?11 ";
	
	// Tiles & Grid View  - Search Customers with Address with CR
	private final String fetchSrchInActivePendingCRsOnTiles = "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
			+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustAddr.street_name, myTCustAddr.city, "
			+"myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postal_cd, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
			+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag, myTCustAlgmnt.aff_flag, myChng.status_id FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
			+"t_cust_type myTCustType,t_cust_addr myTCustAddr, t_cust_algmnt_chng_req_det myChng WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
			+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
			+"AND myCust.tenant_id = myTCustType.tenant_id AND myCust.cust_id = myTCustAddr.cust_id AND myCust.tenant_id = myTCustAddr.tenant_id " +
			" AND myChng.cust_algmnt_id = myTCustAlgmnt.cust_algmnt_id "
			+"AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
			+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6" 
			+ " AND myTCustAlgmnt.active_flag = 'N' AND myChng.status_id IN (?7) "
			+ " AND myTCustType.locale_id = ?8 AND myTCustAddr.pr_addr_flag = ?9 AND myTCustAddr.active_flag = ?10 AND myTCustCategory.locale_id = ?11 ";
	
	// Tiles & Grid View  - Search Customers with Address with CR - With Extended Attributes
	private final String fetchAssActPropCustFrSrchOnTilesExtAttr =  "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
			+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustAddr.street_name, myTCustAddr.city, "
			+"myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postal_cd, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
			+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag, myChng.status_id FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
			+"t_cust_type myTCustType,t_cust_addr myTCustAddr, t_cust_algmnt_chng_req_det myChng ";
		
	// Tiles & Grid View  - Search Customers with Address with CR - With Extended Attributes
	private final String fetchAssActPropCustFrSrchOnTilesExtAttr1=" WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
			+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
			+"AND myCust.tenant_id = myTCustType.tenant_id AND myCust.cust_id = myTCustAddr.cust_id AND myCust.tenant_id = myTCustAddr.tenant_id " +
			" AND myChng.cust_algmnt_id = myTCustAlgmnt.cust_algmnt_id ";
	
	// Tiles & Grid View  - Search Customers with Address with CR - With Extended Attributes
	private final String fetchAssActPropCustFrSrchOnTilesExtAttr2= " AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
			+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
			+ " AND myTCustAlgmnt.active_flag = 'N' AND myChng.status_id IN (?7) "
			+" AND myTCustType.locale_id = ?8 AND myTCustAddr.pr_addr_flag = ?9 AND myTCustAddr.active_flag = ?10 AND myTCustCategory.locale_id = ?11 ";

	private static final String fetchAssignedActPropCustFrGrid = "SELECT myCust.cust_name, myCust.cust_id, myCust.cust_cd, myTCustType.type_name,myCust.state_lic_id,"
														+"myTCustAlgmnt.cust_algmnt_id, myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag,"
														+"myTCustAddr.street_name, myTCustAddr.city, myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postal_cd,"
														+"myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, myCust.cust_last_name, myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag "
														+" FROM t_cust_algmnt myTCustAlgmnt, t_cust myCust, t_cust_category myTCustCategory,"
														+"t_cust_type myTCustType,t_cust_addr myTCustAddr WHERE myTCustAlgmnt.cust_id = myCust.cust_id"
														+" AND myTCustAlgmnt.tenant_id = myCust.tenant_id AND myCust.category_id = myTCustCategory.category_id"
														+" AND myCust.tenant_id  = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id"
														+" AND myCust.tenant_id = myTCustType.tenant_id AND myCust.cust_id = myTCustAddr.cust_id"
														+" AND myCust.tenant_id = myTCustAddr.tenant_id AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2"
														+" AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 AND myTCustAlgmnt.sales_pos_id = ?5"
														+" AND myTCustAlgmnt.tenant_id = ?6 AND (myTCustAlgmnt.eff_end_dt >= ?7 or myTCustAlgmnt.eff_end_dt IS null)"
														+" AND myTCustAlgmnt.active_flag = ?8 AND myTCustType.locale_id = ?9 AND myTCustAddr.pr_addr_flag = ?10 and myTCustAddr.active_flag = ?11"
														+" AND myTCustCategory.locale_id = ?12";

		private static final String FETCHCUSTALIGNEDLIST = "SELECT c.custAlgmntId,c.effStartDt,c.effEndDt,c.algmntId,c.bussUnitId,c.salesTeamId," +
				" c.salesPosId, c.salesHierId, c.tCust.custId FROM TCustAlgmnt c where c.activeFlag = 'Y' AND c.tenantId = ?1  ";
		
		private static final String FETCHCUSTALIGNEDLISTWITHCUSTID = "SELECT c.custAlgmntId,c.effStartDt,c.effEndDt,c.algmntId,c.bussUnitId,c.salesTeamId," +
				" c.salesPosId, c.salesHierId, c.tCust.custId FROM TCustAlgmnt c where c.activeFlag = 'Y' AND c.tenantId = ?1 AND c.tCust.custId = ?2 ";

		// newly added for secondary address validation	
		// Tiles & Grid View  - Search Customers without Address without CR
		private final String fetchAssignedActPropCustFrSrchOnTilesNoAddrs = "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
				+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, " 
				+"myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
				+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
				+"t_cust_type myTCustType WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
				+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
				+"AND myCust.tenant_id = myTCustType.tenant_id "
				+"AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
				+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
				+"AND (myTCustAlgmnt.eff_end_dt >= ?7 or myTCustAlgmnt.eff_end_dt IS null) AND myTCustAlgmnt.active_flag = 'Y' "
				+"AND myTCustType.locale_id = ?8 AND myTCustCategory.locale_id = ?9 " ;
		
		// Tiles & Grid View  - Search Customers without Address with CR -With Extended Attribute
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr =  "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
				+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
				+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag, myChng.status_id FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
				+"t_cust_type myTCustType, t_cust_algmnt_chng_req_det myChng ";
		// Tiles & Grid View  - Search Customers without Address with CR -With Extended Attribute									
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr1=" WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
				+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
				+"AND myCust.tenant_id = myTCustType.tenant_id " +
				" AND myChng.cust_algmnt_id = myTCustAlgmnt.cust_algmnt_id ";
		// Tiles & Grid View  - Search Customers without Address with CR -With Extended Attribute
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr2= " AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 " +
				" AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
				+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
				+ " AND myTCustAlgmnt.active_flag = 'N' AND myChng.status_id IN (?7) "
				+" AND myTCustType.locale_id = ?8 AND myTCustCategory.locale_id = ?9";
		
		// Tiles & Grid View  - Search Customers without Address with CR
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrs = "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
				+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, " 
				+"myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
				+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag , myChng.status_id FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
				+"t_cust_type myTCustType, t_cust_algmnt_chng_req_det myChng  WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
				+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
				+"AND myCust.tenant_id = myTCustType.tenant_id AND myChng.cust_algmnt_id = myTCustAlgmnt.cust_algmnt_id "
				+"AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
				+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
				+" AND myTCustAlgmnt.active_flag = 'N' AND myChng.status_id IN (?7) "
				+" AND myTCustType.locale_id = ?8 AND myTCustCategory.locale_id = ?9";
		
		// Tiles & Grid View  - Search Customers without Address without CR- With Exteneded Attriutes
		private final String fetchSrchInActivePendingCRsOnTilesExtAttr =  "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
				+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
				+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
				+"t_cust_type myTCustType ";
		
		// Tiles & Grid View  - Search Customers without Address without CR - With Exteneded Attriutes
		private final String fetchSrchInActivePendingCRsOnTilesExtAttr1=" WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
				+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
				+"AND myCust.tenant_id = myTCustType.tenant_id " ;
		
		// Tiles & Grid View  - Search Customers without Address without CR - With Exteneded Attriutes
		private final String fetchSrchInActivePendingCRsOnTilesExtAttr2= " AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 " +
				" AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
				+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
				+ " AND ( myTCustAlgmnt.eff_end_dt >= ?7 or myTCustAlgmnt.eff_end_dt IS null ) AND myTCustAlgmnt.active_flag = 'Y' "
				+ " AND myTCustType.locale_id = ?8 AND myTCustCategory.locale_id = ?9 ";
		
		// Tiles & Grid View  - Search Customers with Address without CR - With Extended Attributes
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr =  "SELECT myCust.cust_name,myCust.cust_id,myCust.cust_cd,myTCustType.type_name, myCust.state_lic_id, myTCustAlgmnt.cust_algmnt_id, "
				+"myTCustAlgmnt.eff_start_dt, myTCustAlgmnt.eff_end_dt, myTCustAlgmnt.target_flag, myTCustAddr.street_name, myTCustAddr.city, "
				+"myTCustAddr.state, myTCustAddr.cntry, myTCustAddr.postal_cd, myTCustType.icon_cd, myCust.cust_first_name, myCust.cust_middle_name, "
				+"myCust.cust_last_name,myTCustAlgmnt.active_flag,myTCustAlgmnt.prt_type_id, myTCustAlgmnt.impl_flag , myTCustAlgmnt.aff_flag FROM t_cust_algmnt myTCustAlgmnt,t_cust myCust,t_cust_category myTCustCategory, "
				+"t_cust_type myTCustType,t_cust_addr myTCustAddr ";
			
		// Tiles & Grid View  - Search Customers with Address without CR - With Extended Attributes
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr1=" WHERE myTCustAlgmnt.cust_id = myCust.cust_id AND myTCustAlgmnt.tenant_id = myCust.tenant_id "
				+"AND myCust.category_id = myTCustCategory.category_id AND myCust.tenant_id = myTCustCategory.tenant_id AND myCust.cust_type_id = myTCustType.cust_type_id "
				+"AND myCust.tenant_id = myTCustType.tenant_id AND myCust.cust_id = myTCustAddr.cust_id AND myCust.tenant_id = myTCustAddr.tenant_id ";
		
		// Tiles & Grid View  - Search Customers with Address without CR - With Extended Attributes
		private final String fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr2= " AND myTCustAlgmnt.algmnt_id = ?1 AND myTCustAlgmnt.buss_unit_id = ?2 AND myTCustAlgmnt.sales_team_id = ?3 AND myTCustAlgmnt.sales_hier_id = ?4 "
				+"AND myTCustAlgmnt.sales_pos_id = ?5 AND myTCustAlgmnt.tenant_id = ?6 " 
				+ " AND ( myTCustAlgmnt.eff_end_dt >= ?7 or myTCustAlgmnt.eff_end_dt IS null ) AND myTCustAlgmnt.active_flag = 'Y' "
				+" AND myTCustType.locale_id = ?8 AND myTCustAddr.pr_addr_flag = ?9 AND myTCustAddr.active_flag = ?10 AND myTCustCategory.locale_id = ?11";
		
		private final String updateActiveTcustAlgmnt = "UPDATE t_cust_algmnt tca, t_sales_pos tpos SET tca.active_flag = 'N', tca.eff_end_dt=tpos.eff_end_dt, tca.update_dt = ?1 "
		    +  "WHERE tca.sales_pos_id=tpos.sales_pos_id AND tca.sales_hier_id=tpos.sales_hier_id AND tca.active_flag='Y' AND tca.tenant_id = ?2 "
			+  "AND (tca.eff_end_dt<= ?1 OR tpos.eff_end_dt<= ?1 OR tpos.active_flag='N')";
		
		
	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUST = "tCust";

	private final Class<TCustAlgmnt> clazz;

	public Class<TCustAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAlgmntDAOImpl() {
		super();
		this.clazz = TCustAlgmnt.class;
	}

	private static final String JPAQL = "select tCustAlgmntentity from TCustAlgmnt tCustAlgmntentity";

	private static final String JPACOUNTQL = "select count(tCustAlgmntentity) from TCustAlgmnt tCustAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tCustAlgmntentity.custAlgmntId like '#{tCustAlgmntentity.getCustAlgmntId}%'",
			"tCustAlgmntentity.activeFlag = #{tCustAlgmntentity.getActiveFlag}",
			"Date(tCustAlgmntentity.effStartDt) = '#{tCustAlgmntentity.getEffStartDt}'",
			"Date(tCustAlgmntentity.effEndDt) = '#{tCustAlgmntentity.getEffEndDt}'",
			"tCustAlgmntentity.createdBy = #{tCustAlgmntentity.getCreatedBy}",
			"Date(tCustAlgmntentity.createDt) = '#{tCustAlgmntentity.getCreateDt}'",
			"tCustAlgmntentity.updatedBy = #{tCustAlgmntentity.getUpdatedBy}",
			"Date(tCustAlgmntentity.updateDt) = '#{tCustAlgmntentity.getUpdateDt}'",
			"tCustAlgmntentity.tenantId = #{tCustAlgmntentity.getTenantId}",
			"tCustAlgmntentity.salesPosId = #{tCustAlgmntentity.getSalesPosId}",
			"tCustAlgmntentity.salesHierId = #{tCustAlgmntentity.getSalesHierId}",
			"tCustAlgmntentity.algmntId = #{tCustAlgmntentity.getAlgmntId}",
			"tCustAlgmntentity.bussUnitId = #{tCustAlgmntentity.getBussUnitId}",
			"tCustAlgmntentity.salesTeamId = #{tCustAlgmntentity.getSalesTeamId}",
			"tCustAlgmntentity.geoAlgmntFlag = #{tCustAlgmntentity.getGeoAlgmntFlag}",
			"tCustAlgmntentity.tCust.custId = #{tCustAlgmntentity.tCust.getCustId}" ,
			"tCustAlgmntentity.statusId = #{tCustAlgmntentity.getStatusId}",
			"tCustAlgmntentity.compElgFlag = #{tCustAlgmntentity.getCompElgFlag}",
			"tCustAlgmntentity.compAlgmntFlag = #{tCustAlgmntentity.getCompAlgmntFlag}"};

	/**
	 * Stores a new TCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be persisted
	 * @return tCustAlgmnt Persisted TCustAlgmnt object
	 */
	public TCustAlgmnt createTCustAlgmnt(final TCustAlgmnt tCustAlgmnt) {
		LOGGER.info("=========== Create TCustAlgmnt ===========");
		return genericDAO.store(tCustAlgmnt);
	}
	
	
	
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<TCustAlgmnt> getCustomerAlignmentBetweenDates(Date startDate,Date endDate,int custId,short tenantId,Long algmntId,Long buId,Long stId)
	{
		List<TCustAlgmnt> custAlignments = new ArrayList<TCustAlgmnt>();
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(startDate);
		paramList.add(endDate);
		paramList.add(Integer.valueOf(custId));		
		paramList.add(Short.valueOf(tenantId));
		paramList.add(Character.valueOf('Y'));
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		
		custAlignments= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustAlgmntsInbetweenDatesForStart", paramList, 0, -1);
		
		
		return custAlignments;
	}
	
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param custId
	 * @param tenantId
	 * @return
	 */
	public List<TCustAlgmnt> getCustomerAlignmentBetweenDatesForEnd(Date startDate,Date endDate,int custId,short tenantId,Long algmntId,Long buId,Long stId)
	{
		List<TCustAlgmnt> custAlignments = new ArrayList<TCustAlgmnt>();

		List<Object> paramList = new ArrayList<Object>();
		paramList.add(startDate);
		paramList.add(endDate);
		paramList.add(Integer.valueOf(custId));
		paramList.add(Short.valueOf(tenantId));
		paramList.add(Character.valueOf('Y'));
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);

		custAlignments = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustAlgmntsInbetweenDatesForEnd", paramList, 0, -1);

		return custAlignments;
	}
	

	/**
	 * Deletes a TCustAlgmnt entity object from the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be deleted
	 */
	public void deleteTCustAlgmnt(final String custAlgmntId) {
		LOGGER.info("=========== Delete TCustAlgmnt ===========");
		final TCustAlgmnt tCustAlgmnt = genericDAO.get(clazz, custAlgmntId);
		genericDAO.remove(tCustAlgmnt);
	}
	
	
	public void deleteTCustAlgmnt(final Long custAlgmntId) {
		LOGGER.info("=========== Delete TCustAlgmnt ===========");
		final TCustAlgmnt tCustAlgmnt = genericDAO.get(clazz, custAlgmntId);
		genericDAO.remove(tCustAlgmnt);
	}


	/**
	 * Updates a TCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be updated
	 * @return tCustAlgmnt Persisted TCustAlgmnt object
	 */
	public TCustAlgmnt updateTCustAlgmnt(final TCustAlgmnt tCustAlgmnt) {
		LOGGER.info("=========== Update TCustAlgmnt ===========");
		return genericDAO.update(tCustAlgmnt);
	}

	/**
	 * Retrieve an TCustAlgmnt object based on given TCustAlgmnt custAlgmntId.
	 * 
	 * @param tCustAlgmntId
	 *            the primary key value of the TCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAlgmnt findTCustAlgmntById(final String tCustAlgmntId) {
		LOGGER.info("find TCustAlgmnt instance with custAlgmntId: " + tCustAlgmntId);
		return genericDAO.get(clazz, tCustAlgmntId);
	}

	/**
	 * Retrieve TCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAlgmnt> findTCustAlgmnts(final SearchFilter<TCustAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TCustAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAlgmntentity", tCustAlgmnt);

		if (tCustAlgmnt.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}" + tCustAlgmnt.getTCust());

			jpaQuery.bind(TCUST, tCustAlgmnt.getTCust());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAlgmnts(final SearchFilter<TCustAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TCustAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAlgmntentity", tCustAlgmnt);

		if (tCustAlgmnt.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}" + tCustAlgmnt.getTCust());

			jpaCountQuery.bind(TCUST, tCustAlgmnt.getTCust());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmnt> getTCustAlgmntsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntByTCust", tCustList, index, maxresult);
	}

	/**
	 * Count TCustAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntsByTCust", tCustList);
	}

	/**
	 * Retrieve CustomerTemplatesByAlignment
	 * 
	 * @param staffId
	 * @param boId
	 * @param tenantId
	 * 
	 * @return List of TCustAlgmntByJoinQuery
	 */
	@Override
	public List findCustomerTemplatesByAlignment(Integer staffId,
			Integer boId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(staffId);
		paramList.add(boId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAlgmntByJoinQuery", paramList,0,-1);
	}
	
	/**
	 * Retrieve searchCustomerAlgmnt
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of searchCustomerAlgmnt
	 */
	@Override
	public List<Object[]> searchCustomerAlgmnt2(String salesPosId,String salesHierId,String alignmentId,String salesTeamId,String businessUnitId) {
		final List<Object> paramList = new ArrayList<Object>();
		List<Object[]> result = null;
		paramList.add(Long.parseLong(salesPosId));
		paramList.add(Long.parseLong(salesHierId));
		paramList.add(Long.parseLong(alignmentId));
		paramList.add(Long.parseLong(salesTeamId));
		paramList.add(Long.parseLong(businessUnitId));
        
        try{
        	result = genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmnts2", paramList, 0, -1);
           }
           catch(PersistenceException e){
        	   LOGGER.error("Error while retrieving universal details");
           }
        return result;
	}
	/**
	 * Retrieve searchCustomerAlgmnt
	 * 
	
	
	 * @return List of object of searchCustomerAlgmnt
	 */
	@Override
	public List<Object[]> searchCustomerAlgmnt3() {
		List<Object[]> result = null;
		final List<Object> paramList = new ArrayList<Object>();
	     try{
	    	 result = genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmnts3", paramList, 0, -1);
	           }
	           catch(PersistenceException e){
	        	   LOGGER.error("Error while retrieving universal details");
	           }
	     return result;
	}
	/**
	 * Retrieve countOfCustAlgmnts
	 * 
	
	 * @param paramList
	
	 * @returncountL
	 */
	@Override
	public Long countOfCustAlgmnts(List paramList) {
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("CountTCustAlgmntsTenant", paramList, -1, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}
	/**
	 * Retrieve countOfCustPlannedCall
	 * 
	
	 * @param paramList
	
	 * @return countL
	 */
	@Override
	public Long countOfCustPlannedCall(List paramList) {
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustPlannedCallCount", paramList, -1, -1);
		Long countL = (Long) count.get(0) ;
		return countL;
	}

	/**
	 * Retrieve delCustAlgmnts
	 * 
	
	 * @param effEndDt
	 * @param buId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param tenantId
	 * @param custIdInt
	 * @param updatedBy
	
	 */
	@Override

	public void delCustAlgmnts(Date effEndDt,Short tenantId, Long salesPosId,
			Long salesHierId, Long alignId, Long buId, Long salesTeamId,
			Integer custIdInt,Integer updatedBy) {
		List paramList = new ArrayList();
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(updatedBy);
		paramList.add(effEndDt);
		paramList.add(tenantId);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(alignId);
		paramList.add(buId);
		paramList.add(salesTeamId);
		paramList.add(custIdInt);
			
		genericDAO.updateEntitiesByNamedQueryMultiCond("DeleteTCustAlgmntsTenant", paramList, -1, -1);
		
	}
	
	/**
	 * Retrieve an TCustAlgmnt object based on given TCustAlgmnt custAlgmntId.
	 * 
	 * @param tCustAlgmntId
	 *            the primary key value of the TCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAlgmnt findTCustAlgmntById(final Long tCustAlgmntId) {
		LOGGER.info("find TCustAlgmnt instance with custAlgmntId: " + tCustAlgmntId);
		return genericDAO.get(clazz, tCustAlgmntId);
	}
	/**
	 * Retrieve TCustAlgmnt
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForUnAssign
	 */
	@Override
	public List<TCustAlgmnt> findTCustAlgmnts(Short tenantId,
			 Long salesHierId,Long bussinessUnitId,Long salesTeamId,Long alignmentId,Long salesPosId,
			 Integer custId) {
		List list = new ArrayList();
		list.add(tenantId);
		list.add(salesPosId);
		list.add(salesHierId);
		list.add(alignmentId);
		list.add(bussinessUnitId);
		list.add(salesTeamId);
		list.add('Y');
		list.add(custId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForUnAssign", list, 0, -1);
	}
	@Override
	public List<TCustAlgmnt> findTargetedCustomerForRemoval(List queryParams){
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTargetCustomerForRemoval",queryParams,0,-1);
	}

	
	/**
	 * Find all custalignments based on cid
	 * @param cid
	 * @return
	 */
	public List<TCustAlgmnt> getAllCustAlgmntsFromCId(Integer cid, Short tenantId)
	{
		List paramList = new ArrayList();
		paramList.add(cid);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(Character.valueOf('Y'));
		
		 return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustAlgmntsFromCustId", paramList, 0, -1);
		
	}
	/**
	 * Retrieve TCustAlgmntsForUnAssignSP
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForUnAssignSP
	 */
@Override
	public List<TCustAlgmnt> findTCustAlgmntsForUnAssignSP(Long salesPosId,Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId,boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(alignmentId);
		queryParam.add(bussinessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(flag?'Y':'N');
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForUnAssignSP", queryParam, 0, -1);
	}
/**
 * Retrieve TCustAlgmntByALBUST
 * 

 * @param alignmentId
 * @param businessUnitId
 * @param salesTeamId
 * @param salesPosId
 * @param salesHierId
 * @return List of TCustAlgmntByALBUST
 */
	@Override
	public List<TCustAlgmnt> findTCustAlgmntByALBUST(Short tenantId,
			 Long bussinessUnitId,Long salesTeamId,Long alignmentId,
			 Integer custId) {
		List list = new ArrayList();
		list.add(tenantId);
		list.add(alignmentId);
		list.add(bussinessUnitId);
		list.add(salesTeamId);
		list.add('Y');
		list.add(custId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntByALBUST", list, 0, -1);
	}

	/**
	 * Retrieve CountOfTCustAlgmnt
	 * 
	 * 
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param currentDate
	 * @param localeId
	 * @return countL
	 */
	@Override
public Long fetchCountOfTCustAlgmnt(Long salesPosId, Long salesHierId,Short tenantId) {
	List paramList = new ArrayList();
	paramList.add(salesPosId);
	paramList.add(salesHierId);
	paramList.add(DateUtil.getCurrentDate());
	paramList.add('Y');
	paramList.add(tenantId);
	List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTCustAlgmnt", paramList, 0,-1);
	Long countL = (Long) count.get(0) ;
	return countL;
}
	/**
	 * Retrieve TCustAlgmntsforPopUp
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForPopUp
	 */
	public List<TCustAlgmnt> findTCustAlgmntsforPopUp(Long alignId, Long buId,Long salesTeamId,Long salesHierId,Long salesPosId,Integer custId, short tenantId){
		 List paramList = new ArrayList();
			paramList.add(alignId);
			paramList.add(buId);
			paramList.add(salesTeamId);
			paramList.add(salesHierId);
			paramList.add(salesPosId);
			paramList.add(custId);
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForPopUp", paramList, 0, -1);
	 }
	/**
	 * Retrieve CustomerDetailsForCustomerSearch
	 * 
	
	 * @param searchFilter
	 * @param priAddrFlag
	 * @param localeId_Cat
	
	 * @return object of TCustAlgmntsForCustomerSearch
	 */
	@Override
	public List<Object[]> getCustomerDetailsForCustomerSearch(
			SearchFilter<TCustAlgmnt> searchFilter,Character priAddrFlag,
			String localeId_Cat) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		 List paramList = new ArrayList();
			paramList.add(tCustAlgmnt.getAlgmntId());
			paramList.add(tCustAlgmnt.getBussUnitId());
			paramList.add(tCustAlgmnt.getSalesTeamId());
			paramList.add(tCustAlgmnt.getSalesHierId());
			paramList.add(tCustAlgmnt.getSalesPosId());
			paramList.add(tCustAlgmnt.getTenantId());
			paramList.add(tCustAlgmnt.getEffEndDt());
			paramList.add(tCustAlgmnt.getActiveFlag());
			paramList.add("%"+tCustAlgmnt.getTCust().getCustName()+"%");
			paramList.add(localeId_Cat);
			paramList.add(priAddrFlag);
		
			return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForCustomerSearch", paramList, 0, -1);
	}
	/**
	 * Retrieve getActPropCustomers
	 * 
	
	 * @param searchFilter
	
	 * @return List of ActPropCustomers
	 */
	public List<TCustAlgmnt> getActPropCustomers(SearchFilter<TCustAlgmnt> searchFilter){
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActPropCustomers", paramList, pInfo.getStartIndex(), pInfo.getMaxRows());
	}

	/**
	 * Retrieve TCustAlgmntsForUnassign
	 * 
	 * 
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param custId
	 * @param activeFlag
	 * @param tenantId
	 * @return List of TCustAlgmntsForUnAssign
	 */
	
	@Override
	public List<TCustAlgmnt> findTCustAlgmntsForUnassign(Short tenantId,
			 Long salesHierId,Long bussinessUnitId,Long salesTeamId,Long alignmentId,Long salesPosId,
			 Integer custId, Character activeFlag) {
		List list = new ArrayList();
		list.add(tenantId);
		list.add(salesPosId);
		list.add(salesHierId);
		list.add(alignmentId);
		list.add(bussinessUnitId);
		list.add(salesTeamId);
		list.add(activeFlag);
		list.add(custId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForUnAssign", list, 0, -1);
	}
	/**
	 * Retrieve CountOfTCustAlgmntandChild
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return countL
	 */
	@Override
	public Long fetchCountOfTCustAlgmntandChild(Set<Long> childsp,
			Long alignmentId, Long bussinessUnitId, Long salesTeamId,
			Short tenantId, Date currentDate, String localeId, List<Integer> crStatusList) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(childsp);
		paramList.add(alignmentId);
		paramList.add(bussinessUnitId);
		paramList.add(salesTeamId);
		paramList.add(currentDate);
		//paramList.add(localeId);
	
		List<Object> custList = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTCustAlgmntandChild", paramList, 0,-1);
		Long countL =(Long)custList.get(0);
		return countL;
	}
	/**
	 * Retrieve AssignedActPropCust
	 * 
	
	 * @param searchFilter
	 * @param localeId_Type
	 * @param priAddrFlag
	 
	 * @return List of AssignedActPropCust
	 */
	public List<Object[]> fetchAssignedActPropCust(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			Character priAddrFlag,
			Character activeAddrFlag,String localeId_Cat, List<Integer> crStatusList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddrFlag);
		paramList.add(localeId_Cat);
	
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAssignedActPropCust", paramList, pInfo.getStartIndex(),
				pInfo.getMaxRows());
	}
	/**
	 * Retrieve CustDtlsByCustAlgmntId
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of custDtlsList
	 */
	@Override
	public List<Object[]> fetchCustDtlsByCustAlgmntId(
			List<Long> custAlgmntIdList, Long salesHeirId, Long salesPosId,
			String localeId) {
		
		List paramList = new ArrayList<>();
		paramList.add(custAlgmntIdList);
		paramList.add(salesHeirId);
		paramList.add(salesPosId);
		paramList.add(localeId);
		
		List<Object[]> custDtlsList = genericDAO.findEntitiesByNamedQueryMultiCond("FindCustDtlsByCustAlgmntId", paramList, 0,-1);
		return custDtlsList;
	}
	
	/**
	 * Retrieve AssignedCustomers
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of AssignedCustomers
	 */
	public List<Object> fetchAssignedCustomers(List<Integer> custIds,Long spId,Long shId, Short tenantId) {
		
		List<Object> paramList = new ArrayList<>();
		paramList.add(custIds);
		paramList.add(spId);
		paramList.add(shId);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchAssignedCustomers", paramList, 0, -1);

	}

	/**
	 * Retrieve ActAlgmntsFromCustIdAndSP
	 * 
	 * 
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param custId
	 * @param activeFlag
	 * @param stDt
	 * @param tenantId
	 * @param endDt
	 * 
	 * @return List of object of ActAlgmntsFromCustIdAndSP
	 */
	@Override
	public List<TCustAlgmnt> fetchActAlgmntsFromCustIdAndSP(Integer custId,
			Long algtId, Long buId, Long stId, Long spId, Long shId,
			Character activeFlag, Date stDt, Date endDt, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(algtId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(spId);
		paramList.add(shId);
		paramList.add(activeFlag);
		paramList.add(stDt);
		paramList.add(endDt);
		paramList.add(tenantId);
		List<TCustAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond("FetchActAlgmntsFromCustIdAndSP", paramList, 0,-1);
		return list;
	}
	/**
	 * Retrieve ActAlgmntsFromCustIdAndST
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param custId
	 * @param activeFlag
	 * @param stDt
	 * @param tenantId
	 * @param endDt
	 * @return List of object of ActAlgmntsFromCustIdAndST
	 */
	@Override
	public List<TCustAlgmnt> fetchActAlgmntsFromCustIdAndST(Integer custId,
			Long algtId, Long buId, Long stId, 
			Character activeFlag, Date stDt, Date endDt, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(algtId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(activeFlag);
		paramList.add(stDt);
		paramList.add(endDt);
		paramList.add(tenantId);
		List<TCustAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond("FetchActAlgmntsFromCustIdAndST", paramList, 0,-1);
		return list;
	}
	/**
	 * Retrieve ActAlgmntsFromCustId
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param custId
	 * @param activeFlag
	 * @param stDt
	 * @param tenantId
	 * @param endDt
	 * @return List<TCustAlgmnt>
	 */
	@Override
	public List<TCustAlgmnt> fetchActAlgmntsFromCustId(Integer custId,
			Character activeFlag, Date stDt, Date endDt, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(activeFlag);
		paramList.add(stDt);
		paramList.add(endDt);
		paramList.add(tenantId);
		List<TCustAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond("FetchActAlgmntsFromCustId", paramList, 0,-1);
		return list;
	}
	/**
	 * Retrieve getCustCountBasedonName
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return countL
	 */
	@Override
	public Long getCustCountBasedonName(SearchFilter<TCustAlgmnt> searchFilter) {

		 final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		 List paramList = new ArrayList();
			paramList.add(tCustAlgmnt.getAlgmntId());
			paramList.add(tCustAlgmnt.getBussUnitId());
			paramList.add(tCustAlgmnt.getSalesTeamId());
			paramList.add(tCustAlgmnt.getSalesPosId());
			paramList.add(tCustAlgmnt.getSalesHierId());
			paramList.add("%"+tCustAlgmnt.getTCust().getCustName()+"%");
			paramList.add(tCustAlgmnt.getTenantId());
			paramList.add(tCustAlgmnt.getActiveFlag());
			List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("FindCountofTCustAlgmntsForCustomerSearch", paramList, 0, -1); 
			Long countL = (Long) count.get(0) ;
			return countL;
			 
	
	}
	/**
	 * Retrieve AllAssignmentsForCustomer
	 * 
	
	 * @param custId
	 * @param activeFlag
	 * @param currentDate
	 
	 * @return List of object of assignMentDetails
	 */
	public List<Object[]> getAllAssignmentsForCustomer(Integer custId, Character activeFlag, Date currentDate){
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(activeFlag);
		paramList.add(currentDate);
		List<Object[]> assignMentDetails = genericDAO.findEntitiesByNamedQueryMultiCond("FetchAllAssignmentsForCustomer",paramList, 0, -1);
		return assignMentDetails;
	}

	/**
	 * Retrieve CountOfAllAssignedActPropCust
	 * 
	 * @param crStatusList
	 * @param localeId_Cat
	 * @param activeAddrFlag
	 * @param priAddrFlag
	 * @param searchFilter
	 * @param localeId_Type
	 * @return custAlgmntIdList
	 */
	public List<Object[]> fetchCountOfAllAssignedActPropCust(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			Character priAddrFlag,
			Character activeAddrFlag,String localeId_Cat, List<Integer> crStatusList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddrFlag);
		paramList.add(localeId_Cat);
		
		
		List<Object[]> custAlgmntIdList = genericDAO.findEntitiesByNamedQueryMultiCond(
				
				"fetchCountOfAllAssignedActPropCust", paramList, 0,
				-1);
		
		return custAlgmntIdList;
	}
	/**
	 * Retrieve CustAlgDtBySalesPosEffEndDt
	 * 
	
	 * @param tenantId
	
	 * @return List of object of TCustAlgmntsByJoinQuery
	 */
	@Override
	public List<Object[]> fetchCustAlgDtBySalesPosEffEndDt(short tenantId) {

		List<Object> paramList = new ArrayList<>();
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(DateUtil.getCurrentDate());
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchTCustAlgmntsByJoinQuery", paramList, 0, -1);
	}
	/**
	 * Retrieve ToSpCheckFrGIS
	 * 
	
	 * @param searchFilter
	
	 * @return custAlgmntIdList
	 */
	@Override
	public List<TCustAlgmnt> assignedToSpCheckFrGIS(SearchFilter<TCustAlgmnt> searchFilter){
		
		
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(tCustAlgmnt.getTCust().getCustId());
		
		List<TCustAlgmnt> custAlgmntIdList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"assignedToSpCheckFrGIS", paramList, 0,
				-1);
		
		return custAlgmntIdList;
		
	}
	
	
	/**
	 * Method to fetch customers for the list of custAlgmntList
	 * @param custAlIdList
	 * @return List<TCustAlgmnt>
	 */
	@Override
	public List<TCustAlgmnt> fetchCustomersFrUnassign(List<Long> custAlIdList) {
		List paramList = new ArrayList();
		paramList.add(custAlIdList);
		paramList.add(DateUtil.getCurrentDate());
		List<TCustAlgmnt> tCustAlgmnts  = genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchCustomersFrUnassign", paramList, 0, -1);
		return tCustAlgmnts;
	}
	/**
	 * Retrieve AssignedActPropCustFrSpList
	 * 
	
	 * @param searchFilter
	 * @param localeId_Type
	 * @param priAddrFlag
	 * @param activeAddrFlag
	 * @param spIdList
	 * @return List of object of fetchAssignedActPropCustFrSpList
	 */
	public List<Object[]> fetchAssignedActPropCustFrSpList(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			Character priAddrFlag,
			Character activeAddrFlag,String localeId_Cat, Set<Long> spIdList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
	
		paramList.add(spIdList);
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddrFlag);
		paramList.add(localeId_Cat);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAssignedActPropCustFrSpList", paramList, pInfo.getStartIndex(),
				pInfo.getMaxRows());
	}

	/**
	 * Retrieve CustAssFrGIS
	 * 
	 * 
	 * @param custId
	 * @param date
	 * @param tenantId
	 * 
	 * @return List of object of CustAssFrGIS
	 */
	public List<Object[]> fetchCustAssFrGIS(Integer custId, Date date, Short tenantId){
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(date);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchCustAssFrGIS", paramList, 0, -1);
	}
	/**
	 * RetrieveAssignedActPropCustFrGIS
	 * 
	
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddr
	 * @param localeId_Type
	 * @return List of object of AssignedActPropCustFrGIS
	 */
	@Override
	public List<Object[]> fetchAssignedActPropCustFrGIS(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type,
			List<Long> shIdList, List<Long> spIdList) {

		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(shIdList);
		paramList.add(spIdList);
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddr);
		paramList.add(localeId_Cat);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAssignedActPropCustFrGIS", paramList, pInfo.getStartIndex(),
				pInfo.getMaxRows());
		
	}
	/**
	 * Retrieve TCustAlgmntActiveforPopUp
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of TCustAlgmntActiveforPopUp
	 */
	@Override
	public List<TCustAlgmnt> fetchTCustAlgmnt(Long algmntId,
			Long bussUnitId, Long salesTeamId, Long saleHierId, Long salePosId,
			Integer custId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(saleHierId);
		paramList.add(salePosId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchTCustAlgmnt", paramList, 0, -1);
	}
	/**
	 * Retrieve TCustAlgmntActiveSP
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of TCustAlgmntActiveSP
	 */
	@Override
	public List<Object[]> findTCustAlgmntActiveSP(Long algmntId,
			Long bussUnitId, Long salesTeamId, Long saleHierId, Long salePosId,
			List<Integer> custId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(saleHierId);
		paramList.add(salePosId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntActiveSP", paramList, 0, -1);
	}
	/**
	 * Retrieve AssignedActPropCustFrSrchOnTiles
	 * 
	
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddr
	 * @param localeId_Type
	 * @param crStatusList
	 * @param prtTypeIds
	 * @return customerList
	 */
	// Tiles & Grid View  - Search Customers with Address without CR
	@Override
	public List<Object[]> fetchAssignedActPropCustFrSrchOnTiles(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddr);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
	
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchAssignedActPropCustFrSrchOnTiles);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(!prtTypeIds.isEmpty()){
				
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
			
		
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
	

		
		} else{
			

			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
			for(int i=0;i<attrValList.size();i++){
					query.append(" ,t_cust_attr myTCustAttr"+i);
				}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr1);
			
				for (int j = 0; j < attrValList.size(); j++) {
					query.append(" AND myCust.cust_id =myTCustAttr" + j
							+ ".cust_id ");
				}
			}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
		
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					
					query.append(" AND myTCustAttr"+i+".attr_id='"
						
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
	if(!prtTypeIds.isEmpty()){
				
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
			
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
			
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , paginationInfo.getStartIndex(), paginationInfo.getMaxRows());
		
		return customerList;
	}
	/**
	 * Retrieve AllAssignmentsForCustIdList
	 * 
	
	 * @param custIdList
	 * @param activeFlag
	 * @param currentDate
	
	 * @return List of object of assignMentDetails
	 */
public List<Object[]> getAllAssignmentsForCustIdList(List<Integer> custIdList, Character activeFlag, Date currentDate){
		List paramList = new ArrayList();
		paramList.add(custIdList);
		paramList.add(activeFlag);
		paramList.add(currentDate);
		List<Object[]> assignMentDetails = genericDAO.findEntitiesByNamedQueryMultiCond("getAllAssignmentsForCustIdList",paramList, 0, -1);
		return assignMentDetails;
	}
/**
 * Retrieve AssignedActPropCustFrGrid
 * 

 
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddrFlag
	 * @param localeId_Type
	 * @param crStatusList
	 * @param sortBy
 * @return List of object of AssignedActPropCustFrGrid
 */
	public List<Object[]> fetchAssignedActPropCustFrGrid(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			Character priAddrFlag,
			Character activeAddrFlag,String localeId_Cat, List<Integer> crStatusList, String sortBy) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(tCustAlgmnt.getActiveFlag());
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddrFlag);
		paramList.add(localeId_Cat);
		
		StringBuffer strQuery = new StringBuffer(fetchAssignedActPropCustFrGrid);
		if(sortBy.equalsIgnoreCase("Postal Code")){
			strQuery.append(" ORDER BY myTCustAddr.postal_cd");
		}
		
		if(sortBy.equalsIgnoreCase("Name")){
			strQuery.append(" ORDER BY myCust.cust_name");
		}
		
		if(sortBy.equalsIgnoreCase("Code")){
			strQuery.append(" ORDER BY myCust.cust_cd");
		}
		
		if(sortBy.equalsIgnoreCase("Type")){
			strQuery.append(" ORDER BY myCust.cust_type_id"); 
		}
		
		if(sortBy.equalsIgnoreCase("Tier")){
			strQuery.append(" ORDER BY myTCustAlgmnt.prt_type_id");
		}
		
		if(sortBy.equalsIgnoreCase("Start Date")){
			strQuery.append(" ORDER BY myTCustAlgmnt.eff_start_dt");
		}
		
		if(sortBy.equalsIgnoreCase("End Date")){
			strQuery.append(" ORDER BY myTCustAlgmnt.eff_end_dt");
		}
		
		return genericDAO.findByNativeQueryMultiCond(
				strQuery.toString(), paramList, pInfo.getStartIndex(),
				pInfo.getMaxRows());
	}

	 /*
	  * (non-Javadoc)
	  * @see com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO#fetchActAlgmntsFromCustIds(java.util.List, long, long, long, java.lang.Character, java.lang.Short)
	  */
	@Override
	public List<Object[]> fetchActAlgmntsFromCustIds(List<Integer> custIds,
			long algmntId, long bu, long st, Character activeFlagY, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custIds);
		paramList.add(algmntId);
		paramList.add(bu);
		paramList.add(st);
		paramList.add(activeFlagY);
		paramList.add(tenantId);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("FetchActAlgmntsFromCustIds", paramList, 0,-1);
		return list;
	}
	
	@Override
	public Long findTCustAlgmntsForUnAssignSPCount(Long salesPosId,Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId,boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(alignmentId);
		queryParam.add(bussinessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(flag?'Y':'N');
		queryParam.add(tenantId);		
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntsForUnAssignSPCount", queryParam, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	public List<Object[]> findAssignedCustomer(List<Integer> listOfCustIds, Long algmntId, String bussUnitId, Long salesTeamId, Long salesPosId, Short tenentId){
		List<Object> paramList = new ArrayList<Object>();	
		
	
		paramList.add(listOfCustIds);
		paramList.add(algmntId);
		paramList.add(Long.parseLong(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(tenentId);
				
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAssignCust", paramList, 0, -1);
		
	}
	

	@Override
	public List<TCustAlgmnt> createTCustAlgmnts(List<TCustAlgmnt> tCustAlgmnts) {
		return genericDAO.storeBatch(tCustAlgmnts);
	}

	@Override
	public List<TCustAlgmnt> updateTCustAlgmnts(List<TCustAlgmnt> tCustAlgmnts) {
		return genericDAO.update(tCustAlgmnts);
	}

	@Override
	public List<TCustAlgmnt> findAllTCustAlgmntsByCutId(Integer custId,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();	
		paramList.add(custId);
		paramList.add(tenantId);
				
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAssignSPbyCustId", paramList, 0, -1);
		
	}

	@Override
	public List<Object[]> fetchCustAlignedList(Long alignId, Long bussId,
			Long salesTeamId, Long salesPosId, Long salesHierId, Short tenantId) {
		
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		
		StringBuffer strQuery = new StringBuffer(FETCHCUSTALIGNEDLIST);
		
		if(alignId!=null && alignId!=0) {
			strQuery.append(" AND c.algmnt_id = ?2");
			params.add(alignId);
			
		}
		if(bussId != null && bussId != 0) {
			strQuery.append(" AND c.buss_unit_id = ?"+params.size()+1);
			params.add(bussId);
		}
		if(salesTeamId != null && salesTeamId != 0) {
			strQuery.append(" AND c.sales_team_id = ?"+params.size()+1);
			params.add(salesTeamId);
		}
		if(salesPosId != null && salesPosId != 0) {
			strQuery.append(" AND c.sales_pos_id = ?"+params.size()+1);
			params.add(salesPosId);
		}
		if(salesHierId != null && salesHierId != 0) {
			strQuery.append(" AND c.sales_hier_id = ?"+params.size()+1);
			params.add(salesHierId);
		}
		
		return genericDAO.findEntitiesByBuildQueries(
				strQuery.toString(), params, 0, -1);
	}

	@Override
	public List<Object[]> getCustomerAlignedList(Long alignId,
			Long bussId, Long salesTeamId, Long salesPosId,
			Long salesHierId, Integer custId, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		params.add(custId);
		
		StringBuffer strQuery = new StringBuffer(FETCHCUSTALIGNEDLISTWITHCUSTID);
		
		if(alignId!=null && alignId!=0) {
			strQuery.append(" AND c.algmnt_id = ?3");
			params.add(alignId);
			
		}
		if(bussId != null && bussId != 0) {
			int index = params.size()+1 ;
			strQuery.append(" AND c.buss_unit_id = ?"+index);
			params.add(bussId);
		}
		if(salesTeamId != null && salesTeamId != 0) {
			int index = params.size()+1 ;
			strQuery.append(" AND c.sales_team_id = ?"+index);
			params.add(salesTeamId);
		}
		if(salesPosId != null && salesPosId != 0) {
			strQuery.append(" AND c.sales_pos_id = ?"+params.size()+1);
			params.add(salesPosId);
		}
		if(salesHierId != null && salesHierId != 0) {
			int index = params.size()+1 ;
			strQuery.append(" AND c.sales_hier_id = ?"+index);
			params.add(salesHierId);
		}
		
		return genericDAO.findEntitiesByBuildQueries(
				strQuery.toString(), params, 0, -1);
	}
	@Override
	public int updateTCustAlgmnt(List paramList) {
		LOGGER.info("=========== Update TCustAlgmnt For CVG Imple Falg 'Y' ===========");
			return genericDAO.updateEntitiesByNamedQueryMultiCond("UpdateTCustAlgmntsTenant", paramList, -1, -1);	
		
	}
	@Override
	public List<Object> fetchActAlgmntsALBUST(List paramList) {
		List<Object> custAlgIds = genericDAO.findEntitiesByNamedQueryMultiCond("FetchActAlgmntsALBUST", paramList, -1, -1);
		return custAlgIds;
	}
	
	@Override
	public List<Object> fetchAllAlgmntsALBUST(List paramList) {
		List<Object> custAlgIds = genericDAO.findEntitiesByNamedQueryMultiCond("FetchAllAlgmntsALBUST", paramList, -1, -1);
		return custAlgIds;
	}
	
	@Override
	public void createTempTable(String query) {
		LOGGER.info("=========== createTempTable  ==========="+query);
		genericDAO.executeNativeQuery(query);
		LOGGER.info("=========== createTempTable Completed ===========");
	}

	@Override
	public void insertOrUpdateTempTable(String query) {
		LOGGER.info("=========== insertOrUpdateTempTable  ==========="+query);
		genericDAO.executeNativeQuery(query);
		LOGGER.info("=========== insertOrUpdateTempTable Completed ===========");
	}

	@Override
	public int custAffProcedure(String query) {
		LOGGER.info("=========== custAffProcedure  ==========="+query);
		
		LOGGER.info("=========== custAffProcedure Completed ===========");
		return genericDAO.executeNativeQuery(query);
	}
	
	
	@Override
	public Integer fetchAssignedActPropCustFrSrchOnTilesCount(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		Integer count = 0;
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddr);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
	
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchAssignedActPropCustFrSrchOnTiles);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
				if(!prtTypeIds.isEmpty()){
				
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
				}
			
		
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
	

		
		} else{
			

			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
			for(int i=0;i<attrValList.size();i++){
					query.append(" ,t_cust_attr myTCustAttr"+i);
				}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr1);
			
				for (int j = 0; j < attrValList.size(); j++) {
					query.append(" AND myCust.cust_id =myTCustAttr" + j
							+ ".cust_id ");
				}
			}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
		
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					
					query.append(" AND myTCustAttr"+i+".attr_id='"
						
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
	if(!prtTypeIds.isEmpty()){
				
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
			
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
			
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , 0, -1);
		
		if(customerList != null && !customerList.isEmpty()){
			count = customerList.size();
		}
		return count;
	}

	@Override
	public void inActivateAffiliatedCustomersByCustAlgmntIds(List<Long> custAlgmntIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntIds);				
		paramList.add(tenantId);
		genericDAO.updateEntitiesByNamedQueryMultiCond("InactivateAffiliatedCustomersByCustAlgmntIds", paramList,0,-1);
	}
	
		/**
	 * Retrieve CountOfAllAssignedActPropCust
	 * 
	 * @param crStatusList
	 * @param localeId_Cat
	 * @param activeAddrFlag
	 * @param priAddrFlag
	 * @param searchFilter
	 * @param localeId_Type
	 * @return custAlgmntIdList
	 */                                           // newly added for secondary address validation	
	public Integer fetchCountOfAllAssignedActCustNoAddr(  
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			String localeId_Cat, List<Integer> crStatusList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId()); 
		paramList.add(tCustAlgmnt.getBussUnitId()); 
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId()); 
		paramList.add(tCustAlgmnt.getTenantId()); 
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(localeId_Type);
		paramList.add(localeId_Cat);	
		
		List<Object> custAlgmntIdList = genericDAO.findEntitiesByNamedQueryMultiCond(				
				"fetchCountOfAllAssignedActCustNoAddr", paramList, 0,
				-1);
		
		Integer res = custAlgmntIdList!=null && !custAlgmntIdList.isEmpty() ? Integer.parseInt(custAlgmntIdList.get(0).toString()) : 0;
		return res;
	}
	
		/**
	 * Retrieve AssignedActPropCust
	 * 
	
	 * @param searchFilter
	 * @param localeId_Type
	 * @param priAddrFlag
	 
	 * @return List of AssignedActPropCust
	 */ 	                         // newly added for secondary address validation
	public List<Object[]> fetchAssignedActPropCustWithNoAddr(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId,
			String localeId_Cat, List<Integer> crStatusList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		/*paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());*/
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
//		paramList.add(crStatusList);
		paramList.add(localeId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAssignedActPropCustWithNoAddr", paramList, pInfo.getStartIndex(),
				pInfo.getMaxRows());
	}
	
		/**
	 * Retrieve AssignedActPropCustFrSrchOnTiles
	 * 
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param localeId_Type
	 * @param crStatusList
	 * @param prtTypeIds
	 * @return customerList
	 */
	// Tiles & Grid View  - Search Customers without Address without CR
	@Override // newly added for secondary address validation	
	public List<Object[]> fetchAssignedActPropCustFrSrchOnTilesNoAddrs(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(tCustAlgmnt.getEffEndDt());
		paramList.add(localeId_Type);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchAssignedActPropCustFrSrchOnTilesNoAddrs);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
		
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
			}
			
		} else{
			query.append(fetchSrchInActivePendingCRsOnTilesExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
			for(int i=0;i<attrValList.size();i++){
					query.append(" ,t_cust_attr myTCustAttr"+i);
				}
			
			query.append(fetchSrchInActivePendingCRsOnTilesExtAttr1);
			
			for(int j=0;j<attrValList.size();j++){
				query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
			}
			}
			
			query.append(fetchSrchInActivePendingCRsOnTilesExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					
					query.append(" AND myTCustAttr"+i+".attr_id='"
						
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
			}
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
			
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , paginationInfo.getStartIndex(), paginationInfo.getMaxRows());
		return customerList;
	}
	
	@Override
	public Integer fetchCountOfInactiveCustomerWithPendingCR(  
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			String localeId_Cat, List<Integer> crStatusList) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		PaginationInfo pInfo = searchFilter.getPaginationInfo();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmnt.getAlgmntId()); 
		paramList.add(tCustAlgmnt.getBussUnitId()); 
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId()); 
		paramList.add(tCustAlgmnt.getTenantId()); 
		paramList.add(crStatusList);
		paramList.add(localeId_Type);
		paramList.add(localeId_Cat);	
		
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond(				
				"FetchCountOfInactiveCustomerWithPendingCR", paramList, 0,
				-1);
		
		Integer res = (list!=null && !list.isEmpty()) ? Integer.parseInt(list.get(0).toString()) : 0 ;
		return res;
	}
	
	@Override
	public List<Object[]> fetchInactiveCustomerWithPendingCR(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId,
			String localeId_Cat, List<Integer> crStatusList,Integer start,Integer max) {
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List paramList = new ArrayList();
	//	paramList.add(tCustAlgmnt.getAlgmntId());
	//	paramList.add(tCustAlgmnt.getBussUnitId());
	//	paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(crStatusList);
		paramList.add(localeId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchInactiveCustomerWithPendingCR", paramList, start,
				max);
	}
	
	@Override
	// Tiles & Grid View  - Search Customers with Address with CR
	public List<Object[]> fetchSrchInActivePendingCRsOnTiles(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(crStatusList);
		
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddr);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchSrchInActivePendingCRsOnTiles);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
		
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
		} else{
			query.append(fetchAssActPropCustFrSrchOnTilesExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
				for(int i=0;i<attrValList.size();i++){
						query.append(" ,t_cust_attr myTCustAttr"+i);
					}
				query.append(fetchAssActPropCustFrSrchOnTilesExtAttr1);
				for(int j=0;j<attrValList.size();j++){
					query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
				}
			}
			
			query.append(fetchAssActPropCustFrSrchOnTilesExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					query.append(" AND myTCustAttr"+i+".attr_id='"
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
			
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
		query.append(" group by myChng.cust_algmnt_id ");	
		
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , paginationInfo.getStartIndex(), paginationInfo.getMaxRows());
		return customerList;
	}
	
	// Tiles & Grid View  - Search Customers without Address with CR
	@Override
	public List<Object[]>  fetchSrchInActivePendingCRsOnTilesNoAddrs(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(crStatusList);
		paramList.add(localeId_Type);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrs);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
		
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
			}
		} else{
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
			for(int i=0;i<attrValList.size();i++){
					query.append(" ,t_cust_attr myTCustAttr"+i);
				}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr1);
			
			for(int j=0;j<attrValList.size();j++){
				query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
			}
			}
			
			query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					
					query.append(" AND myTCustAttr"+i+".attr_id='"
						
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
			}
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
		query.append(" group by myChng.cust_algmnt_id ");	
			
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , paginationInfo.getStartIndex(), paginationInfo.getMaxRows());
		return customerList;
	}	
	
	@Override
	// Tiles & Grid View  - Search Customers with Address with CR
	public Integer fetchSrchInActivePendingCRsOnTilesCount(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
		Integer count = 0;
		
		TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
		
		PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tCustAlgmnt.getAlgmntId());
		paramList.add(tCustAlgmnt.getBussUnitId());
		paramList.add(tCustAlgmnt.getSalesTeamId());
		paramList.add(tCustAlgmnt.getSalesHierId());
		paramList.add(tCustAlgmnt.getSalesPosId());
		
		paramList.add(tCustAlgmnt.getTenantId());
		paramList.add(crStatusList);
		
		paramList.add(localeId_Type);
		paramList.add(priAddrFlag);
		paramList.add(activeAddr);
		paramList.add(localeId_Cat);
		
		Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
		List<String> attrValList = new ArrayList<String>();
		List<Long> attrIdList = new ArrayList<Long>();
		if(tCustAttr != null){
			for(TCustAttr custAttr : tCustAttr){
				if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
					attrValList.add(custAttr.getAttrValue());
					attrIdList.add(custAttr.getTCustAttrId().getAttrId());
				}
			}
		}
		
		StringBuffer query = new StringBuffer();
		
		if(attrValList.size() == 0){
		
			query.append(fetchSrchInActivePendingCRsOnTiles);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
			}
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
		
		} else{
			query.append(fetchAssActPropCustFrSrchOnTilesExtAttr);
	
			if(tCustAttr!= null && tCustAttr.size()>0){
				for(int i=0;i<attrValList.size();i++){
						query.append(" ,t_cust_attr myTCustAttr"+i);
					}
				query.append(fetchAssActPropCustFrSrchOnTilesExtAttr1);
				for(int j=0;j<attrValList.size();j++){
					query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
				}
			}
			
			query.append(fetchAssActPropCustFrSrchOnTilesExtAttr2);
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
				query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
				query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
			}
			
			if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
				if(tCustAlgmnt.getImplFlag().equals('A')){
					query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
				}else if(tCustAlgmnt.getImplFlag().equals('Y')){
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
				}else{
					query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
				}
			}
			
			if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
			}
			
			if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
			}
			
			if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
			}
			
			if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
				query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
			}
			
			if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
				for(int i=0;i<attrValList.size();i++){
					query.append(" AND myTCustAttr"+i+".attr_id='"
							+attrIdList.get(i)
							+ "' AND myTCustAttr"+i+".attr_value LIKE"
							+ "'%"
							+ attrValList.get(i) + "%'");
				}
			}
			
			if(!prtTypeIds.isEmpty()){
				paramList.add(prtTypeIds);
				query.append(" AND myTCustAlgmnt.prt_type_id IN ?12");
			}
			
		} 
		if(targetFlag == true){
			query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
		}
		query.append(" group by myChng.cust_algmnt_id ");	
		
		List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , 0, -1);
		if(customerList != null && !customerList.isEmpty()){
			count = customerList.size();
		}
		return count;
	}
	
	// Tiles & Grid View  - Search Customers without Address without CR
		@Override	
		public Integer fetchAssignedActPropCustFrSrchOnTilesNoAddrsCount(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
				String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
				SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
			Integer count = 0;
			
			TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
			TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
			
			PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
			List<Object> paramList = new ArrayList<Object>();
			paramList.add(tCustAlgmnt.getAlgmntId());
			paramList.add(tCustAlgmnt.getBussUnitId());
			paramList.add(tCustAlgmnt.getSalesTeamId());
			paramList.add(tCustAlgmnt.getSalesHierId());
			paramList.add(tCustAlgmnt.getSalesPosId());
			paramList.add(tCustAlgmnt.getTenantId());
			paramList.add(tCustAlgmnt.getEffEndDt());
			paramList.add(localeId_Type);
			paramList.add(localeId_Cat);
			
			Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
			List<String> attrValList = new ArrayList<String>();
			List<Long> attrIdList = new ArrayList<Long>();
			if(tCustAttr != null){
				for(TCustAttr custAttr : tCustAttr){
					if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
						attrValList.add(custAttr.getAttrValue());
						attrIdList.add(custAttr.getTCustAttrId().getAttrId());
					}
				}
			}
			
			StringBuffer query = new StringBuffer();
			
			if(attrValList.size() == 0){
			
				query.append(fetchAssignedActPropCustFrSrchOnTilesNoAddrs);
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
					query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
					if(tCustAlgmnt.getImplFlag().equals('A')){
						query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
					}else if(tCustAlgmnt.getImplFlag().equals('Y')){
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
					}else{
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
					}
				}
				
				if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
				}
				
				if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
				}
				
				if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
				}
				
				if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
				}
				
				if(!prtTypeIds.isEmpty()){
					paramList.add(prtTypeIds);
					query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
				}
			
			} else{
				query.append(fetchSrchInActivePendingCRsOnTilesExtAttr);
		
				if(tCustAttr!= null && tCustAttr.size()>0){
				for(int i=0;i<attrValList.size();i++){
						query.append(" ,t_cust_attr myTCustAttr"+i);
					}
				
				query.append(fetchSrchInActivePendingCRsOnTilesExtAttr1);
				
				for(int j=0;j<attrValList.size();j++){
					query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
				}
				}
				
				query.append(fetchSrchInActivePendingCRsOnTilesExtAttr2);
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
					query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
					if(tCustAlgmnt.getImplFlag().equals('A')){
						query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
					}else if(tCustAlgmnt.getImplFlag().equals('Y')){
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
					}else{
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
					}
				}
				
				
				if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
				}
				
				if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
				}
				
				if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
				}
				
				if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
				}
				
				if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
					for(int i=0;i<attrValList.size();i++){
						
						query.append(" AND myTCustAttr"+i+".attr_id='"
							
								+attrIdList.get(i)
								+ "' AND myTCustAttr"+i+".attr_value LIKE"
								+ "'%"
								+ attrValList.get(i) + "%'");
					}
				}
				
				if(!prtTypeIds.isEmpty()){
					paramList.add(prtTypeIds);
					query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
				}
			} 
			if(targetFlag == true){
				query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
			}
				
			List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , 0, -1);
			if(customerList != null && !customerList.isEmpty()){
				count = customerList.size();
			}
			return count;
		}

		// Tiles & Grid View  - Search Customers without Address with CR
		@Override
		public Integer  fetchSrchInActivePendingCRsOnTilesNoAddrsCount(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
				String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
				SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag){
			Integer count = 0;
			TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
			TCustAddr tCustAddr = srchFltrAddr.getEntityClass();
			
			PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
			List<Object> paramList = new ArrayList<Object>();
			paramList.add(tCustAlgmnt.getAlgmntId());
			paramList.add(tCustAlgmnt.getBussUnitId());
			paramList.add(tCustAlgmnt.getSalesTeamId());
			paramList.add(tCustAlgmnt.getSalesHierId());
			paramList.add(tCustAlgmnt.getSalesPosId());
			paramList.add(tCustAlgmnt.getTenantId());
			paramList.add(crStatusList);
			paramList.add(localeId_Type);
			paramList.add(localeId_Cat);
			
			Set<TCustAttr> tCustAttr = tCustAlgmnt.getTCust().getTCustAttrss();
			List<String> attrValList = new ArrayList<String>();
			List<Long> attrIdList = new ArrayList<Long>();
			if(tCustAttr != null){
				for(TCustAttr custAttr : tCustAttr){
					if(custAttr.getAttrValue() != null && !custAttr.getAttrValue().equalsIgnoreCase("")){
						attrValList.add(custAttr.getAttrValue());
						attrIdList.add(custAttr.getTCustAttrId().getAttrId());
					}
				}
			}
			
			StringBuffer query = new StringBuffer();
			
			if(attrValList.size() == 0){
			
				query.append(fetchSrchInActivePendingCRsOnTilesNoAddrs);
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_cd like " +"'%" + tCustAlgmnt.getTCust().getCustCd() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
					query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
					if(tCustAlgmnt.getImplFlag().equals('A')){
						query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
					}else if(tCustAlgmnt.getImplFlag().equals('Y')){
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
					}else{
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
					}
				}
				
				if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.postal_cd like '%" + tCustAddr.getPostalCd() + "%'");
				}
				
				if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.city like '%" + tCustAddr.getCity() + "%'");
				}
				
				if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.state like '%" + tCustAddr.getState() + "%'");
				}
				
				if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.cntry like '%" + tCustAddr.getCntry() + "%'");
				}
				
				if(!prtTypeIds.isEmpty()){
					paramList.add(prtTypeIds);
					query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
				}
			
			} else{
				query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr);
		
				if(tCustAttr!= null && tCustAttr.size()>0){
				for(int i=0;i<attrValList.size();i++){
						query.append(" ,t_cust_attr myTCustAttr"+i);
					}
				
				query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr1);
				
				for(int j=0;j<attrValList.size();j++){
					query.append(" AND myCust.cust_id =myTCustAttr"+j+".cust_id ");
				}
				}
				
				query.append(fetchSrchInActivePendingCRsOnTilesNoAddrsExtAttr2);
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustName() != null && !tCustAlgmnt.getTCust().getCustName().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_name like " + "'%" + tCustAlgmnt.getTCust().getCustName() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustCd() != null && !tCustAlgmnt.getTCust().getCustCd().equalsIgnoreCase("")){
					query.append(" AND myCust.cust_cd like " + "'%"+ tCustAlgmnt.getTCust().getCustCd() + "%'");
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getTCust().getCustTypeId() != null && !tCustAlgmnt.getTCust().getCustTypeId().equals(-1)){
					query.append(" AND myCust.cust_type_id = " + tCustAlgmnt.getTCust().getCustTypeId());
				}
				
				if(tCustAlgmnt.getTCust() != null && tCustAlgmnt.getImplFlag()!= null && !tCustAlgmnt.getImplFlag().equals(-1)){
					if(tCustAlgmnt.getImplFlag().equals('A')){
						query.append(" AND myTCustAlgmnt.impl_flag = 'Y' AND myTCustAlgmnt.aff_flag = 'Y' " );
					}else if(tCustAlgmnt.getImplFlag().equals('Y')){
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" + " AND ( myTCustAlgmnt.aff_flag = 'N' OR myTCustAlgmnt.aff_flag IS NULL ) "  );
					}else{
						query.append(" AND myTCustAlgmnt.impl_flag = " + "'" + tCustAlgmnt.getImplFlag() + "'" );
					}
				}
				
				
				if(tCustAddr.getPostalCd() != null && !tCustAddr.getPostalCd().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.postal_cd LIKE '%" + tCustAddr.getPostalCd() + "%'");
				}
				
				if(tCustAddr.getCity()!= null && !tCustAddr.getCity().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.city LIKE '%" + tCustAddr.getCity() + "%'");
				}
				
				if(tCustAddr.getState() != null && !tCustAddr.getState().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.state LIKE '%" + tCustAddr.getState() + "%'");
				}
				
				if(tCustAddr.getCntry() != null && !tCustAddr.getCntry().equalsIgnoreCase("")){
					query.append(" AND myTCustAddr.cntry LIKE '%" + tCustAddr.getCntry() + "%'");
				}
				
				if(attrIdList!= null && attrValList!= null && attrValList.size()>0 && attrIdList.size()>0){
					for(int i=0;i<attrValList.size();i++){
						
						query.append(" AND myTCustAttr"+i+".attr_id='"
							
								+attrIdList.get(i)
								+ "' AND myTCustAttr"+i+".attr_value LIKE"
								+ "'%"
								+ attrValList.get(i) + "%'");
					}
				}
				
				if(!prtTypeIds.isEmpty()){
					paramList.add(prtTypeIds);
					query.append(" AND myTCustAlgmnt.prt_type_id IN ?10");
				}
			} 
			if(targetFlag == true){
				query.append(" AND myTCustAlgmnt.target_flag = 'Y'");
			}
			query.append(" group by myChng.cust_algmnt_id ");	
				
			List<Object[]> customerList = genericDAO.findByNativeQueryMultiCond(query.toString(), paramList , 0, -1);
			if(customerList != null && !customerList.isEmpty()){
				count = customerList.size();
			}
			return count;
		}

		@Override
		public void updateActiveTcustAlgmnt(Date currentdate, Short tenantId) {
			List<Object> queryParam =  new ArrayList<Object>();
			queryParam.add(currentdate);
			queryParam.add(tenantId);
			genericDAO.executeNativeQueryMultiCondition(updateActiveTcustAlgmnt, queryParam);
	
		}
	

		@Override
		public List<TCustAlgmnt> getCustomerAlignmentFrBuid(int cid,
				long buId, Short tenantId) {
			List paramList = new ArrayList();
			paramList.add(cid);
			paramList.add(buId);
			paramList.add(tenantId);
			paramList.add(DateUtil.getCurrentDate());
			paramList.add(Character.valueOf('Y'));
			
			 return genericDAO.findEntitiesByNamedQueryMultiCond("getCustomerAlignmentFrCustIdWithBuId", paramList, 0, -1);
			
		}

		@Override
		public List<TCustAlgmnt> findCustAlgmntsBySerachCriteria(
				ISearchCriteria criteria) {
			//return genericDAO.search(clazz, criteria);
			
            EntityManager em = genericDAO.getEntityManagerFromJPA();
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            CriteriaQuery<TCustAlgmnt> criteriaQuery = criteriaBuilder.createQuery(TCustAlgmnt.class);
            Root<TCustAlgmnt> custAlignment = criteriaQuery.from(TCustAlgmnt.class);
            Join<TCustAlgmnt,TCust> cust = custAlignment.join("tCust");
            Join<TCust,TCustAddr> addrs = cust.join("tCustAddrss"); 
            
            Fetch fCust = custAlignment.fetch("tCust");
            fCust.fetch("tCustAddrss");
            
            List<Long> spIdList = null;
            List<Long> shIdList = null;
            
            SearchCriteria searchCriteria = (SearchCriteria) criteria;
            String attribute = searchCriteria.getCriteriaObject().getAttribute();
            if(attribute.equals(CommonConstants.SALES_POS_ID)){
            	spIdList = (List<Long>) searchCriteria.getCriteriaObject().getValue();
            }
            else if(attribute.equals(CommonConstants.SALES_HIER_ID)){
            	shIdList = (List<Long>) searchCriteria.getCriteriaObject().getValue();
            }
            for(ISearchCriteria subCriteria : searchCriteria.getOperant()){
            	SearchCriteria search = (SearchCriteria) subCriteria;
                String subAttribute = search.getCriteriaObject().getAttribute();
                if(subAttribute.equals(CommonConstants.SALES_POS_ID)){
                	spIdList = (List<Long>) search.getCriteriaObject().getValue();
                }
                else if(subAttribute.equals(CommonConstants.SALES_HIER_ID)){
                	shIdList = (List<Long>) search.getCriteriaObject().getValue();
                }
            }
            
            Predicate spIds = custAlignment.<Long>get("salesPosId").in(spIdList);
            Predicate shIds = custAlignment.<Long>get("salesHierId").in(shIdList);
            
      Predicate targetFlag = criteriaBuilder.equal(custAlignment.<String>get("targetFlag"),'Y');
      Predicate activeFlag = criteriaBuilder.equal(custAlignment.<String>get("activeFlag"),'Y');
      
      Predicate custActiveFlag = criteriaBuilder.equal(cust.<String>get("activeFlag"),'Y');
      Predicate pPrimaryAddress = criteriaBuilder.equal(addrs.<String>get("prAddrFlag"),'Y');
      
      
      
      criteriaQuery.where(criteriaBuilder.and(spIds,shIds,targetFlag,activeFlag,custActiveFlag,pPrimaryAddress)); //Step 5
      criteriaQuery.select(custAlignment);

      
            TypedQuery<TCustAlgmnt> typedQuery = em.createQuery(criteriaQuery);
            return typedQuery.getResultList();

		}

	@Override
	public List<Object[]> getCountGeoAlgdCust(Long spId,Long shId, List<String> postalCode, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(shId);
		queryParam.add(postalCode);
		queryParam.add(tenantId);
		List<Object[]> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"getCountGeoAlgdCust", queryParam, 0, -1);
		return count;
	}

	@Override
	public List<Object[]> getCountCompElgCust(Long spId,Long shId, List<String> postalCode, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(shId);
		queryParam.add(postalCode);
		queryParam.add(tenantId);
		List<Object[]> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"getCountCompElgCust", queryParam, 0, -1);
		return count;
	}

	@Override
	public List<TCustAlgmnt> findAllTCustAlgmntsByIDS(Long custAlgmntIds,
			Short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(custAlgmntIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTCustAlgmntsByIDS", paramList, 0, -1);
	}

	
	@Override
	public void updateTargetFlagOfCust(Long custAlgmntId, Integer userId, Short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(userId);
		paramList.add(custAlgmntId);
		paramList.add(tenantId);
		
	   genericDAO.updateEntitiesNamedQuery("updateTargetFlagOfCust", paramList);
	}

	@Override
	public List<TCustAlgmnt> findGeoCustAlignmentIdBySalesPostion(Long spId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findGeoCustAlignmentIdBySalesPostion", queryParam, 0, -1);
	}

	/**
	 * Find geo cust alignment id by sales postion.
	 *
	 * @param spId the sp id
	 * @param shId the sh id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	@Override
	public List<TCustAlgmnt> findNonGeoCustAlignmentIdBySalesPostion(Long spId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findNonGeoCustAlignmentIdBySalesPostion", queryParam, 0, -1);
	}

	/**
	 * Find geo cust alignment id by sales postion.
	 *
	 * @param spId the sp id
	 * @param shId the sh id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	@Override
	public Long getCountOfGeoAlgdCustFrSp(Long spId, Long shId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(shId);
		queryParam.add(tenantId);
		queryParam.add(DateUtil.getCurrentDate());
		queryParam.add('Y');
		List<Object> geoCusCount = genericDAO.findEntitiesByNamedQueryMultiCond("getCountGeoAndNonGeoAlgdCustFrSp", queryParam, 0, -1);
		Long  geoCustCount = (Long) geoCusCount.get(0);
		return geoCustCount;
	}

	/**
	 * Find non geo cust alignment id by sales postion.
	 *
	 * @param spId the sp id
	 * @param shId the sh id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	@Override
	public Long getCountOfNonGeoAlgdCustFrSp(Long spId, Long shId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(spId);
		queryParam.add(shId);
		queryParam.add(tenantId);
		queryParam.add(DateUtil.getCurrentDate());
		queryParam.add('N');
		List<Object> geoCusCount = genericDAO.findEntitiesByNamedQueryMultiCond("getCountGeoAndNonGeoAlgdCustFrSp", queryParam, 0, -1);
		Long  geoCustCount = (Long) geoCusCount.get(0);
		return geoCustCount;
	}
	
	@Override
	public void updateEffEndDateFrCust(Date effEndDt, Character flagToSet, Long custAlgmntId,  Integer userId,
			Short tenantId) {		
		List paramList = new ArrayList();
		paramList.add(effEndDt);
		paramList.add(flagToSet);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(custAlgmntId);
		paramList.add(userId);
		paramList.add(tenantId);
		
	   genericDAO.updateEntitiesNamedQuery("updateEffEndDateFrCust", paramList);
	}

	/**
	 * Find custAlgmntId from custid and sales postion.
	 *
	 * @param custId the customer id
	 * @param spId the salesposition id
	 * @return the long
	 */
	@Override
	public Long fetchCustAlgmntIdfromCustIdAndSPId(Integer custId, Long SPId) {
		// TODO Auto-generated method stub
		
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(SPId);
		return (Long) genericDAO.findEntitiesByNamedQueryMultiCond("fetchCustAlgmntIdfromCustIdAndSPId",paramList,0,-1).get(0);
	}


	/**
	 * Count of cust algmnt by status.
	 *
	 * @param custId the cust id
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param userDetails the user details
	 * @return the long
	 */
	@Override
	public Long countOfCustAlgmntByStatus(Integer custId, Long alId, Long buId,
			Long stId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custId);
		paramList.add(alId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(tenantId);
		List<Long> count = genericDAO.findEntitiesByNamedQueryMultiCond("countTCustAlgmntStatus",paramList,0,-1);
		return count.get(0);
	}

	@Override
	public Long findCountOfTCustAlgmntForSpList(List<Long> spIds, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spIds);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		return (Long) genericDAO.findEntitiesByNamedQueryMultiCond("FindCountOfTCustAlgmntForSpList", paramList, 0, -1).get(0);
	}

	@Override
	public Long getGeoAlgdCustomerCountForSalesPositions(List<Long> spIds, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spIds);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add('Y');
		return (Long) genericDAO.findEntitiesByNamedQueryMultiCond("getCountGeoAndNonGeoAlgdCustFrSpList", paramList, 0, -1).get(0);
	}

	@Override
	public Long getCountOfNonGeoAlgdCustFrSpList(List<Long> spIds, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(spIds);
		paramList.add(tenantId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add('N');
		return (Long) genericDAO.findEntitiesByNamedQueryMultiCond("getCountGeoAndNonGeoAlgdCustFrSpList", paramList, 0, -1).get(0);
	}
		
	@Override
	public List<TCustAlgmnt> findTCustAlgmntFrCustIdList(Short tenantId,
			Long salesHierId, Long bussinessUnitId, Long salesTeamId,
			Long alignmentId, Long salesPosId, List<Integer> custId) {
		
		List list = new ArrayList();
		list.add(alignmentId);
		list.add(bussinessUnitId);
		list.add(salesTeamId);
		list.add(salesPosId);
		list.add(salesHierId);
		list.add(custId);
		list.add(DateUtil.getCurrentDate());
		list.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntFrCustIdList", list, 0, -1);
	}
	/**
	 * @param custId
	 * @param spIdList
	 * @param tenantId
	 * @return
	 */
	@Override
	public List<TSalesPos> getAssignedCustomerFrSp(Long custId,List<Long> spIdList, short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custId.intValue());
		paramList.add(spIdList);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("FindAssignedCustFrSp", paramList, 0, -1);
	}

	@Override
	public int updateTCustAlgmntToLock(Long CustAlgmntId,Integer statusId, Integer userId) {
		List paramList = new ArrayList();
		paramList.add(statusId);
		paramList.add(userId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(CustAlgmntId);
		return  genericDAO.updateEntitiesByNamedQueryMultiCond("updateTCustAlgmntToLock", paramList, 0, -1);
	}
	
	@Override
	public int updateTCustAlgmntToUnLock(List<Long> CustAlgmntIds,
			Integer statusId, Integer userId) {
		List paramList = new ArrayList();
		paramList.add(statusId);
		paramList.add(userId);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(CustAlgmntIds);
		return  genericDAO.updateEntitiesByNamedQueryMultiCond("updateTCustAlgmntToUnLock", paramList, 0, -1);
	}
	
		
	
}