package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.core.entity.TSalesTeam;
import com.cognizant.opserv.sp.core.entity.TSalesTeamId;
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
 * Class provides API implementation for TSalesTeamDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesTeamDAO")
public class TSalesTeamDAOImpl implements TSalesTeamDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesTeamDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSUNIT = "tBussUnit";

	private final Class<TSalesTeam> clazz;

	public Class<TSalesTeam> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesTeamDAOImpl() {
		super();
		this.clazz = TSalesTeam.class;
	}

	private static final String JPAQL = "select tSalesTeamentity from TSalesTeam tSalesTeamentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesTeam tSalesTeamentity";

	private static final String[] RESTRICTIONS = {
			"tSalesTeamentity.tSalesTeamId.bussUnitId = #{tSalesTeamentity.tSalesTeamId.getBussUnitId}",
			"tSalesTeamentity.tSalesTeamId.salesTeamId = #{tSalesTeamentity.tSalesTeamId.getSalesTeamId}",
			"tSalesTeamentity.salesTeamName like '#{tSalesTeamentity.getSalesTeamName}%'",
			"tSalesTeamentity.activeFlag = #{tSalesTeamentity.getActiveFlag}",
			"Date(tSalesTeamentity.effStartDt) = '#{tSalesTeamentity.getEffStartDt}'",
			"Date(tSalesTeamentity.effEndDt) = '#{tSalesTeamentity.getEffEndDt}'",
			"tSalesTeamentity.deleteFlag = #{tSalesTeamentity.getDeleteFlag}",
			"tSalesTeamentity.createdBy = #{tSalesTeamentity.getCreatedBy}",
			"Date(tSalesTeamentity.createDt) = '#{tSalesTeamentity.getCreateDt}'",
			"tSalesTeamentity.updatedBy = #{tSalesTeamentity.getUpdatedBy}",
			"Date(tSalesTeamentity.updateDt) = '#{tSalesTeamentity.getUpdateDt}'",
			"tSalesTeamentity.tenantId = #{tSalesTeamentity.getTenantId}",
			"tSalesTeamentity.extSalesTeamId like '#{tSalesTeamentity.getExtSalesTeamId}%'",
			"tSalesTeamentity.tBussUnit.bussUnitId = #{tSalesTeamentity.tBussUnit.getBussUnitId}" };

	/**
	 * Stores a new TSalesTeam entity object in to the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be persisted
	 * @return tSalesTeam Persisted TSalesTeam object
	 */
	public TSalesTeam createTSalesTeam(final TSalesTeam tSalesTeam) {
		LOGGER.info("=========== Create TSalesTeam ===========");
		return genericDAO.store(tSalesTeam);
	}

	/**
	 * Deletes a TSalesTeam entity object from the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be deleted
	 */
	public void deleteTSalesTeam(final TSalesTeamId tSalesTeamId) {
		LOGGER.info("=========== Delete TSalesTeam ===========");
		final TSalesTeam tSalesTeam = genericDAO.get(clazz, tSalesTeamId);
		genericDAO.remove(tSalesTeam);
	}

	/**
	 * Updates a TSalesTeam entity object in to the persistent store
	 * 
	 * @param tSalesTeam
	 *            TSalesTeam Entity object to be updated
	 * @return tSalesTeam Persisted TSalesTeam object
	 */
	public TSalesTeam updateTSalesTeam(final TSalesTeam tSalesTeam) {
		LOGGER.info("=========== Update TSalesTeam ===========");
		return genericDAO.update(tSalesTeam);
	}

	/**
	 * Retrieve an TSalesTeam object based on given TSalesTeam TSalesTeamId.
	 * 
	 * @param tSalesTeamId
	 *            the primary key value of the TSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesTeam findTSalesTeamById(final TSalesTeamId tSalesTeamId) {
		LOGGER.info("find TSalesTeam instance with TSalesTeamId: " + tSalesTeamId);
		return genericDAO.get(clazz, tSalesTeamId);
	}

	/**
	 * Retrieve TSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesTeam> list of TSalesTeam if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesTeam> findTSalesTeams(final SearchFilter<TSalesTeam> searchFilter) {
		LOGGER.info("=========== Find TSalesTeams ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesTeam tSalesTeam = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesTeamentity", tSalesTeam);

		if (tSalesTeam.getTBussUnit() == null) {
			jpaQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+ tSalesTeam.getTBussUnit());

			jpaQuery.bind(TBUSSUNIT, tSalesTeam.getTBussUnit());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesTeams.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesTeams(final SearchFilter<TSalesTeam> searchFilter) {
		LOGGER.info("=========== Count TSalesTeam ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesTeam tSalesTeam = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesTeamentity", tSalesTeam);

		if (tSalesTeam.getTBussUnit() == null) {
			jpaCountQuery.bind(TBUSSUNIT, new TBussUnit());
		} else {
			LOGGER.info("tBussUnit {}"+ tSalesTeam.getTBussUnit());

			jpaCountQuery.bind(TBUSSUNIT, tSalesTeam.getTBussUnit());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesTeam> list of TSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesTeam> getTSalesTeamsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesTeamByTBussUnit", queryParam, index, maxresult);
	}

	/**
	 * Count TSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TBussUnit type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesTeamsByTBussUnit(final SearchFilter<TBussUnit> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesTeamsByTBussUnit", queryParam);
	}

	public Object maxTSalesTeamsByTBussUnit(final SearchFilter<TSalesTeamId> searchFilter) {

		final TSalesTeamId tSalesTeamId = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tSalesTeamId.getBussUnitId());
		return genericDAO.findEntitiesByNamedQuery("MaxTSalesTeamsByTBussUnit", queryParam);
	}
	
	public List<TSalesTeam> getTSalesTeamsByTBussUnitIds(List<Long> bussUnitIds , Short tenantId){
		
		List<TSalesTeam> tsalesTeams = new ArrayList<TSalesTeam>();
		
		for(Long bussUnitId:bussUnitIds ){
			
			List<Object> params = new ArrayList<Object>();
			params.add(bussUnitId);
			params.add(tenantId);
			List<TSalesTeam> tsalesTeamsNews = genericDAO.findEntitiesByNamedQueryMultiCond("FindSalesTeamsByTBussUnitId", params, 0, -1);
			
			for(TSalesTeam tSalesTeam :tsalesTeamsNews){
				
				tsalesTeams.add(tSalesTeam);
			}
			
		}
		
		return tsalesTeams;
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TSalesTeamDAO#findTSalesTeamBySearchCriteria(com.cognizant.peg.core.common.ISearchCriteria)
	 */
	@Override
	public List<TSalesTeam> findTSalesTeamBySearchCriteria(ISearchCriteria criteria) {
				return genericDAO.search(clazz, criteria);
	}

}
