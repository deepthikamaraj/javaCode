package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrGroupDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrGroupDAO")
public class TAttrGroupDAOImpl implements TAttrGroupDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrGroupDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSOBJTMPL = "tBussObjTmpl";

	private final Class<TAttrGroup> clazz;

	public Class<TAttrGroup> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrGroupDAOImpl() {
		super();
		this.clazz = TAttrGroup.class;
	}

	private static final String JPAQL = "select tAttrGroupentity from TAttrGroup tAttrGroupentity";

	private static final String JPACOUNTQL = "select count(tAttrGroupentity) from TAttrGroup tAttrGroupentity";

	private static final String[] RESTRICTIONS = { "tAttrGroupentity.attrGroupId = #{tAttrGroupentity.getAttrGroupId}",
			"tAttrGroupentity.groupName like '#{tAttrGroupentity.getGroupName}%'",
			"Date(tAttrGroupentity.effStartDt) = '#{tAttrGroupentity.getEffStartDt}'",
			"Date(tAttrGroupentity.effEndDt) = '#{tAttrGroupentity.getEffEndDt}'",
			"tAttrGroupentity.orderSeq = #{tAttrGroupentity.getOrderSeq}",
			"tAttrGroupentity.defFlag = #{tAttrGroupentity.getDefFlag}",
			"tAttrGroupentity.createdBy = #{tAttrGroupentity.getCreatedBy}",
			"Date(tAttrGroupentity.createDt) = '#{tAttrGroupentity.getCreateDt}'",
			"tAttrGroupentity.updatedBy = #{tAttrGroupentity.getUpdatedBy}",
			"Date(tAttrGroupentity.updateDt) = '#{tAttrGroupentity.getUpdateDt}'",
			"tAttrGroupentity.tenantId = #{tAttrGroupentity.getTenantId}",
			"tAttrGroupentity.activeFlag = #{tAttrGroupentity.getActiveFlag}",
			"tAttrGroupentity.tBussObjTmpl.tmplId = #{tAttrGroupentity.tBussObjTmpl.getTmplId}" };

	/**
	 * Stores a new TAttrGroup entity object in to the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be persisted
	 * @return tAttrGroup Persisted TAttrGroup object
	 */
	public TAttrGroup createTAttrGroup(final TAttrGroup tAttrGroup) {
		LOGGER.info("=========== Create TAttrGroup ===========");
		return genericDAO.store(tAttrGroup);
	}

	/**
	 * Deletes a TAttrGroup entity object from the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be deleted
	 */
	public void deleteTAttrGroup(final Integer attrGroupId) {
		LOGGER.info("=========== Delete TAttrGroup ===========");
		final TAttrGroup tAttrGroup = genericDAO.get(clazz, attrGroupId);
		genericDAO.remove(tAttrGroup);
	}

	/**
	 * Updates a TAttrGroup entity object in to the persistent store
	 * 
	 * @param tAttrGroup
	 *            TAttrGroup Entity object to be updated
	 * @return tAttrGroup Persisted TAttrGroup object
	 */
	public TAttrGroup updateTAttrGroup(final TAttrGroup tAttrGroup) {
		LOGGER.info("=========== Update TAttrGroup ===========");
		return genericDAO.update(tAttrGroup);
	}

	/**
	 * Retrieve an TAttrGroup object based on given TAttrGroup attrGroupId.
	 * 
	 * @param tAttrGroupId
	 *            the primary key value of the TAttrGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrGroup findTAttrGroupById(final Integer tAttrGroupId) {
		LOGGER.info("find TAttrGroup instance with attrGroupId: " + tAttrGroupId);
		return genericDAO.get(clazz, tAttrGroupId);
	}

	/**
	 * Retrieve TAttrGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroup> list of TAttrGroup if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrGroup> findTAttrGroups(final SearchFilter<TAttrGroup> searchFilter) {
		LOGGER.info("=========== Find TAttrGroups ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrGroup tAttrGroup = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrGroupentity", tAttrGroup);

		if (tAttrGroup.getTBussObjTmpl() == null) {
			jpaQuery.bind(TBUSSOBJTMPL, new TBussObjTmpl());
		} else {
			LOGGER.info("tBussObjTmpl {}" + tAttrGroup.getTBussObjTmpl());

			jpaQuery.bind(TBUSSOBJTMPL, tAttrGroup.getTBussObjTmpl());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrGroups.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrGroups(final SearchFilter<TAttrGroup> searchFilter) {
		LOGGER.info("=========== Count TAttrGroup ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrGroup tAttrGroup = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrGroupentity", tAttrGroup);

		if (tAttrGroup.getTBussObjTmpl() == null) {
			jpaCountQuery.bind(TBUSSOBJTMPL, new TBussObjTmpl());
		} else {
			LOGGER.info("tBussObjTmpl {}" + tAttrGroup.getTBussObjTmpl());

			jpaCountQuery.bind(TBUSSOBJTMPL, tAttrGroup.getTBussObjTmpl());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAttrGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroup> list of TAttrGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAttrGroup> getTAttrGroupsByTBussObjTmpl(final SearchFilter<TBussObjTmpl> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupByTBussObjTmpl", tBussObjTmplList, index, maxresult);
	}

	/**
	 * Count TAttrGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObjTmpl type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAttrGroupsByTBussObjTmpl(final SearchFilter<TBussObjTmpl> searchFilter) {

		//final TBussObjTmpl tBussObjTmpl = searchFilter.getEntityClass();
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(searchFilter.getEntityClass());
		
		return genericDAO.findEntitiesByNamedQuery("CountTAttrGroupsByTBussObjTmpl", tBussObjTmplList);
	}
	/**
	 * Retrieve an TAttrGroup object based on given attrGroupName.
	 * 
	 * @param attrGroupName
	 *            the group name of the TAttrGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	@Override
	public List<Object> findTAttrGroupByName(String attrGroupName) {
		LOGGER.info("find TAttrGroup instance with attrGroupName: " + attrGroupName);
		List<Object> attrGroupNameList = new ArrayList<Object>();
		attrGroupNameList.add(attrGroupName);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupByGroupName", attrGroupNameList, 0 ,-1);
	}
	/**
	 * Find t attr group by group name and t buss obj tmpl.
	 *
	 * @param attrGroupName the attr group name
	 * @param tmplId the tmpl id
	 * @param activeFlagY the active flag y
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object> findTAttrGroupByGroupNameAndTBussObjTmpl(String attrGroupName, Integer tmplId,Character activeFlag,Short tenantId) {
		LOGGER.info("find TAttrGroup instance with attrGroupName: " + attrGroupName + "tmplId: " + tmplId);
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(0, attrGroupName);
		queryParam.add(1, tmplId);
		queryParam.add(2, activeFlag);
		queryParam.add(3,tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupByGroupNameAndTBussObjTmpl", queryParam, 0, -1);
	}
	
	/**
	 * Gets the t attr groups by t buss obj tmpl id.
	 *
	 * @param templateId the template id
	 * @param tenantID the tenant id
	 * @return the t attr groups by t buss obj tmpl id
	 */
	public List<TAttrGroup> getTAttrGroupsByTBussObjTmplId(final Integer templateId,Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(templateId);
		list.add(ApplicationConstant.FLAG_YES);
		list.add(tenantId);
		int index=0;
		int maxresult=-1;
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupByTmplId",list,index,maxresult);
	}
	/**
	 * Find non active t attr group by t buss obj tmpl.
	 *
	 * @param tBussObjTmpl the t buss obj tmpl
	 * @return the list
	 */
	@Override
	public List<TAttrGroup> findNonActiveTAttrGroupByTBussObjTmpl( TBussObjTmpl tBussObjTmpl) {
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(tBussObjTmpl);
		return genericDAO.findEntitiesByNamedQuery("FindNonActiveTAttrGroupByTBussObjTmpl", tBussObjTmplList);
	}

	/**
	 * Find active t attr group by t buss obj tmpl.
	 *
	 * @param tmplId the tmpl id
	 * @param tenantId the tenant id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	@Override
	public List<Object[]> findActiveTAttrGroupByTBussObjTmpl(Integer tmplId,Short tenantId,Character activeFlag) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tmplId);
		queryParam.add(tenantId);
		queryParam.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTAttrGroupByTBussObjTmpl", queryParam, 0, -1);
	}
	/**
	 * Find active groups by t buss obj tmpl.
	 *
	 * @param tmplId the tmpl id
	 * @param activeFlag the active flag
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findActiveGroupsByTBussObjTmpl(Integer tmplId,Character activeFlag,Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tmplId);
		queryParam.add(activeFlag);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveGroupsByTBussObjTmpl", queryParam, 0, -1);
	}
}
