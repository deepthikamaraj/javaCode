package com.cognizant.opserv.sp.core.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommStatus;
import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommDAO")
public class TCommDAOImpl implements TCommDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCOMMTYPE = "tCommType";
	private static final String TCOMMSTATUS = "tCommStatus";

	private final Class<TComm> clazz;

	public Class<TComm> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommDAOImpl() {
		super();
		this.clazz = TComm.class;
	}

	private static final String JPAQL = "select tCommentity from TComm tCommentity";

	private static final String JPACOUNTQL = "select count(tCommentity) from TComm tCommentity";

	private static final String[] RESTRICTIONS = { "tCommentity.commId = #{tCommentity.getCommId}",
			"Date(tCommentity.validityStartDt) = '#{tCommentity.getValidityStartDt}'",
			"Date(tCommentity.validityEndDt) = '#{tCommentity.getValidityEndDt}'",
			"Date(tCommentity.publishedDt) = '#{tCommentity.getPublishedDt}'",
			"tCommentity.commDetail like '#{tCommentity.getCommDetail}%'",
			"Date(tCommentity.draftDt) = '#{tCommentity.getDraftDt}'",
			"tCommentity.commTitle like '#{tCommentity.getCommTitle}%'",
			"tCommentity.ackRequired = #{tCommentity.getAckRequired}",
			"tCommentity.importanceFlag = #{tCommentity.getImportanceFlag}",
			"tCommentity.createdBy = #{tCommentity.getCreatedBy}",
			"Date(tCommentity.createDt) = '#{tCommentity.getCreateDt}'",
			"tCommentity.updatedBy = #{tCommentity.getUpdatedBy}",
			"Date(tCommentity.updateDate) = '#{tCommentity.getUpdateDate}'",
			"tCommentity.tenantId = #{tCommentity.getTenantId}",
			"Date(tCommentity.activityStartDt) = '#{tCommentity.getActivityStartDt}'",
			"Date(tCommentity.activityEndDt) = '#{tCommentity.getActivityEndDt}'",
			"tCommentity.tCommType.commTypeId = #{tCommentity.tCommType.getCommTypeId}",
			"tCommentity.tCommStatus.commStatusId = #{tCommentity.tCommStatus.getCommStatusId}" };

	/**
	 * Stores a new TComm entity object in to the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be persisted
	 * @return tComm Persisted TComm object
	 */
	public TComm createTComm(final TComm tComm) {
		LOGGER.info("=========== Create TComm ===========");
		return genericDAO.store(tComm);
	}

	/**
	 * Deletes a TComm entity object from the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be deleted
	 */
	public void deleteTComm(final Long commId) {
		LOGGER.info("=========== Delete TComm ===========");
		final TComm tComm = genericDAO.get(clazz, commId);
		genericDAO.remove(tComm);
	}

	/**
	 * Updates a TComm entity object in to the persistent store
	 * 
	 * @param tComm
	 *            TComm Entity object to be updated
	 * @return tComm Persisted TComm object
	 */
	public TComm updateTComm(final TComm tComm) {
		LOGGER.info("=========== Update TComm ===========");
		return genericDAO.update(tComm);
	}

	/**
	 * Retrieve an TComm object based on given TComm commId.
	 * 
	 * @param tCommId
	 *            the primary key value of the TComm Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TComm findTCommById(final Long tCommId) {
		LOGGER.info("find TComm instance with commId: " + tCommId);
		return genericDAO.get(clazz, tCommId);
	}

	/**
	 * Retrieve TComm based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComm if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TComm> findTComms(final SearchFilter<TComm> searchFilter) {
		LOGGER.info("=========== Find TComms ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommentity", tComm);

		if (tComm.getTCommType() == null) {
			jpaQuery.bind(TCOMMTYPE, new TCommType());
		} else {
			LOGGER.info("tCommType {}" + tComm.getTCommType());

			jpaQuery.bind(TCOMMTYPE, tComm.getTCommType());
		}

		if (tComm.getTCommStatus() == null) {
			jpaQuery.bind(TCOMMSTATUS, new TCommStatus());
		} else {
			LOGGER.info("tCommStatus {}" + tComm.getTCommStatus());

			jpaQuery.bind(TCOMMSTATUS, tComm.getTCommStatus());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TComms.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTComms(final SearchFilter<TComm> searchFilter) {
		LOGGER.info("=========== Count TComm ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommentity", tComm);

		if (tComm.getTCommType() == null) {
			jpaCountQuery.bind(TCOMMTYPE, new TCommType());
		} else {
			LOGGER.info("tCommType {}" + tComm.getTCommType());

			jpaCountQuery.bind(TCOMMTYPE, tComm.getTCommType());
		}

		if (tComm.getTCommStatus() == null) {
			jpaCountQuery.bind(TCOMMSTATUS, new TCommStatus());
		} else {
			LOGGER.info("tCommStatus {}" + tComm.getTCommStatus());

			jpaCountQuery.bind(TCOMMSTATUS, tComm.getTCommStatus());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TComm> getTCommsByTCommType(final SearchFilter<TCommType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommType tCommType = searchFilter.getEntityClass();
		List<Object> tCommTypeList = new ArrayList<Object>();
		tCommTypeList.add(tCommType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommByTCommType", tCommTypeList, index, maxresult);
	}

	/**
	 * Count TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommsByTCommType(final SearchFilter<TCommType> searchFilter) {

		final TCommType tCommType = searchFilter.getEntityClass();
		List<Object> tCommTypeList = new ArrayList<Object>();
		tCommTypeList.add(tCommType);
		return genericDAO.findEntitiesByNamedQuery("CountTCommsByTCommType", tCommTypeList);
	}

	/**
	 * Retrieve TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TComm> list of TComms if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TComm> getTCommsByTCommStatus(final SearchFilter<TCommStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommStatus tCommStatus = searchFilter.getEntityClass();
		List<Object> tCommStatusList = new ArrayList<Object>();
		tCommStatusList.add(tCommStatus);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommByTCommStatus", tCommStatusList, index, maxresult);
	}

	/**
	 * Count TComm based on given search criteria using JPA named Query.
	 * The search criteria is of TCommStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommsByTCommStatus(final SearchFilter<TCommStatus> searchFilter) {

		final TCommStatus tCommStatus = searchFilter.getEntityClass();
		List<Object> tCommStatusList = new ArrayList<Object>();
		tCommStatusList.add(tCommStatus);
		return genericDAO.findEntitiesByNamedQuery("CountTCommsByTCommStatus", tCommStatusList);
	}

	@Override
	public List<TComm> getTCommsByTCommTypeStatus(
			SearchFilter<TComm> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getCreatedBy());
		paramList.add(tComm.getValidityEndDt());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommByStatusType",paramList, index, maxresult);
	}


	@Override
	public List<TComm> getTCommsByAdvanceSearch(
			SearchFilter<TComm> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getCreatedBy());
		paramList.add(tComm.getActivityStartDt());
		paramList.add(tComm.getValidityStartDt());
		paramList.add(tComm.getValidityEndDt());
		paramList.add(tComm.getCommTitle());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommByStatusTypeTitle",paramList, index, maxresult);
	}
	/**
	 * Gets the t comms by t comm type status_ activity.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by t comm type status_ activity
	 */
	@Override
	public List<TComm> getTCommsByTCommTypeStatusActivity(
			SearchFilter<TCommStatus> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCommStatus tCommStatus = searchFilter.getEntityClass();
		List<Object> tCommStatusList = new ArrayList<Object>();
		tCommStatusList.add(tCommStatus);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommsActivityByStatusAndType",tCommStatusList, index, maxresult);
	}
	/**
	 * Gets the t comms by month.
	 *
	 * @param searchFilter the search filter
	 * @param statusIdentity the status identity
	 * @return the t comms by month
	 */
	@Override
	public List<TComm> getTCommsByMonth(final SearchFilter<TComm> searchFilter,Integer statusIdentity) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		List paramList = new ArrayList();
		paramList.add(tComm.getCreatedBy());
		paramList.add(tComm.getValidityEndDt());
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(statusIdentity);
		paramList.add(tComm.getTCommType().getCommTypeId());
		
		//Added to fix defect 28783(to get all the activity data according to selected calendar month.)
        Calendar caldate = Calendar.getInstance();
	    caldate.setTime(tComm.getActivityStartDt()) ;
	    
	  	caldate.set(Calendar.DAY_OF_MONTH, 1);
	    //First date of calendar month.
	  	paramList.add(df.format(caldate.getTime()));
	  	 
	  	caldate.set(Calendar.DAY_OF_MONTH, caldate.getActualMaximum(Calendar.DAY_OF_MONTH));
	  	
	  	//Last date of calendar month.
	  	paramList.add(df.format(caldate.getTime()));
	  	paramList.add(tComm.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommsByMonth",paramList, index, maxresult);
	}
	/**
	 * Gets the t comms by created by type status date for published.
	 *
	 * @param searchFilter the search filter
	 * @param statusIdentity the status identity
	 * @return the t comms by created by type status date for published
	 */
	public List<TComm> getTCommsByCreatedByTypeStatusDateForPublished(final SearchFilter<TComm> searchFilter, Integer statusIdentity){
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(tComm.getCreatedBy());
		paramList.add(tComm.getValidityEndDt());
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(statusIdentity);
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommsByCommDate", paramList, index, maxresult);
	}
	/**
	 * Gets the t comms by advance search.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by advance search
	 */
	public Object findLastPublishedCommId(final SearchFilter<TComm> searchFilter) {
		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final TComm tComm = searchFilter.getEntityClass();
		final Integer tCommTypeId= tComm.getTCommType().getCommTypeId();
		final Date validityStDt = tComm.getValidityStartDt();
		final Integer tCommStatusId = tComm.getTCommStatus().getCommStatusId();
		List paramList = new ArrayList();
		paramList.add(tCommTypeId);
		paramList.add(validityStDt);
		paramList.add(tCommStatusId);
		paramList.add(tComm.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindLastPublishedCommId", paramList, index, maxresult);
	}
	/**
	 * Gets the t comms draft by t comm type status.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms draft by t comm type status
	 */
	public List<TComm> getTCommsDraftByTCommTypeStatus(
			SearchFilter<TComm> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getCreatedBy());
		paramList.add(tComm.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommDraftByStatusType",paramList, index, maxresult);
	}
	/**
	 * Gets the t comms by date.
	 *
	 * @param searchFilter the search filter
	 * @return the t comms by date
	 */
	@Override
	public List<TComm> getTCommsByDate(SearchFilter<TComm> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final TComm tComm = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getActivityStartDt());
		paramList.add(tComm.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActivityByDate", paramList, index, maxresult);
	}

	/**
	 * Find activity for logged user.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	@Override
	public List<TComm> findActivityForLoggedUser(SearchFilter<TComm> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final TComm tComm = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tComm.getTCommStatus().getCommStatusId());
		paramList.add(tComm.getTCommType().getCommTypeId());
		paramList.add(tComm.getTenantId());
		paramList.add(tComm.getCommId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActivityByCommId", paramList, index, maxresult);
	}
	
	/**
	 * Piblish announcement.
	 *
	 * @param communicationId the communication id
	 * @param publishedDate the published date
	 * @param userId the user id
	 */
	@Override
	public void piblishAnnouncement(Long communicationId, Date publishedDate,
			Integer userId,int status) {
		List queryParams = new ArrayList();
		queryParams.add(publishedDate);
		queryParams.add(userId);
		queryParams.add(communicationId);
		queryParams.add(status);
		genericDAO.updateEntitiesNamedQuery("PublishAssignment", queryParams);
		
	}
	
	@Override
	public List<TComm> getAnnouncementsByUser(Integer userId, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(CommunicationStatusType.PUBLISHED.getId());
		paramList.add(userId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAnnouncementsByUser", paramList, 0, 3);
	}
	
	@Override
	public List<TComm> getAnnouncementsByPublisher(Integer userId, Integer typeId,
			PaginationInfo paginInfo, Short tenantId) {
		
		final int index = paginInfo.getStartIndex();
		final int maxresult = paginInfo.getMaxRows();
		
		List paramList = new ArrayList();
		paramList.add(userId);
		paramList.add(typeId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAnnouncementsByPublisher", paramList, index, maxresult);
	}
}
