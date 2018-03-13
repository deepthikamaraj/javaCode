package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.core.entity.TCustCallPlanChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustCallPlanChngReqDetId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustCallPlanChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustCallPlanChngReqDetDAO")
public class TCustCallPlanChngReqDetDAOImpl implements
		TCustCallPlanChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustCallPlanChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";
	private static final String TCUSTCALLPLAN = "tCustCallPlan";
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";

	private final Class<TCustCallPlanChngReqDet> clazz;

	public Class<TCustCallPlanChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustCallPlanChngReqDetDAOImpl() {
		super();
		this.clazz = TCustCallPlanChngReqDet.class;
	}

	private static final String JPAQL = "select tCustCallPlanChngReqDetentity from TCustCallPlanChngReqDet tCustCallPlanChngReqDetentity";

	private static final String JPACOUNTQL = "select count(*) from TCustCallPlanChngReqDet tCustCallPlanChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.custCallPlanId = #{tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.getCustCallPlanId}",
			"tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.chngReqId = #{tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.getChngReqId}",
			"tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.chngReqDetailId = #{tCustCallPlanChngReqDetentity.tCustCallPlanChngReqDetId.getChngReqDetailId}",
			"tCustCallPlanChngReqDetentity.statusId = #{tCustCallPlanChngReqDetentity.getStatusId}",
			"tCustCallPlanChngReqDetentity.createdBy = #{tCustCallPlanChngReqDetentity.getCreatedBy}",
			"Date(tCustCallPlanChngReqDetentity.createDt) = '#{tCustCallPlanChngReqDetentity.getCreateDt}'",
			"tCustCallPlanChngReqDetentity.updatedBy = #{tCustCallPlanChngReqDetentity.getUpdatedBy}",
			"Date(tCustCallPlanChngReqDetentity.updateDt) = '#{tCustCallPlanChngReqDetentity.getUpdateDt}'",
			"tCustCallPlanChngReqDetentity.tenantId = #{tCustCallPlanChngReqDetentity.getTenantId}",
			"tCustCallPlanChngReqDetentity.tChngReq.chngReqId = #{tCustCallPlanChngReqDetentity.tChngReq.getChngReqId}",
			"tCustCallPlanChngReqDetentity.tCustCallPlan.custCallPlanId = #{tCustCallPlanChngReqDetentity.tCustCallPlan.getCustCallPlanId}" ,
			"tCustCallPlanChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tCustCallPlanChngReqDetentity.tChngReqBussReason.getChngReqBrId}"};

	/**
	 * Stores a new TCustCallPlanChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be persisted
	 * @return tCustCallPlanChngReqDet Persisted TCustCallPlanChngReqDet object
	 */
	public TCustCallPlanChngReqDet createTCustCallPlanChngReqDet(
			final TCustCallPlanChngReqDet tCustCallPlanChngReqDet) {
		LOGGER.info("=========== Create TCustCallPlanChngReqDet ===========");
		return genericDAO.store(tCustCallPlanChngReqDet);
	}

	/**
	 * Deletes a TCustCallPlanChngReqDet entity object from the persistent store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be deleted
	 */
	public void deleteTCustCallPlanChngReqDet(
			final TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId) {
		LOGGER.info("=========== Delete TCustCallPlanChngReqDet ===========");
		final TCustCallPlanChngReqDet tCustCallPlanChngReqDet = genericDAO.get(
				clazz, tCustCallPlanChngReqDetId);
		genericDAO.remove(tCustCallPlanChngReqDet);
	}

	/**
	 * Updates a TCustCallPlanChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tCustCallPlanChngReqDet
	 *            TCustCallPlanChngReqDet Entity object to be updated
	 * @return tCustCallPlanChngReqDet Persisted TCustCallPlanChngReqDet object
	 */
	public TCustCallPlanChngReqDet updateTCustCallPlanChngReqDet(
			final TCustCallPlanChngReqDet tCustCallPlanChngReqDet) {
		LOGGER.info("=========== Update TCustCallPlanChngReqDet ===========");
		return genericDAO.update(tCustCallPlanChngReqDet);
	}
	
	
	public List<TCustCallPlanChngReqDet> updateTCustCallPlanChngReqDet(
			final List<TCustCallPlanChngReqDet> tCustCallPlanChngReqDetList) {
		LOGGER.info("=========== Update TCustCallPlanChngReqDet ===========");
		return genericDAO.update(tCustCallPlanChngReqDetList);
	}

	/**
	 * Retrieve an TCustCallPlanChngReqDet object based on given
	 * TCustCallPlanChngReqDet TCustCallPlanChngReqDetId.
	 * 
	 * @param tCustCallPlanChngReqDetId
	 *            the primary key value of the TCustCallPlanChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustCallPlanChngReqDet findTCustCallPlanChngReqDetById(
			final TCustCallPlanChngReqDetId tCustCallPlanChngReqDetId) {
		LOGGER.info("find TCustCallPlanChngReqDet instance with TCustCallPlanChngReqDetId: "
				+ tCustCallPlanChngReqDetId);
		return genericDAO.get(clazz, tCustCallPlanChngReqDetId);
	}

	/**
	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDet if
	 *         it exists against given criteria. Returns null if not found
	 */
	public List<TCustCallPlanChngReqDet> findTCustCallPlanChngReqDets(
			final SearchFilter<TCustCallPlanChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TCustCallPlanChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustCallPlanChngReqDet tCustCallPlanChngReqDet = searchFilter
				.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustCallPlanChngReqDetentity",
				tCustCallPlanChngReqDet);

		if (tCustCallPlanChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustCallPlanChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tCustCallPlanChngReqDet.getTChngReq());
		}


		if (tCustCallPlanChngReqDet.getTCustCallPlan() == null) {
			jpaQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}" +
					tCustCallPlanChngReqDet.getTCustCallPlan());

			jpaQuery.bind(TCUSTCALLPLAN,
					tCustCallPlanChngReqDet.getTCustCallPlan());
		}
		if (tCustCallPlanChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustCallPlanChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tCustCallPlanChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustCallPlanChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustCallPlanChngReqDets(
			final SearchFilter<TCustCallPlanChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TCustCallPlanChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustCallPlanChngReqDet tCustCallPlanChngReqDet = searchFilter
				.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery(
				"tCustCallPlanChngReqDetentity", tCustCallPlanChngReqDet);

		if (tCustCallPlanChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustCallPlanChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tCustCallPlanChngReqDet.getTChngReq());
		}

		if (tCustCallPlanChngReqDet.getTCustCallPlan() == null) {
			jpaCountQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}" +
					tCustCallPlanChngReqDet.getTCustCallPlan());

			jpaCountQuery.bind(TCUSTCALLPLAN,
					tCustCallPlanChngReqDet.getTCustCallPlan());
		}
		if (tCustCallPlanChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustCallPlanChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tCustCallPlanChngReqDet.getTChngReqBussReason());
		}
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

//	/**
//	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDets if
//	 *         it exists against given criteria. Returns null if not found
//	 */
//	public List<TCustCallPlanChngReqDet> getTCustCallPlanChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlanChngReqDetByTChngReqDetail", tChngReqDetailList,
//				index, maxresult);
//	}

//	/**
//	 * Count TCustCallPlanChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTCustCallPlanChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		return genericDAO
//				.findEntitiesByNamedQuery(
//						"CountTCustCallPlanChngReqDetsByTChngReqDetail",
//						tChngReqDetailList);
//	}

	/**
	 * Retrieve TCustCallPlanChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlanChngReqDet> list of TCustCallPlanChngReqDets if
	 *         it exists against given criteria. Returns null if not found
	 */
	public List<TCustCallPlanChngReqDet> getTCustCallPlanChngReqDetsByTCustCallPlan(
			final SearchFilter<TCustCallPlan> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		tCustCallPlanList.add(tCustCallPlan);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlanChngReqDetByTCustCallPlan", tCustCallPlanList,
				index, maxresult);
	}

	/**
	 * Count TCustCallPlanChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustCallPlanChngReqDetsByTCustCallPlan(
			final SearchFilter<TCustCallPlan> searchFilter) {

		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		tCustCallPlanList.add(tCustCallPlan);
		return genericDAO.findEntitiesByNamedQuery(
				"CountTCustCallPlanChngReqDetsByTCustCallPlan", tCustCallPlanList);
	}
	/**
	 * Fetch detail list.
	 *
	 * @param params the params
	 * @param Query the query
	 * @return the list
	 */
	public List<TCustCallPlanChngReqDet> fetchDetailList(List<Object> params,
			String Query) {
		return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0,
				-1);
	}

	/* Added By 407986 To fetch all CustomerAffiliationReqDetails by ChngReqId */
	/**
	 * Gets the customer callplan req details.
	 * 
	 * @param params
	 *            the params
	 * @return the customer callplan req details
	 */

	public List<Object> getCustomerCallPlanReqDetails(List params) {

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetCustomerCallPlanReqDetails", params, 0, -1);
	}

	/**
	 * Added by 408372 to fetch CallPlanCRStatus based on the customerCallPlanId
	 * and tenant Id
	 */
	@Override
	public TCustCallPlanChngReqDet getCustCallPlanChangeReqDtl(
			Integer custCallPlanId, Short tenantId) {
		// TODO Auto-generated method stub
		List params = new ArrayList<>();
		params.add(custCallPlanId);
		params.add(tenantId);
		List<TCustCallPlanChngReqDet> tCustCallPlanChngReqDetList = genericDAO
				.findEntitiesByNamedQueryMultiCond(
						"GetCustCallPlanChangeReqDtl", params, 0, -1);
		if (tCustCallPlanChngReqDetList != null
				&& !(tCustCallPlanChngReqDetList.isEmpty()))
			return tCustCallPlanChngReqDetList.get(0);
		else
			return null;
	}

	/**
	 * 
	 * @author 409793 to fetch customer id, if CR forAdd/Remove Customer
	 *         CallPlan is there on same SP for pending submission OR approval
	 *         status and Customer is present in that CR.
	 */
	@Override
	public List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId, Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(custId);
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustLockedInCallPlan", queryParam, 0, -1);

	}
	/**
	 * Gets the locked customer for callplan req details.
	 * 
	 * @param callPlanIds
	 *            the callPlanIds
	 * @Param statusList
	 *             statusList
	 *@Param tenantId
	 *           TenantId
	 * @return the Locked customer callplan 
	 */
	// Added By 407986 To fetch All the Locked Call Plans
	public List<Object> fetchLockedCustCallPlan(List<Integer> callPlanIds,
			List<Integer> statusList, Short tenantId) {

		List<Object> queryParams = new ArrayList<>();
		queryParams.add(callPlanIds);
		queryParams.add(statusList);
		queryParams.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchLockedCustCallPlan", queryParams, 0, -1);
	}
	/**
	 * Gets the locked customer for callplan req details.
	 * 
	 * @param callPlanIds
	 *            the callPlanIds
	 * @Param statusList
	 *             statusList
	 *@Param tenantId
	 *           TenantId
	 * @return the Locked customer callplan 
	 */
	// Added By 407986 To fetch All Locked Customers For the Call Plan
		public List<Object[]> fetchLockedCustByCallPlan(List<Integer> callPlanIds,
				List<Integer> statusList, Short tenantId) {

			List<Object> queryParams = new ArrayList<>();
			queryParams.add(callPlanIds);
			queryParams.add(statusList);
			queryParams.add(tenantId);
			

			return genericDAO.findEntitiesByNamedQueryMultiCond(
					"FetchLockedCustByCallPlanId", queryParams, 0, -1);
		}

}
