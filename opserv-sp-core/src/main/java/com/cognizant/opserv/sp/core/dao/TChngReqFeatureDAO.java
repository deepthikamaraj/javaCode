package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqFeature;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqFeature DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqFeatureDAO {

	/**
	 * Stores a new TChngReqFeature entity object in to the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be persisted
	 * @return tChngReqFeature Persisted TChngReqFeature object
	 */
	TChngReqFeature createTChngReqFeature(TChngReqFeature tChngReqFeature);

	/**
	 * Deletes a TChngReqFeature entity object from the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be deleted
	 */
	void deleteTChngReqFeature(Integer featureId);

	/**
	 * Updates a TChngReqFeature entity object in to the persistent store
	 * 
	 * @param tChngReqFeature
	 *            TChngReqFeature Entity object to be updated
	 * @return tChngReqFeature Persisted TChngReqFeature object
	 */
	TChngReqFeature updateTChngReqFeature(TChngReqFeature tChngReqFeature);

	/**
	 * Retrieve an TChngReqFeature object based on given featureId.
	 * 
	 * @param featureId
	 *            the primary key value of the TChngReqFeature Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqFeature findTChngReqFeatureById(Integer featureId);

	/**
	 * Retrieve TChngReqFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqFeature> list of TChngReqFeatures if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqFeature> findTChngReqFeatures(SearchFilter<TChngReqFeature> searchFilter);

	/**
	 * Count TChngReqFeature based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqFeatures(SearchFilter<TChngReqFeature> searchFilter);

}
