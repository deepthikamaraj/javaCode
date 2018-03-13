package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAppConfig;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAppConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAppConfigDAO {

	/**
	 * Stores a new TAppConfig entity object in to the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be persisted
	 * @return tAppConfig Persisted TAppConfig object
	 */
	TAppConfig createTAppConfig(TAppConfig tAppConfig);

	/**
	 * Deletes a TAppConfig entity object from the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be deleted
	 */
	void deleteTAppConfig(Integer orgId);

	/**
	 * Updates a TAppConfig entity object in to the persistent store
	 * 
	 * @param tAppConfig
	 *            TAppConfig Entity object to be updated
	 * @return tAppConfig Persisted TAppConfig object
	 */
	TAppConfig updateTAppConfig(TAppConfig tAppConfig);

	/**
	 * Retrieve an TAppConfig object based on given orgId.
	 * 
	 * @param orgId
	 *            the primary key value of the TAppConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAppConfig findTAppConfigById(Integer orgId);

	/**
	 * Retrieve TAppConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAppConfig> list of TAppConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAppConfig> findTAppConfigs(SearchFilter<TAppConfig> searchFilter);

	/**
	 * Count TAppConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAppConfigs(SearchFilter<TAppConfig> searchFilter);

	/**
	 * Retrieve TAppConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAppConfig> list of TAppConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAppConfig> getTAppConfigsByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Count TAppConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAppConfigsByTOrg(SearchFilter<TOrg> searchFilter);

	/**
	 * Gets the default country id.
	 *
	 * @param tenantId the tenant id
	 * @return the default country id
	 */
	List<TAppConfig> getDefaultCountryID(Short tenantId);

}
