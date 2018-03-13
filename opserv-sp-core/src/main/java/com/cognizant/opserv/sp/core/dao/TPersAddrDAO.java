package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.core.entity.TPersAddrId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPersAddr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPersAddrDAO {

	/**
	 * Stores a new TPersAddr entity object in to the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be persisted
	 * @return tPersAddr Persisted TPersAddr object
	 */
	TPersAddr createTPersAddr(TPersAddr tPersAddr);

	/**
	 * Deletes a TPersAddr entity object from the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be deleted
	 */
	void deleteTPersAddr(TPersAddrId tPersAddrId);

	/**
	 * Updates a TPersAddr entity object in to the persistent store
	 * 
	 * @param tPersAddr
	 *            TPersAddr Entity object to be updated
	 * @return tPersAddr Persisted TPersAddr object
	 */
	TPersAddr updateTPersAddr(TPersAddr tPersAddr);

	/**
	 * Retrieve an TPersAddr object based on given TPersAddrId.
	 * 
	 * @param tPersAddrId
	 *            the primary key value of the TPersAddr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPersAddr findTPersAddrById(TPersAddrId tPersAddrId);

	/**
	 * Retrieve TPersAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersAddr> list of TPersAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPersAddr> findTPersAddrs(SearchFilter<TPersAddr> searchFilter);

	/**
	 * Count TPersAddr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPersAddrs(SearchFilter<TPersAddr> searchFilter);

	/**
	 * Retrieve TPersAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersAddr> list of TPersAddrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPersAddr> getTPersAddrsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TPersAddr based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPersAddrsByTPers(SearchFilter<TPers> searchFilter);
	
	TPersAddr findTPersAddrById(int tPersAddrId,int staffId , short tentent);

	List<TPersAddr> findPriAddressForEmp(List<Integer> staffId, Short tenantId,
			String prAddrFlag, Character activeFlag);
	/**
	 * @param staffId
	 * @param tenantId
	 * @param locale
	 * @return list of object[] with each object[] containing staffId,addrId,addrTypeId,prAddrFlag,doorNumber,streetName,addrLine,city,
	 * state,country,postalCd,addrTypeDesc in that order,null if no addresses found
	 */
	List<Object[]> findAddressesForEmp(Integer staffId,Short tenantId,String locale);

}
