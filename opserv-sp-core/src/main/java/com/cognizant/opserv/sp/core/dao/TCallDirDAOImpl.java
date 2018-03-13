package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallDir;
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
 * Class provides API implementation for TCallDirDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallDirDAO")
public class TCallDirDAOImpl implements TCallDirDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallDirDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTCALLPLAN = "tCustCallPlan";

	private final Class<TCallDir> clazz;

	public Class<TCallDir> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallDirDAOImpl() {
		super();
		this.clazz = TCallDir.class;
	}

	private static final String JPAQL = "select tCallDirentity from TCallDir tCallDirentity";

	private static final String JPACOUNTQL = "select count(tCallDirentity) from TCallDir tCallDirentity";

	private static final String[] RESTRICTIONS = { "tCallDirentity.callDirId = #{tCallDirentity.getCallDirId}",
			"tCallDirentity.numCalls = #{tCallDirentity.getNumCalls}",
			"tCallDirentity.activeFlag = #{tCallDirentity.getActiveFlag}",
			"tCallDirentity.createdBy = #{tCallDirentity.getCreatedBy}",
			"Date(tCallDirentity.createDt) = '#{tCallDirentity.getCreateDt}'",
			"tCallDirentity.updatedBy = #{tCallDirentity.getUpdatedBy}",
			"Date(tCallDirentity.updateDt) = '#{tCallDirentity.getUpdateDt}'",
			"tCallDirentity.tenantId = #{tCallDirentity.getTenantId}",
			"tCallDirentity.tCustCallPlan.custCallPlanId = #{tCallDirentity.tCustCallPlan.getCustCallPlanId}" };

	/**
	 * Stores a new TCallDir entity object in to the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be persisted
	 * @return tCallDir Persisted TCallDir object
	 */
	public TCallDir createTCallDir(final TCallDir tCallDir) {
		LOGGER.info("=========== Create TCallDir ===========");
		return genericDAO.store(tCallDir);
	}

	/**
	 * Deletes a TCallDir entity object from the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be deleted
	 * @return tCallDir Persisted TCallDir object
	 */
	public void deleteTCallDir(final Integer callDirId) {
		LOGGER.info("=========== Delete TCallDir ===========");
		final TCallDir tCallDir = genericDAO.get(clazz, callDirId);
		genericDAO.remove(tCallDir);
	}

	/**
	 * Updates a TCallDir entity object in to the persistent store
	 * 
	 * @param tCallDir
	 *            TCallDir Entity object to be updated
	 * @return tCallDir Persisted TCallDir object
	 */
	public TCallDir updateTCallDir(final TCallDir tCallDir) {
		LOGGER.info("=========== Update TCallDir ===========");
		return genericDAO.update(tCallDir);
	}

	/**
	 * Retrieve an TCallDir object based on given TCallDir callDirId.
	 * 
	 * @param tCallDirId
	 *            the primary key value of the TCallDir Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallDir findTCallDirById(final Integer tCallDirId) {
		LOGGER.info("find TCallDir instance with callDirId: " + tCallDirId);
		return genericDAO.get(clazz, tCallDirId);
	}

	/**
	 * Retrieve TCallDir based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDir> list of TCallDir if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallDir> findTCallDirs(final SearchFilter<TCallDir> searchFilter) {
		LOGGER.info("=========== Find TCallDirs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallDir tCallDir = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallDirentity", tCallDir);

		if (tCallDir.getTCustCallPlan() == null) {
			jpaQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}"+ tCallDir.getTCustCallPlan());

			jpaQuery.bind(TCUSTCALLPLAN, tCallDir.getTCustCallPlan());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallDirs.
	 * @param searchFilter
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallDirs(final SearchFilter<TCallDir> searchFilter) {
		LOGGER.info("=========== Count TCallDir ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallDir tCallDir = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallDirentity", tCallDir);

		if (tCallDir.getTCustCallPlan() == null) {
			jpaCountQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}"+ tCallDir.getTCustCallPlan());

			jpaCountQuery.bind(TCUSTCALLPLAN, tCallDir.getTCustCallPlan());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCallDir based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallDir> list of TCallDirs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallDir> getTCallDirsByTCustCallPlan(final SearchFilter<TCustCallPlan> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		tCustCallPlanList.add(tCustCallPlan);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallDirByTCustCallPlan", tCustCallPlanList, index, maxresult);
	}

	/**
	 * Count TCallDir based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallDirsByTCustCallPlan(final SearchFilter<TCustCallPlan> searchFilter) {

		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery(
				"CountTCallDirsByTCustCallPlan", tCustCallPlanList);
	}

	/**
	 * Get Call Direction Details from TCallDir
	 * 
	 * @param custCallPlanId
	 * 
	 * @return an Object of CallDirectionMatrix type
	 * 
	 */
	public List<Object> getCallDirectionMatrix(TCustCallPlan tCustCallPlan) {
		final Integer custCallPlanId = tCustCallPlan.getCustCallPlanId();
		List<Object> custCallPlanIdList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("getCallDirectionData",
				custCallPlanIdList);
	}

	/**
	 * Delete CallDirection Details From TCallDir
	 * 
	 * @param callDirectionId
	 * 
	 * @return boolean
	 * 
	 */
	public Boolean deleteCallDirection(Integer callDirectionId,
			Short tenantId) {
	
		List<Object> callDirIds = new ArrayList<Object>();
		callDirIds.add(callDirectionId);
		callDirIds.add(tenantId);
		try {
			LOGGER.info("==== Deletion Starts ===="+tenantId.intValue()+ " & "+callDirectionId);
			LOGGER.info("list contains : "+callDirIds);
			genericDAO.updateEntitiesByNamedQueryMultiCond(
					"deleteCallDirection", callDirIds, -1, -1);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return false;
		}
		
	}
	/**
	 * Get Header Names
	 * 
	 * @param size
	 * 
	 * @return String of TopColNames
	 * 
	 */
	@Override
	public List<String> getHeaderNames(Integer size){
		List<Object> sizeList = new ArrayList<Object>();
		return genericDAO.findEntitiesByNamedQuery("getTopColNames", sizeList);
	}

	/**
	 * Get Targeted Customers
	 * 
	 * @param queryParams
	 * 
	 * @param showBy
	 * 
	 * @param custType
	 * 
	 * @param targetFlag
	 * 
	 * @return an object of targetedCustomerList
	 * 
	 */
	
	@Override
	public List<Object> getTargetedCustomers(List queryParams, Integer showBy, Boolean callPlanAffiliationFlag, Integer custType, String targetFlag) {
		List<Object> targetedCustomerList = null;
		if(callPlanAffiliationFlag){
			
			targetedCustomerList = genericDAO.findEntitiesByNamedQueryMultiCond("getTargetedCustomersForAff",queryParams,0,showBy);
		} else {
			if(targetFlag==null){
				targetedCustomerList = genericDAO.findEntitiesByNamedQueryMultiCond("getTargetedCustomersForNonAff",queryParams,0,showBy);

			} else{
							}
		}
		return targetedCustomerList;
	}

	/**
	 * Get ProductsForCallDir
	 * 
	 * @param queryParams
	 * 
	 * @return an object of ProductsForNonCallDirectionBasedCallPlan
	 * 
	 */
	@Override
	public List<Object[]> fetchProductsForCallDir(List queryParams){
		return genericDAO.findEntitiesByNamedQueryMultiCond("getProductsForNonCallDirectionBasedCallPlan",queryParams,0,-1);
	}	

	/**
	 * Get Targeted Customer
	 * 
	 * @param queryParams
	 * 
	 * @param showBy
	 * 
	 * @return an object of TargetedCustomer
	 * 
	 */
	
	@Override
	public List<Object> searchTargetedCustomer(List queryParams, Integer showBy) {
		return genericDAO.findEntitiesByNamedQueryMultiCond("searchTargetedCustomer",queryParams,0,showBy);
	}
	/**
	 * Get Customers Count
	 * 
	 * @param queryParams
	 * 
	 * @param callPlanAffiliationFlag
	 * 
	 * @return count
	 * 
	 */
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public Long getCustomersCount(List queryParams,Boolean callPlanAffiliationFlag){
		
		List<Object> targetedCustomerCount = null;
		if(callPlanAffiliationFlag){
			targetedCustomerCount = genericDAO.findEntitiesByNamedQueryMultiCond("getTargetedCustomersForAffCount",queryParams,0,-1);
			
		} else {
			
			targetedCustomerCount = genericDAO.findEntitiesByNamedQueryMultiCond("getTargetedCustomersForNonAffCount",queryParams,0,-1);
		}
		
		Long count = (long) targetedCustomerCount.get(0);
		
		return count;
	}
	/**
	 * Get Targeted Customers
	 * 
	 * @param custAlgmntIds
	 * 
	 * @param tenantId
	 * 
	 * @param localeId
	 * 
	 * @return targetedCustomerList
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> getTargetedCustomers(List<Long> custAlgmntIds,Short tenantId,String localeId) {
		List paramList = new ArrayList();
		paramList.add(custAlgmntIds);
		paramList.add(tenantId);
		paramList.add(localeId);
		List<Object> targetedCustomerList;
		 targetedCustomerList = genericDAO.findEntitiesByNamedQueryMultiCond("getCustomersByCustAlgmntIds",paramList,0,-1);
		return targetedCustomerList;
	}
}
