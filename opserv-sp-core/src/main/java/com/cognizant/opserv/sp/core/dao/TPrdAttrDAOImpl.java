package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAttr;
import com.cognizant.opserv.sp.core.entity.TPrdAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdAttrDAO")
public class TPrdAttrDAOImpl implements TPrdAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRD = "tPrd";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TPrdAttr> clazz;

	public Class<TPrdAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdAttrDAOImpl() {
		super();
		this.clazz = TPrdAttr.class;
	}

	private static final String JPAQL = "select tPrdAttrentity from TPrdAttr tPrdAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdAttr tPrdAttrentity";

	private static final String[] RESTRICTIONS = {
			"tPrdAttrentity.tPrdAttrId.prdId like '#{tPrdAttrentity.tPrdAttrId.getPrdId}%'",
			"tPrdAttrentity.tPrdAttrId.attrId = #{tPrdAttrentity.tPrdAttrId.getAttrId}",
			"tPrdAttrentity.attrValue like '#{tPrdAttrentity.getAttrValue}%'",
			"tPrdAttrentity.activeFlag = #{tPrdAttrentity.getActiveFlag}",
			"tPrdAttrentity.createdBy = #{tPrdAttrentity.getCreatedBy}",
			"Date(tPrdAttrentity.createDt) = '#{tPrdAttrentity.getCreateDt}'",
			"tPrdAttrentity.updatedBy = #{tPrdAttrentity.getUpdatedBy}",
			"Date(tPrdAttrentity.updateDt) = '#{tPrdAttrentity.getUpdateDt}'",
			"tPrdAttrentity.tenantId = #{tPrdAttrentity.getTenantId}",
			"tPrdAttrentity.tPrd.prdId = #{tPrdAttrentity.tPrd.getPrdId}",
			"tPrdAttrentity.tAttrDef.attrId = #{tPrdAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TPrdAttr entity object in to the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be persisted
	 * @return tPrdAttr Persisted TPrdAttr object
	 */
	public TPrdAttr createTPrdAttr(final TPrdAttr tPrdAttr) {
		LOGGER.info("=========== Create TPrdAttr ===========");
		return genericDAO.store(tPrdAttr);
	}

	/**
	 * Deletes a TPrdAttr entity object from the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be deleted
	 */
	public void deleteTPrdAttr(final TPrdAttrId tPrdAttrId) {
		LOGGER.info("=========== Delete TPrdAttr ===========");
		final TPrdAttr tPrdAttr = genericDAO.get(clazz, tPrdAttrId);
		genericDAO.remove(tPrdAttr);
	}

	/**
	 * Updates a TPrdAttr entity object in to the persistent store
	 * 
	 * @param tPrdAttr
	 *            TPrdAttr Entity object to be updated
	 * @return tPrdAttr Persisted TPrdAttr object
	 */
	public TPrdAttr updateTPrdAttr(final TPrdAttr tPrdAttr) {
		LOGGER.info("=========== Update TPrdAttr ===========");
		return genericDAO.update(tPrdAttr);
	}

	/**
	 * Retrieve an TPrdAttr object based on given TPrdAttr TPrdAttrId.
	 * 
	 * @param tPrdAttrId
	 *            the primary key value of the TPrdAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdAttr findTPrdAttrById(final TPrdAttrId tPrdAttrId) {
		LOGGER.info("find TPrdAttr instance with TPrdAttrId: " + tPrdAttrId);
		return genericDAO.get(clazz, tPrdAttrId);
	}

	/**
	 * Retrieve TPrdAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdAttr> findTPrdAttrs(final SearchFilter<TPrdAttr> searchFilter) {
		LOGGER.info("=========== Find TPrdAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdAttr tPrdAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdAttrentity", tPrdAttr);

		if (tPrdAttr.getTPrd() == null) {
			jpaQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdAttr.getTPrd());

			jpaQuery.bind(TPRD, tPrdAttr.getTPrd());
		}

		if (tPrdAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tPrdAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tPrdAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdAttrs(final SearchFilter<TPrdAttr> searchFilter) {
		LOGGER.info("=========== Count TPrdAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdAttr tPrdAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdAttrentity", tPrdAttr);

		if (tPrdAttr.getTPrd() == null) {
			jpaCountQuery.bind(TPRD, new TPrd());
		} else {
			LOGGER.info("tPrd {}"+ tPrdAttr.getTPrd());

			jpaCountQuery.bind(TPRD, tPrdAttr.getTPrd());
		}

		if (tPrdAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tPrdAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tPrdAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAttr> getTPrdAttrsByTPrd(final SearchFilter<TPrd> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAttrByTPrd", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrd type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAttrsByTPrd(final SearchFilter<TPrd> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdAttrsByTPrd", queryParam);
	}

	/**
	 * Retrieve TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAttr> list of TPrdAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAttr> getTPrdAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAttrByTAttrDef", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdAttrsByTAttrDef", queryParam);
	}

	@Override
	public List<TPrdAttr> GetTPrdAttrById(SearchFilter<TPrdAttr> searchFilter) {
        
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdAttr tPrdAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tPrdAttr.getTPrdAttrId().getPrdId());
		paramList.add(tPrdAttr.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdAttrById",paramList, index, maxresult);
	}
	
	@Override
	public List<TPrdAttr> getTPrdAttrByAttrId(TPrdAttr tPrdAttr) {
		List paramList = new ArrayList();
		
		paramList.add(tPrdAttr.getTAttrDef().getAttrId());
		paramList.add(tPrdAttr.getAttrValue());
		paramList.add(tPrdAttr.getTenantId());
		paramList.add(tPrdAttr.getTPrdAttrId().getPrdId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdAttrByAttrId",paramList,0,-1);
	}
	
	@Override
	public List findPrdExtAttrByGrp(Long algmntId, String bussUnitId,
			Long salesTeamId, Long prdId, short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(algmntId);
		paramList.add(Long.valueOf(bussUnitId));
		paramList.add(salesTeamId);
		paramList.add(prdId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindTPrdAttrByTAttGrpMap", paramList,0,-1);
	}

		@Override
	public List<TPrdAttr> findPrdExtAttrByPrdId(Long prdId, Long attrId, short tenantId) {

		
		TPrdAttrId tPrdAttrId=new TPrdAttrId();
		tPrdAttrId.setPrdId(prdId);
		tPrdAttrId.setAttrId(attrId);
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPrdAttrId);
			
		List<TPrdAttr> list =genericDAO.findEntitiesByNamedQuery("FindTPrdAttrByPrdAttrId", queryParam);
		
			return list;
	}
		
		@Override
		public List<TPrdAttr> findPrdExtAttrByPrdIdAndAttrList(Long prdId,
				List<Long> attrId) {

			List<TPrdAttr> list =new ArrayList<TPrdAttr>();	
			for(Long attr:attrId)	{
				TPrdAttrId tPrdAttrId=new TPrdAttrId();
				tPrdAttrId.setPrdId(prdId);
				tPrdAttrId.setAttrId(attr);
				/*tPrdAttrList.add(tPrdAttrId);*/	
				List<Object> queryParam=new ArrayList<Object> ();
				queryParam.add(tPrdAttrId);
				List<TPrdAttr> listAttr =genericDAO.findEntitiesByNamedQuery("FindTPrdAttrByPrdAttrId", queryParam);
				list.addAll(listAttr);
			}
				
			/*List<TPrdAttr> list =genericDAO.findEntitiesByNamedQuery("FindPrdExtAttrByPrdIdAndAttrList", tPrdAttrList);*/
				return list;
		}

		
		
		public List findPrdExtAttrByGrpDefine(Long algmntId, String bussUnitId,
				Long salesTeamId, short tenantId) {
			List<Object> paramList = new ArrayList<Object>();
			paramList.add("Sales Position - Product");
			paramList.add(algmntId);
			paramList.add(Long.valueOf(bussUnitId));
			paramList.add(salesTeamId);
			paramList.add(tenantId);
			return genericDAO.findEntitiesByNamedQueryMultiCond(
					"FindTPrdAttrByTAttGrpMapDefine", paramList,0,-1);
		}

		@Override
		public List<Object[]> findTPrdAttrByPrdIDList(
				List<Long> prdIdlist, Long algmntId, String bussUnitId,
				Long salesTeamId, short tenantId) {
			List<Object> paramList = new ArrayList<Object>();
			paramList.add(prdIdlist);
			/*paramList.add(algmntId);
			paramList.add(Long.valueOf(bussUnitId));
			paramList.add(salesTeamId);*/
			paramList.add(tenantId);
			List<Object[]> prdAttrList = genericDAO.findEntitiesByNamedQueryMultiCond(
					"FindTPrdAttrByTAttGrpMapValues", paramList,0,-1);
			return prdAttrList;
		}

		
		
		
		
}
