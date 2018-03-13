package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.core.entity.TOrgSalesHier;
import com.cognizant.opserv.sp.core.entity.TSalesHierList;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TOrgSalesHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TOrgSalesHierDAO {

	/**
	 * Stores a new TOrgSalesHier entity object in to the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be persisted
	 * @return tOrgSalesHier Persisted TOrgSalesHier object
	 */
	TOrgSalesHier createTOrgSalesHier(TOrgSalesHier tOrgSalesHier);

	/**
	 * Deletes a TOrgSalesHier entity object from the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be deleted
	 */
	void deleteTOrgSalesHier(Long orgSalesHierId);

	/**
	 * Updates a TOrgSalesHier entity object in to the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be updated
	 * @return tOrgSalesHier Persisted TOrgSalesHier object
	 */
	TOrgSalesHier updateTOrgSalesHier(TOrgSalesHier tOrgSalesHier);

	/**
	 * Retrieve an TOrgSalesHier object based on given orgSalesHierId.
	 * 
	 * @param orgSalesHierId
	 *            the primary key value of the TOrgSalesHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TOrgSalesHier findTOrgSalesHierById(Long orgSalesHierId);

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgSalesHier> findTOrgSalesHiers(SearchFilter<TOrgSalesHier> searchFilter);

	/**
	 * Count TOrgSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgSalesHiers(SearchFilter<TOrgSalesHier> searchFilter);

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgSalesHier> getTOrgSalesHiersByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgSalesHiersByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgSalesHier> getTOrgSalesHiersByTOrgSalesHier(SearchFilter<TOrgSalesHier> searchFilter);

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgSalesHiersByTOrgSalesHier(SearchFilter<TOrgSalesHier> searchFilter);

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TOrgSalesHier> getTOrgSalesHiersByTSalesHierList(SearchFilter<TSalesHierList> searchFilter);

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTOrgSalesHiersByTSalesHierList(SearchFilter<TSalesHierList> searchFilter);
	
	List<TOrgSalesHier> getActiveOrgSalesHierByTenant(SearchFilter<TOrgSalesHier> searchFilter);

	List<TOrgSalesHier> fetchSequenceNumber(Short tenantId);

	List<TOrgSalesHier> findTorgSalesHier(Long orgsalesHierId, Short tenantId);

}
