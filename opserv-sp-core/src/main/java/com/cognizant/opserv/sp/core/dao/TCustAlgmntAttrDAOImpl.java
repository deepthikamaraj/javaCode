package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAlgmntAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAlgmntAttrDAO")
public class TCustAlgmntAttrDAOImpl implements TCustAlgmntAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAlgmntAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTALGMNT = "tCustAlgmnt";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TCustAlgmntAttr> clazz;

	public Class<TCustAlgmntAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAlgmntAttrDAOImpl() {
		super();
		this.clazz = TCustAlgmntAttr.class;
	}

	private static final String JPAQL = "select tCustAlgmntAttrentity from TCustAlgmntAttr tCustAlgmntAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TCustAlgmntAttr tCustAlgmntAttrentity";

	private static final String[] RESTRICTIONS = {
			"tCustAlgmntAttrentity.tCustAlgmntAttrId.custAlgmntId = #{tCustAlgmntAttrentity.tCustAlgmntAttrId.getCustAlgmntId}",
			"tCustAlgmntAttrentity.tCustAlgmntAttrId.attrId = #{tCustAlgmntAttrentity.tCustAlgmntAttrId.getAttrId}",
			"tCustAlgmntAttrentity.attrValue like '#{tCustAlgmntAttrentity.getAttrValue}%'",
			"tCustAlgmntAttrentity.activeFlag = #{tCustAlgmntAttrentity.getActiveFlag}",
			"tCustAlgmntAttrentity.createdBy = #{tCustAlgmntAttrentity.getCreatedBy}",
			"Date(tCustAlgmntAttrentity.createDt) = '#{tCustAlgmntAttrentity.getCreateDt}'",
			"tCustAlgmntAttrentity.updatedBy = #{tCustAlgmntAttrentity.getUpdatedBy}",
			"Date(tCustAlgmntAttrentity.updateDt) = '#{tCustAlgmntAttrentity.getUpdateDt}'",
			"tCustAlgmntAttrentity.tenantId = #{tCustAlgmntAttrentity.getTenantId}",
			"tCustAlgmntAttrentity.tCustAlgmnt.custAlgmntId = #{tCustAlgmntAttrentity.tCustAlgmnt.getCustAlgmntId}",
			"tCustAlgmntAttrentity.tAttrDef.attrId = #{tCustAlgmntAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TCustAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be persisted
	 * @return tCustAlgmntAttr Persisted TCustAlgmntAttr object
	 */
	public TCustAlgmntAttr createTCustAlgmntAttr(final TCustAlgmntAttr tCustAlgmntAttr) {
		LOGGER.info("=========== Create TCustAlgmntAttr ===========");
		return genericDAO.store(tCustAlgmntAttr);
	}

	/**
	 * Deletes a TCustAlgmntAttr entity object from the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be deleted
	 */
	public void deleteTCustAlgmntAttr(final TCustAlgmntAttrId tCustAlgmntAttrId) {
		LOGGER.info("=========== Delete TCustAlgmntAttr ===========");
		final TCustAlgmntAttr tCustAlgmntAttr = genericDAO.get(clazz, tCustAlgmntAttrId);
		genericDAO.remove(tCustAlgmntAttr);
	}

	/**
	 * Updates a TCustAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAttr
	 *            TCustAlgmntAttr Entity object to be updated
	 * @return tCustAlgmntAttr Persisted TCustAlgmntAttr object
	 */
	public TCustAlgmntAttr updateTCustAlgmntAttr(final TCustAlgmntAttr tCustAlgmntAttr) {
		LOGGER.info("=========== Update TCustAlgmntAttr ===========");
		return genericDAO.update(tCustAlgmntAttr);
	}

	/**
	 * Retrieve an TCustAlgmntAttr object based on given TCustAlgmntAttr TCustAlgmntAttrId.
	 * 
	 * @param tCustAlgmntAttrId
	 *            the primary key value of the TCustAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAlgmntAttr findTCustAlgmntAttrById(final TCustAlgmntAttrId tCustAlgmntAttrId) {
		LOGGER.info("find TCustAlgmntAttr instance with TCustAlgmntAttrId: " + tCustAlgmntAttrId);
		return genericDAO.get(clazz, tCustAlgmntAttrId);
	}

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAlgmntAttr> findTCustAlgmntAttrs(final SearchFilter<TCustAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Find TCustAlgmntAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAlgmntAttr tCustAlgmntAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAlgmntAttrentity", tCustAlgmntAttr);

		if (tCustAlgmntAttr.getTCustAlgmnt() == null) {
			jpaQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustAlgmntAttr.getTCustAlgmnt());

			jpaQuery.bind(TCUSTALGMNT, tCustAlgmntAttr.getTCustAlgmnt());
		}

		if (tCustAlgmntAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}" + tCustAlgmntAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tCustAlgmntAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAlgmntAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAlgmntAttrs(final SearchFilter<TCustAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Count TCustAlgmntAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAlgmntAttr tCustAlgmntAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAlgmntAttrentity", tCustAlgmntAttr);

		if (tCustAlgmntAttr.getTCustAlgmnt() == null) {
			jpaCountQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}" + tCustAlgmntAttr.getTCustAlgmnt());

			jpaCountQuery.bind(TCUSTALGMNT, tCustAlgmntAttr.getTCustAlgmnt());
		}

		if (tCustAlgmntAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}" + tCustAlgmntAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tCustAlgmntAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrsByTCustAlgmnt(final SearchFilter<TCustAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustAlgmntList = new ArrayList<Object>();
		tCustAlgmntList.add(tCustAlgmnt);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAttrByTCustAlgmnt", tCustAlgmntList, index, maxresult);
	}

	/**
	 * Count TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntAttrsByTCustAlgmnt(final SearchFilter<TCustAlgmnt> searchFilter) {

		final TCustAlgmnt tCustAlgmnt = searchFilter.getEntityClass();
		List<Object> tCustAlgmntList = new ArrayList<Object>();
		tCustAlgmntList.add(tCustAlgmnt);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntAttrsByTCustAlgmnt", tCustAlgmntList);
	}

	/**
	 * Retrieve TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAttr> list of TCustAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAttrByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TCustAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntAttrsByTAttrDef", tAttrDefList);
	}
	
	/**
	 * Gets the t cust algmnt attrs by attr.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @param attrId the attr id
	 * @param tenantId the tenant id
	 * @return the t cust algmnt attrs by attr
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrsByAttr(long custAlgmntId, Long attrId, short tenantId){
		List queryParam = new ArrayList();
		queryParam.add(custAlgmntId);
		queryParam.add(attrId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAttrByAttrId", queryParam, 0, -1);
	}
	
	/**
	 * This method returns all custalgmnt attributes based on custalignment id
	 */
	public List<TCustAlgmntAttr> getTCustAlgmntAttrsByCustAlgmntId(long custAlgmntId)
	{
		List queryParam = new ArrayList();		
		queryParam.add(custAlgmntId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustAlgmntAttrsByCustAlgmntId", queryParam, 0, -1);
		
	}
	
	/**
	 * Gets the t cust algmnt attr by id.
	 *
	 * @param searchFilter the search filter
	 * @return the t cust algmnt attr by id
	 */
	@Override
	public List<TCustAlgmntAttr> getTCustAlgmntAttrById(SearchFilter<TCustAlgmntAttr> searchFilter) {
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAlgmntAttr tCustAlgmntAttr = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tCustAlgmntAttr.getTCustAlgmntAttrId().getCustAlgmntId());
		paramList.add(tCustAlgmntAttr.getTenantId());
		 List<TCustAlgmntAttr> tCustAlgmntAttrs = genericDAO.findEntitiesByNamedQueryMultiCond("getTCustAlgmntAttrById",paramList, 0, -1);
		 return tCustAlgmntAttrs;
	}
	/**
	 * Gets the t cust algmnt attr by attr id.
	 *
	 * @param tCustAlgmntAttr the t cust algmnt attr
	 * @return the t cust algmnt attr by attr id
	 */
	@Override
	public List<TCustAlgmntAttr> getTCustAlgmntAttrByAttrId(
			TCustAlgmntAttr tCustAlgmntAttr) {
		List paramList = new ArrayList();
		
		paramList.add(tCustAlgmntAttr.getTAttrDef().getAttrId());
		paramList.add(tCustAlgmntAttr.getAttrValue());
		paramList.add(tCustAlgmntAttr.getTenantId());
		paramList.add(tCustAlgmntAttr.getTCustAlgmntAttrId().getCustAlgmntId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAlgmntAttrByAttrId",paramList,0,-1);
	}
	
	/**
	 * Gets the t cust algmnt attr by id list.
	 *
	 * @param custAlgmntIdList the cust algmnt id list
	 * @param tenantId the tenant id
	 * @return the t cust algmnt attr by id list
	 */
	@Override
	public List<TCustAlgmntAttr> getTCustAlgmntAttrByIdList(List<Long> custAlgmntIdList, Short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(custAlgmntIdList);
		paramList.add(tenantId);
		 List<TCustAlgmntAttr> tCustAlgmntAttrs = genericDAO.findEntitiesByNamedQueryMultiCond("GetTCustAlgmntAttrByIdList",paramList, 0, -1);
		 return tCustAlgmntAttrs;
	}
	/**
	 * Find all attrs for cust algmnt.
	 *
	 * @param custAlgmntId the cust algmnt id
	 * @param attrIds the attr ids
	 * @return the list
	 */
	@Override
	public List<TCustAlgmntAttr> findAllAttrsForCustAlgmnt(Long custAlgmntId,
			List<Long> attrIds) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntId);
		paramList.add(attrIds);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllAttrsForCustAlgmnt",paramList, 0, -1);
	}

	/**
	 * Find all attrs for all cust algmnt.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param attrIds the attr ids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TCustAlgmntAttr> findAllAttrsForAllCustAlgmnt(
			List<Long> custAlgmntIds,List<Long> attrIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntIds);
		paramList.add(attrIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllAttrsForAllCustAlgmnt",paramList, 0, -1);
	}
	/**
	 * Update t cust algmnt attrs.
	 *
	 * @param tCustAlgmntAttrs the t cust algmnt attrs
	 * @return the list
	 */
	@Override
	public List<TCustAlgmntAttr> updateTCustAlgmntAttrs(
			List<TCustAlgmntAttr> tCustAlgmntAttrs) {
		return genericDAO.update(tCustAlgmntAttrs);
	}
	/**
	 * Store t cust algmnt attrs.
	 *
	 * @param tCustAlgmntAttrs the t cust algmnt attrs
	 * @return the list
	 */
	@Override
	public List<TCustAlgmntAttr> storeTCustAlgmntAttrs(
			List<TCustAlgmntAttr> tCustAlgmntAttrs) {
		return genericDAO.storeBatch(tCustAlgmntAttrs);
	}
	
}
