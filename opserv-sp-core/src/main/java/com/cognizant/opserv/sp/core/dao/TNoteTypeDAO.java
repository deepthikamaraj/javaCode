package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TNoteType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TNoteType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TNoteTypeDAO {

	/**
	 * Stores a new TNoteType entity object in to the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be persisted
	 * @return tNoteType Persisted TNoteType object
	 */
	TNoteType createTNoteType(TNoteType tNoteType);

	/**
	 * Deletes a TNoteType entity object from the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be deleted
	 */
	void deleteTNoteType(Integer noteTypeId);

	/**
	 * Updates a TNoteType entity object in to the persistent store
	 * 
	 * @param tNoteType
	 *            TNoteType Entity object to be updated
	 * @return tNoteType Persisted TNoteType object
	 */
	TNoteType updateTNoteType(TNoteType tNoteType);

	/**
	 * Retrieve an TNoteType object based on given noteTypeId.
	 * 
	 * @param noteTypeId
	 *            the primary key value of the TNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TNoteType findTNoteTypeById(Integer noteTypeId);

	/**
	 * Retrieve TNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNoteType> list of TNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNoteType> findTNoteTypes(SearchFilter<TNoteType> searchFilter);

	/**
	 * Count TNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNoteTypes(SearchFilter<TNoteType> searchFilter);
	
	List<TNoteType> fetchAllNoteTypes();
	List<TNoteType> fetchAllNoteTypes(Short tenantId);
	List<TNoteType> FindTNoteTypesByNoteTypeId(Integer noteTypeId,short tenantId);
}
