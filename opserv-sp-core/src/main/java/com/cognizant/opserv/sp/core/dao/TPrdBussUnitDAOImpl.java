package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TPrdBussUnit;
import com.cognizant.opserv.sp.core.entity.TPrdBussUnitId;
import com.cognizant.opserv.sp.core.entity.TPrdConfig;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdBussUnitDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdBussUnitDAO")
public class TPrdBussUnitDAOImpl implements TPrdBussUnitDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdBussUnitDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRDCONFIG = "tPrdConfig";
	private static final String TBUSSUNIT = "tBussUnit";

	private final Class<TPrdBussUnit> clazz;

	public Class<TPrdBussUnit> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdBussUnitDAOImpl() {
		super();
		this.clazz = TPrdBussUnit.class;
	}

	private static final String JPAQL = "select tPrdBussUnitentity from TPrdBussUnit tPrdBussUnitentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdBussUnit tPrdBussUnitentity";

	private static final String[] RESTRICTIONS = {
			"tPrdBussUnitentity.tPrdBussUnitId.bussUnitId = #{tPrdBussUnitentity.tPrdBussUnitId.getBussUnitId}",
			"tPrdBussUnitentity.tPrdBussUnitId.prdConfigId = #{tPrdBussUnitentity.tPrdBussUnitId.getPrdConfigId}",
			"tPrdBussUnitentity.activeFlag = #{tPrdBussUnitentity.getActiveFlag}",
			"Date(tPrdBussUnitentity.effStartDt) = '#{tPrdBussUnitentity.getEffStartDt}'",
			"Date(tPrdBussUnitentity.effEndDt) = '#{tPrdBussUnitentity.getEffEndDt}'",
			"tPrdBussUnitentity.createdBy = #{tPrdBussUnitentity.getCreatedBy}",
			"Date(tPrdBussUnitentity.createDt) = '#{tPrdBussUnitentity.getCreateDt}'",
			"tPrdBussUnitentity.updatedBy = #{tPrdBussUnitentity.getUpdatedBy}",
			"Date(tPrdBussUnitentity.updateDt) = '#{tPrdBussUnitentity.getUpdateDt}'",
			"tPrdBussUnitentity.tenantId = #{tPrdBussUnitentity.getTenantId}",
			"tPrdBussUnitentity.tPrdConfig.prdConfigId = #{tPrdBussUnitentity.tPrdConfig.getPrdConfigId}",
			"tPrdBussUnitentity.tBussUnit.bussUnitId = #{tPrdBussUnitentity.tBussUnit.getBussUnitId}" };

	/**
	 * Stores a new TPrdBussUnit entity object in to the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be persisted
	 * @return tPrdBussUnit Persisted TPrdBussUnit object
	 */
	public TPrdBussUnit createTPrdBussUnit(final TPrdBussUnit tPrdBussUnit) {
		LOGGER.info("=========== Create TPrdBussUnit ===========");
		return genericDAO.store(tPrdBussUnit);
	}

	/**
	 * Deletes a TPrdBussUnit entity object from the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be deleted
	 */
	public void deleteTPrdBussUnit(final TPrdBussUnitId tPrdBussUnitId) {
		LOGGER.info("=========== Delete TPrdBussUnit ===========");
		final TPrdBussUnit tPrdBussUnit = genericDAO.get(clazz, tPrdBussUnitId);
		genericDAO.remove(tPrdBussUnit);
	}

	/**
	 * Updates a TPrdBussUnit entity object in to the persistent store
	 * 
	 * @param tPrdBussUnit
	 *            TPrdBussUnit Entity object to be updated
	 * @return tPrdBussUnit Persisted TPrdBussUnit object
	 */
	public TPrdBussUnit updateTPrdBussUnit(final TPrdBussUnit tPrdBussUnit) {
		LOGGER.info("=========== Update TPrdBussUnit ===========");
		return genericDAO.update(tPrdBussUnit);
	}

	/**
	 * Retrieve an TPrdBussUnit object based on given TPrdBussUnit TPrdBussUnitId.
	 * 
	 * @param tPrdBussUnitId
	 *            the primary key value of the TPrdBussUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdBussUnit findTPrdBussUnitById(final TPrdBussUnitId tPrdBussUnitId) {
		LOGGER.info("find TPrdBussUnit instance with TPrdBussUnitId: " + tPrdBussUnitId);
		return genericDAO.get(clazz, tPrdBussUnitId);
	}

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnit if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdBussUnit> findTPrdBussUnits(final SearchFilter<TPrdBussUnit> searchFilter) {
		LOGGER.info("=========== Find TPrdBussUnits ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdBussUnit tPrdBussUnit = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdBussUnitentity", tPrdBussUnit);

		if (tPrdBussUnit.getTPrdConfig() == null) {
			jpaQuery.bind(TPRDCONFIG, new TPrdConfig());
		} else {
			LOGGER.info("tPrdConfig {}"+ tPrdBussUnit.getTPrdConfig());

			jpaQuery.bind(TPRDCONFIG, tPrdBussUnit.getTPrdConfig());
		}

		if (tPrdBussUnit.getTBussUnit() == null) {
			jpaQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+ tPrdBussUnit.getTBussUnit());

			jpaQuery.bind(TBUSSUNIT, tPrdBussUnit.getTBussUnit());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdBussUnits.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdBussUnits(final SearchFilter<TPrdBussUnit> searchFilter) {
		LOGGER.info("=========== Count TPrdBussUnit ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdBussUnit tPrdBussUnit = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdBussUnitentity", tPrdBussUnit);

		if (tPrdBussUnit.getTPrdConfig() == null) {
			jpaCountQuery.bind(TPRDCONFIG, new TPrdConfig());
		} else {
			LOGGER.info("tPrdConfig {}"+ tPrdBussUnit.getTPrdConfig());

			jpaCountQuery.bind(TPRDCONFIG, tPrdBussUnit.getTPrdConfig());
		}

		if (tPrdBussUnit.getTBussUnit() == null) {
			jpaCountQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+ tPrdBussUnit.getTBussUnit());

			jpaCountQuery.bind(TBUSSUNIT, tPrdBussUnit.getTBussUnit());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdBussUnit> getTPrdBussUnitsByTPrdConfig(final SearchFilter<TPrdConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdBussUnitByTPrdConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdBussUnitsByTPrdConfig(final SearchFilter<TPrdConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdBussUnitsByTPrdConfig", queryParam);
	}

	/**
	 * Retrieve TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdBussUnit> list of TPrdBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdBussUnit> getTPrdBussUnitsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdBussUnitByTBussUnit", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdBussUnitsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdBussUnitsByTBussUnit", queryParam);
	}

}
