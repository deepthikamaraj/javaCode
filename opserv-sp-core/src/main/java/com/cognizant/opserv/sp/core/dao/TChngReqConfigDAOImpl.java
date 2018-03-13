package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqConfigDAO")
public class TChngReqConfigDAOImpl implements TChngReqConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQTRIGGER = "tChngReqTrigger";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TChngReqConfig> clazz;

	public Class<TChngReqConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqConfigDAOImpl() {
		super();
		this.clazz = TChngReqConfig.class;
	}

	private static final String JPAQL = "select tChngReqConfigentity from TChngReqConfig tChngReqConfigentity";

	private static final String JPACOUNTQL = "select count(tChngReqConfigentity) from TChngReqConfig tChngReqConfigentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqConfigentity.chngReqConfigId = #{tChngReqConfigentity.getChngReqConfigId}",
			"tChngReqConfigentity.activeFlag = #{tChngReqConfigentity.getActiveFlag}",
			"tChngReqConfigentity.apprStageCnt = #{tChngReqConfigentity.getApprStageCnt}",
			"tChngReqConfigentity.escApplFlag = #{tChngReqConfigentity.getEscApplFlag}",
			"tChngReqConfigentity.dlgApplFlag = #{tChngReqConfigentity.getDlgApplFlag}",
			"tChngReqConfigentity.duration = #{tChngReqConfigentity.getDuration}",
			"tChngReqConfigentity.remNoteDays = #{tChngReqConfigentity.getRemNoteDays}",
			"tChngReqConfigentity.apprHierFlag = #{tChngReqConfigentity.getApprHierFlag}",
			"tChngReqConfigentity.autoAction like '#{tChngReqConfigentity.getAutoAction}%'",
			"tChngReqConfigentity.createdBy = #{tChngReqConfigentity.getCreatedBy}",
			"Date(tChngReqConfigentity.createDt) = '#{tChngReqConfigentity.getCreateDt}'",
			"tChngReqConfigentity.updatedBy = #{tChngReqConfigentity.getUpdatedBy}",
			"Date(tChngReqConfigentity.updateDt) = '#{tChngReqConfigentity.getUpdateDt}'",
			"tChngReqConfigentity.tenantId = #{tChngReqConfigentity.getTenantId}",
			"tChngReqConfigentity.prApprTypeId = #{tChngReqConfigentity.getPrApprTypeId}",
			"tChngReqConfigentity.secApprTypeId = #{tChngReqConfigentity.getSecApprTypeId}",
			"tChngReqConfigentity.tChngReqTrigger.triggerId = #{tChngReqConfigentity.tChngReqTrigger.getTriggerId}",
			"tChngReqConfigentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tChngReqConfigentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TChngReqConfig entity object in to the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be persisted
	 * @return tChngReqConfig Persisted TChngReqConfig object
	 */
	public TChngReqConfig createTChngReqConfig(final TChngReqConfig tChngReqConfig) {
		LOGGER.info("=========== Create TChngReqConfig ===========");
		return genericDAO.store(tChngReqConfig);
	}

	/**
	 * Deletes a TChngReqConfig entity object from the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be deleted
	 */
	public void deleteTChngReqConfig(final Integer chngReqConfigId) {
		LOGGER.info("=========== Delete TChngReqConfig ===========");
		final TChngReqConfig tChngReqConfig = genericDAO.get(clazz, chngReqConfigId);
		genericDAO.remove(tChngReqConfig);
	}

	/**
	 * Updates a TChngReqConfig entity object in to the persistent store
	 * 
	 * @param tChngReqConfig
	 *            TChngReqConfig Entity object to be updated
	 * @return tChngReqConfig Persisted TChngReqConfig object
	 */
	public TChngReqConfig updateTChngReqConfig(final TChngReqConfig tChngReqConfig) {
		LOGGER.info("=========== Update TChngReqConfig ===========");
		return genericDAO.update(tChngReqConfig);
	}

	/**
	 * Retrieve an TChngReqConfig object based on given TChngReqConfig chngReqConfigId.
	 * 
	 * @param tChngReqConfigId
	 *            the primary key value of the TChngReqConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqConfig findTChngReqConfigById(final Integer tChngReqConfigId) {
		LOGGER.info("find TChngReqConfig instance with chngReqConfigId: " + tChngReqConfigId);
		return genericDAO.get(clazz, tChngReqConfigId);
	}

	/**
	 * Retrieve TChngReqConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqConfig> findTChngReqConfigs(final SearchFilter<TChngReqConfig> searchFilter) {
		LOGGER.info("=========== Find TChngReqConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqConfig tChngReqConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqConfigentity", tChngReqConfig);

		if (tChngReqConfig.getTChngReqTrigger() == null) {
			jpaQuery.bind(TCHNGREQTRIGGER, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTrigger {}" + tChngReqConfig.getTChngReqTrigger());

			jpaQuery.bind(TCHNGREQTRIGGER, tChngReqConfig.getTChngReqTrigger());
		}

		if (tChngReqConfig.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tChngReqConfig.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tChngReqConfig.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqConfigs(final SearchFilter<TChngReqConfig> searchFilter) {
		LOGGER.info("=========== Count TChngReqConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqConfig tChngReqConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqConfigentity", tChngReqConfig);

		if (tChngReqConfig.getTChngReqTrigger() == null) {
			jpaCountQuery.bind(TCHNGREQTRIGGER, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTrigger {}"+ tChngReqConfig.getTChngReqTrigger());

			jpaCountQuery.bind(TCHNGREQTRIGGER, tChngReqConfig.getTChngReqTrigger());
		}

		if (tChngReqConfig.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tChngReqConfig.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tChngReqConfig.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReqConfig> getTChngReqConfigsByTChngReqTrigger(final SearchFilter<TChngReqTrigger> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqConfigByTChngReqTrigger", tChngReqTriggerList, index,
				maxresult);
	}

	/**
	 * Count TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqConfigsByTChngReqTrigger(final SearchFilter<TChngReqTrigger> searchFilter) {

		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqConfigsByTChngReqTrigger", tChngReqTriggerList);
	}

	/**
	 * Retrieve TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqConfig> list of TChngReqConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReqConfig> getTChngReqConfigsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqConfigByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TChngReqConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqConfigsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqConfigsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

	/*Added by Ashis Samal*/
	/**
	 * Gets the t chng req config.
	 *
	 * @param params the params
	 * @param tenantId the tenant id
	 * @return the List<TChngReqConfig> list of 
	 */
	@Override
	public List<TChngReqConfig> getTChngReqConfig(List<Object> params) {
		
		return  genericDAO.findEntitiesByNamedQueryMultiCond("GetTChngReqConfig", params, 0, -1);
		// TODO Auto-generated method stub
		
	}
	/**
	 * Gets the t chng req config.
	 *
	 * @param salesTeamId the sales team id
	 * @param AlignmentId the alignment id
	 * @param businessUnitId the business unit id
	 * @param triggerId the trigger id
	 * @param tenantid the tenantid
	 * @return the t chng req config
	 */
	public List<TChngReqConfig> getTChngReqConfig(Long salesTeamId, Long AlignmentId, Long businessUnitId, Integer triggerId, Short tenantid) {

		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesTeamId);
		queryParam.add(AlignmentId);
		queryParam.add(businessUnitId);
		queryParam.add(triggerId);
		queryParam.add(tenantid);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTChngReqConfig", queryParam, 0, -1);
	}

	/**
	 * Find business rule.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object> findBusinessRule(Long algnmntId, Long buId, Long stId,Short tenantid) {
		List queryParam=new ArrayList();
		queryParam.add(algnmntId);
		queryParam.add(buId);
		queryParam.add(stId);
		queryParam.add(1);
		queryParam.add(1);
		queryParam.add(tenantid);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindBusinessRule", queryParam, 0, -1);
			
	}
	/**
	 * Gets the t chng req configs by t chng req trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the t chng req configs by t chng req trigger id
	 */
	@Override
	public List<Object[]> getTChngReqConfigsByTChngReqTriggerId(Integer triggerId,Long algnmntId,Long buId,Long salesTeamId, short tenantId) {
		List<Object> param = new ArrayList <Object>();
		param.add(triggerId);
		param.add(algnmntId);
		param.add(buId);
		param.add(salesTeamId);
		param.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTChngReqConfigsByTChngReqTriggerId", param, 0, -1);
	}
	/**
	 * Gets the t chng req configs trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the t chng req configs trigger id
	 */
	@Override
	public List<TChngReqConfig> getTChngReqConfigsTriggerId(Integer triggerId,Long algnmntId,Long buId,Long salesTeamId, Short tenantId) {
		List<Object> param = new ArrayList <Object>();
		param.add(triggerId);
		param.add(algnmntId);
		param.add(buId);
		param.add(salesTeamId);
		param.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getTChngReqConfigsByTriggerId", param, 0, -1);
	}
	
	/**
	 * Find t chng req triggers by al bu st.
	 *
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TChngReqConfig> findTChngReqTriggersByAlBuSt(Long algnmntId,Long buId, Long salesTeamId, short tenantId) {
		List<Object> param = new ArrayList <Object>();
		param.add(algnmntId);
		param.add(buId);
		param.add(salesTeamId);
		param.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTChngReqTriggersByAlBuSt", param, 0, -1);
	}
	/**
	 * Findprmy secdry by al bu st trger id.
	 *
	 * @param triggerId the trigger id
	 * @param algnmntId the algnmnt id
	 * @param buId the bu id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findprmySecdryByAlBuStTrgerId(Integer triggerId,Long algnmntId,Long buId,Long salesTeamId, short tenantId) {
		List<Object> param = new ArrayList <Object>();
		param.add(triggerId);
		param.add(algnmntId);
		param.add(buId);
		param.add(salesTeamId);
		param.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findprmySecdryByAlBuStTrgerId", param, 0, -1);
	}
	
}
