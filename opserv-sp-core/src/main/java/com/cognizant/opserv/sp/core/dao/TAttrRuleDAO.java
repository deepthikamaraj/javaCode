package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrRule;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrRule DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrRuleDAO {

	/**
	 * Stores a new TAttrRule entity object in to the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be persisted
	 * @return tAttrRule Persisted TAttrRule object
	 */
	TAttrRule createTAttrRule(TAttrRule tAttrRule);

	/**
	 * Deletes a TAttrRule entity object from the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be deleted
	 */
	void deleteTAttrRule(Integer ruleId);

	/**
	 * Updates a TAttrRule entity object in to the persistent store
	 * 
	 * @param tAttrRule
	 *            TAttrRule Entity object to be updated
	 * @return tAttrRule Persisted TAttrRule object
	 */
	TAttrRule updateTAttrRule(TAttrRule tAttrRule);

	/**
	 * Retrieve an TAttrRule object based on given ruleId.
	 * 
	 * @param ruleId
	 *            the primary key value of the TAttrRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrRule findTAttrRuleById(Integer ruleId);

	/**
	 * Retrieve TAttrRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrRule> list of TAttrRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrRule> findTAttrRules(SearchFilter<TAttrRule> searchFilter);

	/**
	 * Count TAttrRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrRules(SearchFilter<TAttrRule> searchFilter);

	/**
	 * Retrieve TAttrRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrRule> list of TAttrRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrRule> getTAttrRulesByTAttrDef(SearchFilter<TAttrDef> searchFilter);

	/**
	 * Count TAttrRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrRulesByTAttrDef(SearchFilter<TAttrDef> searchFilter);

}
