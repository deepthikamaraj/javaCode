package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptCategory;
import com.cognizant.opserv.sp.core.entity.TRptCategoryId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptCategory DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptCategoryDAO {

	/**
	 * Stores a new TRptCategory entity object in to the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be persisted
	 * @return tRptCategory Persisted TRptCategory object
	 */
	TRptCategory createTRptCategory(TRptCategory tRptCategory);

	/**
	 * Deletes a TRptCategory entity object from the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be deleted
	 */
	void deleteTRptCategory(Integer rptCategoryId);

	/**
	 * Updates a TRptCategory entity object in to the persistent store
	 * 
	 * @param tRptCategory
	 *            TRptCategory Entity object to be updated
	 * @return tRptCategory Persisted TRptCategory object
	 */
	TRptCategory updateTRptCategory(TRptCategory tRptCategory);

	/**
	 * Retrieve an TRptCategory object based on given rptCategoryId.
	 * 
	 * @param rptCategoryId
	 *            the primary key value of the TRptCategory Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptCategory findTRptCategoryById(TRptCategoryId rptCategoryId);

	/**
	 * Retrieve TRptCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptCategory> list of TRptCategorys if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptCategory> findTRptCategorys(SearchFilter<TRptCategory> searchFilter);

	/**
	 * Count TRptCategory based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptCategorys(SearchFilter<TRptCategory> searchFilter);
	
	List<TRptCategory> findTRptCategoryByCategoryIds(Integer rptCategoryId);
	
	List<Object[]> findCategoryName(Integer rptCategoryId,String localeId);
	
	List<Object[]> findAllCategories(String localeId, short tenantId);

	String findCategoryNameByCategoryId(Integer categoryId);
}
