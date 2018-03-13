package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustAffType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAffType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAffTypeDAO {

	/**
	 * Stores a new TCustAffType entity object in to the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be persisted
	 * @return tCustAffType Persisted TCustAffType object
	 */
	TCustAffType createTCustAffType(TCustAffType tCustAffType);

	/**
	 * Deletes a TCustAffType entity object from the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be deleted
	 */
	void deleteTCustAffType(Integer custAffTypeId);

	/**
	 * Updates a TCustAffType entity object in to the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be updated
	 * @return tCustAffType Persisted TCustAffType object
	 */
	TCustAffType updateTCustAffType(TCustAffType tCustAffType);

	/**
	 * Retrieve an TCustAffType object based on given custAffTypeId.
	 * 
	 * @param custAffTypeId
	 *            the primary key value of the TCustAffType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAffType findTCustAffTypeById(Integer custAffTypeId);

	/**
	 * Retrieve TCustAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffType> list of TCustAffTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffType> findTCustAffTypes(SearchFilter<TCustAffType> searchFilter);

	/**
	 * Count TCustAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffTypes(SearchFilter<TCustAffType> searchFilter);

	/**
	 * Retrieve TCustAffType based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAffType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffType> list of TCustAffTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	List<TCustAffType> getTCustAffTypesByTCustAffType(SearchFilter<TCustAffType> searchFilter);*/

	/**
	 * Count TCustAffType based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAffType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*Object countTCustAffTypesByTCustAffType(SearchFilter<TCustAffType> searchFilter);*/

}
