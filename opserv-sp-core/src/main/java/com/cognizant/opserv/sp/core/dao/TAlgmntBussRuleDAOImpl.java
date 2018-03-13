package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TAlgmntBussRule;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TBussRuleConfig;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntBussRuleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntBussRuleDAO")
public class TAlgmntBussRuleDAOImpl implements TAlgmntBussRuleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntBussRuleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSRULECONFIG = "tBussRuleConfig";
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TAlgmntBussRule> clazz;

	public Class<TAlgmntBussRule> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntBussRuleDAOImpl() {
		super();
		this.clazz = TAlgmntBussRule.class;
	}

	private static final String JPAQL = "select tAlgmntBussRuleentity from TAlgmntBussRule tAlgmntBussRuleentity";

	private static final String JPACOUNTQL = "select count(tAlgmntBussRuleentity) from TAlgmntBussRule tAlgmntBussRuleentity";

	private static final String[] RESTRICTIONS = {
			"tAlgmntBussRuleentity.ruleId = #{tAlgmntBussRuleentity.getRuleId}",
			"tAlgmntBussRuleentity.value like '#{tAlgmntBussRuleentity.getValue}%'",
			"tAlgmntBussRuleentity.activeFlag = #{tAlgmntBussRuleentity.getActiveFlag}",
			"Date(tAlgmntBussRuleentity.effStartDt) = '#{tAlgmntBussRuleentity.getEffStartDt}'",
			"Date(tAlgmntBussRuleentity.effEndDt) = '#{tAlgmntBussRuleentity.getEffEndDt}'",
			"tAlgmntBussRuleentity.createdBy = #{tAlgmntBussRuleentity.getCreatedBy}",
			"Date(tAlgmntBussRuleentity.createDt) = '#{tAlgmntBussRuleentity.getCreateDt}'",
			"tAlgmntBussRuleentity.updatedBy = #{tAlgmntBussRuleentity.getUpdatedBy}",
			"Date(tAlgmntBussRuleentity.updateDt) = '#{tAlgmntBussRuleentity.getUpdateDt}'",
			"tAlgmntBussRuleentity.tenantId = #{tAlgmntBussRuleentity.getTenantId}",
			"tAlgmntBussRuleentity.tBussRuleConfig.tBussRuleConfigId = #{tAlgmntBussRuleentity.tBussRuleConfig.getTBussRuleConfigId}",
			"tAlgmntBussRuleentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tAlgmntBussRuleentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TAlgmntBussRule entity object in to the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be persisted
	 * @return tAlgmntBussRule Persisted TAlgmntBussRule object
	 */
	public TAlgmntBussRule createTAlgmntBussRule(final TAlgmntBussRule tAlgmntBussRule) {
		LOGGER.info("=========== Create TAlgmntBussRule ===========");
		return genericDAO.store(tAlgmntBussRule);
	}

	/**
	 * Deletes a TAlgmntBussRule entity object from the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be deleted
	 */
	public void deleteTAlgmntBussRule(final Integer ruleId) {
		LOGGER.info("=========== Delete TAlgmntBussRule ===========");
		final TAlgmntBussRule tAlgmntBussRule = genericDAO.get(clazz, ruleId);
		genericDAO.remove(tAlgmntBussRule);
	}

	/**
	 * Updates a TAlgmntBussRule entity object in to the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be updated
	 * @return tAlgmntBussRule Persisted TAlgmntBussRule object
	 */
	public TAlgmntBussRule updateTAlgmntBussRule(final TAlgmntBussRule tAlgmntBussRule) {
		LOGGER.info("=========== Update TAlgmntBussRule ===========");
		return genericDAO.update(tAlgmntBussRule);
	}

	/**
	 * Retrieve an TAlgmntBussRule object based on given TAlgmntBussRule ruleId.
	 * 
	 * @param tAlgmntBussRuleId
	 *            the primary key value of the TAlgmntBussRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntBussRule findTAlgmntBussRuleById(final Integer tAlgmntBussRuleId) {
		LOGGER.info("find TAlgmntBussRule instance with ruleId: " + tAlgmntBussRuleId);
		return genericDAO.get(clazz, tAlgmntBussRuleId);
	}

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntBussRule> findTAlgmntBussRules(final SearchFilter<TAlgmntBussRule> searchFilter) {
		LOGGER.info("=========== Find TAlgmntBussRules ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntBussRule tAlgmntBussRule = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntBussRuleentity", tAlgmntBussRule);

		if (tAlgmntBussRule.getTBussRuleConfig() == null) {
			jpaQuery.bind(TBUSSRULECONFIG, new TBussRuleConfig());
		} else {
			LOGGER.info("tBussRuleConfig {}" + tAlgmntBussRule.getTBussRuleConfig());

			jpaQuery.bind(TBUSSRULECONFIG, tAlgmntBussRule.getTBussRuleConfig());
		}

		if (tAlgmntBussRule.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntBussRule.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tAlgmntBussRule.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntBussRules.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntBussRules(final SearchFilter<TAlgmntBussRule> searchFilter) {
		LOGGER.info("=========== Count TAlgmntBussRule ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntBussRule tAlgmntBussRule = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntBussRuleentity", tAlgmntBussRule);

		if (tAlgmntBussRule.getTBussRuleConfig() == null) {
			jpaCountQuery.bind(TBUSSRULECONFIG, new TBussRuleConfig());
		} else {
			LOGGER.info("tBussRuleConfig {}" + tAlgmntBussRule.getTBussRuleConfig());

			jpaCountQuery.bind(TBUSSRULECONFIG, tAlgmntBussRule.getTBussRuleConfig());
		}

		if (tAlgmntBussRule.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntBussRule.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tAlgmntBussRule.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussRuleConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntBussRule> getTAlgmntBussRulesByTBussRuleConfig(final SearchFilter<TBussRuleConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussRuleConfig tBussRuleConfig = searchFilter.getEntityClass();
		List<Object> tBussRuleConfigList=new ArrayList<Object>();
		tBussRuleConfigList.add(tBussRuleConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntBussRuleByTBussRuleConfig", tBussRuleConfigList, index, maxresult);
		
	}

	/**
	 * Count TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussRuleConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntBussRulesByTBussRuleConfig(final SearchFilter<TBussRuleConfig> searchFilter) {

		final TBussRuleConfig tBussRuleConfig = searchFilter.getEntityClass();
		List<Object> tBussRuleConfigList = new ArrayList<Object>();
		tBussRuleConfigList.add(tBussRuleConfig);
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntBussRulesByTBussRuleConfig", tBussRuleConfigList);
	}

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntBussRule> getTAlgmntBussRulesByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList=new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntBussRuleByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntBussRulesByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntBussRulesByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}
	/**
	 * Gets the t algmnt buss rules by t algmnt sales team rule config id.
	 *
	 * @param searchFilter the search filter
	 * @return the t algmnt buss rules by t algmnt sales team rule config id
	 */
	@Override
	public List<TAlgmntBussRule> getTAlgmntBussRulesByTAlgmntSalesTeamRuleConfigId(
			SearchFilter<TAlgmntBussRule> searchFilter) {
		final TAlgmntBussRule tAlgmntBussRule = searchFilter.getEntityClass();
		 List paramList = new ArrayList();
	        paramList.add(Long.valueOf(tAlgmntBussRule.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId()));
	        paramList.add(Long.valueOf(tAlgmntBussRule.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId()));
	        paramList.add(Long.valueOf(tAlgmntBussRule.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId()));
	        paramList.add(Integer.valueOf(tAlgmntBussRule.getTBussRuleConfig().getTBussRuleConfigId().getBussRuleConfigId()));
	        
	        return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntBussRuleByTAlgmntSalesTeamRuleConfig", paramList, 0, -1);
		//return genericDAO.findEntitiesByNamedQuery("FindTAlgmntBussRuleByTAlgmntSalesTeamRuleConfig", tAlgmntBussRule);		
	}
	
	 /**
 	 * Search customer share rule.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
	@Override
	public List <TAlgmntBussRule> searchCustomerShareRule(String algmntId,String bussUnitId,String salesTeamId,String tenantId) {
		List paramList = new ArrayList();
		List <TAlgmntBussRule> result = null;
		
		paramList.add(Long.valueOf(algmntId));
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(Long.valueOf(salesTeamId));
		
		
		paramList.add(Short.valueOf(tenantId));
		
		/*paramList.add(Integer.valueOf(1));*/
		
        
        try{
        	result = genericDAO.findEntitiesByNamedQueryMultiCond("FindCustomerSharingOption", paramList, 0, -1);
           }
           catch(Exception e){
        	   LOGGER.error("Error while retrieving universal details");
           }
        return result;
	}
	 /**
 	 * Find valueby align info and rule name.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param BussRuleName the buss rule name
 	 * @return the list
 	 */
	@Override
	public List<Object[]> findValuebyAlignInfoAndRuleName(Long algmntId, Long bussUnitId, Long salesTeamId, String BussRuleName) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(BussRuleName);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindValuebyAlignInfoAndRuleName", paramList, 0, -1);
	}
	/**
 	 * Gets the all t algmnt buss rule.
 	 *
 	 * @param tenantId the tenant id
 	 * @return the all t algmnt buss rule
 	 */
	@Override
	public  List<TAlgmntBussRule> getAllTAlgmntBussRule(Short tenantId){
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(ApplicationConstant.FLAG_YES);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntBussRulesByTenantId", paramList, 0, -1);
	}
	/**
	 * Gets the all t algmnt buss rule by id.
	 *
	 * @param tenantId the tenant id
	 * @param alignId the align id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @return the all t algmnt buss rule by id
	 */
	@Override
	public  List<TAlgmntBussRule> getAllTAlgmntBussRuleById(Short tenantId,Long alignId,Long bussUnitId,Long salesTeamId){
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(alignId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(ApplicationConstant.FLAG_YES);
		List<TAlgmntBussRule> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntBussRulesByAlignId", paramList, 0, -1);
		return list;
	}
	/**
	 * Gets the rule by albust rule id.
	 *
	 * @param alignId the align id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussRuleId the buss rule id
	 * @param tenantId the tenant id
	 * @return the rule by albust rule id
	 */
	@Override
	public List<TAlgmntBussRule> getRuleByALBUSTRuleID(Long alignId,
			Long bussUnitId, Long salesTeamId, Integer bussRuleId,
			Short tenantId) {
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(alignId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(bussRuleId);
		paramList.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("getRuleByALBUSTRuleID", paramList, 0, -1);
	}
	/**
	 * Find valueby align info and rule config id.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param ruleId the rule id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findValuebyAlignInfoAndRuleConfigId(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(ruleId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindValuebyAlignInfoAndRuleConfigId", paramList, 0, -1);

	}
	
	/**
	* Find flag valueby align info and rule config id.
	*
	* @param algmntId the algmnt id
	* @param bussUnitId the buss unit id
	* @param salesTeamId the sales team id
	* @param ruleId the rule id
	* @param tenantId the tenant id
	* @return the string
	*/
		// newly added for secondary address validation	
	@Override
	public String findFlagValuebyAlignInfoAndRuleConfigId(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId, Short tenantId) {
		String flag =null;		
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(ruleId);
		paramList.add(tenantId);
		Object temp = null;
		List<Object[]> rule = genericDAO.findEntitiesByNamedQueryMultiCond("FindValuebyAlignInfoAndRuleConfigId", paramList, 0, -1);
		if(!rule.isEmpty()){
			temp = rule.get(0) ;
			flag = temp.toString() ;
		}
		return flag;
	}
	/**
	 * Find valueby align info and rule config id fr bricks.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param ruleId the rule id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<String> FindValuebyAlignInfoAndRuleConfigIdFrBricks(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId,Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(ruleId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindValuebyAlignInfoAndRuleConfigIdFrBricks", paramList, 0, -1);

	}
	/**
	 * Feth secondary address flag.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param bussRuleConfId the buss rule conf id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<String> fethSecondaryAddressFlag(Long algmntId,
			Long bussUnitId, Long salesTeamId,Integer bussRuleConfId,Short tenantId){
			List paramList = new ArrayList();
			paramList.add(algmntId);
			paramList.add(bussUnitId);
			paramList.add(salesTeamId);
			paramList.add(bussRuleConfId);
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond("FethSecondaryAddressFlag", paramList, 0, -1);
	}
	/**
	 * Gets the all t algmnt buss rule by albust.
	 *
	 * @param tenantId the tenant id
	 * @param alignId the align id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @return the all t algmnt buss rule by albust
	 */
	@Override
	public  List<TAlgmntBussRule> getAllTAlgmntBussRuleByALBUST(Short tenantId,Long alignId,Long bussUnitId,Long salesTeamId){
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(alignId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(ApplicationConstant.FLAG_YES);
		List<TAlgmntBussRule> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntBussRulesByALBUST", paramList, 0, -1);
		return list;
	}
	
	/**
	 * Gets the customer movement flag.
	 *
	 * @param alignmentId the alignment id
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the customer movement flag
	 */
	@Override
	public List<TAlgmntBussRule> getBusinessRuleValue(Long alignmentId,
			Long businessUnitId, Long salesTeamId, String ruleName, Short tenantId) {
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(ruleName);
		paramList.add(alignmentId);
		paramList.add(businessUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("FindBusinessRuleValue", paramList);
	}
}
