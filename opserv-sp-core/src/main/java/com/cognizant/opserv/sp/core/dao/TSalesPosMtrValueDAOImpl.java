package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtrExec;
import com.cognizant.opserv.sp.core.entity.TSalesPosMtrValue;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosMtrValueDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosMtrValueDAO")
public class TSalesPosMtrValueDAOImpl implements TSalesPosMtrValueDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosMtrValueDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTREXEC = "tMtrExec";

	private final Class<TSalesPosMtrValue> clazz;

	public Class<TSalesPosMtrValue> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosMtrValueDAOImpl() {
		super();
		this.clazz = TSalesPosMtrValue.class;
	}

	private static final String JPAQL = "select tSalesPosMtrValueentity from TSalesPosMtrValue tSalesPosMtrValueentity";

	private static final String JPACOUNTQL = "select count(tSalesPosMtrValueentity) from TSalesPosMtrValue tSalesPosMtrValueentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosMtrValueentity.seqNumber = #{tSalesPosMtrValueentity.getSeqNumber}",
			"tSalesPosMtrValueentity.mtrValue = #{tSalesPosMtrValueentity.getMtrValue}",
			"tSalesPosMtrValueentity.salesPosId = #{tSalesPosMtrValueentity.getSalesPosId}",
			"tSalesPosMtrValueentity.salesHierId = #{tSalesPosMtrValueentity.getSalesHierId}",
			"Date(tSalesPosMtrValueentity.execDtTm) = '#{tSalesPosMtrValueentity.getExecDtTm}'",
			"tSalesPosMtrValueentity.mtrId = #{tSalesPosMtrValueentity.getMtrId}",
			"tSalesPosMtrValueentity.createdBy = #{tSalesPosMtrValueentity.getCreatedBy}",
			"Date(tSalesPosMtrValueentity.createDt) = '#{tSalesPosMtrValueentity.getCreateDt}'",
			"tSalesPosMtrValueentity.updatedBy = #{tSalesPosMtrValueentity.getUpdatedBy}",
			"Date(tSalesPosMtrValueentity.updateDt) = '#{tSalesPosMtrValueentity.getUpdateDt}'",
			"tSalesPosMtrValueentity.tenantId = #{tSalesPosMtrValueentity.getTenantId}",
			"tSalesPosMtrValueentity.mtrValueTypeId = #{tSalesPosMtrValueentity.getMtrValueTypeId}",
			"tSalesPosMtrValueentity.tMtrExec.tMtrExecId = #{tSalesPosMtrValueentity.tMtrExec.getTMtrExecId}" };

	/**
	 * Stores a new TSalesPosMtrValue entity object in to the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be persisted
	 * @return tSalesPosMtrValue Persisted TSalesPosMtrValue object
	 */
	public TSalesPosMtrValue createTSalesPosMtrValue(final TSalesPosMtrValue tSalesPosMtrValue) {
		LOGGER.info("=========== Create TSalesPosMtrValue ===========");
		return genericDAO.store(tSalesPosMtrValue);
	}

	/**
	 * Deletes a TSalesPosMtrValue entity object from the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be deleted
	 */
	public void deleteTSalesPosMtrValue(final Long seqNumber) {
		LOGGER.info("=========== Delete TSalesPosMtrValue ===========");
		final TSalesPosMtrValue tSalesPosMtrValue = genericDAO.get(clazz, seqNumber);
		genericDAO.remove(tSalesPosMtrValue);
	}

	/**
	 * Updates a TSalesPosMtrValue entity object in to the persistent store
	 * 
	 * @param tSalesPosMtrValue
	 *            TSalesPosMtrValue Entity object to be updated
	 * @return tSalesPosMtrValue Persisted TSalesPosMtrValue object
	 */
	public TSalesPosMtrValue updateTSalesPosMtrValue(final TSalesPosMtrValue tSalesPosMtrValue) {
		LOGGER.info("=========== Update TSalesPosMtrValue ===========");
		return genericDAO.update(tSalesPosMtrValue);
	}

	/**
	 * Retrieve an TSalesPosMtrValue object based on given TSalesPosMtrValue seqNumber.
	 * 
	 * @param tSalesPosMtrValueId
	 *            the primary key value of the TSalesPosMtrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosMtrValue findTSalesPosMtrValueById(final Long tSalesPosMtrValueId) {
		LOGGER.info("find TSalesPosMtrValue instance with seqNumber: " + tSalesPosMtrValueId);
		return genericDAO.get(clazz, tSalesPosMtrValueId);
	}

	/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValue if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosMtrValue> findTSalesPosMtrValues(final SearchFilter<TSalesPosMtrValue> searchFilter) {
		LOGGER.info("=========== Find TSalesPosMtrValues ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosMtrValue tSalesPosMtrValue = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosMtrValueentity", tSalesPosMtrValue);

		if (tSalesPosMtrValue.getTMtrExec() == null) {
			jpaQuery.bind(TMTREXEC, new TMtrExec());
		} else {
			LOGGER.info("tMtrExec {}"+ tSalesPosMtrValue.getTMtrExec());

			jpaQuery.bind(TMTREXEC, tSalesPosMtrValue.getTMtrExec());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosMtrValues.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosMtrValues(final SearchFilter<TSalesPosMtrValue> searchFilter) {
		LOGGER.info("=========== Count TSalesPosMtrValue ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosMtrValue tSalesPosMtrValue = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosMtrValueentity", tSalesPosMtrValue);

		if (tSalesPosMtrValue.getTMtrExec() == null) {
			jpaCountQuery.bind(TMTREXEC, new TMtrExec());
		} else {
			LOGGER.info("tMtrExec {}"+ tSalesPosMtrValue.getTMtrExec());

			jpaCountQuery.bind(TMTREXEC, tSalesPosMtrValue.getTMtrExec());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesPosMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosMtrValue> list of TSalesPosMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPosMtrValue> getTSalesPosMtrValuesByTMtrExec(final SearchFilter<TMtrExec> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosMtrValueByTMtrExec", queryParam, index, maxresult);
	}

	/**
	 * Count TSalesPosMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TMtrExec type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPosMtrValuesByTMtrExec(final SearchFilter<TMtrExec> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPosMtrValuesByTMtrExec", queryParam);
	}

	@Override
	public List<Object> getOldMetricValue(Integer metricId,Short tenantId) {
		// TODO Auto-generated method stub
		List queryParam = new ArrayList();
		queryParam.add(metricId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getLatestMetricValueByMetricId", queryParam, 0, 1);
	}
	/**
	 *TSalesPosMtrValue based on given search criteria using JPA named Query.
	
	 * @param salesPosId
	 *            The query criteria and salesPosId conditions are set
	 * @return a Object indicating the list
	 */
	
	@Override
	public List<TSalesPosMtrValue> fetchmetricsBySalesPosId(Short tenantId, final Long salesPosId) {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		
		paramList.add(salesPosId);
		paramList.add(tenantId);
		
		List<TSalesPosMtrValue> list=null;
		try {
			list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosMtrValueBySalesPosId", paramList, 0, 100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*List<TSalesPosMtrValue> list=listl;*/
		return list; 
	}
	
	
	/**
	 *TSalesPosMtrValue based on given search criteria using JPA named Query.
	
	 * @param salesPosId
	 *            The query criteria and salesPosId conditions are set
	 * @return a Object indicating the list
	 */
	
	@Override
	public List<Object[]> fetchMetricValueByBaseValueFlag(Long salesPosId, Long salesHier,Short tenantId,Integer metricID, Integer mtrValueTypeId) {
		
		List paramList = new ArrayList();
		paramList.add(metricID);
		paramList.add(salesHier);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		
		List<Object[]> list= genericDAO.findEntitiesByNamedQueryMultiCond("getLatestMetricValueByBaseValueFlag", paramList, 0, 1);
		
		return list; 
	}

	
	@Override
	public  List<Object[]> fetchMetricValueByBaseValueFlagList(Short tenantId,List<Integer> metricID,Long salesPosId, Long salesHierId, Integer mtrValueTypeId) {
		List paramList = new ArrayList();
		paramList.add(metricID);
		paramList.add(salesHierId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		List<Object[]> list= genericDAO.findEntitiesByNamedQueryMultiCond("getLatestMetricValueByBaseValueFlag", paramList, 0, 1);
		return list; 
	}
	
	
	@Override
	public List<TSalesPosMtrValue> findSalesPosMtrValByPrnIDs(Long prnSalesPosId, Long prnSalesHierId,int mtrId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(prnSalesPosId);
		paramList.add(prnSalesHierId);
		paramList.add(mtrId);
		//paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTSalesPosMtrValueByIds", paramList,0,-1);
	}
@Override
	public List<TSalesPosMtrValue> getTSalesPosMtrValuesByPosIdHierIdAndTenant(Long salesPosId, Long salesHierId, Short tenantId,boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(salesPosId);
		queryParam.add(salesHierId);
		queryParam.add(tenantId);
		queryParam.add(flag ? 'Y' : 'N');
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosMtrValueByPosIDHierIDAndTenant", queryParam, 0,-1);
	}

	@Override
	public List<TSalesPosMtrValue> findTSalesPosMtrValueBySalesPosId(Long salesPosId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(salesPosId);
		 return genericDAO.findEntitiesByNamedQueryMultiCond("FindMetricBySalesPosId", queryParam, 0, -1);
	}

	@Override
	public List<TSalesPosMtrValue> findTSalesPosMtrValueBySalesPosMtrId(Long salesPosId,
			Long salesHierId, int mtrId,  Short tenantId, Integer mtrValueTypeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(mtrId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTSalesPosMtrValueBySalesPosMtrId", paramList,0,1);
	}

	@Override
	public List getTSalesPosMtrValue(String query) {
		List tMtrValueList = genericDAO.findByNativeQuery(query);
		//return tPersList;
		return tMtrValueList;
	}
	
	@Override
	public List<Object[]> fetchMetricValueByBaseFlag(Long salesPosId, Long salesHier,Short tenantId,Integer metricID, Integer mtrValueTypeId) {
		
		List paramList = new ArrayList();
		
		paramList.add(metricID);
		paramList.add(salesHier);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		
		List<Object[]> list= genericDAO.findEntitiesByNamedQueryMultiCond("getLatestMetricValueByBaseFlag", paramList, 0, 1);
		
		return list; 
	}

	@Override
	public List<TSalesPosMtrValue> getMetricValForSP(int mtrId,
			Long salesHierId, List<Long> spList, Short tenantId, Integer mtrValueTypeId) {
        List paramList = new ArrayList();
		paramList.add(mtrId);
		paramList.add(spList);
		paramList.add(salesHierId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTSalesPosMtrValueByMtrId", paramList,0,-1);
	}

	@Override
	public List<TSalesPosMtrValue> checkMetricIds(int mtrId, Short tenantId) {
		 List<Object> paramList = new ArrayList<Object>();
			paramList.add(mtrId);
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond("CheckMetricIds", paramList,0,-1);
	}
	
	@Override
	public void updateMetricBaseValuesByMetricId(int mtrId, Short tenantId, Integer mtrValueTypeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(mtrId);
		paramList.add(tenantId);
		paramList.add(mtrValueTypeId);
		genericDAO.updateEntitiesByNamedQueryMultiCond("UpdateMetricBaseValuesByMetricId", paramList,0,-1);
	}
	
@Override
	public List<Integer> checkIfMetricsExecuted(List<Integer> mtrIds, Integer mtrValueTypeId, Character activeFlag,Short tenantId) {
		 List<Object> paramList = new ArrayList<Object>();
			paramList.add(mtrIds);
			paramList.add(mtrValueTypeId);
			paramList.add(activeFlag);
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond("CheckIfMetricsExecuted", paramList,0,-1);
	}

@Override
public void inactivateSPMetricValues(List<Integer> mtrIds, Short tenantId,Long spId,Integer mtrValueTypeId,Character actFlag) {
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrIds);
	paramList.add(mtrValueTypeId);
	paramList.add(actFlag);
	paramList.add(tenantId);
	paramList.add(spId);
	genericDAO.updateEntitiesByNamedQueryMultiCond("InactivateSPMetricValues", paramList,0,-1);
}

@Override
public List<Object[]> getMtrValueBySpAndValueType(Long spId,Integer mtrValueTypeId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(spId);
	paramList.add(mtrValueTypeId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMtrValueBySpAndValueType", paramList, 0, -1);
}

@Override
public List<Object[]> getMtrValueByMtrSpAndBaseFlag(List<Integer> mtrId,Long spId,List<Integer> mtrValueTypeId,Short tenantId){
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(mtrId);
	paramList.add(spId);
	paramList.add(mtrValueTypeId);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMtrValueByMtrSpAndBaseFlag", paramList, 0, -1);
}

@Override
public List<Object[]> getMtrValueBySpAndValueTypeAndMtrIdAndConfigId(Long salesPosId,
		Integer mtrValueTypeId,Character activeFlag, List<Integer> mtrIds, List<Integer> configIds, 
		Short tenantId) {
	List<Object> paramList = new ArrayList<Object>();
	paramList.add(salesPosId);
	paramList.add(mtrValueTypeId);
	paramList.add(activeFlag);
	paramList.add(mtrIds);
	paramList.add(configIds);
	paramList.add(tenantId);	
	return genericDAO.findEntitiesByNamedQueryMultiCond("GetMtrValueBySpAndValueTypeAndMtrIdAndConfigId", paramList, 0, -1);
}

	@Override
	public List<Object[]> getMtrValueBySpListAndValueType(List<Long> spIdList,
			Integer mtrValueTypeId, Integer mtrId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(spIdList);
		paramList.add(mtrValueTypeId);
		paramList.add(mtrId);
		paramList.add(tenantId);	
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetMtrValueBySpListAndValueType", paramList, 0, -1);
	}
	
	@Override
	public List<Object[]> findMinAndMaxValueByMtrId(Long mtrId,Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(mtrId.intValue());
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findMinAndMaxValueByMtrId", paramList, 0, -1);
	}

}
