package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.core.entity.TChngReqTrack;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.peg.core.common.SearchFilter;

// TODO: Auto-generated Javadoc
/**
 * Interface represents API's of TChngReq DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TChngReqDAO {

	/**
	 * Stores a new TChngReq entity object in to the persistent store.
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be persisted
	 * @return tChngReq Persisted TChngReq object
	 */
	TChngReq createTChngReq(TChngReq tChngReq);

	/**
	 * Deletes a TChngReq entity object from the persistent store.
	 * 
	 * @param chngReqId
	 *            the chng req id
	 */
	void deleteTChngReq(Long chngReqId);

	/**
	 * Updates a TChngReq entity object in to the persistent store.
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be updated
	 * @return tChngReq Persisted TChngReq object
	 */
	TChngReq updateTChngReq(TChngReq tChngReq);

	/**
	 * Retrieve an TChngReq object based on given chngReqId.
	 * 
	 * @param chngReqId
	 *            the primary key value of the TChngReq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReq findTChngReqById(Long chngReqId);

	/**
	 * Retrieve TChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReq> findTChngReqs(SearchFilter<TChngReq> searchFilter);

	/**
	 * Count TChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqs(SearchFilter<TChngReq> searchFilter);

	/**
	 * Retrieve TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReq> getTChngReqsByTChngReqConfig(
			SearchFilter<TChngReqConfig> searchFilter);

	/**
	 * Count TChngReq based on given search criteria using JPA named Query. The
	 * search criteria is of TChngReqConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqsByTChngReqConfig(
			SearchFilter<TChngReqConfig> searchFilter);

	/* Added BY Ashis */

	/**
	 * Gets the change request by status.
	 * 
	 * @param params
	 *            the params
	 * @return the change request by status
	 */
	List<TChngReq> getChangeRequestByStatus(List<Object> params);

	/* Added BY Mansoor */
	/**
	 * Gets the t cust algn details.
	 * 
	 * @param crId
	 *            the cr id
	 * @param tenantId
	 *            the tenant id
	 * @return the t cust algn details
	 */
	List<Object[]> getTCustAlgnDetails(long crId, Short tenantId,
			String localeId);

	/**
	 * Find all my chng request.
	 *
	 * @param spList the sp list
	 * @return the list
	 */
	List<Object[]> findAllMyChngRequest(List<Long> spList);

	/**
	 * Gets the t prd details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t prd details
	 */
	List<Object[]> getTPrdDetails(long crId, short tenantId);

	/**
	 * Fetch transction details.
	 *
	 * @param dataList the data list
	 * @param query the query
	 * @return the list
	 */
	List<Object> fetchTransctionDetails(List<Object> dataList, String query);
	
	/**
	 * Fetch change request based on role.
	 *
	 * @param spList the sp list
	 * @param shList the sh list
	 * @param statusList the status list
	 * @param localeId the locale id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> fetchChangeRequestBasedOnRole(Long spList,Long shList, List statusList, String localeId, Short tenantId);

	/**
	 * Fetch t cust aff transction details.
	 *
	 * @param crId the cr id
	 * @param custAffList the cust aff list
	 * @param detailIdList the detail id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object> fetchTCustAffTransctionDetails(Long crId,List<Integer> custAffList,List<Integer> detailIdList, Short tenantId);
	
	/**
	 * Gets the t cust affltn details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t cust affltn details
	 */
	List<Object[]> getTCustAffltnDetails(long crId, short tenantId);

	/**
	 * Gets the t sales pos details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t sales pos details
	 */
	List<Object[]> getTSalesPosDetails(long crId, short tenantId);

	/**
	 * Find all my chng request.
	 *
	 * @param spList the sp list
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	public List<Object[]> findAllMyChngRequest(Long spList, short tenantId,String localeId);

	/**
	 * Gets the t call pln details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t call pln details
	 */
	List<Object[]> getTCallPlnDetails(long crId, short tenantId);

	/**
	 * Gets the t move reporting details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t move reporting details
	 */
	List<Object[]> getTMoveReportingDetails(long crId, short tenantId);

	/**
	 * Added by 409793 for fetching "REQUEST FOR MY APPROVAL" tab details.
	 *
	 * @param salesPositionID the sales position id
	 * @param salesHiearchyID the sales hiearchy id
	 * @param paramList the param list
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param tenantID the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	public List<Object[]> fetchTChngReqApprCRBySalesPositionID(
			long salesPositionID, long salesHiearchyID, List<Integer> paramList,int index,int noOfResults,short tenantID,String localeId);

	/**
	 * Gets the t cust algn details by target flag.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @param detailList the detail list
	 * @return the t cust algn details by target flag
	 */
	List<Object[]> getTCustAlgnDetailsByTargetFlag(long crId, Short tenantId,
			List<Integer> detailList);

	/**
	 * Gets the t cust trans details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the t cust trans details
	 */
	List<Object[]> getTCustTransDetails(long crId, Short tenantId);

	/**
	 * Gets the prod trans details.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the prod trans details
	 */
	List<Object[]> getProdTransDetails(long crId, Short tenantId);
	
    /**
     * Gets the t zip trans details.
     *
     * @param crId the cr id
     * @param tenantId the tenant id
     * @param triggerId the trigger id
     * @return the t zip trans details
     */
    List<Object[]> getTZipTransDetails(long crId, Short tenantId,int triggerId);

	/**
	 * Fetch all my chng request.
	 *
	 * @param salesPositionIDS the sales position ids
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param statusIds the status ids
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @param paStatus the pa status
	 * @return the list
	 */
	public List<Object[]> fetchAllMyChngRequest(Set<Long> salesPositionIDS,int index,int noOfResults,List<Integer> statusIds,Short tenantId,String localeId,Integer paStatus);

	/**
	 * Gets the cRSP end date.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the cRSP end date
	 */
	List<Object> getCRSPEndDate(long crId, short tenantId);

	/**
	 * Gets the cR req sh id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the cR req sh id
	 */
	List<Object> getCRReqSHId(long salesPosId, short tenantId);
 
    /**
     * Fetch cust aff trans details.
     *
     * @param crId the cr id
     * @param tenantId the tenant id
     * @return the list
     */
    List<Object[]> fetchCustAffTransDetails(long crId, Short tenantId);
    
    /**
     * Gets the employee mail ids.
     *
     * @param salesPosId the sales pos id
     * @param tenantId the tenant id
     * @return the employee mail ids
     */
    List<Object> getEmployeeMailIds(Long salesPosId, Short tenantId);
    
//Added For CR Locking
    
    /**
	 * Gets the sP pending cr.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHiearchyID the sales hiearchy id
	 * @param triggerList the trigger list
	 * @param statusList the status list
	 * @param queryName the query name
	 * @param tenantId the tenant id
	 * @return the sP pending cr
	 */
	Object getSPPendingCR(Long salesPositionId, Long salesHiearchyID,
                     List<Integer> triggerList, List<Integer> statusList,
                     String queryName ,Short tenantId);

    /**
     * Gets the prd pending cr.
     *
     * @param salesPositionId the sales position id
     * @param salesHiearchyID the sales hiearchy id
     * @param triggerList the trigger list
     * @param statusList the status list
     * @param prdAlignmentId the prd alignment id
     * @param queryName the query name
     * @return the prd pending cr
     */
    Object getPrdPendingCR(Long salesPositionId, Long salesHiearchyID,
                     List<Integer> triggerList, List<Integer> statusList,
                     List<Long> prdAlignmentId, String queryName);

   /**
    * Gets the prd pending cr by prd id.
    *
    * @param salesPositionId the sales position id
    * @param salesHiearchyID the sales hiearchy id
    * @param triggerList the trigger list
    * @param statusList the status list
    * @param prdId the prd id
    * @param queryName the query name
    * @return the prd pending cr by prd id
    */
   Object getPrdPendingCRByPrdId(Long salesPositionId, Long salesHiearchyID,
                 List<Integer> triggerList, List<Integer> statusList,
                 List<String> prdId, String queryName);
   
   
   //Added For SP CR Locking
   /**
    * Gets the sP other locked triggers.
    *
    * @param salesPositionId the sales position id
    * @param salesHiearchyID the sales hiearchy id
    * @param triggerList the trigger list
    * @param statusList the status list
    * @param queryName the query name
    * @param tenantId the tenant id
    * @return the sP other locked triggers
    */
   Object getSPOtherLockedTriggers(Long salesPositionId, Long salesHiearchyID,
                 List<Integer> triggerList, List<Integer> statusList,
                 String queryName  ,Short tenantId) ;
   
   //Added For Move Reporting CR Locking
   /**
    * Gets the move rep other pending cr.
    *
    * @param salesPositionId the sales position id
    * @param salesHiearchyID the sales hiearchy id
    * @param triggerList the trigger list
    * @param statusList the status list
    * @param queryName the query name
    * @param tenantId the tenant id
    * @return the move rep other pending cr
    */
   Object getMoveRepOtherPendingCR(Long salesPositionId, Long salesHiearchyID,
                       List<Integer> triggerList, List<Integer> statusList,
                       String queryName ,Short tenantId );

   /**
    * Fetch my req tab count.
    *
    * @param childSalesPosSet the child sales pos set
    * @param tenantId the tenant id
    * @return the object
    */
   public Object fetchMyReqTabCount(Set<Long> childSalesPosSet, Short tenantId);
   
   /**
    * Fetch req fr my appr tab count.
    *
    * @param salesPositionId the sales position id
    * @param salesHiearchyID the sales hiearchy id
    * @param statusList the status list
    * @param tenantId the tenant id
    * @return the object
    */
   public Object fetchReqFrMyApprTabCount(Long salesPositionId, Long salesHiearchyID, List<Integer> statusList, Short tenantId);
   
   /**
    * Gets the t call pln details by id.
    *
    * @param crId the cr id
    * @param tenantId the tenant id
    * @return the t call pln details by id
    */
   public List<Object[]> getTCallPlnDetailsById(Long crId,Short tenantId);
   
   /**
    * Gets the cR trigger and feature by id.
    *
    * @param crId the cr id
    * @param tenantId the tenant id
    * @return the cR trigger and feature by id
    */
   public List<Object[]> getCRTriggerAndFeatureById(Long crId,Short tenantId);
   
   /**
    * Gets the sP primary emp user id.
    *
    * @param spId the sp id
    * @param shId the sh id
    * @param algmntId the algmnt id
    * @param bussId the buss id
    * @param salesteamId the salesteam id
    * @param tenantId the tenant id
    * @return the sP primary emp user id
    */
   public List<Object[]> getSPPrimaryEmpUserId(Long spId,Long shId,Long algmntId,Long bussId,Long salesteamId,Short tenantId);

   /**
    * Fetch chng req num.
    *
    * @param grpName the grp name
    * @param table the table
    * @param valueCol the value col
    * @param pKeyColumn the key column
    * @return the long
    */
   public Long fetchChngReqNum(String grpName,String table,String valueCol,String pKeyColumn);
  
   /**
    * Fetch chng req and config details.
    *
    * @param crId the cr id
    * @param tenatId the tenat id
    * @return the list
    */
   public List<Object[]> fetchChngReqAndConfigDetails(List<Long> crId,Short tenatId);
  
   /**
    * Fetch inactive c rs.
    *
    * @param tenantId the tenant id
    * @return the list
    */
   public List<Object[]> fetchInactiveCRs( short tenantId);
  
   /**
    * Update flag.
    *
    * @param crList the cr list
    * @return the boolean
    */
   public Boolean  updateFlag(Set<Long> crList);
  	
   /**
    * Fetch sp code.
    *
    * @param crIdlist the cr idlist
    * @param tenantId the tenant id
    * @return the list
    */
   public List<Object[]> fetchSpCode(List<Long> crIdlist, Short tenantId);
  
   /**
    * Fetch active c rs for trigger.
    *
    * @param triggerId the trigger id
    * @param statusId the status id
    * @param tenantId the tenant id
    * @param algmntId the algmnt id
    * @param bussId the buss id
    * @param salesteamId the salesteam id
    * @return the integer
    */
   Integer fetchActiveCRsForTrigger(Integer triggerId, Integer statusId, Short tenantId,
		  Long algmntId,Long bussId,Long salesteamId);
      
      /**
       * Gets the trigger id list.
       *
       * @param tenantId the tenant id
       * @return the trigger id list
       */
    public List<Object[]> getTriggerIdList( Short tenantId);
      
  /**
   * Gets the pending status cr.
   *
   * @param algmntId the algmnt id
   * @param bussUnitId the buss unit id
   * @param salesTeamId the sales team id
   * @param tenantId the tenant id
   * @return the pending status cr
   */
	public List<Object[]> getPendingStatusCR(Long algmntId,Long bussUnitId,Long salesTeamId,short tenantId);
  	
  /**
   * Update tchng req.
   *
   * @param queryParams the query params
   * @return the int
   */
	public int updateTchngReq(List<Object> queryParams) ;

	/**
	 * Find all t chng req by customer.
	 *
	 * @param customerId the customer id
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TChngReq> findAllTChngReqByCustomer(Integer customerId, Long salesPositionId, Long salesHierchyId, Long algmntId, Long bussUnitId,
			Long salesTeamId, short tenantId);

	/**
	 * Find all t chng req.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TChngReq> findAllTChngReq(Long salesPositionId, Long salesHierchyId, short tenantId);
	
	/**
	 * Find all t chng req for approval.
	 *
	 * @param salesPositionId the sales position id
	 * @param salesHierchyId the sales hierchy id
	 * @param tenantId the tenant id
	 * @param statusId the status id
	 * @return the list
	 */
	public List<TChngReq> findAllTChngReqForApproval(Long salesPositionId, Long salesHierchyId, short tenantId, int statusId);
    
	/**
	 * Retrieve TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrack type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReq> list of TChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TChngReq> getTChngReqsByTChngReqTrack(SearchFilter<TChngReqTrack> searchFilter);

	/**
	 * Count TChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrack type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqsByTChngReqTrack(SearchFilter<TChngReqTrack> searchFilter);
	
	/**
	 * Gets the change request by status.
	 *
	 * @param raiseSalesPostion the raise sales postion
	 * @param raiseSalesHierarchyId the raise sales hierarchy id
	 * @param status the status
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the change request by status
	 */
	List<TChngReq> getChangeRequestByStatus(Long reqSalesPostion, Long reqSalesHierarchyId,Long raiseSalesPostion, Long raiseSalesHierarchyId, Integer status, Integer triggerId, Short tenantId); 
	
	
	/**
	 * Gets the change request by status.
	 *
	 * @param raiseSalesPostion the raise sales postion
	 * @param raiseSalesHierarchyId the raise sales hierarchy id
	 * @param status the status
	 * @param triggerId the trigger id
	 * @param tenantId the tenant id
	 * @return the change request by status
	 */
	List<Long> getChangeRequestIdByStatus(Long reqSalesPostion, Long reqSalesHierarchyId,Long raiseSalesPostion, Long raiseSalesHierarchyId, Integer status, Integer triggerId, Short tenantId); 
	
	/**
	 * Retrieve TChngReq based on given parent change request id named Query.
	 * @param chngReqId
	 * @return List
	 */
	List<TChngReq> findTChngReqByParentChangeRequestId(Long chngReqId);
	
	/**
	 * Updates a TChngReq entity object in to the persistent store.
	 * 
	 * @param tChngReq
	 *            TChngReq Entity object to be updated
	 * @return tChngReq Persisted TChngReq object
	 */
	List<TChngReq> updateTChngReq(List<TChngReq> tChngReqList);
	
	
	/**
	 * @param chngReqId
	 * @param tenantId
	 * @return List
	 */
	List<Object[]> findCustomerAlignmentSalesPostionIdByChangeRequestId(Long chngReqId,short tenantId);
	
	/**
	 * @param chngReqId
	 * @param tenantId
	 * @return List
	 */
	List<Object[]> findZipAlignmentSalesPostionIdByChangeRequestId(Long chngReqId,short tenantId);
	
	
	/**
	 * @param chngReqId
	 * @param tenantId
	 * @return
	 */
	List<Object[]> findCustomerIdByChangeRequestId(Long chngReqId,short tenantId);
	
        
     /**
      * Update cr status.
      *
      * @param changeRequestId the change request id
      * @param statusId the status id
      * @param userDetails the user details
      */
     void updateCRStatus(long changeRequestId, Integer statusId, Integer userId, Short tenantId);
     
     
     /**
      * Mark change request as dirty.
      *
      * @param spId the sp id
      * @param userDetails the user details
      * @return the list
      */
     List<TChngReq> markChangeRequestAsDirty(Long spId, Short tenantId);
     
     /**
     * @param chngReqId
     * @param tenantId
     * @return List<Object[]>
     */
    List<Object[]> findParentChangeRequestIdByChildChangeRequestId(Long chngReqId, short tenantId);
     
	 /** Changes For Update Chnage Request Status -- start **/
     List<TChngReq> findChangeRequestByStatusId(List<Integer> statusIds , Short tenantId);
     /** Changes For Update Chnage Request Status -- end **/
     
     
     
     /**
 	 * Gets the change request by status.
 	 *
 	 * @param raiseSalesPostion the raise sales postion
 	 * @param raiseSalesHierarchyId the raise sales hierarchy id
 	 * @param status the status
 	 * @param triggerId the trigger id
 	 * @param tenantId the tenant id
 	 * @return the change request by status
 	 */
 	List<TChngReq> getChangeRequestByStatusForSubmit(Long reqSalesPostion, Long reqSalesHierarchyId,Long raiseSalesPostion, Long raiseSalesHierarchyId, Integer status, Integer triggerId, Short tenantId); 


     /**
      * Gets the cR count by sps and status.
      *
      * @param spIds the sp ids
      * @param statusId the status id
      * @param tenantId the tenant id
      * @return the cR count by sps and status
      */
     Integer getCRCountBySpsAndStatus(List<Long> spIds, Integer statusId, Short tenantId);
     
     
     /**
     * @param chngReqId
     * @param alignmentFlag
     * @param tenantId
     * @return List<TTerrZipAlgmnt>
     */
    List<TTerrZipAlgmnt> findZipAlignmentByChangeRequestId(Long chngReqId,Character alignmentFlag,Short tenantId);
    
    
    /**
     * @param chngReqId
     * @param alignmentFlag
     * @param tenantId
     * @return List<TTerrZipAlgmnt>
     */
    List<Object> findMirrorZipAlignmentByChangeRequestId(Long chngReqId,Character alignmentFlag,Short tenantId);
    
    List<Object[]> findCRIdsNotInGivenCRIdList(Set<Long> crIds , Short tenantId);
    
    Boolean updateFlagAsInactive(Set<Long> crList, Long timeDiffForInitiate, short tenantId);
     
}
