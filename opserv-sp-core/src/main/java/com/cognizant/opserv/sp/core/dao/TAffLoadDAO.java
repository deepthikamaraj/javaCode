package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAffLoad;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAffLoad DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAffLoadDAO {

	/**
	 * Stores a new TAffLoad entity object in to the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be persisted
	 * @return tAffLoad Persisted TAffLoad object
	 */
	TAffLoad createTAffLoad(TAffLoad tAffLoad);

	/**
	 * Deletes a TAffLoad entity object from the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be deleted
	 */
	void deleteTAffLoad(Long affId);

	/**
	 * Updates a TAffLoad entity object in to the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be updated
	 * @return tAffLoad Persisted TAffLoad object
	 */
	TAffLoad updateTAffLoad(TAffLoad tAffLoad);

	/**
	 * Retrieve an TAffLoad object based on given affId.
	 * 
	 * @param affId
	 *            the primary key value of the TAffLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAffLoad findTAffLoadById(Long affId);

	/**
	 * Retrieve TAffLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffLoad> list of TAffLoads if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAffLoad> findTAffLoads(SearchFilter<TAffLoad> searchFilter);

	/**
	 * Count TAffLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAffLoads(SearchFilter<TAffLoad> searchFilter);

	/**
	 * Gets the affiliated cust ids by cust id.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the affiliated cust ids by cust id
	 */
	List<Object[]> getAffiliatedCustIdsByCustId(Integer custId, Short tenantId,Character flag);

	/**
	 * Gets the child affiliated cust ids by cust id.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the child affiliated cust ids by cust id
	 */
	List<Object[]> getChildAffiliatedCustIdsByCustId(Integer custId,Short tenantId, Character flag);
	
	/**
	 * Check t cust aff heir fr cust.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> checkTCustAffHeirFrCust(List<Integer> custIdList,  Short tenantId);

	/**
	 * Inactivate aff customers.
	 *
	 * @param tempTableName the temp table name
	 * @param procedureName the procedure name
	 * @return the string
	 */
	String inactivateAffCustomers(String tempTableName,String procedureName);

	/**
	 * Creates the or drop temp table.
	 *
	 * @param tableInfo the table info
	 * @return the integer
	 */
	Integer createOrDropTempTable(String tableInfo);

}
