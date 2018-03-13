package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPubType;
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
 * Class provides API implementation for TTargetRecipientPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTargetRecipientPrefDAO")
public class TTargetRecipientPrefDAOImpl implements TTargetRecipientPrefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TTargetRecipientPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPERS = "tPers";
	private static final String TPUBTYPE = "tPubType";

	private final Class<TTargetRecipientPref> clazz;

	public Class<TTargetRecipientPref> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTargetRecipientPrefDAOImpl() {
		super();
		this.clazz = TTargetRecipientPref.class;
	}

	private static final String JPAQL = "select tTargetRecipientPrefentity from TTargetRecipientPref tTargetRecipientPrefentity";

	private static final String JPACOUNTQL = "select count(tTargetRecipientPrefentity) from TTargetRecipientPref tTargetRecipientPrefentity";

	private static final String[] RESTRICTIONS = {
			"tTargetRecipientPrefentity.recipientPrefId = #{tTargetRecipientPrefentity.getRecipientPrefId}",
			"tTargetRecipientPrefentity.srchPrefName like '#{tTargetRecipientPrefentity.getSrchPrefName}%'",
			"tTargetRecipientPrefentity.srchPrefDesc = #{tTargetRecipientPrefentity.getSrchPrefDesc}",
			"tTargetRecipientPrefentity.dtPeriodId = #{tTargetRecipientPrefentity.getDtPeriodId}",
			"Date(tTargetRecipientPrefentity.srchCritStartDt) = '#{tTargetRecipientPrefentity.getSrchCritStartDt}'",
			"Date(tTargetRecipientPrefentity.srchCritEndDt) = '#{tTargetRecipientPrefentity.getSrchCritEndDt}'",
			"tTargetRecipientPrefentity.activeFlag = #{tTargetRecipientPrefentity.getActiveFlag}",
			"tTargetRecipientPrefentity.createdBy = #{tTargetRecipientPrefentity.getCreatedBy}",
			"Date(tTargetRecipientPrefentity.createDt) = '#{tTargetRecipientPrefentity.getCreateDt}'",
			"tTargetRecipientPrefentity.updatedBy = #{tTargetRecipientPrefentity.getUpdatedBy}",
			"Date(tTargetRecipientPrefentity.updateDt) = '#{tTargetRecipientPrefentity.getUpdateDt}'",
			"tTargetRecipientPrefentity.tenantId = #{tTargetRecipientPrefentity.getTenantId}",
			"tTargetRecipientPrefentity.tPers.staffId = #{tTargetRecipientPrefentity.tPers.getStaffId}",
			"tTargetRecipientPrefentity.tPubType.pubTypeId = #{tTargetRecipientPrefentity.tPubType.getPubTypeId}" };

	/**
	 * Stores a new TTargetRecipientPref entity object in to the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be persisted
	 * @return tTargetRecipientPref Persisted TTargetRecipientPref object
	 */
	public TTargetRecipientPref createTTargetRecipientPref(final TTargetRecipientPref tTargetRecipientPref) {
		LOGGER.info("=========== Create TTargetRecipientPref ===========");
		return genericDAO.store(tTargetRecipientPref);
	}

	/**
	 * Deletes a TTargetRecipientPref entity object from the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be deleted
	 */
	public void deleteTTargetRecipientPref(final Integer recipientPrefId) {
		LOGGER.info("=========== Delete TTargetRecipientPref ===========");
		final TTargetRecipientPref tTargetRecipientPref = genericDAO.get(clazz, recipientPrefId);
		genericDAO.remove(tTargetRecipientPref);
	}

	/**
	 * Updates a TTargetRecipientPref entity object in to the persistent store
	 * 
	 * @param tTargetRecipientPref
	 *            TTargetRecipientPref Entity object to be updated
	 * @return tTargetRecipientPref Persisted TTargetRecipientPref object
	 */
	public TTargetRecipientPref updateTTargetRecipientPref(final TTargetRecipientPref tTargetRecipientPref) {
		LOGGER.info("=========== Update TTargetRecipientPref ===========");
		return genericDAO.update(tTargetRecipientPref);
	}

	/**
	 * Retrieve an TTargetRecipientPref object based on given TTargetRecipientPref recipientPrefId.
	 * 
	 * @param tTargetRecipientPrefId
	 *            the primary key value of the TTargetRecipientPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTargetRecipientPref findTTargetRecipientPrefById(final Integer tTargetRecipientPrefId) {
		LOGGER.info("find TTargetRecipientPref instance with recipientPrefId: " + tTargetRecipientPrefId);
		return genericDAO.get(clazz, tTargetRecipientPrefId);
	}

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPref if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTargetRecipientPref> findTTargetRecipientPrefs(final SearchFilter<TTargetRecipientPref> searchFilter) {
		LOGGER.info("=========== Find TTargetRecipientPrefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTargetRecipientPref tTargetRecipientPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTargetRecipientPrefentity", tTargetRecipientPref);

		if (tTargetRecipientPref.getTPers() == null) {
			jpaQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tTargetRecipientPref.getTPers());

			jpaQuery.bind(TPERS, tTargetRecipientPref.getTPers());
		}

		if (tTargetRecipientPref.getTPubType() == null) {
			jpaQuery.bind(TPUBTYPE, new TPubType());
		} else {
			LOGGER.info("tPubType {}"+ tTargetRecipientPref.getTPubType());

			jpaQuery.bind(TPUBTYPE, tTargetRecipientPref.getTPubType());
			
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTargetRecipientPrefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTargetRecipientPrefs(final SearchFilter<TTargetRecipientPref> searchFilter) {
		LOGGER.info("=========== Count TTargetRecipientPref ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTargetRecipientPref tTargetRecipientPref = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTargetRecipientPrefentity", tTargetRecipientPref);

		if (tTargetRecipientPref.getTPers() == null) {
			jpaCountQuery.bind(TPERS, new TPers());
		} else {
			LOGGER.info("tPers {}"+ tTargetRecipientPref.getTPers());

			jpaCountQuery.bind(TPERS, tTargetRecipientPref.getTPers());
		}

		if (tTargetRecipientPref.getTPubType() == null) {
			jpaCountQuery.bind(TPUBTYPE, new TPubType());
		} else {
			LOGGER.info("tPubType {}"+ tTargetRecipientPref.getTPubType());

			jpaCountQuery.bind(TPUBTYPE, tTargetRecipientPref.getTPubType());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TTargetRecipientPref> getTTargetRecipientPrefsByTPers(final SearchFilter<TPers> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTargetRecipientPrefByTPers", queryParam, index, maxresult);
	}

	/**
	 * Count TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTTargetRecipientPrefsByTPers(final SearchFilter<TPers> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTTargetRecipientPrefsByTPers", queryParam);
	}

	/**
	 * Retrieve TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPubType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTargetRecipientPref> list of TTargetRecipientPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TTargetRecipientPref> getTTargetRecipientPrefsByTPubType(final SearchFilter<TPubType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTargetRecipientPrefByTPubType", queryParam, index, maxresult);
	}

	/**
	 * Count TTargetRecipientPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPubType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTTargetRecipientPrefsByTPubType(final SearchFilter<TPubType> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTTargetRecipientPrefsByTPubType", queryParam);
	}
	
	public List<TTargetRecipientPref> getTCommTargetRecipientsByHistory(final SearchFilter<TTargetRecipientPref> searchFilter, Date fromDate, Date toDate) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTargetRecipientPref tTargetRecipientPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List paramList = new ArrayList();
		//paramList.add(fromDate);
		//paramList.add(toDate);
		paramList.add(tTargetRecipientPref.getCreatedBy());
		paramList.add(tTargetRecipientPref.getActiveFlag()) ;
		paramList.add(tTargetRecipientPref.getSrchCritEndDt()) ;
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTTargetRecipientPrefByHistory", paramList, index, maxresult);
	}

	/**
	 * Creates the t comm target recipients.
	 *
	 * @param tCommTargetRecipients the t comm target recipients
	 * @return the list
	 */
	@Override
	public List<TTargetRecipientPref> createTCommTargetRecipients(List<TTargetRecipientPref> tTargetRecipientPrefs) {
		return genericDAO.storeBatch(tTargetRecipientPrefs);
	}

}
