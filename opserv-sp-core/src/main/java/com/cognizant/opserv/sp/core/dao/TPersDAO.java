package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPers DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPersDAO {

	/**
	 * Stores a new TPers entity object in to the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be persisted
	 * @return tPers Persisted TPers object
	 */
	TPers createTPers(TPers tPers);

	/**
	 * Deletes a TPers entity object from the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be deleted
	 */
	void deleteTPers(Integer staffId);

	/**
	 * Updates a TPers entity object in to the persistent store
	 * 
	 * @param tPers
	 *            TPers Entity object to be updated
	 * @return tPers Persisted TPers object
	 */
	TPers updateTPers(TPers tPers);

	/**
	 * Retrieve an TPers object based on given staffId.
	 * 
	 * @param staffId
	 *            the primary key value of the TPers Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPers findTPersById(Integer staffId);

	/**
	 * Retrieve TPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPers> list of TPerss if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPers> findTPerss(SearchFilter<TPers> searchFilter);

	/**
	 * Count TPers based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPerss(SearchFilter<TPers> searchFilter);

	/**
	 * 
	 * @param searchFilter
	 * @param tenantId
	 * @return
	 */
	Long findEmployeeCount(SearchFilter<TPers> searchFilter, short tenantId);

	/**
	 * 
	 * @param filter
	 * @param tenantId
	 * @param start
	 * @param size
	 * @return
	 */
	List<TPers> findEmployeeList(SearchFilter<TPers> filter,Short tenantId,int start,int size);

	/**
	 * Gets the t pers by native query.
	 *
	 * @param query the query
	 * @return the t pers by native query
	 */
	List getTPersByNativeQuery(String query);
	
	/**
	 * Find t pers by name.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List <TPers> FindTPersByName(SearchFilter<TPers> searchFilter);
	
	/**
	 * Gets the t perss by t pers.
	 *
	 * @param searchFilter the search filter
	 * @return the t perss by t pers
	 */
	List<TPers> getTPerssByTPers(SearchFilter<TPers> searchFilter);
	
	/**
	 * Gets the t pers by user id.
	 *
	 * @param params the params
	 * @return the t pers by user id
	 */
	List<Object[]> getTPersByUserId(List<Object> params) ;
	
	/**
	 * Find t pers by user id single.
	 *
	 * @param params the params
	 * @return the t pers
	 */
	TPers findTPersByUserIdSingle(List<Object> params) ;
	
    /**
     * Gets the tpers by client id.
     *
     * @param params the params
     * @return the tpers by client id
     */
    List<TPers> getTpersByClientId(List<Object> params) ;

    /**
     * Gets the pers by id.
     *
     * @param userId the user id
     * @param localeId the locale id
     * @param tenantId the tenant id
     * @return the pers by id
     */
    List<TPers> getPersById(Integer userId, String localeId,Short tenantId);

    /**
     * Find t pers by user id single fr comm.
     *
     * @param params the params
     * @return the string
     */
    String findTPersByUserIdSingleFrComm(List<Object> params);

	/**This method returns list of employees details matching the given search parameters
	 * @param name
	 * @param code
	 * @param zip
	 * @param city
	 * @param type
	 * @param state
	 * @param attrMap
	 * @param statusId
	 * @param start
	 * @param results
	 * @param tenantId
	 * @return List<Object[]>
	 */
	List<Object[]> buildQueryFromWhereClause(String name, String code, String zip,
			String city, Integer type,String state,Map<String,String> attrMap, Integer statusId, int start,
			int results, Short tenantId);

    /**
     * @param staffId
     * @param tenantId
     * @param locale
     * @return object[] containing staffId,firstName,lastName,birthDtStr,statusDesc,gender,empName,clientId in that order,null if no details exist 
     */
    Object[] findEmpDetails(Integer staffId,Short tenantId,String locale);
    
    /**
     * Find t pers single by user id.
     *
     * @param params the params
     * @return the t pers
     */
    TPers findTPersSingleByUserId(List<Object> params);

	/**
	 * Find t pers dtls by user id.
	 *
	 * @param usrId the usr id
	 * @return the list
	 */
	List<TPers> findTPersDtlsByUserId(Integer usrId);

	/**
	 * Gets the t pers info by user ids.
	 *
	 * @param userIds the user ids
	 * @param tenantId the tenant id
	 * @return the t pers info by user ids
	 */
	List<Object[]> getTPersInfoByUserIds(List<Integer> userIds, Short tenantId);
	
	/**
	 * Find t pers by staff id.
	 *
	 * @param staffId the staff id
	 * @param tenantId the tenant id
	 * @return the t pers
	 */
	TPers findTPersByStaffId(Integer staffId , Short tenantId);
	
	/**
	 * Find t pers by user id.
	 *
	 * @param userId the user id
	 * @param tenantId the tenant id
	 * @return the t pers
	 */
	TPers findTPersByUserId(Integer userId , Short tenantId);
	
	/**
	 * Find chng req approvers.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAprFlg the target apr flg
	 * @param tenantId the tenant id
	 * @param allocTypeId the alloc type id
	 * @return the list
	 */
	List<TPers> findChngReqApprovers(Long chngReqId,Character targetAprFlg,Short tenantId,Integer allocTypeId);
	
	/**
	 * Find t pers by sales pos id.
	 *
	 * @param spId the sp id
	 * @param allocTypeId the alloc type id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TPers> findTPersBySalesPosId(Long spId,Integer allocTypeId,Short tenantId);
}
