package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TDsMirror;
import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TDsMirror DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TDsMirrorDAO {

	/**
	 * Stores a new TDsMirror entity object in to the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be persisted
	 * @return tDsMirror Persisted TDsMirror object
	 */
	TDsMirror createTDsMirror(TDsMirror tDsMirror);

	/**
	 * Deletes a TDsMirror entity object from the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be deleted
	 */
	void deleteTDsMirror(Integer dsMirrorId);

	/**
	 * Updates a TDsMirror entity object in to the persistent store
	 * 
	 * @param tDsMirror
	 *            TDsMirror Entity object to be updated
	 * @return tDsMirror Persisted TDsMirror object
	 */
	TDsMirror updateTDsMirror(TDsMirror tDsMirror);

	/**
	 * Retrieve an TDsMirror object based on given dsMirrorId.
	 * 
	 * @param dsMirrorId
	 *            the primary key value of the TDsMirror Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TDsMirror findTDsMirrorById(Integer dsMirrorId);

	/**
	 * Retrieve TDsMirror based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirrors if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsMirror> findTDsMirrors(SearchFilter<TDsMirror> searchFilter);

	/**
	 * Count TDsMirror based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsMirrors(SearchFilter<TDsMirror> searchFilter);

	/**
	 * Retrieve TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirrors if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsMirror> getTDsMirrorsByTDsStgByDsId(SearchFilter<TDsStg> searchFilter);

	/**
	 * Count TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsMirrorsByTDsStgByDsId(SearchFilter<TDsStg> searchFilter);

	/**
	 * Retrieve TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsMirror> list of TDsMirrors if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsMirror> getTDsMirrorsByTDsStgByMirrorDsId(SearchFilter<TDsStg> searchFilter);

	/**
	 * Count TDsMirror based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsMirrorsByTDsStgByMirrorDsId(SearchFilter<TDsStg> searchFilter);

}
