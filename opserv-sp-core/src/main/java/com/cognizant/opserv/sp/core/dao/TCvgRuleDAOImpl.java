package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.opserv.sp.core.entity.TCvgRule;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgRuleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgRuleDAO")
public  class TCvgRuleDAOImpl implements TCvgRuleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCvgRuleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSFUNCTION = "tBussFunction";
	//private static final String TRULETYPE = "tRuleType";

	private final Class<TCvgRule> clazz;

	public Class<TCvgRule> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgRuleDAOImpl() {
		super();
		this.clazz = TCvgRule.class;
	}

	//private static final String JPAQL = "select tCvgRuleentity from TCvgRule tCvgRuleentity";
	private static final String JPAORD = "select tCvgRuleentity from TCvgRule tCvgRuleentity ORDER BY tCvgRuleentity.ruleId DESC";

	private static final String JPACOUNTQL = "select count(tCvgRuleentity) from TCvgRule tCvgRuleentity";

	private static final String[] RESTRICTIONS = { "tCvgRuleentity.ruleId = #{tCvgRuleentity.getRuleId}",
			"tCvgRuleentity.ruleDesc like '#{tCvgRuleentity.getRuleDesc}%'",
			"tCvgRuleentity.activeFlag = #{tCvgRuleentity.getActiveFlag}",
			"tCvgRuleentity.ruleName like '#{tCvgRuleentity.getRuleName}%'",
			"tCvgRuleentity.ruleExpr like '#{tCvgRuleentity.getRuleExpr}%'",
			"tCvgRuleentity.createdBy = #{tCvgRuleentity.getCreatedBy}",
			"Date(tCvgRuleentity.createDt) = '#{tCvgRuleentity.getCreateDt}'",
			"tCvgRuleentity.updatedBy = #{tCvgRuleentity.getUpdatedBy}",
			"Date(tCvgRuleentity.updateDt) = '#{tCvgRuleentity.getUpdateDt}'",
			"tCvgRuleentity.tenantId = #{tCvgRuleentity.getTenantId}",
			"tCvgRuleentity.tBussFunction.bussFunctionId = #{tCvgRuleentity.tBussFunction.getBussFunctionId}",
			"tCvgRuleentity.tRuleType.ruleTypeId = #{tCvgRuleentity.tRuleType.getRuleTypeId}",
			"tCvgRuleentity.ruleTypeId = #{tCvgRuleentity.getRuleTypeId}" };

	/**
	 * Stores a new TCvgRule entity object in to the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be persisted
	 * @return tCvgRule Persisted TCvgRule object
	 */
	public TCvgRule createTCvgRule(final TCvgRule tCvgRule) {
		LOGGER.info("=========== Create TCvgRule ===========");
		return genericDAO.store(tCvgRule);
	}

	/**
	 * Deletes a TCvgRule entity object from the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be deleted
	 */
	public void deleteTCvgRule(final Integer ruleId) {
		LOGGER.info("=========== Delete TCvgRule ===========");
		final TCvgRule tCvgRule = genericDAO.get(clazz, ruleId);
		genericDAO.remove(tCvgRule);
	}

	/**
	 * Updates a TCvgRule entity object in to the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be updated
	 * @return tCvgRule Persisted TCvgRule object
	 */
	public TCvgRule updateTCvgRule(final TCvgRule tCvgRule) {
		LOGGER.info("=========== Update TCvgRule ===========");
		return genericDAO.update(tCvgRule);
	}

	/**
	 * Retrieve an TCvgRule object based on given TCvgRule ruleId.
	 * 
	 * @param tCvgRuleId
	 *            the primary key value of the TCvgRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCvgRule findTCvgRuleById(final Integer tCvgRuleId) {
		LOGGER.info("find TCvgRule instance with ruleId: " + tCvgRuleId);
		return genericDAO.get(clazz, tCvgRuleId);
	}

	/**
	 * Retrieve TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRule> findTCvgRules(final SearchFilter<TCvgRule> searchFilter) {
		LOGGER.info("=========== Find TCvgRules ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgRule tCvgRule = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgRuleentity", tCvgRule);

		if (tCvgRule.getTBussFunction() == null) {
			jpaQuery.bind(TBUSSFUNCTION, new TBussFunction());
		} else {
			LOGGER.info("tBussFunction {}"+ tCvgRule.getTBussFunction());

			jpaQuery.bind(TBUSSFUNCTION, tCvgRule.getTBussFunction());
		}

		/*if (tCvgRule.getTRuleType() == null) {
			jpaQuery.bind(TRULETYPE, new TRuleType());
		} else {
			LOGGER.info("tRuleType {}", tCvgRule.getTRuleType());

			jpaQuery.bind(TRULETYPE, tCvgRule.getTRuleType());
		}*/
		//jpaQuery.setJPAql(JPAQL);
		jpaQuery.setJPAql(JPAORD);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		
		//Setting cacheable true to enable caching
		jpaQuery.setCacheable(true);
		
		
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgRules.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgRules(final SearchFilter<TCvgRule> searchFilter) {
		LOGGER.info("=========== Count TCvgRule ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgRule tCvgRule = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgRuleentity", tCvgRule);

		if (tCvgRule.getTBussFunction() == null) {
			jpaCountQuery.bind(TBUSSFUNCTION, new TBussFunction());
		} else {
			LOGGER.info("tBussFunction {}" + tCvgRule.getTBussFunction());

			jpaCountQuery.bind(TBUSSFUNCTION, tCvgRule.getTBussFunction());
		}

		/*if (tCvgRule.getTRuleType() == null) {
			jpaCountQuery.bind(TRULETYPE, new TRuleType());
		} else {
			LOGGER.info("tRuleType {}", tCvgRule.getTRuleType());

			jpaCountQuery.bind(TRULETYPE, tCvgRule.getTRuleType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> getTCvgRulesByTBussFunction(final SearchFilter<TBussFunction> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		List<Object> tBussFunctionList = new ArrayList<Object>();
		tBussFunctionList.add(tBussFunction);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleByTBussFunction", tBussFunctionList, index, maxresult);
	}

	/**
	 * Count TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCvgRulesByTBussFunction(final SearchFilter<TBussFunction> searchFilter) {

		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		List<Object> tBussFunctionList = new ArrayList<Object>();
		tBussFunctionList.add(tBussFunction);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRulesByTBussFunction", tBussFunctionList);
	}

	/**
	 * Retrieve TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TRuleType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TCvgRule> getTCvgRulesByTRuleType(final SearchFilter<TRuleType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRuleType tRuleType = searchFilter.getEntityClass();
		List<Object> tRuleTypeList = new ArrayList<Object>();
		tRuleTypeList.add(tRuleType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleByTRuleType", tRuleTypeList, index, maxresult);
	}*/

	/**
	 * Count TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TRuleType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTCvgRulesByTRuleType(final SearchFilter<TRuleType> searchFilter) {

		final TRuleType tRuleType = searchFilter.getEntityClass();
		List<Object> tRuleTypeList = new ArrayList<Object>();
		tRuleTypeList.add(tRuleType);
		return genericDAO.findEntitiesByNamedQuery("CountTCvgRulesByTRuleType", tRuleTypeList);
	}*/
	/**
	 * Retrieve TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgRule> findRuleByName(final SearchFilter<TCvgRule> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCvgRule tCvgRule = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(tCvgRule.getRuleName());
		paramList.add(tCvgRule.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindRuleByRuleName",paramList, index, maxresult);
	}

	/**
	 * Retrieve TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param tCvgRuleId
	 *            -holds ruleId
	 * @param tenantId
	 *            -holds tenant Id
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public TCvgRule findTCvgRuleById(final int tCvgRuleId,final Short tenantId) {
		TCvgRule tCvgRule = new TCvgRule();
		List list = new ArrayList();
		list.add(tCvgRuleId);
		list.add(tenantId);	
		List<TCvgRule> tCvgRuleList= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCvgRulesByRuleIdTentantId", list,0,-1);
		if(tCvgRuleList!= null){
			tCvgRule = tCvgRuleList.get(0);
		}
		return tCvgRule;
	}

	/**
	 * Retrieve TCvgRule for created date based on given search criteria
	 * 
	 * 
	 * @param tenantId
	 *            -holds tenant Id
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> findTCvgRulesSortByCreDt(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesSortByCreDt",paramList, 0, -1);
	}

	/**
	 * Retrieve TCvgRule based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	 * @param ruleDescription
	 *            -holds ruleDescription
	 * @param ruleType
	 *            -holds ruleType
	 * @param busFunId
	 *            -holds busFunId
	 * @param localeId
	 *            -holds localeId
	 * @param start
	 *            -holds start
	 * @param max
	 *            -holds max
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> searchCoverageRulesforView(String ruleName,
			String ruleDescription, String ruleType, int busFunId,
			Short tenantId, String localeId, int start, int max) {
		
		List<TCvgRule> list= new ArrayList<TCvgRule>();
		
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+ruleName.trim()+"%");
		paramList.add("%"+ruleDescription.trim()+"%");
		if(ruleType.equalsIgnoreCase("INCLUSION")){
		paramList.add(1);
		} else if(ruleType.equalsIgnoreCase("EXCLUSION")){
			paramList.add(2);
		} else {
			paramList.add(3);
		}
		if(busFunId>0) {
		paramList.add(busFunId);
		paramList.add(tenantId);
		list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsForView",paramList, 0, -1);
		} else {
			paramList.add(tenantId);
			list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsWithoutBussFunForView",paramList, 0, -1);	
		}
		
		
		return list;
		
	}

	/**
	 * Retrieve TCvgRule based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	 * @param ruleDescription
	 *            -holds ruleDescription
	 * @param ruleType
	 *            -holds ruleType
	 * @param busFunId
	 *            -holds busFunId
	 * @param localeId
	 *            -holds localeId
	 * @param start
	 *            -holds start
	 * @param max
	 *            -holds max
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> searchCoverageRules(String ruleName,
			String ruleDescription, String ruleType, int busFunId,
			Short tenantId, String localeId, int start, int max, Long AlgnId,
			Long BuId, Long StId)  {
		
		List<TCvgRule> list= new ArrayList<TCvgRule>();
		
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+ruleName.trim()+"%");
		paramList.add("%"+ruleDescription.trim()+"%");
		paramList.add(AlgnId);
		paramList.add(BuId);
		paramList.add(StId);
		if(ruleType.equalsIgnoreCase("INCLUSION")){
		paramList.add(1);
		} else if(ruleType.equalsIgnoreCase("EXCLUSION")){
			paramList.add(2);
		} else {
			paramList.add(3);
		}
	
	
		
		if(busFunId>0) {
		paramList.add(busFunId);
		paramList.add(tenantId);
	
		list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParams",paramList, 0, -1);
		} 
		
		else {
			paramList.add(tenantId);
			list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsWithoutBussFun",paramList, 0, -1);	
		}
		
		
	
			
		
		
		return list;
		
	}

	/**
	 * Retrieve object based on given search criteria
	 * 
	 * @param AlgnId
	 *            -holds AlgnId
	 * @param tenantId
	 *            -holds tenant Id
	 * @param BuId
	 *            -holds BuId
	 * @param StId
	 *            -holds StId
	 * 
	 * 
	 * @return object of tCvgRule
	 */
	public Object searchCoverageRulesForAlBuSt(Long AlgnId,Long BuId,Long StId,Short tenantId) {
		List paramList = new ArrayList();
		
		paramList.add(AlgnId);
		paramList.add(BuId);
		paramList.add(StId);
		paramList.add(tenantId);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAlignmentsByAlBuStNames",paramList, 0, -1);
		
	}
	/**
	 * Retrieve list object based on given search criteria
	 * 
	 * @param status
	 *            -holds status
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRule
	 */
	public List<Object[]> searchCoverageRulesForDiffRuleNames(String status, Short tenantId) {
		List paramList = new ArrayList();
		List<Object[]> list= new ArrayList<Object[]>();
	
		paramList.add(tenantId);
		
		
		list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByDiffRulenames",paramList, 0, -1);
		return list;
		
	}
	/**
	 * Retrieve list of arrayobject based on given search criteria
	 * 
	 * @param orderId
	 *            -holds orderId
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRule
	 */
	public  List<Object[]> FindTCvgRuleExecByExenameAndUpdateDdate(Integer orderId,Short tenantId) {
		List paramList = new ArrayList();
		
		
		paramList.add(orderId);
		paramList.add(tenantId);
		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecByExenameAndUpdateDdate",paramList, 0, -1);
		
	}
	
	
	/**
	 * Retrieve list object based on given search criteria
	 * 
	 * @param status
	 *            -holds status
	 * @param tenantId
	 *            -holds tenant Id
	
	 * 
	 * @return list of object of tCvgRule
	 */
	public List<Object[]> searchCoverageRulesWithExecDetails(String execName, String status, Short tenantId) {
		List paramList = new ArrayList();
		List<Object[]> list= new ArrayList<Object[]>();
	
		paramList.add("%"+execName.trim()+"%");
		if("Active".equalsIgnoreCase(status)) {
		paramList.add('Y');
		} else {
			paramList.add('N');
		}
		paramList.add(tenantId);
		
		
		list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRuleExecWithExecDetails",paramList, 0, -1);
		return list;
		
	}
	
	
	
	/**
	 * Retrieve TCvgRule based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	 * @param ruleDescription
	 *            -holds ruleDescription
	 * @param ruleType
	 *            -holds ruleType
	 * @param busFunId
	 *            -holds busFunId
	 * @param localeId
	 *            -holds localeId
	 * @param start
	 *            -holds start
	 * @param max
	 *            -holds max
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> searchCoverageRulesForAllRuleTypes(String ruleName,
			String ruleDescription, String ruleType, int busFunId,
			Short tenantId, String localeId, int start, int max, Long AlgnId,
			Long BuId, Long StId)  {
		
		List<TCvgRule> list= new ArrayList<TCvgRule>();
		
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+ruleName.trim()+"%");
		paramList.add("%"+ruleDescription.trim()+"%");
		paramList.add(AlgnId);
		paramList.add(BuId);
		paramList.add(StId);
		if(busFunId>0) {
		paramList.add(busFunId);
		paramList.add(tenantId);
	
		list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsForAllRuleType",paramList, 0, -1);
		} 
		
		else {
			paramList.add(tenantId);
			list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsWithoutBussFunForAllRuleType",paramList, 0, -1);	
		}
		
	
		return list;
		
	}
	
	
	/**
	 * Retrieve TCvgRule based on given search criteria
	 * 
	 * @param ruleName
	 *            -holds ruleName
	 * @param tenantId
	 *            -holds tenant Id
	 * @param ruleDescription
	 *            -holds ruleDescription
	 * @param ruleType
	 *            -holds ruleType
	 * @param busFunId
	 *            -holds busFunId
	 * @param localeId
	 *            -holds localeId
	 * @param start
	 *            -holds start
	 * @param max
	 *            -holds max
	 * 
	 * @return List<TCvgRule> list of TCvgRule if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCvgRule> searchCoverageRulesforViewForAllRuleType(String ruleName,
			String ruleDescription, String ruleType, int busFunId,
			Short tenantId, String localeId, int start, int max) {
		
		List<TCvgRule> list= new ArrayList<TCvgRule>();
		
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+ruleName.trim()+"%");
		paramList.add("%"+ruleDescription.trim()+"%");
		
		if(busFunId>0) {
		paramList.add(busFunId);
		paramList.add(tenantId);
		list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsForViewForAllRuleType",paramList, 0, -1);
		} else {
			paramList.add(tenantId);
			list=genericDAO.findEntitiesByNamedQueryMultiCond("FindTCvgRulesByMultiParamsWithoutBussFunForViewForAllRuleType",paramList, 0, -1);	
		}
		
		
		return list;
		
	}

	
}
