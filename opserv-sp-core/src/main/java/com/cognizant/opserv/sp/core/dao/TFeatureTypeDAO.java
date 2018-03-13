package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TFeatureType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TFeatureType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TFeatureTypeDAO {

	/**
	 * Stores a new TFeatureType entity object in to the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be persisted
	 * @return tFeatureType Persisted TFeatureType object
	 */
	TFeatureType createTFeatureType(TFeatureType tFeatureType);

	/**
	 * Deletes a TFeatureType entity object from the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be deleted
	 */
	void deleteTFeatureType(Integer typeId);

	/**
	 * Updates a TFeatureType entity object in to the persistent store
	 * 
	 * @param tFeatureType
	 *            TFeatureType Entity object to be updated
	 * @return tFeatureType Persisted TFeatureType object
	 */
	TFeatureType updateTFeatureType(TFeatureType tFeatureType);

	/**
	 * Retrieve an TFeatureType object based on given typeId.
	 * 
	 * @param typeId
	 *            the primary key value of the TFeatureType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TFeatureType findTFeatureTypeById(Integer typeId);

	/**
	 * Retrieve TFeatureType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TFeatureType> list of TFeatureTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TFeatureType> findTFeatureTypes(SearchFilter<TFeatureType> searchFilter);

	/**
	 * Count TFeatureType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTFeatureTypes(SearchFilter<TFeatureType> searchFilter);

}
