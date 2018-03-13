package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntStatus;
import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntDAO {

	/**
	 * Stores a new TAlgmnt entity object in to the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be persisted
	 * @return tAlgmnt Persisted TAlgmnt object
	 */
	TAlgmnt createTAlgmnt(TAlgmnt tAlgmnt);

	/**
	 * Deletes a TAlgmnt entity object from the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be deleted
	 */
	void deleteTAlgmnt(Long algmntId);

	/**
	 * Updates a TAlgmnt entity object in to the persistent store
	 * 
	 * @param tAlgmnt
	 *            TAlgmnt Entity object to be updated
	 * @return tAlgmnt Persisted TAlgmnt object
	 */
	TAlgmnt updateTAlgmnt(TAlgmnt tAlgmnt);

	/**
	 * Retrieve an TAlgmnt object based on given algmntId.
	 * 
	 * @param algmntId
	 *            the primary key value of the TAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmnt findTAlgmntById(Long algmntId);

	/**
	 * Retrieve TAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmnt> findTAlgmnts(SearchFilter<TAlgmnt> searchFilter);

	/**
	 * Count TAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmnts(SearchFilter<TAlgmnt> searchFilter);

	/**
	 * Retrieve TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmnt> getTAlgmntsByTBussUnit(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Count TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntsByTBussUnit(SearchFilter<TBussUnit> searchFilter);

	/**
	 * Retrieve TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmnt> list of TAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmnt> getTAlgmntsByTAlgmntStatus(SearchFilter<TAlgmntStatus> searchFilter);

	/**
	 * Count TAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntsByTAlgmntStatus(SearchFilter<TAlgmntStatus> searchFilter);
	
	/**
	 * Find active algmnt.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TAlgmnt> findActiveAlgmnt(SearchFilter<TAlgmnt> searchFilter);

	/**
	 * Gets the all t algmnts by name.
	 *
	 * @param alignmentname the alignmentname
	 * @return the all t algmnts by name
	 */
	List<TAlgmnt> getAllTAlgmntsByName(String alignmentname);
	
	
	/**
	 * Find t algmnts.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<TAlgmnt> findTAlgmnts(Short id);

	/**
	 * Find t algmnts by status.
	 *
	 * @param params the params
	 * @param alignmentStatusId the alignment status id
	 * @return the list
	 */
	List<TAlgmnt> findTAlgmntsByStatus(List<Object>params,Integer alignmentStatusId);

	/**
	 * Find t algmntsfor active proposed.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<TAlgmnt> findTAlgmntsforActiveProposed(Short id); 
	
	/**
	 * Find all active proposed algmnts.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<TAlgmnt> findAllActiveProposedAlgmnts(Short id);

	/**
	 * Method to find present/past/future alignments for a user based on assnmntType,locale,staff and tenant Id's
	 * @param assnmntType
	 * @param localeId
	 * @param stId
	 * @param tenantId
	 * @return list of object[] with each object array containing alName,alStatus,startDate,endDate,salesPosName,salesPosCode,algmntType in that order,
	 * null if no present/past/future alignments found
	 */
	List<Object[]> findAlgmntDetails(String assnmntType,String localeId, Integer stId, Short tenantId);

	/**
	 * Find active algmnt by algmnt id.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TAlgmnt> findActiveAlgmntByAlgmntId(SearchFilter<TAlgmnt> searchFilter);

	/**
	 * Gets the bus rule config.
	 *
	 * @param tenantId the tenant id
	 * @return the bus rule config
	 */
	List<Object[]> getBusRuleConfig(Short tenantId);

	List<TAlgmnt> findTAlgnmntBySearchCriteria(ISearchCriteria criteria, Short tenantId);
	
	
	/**
	 * Find active algmnt by algmnt id.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	TAlgmnt findAlgmntByAlgmntId(Long algId , Short tenantId);
	

}
