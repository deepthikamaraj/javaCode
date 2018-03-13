package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptConfig;
import com.cognizant.opserv.sp.core.entity.TRptFreq;
import com.cognizant.opserv.sp.core.entity.TRptSched;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptSchedDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptSchedDAO")
public class TRptSchedDAOImpl implements TRptSchedDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptSchedDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TRPTFREQ = "tRptFreq";
	private static final String TRPTCONFIG = "tRptConfig";

	private final Class<TRptSched> clazz;

	private Character activeFlag = 'Y';

	public Class<TRptSched> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptSchedDAOImpl() {
		super();
		this.clazz = TRptSched.class;
	}

	private static final String JPAQL = "select tRptSchedentity from TRptSched tRptSchedentity";

	private static final String JPACOUNTQL = "select count(*) from TRptSched tRptSchedentity";

	private static final String[] RESTRICTIONS = {
			"tRptSchedentity.tRptSchedId.rptConfigId = #{tRptSchedentity.tRptSchedId.getRptConfigId}",
			"tRptSchedentity.tRptSchedId.rptSchedId = #{tRptSchedentity.tRptSchedId.getRptSchedId}",
			"tRptSchedentity.freqInterval = #{tRptSchedentity.getFreqInterval}",
			"Date(tRptSchedentity.startDt) = '#{tRptSchedentity.getStartDt}'",
			"Date(tRptSchedentity.endDt) = '#{tRptSchedentity.getEndDt}'",
			"Date(tRptSchedentity.startTm) = '#{tRptSchedentity.getStartTm}'",
			"Date(tRptSchedentity.endTm) = '#{tRptSchedentity.getEndTm}'",
			"tRptSchedentity.activeFlag = #{tRptSchedentity.getActiveFlag}",
			"tRptSchedentity.schedName like '#{tRptSchedentity.getSchedName}%'",
			"tRptSchedentity.schedDesc like '#{tRptSchedentity.getSchedDesc}%'",
			"tRptSchedentity.createdBy = #{tRptSchedentity.getCreatedBy}",
			"Date(tRptSchedentity.createDt) = '#{tRptSchedentity.getCreateDt}'",
			"tRptSchedentity.updatedBy = #{tRptSchedentity.getUpdatedBy}",
			"Date(tRptSchedentity.updateDate) = '#{tRptSchedentity.getUpdateDate}'",
			"tRptSchedentity.tenantId = #{tRptSchedentity.getTenantId}",
			"tRptSchedentity.freqRelativeInterval = #{tRptSchedentity.getFreqRelativeInterval}",
			"tRptSchedentity.freqSubInterval = #{tRptSchedentity.getFreqSubInterval}",
			"tRptSchedentity.freqSubIntervalType = #{tRptSchedentity.getFreqSubIntervalType}",
			"tRptSchedentity.recurFactor = #{tRptSchedentity.getRecurFactor}",
			"tRptSchedentity.tRptFreq.rptFreqId = #{tRptSchedentity.tRptFreq.getRptFreqId}",
			"tRptSchedentity.tRptConfig.rptConfigId = #{tRptSchedentity.tRptConfig.getRptConfigId}" };

	/**
	 * Stores a new TRptSched entity object in to the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be persisted
	 * @return tRptSched Persisted TRptSched object
	 */
	public TRptSched createTRptSched(final TRptSched tRptSched) {
		LOGGER.info("=========== Create TRptSched ===========");
		return genericDAO.store(tRptSched);
	}

	/**
	 * Deletes a TRptSched entity object from the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be deleted
	 */
	public void deleteTRptSched(final Integer tRptSchedId) {
		LOGGER.info("=========== Delete TRptSched ===========");
		final TRptSched tRptSched = genericDAO.get(clazz, tRptSchedId);
		genericDAO.remove(tRptSched);
	}

	/**
	 * Updates a TRptSched entity object in to the persistent store
	 * 
	 * @param tRptSched
	 *            TRptSched Entity object to be updated
	 * @return tRptSched Persisted TRptSched object
	 */
	public TRptSched updateTRptSched(final TRptSched tRptSched) {
		LOGGER.info("=========== Update TRptSched ===========");
		return genericDAO.update(tRptSched);
	}

	/**
	 * Retrieve an TRptSched object based on given TRptSched TRptSchedId.
	 * 
	 * @param tRptSchedId
	 *            the primary key value of the TRptSched Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptSched findTRptSchedById(final Integer tRptSchedId) {
		LOGGER.info("find TRptSched instance with TRptSchedId: " + tRptSchedId);
		return genericDAO.get(clazz, tRptSchedId);
	}

	/**
	 * Retrieve TRptSched based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptSched if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptSched> findTRptScheds(final SearchFilter<TRptSched> searchFilter) {
		LOGGER.info("=========== Find TRptScheds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptSched tRptSched = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptSchedentity", tRptSched);

		/*if (tRptSched.getTRptFreq() == null) {
			jpaQuery.bind(TRPTFREQ, new TRptFreq());
		} else {
			LOGGER.info("tRptFreq {}", tRptSched.getTRptFreq());

			jpaQuery.bind(TRPTFREQ, tRptSched.getTRptFreq());
		}*/

		if (tRptSched.getTRptConfig() == null) {
			jpaQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptSched.getTRptConfig());

			jpaQuery.bind(TRPTCONFIG, tRptSched.getTRptConfig());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptScheds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptScheds(final SearchFilter<TRptSched> searchFilter) {
		LOGGER.info("=========== Count TRptSched ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptSched tRptSched = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptSchedentity", tRptSched);

	/*	if (tRptSched.getTRptFreq() == null) {
			jpaCountQuery.bind(TRPTFREQ, new TRptFreq());
		} else {
			LOGGER.info("tRptFreq {}", tRptSched.getTRptFreq());

			jpaCountQuery.bind(TRPTFREQ, tRptSched.getTRptFreq());
		}
*/
		if (tRptSched.getTRptConfig() == null) {
			jpaCountQuery.bind(TRPTCONFIG, new TRptConfig());
		} else {
			LOGGER.info("tRptConfig {}"+ tRptSched.getTRptConfig());

			jpaCountQuery.bind(TRPTCONFIG, tRptSched.getTRptConfig());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptFreq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptSched> getTRptSchedsByTRptFreq(final SearchFilter<TRptFreq> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TRptFreq tRptFreq = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tRptFreq.gettRptFreqId().getRptFreqId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptSchedByTRptFreq",queryParam , index, maxresult);
	}

	/**
	 * Count TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptFreq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptSchedsByTRptFreq(final SearchFilter<TRptFreq> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		final TRptFreq tRptFreq = searchFilter.getEntityClass();
		queryParam.add(tRptFreq.gettRptFreqId().getRptFreqId());
		return genericDAO.findEntitiesByNamedQuery("CountTRptSchedsByTRptFreq", queryParam);
	}

	/**
	 * Retrieve TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptSched> list of TRptScheds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptSched> getTRptSchedsByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptSchedByTRptConfig", queryParam, index, maxresult);
	}

	/**
	 * Count TRptSched based on given search criteria using JPA named Query.
	 * The search criteria is of TRptConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptSchedsByTRptConfig(final SearchFilter<TRptConfig> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptSchedsByTRptConfig", queryParam);
	}

	@Override
	public List<TRptSched> loadAllTRptScheds(Short tenantID) {
		SearchFilter<TRptSched> searchFilter = new SearchFilter<TRptSched>();
		OperatorInfo operatorInfo = new OperatorInfo();
		PaginationInfo paginationInfo = new PaginationInfo(-1, -1);
		searchFilter.setOperatorInfo(operatorInfo);
		searchFilter.setPaginationInfo(paginationInfo);
		TRptSched tRptSched = new TRptSched();
		tRptSched.setActiveFlag(activeFlag);
		tRptSched.setTenantId(tenantID);
		searchFilter.setEntityClass(tRptSched);
		List<TRptSched> tRptSchedList = findTRptScheds(searchFilter);
		return tRptSchedList;		
	}
	
}
