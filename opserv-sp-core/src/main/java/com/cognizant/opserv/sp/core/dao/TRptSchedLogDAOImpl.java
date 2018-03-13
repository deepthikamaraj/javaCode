package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptSchedLog;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptSchedLogDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptSchedLogDAO")
public class TRptSchedLogDAOImpl implements TRptSchedLogDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptSchedLogDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRptSchedLog> clazz;

	public Class<TRptSchedLog> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptSchedLogDAOImpl() {
		super();
		this.clazz = TRptSchedLog.class;
	}

	private static final String JPAQL = "select tRptSchedLogentity from TRptSchedLog tRptSchedLogentity";

	private static final String JPACOUNTQL = "select count(tRptSchedLogentity) from TRptSchedLog tRptSchedLogentity";

	private static final String[] RESTRICTIONS = {
			"tRptSchedLogentity.rptSchedId = #{tRptSchedLogentity.getRptSchedId}",
			"Date(tRptSchedLogentity.lastExecutionDt) = '#{tRptSchedLogentity.getLastExecutionDt}'",
			"Date(tRptSchedLogentity.nextExecutionDt) = '#{tRptSchedLogentity.getNextExecutionDt}'",
			"tRptSchedLogentity.executionCnt = #{tRptSchedLogentity.getExecutionCnt}",
			"tRptSchedLogentity.executionStatusId = #{tRptSchedLogentity.getExecutionStatusId}",
			"tRptSchedLogentity.createdBy = #{tRptSchedLogentity.getCreatedBy}",
			"Date(tRptSchedLogentity.createDt) = '#{tRptSchedLogentity.getCreateDt}'",
			"tRptSchedLogentity.updatedBy = #{tRptSchedLogentity.getUpdatedBy}",
			"Date(tRptSchedLogentity.updateDt) = '#{tRptSchedLogentity.getUpdateDt}'",
			"tRptSchedLogentity.tenantId = #{tRptSchedLogentity.getTenantId}" };

	/**
	 * Stores a new TRptSchedLog entity object in to the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be persisted
	 * @return tRptSchedLog Persisted TRptSchedLog object
	 */
	public TRptSchedLog createTRptSchedLog(final TRptSchedLog tRptSchedLog) {
		LOGGER.info("=========== Create TRptSchedLog ===========");
		return genericDAO.store(tRptSchedLog);
	}

	/**
	 * Deletes a TRptSchedLog entity object from the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be deleted
	 */
	public void deleteTRptSchedLog(final Integer rptSchedId) {
		LOGGER.info("=========== Delete TRptSchedLog ===========");
		final TRptSchedLog tRptSchedLog = genericDAO.get(clazz, rptSchedId);
		genericDAO.remove(tRptSchedLog);
	}

	/**
	 * Updates a TRptSchedLog entity object in to the persistent store
	 * 
	 * @param tRptSchedLog
	 *            TRptSchedLog Entity object to be updated
	 * @return tRptSchedLog Persisted TRptSchedLog object
	 */
	public TRptSchedLog updateTRptSchedLog(final TRptSchedLog tRptSchedLog) {
		LOGGER.info("=========== Update TRptSchedLog ===========");
		return genericDAO.update(tRptSchedLog);
	}

	/**
	 * Retrieve an TRptSchedLog object based on given TRptSchedLog rptSchedId.
	 * 
	 * @param tRptSchedLogId
	 *            the primary key value of the TRptSchedLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptSchedLog findTRptSchedLogById(final Integer tRptSchedLogId) {
		LOGGER.info("find TRptSchedLog instance with rptSchedId: " + tRptSchedLogId);
		return genericDAO.get(clazz, tRptSchedLogId);
	}

	/**
	 * Retrieve TRptSchedLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSchedLog> list of TRptSchedLog if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptSchedLog> findTRptSchedLogs(final SearchFilter<TRptSchedLog> searchFilter) {
		LOGGER.info("=========== Find TRptSchedLogs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptSchedLog tRptSchedLog = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptSchedLogentity", tRptSchedLog);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptSchedLogs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptSchedLogs(final SearchFilter<TRptSchedLog> searchFilter) {
		LOGGER.info("=========== Count TRptSchedLog ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptSchedLog tRptSchedLog = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptSchedLogentity", tRptSchedLog);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/**
	 * Retrieve an TRptSchedLog object based on given TRptSchedLog rptSchedId and TenantId.
	 * 
	 * @param tRptSchedLogId
	 *            the primary key value of the TRptSchedLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public TRptSchedLog findTRptSchedLogById(final Integer tRptSchedId, final Short tenantId) {
		LOGGER.info("=========== find TRptSchedLogById ===========");
		TRptSchedLog tRptSchedLog = new TRptSchedLog();
		List list = new ArrayList();
		list.add(tRptSchedId);
		list.add(tenantId);	
		List<TRptSchedLog> tRptSchedLogList= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTRptSchedLogsById", list,0,-1);
		if(tRptSchedLogList!= null){
			tRptSchedLog = tRptSchedLogList.get(0);
		}
		return tRptSchedLog;
	}
	
	
	
}
