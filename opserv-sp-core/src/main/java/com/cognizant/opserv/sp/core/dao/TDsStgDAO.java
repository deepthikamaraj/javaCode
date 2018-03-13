package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TDsStatus;
import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TDsStg DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TDsStgDAO {

	/**
	 * Stores a new TDsStg entity object in to the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be persisted
	 * @return tDsStg Persisted TDsStg object
	 */
	TDsStg createTDsStg(TDsStg tDsStg);

	/**
	 * Deletes a TDsStg entity object from the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be deleted
	 */
	void deleteTDsStg(Long dsId);

	/**
	 * Updates a TDsStg entity object in to the persistent store
	 * 
	 * @param tDsStg
	 *            TDsStg Entity object to be updated
	 * @return tDsStg Persisted TDsStg object
	 */
	TDsStg updateTDsStg(TDsStg tDsStg);

	/**
	 * Retrieve an TDsStg object based on given dsId.
	 * 
	 * @param dsId
	 *            the primary key value of the TDsStg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TDsStg findTDsStgById(Long dsId);

	/**
	 * Retrieve TDsStg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStg> list of TDsStgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsStg> findTDsStgs(SearchFilter<TDsStg> searchFilter);

	/**
	 * Count TDsStg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsStgs(SearchFilter<TDsStg> searchFilter);

	/**
	 * Retrieve TDsStg based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStg> list of TDsStgs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TDsStg> getTDsStgsByTDsStatus(SearchFilter<TDsStatus> searchFilter);

	/**
	 * Count TDsStg based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTDsStgsByTDsStatus(SearchFilter<TDsStatus> searchFilter);
	
	/**
	 * Find t ds stgs by name.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TDsStg> findTDsStgsByName(SearchFilter<TDsStg> searchFilter);
	
	/*List<TDsStg> findTDsStgByTenantIdAndDSStatus(final SearchFilter<TDsStg> searchFilter);*/
	/**
	 * Find t ds stg by tenant id and ds status.
	 *
	 * @param tenantId the tenant id
	 * @param statusIdList the status id list
	 * @return the list
	 */
	List<TDsStg> findTDsStgByTenantIdAndDSStatus(short tenantId, List<Integer> statusIdList);
	
	/**
	 * Find t ds stgs created aftr date.
	 *
	 * @param searchFilter the search filter
	 * @param beforeDate the before date
	 * @param createDate the create date
	 * @return the list
	 */
	List<TDsStg> findTDsStgsCreatedAftrDate(final SearchFilter<TDsStg> searchFilter, Date beforeDate, Date createDate);
	
	/**
	 * Find t ds stgs by ds name.
	 *
	 * @param entityName the entity name
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TDsStg> findTDsStgsByDSName(final String entityName, Short tenantId);
	
	/**
	 * Gets the active data source.
	 *
	 * @param tenantId the tenant id
	 * @return the active data source
	 */
	List<Object[]> getActiveDataSource(Short tenantId);

/* Added by 407986 For Call Plan Load */
	/**
	 * Update buss function for call plan.
	 *
	 * @param bussFun the buss fun
	 * @param tableName the table name
	 * @param tenantId the tenant id
	 */
	void updateBussFunctionForCallPlan(String bussFun, String tableName, Short tenantId);
}
