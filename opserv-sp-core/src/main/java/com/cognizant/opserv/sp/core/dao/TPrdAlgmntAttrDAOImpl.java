package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmntAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdAlgmntAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdAlgmntAttrDAO")
public class TPrdAlgmntAttrDAOImpl implements TPrdAlgmntAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdAlgmntAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TPRDALGMNT = "tPrdAlgmnt";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TPrdAlgmntAttr> clazz;

	public Class<TPrdAlgmntAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdAlgmntAttrDAOImpl() {
		super();
		this.clazz = TPrdAlgmntAttr.class;
	}

	private static final String JPAQL = "select tPrdAlgmntAttrentity from TPrdAlgmntAttr tPrdAlgmntAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TPrdAlgmntAttr tPrdAlgmntAttrentity";

	private static final String[] RESTRICTIONS = {
			"tPrdAlgmntAttrentity.tPrdAlgmntAttrId.attrId = #{tPrdAlgmntAttrentity.tPrdAlgmntAttrId.getAttrId}",
			"tPrdAlgmntAttrentity.tPrdAlgmntAttrId.prdAlgmntId = #{tPrdAlgmntAttrentity.tPrdAlgmntAttrId.getPrdAlgmntId}",
			"tPrdAlgmntAttrentity.tPrdAlgmntAttrId.attrId = #{tPrdAlgmntAttrentity.tPrdAlgmntAttrId.getAttrId}",
			"tPrdAlgmntAttrentity.attrValue like '#{tPrdAlgmntAttrentity.getAttrValue}%'",
			"tPrdAlgmntAttrentity.activeFlag = #{tPrdAlgmntAttrentity.getActiveFlag}",
			"tPrdAlgmntAttrentity.createdBy = #{tPrdAlgmntAttrentity.getCreatedBy}",
			"Date(tPrdAlgmntAttrentity.createDt) = '#{tPrdAlgmntAttrentity.getCreateDt}'",
			"tPrdAlgmntAttrentity.updatedBy = #{tPrdAlgmntAttrentity.getUpdatedBy}",
			"Date(tPrdAlgmntAttrentity.updateDt) = '#{tPrdAlgmntAttrentity.getUpdateDt}'",
			"tPrdAlgmntAttrentity.tenantId = #{tPrdAlgmntAttrentity.getTenantId}",
			"tPrdAlgmntAttrentity.tPrdAlgmnt.prdAlgmntId = #{tPrdAlgmntAttrentity.tPrdAlgmnt.getPrdAlgmntId}",
			"tPrdAlgmntAttrentity.tAttrDef.attrId = #{tPrdAlgmntAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TPrdAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be persisted
	 * @return tPrdAlgmntAttr Persisted TPrdAlgmntAttr object
	 */
	public TPrdAlgmntAttr createTPrdAlgmntAttr(final TPrdAlgmntAttr tPrdAlgmntAttr) {
		LOGGER.info("=========== Create TPrdAlgmntAttr ===========");
		return genericDAO.store(tPrdAlgmntAttr);
	}

	/**
	 * Deletes a TPrdAlgmntAttr entity object from the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be deleted
	 */
	public void deleteTPrdAlgmntAttr(final TPrdAlgmntAttrId tPrdAlgmntAttrId) {
		LOGGER.info("=========== Delete TPrdAlgmntAttr ===========");
		final TPrdAlgmntAttr tPrdAlgmntAttr = genericDAO.get(clazz, tPrdAlgmntAttrId);
		genericDAO.remove(tPrdAlgmntAttr);
	}

	/**
	 * Updates a TPrdAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tPrdAlgmntAttr
	 *            TPrdAlgmntAttr Entity object to be updated
	 * @return tPrdAlgmntAttr Persisted TPrdAlgmntAttr object
	 */
	public TPrdAlgmntAttr updateTPrdAlgmntAttr(final TPrdAlgmntAttr tPrdAlgmntAttr) {
		LOGGER.info("=========== Update TPrdAlgmntAttr ===========");
		return genericDAO.update(tPrdAlgmntAttr);
	}

	/**
	 * Retrieve an TPrdAlgmntAttr object based on given TPrdAlgmntAttr TPrdAlgmntAttrId.
	 * 
	 * @param tPrdAlgmntAttrId
	 *            the primary key value of the TPrdAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdAlgmntAttr findTPrdAlgmntAttrById(final TPrdAlgmntAttrId tPrdAlgmntAttrId) {
		LOGGER.info("find TPrdAlgmntAttr instance with TPrdAlgmntAttrId: " + tPrdAlgmntAttrId);
		return genericDAO.get(clazz, tPrdAlgmntAttrId);
	}

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdAlgmntAttr> findTPrdAlgmntAttrs(final SearchFilter<TPrdAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Find TPrdAlgmntAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdAlgmntAttr tPrdAlgmntAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdAlgmntAttrentity", tPrdAlgmntAttr);

		if (tPrdAlgmntAttr.getTPrdAlgmnt() == null) {
			jpaQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}"+ tPrdAlgmntAttr.getTPrdAlgmnt());

			jpaQuery.bind(TPRDALGMNT, tPrdAlgmntAttr.getTPrdAlgmnt());
		}

		if (tPrdAlgmntAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tPrdAlgmntAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tPrdAlgmntAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdAlgmntAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdAlgmntAttrs(final SearchFilter<TPrdAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Count TPrdAlgmntAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdAlgmntAttr tPrdAlgmntAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdAlgmntAttrentity", tPrdAlgmntAttr);

		if (tPrdAlgmntAttr.getTPrdAlgmnt() == null) {
			jpaCountQuery.bind(TPRDALGMNT, new TPrdAlgmnt());
		} else {
			LOGGER.info("tPrdAlgmnt {}"+ tPrdAlgmntAttr.getTPrdAlgmnt());

			jpaCountQuery.bind(TPRDALGMNT, tPrdAlgmntAttr.getTPrdAlgmnt());
		}

		if (tPrdAlgmntAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tPrdAlgmntAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tPrdAlgmntAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAlgmntAttr> getTPrdAlgmntAttrsByTPrdAlgmnt(final SearchFilter<TPrdAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAlgmntAttrByTPrdAlgmnt", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAlgmntAttrsByTPrdAlgmnt(final SearchFilter<TPrdAlgmnt> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdAlgmntAttrsByTPrdAlgmnt", queryParam);
	}

	/**
	 * Retrieve TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdAlgmntAttr> list of TPrdAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdAlgmntAttr> getTPrdAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdAlgmntAttrByTAttrDef", queryParam, index, maxresult);
	}

	/**
	 * Count TPrdAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrdAlgmntAttrsByTAttrDef", queryParam);
	}

	@Override
	public List<TPrdAlgmntAttr> findPrdExtAttrByPrdAlgmnt(Long prdAlgmntId,
			List<Long> attrId) {
		
		
		List<TPrdAlgmntAttrId> tPrdAlgmntAttrList=new ArrayList<TPrdAlgmntAttrId>();
			
		for(Long attr:attrId)	{
			TPrdAlgmntAttrId tPrdAlgmntAttrId=new TPrdAlgmntAttrId();
			tPrdAlgmntAttrId.setPrdAlgmntId(prdAlgmntId);
			tPrdAlgmntAttrId.setAttrId(attr);
		tPrdAlgmntAttrList.add(tPrdAlgmntAttrId);	
		}
			
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(tPrdAlgmntAttrList);
		List<TPrdAlgmntAttr> list =genericDAO.findEntitiesByNamedQuery("FindTPrdAlgmntAttrByPrdAlgmnt", queryParam);
			return list;
	}
	
	
	public List<TPrdAlgmntAttr> GetTPrdAlgmntAttrById(SearchFilter<TPrdAlgmntAttr> searchFilter) {
        
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdAlgmntAttr tPrdAlgmntAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		List paramList = new ArrayList();
		paramList.add(tPrdAlgmntAttr.getTPrdAlgmntAttrId().getPrdAlgmntId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdAlgmntAttrById",paramList, index, maxresult);
	}

	@Override
	public List<TPrdAlgmntAttr> getTPrdAlgmntAttrByAttrId(TPrdAlgmntAttr tPrdAlgmntAttr) {
		List paramList = new ArrayList();
		
		paramList.add(tPrdAlgmntAttr.getTAttrDef().getAttrId());
		paramList.add(tPrdAlgmntAttr.getAttrValue());
		paramList.add(tPrdAlgmntAttr.getTenantId());
		paramList.add(tPrdAlgmntAttr.getTPrdAlgmntAttrId().getPrdAlgmntId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetTPrdAlgmntAttrByAttrId",paramList,0,-1);
	}


}
