package com.cognizant.opserv.sp.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeo;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoId;
import com.cognizant.opserv.tenant.common.TenantDataType;
import com.cognizant.opserv.tenant.common.TenantHolder;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.opserv.tenant.vo.Tenant;
import com.cognizant.opserv.tenant.vo.TenantSchema;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosGeoDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosGeoDAO")
public class TSalesPosGeoDAOImpl implements TSalesPosGeoDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosGeoDAOImpl.class);

	private final String getSPShapePolygon = "select tspg.sales_pos_id,AsText(Centroid(tspg.shape_polygon)) from t_sales_pos_geo tspg where tspg.sales_hier_id = ?1 and tspg.sales_pos_id in ?2 ";

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TSalesPosGeo> clazz;

	public Class<TSalesPosGeo> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosGeoDAOImpl() {
		super();
		this.clazz = TSalesPosGeo.class;
	}

	private static final String JPAQL = "select tSalesPosGeoentity from TSalesPosGeo tSalesPosGeoentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPosGeo tSalesPosGeoentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosGeoentity.tSalesPosGeoId.salesHierId = #{tSalesPosGeoentity.tSalesPosGeoId.getSalesHierId}",
			"tSalesPosGeoentity.tSalesPosGeoId.salesPosId = #{tSalesPosGeoentity.tSalesPosGeoId.getSalesPosId}",
			"tSalesPosGeoentity.prnSalesPosId = #{tSalesPosGeoentity.getPrnSalesPosId}",
			"tSalesPosGeoentity.prnSalesHierId = #{tSalesPosGeoentity.getPrnSalesHierId}",
			"tSalesPosGeoentity.shapePolygon = #{tSalesPosGeoentity.getShapePolygon}",
			"tSalesPosGeoentity.rootHierId = #{tSalesPosGeoentity.getRootHierId}",
			"tSalesPosGeoentity.posName like '#{tSalesPosGeoentity.getPosName}%'",
			"tSalesPosGeoentity.podTitle like '#{tSalesPosGeoentity.getPodTitle}%'" };

	/**
	 * Stores a new TSalesPosGeo entity object in to the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be persisted
	 * @return tSalesPosGeo Persisted TSalesPosGeo object
	 */
	public TSalesPosGeo createTSalesPosGeo(final TSalesPosGeo tSalesPosGeo) {
		LOGGER.info("=========== Create TSalesPosGeo ===========");
		return genericDAO.store(tSalesPosGeo);
	}

	/**
	 * Deletes a TSalesPosGeo entity object from the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be deleted
	 */
	public void deleteTSalesPosGeo(final TSalesPosGeoId tSalesPosGeoId) {
		LOGGER.info("=========== Delete TSalesPosGeo ===========");
		final TSalesPosGeo tSalesPosGeo = genericDAO.get(clazz, tSalesPosGeoId);
		genericDAO.remove(tSalesPosGeo);
	}

	/**
	 * Updates a TSalesPosGeo entity object in to the persistent store
	 * 
	 * @param tSalesPosGeo
	 *            TSalesPosGeo Entity object to be updated
	 * @return tSalesPosGeo Persisted TSalesPosGeo object
	 */
	public TSalesPosGeo updateTSalesPosGeo(final TSalesPosGeo tSalesPosGeo) {
		LOGGER.info("=========== Update TSalesPosGeo ===========");
		return genericDAO.update(tSalesPosGeo);
	}

	/**
	 * Retrieve an TSalesPosGeo object based on given TSalesPosGeo TSalesPosGeoId.
	 * 
	 * @param tSalesPosGeoId
	 *            the primary key value of the TSalesPosGeo Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosGeo findTSalesPosGeoById(final TSalesPosGeoId tSalesPosGeoId) {
		LOGGER.info("find TSalesPosGeo instance with TSalesPosGeoId: " + tSalesPosGeoId);
		return genericDAO.get(clazz, tSalesPosGeoId);
	}

	/**
	 * Retrieve TSalesPosGeo based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeo> list of TSalesPosGeo if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosGeo> findTSalesPosGeos(final SearchFilter<TSalesPosGeo> searchFilter) {
		LOGGER.info("=========== Find TSalesPosGeos ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosGeo tSalesPosGeo = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosGeoentity", tSalesPosGeo);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosGeos.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosGeos(final SearchFilter<TSalesPosGeo> searchFilter) {
		LOGGER.info("=========== Count TSalesPosGeo ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosGeo tSalesPosGeo = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosGeoentity", tSalesPosGeo);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	@Override
	public Long countOfTSalesPosGeos(List paramList) {
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond("CountTSalesPosGeoBySalPos", paramList, -1, -1);
		Long countL = (Long) count.get(0);
		return countL;
	}

	@Override
	public Integer updateTSalesPosGeoByNative(String query) {
		return genericDAO.executeNativeQuery(query);	
	}
 
	@Override
	public String getPolygonForParent(String query) {
		List<String> polyList = genericDAO.findByNativeQuery(query);
		if(polyList!=null){
		return polyList.get(0);
		}
		return null;
	}
	
	public List<String> getPolygon(String query){		
		return genericDAO.findByNativeQuery(query);
	}

	/*@Override
	public List<Object[]> getTSalesPosGeo(String sqlQueryForSPoly) {
		List tShapePolyList = genericDAO.findByNativeQuery(sqlQueryForSPoly);
		return tShapePolyList;
	}*/
	
	@Override
	public List<Object[]> getTSalesPosGeo(Long salesHierId, List<Long> spList) {
		List<Object> queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(spList);
		List tShapePolyList = genericDAO.findByNativeQueryMultiCond(getSPShapePolygon, queryParam, 0,-1);
		return tShapePolyList;
	}
	
	/*@Override
	public String isShapePolygonContinousProc(final Long salesPosId, final Long salesHierId, final String zipList, final String operation, 
			final String lowestTableName, final Integer userId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(zipList);
		paramList.add(operation);
		paramList.add(lowestTableName);
		LOGGER.info("=========== isShapePolygonContinousProc ========START=== ");
		String zipFinal = ""+zipList.toString()+"";
		LOGGER.info("=========== destSalesSPId = "+salesPosId);
		LOGGER.info("=========== destHierId = "+salesHierId);
		LOGGER.info("=========== zipStr = "+zipFinal);
		LOGGER.info("=========== operationFlag = "+operation); 
		LOGGER.info("=========== lowestTableName = "+lowestTableName);
		LOGGER.info("=========== START TIME = "+System.currentTimeMillis()); 
//		List list = genericDAO.findByNativeQueryMultiCond("{ CALL P2_isShape_Continuous_M(?,?,?,?,?) }", paramList, 0, -1);
//		String result =(String) list.get(0);
		final Query queryJPQL = genericDAO.getEntityManagerFromJPA()
				.createNativeQuery("{ CALL P2_isShape_Continuous_M(?,?,?,?,?) }");
		queryJPQL.setParameter(1, salesPosId);
		queryJPQL.setParameter(2, salesHierId);
		queryJPQL.setParameter(3, zipList);
		queryJPQL.setParameter(4, operation);
		queryJPQL.setParameter(5, lowestTableName);
		String result = (String) queryJPQL.getSingleResult();
		LOGGER.info("=========== END TIME = "+System.currentTimeMillis());
		LOGGER.info("=========== isShapePolygonContinousProc ========END=== "+result);
		return result;
	}
*/
	
	
	@Override
	public String isShapePolygonContinousProc(final Long salesPosId, final Long salesHierId, final String zipList, final String operation, 
			final String lowestTableName, final Integer userId) {
		
		LOGGER.info("===========NEW isShapePolygonContinousProc ========START=== ");
		String result = null;
		Connection connection = null;
		ResultSet rs = null;
		CallableStatement callableSt = null;
		try {		
			LOGGER.info("=========== destSalesSPId = "+salesPosId);
			LOGGER.info("=========== destHierId = "+salesHierId);
			LOGGER.info("=========== zipStr = "+zipList);
			LOGGER.info("=========== operationFlag = "+operation); 
			LOGGER.info("=========== lowestTableName = "+lowestTableName);
			LOGGER.info("=========== START TIME = "+System.currentTimeMillis());
			
			connection = getTenantSpecificDataSource().getConnection();
			final String callStoredProc = "{ CALL P2_isShape_Continuous_M(?,?,?,?,?) }";
			if(connection != null) {
				callableSt = connection.prepareCall(callStoredProc);
				callableSt.setLong(1, salesPosId);
				callableSt.setLong(2, salesHierId);
				callableSt.setString(3, zipList);
				callableSt.setString(4, operation);
				callableSt.setString(5, lowestTableName);
				rs = callableSt.executeQuery();
				if(rs.next()) {
					result = rs.getString(1);
				}
			}
		} catch(Exception e) {
			LOGGER.error("Exception in calling stored procedure",e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(callableSt != null) {
					callableSt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch(Exception e) {
				LOGGER.error("Error closing resources",e);
			}
		}
		
	    LOGGER.info("=========== END TIME = "+System.currentTimeMillis());
		LOGGER.info("=========== NEW isShapePolygonContinousProc ========END=== "+result);
	    return result;
		
	}


	/*
	@Override
	public String updateChildParentShapePolygonProc(final Long salesPosId, final Long salesHierId, final String zipList, final String zipAssignedList, 
			final String flagAssignOrUnassign, final String lowestTableName, final Integer userId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(zipList);
		paramList.add(zipAssignedList);
		paramList.add(flagAssignOrUnassign);
		paramList.add(lowestTableName);
		paramList.add(userId);
		LOGGER.info("=========== updateChildParentShapePolygonProc ========START=== ");
		LOGGER.info("=========== destSalesSPId = "+salesPosId);
		LOGGER.info("=========== destHierId = "+salesHierId);
		LOGGER.info("=========== zipStr = "+zipList);
		LOGGER.info("=========== zipAssignedList = "+zipAssignedList);
		LOGGER.info("=========== flagAssignOrUnassign = "+flagAssignOrUnassign); 
		LOGGER.info("=========== lowestTableName = "+lowestTableName);
		LOGGER.info("=========== userId = "+userId);
		LOGGER.info("=========== START TIME = "+System.currentTimeMillis()); 
//		List list = genericDAO.findByNativeQueryMultiCond("{ CALL P7_Update_Shape_AssignUnAssignGeo_M(?,?,?,?,?,?,?) }", paramList, 0, -1);
//		String result =(String) list.get(0);
		final Query queryJPQL = genericDAO.getEntityManagerFromJPA()
				.createNativeQuery("{ CALL P7_Update_Shape_AssignUnAssignGeo_M(?,?,?,?,?,?,?) }");
		queryJPQL.setParameter(1, salesPosId);
		queryJPQL.setParameter(2, salesHierId);
		queryJPQL.setParameter(3, zipList);
		queryJPQL.setParameter(4, zipAssignedList);
		queryJPQL.setParameter(5, flagAssignOrUnassign);
		queryJPQL.setParameter(6, lowestTableName);
		queryJPQL.setParameter(7, userId);

		String result = (String) queryJPQL.getSingleResult();
	    LOGGER.info("=========== END TIME = "+System.currentTimeMillis());
		LOGGER.info("=========== updateChildParentShapePolygonProc ========END=== "+result);
	    return result;
	}
	*/
	
	@Override
	public String updateChildParentShapePolygonProc(final Long salesPosId, final Long salesHierId, final String zipList, final String zipAssignedList, 
			final String flagAssignOrUnassign, final String lowestTableName, final Integer userId) {

		LOGGER.info("=========== CALLING New updateChildParentShapePolygonProc ========START=== ");
		
		String result = null;
		Connection connection = null;
		ResultSet rs = null;
		CallableStatement callableSt = null;
		String callStoredProc = null;
		try {		
			LOGGER.info("=========== updateChildParentShapePolygonProc ========START=== ");
			LOGGER.info("=========== destSalesSPId = "+salesPosId);
			LOGGER.info("=========== destHierId = "+salesHierId);
			LOGGER.info("=========== zipStr = "+zipList);
			LOGGER.info("=========== zipAssignedList = "+zipAssignedList);
			LOGGER.info("=========== flagAssignOrUnassign = "+flagAssignOrUnassign); 
			LOGGER.info("=========== lowestTableName = "+lowestTableName);
			LOGGER.info("=========== userId = "+userId);
			LOGGER.info("=========== START TIME = "+System.currentTimeMillis()); 
			
			connection = getTenantSpecificDataSource().getConnection();
			if(connection != null) {
				if(null!= salesPosId && null!= salesHierId)
				{
					callStoredProc = "{ CALL P7_Update_Shape_AssignUnAssignGeo_M(?,?,?,?,?,?,?) }";
				callableSt = connection.prepareCall(callStoredProc);
				callableSt.setLong(1, salesPosId);
				callableSt.setLong(2, salesHierId);
				callableSt.setString(3, zipList);
				callableSt.setString(4, zipAssignedList);
				callableSt.setString(5, flagAssignOrUnassign);
				callableSt.setString(6, lowestTableName);
				callableSt.setInt(7, userId);
				}else
				{
					callStoredProc = "{ CALL P7_Update_Shape_AssignUnAssignGeo_M(null,null,?,?,?,?,?) }";
					callableSt = connection.prepareCall(callStoredProc);
//					callableSt.setLong(1, salesPosId);
//					callableSt.setLong(2, salesHierId);
					callableSt.setString(1, zipList);
					callableSt.setString(2, zipAssignedList);
					callableSt.setString(3, flagAssignOrUnassign);
					callableSt.setString(4, lowestTableName);
					callableSt.setInt(5, userId);
				}
				
				rs = callableSt.executeQuery();
				if(rs.next()) {
					result = rs.getString(1);
				}
			}
		} catch(Exception e) {
			LOGGER.error("Exception in calling stored procedure",e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(callableSt != null) {
					callableSt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch(Exception e) {
				LOGGER.error("Error closing resources",e);
			}
		}
		
	    LOGGER.info("=========== END TIME = "+System.currentTimeMillis());
		LOGGER.info("=========== NEW updateChildParentShapePolygonProc ========END=== "+result);
	    return result;

		
	}
	
	private DataSource getTenantSpecificDataSource(){
		
		Tenant tenant = TenantHolder.getTenant(TenantContext.getTenantKey());
		TenantSchema tenantSchema = tenant.getTSchConfig(TenantDataType.APPDB.intValue());
    	DriverManagerDataSource ds = new DriverManagerDataSource();

    	ds.setDriverClassName("com.mysql.jdbc.Driver");
    	ds.setUrl("jdbc:mysql://"+ tenantSchema.getServerName() + ":3306/"+tenantSchema.getDbName());
		ds.setUsername(tenantSchema.getUsrName());
		ds.setPassword(tenantSchema.getPasswrd());
		
		return ds;
		
	}	
}
