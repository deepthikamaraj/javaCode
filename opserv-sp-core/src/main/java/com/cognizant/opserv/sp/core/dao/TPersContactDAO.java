package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersContact;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPersContact DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPersContactDAO {

	/**
	 * Stores a new TPersContact entity object in to the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be persisted
	 * @return tPersContact Persisted TPersContact object
	 */
	TPersContact createTPersContact(TPersContact tPersContact);

	/**
	 * Deletes a TPersContact entity object from the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be deleted
	 */
	//void deleteTPersContact(TPersContactId tPersContactId);

	/**
	 * Updates a TPersContact entity object in to the persistent store
	 * 
	 * @param tPersContact
	 *            TPersContact Entity object to be updated
	 * @return tPersContact Persisted TPersContact object
	 */
	TPersContact updateTPersContact(TPersContact tPersContact);

	/**
	 * Retrieve an TPersContact object based on given TPersContactId.
	 * 
	 * @param tPersContactId
	 *            the primary key value of the TPersContact Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPersContact findTPersContactById(Integer persContactId);

	/**
	 * Retrieve TPersContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersContact> list of TPersContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPersContact> findTPersContacts(SearchFilter<TPersContact> searchFilter);

	/**
	 * Count TPersContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPersContacts(SearchFilter<TPersContact> searchFilter);

	/**
	 * Retrieve TPersContact based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPersContact> list of TPersContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPersContact> getTPersContactsByTPers(SearchFilter<TPers> searchFilter);

	/**
	 * Count TPersContact based on given search criteria using JPA named Query.
	 * The search criteria is of TPers type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPersContactsByTPers(SearchFilter<TPers> searchFilter);

	
	List<Integer> findMaxTPersContact();
	
	List<TPersContact> getTPersContactsByTPersusingStaffId(SearchFilter<TPersContact> searchFilter);
	
	List<TPersContact> findTpersContactByStaffId(Integer contactTypeId, String prConatactFlag, Character flag, Integer StaffId,Short tenantId);
	
	List<TPersContact> getTPersContactsByStaffId(Integer staffId,Short tenantId);
	
	
	/**
	 * @param staffId
	 * @return list of object[] with each object array containing contactType,contactDetail,contactFlag,persContactId in that order,null if no contact details found
	 */
	List<Object[]> getTPersContactDetailsByStaffId(Integer staffId);
	
	List<TPersContact> getTPersContactsByStaffIds(Set<Integer>staffIdList,Short tenantId);
	
	/**
	 * Find t pers by email.
	 *
	 * @param staffId the staff id
	 * @param contactTypeId the contact type id
	 * @return the list
	 */
	List <String> FindTPersEmailByStaffId(List<Integer> staffId,Integer contactTypeId);
}
