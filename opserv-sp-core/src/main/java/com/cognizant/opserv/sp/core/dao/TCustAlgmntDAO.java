package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAlgmntDAO {

	/**
	 * Stores a new TCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be persisted
	 * @return tCustAlgmnt Persisted TCustAlgmnt object
	 */
	TCustAlgmnt createTCustAlgmnt(TCustAlgmnt tCustAlgmnt);

	/**
	 * Deletes a TCustAlgmnt entity object from the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be deleted
	 */
	void deleteTCustAlgmnt(String custAlgmntId);


	/**
	 * Deletes a TCustAlgmnt entity object from the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be deleted
	 */
	public void deleteTCustAlgmnt(final Long custAlgmntId);
	
	/**
	 * Updates a TCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustAlgmnt
	 *            TCustAlgmnt Entity object to be updated
	 * @return tCustAlgmnt Persisted TCustAlgmnt object
	 */
	TCustAlgmnt updateTCustAlgmnt(TCustAlgmnt tCustAlgmnt);

	/**
	 * Retrieve an TCustAlgmnt object based on given custAlgmntId.
	 * 
	 * @param custAlgmntId
	 *            the primary key value of the TCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAlgmnt findTCustAlgmntById(String custAlgmntId);

	/**
	 * Retrieve TCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmnt> findTCustAlgmnts(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Count TCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmnts(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Retrieve TCustAlgmnt based on given search criteria using JPA named
	 * Query. The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmnt> getTCustAlgmntsByTCust(SearchFilter<TCust> searchFilter);

	/**
	 * Count TCustAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntsByTCust(SearchFilter<TCust> searchFilter);
	/**
	 * Retrieve CustomerTemplatesByAlignment
	 * 
	 * @param staffId
	 * @param boId
	 * @param tenantId
	 * 
	 * @return List of TemplatesByAlignment
	 */
	List findCustomerTemplatesByAlignment(Integer staffId, Integer boId,
			Short tenantId);
	/**
	 * Retrieve countOfCustAlgmnts
	 * 
	
	 * @param paramList
	
	 * @return countL
	 */
	 Long countOfCustAlgmnts(List paramList);

	 /**
	 * Retrieve searchCustomerAlgmnt
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of searchCustomerAlgmnt
	 */
	List<Object[]> searchCustomerAlgmnt2(String salesPosId, String salesHierId,
			String alignmentId, String salesTeamId, String businessUnitId);
	/**
	 * Retrieve searchCustomerAlgmnt
	 * 
	
	
	 * @return List of object of searchCustomerAlgmnt
	 */
	List<Object[]> searchCustomerAlgmnt3();
	/**
	 * Retrieve delCustAlgmnts
	 * 
	
	 * @param effEndDt
	 * @param buId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param tenantId
	 * @param custIdInt
	 * @param updatedBy
	
	 */
	void delCustAlgmnts(Date effEndDt,Short tenantId, Long salesPosId,
			Long salesHierId, Long alignId, Long buId, Long salesTeamId,
			Integer custIdInt,Integer updatedBy);
	/**
	 * Retrieve countOfCustPlannedCall
	 * 
	
	 * @param paramList
	
	 * @return countL
	 */
	Long countOfCustPlannedCall(List paramList);
	/**
	 * Retrieve an TCustAlgmnt object based on given TCustAlgmnt custAlgmntId.
	 * 
	 * @param tCustAlgmntId
	 *            the primary key value of the TCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAlgmnt findTCustAlgmntById(final Long tCustAlgmntId);
	/**
	 * Retrieve TCustAlgmnt
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForUnAssign
	 */
	List<TCustAlgmnt> findTCustAlgmnts(Short tenantId, Long salesHierId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Long salesPosId, Integer custId);

	List<TCustAlgmnt> findTargetedCustomerForRemoval(List queryParams);
	/**
	 * Find all custalignments based on cid
	 * @param cid
	 * @param userDetails 
	 * @return
	 */
	List<TCustAlgmnt> getAllCustAlgmntsFromCId(Integer cid, Short tenantId);
	/**
	 * Retrieve TCustAlgmntsForUnAssignSP
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForUnAssignSP
	 */
	List<TCustAlgmnt> findTCustAlgmntsForUnAssignSP(Long salesPosId,
			Long salesHierId, Long alignmentId, Long bussinessUnitId,
			Long salesTeamId, Short tenantId, boolean flag);
	/**
	 * Retrieve TCustAlgmntByALBUST
	 * 

	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntByALBUST
	 */
	List<TCustAlgmnt> findTCustAlgmntByALBUST(Short tenantId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Integer custId);

	/**
	 * This API is to fetch count of customers assigned for sales position
	 * 
	 * @param salesPosId
	 * @param salesHierId
	 * @param alignmentId
	 * @param bussinessUnitId
	 * @param salesTeamId
	 * @param tenantId
	 * @return
	 */
	Long fetchCountOfTCustAlgmnt(Long salesPosId, Long salesHierId,
			Short tenantId);
	
	/**
	 * Retrieve CountOfTCustAlgmntandChild
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return countL
	 */
	
	Long fetchCountOfTCustAlgmntandChild(Set<Long> childsp ,
			Long alignmentId, Long bussinessUnitId, Long salesTeamId,
			Short tenantId, Date currentDate, String localeId, List<Integer> crStatustList);
	/**
	 * Retrieve TCustAlgmntsforPopUp
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForPopUp
	 */
	List<TCustAlgmnt> findTCustAlgmntsforPopUp(Long alignId, Long buId,
			Long salesTeamId, Long salesHierId, Long salesPosId,
			Integer custId, short tenantId);
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<TCustAlgmnt> getCustomerAlignmentBetweenDates(Date startDate,Date endDate,int custId,short tenantId,Long algmntId,Long buId,Long stId);
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param custId
	 * @param tenantId
	 * @return
	 */
	List<TCustAlgmnt> getCustomerAlignmentBetweenDatesForEnd(Date startDate,Date endDate,int custId,short tenantId,Long algmntId,Long buId,Long stId);
	
	/**
	 * Retrieve CustomerDetailsForCustomerSearch
	 * 
	
	 * @param searchFilter
	 * @param priAddrFlag
	 * @param localeId_Cat
	
	 * @return object of TCustAlgmntsForCustomerSearch
	 */
	List<Object[]> getCustomerDetailsForCustomerSearch(SearchFilter<TCustAlgmnt> searchFilter, Character priAddrFlag,String localeId_Type);
	/**
	 * Retrieve getActPropCustomers
	 * 
	
	 * @param searchFilter
	
	 * @return List of ActPropCustomers
	 */
	List<TCustAlgmnt> getActPropCustomers(SearchFilter<TCustAlgmnt> searchFilter);
	/**
	 * Retrieve TCustAlgmntsForUnassign
	 * 
	 * 
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param custId
	 * @param activeFlag
	 * @param tenantId
	 * @return List of TCustAlgmntsForUnAssign
	 */
	List<TCustAlgmnt> findTCustAlgmntsForUnassign(Short tenantId, Long salesHierId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Long salesPosId, Integer custId, Character activeFlag);
	/**
	 * Retrieve AssignedActPropCust
	 * 
	
	 * @param searchFilter
	 * @param localeId_Type
	 * @param priAddrFlag
	 
	 * @return List of AssignedActPropCust
	 */
	List<Object[]> fetchAssignedActPropCust(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag,
			Character activeAddr, String localeId_Type,List<Integer> crStatusList);
	/**
	 * Retrieve CustDtlsByCustAlgmntId
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of custDtlsList
	 */
	List<Object[]> fetchCustDtlsByCustAlgmntId(List<Long> custAlgmntIdList, Long salesHeirId, Long salesPosId, String localeId);
	
	/**
	 * Retrieve AssignedCustomers
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of AssignedCustomers
	 */
	
			List<Object> fetchAssignedCustomers(List<Integer> custIds,Long spId,Long shId, Short tenantId) ;

	/**
	 * Method to fetch active Alignments based on given input parameters
	 * @param custId
	 * @param algtId
	 * @param buId
	 * @param stId
	 * @param spId
	 * @param shId
	 * @param activeFlag
	 * @param stDt
	 * @param endDt
	 * @param tenantId
	 * @return
	 */
	List<TCustAlgmnt> fetchActAlgmntsFromCustIdAndSP(Integer custId,
			Long algtId, Long buId, Long stId, Long spId, Long shId,
			Character activeFlag, Date stDt, Date endDt, Short tenantId);

	/**
	 * Method to fetch active Alignments based on given input parameters
	 * @param custId
	 * @param algtId
	 * @param buId
	 * @param stId
	 * @param activeFlag
	 * @param stDt
	 * @param endDt
	 * @param tenantId
	 * @return
	 */
	List<TCustAlgmnt> fetchActAlgmntsFromCustIdAndST(Integer custId,
			Long algtId, Long buId, Long stId, Character activeFlag, Date stDt,
			Date endDt, Short tenantId);

	/**
	 * Method to fetch active Alignments based on given input parameters
	 * @param custId
	 * @param activeFlag
	 * @param stDt
	 * @param endDt
	 * @param tenantId
	 * @return
	 */
	List<TCustAlgmnt> fetchActAlgmntsFromCustId(Integer custId,
			Character activeFlag, Date stDt, Date endDt, Short tenantId);
	/**
	 * Retrieve getCustCountBasedonName
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return countL
	 */
	Long getCustCountBasedonName(SearchFilter<TCustAlgmnt> searchFilter);
	/**
	 * Retrieve AllAssignmentsForCustomer
	 * 
	
	 * @param custId
	 * @param activeFlag
	 * @param currentDate
	 
	 * @return List of object of assignMentDetails
	 */
	List<Object[]> getAllAssignmentsForCustomer(Integer custId, Character activeFlag, Date currentDate);
	
	/**
	 * Retrieve CountOfAllAssignedActPropCust
	 * 
	 * @param crStatusList
	 * @param localeId_Cat
	 * @param activeAddrFlag
	 * @param priAddrFlag
	 * @param searchFilter
	 * @param localeId_Type
	 * @return custAlgmntIdList
	 */
	List<Object[]> fetchCountOfAllAssignedActPropCust(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag,
			Character activeAddr, String localeId_Type, List<Integer> crStatusList);
	/**
	 * Retrieve CustAlgDtBySalesPosEffEndDt
	 * 
	
	 * @param tenantId
	
	 * @return List of object of TCustAlgmntsByJoinQuery
	 */
	List<Object[]> fetchCustAlgDtBySalesPosEffEndDt(short tenantId);
	/**
	 * Retrieve ToSpCheckFrGIS
	 * 
	
	 * @param searchFilter
	
	 * @return custAlgmntIdList
	 */
	List<TCustAlgmnt> assignedToSpCheckFrGIS(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Method to fetch customers for the list of custAlgmntList
	 * @param custAlIdList
	 * @return List<TCustAlgmnt>
	 */
	List<TCustAlgmnt> fetchCustomersFrUnassign(List<Long> custAlIdList);
	/**
	 * Retrieve AssignedActPropCustFrSpList
	 * 
	
	 * @param searchFilter
	 * @param localeId_Type
	 * @param priAddrFlag
	 * @param activeAddrFlag
	 * @param spIdList
	 * @return List of object of fetchAssignedActPropCustFrSpList
	 */
	List<Object[]> fetchAssignedActPropCustFrSpList(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag,
			Character activeAddr, String localeId_Type, Set<Long> spIdList);
	/**
	 * Retrieve CustAssFrGIS
	 * 
	 * 
	 * @param custId
	 * @param date
	 * @param tenantId
	 * 
	 * @return List of object of CustAssFrGIS
	 */
	List<Object[]> fetchCustAssFrGIS(Integer custId, Date date, Short tenantId);
	/**
	 * RetrieveAssignedActPropCustFrGIS
	 * 
	
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddr
	 * @param localeId_Type
	 * @return List of object of AssignedActPropCustFrGIS
	 */
	List<Object[]> fetchAssignedActPropCustFrGIS(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag,
			Character activeAddr, String localeId_Type,List<Long> shIdList, List<Long> spIdList);
	/**
	 * Retrieve TCustAlgmntActiveforPopUp
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of TCustAlgmntActiveforPopUp
	 */
   List<TCustAlgmnt> fetchTCustAlgmnt(Long algmntId,
			Long bussUnitId, Long salesTeamId, Long saleHierId, Long salePosId,
			Integer custId, Short tenantId);
   /**
	 * Retrieve TCustAlgmntActiveSP
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of object of TCustAlgmntActiveSP
	 */
   List<Object[]> findTCustAlgmntActiveSP(Long algmntId,
			Long bussUnitId, Long salesTeamId, Long saleHierId, Long salePosId,
			List<Integer> custId, Short tenantId);
   /**
	 * Retrieve AssignedActPropCustFrSrchOnTiles
	 * 
	
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddr
	 * @param localeId_Type
	 * @param crStatusList
	 * @param prtTypeIds
	 * @return customerList
	 */

	List<Object[]> fetchAssignedActPropCustFrSrchOnTiles(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
			Character priAddrFlag, Character activeAddr, String localeId_Type,
			List<Integer> crStatusList, List<Integer> prtTypeIds,
			SearchFilter<TCustAddr> searFilterAddr, Boolean targetFlag);
	/**
	 * Retrieve AllAssignmentsForCustIdList
	 * 
	
	 * @param custIdList
	 * @param activeFlag
	 * @param currentDate
	
	 * @return List of object of assignMentDetails
	 */
List<Object[]> getAllAssignmentsForCustIdList(List<Integer> custIdList, Character activeFlag, Date currentDate);
/**
 * Retrieve AssignedActPropCustFrGrid
 * 

 
	 * @param searchFilter
	 * @param localeId_Cat
	 * @param priAddrFlag
	 * @param activeAddrFlag
	 * @param localeId_Type
	 * @param crStatusList
	 * @param sortBy
 * @return List of object of AssignedActPropCustFrGrid
 */
   public List<Object[]> fetchAssignedActPropCustFrGrid(
			SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
			Character priAddrFlag,
			Character activeAddrFlag,String localeId_Cat, List<Integer> crStatusList, String sortBy);

   /**
    * Retrieve active assignments for the customer
    * @param custIds
    * @param algmntId
    * @param bu
    * @param st
    * @param activeFlagY
    * @param tenantId
    * @return List<Object[]>
    */
   List<Object[]> fetchActAlgmntsFromCustIds(List<Integer> custIds, long algmntId,
		long bu, long st, Character activeFlagY, Short tenantId);

Long findTCustAlgmntsForUnAssignSPCount(Long salesPosId, Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId,Short tenantId, boolean flag);

List<Object[]> findAssignedCustomer(List<Integer> listOfCustIds, Long algmntId, String bussUnitId, Long salesTeamId, Long salesPosId, Short tenantId);


   List<TCustAlgmnt> createTCustAlgmnts(List<TCustAlgmnt> tCustAlgmnts);
   
   List<TCustAlgmnt> updateTCustAlgmnts(List<TCustAlgmnt> tCustAlgmnts);
   
   
   List<TCustAlgmnt> findAllTCustAlgmntsByCutId(Integer custId,	Short tenantId);
   
   
   


   List<Object[]> fetchCustAlignedList(Long alignId, Long bussId,Long salesTeamId,
		   Long salesPosId, Long salesHierId, Short tenantId);

List<Object[]> getCustomerAlignedList(Long alignmentId, Long bussinessUnitId,
		Long salesTeamId, Long salesPosId, Long salesHierId, Integer custId,
		Short tenantId);

List<Object> fetchActAlgmntsALBUST(List paramList);
List<Object> fetchAllAlgmntsALBUST(List paramList);
int updateTCustAlgmnt(List paramList);
public void createTempTable(String query);
public void insertOrUpdateTempTable(String query);
public int custAffProcedure(String query);


Integer fetchAssignedActPropCustFrSrchOnTilesCount(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		Character priAddrFlag, Character activeAddr, String localeId_Type,
		List<Integer> crStatusList, List<Integer> prtTypeIds,
		SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag);

void inActivateAffiliatedCustomersByCustAlgmntIds(List<Long> custAlgmntIds,Short tenantId);

// newly added for secondary address validation	
Integer fetchCountOfAllAssignedActCustNoAddr(  
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
		String localeId_Cat, List<Integer> crStatusList); 


List<Object[]> fetchAssignedActPropCustWithNoAddr(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
		String localeId_Cat, List<Integer> crStatusList);


List<Object[]> fetchAssignedActPropCustFrSrchOnTilesNoAddrs(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
		SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag);

Integer fetchCountOfInactiveCustomerWithPendingCR( SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
		String localeId_Cat, List<Integer> crStatusList);

List<Object[]> fetchInactiveCustomerWithPendingCR(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Type,
		String localeId_Cat, List<Integer> crStatusList,Integer start,Integer max);

List<Object[]> fetchSrchInActivePendingCRsOnTiles(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		Character priAddrFlag, Character activeAddr, String localeId_Type,
		List<Integer> crStatusList, List<Integer> prtTypeIds,
		SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag);

List<Object[]> fetchSrchInActivePendingCRsOnTilesNoAddrs(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		String localeId_Type, List<Integer> crStatusList,
		List<Integer> prtTypeIds, SearchFilter<TCustAddr> srchFltrAddr,
		Boolean targetFlag);

Integer fetchSrchInActivePendingCRsOnTilesCount(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		Character priAddrFlag, Character activeAddr, String localeId_Type,
		List<Integer> crStatusList, List<Integer> prtTypeIds,
		SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag);

Integer fetchAssignedActPropCustFrSrchOnTilesNoAddrsCount(
		SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		String localeId_Type, List<Integer> crStatusList,
		List<Integer> prtTypeIds, SearchFilter<TCustAddr> srchFltrAddr,
		Boolean targetFlag);

Integer fetchSrchInActivePendingCRsOnTilesNoAddrsCount(SearchFilter<TCustAlgmnt> searchFilter, String localeId_Cat,
		String localeId_Type, List<Integer> crStatusList, List<Integer> prtTypeIds,
		SearchFilter<TCustAddr> srchFltrAddr, Boolean targetFlag);

void updateActiveTcustAlgmnt(Date currentdate, Short tenantId);




/**
 * @param customerId
 * @param buId
 * @param userDetails
 * @return
 */
List<TCustAlgmnt> getCustomerAlignmentFrBuid(int customerId, long buId,
		Short tenantId);

/**
	 * Retrieve TCustAlgmnt based on given search criteria.
	 * 
	 * @param criteria
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmnt> list of TCustAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmnt> findCustAlgmntsBySerachCriteria(ISearchCriteria criteria);

	/**Get count of GeoAlgned customers
	 * @param custIds
	 * @param tenantId
	 * @return
	 */
	List<Object[]> getCountGeoAlgdCust(Long spId,Long shId, List<String> postalCode, Short tenantId);

	/**Get count of comp elg customers
	 * @param custIds
	 * @param tenantId
	 * @return
	 */
	List<Object[]> getCountCompElgCust(Long spId,Long shId, List<String> postalCode, Short tenantId);

	
	/**
	 * @param custAlgmntIds
	 * @param tenantId
	 * @return
	 */
	List<TCustAlgmnt> findAllTCustAlgmntsByIDS(Long custAlgmntIds,
			Short tenantId);
	
	
	/**
	 * @param custAlgmntIds
	 * @param userDetails
	 * @return
	 */
	void updateTargetFlagOfCust(Long custAlgmntIds, Integer userId, Short tenantId);
	
	
	/**
	 * @param spId
	 * @param tenantId
	 * @return List
	 */
	List<TCustAlgmnt> findGeoCustAlignmentIdBySalesPostion(Long spId,Short tenantId);
	
	/**
	 * Find geo cust alignment id by sales postion.
	 *
	 * @param spId the sp id
	 * @param shId the sh id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	Long getCountOfGeoAlgdCustFrSp(Long spId,Long shId,
			Short tenantId);
	
	/**
	 * Find non geo cust alignment id by sales postion.
	 *
	 * @param spId the sp id
	 * @param shId the sh id
	 * @param tenantId the tenant id
	 * @return the long
	 */
	Long getCountOfNonGeoAlgdCustFrSp(Long spId,Long shId,
			Short tenantId);

	/**
	 * @param custAlgmntIds
	 * @param userDetails
	 * @return
	 */
	void updateEffEndDateFrCust(Date effEndDt, Character flagToSet, Long custAlgmntId, Integer userId,
			Short tenantId);
	
	/**
     * get custalgmntID by custId and salesPosId 
	 * @param custId customerId
	 * @param spId salesPosId
	 * @return 
	 */
	Long fetchCustAlgmntIdfromCustIdAndSPId(Integer custId, Long SPId);
	
	
	/**
	 * Count of cust algmnt by status.
	 *
	 * @param custId the cust id
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param userDetails the user details
	 * @return the long
	 */
	Long countOfCustAlgmntByStatus(Integer custId, Long alId, Long buId, Long stId, Short tenantId);
	
	/**
	 * This API is to fetch count of customers assigned for sales positions
	 * 
	 * @param salesPosIds
	 * @param tenantId
	 * @return
	 */
	Long findCountOfTCustAlgmntForSpList(List<Long> spIds, Short tenantId);
	
	/**
	 * This API is to get geo aligned count of customers assigned for sales positions
	 * 
	 * @param salesPosIds
	 * @param tenantId
	 * @return
	 */
	Long getGeoAlgdCustomerCountForSalesPositions(List<Long> spIds, Short tenantId);
	
	/**
	 * This API is to fetch count of customers assigned for sales positions
	 * 
	 * @param salesPosIds
	 * @param tenantId
	 * @return
	 */
	Long getCountOfNonGeoAlgdCustFrSpList(List<Long> spIds, Short tenantId);
	List<TCustAlgmnt> findNonGeoCustAlignmentIdBySalesPostion(Long spId,
			Short tenantId);
	
	/**
	 * Retrieve TCustAlgmnt
	 * 
	
	 * @param alignmentId
	 * @param businessUnitId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @return List of TCustAlgmntsForUnAssign
	 */
	List<TCustAlgmnt> findTCustAlgmntFrCustIdList(Short tenantId, Long salesHierId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Long salesPosId,List<Integer> custId);
	
	/**
	 * @param custId
	 * @param spIdList
	 * @param tenantId
	 * @return
	 */
	List<TSalesPos> getAssignedCustomerFrSp(Long custId,List<Long> spIdList, short tenantId);
	
    /**
     * @param tCustAlgmnt
     * @return TCustAlgmnt
     */
    int updateTCustAlgmntToLock(Long CustAlgmntId,Integer statusId, Integer userId);
    
    /**
     * @param tCustAlgmnt
     * @return TCustAlgmnt
     */
	int updateTCustAlgmntToUnLock(List<Long> CustAlgmntId, Integer statusId,
			Integer userId);
}
