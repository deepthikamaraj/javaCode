package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TUsrRptPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TUsrRptPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsrRptPrefDAO {

	/**
	 * Stores a new TUsrRptPref entity object in to the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be persisted
	 * @return tUsrRptPref Persisted TUsrRptPref object
	 */
	TUsrRptPref createTUsrRptPref(TUsrRptPref tUsrRptPref);

	/**
	 * Deletes a TUsrRptPref entity object from the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be deleted
	 */
	void deleteTUsrRptPref(Integer prefId);
	//void deleteTUsrRptPref(TUsrRptPrefId tUsrRptPrefId);

	/**
	 * Updates a TUsrRptPref entity object in to the persistent store
	 * 
	 * @param tUsrRptPref
	 *            TUsrRptPref Entity object to be updated
	 * @return tUsrRptPref Persisted TUsrRptPref object
	 */
	TUsrRptPref updateTUsrRptPref(TUsrRptPref tUsrRptPref);

	/**
	 * Retrieve an TUsrRptPref object based on given TUsrRptPrefId.
	 * 
	 * @param tUsrRptPrefId
	 *            the primary key value of the TUsrRptPref Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	 TUsrRptPref findTUsrRptPrefById(Integer prefId);
	//TUsrRptPref findTUsrRptPrefById(TUsrRptPrefId tUsrRptPrefId);
	 
	 List<TUsrRptPref> findTUsrRptPrefByFolderName(final SearchFilter<TUsrRptPref> searchFilter);
	 
	 List<TUsrRptPref> findTUsrRptPrefsByStaffAndRptType(final Integer prnFolderId, final Integer staffId, final Short tenantId);

	/**
	 * Retrieve TUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrRptPref> findTUsrRptPrefs(SearchFilter<TUsrRptPref> searchFilter);
	
	List<TUsrRptPref> findTUsrRptPrefByParentId(final SearchFilter<TUsrRptPref> searchFilter);

	/**
	 * Count TUsrRptPref based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrRptPrefs(SearchFilter<TUsrRptPref> searchFilter);

	/**
	 * Retrieve TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrRptPref> getTUsrRptPrefsByTUsrRptPref(SearchFilter<TUsrRptPref> searchFilter);

	/**
	 * Count TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TUsrRptPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrRptPrefsByTUsrRptPref(SearchFilter<TUsrRptPref> searchFilter);

	/**
	 * Retrieve TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsrRptPref> list of TUsrRptPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TUsrRptPref> getTUsrRptPrefsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TUsrRptPref based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTUsrRptPrefsByTPers(SearchFilter<TPers> searchFilter);
	
	List<Object[]> fetchUserFoldersByRptType(Integer prefId,Integer staffId, Short tenantId);
	
	/**
	 * @method - countByFolderNameAndParentFolder
	 * @param folderName - folder Name
	 * @param parentFolderId - parent Folder Id
	 * @param staffId - staff Id
	 * @param tenantId - tenant Id
	 * @return Integer - count By Folder Name And Parent Folder
	 */
	Integer countByFolderNameAndParentFolder(String folderName,
			Integer parentFolderId, Integer staffId, Short tenantId);
	
	/**
	 * @method - countOfSubFolders
	 * @param parentFolderId - parent Folder Id
	 * @param tenantId - tenant Id
	 * @return Integer - count By sub Folder
	 */
	Integer countOfSubFolders(Integer parentFolderId, Short tenantId);
	
}
