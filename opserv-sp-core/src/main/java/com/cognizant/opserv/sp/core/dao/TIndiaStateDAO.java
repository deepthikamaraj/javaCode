package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TIndiaState;

/**
 * Interface represents API's of TIndiaState DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TIndiaStateDAO {

	/**
	 * Stores a new TIndiaState entity object in to the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be persisted
	 * @return tIndiaState Persisted TIndiaState object
	 */
	TIndiaState createTIndiaState(TIndiaState tIndiaState);

	/**
	 * Deletes a TIndiaState entity object from the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be deleted
	 */
	void deleteTIndiaState(Integer stateId);

	/**
	 * Updates a TIndiaState entity object in to the persistent store
	 * 
	 * @param tIndiaState
	 *            TIndiaState Entity object to be updated
	 * @return tIndiaState Persisted TIndiaState object
	 */
	TIndiaState updateTIndiaState(TIndiaState tIndiaState);

	/**
	 * Retrieve an TIndiaState object based on given stateId.
	 * 
	 * @param stateId
	 *            the primary key value of the TIndiaState Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TIndiaState findTIndiaStateById(Integer stateId);

	/**
	 * Retrieve TIndiaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaState> list of TIndiaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TIndiaState> findTIndiaStates(SearchFilter<TIndiaState> searchFilter);

	/**
	 * Count TIndiaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTIndiaStates(SearchFilter<TIndiaState> searchFilter);

	/**
	 * Retrieve TIndiaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TIndiaState> list of TIndiaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TIndiaState> getTIndiaStatesByTCountry(SearchFilter<TCountry> searchFilter);

	/**
	 * Count TIndiaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTIndiaStatesByTCountry(SearchFilter<TCountry> searchFilter);
	
	List<String> findTIndiaStatesPolygon();
	//added for logical layer themes apply
	List<String> findIndiaStates();

}
