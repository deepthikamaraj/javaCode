package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAddrType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAddrType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAddrTypeDAO {

	/**
	 * Stores a new TAddrType entity object in to the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be persisted
	 * @return tAddrType Persisted TAddrType object
	 */
	TAddrType createTAddrType(TAddrType tAddrType);

	/**
	 * Deletes a TAddrType entity object from the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be deleted
	 */
	void deleteTAddrType(Integer addrTypeId);

	/**
	 * Updates a TAddrType entity object in to the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be updated
	 * @return tAddrType Persisted TAddrType object
	 */
	TAddrType updateTAddrType(TAddrType tAddrType);

	/**
	 * Retrieve an TAddrType object based on given addrTypeId.
	 * 
	 * @param addrTypeId
	 *            the primary key value of the TAddrType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAddrType findTAddrTypeById(Integer addrTypeId);

	/**
	 * Retrieve TAddrType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAddrType> list of TAddrTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAddrType> findTAddrTypes(SearchFilter<TAddrType> searchFilter);

	/**
	 * Count TAddrType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAddrTypes(SearchFilter<TAddrType> searchFilter);

}
