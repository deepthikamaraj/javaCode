package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussFunction DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussFunctionDAO {

	/**
	 * Stores a new TBussFunction entity object in to the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be persisted
	 * @return tBussFunction Persisted TBussFunction object
	 */
	TBussFunction createTBussFunction(TBussFunction tBussFunction);

	/**
	 * Deletes a TBussFunction entity object from the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be deleted
	 */
	void deleteTBussFunction(Integer bussFunctionId);

	/**
	 * Updates a TBussFunction entity object in to the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be updated
	 * @return tBussFunction Persisted TBussFunction object
	 */
	TBussFunction updateTBussFunction(TBussFunction tBussFunction);

	/**
	 * Retrieve an TBussFunction object based on given bussFunctionId.
	 * 
	 * @param bussFunctionId
	 *            the primary key value of the TBussFunction Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussFunction findTBussFunctionById(Integer bussFunctionId);

	/**
	 * Retrieve TBussFunction based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussFunction> list of TBussFunctions if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussFunction> findTBussFunctions(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Count TBussFunction based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussFunctions(SearchFilter<TBussFunction> searchFilter);

	/**
	 * Find buss fun entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TBussFunction> findBussFunEntitiesByTenantId(Short tenantId);

}
