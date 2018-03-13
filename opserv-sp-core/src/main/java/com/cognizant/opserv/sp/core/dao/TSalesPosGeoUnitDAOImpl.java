package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosGeoUnitDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosGeoUnitDAO")
public class TSalesPosGeoUnitDAOImpl implements TSalesPosGeoUnitDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosGeoUnitDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TSalesPosGeoUnit> clazz;

	public Class<TSalesPosGeoUnit> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosGeoUnitDAOImpl() {
		super();
		this.clazz = TSalesPosGeoUnit.class;
	}

	private static final String JPAQL = "select tSalesPosGeoUnitentity from TSalesPosGeoUnit tSalesPosGeoUnitentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPosGeoUnit tSalesPosGeoUnitentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosGeoUnitentity.tSalesPosGeoUnitId.geoId = #{tSalesPosGeoUnitentity.tSalesPosGeoUnitId.getGeoId}",
			"tSalesPosGeoUnitentity.tSalesPosGeoUnitId.salesPosId = #{tSalesPosGeoUnitentity.tSalesPosGeoUnitId.getSalesPosId}",
			"tSalesPosGeoUnitentity.tSalesPosGeoUnitId.salesHierId = #{tSalesPosGeoUnitentity.tSalesPosGeoUnitId.getSalesHierId}",
			"tSalesPosGeoUnitentity.activeFlag = #{tSalesPosGeoUnitentity.getActiveFlag}",
			"tSalesPosGeoUnitentity.algmntId = #{tSalesPosGeoUnitentity.getAlgmntId}",
			"tSalesPosGeoUnitentity.bussUnitId = #{tSalesPosGeoUnitentity.getBussUnitId}",
			"tSalesPosGeoUnitentity.salesTeamId = #{tSalesPosGeoUnitentity.getSalesTeamId}",
			"tSalesPosGeoUnitentity.createdBy = #{tSalesPosGeoUnitentity.getCreatedBy}",
			"Date(tSalesPosGeoUnitentity.createDt) = '#{tSalesPosGeoUnitentity.getCreateDt}'",
			"tSalesPosGeoUnitentity.updatedBy = #{tSalesPosGeoUnitentity.getUpdatedBy}",
			"Date(tSalesPosGeoUnitentity.updateDt) = '#{tSalesPosGeoUnitentity.getUpdateDt}'",
			"tSalesPosGeoUnitentity.tenantId = #{tSalesPosGeoUnitentity.getTenantId}" };

	/**
	 * Stores a new TSalesPosGeoUnit entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be persisted
	 * @return tSalesPosGeoUnit Persisted TSalesPosGeoUnit object
	 */
	public TSalesPosGeoUnit createTSalesPosGeoUnit(final TSalesPosGeoUnit tSalesPosGeoUnit) {
		LOGGER.info("=========== Create TSalesPosGeoUnit ===========");
		return genericDAO.store(tSalesPosGeoUnit);
	}

	/**
	 * Deletes a TSalesPosGeoUnit entity object from the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be deleted
	 */
	public void deleteTSalesPosGeoUnit(final TSalesPosGeoUnitId tSalesPosGeoUnitId) {
		LOGGER.info("=========== Delete TSalesPosGeoUnit ===========");
		final TSalesPosGeoUnit tSalesPosGeoUnit = genericDAO.get(clazz, tSalesPosGeoUnitId);
		genericDAO.remove(tSalesPosGeoUnit);
	}

	/**
	 * Updates a TSalesPosGeoUnit entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnit
	 *            TSalesPosGeoUnit Entity object to be updated
	 * @return tSalesPosGeoUnit Persisted TSalesPosGeoUnit object
	 */
	public TSalesPosGeoUnit updateTSalesPosGeoUnit(final TSalesPosGeoUnit tSalesPosGeoUnit) {
		LOGGER.info("=========== Update TSalesPosGeoUnit ===========");
		return genericDAO.update(tSalesPosGeoUnit);
	}

	/**
	 * Retrieve an TSalesPosGeoUnit object based on given TSalesPosGeoUnit TSalesPosGeoUnitId.
	 * 
	 * @param tSalesPosGeoUnitId
	 *            the primary key value of the TSalesPosGeoUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosGeoUnit findTSalesPosGeoUnitById(final TSalesPosGeoUnitId tSalesPosGeoUnitId) {
		LOGGER.info("find TSalesPosGeoUnit instance with TSalesPosGeoUnitId: " + tSalesPosGeoUnitId);
		return genericDAO.get(clazz, tSalesPosGeoUnitId);
	}

	/**
	 * Retrieve TSalesPosGeoUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnit> list of TSalesPosGeoUnit if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosGeoUnit> findTSalesPosGeoUnits(final SearchFilter<TSalesPosGeoUnit> searchFilter) {
		LOGGER.info("=========== Find TSalesPosGeoUnits ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosGeoUnit tSalesPosGeoUnit = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosGeoUnitentity", tSalesPosGeoUnit);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosGeoUnits.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosGeoUnits(final SearchFilter<TSalesPosGeoUnit> searchFilter) {
		LOGGER.info("=========== Count TSalesPosGeoUnit ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosGeoUnit tSalesPosGeoUnit = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosGeoUnitentity", tSalesPosGeoUnit);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}


	@Override
	public List<TSalesPosGeoUnit> getExistingGeoIDs(Long algmntId, Long bussUnitId, Long salesTeamId,Long salesPosId,
			Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosGeoUnitList", paramList,0, -1);
	}
	
	@Override
	public List<Object> findZipByGeoId(Long algmntId, Long bussUnitId,
			Long salesTeamId, Long salehierId, Long salePosId, Integer geoId,
			Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salehierId);
		paramList.add(salePosId);
		paramList.add(geoId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findZipByGeoId", paramList,0, -1);
	}

	@Override
	public List<TSalesPosGeoUnit> findGeoIdFrSP(Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId, Long salesPosId, Short tenantId){
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesHierId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
	List<TSalesPosGeoUnit> geoIdList = genericDAO.findEntitiesByNamedQueryMultiCond("findGeoIdFrSP", paramList,0, -1);
	return geoIdList;
	}
	
	@Override
	public List<TSalesPosGeoUnit> getExistingGeoIDs(Long algmntId, Long bussUnitId, Long salesTeamId,List<Long> salesPosId,
			Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosGeoUnitListForCopy", paramList,0, -1);
	}

	@Override
	public Object getMaxGeoID(Short tenantId) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findMaxGeoIdFrSP", paramList,0, -1);
	}
	
}
