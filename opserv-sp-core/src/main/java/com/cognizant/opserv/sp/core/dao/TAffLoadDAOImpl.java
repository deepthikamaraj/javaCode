package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAffLoad;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAffLoadDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAffLoadDAO")
public class TAffLoadDAOImpl implements TAffLoadDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAffLoadDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAffLoad> clazz;

	public Class<TAffLoad> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAffLoadDAOImpl() {
		super();
		this.clazz = TAffLoad.class;
	}

	private static final String JPAQL = "select tAffLoadentity from TAffLoad tAffLoadentity";

	private static final String JPACOUNTQL = "select count(tAffLoadentity) from TAffLoad tAffLoadentity";

	private static final String[] RESTRICTIONS = { "tAffLoadentity.affId = #{tAffLoadentity.getAffId}",
			"tAffLoadentity.custId = #{tAffLoadentity.getCustId}",
			"tAffLoadentity.affCustId = #{tAffLoadentity.getAffCustId}",
			"tAffLoadentity.custCd like '#{tAffLoadentity.getCustCd}%'",
			"tAffLoadentity.affCustCd like '#{tAffLoadentity.getAffCustCd}%'",
			"tAffLoadentity.activeFlag = #{tAffLoadentity.getActiveFlag}",
			"tAffLoadentity.createdBy = #{tAffLoadentity.getCreatedBy}",
			"Date(tAffLoadentity.createDt) = '#{tAffLoadentity.getCreateDt}'",
			"tAffLoadentity.updatedBy = #{tAffLoadentity.getUpdatedBy}",
			"Date(tAffLoadentity.updateDt) = '#{tAffLoadentity.getUpdateDt}'",
			"tAffLoadentity.tenantId = #{tAffLoadentity.getTenantId}" };

	/**
	 * Stores a new TAffLoad entity object in to the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be persisted
	 * @return tAffLoad Persisted TAffLoad object
	 */
	public TAffLoad createTAffLoad(final TAffLoad tAffLoad) {
		LOGGER.info("=========== Create TAffLoad ===========");
		return genericDAO.store(tAffLoad);
	}

	/**
	 * Deletes a TAffLoad entity object from the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be deleted
	 */
	public void deleteTAffLoad(final Long affId) {
		LOGGER.info("=========== Delete TAffLoad ===========");
		final TAffLoad tAffLoad = genericDAO.get(clazz, affId);
		genericDAO.remove(tAffLoad);
	}

	/**
	 * Updates a TAffLoad entity object in to the persistent store
	 * 
	 * @param tAffLoad
	 *            TAffLoad Entity object to be updated
	 * @return tAffLoad Persisted TAffLoad object
	 */
	public TAffLoad updateTAffLoad(final TAffLoad tAffLoad) {
		LOGGER.info("=========== Update TAffLoad ===========");
		return genericDAO.update(tAffLoad);
	}

	/**
	 * Retrieve an TAffLoad object based on given TAffLoad affId.
	 * 
	 * @param tAffLoadId
	 *            the primary key value of the TAffLoad Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAffLoad findTAffLoadById(final Long tAffLoadId) {
		LOGGER.info("find TAffLoad instance with affId: " + tAffLoadId);
		return genericDAO.get(clazz, tAffLoadId);
	}

	/**
	 * Retrieve TAffLoad based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffLoad> list of TAffLoad if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAffLoad> findTAffLoads(final SearchFilter<TAffLoad> searchFilter) {
		LOGGER.info("=========== Find TAffLoads ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAffLoad tAffLoad = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAffLoadentity", tAffLoad);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAffLoads.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAffLoads(final SearchFilter<TAffLoad> searchFilter) {
		LOGGER.info("=========== Count TAffLoad ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAffLoad tAffLoad = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAffLoadentity", tAffLoad);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Gets the affiliated cust ids by cust id.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the affiliated cust ids by cust id
	 */
	@Override
	public List<Object[]> getAffiliatedCustIdsByCustId(Integer custId, Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);				
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetParentAffiliatedCustIdsByCustId", paramList, 0, -1);
		return list;
	}
	/**
	 * Gets the child affiliated cust ids by cust id.
	 *
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the child affiliated cust ids by cust id
	 */
	@Override
	public List<Object[]> getChildAffiliatedCustIdsByCustId(Integer custId, Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);				
		paramList.add(tenantId);
		paramList.add(flag);
		List<Object[]> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetChildAffiliatedCustIdsByCustId", paramList, 0, -1);
		return list;
	}
	

	/**
	 * Check t cust aff heir fr cust.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> checkTCustAffHeirFrCust(List<Integer> custIdList, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(custIdList);
		queryParam.add(tenantId);
		
		List<Object[]> custAffDtlList  = genericDAO.findEntitiesByNamedQueryMultiCond("checkTCustAffHeirFrCustList", queryParam, 0, -1);
		return custAffDtlList;
	}

	/**
	 * This DAO method fetches date vlaue of SP by SpId from DB Function
	 * @param salesPosId
	 * @return SP EffEnd Date
	 */
	@Override
	public String inactivateAffCustomers(String tempTableName,String procedureName){
		List<Object> status =  genericDAO.findByNativeQuery("call "+procedureName+"('"+tempTableName+"')");
		return (status!=null&&status.size()>0)?status.get(0).toString():"NO STATUS";
	}

	/**
	 * Creates the or drop temp table.
	 *
	 * @param tableInfo the table info
	 * @return the integer
	 */
	@Override
	public Integer createOrDropTempTable(String tableInfo){
		return genericDAO.executeNativeQuery(tableInfo);
	}
}
