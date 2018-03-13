package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdHier;
import com.cognizant.opserv.sp.core.entity.TPrdHierGroup;
import com.cognizant.opserv.sp.core.entity.TPrdHierId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdHierDAO")
public class TPrdHierDAOImpl implements TPrdHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRDHIERGROUP = "tPrdHierGroup";
	private static final String TPRD = "tPrd";
	private static final String TPRDHIER = "tPrdHier";

	private final Class<TPrdHier> clazz;

	public Class<TPrdHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdHierDAOImpl() {
		super();
		this.clazz = TPrdHier.class;
	}

	private static final String JPAQL = "select tPrdHierentity from TPrdHier tPrdHierentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdHier tPrdHierentity";

	private static final String[] RESTRICTIONS = {
			"tPrdHierentity.tPrdHierId.groupId = #{tPrdHierentity.tPrdHierId.getGroupId}",
			"tPrdHierentity.tPrdHierId.prdId like '#{tPrdHierentity.tPrdHierId.getPrdId}%'",
			"tPrdHierentity.activeFlag = #{tPrdHierentity.getActiveFlag}",
			"Date(tPrdHierentity.effStartDt) = '#{tPrdHierentity.getEffStartDt}'",
			"Date(tPrdHierentity.effEndDt) = '#{tPrdHierentity.getEffEndDt}'",
			"tPrdHierentity.createdBy = #{tPrdHierentity.getCreatedBy}",
			"Date(tPrdHierentity.createDt) = '#{tPrdHierentity.getCreateDt}'",
			"tPrdHierentity.updatedBy = #{tPrdHierentity.getUpdatedBy}",
			"Date(tPrdHierentity.updateDt) = '#{tPrdHierentity.getUpdateDt}'",
			"tPrdHierentity.tenantId = #{tPrdHierentity.getTenantId}",
			"tPrdHierentity.tPrdHierGroup.groupId = #{tPrdHierentity.tPrdHierGroup.getGroupId}",
			"tPrdHierentity.tPrd.prdId = #{tPrdHierentity.tPrd.getPrdId}",
			"tPrdHierentity.tPrdHier.tPrdHierId = #{tPrdHierentity.tPrdHier.getTPrdHierId}" };

	/**
	 * Stores a new TPrdHier entity object in to the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be persisted
	 * @return tPrdHier Persisted TPrdHier object
	 */
	public TPrdHier createTPrdHier(final TPrdHier tPrdHier) {
		LOGGER.info("=========== Create TPrdHier ===========");
		return genericDAO.store(tPrdHier);
	}

	/**
	 * Deletes a TPrdHier entity object from the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be deleted
	 */
	public void deleteTPrdHier(final TPrdHierId tPrdHierId) {
		LOGGER.info("=========== Delete TPrdHier ===========");
		final TPrdHier tPrdHier = genericDAO.get(clazz, tPrdHierId);
		genericDAO.remove(tPrdHier);
	}

	/**
	 * Updates a TPrdHier entity object in to the persistent store
	 * 
	 * @param tPrdHier
	 *            TPrdHier Entity object to be updated
	 * @return tPrdHier Persisted TPrdHier object
	 */
	public TPrdHier updateTPrdHier(final TPrdHier tPrdHier) {
		LOGGER.info("=========== Update TPrdHier ===========");
		return genericDAO.update(tPrdHier);
	}

	/**
	 * Retrieve an TPrdHier object based on given TPrdHier TPrdHierId.
	 * 
	 * @param tPrdHierId
	 *            the primary key value of the TPrdHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdHier findTPrdHierById(final TPrdHierId tPrdHierId) {
		LOGGER.info("find TPrdHier instance with TPrdHierId: " + tPrdHierId);
		return genericDAO.get(clazz, tPrdHierId);
	}

	/**
	 * Retrieve TPrdHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdHier> findTPrdHiers(final SearchFilter<TPrdHier> searchFilter) {
		LOGGER.info("=========== Find TPrdHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdHier tPrdHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdHierentity", tPrdHier);

		if (tPrdHier.getTPrdHierGroup() == null) {
			jpaQuery.bind(TPRDHIERGROUP, new TPrdHierGroup());
		} else {
			LOGGER.info("tPrdHierGroup {}"+ tPrdHier.getTPrdHierGroup());

			jpaQuery.bind(TPRDHIERGROUP, tPrdHier.getTPrdHierGroup());
		}

		if (tPrdHier.getTPrd() == null) {
			jpaQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdHier.getTPrd());

			jpaQuery.bind(TPRD, tPrdHier.getTPrd());
		}

		if (tPrdHier.getTPrdHier() == null) {
			jpaQuery.bind(TPRDHIER, new TPrdHier());
		} else {
			LOGGER.info("tPrdHier {}"+ tPrdHier.getTPrdHier());

			jpaQuery.bind(TPRDHIER, tPrdHier.getTPrdHier());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdHiers(final SearchFilter<TPrdHier> searchFilter) {
		LOGGER.info("=========== Count TPrdHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdHier tPrdHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdHierentity", tPrdHier);

		if (tPrdHier.getTPrdHierGroup() == null) {
			jpaCountQuery.bind(TPRDHIERGROUP, new TPrdHierGroup());
		} else {
			LOGGER.info("tPrdHierGroup {}"+ tPrdHier.getTPrdHierGroup());

			jpaCountQuery.bind(TPRDHIERGROUP, tPrdHier.getTPrdHierGroup());
		}

		if (tPrdHier.getTPrd() == null) {
			jpaCountQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdHier.getTPrd());

			jpaCountQuery.bind(TPRD, tPrdHier.getTPrd());
		}

		if (tPrdHier.getTPrdHier() == null) {
			jpaCountQuery.bind(TPRDHIER, new TPrdHier());
		} else {
			LOGGER.info("tPrdHier {}"+ tPrdHier.getTPrdHier());

			jpaCountQuery.bind(TPRDHIER, tPrdHier.getTPrdHier());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHier> getTPrdHiersByTPrdHierGroup(final SearchFilter<TPrdHierGroup> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierByTPrdHierGroup", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHiersByTPrdHierGroup(final SearchFilter<TPrdHierGroup> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdHiersByTPrdHierGroup", queryParam);
	}

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHier> getTPrdHiersByTPrd(final SearchFilter<TPrd> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierByTPrd", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHiersByTPrd(final SearchFilter<TPrd> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		return genericDAO.findEntitiesByNamedQuery("CountTPrdHiersByTPrd", queryParam);
	}

	/**
	 * Retrieve TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHier> list of TPrdHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHier> getTPrdHiersByTPrdHier(final SearchFilter<TPrdHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierByTPrdHier", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdHier based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHiersByTPrdHier(final SearchFilter<TPrdHier> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());

		return genericDAO.findEntitiesByNamedQuery("CountTPrdHiersByTPrdHier", queryParam);
	}

}
