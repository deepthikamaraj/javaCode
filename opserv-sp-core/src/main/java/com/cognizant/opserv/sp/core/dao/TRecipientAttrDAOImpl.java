package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttr;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.opserv.sp.core.entity.TRecipientAttrId;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRecipientAttrDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRecipientAttrDAO")
public class TRecipientAttrDAOImpl implements TRecipientAttrDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRecipientAttrDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TTARGETRECIPIENTPREF = "tTargetRecipientPref";
	private static final String TATTR = "tAttr";

	private final Class<TRecipientAttr> clazz;

	public Class<TRecipientAttr> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRecipientAttrDAOImpl() {
		super();
		this.clazz = TRecipientAttr.class;
	}

	private static final String JPAQL = "select tRecipientAttrentity from TRecipientAttr tRecipientAttrentity";

	private static final String JPACOUNTQL = "select count(*) from TRecipientAttr tRecipientAttrentity";

	private static final String[] RESTRICTIONS = {
			"tRecipientAttrentity.tRecipientAttrId.attrId = #{tRecipientAttrentity.tRecipientAttrId.getAttrId}",
			"tRecipientAttrentity.tRecipientAttrId.recipientPrefId = #{tRecipientAttrentity.tRecipientAttrId.getRecipientPrefId}",
			"tRecipientAttrentity.tRecipientAttrId.sequenceId = #{tRecipientAttrentity.tRecipientAttrId.getSequenceId}",
			"tRecipientAttrentity.comparisonFactor like '#{tRecipientAttrentity.getComparisonFactor}%'",
			"tRecipientAttrentity.operator like '#{tRecipientAttrentity.getOperator}%'",
			"tRecipientAttrentity.createdBy = #{tRecipientAttrentity.getCreatedBy}",
			"Date(tRecipientAttrentity.createDt) = '#{tRecipientAttrentity.getCreateDt}'",
			"tRecipientAttrentity.updatedBy = #{tRecipientAttrentity.getUpdatedBy}",
			"Date(tRecipientAttrentity.updateDt) = '#{tRecipientAttrentity.getUpdateDt}'",
			"tRecipientAttrentity.tenantId = #{tRecipientAttrentity.getTenantId}",
			"tRecipientAttrentity.tTargetRecipientPref.recipientPrefId = #{tRecipientAttrentity.tTargetRecipientPref.getRecipientPrefId}",
			"tRecipientAttrentity.tAttr.attrId = #{tRecipientAttrentity.tAttr.getAttrId}" };

	/**
	 * Stores a new TRecipientAttr entity object in to the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be persisted
	 * @return tRecipientAttr Persisted TRecipientAttr object
	 */
	public TRecipientAttr createTRecipientAttr(final TRecipientAttr tRecipientAttr) {
		LOGGER.info("=========== Create TRecipientAttr ===========");
		return genericDAO.store(tRecipientAttr);
	}

	/**
	 * Deletes a TRecipientAttr entity object from the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be deleted
	 */
	public void deleteTRecipientAttr(final TRecipientAttrId tRecipientAttrId) {
		LOGGER.info("=========== Delete TRecipientAttr ===========");
		final TRecipientAttr tRecipientAttr = genericDAO.get(clazz, tRecipientAttrId);
		genericDAO.remove(tRecipientAttr);
	}

	/**
	 * Updates a TRecipientAttr entity object in to the persistent store
	 * 
	 * @param tRecipientAttr
	 *            TRecipientAttr Entity object to be updated
	 * @return tRecipientAttr Persisted TRecipientAttr object
	 */
	public TRecipientAttr updateTRecipientAttr(final TRecipientAttr tRecipientAttr) {
		LOGGER.info("=========== Update TRecipientAttr ===========");
		return genericDAO.update(tRecipientAttr);
	}

	/**
	 * Retrieve an TRecipientAttr object based on given TRecipientAttr TRecipientAttrId.
	 * 
	 * @param tRecipientAttrId
	 *            the primary key value of the TRecipientAttr Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRecipientAttr findTRecipientAttrById(final TRecipientAttrId tRecipientAttrId) {
		LOGGER.info("find TRecipientAttr instance with TRecipientAttrId: " + tRecipientAttrId);
		return genericDAO.get(clazz, tRecipientAttrId);
	}

	/**
	 * Retrieve TRecipientAttr based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttr if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRecipientAttr> findTRecipientAttrs(final SearchFilter<TRecipientAttr> searchFilter) {
		LOGGER.info("=========== Find TRecipientAttrs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRecipientAttr tRecipientAttr = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRecipientAttrentity", tRecipientAttr);

		if (tRecipientAttr.getTTargetRecipientPref() == null) {
			jpaQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}"+ tRecipientAttr.getTTargetRecipientPref());

			jpaQuery.bind(TTARGETRECIPIENTPREF, tRecipientAttr.getTTargetRecipientPref());
		}

		if (tRecipientAttr.getTAttr() == null) {
			jpaQuery.bind(TATTR, new TAttr());
		} else {
			LOGGER.info("tAttr {}"+ tRecipientAttr.getTAttr());

			jpaQuery.bind(TATTR, tRecipientAttr.getTAttr());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRecipientAttrs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRecipientAttrs(final SearchFilter<TRecipientAttr> searchFilter) {
		LOGGER.info("=========== Count TRecipientAttr ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRecipientAttr tRecipientAttr = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRecipientAttrentity", tRecipientAttr);

		if (tRecipientAttr.getTTargetRecipientPref() == null) {
			jpaCountQuery.bind(TTARGETRECIPIENTPREF, new TTargetRecipientPref());
		} else {
			LOGGER.info("tTargetRecipientPref {}"+ tRecipientAttr.getTTargetRecipientPref());

			jpaCountQuery.bind(TTARGETRECIPIENTPREF, tRecipientAttr.getTTargetRecipientPref());
		}

		if (tRecipientAttr.getTAttr() == null) {
			jpaCountQuery.bind(TATTR, new TAttr());
		} else {
			LOGGER.info("tAttr {}"+ tRecipientAttr.getTAttr());

			jpaCountQuery.bind(TATTR, tRecipientAttr.getTAttr());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRecipientAttr> getTRecipientAttrsByTTargetRecipientPref(
			final SearchFilter<TTargetRecipientPref> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRecipientAttrByTTargetRecipientPref", queryParam,
				index, maxresult);
	}

	/**
	 * Count TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TTargetRecipientPref type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRecipientAttrsByTTargetRecipientPref(final SearchFilter<TTargetRecipientPref> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRecipientAttrsByTTargetRecipientPref", queryParam);
	}

	/**
	 * Retrieve TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRecipientAttr> list of TRecipientAttrs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRecipientAttr> getTRecipientAttrsByTAttr(final SearchFilter<TAttr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRecipientAttrByTAttr", queryParam, index, maxresult);
	}

	/**
	 * Count TRecipientAttr based on given search criteria using JPA named Query.
	 * The search criteria is of TAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRecipientAttrsByTAttr(final SearchFilter<TAttr> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRecipientAttrsByTAttr", queryParam);
	}

	@Override
	public List<Object[]> findTRecipientAttrsByRecipientPrefId(
			Integer recipientPrefId) {
		List<Object> queryParam=new ArrayList<Object>();
		queryParam.add(recipientPrefId);
		return genericDAO.findEntitiesByNamedQuery("FindTRecipientAttrsByRecipientPrefId", queryParam);
	}

}
