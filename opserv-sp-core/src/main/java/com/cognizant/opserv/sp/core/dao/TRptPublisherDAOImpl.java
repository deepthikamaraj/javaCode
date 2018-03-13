package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptPublisher;
import com.cognizant.opserv.sp.core.entity.TRptPublisherId;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptPublisherDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptPublisherDAO")
public class TRptPublisherDAOImpl implements TRptPublisherDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptPublisherDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TTARGETRECIPIENTPREF = "tTargetRecipientPref";
	private static final String TPERS = "tPers";
	private static final String TRPT = "tRpt";

	private final Class<TRptPublisher> clazz;

	public Class<TRptPublisher> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptPublisherDAOImpl() {
		super();
		this.clazz = TRptPublisher.class;
	}

	private static final String JPAQL = "select tRptPublisherentity from TRptPublisher tRptPublisherentity";

	private static final String JPACOUNTQL = "select count(*) from TRptPublisher tRptPublisherentity";

	private static final String[] RESTRICTIONS = {
			"tRptPublisherentity.tRptPublisherId.staffId = #{tRptPublisherentity.tRptPublisherId.getStaffId}",
			"tRptPublisherentity.tRptPublisherId.rptId = #{tRptPublisherentity.tRptPublisherId.getRptId}",
			"tRptPublisherentity.tenantId = #{tRptPublisherentity.getTenantId}",
			"tRptPublisherentity.tTargetRecipientPref.recipientPrefId = #{tRptPublisherentity.tTargetRecipientPref.getRecipientPrefId}",
			"tRptPublisherentity.tPers.staffId = #{tRptPublisherentity.tPers.getStaffId}",
			"tRptPublisherentity.tRpt.rptId = #{tRptPublisherentity.tRpt.getRptId}" };

	/**
	 * Stores a new TRptPublisher entity object in to the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be persisted
	 * @return tRptPublisher Persisted TRptPublisher object
	 */
	public TRptPublisher createTRptPublisher(final TRptPublisher tRptPublisher) {
		LOGGER.info("=========== Create TRptPublisher ===========");
		return genericDAO.store(tRptPublisher);
	}

	/**
	 * Deletes a TRptPublisher entity object from the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be deleted
	 */
	public void deleteTRptPublisher(final TRptPublisherId tRptPublisherId) {
		LOGGER.info("=========== Delete TRptPublisher ===========");
		final TRptPublisher tRptPublisher = genericDAO.get(clazz, tRptPublisherId);
		genericDAO.remove(tRptPublisher);
	}

	/**
	 * Updates a TRptPublisher entity object in to the persistent store
	 * 
	 * @param tRptPublisher
	 *            TRptPublisher Entity object to be updated
	 * @return tRptPublisher Persisted TRptPublisher object
	 */
	public TRptPublisher updateTRptPublisher(final TRptPublisher tRptPublisher) {
		LOGGER.info("=========== Update TRptPublisher ===========");
		return genericDAO.update(tRptPublisher);
	}

	/**
	 * Retrieve an TRptPublisher object based on given TRptPublisher TRptPublisherId.
	 * 
	 * @param tRptPublisherId
	 *            the primary key value of the TRptPublisher Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptPublisher findTRptPublisherById(final TRptPublisherId tRptPublisherId) {
		LOGGER.info("find TRptPublisher instance with TRptPublisherId: " + tRptPublisherId);
		return genericDAO.get(clazz, tRptPublisherId);
	}

	/**
	 * Retrieve TRptPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublisher if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptPublisher> findTRptPublishers(final SearchFilter<TRptPublisher> searchFilter) {
		LOGGER.info("=========== Find TRptPublishers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptPublisher tRptPublisher = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptPublisherentity", tRptPublisher);

		if (tRptPublisher.getTTargetRecipientPref() == null) {
			jpaQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}"+ tRptPublisher.getTTargetRecipientPref());

			jpaQuery.bind(TTARGETRECIPIENTPREF, tRptPublisher.getTTargetRecipientPref());
		}

		if (tRptPublisher.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptPublisher.getTPers());

			jpaQuery.bind(TPERS, tRptPublisher.getTPers());
		}

		if (tRptPublisher.getTRpt() == null) {
			jpaQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptPublisher.getTRpt());

			jpaQuery.bind(TRPT, tRptPublisher.getTRpt());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptPublishers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptPublishers(final SearchFilter<TRptPublisher> searchFilter) {
		LOGGER.info("=========== Count TRptPublisher ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptPublisher tRptPublisher = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptPublisherentity", tRptPublisher);

		if (tRptPublisher.getTTargetRecipientPref() == null) {
			jpaCountQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}"+ tRptPublisher.getTTargetRecipientPref());

			jpaCountQuery.bind(TTARGETRECIPIENTPREF, tRptPublisher.getTTargetRecipientPref());
		}

		if (tRptPublisher.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptPublisher.getTPers());

			jpaCountQuery.bind(TPERS, tRptPublisher.getTPers());
		}

		if (tRptPublisher.getTRpt() == null) {
			jpaCountQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptPublisher.getTRpt());

			jpaCountQuery.bind(TRPT, tRptPublisher.getTRpt());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptPublisher> getTRptPublishersByTTargetRecipientPref(
			final SearchFilter<TTargetRecipientPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptPublisherByTTargetRecipientPref", queryParam,
				index, maxresult);
	}

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptPublishersByTTargetRecipientPref(final SearchFilter<TTargetRecipientPref> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptPublishersByTTargetRecipientPref", queryParam);
	}

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptPublisher> getTRptPublishersByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptPublisherByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptPublishersByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptPublishersByTPers", queryParam);
	}

	/**
	 * Retrieve TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptPublisher> list of TRptPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptPublisher> getTRptPublishersByTRpt(final SearchFilter<TRpt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptPublisherByTRpt", queryParam, index, maxresult);
	}

	/**
	 * Count TRptPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptPublishersByTRpt(final SearchFilter<TRpt> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptPublishersByTRpt", queryParam);
	}

}
