package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmntId;


import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;
//import com.cognizant.opserv.sp.core.util.DateUtil;

/**
 * Class provides API implementation for TCustPrdAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustPrdAlgmntDAO")
public class TCustPrdAlgmntDAOImpl implements TCustPrdAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustPrdAlgmntDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCustPrdAlgmnt> clazz;

	public Class<TCustPrdAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustPrdAlgmntDAOImpl() {
		super();
		this.clazz = TCustPrdAlgmnt.class;
	}

	private static final String JPAQL = "select tCustPrdAlgmntentity from TCustPrdAlgmnt tCustPrdAlgmntentity";

	private static final String JPACOUNTQL = "select count(*) from TCustPrdAlgmnt tCustPrdAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tCustPrdAlgmntentity.tCustPrdAlgmntId.prdAlgmntId = #{tCustPrdAlgmntentity.tCustPrdAlgmntId.getPrdAlgmntId}",
			"tCustPrdAlgmntentity.tCustPrdAlgmntId.custAlgmntId = #{tCustPrdAlgmntentity.tCustPrdAlgmntId.getCustAlgmntId}",
			"tCustPrdAlgmntentity.activeFlag = #{tCustPrdAlgmntentity.getActiveFlag}",
			"tCustPrdAlgmntentity.createdBy = #{tCustPrdAlgmntentity.getCreatedBy}",
			"Date(tCustPrdAlgmntentity.createDt) = '#{tCustPrdAlgmntentity.getCreateDt}'",
			"tCustPrdAlgmntentity.updatedBy = #{tCustPrdAlgmntentity.getUpdatedBy}",
			"Date(tCustPrdAlgmntentity.updateDt) = '#{tCustPrdAlgmntentity.getUpdateDt}'",
			"tCustPrdAlgmntentity.classTypeId = #{tCustPrdAlgmntentity.getClassTypeId}",
			"tCustPrdAlgmntentity.compFlag = #{tCustPrdAlgmntentity.getCompFlag}",
			"tCustPrdAlgmntentity.allocPct = #{tCustPrdAlgmntentity.getAllocPct}",
			"tCustPrdAlgmntentity.classValue like '#{tCustPrdAlgmntentity.getClassValue}%'",
			"Date(tCustPrdAlgmntentity.effStartDt) = '#{tCustPrdAlgmntentity.getEffStartDt}'",
			"Date(tCustPrdAlgmntentity.effEndDt) = '#{tCustPrdAlgmntentity.getEffEndDt}'",
			"Date(tCustPrdAlgmntentity.elgEffStartDt) = '#{tCustPrdAlgmntentity.getElgEffStartDt}'",
			"Date(tCustPrdAlgmntentity.elgEffEndDt) = '#{tCustPrdAlgmntentity.getElgEffEndDt}'",
			"tCustPrdAlgmntentity.tenantId = #{tCustPrdAlgmntentity.getTenantId}" };

	/**
	 * Stores a new TCustPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be persisted
	 * @return tCustPrdAlgmnt Persisted TCustPrdAlgmnt object
	 */
	public TCustPrdAlgmnt createTCustPrdAlgmnt(final TCustPrdAlgmnt tCustPrdAlgmnt) {
		LOGGER.info("=========== Create TCustPrdAlgmnt ===========");
		return genericDAO.store(tCustPrdAlgmnt);
	}

	/**
	 * Deletes a TCustPrdAlgmnt entity object from the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be deleted
	 */
	public void deleteTCustPrdAlgmnt(final TCustPrdAlgmntId tCustPrdAlgmntId) {
		LOGGER.info("=========== Delete TCustPrdAlgmnt ===========");
		final TCustPrdAlgmnt tCustPrdAlgmnt = genericDAO.get(clazz, tCustPrdAlgmntId);
		genericDAO.remove(tCustPrdAlgmnt);
	}

	/**
	 * Updates a TCustPrdAlgmnt entity object in to the persistent store
	 * 
	 * @param tCustPrdAlgmnt
	 *            TCustPrdAlgmnt Entity object to be updated
	 * @return tCustPrdAlgmnt Persisted TCustPrdAlgmnt object
	 */
	public TCustPrdAlgmnt updateTCustPrdAlgmnt(final TCustPrdAlgmnt tCustPrdAlgmnt) {
		LOGGER.info("=========== Update TCustPrdAlgmnt ===========");
		return genericDAO.update(tCustPrdAlgmnt);
	}

	/**
	 * Retrieve an TCustPrdAlgmnt object based on given TCustPrdAlgmnt TCustPrdAlgmntId.
	 * 
	 * @param tCustPrdAlgmntId
	 *            the primary key value of the TCustPrdAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustPrdAlgmnt findTCustPrdAlgmntById(final TCustPrdAlgmntId tCustPrdAlgmntId) {
		LOGGER.info("find TCustPrdAlgmnt instance with TCustPrdAlgmntId: " + tCustPrdAlgmntId);
		return genericDAO.get(clazz, tCustPrdAlgmntId);
	}

	/**
	 * Retrieve TCustPrdAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustPrdAlgmnt> list of TCustPrdAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustPrdAlgmnt> findTCustPrdAlgmnts(final SearchFilter<TCustPrdAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TCustPrdAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustPrdAlgmnt tCustPrdAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustPrdAlgmntentity", tCustPrdAlgmnt);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustPrdAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustPrdAlgmnts(final SearchFilter<TCustPrdAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TCustPrdAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustPrdAlgmnt tCustPrdAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustPrdAlgmntentity", tCustPrdAlgmnt);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find customer products by sale pos id.
	 *
	 * @param custId the cust id
	 * @param salesPosId the sales pos id
	 * @param index the index
	 * @param noOfResult the no of result
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TCustPrdAlgmnt> findCustomerProductsBySalePosId(Long custId,
			Long salesPosId,int index, int noOfResult, Short tenantId,boolean flag) {
		Date today = new Date();
		List paramList = new ArrayList();
		List<TCustPrdAlgmnt> list = null;
		if(flag){
		paramList.add(Integer.valueOf(custId.toString()));
		paramList.add(tenantId);
		paramList.add(salesPosId);
		paramList.add(tenantId);
		paramList.add(today);



		list = genericDAO.findEntitiesByNamedQueryMultiCond(
				"GetAllTCustPrdsAlgmntBySPIdCustId", paramList, index, noOfResult);
		}else{
			paramList.add(salesPosId);
			paramList.add(tenantId);
			paramList.add(tenantId);
			paramList.add(today);
			paramList.add(today);




			list = genericDAO.findEntitiesByNamedQueryMultiCond(
					"FindAllTCustPrdsAlgmntsBySalesPosId", paramList, index, noOfResult);
		}

		return list;
	}
	/**
	 * Find customer product list by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TCustPrdAlgmnt> findCustomerProductListBySalesPosId(
			Long salesPosId, Short tenantId) {
		// TODO Auto-generated method stub
		//Date today = new Date();
		List paramList = new ArrayList();
		List<TCustPrdAlgmnt> custPrdAlignList = null;
		paramList.add(salesPosId);
		paramList.add(tenantId);
		paramList.add(tenantId);
		//paramList.add(today);
		//paramList.add(today);
		custPrdAlignList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustPrdsAlgmntsBySalesPosIdWithoutDate",paramList,0,-1);
		
		return custPrdAlignList;
	}
	/**
	 * Fetch t cust prd algmnt by cust al id.
	 *
	 * @param custALId the cust al id
	 * @return the list
	 */
	@Override
	public List<TCustPrdAlgmnt> fetchTCustPrdAlgmntByCustALId(Long custALId,Character activeFlag) {
		List paramList = new ArrayList();
		List<TCustPrdAlgmnt> custPrdAlignList = null;
		paramList.add(custALId);
		paramList.add(activeFlag);
		custPrdAlignList = genericDAO.findEntitiesByNamedQueryMultiCond("fetchTCustPrdAlgmntByCustALId",paramList,0,-1);
		
		return custPrdAlignList;
	}

	@Override
	public List<TCustPrdAlgmnt> populateCustPrdAlgmntByCustAlId(List<Long> custALIdList,
			Short tenantId) {
		List paramList = new ArrayList();
		List<TCustPrdAlgmnt> custPrdAlgmtList = null;
		paramList.add(custALIdList);
		paramList.add(tenantId);
		custPrdAlgmtList = genericDAO.findEntitiesByNamedQueryMultiCond("fetchTCustPrdAlgmntByCustAlIds",paramList,0,-1);
		
		return custPrdAlgmtList;
	}

}

