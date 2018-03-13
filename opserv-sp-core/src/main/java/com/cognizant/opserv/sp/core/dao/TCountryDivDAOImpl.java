package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCountry;
import com.cognizant.opserv.sp.core.entity.TCountryDiv;
import com.cognizant.opserv.sp.core.entity.TCountryDivId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCountryDivDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCountryDivDAO")
public class TCountryDivDAOImpl implements TCountryDivDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCountryDivDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	@Autowired
	private GisDAO gisDAO;
	
	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	public GisDAO getGisDAO() {
		return gisDAO;
	}

	public void setGisDAO(final GisDAO gisDAO) {
		this.gisDAO = gisDAO;
	}
	
	private static final String TCOUNTRY = "tCountry";

	private final Class<TCountryDiv> clazz;

	public Class<TCountryDiv> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCountryDivDAOImpl() {
		super();
		this.clazz = TCountryDiv.class;
	}

	private static final String JPAQL = "select tCountryDiventity from TCountryDiv tCountryDiventity";

	private static final String JPACOUNTQL = "select count(*) from TCountryDiv tCountryDiventity";

	private static final String[] RESTRICTIONS = {
			"tCountryDiventity.tCountryDivId.divId = #{tCountryDiventity.tCountryDivId.getDivId}",
			"tCountryDiventity.tCountryDivId.countryId = #{tCountryDiventity.tCountryDivId.getCountryId}",
			"tCountryDiventity.divName like '#{tCountryDiventity.getDivName}%'",
			"tCountryDiventity.divCd like '#{tCountryDiventity.getDivCd}%'",
			"tCountryDiventity.lowestDivFlag = #{tCountryDiventity.getLowestDivFlag}",
			"tCountryDiventity.lookupTbl like '#{tCountryDiventity.getLookupTbl}%'",
			"tCountryDiventity.tCountry.countryId = #{tCountryDiventity.tCountry.getCountryId}" };

	/**
	 * Stores a new TCountryDiv entity object in to the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be persisted
	 * @return tCountryDiv Persisted TCountryDiv object
	 */
	public TCountryDiv createTCountryDiv(final TCountryDiv tCountryDiv) {
		LOGGER.info("=========== Create TCountryDiv ===========");
		return genericDAO.store(tCountryDiv);
	}

	/**
	 * Deletes a TCountryDiv entity object from the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be deleted
	 */
	public void deleteTCountryDiv(final TCountryDivId tCountryDivId) {
		LOGGER.info("=========== Delete TCountryDiv ===========");
		final TCountryDiv tCountryDiv = genericDAO.get(clazz, tCountryDivId);
		genericDAO.remove(tCountryDiv);
	}

	/**
	 * Updates a TCountryDiv entity object in to the persistent store
	 * 
	 * @param tCountryDiv
	 *            TCountryDiv Entity object to be updated
	 * @return tCountryDiv Persisted TCountryDiv object
	 */
	public TCountryDiv updateTCountryDiv(final TCountryDiv tCountryDiv) {
		LOGGER.info("=========== Update TCountryDiv ===========");
		return genericDAO.update(tCountryDiv);
	}

	/**
	 * Retrieve an TCountryDiv object based on given TCountryDiv TCountryDivId.
	 * 
	 * @param tCountryDivId
	 *            the primary key value of the TCountryDiv Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCountryDiv findTCountryDivById(final TCountryDivId tCountryDivId) {
		LOGGER.info("find TCountryDiv instance with TCountryDivId: " + tCountryDivId);
		return genericDAO.get(clazz, tCountryDivId);
	}

	/**
	 * Retrieve TCountryDiv based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountryDiv> list of TCountryDiv if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCountryDiv> findTCountryDivs(final SearchFilter<TCountryDiv> searchFilter) {
		LOGGER.info("=========== Find TCountryDivs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCountryDiv tCountryDiv = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCountryDiventity", tCountryDiv);

		if (tCountryDiv.getTCountry() == null) {
			jpaQuery.bind(TCOUNTRY, new TCountry());
		} else {
			LOGGER.info("tCountry {}" + tCountryDiv.getTCountry());

			jpaQuery.bind(TCOUNTRY, tCountryDiv.getTCountry());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCountryDivs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCountryDivs(final SearchFilter<TCountryDiv> searchFilter) {
		LOGGER.info("=========== Count TCountryDiv ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCountryDiv tCountryDiv = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCountryDiventity", tCountryDiv);

		if (tCountryDiv.getTCountry() == null) {
			jpaCountQuery.bind(TCOUNTRY, new TCountry());
		} else {
			LOGGER.info("tCountry {}" + tCountryDiv.getTCountry());

			jpaCountQuery.bind(TCOUNTRY, tCountryDiv.getTCountry());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCountryDiv based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCountryDiv> list of TCountryDivs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCountryDiv> getTCountryDivsByTCountry(final SearchFilter<TCountry> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCountry tCountry = searchFilter.getEntityClass();
		List<Object> tCountryList = new ArrayList<Object>();
		tCountryList.add(tCountry);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCountryDivByTCountry", tCountryList, index, maxresult);
	}

	/**
	 * Count TCountryDiv based on given search criteria using JPA named Query.
	 * The search criteria is of TCountry type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCountryDivsByTCountry(final SearchFilter<TCountry> searchFilter) {

		final TCountry tCountry = searchFilter.getEntityClass();
		List<Object> tCountryList = new ArrayList<Object>();
		tCountryList.add(tCountry);
		return genericDAO.findEntitiesByNamedQuery("CountTCountryDivsByTCountry", tCountryList);
	}
	
	@Override
	public List<String> findTCountryDivLookup_tblForExtents(String countryName) {
		return genericDAO.findByNativeQuery("select t_country_div.lookup_tbl from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country.country_name='"+countryName+"' and t_country_div.extents_div_flag='Y'");
	}
	
	@Override
	public List<String> findTCountryDivShapePolygons(String lookup_tblForExtents) {
		LOGGER.info("find TCountryDiv ShapePolygons for lookup_tbl For Extents: " + lookup_tblForExtents);
		return gisDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) as shape from "+ lookup_tblForExtents);
	}

	@Override
	public List<String> findTCountryDivLookup_tblList(String countryName) {
		return genericDAO.findByNativeQuery("select t_country_div.lookup_tbl from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country.country_name='"+countryName+"'");
	}
	
	@Override
	public List<String> findDivColumn(String lookup_tbl) {
		return genericDAO.findByNativeQuery("select div_name from t_country_div where lookup_tbl='"+lookup_tbl+"'");		
	}

	@Override
	public List<String> findTCountryDivLowest_lookup_tbl(String countryName) {
		return genericDAO.findByNativeQuery("select t_country_div.lookup_tbl from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country_div.lowest_div_flag='Y' and t_country.country_name='"+countryName+"'");
	}
	@Override
	public List<String> findTCountryDivLowest_DivColumn(String countryName){
		return genericDAO.findByNativeQuery("select t_country_div.div_name from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country_div.lowest_div_flag='Y' and t_country.country_name='"+countryName+"'");
	}
	@Override
	public List<String> findTCountryDivLowest_DivColumnById(String countryId){
		return genericDAO.findByNativeQuery("select t_country_div.div_name from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country_div.lowest_div_flag='Y' and t_country.country_id="+countryId+" ");
	}
	
	@Override
	public List<TCountryDiv> findLowestCountryDivision(Integer countryId){
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(countryId);
		return genericDAO.findEntitiesByNamedQuery("findLowestCountryDivision", queryParam);
	}
	
	//this is gis country specific table query
	public List<String> findLowestDivShapePolygon(String lookup_tbl,String lowest_div,String zipCodes){
		return gisDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) from "+lookup_tbl+" where "+lowest_div.toLowerCase()+"_cd in("+zipCodes+") ");
	}
	
	public List<String> findSearchedShapePolygon(String lookup_tbl, String divColumn, String filterCodes){
		if(divColumn.toLowerCase().equalsIgnoreCase("zip")){
		return genericDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) from "+lookup_tbl+" " +
					"where "+divColumn.toLowerCase()+"_cd like '"+filterCodes+"%'");
		}else{
			return genericDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) from "+lookup_tbl+" " +
					"where "+divColumn.toLowerCase()+"_name like '"+filterCodes+"%'");
		}
	}
	
	public List<String> findLowestDivShapePoint(String lookup_tbl,String lowest_div,String zipCodes){
		 return gisDAO.findByNativeQuery("select AsText(data_point) from "+lookup_tbl+" where "+lowest_div.toLowerCase()+"_cd in ("+zipCodes+") ");
	}
	
	public List<Object[]> findPointLatLong(String lookup_tbl,String lowest_div,String zipCodes){
		//return gisDAO.findByNativeQuery("select X(data_point),Y(data_point) from "+lookup_tbl.toLowerCase()+" where "+lowest_div.toLowerCase()+"_cd"+"='"+ zipCodes + "'");
		return gisDAO.findByNativeQuery("select X(data_point),Y(data_point),zip_cd from "+lookup_tbl.toLowerCase()+" where "+lowest_div.toLowerCase()+"_cd"+" in ("+ zipCodes + ")");
	}
	
	@Override
	public List<String> findTCountryDivprnZipTableName(String countryName) {
		return genericDAO.findByNativeQuery("select t_country_div.point_lookup_tbl from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country_div.lowest_div_flag='Y' and t_country.country_name='"+countryName+"'");
	}

	@Override
	public List<String> getLayerListForCountry(String countryName) {
		return genericDAO.findByNativeQuery("select t_country_div.div_name from t_country_div, t_country where t_country_div.country_id=t_country.country_id and t_country.country_name='"+countryName+"'");
	}
	@Override
	public List<String> getLowestDivColumn(String div_name) {
		return genericDAO.findByNativeQuery("select lookup_col from t_country_div where div_name='"+div_name+"'");
	}
	
	@Override
	public List<String> findstaticLayerLookUpTableName(String countryName, String staticLayerName) {
		return genericDAO.findByNativeQuery("select t_country_div.lookup_tbl from t_country_div, t_country where " +
				"t_country_div.country_id=t_country.country_id and " +
				"t_country.country_name='"+countryName.toLowerCase()+"' and " +
				"t_country_div.div_name='"+staticLayerName.toLowerCase()+"'");
	}
	@Override
	public List<String> getInvalidZipCount(String lowestLevelTbl,String lowestDiv,String zipCodes){
		return gisDAO.findByNativeQuery("select "+lowestDiv.toLowerCase()+"_cd from "+lowestLevelTbl+" where "+lowestDiv.toLowerCase()+"_cd in ("+zipCodes+") ");
	}
}
