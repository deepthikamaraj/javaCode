package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommPublisher;
import com.cognizant.opserv.sp.core.entity.TCommPublisherId;
import com.cognizant.opserv.sp.core.entity.TPers;
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
 * Class provides API implementation for TCommPublisherDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommPublisherDAO")
public class TCommPublisherDAOImpl implements TCommPublisherDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommPublisherDAOImpl.class);

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
	private static final String TCOMM = "tComm";

	private final Class<TCommPublisher> clazz;

	public Class<TCommPublisher> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommPublisherDAOImpl() {
		super();
		this.clazz = TCommPublisher.class;
	}

	private static final String JPAQL = "select tCommPublisherentity from TCommPublisher tCommPublisherentity";

	private static final String JPACOUNTQL = "select count(*) from TCommPublisher tCommPublisherentity";

	private static final String[] RESTRICTIONS = {
			"tCommPublisherentity.tCommPublisherId.staffId = #{tCommPublisherentity.tCommPublisherId.getStaffId}",
			"tCommPublisherentity.tCommPublisherId.commId = #{tCommPublisherentity.tCommPublisherId.getCommId}",
			"tCommPublisherentity.tenantId = #{tCommPublisherentity.getTenantId}",
			"tCommPublisherentity.tTargetRecipientPref.recipientPrefId = #{tCommPublisherentity.tTargetRecipientPref.getRecipientPrefId}",
			"tCommPublisherentity.tPers.staffId = #{tCommPublisherentity.tPers.getStaffId}",
			"tCommPublisherentity.tComm.commId = #{tCommPublisherentity.tComm.getCommId}" };

	/**
	 * Stores a new TCommPublisher entity object in to the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be persisted
	 * @return tCommPublisher Persisted TCommPublisher object
	 */
	public TCommPublisher createTCommPublisher(final TCommPublisher tCommPublisher) {
		LOGGER.info("=========== Create TCommPublisher ===========");
		return genericDAO.store(tCommPublisher);
	}

	/**
	 * Deletes a TCommPublisher entity object from the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be deleted
	 */
	public void deleteTCommPublisher(final TCommPublisherId tCommPublisherId) {
		LOGGER.info("=========== Delete TCommPublisher ===========");
		final TCommPublisher tCommPublisher = genericDAO.get(clazz, tCommPublisherId);
		genericDAO.remove(tCommPublisher);
	}

	/**
	 * Updates a TCommPublisher entity object in to the persistent store
	 * 
	 * @param tCommPublisher
	 *            TCommPublisher Entity object to be updated
	 * @return tCommPublisher Persisted TCommPublisher object
	 */
	public TCommPublisher updateTCommPublisher(final TCommPublisher tCommPublisher) {
		LOGGER.info("=========== Update TCommPublisher ===========");
		return genericDAO.update(tCommPublisher);
	}

	/**
	 * Retrieve an TCommPublisher object based on given TCommPublisher TCommPublisherId.
	 * 
	 * @param tCommPublisherId
	 *            the primary key value of the TCommPublisher Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommPublisher findTCommPublisherById(final TCommPublisherId tCommPublisherId) {
		LOGGER.info("find TCommPublisher instance with TCommPublisherId: " + tCommPublisherId);
		return genericDAO.get(clazz, tCommPublisherId);
	}

	/**
	 * Retrieve TCommPublisher based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublisher if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommPublisher> findTCommPublishers(final SearchFilter<TCommPublisher> searchFilter) {
		LOGGER.info("=========== Find TCommPublishers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommPublisher tCommPublisher = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommPublisherentity", tCommPublisher);

		if (tCommPublisher.getTTargetRecipientPref() == null) {
			jpaQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}" + tCommPublisher.getTTargetRecipientPref());

			jpaQuery.bind(TTARGETRECIPIENTPREF, tCommPublisher.getTTargetRecipientPref());
		}

		if (tCommPublisher.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommPublisher.getTPers());

			jpaQuery.bind(TPERS, tCommPublisher.getTPers());
		}

		if (tCommPublisher.getTComm() == null) {
			jpaQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}"+ tCommPublisher.getTComm());

			jpaQuery.bind(TCOMM, tCommPublisher.getTComm());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommPublishers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommPublishers(final SearchFilter<TCommPublisher> searchFilter) {
		LOGGER.info("=========== Count TCommPublisher ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommPublisher tCommPublisher = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommPublisherentity", tCommPublisher);

		if (tCommPublisher.getTTargetRecipientPref() == null) {
			jpaCountQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}" + tCommPublisher.getTTargetRecipientPref());

			jpaCountQuery.bind(TTARGETRECIPIENTPREF, tCommPublisher.getTTargetRecipientPref());
		}

		if (tCommPublisher.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommPublisher.getTPers());

			jpaCountQuery.bind(TPERS, tCommPublisher.getTPers());
		}

		if (tCommPublisher.getTComm() == null) {
			jpaCountQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommPublisher.getTComm());

			jpaCountQuery.bind(TCOMM, tCommPublisher.getTComm());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommPublisher> getTCommPublishersByTTargetRecipientPref(
			final SearchFilter<TTargetRecipientPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTargetRecipientPref tTargetRecipientPref = searchFilter.getEntityClass();
		List<Object> tTargetRecipientPrefList = new ArrayList<Object>();
		tTargetRecipientPrefList.add(tTargetRecipientPref);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommPublisherByTTargetRecipientPref", tTargetRecipientPrefList,
				index, maxresult);
	}

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommPublishersByTTargetRecipientPref(final SearchFilter<TTargetRecipientPref> searchFilter) {

		final TTargetRecipientPref tTargetRecipientPref = searchFilter.getEntityClass();
		List<Object> tTargetRecipientPrefList = new ArrayList<Object>();
		tTargetRecipientPrefList.add(tTargetRecipientPref);
		return genericDAO.findEntitiesByNamedQuery("CountTCommPublishersByTTargetRecipientPref", tTargetRecipientPrefList);
	}

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommPublisher> getTCommPublishersByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommPublisherByTPers", tPersList, index, maxresult);
	}

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommPublishersByTPers(final SearchFilter<TPers> searchFilter) {

		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		return genericDAO.findEntitiesByNamedQuery("CountTCommPublishersByTPers", tPersList);
	}

	/**
	 * Retrieve TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommPublisher> list of TCommPublishers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommPublisher> getTCommPublishersByTComm(final SearchFilter<TComm> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommPublisherByTComm", tCommList, index, maxresult);
	}

	/**
	 * Count TCommPublisher based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommPublishersByTComm(final SearchFilter<TComm> searchFilter) {

		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		return genericDAO.findEntitiesByNamedQuery("CountTCommPublishersByTComm", tCommList);
	}

}
