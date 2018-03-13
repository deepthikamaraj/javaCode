package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallPlanType;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustCallPlan DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustCallPlanDAO {

	/**
	 * Stores a new TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be persisted
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	TCustCallPlan createTCustCallPlan(TCustCallPlan tCustCallPlan);
	
	
	/**
	 * Stores a new TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be persisted
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	List<TCustCallPlan> createTCustCallPlan(List<TCustCallPlan> tCustCallPlanList);

	/**
	 * Deletes a TCustCallPlan entity object from the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be deleted
	 */
	void deleteTCustCallPlan(Integer custCallPlanId);

	/**
	 * Updates a TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be updated
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	TCustCallPlan updateTCustCallPlan(TCustCallPlan tCustCallPlan);

	/**
	 * Retrieve an TCustCallPlan object based on given custCallPlanId.
	 * 
	 * @param custCallPlanId
	 *            the primary key value of the TCustCallPlan Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustCallPlan findTCustCallPlanById(Integer custCallPlanId);

	/**
	 * Retrieve TCustCallPlan based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlans if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustCallPlan> findTCustCallPlans(SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Count TCustCallPlan based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCallPlans(SearchFilter<TCustCallPlan> searchFilter);

	/**
	 * Retrieve TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlans if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustCallPlan> getTCustCallPlansByTCustAlgmnt(TCustAlgmnt tCustAlgmnt,Short tenantId,boolean flag);

	/**
	 * Count TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCallPlansByTCustAlgmnt(SearchFilter<TCustAlgmnt> searchFilter);

	/**
	 * Retrieve TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlans if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustCallPlan> getTCustCallPlansByTCallPlanType(SearchFilter<TCallPlanType> searchFilter);

	/**
	 * Count TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustCallPlansByTCallPlanType(SearchFilter<TCallPlanType> searchFilter);
	/**
	 * Retrieve an CustomerCallPlanByJointStmt based on search criteria
	 * 
	 * @param callPlanTypeDescription
	 *           
	 * @return an Object of FindCustomerCallPlanByJointStmt if it exists 
	 */
    List<Object[]> findCustomerCallPlanByJointStmt(Long salesPosId, Long salesHeirId, Short tenantId, String callPlanTypeDescription);
    /**
	 * Retrieve an CustomerCallPlanObject based on search criteria
	 * 
	 * @param salesPosId
	 * 
	* @param details 
	 *           
	 * @return an Object of CustomerCallPlanObjectQuery if it exists 
	 */
	List<Object[]> findCustomerCallPlanObject(Long salesPosId, Short tenantId);

	/**
	 * 
	 * @param plannedId
	 * @param custAlignment
	 * @return
	 */
	List<TCustCallPlan> findPlannedCustomerCallPlanObject(short plannedId ,long custAlignment, Short tenantId);

	/**
	 * Retrieve an CustomerCallPlan based on search criteria
	 * 
	 * @param CustomerCallPlan
	 * 
	 * @param algmntId
	 * @param bussUnitId
	 * @param algmntId
	 * @param salesTeamId
	 * @param salesHierId
	 * @param salesPosId
	 * @param callPlanTypeId
	 * 
	 * 
	 * @return custCallPlanId if it exists
	 */
	Integer findCustomerCallPlan(Integer customerId,Long algmntId,Long bussUnitId,Long salesTeamId,Long salesHierId,Long salesPosId,Short tenantId,Short callPlanTypeId);

	/**
	 * This API gets count of call plans for SP
	 * @param userDetails
	 * @return count of call plans for SP
	 */
	Long fetchCountOfcallPlan(Long salesPosId, Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId, Date currentDate);
	
	/**
	 * Retrieve TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * @param custAffId
	 * @param activeFlag
	 * @param tenantId
	 * @return
	 */
	List<TCustCallPlan> findTCustCallPlansByCustAffId(Integer custAffId, Character activeFlag, Short tenantId);
	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan custAlgmntId.
	 * 
	 * @param custAlgmntId
	 *            
	 * @return List<TCustCallPlan>
	 */
	List<TCustCallPlan> findTCustCallPlansByCustId(CustomerAlignment customerAlignment,short tenantId);
	/**
	 * Retrieve an TCustCallPlan object based on given updateTCustAlgmnt 
	 * 
	 * @param updateTCustAlgmnt
	 *            
	 * @return  List<TCustCallPlan>
	 */
	List<TCustCallPlan> findTCustCallPlanByTCustAlgmntObj(TCustAlgmnt updateTCustAlgmnt, Short tenantId);

	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan customerId.
	 * 
	 * @param customerId
	 * @param custAlgmntId
	 * @param algmntId
	 * @param bussUnitId
	 * @param salesTeamId
	 * @param tenantId
	 * @param custAffId
	 * @param flag
	 * 
	 * @return an tCustCallPlansList
	 */
	public List<TCustCallPlan> getTCustCall(Long custAlgmntId, Integer customerId,Long algmntId, Long bussUnitId, Long salesTeamId,Short tenantId, Character flag,Short type, Integer custAffId);
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	
	public List<TCustCallPlan> getCommitedCallPlan(List<Long> custAlgmntList,Short callPlanType,Short tenantId);
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	List<TCustCallPlan> findTCustCallPlansByCustIdAndCustAffId(Integer custId,Integer custAffId, Character activeFlag,Short tCallPlanType, Short tenantId);
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	List<TCustCallPlan> findActiveCustCallPlansByCustAlgmntId(List<Long> custAlIds,short tenantId);
	/**
	 * Retrieve an object based on given custAlgmntIds
	 * 
	 * @param custAlgmntIds
	 * @param customerIds
	 * @param algmntId
	 * @param bussUnitId
	 * @param tenantId
	 * @param flag
	 * @param type
	 * @param custAffIds
	 * 
	 * 
	 * @return tCustCallPlansList
	 */
	List<Object[]> getCustPLCalls(List<Long> custAlgmntIds,List<Integer> customerIds, Long algmntId, Long bussUnitId,Long salesTeamId, Short tenantId, Character flag, Short type,	List<Integer> custAffIds);
	/**
	 * Retrieve an getCallDirInfo 
	 * 
	 * @param custAlgmntList
	 * @param callPlanType
	 * @param tenantId
	 *          
	 * @return object of TCustCallDIRInfo
	 */
	List<Object[]> getCallDirInfo(List<Long> custAlgmntList,Short callPlanType, Short tenantId, Boolean isCalDir);
	/**
	 * Retrieve an getCallDirInfo
	 * 
	 * @param custCallPlanIds
	 * 
	 * 
	 * @return object of CallDirInfoByCustCalPlanId
	 */
	
	List<Object[]> getCallDirInfo(List<Integer> custCallIds);
	/**
	 * Retrieve an ProdForCallDirInf
	 * 
	 * @param custCallPlanIds
	 * 
	 * 
	 * @return object of ProdForCallDirInfoByCustCalPlanId
	 */
	List<Object[]> getProdForCallDirInfo(List<Integer> custCallIds);
	/**
	 * Retrieve an getPlannedCallsForTier.
	 * 
	 * @param chngReqStatusId
	 * @param custcallplanchngreqdetStatusId
	 * @param salesPosId
	 * 
	 * @return an ObjectPlannedCallsForTiers
	 */
	List<Object[]> getPlannedCallsForTier(Integer chngReqStatusId, Integer custcallplanchngreqdetStatusId, Long salesPosId);

	
	/**
	 * Gets the customer call plan details.
	 *
	 * @param tCustCallPlan the t cust call plan
	 * @return the customer call plan details
	 */
	List<TCustCallPlan> getCustomerCallPlanDetails(TCustCallPlan tCustCallPlan);
	/**
	 * @param CustAlId
	 * @param tenantId
	 * @return custCallPlanId
	 * 
	 */
	List<TCustCallPlan> FindCallPlanByCustAlId(Long customerAlId,short tenantId);
	/**
	 * @param CustAlId
	 * @param tenantId
	 * @return custCallPlanId
	 * 
	 */
	List<TCustCallPlan> FindCallPlanByCustAlIdForEdit(Long customerAlignmentId,
			short tenantId);



}
