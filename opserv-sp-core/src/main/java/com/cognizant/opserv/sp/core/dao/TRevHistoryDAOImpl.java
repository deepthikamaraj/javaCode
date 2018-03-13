package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRevHistory;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRevHistoryDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRevHistoryDAO")
public class TRevHistoryDAOImpl implements TRevHistoryDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(TRevHistoryDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {

		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRevHistory> clazz;

	public Class<TRevHistory> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRevHistoryDAOImpl() {
		super();
		this.clazz = TRevHistory.class;
	}

	private static final String JPAQL = "select tRevHistoryentity from TRevHistory tRevHistoryentity";

	private static final String JPACOUNTQL = "select count(tRevHistoryentity) from TRevHistory tRevHistoryentity";

	private static final String[] RESTRICTIONS = {
			"tRevHistoryentity.revId = #{tRevHistoryentity.getRevId}",
			"Date(tRevHistoryentity.timestamp) = '#{tRevHistoryentity.getTimestamp}'" };

	/**
	 * Stores a new TRevHistory entity object in to the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be persisted
	 * @return tRevHistory Persisted TRevHistory object
	 */
	public TRevHistory createTRevHistory(final TRevHistory tRevHistory) {
		LOGGER.info("=========== Create TRevHistory ===========");
		return genericDAO.store(tRevHistory);
	}

	/**
	 * Deletes a TRevHistory entity object from the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be deleted
	 */
	public void deleteTRevHistory(final Long revId) {
		LOGGER.info("=========== Delete TRevHistory ===========");
		final TRevHistory tRevHistory = genericDAO.get(clazz, revId);
		genericDAO.remove(tRevHistory);
	}

	/**
	 * Updates a TRevHistory entity object in to the persistent store
	 * 
	 * @param tRevHistory
	 *            TRevHistory Entity object to be updated
	 * @return tRevHistory Persisted TRevHistory object
	 */
	public TRevHistory updateTRevHistory(final TRevHistory tRevHistory) {
		LOGGER.info("=========== Update TRevHistory ===========");
		return genericDAO.update(tRevHistory);
	}

	/**
	 * Retrieve an TRevHistory object based on given TRevHistory revId.
	 * 
	 * @param tRevHistoryId
	 *            the primary key value of the TRevHistory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRevHistory findTRevHistoryById(final Long tRevHistoryId) {
		LOGGER.info("find TRevHistory instance with revId: " + tRevHistoryId);
		return genericDAO.get(clazz, tRevHistoryId);
	}

	/**
	 * Retrieve TRevHistory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRevHistory> list of TRevHistory if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRevHistory> findTRevHistorys(
			final SearchFilter<TRevHistory> searchFilter) {
		LOGGER.info("=========== Find TRevHistorys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRevHistory tRevHistory = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRevHistoryentity", tRevHistory);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRevHistorys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRevHistorys(final SearchFilter<TRevHistory> searchFilter) {
		LOGGER.info("=========== Count TRevHistory ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRevHistory tRevHistory = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRevHistoryentity",
				tRevHistory);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
