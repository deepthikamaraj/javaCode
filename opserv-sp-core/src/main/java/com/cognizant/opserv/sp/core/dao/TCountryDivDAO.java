package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCountry;
import com.cognizant.opserv.sp.core.entity.TCountryDiv;
import com.cognizant.opserv.sp.core.entity.TCountryDivId;
import com.cognizant.peg.core.common.SearchFilter;

/**
 * Interface represents API's of TCountryDiv DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TCountryDivDAO {

	/**
	 * Stores a new TCountryDiv entity object in to the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be persisted
	 * @return tCountryDiv Persisted TCountryDiv object
	 */
	TCountryDiv createTCountryDiv(TCountryDiv tCountryDiv);

	/**
	 * Deletes a TCountryDiv entity object from the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be deleted
	 */
	void deleteTCountryDiv(TCountryDivId tCountryDivId);

	/**
	 * Updates a TCountryDiv entity object in to the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be updated
	 * @return tCountryDiv Persisted TCountryDiv object
	 */
	TCountryDiv updateTCountryDiv(TCountryDiv tCountryDiv);

	/**
	 * Retrieve an TCountryDiv object based on given TCountryDivId.
	 * 
	 * @param tCountryDivId
	 *            the primary key value of the TCountryDiv Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	TCountryDiv findTCountryDivById(TCountryDivId tCountryDivId);

	/**
	 * Retrieve TCountryDiv based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountryDiv> list of TCountryDivs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCountryDiv> findTCountryDivs(SearchFilter<TCountryDiv> searchFilter);

	/**
	 * Count TCountryDiv based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCountryDivs(SearchFilter<TCountryDiv> searchFilter);

	/**
	 * Retrieve TCountryDiv based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountryDiv> list of TCountryDivs if it exists against given
	 *         criteria. Returns null if not found
	 */
	List<TCountryDiv> getTCountryDivsByTCountry(SearchFilter<TCountry> searchFilter);

	/**
	 * Count TCountryDiv based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	Object countTCountryDivsByTCountry(SearchFilter<TCountry> searchFilter);
	
	/**
	 * Find t country div lookup_tbl for extents.
	 *
	 * @param countryName the country name
	 * @return the list
	 */
	public List<String> findTCountryDivLookup_tblForExtents(String countryName);
	
	//public List<String> findTCountryDivLookup_tblForLowestDiv(String countryName);
	
	/**
	 * Find t country div lookup_tbl list.
	 *
	 * @param countryName the country name
	 * @return the list
	 */
	public List<String> findTCountryDivLookup_tblList(String countryName);
	
	/**
	 * Find t country div shape polygons.
	 *
	 * @param lookup_tblForExtents the lookup_tbl for extents
	 * @return the list
	 */
	public List<String> findTCountryDivShapePolygons(String lookup_tblForExtents);
		
	/**
	 * Find t country div lowest_lookup_tbl.
	 *
	 * @param countryName the country name
	 * @return the list
	 */
	public List<String> findTCountryDivLowest_lookup_tbl(String countryName);
	
	/**
	 * Find div column.
	 *
	 * @param lookup_tbl the lookup_tbl
	 * @return the list
	 */
	public List<String> findDivColumn(String lookup_tbl);
	
	/**
	 * Find t country div lowest_ div column.
	 *
	 * @param countryName the country name
	 * @return the list
	 */
	public List<String> findTCountryDivLowest_DivColumn(String countryName);
	
	/**
	 * Find t country div lowest_ div column by id.
	 *
	 * @param countryId the country id
	 * @return the list
	 */
	public List<String> findTCountryDivLowest_DivColumnById(String countryId);
	
	/**
	 * Find lowest country division.
	 *
	 * @param countryId the country id
	 * @return the list
	 */
	public List<TCountryDiv> findLowestCountryDivision(Integer countryId);
	
	/**
	 * Find lowest div shape polygon.
	 *
	 * @param lookup_tbl the lookup_tbl
	 * @param lowest_div the lowest_div
	 * @param zipCodes the zip codes
	 * @return the list
	 */
	public List<String> findLowestDivShapePolygon(String lookup_tbl,String lowest_div,String zipCodes);
	
	/**
	 * Find searched shape polygon.
	 *
	 * @param lookup_tbl the lookup_tbl
	 * @param divColumn the div column
	 * @param filterCodes the filter codes
	 * @return the list
	 */
	public List<String> findSearchedShapePolygon(String lookup_tbl, String divColumn, String filterCodes);
	
	/**
	 * Find lowest div shape point.
	 *
	 * @param lookup_tbl the lookup_tbl
	 * @param lowest_div the lowest_div
	 * @param zipCodes the zip codes
	 * @return the list
	 */
	public List<String> findLowestDivShapePoint(String lookup_tbl,String lowest_div,String zipCodes);
	
	/**
	 * Find point lat long.
	 *
	 * @param lookup_tbl the lookup_tbl
	 * @param lowest_div the lowest_div
	 * @param zipCodes the zip codes
	 * @return the list
	 */
	public List<Object[]> findPointLatLong(String lookup_tbl,String lowest_div,String zipCodes);

	/**
	 * Find t country divprn zip table name.
	 *
	 * @param countryName the country name
	 * @return the list
	 */
	public List<String> findTCountryDivprnZipTableName(String countryName);
	
	/**
	 * Gets the layer list for country.
	 *
	 * @param countryName the country name
	 * @return the layer list for country
	 */
	public List<String> getLayerListForCountry(String countryName);
	
	/**
	 * Gets the lowest div column.
	 *
	 * @param div_name the div_name
	 * @return the lowest div column
	 */
	public List<String> getLowestDivColumn(String div_name);
	
	/**
	 * Findstatic layer look up table name.
	 *
	 * @param countryName the country name
	 * @param staticLayerName the static layer name
	 * @return the list
	 */
	public List<String> findstaticLayerLookUpTableName(String countryName, String staticLayerName);
	/**
	 * Gets the invalid zip count.
	 *
	 * @param lowestLevelTbl the lowest level table
	 * @param lowestDiv the lowest div
	 * @param zipCodes the zip codes
	 * @return the invalid zip count
	 */
	public List<String> getInvalidZipCount(String lowestLevelTbl,String lowestDiv,String zipCodes);	
}
