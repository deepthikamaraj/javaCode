package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TNoteType;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigTNoteType;
import com.cognizant.opserv.sp.core.entity.TRptConfigTNoteTypeId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptConfigTNoteType DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptConfigTNoteTypeDAO {

	/**
	 * Stores a new TRptConfigTNoteType entity object in to the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be persisted
	 * @return tRptConfigTNoteType Persisted TRptConfigTNoteType object
	 */
	TRptConfigTNoteType createTRptConfigTNoteType(TRptConfigTNoteType tRptConfigTNoteType);

	/**
	 * Deletes a TRptConfigTNoteType entity object from the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be deleted
	 */
	void deleteTRptConfigTNoteType(TRptConfigTNoteTypeId tRptConfigTNoteTypeId);

	/**
	 * Updates a TRptConfigTNoteType entity object in to the persistent store
	 * 
	 * @param tRptConfigTNoteType
	 *            TRptConfigTNoteType Entity object to be updated
	 * @return tRptConfigTNoteType Persisted TRptConfigTNoteType object
	 */
	TRptConfigTNoteType updateTRptConfigTNoteType(TRptConfigTNoteType tRptConfigTNoteType);

	/**
	 * Retrieve an TRptConfigTNoteType object based on given TRptConfigTNoteTypeId.
	 * 
	 * @param tRptConfigTNoteTypeId
	 *            the primary key value of the TRptConfigTNoteType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptConfigTNoteType findTRptConfigTNoteTypeById(TRptConfigTNoteTypeId tRptConfigTNoteTypeId);

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTNoteType> findTRptConfigTNoteTypes(SearchFilter<TRptConfigTNoteType> searchFilter);

	/**
	 * Count TRptConfigTNoteType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTNoteTypes(SearchFilter<TRptConfigTNoteType> searchFilter);

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTNoteType> getTRptConfigTNoteTypesByTNoteType(SearchFilter<TNoteType> searchFilter);

	/**
	 * Count TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TNoteType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTNoteTypesByTNoteType(SearchFilter<TNoteType> searchFilter);

	/**
	 * Retrieve TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfigTNoteType> list of TRptConfigTNoteTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfigTNoteType> getTRptConfigTNoteTypesByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptConfigTNoteType based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigTNoteTypesByTRptConfig(SearchFilter<TRptConfig> searchFilter);

}
