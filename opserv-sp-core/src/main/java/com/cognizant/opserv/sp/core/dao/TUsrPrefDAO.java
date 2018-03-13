package com.cognizant.opserv.sp.core.dao;

import java.util.List;


import com.cognizant.opserv.sp.core.entity.TUsrPref;
import com.cognizant.opserv.sp.core.entity.TUsrPrefId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsrPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsrPrefDAO {

	/**
	 * Stores a new TUsrPref entity object in to the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be persisted
	 * @return tUsrPref Persisted TUsrPref object
	 */
	TUsrPref createTUsrPref(TUsrPref tUsrPref);

	/**
	 * Deletes a TUsrPref entity object from the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be deleted
	 */
	void deleteTUsrPref(TUsrPrefId tUsrPrefId);

	/**
	 * Updates a TUsrPref entity object in to the persistent store
	 * 
	 * @param tUsrPref
	 *            TUsrPref Entity object to be updated
	 * @return tUsrPref Persisted TUsrPref object
	 */
	TUsrPref updateTUsrPref(TUsrPref tUsrPref);

	/**
	 * Retrieve an TUsrPref object based on given TUsrPrefId.
	 * 
	 * @param tUsrPrefId
	 *            the primary key value of the TUsrPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsrPref findTUsrPrefById(TUsrPrefId tUsrPrefId);

	/**
	 * Retrieve TUsrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrPref> list of TUsrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrPref> findTUsrPrefs(SearchFilter<TUsrPref> searchFilter);

	/**
	 * Count TUsrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrPrefs(SearchFilter<TUsrPref> searchFilter);

	/**
	 * Retrieve TUsrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrPref> list of TUsrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	List<TUsrPref> getTUsrPrefsByTFeature(SearchFilter<TFeature> searchFilter);*/

	/**
	 * Count TUsrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TFeature type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
/*	Object countTUsrPrefsByTFeature(SearchFilter<TFeature> searchFilter);*/

	

	

}
