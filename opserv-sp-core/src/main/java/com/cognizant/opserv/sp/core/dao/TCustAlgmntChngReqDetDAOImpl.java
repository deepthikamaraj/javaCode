package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDetId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAlgmntChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAlgmntChngReqDetDAO")
public class TCustAlgmntChngReqDetDAOImpl implements TCustAlgmntChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAlgmntChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTALGMNT = "tCustAlgmnt";
	
	private static final String TCHNGREQ = "tChngReq";

	private final Class<TCustAlgmntChngReqDet> clazz;
	
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";

	public Class<TCustAlgmntChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAlgmntChngReqDetDAOImpl() {
		super();
		this.clazz = TCustAlgmntChngReqDet.class;
	}

	private static final String JPAQL = "select tCustAlgmntChngReqDetentity from TCustAlgmntChngReqDet tCustAlgmntChngReqDetentity";

	private static final String JPACOUNTQL = "select count(*) from TCustAlgmntChngReqDet tCustAlgmntChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.chngReqDetailId = #{tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.getChngReqDetailId}",
			"tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.chngReqId = #{tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.getChngReqId}",
			"tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.custAlgmntId = #{tCustAlgmntChngReqDetentity.tCustAlgmntChngReqDetId.getCustAlgmntId}",
			"tCustAlgmntChngReqDetentity.statusId = #{tCustAlgmntChngReqDetentity.getStatusId}",
			"tCustAlgmntChngReqDetentity.createdBy = #{tCustAlgmntChngReqDetentity.getCreatedBy}",
			"Date(tCustAlgmntChngReqDetentity.createDt) = '#{tCustAlgmntChngReqDetentity.getCreateDt}'",
			"tCustAlgmntChngReqDetentity.updatedBy = #{tCustAlgmntChngReqDetentity.getUpdatedBy}",
			"Date(tCustAlgmntChngReqDetentity.updateDt) = '#{tCustAlgmntChngReqDetentity.getUpdateDt}'",
			"tCustAlgmntChngReqDetentity.tenantId = #{tCustAlgmntChngReqDetentity.getTenantId}",
			"tCustAlgmntChngReqDetentity.tCustAlgmnt.custAlgmntId = #{tCustAlgmntChngReqDetentity.tCustAlgmnt.getCustAlgmntId}",
			"tCustAlgmntChngReqDetentity.tChngReq.chngReqId = #{tCustAlgmntChngReqDetentity.tChngReq.getChngReqId}" ,
			"tCustAlgmntChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tCustAlgmntChngReqDetentity.tChngReqBussReason.getChngReqBrId}" };

	/**
	 * Stores a new TCustAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be persisted
	 * @return tCustAlgmntChngReqDet Persisted TCustAlgmntChngReqDet object
	 */
	public TCustAlgmntChngReqDet createTCustAlgmntChngReqDet(final TCustAlgmntChngReqDet tCustAlgmntChngReqDet) {
		LOGGER.info("=========== Create TCustAlgmntChngReqDet ===========");
		return genericDAO.store(tCustAlgmntChngReqDet);
	}

	/**
	 * Deletes a TCustAlgmntChngReqDet entity object from the persistent store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be deleted
	 */
	public void deleteTCustAlgmntChngReqDet(final TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId) {
		LOGGER.info("=========== Delete TCustAlgmntChngReqDet ===========");
		final TCustAlgmntChngReqDet tCustAlgmntChngReqDet = genericDAO.get(clazz, tCustAlgmntChngReqDetId);
		genericDAO.remove(tCustAlgmntChngReqDet);
	}

	/**
	 * Updates a TCustAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAlgmntChngReqDet
	 *            TCustAlgmntChngReqDet Entity object to be updated
	 * @return tCustAlgmntChngReqDet Persisted TCustAlgmntChngReqDet object
	 */
	public TCustAlgmntChngReqDet updateTCustAlgmntChngReqDet(final TCustAlgmntChngReqDet tCustAlgmntChngReqDet) {
		LOGGER.info("=========== Update TCustAlgmntChngReqDet ===========");
		return genericDAO.update(tCustAlgmntChngReqDet);
	}
	
	
	public List<TCustAlgmntChngReqDet> updateTCustAlgmntChngReqDet(final List<TCustAlgmntChngReqDet> list) {
		LOGGER.info("=========== Update TCustAlgmntChngReqDet ===========");
		return genericDAO.update(list);
	}

	/**
	 * Retrieve an TCustAlgmntChngReqDet object based on given TCustAlgmntChngReqDet TCustAlgmntChngReqDetId.
	 * 
	 * @param tCustAlgmntChngReqDetId
	 *            the primary key value of the TCustAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAlgmntChngReqDet findTCustAlgmntChngReqDetById(final TCustAlgmntChngReqDetId tCustAlgmntChngReqDetId) {
		LOGGER.info("find TCustAlgmntChngReqDet instance with TCustAlgmntChngReqDetId: " + tCustAlgmntChngReqDetId);
		return genericDAO.get(clazz, tCustAlgmntChngReqDetId);
	}

	/**
	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDet if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAlgmntChngReqDet> findTCustAlgmntChngReqDets(final SearchFilter<TCustAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TCustAlgmntChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAlgmntChngReqDet tCustAlgmntChngReqDet = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAlgmntChngReqDetentity", tCustAlgmntChngReqDet);

		if (tCustAlgmntChngReqDet.getTCustAlgmnt() == null) {
			jpaQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustAlgmntChngReqDet.getTCustAlgmnt());

			jpaQuery.bind(TCUSTALGMNT, tCustAlgmntChngReqDet.getTCustAlgmnt());
		}

		if (tCustAlgmntChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustAlgmntChngReqDet.getTChngReq());
	
			jpaQuery.bind(TCHNGREQ, tCustAlgmntChngReqDet.getTChngReq());
		}
		if (tCustAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustAlgmntChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tCustAlgmntChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAlgmntChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAlgmntChngReqDets(final SearchFilter<TCustAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TCustAlgmntChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAlgmntChngReqDet tCustAlgmntChngReqDet = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAlgmntChngReqDetentity", tCustAlgmntChngReqDet);

		if (tCustAlgmntChngReqDet.getTCustAlgmnt() == null) {
			jpaCountQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustAlgmntChngReqDet.getTCustAlgmnt());

			jpaCountQuery.bind(TCUSTALGMNT, tCustAlgmntChngReqDet.getTCustAlgmnt());
		}
		if (tCustAlgmntChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustAlgmntChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tCustAlgmntChngReqDet.getTChngReq());
		}
		if (tCustAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustAlgmntChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tCustAlgmntChngReqDet.getTChngReqBussReason());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmntChngReqDet> getTCustAlgmntChngReqDetsByTCustAlgmnt(
			final SearchFilter<TCustAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustAlgmntList = new ArrayList<Object>();
		tCustAlgmntList.add(tCustAlgmnt);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntChngReqDetByTCustAlgmnt", tCustAlgmntList, index,
				maxresult);
	}

	/**
	 * Count TCustAlgmntChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntChngReqDetsByTCustAlgmnt(final SearchFilter<TCustAlgmnt> searchFilter) {

		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustAlgmntList = new ArrayList<Object>();
		tCustAlgmntList.add(tCustAlgmnt);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntChngReqDetsByTCustAlgmnt", tCustAlgmntList);
	}

//	/**
//	 * Retrieve TCustAlgmntChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustAlgmntChngReqDet> list of TCustAlgmntChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	public List<TCustAlgmntChngReqDet> getTCustAlgmntChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntChngReqDetByTChngReqDetail", tChngReqDetailList, index,
//				maxresult);
//	}
	
	/**
	 * To get status from customer alignment details
	 */
	public Integer getTCustAlgmntChngReqDetByCustAlgmntId(long custAlgmntId, Integer pendingSub, Integer pendingAppr)
	{
		Integer returnVal = null;
		List queryParam = new ArrayList();		
		queryParam.add(custAlgmntId);
		queryParam.add(pendingSub);
		queryParam.add(pendingAppr);
		List<Integer> statusIdList  = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustAlgmntChngReqDetsByCustAlgmnt", queryParam, 0, -1);
		if((statusIdList != null) && (statusIdList.size() > 0)){
			returnVal = statusIdList.get(0);
		} 
		
	return returnVal;	
	}
//	/**
//	 * Count TCustAlgmntChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTCustAlgmntChngReqDetsByTChngReqDetail(final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntChngReqDetsByTChngReqDetail", tChngReqDetailList);
//	}

	public 	List<TCustAlgmntChngReqDet> fetchDetailList(List<Object> params,String Query){
		return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0, -1);
	}
	
     
	/*Added By 407986*/
	/**
	 * Gets the customer req details.
	 *
	 * @param params the params
	 * @return the customer req details
	 */
	public List<Object> getCustomerReqDetails(List<Object> params){
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomerReqDetails", params, 0, -1) ;
	}

	
	public List<Object[]> getCustomerCRStatus(List<Long> custAlgmntIdList, List<Integer> crStatusIdList, Short tenantId){
		List paramList = new ArrayList();
		paramList.add(custAlgmntIdList);
		paramList.add(crStatusIdList);
		paramList.add(tenantId);
		List<Object[]> resultList = genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomerCRStatus", paramList, 0, -1) ;
		return resultList;
		
	}
	/**
	 * Added by 409793 to fetch Customer pending submission/approve CR customer
	 * id.
	 */
	@Override
	public List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(custId);
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustLocked", queryParam, 0, -1);

	}

	/**
	 * Added by 409793 to fetch Customer Global Attribute pending
	 * submission/approve CR customer id.
	 */
	@Override
	public List<Object[]> getCustGlobalAttrPendingCR(List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(custId);
		queryParam.add(tenantId);
		

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustGlobalAttrLocked", queryParam, 0, -1);

	}
	
	/**
	 * Added by 407986 to fetch Customer locked For Zip Movement pending
	 * submission/approve CR customer id.
	 * 
	 * @param 
	 * @param custIds
	 * @param statusList
	 * @return
	 */
	@Override
	public List<Object[]> getLockedCustomerForZip(List<Integer> custIds,
			List<Integer> statusList, Short tenantId) {

		List queryParam = new ArrayList();
		
		queryParam.add(statusList);
		queryParam.add(custIds);
		queryParam.add(tenantId);
		

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustZipLocked", queryParam, 0, -1);

	}
	/**
	 * Gets the locked customer for zip movement.
	 *
	 * @param custIds the cust ids
	 * @param statusList the status list
	 * @param triggerIds the trigger ids
	 * @param tenantId the tenant id
	 * @return the locked customer for zip movement
	 */
	@Override
	public List<Object[]> getLockedCustomerForZipMovement(List<Integer> custIds,
			List<Integer> statusList,List<Integer> triggerIds, Short tenantId) {

		List queryParam = new ArrayList();
		
		queryParam.add(custIds);
		queryParam.add(statusList);
		queryParam.add(triggerIds);
		queryParam.add(tenantId);
		

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustLockedForZipMovement", queryParam, 0, -1);

	}
	/**
	 * Gets the cust info by algnmt id.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @return the cust info by algnmt id
	 */
	@Override
	public List<Object[]> getCustInfoByAlgnmtId(List<Long> custAlgmntId) {
		
	List queryParam = new ArrayList();
		
		queryParam.add(custAlgmntId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"getCustInfoByAlgnmtId", queryParam, 0, -1);
	}
	/**
	 * Creates the t cust algmnt chng req det.
	 *
	 * @param tCustAlgmntChngReqDetList the t cust algmnt chng req det list
	 * @return the list
	 */
	@Override
	public List<TCustAlgmntChngReqDet> createTCustAlgmntChngReqDet(List<TCustAlgmntChngReqDet> tCustAlgmntChngReqDetList) {

		return genericDAO.storeBatch(tCustAlgmntChngReqDetList);
	}
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TCustAlgmntChngReqDetDAO#updateCustAlgmntCRDetStatus(long, long, java.lang.Integer, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateCustAlgmntCRDetStatus(long custAlgmntId, long changeRequestId,
			Integer statusId, Integer userId, Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
        paramList.add(statusId);
        paramList.add(DateUtil.getCurrentDate());
        paramList.add(userId);
        paramList.add(changeRequestId);
        paramList.add(custAlgmntId);
        paramList.add(tenantId);     
        genericDAO.updateEntitiesNamedQuery("updateCustAlgCRDetStatus", paramList);
	}
	
	@Override
	public List<Object[]> getAllCrIdInTCustAffChngReqDets(Short tenantId){
		List paramList = new ArrayList();
		paramList.add(tenantId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAllCrIdInTCustAffChngReqDets", paramList, 0, -1);
	}
	
}
