package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptDataSource;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptDataSource DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptDataSourceDAO {

	/**
	 * Stores a new TRptDataSource entity object in to the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be persisted
	 * @return tRptDataSource Persisted TRptDataSource object
	 */
	TRptDataSource createTRptDataSource(TRptDataSource tRptDataSource);

	/**
	 * Deletes a TRptDataSource entity object from the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be deleted
	 */
	void deleteTRptDataSource(Integer tRptDataSourceId);

	/**
	 * Updates a TRptDataSource entity object in to the persistent store
	 * 
	 * @param tRptDataSource
	 *            TRptDataSource Entity object to be updated
	 * @return tRptDataSource Persisted TRptDataSource object
	 */
	TRptDataSource updateTRptDataSource(TRptDataSource tRptDataSource);

	/**
	 * Retrieve an TRptDataSource object based on given TRptDataSourceId.
	 * 
	 * @param tRptDataSourceId
	 *            the primary key value of the TRptDataSource Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptDataSource findTRptDataSourceById(Integer tRptDataSourceId);

	/**
	 * Retrieve TRptDataSource based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDataSource> list of TRptDataSources if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptDataSource> findTRptDataSources(SearchFilter<TRptDataSource> searchFilter);

	/**
	 * Count TRptDataSource based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptDataSources(SearchFilter<TRptDataSource> searchFilter);

	/**
	 * Retrieve TRptDataSource based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDataSource> list of TRptDataSources if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptDataSource> getTRptDataSourcesByTRptConfig(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptDataSource based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptDataSourcesByTRptConfig(SearchFilter<TRptConfig> searchFilter);

}
