package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCallPlanConfig;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallPlanConfigDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallPlanConfigDAO")
public class TCallPlanConfigDAOImpl implements TCallPlanConfigDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallPlanConfigDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TCallPlanConfig> clazz;

	public Class<TCallPlanConfig> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallPlanConfigDAOImpl() {
		super();
		this.clazz = TCallPlanConfig.class;
	}

	private static final String JPAQL = "select tCallPlanConfigentity from TCallPlanConfig tCallPlanConfigentity";

	private static final String JPACOUNTQL = "select count(tCallPlanConfigentity) from TCallPlanConfig tCallPlanConfigentity";

	private static final String[] RESTRICTIONS = {
			"tCallPlanConfigentity.callPlanConfigId = #{tCallPlanConfigentity.getCallPlanConfigId}",
			"tCallPlanConfigentity.activeFlag = #{tCallPlanConfigentity.getActiveFlag}",
			"tCallPlanConfigentity.maxPrdCnt = #{tCallPlanConfigentity.getMaxPrdCnt}",
			"Date(tCallPlanConfigentity.effStartDt) = '#{tCallPlanConfigentity.getEffStartDt}'",
			"Date(tCallPlanConfigentity.effEndDt) = '#{tCallPlanConfigentity.getEffEndDt}'",
			"tCallPlanConfigentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tCallPlanConfigentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TCallPlanConfig entity object in to the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be persisted
	 * @return tCallPlanConfig Persisted TCallPlanConfig object
	 */
	public TCallPlanConfig createTCallPlanConfig(final TCallPlanConfig tCallPlanConfig) {
		LOGGER.info("=========== Create TCallPlanConfig ===========");
		return genericDAO.store(tCallPlanConfig);
	}

	/**
	 * Deletes a TCallPlanConfig entity object from the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be deleted
	 */
	public void deleteTCallPlanConfig(final Integer callPlanConfigId) {
		LOGGER.info("=========== Delete TCallPlanConfig ===========");
		final TCallPlanConfig tCallPlanConfig = genericDAO.get(clazz, callPlanConfigId);
		genericDAO.remove(tCallPlanConfig);
	}

	/**
	 * Updates a TCallPlanConfig entity object in to the persistent store
	 * 
	 * @param tCallPlanConfig
	 *            TCallPlanConfig Entity object to be updated
	 * @return tCallPlanConfig Persisted TCallPlanConfig object
	 */
	public TCallPlanConfig updateTCallPlanConfig(final TCallPlanConfig tCallPlanConfig) {
		LOGGER.info("=========== Update TCallPlanConfig ===========");
		return genericDAO.update(tCallPlanConfig);
	}

	/**
	 * Retrieve an TCallPlanConfig object based on given TCallPlanConfig callPlanConfigId.
	 * 
	 * @param tCallPlanConfigId
	 *            the primary key value of the TCallPlanConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallPlanConfig findTCallPlanConfigById(final Integer tCallPlanConfigId) {
		LOGGER.info("find TCallPlanConfig instance with callPlanConfigId: " + tCallPlanConfigId);
		return genericDAO.get(clazz, tCallPlanConfigId);
	}

	/**
	 * Retrieve TCallPlanConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfig if it exists against given criteria.
	 *         Returns null if not found
	 */
	
	public List<TCallPlanConfig> findTCallPlanConfigs(final SearchFilter<TCallPlanConfig> searchFilter) {
		LOGGER.info("=========== Find TCallPlanConfigs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallPlanConfig tCallPlanConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallPlanConfigentity", tCallPlanConfig);

		if (tCallPlanConfig.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCallPlanConfig.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tCallPlanConfig.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallPlanConfigs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallPlanConfigs(final SearchFilter<TCallPlanConfig> searchFilter) {
		LOGGER.info("=========== Count TCallPlanConfig ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallPlanConfig tCallPlanConfig = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallPlanConfigentity", tCallPlanConfig);

		if (tCallPlanConfig.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCallPlanConfig.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tCallPlanConfig.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallPlanConfig> getTCallPlanConfigsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallPlanConfigByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallPlanConfigsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("CountTCallPlanConfigsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * 
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return update TCallPlanConfigActiveFlag if it exists against given
	 *         criteria. 
	 */
	
	
	
	
	
	public void updateTCallPlanConfig(Character activeFlag,
			Integer tCallPlanConfigID) {
		List<Object> posParam = new ArrayList<Object>();
		posParam.add(activeFlag);
		posParam.add(tCallPlanConfigID);
		genericDAO.updateEntitiesByNamedQueryMultiCond(
				"updateTCallPlanConfigActiveFlag", posParam, -1, -1);
	}
	
	/**
	 * Retrieve StatusOfCallPlan based on given search criteria using JPA named Query.
	 * 
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of StatusOfCallPlan if it exists against given
	 *         criteria.
	 */
	public List<Object> findStatusOfCallPlan(Long posId, Long salesHeirId, Short tenantId){
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(posId);
		paramList.add(salesHeirId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findStatusOfCallPlan", paramList, 0, -1);
		
	}
	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigsByAlBuSt if it exists against given
	 *         criteria. 
	 */
	@Override
	public List<TCallPlanConfig> getCallPlanConfig(TCallPlanConfig tCallPlanConfig)
	{
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(tCallPlanConfig.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
		paramList.add(tCallPlanConfig.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
		paramList.add(tCallPlanConfig.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
		paramList.add(tCallPlanConfig.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallPlanConfigsByAlBuSt", paramList, 0, -1);
	}
	/**
	 * Retrieve TCallPlanConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanConfig> list of TCallPlanConfigsByFlag if it exists against given
	 *         criteria. 
	 */
	public List<TCallPlanConfig> getCallPlanConfigByFlag(Character affiliationFlag,	Character callDirChangeFlag,Short tenantId)
	{
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(affiliationFlag);
		paramList.add(callDirChangeFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallPlanConfigsByFlag", paramList, 0, -1);
	}
	/**
	 * Retrieve TCallPlanConfigsByAlBuSt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanConfigsByAlBuSt
	 * 
	 * @param algmntId
	 *           - algmntId
	 * @Param tenantId
	 *           -tenantId  
	 *@Param salesTeamId
	 *          -salesTeamId 
	 *@Param bussUnitId
	 *           -bussUnitId 
	 *@Param activeFlag
	 *           -activeFlag 
	 *@Param actFlag
	 *           -actFlag         
	 * @return List<character> list of TCallPlanConfigsByAlBuSt if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<Character> findActiveTCallPlanConfigsByAlBuSt(Long algmntId,Long bussUnitId,Long salesTeamId,Character activeFlag,Character actFlag,Short tenantId)
	{
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(activeFlag);
		paramList.add(actFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTCallPlanConfigsByAlBuSt", paramList, 0, -1);
	}

	
}
