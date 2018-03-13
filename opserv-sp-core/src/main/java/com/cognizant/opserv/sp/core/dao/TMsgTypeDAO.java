package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TMsgType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TMsgType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TMsgTypeDAO {

	/**
	 * Stores a new TMsgType entity object in to the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be persisted
	 * @return tMsgType Persisted TMsgType object
	 */
	TMsgType createTMsgType(TMsgType tMsgType);

	/**
	 * Deletes a TMsgType entity object from the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be deleted
	 */
	void deleteTMsgType(Integer msgTypeId);

	/**
	 * Updates a TMsgType entity object in to the persistent store
	 * 
	 * @param tMsgType
	 *            TMsgType Entity object to be updated
	 * @return tMsgType Persisted TMsgType object
	 */
	TMsgType updateTMsgType(TMsgType tMsgType);

	/**
	 * Retrieve an TMsgType object based on given msgTypeId.
	 * 
	 * @param msgTypeId
	 *            the primary key value of the TMsgType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TMsgType findTMsgTypeById(Integer msgTypeId);

	/**
	 * Retrieve TMsgType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMsgType> list of TMsgTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TMsgType> findTMsgTypes(SearchFilter<TMsgType> searchFilter);

	/**
	 * Count TMsgType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTMsgTypes(SearchFilter<TMsgType> searchFilter);

}
