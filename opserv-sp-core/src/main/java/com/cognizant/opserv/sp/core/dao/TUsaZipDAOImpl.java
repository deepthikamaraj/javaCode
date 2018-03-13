package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsaCounty;
import com.cognizant.opserv.sp.core.entity.TUsaZip;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TUsaZipDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsaZipDAO")
public class TUsaZipDAOImpl implements TUsaZipDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsaZipDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

//	@Autowired
//	private GisDAO gisDAO;
//	
	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

//	public GisDAO getGisDAO() {
//		return gisDAO;
//	}
//
//	public void setGisDAO(final GisDAO gisDAO) {
//		this.gisDAO = gisDAO;
//	}
	
	private static final String TUSACOUNTY = "tUsaCounty";

	private final Class<TUsaZip> clazz;

	public Class<TUsaZip> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsaZipDAOImpl() {
		super();
		this.clazz = TUsaZip.class;
	}

	private static final String JPAQL = "select tUsaZipentity from TUsaZip tUsaZipentity";

	private static final String JPACOUNTQL = "select count(tUsaZipentity) from TUsaZip tUsaZipentity";

	private static final String[] RESTRICTIONS = { "tUsaZipentity.zipId = #{tUsaZipentity.getZipId}",
			"tUsaZipentity.zipCd like '#{tUsaZipentity.getZipCd}%'",
			"tUsaZipentity.shapePolygon = #{tUsaZipentity.getShapePolygon}",
			"tUsaZipentity.dataPoint = #{tUsaZipentity.getDataPoint}",
			"tUsaZipentity.prnZipCd = #{tUsaZipentity.getPrnZipCd}",
			"tUsaZipentity.pointZipFlag = #{tUsaZipentity.getPointZipFlag}",
			"tUsaZipentity.tUsaCounty.countyId = #{tUsaZipentity.tUsaCounty.getCountyId}" };

	/**
	 * Stores a new TUsaZip entity object in to the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be persisted
	 * @return tUsaZip Persisted TUsaZip object
	 */
	public TUsaZip createTUsaZip(final TUsaZip tUsaZip) {
		LOGGER.info("=========== Create TUsaZip ===========");
		return genericDAO.store(tUsaZip);
	}

	/**
	 * Deletes a TUsaZip entity object from the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be deleted
	 */
	public void deleteTUsaZip(final Integer zipId) {
		LOGGER.info("=========== Delete TUsaZip ===========");
		final TUsaZip tUsaZip = genericDAO.get(clazz, zipId);
		genericDAO.remove(tUsaZip);
	}

	/**
	 * Updates a TUsaZip entity object in to the persistent store
	 * 
	 * @param tUsaZip
	 *            TUsaZip Entity object to be updated
	 * @return tUsaZip Persisted TUsaZip object
	 */
	public TUsaZip updateTUsaZip(final TUsaZip tUsaZip) {
		LOGGER.info("=========== Update TUsaZip ===========");
		return genericDAO.update(tUsaZip);
	}

	/**
	 * Retrieve an TUsaZip object based on given TUsaZip zipId.
	 * 
	 * @param tUsaZipId
	 *            the primary key value of the TUsaZip Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TUsaZip findTUsaZipById(final Integer tUsaZipId) {
		LOGGER.info("find TUsaZip instance with zipId: " + tUsaZipId);
		return genericDAO.get(clazz, tUsaZipId);
	}

	/**
	 * Retrieve TUsaZip based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaZip> list of TUsaZip if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TUsaZip> findTUsaZips(final SearchFilter<TUsaZip> searchFilter) {
		LOGGER.info("=========== Find TUsaZips ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TUsaZip tUsaZip = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tUsaZipentity", tUsaZip);

		if (tUsaZip.getTUsaCounty() == null) {
			jpaQuery.bind(TUSACOUNTY, new TUsaCounty());
		} else {
			LOGGER.info("tUsaCounty {}"+ tUsaZip.getTUsaCounty());

			jpaQuery.bind(TUSACOUNTY, tUsaZip.getTUsaCounty());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TUsaZips.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTUsaZips(final SearchFilter<TUsaZip> searchFilter) {
		LOGGER.info("=========== Count TUsaZip ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TUsaZip tUsaZip = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tUsaZipentity", tUsaZip);

		if (tUsaZip.getTUsaCounty() == null) {
			jpaCountQuery.bind(TUSACOUNTY, new TUsaCounty());
		} else {
			LOGGER.info("tUsaCounty {}"+ tUsaZip.getTUsaCounty());

			jpaCountQuery.bind(TUSACOUNTY, tUsaZip.getTUsaCounty());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TUsaZip based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaCounty type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TUsaZip> list of TUsaZips if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TUsaZip> getTUsaZipsByTUsaCounty(final SearchFilter<TUsaCounty> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsaZipByTUsaCounty", queryParam, index, maxresult);
	}

	/**
	 * Count TUsaZip based on given search criteria using JPA named Query.
	 * The search criteria is of TUsaCounty type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTUsaZipsByTUsaCounty(final SearchFilter<TUsaCounty> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTUsaZipsByTUsaCounty", queryParam);
	}

	public List<String> getTUsaZips(String query){		
//		return gisDAO.findByNativeQuery(query);
		//return genericDAO.findByNativeQuery(query);
		return null;
	}
	
	public List<String> getTUsaZipsFromTenantDb(String query){		
		//return gisDAO.findByNativeQuery(query);
		return genericDAO.findByNativeQuery(query);
	}
	
    public List<Integer> getTUsaZipsMetric(String query){		
		return genericDAO.findByNativeQuery(query);
	}
	@Override
	public List<String> getTUsaZips_pointZipFlag(String zipcodes) {
		// TODO Auto-generated method stub
		//List<String> records =new ArrayList<String>();
		List<String> flagStatus =null;//gisDAO.findByNativeQuery("select zip_cd from t_usa_point_zip where prn_zip_id in ("+zipcodes+")");
		/*for(int i =0;i<flagStatus.size();i++)
		{
			if(flagStatus.get(i)!=null){
			String temp = Character.toString((char)flagStatus.get(i));
			records.add(temp);
			}
			else
			{
				records.add((String) flagStatus.get(i));
			}
			
		}*/
		return flagStatus;
	}

	@Override
	public List<String> getTUsaZip(List<String> assignedZipCd) {
		
		 StringBuilder filter = new StringBuilder();
		 String zipFilter = new String();
		 
		for(String zip1 : assignedZipCd)
		{   zip1.trim();
			filter= filter.append("'"+zip1+"',");
		}
		
		 if(filter.toString().endsWith(",")){
				int start = filter.lastIndexOf(",");
				int end = start+",".length();
				zipFilter=filter.delete(start,end).toString();
			}
	List<String> ZipCodes = null;//gisDAO.findByNativeQuery(" select tuz.zip_cd from t_usa_zip tuz where tuz.zip_cd not in ("+zipFilter+") Limit 5 ");
		
		return ZipCodes;
	}

}
