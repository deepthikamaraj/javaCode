package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDef;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmntAttr;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmntAttrId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TEmpAlgmntAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tEmpAlgmntAttrDAO")
public class TEmpAlgmntAttrDAOImpl implements TEmpAlgmntAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TEmpAlgmntAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TEMPALGMNT = "tEmpAlgmnt";
	private static final String TATTRDEF = "tAttrDef";

	private final Class<TEmpAlgmntAttr> clazz;

	public Class<TEmpAlgmntAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TEmpAlgmntAttrDAOImpl() {
		super();
		this.clazz = TEmpAlgmntAttr.class;
	}

	private static final String JPAQL = "select tEmpAlgmntAttrentity from TEmpAlgmntAttr tEmpAlgmntAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TEmpAlgmntAttr tEmpAlgmntAttrentity";

	private static final String[] RESTRICTIONS = {
			"tEmpAlgmntAttrentity.tEmpAlgmntAttrId.attrId = #{tEmpAlgmntAttrentity.tEmpAlgmntAttrId.getAttrId}",
			"tEmpAlgmntAttrentity.tEmpAlgmntAttrId.empAlgmntId like '#{tEmpAlgmntAttrentity.tEmpAlgmntAttrId.getEmpAlgmntId}%'",
			"tEmpAlgmntAttrentity.attrValue like '#{tEmpAlgmntAttrentity.getAttrValue}%'",
			"tEmpAlgmntAttrentity.activeFlag = #{tEmpAlgmntAttrentity.getActiveFlag}",
			"tEmpAlgmntAttrentity.createdBy = #{tEmpAlgmntAttrentity.getCreatedBy}",
			"Date(tEmpAlgmntAttrentity.createDt) = '#{tEmpAlgmntAttrentity.getCreateDt}'",
			"tEmpAlgmntAttrentity.updatedBy = #{tEmpAlgmntAttrentity.getUpdatedBy}",
			"Date(tEmpAlgmntAttrentity.updateDt) = '#{tEmpAlgmntAttrentity.getUpdateDt}'",
			"tEmpAlgmntAttrentity.tenantId = #{tEmpAlgmntAttrentity.getTenantId}",
			"tEmpAlgmntAttrentity.tEmpAlgmnt.empAlgmntId = #{tEmpAlgmntAttrentity.tEmpAlgmnt.getEmpAlgmntId}",
			"tEmpAlgmntAttrentity.tAttrDef.attrId = #{tEmpAlgmntAttrentity.tAttrDef.getAttrId}" };

	/**
	 * Stores a new TEmpAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be persisted
	 * @return tEmpAlgmntAttr Persisted TEmpAlgmntAttr object
	 */
	public TEmpAlgmntAttr createTEmpAlgmntAttr(final TEmpAlgmntAttr tEmpAlgmntAttr) {
		LOGGER.info("=========== Create TEmpAlgmntAttr ===========");
		return genericDAO.store(tEmpAlgmntAttr);
	}

	/**
	 * Deletes a TEmpAlgmntAttr entity object from the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be deleted
	 */
	public void deleteTEmpAlgmntAttr(final TEmpAlgmntAttrId tEmpAlgmntAttrId) {
		LOGGER.info("=========== Delete TEmpAlgmntAttr ===========");
		final TEmpAlgmntAttr tEmpAlgmntAttr = genericDAO.get(clazz, tEmpAlgmntAttrId);
		genericDAO.remove(tEmpAlgmntAttr);
	}

	/**
	 * Updates a TEmpAlgmntAttr entity object in to the persistent store
	 * 
	 * @param tEmpAlgmntAttr
	 *            TEmpAlgmntAttr Entity object to be updated
	 * @return tEmpAlgmntAttr Persisted TEmpAlgmntAttr object
	 */
	public TEmpAlgmntAttr updateTEmpAlgmntAttr(final TEmpAlgmntAttr tEmpAlgmntAttr) {
		LOGGER.info("=========== Update TEmpAlgmntAttr ===========");
		return genericDAO.update(tEmpAlgmntAttr);
	}

	/**
	 * Retrieve an TEmpAlgmntAttr object based on given TEmpAlgmntAttr TEmpAlgmntAttrId.
	 * 
	 * @param tEmpAlgmntAttrId
	 *            the primary key value of the TEmpAlgmntAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TEmpAlgmntAttr findTEmpAlgmntAttrById(final TEmpAlgmntAttrId tEmpAlgmntAttrId) {
		LOGGER.info("find TEmpAlgmntAttr instance with TEmpAlgmntAttrId: " + tEmpAlgmntAttrId);
		return genericDAO.get(clazz, tEmpAlgmntAttrId);
	}

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TEmpAlgmntAttr> findTEmpAlgmntAttrs(final SearchFilter<TEmpAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Find TEmpAlgmntAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TEmpAlgmntAttr tEmpAlgmntAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tEmpAlgmntAttrentity", tEmpAlgmntAttr);

		if (tEmpAlgmntAttr.getTEmpAlgmnt() == null) {
			jpaQuery.bind(TEMPALGMNT, new TEmpAlgmnt());
		} else {
			LOGGER.info("tEmpAlgmnt {}"+ tEmpAlgmntAttr.getTEmpAlgmnt());

			jpaQuery.bind(TEMPALGMNT, tEmpAlgmntAttr.getTEmpAlgmnt());
		}

		if (tEmpAlgmntAttr.getTAttrDef() == null) {
			jpaQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tEmpAlgmntAttr.getTAttrDef());

			jpaQuery.bind(TATTRDEF, tEmpAlgmntAttr.getTAttrDef());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TEmpAlgmntAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTEmpAlgmntAttrs(final SearchFilter<TEmpAlgmntAttr> searchFilter) {
		LOGGER.info("=========== Count TEmpAlgmntAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TEmpAlgmntAttr tEmpAlgmntAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tEmpAlgmntAttrentity", tEmpAlgmntAttr);

		if (tEmpAlgmntAttr.getTEmpAlgmnt() == null) {
			jpaCountQuery.bind(TEMPALGMNT, new TEmpAlgmnt());
		} else {
			LOGGER.info("tEmpAlgmnt {}"+ tEmpAlgmntAttr.getTEmpAlgmnt());

			jpaCountQuery.bind(TEMPALGMNT, tEmpAlgmntAttr.getTEmpAlgmnt());
		}

		if (tEmpAlgmntAttr.getTAttrDef() == null) {
			jpaCountQuery.bind(TATTRDEF, new TAttrDef());
		} else {
			LOGGER.info("tAttrDef {}"+ tEmpAlgmntAttr.getTAttrDef());

			jpaCountQuery.bind(TATTRDEF, tEmpAlgmntAttr.getTAttrDef());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TEmpAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TEmpAlgmntAttr> getTEmpAlgmntAttrsByTEmpAlgmnt(final SearchFilter<TEmpAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TEmpAlgmnt tEmpAlgmnt = searchFilter.getEntityClass();
		List<Object> tEmpAlgmntList = new ArrayList<Object>();
		tEmpAlgmntList.add(tEmpAlgmnt);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTEmpAlgmntAttrByTEmpAlgmnt", tEmpAlgmntList, index, maxresult);
	}

	/**
	 * Count TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TEmpAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTEmpAlgmntAttrsByTEmpAlgmnt(final SearchFilter<TEmpAlgmnt> searchFilter) {

		final TEmpAlgmnt tEmpAlgmnt = searchFilter.getEntityClass();
		List<Object> tEmpAlgmntList = new ArrayList<Object>();
		tEmpAlgmntList.add(tEmpAlgmnt);
		return genericDAO.findEntitiesByNamedQuery("CountTEmpAlgmntAttrsByTEmpAlgmnt", tEmpAlgmntList);
	}

	/**
	 * Retrieve TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpAlgmntAttr> list of TEmpAlgmntAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TEmpAlgmntAttr> getTEmpAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTEmpAlgmntAttrByTAttrDef", tAttrDefList, index, maxresult);
	}

	/**
	 * Count TEmpAlgmntAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDef type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTEmpAlgmntAttrsByTAttrDef(final SearchFilter<TAttrDef> searchFilter) {

		final TAttrDef tAttrDef = searchFilter.getEntityClass();
		List<Object> tAttrDefList = new ArrayList<Object>();
		tAttrDefList.add(tAttrDef);
		return genericDAO.findEntitiesByNamedQuery("CountTEmpAlgmntAttrsByTAttrDef", tAttrDefList);
	}

}
