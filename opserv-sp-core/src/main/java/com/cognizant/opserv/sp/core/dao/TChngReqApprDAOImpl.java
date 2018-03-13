package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqAppr;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqApprDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqApprDAO")
public class TChngReqApprDAOImpl implements TChngReqApprDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqApprDAOImpl.class);
	private final String apprStage = "SELECT sp.pos_name,tp.first_name, tp.middle_name, tp.last_name, ta.appr_id, "
			+ " ws.status_name,ta.update_dt,ta.target_appr_flag,ws.status_desc,(select ts.status_name from  t_chng_req_appr tcra,t_wkflw_status ts where tcra.chng_req_id = ta.chng_req_id and tcra.active_flag=ta.active_flag  and tcra.status_id=ts.status_id and tcra.target_appr_flag='N' ORDER BY tcra.update_dt ASC LIMIT 1)as sourceStatus, (select count(DISTINCT tcra.chng_req_id) from t_chng_req_appr tcra,t_wkflw_status ts where tcra.chng_req_id = ta.chng_req_id and tcra.active_flag=ta.active_flag and tcra.target_appr_flag='N' and tcra.status_id=ts.status_id and ts.status_name = 'pendingApproval' and ts.tenant_id = ta.tenant_id ) as sflag,(select tws.status_name from t_chng_req tcr,t_wkflw_status tws where tcr.chng_req_id = ta.chng_req_id and tcr.status_id=tws.status_id and tws.tenant_id = ta.tenant_id LIMIT 1)as mainStatus FROM t_chng_req tc, t_chng_req_appr ta LEFT JOIN t_pers tp ON ta.updated_by = tp.usr_id and tp.tenant_id =ta.tenant_id ,t_sales_pos sp , t_wkflw_status ws "
			+ " WHERE ta.chng_req_id = ?1 AND ta.active_flag = 'Y' AND sp.sales_hier_id = ta.appr_sales_hier_id AND sp.sales_pos_id = ta.appr_sales_pos_id  AND ws.status_id = ta.status_id  AND ws.tenant_id = sp.tenant_id  AND sp.tenant_id = ta.tenant_id AND ta.tenant_id =?2  and tc.chng_req_id = ta.chng_req_id and tc.status_id !=?4 and ws.locale_id=?3    GROUP BY ta.appr_sales_pos_id,ta.target_appr_flag,ta.status_id   ORDER BY ta.target_appr_flag, ta.appr_id  ";
	
	private final String affCount ="select count(distinct tcf.chng_req_detail_id) as AFF_COUNT,  count(distinct tca.chng_req_detail_id) as CUST_COUNT,ta.chng_req_id from t_chng_req_appr ta LEFT JOIN t_cust_aff_chng_req_det tcf ON (tcf.chng_req_id = ta.chng_req_id and  ta.chng_req_detail_id = tcf.chng_req_detail_id)LEFT JOIN t_cust_algmnt_chng_req_det tca ON (tca.chng_req_id = ta.chng_req_id " +
			"and  ta.chng_req_detail_id = tca.chng_req_detail_id)where ta.chng_req_id IN ?1 and ta.target_appr_flag = ( SELECT tcra.target_appr_flag FROM t_chng_req_appr  tcra  WHERE   tcra.chng_req_id = ta.chng_req_id AND tcra.status_id = ta.status_id AND ta.active_flag=tcra.active_flag AND tcra.appr_sales_pos_id = ta.appr_sales_pos_id AND tcra.appr_sales_hier_id = ta.appr_sales_hier_id AND tcra.tenant_id = ta.tenant_id order by tcra.update_dt desc,tcra.target_appr_flag limit 1) and ta.appr_sales_hier_id= ?4 and  ta.appr_sales_pos_id=?3 and ta.active_flag='Y'  and ta.tenant_id = ?2 group by ta.chng_req_id ";

  @Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";

	private final Class<TChngReqAppr> clazz;

	public Class<TChngReqAppr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqApprDAOImpl() {
		super();
		this.clazz = TChngReqAppr.class;
	}

	private static final String JPAQL = "select tChngReqApprentity from TChngReqAppr tChngReqApprentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqAppr tChngReqApprentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqApprentity.apprId = #{tChngReqApprentity.getApprId}",
			"tChngReqApprentity.apprSalesHierId = #{tChngReqApprentity.getApprSalesHierId}",
			"tChngReqApprentity.apprSalesPosId = #{tChngReqApprentity.getApprSalesPosId}",
			"tChngReqApprentity.statusId = #{tChngReqApprentity.getStatusId}",
			"tChngReqApprentity.apprTypeId = #{tChngReqApprentity.getApprTypeId}",
			"tChngReqApprentity.dlgSalesPosId = #{tChngReqApprentity.getDlgSalesPosId}",
			"tChngReqApprentity.dlgSalesHierId = #{tChngReqApprentity.getDlgSalesHierId}",
			"tChngReqApprentity.targetApprFlag = #{tChngReqApprentity.getTargetApprFlag}",
			"Date(tChngReqApprentity.actionDtTm) = '#{tChngReqApprentity.getActionDtTm}'",
			"tChngReqApprentity.actionBy = #{tChngReqApprentity.getActionBy}",
			"tChngReqApprentity.createdBy = #{tChngReqApprentity.getCreatedBy}",
			"Date(tChngReqApprentity.createDt) = '#{tChngReqApprentity.getCreateDt}'",
			"tChngReqApprentity.updatedBy = #{tChngReqApprentity.getUpdatedBy}",
			"Date(tChngReqApprentity.updateDt) = '#{tChngReqApprentity.getUpdateDt}'",
			"tChngReqApprentity.tenantId = #{tChngReqApprentity.getTenantId}",
			"tChngReqApprentity.comments like '#{tChngReqApprentity.getComments}%'",
			"tChngReqApprentity.tChngReq.chngReqId = #{tChngReqApprentity.tChngReq.getChngReqId}" };

	/**
	 * Stores a new TChngReqAppr entity object in to the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be persisted
	 * @return tChngReqAppr Persisted TChngReqAppr object
	 */
	public TChngReqAppr createTChngReqAppr(final TChngReqAppr tChngReqAppr) {
		LOGGER.info("=========== Create TChngReqAppr ===========");
		return genericDAO.store(tChngReqAppr);
	}

	/**
	 * Deletes a TChngReqAppr entity object from the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be deleted
	 */
	public void deleteTChngReqAppr(final Integer tChngReqApprId) {
		LOGGER.info("=========== Delete TChngReqAppr ===========");
		final TChngReqAppr tChngReqAppr = genericDAO.get(clazz, tChngReqApprId);
		genericDAO.remove(tChngReqAppr);
	}

	/**
	 * Updates a TChngReqAppr entity object in to the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be updated
	 * @return tChngReqAppr Persisted TChngReqAppr object
	 */
	public TChngReqAppr updateTChngReqAppr(final TChngReqAppr tChngReqAppr) {
		LOGGER.info("=========== Update TChngReqAppr ===========");
		return genericDAO.update(tChngReqAppr);
	}

	/**
	 * Update t chng req appr.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	public List<TChngReqAppr> updateTChngReqAppr(final List<TChngReqAppr> tChngReqApprList) {
		LOGGER.info("=========== Update TChngReqAppr ===========");
		return genericDAO.update(tChngReqApprList);
	}

	/**
	 * Retrieve an TChngReqAppr object based on given TChngReqAppr
	 * TChngReqApprId.
	 * 
	 * @param tChngReqApprId
	 *            the primary key value of the TChngReqAppr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqAppr findTChngReqApprById(final Integer tChngReqApprId) {
		LOGGER.info("find TChngReqAppr instance with TChngReqApprId: "
				+ tChngReqApprId);
		return genericDAO.get(clazz, tChngReqApprId);
	}

	/**
	 * Retrieve TChngReqAppr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqAppr> list of TChngReqAppr if it exists against
	 *         given criteria. Returns null if not found
	 */
	public List<TChngReqAppr> findTChngReqApprs(
			final SearchFilter<TChngReqAppr> searchFilter) {
		LOGGER.info("=========== Find TChngReqApprs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqAppr tChngReqAppr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqApprentity",
				tChngReqAppr);

		if (tChngReqAppr.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}" + tChngReqAppr
					.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tChngReqAppr.getTChngReq());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqApprs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqApprs(
			final SearchFilter<TChngReqAppr> searchFilter) {
		LOGGER.info("=========== Count TChngReqAppr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqAppr tChngReqAppr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqApprentity",
				tChngReqAppr);

		if (tChngReqAppr.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}" + tChngReqAppr.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tChngReqAppr.getTChngReq());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReqAppr based on given search criteria using JPA named
	 * Query. The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqAppr> list of TChngReqApprs if it exists against
	 *         given criteria. Returns null if not found
	 */
	public List<TChngReqAppr> getTChngReqApprsByTChngReq(
			final SearchFilter<TChngReq> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> tChngReqList = new ArrayList<Object>();
		tChngReqList.add(tChngReq);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqApprByTChngReq", tChngReqList, index, maxresult);
	}

	/**
	 * Count TChngReqAppr based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqApprsByTChngReq(
			final SearchFilter<TChngReq> searchFilter) {

		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> tChngReqList = new ArrayList<Object>();
		tChngReqList.add(tChngReq);
		return genericDAO.findEntitiesByNamedQuery(
				"CountTChngReqApprsByTChngReq", tChngReqList);
	}
	/**
	 * Fetch t chng req apprs.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	public List<TChngReqAppr> fetchTChngReqApprs(
			SearchFilter<TChngReqAppr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqAppr tChngReqAppr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List queryParam = new ArrayList();
		queryParam.add(tChngReqAppr.getTChngReq()
				.getChngReqId());
		queryParam.add(tChngReqAppr.getApprId());
		queryParam.add(tChngReqAppr.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTChngReqApprByTChngReq", queryParam, index, maxresult);

	}
	/**
	 * Fetch t chng req apprs type by status.
	 *
	 * @param crId the cr id
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the long
	 */
	public long fetchTChngReqApprsTypeByStatus(long crId, int statusId,
			short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(statusId);
		queryParam.add(tenantId);
		List list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"CountTChngReqApprsByStatus", queryParam, 0, -1);
		return (long) list.get(0);

	}
	/**
	 * Gets the max appr id by cr id.
	 *
	 * @param params the params
	 * @return the max appr id by cr id
	 */
	public List<Object> getMaxApprIdByCRId(List<Object> params) {
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetMaxApprIdByCRId", params, 0, -1);
	}
	 /**
 	 * Fetch t chng req details.
 	 *
 	 * @param crId the cr id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
	@Override
	public List<TChngReqAppr> fetchTChngReqDetails(Long crId, Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchTChngReqApprByTChngReq", queryParam, 0, -1);

	}

	 /**
 	 * Fetch t chng req appr.
 	 *
 	 * @param crId the cr id
 	 * @param salesHeirId the sales heir id
 	 * @param salesPosId the sales pos id
 	 * @param targetflag the targetflag
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
	@Override
	public List<TChngReqAppr> fetchTChngReqAppr(Long crId,
			Long salesHeirId, Long salesPosId,char targetflag, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(salesHeirId);
		queryParam.add(salesPosId);
	    queryParam.add(targetflag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTChngReqApprForSP", queryParam, 0, -1);
	}

	/**
	 * Added by 409793 This method will fetch the list of Target Approvers Sales
	 * Positions.
	 */

	/**
	 * Fetch t chng req appr list.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchTChngReqApprList(Long crId,Short tenantId) {
	
		List queryParam = new ArrayList<>();
		queryParam.add(crId);
		queryParam.add(tenantId);
	    return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindApprSalesPosID", queryParam, 0, -1);

	}

	/**
	 * Added by 409793 This method will fetch the list of Source Approvers Sales
	 * Positions.
	 */
	/**
	 * Gets the status.
	 *
	 * @param crId the cr id
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the status
	 */
	public long getStatus(long crId, int statusId, short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(statusId);
		queryParam.add(tenantId);
		List list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"getStatusForCR", queryParam, 0, -1);
		return (long) list.get(0);

	}
	 /**
     * Gets the cust aff t flag.
     *
     * @param params the params
     * @return the cust aff t flag
     */
	@Override
	public List<Object[]> getCustAffTFlag(List<Object> queryParam){
		// commenting below line because TChngReqDetail is removed -- syam
//	  List list = genericDAO.findEntitiesByNamedQueryMultiCond(
//				"getAffTFlag", queryParam, 0, -1);
//		return list;
		return null;
	}

	/**
	 * Fetch t chng req appr detail list.
	 *
	 * @param crId the cr id
	 * @param statusId the status id
	 * @param targetFlag the target flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TChngReqAppr> fetchTChngReqApprDetailList(Long crId,int statusId,Character targetFlag, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(statusId);
		queryParam.add(targetFlag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchApprDetailId", queryParam, 0, -1);
	}
	 /**
	    * Gets the change request by flag.
	    *
	    * @param params the params
	    * @return the change request by flag
	    */
	@Override
	public List<Object[]> getChangeRequestByFlag(List<Object> params) {

		return genericDAO.findEntitiesByNamedQueryMultiCond("getDetailsByTargetFlag",
				params, 0, -1);
	}
   /**
    * Fetch appr status.
    *
    * @param crId the cr id
    * @param tenantId the tenant id
    * @param localId the local id
    * @param cnclStsId the cncl sts id
    * @return the list
    */
	@Override
	public List<Object[]> fetchApprStatus(long crId, short tenantId,String localId,int cnclStsId) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(tenantId);
		queryParam.add(localId);
		queryParam.add(cnclStsId);
		return genericDAO.findByNativeQueryMultiCond(apprStage, queryParam, 0,
				-1);
	}

	/**
     * Fetch t chng req in active appr list.
     *
     * @param crId the cr id
     * @param status the status
     * @param salesHeirId the sales heir id
     * @param salesPosId the sales pos id
     * @param tenantId the tenant id
     * @return the list
     */
	@Override
	public List<TChngReqAppr> fetchTChngReqInActiveApprList(Long crId,
			int status, Long salesHeirId, Long salesPosId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(crId);
		queryParam.add(status);
		queryParam.add(salesHeirId);
		queryParam.add(salesPosId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchInActiveAppr", queryParam, 0, -1);
	}
	
	/**
     * Fetch appr trans count.
     *
     * @param crIdList the cr id list
     * @param statusId the status id
     * @param spId the sp id
     * @param shId the sh id
     * @param tenantId the tenant id
     * @return the list
     */
	@Override
	public List<Object[]> fetchApprTransCount(List<Long> crIdList, int statusId, Long spId,Long shId,
			short tenantId) {
		// commenting below line because TChngReqDetail is removed -- syam
//		List queryParam = new ArrayList();
//		queryParam.add(crIdList);
//		queryParam.add(statusId);
//		queryParam.add(spId);
//		queryParam.add(shId);
//		queryParam.add(tenantId);
//		return genericDAO.findEntitiesByNamedQueryMultiCond(
//				"fetchApprTransCount", queryParam, 0, -1);
		return null;

	}
	/**
	 * Fetch aff count.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @param spId the sp id
	 * @param shId the sh id
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchAffCount(List<Long> crIdList, short tenantId,long spId,long shId) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crIdList);
		queryParam.add(tenantId);
		queryParam.add(spId);
		queryParam.add(shId);
		
		return genericDAO.findByNativeQueryMultiCond(affCount, queryParam, 0,
				-1);
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
					"updateTchngReqApprFlag", crIds, -1, -1);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Fetch t chng req appr list.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
   @Override
	public List<TChngReqAppr> fetchAllPendingApprovalCR(Long crId, Integer statusId,Short tenantId ) {
     		    List<Object> queryParam = new ArrayList<Object>();
			queryParam.add(crId);
			queryParam.add(statusId);
			queryParam.add(tenantId);
			List<TChngReqAppr> list=genericDAO.findEntitiesByNamedQuery("fetchPACRS", queryParam);
		    return list;
	}
   /**
	 * Count t chng req apprs.
	 *
	 * @return the object
	 */
	@Override
	public Object countTChngReqApprs() {
		
		return genericDAO.jpaSession().getNamedQuery("CountTChngReqApprs").uniqueResult();
	}
	/**
	 * Creates the t chng req appr.
	 *
	 * @param tChngReqApprList the t chng req appr list
	 * @return the list
	 */
	@Override
	public List<TChngReqAppr> createTChngReqAppr(
			List<TChngReqAppr> tChngReqApprList) {
		// TODO Auto-generated method stub
		return genericDAO.storeBatch(tChngReqApprList);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqApprDAO#fetchCRApproverDetails(java.lang.Long, java.lang.Short)
	 */
	@Override
	public List<TChngReqAppr> fetchCRApproverDetails(Long crId, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(crId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("fetchCRApproversDetails", queryParam);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TChngReqApprDAO#getApproversCountBySpsAndStatus(java.util.List, java.lang.Integer, java.lang.Short)
	 */
	@Override
	public Integer getApproversCountBySpsAndStatus(List<Long> spIds, Integer statusId,
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(spIds);
		queryParam.add(statusId);
		queryParam.add(tenantId);
		
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("CountTChngReqApprsBySPandStatus", queryParam, 0, -1);
		
		if(list != null && !list.isEmpty()){
			return Integer.parseInt(list.get(0).toString()) ;
		}
		
		return 0; 
	}
	/**
	 * Gets the target approver status.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the target approver status
	 */
	@Override
	public Integer getTargetApproverStatus(Long chngReqId,
			Character targetAppvrFlg, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		queryParam.add(targetAppvrFlg);
		queryParam.add(tenantId);
		List<Object> resultList =genericDAO.findEntitiesByNamedQuery(
				"FindTargetApprStatusId", queryParam);
		if(null != resultList && !resultList.isEmpty()){
			return (Integer)resultList.get(0);
		}
		return  null;
	}

	@Override
	public String findApproverComments(Long chngReqId,
			Character targetAppvrFlg, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(chngReqId);
		queryParam.add(targetAppvrFlg);
		queryParam.add(tenantId);
		List<Object> resultList =genericDAO.findEntitiesByNamedQuery(
				"FindApproverComments", queryParam);
		if(null != resultList && !resultList.isEmpty()){
			return (String)resultList.get(0);
		}
		return null;
	}
}
