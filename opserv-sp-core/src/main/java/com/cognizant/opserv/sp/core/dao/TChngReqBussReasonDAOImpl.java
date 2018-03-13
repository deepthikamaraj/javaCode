package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqBussReasonDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqBussReasonDAO")
public class TChngReqBussReasonDAOImpl implements TChngReqBussReasonDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TChngReqBussReasonDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQTRIGGER = "tChngReqTrigger";
	private static final String TBUSSREASON = "tBussReason";

	private final Class<TChngReqBussReason> clazz;

	public Class<TChngReqBussReason> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqBussReasonDAOImpl() {
		super();
		this.clazz = TChngReqBussReason.class;
	}

	private static final String JPAQL = "select tChngReqBussReasonentity from TChngReqBussReason tChngReqBussReasonentity";

	private static final String JPACOUNTQL = "select count(tChngReqBussReasonentity) from TChngReqBussReason tChngReqBussReasonentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqBussReasonentity.chngReqBrId = #{tChngReqBussReasonentity.getChngReqBrId}",
			"tChngReqBussReasonentity.activeFlag = #{tChngReqBussReasonentity.getActiveFlag}",
			"tChngReqBussReasonentity.defFlag = #{tChngReqBussReasonentity.getDefFlag}",
			"tChngReqBussReasonentity.algmntId = #{tChngReqBussReasonentity.getAlgmntId}",
			"tChngReqBussReasonentity.bussUnitId = #{tChngReqBussReasonentity.getBussUnitId}",
			"tChngReqBussReasonentity.salesTeamId = #{tChngReqBussReasonentity.getSalesTeamId}",
			"tChngReqBussReasonentity.categoryId = #{tChngReqBussReasonentity.getCategoryId}",
			"tChngReqBussReasonentity.createdBy = #{tChngReqBussReasonentity.getCreatedBy}",
			"Date(tChngReqBussReasonentity.createDt) = '#{tChngReqBussReasonentity.getCreateDt}'",
			"tChngReqBussReasonentity.updatedBy = #{tChngReqBussReasonentity.getUpdatedBy}",
			"Date(tChngReqBussReasonentity.updateDt) = '#{tChngReqBussReasonentity.getUpdateDt}'",
			"tChngReqBussReasonentity.tenantId = #{tChngReqBussReasonentity.getTenantId}",
			"tChngReqBussReasonentity.chngType like '#{tChngReqBussReasonentity.getChngType}%'",
			"tChngReqBussReasonentity.tChngReqTrigger.triggerId = #{tChngReqBussReasonentity.tChngReqTrigger.getTriggerId}",
			"tChngReqBussReasonentity.tBussReason.bussReasonId = #{tChngReqBussReasonentity.tBussReason.getBussReasonId}" };

	/**
	 * Stores a new TChngReqBussReason entity object in to the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be persisted
	 * @return tChngReqBussReason Persisted TChngReqBussReason object
	 */
	public TChngReqBussReason createTChngReqBussReason(final TChngReqBussReason tChngReqBussReason) {
		LOGGER.info("=========== Create TChngReqBussReason ===========");
		return genericDAO.store(tChngReqBussReason);
	}

	/**
	 * Deletes a TChngReqBussReason entity object from the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be deleted
	 */
	public void deleteTChngReqBussReason(final Long chngReqBrId) {
		LOGGER.info("=========== Delete TChngReqBussReason ===========");
		final TChngReqBussReason tChngReqBussReason = genericDAO.get(clazz, chngReqBrId);
		genericDAO.remove(tChngReqBussReason);
	}

	/**
	 * Updates a TChngReqBussReason entity object in to the persistent store
	 * 
	 * @param tChngReqBussReason
	 *            TChngReqBussReason Entity object to be updated
	 * @return tChngReqBussReason Persisted TChngReqBussReason object
	 */
	public TChngReqBussReason updateTChngReqBussReason(final TChngReqBussReason tChngReqBussReason) {
		LOGGER.info("=========== Update TChngReqBussReason ===========");
		return genericDAO.update(tChngReqBussReason);
	}

	/**
	 * Retrieve an TChngReqBussReason object based on given TChngReqBussReason chngReqBrId.
	 * 
	 * @param tChngReqBussReasonId
	 *            the primary key value of the TChngReqBussReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqBussReason findTChngReqBussReasonById(final Long tChngReqBussReasonId) {
		LOGGER.info("find TChngReqBussReason instance with chngReqBrId: " + tChngReqBussReasonId);
		return genericDAO.get(clazz, tChngReqBussReasonId);
	}

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReason if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqBussReason> findTChngReqBussReasons(final SearchFilter<TChngReqBussReason> searchFilter) {
		LOGGER.info("=========== Find TChngReqBussReasons ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqBussReason tChngReqBussReason = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqBussReasonentity", tChngReqBussReason);

		if (tChngReqBussReason.getTChngReqTrigger() == null) {
			jpaQuery.bind(TCHNGREQTRIGGER, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTrigger {}", tChngReqBussReason.getTChngReqTrigger());

			jpaQuery.bind(TCHNGREQTRIGGER, tChngReqBussReason.getTChngReqTrigger());
		}

		if (tChngReqBussReason.getTBussReason() == null) {
			jpaQuery.bind(TBUSSREASON, new TBussReason());
		} else {
			LOGGER.info("tBussReason {}", tChngReqBussReason.getTBussReason());

			jpaQuery.bind(TBUSSREASON, tChngReqBussReason.getTBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqBussReasons.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqBussReasons(final SearchFilter<TChngReqBussReason> searchFilter) {
		LOGGER.info("=========== Count TChngReqBussReason ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqBussReason tChngReqBussReason = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqBussReasonentity", tChngReqBussReason);

		if (tChngReqBussReason.getTChngReqTrigger() == null) {
			jpaCountQuery.bind(TCHNGREQTRIGGER, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTrigger {}", tChngReqBussReason.getTChngReqTrigger());

			jpaCountQuery.bind(TCHNGREQTRIGGER, tChngReqBussReason.getTChngReqTrigger());
		}

		if (tChngReqBussReason.getTBussReason() == null) {
			jpaCountQuery.bind(TBUSSREASON, new TBussReason());
		} else {
			LOGGER.info("tBussReason {}", tChngReqBussReason.getTBussReason());

			jpaCountQuery.bind(TBUSSREASON, tChngReqBussReason.getTBussReason());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReqBussReason> getTChngReqBussReasonsByTChngReqTrigger(
			final SearchFilter<TChngReqTrigger> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();

		int maxresult = 0;
		int index = -1;
		if(null != paginationInfo){
			maxresult = paginationInfo.getMaxRows();
			index = paginationInfo.getStartIndex();
		}
		List<Object> args = new ArrayList<Object>();
		args.add(tChngReqTrigger);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqBussReasonByTChngReqTrigger", args, index,
						maxresult);
	}

	/**
	 * Count TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqBussReasonsByTChngReqTrigger(final SearchFilter<TChngReqTrigger> searchFilter) {

		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> args = new ArrayList<Object>();
		args.add(tChngReqTrigger);
		
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqBussReasonsByTChngReqTrigger", args);
	}

	/**
	 * Retrieve TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TBussReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqBussReason> list of TChngReqBussReasons if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReqBussReason> getTChngReqBussReasonsByTBussReason(final SearchFilter<TBussReason> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussReason tBussReason = searchFilter.getEntityClass();

		int maxresult = 0;
		int index = -1;
		if(null != paginationInfo){
			maxresult = paginationInfo.getMaxRows();
			index = paginationInfo.getStartIndex();
		}
		List<Object> args = new ArrayList<Object>();
		args.add(tBussReason);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqBussReasonByTBussReason", args, index, maxresult);
	}

	/**
	 * Count TChngReqBussReason based on given search criteria using JPA named Query.
	 * The search criteria is of TBussReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqBussReasonsByTBussReason(final SearchFilter<TBussReason> searchFilter) {

		final TBussReason tBussReason = searchFilter.getEntityClass();
		List<Object> args = new ArrayList<Object>();
		args.add(tBussReason);
		
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqBussReasonsByTBussReason", args);
	}

}