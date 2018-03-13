package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPubType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPubType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPubTypeDAO {

	/**
	 * Stores a new TPubType entity object in to the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be persisted
	 * @return tPubType Persisted TPubType object
	 */
	TPubType createTPubType(TPubType tPubType);

	/**
	 * Deletes a TPubType entity object from the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be deleted
	 */
	void deleteTPubType(Integer pubTypeId);

	/**
	 * Updates a TPubType entity object in to the persistent store
	 * 
	 * @param tPubType
	 *            TPubType Entity object to be updated
	 * @return tPubType Persisted TPubType object
	 */
	TPubType updateTPubType(TPubType tPubType);

	/**
	 * Retrieve an TPubType object based on given pubTypeId.
	 * 
	 * @param pubTypeId
	 *            the primary key value of the TPubType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPubType findTPubTypeById(Integer pubTypeId);

	/**
	 * Retrieve TPubType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPubType> list of TPubTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPubType> findTPubTypes(SearchFilter<TPubType> searchFilter);

	/**
	 * Count TPubType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPubTypes(SearchFilter<TPubType> searchFilter);

}
