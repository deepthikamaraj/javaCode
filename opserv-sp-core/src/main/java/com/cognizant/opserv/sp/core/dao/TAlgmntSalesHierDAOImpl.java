package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
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
 * Class provides API implementation for TAlgmntSalesHierDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntSalesHierDAO")
public class TAlgmntSalesHierDAOImpl implements TAlgmntSalesHierDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntSalesHierDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";
	private static final String TORGSALESHIER = "tOrgSalesHier";

	private final Class<TAlgmntSalesHier> clazz;

	public Class<TAlgmntSalesHier> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntSalesHierDAOImpl() {
		super();
		this.clazz = TAlgmntSalesHier.class;
	}

	private static final String JPAQL = "select tAlgmntSalesHierentity from TAlgmntSalesHier tAlgmntSalesHierentity";

	private static final String JPACOUNTQL = "select count(tAlgmntSalesHierentity) from TAlgmntSalesHier tAlgmntSalesHierentity";

	private static final String[] RESTRICTIONS = {
			"tAlgmntSalesHierentity.salesHierId = #{tAlgmntSalesHierentity.getSalesHierId}",
			"tAlgmntSalesHierentity.activeFlag = #{tAlgmntSalesHierentity.getActiveFlag}",
			"Date(tAlgmntSalesHierentity.effStartDt) = '#{tAlgmntSalesHierentity.getEffStartDt}'",
			"Date(tAlgmntSalesHierentity.effEndDt) = '#{tAlgmntSalesHierentity.getEffEndDt}'",
			"tAlgmntSalesHierentity.hierName like '#{tAlgmntSalesHierentity.getHierName}%'",
			"tAlgmntSalesHierentity.createdBy = #{tAlgmntSalesHierentity.getCreatedBy}",
			"Date(tAlgmntSalesHierentity.createDt) = '#{tAlgmntSalesHierentity.getCreateDt}'",
			"tAlgmntSalesHierentity.updatedBy = #{tAlgmntSalesHierentity.getUpdatedBy}",
			"Date(tAlgmntSalesHierentity.updateDt) = '#{tAlgmntSalesHierentity.getUpdateDt}'",
			"tAlgmntSalesHierentity.tenantId = #{tAlgmntSalesHierentity.getTenantId}",
			"tAlgmntSalesHierentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tAlgmntSalesHierentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}",
			"tAlgmntSalesHierentity.tOrgSalesHier.orgSalesHierId = #{tAlgmntSalesHierentity.tOrgSalesHier.getOrgSalesHierId}" };

	/**
	 * Stores a new TAlgmntSalesHier entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be persisted
	 * @return tAlgmntSalesHier Persisted TAlgmntSalesHier object
	 */
	public TAlgmntSalesHier createTAlgmntSalesHier(final TAlgmntSalesHier tAlgmntSalesHier) {
		LOGGER.info("=========== Create TAlgmntSalesHier ===========");
		return genericDAO.store(tAlgmntSalesHier);
	}

	/**
	 * Deletes a TAlgmntSalesHier entity object from the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be deleted
	 */
	public void deleteTAlgmntSalesHier(final Long salesHierId) {
		LOGGER.info("=========== Delete TAlgmntSalesHier ===========");
		final TAlgmntSalesHier tAlgmntSalesHier = genericDAO.get(clazz, salesHierId);
		genericDAO.remove(tAlgmntSalesHier);
	}

	/**
	 * Updates a TAlgmntSalesHier entity object in to the persistent store
	 * 
	 * @param tAlgmntSalesHier
	 *            TAlgmntSalesHier Entity object to be updated
	 * @return tAlgmntSalesHier Persisted TAlgmntSalesHier object
	 */
	public TAlgmntSalesHier updateTAlgmntSalesHier(final TAlgmntSalesHier tAlgmntSalesHier) {
		LOGGER.info("=========== Update TAlgmntSalesHier ===========");
		return genericDAO.update(tAlgmntSalesHier);
	}

	/**
	 * Retrieve an TAlgmntSalesHier object based on given TAlgmntSalesHier salesHierId.
	 * 
	 * @param tAlgmntSalesHierId
	 *            the primary key value of the TAlgmntSalesHier Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntSalesHier findTAlgmntSalesHierById(final Long tAlgmntSalesHierId) {
		LOGGER.info("find TAlgmntSalesHier instance with salesHierId: " + tAlgmntSalesHierId);
		return genericDAO.get(clazz, tAlgmntSalesHierId);
	}

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHier if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntSalesHier> findTAlgmntSalesHiers(final SearchFilter<TAlgmntSalesHier> searchFilter) {
		LOGGER.info("=========== Find TAlgmntSalesHiers ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntSalesHier tAlgmntSalesHier = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntSalesHierentity", tAlgmntSalesHier);

		if (tAlgmntSalesHier.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntSalesHier.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tAlgmntSalesHier.getTAlgmntSalesTeam());
		}

		if (tAlgmntSalesHier.getTOrgSalesHier() == null) {
			jpaQuery.bind(TORGSALESHIER, new TOrgSalesHier());
		} else {
			LOGGER.info("tOrgSalesHier {}" + tAlgmntSalesHier.getTOrgSalesHier());

			jpaQuery.bind(TORGSALESHIER, tAlgmntSalesHier.getTOrgSalesHier());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntSalesHiers.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntSalesHiers(final SearchFilter<TAlgmntSalesHier> searchFilter) {
		LOGGER.info("=========== Count TAlgmntSalesHier ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntSalesHier tAlgmntSalesHier = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntSalesHierentity", tAlgmntSalesHier);

		if (tAlgmntSalesHier.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tAlgmntSalesHier.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tAlgmntSalesHier.getTAlgmntSalesTeam());
		}

		if (tAlgmntSalesHier.getTOrgSalesHier() == null) {
			jpaCountQuery.bind(TORGSALESHIER, new TOrgSalesHier());
		} else {
			LOGGER.info("tOrgSalesHier {}" + tAlgmntSalesHier.getTOrgSalesHier());

			jpaCountQuery.bind(TORGSALESHIER, tAlgmntSalesHier.getTOrgSalesHier());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	@SuppressWarnings("unchecked")
	public List<TAlgmntSalesHier> getTAlgmntSalesHiersByTAlgmntSalesTeam(
			final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}

	/**
	 * Count TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesHiersByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesHiersByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}

	/**
	 * Retrieve TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntSalesHier> list of TAlgmntSalesHiers if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAlgmntSalesHier> getTAlgmntSalesHiersByTOrgSalesHier(final SearchFilter<TOrgSalesHier> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tOrgSalesHierList = new ArrayList<Object>();
		tOrgSalesHierList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByTOrgSalesHier", tOrgSalesHierList, index,
				maxresult);
	}

	/**
	 * Count TAlgmntSalesHier based on given search criteria using JPA named Query.
	 * The search criteria is of TOrgSalesHier type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAlgmntSalesHiersByTOrgSalesHier(final SearchFilter<TOrgSalesHier> searchFilter) {

		List<Object> tOrgSalesHierList = new ArrayList<Object>();
		tOrgSalesHierList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAlgmntSalesHiersByTOrgSalesHier", tOrgSalesHierList);
	}
	/**
	 * Find t alg sal hier by id.
	 *
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param alignID the align id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTAlgSalHierById(Long businessUnitId,Long salesTeamId,Long AlgID,Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(AlgID);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHiersByTBussUnitAndTeam", queryParam, 0, -1);
	}

	/**
	 * Find t prn sal hier by id.
	 *
	 * @param businessUnitId the business unit id
	 * @param salesTeamId the sales team id
	 * @param AlgID the alg id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTPrnSalHierById(Long businessUnitId,Long salesTeamId,Long AlgID,Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(businessUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(AlgID);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindPrnSalesHierByTBussUnitAndTeam", queryParam, 0, -1);
	}
	/**
	 * Find t algmnt sales hier by t algmnt id.
	 *
	 * @param alignmentId the alignment id
	 * @param buId the bu id
	 * @param salesId the sales id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTAlgmntSalesHierByTAlgmntID(Long alignmentId,Long buId,Long salesId,Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(alignmentId);
		list.add(buId);
		list.add(salesId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByTAlgmntID", list, 0, -1);
	}
	/**
	 * Creates the t sales team hier.
	 *
	 * @param tSalesHierList the t sales hier list
	 * @return the t sales hier list
	 */
	@Override
	public TSalesHierList createTSalesTeamHier(TSalesHierList tSalesHierList) {
		LOGGER.info("=========== Create TAlgmntSalesHier ===========");
	
		return genericDAO.store(tSalesHierList);
	}
	/**
	 * Find hier names by i ds.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findHierNamesByIDs(Long algmntId, Long bussUnitId,
			Long salesTeamId,Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindHierNamesByIDs", paramList,0,-1);
	}
	/**
	 * Find hier name.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findHierName(Long algmntId, Long bussUnitId,
			Long salesTeamId, Long salesHierId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesHierId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindHierName", paramList,0,-1);
	}
	/**
	 * Find t alg sales hier.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param long1 the long1
	 * @param salesHierListID the sales hier list id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTAlgSalesHier(Long algmntId, Long bussUnitId,
			Long salesTeamsId, Long salesHierListID, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamsId);
		paramList.add(salesHierListID);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findTAlgSalesHier", paramList,0,-1);
	}
	/**
	 * Fetch t algmnt hier by buss id.
	 *
	 * @param tenantID the tenant id
	 * @param currentDate the current date
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> fetchTAlgmntHierByBussID(Short tenantID,
			Date currentDate) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantID);
		paramList.add(currentDate);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"fetchTAlgmntHierByBussID", paramList,0,-1);
	}
	/**
	 * Find t algmnt sales hier by prn.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTAlgmntSalesHierByPrn(Long salesHierId,
			Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByPrnSalesHier", queryParam, -1, -1);	
	}
	/**
	 * Find t algmnt sales hier by prn.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param alignmentId the alignment id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findTAlgmntSalesHierByPrn(Long salesHierId,
			Short tenantId, Long alignmentId) {
		List queryParam = new ArrayList();
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		queryParam.add(alignmentId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByPrnandAlgmntIdSalesHier", queryParam, -1, -1);	
	}
	/**
	 * Find hier names by albust.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param activeFlag the active flag
	 * @param effStrtDt the eff strt dt
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findHierNamesByALBUST(Integer algmntId, Integer bussUnitId,
			Integer salesTeamId, Character activeFlag, Date effStrtDt, Date effEndDt,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add((long)algmntId);
		paramList.add((long)bussUnitId);
		paramList.add((long)salesTeamId);
		paramList.add(activeFlag);
		paramList.add(effStrtDt);
		paramList.add(effEndDt);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindHierNamesByALBUST", paramList,0,-1);
	}
	
	/**
	 * Find hier names by albust.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param activeFlag the active flag
	 * @param effStrtDt the eff strt dt
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAlgmntSalesHier> findHierNamesByALBUST(Long algmntId, Long bussUnitId,
			Long salesTeamId, Character activeFlag, Date effStrtDt, Date effEndDt,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(activeFlag);
		paramList.add(effStrtDt);
		paramList.add(effEndDt);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindHierNamesByALBUST", paramList,0,-1);
	}
	/**
	 * Find t algmnt sales hier by prnand algmnt id sales hier count.
	 *
	 * @param salesHierId the sales hier id
	 * @param tenantId the tenant id
	 * @param alignmentId the alignment id
	 * @return the long
	 */
	@Override
	public Long findTAlgmntSalesHierByPrnandAlgmntIdSalesHierCount(Long salesHierId,	Short tenantId, Long alignmentId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		queryParam.add(alignmentId);
		List<Object> count =  genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntSalesHierByPrnandAlgmntIdSalesHierCount", queryParam, -1, -1);
		return  (Long) count.get(0);
	}
	/**
	 * Fetch act hier names by albust.
	 *
	 * @param effEndDt the eff end dt
	 * @param tenantId the tenant id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	@Override
	public List<Object[]> fetchActHierNamesByALBUST(Date effEndDt,Short tenantId, Character activeFlag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(effEndDt);
		paramList.add(tenantId);
		paramList.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchActHierNamesByALBUST", paramList,0,-1);
	}


}
