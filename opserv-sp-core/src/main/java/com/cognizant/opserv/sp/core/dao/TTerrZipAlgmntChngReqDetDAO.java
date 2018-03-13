package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTerrZipAlgmntChngReqDet DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTerrZipAlgmntChngReqDetDAO {

	/**
	 * Stores a new TTerrZipAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be persisted
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet object
	 */
	TTerrZipAlgmntChngReqDet createTTerrZipAlgmntChngReqDet(TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet);

	/**
	 * Deletes a TTerrZipAlgmntChngReqDet entity object from the persistent store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be deleted
	 */
	void deleteTTerrZipAlgmntChngReqDet(Integer zipChngReqId);

	/**
	 * Updates a TTerrZipAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be updated
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet object
	 */
	TTerrZipAlgmntChngReqDet updateTTerrZipAlgmntChngReqDet(TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet);
	
	List<TTerrZipAlgmntChngReqDet> updateTTerrZipAlgmntChngReqDet(List<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDetlist);

	/**
	 * Retrieve an TTerrZipAlgmntChngReqDet object based on given zipChngReqId.
	 * 
	 * @param zipChngReqId
	 *            the primary key value of the TTerrZipAlgmntChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTerrZipAlgmntChngReqDet findTTerrZipAlgmntChngReqDetById(Integer zipChngReqId);

	/**
	 * Retrieve TTerrZipAlgmntChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmntChngReqDet> list of TTerrZipAlgmntChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTerrZipAlgmntChngReqDet> findTTerrZipAlgmntChngReqDets(SearchFilter<TTerrZipAlgmntChngReqDet> searchFilter);

	/**
	 * Count TTerrZipAlgmntChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTerrZipAlgmntChngReqDets(SearchFilter<TTerrZipAlgmntChngReqDet> searchFilter);
	public 	List<TTerrZipAlgmntChngReqDet> fetchDetailList(List<Object> params,String Query);
	
	
	/*Added By 407986*/
	
	
	/**
	 * Gets the zip movement req details.
	 *
	 * @param params the params
	 * @return the zip movement req details
	 */

	List<Object[]> getZipMovementReqDetails(List<Object> params) ;
	
	//Added For ZIP CR Locking
	List<Object[]> getLockedZip(List<String> zipCodes, 
                 List<Integer> triggerList, List<Integer> statusList,
                 String queryName ,Short tenantId,Long algmntId,Long buId,Long stId) ;
	
	
	/**
	 * Stores a new TTerrZipAlgmntChngReqDet entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntChngReqDet
	 *            TTerrZipAlgmntChngReqDet Entity object to be persisted
	 * @return tTerrZipAlgmntChngReqDet Persisted TTerrZipAlgmntChngReqDet object
	 */
	List<TTerrZipAlgmntChngReqDet> createTTerrZipAlgmntChngReqDet(List<TTerrZipAlgmntChngReqDet> tTerrZipAlgmntChngReqDet);

	List<TTerrZipAlgmntChngReqDet> findZipLineItemByChngReqPostalCode(
			String zipCode, Short tenantId, Long chngReqId);
	
	
}
