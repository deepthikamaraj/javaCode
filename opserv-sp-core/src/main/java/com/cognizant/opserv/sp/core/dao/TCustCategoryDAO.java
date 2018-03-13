package com.cognizant.opserv.sp.core.dao;


import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.core.entity.TCustCategory;
import com.cognizant.opserv.sp.core.entity.TCustCategoryId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustCategory DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustCategoryDAO {

	/**
	 * Stores a new TCustCategory entity object in to the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be persisted
	 * @return tCustCategory Persisted TCustCategory object
	 */
	TCustCategory createTCustCategory(TCustCategory tCustCategory);

	/**
	 * Deletes a TCustCategory entity object from the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be deleted
	 */
	void deleteTCustCategory(Integer categoryId);

	/**
	 * Updates a TCustCategory entity object in to the persistent store
	 * 
	 * @param tCustCategory
	 *            TCustCategory Entity object to be updated
	 * @return tCustCategory Persisted TCustCategory object
	 */
	TCustCategory updateTCustCategory(TCustCategory tCustCategory);

	/**
	 * Retrieve an TCustCategory object based on given categoryId.
	 * 
	 * @param categoryId
	 *            the primary key value of the TCustCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustCategory findTCustCategoryById(Integer categoryId);

   TCustCategory findTCustCategoryById(TCustCategoryId categoryId);  

	/**
	 * Retrieve TCustCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCategory> list of TCustCategorys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustCategory> findTCustCategorys(SearchFilter<TCustCategory> searchFilter);

	/**
	 * Count TCustCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCategorys(SearchFilter<TCustCategory> searchFilter);
	
	/**
	 * Find all categorys.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<TCustCategory> findAllCategorys(Short tenantId, String localeId);

	/**
	 * Find all categories.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findAllCategories(Short tenantId, String localeId);
	
	/**
	 * Find all t cust cat name and id.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the map
	 */
	Map<Integer,String> findAllTCustCatNameAndId(Short tenantId, String localeId);

}
