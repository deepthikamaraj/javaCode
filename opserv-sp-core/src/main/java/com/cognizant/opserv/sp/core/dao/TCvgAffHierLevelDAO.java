package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCvgAffHierLevel;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCvgAffHierLevel DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCvgAffHierLevelDAO {

	/**
	 * Stores a new TCvgAffHierLevel entity object in to the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be persisted
	 * @return tCvgAffHierLevel Persisted TCvgAffHierLevel object
	 */
	TCvgAffHierLevel createTCvgAffHierLevel(TCvgAffHierLevel tCvgAffHierLevel);

	/**
	 * Deletes a TCvgAffHierLevel entity object from the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be deleted
	 */
	//void deleteTCvgAffHierLevel(TCvgAffHierLevelId tCvgAffHierLevelId);

	/**
	 * Updates a TCvgAffHierLevel entity object in to the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be updated
	 * @return tCvgAffHierLevel Persisted TCvgAffHierLevel object
	 */
	TCvgAffHierLevel updateTCvgAffHierLevel(TCvgAffHierLevel tCvgAffHierLevel);

	/**
	 * Retrieve an TCvgAffHierLevel object based on given TCvgAffHierLevelId.
	 * 
	 * @param tCvgAffHierLevelId
	 *            the primary key value of the TCvgAffHierLevel Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	//TCvgAffHierLevel findTCvgAffHierLevelById(TCvgAffHierLevelId tCvgAffHierLevelId);

	/**
	 * Retrieve TCvgAffHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgAffHierLevel> list of TCvgAffHierLevels if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgAffHierLevel> findTCvgAffHierLevels(SearchFilter<TCvgAffHierLevel> searchFilter);

	/**
	 * Count TCvgAffHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCvgAffHierLevels(SearchFilter<TCvgAffHierLevel> searchFilter);
	
	/**
	 * findTCvgAffHierLevelsByTntIdLocaleId based on given tenant Id and localeId
	 * @param tenantId
	 * @param localeId
	 * @return List<TCvgAffHierLevel> list of TCvgAffHierLevels if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCvgAffHierLevel> findTCvgAffHierLevelsByTntIdLocaleId(Short tenantId,String localeId);

}
