package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TUsrAttrPref;
import com.cognizant.opserv.sp.core.entity.TUsrPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsrAttrPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsrAttrPrefDAO {

	/**
	 * Stores a new TUsrAttrPref entity object in to the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be persisted
	 * @return tUsrAttrPref Persisted TUsrAttrPref object
	 */
	TUsrAttrPref createTUsrAttrPref(TUsrAttrPref tUsrAttrPref);

	/**
	 * Deletes a TUsrAttrPref entity object from the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be deleted
	 */
	void deleteTUsrAttrPref(Integer prefId);

	/**
	 * Updates a TUsrAttrPref entity object in to the persistent store
	 * 
	 * @param tUsrAttrPref
	 *            TUsrAttrPref Entity object to be updated
	 * @return tUsrAttrPref Persisted TUsrAttrPref object
	 */
	TUsrAttrPref updateTUsrAttrPref(TUsrAttrPref tUsrAttrPref);

	/**
	 * Retrieve an TUsrAttrPref object based on given prefId.
	 * 
	 * @param prefId
	 *            the primary key value of the TUsrAttrPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsrAttrPref findTUsrAttrPrefById(Integer prefId);

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrAttrPref> findTUsrAttrPrefs(SearchFilter<TUsrAttrPref> searchFilter);

	/**
	 * Count TUsrAttrPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrAttrPrefs(SearchFilter<TUsrAttrPref> searchFilter);

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroupMap type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrAttrPref> getTUsrAttrPrefsByTAttrGroupMap(SearchFilter<TAttrGroupMap> searchFilter);

	/**
	 * Count TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroupMap type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrAttrPrefsByTAttrGroupMap(SearchFilter<TAttrGroupMap> searchFilter);

	/**
	 * Retrieve TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrAttrPref> list of TUsrAttrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrAttrPref> getTUsrAttrPrefsByTUsrPref(SearchFilter<TUsrPref> searchFilter);

	/**
	 * Count TUsrAttrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrAttrPrefsByTUsrPref(SearchFilter<TUsrPref> searchFilter);

	List<Object[]> findTUsrAttrPrefsByTUsrPref(Integer featureId,
			Integer usrId, Integer roleId, Character activeFlag, Short tenantId);

}
