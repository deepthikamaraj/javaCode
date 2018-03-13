package com.cognizant.opserv.sp.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.peg.core.common.SearchFilter;


/**
 * Interface represents API's of TPrdAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdAlgmntDAO {

	/**
	 * Stores a new TPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be persisted
	 * @return tPrdAlgmnt Persisted TPrdAlgmnt object
	 */
	TPrdAlgmnt createTPrdAlgmnt(TPrdAlgmnt tPrdAlgmnt);

	/**
	 * Deletes a TPrdAlgmnt entity object from the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be deleted
	 */
	void deleteTPrdAlgmnt(Long prdAlgmntId);

	/**
	 * Updates a TPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tPrdAlgmnt
	 *            TPrdAlgmnt Entity object to be updated
	 * @return tPrdAlgmnt Persisted TPrdAlgmnt object
	 */
	TPrdAlgmnt updateTPrdAlgmnt(TPrdAlgmnt tPrdAlgmnt);

	/**
	 * Retrieve an TPrdAlgmnt object based on given prdAlgmntId.
	 * 
	 * @param prdAlgmntId
	 *            the primary key value of the TPrdAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdAlgmnt findTPrdAlgmntById(Long prdAlgmntId);

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmnt> findTPrdAlgmnts(SearchFilter<TPrdAlgmnt> searchFilter);

	/**
	 * Count TPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmnts(SearchFilter<TPrdAlgmnt> searchFilter);

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmnt> getTPrdAlgmntsByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Count TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntsByTSalesPos(SearchFilter<TSalesPos> searchFilter);

	/**
	 * Retrieve TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmnt> list of TPrdAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdAlgmnt> getTPrdAlgmntsByTPrd(SearchFilter<TPrd> searchFilter);

	/**
	 * Count TPrdAlgmnt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdAlgmntsByTPrd(SearchFilter<TPrd> searchFilter);
	
	List findProductTemplatesByAlignment(Long productId,Integer boId,Short tenantId);
    List<TPrdAlgmnt> findTPrdAlgmntsByPrdId(Long prdId);
    Long countOfPrdAlgmnts(List paramList);
    void delPrdAlgmnts(Date effEndDt,Short tenantId, Long salesPosId, Long salesHierId, Long alignId, Long buId, Long salesTeamId, Long prdIdStr,Integer updatedBy);
    
    List<TPrdAlgmnt> findTPrdAlgmnts(Short tenantId, Long salesHierId,
			Long bussinessUnitId, Long salesTeamId, Long alignmentId,
			Long salesPosId, Long prdId);

    List<TPrdAlgmnt> findProductsBySalePosId(final Long salesPosId, Long algmntId, Long bussId, Long salesTeamId, int index, int noOfResult,Short tenantId);
	
	List<TPrdAlgmnt> findTPrdAlgmntsForUnAssignSP(Long salesPosId,Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId,boolean flag);
  
   /**
	 * This DAO method fetches count of products assigned for SP
	 * @param salesPosId
	 * @param salesHierId
	 * @param alignmentId
	 * @param bussinessUnitId
	 * @param salesTeamId
	 * @param tenantId
	 * @return
	 */
	Long fetchCountOfPrdForSPandChild(Set<Long> childSP,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId, Date currentDate);
	Long fetchCountOfPrdForSP(Long salesPositionId,Long salesHierId,Short tenantId, Date currentDate);
	
		List<TPrdAlgmnt> findProductsWithAlignmentInfoByList(List<Long> prodIdList, long salePosId, int index, int noOfResult,short tenantId);
		
		List<TPrdAlgmnt> getProductDetailsForProductSearch(SearchFilter<TPrdAlgmnt> searchFilter);
		
		//Added By 407986 To Verify Products are Assigned to SP or not
		List<Object> fetchAssignedProducts(List<String> productIds,Long spId,Long shId, Short tenantId) ;

		List<TPrdAlgmnt> findProductsWithAlignmentInfoByPrdIdList(
				List<Long> prodIdlist, int i, int j, Short tenantId);

		List<TPrdAlgmnt> fetchPrdAlgDtBySalesPosEffEndDt(short tenantId);

		List<TPrdAlgmnt> fetchPrdAlgmntsFrRemoveCr(List<Long> prdAlgIdList,
				short tenantId);

		List<TPrdAlgmnt> fetchPrdALgmntsDtsforValidate(Long prdId, Long spId,
				Long algnmntId, Long bussId, Long salesTeamId,
				Long saleshierId, short tenantId);

		List<TPrdAlgmnt> searchProductsBySalePosId(long salePosId,
				long algmntId, long bussId, long salesTeamId,
				 Short tenantId, SearchFilter<TPrdAlgmnt> searchFilter);

		Long findTPrdAlgmntsForUnAssignSPCount(Long salesPosId,	Long salesHierId, Long alignmentId, Long bussinessUnitId,Long salesTeamId, Short tenantId, boolean flag);
		
		
		
		List<Object[]> findAssignedProducts(List<Long> listOfPrdIds, Long algmntId, String bussUnitId, Long salesTeamId, Long salesPosId, Short tenantId);
		
		List<TPrdAlgmnt> fetchPrdALgmntsDtsByPrdId(Long prdId, short tenantId);

		List<TPrdAlgmnt> getPrdAlListForSP(SalesPosition salesPosition,
				Short tenantId, List<Long> prdIdList);
   
		
		/**
		 * Gets the product names for sales position.
		 *
		 * @param spId the sp id
		 * @param shId the sh id
		 * @param alId the al id
		 * @param buId the bu id
		 * @param stId the st id
		 * @param currentDate the current date
		 * @param tenantId the tenant id
		 * @return the product names for sales position
		 */
		List<String> getProductNamesForSalesPosition(Long spId,Long shId,Date currentDate,Short tenantId);
		
	/**
     * get prdalgmntID by prdId and salesPosId 
	 * @param prdId productId
	 * @param spId salesPosId
	 * @return 
	 */
		Long findTPrdAlgmntByTPrdAndSPId(Long prdId, Long SPId);

	
	/**
	 * Fetch count of prd for sales positions.
	 *
	 * @param salesPosIds the sales pos ids
	 * @param tenantId the tenant id
	 * @return the long
	 */
	Long fetchCountOfPrdForSalesPositions(List<Long> salesPosIds,Short tenantId);

	/**
	 * Gets the product names for sales position.
	 *
	 * @param spIdList the sp id list
	 * @param tenantId the tenant id
	 * @return the product names for sales position
	 */
	List<String> getProductNamesForSalesPositions(List<Long> spIdList,
			Short tenantId);
}
