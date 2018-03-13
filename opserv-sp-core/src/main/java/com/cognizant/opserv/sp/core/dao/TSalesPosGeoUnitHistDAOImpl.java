package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitHist;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitHistId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosGeoUnitHistDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosGeoUnitHistDAO")
public class TSalesPosGeoUnitHistDAOImpl implements TSalesPosGeoUnitHistDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosGeoUnitHistDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESPOSGEOUNIT = "tSalesPosGeoUnit";

	private final Class<TSalesPosGeoUnitHist> clazz;

	public Class<TSalesPosGeoUnitHist> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosGeoUnitHistDAOImpl() {
		super();
		this.clazz = TSalesPosGeoUnitHist.class;
	}

	private static final String JPAQL = "select tSalesPosGeoUnitHistentity from TSalesPosGeoUnitHist tSalesPosGeoUnitHistentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPosGeoUnitHist tSalesPosGeoUnitHistentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.salesHierId = #{tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.getSalesHierId}",
			"tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.salesPosId = #{tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.getSalesPosId}",
			"tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.geoId = #{tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.getGeoId}",
			"tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.revId = #{tSalesPosGeoUnitHistentity.tSalesPosGeoUnitHistId.getRevId}",
			"Date(tSalesPosGeoUnitHistentity.timestamp) = '#{tSalesPosGeoUnitHistentity.getTimestamp}'",
			"tSalesPosGeoUnitHistentity.activeFlag = #{tSalesPosGeoUnitHistentity.getActiveFlag}",
			"tSalesPosGeoUnitHistentity.createdBy = #{tSalesPosGeoUnitHistentity.getCreatedBy}",
			"Date(tSalesPosGeoUnitHistentity.createDt) = '#{tSalesPosGeoUnitHistentity.getCreateDt}'",
			"tSalesPosGeoUnitHistentity.updatedBy = #{tSalesPosGeoUnitHistentity.getUpdatedBy}",
			"Date(tSalesPosGeoUnitHistentity.updateDt) = '#{tSalesPosGeoUnitHistentity.getUpdateDt}'",
			"tSalesPosGeoUnitHistentity.tenantId = #{tSalesPosGeoUnitHistentity.getTenantId}",
			"tSalesPosGeoUnitHistentity.revType like '#{tSalesPosGeoUnitHistentity.getRevType}%'",
			"tSalesPosGeoUnitHistentity.tSalesPosGeoUnit.tSalesPosGeoUnitId = #{tSalesPosGeoUnitHistentity.tSalesPosGeoUnit.getTSalesPosGeoUnitId}" };

	/**
	 * Stores a new TSalesPosGeoUnitHist entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be persisted
	 * @return tSalesPosGeoUnitHist Persisted TSalesPosGeoUnitHist object
	 */
	public TSalesPosGeoUnitHist createTSalesPosGeoUnitHist(final TSalesPosGeoUnitHist tSalesPosGeoUnitHist) {
		LOGGER.info("=========== Create TSalesPosGeoUnitHist ===========");
		return genericDAO.store(tSalesPosGeoUnitHist);
	}

	/**
	 * Deletes a TSalesPosGeoUnitHist entity object from the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be deleted
	 */
	public void deleteTSalesPosGeoUnitHist(final TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId) {
		LOGGER.info("=========== Delete TSalesPosGeoUnitHist ===========");
		final TSalesPosGeoUnitHist tSalesPosGeoUnitHist = genericDAO.get(clazz, tSalesPosGeoUnitHistId);
		genericDAO.remove(tSalesPosGeoUnitHist);
	}

	/**
	 * Updates a TSalesPosGeoUnitHist entity object in to the persistent store
	 * 
	 * @param tSalesPosGeoUnitHist
	 *            TSalesPosGeoUnitHist Entity object to be updated
	 * @return tSalesPosGeoUnitHist Persisted TSalesPosGeoUnitHist object
	 */
	public TSalesPosGeoUnitHist updateTSalesPosGeoUnitHist(final TSalesPosGeoUnitHist tSalesPosGeoUnitHist) {
		LOGGER.info("=========== Update TSalesPosGeoUnitHist ===========");
		return genericDAO.update(tSalesPosGeoUnitHist);
	}

	/**
	 * Retrieve an TSalesPosGeoUnitHist object based on given TSalesPosGeoUnitHist TSalesPosGeoUnitHistId.
	 * 
	 * @param tSalesPosGeoUnitHistId
	 *            the primary key value of the TSalesPosGeoUnitHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosGeoUnitHist findTSalesPosGeoUnitHistById(final TSalesPosGeoUnitHistId tSalesPosGeoUnitHistId) {
		LOGGER.info("find TSalesPosGeoUnitHist instance with TSalesPosGeoUnitHistId: " + tSalesPosGeoUnitHistId);
		return genericDAO.get(clazz, tSalesPosGeoUnitHistId);
	}

	/**
	 * Retrieve TSalesPosGeoUnitHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnitHist> list of TSalesPosGeoUnitHist if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosGeoUnitHist> findTSalesPosGeoUnitHists(final SearchFilter<TSalesPosGeoUnitHist> searchFilter) {
		LOGGER.info("=========== Find TSalesPosGeoUnitHists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosGeoUnitHist tSalesPosGeoUnitHist = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosGeoUnitHistentity", tSalesPosGeoUnitHist);

		if (tSalesPosGeoUnitHist.getTSalesPosGeoUnit() == null) {
			jpaQuery.bind(TSALESPOSGEOUNIT, new TSalesPosGeoUnit());
		} else {
			LOGGER.info("tSalesPosGeoUnit {}"+ tSalesPosGeoUnitHist.getTSalesPosGeoUnit());

			jpaQuery.bind(TSALESPOSGEOUNIT, tSalesPosGeoUnitHist.getTSalesPosGeoUnit());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosGeoUnitHists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosGeoUnitHists(final SearchFilter<TSalesPosGeoUnitHist> searchFilter) {
		LOGGER.info("=========== Count TSalesPosGeoUnitHist ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosGeoUnitHist tSalesPosGeoUnitHist = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosGeoUnitHistentity", tSalesPosGeoUnitHist);

		if (tSalesPosGeoUnitHist.getTSalesPosGeoUnit() == null) {
			jpaCountQuery.bind(TSALESPOSGEOUNIT, new TSalesPosGeoUnit());
		} else {
			LOGGER.info("tSalesPosGeoUnit {}"+ tSalesPosGeoUnitHist.getTSalesPosGeoUnit());

			jpaCountQuery.bind(TSALESPOSGEOUNIT, tSalesPosGeoUnitHist.getTSalesPosGeoUnit());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesPosGeoUnitHist based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosGeoUnitHist> list of TSalesPosGeoUnitHists if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPosGeoUnitHist> getTSalesPosGeoUnitHistsByTSalesPosGeoUnit(
			final SearchFilter<TSalesPosGeoUnit> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosGeoUnitHistByTSalesPosGeoUnit", queryParam,
				index, maxresult);
	}

	/**
	 * Count TSalesPosGeoUnitHist based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosGeoUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPosGeoUnitHistsByTSalesPosGeoUnit(final SearchFilter<TSalesPosGeoUnit> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPosGeoUnitHistsByTSalesPosGeoUnit", queryParam);
	}

}
