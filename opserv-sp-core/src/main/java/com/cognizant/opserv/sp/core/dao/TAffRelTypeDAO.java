package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAffRelType;
import com.cognizant.opserv.sp.core.entity.TAffRelTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAffRelType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAffRelTypeDAO {

	/**
	 * Stores a new TAffRelType entity object in to the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be persisted
	 * @return tAffRelType Persisted TAffRelType object
	 */
	TAffRelType createTAffRelType(TAffRelType tAffRelType);

	/**
	 * Deletes a TAffRelType entity object from the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be deleted
	 */
	void deleteTAffRelType(TAffRelTypeId tAffRelTypeId);

	/**
	 * Updates a TAffRelType entity object in to the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be updated
	 * @return tAffRelType Persisted TAffRelType object
	 */
	TAffRelType updateTAffRelType(TAffRelType tAffRelType);

	/**
	 * Retrieve an TAffRelType object based on given TAffRelTypeId.
	 * 
	 * @param tAffRelTypeId
	 *            the primary key value of the TAffRelType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAffRelType findTAffRelTypeById(TAffRelTypeId tAffRelTypeId);

	/**
	 * Retrieve TAffRelType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffRelType> list of TAffRelTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAffRelType> findTAffRelTypes(SearchFilter<TAffRelType> searchFilter);

	/**
	 * Count TAffRelType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAffRelTypes(SearchFilter<TAffRelType> searchFilter);

}
