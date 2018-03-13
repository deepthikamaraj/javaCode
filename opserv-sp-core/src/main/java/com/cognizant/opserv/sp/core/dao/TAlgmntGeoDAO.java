package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntGeo;

/**
 * Interface represents API's of TAlgmntGeo DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntGeoDAO {

	/**
	 * Stores a new TAlgmntGeo entity object in to the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be persisted
	 * @return tAlgmntGeo Persisted TAlgmntGeo object
	 */
	TAlgmntGeo createTAlgmntGeo(TAlgmntGeo tAlgmntGeo);

	/**
	 * Deletes a TAlgmntGeo entity object from the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be deleted
	 */
	void deleteTAlgmntGeo(Long geoPosId);

	/**
	 * Updates a TAlgmntGeo entity object in to the persistent store
	 * 
	 * @param tAlgmntGeo
	 *            TAlgmntGeo Entity object to be updated
	 * @return tAlgmntGeo Persisted TAlgmntGeo object
	 */
	TAlgmntGeo updateTAlgmntGeo(TAlgmntGeo tAlgmntGeo);

	/**
	 * Retrieve an TAlgmntGeo object based on given geoPosId.
	 * 
	 * @param geoPosId
	 *            the primary key value of the TAlgmntGeo Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntGeo findTAlgmntGeoById(Long geoPosId);	

	/**
	 * Gets the alignment geo hier ids by al bu st.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the alignment geo hier ids by al bu st
	 */
	List<Long> getAlignmentGeoHierIdsByAlBuSt(Long alId, Long buId, Long stId,Short tenantId, Character flag);

	/**
	 * Checks if is alignment geo hier ids are mapped.
	 *
	 * @param levelIds the level ids
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return true, if is alignment geo hier ids are mapped
	 */
	boolean isAlignmentGeoHierIdsAreMapped(List<Long> levelIds, Short tenantId,	Character flag);

	/**
	 * Gets the all alignment geo hiers.
	 *
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the all alignment geo hiers
	 */
	List<Object[]> getAllAlignmentGeoHiers(Short tenantId, Character flag);

}
