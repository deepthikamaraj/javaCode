package com.cognizant.opserv.sp.core.dao;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqAppr;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TChngReqAppr DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */

public interface TChngReqApprDAO {

	/**
	 * Stores a new TChngReqAppr entity object in to the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be persisted
	 * @return tChngReqAppr Persisted TChngReqAppr object
	 */
	TChngReqAppr createTChngReqAppr(TChngReqAppr tChngReqAppr);

	/**
	 * Deletes a TChngReqAppr entity object from the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be deleted
	 */
	void deleteTChngReqAppr(Integer tChngReqApprId);

	/**
	 * Updates a TChngReqAppr entity object in to the persistent store
	 * 
	 * @param tChngReqAppr
	 *            TChngReqAppr Entity object to be updated
	 * @return tChngReqAppr Persisted TChngReqAppr object
	 */
	TChngReqAppr updateTChngReqAppr(TChngReqAppr tChngReqAppr);

	/**
	 * Update t chng req appr.
	 *
	 * @param entityList the entity list
	 * @return the list
	 */
	List<TChngReqAppr> updateTChngReqAppr(final List<TChngReqAppr> entityList);
	/**
	 * Retrieve an TChngReqAppr object based on given TChngReqApprId.
	 * 
	 * @param tChngReqApprId
	 *            the primary key value of the TChngReqAppr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TChngReqAppr findTChngReqApprById(Integer tChngReqApprId);

	/**
	 * Retrieve TChngReqAppr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqAppr> list of TChngReqApprs if it exists against
	 *         given criteria. Returns null if not found
	 */
	List<TChngReqAppr> findTChngReqApprs(SearchFilter<TChngReqAppr> searchFilter);

	/**
	 * Count TChngReqAppr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqApprs(SearchFilter<TChngReqAppr> searchFilter);

	/**
	 * Retrieve TChngReqAppr based on given search criteria using JPA named
	 * Query. The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqAppr> list of TChngReqApprs if it exists against
	 *         given criteria. Returns null if not found
	 */
	List<TChngReqAppr> getTChngReqApprsByTChngReq(
			SearchFilter<TChngReq> searchFilter);

	/**
	 * Count TChngReqAppr based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTChngReqApprsByTChngReq(SearchFilter<TChngReq> searchFilter);

	/**
	 * Fetch t chng req apprs.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	List<TChngReqAppr> fetchTChngReqApprs(
			SearchFilter<TChngReqAppr> searchFilter);

	
	/**
	 * Fetch t chng req apprs type by status.
	 *
	 * @param crId the cr id
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the long
	 */
	long fetchTChngReqApprsTypeByStatus(long crId, int status,
			short tenantId);

	/**
	 * Gets the max appr id by cr id.
	 *
	 * @param params the params
	 * @return the max appr id by cr id
	 */
	List<Object> getMaxApprIdByCRId(List<Object> params);

	/**
	 * Added by 409793 for fetching "TARGET AND SOURCE APPR LIST" tab details
	 */
	
	 /**
 	 * Fetch t chng req details.
 	 *
 	 * @param crId the cr id
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
 	List<TChngReqAppr> fetchTChngReqDetails(Long crId, 
			Short tenantId);

	 
	 /**
 	 * Fetch t chng req appr.
 	 *
 	 * @param crId the cr id
 	 * @param salesHeirId the sales heir id
 	 * @param salesPosId the sales pos id
 	 * @param targetflag the targetflag
 	 * @param tenantId the tenant id
 	 * @return the list
 	 */
 	public List<TChngReqAppr> fetchTChngReqAppr(Long crId,
				Long salesHeirId, Long salesPosId,char targetflag, Short tenantId);
	
	/**
	 * Gets the status.
	 *
	 * @param crId the cr id
	 * @param status the status
	 * @param tenantId the tenant id
	 * @return the status
	 */
	long getStatus(long crId, int status,
			short tenantId);

	/**
	 * Fetch t chng req appr detail list.
	 *
	 * @param crId the cr id
	 * @param statusId the status id
	 * @param targetFlag the target flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TChngReqAppr> fetchTChngReqApprDetailList(Long crId,int statusId,Character targetFlag, Short tenantId);

   /**
    * Gets the change request by flag.
    *
    * @param params the params
    * @return the change request by flag
    */
   List<Object[]> getChangeRequestByFlag(List<Object> params);
   
   /**
    * Fetch appr status.
    *
    * @param crId the cr id
    * @param tenantId the tenant id
    * @param localId the local id
    * @param cnclStsId the cncl sts id
    * @return the list
    */
   public List<Object[]> fetchApprStatus(long crId,short tenantId,String localId,int cnclStsId);

     /**
      * Fetch t chng req in active appr list.
      *
      * @param crId the cr id
      * @param status the status
      * @param salesHeirId the sales heir id
      * @param salesPosId the sales pos id
      * @param tenantId the tenant id
      * @return the list
      */
     List<TChngReqAppr> fetchTChngReqInActiveApprList(Long crId, int status,Long salesHeirId, Long salesPosId,Short tenantId);


    /**
     * Gets the cust aff t flag.
     *
     * @param params the params
     * @return the cust aff t flag
     */
    List<Object[]> getCustAffTFlag(List<Object> params); 
    
    /**
     * Fetch appr trans count.
     *
     * @param crIdList the cr id list
     * @param statusId the status id
     * @param spId the sp id
     * @param shId the sh id
     * @param tenantId the tenant id
     * @return the list
     */
    public List<Object[]> fetchApprTransCount(List<Long> crIdList, int statusId, Long spId,Long shId,
			short tenantId);
	
	/**
	 * Fetch aff count.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @param spId the sp id
	 * @param shId the sh id
	 * @return the list
	 */
	public List<Object[]> fetchAffCount(List<Long> crId, short tenantId,long spId,long shId);
	
	/**
	 * Update flag.
	 *
	 * @param crList the cr list
	 * @return the boolean
	 */
	public Boolean updateFlag(Set<Long> crList);
	
	/**
	 * Fetch t chng req appr list.
	 *
	 * @param crId the cr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> fetchTChngReqApprList(Long crId,Short tenantId) ;
	
	/**
	 * Fetch all pending approval cr.
	 *
	 * @param crId the cr id
	 * @param statusId the status id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TChngReqAppr> fetchAllPendingApprovalCR(Long crId, Integer statusId,Short tenantId );
	
	/**
	 * Count t chng req apprs.
	 *
	 * @return the object
	 */
	Object countTChngReqApprs();
	
	/**
	 * Creates the t chng req appr.
	 *
	 * @param tChngReqApprList the t chng req appr list
	 * @return the list
	 */
	List<TChngReqAppr> createTChngReqAppr(List<TChngReqAppr> tChngReqApprList);

	/**
	 * @param crId
	 * @param tenantId
	 * @return
	 */
	List<TChngReqAppr> fetchCRApproverDetails(Long crId, Short tenantId);

	/**
	 * Gets the approvers count by sps and status.
	 *
	 * @param spIds the sp ids
	 * @param id the id
	 * @param tenantId the tenant id
	 * @return the approvers count by sps and status
	 */
	Integer getApproversCountBySpsAndStatus(List<Long> spIds, Integer id, Short tenantId);
	
	/**
	 * Gets the target approver status.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the target approver status
	 */
	Integer getTargetApproverStatus(Long chngReqId,Character targetAppvrFlg,Short tenantId);
	
	/**
	 * Find approver comments.
	 *
	 * @param chngReqId the chng req id
	 * @param targetAppvrFlg the target appvr flg
	 * @param tenantId the tenant id
	 * @return the string
	 */
	String findApproverComments(Long chngReqId,Character targetAppvrFlg,Short tenantId);
}
