package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqTrack;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqTrack DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqTrackDAO {

	/**
	 * Stores a new TChngReqTrack entity object in to the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be persisted
	 * @return tChngReqTrack Persisted TChngReqTrack object
	 */
	TChngReqTrack createTChngReqTrack(TChngReqTrack tChngReqTrack);

	/**
	 * Deletes a TChngReqTrack entity object from the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be deleted
	 */
	void deleteTChngReqTrack(Integer chngReqTrackId);

	/**
	 * Updates a TChngReqTrack entity object in to the persistent store
	 * 
	 * @param tChngReqTrack
	 *            TChngReqTrack Entity object to be updated
	 * @return tChngReqTrack Persisted TChngReqTrack object
	 */
	TChngReqTrack updateTChngReqTrack(TChngReqTrack tChngReqTrack);

	/**
	 * Retrieve an TChngReqTrack object based on given chngReqTrackId.
	 * 
	 * @param chngReqTrackId
	 *            the primary key value of the TChngReqTrack Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqTrack findTChngReqTrackById(Integer chngReqTrackId);

	/**
	 * Retrieve TChngReqTrack based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqTrack> list of TChngReqTracks if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqTrack> findTChngReqTracks(SearchFilter<TChngReqTrack> searchFilter);

	/**
	 * Count TChngReqTrack based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqTracks(SearchFilter<TChngReqTrack> searchFilter);

}
