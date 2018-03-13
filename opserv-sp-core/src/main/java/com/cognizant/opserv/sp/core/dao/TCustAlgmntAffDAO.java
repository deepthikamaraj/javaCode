package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAff;
import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCustAlgmntAff DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCustAlgmntAffDAO {

	/**
	 * Stores a new TCustAlgmntAff entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be persisted
	 * @return tCustAlgmntAff Persisted TCustAlgmntAff object
	 */
	TCustAlgmntAff createTCustAlgmntAff(TCustAlgmntAff tCustAlgmntAff);

	/**
	 * Deletes a TCustAlgmntAff entity object from the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be deleted
	 */
	void deleteTCustAlgmntAff(Integer affTypeId);

	/**
	 * Updates a TCustAlgmntAff entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be updated
	 * @return tCustAlgmntAff Persisted TCustAlgmntAff object
	 */
	TCustAlgmntAff updateTCustAlgmntAff(TCustAlgmntAff tCustAlgmntAff);

	/**
	 * Retrieve an TCustAlgmntAff object based on given affTypeId.
	 * 
	 * @param affTypeId
	 *            the primary key value of the TCustAlgmntAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCustAlgmntAff findTCustAlgmntAffById(Integer affTypeId);

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAff> findTCustAlgmntAffs(SearchFilter<TCustAlgmntAff> searchFilter);

	/**
	 * Count TCustAlgmntAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAffs(SearchFilter<TCustAlgmntAff> searchFilter);

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAff> getTCustAlgmntAffsByTCustType(SearchFilter<TCustType> searchFilter);

	/**
	 * Count TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAffsByTCustType(SearchFilter<TCustType> searchFilter);

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCustAlgmntAff> getTCustAlgmntAffsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns list if  found
	 */
	List<TCustAlgmntAff> getTCustAlgmntAffsByTAlgmntSalesTeamAndTCustType(final TAlgmntSalesTeam tAlgmntSalesTeam, final TCustType custType,Short tenantId);

	/**
	 * Count TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCustAlgmntAffsByTAlgmntSalesTeam(SearchFilter<TAlgmntSalesTeam> searchFilter);
	/**
	 * Retrieve lowestCustAffsHier based on given search criteria using JPA named Query.
	 * The search criteria is of CustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return String of CustAffsHier if it exists against given
	 *         criteria. Returns null if not found
	 */
	String getLowestLevelInCustAffHier(Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId,String localeId);

}
