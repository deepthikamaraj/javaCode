package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TLoadLog;
import com.cognizant.opserv.sp.core.entity.TTerrZipLoad;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TLoadLogDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tLoadLogDAO")
public class TLoadLogDAOImpl implements TLoadLogDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TLoadLogDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TTERRZIPLOAD = "tTerrZipLoad";

	private final Class<TLoadLog> clazz;

	public Class<TLoadLog> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TLoadLogDAOImpl() {
		super();
		this.clazz = TLoadLog.class;
	}

	private static final String JPAQL = "select tLoadLogentity from TLoadLog tLoadLogentity";

	private static final String JPACOUNTQL = "select count(tLoadLogentity) from TLoadLog tLoadLogentity";

	private static final String[] RESTRICTIONS = { "tLoadLogentity.logId = #{tLoadLogentity.getLogId}",
			"Date(tLoadLogentity.execStartDtTm) = '#{tLoadLogentity.getExecStartDtTm}'",
			"tLoadLogentity.createdBy = #{tLoadLogentity.getCreatedBy}",
			"Date(tLoadLogentity.createDt) = '#{tLoadLogentity.getCreateDt}'",
			"tLoadLogentity.tenantId = #{tLoadLogentity.getTenantId}",
			"tLoadLogentity.logFileName like '#{tLoadLogentity.getLogFileName}%'",
			"tLoadLogentity.logLoc like '#{tLoadLogentity.getLogLoc}%'",
			"Date(tLoadLogentity.execEndDtTm) = '#{tLoadLogentity.getExecEndDtTm}'",
			"tLoadLogentity.tTerrZipLoad.dsId = #{tLoadLogentity.tTerrZipLoad.getDsId}" };

	/**
	 * Stores a new TLoadLog entity object in to the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be persisted
	 * @return tLoadLog Persisted TLoadLog object
	 */
	public TLoadLog createTLoadLog(final TLoadLog tLoadLog) {
		LOGGER.info("=========== Create TLoadLog ===========");
		return genericDAO.store(tLoadLog);
	}

	/**
	 * Deletes a TLoadLog entity object from the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be deleted
	 */
	public void deleteTLoadLog(final Long logId) {
		LOGGER.info("=========== Delete TLoadLog ===========");
		final TLoadLog tLoadLog = genericDAO.get(clazz, logId);
		genericDAO.remove(tLoadLog);
	}

	/**
	 * Updates a TLoadLog entity object in to the persistent store
	 * 
	 * @param tLoadLog
	 *            TLoadLog Entity object to be updated
	 * @return tLoadLog Persisted TLoadLog object
	 */
	public TLoadLog updateTLoadLog(final TLoadLog tLoadLog) {
		LOGGER.info("=========== Update TLoadLog ===========");
		return genericDAO.update(tLoadLog);
	}

	/**
	 * Retrieve an TLoadLog object based on given TLoadLog logId.
	 * 
	 * @param tLoadLogId
	 *            the primary key value of the TLoadLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TLoadLog findTLoadLogById(final Long tLoadLogId) {
		LOGGER.info("find TLoadLog instance with logId: " + tLoadLogId);
		return genericDAO.get(clazz, tLoadLogId);
	}

	/**
	 * Retrieve TLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLog> list of TLoadLog if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TLoadLog> findTLoadLogs(final SearchFilter<TLoadLog> searchFilter) {
		LOGGER.info("=========== Find TLoadLogs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TLoadLog tLoadLog = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tLoadLogentity", tLoadLog);

		if (tLoadLog.getTTerrZipLoad() == null) {
			jpaQuery.bind(TTERRZIPLOAD, new TTerrZipLoad());
		} else {
			LOGGER.info("tTerrZipLoad {}", tLoadLog.getTTerrZipLoad());

			jpaQuery.bind(TTERRZIPLOAD, tLoadLog.getTTerrZipLoad());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TLoadLogs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTLoadLogs(final SearchFilter<TLoadLog> searchFilter) {
		LOGGER.info("=========== Count TLoadLog ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TLoadLog tLoadLog = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tLoadLogentity", tLoadLog);

		if (tLoadLog.getTTerrZipLoad() == null) {
			jpaCountQuery.bind(TTERRZIPLOAD, new TTerrZipLoad());
		} else {
			LOGGER.info("tTerrZipLoad {}", tLoadLog.getTTerrZipLoad());

			jpaCountQuery.bind(TTERRZIPLOAD, tLoadLog.getTTerrZipLoad());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public List<TLoadLog> findTLoadLogsBYDataSetID(Integer dataSetID,
			short tenentID) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(dataSetID);
		paramList.add(tenentID);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTLoadLogByDataSetID",paramList,  0, -1);
	}

	/**
	 * Retrieve TLoadLog based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipLoad type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TLoadLog> list of TLoadLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TLoadLog> getTLoadLogsByTTerrZipLoad(final SearchFilter<TTerrZipLoad> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTerrZipLoad tTerrZipLoad = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTLoadLogByTTerrZipLoad", tTerrZipLoad, index, maxresult);
	}

	/**
	 * Count TLoadLog based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipLoad type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTLoadLogsByTTerrZipLoad(final SearchFilter<TTerrZipLoad> searchFilter) {

		final TTerrZipLoad tTerrZipLoad = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTLoadLogsByTTerrZipLoad", tTerrZipLoad);
	}*/

}
