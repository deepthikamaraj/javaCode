package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigTPers;
import com.cognizant.opserv.sp.core.entity.TRptConfigTPersId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptConfigTPers DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptConfigTPersDAO {

	/**
	 * Stores a new TRptConfigTPers entity object in to the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be persisted
	 * @return tRptConfigTPers Persisted TRptConfigTPers object
	 */
	TRptConfigTPers createTRptConfigTPers(TRptConfigTPers tRptConfigTPers);

	/**
	 * Deletes a TRptConfigTPers entity object from the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be deleted
	 */
	void deleteTRptConfigTPers(TRptConfigTPersId tRptConfigTPersId);

	/**
	 * Updates a TRptConfigTPers entity object in to the persistent store
	 * 
	 * @param tRptConfigTPers
	 *            TRptConfigTPers Entity object to be updated
	 * @return tRptConfigTPers Persisted TRptConfigTPers object
	 */
	TRptConfigTPers updateTRptConfigTPers(TRptConfigTPers tRptConfigTPers);

	/**
	 * Retrieve an TRptConfigTPers object based on given TRptConfigTPersId.
	 * 
	 * @param tRptConfigTPersId
	 *            the primary key value of the TRptConfigTPers Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptConfigTPers findTRptConfigTPersById(TRptConfigTPersId tRptConfigTPersId);

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTPers> findTRptConfigTPerss(SearchFilter<TRptConfigTPers> searchFilter);

	/**
	 * Count TRptConfigTPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTPerss(SearchFilter<TRptConfigTPers> searchFilter);

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTPers> getTRptConfigTPerssByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTPerssByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Retrieve TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTPers> list of TRptConfigTPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTPers> getTRptConfigTPerssByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptConfigTPers based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTPerssByTRptConfig(SearchFilter<TRptConfig> searchFilter);

}
