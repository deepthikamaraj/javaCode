package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TBussWkflwWndw;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TBussWkflwWndw DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TBussWkflwWndwDAO {

	/**
	 * Stores a new TBussWkflwWndw entity object in to the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be persisted
	 * @return tBussWkflwWndw Persisted TBussWkflwWndw object
	 */
	TBussWkflwWndw createTBussWkflwWndw(TBussWkflwWndw tBussWkflwWndw);

	/**
	 * Deletes a TBussWkflwWndw entity object from the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be deleted
	 */
	void deleteTBussWkflwWndw(Integer wkflwWndwId);

	/**
	 * Updates a TBussWkflwWndw entity object in to the persistent store
	 * 
	 * @param tBussWkflwWndw
	 *            TBussWkflwWndw Entity object to be updated
	 * @return tBussWkflwWndw Persisted TBussWkflwWndw object
	 */
	TBussWkflwWndw updateTBussWkflwWndw(TBussWkflwWndw tBussWkflwWndw);

	/**
	 * Retrieve an TBussWkflwWndw object based on given wkflwWndwId.
	 * 
	 * @param wkflwWndwId
	 *            the primary key value of the TBussWkflwWndw Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TBussWkflwWndw findTBussWkflwWndwById(Integer wkflwWndwId);

	/**
	 * Retrieve TBussWkflwWndw based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussWkflwWndw> list of TBussWkflwWndws if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussWkflwWndw> findTBussWkflwWndws(SearchFilter<TBussWkflwWndw> searchFilter);

	/**
	 * Count TBussWkflwWndw based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussWkflwWndws(SearchFilter<TBussWkflwWndw> searchFilter);

	/**
	 * Retrieve TBussWkflwWndw based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussWkflwWndw> list of TBussWkflwWndws if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TBussWkflwWndw> getTBussWkflwWndwsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TBussWkflwWndw based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTBussWkflwWndwsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

}
