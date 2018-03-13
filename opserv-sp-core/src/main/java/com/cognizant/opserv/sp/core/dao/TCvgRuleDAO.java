package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.opserv.sp.core.entity.TCvgRule;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgRule DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgRuleDAO {

	/**
	 * Stores a new TCvgRule entity object in to the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be persisted
	 * @return tCvgRule Persisted TCvgRule object
	 */
	TCvgRule createTCvgRule(TCvgRule tCvgRule);

	/**
	 * Deletes a TCvgRule entity object from the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be deleted
	 */
	void deleteTCvgRule(Integer ruleId);

	/**
	 * Updates a TCvgRule entity object in to the persistent store
	 * 
	 * @param tCvgRule
	 *            TCvgRule Entity object to be updated
	 * @return tCvgRule Persisted TCvgRule object
	 */
	TCvgRule updateTCvgRule(TCvgRule tCvgRule);

	/**
	 * Retrieve an TCvgRule object based on given ruleId.
	 * 
	 * @param ruleId
	 *            the primary key value of the TCvgRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRule findTCvgRuleById(Integer ruleId);
	
	/**
	 * Retrieve an TCvgRule object based on given ruleId.
	 * 
	 * @param ruleId
	 *            the primary key value of the TCvgRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCvgRule findTCvgRuleById(int ruleId,Short tenantId);

	/**
	 * Retrieve TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRule> findTCvgRules(SearchFilter<TCvgRule> searchFilter);

	/**
	 * Count TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRules(SearchFilter<TCvgRule> searchFilter);

	/**
	 * Retrieve TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgRule> getTCvgRulesByTBussFunction(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Count TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgRulesByTBussFunction(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Retrieve TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TRuleType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TCvgRule> getTCvgRulesByTRuleType(SearchFilter<TRuleType> searchFilter);

	/**
	 * Count TCvgRule based on given search criteria using JPA named Query.
	 * The search criteria is of TRuleType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTCvgRulesByTRuleType(SearchFilter<TRuleType> searchFilter);
	/**
	 * Retrieve TCvgRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgRule> list of TCvgRule if it exists against given criteria.
	 *         Returns null if not found
	 */
	
	 List<TCvgRule> findRuleByName(SearchFilter<TCvgRule> searchFilter);
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
	 List<TCvgRule> findTCvgRulesSortByCreDt(Short tenantId);

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
	 List<TCvgRule> searchCoverageRules(String ruleName,
				String ruleDescription, String ruleType, int busFunId,
				Short tenantId, String localeId, int start, int max,Long AlgnId,Long BuId,Long StId);
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
	 Object searchCoverageRulesForAlBuSt(Long AlgnId,Long BuId,Long StId,Short tenantId);
	

}
