package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TMtrExecConfig;
import com.cognizant.opserv.sp.core.entity.TMtrExecId;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrExecDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrExecDAO")
public class TMtrExecDAOImpl implements TMtrExecDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrExecDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TEXECUTIONSTATUS = "tExecutionStatus";
	private static final String TMTREXECCONFIG = "tMtrExecConfig";
	private static final String TSALESPOS = "tSalesPos";

	private final Class<TMtrExec> clazz;

	public Class<TMtrExec> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrExecDAOImpl() {
		super();
		this.clazz = TMtrExec.class;
	}

	private static final String JPAQL = "select tMtrExecentity from TMtrExec tMtrExecentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrExec tMtrExecentity";

	private static final String[] RESTRICTIONS = {
			"tMtrExecentity.tMtrExecId.execId = #{tMtrExecentity.tMtrExecId.getExecId}",
			"tMtrExecentity.tMtrExecId.configId = #{tMtrExecentity.tMtrExecId.getConfigId}",
			"Date(tMtrExecentity.execDtTm) = '#{tMtrExecentity.getExecDtTm}'",
			"tMtrExecentity.algmntId = #{tMtrExecentity.getAlgmntId}",
			"tMtrExecentity.bussUnitId = #{tMtrExecentity.getBussUnitId}",
			"tMtrExecentity.salesTeamId = #{tMtrExecentity.getSalesTeamId}",
			"tMtrExecentity.mtrId = #{tMtrExecentity.getMtrId}",
			"tMtrExecentity.createdBy = #{tMtrExecentity.getCreatedBy}",
			"Date(tMtrExecentity.createDt) = '#{tMtrExecentity.getCreateDt}'",
			"tMtrExecentity.updatedBy = #{tMtrExecentity.getUpdatedBy}",
			"Date(tMtrExecentity.updateDt) = '#{tMtrExecentity.getUpdateDt}'",
			"tMtrExecentity.tenantId = #{tMtrExecentity.getTenantId}",
			"tMtrExecentity.tExecutionStatus.executionStatusId = #{tMtrExecentity.tExecutionStatus.getExecutionStatusId}",
			"tMtrExecentity.tMtrExecConfig.configId = #{tMtrExecentity.tMtrExecConfig.getConfigId}",
			"tMtrExecentity.tSalesPos.tSalesPosId = #{tMtrExecentity.tSalesPos.getTSalesPosId}" };

	/**
	 * Stores a new TMtrExec entity object in to the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be persisted
	 * @return tMtrExec Persisted TMtrExec object
	 */
	public TMtrExec createTMtrExec(final TMtrExec tMtrExec) {
		LOGGER.info("=========== Create TMtrExec ===========");
		return genericDAO.store(tMtrExec);
	}

	/**
	 * Deletes a TMtrExec entity object from the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be deleted
	 */
	public void deleteTMtrExec(final TMtrExecId tMtrExecId) {
		LOGGER.info("=========== Delete TMtrExec ===========");
		final TMtrExec tMtrExec = genericDAO.get(clazz, tMtrExecId);
		genericDAO.remove(tMtrExec);
	}

	/**
	 * Updates a TMtrExec entity object in to the persistent store
	 * 
	 * @param tMtrExec
	 *            TMtrExec Entity object to be updated
	 * @return tMtrExec Persisted TMtrExec object
	 */
	public TMtrExec updateTMtrExec(final TMtrExec tMtrExec) {
		LOGGER.info("=========== Update TMtrExec ===========");
		return genericDAO.update(tMtrExec);
	}

	/**
	 * Retrieve an TMtrExec object based on given TMtrExec TMtrExecId.
	 * 
	 * @param tMtrExecId
	 *            the primary key value of the TMtrExec Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrExec findTMtrExecById(final TMtrExecId tMtrExecId) {
		LOGGER.info("find TMtrExec instance with TMtrExecId: " + tMtrExecId);
		return genericDAO.get(clazz, tMtrExecId);
	}

	/**
	 * Retrieve TMtrExec based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExec if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrExec> findTMtrExecs(final SearchFilter<TMtrExec> searchFilter) {
		LOGGER.info("=========== Find TMtrExecs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrExec tMtrExec = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrExecentity", tMtrExec);

		if (tMtrExec.getTExecutionStatus() == null) {
			jpaQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}"+ tMtrExec.getTExecutionStatus());

			jpaQuery.bind(TEXECUTIONSTATUS, tMtrExec.getTExecutionStatus());
		}

		if (tMtrExec.getTMtrExecConfig() == null) {
			jpaQuery.bind(TMTREXECCONFIG, new TMtrExecConfig());
		} else {
			LOGGER.info("tMtrExecConfig {}"+ tMtrExec.getTMtrExecConfig());

			jpaQuery.bind(TMTREXECCONFIG, tMtrExec.getTMtrExecConfig());
		}

		if (tMtrExec.getTSalesPos() == null) {
			jpaQuery.bind(TSALESPOS, new TSalesPos());
		} else {
			LOGGER.info("tSalesPos {}" +tMtrExec.getTSalesPos());

			jpaQuery.bind(TSALESPOS, tMtrExec.getTSalesPos());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrExecs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrExecs(final SearchFilter<TMtrExec> searchFilter) {
		LOGGER.info("=========== Count TMtrExec ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrExec tMtrExec = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrExecentity", tMtrExec);

		if (tMtrExec.getTExecutionStatus() == null) {
			jpaCountQuery.bind(TEXECUTIONSTATUS, new TExecutionStatus());
		} else {
			LOGGER.info("tExecutionStatus {}"+ tMtrExec.getTExecutionStatus());

			jpaCountQuery.bind(TEXECUTIONSTATUS, tMtrExec.getTExecutionStatus());
		}

		if (tMtrExec.getTMtrExecConfig() == null) {
			jpaCountQuery.bind(TMTREXECCONFIG, new TMtrExecConfig());
		} else {
			LOGGER.info("tMtrExecConfig {}"+ tMtrExec.getTMtrExecConfig());

			jpaCountQuery.bind(TMTREXECCONFIG, tMtrExec.getTMtrExecConfig());
		}

		if (tMtrExec.getTSalesPos() == null) {
			jpaCountQuery.bind(TSALESPOS, new TSalesPos());
		} else {
			LOGGER.info("tSalesPos {}"+ tMtrExec.getTSalesPos());

			jpaCountQuery.bind(TSALESPOS, tMtrExec.getTSalesPos());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExec> getTMtrExecsByTExecutionStatus(final SearchFilter<TExecutionStatus> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		List<Object> tExecutionStatusList = new ArrayList<Object>();
		tExecutionStatusList.add(tExecutionStatus);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecByTExecutionStatus", tExecutionStatusList, index, maxresult);
	}

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TExecutionStatus type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecsByTExecutionStatus(final SearchFilter<TExecutionStatus> searchFilter) {

		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		List<Object> tExecutionStatusList = new ArrayList<Object>();
		tExecutionStatusList.add(tExecutionStatus);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecsByTExecutionStatus", tExecutionStatusList);
	}

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExecConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExec> getTMtrExecsByTMtrExecConfig(final SearchFilter<TMtrExecConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtrExecConfig tMtrExecConfig = searchFilter.getEntityClass();
		List<Object> tMtrExecConfigList = new ArrayList<Object>();
		tMtrExecConfigList.add(tMtrExecConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecByTMtrExecConfig", tMtrExecConfigList, index, maxresult);
	}

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExecConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecsByTMtrExecConfig(final SearchFilter<TMtrExecConfig> searchFilter) {

		final TMtrExecConfig tMtrExecConfig = searchFilter.getEntityClass();
		List<Object> tMtrExecConfigList = new ArrayList<Object>();
		tMtrExecConfigList.add(tMtrExecConfig);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecsByTMtrExecConfig", tMtrExecConfigList);
	}

	/**
	 * Retrieve TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrExec> list of TMtrExecs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrExec> getTMtrExecsByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> tSalesPosList = new ArrayList<Object>();
		tSalesPosList.add(tSalesPos);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrExecByTSalesPos", tSalesPosList, index, maxresult);
	}

	/**
	 * Count TMtrExec based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrExecsByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> tSalesPosList = new ArrayList<Object>();
		tSalesPosList.add(tSalesPos);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrExecsByTSalesPos", tSalesPosList);
	}
	
	
	@Override
	public List<Object> getMaxExecutionId(Short tenantId){
		List<Object> tenantIdList = new ArrayList<Object>();
		tenantIdList.add(tenantId);
		return genericDAO.findEntitiesByNamedQuery("getMaxid",tenantIdList);
	}


}
