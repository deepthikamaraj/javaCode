package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAttr;
import com.cognizant.opserv.sp.core.entity.TCustAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAttrDAO")
public class TCustAttrDAOImpl implements TCustAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUST = "tCust";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TCustAttr> clazz;

	public Class<TCustAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAttrDAOImpl() {
		super();
		this.clazz = TCustAttr.class;
	}

	private static final String JPAQL = "select tCustAttrentity from TCustAttr tCustAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TCustAttr tCustAttrentity";

	private static final String[] RESTRICTIONS = {
			"tCustAttrentity.tCustAttrId.custId = #{tCustAttrentity.tCustAttrId.getCustId}",
			"tCustAttrentity.tCustAttrId.attrId = #{tCustAttrentity.tCustAttrId.getAttrId}",
			"tCustAttrentity.activeFlag = #{tCustAttrentity.getActiveFlag}",
			"tCustAttrentity.attrValue like '#{tCustAttrentity.getAttrValue}%'",
			"tCustAttrentity.createdBy = #{tCustAttrentity.getCreatedBy}",
			"Date(tCustAttrentity.createDt) = '#{tCustAttrentity.getCreateDt}'",
			"tCustAttrentity.updatedBy = #{tCustAttrentity.getUpdatedBy}",
			"Date(tCustAttrentity.updateDt) = '#{tCustAttrentity.getUpdateDt}'",
			"tCustAttrentity.tenantId = #{tCustAttrentity.getTenantId}",
			"tCustAttrentity.tCust.custId = #{tCustAttrentity.tCust.getCustId}",
			"tCustAttrentity.tAttrDef.attrId = #{tCustAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TCustAttr entity object in to the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be persisted
	 * @return tCustAttr Persisted TCustAttr object
	 */
	public TCustAttr createTCustAttr(final TCustAttr tCustAttr) {
		LOGGER.info("=========== Create TCustAttr ===========");
		return genericDAO.store(tCustAttr);
	}

	/**
	 * Deletes a TCustAttr entity object from the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be deleted
	 */
	public void deleteTCustAttr(final TCustAttrId tCustAttrId) {
		LOGGER.info("=========== Delete TCustAttr ===========");
		final TCustAttr tCustAttr = genericDAO.get(clazz, tCustAttrId);
		genericDAO.remove(tCustAttr);
	}

	/**
	 * Updates a TCustAttr entity object in to the persistent store
	 * 
	 * @param tCustAttr
	 *            TCustAttr Entity object to be updated
	 * @return tCustAttr Persisted TCustAttr object
	 */
	public TCustAttr updateTCustAttr(final TCustAttr tCustAttr) {
		LOGGER.info("=========== Update TCustAttr ===========");
		return genericDAO.update(tCustAttr);
	}

	/**
	 * Retrieve an TCustAttr object based on given TCustAttr TCustAttrId.
	 * 
	 * @param tCustAttrId
	 *            the primary key value of the TCustAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAttr findTCustAttrById(final TCustAttrId tCustAttrId) {
		LOGGER.info("find TCustAttr instance with TCustAttrId: " + tCustAttrId);
		return genericDAO.get(clazz, tCustAttrId);
	}

	/**
	 * Retrieve TCustAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAttr> findTCustAttrs(final SearchFilter<TCustAttr> searchFilter) {
		LOGGER.info("=========== Find TCustAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAttr tCustAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAttrentity", tCustAttr);

		if (tCustAttr.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}" + tCustAttr.getTCust());

			jpaQuery.bind(TCUST, tCustAttr.getTCust());
		}

		if (tCustAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}" + tCustAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tCustAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAttrs(final SearchFilter<TCustAttr> searchFilter) {
		LOGGER.info("=========== Count TCustAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAttr tCustAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAttrentity", tCustAttr);

		if (tCustAttr.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}"+ tCustAttr.getTCust());

			jpaCountQuery.bind(TCUST, tCustAttr.getTCust());
		}

		if (tCustAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tCustAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tCustAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAttr> getTCustAttrsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAttrByTCust", tCustList, index, maxresult);
	}

	/**
	 * Count TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAttrsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAttrsByTCust", tCustList);
	}

	/**
	 * Retrieve TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAttr> list of TCustAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAttr> getTCustAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAttrByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TCustAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAttrsByTAttrDef", tAttrDefList);
	}
	 /**
 	 * Gets the t cust attr by id.
 	 *
 	 * @param searchFilter the search filter
 	 * @return the list
 	 */
	@Override
	public List<TCustAttr> GetTCustAttrById(SearchFilter<TCustAttr> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAttr tCustAttr = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tCustAttr.getTCustAttrId().getCustId());
		paramList.add(tCustAttr.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAttrById",paramList, 0, -1);
	}
	
	/**
	 *  Find custattributes by custid
	 *
	 * @param custId the cust id
	 * @return the t cust attr by cust id
	 */
	public List<TCustAttr> getTCustAttrByCustId(Integer custId) {
		List paramList = new ArrayList();
		
		paramList.add(custId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustAttrsByCustId",paramList, 0, -1);
	}

	/**
	 * Find cust ext attr by grp.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param custId the cust id
	 * @param tenantId the tenant id
	 * @param custBuName the cust bu name
	 * @return the list
	 */
	@Override
	public List findCustExtAttrByGrp(Long algmntId,String bussUnitId,Long salesTeamId,Integer custId,short tenantId,String custBUName) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custBUName);
		paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(custId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAttrByTAttGrpMap", paramList,0,-1);
	}
	/**
	 * Gets the t cust attr by attr id.
	 *
	 * @param tCustAttr the t cust attr
	 * @return the t cust attr by attr id
	 */
	@Override
	public List<TCustAttr> getTCustAttrByAttrId(
			TCustAttr tCustAttr) {
		List paramList = new ArrayList();
		
		paramList.add(tCustAttr.getTAttrDef().getAttrId());
		paramList.add(tCustAttr.getAttrValue());
		paramList.add(tCustAttr.getTenantId());
		paramList.add(tCustAttr.getTCustAttrId().getCustId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAttrByAttrId",paramList,0,-1);
	}
	/**
	 * Gets the t cust attr by attr id list.
	 *
	 * @param custIdList the cust id list
	 * @param tenantId the tenant id
	 * @return the t cust attr by attr id list
	 */
	@Override
	public List<TCustAttr> getTCustAttrByAttrIdList(
			List<Integer> custIdList, Short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(custIdList);
		paramList.add(tenantId);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAttrByAttrIdList",paramList, 0, -1);
	}
	/**
	 * Gets the t cust attr by attr id list.
	 *
	 * @param custIdList the cust id list
	 * @param attrId the attr id
	 * @param attrVal the attr val
	 * @return the t cust attr by attr id list
	 */
	@Override
	public List<Integer> getTCustAttrByAttrIdList(List<Integer> custIdList, Long attrId,String attrVal) {		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIdList);
		paramList.add(attrId);
		paramList.add("%"+attrVal+"%");
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAttrByAttrIdValCust",paramList, 0, -1);
	}
	/**
	 * Find cust ext attr names.
	 *
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List findCustExtAttrNames(Long algmntId,String bussUnitId,Long salesTeamId,short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add("Sales Position - Customer");
		paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAttrName", paramList,0,-1);
	}
	/**
	 * Find all attrs for cust.
	 *
	 * @param custId the cust id
	 * @param attrIds the attr ids
	 * @return the list
	 */
	@Override
	public List<TCustAttr> findAllAttrsForCust(Integer custId,
			List<Long> attrIds) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);
		paramList.add(attrIds);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllAttrsForCust",paramList, 0, -1);
	}
	/**
	 * Find cust attrs for cust.
	 *
	 * @param custId the cust id
	 * @param attrIds the attr ids
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findCustAttrsForCust(Integer custId,
			List<Long> attrIds,Character activeFlag, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);
		paramList.add(attrIds);
		paramList.add(activeFlag);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindCustAttrsForCust",paramList, 0, -1);
	}
	/**
	 * Find all attrs for all cust.
	 *
	 * @param custIds the cust ids
	 * @param custAttrIds the cust attr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAttr> findAllAttrsForAllCust(List<Integer> custIds,List<Long> custAttrIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(custAttrIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllAttrsForAllCust",paramList, 0, -1);
	}
	/**
	 * Update t cust attr.
	 *
	 * @param tCustAttrs the t cust attrs
	 * @return the list
	 */
	@Override
	public List<TCustAttr> updateTCustAttr(List<TCustAttr> tCustAttrs) {
		return genericDAO.update(tCustAttrs);
	}
	/**
	 * Creates the t cust attr.
	 *
	 * @param tCustAttrs the t cust attrs
	 * @return the list
	 */
	@Override
	public List<TCustAttr> createTCustAttr(List<TCustAttr> tCustAttrs) {
		return genericDAO.storeBatch(tCustAttrs);
	}
	
	/**
	 * Find t cust attr by t att grp map and cust id.
	 *
	 * @param custId the cust id
	 * @param algmntId the algmnt id
	 * @param bussUnitId the buss unit id
	 * @param salesTeamId the sales team id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTCustAttrByTAttGrpMapAndCustID(List<Integer> custId ,Long algmntId,String bussUnitId,Long salesTeamId,short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(custId);
		/*paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);*/
		paramList.add(tenantId);
		List<Object[]> custAttrList = genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTCustAttrByTAttGrpMapAndCustID", paramList,0,-1);
		return custAttrList;
	}
	/**
	 * Find all ext attrs for all cust.
	 *
	 * @param custIds the cust ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAttr> findAllExtAttrsForAllCust(List<Integer> custIds,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllExtAttrsForAllCust",paramList, 0, -1);
	}
	
}
