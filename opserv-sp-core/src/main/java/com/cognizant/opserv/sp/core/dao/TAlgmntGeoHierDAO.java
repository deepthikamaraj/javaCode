package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntGeoHier;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntGeoHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntGeoHierDAO {

	/**
	 * Stores a new TAlgmntGeoHier entity object in to the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be persisted
	 * @return tAlgmntGeoHier Persisted TAlgmntGeoHier object
	 */
	TAlgmntGeoHier createTAlgmntGeoHier(TAlgmntGeoHier tAlgmntGeoHier);

	/**
	 * Deletes a TAlgmntGeoHier entity object from the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be deleted
	 */
	void deleteTAlgmntGeoHier(Long geoHierId);

	/**
	 * Updates a TAlgmntGeoHier entity object in to the persistent store
	 * 
	 * @param tAlgmntGeoHier
	 *            TAlgmntGeoHier Entity object to be updated
	 * @return tAlgmntGeoHier Persisted TAlgmntGeoHier object
	 */
	TAlgmntGeoHier updateTAlgmntGeoHier(TAlgmntGeoHier tAlgmntGeoHier);

	/**
	 * Retrieve an TAlgmntGeoHier object based on given geoHierId.
	 * 
	 * @param geoHierId
	 *            the primary key value of the TAlgmntGeoHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntGeoHier findTAlgmntGeoHierById(Long geoHierId);

	/**
	 * Retrieve TAlgmntGeoHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntGeoHier> list of TAlgmntGeoHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntGeoHier> findTAlgmntGeoHiers(SearchFilter<TAlgmntGeoHier> searchFilter);

	/**
	 * Count TAlgmntGeoHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntGeoHiers(SearchFilter<TAlgmntGeoHier> searchFilter);

	/**
	 * Inactivate alignment levels.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 */
	void inactivateAlignmentLevels(Long alId, Long buId, Long stId,	Short tenantId, Character flag);

	/**
	 * Checks if is geo level mapped to alignments.
	 *
	 * @param geoHierId the geo hier id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return true, if is geo level mapped to alignments
	 */
	boolean isGeoLevelMappedToAlignments(Integer geoHierId, Short tenantId,	Character flag);

	/**
	 * Gets the child level by level id.
	 *
	 * @param parentLevelId the parent level id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the child level by level id
	 */
	List<Long> getChildLevelByLevelId(Long parentLevelId, Short tenantId,Character flag);

	/**
	 * Gets the selected alignment levels.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the selected alignment levels
	 */
	List<Object[]> getSelectedAlignmentLevels(Long alId, Long buId, Long stId,Short tenantId, Character flag);

	/**
	 * Gets the all alignment levels.
	 *
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the all alignment levels
	 */
	List<Object[]> getAllAlignmentLevels(Short tenantId, Character flag);


}
