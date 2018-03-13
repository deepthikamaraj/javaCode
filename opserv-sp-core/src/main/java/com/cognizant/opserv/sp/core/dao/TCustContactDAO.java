package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import com.cognizant.opserv.sp.core.entity.TContactType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.core.entity.TCustContactId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustContact DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustContactDAO {

	/**
	 * Stores a new TCustContact entity object in to the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be persisted
	 * @return tCustContact Persisted TCustContact object
	 */
	TCustContact createTCustContact(TCustContact tCustContact);

	/**
	 * Deletes a TCustContact entity object from the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be deleted
	 */
	void deleteTCustContact(TCustContactId tCustContactId);

	/**
	 * Updates a TCustContact entity object in to the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be updated
	 * @return tCustContact Persisted TCustContact object
	 */
	TCustContact updateTCustContact(TCustContact tCustContact);

	/**
	 * Retrieve an TCustContact object based on given TCustContactId.
	 * 
	 * @param tCustContactId
	 *            the primary key value of the TCustContact Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustContact findTCustContactById(TCustContactId tCustContactId);

	/**
	 * Retrieve TCustContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustContact> findTCustContacts(SearchFilter<TCustContact> searchFilter);

	/**
	 * Count TCustContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustContacts(SearchFilter<TCustContact> searchFilter);

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustContact> getTCustContactsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustContactsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TContactType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustContact> getTCustContactsByTContactType(SearchFilter<TContactType> searchFilter);

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TContactType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustContactsByTContactType(SearchFilter<TContactType> searchFilter);

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	List<TCustContact> getTCustContactsByTAddrType(SearchFilter<TAddrType> searchFilter);*/

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
/*	Object countTCustContactsByTAddrType(SearchFilter<TAddrType> searchFilter);*/

}
