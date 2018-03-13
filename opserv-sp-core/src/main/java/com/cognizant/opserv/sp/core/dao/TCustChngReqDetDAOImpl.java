package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustChngReqDetId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustChngReqDetDAO")
public class TCustChngReqDetDAOImpl implements TCustChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";
	private static final String TCUST = "tCust";
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";

	private final Class<TCustChngReqDet> clazz;

	public Class<TCustChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustChngReqDetDAOImpl() {
		super();
		this.clazz = TCustChngReqDet.class;
	}

	private static final String JPAQL = "select tCustChngReqDetentity from TCustChngReqDet tCustChngReqDetentity";

	private static final String JPACOUNTQL = "select count(*) from TCustChngReqDet tCustChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tCustChngReqDetentity.tCustChngReqDetId.chngReqId = #{tCustChngReqDetentity.tCustChngReqDetId.getChngReqId}",
			"tCustChngReqDetentity.tCustChngReqDetId.chngReqDetailId = #{tCustChngReqDetentity.tCustChngReqDetId.getChngReqDetailId}",
			"tCustChngReqDetentity.tCustChngReqDetId.custId = #{tCustChngReqDetentity.tCustChngReqDetId.getCustId}",
			"tCustChngReqDetentity.statusId = #{tCustChngReqDetentity.getStatusId}",
			"tCustChngReqDetentity.createdBy = #{tCustChngReqDetentity.getCreatedBy}",
			"Date(tCustChngReqDetentity.createDt) = '#{tCustChngReqDetentity.getCreateDt}'",
			"tCustChngReqDetentity.updatedBy = #{tCustChngReqDetentity.getUpdatedBy}",
			"Date(tCustChngReqDetentity.updateDt) = '#{tCustChngReqDetentity.getUpdateDt}'",
			"tCustChngReqDetentity.tenantId = #{tCustChngReqDetentity.getTenantId}",
			"tCustChngReqDetentity.tChngReq.chngReqId = #{tCustChngReqDetentity.tChngReq.getChngReqId}",
			"tCustChngReqDetentity.tCust.custId = #{tCustChngReqDetentity.tCust.getCustId}",
			"tCustChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tCustChngReqDetentity.tChngReqBussReason.getChngReqBrId}" };

	/**
	 * Stores a new TCustChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be persisted
	 * @return tCustChngReqDet Persisted TCustChngReqDet object
	 */
	public TCustChngReqDet createTCustChngReqDet(final TCustChngReqDet tCustChngReqDet) {
		LOGGER.info("=========== Create TCustChngReqDet ===========");
		return genericDAO.store(tCustChngReqDet);
	}

	/**
	 * Deletes a TCustChngReqDet entity object from the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be deleted
	 */
	public void deleteTCustChngReqDet(final TCustChngReqDetId tCustChngReqDetId) {
		LOGGER.info("=========== Delete TCustChngReqDet ===========");
		final TCustChngReqDet tCustChngReqDet = genericDAO.get(clazz, tCustChngReqDetId);
		genericDAO.remove(tCustChngReqDet);
	}

	/**
	 * Updates a TCustChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustChngReqDet
	 *            TCustChngReqDet Entity object to be updated
	 * @return tCustChngReqDet Persisted TCustChngReqDet object
	 */
	public TCustChngReqDet updateTCustChngReqDet(final TCustChngReqDet tCustChngReqDet) {
		LOGGER.info("=========== Update TCustChngReqDet ===========");
		return genericDAO.update(tCustChngReqDet);
	}

	/**
	 * Retrieve an TCustChngReqDet object based on given TCustChngReqDet TCustChngReqDetId.
	 * 
	 * @param tCustChngReqDetId
	 *            the primary key value of the TCustChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustChngReqDet findTCustChngReqDetById(final TCustChngReqDetId tCustChngReqDetId) {
		LOGGER.info("find TCustChngReqDet instance with TCustChngReqDetId: " + tCustChngReqDetId);
		return genericDAO.get(clazz, tCustChngReqDetId);
	}

	/**
	 * Retrieve TCustChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustChngReqDet> list of TCustChngReqDet if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustChngReqDet> findTCustChngReqDets(final SearchFilter<TCustChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TCustChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustChngReqDet tCustChngReqDet = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustChngReqDetentity", tCustChngReqDet);

		if (tCustChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tCustChngReqDet.getTChngReq());
		}

		if (tCustChngReqDet.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}" + tCustChngReqDet.getTCust());

			jpaQuery.bind(TCUST, tCustChngReqDet.getTCust());
		}
		if (tCustChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tCustChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustChngReqDets(final SearchFilter<TCustChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TCustChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustChngReqDet tCustChngReqDet = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustChngReqDetentity", tCustChngReqDet);

		if (tCustChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tCustChngReqDet.getTChngReq());
		}

		if (tCustChngReqDet.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}" + tCustChngReqDet.getTCust());

			jpaCountQuery.bind(TCUST, tCustChngReqDet.getTCust());
		}
		if (tCustChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tCustChngReqDet.getTChngReqBussReason());
		}
		
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

//	/**
//	 * Retrieve TCustChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustChngReqDet> list of TCustChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	public List<TCustChngReqDet> getTCustChngReqDetsByTChngReqDetail(final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustChngReqDetByTChngReqDetail", tChngReqDetailList, index,
//				maxresult);
//	}
//
//	/**
//	 * Count TCustChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTCustChngReqDetsByTChngReqDetail(final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		return genericDAO.findEntitiesByNamedQuery("CountTCustChngReqDetsByTChngReqDetail", tChngReqDetailList);
//	}

	/**
	 * Retrieve TCustChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustChngReqDet> list of TCustChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustChngReqDet> getTCustChngReqDetsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustChngReqDetByTCust", tCustList, index, maxresult);
	}

	/**
	 * Count TCustChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustChngReqDetsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustChngReqDetsByTCust", tCustList);
	}

}
