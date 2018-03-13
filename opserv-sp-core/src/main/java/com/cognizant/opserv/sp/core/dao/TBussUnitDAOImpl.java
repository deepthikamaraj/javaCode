package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussUnitDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussUnitDAO")
public class TBussUnitDAOImpl implements TBussUnitDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussUnitDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TORG = "tOrg";

	private final Class<TBussUnit> clazz;

	public Class<TBussUnit> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussUnitDAOImpl() {
		super();
		this.clazz = TBussUnit.class;
	}

	private static final String JPAQL = "select tBussUnitentity from TBussUnit tBussUnitentity";

	private static final String JPACOUNTQL = "select count(tBussUnitentity) from TBussUnit tBussUnitentity";

	private static final String[] RESTRICTIONS = { "tBussUnitentity.bussUnitId = #{tBussUnitentity.getBussUnitId}",
			"tBussUnitentity.bussUnitName like '#{tBussUnitentity.getBussUnitName}%'",
			"tBussUnitentity.bussUnitDesc like '#{tBussUnitentity.getBussUnitDesc}%'",
			"tBussUnitentity.createdBy = #{tBussUnitentity.getCreatedBy}",
			"Date(tBussUnitentity.createDt) = '#{tBussUnitentity.getCreateDt}'",
			"tBussUnitentity.updatedBy = #{tBussUnitentity.getUpdatedBy}",
			"Date(tBussUnitentity.updateDt) = '#{tBussUnitentity.getUpdateDt}'",
			"tBussUnitentity.tenantId = #{tBussUnitentity.getTenantId}",
			"tBussUnitentity.bussUnitCd like '#{tBussUnitentity.getBussUnitCd}%'",
			"tBussUnitentity.activeFlag = #{tBussUnitentity.getActiveFlag}",
			"Date(tBussUnitentity.effStartDt) = '#{tBussUnitentity.getEffStartDt}'",
			"Date(tBussUnitentity.effEndDt) = '#{tBussUnitentity.getEffEndDt}'",
			"tBussUnitentity.extBussUnitId like '#{tBussUnitentity.getExtBussUnitId}%'",
			"tBussUnitentity.tOrg.orgId = #{tBussUnitentity.tOrg.getOrgId}" };

	/**
	 * Stores a new TBussUnit entity object in to the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be persisted
	 * @return tBussUnit Persisted TBussUnit object
	 */
	public TBussUnit createTBussUnit(final TBussUnit tBussUnit) {
		LOGGER.info("=========== Create TBussUnit ===========");
		return genericDAO.store(tBussUnit);
	}

	/**
	 * Deletes a TBussUnit entity object from the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be deleted
	 */
	public void deleteTBussUnit(final Long bussUnitId) {
		LOGGER.info("=========== Delete TBussUnit ===========");
		final TBussUnit tBussUnit = genericDAO.get(clazz, bussUnitId);
		genericDAO.remove(tBussUnit);
	}

	/**
	 * Updates a TBussUnit entity object in to the persistent store
	 * 
	 * @param tBussUnit
	 *            TBussUnit Entity object to be updated
	 * @return tBussUnit Persisted TBussUnit object
	 */
	public TBussUnit updateTBussUnit(final TBussUnit tBussUnit) {
		LOGGER.info("=========== Update TBussUnit ===========");
		return genericDAO.update(tBussUnit);
	}

	/**
	 * Retrieve an TBussUnit object based on given TBussUnit bussUnitId.
	 * 
	 * @param tBussUnitId
	 *            the primary key value of the TBussUnit Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussUnit findTBussUnitById(final Long tBussUnitId) {
		LOGGER.info("find TBussUnit instance with bussUnitId: " + tBussUnitId);
		return genericDAO.get(clazz, tBussUnitId);
	}

	/**
	 * Retrieve TBussUnit based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussUnit> list of TBussUnit if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussUnit> findTBussUnits(final SearchFilter<TBussUnit> searchFilter) {
		LOGGER.info("=========== Find TBussUnits ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussUnit tBussUnit = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussUnitentity", tBussUnit);

		if (tBussUnit.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tBussUnit.getTOrg());

			jpaQuery.bind(TORG, tBussUnit.getTOrg());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussUnits.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussUnits(final SearchFilter<TBussUnit> searchFilter) {
		LOGGER.info("=========== Count TBussUnit ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussUnit tBussUnit = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussUnitentity", tBussUnit);

		if (tBussUnit.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tBussUnit.getTOrg());

			jpaCountQuery.bind(TORG, tBussUnit.getTOrg());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussUnit> list of TBussUnits if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussUnit> getTBussUnitsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussUnitByTOrg", tOrgList, index, maxresult);
	}

	/**
	 * Count TBussUnit based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussUnitsByTOrg(final SearchFilter<TOrg> searchFilter) {

		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		return genericDAO.findEntitiesByNamedQuery("CountTBussUnitsByTOrg", tOrgList);
	}

	/**
	 * Find t buss unitsby date.
	 *
	 * @param searchFilter the search filter
	 * @return the list
	 */
	public List<TBussUnit> findTBussUnitsbyDate(
			SearchFilter<TBussUnit> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussUnit tBussUnit = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tBussUnit.getActiveFlag());
		paramList.add(tBussUnit.getEffEndDt());
		paramList.add(tBussUnit.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussUnitByEffDate",paramList, index, maxresult);
	}
	/**
	 * Find all buss unit.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object[]> findAllBussUnit(Short tenantId){
		List<Object> tenantIdList = new ArrayList<Object>();
		tenantIdList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQuery("FindTBussUnits" ,tenantIdList);
	}
	/**
	 * Fetch buss unit names by ids.
	 *
	 * @param Ids the ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<String> fetchBussUnitNamesByIds(List<Long> Ids, Short tenantId) {

		List paramList = new ArrayList();
		paramList.add(Ids);
		paramList.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTBussUnitNamesById", paramList, 0, -1);
	}

	@Override
	public List<TBussUnit> findTBussUnitBySearchCriteria(
			ISearchCriteria criteria) {
		return genericDAO.search(clazz, criteria);
	}
}
