package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptType;
import com.cognizant.opserv.sp.core.entity.TRptTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptTypeDAO {

	/**
	 * Stores a new TRptType entity object in to the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be persisted
	 * @return tRptType Persisted TRptType object
	 */
	TRptType createTRptType(TRptType tRptType);

	/**
	 * Deletes a TRptType entity object from the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be deleted
	 */
	void deleteTRptType(Integer rptTypeId);

	/**
	 * Updates a TRptType entity object in to the persistent store
	 * 
	 * @param tRptType
	 *            TRptType Entity object to be updated
	 * @return tRptType Persisted TRptType object
	 */
	TRptType updateTRptType(TRptType tRptType);

	/**
	 * Retrieve an TRptType object based on given rptTypeId.
	 * 
	 * @param rptTypeId
	 *            the primary key value of the TRptType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptType findTRptTypeById(TRptTypeId rptTypeId);

	/**
	 * Retrieve TRptType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptType> list of TRptTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptType> findTRptTypes(SearchFilter<TRptType> searchFilter);

	/**
	 * Count TRptType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptTypes(SearchFilter<TRptType> searchFilter);

}
