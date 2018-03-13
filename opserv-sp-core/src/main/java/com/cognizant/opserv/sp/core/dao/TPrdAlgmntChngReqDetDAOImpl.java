package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntChngReqDetId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdAlgmntChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdAlgmntChngReqDetDAO")
public class TPrdAlgmntChngReqDetDAOImpl implements TPrdAlgmntChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdAlgmntChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRDALGMNT = "tPrdAlgmnt";
	private static final String TCHNGREQ = "tChngReq";
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";

	private final Class<TPrdAlgmntChngReqDet> clazz;

	public Class<TPrdAlgmntChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdAlgmntChngReqDetDAOImpl() {
		super();
		this.clazz = TPrdAlgmntChngReqDet.class;
	}

	private static final String JPAQL = "select tPrdAlgmntChngReqDetentity from TPrdAlgmntChngReqDet tPrdAlgmntChngReqDetentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdAlgmntChngReqDet tPrdAlgmntChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.prdAlgmntId = #{tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.getPrdAlgmntId}",
			"tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.chngReqDetailId = #{tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.getChngReqDetailId}",
			"tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.chngReqId = #{tPrdAlgmntChngReqDetentity.tPrdAlgmntChngReqDetId.getChngReqId}",
			"tPrdAlgmntChngReqDetentity.statusId = #{tPrdAlgmntChngReqDetentity.getStatusId}",
			"tPrdAlgmntChngReqDetentity.createdBy = #{tPrdAlgmntChngReqDetentity.getCreatedBy}",
			"Date(tPrdAlgmntChngReqDetentity.createDt) = '#{tPrdAlgmntChngReqDetentity.getCreateDt}'",
			"tPrdAlgmntChngReqDetentity.updatedBy = #{tPrdAlgmntChngReqDetentity.getUpdatedBy}",
			"Date(tPrdAlgmntChngReqDetentity.updateDt) = '#{tPrdAlgmntChngReqDetentity.getUpdateDt}'",
			"tPrdAlgmntChngReqDetentity.tenantId = #{tPrdAlgmntChngReqDetentity.getTenantId}",
			"tPrdAlgmntChngReqDetentity.tPrdAlgmnt.prdAlgmntId = #{tPrdAlgmntChngReqDetentity.tPrdAlgmnt.getPrdAlgmntId}",
			"tPrdAlgmntChngReqDetentity.tChngReq.chngReqId = #{tPrdAlgmntChngReqDetentity.tChngReq.getChngReqId}" ,
			"tPrdAlgmntChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tPrdAlgmntChngReqDetentity.tChngReqBussReason.getChngReqBrId}"};

	/**
	 * Stores a new TPrdAlgmntChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tPrdAlgmntChngReqDet
	 *            TPrdAlgmntChngReqDet Entity object to be persisted
	 * @return tPrdAlgmntChngReqDet Persisted TPrdAlgmntChngReqDet object
	 */
	public TPrdAlgmntChngReqDet createTPrdAlgmntChngReqDet(
			final TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet) {
		LOGGER.info("=========== Create TPrdAlgmntChngReqDet ===========");
		return genericDAO.store(tPrdAlgmntChngReqDet);
	}

	/**
	 * Deletes a TPrdAlgmntChngReqDet entity object from the persistent store
	 * 
	 * @param tPrdAlgmntChngReqDet
	 *            TPrdAlgmntChngReqDet Entity object to be deleted
	 */
	public void deleteTPrdAlgmntChngReqDet(
			final TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId) {
		LOGGER.info("=========== Delete TPrdAlgmntChngReqDet ===========");
		final TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet = genericDAO.get(clazz,
				tPrdAlgmntChngReqDetId);
		genericDAO.remove(tPrdAlgmntChngReqDet);
	}

	/**
	 * Updates a TPrdAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tPrdAlgmntChngReqDet
	 *            TPrdAlgmntChngReqDet Entity object to be updated
	 * @return tPrdAlgmntChngReqDet Persisted TPrdAlgmntChngReqDet object
	 */
	public TPrdAlgmntChngReqDet updateTPrdAlgmntChngReqDet(
			final TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet) {
		LOGGER.info("=========== Update TPrdAlgmntChngReqDet ===========");
		return genericDAO.update(tPrdAlgmntChngReqDet);
	}
	
	
	public List<TPrdAlgmntChngReqDet> updateTPrdAlgmntChngReqDet(
			final List<TPrdAlgmntChngReqDet> tPrdAlgmntChngReqDetList) {
		LOGGER.info("=========== Update TPrdAlgmntChngReqDet ===========");
		return genericDAO.update(tPrdAlgmntChngReqDetList);
	}

	/**
	 * Retrieve an TPrdAlgmntChngReqDet object based on given
	 * TPrdAlgmntChngReqDet TPrdAlgmntChngReqDetId.
	 * 
	 * @param tPrdAlgmntChngReqDetId
	 *            the primary key value of the TPrdAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdAlgmntChngReqDet findTPrdAlgmntChngReqDetById(
			final TPrdAlgmntChngReqDetId tPrdAlgmntChngReqDetId) {
		LOGGER.info("find TPrdAlgmntChngReqDet instance with TPrdAlgmntChngReqDetId: "
				+ tPrdAlgmntChngReqDetId);
		return genericDAO.get(clazz, tPrdAlgmntChngReqDetId);
	}

	/**
	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDet if it
	 *         exists against given criteria. Returns null if not found
	 */
	public List<TPrdAlgmntChngReqDet> findTPrdAlgmntChngReqDets(
			final SearchFilter<TPrdAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TPrdAlgmntChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet = searchFilter
				.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdAlgmntChngReqDetentity",
				tPrdAlgmntChngReqDet);

		if (tPrdAlgmntChngReqDet.getTPrdAlgmnt() == null) {
			jpaQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}"+ tPrdAlgmntChngReqDet.getTPrdAlgmnt());

			jpaQuery.bind(TPRDALGMNT, tPrdAlgmntChngReqDet.getTPrdAlgmnt());
		}

		if (tPrdAlgmntChngReqDet.getTPrdAlgmnt() == null) {
			jpaQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}", tPrdAlgmntChngReqDet.getTPrdAlgmnt());

			jpaQuery.bind(TPRDALGMNT, tPrdAlgmntChngReqDet.getTPrdAlgmnt());
		}

		if (tPrdAlgmntChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tPrdAlgmntChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tPrdAlgmntChngReqDet.getTChngReq());
		}
		if (tPrdAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tPrdAlgmntChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tPrdAlgmntChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdAlgmntChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdAlgmntChngReqDets(
			final SearchFilter<TPrdAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TPrdAlgmntChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet = searchFilter
				.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery(
				"tPrdAlgmntChngReqDetentity", tPrdAlgmntChngReqDet);

		if (tPrdAlgmntChngReqDet.getTPrdAlgmnt() == null) {
			jpaCountQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}"+ tPrdAlgmntChngReqDet.getTPrdAlgmnt());

			jpaCountQuery
					.bind(TPRDALGMNT, tPrdAlgmntChngReqDet.getTPrdAlgmnt());
		}
		if (tPrdAlgmntChngReqDet.getTPrdAlgmnt() == null) {
			jpaCountQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}", tPrdAlgmntChngReqDet.getTPrdAlgmnt());

			jpaCountQuery.bind(TPRDALGMNT, tPrdAlgmntChngReqDet.getTPrdAlgmnt());
		}

		if (tPrdAlgmntChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tPrdAlgmntChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tPrdAlgmntChngReqDet.getTChngReq());
		}
		if (tPrdAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tPrdAlgmntChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tPrdAlgmntChngReqDet.getTChngReqBussReason());
		}
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using JPA
	 * named Query. The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDets if it
	 *         exists against given criteria. Returns null if not found
	 */
	public List<TPrdAlgmntChngReqDet> getTPrdAlgmntChngReqDetsByTPrdAlgmnt(
			final SearchFilter<TPrdAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAlgmntChngReqDetByTPrdAlgmnt", queryParam, index,
				maxresult);
	}

	/**
	 * Count TPrdAlgmntChngReqDet based on given search criteria using JPA named
	 * Query. The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAlgmntChngReqDetsByTPrdAlgmnt(
			final SearchFilter<TPrdAlgmnt> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery(
				"CountTPrdAlgmntChngReqDetsByTPrdAlgmnt", queryParam);
	}

//	/**
//	 * Retrieve TPrdAlgmntChngReqDet based on given search criteria using JPA
//	 * named Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TPrdAlgmntChngReqDet> list of TPrdAlgmntChngReqDets if it
//	 *         exists against given criteria. Returns null if not found
//	 */
//	public List<TPrdAlgmntChngReqDet> getTPrdAlgmntChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		List<Object> queryParam=new ArrayList<Object> ();
//		queryParam.add(searchFilter.getEntityClass());
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond(
//				"FindTPrdAlgmntChngReqDetByTChngReqDetail", queryParam,
//				index, maxresult);
//	}
//
//	/**
//	 * Count TPrdAlgmntChngReqDet based on given search criteria using JPA named
//	 * Query. The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTPrdAlgmntChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		List<Object> queryParam=new ArrayList<Object> ();
//		queryParam.add(searchFilter.getEntityClass());
//		return genericDAO.findEntitiesByNamedQuery(
//				"CountTPrdAlgmntChngReqDetsByTChngReqDetail", queryParam);
//	}

	public List<Object> getProductReqDetails(List params) {

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetProductReqDetails", params, 0, -1);
	}

	@Override
	public TPrdAlgmntChngReqDet getTPrdAlgmntChngReqDetsByPrdAlgmntId(
			Long prdAlgmntId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(prdAlgmntId);
		TPrdAlgmntChngReqDet tPrdAlgmntChngReqDet = new TPrdAlgmntChngReqDet();
		List<TPrdAlgmntChngReqDet> list = genericDAO.findEntitiesByNamedQuery(
				"getTPrdAlgmntChngReqDetsByPrdAlgmntId", queryParam);
		if (list != null && list.size() > 0) {
			tPrdAlgmntChngReqDet = list.get(0);
		} else {
			tPrdAlgmntChngReqDet = null;
		}
		return tPrdAlgmntChngReqDet;
	}

	public List<TPrdAlgmntChngReqDet> fetchDetailList(List<Object> params,
			String Query) {
		return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0,
				-1);
	}

	@Override
	public List<Object[]> getPrdPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId, Short tenantID) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(prdId);
		queryParam.add(tenantID);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForPrdLocked", queryParam, 0, -1);

	}

	@Override
	public List<Object[]> getPrdPendingInCallPlan(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId, Short tenantID) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(prdId);
		queryParam.add(tenantID);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForPrdInCallPlan", queryParam, 0, -1);

	}

	@Override
	public List<Object[]> getPrdPendingInCallDir(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<String> prdId, Short tenantID) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(prdId);
		queryParam.add(tenantID);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForPrdInCallDir", queryParam, 0, -1);

	}

}
