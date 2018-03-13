package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsaState;

/**
 * Interface represents API's of TUsaState DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsaStateDAO {

	/**
	 * Stores a new TUsaState entity object in to the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be persisted
	 * @return tUsaState Persisted TUsaState object
	 */
	TUsaState createTUsaState(TUsaState tUsaState);

	/**
	 * Deletes a TUsaState entity object from the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be deleted
	 */
	void deleteTUsaState(Integer stateId);

	/**
	 * Updates a TUsaState entity object in to the persistent store
	 * 
	 * @param tUsaState
	 *            TUsaState Entity object to be updated
	 * @return tUsaState Persisted TUsaState object
	 */
	TUsaState updateTUsaState(TUsaState tUsaState);

	/**
	 * Retrieve an TUsaState object based on given stateId.
	 * 
	 * @param stateId
	 *            the primary key value of the TUsaState Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TUsaState findTUsaStateById(Integer stateId);

	/**
	 * Retrieve TUsaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaState> list of TUsaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TUsaState> findTUsaStates(SearchFilter<TUsaState> searchFilter);

	/**
	 * Count TUsaState based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTUsaStates(SearchFilter<TUsaState> searchFilter);

	/**
	 * Retrieve TUsaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaState> list of TUsaStates if it exists against given
	 *         criteria. Returns null if not found
	 */
	//List<TUsaState> getTUsaStatesByTCountry(SearchFilter<TCountry> searchFilter);

	/**
	 * Count TUsaState based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	//Object countTUsaStatesByTCountry(SearchFilter<TCountry> searchFilter);
	
	
	List<String> findTUsaStatesPolygon();

	List<TUsaState> getTUsaStates(String query);

	List<String> findTUsaStatesCodes();

}
