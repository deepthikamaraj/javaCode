package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TLoadLog;
import com.cognizant.opserv.sp.core.entity.TLoadLogHist;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TLoadLogHistDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tLoadLogHistDAO")
public class TLoadLogHistDAOImpl implements TLoadLogHistDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TLoadLogHistDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TLOADLOG = "tLoadLog";

	private final Class<TLoadLogHist> clazz;

	public Class<TLoadLogHist> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TLoadLogHistDAOImpl() {
		super();
		this.clazz = TLoadLogHist.class;
	}

	private static final String JPAQL = "select tLoadLogHistentity from TLoadLogHist tLoadLogHistentity";

	private static final String JPACOUNTQL = "select count(tLoadLogHistentity) from TLoadLogHist tLoadLogHistentity";

	private static final String[] RESTRICTIONS = { "tLoadLogHistentity.revId = #{tLoadLogHistentity.getRevId}",
			"Date(tLoadLogHistentity.executionDtTm) = '#{tLoadLogHistentity.getExecutionDtTm}'",
			"tLoadLogHistentity.executionStatus like '#{tLoadLogHistentity.getExecutionStatus}%'",
			"tLoadLogHistentity.errorNumber = #{tLoadLogHistentity.getErrorNumber}",
			"tLoadLogHistentity.errorDesc like '#{tLoadLogHistentity.getErrorDesc}%'",
			"tLoadLogHistentity.createdBy = #{tLoadLogHistentity.getCreatedBy}",
			"Date(tLoadLogHistentity.createDt) = '#{tLoadLogHistentity.getCreateDt}'",
			"tLoadLogHistentity.tenantId = #{tLoadLogHistentity.getTenantId}",
			"tLoadLogHistentity.tLoadLog.logId = #{tLoadLogHistentity.tLoadLog.getLogId}" };

	/**
	 * Stores a new TLoadLogHist entity object in to the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be persisted
	 * @return tLoadLogHist Persisted TLoadLogHist object
	 */
	public TLoadLogHist createTLoadLogHist(final TLoadLogHist tLoadLogHist) {
		LOGGER.info("=========== Create TLoadLogHist ===========");
		return genericDAO.store(tLoadLogHist);
	}

	/**
	 * Deletes a TLoadLogHist entity object from the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be deleted
	 */
	public void deleteTLoadLogHist(final Integer revId) {
		LOGGER.info("=========== Delete TLoadLogHist ===========");
		final TLoadLogHist tLoadLogHist = genericDAO.get(clazz, revId);
		genericDAO.remove(tLoadLogHist);
	}

	/**
	 * Updates a TLoadLogHist entity object in to the persistent store
	 * 
	 * @param tLoadLogHist
	 *            TLoadLogHist Entity object to be updated
	 * @return tLoadLogHist Persisted TLoadLogHist object
	 */
	public TLoadLogHist updateTLoadLogHist(final TLoadLogHist tLoadLogHist) {
		LOGGER.info("=========== Update TLoadLogHist ===========");
		return genericDAO.update(tLoadLogHist);
	}

	/**
	 * Retrieve an TLoadLogHist object based on given TLoadLogHist revId.
	 * 
	 * @param tLoadLogHistId
	 *            the primary key value of the TLoadLogHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TLoadLogHist findTLoadLogHistById(final Integer tLoadLogHistId) {
		LOGGER.info("find TLoadLogHist instance with revId: " + tLoadLogHistId);
		return genericDAO.get(clazz, tLoadLogHistId);
	}

	/**
	 * Retrieve TLoadLogHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLogHist> list of TLoadLogHist if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TLoadLogHist> findTLoadLogHists(final SearchFilter<TLoadLogHist> searchFilter) {
		LOGGER.info("=========== Find TLoadLogHists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TLoadLogHist tLoadLogHist = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tLoadLogHistentity", tLoadLogHist);

		if (tLoadLogHist.getTLoadLog() == null) {
			jpaQuery.bind(TLOADLOG, new TLoadLog());
		} else {
			LOGGER.info("tLoadLog {}" + tLoadLogHist.getTLoadLog());

			jpaQuery.bind(TLOADLOG, tLoadLogHist.getTLoadLog());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TLoadLogHists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTLoadLogHists(final SearchFilter<TLoadLogHist> searchFilter) {
		LOGGER.info("=========== Count TLoadLogHist ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TLoadLogHist tLoadLogHist = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tLoadLogHistentity", tLoadLogHist);

		if (tLoadLogHist.getTLoadLog() == null) {
			jpaCountQuery.bind(TLOADLOG, new TLoadLog());
		} else {
			LOGGER.info("tLoadLog {}"+ tLoadLogHist.getTLoadLog());

			jpaCountQuery.bind(TLOADLOG, tLoadLogHist.getTLoadLog());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TLoadLogHist based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadLog type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLogHist> list of TLoadLogHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TLoadLogHist> getTLoadLogHistsByTLoadLog(final SearchFilter<TLoadLog> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TLoadLog tLoadLog = searchFilter.getEntityClass();
		List<Object> tLoadLogList = new ArrayList<Object>();
		tLoadLogList.add(tLoadLog);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTLoadLogHistByTLoadLog", tLoadLogList, index, maxresult);
	}

	/**
	 * Count TLoadLogHist based on given search criteria using JPA named Query.
	 * The search criteria is of TLoadLog type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTLoadLogHistsByTLoadLog(final SearchFilter<TLoadLog> searchFilter) {

		final TLoadLog tLoadLog = searchFilter.getEntityClass();
		List<Object> tLoadLogList = new ArrayList<Object>();
		tLoadLogList.add(tLoadLog);
		return genericDAO.findEntitiesByNamedQuery("CountTLoadLogHistsByTLoadLog", tLoadLogList);
	}

}
