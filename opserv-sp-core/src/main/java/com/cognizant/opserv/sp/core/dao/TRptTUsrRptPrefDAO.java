package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptTUsrRptPref;
import com.cognizant.opserv.sp.core.entity.TRptTUsrRptPrefId;
import com.cognizant.opserv.sp.core.entity.TUsrRptPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptTUsrRptPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptTUsrRptPrefDAO {

	/**
	 * Stores a new TRptTUsrRptPref entity object in to the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be persisted
	 * @return tRptTUsrRptPref Persisted TRptTUsrRptPref object
	 */
	TRptTUsrRptPref createTRptTUsrRptPref(TRptTUsrRptPref tRptTUsrRptPref);

	/**
	 * Deletes a TRptTUsrRptPref entity object from the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be deleted
	 */
	void deleteTRptTUsrRptPref(TRptTUsrRptPrefId tRptTUsrRptPrefId);

	/**
	 * Updates a TRptTUsrRptPref entity object in to the persistent store
	 * 
	 * @param tRptTUsrRptPref
	 *            TRptTUsrRptPref Entity object to be updated
	 * @return tRptTUsrRptPref Persisted TRptTUsrRptPref object
	 */
	TRptTUsrRptPref updateTRptTUsrRptPref(TRptTUsrRptPref tRptTUsrRptPref);

	/**
	 * Retrieve an TRptTUsrRptPref object based on given TRptTUsrRptPrefId.
	 * 
	 * @param tRptTUsrRptPrefId
	 *            the primary key value of the TRptTUsrRptPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptTUsrRptPref findTRptTUsrRptPrefById(TRptTUsrRptPrefId tRptTUsrRptPrefId);

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptTUsrRptPref> findTRptTUsrRptPrefs(SearchFilter<TRptTUsrRptPref> searchFilter);

	/**
	 * Count TRptTUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTUsrRptPrefs(SearchFilter<TRptTUsrRptPref> searchFilter);

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTRptStatusType(SearchFilter<TRptStatusType> searchFilter);

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptStatusType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTRptTUsrRptPrefsByTRptStatusType(SearchFilter<TRptStatusType> searchFilter);

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTUsrRptPref(SearchFilter<TUsrRptPref> searchFilter);

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTUsrRptPrefsByTUsrRptPref(SearchFilter<TUsrRptPref> searchFilter);
	
	List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTUsrRptPrefAndTRpt(Integer reportId, Integer folderId, final Long spId, final Long spHId, Short tenantId);

	/**
	 * Retrieve TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptTUsrRptPref> list of TRptTUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TRptTUsrRptPref> getTRptTUsrRptPrefsByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);

	/**
	 * Count TRptTUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTRptTUsrRptPrefsByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);
	
	/**
	 * @method fetchFolderReports - fetches folder reports
	 * @param folderId - folder Id
	 * @param tenantId - tenant Id
	 * @return List<Object[]> - folder reports details
	 */
	List<Object[]> fetchFolderReports(Integer folderId, Short tenantId,Integer staffId);
	
	/**
	 * @method countOfFolderReports - Count of folder reports
	 * @param folderId - folder Id
	 * @param tenantId - tenant Id
	 * @return Integer - Count of folder reports
	 */
	Integer countOfFolderReports(Integer folderId, Short tenantId);
}
