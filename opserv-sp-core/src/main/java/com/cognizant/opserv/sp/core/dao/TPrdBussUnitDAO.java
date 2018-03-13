package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TPrdBussUnit;
import com.cognizant.opserv.sp.core.entity.TPrdBussUnitId;
import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdBussUnit DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdBussUnitDAO {

	/**
	 * Stores a new TPrdBussUnit entity object in to the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be persisted
	 * @return tPrdBussUnit Persisted TPrdBussUnit object
	 */
	TPrdBussUnit createTPrdBussUnit(TPrdBussUnit tPrdBussUnit);

	/**
	 * Deletes a TPrdBussUnit entity object from the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be deleted
	 */
	void deleteTPrdBussUnit(TPrdBussUnitId tPrdBussUnitId);

	/**
	 * Updates a TPrdBussUnit entity object in to the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be updated
	 * @return tPrdBussUnit Persisted TPrdBussUnit object
	 */
	TPrdBussUnit updateTPrdBussUnit(TPrdBussUnit tPrdBussUnit);

	/**
	 * Retrieve an TPrdBussUnit object based on given TPrdBussUnitId.
	 * 
	 * @param tPrdBussUnitId
	 *            the primary key value of the TPrdBussUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdBussUnit findTPrdBussUnitById(TPrdBussUnitId tPrdBussUnitId);

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdBussUnit> findTPrdBussUnits(SearchFilter<TPrdBussUnit> searchFilter);

	/**
	 * Count TPrdBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdBussUnits(SearchFilter<TPrdBussUnit> searchFilter);

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdBussUnit> getTPrdBussUnitsByTPrdConfig(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Count TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdBussUnitsByTPrdConfig(SearchFilter<TPrdConfig> searchFilter);

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdBussUnit> getTPrdBussUnitsByTBussUnit(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Count TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdBussUnitsByTBussUnit(SearchFilter<TBussUnit> searchFilter);

}
