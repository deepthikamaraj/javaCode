package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustSalesTeam;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustSalesTeamDAO.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/04/2016
 */

@Repository("tCustSalesTeamDAO")
public class TCustSalesTeamDAOImpl implements TCustSalesTeamDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCustSalesTeamDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUST = "tCust";

	private final Class<TCustSalesTeam> clazz;

	public Class<TCustSalesTeam> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustSalesTeamDAOImpl() {
		super();
		this.clazz = TCustSalesTeam.class;
	}
	
	private static final String JPAQL = "select tCustSalesTeamentity from TCustSalesTeam tCustSalesTeamentity";

	private static final String JPACOUNTQL = "select count(tCustSalesTeamentity) from TCustSalesTeam tCustSalesTeamentity";

	private static final String[] RESTRICTIONS = {
			"tCustSalesTeamentity.custSalesTeamId = #{tCustSalesTeamentity.getCustSalesTeamId}",
			"tCustSalesTeamentity.activeFlag = #{tCustSalesTeamentity.getActiveFlag}",
			"Date(tCustSalesTeamentity.effStartDt) = '#{tCustSalesTeamentity.getEffStartDt}'",
			"Date(tCustSalesTeamentity.effEndDt) = '#{tCustSalesTeamentity.getEffEndDt}'",
			"tCustSalesTeamentity.algmntId = #{tCustSalesTeamentity.getAlgmntId}",
			"tCustSalesTeamentity.bussUnitId = #{tCustSalesTeamentity.getBussUnitId}",
			"tCustSalesTeamentity.salesTeamId = #{tCustSalesTeamentity.getSalesTeamId}",
			"tCustSalesTeamentity.attr1 like '#{tCustSalesTeamentity.getAttr1}%'",
			"tCustSalesTeamentity.attr2 like '#{tCustSalesTeamentity.getAttr2}%'",
			"tCustSalesTeamentity.attr3 like '#{tCustSalesTeamentity.getAttr3}%'",
			"tCustSalesTeamentity.attr4 like '#{tCustSalesTeamentity.getAttr4}%'",
			"tCustSalesTeamentity.attr5 like '#{tCustSalesTeamentity.getAttr5}%'",
			"tCustSalesTeamentity.attr6 like '#{tCustSalesTeamentity.getAttr6}%'",
			"tCustSalesTeamentity.attr7 like '#{tCustSalesTeamentity.getAttr7}%'",
			"tCustSalesTeamentity.attr8 like '#{tCustSalesTeamentity.getAttr8}%'",
			"tCustSalesTeamentity.attr9 like '#{tCustSalesTeamentity.getAttr9}%'",
			"tCustSalesTeamentity.attr10 like '#{tCustSalesTeamentity.getAttr10}%'",
			"tCustSalesTeamentity.attr11 like '#{tCustSalesTeamentity.getAttr11}%'",
			"tCustSalesTeamentity.attr12 like '#{tCustSalesTeamentity.getAttr12}%'",
			"tCustSalesTeamentity.attr13 like '#{tCustSalesTeamentity.getAttr13}%'",
			"tCustSalesTeamentity.attr14 like '#{tCustSalesTeamentity.getAttr14}%'",
			"tCustSalesTeamentity.attr15 like '#{tCustSalesTeamentity.getAttr15}%'",
			"tCustSalesTeamentity.attr16 like '#{tCustSalesTeamentity.getAttr16}%'",
			"tCustSalesTeamentity.attr17 like '#{tCustSalesTeamentity.getAttr17}%'",
			"tCustSalesTeamentity.attr18 like '#{tCustSalesTeamentity.getAttr18}%'",
			"tCustSalesTeamentity.attr19 like '#{tCustSalesTeamentity.getAttr19}%'",
			"tCustSalesTeamentity.attr20 like '#{tCustSalesTeamentity.getAttr20}%'",
			"tCustSalesTeamentity.createdBy = #{tCustSalesTeamentity.getCreatedBy}",
			"Date(tCustSalesTeamentity.createDt) = '#{tCustSalesTeamentity.getCreateDt}'",
			"tCustSalesTeamentity.updatedBy = #{tCustSalesTeamentity.getUpdatedBy}",
			"Date(tCustSalesTeamentity.updateDt) = '#{tCustSalesTeamentity.getUpdateDt}'",
			"tCustSalesTeamentity.tenantId = #{tCustSalesTeamentity.getTenantId}",
			"tCustSalesTeamentity.tCust.custId = #{tCustSalesTeamentity.tCust.getCustId}" };
	/**
	 * Find all t custs by AL,BU, ST and custIds.
	 *
	 * @param alId the al id
	 * @param buId the bu id
	 * @param stId the st id
	 * @param custId the cust id
	 * @return the list
	 */
	
	@Override
	public List<TCustSalesTeam> findTCustSalesTeamByCustId(Long alId, Long buId,
			Long stId, List<Integer> custId, short tenantId) {
		List paramList = new ArrayList();
		paramList.add(alId);
		paramList.add(buId);
		paramList.add(stId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTCustSalesTeamByCustId", paramList, 0, -1);
		
	}

	
	/**
	 * Updates a TCustSalesTeam entity object in to the persistent store
	 * 
	 * @param tCustSalesTeam
	 *            TCustSalesTeam Entity object to be updated
	 * @return tCustSalesTeam Persisted TCustSalesTeam object
	 */
	@Override
	public TCustSalesTeam updateTCustSalesTeam(final TCustSalesTeam tCustSalesTeam) {
		LOGGER.info("=========== Update TCustSalesTeam ===========");
		return genericDAO.update(tCustSalesTeam);
	}

	/**
	 * Retrieve an TCustSalesTeam object based on given TCustSalesTeam custSalesTeamId.
	 * 
	 * @param tCustSalesTeamId
	 *            the primary key value of the TCustSalesTeam Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public TCustSalesTeam findTCustSalesTeamById(final Long tCustSalesTeamId) {
		LOGGER.info("find TCustSalesTeam instance with custSalesTeamId: " + tCustSalesTeamId);
		return genericDAO.get(clazz, tCustSalesTeamId);
	}
	
	/**
	 * Retrieve TCustSalesTeam based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustSalesTeam> list of TCustSalesTeam if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustSalesTeam> findTCustSalesTeams(final SearchFilter<TCustSalesTeam> searchFilter) {
		LOGGER.info("=========== Find TCustSalesTeams ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustSalesTeam tCustSalesTeam = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustSalesTeamentity", tCustSalesTeam);

		if (tCustSalesTeam.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustSalesTeam.getTCust());

			jpaQuery.bind(TCUST, tCustSalesTeam.getTCust());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustSalesTeams.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustSalesTeams(final SearchFilter<TCustSalesTeam> searchFilter) {
		LOGGER.info("=========== Count TCustSalesTeam ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustSalesTeam tCustSalesTeam = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustSalesTeamentity", tCustSalesTeam);

		if (tCustSalesTeam.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}", tCustSalesTeam.getTCust());

			jpaCountQuery.bind(TCUST, tCustSalesTeam.getTCust());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustSalesTeam> list of TCustSalesTeams if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustSalesTeam> getTCustSalesTeamsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		int maxresult =0;
		 int index = -1;
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> argList = new ArrayList<Object>();
		argList.add(tCust);
		if(null!= paginationInfo){
			maxresult = paginationInfo.getMaxRows();
			index = paginationInfo.getStartIndex();
		}
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustSalesTeamByTCust", argList, index, maxresult);
	}

	/**
	 * Count TCustSalesTeam based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustSalesTeamsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> argList = new ArrayList<Object>();
		argList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustSalesTeamsByTCust", argList);
	}
}
