package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAckStatus;
import com.cognizant.opserv.sp.core.entity.TAckStatusId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAckStatus DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAckStatusDAO {

	/**
	 * Stores a new TAckStatus entity object in to the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be persisted
	 * @return tAckStatus Persisted TAckStatus object
	 */
	TAckStatus createTAckStatus(TAckStatus tAckStatus);

	/**
	 * Deletes a TAckStatus entity object from the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be deleted
	 */
	void deleteTAckStatus(Integer ackStatusId);

	/**
	 * Updates a TAckStatus entity object in to the persistent store
	 * 
	 * @param tAckStatus
	 *            TAckStatus Entity object to be updated
	 * @return tAckStatus Persisted TAckStatus object
	 */
	TAckStatus updateTAckStatus(TAckStatus tAckStatus);

	/**
	 * Retrieve an TAckStatus object based on given ackStatusId.
	 * 
	 * @param ackStatusId
	 *            the primary key value of the TAckStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAckStatus findTAckStatusById(TAckStatusId ackStatusId);

	/**
	 * Retrieve TAckStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAckStatus> list of TAckStatuss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAckStatus> findTAckStatuss(SearchFilter<TAckStatus> searchFilter);

	/**
	 * Count TAckStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAckStatuss(SearchFilter<TAckStatus> searchFilter);
	
	List<Object[]> findTAckStatusname(Integer ackStatusId,String localeId);

	List<TAckStatus> getAllAckStatus();
	
	List<Object[]> findAllActiveTAckStatus(String localeId, Short tenantId);
}
