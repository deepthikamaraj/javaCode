package com.cognizant.opserv.sp.core.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommReceipient;
import com.cognizant.opserv.sp.core.entity.TCommReceipientId;
import com.cognizant.opserv.sp.core.entity.TNoteStatus;
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
 * Class provides API implementation for TCommReceipientDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommReceipientDAO")
public class TCommReceipientDAOImpl implements TCommReceipientDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommReceipientDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TNOTESTATUS = "tNoteStatus";
	private static final String TPERS = "tPers";
	private static final String TCOMM = "tComm";
	private static final String TACKSTATUS = "tAckStatus";

	private final Class<TCommReceipient> clazz;

	public Class<TCommReceipient> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommReceipientDAOImpl() {
		super();
		this.clazz = TCommReceipient.class;
	}

	private static final String JPAQL = "select tCommReceipiententity from TCommReceipient tCommReceipiententity";

	private static final String JPACOUNTQL = "select count(*) from TCommReceipient tCommReceipiententity";

	private static final String[] RESTRICTIONS = {
			"tCommReceipiententity.tCommReceipientId.commId = #{tCommReceipiententity.tCommReceipientId.getCommId}",
			"tCommReceipiententity.tCommReceipientId.staffId = #{tCommReceipiententity.tCommReceipientId.getStaffId}",
			"Date(tCommReceipiententity.commReadDt) = '#{tCommReceipiententity.getCommReadDt}'",
			"Date(tCommReceipiententity.commAckDt) = '#{tCommReceipiententity.getCommAckDt}'",
			"tCommReceipiententity.rejectReason like '#{tCommReceipiententity.getRejectReason}%'",
			"tCommReceipiententity.tenantId = #{tCommReceipiententity.getTenantId}",
			"tCommReceipiententity.noteStatusId = #{tCommReceipiententity.getNoteStatusId}",
			"tCommReceipiententity.tPers.staffId = #{tCommReceipiententity.tPers.getStaffId}",
			"tCommReceipiententity.tComm.commId = #{tCommReceipiententity.tComm.getCommId}",
			"tCommReceipiententity.ackStatusId = #{tCommReceipiententity.getAckStatusId}" };

	/**
	 * Stores a new TCommReceipient entity object in to the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be persisted
	 * @return tCommReceipient Persisted TCommReceipient object
	 */
	public TCommReceipient createTCommReceipient(final TCommReceipient tCommReceipient) {
		LOGGER.info("=========== Create TCommReceipient ===========");
		return genericDAO.store(tCommReceipient);
	}

	/**
	 * Deletes a TCommReceipient entity object from the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be deleted
	 */
	public void deleteTCommReceipient(final TCommReceipientId tCommReceipientId) {
		LOGGER.info("=========== Delete TCommReceipient ===========");
		final TCommReceipient tCommReceipient = genericDAO.get(clazz, tCommReceipientId);
		genericDAO.remove(tCommReceipient);
	}

	/**
	 * Updates a TCommReceipient entity object in to the persistent store
	 * 
	 * @param tCommReceipient
	 *            TCommReceipient Entity object to be updated
	 * @return tCommReceipient Persisted TCommReceipient object
	 */
	public TCommReceipient updateTCommReceipient(final TCommReceipient tCommReceipient) {
		LOGGER.info("=========== Update TCommReceipient ===========");
		return genericDAO.update(tCommReceipient);
	}

	/**
	 * Retrieve an TCommReceipient object based on given TCommReceipient TCommReceipientId.
	 * 
	 * @param tCommReceipientId
	 *            the primary key value of the TCommReceipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommReceipient findTCommReceipientById(final TCommReceipientId tCommReceipientId) {
		LOGGER.info("find TCommReceipient instance with TCommReceipientId: " + tCommReceipientId);
		return genericDAO.get(clazz, tCommReceipientId);
	}

	/**
	 * Retrieve TCommReceipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipient if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommReceipient> findTCommReceipients(final SearchFilter<TCommReceipient> searchFilter) {
		LOGGER.info("=========== Find TCommReceipients ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommReceipiententity", tCommReceipient);

		/*if (tCommReceipient.getTNoteStatus() == null) {
			jpaQuery.bind(TNOTESTATUS, new TNoteStatus());
		} else {
			LOGGER.info("tNoteStatus {}", tCommReceipient.getTNoteStatus());

			jpaQuery.bind(TNOTESTATUS, tCommReceipient.getTNoteStatus());
		}*/

		if (tCommReceipient.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommReceipient.getTPers());

			jpaQuery.bind(TPERS, tCommReceipient.getTPers());
		}

		if (tCommReceipient.getTComm() == null) {
			jpaQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommReceipient.getTComm());

			jpaQuery.bind(TCOMM, tCommReceipient.getTComm());
		}

		if (tCommReceipient.getAckStatusId() == null) {
			jpaQuery.bind(TACKSTATUS, new TAckStatus());
		} else {
			LOGGER.info("tAckStatus {}" + tCommReceipient.getAckStatusId());

			jpaQuery.bind(TACKSTATUS, tCommReceipient.getAckStatusId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommReceipients.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommReceipients(final SearchFilter<TCommReceipient> searchFilter) {
		LOGGER.info("=========== Count TCommReceipient ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommReceipiententity", tCommReceipient);

		/*if (tCommReceipient.getTNoteStatus() == null) {
			jpaCountQuery.bind(TNOTESTATUS, new TNoteStatus());
		} else {
			LOGGER.info("tNoteStatus {}", tCommReceipient.getTNoteStatus());

			jpaCountQuery.bind(TNOTESTATUS, tCommReceipient.getTNoteStatus());
		}*/

		if (tCommReceipient.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}" + tCommReceipient.getTPers());

			jpaCountQuery.bind(TPERS, tCommReceipient.getTPers());
		}

		if (tCommReceipient.getTComm() == null) {
			jpaCountQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommReceipient.getTComm());

			jpaCountQuery.bind(TCOMM, tCommReceipient.getTComm());
		}

		if (tCommReceipient.getAckStatusId() == null) {
			jpaCountQuery.bind(TACKSTATUS, new TAckStatus());
		} else {
			LOGGER.info("tAckStatus {}" + tCommReceipient.getAckStatusId());

			jpaCountQuery.bind(TACKSTATUS, tCommReceipient.getAckStatusId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommReceipient> getTCommReceipientsByTNoteStatus(final SearchFilter<TNoteStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TNoteStatus tNoteStatus = searchFilter.getEntityClass();
		List<Object> tNoteStatusList = new ArrayList<Object>();
		tNoteStatusList.add(tNoteStatus);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientByTNoteStatus", tNoteStatusList, index, maxresult);
	}

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommReceipientsByTNoteStatus(final SearchFilter<TNoteStatus> searchFilter) {

		final TNoteStatus tNoteStatus = searchFilter.getEntityClass();
		List<Object> tNoteStatusList = new ArrayList<Object>();
		tNoteStatusList.add(tNoteStatus);
		return genericDAO.findEntitiesByNamedQuery("CountTCommReceipientsByTNoteStatus", tNoteStatusList);
	}

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommReceipient> getTCommReceipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientByTPers", tPersList, index, maxresult);
	}

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommReceipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final TPers tPers = searchFilter.getEntityClass();
		List<Object> tPersList = new ArrayList<Object>();
		tPersList.add(tPers);
		return genericDAO.findEntitiesByNamedQuery("CountTCommReceipientsByTPers", tPersList);
	}

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommReceipient> getTCommReceipientsByTComm(final SearchFilter<TComm> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientByTComm", tCommList, index, maxresult);
	}

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommReceipientsByTComm(final SearchFilter<TComm> searchFilter) {

		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		return genericDAO.findEntitiesByNamedQuery("CountTCommReceipientsByTComm", tCommList);
	}

	/**
	 * Retrieve TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommReceipient> list of TCommReceipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommReceipient> getTCommReceipientsByTAckStatus(final SearchFilter<TAckStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		List<Object> tAckStatusList = new ArrayList<Object>();
		tAckStatusList.add(tAckStatus.getTAckStatusId().getAckStatusId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientByTAckStatus", tAckStatusList, index, maxresult);
	}

	/**
	 * Count TCommReceipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommReceipientsByTAckStatus(final SearchFilter<TAckStatus> searchFilter) {

		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		List<Object> tAckStatusList = new ArrayList<Object>();
		tAckStatusList.add(tAckStatus.getTAckStatusId().getAckStatusId());
		return genericDAO.findEntitiesByNamedQuery("CountTCommReceipientsByTAckStatus", tAckStatusList);
	}
	/**
	 * Gets the t comm recipients by status date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by status date
	 */
	public List<TCommReceipient> getTCommRecipientsByStatusDate(final SearchFilter<TCommReceipient> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getTenantId());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommRecipientByStatusDate", paramList, index, maxresult);
	}

	/**
	 * Gets the t comms by month for loggedin.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by month for loggedin
	 */
	@Override
	public List<Object[]> getTCommsByMonthForLoggedin(final SearchFilter<TCommReceipient> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		//paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		
		//Added to fix defect 28783(to get all the activity data according to selected calendar month.)
        Calendar caldate = Calendar.getInstance();
	    caldate.setTime(tCommReceipient.getTComm().getActivityStartDt()) ;
	    
	  	caldate.set(Calendar.DAY_OF_MONTH, 1);
	    //First date of calendar month.
	  	paramList.add(df.format(caldate.getTime()));
	  	 
	  	caldate.set(Calendar.DAY_OF_MONTH, caldate.getActualMaximum(Calendar.DAY_OF_MONTH));
	  	
	  	//Last date of calendar month.
	  	paramList.add(df.format(caldate.getTime()));
	  	paramList.add(tCommReceipient.getTComm().getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommsByMonthForLoggedIn",paramList, index, maxresult);
	}
	
	/**
	 * Gets the t comms by date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by date
	 */
	@Override
	public List<Object[]> getTCommsByDate(SearchFilter<TCommReceipient> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getActivityStartDt());
		paramList.add(tCommReceipient.getTComm().getTenantId());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActivityByDate", paramList, index, maxresult);
	}
	/**
	 * Gets the t comm recipients by ack value.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by ack value
	 */
	@Override
	public List<TCommReceipient> getTCommRecipientsByAckValue(final SearchFilter<TCommReceipient> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getTenantId());
		paramList.add(tCommReceipient.getAckStatusId());
		paramList.add(tCommReceipient.getTComm().getAckRequired());
		paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientByAckValue", paramList, index, maxresult);
	}

	/**
	 * Gets the count t comm recipients by ack value.
	 *
	 * @param searchFilter the search filter
	 * @return the count t comm recipients by ack value
	 */
	@Override
	public List<Object> getCountTCommRecipientsByAckValue(final SearchFilter<TCommReceipient> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getTenantId());
		paramList.add(tCommReceipient.getAckStatusId());
		paramList.add(tCommReceipient.getTComm().getAckRequired());
		paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCountTCommReceipientByAckValue", paramList, index, maxresult);
	}
	/**
	 * Gets the tcomm recepients by comm id and staff id.
	 *
	 * @param id the id
	 * @param staffId the staff id
	 * @param tenantId the tenant id
	 * @return the tcomm recepients by comm id and staff id
	 */
	@Override
	public List<TCommReceipient> getTcommRecepientsByCommIDAndStaffId(Long id,
			Integer staffId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(staffId);
		paramList.add(id);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommReceipientsByCommIdAndStaffId", paramList, 0, -1);
	}
	/**
	 * Gets the count t comm recipients all.
	 *
	 * @param searchFilter the search filter
	 * @return the count t comm recipients all
	 */
	@Override
	public List<Object> getCountTCommRecipientsAll(
			SearchFilter<TCommReceipient> searchFilter) {
		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getTenantId());
		paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCountTCommReceipientALL", paramList, index, maxresult);
	
	}

	/**
	 * Gets the t comm recipients by status publ date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comm recipients by status publ date
	 */
	@Override
	public List<Object[]> getTCommRecipientsByStatusPublDate(final SearchFilter<TCommReceipient> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommReceipient tCommReceipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		paramList.add(tCommReceipient.getTComm().getTCommStatus().getCommStatusId());
		paramList.add(tCommReceipient.getTCommReceipientId().getStaffId());
		paramList.add(tCommReceipient.getTComm().getValidityStartDt());
		paramList.add(tCommReceipient.getTComm().getValidityEndDt());
		paramList.add(tCommReceipient.getTComm().getTCommType().getCommTypeId());
		paramList.add(tCommReceipient.getTComm().getTenantId());
	
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommRecipientByStatusPublDate", paramList, index, maxresult);
	}
	 /**
     * Gets the t comm recipients by dynamic data.
     *
     * @param query the query
     * @param queryParam the query param
     * @param index the index
     * @param maxresult the maxresult
     * @return the t comm recipients by dynamic data
     */
	@Override
	public List<TCommReceipient> getTCommRecipientsByDynamicData(StringBuilder query,
			List queryParam, int index, int maxresult) {
		 final String finalQuery = query.toString();
		List<TCommReceipient> list = genericDAO.findEntitiesByBuildQueries(finalQuery, queryParam, index, maxresult);
		return list;
	}
	
	 /**
     * Creates the t comm recipients.
     *
     * @param tCommRecipients the t comm recipients
     * @return the list
     */
	@Override
	public List<TCommReceipient> createTCommReceipients(List<TCommReceipient> tCommReceipients) {
		return genericDAO.storeBatch(tCommReceipients);
	}
	
	 /**
     * Update t comm receipient.
     *
     * @param tCommReceipients the t comm receipients
     * @return the list
     */
	public List<TCommReceipient> updateTCommReceipients(final List<TCommReceipient> tCommReceipients) {
		LOGGER.info("=========== Update tCommReceipients ===========");
		return genericDAO.update(tCommReceipients);
	}
}
