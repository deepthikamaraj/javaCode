package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TSalesHierList;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TSalesHierList DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TSalesHierListDAO {

	/**
	 * Stores a new TSalesHierList entity object in to the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be persisted
	 * @return tSalesHierList Persisted TSalesHierList object
	 */
	TSalesHierList createTSalesHierList(TSalesHierList tSalesHierList);

	/**
	 * Deletes a TSalesHierList entity object from the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be deleted
	 */
	void deleteTSalesHierList(Long salesHierId);

	/**
	 * Updates a TSalesHierList entity object in to the persistent store
	 * 
	 * @param tSalesHierList
	 *            TSalesHierList Entity object to be updated
	 * @return tSalesHierList Persisted TSalesHierList object
	 */
	TSalesHierList updateTSalesHierList(TSalesHierList tSalesHierList);

	/**
	 * Retrieve an TSalesHierList object based on given salesHierId.
	 * 
	 * @param salesHierId
	 *            the primary key value of the TSalesHierList Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TSalesHierList findTSalesHierListById(Long salesHierId);

	/**
	 * Retrieve TSalesHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesHierList> list of TSalesHierLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesHierList> findTSalesHierLists(SearchFilter<TSalesHierList> searchFilter);

	/**
	 * Count TSalesHierList based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesHierLists(SearchFilter<TSalesHierList> searchFilter);

	/**
	 * Retrieve TSalesHierList based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesHierList> list of TSalesHierLists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TSalesHierList> getTSalesHierListsByTSalesHierList(SearchFilter<TSalesHierList> searchFilter);

	/**
	 * Count TSalesHierList based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTSalesHierListsByTSalesHierList(SearchFilter<TSalesHierList> searchFilter);
	
	List<TSalesHierList> getActiveTSalesHierList(SearchFilter<TSalesHierList> searchFilter);

	List<TSalesHierList> fetchSequenceNumber(Short tenantId);
	
	

}
