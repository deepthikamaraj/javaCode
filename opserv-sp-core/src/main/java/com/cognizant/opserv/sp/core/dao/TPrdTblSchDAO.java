package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TPrdTblSch;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TPrdTblSch DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TPrdTblSchDAO {

	/**
	 * Stores a new TPrdTblSch entity object in to the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be persisted
	 * @return tPrdTblSch Persisted TPrdTblSch object
	 */
	TPrdTblSch createTPrdTblSch(TPrdTblSch tPrdTblSch);

	/**
	 * Deletes a TPrdTblSch entity object from the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be deleted
	 */
	void deleteTPrdTblSch(Integer tblId);

	/**
	 * Updates a TPrdTblSch entity object in to the persistent store
	 * 
	 * @param tPrdTblSch
	 *            TPrdTblSch Entity object to be updated
	 * @return tPrdTblSch Persisted TPrdTblSch object
	 */
	TPrdTblSch updateTPrdTblSch(TPrdTblSch tPrdTblSch);

	/**
	 * Retrieve an TPrdTblSch object based on given tblId.
	 * 
	 * @param tblId
	 *            the primary key value of the TPrdTblSch Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TPrdTblSch findTPrdTblSchById(Integer tblId);

	/**
	 * Retrieve TPrdTblSch based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdTblSch> list of TPrdTblSchs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TPrdTblSch> findTPrdTblSchs(SearchFilter<TPrdTblSch> searchFilter);

	/**
	 * Count TPrdTblSch based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTPrdTblSchs(SearchFilter<TPrdTblSch> searchFilter);
	
	 List<TPrdTblSch> getAllTPrdTblSch();
	
	List<TPrdTblSch> findTPrdTblSchByTenantId(final SearchFilter<TPrdTblSch> searchFilter);
	
	List<TPrdTblSch> findTPrdTblSchByTblAlias(final SearchFilter<TPrdTblSch> searchFilter);

}
