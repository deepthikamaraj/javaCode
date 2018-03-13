package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMsgType;
import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TMtrExecLog;
import com.cognizant.opserv.sp.core.entity.TMtrExecLogId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrExecLogDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrExecLogDAO")
public class TMtrExecLogDAOImpl implements TMtrExecLogDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrExecLogDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMSGTYPE = "tMsgType";
	private static final String TMTREXEC = "tMtrExec";

	private final Class<TMtrExecLog> clazz;

	public Class<TMtrExecLog> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrExecLogDAOImpl() {
		super();
		this.clazz = TMtrExecLog.class;
	}

	private static final String JPAQL = "select tMtrExecLogentity from TMtrExecLog tMtrExecLogentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrExecLog tMtrExecLogentity";

	private static final String[] RESTRICTIONS = {
			"tMtrExecLogentity.tMtrExecLogId.execId = #{tMtrExecLogentity.tMtrExecLogId.getExecId}",
			"tMtrExecLogentity.tMtrExecLogId.configId = #{tMtrExecLogentity.tMtrExecLogId.getConfigId}",
			"tMtrExecLogentity.tMtrExecLogId.logId = #{tMtrExecLogentity.tMtrExecLogId.getLogId}",
			"tMtrExecLogentity.msgText like '#{tMtrExecLogentity.getMsgText}%'",
			"tMtrExecLogentity.localeId like '#{tMtrExecLogentity.getLocaleId}%'",
			"tMtrExecLogentity.tenantId = #{tMtrExecLogentity.getTenantId}",
			"tMtrExecLogentity.tMsgType.msgTypeId = #{tMtrExecLogentity.tMsgType.getMsgTypeId}",
			"tMtrExecLogentity.tMtrExec.tMtrExecId = #{tMtrExecLogentity.tMtrExec.getTMtrExecId}" };

	/**
	 * Stores a new TMtrExecLog entity object in to the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be persisted
	 * @return tMtrExecLog Persisted TMtrExecLog object
	 */
	public TMtrExecLog createTMtrExecLog(final TMtrExecLog tMtrExecLog) {
		LOGGER.info("=========== Create TMtrExecLog ===========");
		return genericDAO.store(tMtrExecLog);
	}

	/**
	 * Deletes a TMtrExecLog entity object from the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be deleted
	 */
	public void deleteTMtrExecLog(final TMtrExecLogId tMtrExecLogId) {
		LOGGER.info("=========== Delete TMtrExecLog ===========");
		final TMtrExecLog tMtrExecLog = genericDAO.get(clazz, tMtrExecLogId);
		genericDAO.remove(tMtrExecLog);
	}

	/**
	 * Updates a TMtrExecLog entity object in to the persistent store
	 * 
	 * @param tMtrExecLog
	 *            TMtrExecLog Entity object to be updated
	 * @return tMtrExecLog Persisted TMtrExecLog object
	 */
	public TMtrExecLog updateTMtrExecLog(final TMtrExecLog tMtrExecLog) {
		LOGGER.info("=========== Update TMtrExecLog ===========");
		return genericDAO.update(tMtrExecLog);
	}

	/**
	 * Retrieve an TMtrExecLog object based on given TMtrExecLog TMtrExecLogId.
	 * 
	 * @param tMtrExecLogId
	 *            the primary key value of the TMtrExecLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrExecLog findTMtrExecLogById(final TMtrExecLogId tMtrExecLogId) {
		LOGGER.info("find TMtrExecLog instance with TMtrExecLogId: " + tMtrExecLogId);
		return genericDAO.get(clazz, tMtrExecLogId);
	}

	/**
	 * Retrieve TMtrExecLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLog if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrExecLog> findTMtrExecLogs(final SearchFilter<TMtrExecLog> searchFilter) {
		LOGGER.info("=========== Find TMtrExecLogs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrExecLog tMtrExecLog = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrExecLogentity", tMtrExecLog);

		if (tMtrExecLog.getTMsgType() == null) {
			jpaQuery.bind(TMSGTYPE, new TMsgType());
		} else {
			LOGGER.info("tMsgType {}"+ tMtrExecLog.getTMsgType());

			jpaQuery.bind(TMSGTYPE, tMtrExecLog.getTMsgType());
		}

		if (tMtrExecLog.getTMtrExec() == null) {
			jpaQuery.bind(TMTREXEC, new TMtrExec());
		} else {
			LOGGER.info("tMtrExec {}"+ tMtrExecLog.getTMtrExec());

			jpaQuery.bind(TMTREXEC, tMtrExecLog.getTMtrExec());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrExecLogs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrExecLogs(final SearchFilter<TMtrExecLog> searchFilter) {
		LOGGER.info("=========== Count TMtrExecLog ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrExecLog tMtrExecLog = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrExecLogentity", tMtrExecLog);

		if (tMtrExecLog.getTMsgType() == null) {
			jpaCountQuery.bind(TMSGTYPE, new TMsgType());
		} else {
			LOGGER.info("tMsgType {}"+ tMtrExecLog.getTMsgType());

			jpaCountQuery.bind(TMSGTYPE, tMtrExecLog.getTMsgType());
		}

		if (tMtrExecLog.getTMtrExec() == null) {
			jpaCountQuery.bind(TMTREXEC, new TMtrExec());
		} else {
			LOGGER.info("tMtrExec {}"+ tMtrExecLog.getTMtrExec());

			jpaCountQuery.bind(TMTREXEC, tMtrExecLog.getTMtrExec());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMsgType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExecLog> getTMtrExecLogsByTMsgType(final SearchFilter<TMsgType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMsgType tMsgType = searchFilter.getEntityClass();
		List<Object> tMsgTypeList = new ArrayList<Object>();
		tMsgTypeList.add(tMsgType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecLogByTMsgType", tMsgTypeList, index, maxresult);
	}

	/**
	 * Count TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMsgType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecLogsByTMsgType(final SearchFilter<TMsgType> searchFilter) {

		final TMsgType tMsgType = searchFilter.getEntityClass();
		List<Object> tMsgTypeList = new ArrayList<Object>();
		tMsgTypeList.add(tMsgType);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecLogsByTMsgType", tMsgTypeList);
	}

	/**
	 * Retrieve TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExecLog> list of TMtrExecLogs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExecLog> getTMtrExecLogsByTMtrExec(final SearchFilter<TMtrExec> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrExec tMtrExec = searchFilter.getEntityClass();
		List<Object> tMtrExecList = new ArrayList<Object>();
		tMtrExecList.add(tMtrExec);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecLogByTMtrExec", tMtrExecList, index, maxresult);
	}

	/**
	 * Count TMtrExecLog based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecLogsByTMtrExec(final SearchFilter<TMtrExec> searchFilter) {

		final TMtrExec tMtrExec = searchFilter.getEntityClass();
		List<Object> tMtrExecList = new ArrayList<Object>();
		tMtrExecList.add(tMtrExec);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecLogsByTMtrExec", tMtrExecList);
	}

}
