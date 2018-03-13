package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.core.entity.TOrgRole;
import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TOrgRoleDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tOrgRoleDAO")
public class TOrgRoleDAOImpl implements TOrgRoleDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TOrgRoleDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TORG = "tOrg";
	private static final String TORGROLE = "tOrgRole";
	private static final String TROLELIST = "tRoleList";

	private final Class<TOrgRole> clazz;

	public Class<TOrgRole> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TOrgRoleDAOImpl() {
		super();
		this.clazz = TOrgRole.class;
	}

	private static final String JPAQL = "select tOrgRoleentity from TOrgRole tOrgRoleentity";

	private static final String JPACOUNTQL = "select count(tOrgRoleentity) from TOrgRole tOrgRoleentity";

	private static final String[] RESTRICTIONS = { "tOrgRoleentity.orgRoleId = #{tOrgRoleentity.getOrgRoleId}",
			"tOrgRoleentity.roleName like '#{tOrgRoleentity.getRoleName}%'",
			"tOrgRoleentity.createdBy = #{tOrgRoleentity.getCreatedBy}",
			"Date(tOrgRoleentity.createDt) = '#{tOrgRoleentity.getCreateDt}'",
			"tOrgRoleentity.updatedBy = #{tOrgRoleentity.getUpdatedBy}",
			"Date(tOrgRoleentity.updateDt) = '#{tOrgRoleentity.getUpdateDt}'",
			"tOrgRoleentity.tenantId = #{tOrgRoleentity.getTenantId}",
			"tOrgRoleentity.roleDesc like '#{tOrgRoleentity.getRoleDesc}%'",
			"tOrgRoleentity.activeFlag = #{tOrgRoleentity.getActiveFlag}",
			"Date(tOrgRoleentity.effStartDt) = '#{tOrgRoleentity.getEffStartDt}'",
			"Date(tOrgRoleentity.effEndDt) = '#{tOrgRoleentity.getEffEndDt}'",
			"tOrgRoleentity.tOrg.orgId = #{tOrgRoleentity.tOrg.getOrgId}",
			"tOrgRoleentity.tOrgRole.orgRoleId = #{tOrgRoleentity.tOrgRole.getOrgRoleId}",
			"tOrgRoleentity.tRoleList.roleId = #{tOrgRoleentity.tRoleList.getRoleId}" };

	/**
	 * Stores a new TOrgRole entity object in to the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be persisted
	 * @return tOrgRole Persisted TOrgRole object
	 */
	public TOrgRole createTOrgRole(final TOrgRole tOrgRole) {
		LOGGER.info("=========== Create TOrgRole ===========");
		return genericDAO.store(tOrgRole);
	}

	/**
	 * Deletes a TOrgRole entity object from the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be deleted
	 */
	public void deleteTOrgRole(final Integer orgRoleId) {
		LOGGER.info("=========== Delete TOrgRole ===========");
		final TOrgRole tOrgRole = genericDAO.get(clazz, orgRoleId);
		genericDAO.remove(tOrgRole);
	}

	/**
	 * Updates a TOrgRole entity object in to the persistent store
	 * 
	 * @param tOrgRole
	 *            TOrgRole Entity object to be updated
	 * @return tOrgRole Persisted TOrgRole object
	 */
	public TOrgRole updateTOrgRole(final TOrgRole tOrgRole) {
		LOGGER.info("=========== Update TOrgRole ===========");
		return genericDAO.update(tOrgRole);
	}

	/**
	 * Retrieve an TOrgRole object based on given TOrgRole orgRoleId.
	 * 
	 * @param tOrgRoleId
	 *            the primary key value of the TOrgRole Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TOrgRole findTOrgRoleById(final Integer tOrgRoleId) {
		LOGGER.info("find TOrgRole instance with orgRoleId: " + tOrgRoleId);
		return genericDAO.get(clazz, tOrgRoleId);
	}

	/**
	 * Retrieve TOrgRole based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRole if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TOrgRole> findTOrgRoles(final SearchFilter<TOrgRole> searchFilter) {
		LOGGER.info("=========== Find TOrgRoles ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TOrgRole tOrgRole = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tOrgRoleentity", tOrgRole);

		if (tOrgRole.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tOrgRole.getTOrg());

			jpaQuery.bind(TORG, tOrgRole.getTOrg());
		}

		if (tOrgRole.getTOrgRole() == null) {
			jpaQuery.bind(TORGROLE, new TOrgRole());
		} else {
			LOGGER.info("tOrgRole {}"+ tOrgRole.getTOrgRole());

			jpaQuery.bind(TORGROLE, tOrgRole.getTOrgRole());
		}

		if (tOrgRole.getTRoleList() == null) {
			jpaQuery.bind(TROLELIST, new TRoleList());
		} else {
			LOGGER.info("tRoleList {}"+ tOrgRole.getTRoleList());

			jpaQuery.bind(TROLELIST, tOrgRole.getTRoleList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TOrgRoles.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTOrgRoles(final SearchFilter<TOrgRole> searchFilter) {
		LOGGER.info("=========== Count TOrgRole ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TOrgRole tOrgRole = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tOrgRoleentity", tOrgRole);

		if (tOrgRole.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tOrgRole.getTOrg());

			jpaCountQuery.bind(TORG, tOrgRole.getTOrg());
		}

		if (tOrgRole.getTOrgRole() == null) {
			jpaCountQuery.bind(TORGROLE, new TOrgRole());
		} else {
			LOGGER.info("tOrgRole {}"+ tOrgRole.getTOrgRole());

			jpaCountQuery.bind(TORGROLE, tOrgRole.getTOrgRole());
		}

		if (tOrgRole.getTRoleList() == null) {
			jpaCountQuery.bind(TROLELIST, new TRoleList());
		} else {
			LOGGER.info("tRoleList {}"+ tOrgRole.getTRoleList());

			jpaCountQuery.bind(TROLELIST, tOrgRole.getTRoleList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgRole> getTOrgRolesByTOrg(final SearchFilter<TOrg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgRoleByTOrg", tOrgList, index, maxresult);
	}

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgRolesByTOrg(final SearchFilter<TOrg> searchFilter) {

		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgRolesByTOrg", tOrgList);
	}

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgRole> getTOrgRolesByTOrgRole(final SearchFilter<TOrgRole> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrgRole tOrgRole = searchFilter.getEntityClass();
		List<Object> tOrgRoleList = new ArrayList<Object>();
		tOrgRoleList.add(tOrgRole);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgRoleByTOrgRole", tOrgRoleList, index, maxresult);
	}

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgRole type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgRolesByTOrgRole(final SearchFilter<TOrgRole> searchFilter) {

		final TOrgRole tOrgRole = searchFilter.getEntityClass();
		List<Object> tOrgRoleList = new ArrayList<Object>();
		tOrgRoleList.add(tOrgRole);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgRolesByTOrgRole", tOrgRoleList);
	}

	/**
	 * Retrieve TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgRole> list of TOrgRoles if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgRole> getTOrgRolesByTRoleList(final SearchFilter<TRoleList> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRoleList tRoleList = searchFilter.getEntityClass();
		List<Object> tRoleLists = new ArrayList<Object>();
		tRoleLists.add(tRoleList);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgRoleByTRoleList", tRoleLists, index, maxresult);
	}

	/**
	 * Count TOrgRole based on given search criteria using JPA named Query.
	 * The search criteria is of TRoleList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgRolesByTRoleList(final SearchFilter<TRoleList> searchFilter) {

		final TRoleList tRoleList = searchFilter.getEntityClass();
		List<Object> tRoleLists = new ArrayList<Object>();
		tRoleLists.add(tRoleList);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgRolesByTRoleList", tRoleLists);
	}

	@Override
	public List<Object[]> findRoleIdOrgRoleIdAndName(Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add('Y');
		paramList.add(tenantId);
		int index=0;
		int maxresult=-1;
		List<Object[]> tOrgRole = genericDAO.findEntitiesByNamedQueryMultiCond("FindRoleIdOrgRoleIdAndRoleName", paramList, index, maxresult);
		return tOrgRole;
	}
	
	@Override
	public List<Object[]> findUnMappedRoleIdOrgRoleIdAndRoleName(Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add('Y');
		paramList.add(tenantId);
		int index=0;
		int maxresult=-1;
		List<Object[]> tOrgRole = genericDAO.findEntitiesByNamedQueryMultiCond("FindUnMappedRoleIdOrgRoleIdAndRoleName", paramList, index, maxresult);
		return tOrgRole;
	}

}
