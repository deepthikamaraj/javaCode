package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TOrgSalesHier;
import com.cognizant.opserv.sp.core.entity.TSalesHierList;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TAlgmntSalesHier DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TAlgmntSalesHierDAO {

	/**
	 * Stores a new TAlgmntSalesHier entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be persisted
	 * @return tAlgmntSalesHier Persisted TAlgmntSalesHier object
	 */
	TAlgmntSalesHier createTAlgmntSalesHier(TAlgmntSalesHier tAlgmntSalesHier);

	/**
	 * Deletes a TAlgmntSalesHier entity object from the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be deleted
	 */
	void deleteTAlgmntSalesHier(Long salesHierId);

	/**
	 * Updates a TAlgmntSalesHier entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be updated
	 * @return tAlgmntSalesHier Persisted TAlgmntSalesHier object
	 */
	TAlgmntSalesHier updateTAlgmntSalesHier(TAlgmntSalesHier tAlgmntSalesHier);

	/**
	 * Retrieve an TAlgmntSalesHier object based on given salesHierId.
	 * 
	 * @param salesHierId
	 *            the primary key value of the TAlgmntSalesHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TAlgmntSalesHier findTAlgmntSalesHierById(Long salesHierId);

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesHier> findTAlgmntSalesHiers(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Count TAlgmntSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesHiers(SearchFilter<TAlgmntSalesHier> searchFilter);

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesHier> getTAlgmntSalesHiersByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesHiersByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TAlgmntSalesHier> getTAlgmntSalesHiersByTOrgSalesHier(SearchFilter<TOrgSalesHier> searchFilter);

	/**
	 * Count TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTAlgmntSalesHiersByTOrgSalesHier(SearchFilter<TOrgSalesHier> searchFilter);
	
	/**
	 * Find t alg sal hier by id.
	 *
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param alignID the align id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTAlgSalHierById(
			Long businessUnitId,Long salesTeamId, Long alignID, Short tenantId);
	
	/**
	 * Find t prn sal hier by id.
	 *
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param AlgID the alg id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTPrnSalHierById(Long businessUnitId,Long salesTeamId,Long AlgID,Short tenantId);
	
	/**
	 * Find t algmnt sales hier by t algmnt id.
	 *
	 * @param alignmentId the alignment id
	 * @param buId the bu id
	 * @param salesId the sales id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTAlgmntSalesHierByTAlgmntID(Long alignmentId,Long buId,Long salesId,Short tenantId);

	/**
	 * Creates the t sales team hier.
	 *
	 * @param tSalesHierList the t sales hier list
	 * @return the t sales hier list
	 */
	TSalesHierList createTSalesTeamHier(TSalesHierList tSalesHierList);
	
	/**
	 * Find hier names by i ds.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findHierNamesByIDs(Long algmntId,Long bussUnitId,Long salesTeamId, Short tenantId);

	/**
	 * Find hier name.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findHierName(Long algmntId, Long bussUnitId,
			Long salesTeamId, Long salesHierId, Short tenantId);

	/**
	 * Find t alg sales hier.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param long1 the long1
	 * @param salesHierListID the sales hier list id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTAlgSalesHier(Long algmntId, Long bussUnitId, Long long1,
			Long salesHierListID, Short tenantId);

	/**
	 * Fetch t algmnt hier by buss id.
	 *
	 * @param tenantID the tenant id
	 * @param currentDate the current date
	 * @return the list
	 */
	List<TAlgmntSalesHier> fetchTAlgmntHierByBussID(Short tenantID, Date currentDate);

	/**
	 * Find t algmnt sales hier by prn.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTAlgmntSalesHierByPrn(Long salesHierId,
			Short tenantId);
	
	/**
	 * Find t algmnt sales hier by prn.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param alignmentId the alignment id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findTAlgmntSalesHierByPrn(Long salesHierId,
			Short tenantId , Long alignmentId);

	/**
	 * Find hier names by albust.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param activeFlag the active flag
	 * @param effStrtDt the eff strt dt
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findHierNamesByALBUST(Integer algmntId, Integer bussUnitId,
			Integer salesTeamId, Character activeFlag, Date effStrtDt, Date effEndDt,
			Short tenantId);

	/**
	 * Find hier names by albust.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param activeFlag the active flag
	 * @param effStrtDt the eff strt dt
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TAlgmntSalesHier> findHierNamesByALBUST(Long algmntId,	Long bussUnitId, Long salesTeamId, Character activeFlag,Date effStrtDt, Date effEndDt, Short tenantId);

	/**
	 * Find t algmnt sales hier by prnand algmnt id sales hier count.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param alignmentId the alignment id
	 * @return the long
	 */
	Long findTAlgmntSalesHierByPrnandAlgmntIdSalesHierCount(Long salesHierId,	Short tenantId, Long alignmentId);

	/**
	 * Fetch act hier names by albust.
	 *
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	List<Object[]> fetchActHierNamesByALBUST(Date effEndDt, Short tenantId,
			Character activeFlag);
	
}
