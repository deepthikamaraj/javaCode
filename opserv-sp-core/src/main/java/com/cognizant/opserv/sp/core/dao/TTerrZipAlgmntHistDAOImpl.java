package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntHist;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntHistId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTerrZipAlgmntHistDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTerrZipAlgmntHistDAO")
public class TTerrZipAlgmntHistDAOImpl implements TTerrZipAlgmntHistDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TTerrZipAlgmntHistDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TTerrZipAlgmntHist> clazz;

	public Class<TTerrZipAlgmntHist> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTerrZipAlgmntHistDAOImpl() {
		super();
		this.clazz = TTerrZipAlgmntHist.class;
	}

	private static final String JPAQL = "select tTerrZipAlgmntHistentity from TTerrZipAlgmntHist tTerrZipAlgmntHistentity";

	private static final String JPACOUNTQL = "select count(*) from TTerrZipAlgmntHist tTerrZipAlgmntHistentity";

	private static final String[] RESTRICTIONS = {
			"tTerrZipAlgmntHistentity.tTerrZipAlgmntHistId.revId = #{tTerrZipAlgmntHistentity.tTerrZipAlgmntHistId.getRevId}",
			"tTerrZipAlgmntHistentity.tTerrZipAlgmntHistId.geoZipId = #{tTerrZipAlgmntHistentity.tTerrZipAlgmntHistId.getGeoZipId}",
			"Date(tTerrZipAlgmntHistentity.timestamp) = '#{tTerrZipAlgmntHistentity.getTimestamp}'",
			"tTerrZipAlgmntHistentity.assignedFlag = #{tTerrZipAlgmntHistentity.getAssignedFlag}",
			"tTerrZipAlgmntHistentity.pointZipFlag = #{tTerrZipAlgmntHistentity.getPointZipFlag}",
			"tTerrZipAlgmntHistentity.activeFlag = #{tTerrZipAlgmntHistentity.getActiveFlag}",
			"tTerrZipAlgmntHistentity.salesHierId = #{tTerrZipAlgmntHistentity.getSalesHierId}",
			"tTerrZipAlgmntHistentity.postalCd like '#{tTerrZipAlgmntHistentity.getPostalCd}%'",
			"tTerrZipAlgmntHistentity.createdBy = #{tTerrZipAlgmntHistentity.getCreatedBy}",
			"Date(tTerrZipAlgmntHistentity.createDt) = '#{tTerrZipAlgmntHistentity.getCreateDt}'",
			"tTerrZipAlgmntHistentity.updatedBy = #{tTerrZipAlgmntHistentity.getUpdatedBy}",
			"Date(tTerrZipAlgmntHistentity.updateDt) = '#{tTerrZipAlgmntHistentity.getUpdateDt}'",
			"tTerrZipAlgmntHistentity.tenantId = #{tTerrZipAlgmntHistentity.getTenantId}",
			"tTerrZipAlgmntHistentity.salesPosId = #{tTerrZipAlgmntHistentity.getSalesPosId}",
			"tTerrZipAlgmntHistentity.geoId = #{tTerrZipAlgmntHistentity.getGeoId}",
			"tTerrZipAlgmntHistentity.revType like '#{tTerrZipAlgmntHistentity.getRevType}%'" };

	/**
	 * Stores a new TTerrZipAlgmntHist entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be persisted
	 * @return tTerrZipAlgmntHist Persisted TTerrZipAlgmntHist object
	 */
	public TTerrZipAlgmntHist createTTerrZipAlgmntHist(final TTerrZipAlgmntHist tTerrZipAlgmntHist) {
		LOGGER.info("=========== Create TTerrZipAlgmntHist ===========");
		return genericDAO.store(tTerrZipAlgmntHist);
	}

	/**
	 * Deletes a TTerrZipAlgmntHist entity object from the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be deleted
	 */
	public void deleteTTerrZipAlgmntHist(final TTerrZipAlgmntHistId tTerrZipAlgmntHistId) {
		LOGGER.info("=========== Delete TTerrZipAlgmntHist ===========");
		final TTerrZipAlgmntHist tTerrZipAlgmntHist = genericDAO.get(clazz, tTerrZipAlgmntHistId);
		genericDAO.remove(tTerrZipAlgmntHist);
	}

	/**
	 * Updates a TTerrZipAlgmntHist entity object in to the persistent store
	 * 
	 * @param tTerrZipAlgmntHist
	 *            TTerrZipAlgmntHist Entity object to be updated
	 * @return tTerrZipAlgmntHist Persisted TTerrZipAlgmntHist object
	 */
	public TTerrZipAlgmntHist updateTTerrZipAlgmntHist(final TTerrZipAlgmntHist tTerrZipAlgmntHist) {
		LOGGER.info("=========== Update TTerrZipAlgmntHist ===========");
		return genericDAO.update(tTerrZipAlgmntHist);
	}

	/**
	 * Retrieve an TTerrZipAlgmntHist object based on given TTerrZipAlgmntHist TTerrZipAlgmntHistId.
	 * 
	 * @param tTerrZipAlgmntHistId
	 *            the primary key value of the TTerrZipAlgmntHist Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTerrZipAlgmntHist findTTerrZipAlgmntHistById(final TTerrZipAlgmntHistId tTerrZipAlgmntHistId) {
		LOGGER.info("find TTerrZipAlgmntHist instance with TTerrZipAlgmntHistId: " + tTerrZipAlgmntHistId);
		return genericDAO.get(clazz, tTerrZipAlgmntHistId);
	}

	/**
	 * Retrieve TTerrZipAlgmntHist based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTerrZipAlgmntHist> list of TTerrZipAlgmntHist if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTerrZipAlgmntHist> findTTerrZipAlgmntHists(final SearchFilter<TTerrZipAlgmntHist> searchFilter) {
		LOGGER.info("=========== Find TTerrZipAlgmntHists ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTerrZipAlgmntHist tTerrZipAlgmntHist = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTerrZipAlgmntHistentity", tTerrZipAlgmntHist);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTerrZipAlgmntHists.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTerrZipAlgmntHists(final SearchFilter<TTerrZipAlgmntHist> searchFilter) {
		LOGGER.info("=========== Count TTerrZipAlgmntHist ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTerrZipAlgmntHist tTerrZipAlgmntHist = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTerrZipAlgmntHistentity", tTerrZipAlgmntHist);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
