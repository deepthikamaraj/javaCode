package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqTrigger DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqTriggerDAO {

	/**
	 * Stores a new TChngReqTrigger entity object in to the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be persisted
	 * @return tChngReqTrigger Persisted TChngReqTrigger object
	 */
	TChngReqTrigger createTChngReqTrigger(TChngReqTrigger tChngReqTrigger);

	/**
	 * Deletes a TChngReqTrigger entity object from the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be deleted
	 */
	void deleteTChngReqTrigger(Integer triggerId);

	/**
	 * Updates a TChngReqTrigger entity object in to the persistent store
	 * 
	 * @param tChngReqTrigger
	 *            TChngReqTrigger Entity object to be updated
	 * @return tChngReqTrigger Persisted TChngReqTrigger object
	 */
	TChngReqTrigger updateTChngReqTrigger(TChngReqTrigger tChngReqTrigger);

	/**
	 * Retrieve an TChngReqTrigger object based on given triggerId.
	 * 
	 * @param triggerId
	 *            the primary key value of the TChngReqTrigger Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqTrigger findTChngReqTriggerById(Integer triggerId);

	/**
	 * Retrieve TChngReqTrigger based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqTrigger> list of TChngReqTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqTrigger> findTChngReqTriggers(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Count TChngReqTrigger based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqTriggers(SearchFilter<TChngReqTrigger> searchFilter);

	/**
	 * Retrieve TChngReqTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 *
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return List<TChngReqTrigger> list of TChngReqTriggers if it exists against given
	 * criteria. Returns null if not found
	 */
	/*List<TChngReqTrigger> getTChngReqTriggersByTFeature(SearchFilter<TFeature> searchFilter);*/

	/**
	 * Count TChngReqTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*Object countTChngReqTriggersByTFeature(SearchFilter<TFeature> searchFilter);*/
	
	/**
	 * Find feature type by trigger id.
	 *
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findFeatureTypeByTriggerId(Integer triggerId,Short tenantId);
	
	/**
	 * Fetch all triggers by tenants.
	 *
	 * @return the list
	 */
	List<Object[]> fetchAllTriggersByTenants();
	
	/**
	 * Fetch all triggers by tenants.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> fetchAllTriggersByTenants(Short tenantId);
	
	
}
