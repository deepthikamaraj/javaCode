package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TGeoLoad;
import com.cognizant.opserv.sp.core.entity.TGeoLoadLog;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TGeoLoadLogDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tGeoLoadLogDAO")
public class TGeoLoadLogDAOImpl implements TGeoLoadLogDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TGeoLoadLogDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TGEOLOAD = "tGeoLoad";

	private final Class<TGeoLoadLog> clazz;

	public Class<TGeoLoadLog> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TGeoLoadLogDAOImpl() {
		super();
		this.clazz = TGeoLoadLog.class;
	}

	private static final String JPAQL = "select tGeoLoadLogentity from TGeoLoadLog tGeoLoadLogentity";

	private static final String JPACOUNTQL = "select count(tGeoLoadLogentity) from TGeoLoadLog tGeoLoadLogentity";

	private static final String[] RESTRICTIONS = { "tGeoLoadLogentity.logId = #{tGeoLoadLogentity.getLogId}",
		"Date(tGeoLoadLogentity.execStartDtTm) = '#{tGeoLoadLogentity.getExecStartDtTm}'",
		"tGeoLoadLogentity.createdBy = #{tGeoLoadLogentity.getCreatedBy}",
		"Date(tGeoLoadLogentity.createDt) = '#{tGeoLoadLogentity.getCreateDt}'",
		"tGeoLoadLogentity.tenantId = #{tGeoLoadLogentity.getTenantId}",
		"tGeoLoadLogentity.logFileName like '#{tGeoLoadLogentity.getLogFileName}%'",
		"tGeoLoadLogentity.logLoc like '#{tGeoLoadLogentity.getLogLoc}%'",
		"Date(tGeoLoadLogentity.execEndDtTm) = '#{tGeoLoadLogentity.getExecEndDtTm}'",
	"tGeoLoadLogentity.tGeoLoad.dsId = #{tGeoLoadLogentity.tGeoLoad.getDsId}" };

	/**
	 * Stores a new TGeoLoadLog entity object in to the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be persisted
	 * @return tGeoLoadLog Persisted TGeoLoadLog object
	 */
	public TGeoLoadLog createTGeoLoadLog(final TGeoLoadLog tGeoLoadLog) {
		LOGGER.info("=========== Create TGeoLoadLog ===========");
		return genericDAO.store(tGeoLoadLog);
	}

	/**
	 * Deletes a TGeoLoadLog entity object from the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be deleted
	 */
	public void deleteTGeoLoadLog(final Long logId) {
		LOGGER.info("=========== Delete TGeoLoadLog ===========");
		final TGeoLoadLog tGeoLoadLog = genericDAO.get(clazz, logId);
		genericDAO.remove(tGeoLoadLog);
	}

	/**
	 * Updates a TGeoLoadLog entity object in to the persistent store
	 * 
	 * @param tGeoLoadLog
	 *            TGeoLoadLog Entity object to be updated
	 * @return tGeoLoadLog Persisted TGeoLoadLog object
	 */
	public TGeoLoadLog updateTGeoLoadLog(final TGeoLoadLog tGeoLoadLog) {
		LOGGER.info("=========== Update TGeoLoadLog ===========");
		return genericDAO.update(tGeoLoadLog);
	}

	/**
	 * Retrieve an TGeoLoadLog object based on given TGeoLoadLog logId.
	 * 
	 * @param tGeoLoadLogId
	 *            the primary key value of the TGeoLoadLog Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TGeoLoadLog findTGeoLoadLogById(final Long tGeoLoadLogId) {
		LOGGER.info("find TGeoLoadLog instance with logId: " + tGeoLoadLogId);
		return genericDAO.get(clazz, tGeoLoadLogId);
	}

	/**
	 * Retrieve TGeoLoadLog based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoLoadLog> list of TGeoLoadLog if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TGeoLoadLog> findTGeoLoadLogs(final SearchFilter<TGeoLoadLog> searchFilter) {
		LOGGER.info("=========== Find TGeoLoadLogs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TGeoLoadLog tGeoLoadLog = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tGeoLoadLogentity", tGeoLoadLog);

		if (tGeoLoadLog.getTGeoLoad() == null) {
			jpaQuery.bind(TGEOLOAD, new TGeoLoad());
		} else {
			LOGGER.info("tGeoLoad {}", tGeoLoadLog.getTGeoLoad());

			jpaQuery.bind(TGEOLOAD, tGeoLoadLog.getTGeoLoad());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TGeoLoadLogs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTGeoLoadLogs(final SearchFilter<TGeoLoadLog> searchFilter) {
		LOGGER.info("=========== Count TGeoLoadLog ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TGeoLoadLog tGeoLoadLog = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tGeoLoadLogentity", tGeoLoadLog);

		if (tGeoLoadLog.getTGeoLoad() == null) {
			jpaCountQuery.bind(TGEOLOAD, new TGeoLoad());
		} else {
			LOGGER.info("tGeoLoad {}", tGeoLoadLog.getTGeoLoad());

			jpaCountQuery.bind(TGEOLOAD, tGeoLoadLog.getTGeoLoad());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
