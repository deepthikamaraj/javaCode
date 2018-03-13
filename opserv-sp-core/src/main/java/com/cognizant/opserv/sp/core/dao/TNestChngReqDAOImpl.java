package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqTrigger;
import com.cognizant.opserv.sp.core.entity.TNestChngReq;
import com.cognizant.opserv.sp.core.entity.TNestChngReqId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TNestChngReqDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tNestChngReqDAO")
public class TNestChngReqDAOImpl implements TNestChngReqDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TNestChngReqDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQTRIGGERBYTRIGGERID = "tChngReqTriggerByTriggerId";
	private static final String TCHNGREQTRIGGERBYNSTTRIGGERID = "tChngReqTriggerByNstTriggerId";

	private final Class<TNestChngReq> clazz;

	public Class<TNestChngReq> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TNestChngReqDAOImpl() {
		super();
		this.clazz = TNestChngReq.class;
	}

	private static final String JPAQL = "select tNestChngReqentity from TNestChngReq tNestChngReqentity";

	private static final String JPACOUNTQL = "select count(*) from TNestChngReq tNestChngReqentity";

	private static final String[] RESTRICTIONS = {
			"tNestChngReqentity.tNestChngReqId.nstTriggerId = #{tNestChngReqentity.tNestChngReqId.getNstTriggerId}",
			"tNestChngReqentity.tNestChngReqId.triggerId = #{tNestChngReqentity.tNestChngReqId.getTriggerId}",
			"tNestChngReqentity.activeFlag = #{tNestChngReqentity.getActiveFlag}",
			"tNestChngReqentity.createdBy = #{tNestChngReqentity.getCreatedBy}",
			"Date(tNestChngReqentity.createDt) = '#{tNestChngReqentity.getCreateDt}'",
			"tNestChngReqentity.updatedBy = #{tNestChngReqentity.getUpdatedBy}",
			"Date(tNestChngReqentity.updateDt) = '#{tNestChngReqentity.getUpdateDt}'",
			"tNestChngReqentity.tenantId = #{tNestChngReqentity.getTenantId}",
			"tNestChngReqentity.tChngReqTriggerByTriggerId.triggerId = #{tNestChngReqentity.tChngReqTriggerByTriggerId.getTriggerId}",
			"tNestChngReqentity.tChngReqTriggerByNstTriggerId.triggerId = #{tNestChngReqentity.tChngReqTriggerByNstTriggerId.getTriggerId}" };

	/**
	 * Stores a new TNestChngReq entity object in to the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be persisted
	 * @return tNestChngReq Persisted TNestChngReq object
	 */
	public TNestChngReq createTNestChngReq(final TNestChngReq tNestChngReq) {
		LOGGER.info("=========== Create TNestChngReq ===========");
		return genericDAO.store(tNestChngReq);
	}

	/**
	 * Deletes a TNestChngReq entity object from the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be deleted
	 */
	public void deleteTNestChngReq(final TNestChngReqId tNestChngReqId) {
		LOGGER.info("=========== Delete TNestChngReq ===========");
		final TNestChngReq tNestChngReq = genericDAO.get(clazz, tNestChngReqId);
		genericDAO.remove(tNestChngReq);
	}

	/**
	 * Updates a TNestChngReq entity object in to the persistent store
	 * 
	 * @param tNestChngReq
	 *            TNestChngReq Entity object to be updated
	 * @return tNestChngReq Persisted TNestChngReq object
	 */
	public TNestChngReq updateTNestChngReq(final TNestChngReq tNestChngReq) {
		LOGGER.info("=========== Update TNestChngReq ===========");
		return genericDAO.update(tNestChngReq);
	}

	/**
	 * Retrieve an TNestChngReq object based on given TNestChngReq TNestChngReqId.
	 * 
	 * @param tNestChngReqId
	 *            the primary key value of the TNestChngReq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TNestChngReq findTNestChngReqById(final TNestChngReqId tNestChngReqId) {
		LOGGER.info("find TNestChngReq instance with TNestChngReqId: " + tNestChngReqId);
		return genericDAO.get(clazz, tNestChngReqId);
	}

	/**
	 * Retrieve TNestChngReq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReq if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TNestChngReq> findTNestChngReqs(final SearchFilter<TNestChngReq> searchFilter) {
		LOGGER.info("=========== Find TNestChngReqs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TNestChngReq tNestChngReq = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tNestChngReqentity", tNestChngReq);

		if (tNestChngReq.getTChngReqTriggerByTriggerId() == null) {
			jpaQuery.bind(TCHNGREQTRIGGERBYTRIGGERID, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTriggerByTriggerId {}"+ tNestChngReq.getTChngReqTriggerByTriggerId());

			jpaQuery.bind(TCHNGREQTRIGGERBYTRIGGERID, tNestChngReq.getTChngReqTriggerByTriggerId());
		}

		if (tNestChngReq.getTChngReqTriggerByNstTriggerId() == null) {
			jpaQuery.bind(TCHNGREQTRIGGERBYNSTTRIGGERID, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTriggerByNstTriggerId {}"+ tNestChngReq.getTChngReqTriggerByNstTriggerId());

			jpaQuery.bind(TCHNGREQTRIGGERBYNSTTRIGGERID, tNestChngReq.getTChngReqTriggerByNstTriggerId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TNestChngReqs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTNestChngReqs(final SearchFilter<TNestChngReq> searchFilter) {
		LOGGER.info("=========== Count TNestChngReq ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TNestChngReq tNestChngReq = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tNestChngReqentity", tNestChngReq);

		if (tNestChngReq.getTChngReqTriggerByTriggerId() == null) {
			jpaCountQuery.bind(TCHNGREQTRIGGERBYTRIGGERID, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTriggerByTriggerId {}"+ tNestChngReq.getTChngReqTriggerByTriggerId());

			jpaCountQuery.bind(TCHNGREQTRIGGERBYTRIGGERID, tNestChngReq.getTChngReqTriggerByTriggerId());
		}

		if (tNestChngReq.getTChngReqTriggerByNstTriggerId() == null) {
			jpaCountQuery.bind(TCHNGREQTRIGGERBYNSTTRIGGERID, new TChngReqTrigger());
		} else {
			LOGGER.info("tChngReqTriggerByNstTriggerId {}"+ tNestChngReq.getTChngReqTriggerByNstTriggerId());

			jpaCountQuery.bind(TCHNGREQTRIGGERBYNSTTRIGGERID, tNestChngReq.getTChngReqTriggerByNstTriggerId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTriggerByTriggerId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TNestChngReq> getTNestChngReqsByTChngReqTriggerByTriggerId(
			final SearchFilter<TChngReqTrigger> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTNestChngReqByTChngReqTriggerByTriggerId", tChngReqTriggerList,
				index, maxresult);
	}

	/**
	 * Count TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTNestChngReqsByTChngReqTriggerByTriggerId(final SearchFilter<TChngReqTrigger> searchFilter) {

		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		return genericDAO.findEntitiesByNamedQuery("CountTNestChngReqsByTChngReqTriggerByTriggerId", tChngReqTriggerList);
	}

	/**
	 * Retrieve TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTriggerByNstTriggerId type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNestChngReq> list of TNestChngReqs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TNestChngReq> getTNestChngReqsByTChngReqTriggerByNstTriggerId(
			final SearchFilter<TChngReqTrigger> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTNestChngReqByTChngReqTriggerByNstTriggerId", tChngReqTriggerList,
				index, maxresult);
	}

	/**
	 * Count TNestChngReq based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReqTrigger type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTNestChngReqsByTChngReqTriggerByNstTriggerId(final SearchFilter<TChngReqTrigger> searchFilter) {

		final TChngReqTrigger tChngReqTrigger = searchFilter.getEntityClass();
		List<Object> tChngReqTriggerList = new ArrayList<Object>();
		tChngReqTriggerList.add(tChngReqTrigger);
		return genericDAO
				.findEntitiesByNamedQuery("CountTNestChngReqsByTChngReqTriggerByNstTriggerId", tChngReqTriggerList);
	}

}
