package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosAttr;
import com.cognizant.opserv.sp.core.entity.TSalesPosAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosAttrDAO")
public class TSalesPosAttrDAOImpl implements TSalesPosAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESPOS = "tSalesPos";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TSalesPosAttr> clazz;

	public Class<TSalesPosAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosAttrDAOImpl() {
		super();
		this.clazz = TSalesPosAttr.class;
	}

	private static final String JPAQL = "select tSalesPosAttrentity from TSalesPosAttr tSalesPosAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPosAttr tSalesPosAttrentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosAttrentity.tSalesPosAttrId.salesPosId = #{tSalesPosAttrentity.tSalesPosAttrId.getSalesPosId}",
			"tSalesPosAttrentity.tSalesPosAttrId.attrId = #{tSalesPosAttrentity.tSalesPosAttrId.getAttrId}",
			"tSalesPosAttrentity.tSalesPosAttrId.salesHierId = #{tSalesPosAttrentity.tSalesPosAttrId.getSalesHierId}",
			"tSalesPosAttrentity.activeFlag = #{tSalesPosAttrentity.getActiveFlag}",
			"tSalesPosAttrentity.attrValue like '#{tSalesPosAttrentity.getAttrValue}%'",
			"tSalesPosAttrentity.createdBy = #{tSalesPosAttrentity.getCreatedBy}",
			"Date(tSalesPosAttrentity.createDt) = '#{tSalesPosAttrentity.getCreateDt}'",
			"tSalesPosAttrentity.updatedBy = #{tSalesPosAttrentity.getUpdatedBy}",
			"Date(tSalesPosAttrentity.updateDt) = '#{tSalesPosAttrentity.getUpdateDt}'",
			"tSalesPosAttrentity.tenantId = #{tSalesPosAttrentity.getTenantId}",
			"tSalesPosAttrentity.tSalesPos.tSalesPosId = #{tSalesPosAttrentity.tSalesPos.getTSalesPosId}",
			"tSalesPosAttrentity.tAttrDef.attrId = #{tSalesPosAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TSalesPosAttr entity object in to the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be persisted
	 * @return tSalesPosAttr Persisted TSalesPosAttr object
	 */
	public TSalesPosAttr createTSalesPosAttr(final TSalesPosAttr tSalesPosAttr) {
		LOGGER.info("=========== Create TSalesPosAttr ===========");
		return genericDAO.store(tSalesPosAttr);
	}

	/**
	 * Deletes a TSalesPosAttr entity object from the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be deleted
	 */
	public void deleteTSalesPosAttr(final TSalesPosAttrId tSalesPosAttrId) {
		LOGGER.info("=========== Delete TSalesPosAttr ===========");
		final TSalesPosAttr tSalesPosAttr = genericDAO.get(clazz, tSalesPosAttrId);
		genericDAO.remove(tSalesPosAttr);
	}

	/**
	 * Updates a TSalesPosAttr entity object in to the persistent store
	 * 
	 * @param tSalesPosAttr
	 *            TSalesPosAttr Entity object to be updated
	 * @return tSalesPosAttr Persisted TSalesPosAttr object
	 */
	public TSalesPosAttr updateTSalesPosAttr(final TSalesPosAttr tSalesPosAttr) {
		LOGGER.info("=========== Update TSalesPosAttr ===========");
		return genericDAO.update(tSalesPosAttr);
	}

	/**
	 * Retrieve an TSalesPosAttr object based on given TSalesPosAttr TSalesPosAttrId.
	 * 
	 * @param tSalesPosAttrId
	 *            the primary key value of the TSalesPosAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosAttr findTSalesPosAttrById(final TSalesPosAttrId tSalesPosAttrId) {
		LOGGER.info("find TSalesPosAttr instance with TSalesPosAttrId: " + tSalesPosAttrId);
		return genericDAO.get(clazz, tSalesPosAttrId);
	}

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosAttr> findTSalesPosAttrs(final SearchFilter<TSalesPosAttr> searchFilter) {
		LOGGER.info("=========== Find TSalesPosAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosAttr tSalesPosAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosAttrentity", tSalesPosAttr);

		if (tSalesPosAttr.getTSalesPos() == null) {
			jpaQuery.bind(TSALESPOS, new TSalesPos());
		} else {
			LOGGER.info("tSalesPos {}"+ tSalesPosAttr.getTSalesPos());

			jpaQuery.bind(TSALESPOS, tSalesPosAttr.getTSalesPos());
		}

		if (tSalesPosAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tSalesPosAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tSalesPosAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosAttrs(final SearchFilter<TSalesPosAttr> searchFilter) {
		LOGGER.info("=========== Count TSalesPosAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosAttr tSalesPosAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosAttrentity", tSalesPosAttr);

		if (tSalesPosAttr.getTSalesPos() == null) {
			jpaCountQuery.bind(TSALESPOS, new TSalesPos());
		} else {
			LOGGER.info("tSalesPos {}"+ tSalesPosAttr.getTSalesPos());

			jpaCountQuery.bind(TSALESPOS, tSalesPosAttr.getTSalesPos());
		}

		if (tSalesPosAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tSalesPosAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tSalesPosAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPosAttr> getTSalesPosAttrsByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosAttrByTSalesPos", queryParam, index, maxresult);
	}

	/**
	 * Count TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPosAttrsByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPosAttrsByTSalesPos", queryParam);
	}

	/**
	 * Retrieve TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosAttr> list of TSalesPosAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TSalesPosAttr> getTSalesPosAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosAttrByTAttrDef", queryParam, index, maxresult);
	}

	/**
	 * Count TSalesPosAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTSalesPosAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTSalesPosAttrsByTAttrDef", queryParam);
	}
	
	public List<TSalesPosAttr> findTSalesPosAttrByTAttrDefPosIdAndTenant(long AttrdefId,long salesPosId,Short tenantId) {
		List queryParam = new ArrayList();
		queryParam.add(AttrdefId);
		queryParam.add(salesPosId);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosAttrByTAttrDefPosIdAndTenant", queryParam, 0, -1);
		
	}

	@Override
	public List<TSalesPosAttr> getTSalesPosAttrsById(SearchFilter<TSalesPosAttr> searchFilter) {
		final TSalesPosAttr tSalesPosAttr = searchFilter.getEntityClass();
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tSalesPosAttr.getTSalesPosAttrId().getSalesPosId());
		paramList.add(tSalesPosAttr.getTSalesPosAttrId().getSalesHierId());
		paramList.add(tSalesPosAttr.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTSalesPosAttrById",paramList, 0, -1);

	}
	
	@Override
	public List<TSalesPosAttr> getTSalesPosAttrByAttrId(TSalesPosAttr tSalesPosAttr) {
		List<Object> paramList = new ArrayList<Object>();
		
		paramList.add(tSalesPosAttr.getTAttrDef().getAttrId());
		paramList.add(tSalesPosAttr.getAttrValue());
		paramList.add(tSalesPosAttr.getTenantId());
		paramList.add(tSalesPosAttr.getTSalesPosAttrId().getSalesPosId());
		paramList.add(tSalesPosAttr.getTSalesPosAttrId().getSalesHierId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTSalesPosAttrByAttrId",paramList,0,-1);
	}
	
	@Override
	public List<Object[]> getTSalesPosAttrValBySP(Long spId,Short tenatId) {
		final List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(spId);		
		queryParam.add(tenatId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTSalesPosAttrValBySP", queryParam, 0, -1);
	}
	

}
