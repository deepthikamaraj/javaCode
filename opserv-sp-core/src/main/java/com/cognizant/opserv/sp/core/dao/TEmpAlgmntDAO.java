package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.peg.core.common.SearchFilter;
/**
 * Interface represents API's of TEmpAlgmnt DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TEmpAlgmntDAO {

	/**
	 * Stores a new TEmpAlgmnt entity object in to the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be persisted
	 * @return tEmpAlgmnt Persisted TEmpAlgmnt object
	 */
	TEmpAlgmnt createTEmpAlgmnt(TEmpAlgmnt tEmpAlgmnt);

	/**
	 * Deletes a TEmpAlgmnt entity object from the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be deleted
	 */
	void deleteTEmpAlgmnt(Long empAlgmntId);

	/**
	 * Updates a TEmpAlgmnt entity object in to the persistent store
	 * 
	 * @param tEmpAlgmnt
	 *            TEmpAlgmnt Entity object to be updated
	 * @return tEmpAlgmnt Persisted TEmpAlgmnt object
	 */
	TEmpAlgmnt updateTEmpAlgmnt(TEmpAlgmnt tEmpAlgmnt);

	/**
	 * Retrieve an TEmpAlgmnt object based on given empAlgmntId.
	 * 
	 * @param empAlgmntId
	 *            the primary key value of the TEmpAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TEmpAlgmnt findTEmpAlgmntById(Long empAlgmntId);

	/**
	 * Retrieve TEmpAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmnt> list of TEmpAlgmnts if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TEmpAlgmnt> findTEmpAlgmnts(SearchFilter<TEmpAlgmnt> searchFilter);

	/**
	 * Count TEmpAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTEmpAlgmnts(SearchFilter<TEmpAlgmnt> searchFilter);

	/**
	 * Gets the t emp algmnts by staff id.
	 *
	 * @param staffId the staff id
	 * @param tenantId the tenant id
	 * @return the t emp algmnts by staff id
	 */
	List<TEmpAlgmnt> getTEmpAlgmntsByStaffId(Integer staffId,Short tenantId);
	
	/**
	 * Find primary t emp algmntfor all sp.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param allocTypeId the alloc type id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<TEmpAlgmnt> findPrimaryTEmpAlgmntforAllSp(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer allocTypeId,
			Short tenantId);
	List<TEmpAlgmnt>findTEmpAlgmntForSalesPosId(Long spId,Long spHierId,Long algmntId,Long buId,Long stId,Short tenantId);

	
	/**
	 * Find emp algmnt.
	 *
	 * @param staffid the staffid
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesPosId the sales pos id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]>findEmpAlgmnt(List<Integer>staffid,Long algmntId, Long bussUnitId,Long salesTeamId, Long salesPosId, Long salesHierId,Short tenantId);
	
	List<Object[]>findEmpAlgmntByIds(Long staffid,Long algmntId, Long bussUnitId,Long salesTeamId, Long salesPosId, Long salesHierId,Short tenantId);

	List<Object[]> findPrimaryTEmpAlgmnt(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesPosId,Long salesHierId, Integer primaryAllocId, Integer value1, Short tenantId);
	List<String> fetchEmailIdFrEmp(List<Long> spIds, Short tenantId);

	List<Object[]> findEmpDetails(Long algmntID, Long buID, Long stID,
			Long spID, Long shID, 
			Short tenantId, String localeID, int index, int maxresult);
	
	List<Integer> fetchStaffIds(List<Long> spIds, Short tenantId);
    
}
