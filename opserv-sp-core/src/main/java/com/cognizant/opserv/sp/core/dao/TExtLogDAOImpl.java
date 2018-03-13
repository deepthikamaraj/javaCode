package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.opserv.sp.core.entity.TExtLog;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TExtLogDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tExtLogDAO")
public class TExtLogDAOImpl implements TExtLogDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TExtLogDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TDSSTG = "tDsStg";

	private final Class<TExtLog> clazz;

	public Class<TExtLog> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TExtLogDAOImpl() {
		super();
		this.clazz = TExtLog.class;
	}

	private static final String JPAQL = "select tExtLogentity from TExtLog tExtLogentity";

	private static final String JPACOUNTQL = "select count(tExtLogentity) from TExtLog tExtLogentity";

	private static final String[] RESTRICTIONS = { "tExtLogentity.logId = #{tExtLogentity.getLogId}",
			"Date(tExtLogentity.executionDtTm) = '#{tExtLogentity.getExecutionDtTm}'",
			"tExtLogentity.createdBy = #{tExtLogentity.getCreatedBy}",
			"Date(tExtLogentity.createDt) = '#{tExtLogentity.getCreateDt}'",
			"tExtLogentity.tenantId = #{tExtLogentity.getTenantId}",
			"tExtLogentity.logFileName like '#{tExtLogentity.getLogFileName}%'",
			"tExtLogentity.logLoc like '#{tExtLogentity.getLogLoc}%'",
			"tExtLogentity.tDsStg.dsId = #{tExtLogentity.tDsStg.getDsId}" };

	/**
	 * Stores a new TExtLog entity object in to the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be persisted
	 * @return tExtLog Persisted TExtLog object
	 */
	public TExtLog createTExtLog(final TExtLog tExtLog) {
		LOGGER.info("=========== Create TExtLog ===========");
		return genericDAO.store(tExtLog);
	}

	/**
	 * Deletes a TExtLog entity object from the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be deleted
	 */
	public void deleteTExtLog(final Long logId) {
		LOGGER.info("=========== Delete TExtLog ===========");
		final TExtLog tExtLog = genericDAO.get(clazz, logId);
		genericDAO.remove(tExtLog);
	}

	/**
	 * Updates a TExtLog entity object in to the persistent store
	 * 
	 * @param tExtLog
	 *            TExtLog Entity object to be updated
	 * @return tExtLog Persisted TExtLog object
	 */
	public TExtLog updateTExtLog(final TExtLog tExtLog) {
		LOGGER.info("=========== Update TExtLog ===========");
		return genericDAO.update(tExtLog);
	}

	/**
	 * Retrieve an TExtLog object based on given TExtLog logId.
	 * 
	 * @param tExtLogId
	 *            the primary key value of the TExtLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TExtLog findTExtLogById(final Long tExtLogId) {
		LOGGER.info("find TExtLog instance with logId: " + tExtLogId);
		return genericDAO.get(clazz, tExtLogId);
	}

	/**
	 * Retrieve TExtLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExtLog> list of TExtLog if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TExtLog> findTExtLogs(final SearchFilter<TExtLog> searchFilter) {
		LOGGER.info("=========== Find TExtLogs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TExtLog tExtLog = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tExtLogentity", tExtLog);

		if (tExtLog.getTDsStg() == null) {
			jpaQuery.bind(TDSSTG, new TDsStg());
		} else {
			LOGGER.info("tDsStg {}"+ tExtLog.getTDsStg());

			jpaQuery.bind(TDSSTG, tExtLog.getTDsStg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TExtLogs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTExtLogs(final SearchFilter<TExtLog> searchFilter) {
		LOGGER.info("=========== Count TExtLog ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TExtLog tExtLog = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tExtLogentity", tExtLog);

		if (tExtLog.getTDsStg() == null) {
			jpaCountQuery.bind(TDSSTG, new TDsStg());
		} else {
			LOGGER.info("tDsStg {}"+ tExtLog.getTDsStg());

			jpaCountQuery.bind(TDSSTG, tExtLog.getTDsStg());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TExtLog based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExtLog> list of TExtLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TExtLog> getTExtLogsByTDsStg(final SearchFilter<TDsStg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTExtLogByTDsStg", tDsStgList, index, maxresult);
	}

	/**
	 * Count TExtLog based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTExtLogsByTDsStg(final SearchFilter<TDsStg> searchFilter) {

		final TDsStg tDsStg = searchFilter.getEntityClass();
		List<Object> tDsStgList = new ArrayList<Object>();
		tDsStgList.add(tDsStg);
		return genericDAO.findEntitiesByNamedQuery("CountTExtLogsByTDsStg", tDsStgList);
	}

}
