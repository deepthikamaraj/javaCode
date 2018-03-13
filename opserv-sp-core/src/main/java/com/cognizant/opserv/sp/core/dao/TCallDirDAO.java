package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallDir;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallDir DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallDirDAO {

	/**
	 * Stores a new TCallDir entity object in to the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be persisted
	 * @return tCallDir Persisted TCallDir object
	 */
	TCallDir createTCallDir(TCallDir tCallDir);

	/**
	 * Deletes a TCallDir entity object from the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be deleted
	 * @return tCallDir Persisted TCallDir object
	 */
	void deleteTCallDir(Integer callDirId);

	/**
	 * Updates a TCallDir entity object in to the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be updated
	 * @return tCallDir Persisted TCallDir object
	 */
	TCallDir updateTCallDir(TCallDir tCallDir);

	/**
	 * Retrieve an TCallDir object based on given callDirId.
	 * 
	 * @param callDirId
	 *            the primary key value of the TCallDir Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallDir findTCallDirById(Integer callDirId);

	/**
	 * Retrieve TCallDir based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDir> list of TCallDirs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDir> findTCallDirs(SearchFilter<TCallDir> searchFilter);

	/**
	 * Count TCallDir based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirs(SearchFilter<TCallDir> searchFilter);

	/**
	 * Retrieve TCallDir based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDir> list of TCallDirs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDir> getTCallDirsByTCustCallPlan(SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Count TCallDir based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirsByTCustCallPlan(SearchFilter<TCustCallPlan> searchFilter);
	
	/**
	 * Get Call Direction Details from TCallDir
	 * @param custCallPlanId
	 * 
	 * @return an Object of CallDirectionMatrix type
	 * 
	 */
	List<Object> getCallDirectionMatrix(TCustCallPlan tCustCallPlan);
	
	/**
	 * Delete CallDirection Details From TCallDir
	 * @param callDirectionId
	 * 
	 * @return void
	 * 
	 */
	Boolean deleteCallDirection(Integer callDirectionId, Short tenantId);
	/**
	 * Get Header Names
	 * 
	 * @param size
	 * 
	 * @return String of TopColNames
	 * 
	 */
	List<String> getHeaderNames(Integer size);
	/**
	 * Get Targeted Customers
	 * 
	 * @param queryParams
	 * 
	 * @param showBy
	 * 
	 * @param custType
	 * 
	 * @param targetFlag
	 * 
	 * @return an object of targetedCustomerList
	 * 
	 */
	
	List<Object> getTargetedCustomers(List queryParams, Integer showBy, Boolean callPlanAffiliationFlag, Integer custType, String targetFlag);
	/**
	 * Get ProductsForCallDir
	 * 
	 * @param queryParams
	 * 
	 * @return an object of ProductsForNonCallDirectionBasedCallPlan
	 * 
	 */
	List<Object[]> fetchProductsForCallDir(List queryParams);
	/**
	 * Get Targeted Customer
	 * 
	 * @param queryParams
	 * 
	 * @param showBy
	 * 
	 * @return an object of TargetedCustomer
	 * 
	 */
	List<Object> searchTargetedCustomer(List queryParams,Integer showBy);
	/**
	 * Get Customers Count
	 * 
	 * @param queryParams
	 * 
	 * @param callPlanAffiliationFlag
	 * 
	 * @return count
	 * 
	 */
	
	Long getCustomersCount(List queryParams,Boolean callPlanAffiliationFlag);
	/**
	 * Get Targeted Customers
	 * 
	 * @param custAlgmntIds
	 * 
	 * @param tenantId
	 * 
	 * @param localeId
	 * 
	 * @return targetedCustomerList
	 * 
	 */
	List<Object> getTargetedCustomers(List<Long> custAlgmntIds,Short tenantId,String localeId);

}
