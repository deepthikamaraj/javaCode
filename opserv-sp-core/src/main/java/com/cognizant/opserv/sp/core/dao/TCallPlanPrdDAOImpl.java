package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCallPlanPrd;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCallPlanPrdDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCallPlanPrdDAO")
public class TCallPlanPrdDAOImpl implements TCallPlanPrdDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCallPlanPrdDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTPRDALGMNT = "tCustPrdAlgmnt";
	private static final String TCUSTCALLPLAN = "tCustCallPlan";

	private final Class<TCallPlanPrd> clazz;

	public Class<TCallPlanPrd> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCallPlanPrdDAOImpl() {
		super();
		this.clazz = TCallPlanPrd.class;
	}

	private static final String JPAQL = "select tCallPlanPrdentity from TCallPlanPrd tCallPlanPrdentity";

	private static final String JPACOUNTQL = "select count(tCallPlanPrdentity) from TCallPlanPrd tCallPlanPrdentity";

	private static final String[] RESTRICTIONS = {
			"tCallPlanPrdentity.callPlanPrdId = #{tCallPlanPrdentity.getCallPlanPrdId}",
			"tCallPlanPrdentity.plannedCalls = #{tCallPlanPrdentity.getPlannedCalls}",
			"tCallPlanPrdentity.activeFlag = #{tCallPlanPrdentity.getActiveFlag}",
			"tCallPlanPrdentity.prdId = #{tCallPlanPrdentity.getPrdId",
			"tCallPlanPrdentity.createdBy = #{tCallPlanPrdentity.getCreatedBy}",
			"Date(tCallPlanPrdentity.createDt) = '#{tCallPlanPrdentity.getCreateDt}'",
			"tCallPlanPrdentity.updatedBy = #{tCallPlanPrdentity.getUpdatedBy}",
			"Date(tCallPlanPrdentity.updateDt) = '#{tCallPlanPrdentity.getUpdateDt}'",
			"tCallPlanPrdentity.tenantId = #{tCallPlanPrdentity.getTenantId}",
			"tCallPlanPrdentity.tCustPrdAlgmnt.tCustPrdAlgmntId = #{tCallPlanPrdentity.tCustPrdAlgmnt.getTCustPrdAlgmntId}",
			"tCallPlanPrdentity.tCustCallPlan.custCallPlanId = #{tCallPlanPrdentity.tCustCallPlan.getCustCallPlanId}" };

	/**
	 * Stores a new TCallPlanPrd entity object in to the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be persisted
	 * @return tCallPlanPrd Persisted TCallPlanPrd object
	 */
	public TCallPlanPrd createTCallPlanPrd(final TCallPlanPrd tCallPlanPrd) {
		LOGGER.info("=========== Create TCallPlanPrd ===========");
		return genericDAO.store(tCallPlanPrd);
	}

	/**
	 * Deletes a TCallPlanPrd entity object from the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be deleted
	 */
	public void deleteTCallPlanPrd(final Integer callPlanPrdId) {
		LOGGER.info("=========== Delete TCallPlanPrd ===========");
		final TCallPlanPrd tCallPlanPrd = genericDAO.get(clazz, callPlanPrdId);
		genericDAO.remove(tCallPlanPrd);
	}

	/**
	 * Updates a TCallPlanPrd entity object in to the persistent store
	 * 
	 * @param tCallPlanPrd
	 *            TCallPlanPrd Entity object to be updated
	 * @return tCallPlanPrd Persisted TCallPlanPrd object
	 */
	public TCallPlanPrd updateTCallPlanPrd(final TCallPlanPrd tCallPlanPrd) {
		LOGGER.info("=========== Update TCallPlanPrd ===========");
		return genericDAO.update(tCallPlanPrd);
	}

	/**
	 * Retrieve an TCallPlanPrd object based on given TCallPlanPrd callPlanPrdId.
	 * 
	 * @param tCallPlanPrdId
	 *            the primary key value of the TCallPlanPrd Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCallPlanPrd findTCallPlanPrdById(final Integer tCallPlanPrdId) {
		LOGGER.info("find TCallPlanPrd instance with callPlanPrdId: " + tCallPlanPrdId);
		return genericDAO.get(clazz, tCallPlanPrdId);
	}

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrd if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCallPlanPrd> findTCallPlanPrds(final SearchFilter<TCallPlanPrd> searchFilter) {
		LOGGER.info("=========== Find TCallPlanPrds ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCallPlanPrd tCallPlanPrd = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCallPlanPrdentity", tCallPlanPrd);

		if (tCallPlanPrd.getTCustPrdAlgmnt() == null) {
			jpaQuery.bind(TCUSTPRDALGMNT, new TCustPrdAlgmnt());
		} else {
			LOGGER.info("tCustPrdAlgmnt {}"+ tCallPlanPrd.getTCustPrdAlgmnt());

			jpaQuery.bind(TCUSTPRDALGMNT, tCallPlanPrd.getTCustPrdAlgmnt());
		}

		if (tCallPlanPrd.getTCustCallPlan() == null) {
			jpaQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}" + tCallPlanPrd.getTCustCallPlan());

			jpaQuery.bind(TCUSTCALLPLAN, tCallPlanPrd.getTCustCallPlan());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCallPlanPrds.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCallPlanPrds(final SearchFilter<TCallPlanPrd> searchFilter) {
		LOGGER.info("=========== Count TCallPlanPrd ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCallPlanPrd tCallPlanPrd = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCallPlanPrdentity", tCallPlanPrd);

		if (tCallPlanPrd.getTCustPrdAlgmnt() == null) {
			jpaCountQuery.bind(TCUSTPRDALGMNT, new TCustPrdAlgmnt());
		} else {
			LOGGER.info("tCustPrdAlgmnt {}" + tCallPlanPrd.getTCustPrdAlgmnt());

			jpaCountQuery.bind(TCUSTPRDALGMNT, tCallPlanPrd.getTCustPrdAlgmnt());
		}

		if (tCallPlanPrd.getTCustCallPlan() == null) {
			jpaCountQuery.bind(TCUSTCALLPLAN, new TCustCallPlan());
		} else {
			LOGGER.info("tCustCallPlan {}" + tCallPlanPrd.getTCustCallPlan());

			jpaCountQuery.bind(TCUSTCALLPLAN, tCallPlanPrd.getTCustCallPlan());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallPlanPrd> getTCallPlanPrdsByTCustPrdAlgmnt(final SearchFilter<TCustPrdAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustPrdAlgmnt tCustPrdAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustPrdAlgmntList = new ArrayList<Object>();
		tCustPrdAlgmntList.add(tCustPrdAlgmnt);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTCallPlanPrdByTCustPrdAlgmnt", tCustPrdAlgmntList, index, maxresult);
	}

	/**
	 * Count TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallPlanPrdsByTCustPrdAlgmnt(final SearchFilter<TCustPrdAlgmnt> searchFilter) {

		final TCustPrdAlgmnt tCustPrdAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustPrdAlgmntList = new ArrayList<Object>();
		tCustPrdAlgmntList.add(tCustPrdAlgmnt);
		return genericDAO.findEntitiesByNamedQuery("CountTCallPlanPrdsByTCustPrdAlgmnt", tCustPrdAlgmntList);
	}

	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrds if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCallPlanPrd> getTCallPlanPrdsByTCustCallPlan(final SearchFilter<TCustCallPlan> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		tCustCallPlanList.add(tCustCallPlan);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCallPlanPrdByTCustCallPlan", tCustCallPlanList, index, maxresult);
	}

	/**
	 * Count TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCustCallPlan type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCallPlanPrdsByTCustCallPlan(final SearchFilter<TCustCallPlan> searchFilter) {

		final TCustCallPlan tCustCallPlan = searchFilter.getEntityClass();
		List<Object> tCustCallPlanList = new ArrayList<Object>();
		tCustCallPlanList.add(tCustCallPlan);
		return genericDAO.findEntitiesByNamedQuery("CountTCallPlanPrdsByTCustCallPlan", tCustCallPlanList);
	}
	/**
	 * Retrieve TCallPlanPrd based on given search criteria using JPA named Query.
	 * The search criteria is of TCallPlanPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCallPlanPrd> list of TCallPlanPrdByJointStmt if it exists against given
	 *         criteria.
	 */
	public List<TCallPlanPrd> findTCallPlanPrdByJointStmt(Long salesPosId, Short tenantId){
	
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(salesPosId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTCallPlanPrdByJointStmt", paramList, 0, -1);
		
	}
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CustomerCallPlanProducts type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return object of TCallPlanPrdByTCustCallPlanFromFetch if it exists against given
	 *         criteria.
	 */
	
	public List<Object[]> findCustomerCallPlanProducts(Long custCallPlanId,Short tenantId){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custCallPlanId.intValue());
		paramList.add(tenantId);
				
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTCallPlanPrdByTCustCallPlanFromFetch", paramList, 0, -1);
		
	}
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CallPlanProduct type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 *  @return List<TCallPlanPrd> list of CallPlanProdFromCustAlignment if it exists against given
	 *         criteria.
	 */
	public List<TCallPlanPrd> findCallPlanProduct(Long alignmentId,String description,Long prdId,Short tenantId)
	{
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(alignmentId);
		paramList.add(description);
		paramList.add(prdId);		
		paramList.add(tenantId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCallPlanProdFromCustAlignment", paramList, 0, -1);
	}
	
	/**
	 * Retrieve object based on given search criteria using JPA named Query.
	 * The search criteria is of CallPlanPrdsByPrdId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 *  @return List<TCallPlanPrd> list of CallPlanPrdsByPrdId if it exists against given
	 *         criteria.
	 */
	public List<TCallPlanPrd> findTCallPlanPrdsByPrdId(final Long prdId)
	{
		List<Object> prdIdList = new ArrayList<Object>();
		prdIdList.add(prdId);
	return genericDAO.findEntitiesByNamedQuery("getCallPlanPrdsByPrdId", prdIdList);
	}


}
