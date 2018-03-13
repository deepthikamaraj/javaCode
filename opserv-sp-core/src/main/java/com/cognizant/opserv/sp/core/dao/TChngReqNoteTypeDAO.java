package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqNoteType;
import com.cognizant.opserv.sp.core.entity.TChngReqNoteTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqNoteType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqNoteTypeDAO {

	/**
	 * Stores a new TChngReqNoteType entity object in to the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be persisted
	 * @return tChngReqNoteType Persisted TChngReqNoteType object
	 */
	TChngReqNoteType createTChngReqNoteType(TChngReqNoteType tChngReqNoteType);

	/**
	 * Deletes a TChngReqNoteType entity object from the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be deleted
	 */
	void deleteTChngReqNoteType(TChngReqNoteTypeId tChngReqNoteTypeId);

	/**
	 * Updates a TChngReqNoteType entity object in to the persistent store
	 * 
	 * @param tChngReqNoteType
	 *            TChngReqNoteType Entity object to be updated
	 * @return tChngReqNoteType Persisted TChngReqNoteType object
	 */
	TChngReqNoteType updateTChngReqNoteType(TChngReqNoteType tChngReqNoteType);

	/**
	 * Retrieve an TChngReqNoteType object based on given TChngReqNoteTypeId.
	 * 
	 * @param tChngReqNoteTypeId
	 *            the primary key value of the TChngReqNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqNoteType findTChngReqNoteTypeById(TChngReqNoteTypeId tChngReqNoteTypeId);

	/**
	 * Retrieve TChngReqNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNoteType> list of TChngReqNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqNoteType> findTChngReqNoteTypes(SearchFilter<TChngReqNoteType> searchFilter);

	/**
	 * Count TChngReqNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqNoteTypes(SearchFilter<TChngReqNoteType> searchFilter);
	
	/**
	 * Fetch note type status.
	 *
	 * @param configId the config id
	 * @param noteTypeId the note type id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> fetchNoteTypeStatus(Integer configId,List<Integer> noteTypeId,Short tenantId);
	
	/**
	 * Find t chng req note type by cr config id.
	 *
	 * @param crConfigId the cr config id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TChngReqNoteType>  findTChngReqNoteTypeByCRConfigId(Integer crConfigId, Short tenantId);

}
