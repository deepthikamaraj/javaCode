package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TAddrType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAddrId;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAddr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAddrDAO {

	/**
	 * Stores a new TCustAddr entity object in to the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be persisted
	 * @return tCustAddr Persisted TCustAddr object
	 */
	TCustAddr createTCustAddr(TCustAddr tCustAddr);

	/**
	 * Deletes a TCustAddr entity object from the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be deleted
	 */
	void deleteTCustAddr(TCustAddrId tCustAddrId);

	/**
	 * Updates a TCustAddr entity object in to the persistent store
	 * 
	 * @param tCustAddr
	 *            TCustAddr Entity object to be updated
	 * @return tCustAddr Persisted TCustAddr object
	 */
	TCustAddr updateTCustAddr(TCustAddr tCustAddr);

	/**
	 * Retrieve an TCustAddr object based on given TCustAddrId.
	 * 
	 * @param tCustAddrId
	 *            the primary key value of the TCustAddr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAddr findTCustAddrById(TCustAddrId tCustAddrId);

	/**
	 * Retrieve TCustAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAddr> findTCustAddrs(SearchFilter<TCustAddr> searchFilter);

	/**
	 * Count TCustAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAddrs(SearchFilter<TCustAddr> searchFilter);

	/**
	 * Retrieve TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAddr> getTCustAddrsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAddrsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAddr> list of TCustAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAddr> getTCustAddrsByTAddrType(SearchFilter<TAddrType> searchFilter);

	/**
	 * Count TCustAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAddrsByTAddrType(SearchFilter<TAddrType> searchFilter);
	
	/**
	 * Gets the postal cd by cust ids.
	 *
	 * @param query the query
	 * @return the postal cd by cust ids
	 */
	List<String> getPostalCdByCustIds(String query);
	
	/**
	 * Find pri address for customer.
	 *
	 * @param custId the cust id
	 * @param priFlg the pri flg
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the t cust addr
	 */
	TCustAddr findPriAddressForCustomer(Integer custId, Character priFlg, Character activeFlag,  short tenantId);  
	
	/**
	 * Gets the assigned customer.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param slPosiD the sl posi d
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the assigned customer
	 */
	List<Object[]> getAssignedCustomer(Long algmntId, Long bussUnitId,
			Long salesTeamId, Long slPosiD, String zip, Short tenantId);   
	
	/**
	 * Gets the customer address by id.
	 *
	 * @param addrid the addrid
	 * @param start the start
	 * @param results the results
	 * @return the customer address by id
	 */
	public List<TCustAddr> getCustomerAddressByID(Integer addrid,int start,int results);
	
	/**
	 * Gets the assigned customerfor assign.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesPosID the sales pos id
	 * @param salesHierID the sales hier id
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the assigned customerfor assign
	 */
	List<Object[]> getAssignedCustomerforAssign(Long algmntId, Long bussUnitId,
			Long salesTeamId,Long salesPosID,Long salesHierID,List<String> zip, Short tenantId);

	/**
	 * Gets the customerfor un assign.
	 *
	 * @param zip the zip
	 * @param tenantId the tenant id
	 * @return the customerfor un assign
	 */
	List<Object[]> getCustomerforUnAssign(List<String> zip, Short tenantId);
	
	/**
	 * Find pri address for all customer.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findPriAddressForAllCustomer(List<Integer> custId, Short tenantId);
	
	
	/**
	 * Find country for all customer.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<String> findCountryForAllCustomer(Short tenantId);

	/**
	 * Find country for state.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<String> findCountryForState(Short tenantId);

	/**
	 * Find addr of all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findAddrOfAllTCustsByIDS(List<Integer> custIds, Short tenantId);
	
	/**
	 * Update t cust addrs.
	 *
	 * @param tCustAddrss the t cust addrss
	 * @return the list
	 */
	List<TCustAddr> updateTCustAddrs(List<TCustAddr> tCustAddrss);
	
	/**
	 * Creates the t cust addrs.
	 *
	 * @param tCustAddrss the t cust addrss
	 * @return the list
	 */
	List<TCustAddr> createTCustAddrs(List<TCustAddr> tCustAddrss);
	
	/**
	 * Find t cust addrs.
	 *
	 * @param addrIds the addr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findTCustAddrs(List<Integer> addrIds,Short tenantId);
	
	/**
	 * Find all active addr by cust ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findAllActiveAddrByCustIds(List<Integer> custIds, Short tenantId);

	/**
	 * Gets the cust fr ass zip.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param postalCd the postal cd
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the cust fr ass zip
	 */
	List<Object[]> getCustFrAssZip(Long algmntId, Long bussUnitId, Long salesTeamId, List<String> postalCd, List<Long> salesPosId, List<Long> salesHierId, Short tenantId);

	/**
	 * Gets the cust fr unassign zip.
	 *
	 * @param unassignZip the unassign zip
	 * @return the cust fr unassign zip
	 */
	List<Object[]> getcustFrUnassignZip(List<String> unassignZip);
	
	/**
	 * Find search for state.
	 *
	 * @param tenantId the tenant id
	 * @param state the state
	 * @return the list
	 */
	List<String> FindSearchForState(Short tenantId,String state);
	
	/**
	 * Gets the univ customer fr bricks.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param zipList the zip list
	 * @param tenantId the tenant id
	 * @return the univ customer fr bricks
	 */
	List<Object[]> getUnivCustomerFrBricks(Long algmntId, Long bussUnitId, Long salesTeamId, List<String> zipList ,Short tenantId);
	
	/**
	 * Find all customers addr list.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findAllCustomersAddrList(List<Integer> custIds,Short tenantId);
	
	/**
	 * Find all sec addr of t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findAllSecAddrOfTCustsByIDS(List<Integer> custIds, Short tenantId);
	
		
	// newly added for secondary address validation	
	/**
	 * Find all addr of t custs by ids.Added for secondary address validation	
	 *
	 * @param custIdsList the cust ids list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findAllAddrOfTCustsByIDS(Set<Integer> custIdsList,Short tenantId);
	
	/**
	 * Find all customers primary addr list.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findAllCustomersPrimaryAddrList(List<Integer> custIds,Short tenantId);
	
	/**
	 * Find addr by addr ids.
	 *
	 * @param addrIds the addr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAddr> findAddrByAddrIds(List<Integer> addrIds,Short tenantId);
	
	/**
	 * Retrieve TCustAlgmnt based on given search criteria.
	 * 
	 * @param criteria
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAddr> findTCustAddrsBySearchCriteria(ISearchCriteria criteria);

}
