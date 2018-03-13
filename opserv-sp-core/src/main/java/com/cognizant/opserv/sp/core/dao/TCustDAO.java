package com.cognizant.opserv.sp.core.dao;
import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustCategory;
import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.opserv.sp.core.entity.TPrtType;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.BusinessException;

/**
 * Interface represents API's of TCust DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustDAO {

	/**
	 * Stores a new TCust entity object in to the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be persisted
	 * @return tCust Persisted TCust object
	 */
	TCust createTCust(TCust tCust);

	/**
	 * Deletes a TCust entity object from the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be deleted
	 */
	void deleteTCust(Integer custId);

	/**
	 * Updates a TCust entity object in to the persistent store
	 * 
	 * @param tCust
	 *            TCust Entity object to be updated
	 * @return tCust Persisted TCust object
	 */
	TCust updateTCust(TCust tCust);

	/**
	 * Retrieve an TCust object based on given custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCust Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCust findTCustById(Integer custId);

	/**
	 * Retrieve TCust based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCust> findTCusts(SearchFilter<TCust> searchFilter);
	
	/**
	 * Retrieve TCust based on given search criteria.
	 * 
	 * @param criteria
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCust> findTCustsBySearchCriteria(ISearchCriteria criteria);

	/**
	 * Count TCust based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCusts(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCust> getTCustsByTCustType(SearchFilter<TCustType> searchFilter);

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustsByTCustType(SearchFilter<TCustType> searchFilter);

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCust> getTCustsByTPrtType(SearchFilter<TPrtType> searchFilter);

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustsByTPrtType(SearchFilter<TPrtType> searchFilter);

	/**
	 * Retrieve TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCust> list of TCusts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCust> getTCustsByTCustCategory(SearchFilter<TCustCategory> searchFilter);

	/**
	 * Count TCust based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustsByTCustCategory(SearchFilter<TCustCategory> searchFilter);

	/**
	 * Gets the cust ids.
	 *
	 * @param query the query
	 * @return the cust ids
	 */
	List<Integer> getCustIds(String query);
	
	/**
	 * Find customers by zip.
	 *
	 * @param zipCode the zip code
	 * @return the list
	 */
	List<Object> findCustomersByZip(String zipCode);

	 /**
 	 * Gets the t prt type from cid.
 	 *
 	 * @param cid the cid
 	 * @return the t prt type from cid
 	 */
 	TPrtType getTPrtTypeFromCID(Integer cid);
	
	/**
	 * Gets the t cust type from cust id.
	 *
	 * @param cid the cid
	 * @return the t cust type from cust id
	 */
	TCustType getTCustTypeFromCustId(Integer cid);
	
	/**
	 * Gets the t cust address from cust id.
	 *
	 * @param cid the cid
	 * @return the t cust address from cust id
	 */
	TCustAddr getTCustAddressFromCustId(Integer cid);
	
	/**
	 * Gets the all cust with cust type desc.
	 *
	 * @param custDesc the cust desc
	 * @param start the start
	 * @param results the results
	 * @return the all cust with cust type desc
	 */
	List<TCust> getAllCustWithCustTypeDesc(String custDesc,int start,int results);
	
	/**
	 * Gets the all cust with prt desc.
	 *
	 * @param prtDesc the prt desc
	 * @param start the start
	 * @param results the results
	 * @return the all cust with prt desc
	 */
	List<TCust> getAllCustWithPrtDesc(String prtDesc,int start,int results);
	
	/**
	 * Gets the all customers by postal cd.
	 *
	 * @param postalCd the postal cd
	 * @param start the start
	 * @param results the results
	 * @return the all customers by postal cd
	 */
	List<TCust> getAllCustomersByPostalCD(String postalCd,int start,int results);
	
	/**
	 * Gets the all customers by city.
	 *
	 * @param city the city
	 * @param start the start
	 * @param results the results
	 * @return the all customers by city
	 */
	List<TCust> getAllCustomersByCity(String city,int start,int results);
	
	/**
	 * Findrecently added customer.
	 *
	 * @param algId the alg id
	 * @param bussId the buss id
	 * @param salesTeamId the sales team id
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @param currentDate the current date
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findrecentlyAddedCustomer(Long algId, Long bussId, Long salesTeamId, Long salesPosId, Long salesHierId, Short tenantId, Character flag, Date currentDate, String localeId);
	  //List<TCust> findrecentlyAddedCustomer(Set<Long> childsp , Long alignmentId, Long bussinessUnitId, Long salesTeamId,	Short tenantId, Date currentDate, String localeId, List<Integer> crStatustList);
	
	/**
  	 * Find customers.
  	 *
  	 * @param algId the alg id
  	 * @param bussId the buss id
  	 * @param salesTeamId the sales team id
  	 * @param salesPosId the sales pos id
  	 * @param salesHierId the sales hier id
  	 * @param tenantId the tenant id
  	 * @param flag the flag
  	 * @param custName the cust name
  	 * @return the list
  	 */
  	List<TCust> findCustomers(Long algId, Long bussId, Long salesTeamId, Long salesPosId, Long salesHierId, Short tenantId, Character flag, String custName);
	
	 /**
 	 * Gets the all t prt type.
 	 *
 	 * @return the all t prt type
 	 */
 	List<TPrtType> getAllTPrtType();
	 
	 /**
 	 * Find customers by tentent id.
 	 *
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
 	List<TCust> findCustomersByTententID(Short tenantId);

	 /**
 	 * Find customers count.
 	 *
 	 * @param filter the filter
 	 * @param tenantId the tenant id
 	 * @return the long
 	 */
 	Long findCustomersCount(SearchFilter<TCust> filter, Short tenantId);

	/**
	 * Find customers list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	List<TCust> findCustomersList(SearchFilter<TCust> filter, Short tenantId,int start,int size);
	
	/**
	 * Gets the t cust type from cust id fr gis.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the t cust type from cust id fr gis
	 */
	TCustType getTCustTypeFromCustIdFrGIS(Integer cid, Short tenantId, String localeId);
		
	/**
	 * Find all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @return the list
	 */
	List<TCust> findAllTCustsByIDS(List<Integer> custIds);

	/**
	 * Builds the query from where clause.
	 *
	 * @param code the code
	 * @param zip the zip
	 * @param city the city
	 * @param name the name
	 * @param priority the priority
	 * @param type the type
	 * @param start the start
	 * @param results the results
	 * @param tenantId the tenant id
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	List<TCust> buildQueryFromWhereClause(String code,
			String zip, String city, String name,
			List<Integer> priority, Integer type, int start,
			int results, Short tenantId) throws BusinessException;

	/**
	 * Find all t custtypes for all cid.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustType> findAllTCusttypesForAllCid(List<Integer> cid, Short tenantId);

	/**
	 * Find all t prt types for all cid.
	 *
	 * @param cid the cid
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TPrtType> findAllTPrtTypesForAllCid(List<Integer> cid, Short tenantId);

	/**
	 * Gets the t prt type from cid for tier.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @return the t prt type from cid for tier
	 */
	TPrtType getTPrtTypeFromCIDForTier(Long custAlgmntId);

	/**
	 * Gets the customer by cust code.
	 *
	 * @param tenantId the tenant id
	 * @param custCode the cust code
	 * @return the customer by cust code
	 */
	TCust getCustomerByCustCode(Short tenantId, String custCode);

	/**
	 * Gets the cust data.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the cust data
	 */
	List<Object[]> getCustData(String localeId, Integer custId, Short tenantId);
	
	/**
	 * Gets the cust basic data.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @return the cust basic data
	 */
	List<Object[]> getCustBasicData(String localeId, Integer custId, Short tenantId);

	/**
	 * Gets the customers basic data.
	 *
	 * @param localeId the locale id
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the customers basic data
	 */
	List<Object[]> getCustomersBasicData(String localeId,
			List<Integer> custIds, Short tenantId);

	/**
	 * Find names of all t custs by ids.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findNamesOfAllTCustsByIDS(List<Integer> custIds,
			Short tenantId);
	
	/**
	 * Update t custs.
	 *
	 * @param tCusts the t custs
	 * @return the list
	 */
	List<TCust> updateTCusts(List<TCust> tCusts);
	
	/**
	 * Gets the customer by cust code count.
	 *
	 * @param tenantId the tenant id
	 * @param custCode the cust code
	 * @return the customer by cust code count
	 */
	Integer getCustomerByCustCodeCount(Short tenantId,String custCode) ;

	/**
	 * Find req customers list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	List<Object[]> findReqCustomersList(SearchFilter<TCust> filter,
			Short tenantId, int start, int size);

	/**
	 * Find custs list.
	 *
	 * @param filter the filter
	 * @param tenantId the tenant id
	 * @param start the start
	 * @param size the size
	 * @return the list
	 */
	List<Object[]> findCustsList(SearchFilter<TCust> filter, Short tenantId,
			int start, int size);
	
	/**
	 * Gets the customer count by criteria.
	 *
	 * @param criteria the criteria
	 * @param userDetails the user details
	 * @return the customer count by criteria
	 */
	Long getCustomerCountByCriteria (ISearchCriteria criteria, Short tenantId);

	/**
	 * Fetch cust names fr cust ids.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<String> fetchCustNameFrCustIds(List<Integer> custIdList, Short tenantId);

}
