package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustPrdSales;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesId;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustPrd DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustPrdSalesDAO {

	/**
	 * Stores a new TCustPrd entity object in to the persistent store
	 * 
	 * @param TCustPrdSales
	 *            TCustPrd Entity object to be persisted
	 * @return tCustPrd Persisted TCustPrd object
	 */
	TCustPrdSales createTCustPrd(TCustPrdSales tCustPrd);

	/**
	 * Deletes a TCustPrd entity object from the persistent store
	 * 
	 * @param tCustPrd
	 *            TCustPrd Entity object to be deleted
	 */
	void deleteTCustPrd(TCustPrdSalesId tCustPrdId);

	/**
	 * Updates a TCustPrd entity object in to the persistent store
	 * 
	 * @param tCustPrd
	 *            TCustPrd Entity object to be updated
	 * @return tCustPrd Persisted TCustPrd object
	 */
	TCustPrdSales updateTCustPrd(TCustPrdSales tCustPrd);

	/**
	 * Retrieve an TCustPrd object based on given TCustPrdId.
	 * 
	 * @param tCustPrdId
	 *            the primary key value of the TCustPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustPrdSales findTCustPrdById(TCustPrdSalesId tCustPrdId);

	/**
	 * Retrieve TCustPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrdSales> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustPrdSales> findTCustPrds(SearchFilter<TCustPrdSales> searchFilter);

	/**
	 * Count TCustPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustPrds(SearchFilter<TCustPrdSales> searchFilter);

	/**
	 * Retrieve TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrd> getTPrdsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustPrdsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustPrdSales based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrd> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustPrdSales> getTCustPrdsByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Count TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustPrdsByTPrd(SearchFilter<TPrd> searchFilter);
	
	/**
	 * Retrieve TCustPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrdSales> list of TCustPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustPrdSales> getTCustPrdsByCustIdList(TCust customer, Short tenantId);

}
