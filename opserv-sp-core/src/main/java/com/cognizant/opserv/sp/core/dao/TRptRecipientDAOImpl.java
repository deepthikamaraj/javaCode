package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TNoteStatus;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRejectReason;
import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptRecipient;
import com.cognizant.opserv.sp.core.entity.TRptRecipientId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptRecipientDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptRecipientDAO")
public class TRptRecipientDAOImpl implements TRptRecipientDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptRecipientDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";
	private static final String TRPT = "tRpt";
	private static final String TACKSTATUS = "tAckStatus";

	private final Class<TRptRecipient> clazz;

	public Class<TRptRecipient> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptRecipientDAOImpl() {
		super();
		this.clazz = TRptRecipient.class;
	}

	private static final String JPAQL = "select tRptRecipiententity from TRptRecipient tRptRecipiententity";

	private static final String JPACOUNTQL = "select count(*) from TRptRecipient tRptRecipiententity";

	private static final String[] RESTRICTIONS = {
			"tRptRecipiententity.tRptRecipientId.rptId = #{tRptRecipiententity.tRptRecipientId.getRptId}",
			"tRptRecipiententity.tRptRecipientId.staffId = #{tRptRecipiententity.tRptRecipientId.getStaffId}",
			"Date(tRptRecipiententity.ackDt) = '#{tRptRecipiententity.getAckDt}'",
			"Date(tRptRecipiententity.rejectDt) = '#{tRptRecipiententity.getRejectDt}'",
			"Date(tRptRecipiententity.readDt) = '#{tRptRecipiententity.getReadDt}'",
			"tRptRecipiententity.tenantId = #{tRptRecipiententity.getTenantId}",
			"tRptRecipiententity.rejectReason like '#{tRptRecipiententity.getRejectReason}%'",
			"tRptRecipiententity.noteStatusId = #{tRptRecipiententity.getNoteStatusId}",
			"tRptRecipiententity.tPers.staffId = #{tRptRecipiententity.tPers.getStaffId}",
			"tRptRecipiententity.tRpt.rptId = #{tRptRecipiententity.tRpt.getRptId}",
			"tRptRecipiententity.ackStatusId = #{tRptRecipiententity.getAckStatusId}",
			"tRptDataSourceentity.rptDesign = #{tRptDataSourceentity.rptDesign}"};

	/**
	 * Stores a new TRptRecipient entity object in to the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be persisted
	 * @return tRptRecipient Persisted TRptRecipient object
	 */
	public TRptRecipient createTRptRecipient(final TRptRecipient tRptRecipient) {
		LOGGER.info("=========== Create TRptRecipient ===========");
		return genericDAO.store(tRptRecipient);
	}

	/**
	 * Deletes a TRptRecipient entity object from the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be deleted
	 */
	public void deleteTRptRecipient(final TRptRecipientId tRptRecipientId) {
		LOGGER.info("=========== Delete TRptRecipient ===========");
		final TRptRecipient tRptRecipient = genericDAO.get(clazz, tRptRecipientId);
		genericDAO.remove(tRptRecipient);
	}

	/**
	 * Updates a TRptRecipient entity object in to the persistent store
	 * 
	 * @param tRptRecipient
	 *            TRptRecipient Entity object to be updated
	 * @return tRptRecipient Persisted TRptRecipient object
	 */
	public TRptRecipient updateTRptRecipient(final TRptRecipient tRptRecipient) {
		LOGGER.info("=========== Update TRptRecipient ===========");
		return genericDAO.update(tRptRecipient);
	}

	/**
	 * Retrieve an TRptRecipient object based on given TRptRecipient TRptRecipientId.
	 * 
	 * @param tRptRecipientId
	 *            the primary key value of the TRptRecipient Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptRecipient findTRptRecipientById(final TRptRecipientId tRptRecipientId) {
		LOGGER.info("find TRptRecipient instance with TRptRecipientId: " + tRptRecipientId);
		return genericDAO.get(clazz, tRptRecipientId);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipient if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptRecipient> findTRptRecipients(final SearchFilter<TRptRecipient> searchFilter) {
		LOGGER.info("=========== Find TRptRecipients ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptRecipient tRptRecipient = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptRecipiententity", tRptRecipient);

		if (tRptRecipient.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptRecipient.getTPers());

			jpaQuery.bind(TPERS, tRptRecipient.getTPers());
		}

		if (tRptRecipient.getTRpt() == null) {
			jpaQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptRecipient.getTRpt());

			jpaQuery.bind(TRPT, tRptRecipient.getTRpt());
		}

		if (tRptRecipient.getAckStatusId() == null) {
			jpaQuery.bind(TACKSTATUS, new TAckStatus());
		} else {
			LOGGER.info("tAckStatus {}"+ tRptRecipient.getAckStatusId());

			jpaQuery.bind(TACKSTATUS, tRptRecipient.getAckStatusId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptRecipients.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptRecipients(final SearchFilter<TRptRecipient> searchFilter) {
		LOGGER.info("=========== Count TRptRecipient ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptRecipient tRptRecipient = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptRecipiententity", tRptRecipient);

		if (tRptRecipient.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tRptRecipient.getTPers());

			jpaCountQuery.bind(TPERS, tRptRecipient.getTPers());
		}

		if (tRptRecipient.getTRpt() == null) {
			jpaCountQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptRecipient.getTRpt());

			jpaCountQuery.bind(TRPT, tRptRecipient.getTRpt());
		}

		if (tRptRecipient.getAckStatusId() == null) {
			jpaCountQuery.bind(TACKSTATUS, new TAckStatus());
		} else {
			LOGGER.info("tAckStatus {}"+ tRptRecipient.getAckStatusId());

			jpaCountQuery.bind(TACKSTATUS, tRptRecipient.getAckStatusId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptRecipient> getTRptRecipientsByTNoteStatus(final SearchFilter<TNoteStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTNoteStatus", queryParam, index, maxresult);
	}

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptRecipientsByTNoteStatus(final SearchFilter<TNoteStatus> searchFilter) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptRecipientsByTNoteStatus", queryParam);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptRecipient> getTRptRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptRecipientsByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptRecipientsByTPers", queryParam);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptRecipient> getTRptRecipientsByTRpt(final SearchFilter<TRpt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTRpt", queryParam, index, maxresult);
	}

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptRecipientsByTRpt(final SearchFilter<TRpt> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptRecipientsByTRpt", queryParam);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRejectReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptRecipient> getTRptRecipientsByTRejectReason(final SearchFilter<TRejectReason> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTRejectReason", queryParam, index, maxresult);
	}

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRejectReason type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptRecipientsByTRejectReason(final SearchFilter<TRejectReason> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptRecipientsByTRejectReason", queryParam);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptRecipient> getTRptRecipientsByTAckStatus(final SearchFilter<TAckStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tAckStatus.getTAckStatusId().getAckStatusId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTAckStatus",queryParam , index, maxresult);
	}

	/**
	 * Count TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TAckStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptRecipientsByTAckStatus(final SearchFilter<TAckStatus> searchFilter) {

		final TAckStatus tAckStatus = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tAckStatus.getTAckStatusId().getAckStatusId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptRecipientsByTAckStatus",queryParam );
	}
	
	/**
	 * Retrieve TRptRecipient based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipient if it exists against
	 *         given criteria. Returns null if not found
	 */
	public List<TRptRecipient> findTRptRecipientsByMonth(
			final SearchFilter<TRptRecipient> searchFilter) {
		List<Object> list = new ArrayList<Object>();
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptRecipient tRptRecipient = searchFilter.getEntityClass();

		Calendar cal = Calendar.getInstance();
		cal.setTime(tRptRecipient.getReadDt());
		list.add(cal.get(Calendar.MONTH)+1);
		list.add(cal.get(Calendar.YEAR));
		list.add(tRptRecipient.getTRptRecipientId().getStaffId());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTRptByRecipientAndMonth",
						list, index, maxresult);

	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRptTypeId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptRecipient> list of TRptRecipients if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TRptRecipient> getTRptRecipientsByTRptType(Integer reportTypeId,Short tenantId) {
		
		List queryParam = new ArrayList();
		queryParam.add(reportTypeId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientsbyTRptType", queryParam , 0, -1);
	}

	/**
	 * Retrieve TRptRecipient based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt and TPers type.
	 * 
	 * @param tRpt
	 * @param tPers
	 * @return TRptRecipient if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TRptRecipient> getTRptRecipientByTRptAndTPers(TRpt tRpt,TPers tPers,int index,int maxResult) {
		
		List<Object> list = new ArrayList<Object>();
		list.add(tRpt);
		list.add(tPers);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptRecipientByTRptAndTPers", list , index, maxResult);
	}

	/**
	 * Retrieve Report design object based on given TRptRecipientId.
	 * The search criteria is of TRpt and TPers type.
	 * 
	 * @param tRptRecipientId
	 * 		the primary key value of the TRptRecipient Entity.
	 * 
	 *  @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public byte[] findRptDesignByTRptAndTPers(TRptRecipientId tRptRecipientId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptRecipientId);
		List<?> list = genericDAO.findEntitiesByNamedQuery("FindRptDesignByTRptAndTPers", queryParam) ;
		if(list !=null || !list.isEmpty()){
			return (byte[]) list.get(0) ;
		}
		return null;
	}

	
}
