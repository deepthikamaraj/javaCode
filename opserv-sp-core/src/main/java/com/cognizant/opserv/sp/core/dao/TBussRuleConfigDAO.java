package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussRuleConfig;
import com.cognizant.opserv.sp.core.entity.TBussRuleConfigId;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussRuleConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussRuleConfigDAO {

	/**
	 * Stores a new TBussRuleConfig entity object in to the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be persisted
	 * @return tBussRuleConfig Persisted TBussRuleConfig object
	 */
	TBussRuleConfig createTBussRuleConfig(TBussRuleConfig tBussRuleConfig);

	/**
	 * Deletes a TBussRuleConfig entity object from the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be deleted
	 */
	void deleteTBussRuleConfig(TBussRuleConfigId tBussRuleConfigId);

	/**
	 * Updates a TBussRuleConfig entity object in to the persistent store
	 * 
	 * @param tBussRuleConfig
	 *            TBussRuleConfig Entity object to be updated
	 * @return tBussRuleConfig Persisted TBussRuleConfig object
	 */
	TBussRuleConfig updateTBussRuleConfig(TBussRuleConfig tBussRuleConfig);

	/**
	 * Retrieve an TBussRuleConfig object based on given TBussRuleConfigId.
	 * 
	 * @param tBussRuleConfigId
	 *            the primary key value of the TBussRuleConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussRuleConfig findTBussRuleConfigById(TBussRuleConfigId tBussRuleConfigId);

	/**
	 * Retrieve TBussRuleConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussRuleConfig> list of TBussRuleConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussRuleConfig> findTBussRuleConfigs(SearchFilter<TBussRuleConfig> searchFilter);

	/**
	 * Count TBussRuleConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussRuleConfigs(SearchFilter<TBussRuleConfig> searchFilter);

	/**
	 * Retrieve TBussRuleConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussRuleConfig> list of TBussRuleConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussRuleConfig> getTBussRuleConfigsByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TBussRuleConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussRuleConfigsByTOrg(SearchFilter<TOrg> searchFilter);
	
	/**
	 * Gets the t buss rule configs by rule name.
	 *
	 * @return the t buss rule configs by rule name
	 */
	List<TBussRuleConfig> getTBussRuleConfigsByRuleName();

	/**
	 * Gets the all t buss rule configs.
	 *
	 * @return the all t buss rule configs
	 */
	List<TBussRuleConfig> getAllTBussRuleConfigs();

}
