package com.cognizant.opserv.sp.core.dao;


import java.util.Date;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrd DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdDAO {

	/**
	 * Stores a new TPrd entity object in to the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be persisted
	 * @return tPrd Persisted TPrd object
	 */
	TPrd createTPrd(TPrd tPrd);

	/**
	 * Deletes a TPrd entity object from the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be deleted
	 */
	void deleteTPrd(Long prdId);

	/**
	 * Updates a TPrd entity object in to the persistent store
	 * 
	 * @param tPrd
	 *            TPrd Entity object to be updated
	 * @return tPrd Persisted TPrd object
	 */
	TPrd updateTPrd(TPrd tPrd);

	/**
	 * Retrieve an TPrd object based on given prdId.
	 * 
	 * @param prdId
	 *            the primary key value of the TPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrd findTPrdById(Long prdId);

	/**
	 * Retrieve TPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrd> list of TPrds if it exists against given criteria.
	 *         Returns null if not found
	 */
	List<TPrd> findTPrds(SearchFilter<TPrd> searchFilter);

	/**
	 * Count TPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrds(SearchFilter<TPrd> searchFilter);

	/**
	 * findrecentlyAddedProduct
	 * 
	 * @param algId
	 * @param bussId
	 * @param salesTeamId
	 * @param salesPosId
	 * @param salesHierId
	 * @param tenantId
	 * @param flag
	 * @param currentDate
	 * @return
	 */
	List<Object[]> findrecentlyAddedProduct(Long algId, Long bussId,
			Long salesTeamId, Long salesPosId, Long salesHierId,
			Short tenantId, Character flag, Date currentDate);

	/**
	 * productsWithExtAttr
	 * 
	 * @param searchFilter
	 * @return
	 */
	List<TPrd> productsWithExtAttr(SearchFilter<TPrd> searchFilter);
	
	

	Long findProductCount(SearchFilter<TPrd> filter, Short tenantId);

	List<TPrd> findProductList(SearchFilter<TPrd> filter, Short tenantId,
			int start, int size);
	
	/**
	 * findTPrdsByPrdId By PrdId and tenantId
	 * @param productId
	 * @return List of TPrd
	 */
	List<TPrd> findTPrdsByPrdId(List<Long> productIds, Short tenantId);

}
