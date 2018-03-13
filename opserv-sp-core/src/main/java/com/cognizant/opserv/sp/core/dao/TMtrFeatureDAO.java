package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtrFeature;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrFeature DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrFeatureDAO {

	/**
	 * Stores a new TMtrFeature entity object in to the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be persisted
	 * @return tMtrFeature Persisted TMtrFeature object
	 */
	TMtrFeature createTMtrFeature(TMtrFeature tMtrFeature);

	/**
	 * Deletes a TMtrFeature entity object from the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be deleted
	 */
	void deleteTMtrFeature(Integer featureId);

	/**
	 * Updates a TMtrFeature entity object in to the persistent store
	 * 
	 * @param tMtrFeature
	 *            TMtrFeature Entity object to be updated
	 * @return tMtrFeature Persisted TMtrFeature object
	 */
	TMtrFeature updateTMtrFeature(TMtrFeature tMtrFeature);

	/**
	 * Retrieve an TMtrFeature object based on given featureId.
	 * 
	 * @param featureId
	 *            the primary key value of the TMtrFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMtrFeature findTMtrFeatureById(Integer featureId);

	/**
	 * Retrieve TMtrFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrFeature> list of TMtrFeatures if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrFeature> findTMtrFeatures(SearchFilter<TMtrFeature> searchFilter);

	/**
	 * Count TMtrFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrFeatures(SearchFilter<TMtrFeature> searchFilter);

}
