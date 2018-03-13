package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMapId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrGroupMapDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrGroupMapDAO")
public class TAttrGroupMapDAOImpl implements TAttrGroupMapDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrGroupMapDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TATTRGROUP = "tAttrGroup";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TAttrGroupMap> clazz;

	public Class<TAttrGroupMap> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrGroupMapDAOImpl() {
		super();
		this.clazz = TAttrGroupMap.class;
	}

	private static final String JPAQL = "select tAttrGroupMapentity from TAttrGroupMap tAttrGroupMapentity";

	private static final String JPACOUNTQL = "select count(*) from TAttrGroupMap tAttrGroupMapentity";

	private static final String[] RESTRICTIONS = {
			"tAttrGroupMapentity.tAttrGroupMapId.attrId = #{tAttrGroupMapentity.tAttrGroupMapId.getAttrId}",
			"tAttrGroupMapentity.tAttrGroupMapId.attrGroupId = #{tAttrGroupMapentity.tAttrGroupMapId.getAttrGroupId}",
			"tAttrGroupMapentity.orderSeq = #{tAttrGroupMapentity.getOrderSeq}",
			"tAttrGroupMapentity.activeFlag = #{tAttrGroupMapentity.getActiveFlag}",
			"tAttrGroupMapentity.displayName like '#{tAttrGroupMapentity.getDisplayName}%'",
			"tAttrGroupMapentity.uniqueFlag = #{tAttrGroupMapentity.getUniqueFlag}",
			"tAttrGroupMapentity.mandatoryFlag = #{tAttrGroupMapentity.getMandatoryFlag}",
			"tAttrGroupMapentity.maskValueFlag = #{tAttrGroupMapentity.getMaskValueFlag}",
			"tAttrGroupMapentity.editableFlag = #{tAttrGroupMapentity.getEditableFlag}",
			"tAttrGroupMapentity.visibleFlag = #{tAttrGroupMapentity.getVisibleFlag}",
			"tAttrGroupMapentity.tooltip like '#{tAttrGroupMapentity.getTooltip}%'",
			"tAttrGroupMapentity.attrDesc like '#{tAttrGroupMapentity.getAttrDesc}%'",
			"tAttrGroupMapentity.createdBy = #{tAttrGroupMapentity.getCreatedBy}",
			"Date(tAttrGroupMapentity.createDt) = '#{tAttrGroupMapentity.getCreateDt}'",
			"tAttrGroupMapentity.updatedBy = #{tAttrGroupMapentity.getUpdatedBy}",
			"Date(tAttrGroupMapentity.updateDt) = '#{tAttrGroupMapentity.getUpdateDt}'",
			"tAttrGroupMapentity.tenantId = #{tAttrGroupMapentity.getTenantId}",
			"tAttrGroupMapentity.tAttrGroup.attrGroupId = #{tAttrGroupMapentity.tAttrGroup.getAttrGroupId}",
			"tAttrGroupMapentity.tAttrDef.attrId = #{tAttrGroupMapentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TAttrGroupMap entity object in to the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be persisted
	 * @return tAttrGroupMap Persisted TAttrGroupMap object
	 */
	public TAttrGroupMap createTAttrGroupMap(final TAttrGroupMap tAttrGroupMap) {
		LOGGER.info("=========== Create TAttrGroupMap ===========");
		return genericDAO.store(tAttrGroupMap);
	}

	/**
	 * Deletes a TAttrGroupMap entity object from the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be deleted
	 */
	public void deleteTAttrGroupMap(final TAttrGroupMapId tAttrGroupMapId) {
		LOGGER.info("=========== Delete TAttrGroupMap ===========");
		final TAttrGroupMap tAttrGroupMap = genericDAO.get(clazz, tAttrGroupMapId);
		genericDAO.remove(tAttrGroupMap);
	}

	/**
	 * Updates a TAttrGroupMap entity object in to the persistent store
	 * 
	 * @param tAttrGroupMap
	 *            TAttrGroupMap Entity object to be updated
	 * @return tAttrGroupMap Persisted TAttrGroupMap object
	 */
	public TAttrGroupMap updateTAttrGroupMap(final TAttrGroupMap tAttrGroupMap) {
		LOGGER.info("=========== Update TAttrGroupMap ===========");
		return genericDAO.update(tAttrGroupMap);
	}

	/**
	 * Retrieve an TAttrGroupMap object based on given TAttrGroupMap TAttrGroupMapId.
	 * 
	 * @param tAttrGroupMapId
	 *            the primary key value of the TAttrGroupMap Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrGroupMap findTAttrGroupMapById(final TAttrGroupMapId tAttrGroupMapId) {
		LOGGER.info("find TAttrGroupMap instance with TAttrGroupMapId: " + tAttrGroupMapId);
		return genericDAO.get(clazz, tAttrGroupMapId);
	}

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMap if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrGroupMap> findTAttrGroupMaps(final SearchFilter<TAttrGroupMap> searchFilter) {
		LOGGER.info("=========== Find TAttrGroupMaps ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrGroupMap tAttrGroupMap = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrGroupMapentity", tAttrGroupMap);

		if (tAttrGroupMap.getTAttrGroup() == null) {
			jpaQuery.bind(TATTRGROUP, new TAttrGroup());
		} else {
			LOGGER.info("tAttrGroup {}" + tAttrGroupMap.getTAttrGroup());

			jpaQuery.bind(TATTRGROUP, tAttrGroupMap.getTAttrGroup());
		}

		if (tAttrGroupMap.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}" + tAttrGroupMap.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tAttrGroupMap.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrGroupMaps.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrGroupMaps(final SearchFilter<TAttrGroupMap> searchFilter) {
		LOGGER.info("=========== Count TAttrGroupMap ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrGroupMap tAttrGroupMap = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrGroupMapentity", tAttrGroupMap);

		if (tAttrGroupMap.getTAttrGroup() == null) {
			jpaCountQuery.bind(TATTRGROUP, new TAttrGroup());
		} else {
			LOGGER.info("tAttrGroup {}" + tAttrGroupMap.getTAttrGroup());

			jpaCountQuery.bind(TATTRGROUP, tAttrGroupMap.getTAttrGroup());
		}

		if (tAttrGroupMap.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}" + tAttrGroupMap.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tAttrGroupMap.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAttrGroupMap> getTAttrGroupMapsByTAttrGroup(final SearchFilter<TAttrGroup> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAttrGroupList = new ArrayList<Object>();
		tAttrGroupList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupMapByTAttrGroup", tAttrGroupList, index, maxresult);
	}

	/**
	 * Count TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAttrGroupMapsByTAttrGroup(final SearchFilter<TAttrGroup> searchFilter) {

		List<Object> tAttrGroupList = new ArrayList<Object>();
		tAttrGroupList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAttrGroupMapsByTAttrGroup", tAttrGroupList);
	}

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TAttrGroupMap> getTAttrGroupMapsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupMapByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTAttrGroupMapsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTAttrGroupMapsByTAttrDef", tAttrDefList);
	}
	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TAttrGroupMap> getTAttrGroupMapByAttrName(String attrName) {
		List<Object> attrNameList = new ArrayList<>();
		attrNameList.add(attrName);
		return genericDAO.findEntitiesByNamedQuery("FindTAttrGroupMapByAttrName", attrNameList);
	}
	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrGroupMap> list of TAttrGroupMaps if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TAttrGroupMap> getTAttrGroupMapByAttrNameAndTAttrGroup(
			String attrName, String attrGroupName) {
		List queryParam = new ArrayList();
		queryParam.add(0, attrName);
		queryParam.add(1, attrGroupName);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupMapByAttrNameAndTAttrGroup", queryParam , 0, -1);
	}
	/**
	 * Find t attr group maps by attr id.
	 *
	 * @param tAttrId the t attr id
	 * @return the list
	 */
	@Override
	public List<TAttrGroupMap> findTAttrGroupMapsByAttrId(Long tAttrId) {	
		List<Object> tAttrIdList = new ArrayList<Object>();
		tAttrIdList.add(tAttrId);
		return genericDAO.findEntitiesByNamedQuery("findTAttrGroupMapsByAttrId",tAttrIdList);	
	}
	/**
	 * Find t attr group maps by attrgroup id.
	 *
	 * @param tAttrGrpId the t attr grp id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TAttrGroupMap> findTAttrGroupMapsByAttrgroupId(Integer tAttrGrpId,short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(tAttrGrpId);
		list.add(tenantId);
		int index=0;
		int maxresult=-1;
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTAttrGroupMapsByAttrgroupId",list,index, maxresult);
	}

	/**
	 * Retrieve TAttrGroupMap based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup and TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRpt> list of TRpts if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TAttrGroupMap> getTAttrGroupMapsByTAttrGroupAndTAttrDef(
			TAttrGroup tAttrGroup, TAttrDef tAttrDef, Integer index,
			Integer maxresult) {
		List paramList = new ArrayList();
		paramList.add(tAttrGroup);
		paramList.add(tAttrDef);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrGroupMapsByTAttrGroupAndTAttrDef", paramList, index, maxresult);
	}
	/**
	 * Delete from t attr group map hist table.
	 *
	 * @param groupId the group id
	 * @param attrId the attr id
	 * @return the int
	 */
	@Override
	public int deleteFromTAttrGroupMapHistTable(Integer groupId, Long attrId) {
		return genericDAO.executeNativeQuery("delete from t_attr_group_map_hist where attr_group_id = "+groupId+" and attr_id =  "+attrId);
	}
	/**
	 * Find group map by attr id.
	 *
	 * @param attrId the attr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findGroupMapByAttrId(Long attrId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(attrId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findGroupMapByAttrId", paramList, 0, -1);
	}

}
