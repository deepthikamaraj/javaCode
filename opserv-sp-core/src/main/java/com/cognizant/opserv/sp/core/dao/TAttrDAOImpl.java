package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttr;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrDAO")
public class TAttrDAOImpl implements TAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TATTRGROUP = "tAttrGroup";
	//private static final String TATTRDATATYPE = "tAttrDataType";

	private final Class<TAttr> clazz;

	public Class<TAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrDAOImpl() {
		super();
		this.clazz = TAttr.class;
	}

	private static final String JPAQL = "select tAttrentity from TAttr tAttrentity";

	private static final String JPACOUNTQL = "select count(tAttrentity) from TAttr tAttrentity";

	private static final String[] RESTRICTIONS = { "tAttrentity.attrId = #{tAttrentity.getAttrId}",
			"tAttrentity.attrName like '#{tAttrentity.getAttrName}%'",
			"tAttrentity.attrDesc like '#{tAttrentity.getAttrDesc}%'",
			"tAttrentity.activeFlag = #{tAttrentity.getActiveFlag}",
			"tAttrentity.createdBy = #{tAttrentity.getCreatedBy}",
			"Date(tAttrentity.createDt) = '#{tAttrentity.getCreateDt}'",
			"tAttrentity.updatedBy = #{tAttrentity.getUpdatedBy}",
			"Date(tAttrentity.updateDt) = '#{tAttrentity.getUpdateDt}'",
			"tAttrentity.tenantId = #{tAttrentity.getTenantId}",
			"tAttrentity.lookupTable like '#{tAttrentity.getLookupTable}%'",
			"tAttrentity.attrType = #{tAttrentity.getAttrType}",
			"tAttrentity.allowedValue like '#{tAttrentity.getAllowedValue}%'",
			"tAttrentity.groupAttrFlag = #{tAttrentity.getGroupAttrFlag}",
			"tAttrentity.orderSeq = #{tAttrentity.getOrderSeq}",
			"Date(tAttrentity.effStartDt) = '#{tAttrentity.getEffStartDt}'",
			"Date(tAttrentity.effEndDt) = '#{tAttrentity.getEffEndDt}'",
			"tAttrentity.dynAttrFlag = #{tAttrentity.getDynAttrFlag}",
			"tAttrentity.displayName like '#{tAttrentity.getDisplayName}%'",
			"tAttrentity.uniqueFlag = #{tAttrentity.getUniqueFlag}",
			"tAttrentity.mandatoryFlag = #{tAttrentity.getMandatoryFlag}",
			"tAttrentity.maskValueFlag = #{tAttrentity.getMaskValueFlag}",
			"tAttrentity.editableFlag = #{tAttrentity.getEditableFlag}",
			"tAttrentity.visibleFlag = #{tAttrentity.getVisibleFlag}",
			"tAttrentity.entityId = #{tAttrentity.getEntityId}",
			"tAttrentity.tooltip like '#{tAttrentity.getTooltip}%'", "tAttrentity.attrLen = #{tAttrentity.getAttrLen}",
			"tAttrentity.mtrFlag = #{tAttrentity.getMtrFlag}",
			"tAttrentity.tAttrGroup.attrGroupId = #{tAttrentity.tAttrGroup.getAttrGroupId}"
		//	,"tAttrentity.tAttrDataType.tAttrDataTypeId.attrDataTypeId = #{tAttrentity.tAttrDataType.tAttrDataTypeId.getAttrDataTypeId}"
			};

	/**
	 * Stores a new TAttr entity object in to the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be persisted
	 * @return tAttr Persisted TAttr object
	 */
	public TAttr createTAttr(final TAttr tAttr) {
		LOGGER.info("=========== Create TAttr ===========");
		return genericDAO.store(tAttr);
	}

	/**
	 * Deletes a TAttr entity object from the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be deleted
	 */
	public void deleteTAttr(final Long attrId) {
		LOGGER.info("=========== Delete TAttr ===========");
		final TAttr tAttr = genericDAO.get(clazz, attrId);
		genericDAO.remove(tAttr);
	}

	/**
	 * Updates a TAttr entity object in to the persistent store
	 * 
	 * @param tAttr
	 *            TAttr Entity object to be updated
	 * @return tAttr Persisted TAttr object
	 */
	public TAttr updateTAttr(final TAttr tAttr) {
		LOGGER.info("=========== Update TAttr ===========");
		return genericDAO.update(tAttr);
	}

	/**
	 * Retrieve an TAttr object based on given TAttr attrId.
	 * 
	 * @param tAttrId
	 *            the primary key value of the TAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttr findTAttrById(final Long tAttrId) {
		LOGGER.info("find TAttr instance with attrId: " + tAttrId);
		return genericDAO.get(clazz, tAttrId);
	}

	/**
	 * Retrieve TAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttr> findTAttrs(final SearchFilter<TAttr> searchFilter) {
		LOGGER.info("=========== Find TAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttr tAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrentity", tAttr);

		/*if (tAttr.getTAttrGroup() == null) {
			jpaQuery.bind(TATTRGROUP, new TAttrGroup());
		} else {
			LOGGER.info("tAttrGroup {}", tAttr.getTAttrGroup());

			jpaQuery.bind(TATTRGROUP, tAttr.getTAttrGroup());
		}*/

		/*if (tAttr.getTAttrDataType() == null) {
			jpaQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tAttr.getTAttrDataType());

			jpaQuery.bind(TATTRDATATYPE, tAttr.getTAttrDataType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrs(final SearchFilter<TAttr> searchFilter) {
		LOGGER.info("=========== Count TAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttr tAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrentity", tAttr);

		/*if (tAttr.getTAttrGroup() == null) {
			jpaCountQuery.bind(TATTRGROUP, new TAttrGroup());
		} else {
			LOGGER.info("tAttrGroup {}", tAttr.getTAttrGroup());

			jpaCountQuery.bind(TATTRGROUP, tAttr.getTAttrGroup());
		}*/

	/*	if (tAttr.getTAttrDataType() == null) {
			jpaCountQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tAttr.getTAttrDataType());

			jpaCountQuery.bind(TATTRDATATYPE, tAttr.getTAttrDataType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TAttr> getTAttrsByTAttrGroup(final SearchFilter<TAttrGroup> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrGroup tAttrGroup = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTAttrByTAttrGroup", tAttrGroup, index, maxresult);
	}*/

	/**
	 * Count TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrGroup type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTAttrsByTAttrGroup(final SearchFilter<TAttrGroup> searchFilter) {

		final TAttrGroup tAttrGroup = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTAttrsByTAttrGroup", tAttrGroup);
	}*/

	/**
	 * Retrieve TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttr> list of TAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TAttr> getTAttrsByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTAttrByTAttrDataType", tAttrDataType, index, maxresult);
	}*/

	/**
	 * Count TAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTAttrsByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTAttrsByTAttrDataType", tAttrDataType);
	}*/

}
