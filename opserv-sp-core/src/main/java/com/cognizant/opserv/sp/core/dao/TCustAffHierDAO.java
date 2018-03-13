package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustAffHier;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAff;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAffHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAffHierDAO {

	/**
	 * Stores a new TCustAffHier entity object in to the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be persisted
	 * @return tCustAffHier Persisted TCustAffHier object
	 */
	TCustAffHier createTCustAffHier(TCustAffHier tCustAffHier);

	/**
	 * Deletes a TCustAffHier entity object from the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be deleted
	 */
	void deleteTCustAffHier(Integer affHierId);

	/**
	 * Updates a TCustAffHier entity object in to the persistent store
	 * 
	 * @param tCustAffHier
	 *            TCustAffHier Entity object to be updated
	 * @return tCustAffHier Persisted TCustAffHier object
	 */
	TCustAffHier updateTCustAffHier(TCustAffHier tCustAffHier);

	/**
	 * Retrieve an TCustAffHier object based on given affHierId.
	 * 
	 * @param affHierId
	 *            the primary key value of the TCustAffHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAffHier findTCustAffHierById(Integer affHierId);

	/**
	 * Retrieve TCustAffHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffHier> findTCustAffHiers(SearchFilter<TCustAffHier> searchFilter);

	/**
	 * Count TCustAffHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffHiers(SearchFilter<TCustAffHier> searchFilter);

	/**
	 * Retrieve TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffHier> getTCustAffHiersByTCustAlgmntAffByAffTypeId(SearchFilter<TCustAlgmntAff> searchFilter);

	/**
	 * Count TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffHiersByTCustAlgmntAffByAffTypeId(SearchFilter<TCustAlgmntAff> searchFilter);

	/**
	 * Retrieve TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffHier> list of TCustAffHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAffHier> getTCustAffHiersByTCustAlgmntAffByPrnAffTypeId(SearchFilter<TCustAlgmntAff> searchFilter);

	/**
	 * Count TCustAffHier based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffHiersByTCustAlgmntAffByPrnAffTypeId(SearchFilter<TCustAlgmntAff> searchFilter);

}
