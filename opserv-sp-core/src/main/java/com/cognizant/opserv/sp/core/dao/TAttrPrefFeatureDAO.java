package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrPrefFeature;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAttrPrefFeature DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAttrPrefFeatureDAO {

	/**
	 * Stores a new TAttrPrefFeature entity object in to the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be persisted
	 * @return tAttrPrefFeature Persisted TAttrPrefFeature object
	 */
	TAttrPrefFeature createTAttrPrefFeature(TAttrPrefFeature tAttrPrefFeature);

	/**
	 * Deletes a TAttrPrefFeature entity object from the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be deleted
	 */
	void deleteTAttrPrefFeature(Integer featureId);

	/**
	 * Updates a TAttrPrefFeature entity object in to the persistent store
	 * 
	 * @param tAttrPrefFeature
	 *            TAttrPrefFeature Entity object to be updated
	 * @return tAttrPrefFeature Persisted TAttrPrefFeature object
	 */
	TAttrPrefFeature updateTAttrPrefFeature(TAttrPrefFeature tAttrPrefFeature);

	/**
	 * Retrieve an TAttrPrefFeature object based on given featureId.
	 * 
	 * @param featureId
	 *            the primary key value of the TAttrPrefFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAttrPrefFeature findTAttrPrefFeatureById(Integer featureId);

	/**
	 * Retrieve TAttrPrefFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrPrefFeature> list of TAttrPrefFeatures if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAttrPrefFeature> findTAttrPrefFeatures(SearchFilter<TAttrPrefFeature> searchFilter);

	/**
	 * Count TAttrPrefFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAttrPrefFeatures(SearchFilter<TAttrPrefFeature> searchFilter);

}
