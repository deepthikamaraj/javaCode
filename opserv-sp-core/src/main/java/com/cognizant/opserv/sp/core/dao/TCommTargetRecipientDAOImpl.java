package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipientId;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommTargetRecipientDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommTargetRecipientDAO")
public class TCommTargetRecipientDAOImpl implements TCommTargetRecipientDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommTargetRecipientDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";
	private static final String TCOMM = "tComm";

	private final Class<TCommTargetRecipient> clazz;

	public Class<TCommTargetRecipient> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommTargetRecipientDAOImpl() {
		super();
		this.clazz = TCommTargetRecipient.class;
	}

	private static final String JPAQL = "select tCommTargetRecipiententity from TCommTargetRecipient tCommTargetRecipiententity";

	private static final String JPACOUNTQL = "select count(*) from TCommTargetRecipient tCommTargetRecipiententity";

	private static final String[] RESTRICTIONS = {
			"tCommTargetRecipiententity.tCommTargetRecipientId.staffId = #{tCommTargetRecipiententity.tCommTargetRecipientId.getStaffId}",
			"tCommTargetRecipiententity.tCommTargetRecipientId.commId = #{tCommTargetRecipiententity.tCommTargetRecipientId.getCommId}",
			"tCommTargetRecipiententity.activeFlag = #{tCommTargetRecipiententity.getActiveFlag}",
			"tCommTargetRecipiententity.createdBy = #{tCommTargetRecipiententity.getCreatedBy}",
			"Date(tCommTargetRecipiententity.createDt) = '#{tCommTargetRecipiententity.getCreateDt}'",
			"tCommTargetRecipiententity.updatedBy = #{tCommTargetRecipiententity.getUpdatedBy}",
			"Date(tCommTargetRecipiententity.updateDt) = '#{tCommTargetRecipiententity.getUpdateDt}'",
			"tCommTargetRecipiententity.tenantId = #{tCommTargetRecipiententity.getTenantId}",
			"tCommTargetRecipiententity.tPers.staffId = #{tCommTargetRecipiententity.tPers.getStaffId}",
			"tCommTargetRecipiententity.tComm.commId = #{tCommTargetRecipiententity.tComm.getCommId}" };

	/**
	 * Stores a new TCommTargetRecipient entity object in to the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be persisted
	 * @return tCommTargetRecipient Persisted TCommTargetRecipient object
	 */
	public TCommTargetRecipient createTCommTargetRecipient(final TCommTargetRecipient tCommTargetRecipient) {
		LOGGER.info("=========== Create TCommTargetRecipient ===========");
		return genericDAO.store(tCommTargetRecipient);
	}

	/**
	 * Deletes a TCommTargetRecipient entity object from the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be deleted
	 */
	public void deleteTCommTargetRecipient(final TCommTargetRecipientId tCommTargetRecipientId) {
		LOGGER.info("=========== Delete TCommTargetRecipient ===========");
		final TCommTargetRecipient tCommTargetRecipient = genericDAO.get(clazz, tCommTargetRecipientId);
		genericDAO.remove(tCommTargetRecipient);
	}

	/**
	 * Updates a TCommTargetRecipient entity object in to the persistent store
	 * 
	 * @param tCommTargetRecipient
	 *            TCommTargetRecipient Entity object to be updated
	 * @return tCommTargetRecipient Persisted TCommTargetRecipient object
	 */
	public TCommTargetRecipient updateTCommTargetRecipient(final TCommTargetRecipient tCommTargetRecipient) {
		LOGGER.info("=========== Update TCommTargetRecipient ===========");
		return genericDAO.update(tCommTargetRecipient);
	}

	/**
	 * Retrieve an TCommTargetRecipient object based on given TCommTargetRecipient TCommTargetRecipientId.
	 * 
	 * @param tCommTargetRecipientId
	 *            the primary key value of the TCommTargetRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommTargetRecipient findTCommTargetRecipientById(final TCommTargetRecipientId tCommTargetRecipientId) {
		LOGGER.info("find TCommTargetRecipient instance with TCommTargetRecipientId: " + tCommTargetRecipientId);
		return genericDAO.get(clazz, tCommTargetRecipientId);
	}

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipient if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommTargetRecipient> findTCommTargetRecipients(final SearchFilter<TCommTargetRecipient> searchFilter) {
		LOGGER.info("=========== Find TCommTargetRecipients ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommTargetRecipient tCommTargetRecipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommTargetRecipiententity", tCommTargetRecipient);

		if (tCommTargetRecipient.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommTargetRecipient.getTPers());

			jpaQuery.bind(TPERS, tCommTargetRecipient.getTPers());
		}

		if (tCommTargetRecipient.getTComm() == null) {
			jpaQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommTargetRecipient.getTComm());

			jpaQuery.bind(TCOMM, tCommTargetRecipient.getTComm());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommTargetRecipients.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommTargetRecipients(final SearchFilter<TCommTargetRecipient> searchFilter) {
		LOGGER.info("=========== Count TCommTargetRecipient ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommTargetRecipient tCommTargetRecipient = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommTargetRecipiententity", tCommTargetRecipient);

		if (tCommTargetRecipient.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommTargetRecipient.getTPers());

			jpaCountQuery.bind(TPERS, tCommTargetRecipient.getTPers());
		}

		if (tCommTargetRecipient.getTComm() == null) {
			jpaCountQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommTargetRecipient.getTComm());

			jpaCountQuery.bind(TCOMM, tCommTargetRecipient.getTComm());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommTargetRecipient> getTCommTargetRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommTargetRecipientByTPers", tPersList, index, maxresult);
	}

	/**
	 * Count TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommTargetRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		return genericDAO.findEntitiesByNamedQuery("CountTCommTargetRecipientsByTPers", tPersList);
	}

	/**
	 * Retrieve TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommTargetRecipient> list of TCommTargetRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommTargetRecipient> getTCommTargetRecipientsByTComm(final SearchFilter<TComm> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommTargetRecipientByTComm", tCommList, index, maxresult);
	}

	/**
	 * Count TCommTargetRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommTargetRecipientsByTComm(final SearchFilter<TComm> searchFilter) {

		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		return genericDAO.findEntitiesByNamedQuery("CountTCommTargetRecipientsByTComm", tCommList);
	}
	/**
	 * Gets the t comm target recipients by status date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm target recipients by status date
	 */
	public List<TCommTargetRecipient> getTCommTargetRecipientsByStatusDate(final SearchFilter<TCommTargetRecipient> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommTargetRecipient tCommTargetRecipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommTargetRecipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommTargetRecipient.getTCommTargetRecipientId().getStaffId());
		paramList.add(tCommTargetRecipient.getTComm().getValidityStartDt());
		paramList.add(tCommTargetRecipient.getTComm().getValidityEndDt());
		paramList.add(tCommTargetRecipient.getTComm().getTCommType().getCommTypeId());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommTargetRecipientByStatusDate", paramList, index, maxresult);
	}
	
	/**
	 * Creates the t comm target recipients.
	 *
	 * @param tCommTargetRecipients the t comm target recipients
	 * @return the list
	 */
	@Override
	public List<TCommTargetRecipient> createTCommTargetRecipients(List<TCommTargetRecipient> tCommTargetRecipients) {
		return genericDAO.storeBatch(tCommTargetRecipients);
	}
	
	 /**
 	 * Update t comm target receipient.
 	 *
 	 * @param tCommTargetRecipients the t comm target recipients
 	 * @return the list
 	 */
	public List<TCommTargetRecipient> updateTCommTargetReceipients(final List<TCommTargetRecipient> tCommTargetRecipients) {
		LOGGER.info("=========== Update tCommTargetReceipients ===========");
		return genericDAO.update(tCommTargetRecipients);
	}

}
