package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAllocType;
import com.cognizant.opserv.sp.core.entity.TAllocTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAllocType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAllocTypeDAO {

	/**
	 * Stores a new TAllocType entity object in to the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be persisted
	 * @return tAllocType Persisted TAllocType object
	 */
	TAllocType createTAllocType(TAllocType tAllocType);

	/**
	 * Deletes a TAllocType entity object from the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be deleted
	 */
	void deleteTAllocType(TAllocTypeId tAllocTypeId);

	/**
	 * Updates a TAllocType entity object in to the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be updated
	 * @return tAllocType Persisted TAllocType object
	 */
	TAllocType updateTAllocType(TAllocType tAllocType);

	/**
	 * Retrieve an TAllocType object based on given TAllocTypeId.
	 * 
	 * @param tAllocTypeId
	 *            the primary key value of the TAllocType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAllocType findTAllocTypeById(TAllocTypeId tAllocTypeId);

	/**
	 * Retrieve TAllocType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAllocType> list of TAllocTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAllocType> findTAllocTypes(SearchFilter<TAllocType> searchFilter);

	/**
	 * Count TAllocType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAllocTypes(SearchFilter<TAllocType> searchFilter);

	/**
	 * Find t alloc types by tenant and locale.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findTAllocTypesByTenantAndLocale(Short tenantId,
			String localeId);
	

}
