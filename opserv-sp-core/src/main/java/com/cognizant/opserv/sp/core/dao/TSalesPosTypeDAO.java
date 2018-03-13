package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesPosType;
import com.cognizant.opserv.sp.core.entity.TSalesPosTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesPosType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesPosTypeDAO {

	/**
	 * Stores a new TSalesPosType entity object in to the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be persisted
	 * @return tSalesPosType Persisted TSalesPosType object
	 */
	TSalesPosType createTSalesPosType(TSalesPosType tSalesPosType);

	/**
	 * Deletes a TSalesPosType entity object from the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be deleted
	 */
	void deleteTSalesPosType(TSalesPosTypeId tSalesPosTypeId);

	/**
	 * Updates a TSalesPosType entity object in to the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be updated
	 * @return tSalesPosType Persisted TSalesPosType object
	 */
	TSalesPosType updateTSalesPosType(TSalesPosType tSalesPosType);

	/**
	 * Retrieve an TSalesPosType object based on given TSalesPosTypeId.
	 * 
	 * @param tSalesPosTypeId
	 *            the primary key value of the TSalesPosType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesPosType findTSalesPosTypeById(TSalesPosTypeId tSalesPosTypeId);

	/**
	 * Retrieve TSalesPosType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosType> list of TSalesPosTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesPosType> findTSalesPosTypes(SearchFilter<TSalesPosType> searchFilter);

	/**
	 * Count TSalesPosType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesPosTypes(SearchFilter<TSalesPosType> searchFilter);

}
