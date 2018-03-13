package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TWdg;
import com.cognizant.opserv.sp.core.entity.TWdgId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TWdg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TWdgDAO {

	
	

	/**
	 * Retrieve an TWdg object based on given wdgId.
	 * 
	 * @param wdgId is
	 *            the primary key values of the TWdg Entity.
	 * @param tenantId 
	 * @return an Object if it exists against given primary key values. Returns null of
	 *         not found
	 */
	
	List<TWdg> findTWdgsById(List<Integer> wdgId, Short tenantId);
	
	
	/**
	 * Stores a new TWdg entity object in to the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be persisted
	 * @return tWdg Persisted TWdg object
	 */
	TWdg createTWdg(TWdg tWdg);

	/**
	 * Deletes a TWdg entity object from the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be deleted
	 */
	void deleteTWdg(TWdgId tWdgId);

	/**
	 * Updates a TWdg entity object in to the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be updated
	 * @return tWdg Persisted TWdg object
	 */
	TWdg updateTWdg(TWdg tWdg);

	/**
	 * Retrieve an TWdg object based on given TWdgId.
	 * 
	 * @param tWdgId
	 *            the primary key value of the TWdg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TWdg findTWdgById(TWdgId tWdgId);

	/**
	 * Retrieve TWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TWdg> list of TWdgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TWdg> findTWdgs(SearchFilter<TWdg> searchFilter);

	/**
	 * Count TWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTWdgs(SearchFilter<TWdg> searchFilter);

}
