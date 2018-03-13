package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallPlanType;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustCallPlanDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustCallPlanDAO")
public class TCustCallPlanDAOImpl implements TCustCallPlanDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustCallPlanDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTALGMNT = "tCustAlgmnt";
	private static final String TCALLPLANTYPE = "tCallPlanType";

	private final Class<TCustCallPlan> clazz;

	public Class<TCustCallPlan> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustCallPlanDAOImpl() {
		super();
		this.clazz = TCustCallPlan.class;
	}

	private static final String JPAQL = "select tCustCallPlanentity from TCustCallPlan tCustCallPlanentity";

	private static final String JPACOUNTQL = "select count(tCustCallPlanentity) from TCustCallPlan tCustCallPlanentity";

	private static final String[] RESTRICTIONS = {
			"tCustCallPlanentity.custCallPlanId = #{tCustCallPlanentity.getCustCallPlanId}",
			"tCustCallPlanentity.plannedCalls = #{tCustCallPlanentity.getPlannedCalls}",
			"tCustCallPlanentity.activeFlag = #{tCustCallPlanentity.getActiveFlag}",
			"Date(tCustCallPlanentity.effStartDt) = '#{tCustCallPlanentity.getEffStartDt}'",
			"Date(tCustCallPlanentity.effEndDt) = '#{tCustCallPlanentity.getEffEndDt}'",
			"tCustCallPlanentity.prtTypeId = #{tCustCallPlanentity.getPrtTypeId}",
			"tCustCallPlanentity.custId = #{tCustCallPlanentity.getCustId}",
			"tCustCallPlanentity.createdBy = #{tCustCallPlanentity.getCreatedBy}",
			"Date(tCustCallPlanentity.createDt) = '#{tCustCallPlanentity.getCreateDt}'",
			"tCustCallPlanentity.updatedBy = #{tCustCallPlanentity.getUpdatedBy}",
			"Date(tCustCallPlanentity.updateDt) = '#{tCustCallPlanentity.getUpdateDt}'",
			"tCustCallPlanentity.tenantId = #{tCustCallPlanentity.getTenantId}",
			"tCustCallPlanentity.statusId = #{tCustCallPlanentity.getStatusId}",
			"tCustCallPlanentity.tCustAlgmnt.custAlgmntId = #{tCustCallPlanentity.tCustAlgmnt.getCustAlgmntId}",
			"tCustCallPlanentity.tCallPlanType.callPlanTypeId = #{tCustCallPlanentity.tCallPlanType.getCallPlanTypeId}" };

	/**
	 * Stores a new TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be persisted
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	public TCustCallPlan createTCustCallPlan(final TCustCallPlan tCustCallPlan) {
		LOGGER.info("=========== Create TCustCallPlan ===========");
		return genericDAO.store(tCustCallPlan);
	}
	
	/**
	 * Stores a new TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be persisted
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	public List<TCustCallPlan> createTCustCallPlan(final List<TCustCallPlan> tCustCallPlanList) {
		LOGGER.info("=========== Create TCustCallPlan ===========");
		return genericDAO.storeBatch(tCustCallPlanList);
	}

	/**
	 * Deletes a TCustCallPlan entity object from the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be deleted
	 */
	public void deleteTCustCallPlan(final Integer custCallPlanId) {
		LOGGER.info("=========== Delete TCustCallPlan ===========");
		final TCustCallPlan tCustCallPlan = genericDAO.get(clazz, custCallPlanId);
		genericDAO.remove(tCustCallPlan);
	}

	/**
	 * Updates a TCustCallPlan entity object in to the persistent store
	 * 
	 * @param tCustCallPlan
	 *            TCustCallPlan Entity object to be updated
	 * @return tCustCallPlan Persisted TCustCallPlan object
	 */
	public TCustCallPlan updateTCustCallPlan(final TCustCallPlan tCustCallPlan) {
		LOGGER.info("=========== Update TCustCallPlan ===========");
		return genericDAO.update(tCustCallPlan);
	}

	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan custCallPlanId.
	 * 
	 * @param tCustCallPlanId
	 *            the primary key value of the TCustCallPlan Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustCallPlan findTCustCallPlanById(final Integer tCustCallPlanId) {
		LOGGER.info("find TCustCallPlan instance with custCallPlanId: " + tCustCallPlanId);
		return genericDAO.get(clazz, tCustCallPlanId);
	}

	/**
	 * Retrieve TCustCallPlan based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlan if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustCallPlan> findTCustCallPlans(final SearchFilter<TCustCallPlan> searchFilter) {
		LOGGER.info("=========== Find TCustCallPlans ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustCallPlanentity", tCustCallPlan);

		if (tCustCallPlan.getTCustAlgmnt() == null) {
			jpaQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustCallPlan.getTCustAlgmnt());

			jpaQuery.bind(TCUSTALGMNT, tCustCallPlan.getTCustAlgmnt());
		}

		if (tCustCallPlan.getTCallPlanType() == null) {
			jpaQuery.bind(TCALLPLANTYPE, new TCallPlanType());
		} else {
			LOGGER.info("tCallPlanType {}" + tCustCallPlan.getTCallPlanType());

			jpaQuery.bind(TCALLPLANTYPE, tCustCallPlan.getTCallPlanType());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustCallPlans.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustCallPlans(final SearchFilter<TCustCallPlan> searchFilter) {
		LOGGER.info("=========== Count TCustCallPlan ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustCallPlanentity", tCustCallPlan);

		if (tCustCallPlan.getTCustAlgmnt() == null) {
			jpaCountQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustCallPlan.getTCustAlgmnt());

			jpaCountQuery.bind(TCUSTALGMNT, tCustCallPlan.getTCustAlgmnt());
		}

		if (tCustCallPlan.getTCallPlanType() == null) {
			jpaCountQuery.bind(TCALLPLANTYPE, new TCallPlanType());
		} else {
			LOGGER.info("tCallPlanType {}" + tCustCallPlan.getTCallPlanType());

			jpaCountQuery.bind(TCALLPLANTYPE, tCustCallPlan.getTCallPlanType());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlans if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustCallPlan> getTCustCallPlansByTCustAlgmnt(TCustAlgmnt tCustAlgmnt,Short tenantId,boolean flag) {
		List queryParam = new ArrayList();
		queryParam.add(tCustAlgmnt);
		queryParam.add(tenantId);
		queryParam.add(flag ? 'Y' : 'N');
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlanByTCustAlgmnt", queryParam, 0, -1);
	}

	/**
	 * Count TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustCallPlansByTCustAlgmnt(final SearchFilter<TCustAlgmnt> searchFilter) {

		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustAlgmntList = new ArrayList<Object>();
		tCustAlgmntList.add(tCustAlgmnt);
		return genericDAO.findEntitiesByNamedQuery("CountTCustCallPlansByTCustAlgmnt", tCustAlgmntList);
	}

	/**
	 * Retrieve TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustCallPlan> list of TCustCallPlans if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustCallPlan> getTCustCallPlansByTCallPlanType(final SearchFilter<TCallPlanType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCallPlanType tCallPlanType = searchFilter.getEntityClass();
		List<Object> tCallPlanTypeList = new ArrayList<Object>();
		tCallPlanTypeList.add(tCallPlanType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlanByTCallPlanType", tCallPlanTypeList, index, maxresult);
	}

	/**
	 * Count TCustCallPlan based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustCallPlansByTCallPlanType(final SearchFilter<TCallPlanType> searchFilter) {

		final TCallPlanType tCallPlanType = searchFilter.getEntityClass();
		List<Object> tCallPlanTypeList = new ArrayList<Object>();
		tCallPlanTypeList.add(tCallPlanType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustCallPlansByTCallPlanType", tCallPlanTypeList);
	}
	/**
	 * Retrieve an CustomerCallPlanByJointStmt based on search criteria
	 * 
	 * @param callPlanTypeDescription
	 *           
	 * @return an Object of FindCustomerCallPlanByJointStmt if it exists 
	 */
	public List<Object[]> findCustomerCallPlanByJointStmt(Long salesPosId, Long salesHeirId, Short tenantId,String callPlanTypeDescription){
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(salesHeirId);
		paramList.add(tenantId);		
		paramList.add(callPlanTypeDescription);		
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCustomerCallPlanByJointStmt", paramList, 0, -1);
		
	}
	
	/**
	 * Retrieve an CustomerCallPlanObject based on search criteria
	 * 
	 * @param salesPosId
	 * 
	* @param details 
	 *           
	 * @return an Object of CustomerCallPlanObjectQuery if it exists 
	 */
	public List<Object[]> findCustomerCallPlanObject(Long salesPosId,Short tenantId){
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findCustomerCallPlanObjectQuery", paramList, 0, -1);
		
	}
	
	/**
	 * 
	 * @param plannedId
	 * @param custAlignment
	 * @return
	 */
	@Override
	public List<TCustCallPlan> findPlannedCustomerCallPlanObject(short callPlanTypeId ,long custAlignment,Short tenantId){
		List paramList = new ArrayList();
		paramList.add(callPlanTypeId);
		paramList.add(custAlignment);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindPlannedCustomerCallPlan", paramList, 0, -1);
		
	}

	/**
	 * Retrieve an CustomerCallPlan based on search criteria
	 * 
	 * @param CustomerCallPlan
	 * 
	 * @param algmntId
	 * @param bussUnitId
	 * @param algmntId
	 * @param salesTeamId
	 * @param salesHierId
	 * @param salesPosId
	 * @param callPlanTypeId
	 * 
	 * 
	 * @return custCallPlanId if it exists
	 */
	@Override
	public Integer findCustomerCallPlan(Integer customerId,Long algmntId, Long bussUnitId, Long salesTeamId, Long salesHierId,Long salesPosId, Short tenantId, Short callPlanTypeId) {
		List queryParam = new ArrayList();
		queryParam.add(customerId);
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(salesHierId);
		queryParam.add(salesPosId);
		queryParam.add(tenantId);
		queryParam.add(callPlanTypeId);
		Integer custCallPlanId = null;
		List<Integer> custCallPlans = genericDAO.findEntitiesByNamedQueryMultiCond("getCustCallPlanId", queryParam, 0, -1);
		if(!(custCallPlans.isEmpty())){
			
			custCallPlanId = custCallPlans.get(0);
			 
			return custCallPlanId;
		}
		else
			return custCallPlanId;
	}
	/**
	 * Retrieve an CountOfcallPlan
	 * 
	 * @param CustomerCallPlan
	 * 
	 * @param algmntId
	 * @param bussUnitId
	 * @param currentDate
	 * @param salesTeamId
	 * @param salesHierId
	 * @param salesPosId
	 * @param tenantId
	 * 
	 * 
	 * @return custCallPlanId if it exists
	 */
	@Override
	public Long fetchCountOfcallPlan(Long salesPosId, Long salesHierId,Long alignmentId, Long bussinessUnitId, Long salesTeamId, Short tenantId, Date currentDate) {
	
		List paramList = new ArrayList();
		paramList.add(alignmentId);
		paramList.add(bussinessUnitId);
		paramList.add(salesTeamId);
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(tenantId);
		paramList.add(currentDate);
		List<Object> count = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FetchCountOfcallPlan", paramList, 0, -1);
		Long countL = (Long) count.get(0);
		return countL;

	}
	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan custAffId.
	 * 
	 * @param custAffId
	 *           
	 * @return an List<TCustCallPlan> if it exists against given primary key.
	 * */
	@Override
	public List<TCustCallPlan> findTCustCallPlansByCustAffId(Integer custAffId,
			Character activeFlag, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(custAffId);
		paramList.add(activeFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlansByCustAffId", paramList, 0, -1);
	}
	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan custAlgmntId.
	 * 
	 * @param custAlgmntId
	 *            
	 * @return List<TCustCallPlan>
	 */
	@Override
	public List<TCustCallPlan> findTCustCallPlansByCustId(CustomerAlignment customerAlignment, short tenantId){
		List paramList = new ArrayList();
		paramList.add(customerAlignment.getCustomer().getId().intValue());
		//Integer cId = Integer.parseInt( customerAlignment.getCustomer().getId().toString());
		paramList.add(customerAlignment.getSalesPosition().getId());
		paramList.add(customerAlignment.getSalesPosition().getHierarchy().getId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTCustCallPlansByCustId", paramList, 0, -1);
		
	}

	/**
	 * Retrieve an TCustCallPlan object based on given TCustCallPlan customerId.
	 * 
	 * @param customerId
	 * @param custAlgmntId
	 * @param algmntId
	 * @param bussUnitId
	 * @param salesTeamId
	 * @param tenantId
	 * @param custAffId
	 * @param flag
	 * 
	 * @return an tCustCallPlansList
	 */
	@Override
	public List<TCustCallPlan> getTCustCall(Long custAlgmntId,Integer customerId,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId, Character flag,Short type,  Integer custAffId){
		List paramList = new ArrayList();
		paramList.add(customerId);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		paramList.add(flag);
		paramList.add(type);
		paramList.add(custAlgmntId);
		List<TCustCallPlan> tCustCallPlansList = null;
		if(custAffId == null){
			tCustCallPlansList = genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCall", paramList, 0, -1);
			} else {
			
			paramList.add(custAffId);
			tCustCallPlansList = genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCallAff", paramList, 0, -1);
		
			}
		return tCustCallPlansList;
		
	}
	/**
	 * Retrieve an TCustCallPlan object based on given updateTCustAlgmnt 
	 * 
	 * @param updateTCustAlgmnt
	 *            
	 * @return  List<TCustCallPlan>
	 */
	@Override
	public List<TCustCallPlan> findTCustCallPlanByTCustAlgmntObj(
			TCustAlgmnt updateTCustAlgmnt, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(updateTCustAlgmnt);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlanByTCustAlgmntObj", paramList, 0, -1);
	}
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	@Override	
	public List<TCustCallPlan> getCommitedCallPlan(List<Long> custAlgmntList,Short callPlanType,Short tenantId){
		List paramList = new ArrayList();
		paramList.add(custAlgmntList);
		paramList.add(callPlanType);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getCommittedCallPlanForCustAlgmnt", paramList, 0, -1);
	}
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	@Override
	public List<TCustCallPlan> findTCustCallPlansByCustIdAndCustAffId(
			Integer custId, Integer custAffId, Character activeFlag,Short tCallPlanType,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);
		paramList.add(custAffId);
		paramList.add(activeFlag);
		paramList.add(tCallPlanType);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustCallPlansByCustAffIdAndCustId", paramList, 0, -1);
	}
	/**
	 * Retrieve an TCustCallPlan object based on given custAlgmntList 
	 * 
	 * @param custAlgmntList
	 *          
	 * @return List<TCustCallPlan> 
	 */
	@Override
	public List<TCustCallPlan> findActiveCustCallPlansByCustAlgmntId(List<Long> custAlgmntId,short tenantId){
		List paramList = new ArrayList();
		paramList.add(custAlgmntId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveCustCallPlansByCustAlgmntId", paramList, 0, -1);
	}

	/**
	 * Retrieve an object based on given custAlgmntIds
	 * 
	 * @param custAlgmntIds
	 * @param customerIds
	 * @param algmntId
	 * @param bussUnitId
	 * @param tenantId
	 * @param flag
	 * @param type
	 * @param custAffIds
	 * 
	 * 
	 * @return tCustCallPlansList
	 */
	@Override
	
	public List<Object[]> getCustPLCalls(List<Long> custAlgmntIds,List<Integer> customerIds,Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId, Character flag,Short type, List<Integer> custAffIds){
		List paramList = new ArrayList();
		paramList.add(customerIds);
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		paramList.add(flag);
		paramList.add(type);
		paramList.add(custAlgmntIds);
		List<Object[]> tCustCallPlansList = null;
		if(custAffIds != null&&!(custAffIds.isEmpty())){
			paramList.add(custAffIds);
			tCustCallPlansList = genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCallAffPLCALS", paramList, 0, -1);
		} else {
			tCustCallPlansList = genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCallPLCALS", paramList, 0, -1);
		}
		return tCustCallPlansList;
		
	}
	
	/**
	 * Retrieve an getCallDirInfo 
	 * 
	 * @param custAlgmntList
	 * @param callPlanType
	 * @param tenantId
	 *          
	 * @return object of TCustCallDIRInfo
	 */
	
	@Override	
	public List<Object[]> getCallDirInfo(List<Long> custAlgmntList,Short callPlanType,Short tenantId,Boolean isCalDir){
		List paramList = new ArrayList();
		paramList.add(custAlgmntList);
		paramList.add(callPlanType);
		paramList.add(tenantId);
		if(isCalDir){
			return genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCallDIRInfo", paramList, 0, -1);
		}else{
			return genericDAO.findEntitiesByNamedQueryMultiCond("getTCustCallPRDInfo", paramList, 0, -1);
		}		
	}

	/**
	 * Retrieve an getCallDirInfo
	 * 
	 * @param custCallPlanIds
	 * 
	 * 
	 * @return object of CallDirInfoByCustCalPlanId
	 */
	
	@Override	
	public List<Object[]> getCallDirInfo(List<Integer> custCallPlanIds){
		List paramList = new ArrayList();
		paramList.add(custCallPlanIds);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCallDirInfoByCustCalPlanId", paramList, 0, -1);				
	}
	/**
	 * Retrieve an ProdForCallDirInf
	 * 
	 * @param custCallPlanIds
	 * 
	 * 
	 * @return object of ProdForCallDirInfoByCustCalPlanId
	 */
	
	@Override	
	public List<Object[]> getProdForCallDirInfo(List<Integer> custCallPlanIds){
		List paramList = new ArrayList();
		paramList.add(custCallPlanIds);	
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetProdForCallDirInfoByCustCalPlanId", paramList, 0, -1);				
	}

	/**
	 * Retrieve an getPlannedCallsForTier.
	 * 
	 * @param chngReqStatusId
	 * @param custcallplanchngreqdetStatusId
	 * @param salesPosId
	 * 
	 * @return an ObjectPlannedCallsForTiers
	 */
	@Override
	public List<Object[]> getPlannedCallsForTier(Integer chngReqStatusId, Integer custcallplanchngreqdetStatusId, Long salesPosId){
		List paramList = new ArrayList();
		
		paramList.add(chngReqStatusId);
		paramList.add(custcallplanchngreqdetStatusId);
		
		paramList.add(salesPosId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getPlannedCallsForTiers", paramList, 0, -1);
	}

	

	/**
	 * Gets the customer call plan details.
	 *
	 * @param customer the customer
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the customer call plan details
	 */
	@Override
	public List<TCustCallPlan> getCustomerCallPlanDetails(
			TCustCallPlan tCustCallPlan) {
		  List paramList = new ArrayList();
			
			paramList.add(tCustCallPlan.getTCustAlgmnt().getTCust().getCustId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getSalesPosId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getSalesHierId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getAlgmntId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getBussUnitId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getSalesTeamId());
			paramList.add(tCustCallPlan.getTCustAlgmnt().getTenantId());
			
			return  genericDAO.findEntitiesByNamedQueryMultiCond("getCustomerCallPlanDetails", paramList, 0, -1);

	}
	/**
	 * @param CustAlId
	 * @param tenantId
	 * @return custCallPlanId
	 * 
	 */
	@Override
	public  List<TCustCallPlan> FindCallPlanByCustAlId(Long customerAlignmentId, short tenantId){
		List paramList = new ArrayList();
		paramList.add(customerAlignmentId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCallPlanByCustAlId", paramList, 0, -1);
		
	}
	/**
	 * @param CustAlId
	 * @param tenantId
	 * @return custCallPlanId
	 * 
	 */
	@Override
	public  List<TCustCallPlan> FindCallPlanByCustAlIdForEdit(Long customerAlignmentId, short tenantId){
		List paramList = new ArrayList();
		paramList.add(customerAlignmentId);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCallPlanByCustAlIdForEdit", paramList, 0, -1);
		
	}
	
}
