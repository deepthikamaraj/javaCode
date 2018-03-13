package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TFeatureType;
import com.cognizant.opserv.sp.core.entity.TMtrTrigger;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrTrigger DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrTriggerDAO {

	/**
	 * Stores a new TMtrTrigger entity object in to the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be persisted
	 * @return tMtrTrigger Persisted TMtrTrigger object
	 */
	TMtrTrigger createTMtrTrigger(TMtrTrigger tMtrTrigger);

	/**
	 * Deletes a TMtrTrigger entity object from the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be deleted
	 */
	void deleteTMtrTrigger(Integer triggerId);

	/**
	 * Updates a TMtrTrigger entity object in to the persistent store
	 * 
	 * @param tMtrTrigger
	 *            TMtrTrigger Entity object to be updated
	 * @return tMtrTrigger Persisted TMtrTrigger object
	 */
	TMtrTrigger updateTMtrTrigger(TMtrTrigger tMtrTrigger);

	/**
	 * Retrieve an TMtrTrigger object based on given triggerId.
	 * 
	 * @param triggerId
	 *            the primary key value of the TMtrTrigger Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrTrigger findTMtrTriggerById(Integer triggerId);

	/**
	 * Retrieve TMtrTrigger based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrTrigger> findTMtrTriggers(SearchFilter<TMtrTrigger> searchFilter);

	/**
	 * Count TMtrTrigger based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrTriggers(SearchFilter<TMtrTrigger> searchFilter);

	/**
	 * Retrieve TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeatureType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrTrigger> getTMtrTriggersByTFeatureType(SearchFilter<TFeatureType> searchFilter);

	/**
	 * Count TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeatureType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrTriggersByTFeatureType(SearchFilter<TFeatureType> searchFilter);

	/**
	 * Retrieve TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrTrigger> list of TMtrTriggers if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*List<TMtrTrigger> getTMtrTriggersByTFeature(SearchFilter<TFeature> searchFilter);*/

	/**
	 * Count TMtrTrigger based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*Object countTMtrTriggersByTFeature(SearchFilter<TFeature> searchFilter);*/
	
	List<TMtrTrigger> findAllTMtrTriggers(Short tenantId);
	
	List<Object> getTMtrTriggerByTFeatureAndTFeatureType(Integer featureTypeId ,Integer featureId, Short tenantId);

	List<Object[]> getAllTMtrTrigger(Short tenantId);

}
