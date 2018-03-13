package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TMtr;
import com.cognizant.opserv.sp.core.entity.TMtrAttr;
import com.cognizant.opserv.sp.core.entity.TMtrAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrAttrDAO")
public class TMtrAttrDAOImpl implements TMtrAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TMtrAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TMTR = "tMtr";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TMtrAttr> clazz;

	public Class<TMtrAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrAttrDAOImpl() {
		super();
		this.clazz = TMtrAttr.class;
	}

	private static final String JPAQL = "select tMtrAttrentity from TMtrAttr tMtrAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrAttr tMtrAttrentity";

	private static final String[] RESTRICTIONS = {
			"tMtrAttrentity.tMtrAttrId.mtrId = #{tMtrAttrentity.tMtrAttrId.getMtrId}",
			"tMtrAttrentity.tMtrAttrId.attrId = #{tMtrAttrentity.tMtrAttrId.getAttrId}",
			"tMtrAttrentity.activeFlag = #{tMtrAttrentity.getActiveFlag}",
			"Date(tMtrAttrentity.effStartDt) = '#{tMtrAttrentity.getEffStartDt}'",
			"Date(tMtrAttrentity.effEndDt) = '#{tMtrAttrentity.getEffEndDt}'",
			"tMtrAttrentity.createdBy = #{tMtrAttrentity.getCreatedBy}",
			"Date(tMtrAttrentity.createDt) = '#{tMtrAttrentity.getCreateDt}'",
			"tMtrAttrentity.updatedBy = #{tMtrAttrentity.getUpdatedBy}",
			"Date(tMtrAttrentity.updateDt) = '#{tMtrAttrentity.getUpdateDt}'",
			"tMtrAttrentity.tenantId = #{tMtrAttrentity.getTenantId}",
			"tMtrAttrentity.tMtr.mtrId = #{tMtrAttrentity.tMtr.getMtrId}",
			"tMtrAttrentity.tAttrDef.attrId = #{tMtrAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TMtrAttr entity object in to the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be persisted
	 * @return tMtrAttr Persisted TMtrAttr object
	 */
	public TMtrAttr createTMtrAttr(final TMtrAttr tMtrAttr) {
		LOGGER.info("=========== Create TMtrAttr ===========");
		return genericDAO.store(tMtrAttr);
	}

	/**
	 * Deletes a TMtrAttr entity object from the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be deleted
	 */
	public void deleteTMtrAttr(final TMtrAttrId tMtrAttrId) {
		LOGGER.info("=========== Delete TMtrAttr ===========");
		final TMtrAttr tMtrAttr = genericDAO.get(clazz, tMtrAttrId);
		genericDAO.remove(tMtrAttr);
	}

	/**
	 * Updates a TMtrAttr entity object in to the persistent store
	 * 
	 * @param tMtrAttr
	 *            TMtrAttr Entity object to be updated
	 * @return tMtrAttr Persisted TMtrAttr object
	 */
	public TMtrAttr updateTMtrAttr(final TMtrAttr tMtrAttr) {
		LOGGER.info("=========== Update TMtrAttr ===========");
		return genericDAO.update(tMtrAttr);
	}

	/**
	 * Retrieve an TMtrAttr object based on given TMtrAttr TMtrAttrId.
	 * 
	 * @param tMtrAttrId
	 *            the primary key value of the TMtrAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrAttr findTMtrAttrById(final TMtrAttrId tMtrAttrId) {
		LOGGER.info("find TMtrAttr instance with TMtrAttrId: " + tMtrAttrId);
		return genericDAO.get(clazz, tMtrAttrId);
	}

	/**
	 * Retrieve TMtrAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrAttr> findTMtrAttrs(final SearchFilter<TMtrAttr> searchFilter) {
		LOGGER.info("=========== Find TMtrAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrAttr tMtrAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrAttrentity", tMtrAttr);

		if (tMtrAttr.getTMtr() == null) {
			jpaQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrAttr.getTMtr());

			jpaQuery.bind(TMTR, tMtrAttr.getTMtr());
		}

		if (tMtrAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tMtrAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tMtrAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrAttrs(final SearchFilter<TMtrAttr> searchFilter) {
		LOGGER.info("=========== Count TMtrAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrAttr tMtrAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrAttrentity", tMtrAttr);

		if (tMtrAttr.getTMtr() == null) {
			jpaCountQuery.bind(TMTR, new TMtr());
		} else {
			LOGGER.info("tMtr {}"+ tMtrAttr.getTMtr());

			jpaCountQuery.bind(TMTR, tMtrAttr.getTMtr());
		}

		if (tMtrAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tMtrAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tMtrAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrAttr> getTMtrAttrsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrAttrByTMtr", tMtrList, index, maxresult);
	}

	/**
	 * Count TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TMtr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrAttrsByTMtr(final SearchFilter<TMtr> searchFilter) {

		final TMtr tMtr = searchFilter.getEntityClass();
		List<Object> tMtrList = new ArrayList<Object>();
		tMtrList.add(tMtr);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrAttrsByTMtr", tMtrList);
	}

	/**
	 * Retrieve TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrAttr> list of TMtrAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMtrAttr> getTMtrAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMtrAttrByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TMtrAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMtrAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		return genericDAO.findEntitiesByNamedQuery("CountTMtrAttrsByTAttrDef", tAttrDefList);
	}

}
