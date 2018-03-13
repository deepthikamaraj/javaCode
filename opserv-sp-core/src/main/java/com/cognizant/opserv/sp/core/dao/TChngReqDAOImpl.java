package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqTrack;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqDAO")
public class TChngReqDAOImpl implements TChngReqDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqDAOImpl.class);
	private final String fetchAllMyChngRequest = "select  distinct tcr.chng_req_id, tcr.create_dt, tcr.update_dt, tcr.submitted_by,tf.feature_name,tft.type_desc,tws.status_name,tcr.trigger_id,tpers.first_name,tpers.middle_name,tpers.last_name,tsp.pos_cd,tcr.submit_dt_tm,ires.eff_end_dt,(SELECT COUNT(DISTINCT(tcra.chng_req_detail_id)) FROM t_chng_req_appr tcra WHERE tcra.chng_req_id = tcr.chng_req_id and tcra.target_appr_flag='Y' and tcra.tenant_id=tcr.tenant_id)trans,tf.feature_desc,tcr.chng_req_num,(SELECT COUNT(DISTINCT(tcra2.appr_sales_pos_id)) FROM t_chng_req_appr tcra2 WHERE tcra2.chng_req_id = tcr.chng_req_id and tcra2.tenant_id=tcr.tenant_id and tcra2.status_id != ?5 )sts  from t_chng_req tcr INNER JOIN t_wkflw_status tws ON (tcr.req_sales_pos_id IN ?1 and tcr.status_id = tws.status_id and tcr.tenant_id=tws.tenant_id and tws.locale_id=?4) INNER JOIN t_chng_req_trigger tcrt ON (tcr.trigger_id = tcrt.trigger_id and tcrt.tenant_id =tcr.tenant_id) INNER JOIN t_chng_req_feature tf ON (tf.feature_id = tcrt.feature_id  ) INNER JOIN t_feature_type tft ON (tcrt.type_id = tft.type_id and tft.tenant_id=tcr.tenant_id) LEFT JOIN t_sales_pos tsp ON (tsp.sales_hier_id=tcr.last_sales_hier_id and tsp.sales_pos_id=tcr.last_sales_pos_id and tsp.tenant_id=tcr.tenant_id) LEFT JOIN t_pers tpers ON (tpers.usr_id=tcr.updated_by and tpers.tenant_id=tcr.tenant_id) INNER JOIN "
			+ "(select isp.eff_end_dt,icr.chng_req_id,icr.tenant_id from t_chng_req icr INNER JOIN t_sales_pos isp ON (icr.req_sales_pos_id IN ?1 and icr.req_sales_pos_id=isp.sales_pos_id and icr.req_sales_hier_id=isp.sales_hier_id and icr.tenant_id=isp.tenant_id)) ires ON (ires.chng_req_id = tcr.chng_req_id and tcr.tenant_id=ires.tenant_id) where tcr.status_id in ?2  and tcr.tenant_id=?3 and tcr.active_flag ='Y' ORDER BY tcr.update_dt DESC ";
	private final String fetchAffDetail = "SELECT res.chng_req_id ,res.cust_cd, group_concat(res.cust_aff_id)as custAffList, group_concat(res.chng_req_detail_id)as detailList, res.cust_name,res.submit_dt_tm,res.buss_reason,res.comments,res.trigger_id,res.cust_algmnt_id,res.target_flag,res.pos_name,res.chng_req_num,res.cust_first_name,res.cust_middle_name,res.cust_last_name "
			+ " FROM (SELECT t_cust.cust_name,t_cust.cust_cd,t_cust.cust_id,t_cust_aff.cust_aff_id,t_chng_req.submit_dt_tm,t_chng_req.buss_reason,t_chng_req.comments,t_chng_req.trigger_id,t_chng_req.chng_req_id,t_cust_aff_chng_req_det.chng_req_detail_id,t_cust_algmnt.target_flag,t_cust_aff_chng_req_det.cust_algmnt_id,t_sales_pos.pos_name,t_chng_req.chng_req_num,t_cust.cust_first_name,t_cust.cust_middle_name,t_cust.cust_last_name FROM t_cust_aff_chng_req_det, t_cust_aff, t_cust,t_chng_req,t_cust_algmnt,t_sales_pos WHERE t_cust_aff_chng_req_det.chng_req_id = ?1 "
			+ "AND t_chng_req.req_sales_pos_id= t_sales_pos.sales_pos_id AND t_cust_aff.cust_aff_id = t_cust_aff_chng_req_det.cust_aff_id AND t_cust.cust_id = t_cust_aff.cust_id AND t_chng_req.chng_req_id=t_cust_aff_chng_req_det.chng_req_id AND t_cust_algmnt.cust_algmnt_id=t_cust_aff_chng_req_det.cust_algmnt_id AND t_cust_algmnt.tenant_id=t_cust.tenant_id AND t_cust.tenant_id = ?2) res GROUP BY res.cust_id ";
	private final String getCustCallPlanDet = "  SELECT CR.chng_req_id, CR_DET.chng_req_detail_id, CUST.cust_cd,CUST.cust_name,IF(CUST1.cust_cd IS NULL, 'NA', CUST1.cust_cd) cust_cd1, CUST_CP.cust_call_plan_id,CR.chng_req_num,CUST.cust_first_name,CUST.cust_middle_name,CUST.cust_last_name FROM t_chng_req CR INNER JOIN t_chng_req_detail CR_DET ON (CR.chng_req_id = CR_DET.chng_req_id AND CR.chng_req_id = ?1 "
			+ "and CR.tenant_id = ?2) INNER JOIN t_cust_call_plan_chng_req_det CUST_CP_DET ON (CUST_CP_DET.chng_req_id = CR_DET.chng_req_id AND CUST_CP_DET.chng_req_detail_id = CR_DET.chng_req_detail_id and CR_DET.tenant_id = ?2) INNER JOIN t_cust_call_plan CUST_CP ON (CUST_CP_DET.cust_call_plan_id = CUST_CP.cust_call_plan_id and CUST_CP.tenant_id =?2) INNER JOIN t_cust CUST ON (CUST_CP.cust_id = CUST.cust_id and CUST_CP.tenant_id = ?2) LEFT OUTER JOIN t_cust_aff CUST_AFF  ON (CUST_CP.cust_aff_id = CUST_AFF.cust_aff_id and CUST_AFF.tenant_id = ?2) LEFT OUTER JOIN t_cust CUST1 ON (CUST1.cust_id = CUST_AFF.aff_cust_id and CUST1.tenant_id =?2)";
	private final String GetTCallPlnDetail = "SELECT DISTINCT (CR_DET.chng_req_detail_id ),CR.chng_req_id ,CUST_CP.cust_call_plan_id , CUST.cust_cd ,SALES_POS.pos_name ,CR.submit_dt_tm ,CR.buss_reason ,CR.comments ,CUST.cust_name ,CR.trigger_id ,CUST_AlG.target_flag,IF(CUST1.cust_name IS NULL, 'NA', CUST1.cust_name) cust_name1,IF(CUST1.cust_cd IS NULL, 'NA', CUST1.cust_cd) cust_cd1,CR.chng_req_num,CUST.cust_first_name,CUST.cust_middle_name,CUST.cust_last_name,IF(CUST1.cust_first_name IS NULL, 'NA', CUST1.cust_first_name) cust_fname1,IF(CUST1.cust_middle_name IS NULL, 'NA', CUST1.cust_middle_name) cust_mname1,IF(CUST1.cust_last_name IS NULL, 'NA', CUST1.cust_last_name) cust_lname1 FROM t_chng_req CR "
			+ "INNER JOIN t_chng_req_detail CR_DET ON (CR.chng_req_id = CR_DET.chng_req_id AND CR.chng_req_id = ?1 and CR.tenant_id = ?2) INNER JOIN t_cust_call_plan_chng_req_det CUST_CP_DET ON (CUST_CP_DET.chng_req_id = CR_DET.chng_req_id AND CUST_CP_DET.chng_req_detail_id = CR_DET.chng_req_detail_id and CR_DET.tenant_id = CR.tenant_id) INNER JOIN t_cust_call_plan CUST_CP ON (CUST_CP_DET.cust_call_plan_id = CUST_CP.cust_call_plan_id and CUST_CP.tenant_id =CR.tenant_id) INNER JOIN t_cust_algmnt CUST_AlG ON(CUST_AlG.cust_algmnt_id = CUST_CP.cust_algmnt_id and CUST_AlG.tenant_id = CUST_CP.tenant_id) INNER JOIN t_sales_pos SALES_POS ON (SALES_POS.sales_pos_id = CR.req_sales_pos_id AND SALES_POS.sales_hier_id = CR.req_sales_hier_id and SALES_POS.tenant_id=CR.tenant_id )INNER JOIN t_cust CUST ON (CUST.cust_id = CUST_CP.cust_id and CUST.tenant_id=CR.tenant_id ) LEFT OUTER JOIN t_cust_aff CUST_AFF  ON (CUST_CP.cust_aff_id = CUST_AFF.cust_aff_id and CUST_AFF.tenant_id = CR.tenant_id) LEFT OUTER JOIN t_cust CUST1 ON (CUST1.cust_id = CUST_AFF.aff_cust_id and CUST1.tenant_id =CR.tenant_id)";
	private final String FindApprCRBySalesPosID = "SELECT DISTINCT tcr.chng_req_id,tcr.create_dt, tcr.updated_by,tcr.update_dt,tf.feature_name,tft.type_desc, tws.status_name,tpers.first_name,tpers.middle_name,tpers.last_name,tsp.pos_cd,tcr.submit_dt_tm,tf.feature_desc,(SELECT COUNT(DISTINCT myTChngReqAppr.chng_req_detail_id)FROM t_chng_req_appr myTChngReqAppr WHERE  myTChngReqAppr.chng_req_id =tcr.chng_req_id AND myTChngReqAppr.status_id IN ?3  AND myTChngReqAppr.appr_sales_pos_id= ?1  and myTChngReqAppr.appr_sales_hier_id= ?2 "
			+ "and myTChngReqAppr.tenant_id = ?4  and  myTChngReqAppr.active_flag='Y' GROUP BY myTChngReqAppr.target_appr_flag  ORDER BY myTChngReqAppr.update_dt DESC LIMIT 1 )as transCount,tcr.chng_req_num   from  t_chng_req tcr INNER JOIN t_chng_req_appr tcra ON (tcra.chng_req_id=tcr.chng_req_id and tcra.active_flag='Y' ) INNER JOIN t_chng_req_trigger tcrt ON (tcr.trigger_id=tcrt.trigger_id)  INNER JOIN t_chng_req_feature tf ON (tcrt.feature_id=tf.feature_id) INNER JOIN t_feature_type tft ON (tcrt.type_id=tft.type_id) INNER JOIN t_wkflw_status tws  ON (tcra.status_id=tws.status_id and tws.tenant_id=tcr.tenant_id and  tws.locale_id=?5)  LEFT OUTER JOIN t_pers tpers ON (tpers.usr_id=tcr.updated_by) LEFT JOIN t_sales_pos tsp ON (tsp.sales_hier_id=tcr.last_sales_hier_id and tsp.sales_pos_id=tcr.last_sales_pos_id ) where tcra.appr_sales_pos_id=?1 and  tcra.appr_sales_hier_id=?2 and tcra.status_id in ?3 and  tcr.tenant_id=?4 and tcr.active_flag ='Y' ORDER BY tcr.update_dt DESC , tcr.chng_req_id ";
    private final String spDetail="select (tsp.pos_cd) as a ,(tsp1.pos_cd) as b,tcr.chng_req_id FROM t_chng_req tcr JOIN t_sales_pos tsp ON tcr.req_sales_pos_id=tsp.sales_pos_id and tsp.active_flag = tcr.active_flag and tsp.tenant_id = tcr.tenant_id LEFT JOIN t_sales_pos tsp1 ON tcr.last_sales_pos_id=tsp1.sales_pos_id and tsp1.active_flag = tcr.active_flag and tsp1.tenant_id = tcr.tenant_id " +
		                              " WHERE tcr.chng_req_id IN ?1 and tcr.active_flag = 'Y' and tcr.tenant_id = ?2 ";
	
    private final String getTriggerId="SELECT res.feature_name, group_concat(res.trigger_id)as triggerIdList FROM (select tcrf.feature_name,tcr.trigger_id,tcrf.feature_id from t_chng_req_feature tcrf,t_chng_req_trigger tcr where tcr.feature_id=tcrf.feature_id  and tcr.active_flag = tcrf.active_flag and tcr.active_flag='Y' and tcr.trigger_id=?1) res GROUP BY res.feature_id";
    private final String fetchAllPendingCRS=" SELECT DISTINCT(tcr.chng_req_id),tws.status_name ,concat(tft.type_desc , ' ', tcrf.feature_name ),tcrt.trigger_id FROM t_chng_req tcr INNER JOIN t_chng_req_config tc ON tcr.chng_req_config_id=tc.chng_req_config_id and tc.algmnt_id=?1 and tc.buss_unit_id = ?2 and tc.sales_team_id=?3 and tcr.active_flag=tc.active_flag INNER JOIN t_wkflw_status tws ON " + " tws.status_id=tcr.status_id and tws.status_name IN ('pendingApproval','pendingSubmission') and tws.active_flag = tcr.active_flag INNER JOIN t_chng_req_trigger tcrt ON  tcrt.active_flag = tcr.active_flag and tcrt.trigger_id = tcr.trigger_id INNER JOIN t_chng_req_feature tcrf ON tcrf.feature_id = tcrt.feature_id and  tcrf.active_flag = tcr.active_flag INNER JOIN t_feature_type tft ON tft.type_id = tcrt.type_id  and tft.active_flag = tcr.active_flag where  tcr.active_flag='Y' and tcr.tenant_id=?4 ";
    @Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	} 

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQCONFIG = "tChngReqConfig";

	private final Class<TChngReq> clazz;

	public Class<TChngReq> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqDAOImpl() {
		super();
		this.clazz = TChngReq.class;
	}

	private static final String JPAQL = "select tChngReqentity from TChngReq tChngReqentity";

	private static final String JPACOUNTQL = "select count(tChngReqentity) from TChngReq tChngReqentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqentity.chngReqId = #{tChngReqentity.getChngReqId}",
			"tChngReqentity.raiseSalesPosId = #{tChngReqentity.getRaiseSalesPosId}",
			"tChngReqentity.statusId = #{tChngReqentity.getStatusId}",
			"Date(tChngReqentity.submitDtTm) = '#{tChngReqentity.getSubmitDtTm}'",
			"tChngReqentity.reqSalesPosId = #{tChngReqentity.getReqSalesPosId}",
			"tChngReqentity.reqSalesHierId = #{tChngReqentity.getReqSalesHierId}",
			"tChngReqentity.raiseSalesHierId = #{tChngReqentity.getRaiseSalesHierId}",
			"tChngReqentity.submittedBy = #{tChngReqentity.getSubmittedBy}",
			"tChngReqentity.activeFlag = #{tChngReqentity.getActiveFlag}",
			"tChngReqentity.createdBy = #{tChngReqentity.getCreatedBy}",
			"Date(tChngReqentity.createDt) = '#{tChngReqentity.getCreateDt}'",
			"tChngReqentity.updatedBy = #{tChngReqentity.getUpdatedBy}",
			"Date(tChngReqentity.updateDt) = '#{tChngReqentity.getUpdateDt}'",
			"tChngReqentity.tenantId = #{tChngReqentity.getTenantId}",
			"tChngReqentity.actionBy = #{tChngReqentity.getActionBy}",
			"Date(tChngReqentity.actionDtTm) = '#{tChngReqentity.getActionDtTm}'",
			"tChngReqentity.bussReason like '#{tChngReqentity.getBussReason}%'",
			"tChngReqentity.triggerId = #{tChngReqentity.getTriggerId}",
			"tChngReqentity.comments like '#{tChngReqentity.getComments}%'",
			"tChngReqentity.tChngReqConfig.chngReqConfigId = #{tChngReqentity.tChngReqConfig.getChngReqConfigId}" ,
			"tChngReqentity.prnChngReq.chngReqId = #{tChngReqentity.prnChngReq.chngReqId}",
			"tChngReqentity.tChngReqTrack.chngReqTrackId = #{tChngReqentity.tChngReqTrack.getChngReqTrackId}" };

	/**
	 * Stores a new TChngReq entity object in to the persistent store
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be persisted
	 * @return tChngReq Persisted TChngReq object
	 */
	public TChngReq createTChngReq(final TChngReq tChngReq) {
		LOGGER.info("=========== Create TChngReq ===========");
		return genericDAO.store(tChngReq);
	}

	/**
	 * Deletes a TChngReq entity object from the persistent store
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be deleted
	 */
	public void deleteTChngReq(final Long chngReqId) {
		LOGGER.info("=========== Delete TChngReq ===========");
		final TChngReq tChngReq = genericDAO.get(clazz, chngReqId);
		genericDAO.remove(tChngReq);
	}

	/**
	 * Updates a TChngReq entity object in to the persistent store
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be updated
	 * @return tChngReq Persisted TChngReq object
	 */
	public TChngReq updateTChngReq(final TChngReq tChngReq) {
		LOGGER.info("=========== Update TChngReq ===========");
		return genericDAO.update(tChngReq);
	}

	/**
	 * Retrieve an TChngReq object based on given TChngReq chngReqId.
	 * 
	 * @param tChngReqId
	 *            the primary key value of the TChngReq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReq findTChngReqById(final Long tChngReqId) {
		LOGGER.info("find TChngReq instance with chngReqId: " + tChngReqId);
		return genericDAO.get(clazz, tChngReqId);
	}

	/**
	 * Retrieve TChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReq if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReq> findTChngReqs(
			final SearchFilter<TChngReq> searchFilter) {
		LOGGER.info("=========== Find TChngReqs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReq tChngReq = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqentity", tChngReq);

		if (tChngReq.getTChngReqConfig() == null) {
			jpaQuery.bind(TCHNGREQCONFIG, new TChngReqConfig());
		} else {
			LOGGER.info("tChngReqConfig {}" + tChngReq.getTChngReqConfig());

			jpaQuery.bind(TCHNGREQCONFIG, tChngReq.getTChngReqConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqs(final SearchFilter<TChngReq> searchFilter) {
		LOGGER.info("=========== Count TChngReq ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReq tChngReq = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqentity", tChngReq);

		if (tChngReq.getTChngReqConfig() == null) {
			jpaCountQuery.bind(TCHNGREQCONFIG, new TChngReqConfig());
		} else {
			LOGGER.info("tChngReqConfig {}" + tChngReq.getTChngReqConfig());

			jpaCountQuery.bind(TCHNGREQCONFIG, tChngReq.getTChngReqConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReq> getTChngReqsByTChngReqConfig(
			final SearchFilter<TChngReqConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqConfig tChngReqConfig = searchFilter.getEntityClass();
		List<Object> tChngReqConfigList = new ArrayList<Object>();
		tChngReqConfigList.add(tChngReqConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqByTChngReqConfig", tChngReqConfigList, index,
				maxresult);
	}

	/**
	 * Count TChngReq based on given search criteria using JPA named Query. The
	 * search criteria is of TChngReqConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqsByTChngReqConfig(
			final SearchFilter<TChngReqConfig> searchFilter) {

		final TChngReqConfig tChngReqConfig = searchFilter.getEntityClass();
		List<Object> tChngReqConfigList = new ArrayList<Object>();
		tChngReqConfigList.add(tChngReqConfig);
		return genericDAO.findEntitiesByNamedQuery(
				"CountTChngReqsByTChngReqConfig", tChngReqConfigList);
	}

	public List<TChngReq> getChangeRequestByStatus(List<Object> params) {

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"getChangeRequestByStatus", params, 0, -1);

	}
	/**
	 * Gets the change request by status.
	 *
	 * @param raiseSalesPostion the raise sales postion
	 * @param raiseSalesHierarchyId the raise sales hierarchy id
	 * @param status the status
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the change request by status
	 */
	public List<TChngReq> getChangeRequestByStatus(Long reqSalesPostion, Long reqSalesHierarchyId, Long raiseSalesPostion, Long raiseSalesHierarchyId,Integer status, Integer triggerId, Short tenantId) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(reqSalesPostion);
		queryParam.add(reqSalesHierarchyId);
		queryParam.add(status);
		queryParam.add(triggerId);
		queryParam.add(tenantId);
		queryParam.add(raiseSalesPostion);
		queryParam.add(raiseSalesHierarchyId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getChangeRequestByStatus", queryParam, 0, -1);

	}
	
	
	/**
	 * Gets the change request by status.
	 *
	 * @param raiseSalesPostion the raise sales postion
	 * @param raiseSalesHierarchyId the raise sales hierarchy id
	 * @param status the status
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the change request by status
	 */
	public List<Long> getChangeRequestIdByStatus(Long reqSalesPostion, Long reqSalesHierarchyId,Long raiseSalesPostion, Long raiseSalesHierarchyId, Integer status, Integer triggerId, Short tenantId) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(reqSalesPostion);
		queryParam.add(reqSalesHierarchyId);
		queryParam.add(status);
		queryParam.add(triggerId);
		queryParam.add(tenantId);
		queryParam.add(raiseSalesPostion);
		queryParam.add(raiseSalesHierarchyId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getChangeRequestIdByStatus", queryParam, 0, -1);

	}

	@Override
	public List<Object[]> getTCustAlgnDetails(long crId, Short tenantId,
			String localeId) {
//		commenting below line because TChngReqDetail is removed -- syam
//		List<Object> queryParam = new ArrayList();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		queryParam.add(localeId);
//		return genericDAO.findByNativeQueryMultiCond(getCustDet, queryParam, 0,
//				-1);
		return null;
	}
	/**
	 * Find all my chng request.
	 *
	 * @param spList the sp list
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllMyChngRequest(List<Long> salesPositionIDS) {

		final List<Object> queryParam = new ArrayList<Object>(salesPositionIDS);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAllMyChngRequest", queryParam, 0, -1);
	}
	/**
	 * Gets the t prd details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t prd details
	 */
	@Override
	public List<Object[]> getTPrdDetails(long crId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdDetail",
				queryParam, 0, -1);
	}
	/**
	 * Fetch transction details.
	 *
	 * @param dataList the data list
	 * @param query the query
	 * @return the list
	 */
	@Override
	public List<Object> fetchTransctionDetails(List<Object> dataList,
			String query) {
		return genericDAO.findEntitiesByNamedQueryMultiCond(query, dataList, 0,
				-1);
	}
	/**
	 * Gets the t cust affltn details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t cust affltn details
	 */
	@Override
	public List<Object[]> getTCustAffltnDetails(long crId, short tenantId) {
//		commenting below line because TChngReqDetail is removed -- syam
		
//		List queryParam = new ArrayList();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAffltn",
//				queryParam, 0, -1);
		return null;
	}
	/**
	 * Gets the t sales pos details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t sales pos details
	 */
	public List<Object[]> getTSalesPosDetails(long crId, short tenantId) {
//		commenting below line because TChngReqDetail is removed -- syam
//		List queryParam = new ArrayList();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTSalesPostn",
//				queryParam, 0, -1);
		return null;
	}
	/**
	 * Find all my chng request.
	 *
	 * @param spList the sp list
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findAllMyChngRequest(Long salesPositionIDS,
			short tenantID, String localeId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionIDS);
		queryParam.add(tenantID);
		queryParam.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchAllMyChngRequest", queryParam, 0, -1);
	}

	/**
	 * Added by 409793 for fetching "REQUEST FOR MY APPROVAL" tab details
	 */
	/**
	 * To fetch "REQUEST FOR MY APPROVAL" tab details.
	 *
	 * @param salesPositionID the sales position id
	 * @param salesHiearchyID the sales hiearchy id
	 * @param paramList the param list
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param tenantID the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchTChngReqApprCRBySalesPositionID(
			long salesPositionID, long salesHiearchyID,
			List<Integer> paramList, int index, int noOfResults,
			short tenantID, String localeId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionID);
		queryParam.add(salesHiearchyID);
		queryParam.add(paramList);
		queryParam.add(tenantID);
		queryParam.add(localeId);

		return genericDAO.findByNativeQueryMultiCond(FindApprCRBySalesPosID,
				queryParam, index, noOfResults);

	}
	/**
	 * Gets the t call pln details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t call pln details
	 */
	@Override
	public List<Object[]> getTCallPlnDetails(long crId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond(GetTCallPlnDetail,
				queryParam, 0, -1);
	}
	/**
	 * Gets the t move reporting details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t move reporting details
	 */
	@Override
	public List<Object[]> getTMoveReportingDetails(long crId, short tenantId) {
//		commenting below line because TChngReqDetail is removed -- syam
//		List queryParam = new ArrayList();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTSalesPostn",
//				queryParam, 0, -1);
		return null;
	}

	@Override
	public List<Object[]> getTCustTransDetails(long crId, Short tenantId) {
//		commenting below line because TChngReqDetail is removed -- syam
//		List<Object> queryParam = new ArrayList<Object>();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		return genericDAO.findEntitiesByNamedQueryMultiCond(
//				"GetTCustTransDetails", queryParam, 0, -1);
		return null;
	}
	/**
	 * Gets the t cust algn details by target flag.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @param detailList the detail list
	 * @return the t cust algn details by target flag
	 */
	@Override
	public List<Object[]> getTCustAlgnDetailsByTargetFlag(long crId,
			Short tenantId, List<Integer> detailList) {
	//	commenting below line because TChngReqDetail is removed -- syam
//		List queryParam = new ArrayList();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		queryParam.add(detailList);
//		return genericDAO.findEntitiesByNamedQueryMultiCond(
//				"GetTCustAlgnByTargetFlag", queryParam, 0, -1);
		return null;
	}
	/**
	 * Gets the t cust trans details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t cust trans details
	 */
	@Override
	public List<Object[]> getProdTransDetails(long crId, Short tenantId) {
//		commenting below line because TChngReqDetail is removed -- syam
//		List<Object> queryParam = new ArrayList<Object>();
//		queryParam.add(crId);
//		queryParam.add(tenantId);
//		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdDetail",
//				queryParam, 0, -1);
		return null;
	}

	/**
	 * Fetch t cust aff transction details.
	 *
	 * @param crId the cr id
	 * @param custAffList the cust aff list
	 * @param detailIdList the detail id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object> fetchTCustAffTransctionDetails(Long crId,
			List<Integer> custAffList, List<Integer> detailIdList,
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(tenantId);

		queryParam.add(detailIdList);
		queryParam.add(custAffList);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"getCustAffDetailedView", queryParam, 0, -1);

	}

	/**
     * Gets the t zip trans details.
     *
     * @param crId the cr id
     * @param tenantId the tenant id
     * @param triggerId the trigger id
     * @return the t zip trans details
     */
	@Override
	public List<Object[]> getTZipTransDetails(long crId, Short tenantId,
			int triggerId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(triggerId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTZipDetail",
				queryParam, 0, -1);
	}

	/**
	 * Fetch all my chng request.
	 *
	 * @param salesPositionIDS the sales position ids
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param statusIds the status ids
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @param paStatus the pa status
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchAllMyChngRequest(Set<Long> salesPositionIDS,
			int index, int noOfResults, List<Integer> statusIds,
			Short tenantId, String localeId,Integer paStatus) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionIDS);
		queryParam.add(statusIds);
		queryParam.add(tenantId);
		queryParam.add(localeId);
		queryParam.add(paStatus);
		return genericDAO.findByNativeQueryMultiCond(fetchAllMyChngRequest,
				queryParam, index, noOfResults);

	}
	/**
	 * Gets the cRSP end date.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the cRSP end date
	 */
	@Override
	public List<Object> getCRSPEndDate(long crId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(crId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCRReqSPEndDt",
				params, 0, -1);
	}
	/**
	 * Gets the cR req sh id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the cR req sh id
	 */
	@Override
	public List<Object> getCRReqSHId(long salesPosId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(salesPosId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSPHierId",
				params, 0, -1);
	}
	  /**
     * Fetch cust aff trans details.
     *
     * @param crId the cr id
     * @param tenantId the tenant id
     * @return the list
     */
	@Override
	public List<Object[]> fetchCustAffTransDetails(long crId, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(crId);
		params.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond(fetchAffDetail, params, 0,
				-1);
	}
	  /**
     * Gets the trigger id list.
     *
     * @param tenantId the tenant id
     * @return the trigger id list
     */
	@Override
	public List<Object[]> getTriggerIdList( Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond(getTriggerId, params, 0,
				-1);
	}
	 /**
     * Gets the employee mail ids.
     *
     * @param salesPosId the sales pos id
     * @param tenantId the tenant id
     * @return the employee mail ids
     */
	public List<Object> getEmployeeMailIds(Long salesPosId, Short tenantId) {

		List<Object> params = new ArrayList<Object>();
		params.add(salesPosId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetEmployeeMailIds", params, 0, -1);

	}
	/**
	 * Gets the sP pending cr.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHiearchyID the sales hiearchy id
	 * @param triggerList the trigger list
	 * @param statusList the status list
	 * @param queryName the query name
	 * @param tenantId the tenant id
	 * @return the sP pending cr
	 */
	@Override
	public Object getSPPendingCR(Long salesPositionId, Long salesHiearchyID,
			List<Integer> triggerList, List<Integer> statusList,
			String queryName, Short tenantId) {

		List<Object> queryParam = new ArrayList<>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(tenantId);

		return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
				queryParam, 0, -1);

	}
	/**
     * Gets the prd pending cr.
     *
     * @param salesPositionId the sales position id
     * @param salesHiearchyID the sales hiearchy id
     * @param triggerList the trigger list
     * @param statusList the status list
     * @param prdAlignmentId the prd alignment id
     * @param queryName the query name
     * @return the prd pending cr
     */
	@Override
	public Object getPrdPendingCR(Long salesPositionId, Long salesHiearchyID,
			List<Integer> triggerList, List<Integer> statusList,
			List<Long> prdAlignmentId, String queryName) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(prdAlignmentId);

		return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
				queryParam, 0, -1);

	}
	 /**
	    * Gets the prd pending cr by prd id.
	    *
	    * @param salesPositionId the sales position id
	    * @param salesHiearchyID the sales hiearchy id
	    * @param triggerList the trigger list
	    * @param statusList the status list
	    * @param prdId the prd id
	    * @param queryName the query name
	    * @return the prd pending cr by prd id
	    */
	@Override
	public Object getPrdPendingCRByPrdId(Long salesPositionId,
			Long salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId, String queryName) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(prdId);

		return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
				queryParam, 0, -1);

	}
	 /**
	    * Gets the sP other locked triggers.
	    *
	    * @param salesPositionId the sales position id
	    * @param salesHiearchyID the sales hiearchy id
	    * @param triggerList the trigger list
	    * @param statusList the status list
	    * @param queryName the query name
	    * @param tenantId the tenant id
	    * @return the sP other locked triggers
	    */
	@Override
	public Object getSPOtherLockedTriggers(Long salesPositionId,
			Long salesHiearchyId, List<Integer> triggerList,
			List<Integer> statusList, String queryName, Short tenantId) {
		List<Object> queryParam = new ArrayList<>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyId);
		queryParam.add(statusList);
		queryParam.add(triggerList);
		queryParam.add(tenantId);

		return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
				queryParam, 0, -1);

	}
	 /**
	    * Gets the move rep other pending cr.
	    *
	    * @param salesPositionId the sales position id
	    * @param salesHiearchyID the sales hiearchy id
	    * @param triggerList the trigger list
	    * @param statusList the status list
	    * @param queryName the query name
	    * @param tenantId the tenant id
	    * @return the move rep other pending cr
	    */
	public Object getMoveRepOtherPendingCR(Long salesPositionId,
			Long salesHiearchyId, List<Integer> triggerList,
			List<Integer> statusList, String queryName, Short tenantId) {

		List<Object> queryParam = new ArrayList<>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyId);
		queryParam.add(triggerList);
		queryParam.add(statusList);

		return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
				queryParam, 0, -1);

	}
	/**
	 * Fetch change request based on role.
	 *
	 * @param spList the sp list
	 * @param shList the sh list
	 * @param statusList the status list
	 * @param localeId the locale id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchChangeRequestBasedOnRole(Long spList,
			Long shList, List statusList, String localeId, Short tenantId) {

		List<Object> params = new ArrayList<Object>();
		params.add(spList);
		params.add(shList);
		params.add(statusList);
		params.add(localeId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchChngRequestBasedOnRole", params, 0, 6);
	}

 /**
    * Fetch my req tab count.
    *
    * @param childSalesPosSet the child sales pos set
    * @param tenantId the tenant id
    * @return the object
    */
	@Override
	public Object fetchMyReqTabCount(Set<Long> childSalesPosSet, Short tenantId) {

		List<Object> queryParam = new ArrayList<>();
		queryParam.add(childSalesPosSet);
		queryParam.add(tenantId);
		return genericDAO.countEntitiesByNamedQueryMultiCond("myRequestCount",
				queryParam, 0, -1);
	}
 /**
    * Fetch req fr my appr tab count.
    *
    * @param salesPositionId the sales position id
    * @param salesHiearchyID the sales hiearchy id
    * @param statusList the status list
    * @param tenantId the tenant id
    * @return the object
    */
	@Override
	public Object fetchReqFrMyApprTabCount(Long salesPositionId,
			Long salesHiearchyID, List<Integer> statusList, Short tenantId) {

		List<Object> queryParam = new ArrayList<>();

		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(statusList);
		queryParam.add(tenantId);
		return genericDAO.countEntitiesByNamedQueryMultiCond("ReqForApprCount",
				queryParam, 0, -1);
	}
	/**
	    * Gets the t call pln details by id.
	    *
	    * @param crId the cr id
	    * @param tenantId the tenant id
	    * @return the t call pln details by id
	    */
	@Override
	public List<Object[]> getTCallPlnDetailsById(Long crId, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(crId);
		params.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond(getCustCallPlanDet,
				params, 0, -1);
	}
   /**
    * Fetch sp code.
    *
    * @param crIdlist the cr idlist
    * @param tenantId the tenant id
    * @return the list
    */
	@Override
	public List<Object[]> fetchSpCode(List<Long> crIdlist, Short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(crIdlist);
		params.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond(spDetail,
				params, 0, -1);
	}
   /**
    * Gets the cR trigger and feature by id.
    *
    * @param crId the cr id
    * @param tenantId the tenant id
    * @return the cR trigger and feature by id
    */
	@Override
	public List<Object[]> getCRTriggerAndFeatureById(Long crId, Short tenantId) {

		List<Object> params = new ArrayList<Object>();
		params.add(crId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetCRTriggerAndFeature", params, 0, -1);
	}
	/**
    * Gets the sP primary emp user id.
    *
    * @param spId the sp id
    * @param shId the sh id
    * @param algmntId the algmnt id
    * @param bussId the buss id
    * @param salesteamId the salesteam id
    * @param tenantId the tenant id
    * @return the sP primary emp user id
    */
	public List<Object[]> getSPPrimaryEmpUserId(Long spId, Long shId,
			Long algmntId, Long bussId, Long salesteamId, Short tenantId) {

		List<Object> params = new ArrayList<Object>();
		params.add(spId);
		params.add(shId);
		params.add(algmntId);
		params.add(bussId);
		params.add(salesteamId);
		params.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetSPPrimaryEmpUserId", params, 0, -1);
	}
	/**
    * Fetch chng req num.
    *
    * @param grpName the grp name
    * @param table the table
    * @param valueCol the value col
    * @param pKeyColumn the key column
    * @return the long
    */
	public  Long fetchChngReqNum(String grpName,String table,String valueCol,String pKeyColumn){
		
		return genericDAO.generateID(grpName, table, valueCol, pKeyColumn);
	}
	/**
    * Fetch chng req and config details.
    *
    * @param crId the cr id
    * @param tenatId the tenat id
    * @return the list
    */
	public List<Object[]> fetchChngReqAndConfigDetails(List<Long> crIds, Short tenatId) {

		List<Object> params = new ArrayList<Object>();
		params.add(crIds);
		params.add(tenatId);

		List<Object[]> chngReqConfigDet = genericDAO
				.findEntitiesByNamedQueryMultiCond("GetTchngReqAndConfig",
						params, 0, -1);
		return chngReqConfigDet;
	}
   /**
    * Fetch inactive c rs.
    *
    * @param tenantId the tenant id
    * @return the list
    */
	@Override
	public List<Object[]> fetchInactiveCRs( short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		params.add(DateUtil.getCurrentDate());
		return genericDAO
				.findEntitiesByNamedQueryMultiCond("fetchInactiveCRs",
						params, 0, -1);
	}
	/**
    * Update flag.
    *
    * @param crList the cr list
    * @return the boolean
    */
	@Override
	public Boolean updateFlag(Set<Long> crList) {
		
		List<Object> crIds = new ArrayList<Object>();
		crIds.add(crList);
		try {
			genericDAO.updateEntitiesByNamedQueryMultiCond(
					"updateTchngReqFlag", crIds, -1, -1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
    * Fetch active c rs for trigger.
    *
    * @param triggerId the trigger id
    * @param statusId the status id
    * @param tenantId the tenant id
    * @param algmntId the algmnt id
    * @param bussId the buss id
    * @param salesteamId the salesteam id
    * @return the integer
    */
	@Override
	public Integer fetchActiveCRsForTrigger(Integer triggerId, Integer statusId,
			Short tenantId,Long algmntId, Long bussId, Long salesteamId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(triggerId);
		queryParam.add(statusId);
		queryParam.add(tenantId);
		queryParam.add(algmntId);
		queryParam.add(bussId);
		queryParam.add(salesteamId);
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond
				("FetchActiveCRsForTrigger", queryParam, 0, -1);
		
		if(list != null && !list.isEmpty()){
			return Integer.parseInt(list.get(0).toString()) ;
		}
		
		return 0; 
	}
	/**
	   * Gets the pending status cr.
	   *
	   * @param algmntId the algmnt id
	   * @param bussUnitId the buss unit id
	   * @param salesTeamId the sales team id
	   * @param tenantId the tenant id
	   * @return the pending status cr
	   */
	@Override
	public List<Object[]> getPendingStatusCR(Long algmntId,Long bussUnitId,Long salesTeamId,short tenantId)  {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		return genericDAO.findByNativeQueryMultiCond
				(fetchAllPendingCRS, queryParam, 0, -1);
	}
  /**
   * Update tchng req.
   *
   * @param queryParams the query params
   * @return the int
   */
	public int updateTchngReq(List<Object> queryParams) {

		return genericDAO.updateEntitiesByNamedQueryMultiCond(
					"updateTchngReq", queryParams, 0, -1);
	}
	/**
	 * Find all t chng req by customer.
	 *
	 * @param customerId the customer id
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TChngReq> findAllTChngReqByCustomer(Integer customerId, Long salesPositionId, Long salesHierchyId, Long algmntId, Long bussUnitId,
			Long salesTeamId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(customerId);
		queryParam.add(salesPositionId);
		queryParam.add(salesHierchyId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findAllTChngReqByCustomer", queryParam, 0, -1);
	}
	/**
	 * Find all t chng req.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TChngReq> findAllTChngReq(Long salesPositionId, Long salesHierchyId, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHierchyId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findAllTChngReqBySalesposition", queryParam, 0, -1);
	}
	/**
	 * Find all t chng req for approval.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param tenantId the tenant id
	 * @param statusId the status id
	 * @return the list
	 */
	@Override
	public List<TChngReq> findAllTChngReqForApproval(Long salesPositionId, Long salesHierchyId, short tenantId, int statusId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesPositionId);
		queryParam.add(salesHierchyId);
		queryParam.add(tenantId);
		queryParam.add(statusId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findAllTChngReqForApproval", queryParam, 0, -1);
	}
	
	/**
	 * Retrieve TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrack type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReq> getTChngReqsByTChngReqTrack(final SearchFilter<TChngReqTrack> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqTrack tChngReqTrack = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tChngReqTrack);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqByTChngReqTrack", queryParam, index, maxresult);
	}

	/**
	 * Count TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrack type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqsByTChngReqTrack(final SearchFilter<TChngReqTrack> searchFilter) {

		final TChngReqTrack tChngReqTrack = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tChngReqTrack);
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqsByTChngReqTrack", queryParam);
	}
	
	/**
	 * Retrieve TChngReq based on given parent change request id named Query.
	 * @param chngReqId
	 * @return List
	 */
	@Override
	public List<TChngReq> findTChngReqByParentChangeRequestId(Long chngReqId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTChngReqByParentChangeRequestId", queryParam, 0, -1);
	}
	/**
	 * Updates a TChngReq entity object in to the persistent store.
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be updated
	 * @return tChngReq Persisted TChngReq object
	 */
	@Override
	public List<TChngReq> updateTChngReq(List<TChngReq> tChngReqList) {
		LOGGER.info("=========== Update TChngReq ===========");
		return genericDAO.update(tChngReqList);
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findCustomerAlignmentSalesPostionIdByChangeRequestId(java.lang.Long, short)
	 */
	@Override
	public List<Object[]> findCustomerAlignmentSalesPostionIdByChangeRequestId(Long chngReqId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(chngReqId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findCustomerAlignmentSalesPostionIdByChangeRequestId",params, 0, -1);
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findZipAlignmentSalesPostionIdByChangeRequestId(java.lang.Long, short)
	 */
	@Override
	public List<Object[]> findZipAlignmentSalesPostionIdByChangeRequestId(Long chngReqId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(chngReqId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findZipAlignmentSalesPostionIdByChangeRequestId",params, 0, -1);

	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findCustomerIdByChangeRequestId(java.lang.Long, short)
	 */
	@Override
	public List<Object[]> findCustomerIdByChangeRequestId(Long chngReqId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(chngReqId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findCustomerIdByChangeRequestId",params, 0, -1);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#updateCRStatus(long, java.lang.Integer, Short)
	 */
	@Override
	public void updateCRStatus(long changeRequestId, Integer statusId, Integer userId,
			Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
	        paramList.add(statusId);
	        paramList.add(DateUtil.getCurrentDate());
	        paramList.add(userId);
	        paramList.add(changeRequestId);
	        paramList.add(tenantId);
	     
	    genericDAO.updateEntitiesNamedQuery("updateChngReqStatus", paramList);
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#markChangeRequestAsDirty(java.lang.Long, Short)
	 */
	@Override
	public List<TChngReq> markChangeRequestAsDirty(Long spId,
			Short tenantId) {
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(spId);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTChngReqBySPIdWithProgressStatus", queryParam, 0, -1);
	}
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findParentChangeRequestIdByChildChangeRequestId(java.lang.Long, short)
	 */
	@Override
	public List<Object[]> findParentChangeRequestIdByChildChangeRequestId(Long chngReqId, short tenantId) {
		List<Object> params = new ArrayList<Object>();
		params.add(chngReqId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findParentChangeRequestIdByChildChangeRequestId",params, 0, -1);
	}

	/** Changes For Update Chnage Request Status -- start **/
	@Override
	public List<TChngReq> findChangeRequestByStatusId(List<Integer> statusIds , Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(statusIds);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("findChangeRequestByStatusId",paramList, 0, -1);
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#getChangeRequestByStatusForSubmit(java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Integer, java.lang.Integer, java.lang.Short)
	 */
	@Override
	public List<TChngReq> getChangeRequestByStatusForSubmit(Long reqSalesPostion, Long reqSalesHierarchyId, Long raiseSalesPostion, Long raiseSalesHierarchyId, Integer status,
			Integer triggerId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(reqSalesPostion);
		queryParam.add(reqSalesHierarchyId);
		queryParam.add(status);
		queryParam.add(triggerId);
		queryParam.add(tenantId);
		queryParam.add(raiseSalesPostion);
		queryParam.add(raiseSalesHierarchyId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getChangeRequestByStatusForSubmit", queryParam, 0, -1);
	}

	@Override
	public List<Object[]> findCRIdsNotInGivenCRIdList(Set<Long> crIds , Short tenantId){
		List paramList = new ArrayList();
		paramList.add(crIds);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("findCRIdsNotInCRIdList",paramList, 0, -1);
	}
	
	@Override
	public Boolean updateFlagAsInactive(Set<Long> crList, Long timeDiffForInitiate, short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(crList);
		paramList.add(timeDiffForInitiate);
		paramList.add(timeDiffForInitiate);
		
		try {
			genericDAO.updateEntitiesByNamedQueryMultiCond("updateTchngReqFlagAsInActive", paramList, -1, -1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/** Changes For Update Chnage Request Status -- end **/


	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#getCRCountBySpsAndStatus(java.util.List, java.lang.Integer, java.lang.Short)
	 */
	@Override
	public Integer getCRCountBySpsAndStatus(List<Long> spIds, Integer statusId,
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(spIds);
		queryParam.add(statusId);
		queryParam.add(tenantId);
		
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("CountTChngReqsBySPandStatus", queryParam, 0, -1);
		
		if(list != null && !list.isEmpty()){
			return Integer.parseInt(list.get(0).toString()) ;
		}
		
		return 0; 
	}

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findZipAlignmentByChangeRequestId(java.lang.Long, java.lang.Character, java.lang.Short)
	 */
	@Override
	public List<TTerrZipAlgmnt> findZipAlignmentByChangeRequestId(Long chngReqId, Character alignmentFlag, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		queryParam.add(alignmentFlag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findZipAlignmentByChangeRequestId", queryParam, 0, -1);
	}
	
	
	
	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqDAO#findMirrorZipAlignmentByChangeRequestId(java.lang.Long, java.lang.Character, java.lang.Short)
	 */
	@Override
	public List<Object> findMirrorZipAlignmentByChangeRequestId(Long chngReqId, Character alignmentFlag, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		queryParam.add(alignmentFlag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findMirrorZipAlignmentByParentChangeRequestId", queryParam, 0, -1);
	}
	
}
