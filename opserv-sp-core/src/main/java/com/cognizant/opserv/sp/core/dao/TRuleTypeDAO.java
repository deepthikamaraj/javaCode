package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRuleType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRuleType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRuleTypeDAO {

	/**
	 * Stores a new TRuleType entity object in to the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be persisted
	 * @return tRuleType Persisted TRuleType object
	 */
	TRuleType createTRuleType(TRuleType tRuleType);

	/**
	 * Deletes a TRuleType entity object from the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be deleted
	 */
	void deleteTRuleType(Integer ruleTypeId);

	/**
	 * Updates a TRuleType entity object in to the persistent store
	 * 
	 * @param tRuleType
	 *            TRuleType Entity object to be updated
	 * @return tRuleType Persisted TRuleType object
	 */
	TRuleType updateTRuleType(TRuleType tRuleType);

	/**
	 * Retrieve an TRuleType object based on given ruleTypeId.
	 * 
	 * @param ruleTypeId
	 *            the primary key value of the TRuleType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRuleType findTRuleTypeById(Integer ruleTypeId);

	/**
	 * Retrieve TRuleType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRuleType> list of TRuleTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRuleType> findTRuleTypes(SearchFilter<TRuleType> searchFilter);

	/**
	 * Count TRuleType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRuleTypes(SearchFilter<TRuleType> searchFilter);

}
