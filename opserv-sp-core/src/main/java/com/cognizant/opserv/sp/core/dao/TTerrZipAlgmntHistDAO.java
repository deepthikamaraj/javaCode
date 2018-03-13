package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntHist;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntHistId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTerrZipAlgmntHist DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTerrZipAlgmntHistDAO {

	/**
	 * Stores a new TTerrZipAlgmntHist entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be persisted
	 * @return tTerrZipAlgmntHist Persisted TTerrZipAlgmntHist object
	 */
	TTerrZipAlgmntHist createTTerrZipAlgmntHist(TTerrZipAlgmntHist tTerrZipAlgmntHist);

	/**
	 * Deletes a TTerrZipAlgmntHist entity object from the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be deleted
	 */
	void deleteTTerrZipAlgmntHist(TTerrZipAlgmntHistId tTerrZipAlgmntHistId);

	/**
	 * Updates a TTerrZipAlgmntHist entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be updated
	 * @return tTerrZipAlgmntHist Persisted TTerrZipAlgmntHist object
	 */
	TTerrZipAlgmntHist updateTTerrZipAlgmntHist(TTerrZipAlgmntHist tTerrZipAlgmntHist);

	/**
	 * Retrieve an TTerrZipAlgmntHist object based on given TTerrZipAlgmntHistId.
	 * 
	 * @param tTerrZipAlgmntHistId
	 *            the primary key value of the TTerrZipAlgmntHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTerrZipAlgmntHist findTTerrZipAlgmntHistById(TTerrZipAlgmntHistId tTerrZipAlgmntHistId);

	/**
	 * Retrieve TTerrZipAlgmntHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmntHist> list of TTerrZipAlgmntHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTerrZipAlgmntHist> findTTerrZipAlgmntHists(SearchFilter<TTerrZipAlgmntHist> searchFilter);

	/**
	 * Count TTerrZipAlgmntHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTerrZipAlgmntHists(SearchFilter<TTerrZipAlgmntHist> searchFilter);

}
