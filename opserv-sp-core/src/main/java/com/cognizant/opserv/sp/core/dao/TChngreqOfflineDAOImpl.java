package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngreqOfflineDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngreqOfflineDAO")
public class TChngreqOfflineDAOImpl implements TChngreqOfflineDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TChngreqOfflineDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";

	private final Class<TChngreqOffline> clazz;

	public Class<TChngreqOffline> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngreqOfflineDAOImpl() {
		super();
		this.clazz = TChngreqOffline.class;
	}

	private static final String JPAQL = "select tChngreqOfflineEntity from TChngreqOffline tChngreqOfflineEntity";

	private static final String JPACOUNTQL = "select count(tChngreqOfflineEntity) from TChngreqOffline tChngreqOfflineEntity";

	private static final String[] RESTRICTIONS = { "tChngreqOfflineEntity.offlineId = #{tChngreqOfflineEntity.getOfflineId}",
			"tChngreqOfflineEntity.triggerId = #{tChngreqOfflineEntity.getTriggerId}",
			"tChngreqOfflineEntity.statusId = #{tChngreqOfflineEntity.getStatusId}",
			"tChngreqOfflineEntity.reqDetail = #{tChngreqOfflineEntity.getReqDetail}",
			"tChngreqOfflineEntity.failedReason like '#{tChngreqOfflineEntity.getFailedReason}%'",
			"tChngreqOfflineEntity.tChngReq.chngReqId = #{tChngreqOfflineEntity.tChngReq.getChngReqId}" };

	/**
	 * Stores a new TChngreqOffline entity object in to the persistent store
	 * 
	 * @param tChngreqOffline
	 *            TChngreqOffline Entity object to be persisted
	 * @return tChngreqOffline Persisted TChngreqOffline object
	 */
	public TChngreqOffline createTChngreqOffline(final TChngreqOffline tChngreqOffline) {
		LOGGER.info("=========== Create TChngreqOffline ===========");
		return genericDAO.store(tChngreqOffline);
	}

	/**
	 * Deletes a TChngreqOffline entity object from the persistent store
	 * 
	 * @param tChngreqOffline
	 *            TChngreqOffline Entity object to be deleted
	 */
	public void deleteTChngreqOffline(final Long offlineId) {
		LOGGER.info("=========== Delete TChngreqOffline ===========");
		final TChngreqOffline tChngreqOffline = genericDAO.get(clazz, offlineId);
		genericDAO.remove(tChngreqOffline);
	}

	/**
	 * Updates a TChngreqOffline entity object in to the persistent store
	 * 
	 * @param tChngreqOffline
	 *            TChngreqOffline Entity object to be updated
	 * @return tChngreqOffline Persisted TChngreqOffline object
	 */
	public TChngreqOffline updateTChngreqOffline(final TChngreqOffline tChngreqOffline) {
		LOGGER.info("=========== Update TChngreqOffline ===========");
		return genericDAO.update(tChngreqOffline);
	}

	/**
	 * Retrieve an TChngreqOffline object based on given TChngreqOffline offlineId.
	 * 
	 * @param tChngreqOfflineId
	 *            the primary key value of the TChngreqOffline Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngreqOffline findTChngreqOfflineById(final Long tChngreqOfflineId) {
		LOGGER.info("find TChngreqOffline instance with offlineId: " + tChngreqOfflineId);
		return genericDAO.get(clazz, tChngreqOfflineId);
	}

	/**
	 * Retrieve TChngreqOffline based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngreqOffline> list of TChngreqOffline if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngreqOffline> findTChngreqOfflines(final SearchFilter<TChngreqOffline> searchFilter) {
		LOGGER.info("=========== Find TChngreqOfflines ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngreqOffline tChngreqOffline = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngreqOfflineEntity", tChngreqOffline);

		if (tChngreqOffline.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tChngreqOffline.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tChngreqOffline.getTChngReq());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngreqOfflines.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngreqOfflines(final SearchFilter<TChngreqOffline> searchFilter) {
		LOGGER.info("=========== Count TChngreqOffline ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngreqOffline tChngreqOffline = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngreqOfflineEntity", tChngreqOffline);

		if (tChngreqOffline.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tChngreqOffline.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tChngreqOffline.getTChngReq());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngreqOffline based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngreqOffline> list of TChngreqOfflines if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngreqOffline> getTChngreqOfflinesByTChngReq(final SearchFilter<TChngReq> searchFilter) {

		
		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(tChngReq);
		int maxresult = -1;
		int index = -1;
		if(null != searchFilter.getPaginationInfo()){
			final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
			maxresult = paginationInfo.getMaxRows();
			index = paginationInfo.getStartIndex();
		}
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngreqOfflineByTChngReq", queryParams, index, maxresult);
	}

	/**
	 * Count TChngreqOffline based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngreqOfflinesByTChngReq(final SearchFilter<TChngReq> searchFilter) {

		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(tChngReq);
		return genericDAO.findEntitiesByNamedQuery("CountTChngreqOfflinesByTChngReq", queryParams);
	}

	/**
	 * Retrieve TChngreqOffline based on CRID and Tenant Id
	 * 
	 * @param crID
	 * @param tenantId
	 * @return List<TChngreqOffline>
	 */
	@Override
	public List<TChngreqOffline> fetchTChngreqOffline(Long crID, short tenantId) {
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(crID);
		queryParams.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchTChngreqOfflineByTChngReq", queryParams, 0, -1);
	}

	/**
	 * Fetch temp cr info for offline status Initiated(1).
	 *
	 * @param tempCRId the temp cr id
	 * @param userDetails the user details
	 * @return the temp cr
	 */
	@Override
	public List<TChngreqOffline> fetchTChngreqOfflineByOfflineId(Long tempCrID, short tenantId) {
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(tempCrID);
		queryParams.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchTChngreqOfflineByOfflineId", queryParams, 0, -1);
	}

	/**
	 * update TChngreqOffline By StatusId
	 * 
	 * @param stausId
	 *            -Status Id
	 * @param crOfflineId
	 *            - offline Cr Id
	 * @param userId
	 *            - user Id
	 * @param tenantId
	 *            - Tenant Id
	 * @param failedReason
	 *            - failed Reason
	 */
	@Override
	public void updateTChngreqOfflineByStatusId(Integer stausId, Long crOfflineId, Integer userId,
			Short tenantId, String failedReason) {
		
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(stausId);
		queryParams.add(DateUtil.getCurrentDate());
		queryParams.add(userId);
		queryParams.add(crOfflineId);
		queryParams.add(tenantId);
		queryParams.add(failedReason);
		
		 genericDAO.updateEntitiesNamedQuery("updateTChngreqOfflineByStatusId", queryParams);
	}

	@Override
	public long countOfOfflineCRStatus(Long CrId, Short tenantId) {
		List<Object> queryParams = new ArrayList<Object>();
		queryParams.add(CrId);
		queryParams.add(tenantId);
		
		List countOfOfflineCRStatus = genericDAO.findEntitiesByNamedQueryMultiCond("countOfOfflineCRStatus", queryParams, 0, -1);
		return (Long) countOfOfflineCRStatus.get(0);
	}

	/**
	 * checkStatusOfTChngreqOffline
	 * @param offlineID
	 * @param tenantId
	 * @return Long
	 */
	@Override
	public Long checkStatusOfTChngreqOffline(Long offlineID,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(offlineID);
		paramList.add(tenantId);
		List<Long> count = genericDAO.findEntitiesByNamedQueryMultiCond("countOfTChngreqOfflineByStatus",paramList,0,-1);
		LOGGER.info("******************Count is" + count + " for CROffline data for Offline Id == " + offlineID + "with status as Iniaited.*****************");
		return count.get(0);
	}
	
	/**
	 * getTChngreqOfflineByStatusId
	 * @param statusId
	 * @param userDetails
	 * @return List
	 */
	@Override
	public List<TChngreqOffline> getTChngreqOfflineByStatusId(Integer statusId, Short tenantId) {
		LOGGER.info("******************getTChngreqOfflineByStatusId for status Id == " + statusId);
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(statusId);
		paramList.add(tenantId);
		List<TChngreqOffline> chngeReqOfflineList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngreqOfflineByStatus",paramList,0,-1);
		return chngeReqOfflineList;
	}
	
	/**
	 * Find status of t chngreq offline.
	 *
	 * @param offlineReqId the offline req id
	 * @param userDetails the user details
	 * @return the integer
	 */
	@Override
	public Integer findStatusOfTChngreqOffline(Long offlineID,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(offlineID);
		paramList.add(tenantId);
		List<Integer> status = genericDAO.findEntitiesByNamedQueryMultiCond("FindStatusOfTChngreqOffline",paramList,0,-1);
		LOGGER.info("******************Status is" + status.get(0) + " for CROffline data for Offline Id == " + offlineID + "*****************");
		return status.get(0);
	}
	
}
