package com.cognizant.opserv.sp.core.dao;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAff DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAffDAO {

	/**
	 * Stores a new TCustAff entity object in to the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be persisted
	 * @return tCustAff Persisted TCustAff object
	 */
	TCustAff createTCustAff(TCustAff tCustAff);

	/**
	 * Deletes a TCustAff entity object from the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be deleted
	 */
	void deleteTCustAff(Integer custAffId);

	/**
	 * Updates a TCustAff entity object in to the persistent store
	 * 
	 * @param tCustAff
	 *            TCustAff Entity object to be updated
	 * @return tCustAff Persisted TCustAff object
	 */
	TCustAff updateTCustAff(TCustAff tCustAff);

	/**
	 * Retrieve an TCustAff object based on given custAffId.
	 * 
	 * @param custAffId
	 *            the primary key value of the TCustAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAff findTCustAffById(Integer custAffId);

	/**
	 * Retrieve TCustAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> findTCustAffs(SearchFilter<TCustAff> searchFilter);

	/**
	 * Count TCustAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffs(SearchFilter<TCustAff> searchFilter);

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> getTCustAffsByTCustByAffCustId(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffsByTCustByAffCustId(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> getTCustAffsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);

	
	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> getTCustAffsByTCustByCustId(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAffsByTCustByCustId(SearchFilter<TCust> searchFilter);

	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> getTCustAffsByTAlgmntSalesTeamAndTCustByCustId(Integer algmntId, Integer businessUnitId, Integer salesTeamId, Integer customerId,Character activeFlag);
	
	
	/**
	 * Retrieve TCustAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAff> list of TCustAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAff> getTCustAffsByTAlgmntSalesTeamAndTCustByAffCustId(Integer algmntId, Integer businessUnitId, Integer salesTeamId, Integer customerId,Character activeFlag);
	/**
	 * Retrieve an True or False based on given  custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return True or False if it exists against given primary key. 
	 */
	Boolean checkAffiliation(Integer custId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId);
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return List of  AffDetails if it exists against given primary key. Returns null of
	 *         not found
	 */
	public List<TCustAff> getTCustAffs(Integer custId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId);
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public List<TCustAff> getTCustAffsCallPlan(Integer custId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId,Long salesHierId,Long salesPosId);
	/**
	 * Retrieve an TCustAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return List of  AffDetails if it exists against given primary key. Returns null of
	 *         not found
	 */
	public List<Object[]> checkTCustAffHeirFrCust(List<Integer> custIdList,  Short tenantId);
	/**
	 * Retrieve an count based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return  count if it exists against given primary key. Returns null of
	 *         not found
	 */
	Object countTCustAffByAffCustId(Integer custId, Character activeFlag, Date affStartDt,
			Date affEndDt, Short tenantId);
	/**
	 * Retrieve an count based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return  count if it exists against given primary key. Returns null of
	 *         not found
	 */
	Object countTCustAffByCustId(Integer custId, Character activeFlag, Date affStartDt,
			Date affEndDt, Short tenantId);
	/**
	 * Retrieve an CustsForAff object based on given TCustAff custId.
	 * 
	 * @param custId
	 *            the primary key value of the TCustAff Entity.
	 * @return custList if it exists against given primary key. Returns null of
	 *         not found
	 */
	List<Object[]> findCustsForAff(String custName, String custCd,
			String postalCd, String city, Integer custTypeId, Date createDt,
			Integer custId,Character activeFlag,
			Date effStartDt, Date effEndDt, Short tenantId, int index, int maxresult) throws ParseException;

	/**
	 * Retrieve an TCustAff object based on given TCustAff customerId.
	 * 
	 * @param customerId
	 *            the primary key value of the TCustAff Entity.
	 * @return  List<TCustAff> if it exists against given primary key. Returns null of
	 *         not found
	 */
	List<TCustAff> findTCustAffsByCustId(Integer customerId,
			Character activeFlag, Short tenantId);

	
	/**
	 * Find t cust data by cust id.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findTCustDataByCustId(String localeId, Integer custId,
			Character activeFlag, Short tenantId);
	
	/**
	 * Find t cust data by aff cust id.
	 *
	 * @param localeId the locale id
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findTCustDataByAffCustId(String localeId, Integer custId,
			Character activeFlag, Short tenantId);

	/**
	 * Find t cust aff by cust id.
	 *
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findTCustAffByCustId(Integer custId, Character activeFlag,
			Short tenantId, Date curDate, String localeId);

	/**
	 * Find t cust aff by aff cust id.
	 *
	 * @param custId the cust id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @param localeId the locale id
	 * @return the list
	 */
	List<Object[]> findTCustAffByAffCustId(Integer custId,
			Character activeFlag, Short tenantId, Date curDate, String localeId);
	
	//Newly Added
/*	List<TCustAff> getTCustAffsByAlignmentAndCustId(Long algmntId,
			Long businessUnitId, Long salesTeamId, Integer customerId,
			Character activeFlag, Date curDate, Short tenantId);
	
	List<TCustAff> getTCustAffsByAlignmentAndAffCustId(Long algmntId,
			Long businessUnitId, Long salesTeamId, Integer customerId,
			Character activeFlag, Date curDate, Short tenantId);*/
	
	/**
	 * Find account t cust affs for t cust.
	 *
	 * @param customerId the customer id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Integer> findAccountTCustAffsForTCust(Integer customerId, Short tenantId);
	
	/**
	 * Find t cust affs for aff cust id.
	 *
	 * @param customerId the customer id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @return the list
	 */
	List<TCustAff> findTCustAffsForAffCustId(Integer customerId, Character activeFlag, Short tenantId, Date curDate);
	
	/**
	 * Find t cust affs for cust id.
	 *
	 * @param customerId the customer id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @return the list
	 */
	List<TCustAff> findTCustAffsForCustId(Integer customerId, Character activeFlag, Short tenantId, Date curDate);
	
	/**
	 * Find root aff ids for cust id.
	 *
	 * @param customerId the customer id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Integer> findRootAffIdsForCustId(Integer customerId, Short tenantId);
	
	/**
	 * Find root aff ids for cust id and alignment.
	 *
	 * @param customerId the customer id
	 * @param algmntId the algmnt id
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Integer> findRootAffIdsForCustIdAndAlignment(Integer customerId, Long algmntId, Long businessUnitId, Long salesTeamId, Short tenantId);
	
	/**
	 * Find t cust affs for root aff id.
	 *
	 * @param rootAffId the root aff id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TCustAff> findTCustAffsForRootAffId(Integer rootAffId, Short tenantId);
	
	/**
	 * Find AccountTCustAffsForCustId for custId.
	 *
	 * @param customerId the customer id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @param curDate the cur date
	 * @return the list
	 */
	List<TCustAff> findAccountTCustAffsForCustId(Integer customerId, Short tenantId, Date curDate);
}
