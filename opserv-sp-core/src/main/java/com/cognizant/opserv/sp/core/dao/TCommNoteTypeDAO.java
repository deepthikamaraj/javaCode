package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommNoteType;
import com.cognizant.opserv.sp.core.entity.TCommNoteTypeId;
import com.cognizant.opserv.sp.core.entity.TNoteType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCommNoteType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCommNoteTypeDAO {

	/**
	 * Stores a new TCommNoteType entity object in to the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be persisted
	 * @return tCommNoteType Persisted TCommNoteType object
	 */
	TCommNoteType createTCommNoteType(TCommNoteType tCommNoteType);

	/**
	 * Deletes a TCommNoteType entity object from the persistent store
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be deleted
	 * @return 
	 */
	void deleteTCommNoteType(TCommNoteTypeId tCommNoteTypeId);

	/**
	 * Updates a TCommNoteType entity object in to the persistent store
	 * 
	 * @param tCommNoteType
	 *            TCommNoteType Entity object to be updated
	 * @return tCommNoteType Persisted TCommNoteType object
	 */
	TCommNoteType updateTCommNoteType(TCommNoteType tCommNoteType);

	/**
	 * Retrieve an TCommNoteType object based on given TCommNoteTypeId.
	 * 
	 * @param tCommNoteTypeId
	 *            the primary key value of the TCommNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCommNoteType findTCommNoteTypeById(TCommNoteTypeId tCommNoteTypeId);

	/**
	 * Retrieve TCommNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommNoteType> findTCommNoteTypes(SearchFilter<TCommNoteType> searchFilter);

	/**
	 * Count TCommNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommNoteTypes(SearchFilter<TCommNoteType> searchFilter);

	/**
	 * Retrieve TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommNoteType> getTCommNoteTypesByTNoteType(SearchFilter<TNoteType> searchFilter);

	/**
	 * Count TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommNoteTypesByTNoteType(SearchFilter<TNoteType> searchFilter);

	/**
	 * Retrieve TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommNoteType> list of TCommNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCommNoteType> getTCommNoteTypesByTComm(SearchFilter<TComm> searchFilter);

	/**
	 * Count TCommNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCommNoteTypesByTComm(SearchFilter<TComm> searchFilter);
	
	/**
	 * Gets the notification details.
	 *
	 * @param searchFilter the search filter
	 * @return the notification details
	 */
	List<TCommNoteType> getNotificationDetails(SearchFilter<TCommNoteType> searchFilter);

	/**
	 * Find t comm note type by comm id.
	 *
	 * @param commId the comm id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCommNoteType> findTCommNoteTypeByCommID(Long commId, Short tenantId);

	/*TCommNoteType findTCommNoteTypeByTCommID(TCommNoteTypeId tCommNoteTypeId);*/


}
