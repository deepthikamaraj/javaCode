package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TNlBrick;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TNlBrick DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TNlBrickDAO {

	/**
	 * Stores a new TNlBrick entity object in to the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be persisted
	 * @return tNlBrick Persisted TNlBrick object
	 */
	TNlBrick createTNlBrick(TNlBrick tNlBrick);

	/**
	 * Deletes a TNlBrick entity object from the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be deleted
	 */
	void deleteTNlBrick(Integer brickId);

	/**
	 * Updates a TNlBrick entity object in to the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be updated
	 * @return tNlBrick Persisted TNlBrick object
	 */
	TNlBrick updateTNlBrick(TNlBrick tNlBrick);

	/**
	 * Retrieve an TNlBrick object based on given brickId.
	 * 
	 * @param brickId
	 *            the primary key value of the TNlBrick Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TNlBrick findTNlBrickById(Integer brickId);

	/**
	 * Retrieve TNlBrick based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNlBrick> list of TNlBricks if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TNlBrick> findTNlBricks(SearchFilter<TNlBrick> searchFilter);

	/**
	 * Count TNlBrick based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTNlBricks(SearchFilter<TNlBrick> searchFilter);

}
