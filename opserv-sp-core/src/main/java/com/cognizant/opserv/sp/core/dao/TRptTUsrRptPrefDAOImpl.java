package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptTUsrRptPref;
import com.cognizant.opserv.sp.core.entity.TRptTUsrRptPrefId;
import com.cognizant.opserv.sp.core.entity.TUsrRptPref;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptTUsrRptPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptTUsrRptPrefDAO")
public class TRptTUsrRptPrefDAOImpl implements TRptTUsrRptPrefDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptTUsrRptPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TRPTSTATUSTYPE = "tRptStatusType";
	private static final String TUSRRPTPREF = "tUsrRptPref";
	//private static final String TRPTCONFIGSTATUS = "tRptConfigStatus";
	private static final String TRPT = "tRpt";

	private final Class<TRptTUsrRptPref> clazz;

	public Class<TRptTUsrRptPref> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptTUsrRptPrefDAOImpl() {
		super();
		this.clazz = TRptTUsrRptPref.class;
	}

	private static final String JPAQL = "select tRptTUsrRptPrefentity from TRptTUsrRptPref tRptTUsrRptPrefentity";

	private static final String JPACOUNTQL = "select count(*) from TRptTUsrRptPref tRptTUsrRptPrefentity";

	private static final String[] RESTRICTIONS = {
			"tRptTUsrRptPrefentity.tUsrRptPref.prefId = #{tRptTUsrRptPrefentity.tUsrRptPref.getPrefId}",
			"tRptTUsrRptPrefentity.sequenceNumber = #{tRptTUsrRptPrefentity.getSequenceNumber}",
			"tRptTUsrRptPrefentity.activeFlag = #{tRptTUsrRptPrefentity.getActiveFlag}",
			"tRptTUsrRptPrefentity.tenantId = #{tRptTUsrRptPrefentity.getTenantId}",
			"tRptTUsrRptPrefentity.tRpt.rptId = #{tRptTUsrRptPrefentity.tRpt.getRptId}", 
			"tRptTUsrRptPrefentity.tSalesPos.tSalesPosId.salesPosId = #{tRptTUsrRptPrefentity.tSalesPos.tSalesPosId.getSalesPosId}"	,
			"tRptTUsrRptPrefentity.tSalesPos.tSalesPosId.salesHierId = #{tRptTUsrRptPrefentity.tSalesPos.tSalesPosId.getSalesHierId}"	
			};

	/**
	 * Stores a new TRptTUsrRptPref entity object in to the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be persisted
	 * @return tRptTUsrRptPref Persisted TRptTUsrRptPref object
	 */
	public TRptTUsrRptPref createTRptTUsrRptPref(final TRptTUsrRptPref tRptTUsrRptPref) {
		LOGGER.info("=========== Create TRptTUsrRptPref ===========");
		return genericDAO.store(tRptTUsrRptPref);
	}

	/**
	 * Deletes a TRptTUsrRptPref entity object from the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be deleted
	 */
	public void deleteTRptTUsrRptPref(final TRptTUsrRptPrefId tRptTUsrRptPrefId) {
		LOGGER.info("=========== Delete TRptTUsrRptPref ===========");
		final TRptTUsrRptPref tRptTUsrRptPref = genericDAO.get(clazz, tRptTUsrRptPrefId);
		genericDAO.remove(tRptTUsrRptPref);
	}

	/**
	 * Updates a TRptTUsrRptPref entity object in to the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be updated
	 * @return tRptTUsrRptPref Persisted TRptTUsrRptPref object
	 */
	public TRptTUsrRptPref updateTRptTUsrRptPref(final TRptTUsrRptPref tRptTUsrRptPref) {
		LOGGER.info("=========== Update TRptTUsrRptPref ===========");
		return genericDAO.update(tRptTUsrRptPref);
	}

	/**
	 * Retrieve an TRptTUsrRptPref object based on given TRptTUsrRptPref TRptTUsrRptPrefId.
	 * 
	 * @param tRptTUsrRptPrefId
	 *            the primary key value of the TRptTUsrRptPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptTUsrRptPref findTRptTUsrRptPrefById(final TRptTUsrRptPrefId tRptTUsrRptPrefId) {
		LOGGER.info("find TRptTUsrRptPref instance with TRptTUsrRptPrefId: " + tRptTUsrRptPrefId);
		return genericDAO.get(clazz, tRptTUsrRptPrefId);
	}

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPref if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptTUsrRptPref> findTRptTUsrRptPrefs(final SearchFilter<TRptTUsrRptPref> searchFilter) {
		LOGGER.info("=========== Find TRptTUsrRptPrefs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptTUsrRptPref tRptTUsrRptPref = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptTUsrRptPrefentity", tRptTUsrRptPref);

		/*if (tRptTUsrRptPref.getTRptStatusType() == null) {
			jpaQuery.bind(TRPTSTATUSTYPE, new TRptStatusType());
		} else {
			LOGGER.info("tRptStatusType {}", tRptTUsrRptPref.getTRptStatusType());

			jpaQuery.bind(TRPTSTATUSTYPE, tRptTUsrRptPref.getTRptStatusType());
		}*/
		
		if (tRptTUsrRptPref.getTRpt() == null) {
			jpaQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptTUsrRptPref.getTRpt());

			jpaQuery.bind(TRPT, tRptTUsrRptPref.getTRpt());
		}

		if (tRptTUsrRptPref.getTUsrRptPref() == null) {
			jpaQuery.bind(TUSRRPTPREF, new TUsrRptPref());
		} else {
			LOGGER.info("tUsrRptPref {}"+ tRptTUsrRptPref.getTUsrRptPref());

			jpaQuery.bind(TUSRRPTPREF, tRptTUsrRptPref.getTUsrRptPref());
		}

		/*if (tRptTUsrRptPref.getTRptConfigStatus() == null) {
			jpaQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptTUsrRptPref.getTRptConfigStatus());

			jpaQuery.bind(TRPTCONFIGSTATUS, tRptTUsrRptPref.getTRptConfigStatus());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptTUsrRptPrefs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptTUsrRptPrefs(final SearchFilter<TRptTUsrRptPref> searchFilter) {
		LOGGER.info("=========== Count TRptTUsrRptPref ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptTUsrRptPref tRptTUsrRptPref = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptTUsrRptPrefentity", tRptTUsrRptPref);

		/*if (tRptTUsrRptPref.getTRptStatusType() == null) {
			jpaCountQuery.bind(TRPTSTATUSTYPE, new TRptStatusType());
		} else {
			LOGGER.info("tRptStatusType {}", tRptTUsrRptPref.getTRptStatusType());

			jpaCountQuery.bind(TRPTSTATUSTYPE, tRptTUsrRptPref.getTRptStatusType());
		}*/

		if (tRptTUsrRptPref.getTUsrRptPref() == null) {
			jpaCountQuery.bind(TUSRRPTPREF, new TUsrRptPref());
		} else {
			LOGGER.info("tUsrRptPref {}"+ tRptTUsrRptPref.getTUsrRptPref());

			jpaCountQuery.bind(TUSRRPTPREF, tRptTUsrRptPref.getTUsrRptPref());
		}

		/*if (tRptTUsrRptPref.getTRptConfigStatus() == null) {
			jpaCountQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptTUsrRptPref.getTRptConfigStatus());

			jpaCountQuery.bind(TRPTCONFIGSTATUS, tRptTUsrRptPref.getTRptConfigStatus());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTRptStatusType(final SearchFilter<TRptStatusType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTRptTUsrRptPrefByTRptStatusType", tRptStatusType, index,
				maxresult);
	}*/

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTRptTUsrRptPrefsByTRptStatusType(final SearchFilter<TRptStatusType> searchFilter) {

		final TRptStatusType tRptStatusType = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTRptTUsrRptPrefsByTRptStatusType", tRptStatusType);
	}*/

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTUsrRptPref(final SearchFilter<TUsrRptPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTUsrRptPrefByTUsrRptPref", queryParam, index, maxresult);
	}
	
	public List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTUsrRptPrefAndTRpt(final Integer reportId, final Integer folderId, final Long spId, final Long spHId, final Short tenantId) {

		List<Object> queryParam = new ArrayList<Object>();
		List<TRptTUsrRptPref> list = new ArrayList<TRptTUsrRptPref>();
		queryParam.add(folderId);
		queryParam.add(reportId);
		queryParam.add(tenantId);
		if(spId != null && spHId != null){
			queryParam.add(spId);
			queryParam.add(spHId);
			list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTUsrRptPrefByTUsrRptPrefAndTRpt", queryParam, 0, -1);
		} else{
			list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTUsrRptPrefByFolderAndRpt", queryParam, 0, -1);
		}		
		//List<TRptTUsrRptPref> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptTUsrRptPrefByTUsrRptPrefAndTRpt", queryParam, 0, -1);
		return list;
	}

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptTUsrRptPrefsByTUsrRptPref(final SearchFilter<TUsrRptPref> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptTUsrRptPrefsByTUsrRptPref", queryParam);
	}
	
	/**
	 * @method fetchFolderReports - fetches folder reports
	 * @param folderId - folder Id
	 * @param tenantId - tenant Id
	 * @return List<Object[]> - folder reports details
	 */
	@Override
	public List<Object[]> fetchFolderReports(Integer folderId, Short tenantId,Integer staffId) {
		List<Object> params = new ArrayList<Object>();
		params.add(folderId);
		params.add(tenantId);
		params.add(staffId);
		return genericDAO.findEntitiesByNamedQuery("FetchFolderReports", params );
	}
	
	/**
	 * @method countOfFolderReports - Count of folder reports
	 * @param folderId - folder Id
	 * @param tenantId - tenant Id
	 * @return Integer - Count of folder reports
	 */
	@Override
	public Integer countOfFolderReports(Integer folderId, Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(folderId);
		paramList.add(tenantId);
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("CountOfFolderReports", paramList, 0, -1);
		
		return Integer.parseInt(list.get(0).toString());
	}

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTRptConfigStatus(final SearchFilter<TRptConfigStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTRptTUsrRptPrefByTRptConfigStatus", tRptConfigStatus, index,
				maxresult);
	}*/

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTRptTUsrRptPrefsByTRptConfigStatus(final SearchFilter<TRptConfigStatus> searchFilter) {

		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTRptTUsrRptPrefsByTRptConfigStatus", tRptConfigStatus);
	}*/

}
