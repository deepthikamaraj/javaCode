package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallDirConfig;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdWt;
import com.cognizant.opserv.sp.core.entity.TCallDirPrdWtId;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallDirPrdWtDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallDirPrdWtDAO")
public class TCallDirPrdWtDAOImpl implements TCallDirPrdWtDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallDirPrdWtDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCALLDIRCONFIG = "tCallDirConfig";
	//private static final String TPRDPRTTYPE = "tPrdPrtType";

	private final Class<TCallDirPrdWt> clazz;

	public Class<TCallDirPrdWt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallDirPrdWtDAOImpl() {
		super();
		this.clazz = TCallDirPrdWt.class;
	}

	private static final String JPAQL = "select tCallDirPrdWtentity from TCallDirPrdWt tCallDirPrdWtentity";

	private static final String JPACOUNTQL = "select count(*) from TCallDirPrdWt tCallDirPrdWtentity";

	private static final String[] RESTRICTIONS = {
			"tCallDirPrdWtentity.tCallDirPrdWtId.prdPrtTypeId = #{tCallDirPrdWtentity.tCallDirPrdWtId.getPrdPrtTypeId}",
			"tCallDirPrdWtentity.tCallDirPrdWtId.callDirConfigId = #{tCallDirPrdWtentity.tCallDirPrdWtId.getCallDirConfigId}",
			"tCallDirPrdWtentity.prdWtg = #{tCallDirPrdWtentity.getPrdWtg}",
			"tCallDirPrdWtentity.activeFlag = #{tCallDirPrdWtentity.getActiveFlag}",
			"tCallDirPrdWtentity.createdBy = #{tCallDirPrdWtentity.getCreatedBy}",
			"Date(tCallDirPrdWtentity.createDt) = '#{tCallDirPrdWtentity.getCreateDt}'",
			"tCallDirPrdWtentity.updatedBy = #{tCallDirPrdWtentity.getUpdatedBy}",
			"Date(tCallDirPrdWtentity.updateDt) = '#{tCallDirPrdWtentity.getUpdateDt}'",
			"tCallDirPrdWtentity.tenantId = #{tCallDirPrdWtentity.getTenantId}",
			"tCallDirPrdWtentity.tCallDirConfig.callDirConfigId = #{tCallDirPrdWtentity.tCallDirConfig.getCallDirConfigId}",
			"tCallDirPrdWtentity.tPrdPrtType.prdPrtTypeId = #{tCallDirPrdWtentity.tPrdPrtType.getPrdPrtTypeId}" };

	/**
	 * Stores a new TCallDirPrdWt entity object in to the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be persisted
	 * @return tCallDirPrdWt Persisted TCallDirPrdWt object
	 */
	public TCallDirPrdWt createTCallDirPrdWt(final TCallDirPrdWt tCallDirPrdWt) {
		LOGGER.info("=========== Create TCallDirPrdWt ===========");
		return genericDAO.store(tCallDirPrdWt);
	}

	/**
	 * Deletes a TCallDirPrdWt entity object from the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be deleted
	 */
	public void deleteTCallDirPrdWt(final TCallDirPrdWtId tCallDirPrdWtId) {
		LOGGER.info("=========== Delete TCallDirPrdWt ===========");
		final TCallDirPrdWt tCallDirPrdWt = genericDAO.get(clazz, tCallDirPrdWtId);
		genericDAO.remove(tCallDirPrdWt);
	}

	/**
	 * Updates a TCallDirPrdWt entity object in to the persistent store
	 * 
	 * @param tCallDirPrdWt
	 *            TCallDirPrdWt Entity object to be updated
	 * @return tCallDirPrdWt Persisted TCallDirPrdWt object
	 */
	public TCallDirPrdWt updateTCallDirPrdWt(final TCallDirPrdWt tCallDirPrdWt) {
		LOGGER.info("=========== Update TCallDirPrdWt ===========");
		return genericDAO.update(tCallDirPrdWt);
	}

	/**
	 * Retrieve an TCallDirPrdWt object based on given TCallDirPrdWt TCallDirPrdWtId.
	 * 
	 * @param tCallDirPrdWtId
	 *            the primary key value of the TCallDirPrdWt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallDirPrdWt findTCallDirPrdWtById(final TCallDirPrdWtId tCallDirPrdWtId) {
		LOGGER.info("find TCallDirPrdWt instance with TCallDirPrdWtId: " + tCallDirPrdWtId);
		return genericDAO.get(clazz, tCallDirPrdWtId);
	}

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallDirPrdWt> findTCallDirPrdWts(final SearchFilter<TCallDirPrdWt> searchFilter) {
		LOGGER.info("=========== Find TCallDirPrdWts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallDirPrdWt tCallDirPrdWt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallDirPrdWtentity", tCallDirPrdWt);

		if (tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig() == null) {
			jpaQuery.bind(TCALLDIRCONFIG, new TCallDirConfig());
		} else {
			LOGGER.info("tCallDirConfig {}" + tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig());

			jpaQuery.bind(TCALLDIRCONFIG, tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig());
		}

	/*	if (tCallDirPrdWt.getTPrdPrtType() == null) {
			jpaQuery.bind(TPRDPRTTYPE, new TPrdPrtType());
		} else {
			LOGGER.info("tPrdPrtType {}", tCallDirPrdWt.getTPrdPrtType());

			jpaQuery.bind(TPRDPRTTYPE, tCallDirPrdWt.getTPrdPrtType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallDirPrdWts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	 public Object countTCallDirPrdWts(final SearchFilter<TCallDirPrdWt>
	 searchFilter) {
	 LOGGER.info("=========== Count TCallDirPrdWt ===========");
	
	 final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
	 final TCallDirPrdWt tCallDirPrdWt = searchFilter.getEntityClass();
	 final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
	 final JPAQuery jpaCountQuery = new JPAQuery("tCallDirPrdWtentity",
	 tCallDirPrdWt);
	
	 if (tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig() == null) {
	 jpaCountQuery.bind(TCALLDIRCONFIG, new TCallDirConfig());
	 } else {
	 LOGGER.info("tCallDirConfig {}" + tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig());
	
	 jpaCountQuery.bind(TCALLDIRCONFIG, tCallDirPrdWt.getTCallDirPrdWtId().gettCallDirConfig());
	 }
	
	/* if (tCallDirPrdWt.getTPrdPrtType() == null) {
	 jpaCountQuery.bind(TPRDPRTTYPE, new TPrdPrtType());
	 } else {
	 LOGGER.info("tPrdPrtType {}", tCallDirPrdWt.getTPrdPrtType());
	
	 jpaCountQuery.bind(TPRDPRTTYPE, tCallDirPrdWt.getTPrdPrtType());
	 }
	*/
	 jpaCountQuery.setJPAql(JPACOUNTQL);
	 jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
	 jpaCountQuery.setLogicalOperatorEnum(logOpEnum);
	
	 return genericDAO.findEntities(jpaCountQuery, -1, -1);
	 }

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDirConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDirPrdWt> getTCallDirPrdWtsByTCallDirConfig(final SearchFilter<TCallDirConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCallDirConfig tCallDirConfig = searchFilter.getEntityClass();
		List<Object> tCallDirConfigList = new ArrayList<Object>();
		tCallDirConfigList.add(tCallDirConfig);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirPrdWtByTCallDirConfig", tCallDirConfigList, index,
				maxresult);
	}

	/**
	 * Count TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TCallDirConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallDirPrdWtsByTCallDirConfig(final SearchFilter<TCallDirConfig> searchFilter) {

		final TCallDirConfig tCallDirConfig = searchFilter.getEntityClass();
		List<Object> tCallDirConfigList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("CountTCallDirPrdWtsByTCallDirConfig", tCallDirConfigList);
	}

	/**
	 * Retrieve TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDirPrdWt> list of TCallDirPrdWts if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	public List<TCallDirPrdWt> getTCallDirPrdWtsByTPrdPrtType(final SearchFilter<TPrdPrtType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTCallDirPrdWtByTPrdPrtType", tPrdPrtType, index, maxresult);
	}*/

	/**
	 * Count TCallDirPrdWt based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdPrtType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
/*	public Object countTCallDirPrdWtsByTPrdPrtType(final SearchFilter<TPrdPrtType> searchFilter) {

		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTCallDirPrdWtsByTPrdPrtType", tPrdPrtType);
	}*/
	
	
	/**
	 * Get Product Weightage based on Call_Direction_Config_ID
	 * @param custCallPlanId
	 * 
	 * @return an Object of WeightageMatrix type
	 * 
	 */
	
	public List<Object> getWeightMatrix(TCustCallPlan tCustCallPlan){
		List paramList=new ArrayList();
		final Integer custCallPlanId = tCustCallPlan.getCustCallPlanId();
		paramList.add(custCallPlanId);
		paramList.add(tCustCallPlan.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("getWeightageMatrix", paramList, 0, -1);
	}
	
	/**
	 * Get Product Weightage based on Call_Direction_Config_ID from TCallDir
	 * @param custCallPlanId
	 * 
	 * @return an Object of WeightageMatrix type
	 * 
	 */
	
	public List<Object> getWeightMatrixByCallDirection(TCustCallPlan tCustCallPlan){
		final Integer custCallPlanId = tCustCallPlan.getCustCallPlanId();
		List<Object> custCallPlanIdList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("getWeightageMatrixByCallDirection",custCallPlanIdList);
	}	
	/**
	 * Retrieve ProductSplitStatus based on given search criteria using JPA named Query.
	 * The search criteria is of ProductSplitStatus
	 * 
	 * @param TCustCallPlan
	 *           - TCustCallPlan
	 * @Param tenantId
	 *           -tenantId          
	 * @return List<character> list of ProductSplitStatus if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
    public List<Character> getProductSplitStatus(Integer custCallPlanId,Short tenantId) {
           List list = new ArrayList();
           list.add(custCallPlanId);
           list.add(tenantId);
           return genericDAO.findEntitiesByNamedQueryMultiCond("getProductSplitFlag", list, 0, -1);
    }      


}
