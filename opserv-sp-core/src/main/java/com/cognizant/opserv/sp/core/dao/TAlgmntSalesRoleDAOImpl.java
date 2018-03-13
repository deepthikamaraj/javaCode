package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRole;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRoleId;
import com.cognizant.opserv.sp.core.entity.TOrgRole;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;
/**
 * Class provides API implementation for TAlgmntSalesRoleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntSalesRoleDAO")
public class TAlgmntSalesRoleDAOImpl implements TAlgmntSalesRoleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntSalesRoleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESHIER = "tAlgmntSalesHier";
	private static final String TORGROLE = "tOrgRole";

	private final Class<TAlgmntSalesRole> clazz;

	public Class<TAlgmntSalesRole> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntSalesRoleDAOImpl() {
		super();
		this.clazz = TAlgmntSalesRole.class;
	}

	private static final String JPAQL = "select tAlgmntSalesRoleentity from TAlgmntSalesRole tAlgmntSalesRoleentity";

	private static final String JPACOUNTQL = "select count(*) from TAlgmntSalesRole tAlgmntSalesRoleentity";

	private static final String[] RESTRICTIONS = {
			"tAlgmntSalesRoleentity.tAlgmntSalesRoleId.orgRoleId = #{tAlgmntSalesRoleentity.tAlgmntSalesRoleId.getOrgRoleId}",
			"tAlgmntSalesRoleentity.tAlgmntSalesRoleId.salesHierId = #{tAlgmntSalesRoleentity.tAlgmntSalesRoleId.getSalesHierId}",
			"tAlgmntSalesRoleentity.activeFlag = #{tAlgmntSalesRoleentity.getActiveFlag}",
			"Date(tAlgmntSalesRoleentity.effStartDt) = '#{tAlgmntSalesRoleentity.getEffStartDt}'",
			"Date(tAlgmntSalesRoleentity.effEndDt) = '#{tAlgmntSalesRoleentity.getEffEndDt}'",
			"tAlgmntSalesRoleentity.roleName like '#{tAlgmntSalesRoleentity.getRoleName}%'",
			"tAlgmntSalesRoleentity.createdBy = #{tAlgmntSalesRoleentity.getCreatedBy}",
			"Date(tAlgmntSalesRoleentity.createDt) = '#{tAlgmntSalesRoleentity.getCreateDt}'",
			"tAlgmntSalesRoleentity.updatedBy = #{tAlgmntSalesRoleentity.getUpdatedBy}",
			"Date(tAlgmntSalesRoleentity.updateDt) = '#{tAlgmntSalesRoleentity.getUpdateDt}'",
			"tAlgmntSalesRoleentity.tenantId = #{tAlgmntSalesRoleentity.getTenantId}",
//            "tAlgmntSalesRoleentity.sysRoleId = #{tAlgmntSalesRoleentity.getSysRoleId}",
			"tAlgmntSalesRoleentity.algmntId = #{tAlgmntSalesRoleentity.getAlgmntId}",
			"tAlgmntSalesRoleentity.bussUnitId = #{tAlgmntSalesRoleentity.getBussUnitId}",
			"tAlgmntSalesRoleentity.salesTeamId = #{tAlgmntSalesRoleentity.getSalesTeamId}",
//			"tAlgmntSalesRoleentity.tPers.staffId = #{tAlgmntSalesRoleentity.tPers.getStaffId}",
			"tAlgmntSalesRoleentity.tAlgmntSalesHier.salesHierId = #{tAlgmntSalesRoleentity.tAlgmntSalesHier.getSalesHierId}",
			"tAlgmntSalesRoleentity.tOrgRole.orgRoleId = #{tAlgmntSalesRoleentity.tOrgRole.getOrgRoleId}" };

	/**
	 * Stores a new TAlgmntSalesRole entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be persisted
	 * @return tAlgmntSalesRole Persisted TAlgmntSalesRole object
	 */
	public TAlgmntSalesRole createTAlgmntSalesRole(final TAlgmntSalesRole tAlgmntSalesRole) {
		LOGGER.info("=========== Create TAlgmntSalesRole ===========");
		return genericDAO.store(tAlgmntSalesRole);
	}

	/**
	 * Deletes a TAlgmntSalesRole entity object from the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be deleted
	 */
	public void deleteTAlgmntSalesRole(final TAlgmntSalesRoleId tAlgmntSalesRoleId) {
		LOGGER.info("=========== Delete TAlgmntSalesRole ===========");
		final TAlgmntSalesRole tAlgmntSalesRole = genericDAO.get(clazz, tAlgmntSalesRoleId);
		genericDAO.remove(tAlgmntSalesRole);
	}

	/**
	 * Updates a TAlgmntSalesRole entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesRole
	 *            TAlgmntSalesRole Entity object to be updated
	 * @return tAlgmntSalesRole Persisted TAlgmntSalesRole object
	 */
	public TAlgmntSalesRole updateTAlgmntSalesRole(final TAlgmntSalesRole tAlgmntSalesRole) {
		LOGGER.info("=========== Update TAlgmntSalesRole ===========");
		return genericDAO.update(tAlgmntSalesRole);
	}

	/**
	 * Retrieve an TAlgmntSalesRole object based on given TAlgmntSalesRole TAlgmntSalesRoleId.
	 * 
	 * @param tAlgmntSalesRoleId
	 *            the primary key value of the TAlgmntSalesRole Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntSalesRole findTAlgmntSalesRoleById(final TAlgmntSalesRoleId tAlgmntSalesRoleId) {
		LOGGER.info("find TAlgmntSalesRole instance with TAlgmntSalesRoleId: " + tAlgmntSalesRoleId);
		return genericDAO.get(clazz, tAlgmntSalesRoleId);
	}

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRole if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntSalesRole> findTAlgmntSalesRoles(final SearchFilter<TAlgmntSalesRole> searchFilter) {
		LOGGER.info("=========== Find TAlgmntSalesRoles ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntSalesRole tAlgmntSalesRole = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntSalesRoleentity", tAlgmntSalesRole);

		if (tAlgmntSalesRole.getTAlgmntSalesHier() == null) {
			jpaQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}" + tAlgmntSalesRole.getTAlgmntSalesHier());

			jpaQuery.bind(TALGMNTSALESHIER, tAlgmntSalesRole.getTAlgmntSalesHier());
		}

		if (tAlgmntSalesRole.getTOrgRole() == null) {
			jpaQuery.bind(TORGROLE, new TOrgRole());
		} else {
			LOGGER.info("tOrgRole {}" + tAlgmntSalesRole.getTOrgRole());

			jpaQuery.bind(TORGROLE, tAlgmntSalesRole.getTOrgRole());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntSalesRoles.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntSalesRoles(final SearchFilter<TAlgmntSalesRole> searchFilter) {
		LOGGER.info("=========== Count TAlgmntSalesRole ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntSalesRole tAlgmntSalesRole = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntSalesRoleentity", tAlgmntSalesRole);

		if (tAlgmntSalesRole.getTAlgmntSalesHier() == null) {
			jpaCountQuery.bind(TALGMNTSALESHIER, new TAlgmntSalesHier());
		} else {
			LOGGER.info("tAlgmntSalesHier {}" + tAlgmntSalesRole.getTAlgmntSalesHier());

			jpaCountQuery.bind(TALGMNTSALESHIER, tAlgmntSalesRole.getTAlgmntSalesHier());
		}

		if (tAlgmntSalesRole.getTOrgRole() == null) {
			jpaCountQuery.bind(TORGROLE, new TOrgRole());
		} else {
			LOGGER.info("tOrgRole {}" + tAlgmntSalesRole.getTOrgRole());

			jpaCountQuery.bind(TORGROLE, tAlgmntSalesRole.getTOrgRole());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntSalesRole> getTAlgmntSalesRolesByTAlgmntSalesHier(
			final SearchFilter<TAlgmntSalesHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesRoleByTAlgmntSalesHier", queryParam, index,
				maxresult);
	}

	/**
	 * Count TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesRolesByTAlgmntSalesHier(final SearchFilter<TAlgmntSalesHier> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesRolesByTAlgmntSalesHier", queryParam);
	}

	/**
	 * Retrieve TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesRole> list of TAlgmntSalesRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntSalesRole> getTAlgmntSalesRolesByTOrgRole(final SearchFilter<TOrgRole> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesRoleByTOrgRole", queryParam, index, maxresult);
	}

	/**
	 * Count TAlgmntSalesRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesRolesByTOrgRole(final SearchFilter<TOrgRole> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesRolesByTOrgRole", queryParam);
	}
	/**
	 * Gets the t algmnt sales role b.
	 *
	 * @param searchFilter the search filter
	 * @return the t algmnt sales role b
	 */
   @Override
	public List<TAlgmntSalesRole> getTAlgmntSalesRoleB(
			SearchFilter<TAlgmntSalesRole> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesRole tAlgmntSalesRole = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tAlgmntSalesRole.getTenantId());
		paramList.add(tAlgmntSalesRole.getAlgmntId());
		paramList.add(tAlgmntSalesRole.getBussUnitId());
		paramList.add(tAlgmntSalesRole.getSalesTeamId());
		paramList.add(tAlgmntSalesRole.getTAlgmntSalesRoleId().getSalesHierId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntSalesRoleWithTenant",paramList, index, maxresult);
	}
   /**
	 * Gets the t algmnt sales role by named query.
	 *
	 * @param paramList the param list
	 * @return the t algmnt sales role by named query
	 */
	@Override
	public List<TAlgmntSalesRole> getTAlgmntSalesRoleByNamedQuery(List paramList) {		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTAlgmntSalesRoleWithTenant", paramList, -1, -1);
	}
	/**
	 * Gets the t alignment sales role id by sales hier id.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the t alignment sales role id by sales hier id
	 */
	@Override
	public List<TAlgmntSalesRole> getTAlignmentSalesRoleIdBySalesHierId(Long salesHierId, short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(salesHierId);
		list.add(tenantId);
		return  genericDAO.findEntitiesByNamedQueryMultiCond("getTAlignmentSalesRoleIdBySalesHierId", list, 0, -1);
	}
	/**
	 * Gets the role id details.
	 *
	 * @param searchFilter the search filter
	 * @return the role id details
	 */
	@Override
	public List<TAlgmntSalesRole> getRoleIdDetails(
			SearchFilter<TAlgmntSalesRole> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesRole tAlgmntSalesRole = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
	
		List paramList = new ArrayList();
		paramList.add(tAlgmntSalesRole.getTAlgmntSalesRoleId().getSalesHierId());
		paramList.add(tAlgmntSalesRole.getRoleName());
		paramList.add(tAlgmntSalesRole.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindRoleDetails",paramList, index, maxresult);
	}

	/**
	 * Find active roles in t sales algmnt role.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object> findActiveRolesInTSalesAlgmntRole(Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveRolesInSalesAlgmntRole", paramList,0, -1);
	}
	/**
	 * Find active mapped roles in sales algmnt role.
	 *
	 * @param tenantId the tenant id
	 * @param orgRoleId the org role id
	 * @return the integer
	 */
	@Override
	public Integer findActiveMappedRolesInSalesAlgmntRole(Short tenantId,Integer orgRoleId) {	
		List<Object> paramList = new ArrayList<Object>();	
		paramList.add(orgRoleId);
		paramList.add(tenantId);
		List<Long> recordsCount = genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveMappedRolesInAlgmntSalesRole", paramList,0, -1);
		return null; //recordsCount.get(0).intValue();
	}
	
	/**
	 * @param algmntId
	 * @param buId
	 * @param stId
	 * @param shId
	 * @param tenantId
	 * @param activeFlag
	 * @return List<TAlgmntSalesRole>
	 */
	@Override
	public List<TAlgmntSalesRole> getActiveTAlgmntSalesRole(
			Long algmntId,Long buId,Long stId,Long shId,short tenantId,Character activeFlag) {
	
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(shId);
		paramList.add(tenantId);
		paramList.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTAlgmntSalesRole",paramList, 0, -1);
	}

}
