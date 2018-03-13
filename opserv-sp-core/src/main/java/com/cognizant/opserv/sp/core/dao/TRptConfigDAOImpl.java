package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptCategory;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigStatus;
import com.cognizant.opserv.sp.core.entity.TRptType;
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
 * Class provides API implementation for TRptConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptConfigDAO")
public class TRptConfigDAOImpl implements TRptConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TRPTCATEGORY = "tRptCategory";
	//private static final String TRPTTYPE = "tRptType";
	//private static final String TRPTCONFIGSTATUS = "tRptConfigStatus";

	private final Class<TRptConfig> clazz;

	public Class<TRptConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptConfigDAOImpl() {
		super();
		this.clazz = TRptConfig.class;
	}

	private static final String JPAQL = "select tRptConfigentity from TRptConfig tRptConfigentity";

	private static final String JPACOUNTQL = "select count(tRptConfigentity) from TRptConfig tRptConfigentity";

	private static final String[] RESTRICTIONS = { "tRptConfigentity.rptConfigId = #{tRptConfigentity.getRptConfigId}",
			"tRptConfigentity.rptName like '#{tRptConfigentity.getRptName}%'",
			"tRptConfigentity.ackRequired = #{tRptConfigentity.getAckRequired}",
			"tRptConfigentity.createdBy = #{tRptConfigentity.getCreatedBy}",
			"Date(tRptConfigentity.createDt) = '#{tRptConfigentity.getCreateDt}'",
			"tRptConfigentity.updatedBy = #{tRptConfigentity.getUpdatedBy}",
			"Date(tRptConfigentity.updateDate) = '#{tRptConfigentity.getUpdateDate}'",
			"tRptConfigentity.tenantId = #{tRptConfigentity.getTenantId}",
			"tRptConfigentity.tRptCategory.tRptCategoryId.rptCategoryId = #{tRptConfigentity.tRptCategory.tRptCategoryId.getRptCategoryId}",
			"tRptConfigentity.tRptType.rptTypeId = #{tRptConfigentity.tRptType.getRptTypeId}",
			"tRptConfigentity.tRptConfigStatus.rptConfigStatusId = #{tRptConfigentity.tRptConfigStatus.getRptConfigStatusId}",
			"tRptConfigentity.tTargetRecipientPref.recipientPrefId = #{tRptConfigentity.tTargetRecipientPref.getRecipientPrefId}"};

	/**
	 * Stores a new TRptConfig entity object in to the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be persisted
	 * @return tRptConfig Persisted TRptConfig object
	 */
	public TRptConfig createTRptConfig(final TRptConfig tRptConfig) {
		LOGGER.info("=========== Create TRptConfig ===========");
		return genericDAO.store(tRptConfig);
	}

	/**
	 * Deletes a TRptConfig entity object from the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be deleted
	 */
	public void deleteTRptConfig(final Integer rptConfigId) {
		LOGGER.info("=========== Delete TRptConfig ===========");
		final TRptConfig tRptConfig = genericDAO.get(clazz, rptConfigId);
		genericDAO.remove(tRptConfig);
	}

	/**
	 * Updates a TRptConfig entity object in to the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be updated
	 * @return tRptConfig Persisted TRptConfig object
	 */
	public TRptConfig updateTRptConfig(final TRptConfig tRptConfig) {
		LOGGER.info("=========== Update TRptConfig ===========");
		return genericDAO.update(tRptConfig);
	}

	/**
	 * Retrieve an TRptConfig object based on given TRptConfig rptConfigId.
	 * 
	 * @param tRptConfigId
	 *            the primary key value of the TRptConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptConfig findTRptConfigById(final Integer tRptConfigId) {
		LOGGER.info("find TRptConfig instance with rptConfigId: " + tRptConfigId);
		return genericDAO.get(clazz, tRptConfigId);
	}

	/**
	 * Retrieve TRptConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptConfig> findTRptConfigs(final SearchFilter<TRptConfig> searchFilter) {
		LOGGER.info("=========== Find TRptConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptConfig tRptConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptConfigentity", tRptConfig);

		if (tRptConfig.getRptCategoryId() == null) {
			jpaQuery.bind(TRPTCATEGORY, new TRptCategory());
		} else {
			LOGGER.info("tRptCategory {}"+ tRptConfig.getRptCategoryId());

			jpaQuery.bind(TRPTCATEGORY, tRptConfig.getRptCategoryId());
		}

		/*if (tRptConfig.getTRptType() == null) {
			jpaQuery.bind(TRPTTYPE, new TRptType());
		} else {
			LOGGER.info("tRptType {}", tRptConfig.getTRptType());

			jpaQuery.bind(TRPTTYPE, tRptConfig.getTRptType());
		}*/

		/*if (tRptConfig.getTRptConfigStatus() == null) {
			jpaQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptConfig.getTRptConfigStatus());

			jpaQuery.bind(TRPTCONFIGSTATUS, tRptConfig.getTRptConfigStatus());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptConfigs(final SearchFilter<TRptConfig> searchFilter) {
		LOGGER.info("=========== Count TRptConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptConfig tRptConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptConfigentity", tRptConfig);

		if (tRptConfig.getRptCategoryId() == null) {
			jpaCountQuery.bind(TRPTCATEGORY, new TRptCategory());
		} else {
			LOGGER.info("tRptCategory {}"+ tRptConfig.getRptCategoryId());

			jpaCountQuery.bind(TRPTCATEGORY, tRptConfig.getRptCategoryId());
		}

		/*if (tRptConfig.getTRptType() == null) {
			jpaCountQuery.bind(TRPTTYPE, new TRptType());
		} else {
			LOGGER.info("tRptType {}", tRptConfig.getTRptType());

			jpaCountQuery.bind(TRPTTYPE, tRptConfig.getTRptType());
		}*/

		/*if (tRptConfig.getTRptConfigStatus() == null) {
			jpaCountQuery.bind(TRPTCONFIGSTATUS, new TRptConfigStatus());
		} else {
			LOGGER.info("tRptConfigStatus {}", tRptConfig.getTRptConfigStatus());

			jpaCountQuery.bind(TRPTCONFIGSTATUS, tRptConfig.getTRptConfigStatus());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfig> getTRptConfigsByTRptCategory(final SearchFilter<TRptCategory> searchFilter) {
		LOGGER.info("=========== get TRptConfigsByTRptCategory ===========");
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptCategory tRptCategory = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptCategory.getTRptCategoryId().getRptCategoryId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigByTRptCategory",queryParam, index, maxresult);
	}

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigsByTRptCategory(final SearchFilter<TRptCategory> searchFilter) {
		LOGGER.info("=========== count TRptConfigsByTRptCategory===========");
		final TRptCategory tRptCategory = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptCategory.getTRptCategoryId().getRptCategoryId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigsByTRptCategory", queryParam);
	}

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfig> getTRptConfigsByTRptType(final SearchFilter<TRptType> searchFilter) {
		LOGGER.info("=========== get TRptConfigsByTRptType===========");
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptType tRptType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptType.gettRptTypeId().getRptTypeId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigByTRptType",queryParam , index, maxresult);
	}

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigsByTRptType(final SearchFilter<TRptType> searchFilter) {
		LOGGER.info("=========== count TRptConfigsByTRptType===========");
		final TRptType tRptType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptType.gettRptTypeId().getRptTypeId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigsByTRptType",queryParam);
	}

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfig> getTRptConfigsByTRptConfigStatus(final SearchFilter<TRptConfigStatus> searchFilter) {
		LOGGER.info("=========== get TRptConfigsByTRptConfigStatus===========");
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptConfigStatus.gettRptConfigStatusId().getRptConfigStatusId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigByTRptConfigStatus", queryParam, index,
				maxresult);
	}

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigsByTRptConfigStatus(final SearchFilter<TRptConfigStatus> searchFilter) {
		LOGGER.info("=========== count TRptConfigsByTRptConfigStatus===========");
		final TRptConfigStatus tRptConfigStatus = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptConfigStatus.gettRptConfigStatusId().getRptConfigStatusId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigsByTRptConfigStatus", queryParam);
	}
	//added for Recipient prefID
	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptConfig> getTRptConfigsByTTargetRecipientPref(final SearchFilter<TTargetRecipientPref> searchFilter) {
		LOGGER.info("=========== get TRptConfigsByTTargetRecipientPref===========");
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigByTTargetRecipientPref", queryParam, index,
				maxresult);
	}

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptConfigsByTTargetRecipientPref(final SearchFilter<TTargetRecipientPref> searchFilter) {
		LOGGER.info("=========== count TRptConfigsByTTargetRecipientPref ==========");
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptConfigsByTTargetRecipientPref", queryParam);
	}

	@Override
	public TRptConfig findTRptConfigById(final Integer rptConfigId,final Short tenantId) {
		LOGGER.info("=========== find TRptConfigById ===========");
		TRptConfig tRptConfig = new TRptConfig();
		List list = new ArrayList();
		list.add(rptConfigId);
		list.add(tenantId);	
		List<TRptConfig> tRptConfigList= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTRptConfigById", list,0,-1);
		if(tRptConfigList!= null){
			tRptConfig = tRptConfigList.get(0);
		}
		return tRptConfig;
	}

	@Override
	public List<TRptConfig> findTRptConfigByTRptTypeAndConfigStatus(Integer reportTypeId, Integer statusTypeId, Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(reportTypeId);
		list.add(statusTypeId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptConfigByTRptTypeAndConfigStatus", list, 0, -1);
	}

}
