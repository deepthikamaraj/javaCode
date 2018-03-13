package com.cognizant.opserv.sp.core.dao;



import java.util.List;

import com.cognizant.opserv.sp.core.entity.TChngReqUsrNoteConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqUsrNoteConfigId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqUsrNoteConfig DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqUsrNoteConfigDAO {

	/**
	 * Stores a new TChngReqUsrNoteConfig entity object in to the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be persisted
	 * @return tChngReqUsrNoteConfig Persisted TChngReqUsrNoteConfig object
	 */
	TChngReqUsrNoteConfig createTChngReqUsrNoteConfig(TChngReqUsrNoteConfig tChngReqUsrNoteConfig);

	/**
	 * Deletes a TChngReqUsrNoteConfig entity object from the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be deleted
	 */
	void deleteTChngReqUsrNoteConfig(TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId);

	/**
	 * Updates a TChngReqUsrNoteConfig entity object in to the persistent store
	 * 
	 * @param tChngReqUsrNoteConfig
	 *            TChngReqUsrNoteConfig Entity object to be updated
	 * @return tChngReqUsrNoteConfig Persisted TChngReqUsrNoteConfig object
	 */
	TChngReqUsrNoteConfig updateTChngReqUsrNoteConfig(TChngReqUsrNoteConfig tChngReqUsrNoteConfig);

	/**
	 * Retrieve an TChngReqUsrNoteConfig object based on given TChngReqUsrNoteConfigId.
	 * 
	 * @param tChngReqUsrNoteConfigId
	 *            the primary key value of the TChngReqUsrNoteConfig Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqUsrNoteConfig findTChngReqUsrNoteConfigById(TChngReqUsrNoteConfigId tChngReqUsrNoteConfigId);

	/**
	 * Retrieve TChngReqUsrNoteConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqUsrNoteConfig> list of TChngReqUsrNoteConfigs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReqUsrNoteConfig> findTChngReqUsrNoteConfigs(SearchFilter<TChngReqUsrNoteConfig> searchFilter);

	/**
	 * Count TChngReqUsrNoteConfig based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqUsrNoteConfigs(SearchFilter<TChngReqUsrNoteConfig> searchFilter);
	
	
	/**
	 * Fetch user notification config flag.
	 *
	 * @param configId the config id
	 * @param userTypeId the user type id
	 * @param tenatId the tenat id
	 * @return the list
	 */
	List<Object[]> fetchUserNotificationConfigFlag(Integer configId,List<Integer> userTypeId,Short tenatId);
	
	
	/**
	 * Gets the chng req usr note config by cr cofig id.
	 *
	 * @param crCofigId the cr cofig id
	 * @param tenantId the tenant id
	 * @return the chng req usr note config by cr cofig id
	 */
	List<TChngReqUsrNoteConfig> getChngReqUsrNoteConfigByCRCofigId(Integer crCofigId,Short tenantId);
	

}
