package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TDsStg;
import com.cognizant.opserv.sp.core.entity.TTblSchMap;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TTblSchMap DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TTblSchMapDAO {

	/**
	 * Stores a new TTblSchMap entity object in to the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be persisted
	 * @return tTblSchMap Persisted TTblSchMap object
	 */
	TTblSchMap createTTblSchMap(TTblSchMap tTblSchMap);

	/**
	 * Deletes a TTblSchMap entity object from the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be deleted
	 */
	void deleteTTblSchMap(String mappingId);

	/**
	 * Updates a TTblSchMap entity object in to the persistent store
	 * 
	 * @param tTblSchMap
	 *            TTblSchMap Entity object to be updated
	 * @return tTblSchMap Persisted TTblSchMap object
	 */
	TTblSchMap updateTTblSchMap(TTblSchMap tTblSchMap);

	/**
	 * Retrieve an TTblSchMap object based on given mappingId.
	 * 
	 * @param mappingId
	 *            the primary key value of the TTblSchMap Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TTblSchMap findTTblSchMapById(Long mappingId);

	/**
	 * Retrieve TTblSchMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSchMap> list of TTblSchMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTblSchMap> findTTblSchMaps(SearchFilter<TTblSchMap> searchFilter);

	/**
	 * Count TTblSchMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTblSchMaps(SearchFilter<TTblSchMap> searchFilter);

	/**
	 * Retrieve TTblSchMap based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSchMap> list of TTblSchMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TTblSchMap> getTTblSchMapsByTDsStg(SearchFilter<TDsStg> searchFilter);

	/**
	 * Count TTblSchMap based on given search criteria using JPA named Query.
	 * The search criteria is of TDsStg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTTblSchMapsByTDsStg(SearchFilter<TDsStg> searchFilter);
	
	List<TTblSchMap> getAllTTblSchMap();
	
	List<TTblSchMap> findTTblSchMapByTDsStgAndKeyCol(SearchFilter<TTblSchMap> searchFilter,Character compKey);
	
	int updateTable(String query);

	List<TTblSchMap> getTTblSchMapsByTDsStgAndTenantAndTableName(SearchFilter<TTblSchMap> searchFilter);

}
