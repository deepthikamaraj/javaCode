package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TGeoMtrValue;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TGeoMtrValue DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TGeoMtrValueDAO {

	/**
	 * Stores a new TGeoMtrValue entity object in to the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be persisted
	 * @return tGeoMtrValue Persisted TGeoMtrValue object
	 */
	TGeoMtrValue createTGeoMtrValue(TGeoMtrValue tGeoMtrValue);

	/**
	 * Deletes a TGeoMtrValue entity object from the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be deleted
	 */
	void deleteTGeoMtrValue(Integer seqNumber);

	/**
	 * Updates a TGeoMtrValue entity object in to the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be updated
	 * @return tGeoMtrValue Persisted TGeoMtrValue object
	 */
	TGeoMtrValue updateTGeoMtrValue(TGeoMtrValue tGeoMtrValue);

	/**
	 * Retrieve an TGeoMtrValue object based on given seqNumber.
	 * 
	 * @param seqNumber
	 *            the primary key value of the TGeoMtrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TGeoMtrValue findTGeoMtrValueById(Integer seqNumber);

	/**
	 * Retrieve TGeoMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoMtrValue> list of TGeoMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TGeoMtrValue> findTGeoMtrValues(SearchFilter<TGeoMtrValue> searchFilter);

	/**
	 * Count TGeoMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTGeoMtrValues(SearchFilter<TGeoMtrValue> searchFilter);

	/**
	 * Retrieve TGeoMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoMtrValue> list of TGeoMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TGeoMtrValue> getTGeoMtrValuesByTTerrZipAlgmnt(SearchFilter<TTerrZipAlgmnt> searchFilter);

	/**
	 * Count TGeoMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTGeoMtrValuesByTTerrZipAlgmnt(SearchFilter<TTerrZipAlgmnt> searchFilter);
	
	
	List<TGeoMtrValue> findTGeoMtrValueByIDs(Long salesPosId, Long salesHierId, int mtrId,String postalCd, short tenantId);

	List<TGeoMtrValue> findTGeoMtrValueByPosCodesAndMtrId(int mtrId, List<String> postalCds, Short tenantId);

	List<Object[]> findTGeoMtrValueByIDsForHeatMap(int mtrId,List<String> postalCd, short tenantId);

	List<Object[]> findTGeoMtrValueByPostalCd(List<String> postalCd,
			Short tenantId);
	
	/**
	 * Find min and max value by mtr id.
	 *
	 * @param mtrId the mtr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	List<Object[]> findMinAndMaxValueByMtrId(Long mtrId,Short tenantId);

	/**
	 * 
	 * @param mtrIds
	 * @param postalCd
	 * @param id
	 * @param mtrValueTypeIds
	 * @param tenantId
	 * @return
	 */
	List<Object[]> getMtrValueByPostalCodesAndSP(List<Integer> mtrIds,
			String postalCd, Long spId, List<Integer> mtrValueTypeIds, Short tenantId);

}
