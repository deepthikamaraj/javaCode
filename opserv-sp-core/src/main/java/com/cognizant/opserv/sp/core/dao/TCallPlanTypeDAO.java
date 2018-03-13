package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallPlanType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallPlanType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallPlanTypeDAO {

	/**
	 * Stores a new TCallPlanType entity object in to the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be persisted
	 * @return tCallPlanType Persisted TCallPlanType object
	 */
	TCallPlanType createTCallPlanType(TCallPlanType tCallPlanType);

	/**
	 * Deletes a TCallPlanType entity object from the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be deleted
	 */
	void deleteTCallPlanType(Short callPlanTypeId);

	/**
	 * Updates a TCallPlanType entity object in to the persistent store
	 * 
	 * @param tCallPlanType
	 *            TCallPlanType Entity object to be updated
	 * @return tCallPlanType Persisted TCallPlanType object
	 */
	TCallPlanType updateTCallPlanType(TCallPlanType tCallPlanType);

	/**
	 * Retrieve an TCallPlanType object based on given callPlanTypeId.
	 * 
	 * @param callPlanTypeId
	 *            the primary key value of the TCallPlanType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallPlanType findTCallPlanTypeById(Short callPlanTypeId);

	/**
	 * Retrieve TCallPlanType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanType> list of TCallPlanTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallPlanType> findTCallPlanTypes(SearchFilter<TCallPlanType> searchFilter);

	/**
	 * Count TCallPlanType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallPlanTypes(SearchFilter<TCallPlanType> searchFilter);

}
