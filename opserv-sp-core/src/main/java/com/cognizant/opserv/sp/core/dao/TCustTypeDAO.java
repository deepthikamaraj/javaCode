package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.opserv.sp.core.entity.TCustTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustTypeDAO {

	/**
	 * Stores a new TCustType entity object in to the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be persisted
	 * @return tCustType Persisted TCustType object
	 */
	TCustType createTCustType(TCustType tCustType);

	/**
	 * Deletes a TCustType entity object from the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be deleted
	 */
	void deleteTCustType(TCustTypeId custTypeId);

	/**
	 * Updates a TCustType entity object in to the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be updated
	 * @return tCustType Persisted TCustType object
	 */
	TCustType updateTCustType(TCustType tCustType);

	/**
	 * Retrieve an TCustType object based on given custTypeId.
	 * 
	 * @param custTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustType findTCustTypeById(Integer custTypeId);
	/**
	 * Retrieve an TCustType object based on given TCustType custTypeId.
	 * 
	 * @param tCustTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustType findTCustTypeByIdUsingTCust(final TCustTypeId tCustTypeId);
	/**
	 * Retrieve an TCustType object based on given TCustType custTypeId.
	 * 
	 * @param tCustTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	List<TCustType> findTCustTypeByCustTypeName(String custTypeName, final Short tenantId);

	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustType> list of TCustTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustType> findTCustTypes(SearchFilter<TCustType> searchFilter);

	/**
	 * Count TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustTypes(SearchFilter<TCustType> searchFilter);
	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of TCustTypes if it exists against given criteria.
	 *        
	 */
	List<Object> findallTypes(Short tenantId);
	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of AllTCustTypesByIds if it exists against given criteria.
	 *        
	 */
	
	List<Object[]> findAllCustTypes(Short tenantId, String localeId);
	
	/**
	 * 
	 * @param tenantId
	 * @param locale
	 * @return list of customer types for given locale id and tenant id
	 */
	List<TCustType> findCustTypeByLocale(Short tenantId,String locale);
	
	/**
	 * @param tenantId - holds tenant Id
	 * @param locale - holds locale
	 * @return
	 */
	List<Object[]> findAllTCustTypeIdAndName(Short tenantId,String locale);

	/**
	 * Gets the all customer type name.
	 *
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer type name
	 */
	List<String> getAllCustomerTypeName(Short tenantId, String locale);

}
