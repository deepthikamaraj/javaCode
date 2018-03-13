package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmntId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustPrdAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustPrdAlgmntDAO {

	/**
	 * Stores a new TCustPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be persisted
	 * @return tCustPrdAlgmnt Persisted TCustPrdAlgmnt object
	 */
	TCustPrdAlgmnt createTCustPrdAlgmnt(TCustPrdAlgmnt tCustPrdAlgmnt);

	/**
	 * Deletes a TCustPrdAlgmnt entity object from the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be deleted
	 */
	void deleteTCustPrdAlgmnt(TCustPrdAlgmntId tCustPrdAlgmntId);

	/**
	 * Updates a TCustPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be updated
	 * @return tCustPrdAlgmnt Persisted TCustPrdAlgmnt object
	 */
	TCustPrdAlgmnt updateTCustPrdAlgmnt(TCustPrdAlgmnt tCustPrdAlgmnt);

	/**
	 * Retrieve an TCustPrdAlgmnt object based on given TCustPrdAlgmntId.
	 * 
	 * @param tCustPrdAlgmntId
	 *            the primary key value of the TCustPrdAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustPrdAlgmnt findTCustPrdAlgmntById(TCustPrdAlgmntId tCustPrdAlgmntId);

	/**
	 * Retrieve TCustPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrdAlgmnt> list of TCustPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustPrdAlgmnt> findTCustPrdAlgmnts(SearchFilter<TCustPrdAlgmnt> searchFilter);

	/**
	 * Count TCustPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustPrdAlgmnts(SearchFilter<TCustPrdAlgmnt> searchFilter);
	
	/**
	 * Find customer products by sale pos id.
	 *
	 * @param custId the cust id
	 * @param salesPosId the sales pos id
	 * @param index the index
	 * @param noOfResult the no of result
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the list
	 */
	List<TCustPrdAlgmnt> findCustomerProductsBySalePosId(Long custId,Long salesPosId,int index, int noOfResult,Short tenantId,boolean flag);

	/**
	 * Find customer product list by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustPrdAlgmnt> findCustomerProductListBySalesPosId(Long salesPosId, Short tenantId);
	
	/**
	 * Fetch t cust prd algmnt by cust al id.
	 *
	 * @param custALId the cust al id
	 * @return the list
	 */
	List<TCustPrdAlgmnt> fetchTCustPrdAlgmntByCustALId(Long custALId ,Character activeFlag);
	
	/**
	 * Fetch tcustprdalgmnt by customer Id
	 * @param custIdList
	 * @param tenantId
	 * @return List<TCustPrdAlgmnt>
	 */
	List<TCustPrdAlgmnt> populateCustPrdAlgmntByCustAlId(List<Long> custIdList, Short tenantId);
}
