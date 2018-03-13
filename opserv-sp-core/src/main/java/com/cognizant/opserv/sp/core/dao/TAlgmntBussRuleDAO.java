package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntBussRule;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TBussRuleConfig;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntBussRule DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntBussRuleDAO {

	/**
	 * Stores a new TAlgmntBussRule entity object in to the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be persisted
	 * @return tAlgmntBussRule Persisted TAlgmntBussRule object
	 */
	TAlgmntBussRule createTAlgmntBussRule(TAlgmntBussRule tAlgmntBussRule);

	/**
	 * Deletes a TAlgmntBussRule entity object from the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be deleted
	 */
	void deleteTAlgmntBussRule(Integer ruleId);

	/**
	 * Updates a TAlgmntBussRule entity object in to the persistent store
	 * 
	 * @param tAlgmntBussRule
	 *            TAlgmntBussRule Entity object to be updated
	 * @return tAlgmntBussRule Persisted TAlgmntBussRule object
	 */
	TAlgmntBussRule updateTAlgmntBussRule(TAlgmntBussRule tAlgmntBussRule);

	/**
	 * Retrieve an TAlgmntBussRule object based on given ruleId.
	 * 
	 * @param ruleId
	 *            the primary key value of the TAlgmntBussRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntBussRule findTAlgmntBussRuleById(Integer ruleId);

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntBussRule> findTAlgmntBussRules(SearchFilter<TAlgmntBussRule> searchFilter);

	/**
	 * Count TAlgmntBussRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntBussRules(SearchFilter<TAlgmntBussRule> searchFilter);

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussRuleConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntBussRule> getTAlgmntBussRulesByTBussRuleConfig(SearchFilter<TBussRuleConfig> searchFilter);

	/**
	 * Count TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussRuleConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntBussRulesByTBussRuleConfig(SearchFilter<TBussRuleConfig> searchFilter);

	/**
	 * Retrieve TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntBussRule> list of TAlgmntBussRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntBussRule> getTAlgmntBussRulesByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TAlgmntBussRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntBussRulesByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	
	/**
	 * Gets the t algmnt buss rules by t algmnt sales team rule config id.
	 *
	 * @param searchFilter the search filter
	 * @return the t algmnt buss rules by t algmnt sales team rule config id
	 */
	List<TAlgmntBussRule> getTAlgmntBussRulesByTAlgmntSalesTeamRuleConfigId(SearchFilter<TAlgmntBussRule> searchFilter);
	
	 /**
 	 * Search customer share rule.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
 	List <TAlgmntBussRule> searchCustomerShareRule(String algmntId,String bussUnitId,String salesTeamId,String tenantId);
	 
	 /**
 	 * Find valueby align info and rule name.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param BussRuleName the buss rule name
 	 * @return the list
 	 */
 	List<Object[]> findValuebyAlignInfoAndRuleName(Long algmntId,Long bussUnitId,Long salesTeamId,String BussRuleName);

	 /**
 	 * Gets the all t algmnt buss rule.
 	 *
 	 * @param tenantId the tenant id
 	 * @return the all t algmnt buss rule
 	 */
 	List<TAlgmntBussRule> getAllTAlgmntBussRule(Short tenantId);

	/**
	 * Gets the all t algmnt buss rule by id.
	 *
	 * @param tenantId the tenant id
	 * @param alignId the align id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @return the all t algmnt buss rule by id
	 */
	List<TAlgmntBussRule> getAllTAlgmntBussRuleById(Short tenantId, Long alignId,Long bussUnitId,Long salesTeamId);
	
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
	List<TAlgmntBussRule> getRuleByALBUSTRuleID(Long alignId,Long bussUnitId,Long salesTeamId,Integer bussRuleId,Short tenantId);

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
	List<Object[]> findValuebyAlignInfoAndRuleConfigId(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId, Short tenantId);
			
				// newly added for secondary address validation	
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
	String findFlagValuebyAlignInfoAndRuleConfigId(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId, Short tenantId);
	
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
	List<String> FindValuebyAlignInfoAndRuleConfigIdFrBricks(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer ruleId, Short tenantId);
			
			
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
	List<String> fethSecondaryAddressFlag(Long algmntId,
			Long bussUnitId, Long salesTeamId,Integer bussRuleConfId,Short tenantId);
	
	/**
	 * Gets the all t algmnt buss rule by albust.
	 *
	 * @param tenantId the tenant id
	 * @param alignId the align id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @return the all t algmnt buss rule by albust
	 */
	List<TAlgmntBussRule> getAllTAlgmntBussRuleByALBUST(Short tenantId, Long alignId,Long bussUnitId,Long salesTeamId);
	
	/**
	 * Gets the customer movement flag.
	 *
	 * @param alignmentId the alignment id
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the customer movement flag
	 */
	List<TAlgmntBussRule> getBusinessRuleValue(Long alignmentId, Long businessUnitId, Long salesTeamId, String ruleName, Short tenantId);
}
