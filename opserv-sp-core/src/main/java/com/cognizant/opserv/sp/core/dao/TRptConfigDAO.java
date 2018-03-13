package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TRptCategory;
import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptConfigStatus;
import com.cognizant.opserv.sp.core.entity.TRptType;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TRptConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TRptConfigDAO {

	/**
	 * Stores a new TRptConfig entity object in to the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be persisted
	 * @return tRptConfig Persisted TRptConfig object
	 */
	TRptConfig createTRptConfig(TRptConfig tRptConfig);

	/**
	 * Deletes a TRptConfig entity object from the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be deleted
	 */
	void deleteTRptConfig(Integer rptConfigId);

	/**
	 * Updates a TRptConfig entity object in to the persistent store
	 * 
	 * @param tRptConfig
	 *            TRptConfig Entity object to be updated
	 * @return tRptConfig Persisted TRptConfig object
	 */
	TRptConfig updateTRptConfig(TRptConfig tRptConfig);

	/**
	 * Retrieve an TRptConfig object based on given rptConfigId.
	 * 
	 * @param rptConfigId
	 *            the primary key value of the TRptConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TRptConfig findTRptConfigById(Integer rptConfigId);

	/**
	 * Retrieve TRptConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfig> findTRptConfigs(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Count TRptConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigs(SearchFilter<TRptConfig> searchFilter);

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfig> getTRptConfigsByTRptCategory(SearchFilter<TRptCategory> searchFilter);

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptCategory type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigsByTRptCategory(SearchFilter<TRptCategory> searchFilter);

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfig> getTRptConfigsByTRptType(SearchFilter<TRptType> searchFilter);

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigsByTRptType(SearchFilter<TRptType> searchFilter);

	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfig> getTRptConfigsByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfigStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigsByTRptConfigStatus(SearchFilter<TRptConfigStatus> searchFilter);
	
	//added for recipientPrefId
	/**
	 * Retrieve TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptConfig> list of TRptConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TRptConfig> getTRptConfigsByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	/**
	 * Count TRptConfig based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTRptConfigsByTTargetRecipientPref(SearchFilter<TTargetRecipientPref> searchFilter);

	TRptConfig findTRptConfigById(Integer rptConfigId, Short tenantId);

	List<TRptConfig> findTRptConfigByTRptTypeAndConfigStatus(Integer reportTypeId, Integer statusTypeId, Short tenantId);


}
