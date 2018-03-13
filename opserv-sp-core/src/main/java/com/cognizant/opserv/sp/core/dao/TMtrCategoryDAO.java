package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMtrCategory;
import com.cognizant.opserv.sp.core.entity.TMtrCategoryId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMtrCategory DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMtrCategoryDAO {

	/**
	 * Stores a new TMtrCategory entity object in to the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be persisted
	 * @return tMtrCategory Persisted TMtrCategory object
	 */
	TMtrCategory createTMtrCategory(TMtrCategory tMtrCategory);

	/**
	 * Deletes a TMtrCategory entity object from the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be deleted
	 */
	void deleteTMtrCategory(Integer mtrCategoryId);

	/**
	 * Updates a TMtrCategory entity object in to the persistent store
	 * 
	 * @param tMtrCategory
	 *            TMtrCategory Entity object to be updated
	 * @return tMtrCategory Persisted TMtrCategory object
	 */
	TMtrCategory updateTMtrCategory(TMtrCategory tMtrCategory);

	/**
	 * Retrieve an TMtrCategory object based on given mtrCategoryId.
	 * 
	 * @param mtrCategoryId
	 *            the primary key value of the TMtrCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	//TMtrCategory findTMtrCategoryById(Integer mtrCategoryId);
	TMtrCategory findTMtrCategoryById(TMtrCategoryId tMtrCategoryId);
	/**
	 * Retrieve TMtrCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrCategory> list of TMtrCategorys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMtrCategory> findTMtrCategorys(SearchFilter<TMtrCategory> searchFilter);

	/**
	 * Count TMtrCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMtrCategorys(SearchFilter<TMtrCategory> searchFilter);

	List<TMtrCategory> findActiveTMtrCategorys(Short tenantId);
	
	List<Object[]> findTMtrCategoryList(SearchFilter<TMtrCategory> searchFilter, String localeId);

	List<TMtrCategory> getActiveTMtrCategorys(Short tenantId, String localeId);

	List<TMtrCategory> findAllCategories(Character activeFlag, String localeId, short tenantId);

}
