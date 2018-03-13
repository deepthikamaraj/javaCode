package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TFlexiRule;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TFlexiRule DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TFlexiRuleDAO {

	/**
	 * Stores a new TFlexiRule entity object in to the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be persisted
	 * @return tFlexiRule Persisted TFlexiRule object
	 */
	TFlexiRule createTFlexiRule(TFlexiRule tFlexiRule);

	/**
	 * Deletes a TFlexiRule entity object from the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be deleted
	 */
	void deleteTFlexiRule(Integer flexiRuleId);

	/**
	 * Updates a TFlexiRule entity object in to the persistent store
	 * 
	 * @param tFlexiRule
	 *            TFlexiRule Entity object to be updated
	 * @return tFlexiRule Persisted TFlexiRule object
	 */
	TFlexiRule updateTFlexiRule(TFlexiRule tFlexiRule);

	/**
	 * Retrieve an TFlexiRule object based on given flexiRuleId.
	 * 
	 * @param flexiRuleId
	 *            the primary key value of the TFlexiRule Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TFlexiRule findTFlexiRuleById(Integer flexiRuleId);

	/**
	 * Retrieve TFlexiRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFlexiRule> list of TFlexiRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TFlexiRule> findTFlexiRules(SearchFilter<TFlexiRule> searchFilter);

	/**
	 * Count TFlexiRule based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTFlexiRules(SearchFilter<TFlexiRule> searchFilter);

	/**
	 * Retrieve TFlexiRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFlexiRule> list of TFlexiRules if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TFlexiRule> getTFlexiRulesByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TFlexiRule based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTFlexiRulesByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	
	
}
