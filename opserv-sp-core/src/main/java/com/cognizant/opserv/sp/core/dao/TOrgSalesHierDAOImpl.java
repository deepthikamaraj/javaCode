package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.core.entity.TOrgSalesHier;
import com.cognizant.opserv.sp.core.entity.TSalesHierList;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TOrgSalesHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tOrgSalesHierDAO")
public class TOrgSalesHierDAOImpl implements TOrgSalesHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TOrgSalesHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TORG = "tOrg";
	private static final String TORGSALESHIER = "tOrgSalesHier";
	private static final String TSALESHIERLIST = "tSalesHierList";

	private final Class<TOrgSalesHier> clazz;

	public Class<TOrgSalesHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TOrgSalesHierDAOImpl() {
		super();
		this.clazz = TOrgSalesHier.class;
	}

	private static final String JPAQL = "select tOrgSalesHierentity from TOrgSalesHier tOrgSalesHierentity";

	private static final String JPACOUNTQL = "select count(tOrgSalesHierentity) from TOrgSalesHier tOrgSalesHierentity";

	private static final String[] RESTRICTIONS = {
			"tOrgSalesHierentity.orgSalesHierId = #{tOrgSalesHierentity.getOrgSalesHierId}",
			"tOrgSalesHierentity.hierName like '#{tOrgSalesHierentity.getHierName}%'",
			"tOrgSalesHierentity.hierDesc like '#{tOrgSalesHierentity.getHierDesc}%'",
			"tOrgSalesHierentity.createdBy = #{tOrgSalesHierentity.getCreatedBy}",
			"Date(tOrgSalesHierentity.createDt) = '#{tOrgSalesHierentity.getCreateDt}'",
			"tOrgSalesHierentity.updatedBy = #{tOrgSalesHierentity.getUpdatedBy}",
			"Date(tOrgSalesHierentity.updateDt) = '#{tOrgSalesHierentity.getUpdateDt}'",
			"tOrgSalesHierentity.tenantId = #{tOrgSalesHierentity.getTenantId}",
			"tOrgSalesHierentity.activeFlag = #{tOrgSalesHierentity.getActiveFlag}",
			"Date(tOrgSalesHierentity.effStartDt) = '#{tOrgSalesHierentity.getEffStartDt}'",
			"Date(tOrgSalesHierentity.effEndDt) = '#{tOrgSalesHierentity.getEffEndDt}'",
			"tOrgSalesHierentity.tOrg.orgId = #{tOrgSalesHierentity.tOrg.getOrgId}",
			"tOrgSalesHierentity.tOrgSalesHier.orgSalesHierId = #{tOrgSalesHierentity.tOrgSalesHier.getOrgSalesHierId}",
			"tOrgSalesHierentity.tSalesHierList.salesHierId = #{tOrgSalesHierentity.tSalesHierList.getSalesHierId}" };

	/**
	 * Stores a new TOrgSalesHier entity object in to the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be persisted
	 * @return tOrgSalesHier Persisted TOrgSalesHier object
	 */
	public TOrgSalesHier createTOrgSalesHier(final TOrgSalesHier tOrgSalesHier) {
		LOGGER.info("=========== Create TOrgSalesHier ===========");
		return genericDAO.store(tOrgSalesHier);
	}

	/**
	 * Deletes a TOrgSalesHier entity object from the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be deleted
	 */
	public void deleteTOrgSalesHier(final Long orgSalesHierId) {
		LOGGER.info("=========== Delete TOrgSalesHier ===========");
		final TOrgSalesHier tOrgSalesHier = genericDAO.get(clazz, orgSalesHierId);
		genericDAO.remove(tOrgSalesHier);
	}

	/**
	 * Updates a TOrgSalesHier entity object in to the persistent store
	 * 
	 * @param tOrgSalesHier
	 *            TOrgSalesHier Entity object to be updated
	 * @return tOrgSalesHier Persisted TOrgSalesHier object
	 */
	public TOrgSalesHier updateTOrgSalesHier(final TOrgSalesHier tOrgSalesHier) {
		LOGGER.info("=========== Update TOrgSalesHier ===========");
		return genericDAO.update(tOrgSalesHier);
	}

	/**
	 * Retrieve an TOrgSalesHier object based on given TOrgSalesHier orgSalesHierId.
	 * 
	 * @param tOrgSalesHierId
	 *            the primary key value of the TOrgSalesHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TOrgSalesHier findTOrgSalesHierById(final Long tOrgSalesHierId) {
		LOGGER.info("find TOrgSalesHier instance with orgSalesHierId: " + tOrgSalesHierId);
		return genericDAO.get(clazz, tOrgSalesHierId);
	}

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TOrgSalesHier> findTOrgSalesHiers(final SearchFilter<TOrgSalesHier> searchFilter) {
		LOGGER.info("=========== Find TOrgSalesHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TOrgSalesHier tOrgSalesHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tOrgSalesHierentity", tOrgSalesHier);

		if (tOrgSalesHier.getTOrg() == null) {
			jpaQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tOrgSalesHier.getTOrg());

			jpaQuery.bind(TORG, tOrgSalesHier.getTOrg());
		}

		if (tOrgSalesHier.getTOrgSalesHier() == null) {
			jpaQuery.bind(TORGSALESHIER, new TOrgSalesHier());
		} else {
			LOGGER.info("tOrgSalesHier {}"+ tOrgSalesHier.getTOrgSalesHier());

			jpaQuery.bind(TORGSALESHIER, tOrgSalesHier.getTOrgSalesHier());
		}

		if (tOrgSalesHier.getTSalesHierList() == null) {
			jpaQuery.bind(TSALESHIERLIST, new TSalesHierList());
		} else {
			LOGGER.info("tSalesHierList {}"+ tOrgSalesHier.getTSalesHierList());

			jpaQuery.bind(TSALESHIERLIST, tOrgSalesHier.getTSalesHierList());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TOrgSalesHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTOrgSalesHiers(final SearchFilter<TOrgSalesHier> searchFilter) {
		LOGGER.info("=========== Count TOrgSalesHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TOrgSalesHier tOrgSalesHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tOrgSalesHierentity", tOrgSalesHier);

		if (tOrgSalesHier.getTOrg() == null) {
			jpaCountQuery.bind(TORG, new TOrg());
		} else {
			LOGGER.info("tOrg {}"+ tOrgSalesHier.getTOrg());

			jpaCountQuery.bind(TORG, tOrgSalesHier.getTOrg());
		}

		if (tOrgSalesHier.getTOrgSalesHier() == null) {
			jpaCountQuery.bind(TORGSALESHIER, new TOrgSalesHier());
		} else {
			LOGGER.info("tOrgSalesHier {}"+ tOrgSalesHier.getTOrgSalesHier());

			jpaCountQuery.bind(TORGSALESHIER, tOrgSalesHier.getTOrgSalesHier());
		}

		if (tOrgSalesHier.getTSalesHierList() == null) {
			jpaCountQuery.bind(TSALESHIERLIST, new TSalesHierList());
		} else {
			LOGGER.info("tSalesHierList {}"+ tOrgSalesHier.getTSalesHierList());

			jpaCountQuery.bind(TSALESHIERLIST, tOrgSalesHier.getTSalesHierList());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgSalesHier> getTOrgSalesHiersByTOrg(final SearchFilter<TOrg> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgSalesHierByTOrg", tOrgList, index, maxresult);
	}

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrg type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgSalesHiersByTOrg(final SearchFilter<TOrg> searchFilter) {

		final TOrg tOrg = searchFilter.getEntityClass();
		List<Object> tOrgList = new ArrayList<Object>();
		tOrgList.add(tOrg);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgSalesHiersByTOrg", tOrgList);
	}

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgSalesHier> getTOrgSalesHiersByTOrgSalesHier(final SearchFilter<TOrgSalesHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TOrgSalesHier tOrgSalesHier = searchFilter.getEntityClass();
		List<Object> tOrgSalesHierList = new ArrayList<Object>();
		tOrgSalesHierList.add(tOrgSalesHier);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgSalesHierByTOrgSalesHier", tOrgSalesHierList, index, maxresult);
	}

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgSalesHiersByTOrgSalesHier(final SearchFilter<TOrgSalesHier> searchFilter) {

		final TOrgSalesHier tOrgSalesHier = searchFilter.getEntityClass();
		List<Object> tOrgSalesHierList = new ArrayList<Object>();
		tOrgSalesHierList.add(tOrgSalesHier);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgSalesHiersByTOrgSalesHier", tOrgSalesHierList);
	}

	/**
	 * Retrieve TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TOrgSalesHier> list of TOrgSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TOrgSalesHier> getTOrgSalesHiersByTSalesHierList(final SearchFilter<TSalesHierList> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesHierList tSalesHierList = searchFilter.getEntityClass();
		List<Object> tSalesHierLists = new ArrayList<Object>();
		tSalesHierLists.add(tSalesHierList);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTOrgSalesHierByTSalesHierList", tSalesHierLists, index,
				maxresult);
	}

	/**
	 * Count TOrgSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesHierList type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTOrgSalesHiersByTSalesHierList(final SearchFilter<TSalesHierList> searchFilter) {

		final TSalesHierList tSalesHierList = searchFilter.getEntityClass();
		List<Object> tSalesHierLists = new ArrayList<Object>();
		tSalesHierLists.add(tSalesHierList);
		return genericDAO.findEntitiesByNamedQuery("CountTOrgSalesHiersByTSalesHierList", tSalesHierLists);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TOrgSalesHier> getActiveOrgSalesHierByTenant(
			SearchFilter<TOrgSalesHier> searchFilter) {		
		final PaginationInfo paginationInfo=searchFilter.getPaginationInfo();
		final  TOrgSalesHier tOrgSalesHier=searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		List paramList = new ArrayList();
		paramList.add(tOrgSalesHier.getTenantId());
		paramList.add(tOrgSalesHier.getEffStartDt());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTOrgSalesHiers", paramList, index, maxresult);
	}

	@Override
	public List<TOrgSalesHier> fetchSequenceNumber(Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("fetchSeqNumber", queryParam, -1, 1);	
	}

	@Override
	public List<TOrgSalesHier> findTorgSalesHier(Long orgSalesHierId, Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(orgSalesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTorgSalesHierByPrnOrg", queryParam, -1, -1);	
	}

}
