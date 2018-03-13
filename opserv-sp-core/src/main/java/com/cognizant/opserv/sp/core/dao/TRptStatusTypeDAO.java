package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptStatusType;
import com.cognizant.opserv.sp.core.entity.TRptStatusTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptStatusType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptStatusTypeDAO {

	/**
	 * Stores a new TRptStatusType entity object in to the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be persisted
	 * @return tRptStatusType Persisted TRptStatusType object
	 */
	TRptStatusType createTRptStatusType(TRptStatusType tRptStatusType);

	/**
	 * Deletes a TRptStatusType entity object from the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be deleted
	 */
	void deleteTRptStatusType(Integer rptStatusTypeId);

	/**
	 * Updates a TRptStatusType entity object in to the persistent store
	 * 
	 * @param tRptStatusType
	 *            TRptStatusType Entity object to be updated
	 * @return tRptStatusType Persisted TRptStatusType object
	 */
	TRptStatusType updateTRptStatusType(TRptStatusType tRptStatusType);

	/**
	 * Retrieve an TRptStatusType object based on given rptStatusTypeId.
	 * 
	 * @param rptStatusTypeId
	 *            the primary key value of the TRptStatusType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptStatusType findTRptStatusTypeById(TRptStatusTypeId rptStatusTypeId);

	/**
	 * Retrieve TRptStatusType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptStatusType> list of TRptStatusTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptStatusType> findTRptStatusTypes(SearchFilter<TRptStatusType> searchFilter);

	/**
	 * Count TRptStatusType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptStatusTypes(SearchFilter<TRptStatusType> searchFilter);

	TRptStatusType findTRptStatusTypeById(Integer rptStatusTypeId, Short tenantId);

}
