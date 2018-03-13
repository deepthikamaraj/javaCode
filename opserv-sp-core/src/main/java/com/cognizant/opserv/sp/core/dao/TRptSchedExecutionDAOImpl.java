package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.opserv.sp.core.entity.TRptSched;
import com.cognizant.opserv.sp.core.entity.TRptSchedExecution;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptSchedExecutionDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptSchedExecutionDAO")
public class TRptSchedExecutionDAOImpl implements TRptSchedExecutionDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptSchedExecutionDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TEXECUTIONSTATUS = "tExecutionStatus";
	private static final String TRPTSCHED = "tRptSched";

	private final Class<TRptSchedExecution> clazz;

	public Class<TRptSchedExecution> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptSchedExecutionDAOImpl() {
		super();
		this.clazz = TRptSchedExecution.class;
	}

	private static final String JPAQL = "select tRptSchedExecutionentity from TRptSchedExecution tRptSchedExecutionentity";

	private static final String JPACOUNTQL = "select count(tRptSchedExecutionentity) from TRptSchedExecution tRptSchedExecutionentity";

	private static final String[] RESTRICTIONS = {
			"tRptSchedExecutionentity.schedExecutionId = #{tRptSchedExecutionentity.getSchedExecutionId}",
			"Date(tRptSchedExecutionentity.executionDt) = '#{tRptSchedExecutionentity.getExecutionDt}'",
			"tRptSchedExecutionentity.tenantId = #{tRptSchedExecutionentity.getTenantId}",
			"tRptSchedExecutionentity.failureReason like '#{tRptSchedExecutionentity.getFailureReason}%'",
			"tRptSchedExecutionentity.tExecutionStatus.executionStatusId = #{tRptSchedExecutionentity.tExecutionStatus.getExecutionStatusId}",
			"tRptSchedExecutionentity.tRptSched.tRptSchedId = #{tRptSchedExecutionentity.tRptSched.getTRptSchedId}" };

	/**
	 * Stores a new TRptSchedExecution entity object in to the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be persisted
	 * @return tRptSchedExecution Persisted TRptSchedExecution object
	 */
	public TRptSchedExecution createTRptSchedExecution(final TRptSchedExecution tRptSchedExecution) {
		LOGGER.info("=========== Create TRptSchedExecution ===========");
		return genericDAO.store(tRptSchedExecution);
	}

	/**
	 * Deletes a TRptSchedExecution entity object from the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be deleted
	 */
	public void deleteTRptSchedExecution(final Integer schedExecutionId) {
		LOGGER.info("=========== Delete TRptSchedExecution ===========");
		final TRptSchedExecution tRptSchedExecution = genericDAO.get(clazz, schedExecutionId);
		genericDAO.remove(tRptSchedExecution);
	}

	/**
	 * Updates a TRptSchedExecution entity object in to the persistent store
	 * 
	 * @param tRptSchedExecution
	 *            TRptSchedExecution Entity object to be updated
	 * @return tRptSchedExecution Persisted TRptSchedExecution object
	 */
	public TRptSchedExecution updateTRptSchedExecution(final TRptSchedExecution tRptSchedExecution) {
		LOGGER.info("=========== Update TRptSchedExecution ===========");
		return genericDAO.update(tRptSchedExecution);
	}

	/**
	 * Retrieve an TRptSchedExecution object based on given TRptSchedExecution schedExecutionId.
	 * 
	 * @param tRptSchedExecutionId
	 *            the primary key value of the TRptSchedExecution Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptSchedExecution findTRptSchedExecutionById(final Integer tRptSchedExecutionId) {
		LOGGER.info("find TRptSchedExecution instance with schedExecutionId: " + tRptSchedExecutionId);
		return genericDAO.get(clazz, tRptSchedExecutionId);
	}

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecution if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptSchedExecution> findTRptSchedExecutions(final SearchFilter<TRptSchedExecution> searchFilter) {
		LOGGER.info("=========== Find TRptSchedExecutions ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptSchedExecution tRptSchedExecution = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptSchedExecutionentity", tRptSchedExecution);

		if (tRptSchedExecution.getTExecutionStatus() == null) {
			jpaQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}"+ tRptSchedExecution.getTExecutionStatus());

			jpaQuery.bind(TEXECUTIONSTATUS, tRptSchedExecution.getTExecutionStatus());
		}

		if (tRptSchedExecution.getTRptSched() == null) {
			jpaQuery.bind(TRPTSCHED, new TRptSched());
		} else {
			LOGGER.info("tRptSched {}"+ tRptSchedExecution.getTRptSched());

			jpaQuery.bind(TRPTSCHED, tRptSchedExecution.getTRptSched());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptSchedExecutions.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptSchedExecutions(final SearchFilter<TRptSchedExecution> searchFilter) {
		LOGGER.info("=========== Count TRptSchedExecution ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptSchedExecution tRptSchedExecution = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptSchedExecutionentity", tRptSchedExecution);

		if (tRptSchedExecution.getTExecutionStatus() == null) {
			jpaCountQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}"+ tRptSchedExecution.getTExecutionStatus());

			jpaCountQuery.bind(TEXECUTIONSTATUS, tRptSchedExecution.getTExecutionStatus());
		}

		if (tRptSchedExecution.getTRptSched() == null) {
			jpaCountQuery.bind(TRPTSCHED, new TRptSched());
		} else {
			LOGGER.info("tRptSched {}"+ tRptSchedExecution.getTRptSched());

			jpaCountQuery.bind(TRPTSCHED, tRptSchedExecution.getTRptSched());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecutions if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptSchedExecution> getTRptSchedExecutionsByTExecutionStatus(
			final SearchFilter<TExecutionStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptSchedExecutionByTExecutionStatus", queryParam, index,
				maxresult);
	}

	/**
	 * Count TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptSchedExecutionsByTExecutionStatus(final SearchFilter<TExecutionStatus> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptSchedExecutionsByTExecutionStatus", queryParam);
	}

	/**
	 * Retrieve TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedExecution> list of TRptSchedExecutions if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptSchedExecution> getTRptSchedExecutionsByTRptSched(final SearchFilter<TRptSched> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptSchedExecutionByTRptSched", queryParam, index, maxresult);
	}

	/**
	 * Count TRptSchedExecution based on given search criteria using JPA named Query.
	 * The search criteria is of TRptSched type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptSchedExecutionsByTRptSched(final SearchFilter<TRptSched> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptSchedExecutionsByTRptSched", queryParam);
	}

}
