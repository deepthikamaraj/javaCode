package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTerrZipAlgmntChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTerrZipAlgmntChngReqDetDAO")
public class TTerrZipAlgmntChngReqDetDAOImpl implements
		TTerrZipAlgmntChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TTerrZipAlgmntChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";
	
	private final Class<TTerrZipAlgmntChngReqDet> clazz;

	public Class<TTerrZipAlgmntChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTerrZipAlgmntChngReqDetDAOImpl() {
		super();
		this.clazz = TTerrZipAlgmntChngReqDet.class;
	}

	private static final String JPAQL = "select tTerrZipAlgmntChngReqDetentity from TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDetentity";

	private static final String JPACOUNTQL = "select count(tTerrZipAlgmntChngReqDetentity) from TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tTerrZipAlgmntChngReqDetentity.zipChngReqId = #{tTerrZipAlgmntChngReqDetentity.getZipChngReqId}",
			"tTerrZipAlgmntChngReqDetentity.chngReqId = #{tTerrZipAlgmntChngReqDetentity.getChngReqId}",
			"tTerrZipAlgmntChngReqDetentity.chngReqDetailId = #{tTerrZipAlgmntChngReqDetentity.getChngReqDetailId}",
			"tTerrZipAlgmntChngReqDetentity.statusId = #{tTerrZipAlgmntChngReqDetentity.getStatusId}",
			"tTerrZipAlgmntChngReqDetentity.createdBy = #{tTerrZipAlgmntChngReqDetentity.getCreatedBy}",
			"Date(tTerrZipAlgmntChngReqDetentity.createDt) = '#{tTerrZipAlgmntChngReqDetentity.getCreateDt}'",
			"tTerrZipAlgmntChngReqDetentity.updatedBy = #{tTerrZipAlgmntChngReqDetentity.getUpdatedBy}",
			"Date(tTerrZipAlgmntChngReqDetentity.updateDt) = '#{tTerrZipAlgmntChngReqDetentity.getUpdateDt}'",
			"tTerrZipAlgmntChngReqDetentity.tenantId = #{tTerrZipAlgmntChngReqDetentity.getTenantId}",
			"tTerrZipAlgmntChngReqDetentity.reqDetail = #{tTerrZipAlgmntChngReqDetentity.getReqDetail}",
			"tTerrZipAlgmntChngReqDetentity.salesPosId = #{tTerrZipAlgmntChngReqDetentity.getSalesPosId}",
			"tTerrZipAlgmntChngReqDetentity.salesHierId = #{tTerrZipAlgmntChngReqDetentity.getSalesHierId}",
			"tTerrZipAlgmntChngReqDetentity.postalCd like '#{tTerrZipAlgmntChngReqDetentity.getPostalCd}%'",
			"tTerrZipAlgmntChngReqDetentity.geoId = #{tTerrZipAlgmntChngReqDetentity.getGeoId}",
			"tTerrZipAlgmntChngReqDetentity.tChngReq.chngReqId = #{tTerrZipAlgmntChngReqDetentity.tChngReq.getChngReqId}",
			"tTerrZipAlgmntChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tTerrZipAlgmntChngReqDetentity.tChngReqBussReason.getChngReqBrId}"};

	/**
	 * Stores a new TTerrZipAlgmntChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be persisted
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet
	 *         object
	 */
	public TTerrZipAlgmntChngReqDet createTTerrZipAlgmntChngReqDet(
			final TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet) {
		LOGGER.info("=========== Create TTerrZipAlgmntChngReqDet ===========");
		return genericDAO.store(tTerrZipAlgmntChngReqDet);
	}

	/**
	 * Deletes a TTerrZipAlgmntChngReqDet entity object from the persistent
	 * store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be deleted
	 */
	public void deleteTTerrZipAlgmntChngReqDet(final Integer zipChngReqId) {
		LOGGER.info("=========== Delete TTerrZipAlgmntChngReqDet ===========");
		final TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet = genericDAO
				.get(clazz, zipChngReqId);
		genericDAO.remove(tTerrZipAlgmntChngReqDet);
	}

	/**
	 * Updates a TTerrZipAlgmntChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be updated
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet
	 *         object
	 */
	public TTerrZipAlgmntChngReqDet updateTTerrZipAlgmntChngReqDet(
			final TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet) {
		LOGGER.info("=========== Update TTerrZipAlgmntChngReqDet ===========");
		return genericDAO.update(tTerrZipAlgmntChngReqDet);
	}
	
	public List<TTerrZipAlgmntChngReqDet> updateTTerrZipAlgmntChngReqDet(
			final List<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDetList) {
		LOGGER.info("=========== Update TTerrZipAlgmntChngReqDet ===========");
		return genericDAO.update(tTerrZipAlgmntChngReqDetList);
	}

	/**
	 * Retrieve an TTerrZipAlgmntChngReqDet object based on given
	 * TTerrZipAlgmntChngReqDet zipChngReqId.
	 * 
	 * @param tTerrZipAlgmntChngReqDetId
	 *            the primary key value of the TTerrZipAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTerrZipAlgmntChngReqDet findTTerrZipAlgmntChngReqDetById(
			final Integer tTerrZipAlgmntChngReqDetId) {
		LOGGER.info("find TTerrZipAlgmntChngReqDet instance with zipChngReqId: "
				+ tTerrZipAlgmntChngReqDetId);
		return genericDAO.get(clazz, tTerrZipAlgmntChngReqDetId);
	}

	/**
	 * Retrieve TTerrZipAlgmntChngReqDet based on given search criteria using
	 * Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmntChngReqDet> list of TTerrZipAlgmntChngReqDet
	 *         if it exists against given criteria. Returns null if not found
	 */
	public List<TTerrZipAlgmntChngReqDet> findTTerrZipAlgmntChngReqDets(
			final SearchFilter<TTerrZipAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TTerrZipAlgmntChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet = searchFilter
				.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery(
				"tTerrZipAlgmntChngReqDetentity", tTerrZipAlgmntChngReqDet);
		if (tTerrZipAlgmntChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tTerrZipAlgmntChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tTerrZipAlgmntChngReqDet.getTChngReq());
		}
		if (tTerrZipAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tTerrZipAlgmntChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tTerrZipAlgmntChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTerrZipAlgmntChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTerrZipAlgmntChngReqDets(
			final SearchFilter<TTerrZipAlgmntChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TTerrZipAlgmntChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet = searchFilter
				.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery(
				"tTerrZipAlgmntChngReqDetentity", tTerrZipAlgmntChngReqDet);
		if (tTerrZipAlgmntChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tTerrZipAlgmntChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tTerrZipAlgmntChngReqDet.getTChngReq());
		}
		if (tTerrZipAlgmntChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tTerrZipAlgmntChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tTerrZipAlgmntChngReqDet.getTChngReqBussReason());
		}
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	public List<TTerrZipAlgmntChngReqDet> fetchDetailList(List<Object> params,
			String Query) {
		return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0,
				-1);
	}

	/* Added By 407986 */

	/**
	 * Gets the zip movement req details.
	 * 
	 * @param params
	 *            the params
	 * @return the zip movement req details
	 */
	@Override
	public List<Object[]> getZipMovementReqDetails(List<Object> params) {
		// commenting below line because TChngReqDetail is removed -- syam
//		return genericDAO.findEntitiesByNamedQueryMultiCond(
//				"GetZipMovementReqDetails", params, 0, -1);
		return null;

	}

	// Added For ZIP CR Locking
	@Override
	public List<Object[]> getLockedZip(List<String> zipCodes, List<Integer> triggerList,
			List<Integer> statusList, String queryName, Short tenantId,Long algmntId,Long buId,Long stId) {

		List<Object> queryParam = new ArrayList<>();
		queryParam.add(statusList);
		queryParam.add(triggerList);
		queryParam.add(zipCodes);
		queryParam.add(tenantId);
		queryParam.add(algmntId);
		queryParam.add(buId);
		queryParam.add(stId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(queryName, queryParam, 0, -1);

		 
	}
	
	/**
	 * Stores a new TTerrZipAlgmntChngReqDet entity object in to the persistent
	 * store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be persisted
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet
	 *         object
	 */
	public List<TTerrZipAlgmntChngReqDet> createTTerrZipAlgmntChngReqDet(
			final List<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDet) {
		LOGGER.info("=========== Create TTerrZipAlgmntChngReqDet ===========");
		return genericDAO.storeBatch(tTerrZipAlgmntChngReqDet);
	}
	@Override
	public List<TTerrZipAlgmntChngReqDet> findZipLineItemByChngReqPostalCode(String zipCode, Short tenantId, Long chngReqId) {

		List<Object> queryParam = new ArrayList<>();
		queryParam.add(chngReqId);
		queryParam.add(zipCode);
		queryParam.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTerrZipAlgmntChngReqDetByTChngReqDetail", queryParam, 0, -1);

		 
	}

}
