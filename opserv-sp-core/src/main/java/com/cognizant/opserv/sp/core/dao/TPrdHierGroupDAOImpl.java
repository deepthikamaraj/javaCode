package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdHierConfig;
import com.cognizant.opserv.sp.core.entity.TPrdHierGroup;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdHierGroupDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdHierGroupDAO")
public class TPrdHierGroupDAOImpl implements TPrdHierGroupDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdHierGroupDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TPRDHIERCONFIG = "tPrdHierConfig";

	private final Class<TPrdHierGroup> clazz;

	public Class<TPrdHierGroup> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdHierGroupDAOImpl() {
		super();
		this.clazz = TPrdHierGroup.class;
	}

	private static final String JPAQL = "select tPrdHierGroupentity from TPrdHierGroup tPrdHierGroupentity";

	private static final String JPACOUNTQL = "select count(tPrdHierGroupentity) from TPrdHierGroup tPrdHierGroupentity";

	private static final String[] RESTRICTIONS = { "tPrdHierGroupentity.groupId = #{tPrdHierGroupentity.getGroupId}",
			"tPrdHierGroupentity.groupName like '#{tPrdHierGroupentity.getGroupName}%'",
			"tPrdHierGroupentity.activeFlag = #{tPrdHierGroupentity.getActiveFlag}",
			"Date(tPrdHierGroupentity.effStartDt) = '#{tPrdHierGroupentity.getEffStartDt}'",
			"Date(tPrdHierGroupentity.effEndDt) = '#{tPrdHierGroupentity.getEffEndDt}'",
			"tPrdHierGroupentity.createdBy = #{tPrdHierGroupentity.getCreatedBy}",
			"Date(tPrdHierGroupentity.createDt) = '#{tPrdHierGroupentity.getCreateDt}'",
			"tPrdHierGroupentity.updatedBy = #{tPrdHierGroupentity.getUpdatedBy}",
			"Date(tPrdHierGroupentity.updateDt) = '#{tPrdHierGroupentity.getUpdateDt}'",
			"tPrdHierGroupentity.tenantId = #{tPrdHierGroupentity.getTenantId}",
			"tPrdHierGroupentity.prdConfigId= #{tPrdHierGroupentity.getPrdConfigId}",
			"tPrdHierGroupentity.hierLevelId= #{tPrdHierGroupentity.getHierLevelId}"};

	/**
	 * Stores a new TPrdHierGroup entity object in to the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be persisted
	 * @return tPrdHierGroup Persisted TPrdHierGroup object
	 */
	public TPrdHierGroup createTPrdHierGroup(final TPrdHierGroup tPrdHierGroup) {
		LOGGER.info("=========== Create TPrdHierGroup ===========");
		return genericDAO.store(tPrdHierGroup);
	}

	/**
	 * Deletes a TPrdHierGroup entity object from the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be deleted
	 */
	public void deleteTPrdHierGroup(final Integer groupId) {
		LOGGER.info("=========== Delete TPrdHierGroup ===========");
		final TPrdHierGroup tPrdHierGroup = genericDAO.get(clazz, groupId);
		genericDAO.remove(tPrdHierGroup);
	}

	/**
	 * Updates a TPrdHierGroup entity object in to the persistent store
	 * 
	 * @param tPrdHierGroup
	 *            TPrdHierGroup Entity object to be updated
	 * @return tPrdHierGroup Persisted TPrdHierGroup object
	 */
	public TPrdHierGroup updateTPrdHierGroup(final TPrdHierGroup tPrdHierGroup) {
		LOGGER.info("=========== Update TPrdHierGroup ===========");
		return genericDAO.update(tPrdHierGroup);
	}

	/**
	 * Retrieve an TPrdHierGroup object based on given TPrdHierGroup groupId.
	 * 
	 * @param tPrdHierGroupId
	 *            the primary key value of the TPrdHierGroup Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdHierGroup findTPrdHierGroupById(final Integer tPrdHierGroupId) {
		LOGGER.info("find TPrdHierGroup instance with groupId: " + tPrdHierGroupId);
		return genericDAO.get(clazz, tPrdHierGroupId);
	}

	/**
	 * Retrieve TPrdHierGroup based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroup if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrdHierGroup> findTPrdHierGroups(final SearchFilter<TPrdHierGroup> searchFilter) {
		LOGGER.info("=========== Find TPrdHierGroups ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdHierGroup tPrdHierGroup = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdHierGroupentity", tPrdHierGroup);

		/*if (tPrdHierGroup.getTPrdHierConfig() == null) {
			jpaQuery.bind(TPRDHIERCONFIG, new TPrdHierConfig());
		} else {
			LOGGER.info("tPrdHierConfig {}", tPrdHierGroup.getTPrdHierConfig());

			jpaQuery.bind(TPRDHIERCONFIG, tPrdHierGroup.getTPrdHierConfig());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdHierGroups.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdHierGroups(final SearchFilter<TPrdHierGroup> searchFilter) {
		LOGGER.info("=========== Count TPrdHierGroup ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdHierGroup tPrdHierGroup = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdHierGroupentity", tPrdHierGroup);

		/*if (tPrdHierGroup.getTPrdHierConfig() == null) {
			jpaCountQuery.bind(TPRDHIERCONFIG, new TPrdHierConfig());
		} else {
			LOGGER.info("tPrdHierConfig {}", tPrdHierGroup.getTPrdHierConfig());

			jpaCountQuery.bind(TPRDHIERCONFIG, tPrdHierGroup.getTPrdHierConfig());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdHierGroup> getTPrdHierGroupsByTPrdHierConfig(final SearchFilter<TPrdHierConfig> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TPrdHierConfig tPrdHierConfig = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tPrdHierConfig.getTPrdHierConfigId().getHierLevelId());
		queryParam.add(tPrdHierConfig.getTPrdHierConfigId().getPrdConfigId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdHierGroupByTPrdHierConfig", queryParam, index,	maxresult);
	}

	/**
	 * Count TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierConfig type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrdHierGroupsByTPrdHierConfig(final SearchFilter<TPrdHierConfig> searchFilter) {
		final TPrdHierConfig tPrdHierConfig = searchFilter.getEntityClass();
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tPrdHierConfig.getTPrdHierConfigId().getHierLevelId());
		queryParam.add(tPrdHierConfig.getTPrdHierConfigId().getPrdConfigId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("CountTPrdHierGroupsByTPrdHierConfig",queryParam, index, maxresult );
	}
	
	/**
	 * Retrieve TPrdHierGroup based on given search criteria using JPA named Query.
	 * The search criteria is of TPrdHierGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdHierGroup> list of TPrdHierGroups if it exists against given
	 *         criteria. Returns null if not found
	 */
	@Override
	public List<TPrdHierGroup> getTPrdHierGroupsByTPrdHierLevelId(Integer hierLevelId) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(hierLevelId);
		return genericDAO.findEntitiesByNamedQuery("FindTPrdHierGroupByTPrdHierLevelId", queryParam);
	}

}
