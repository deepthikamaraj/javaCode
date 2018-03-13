package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCallDir;
import com.cognizant.opserv.sp.core.entity.TCallDirPrd;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdId;
import com.cognizant.opserv.sp.core.entity.TPrdPrtType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCallDirPrd DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCallDirPrdDAO {

	/**
	 * Stores a new TCallDirPrd entity object in to the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be persisted
	 * @return tCallDirPrd Persisted TCallDirPrd object
	 */
	TCallDirPrd createTCallDirPrd(TCallDirPrd tCallDirPrd);

	/**
	 * Deletes a TCallDirPrd entity object from the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be deleted
	 */
	void deleteTCallDirPrd(TCallDirPrdId tCallDirPrdId);

	/**
	 * Updates a TCallDirPrd entity object in to the persistent store
	 * 
	 * @param tCallDirPrd
	 *            TCallDirPrd Entity object to be updated
	 * @return tCallDirPrd Persisted TCallDirPrd object
	 */
	TCallDirPrd updateTCallDirPrd(TCallDirPrd tCallDirPrd);

	/**
	 * Retrieve an TCallDirPrd object based on given TCallDirPrdId.
	 * 
	 * @param tCallDirPrdId
	 *            the primary key value of the TCallDirPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCallDirPrd findTCallDirPrdById(TCallDirPrdId tCallDirPrdId);

	/**
	 * Retrieve TCallDirPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrd> findTCallDirPrds(SearchFilter<TCallDirPrd> searchFilter);

	/**
	 * Count TCallDirPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirPrds(SearchFilter<TCallDirPrd> searchFilter);

	/**
	 * Retrieve TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDir type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrd> getTCallDirPrdsByTCallDir(SearchFilter<TCallDir> searchFilter);

	/**
	 * Count TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDir type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirPrdsByTCallDir(SearchFilter<TCallDir> searchFilter);

	/**
	 * Retrieve TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrd> getTCallDirPrdsByTPrdPrtType(SearchFilter<TPrdPrtType> searchFilter);

	/**
	 * Count TCallDirPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCallDirPrdsByTPrdPrtType(SearchFilter<TPrdPrtType> searchFilter);
	

	/**
	 * Retrieve TCallDirPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param prdId
	 *            -prdId
	 * @return List<TCallDirPrd> list of TCallDirPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCallDirPrd> findTCallDirPrdsByPrdId(Long prdId);

}
