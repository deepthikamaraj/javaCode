package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqNotify;
import com.cognizant.opserv.sp.core.entity.TChngReqNotifyId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqNotifyDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqNotifyDAO")
public class TChngReqNotifyDAOImpl implements TChngReqNotifyDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqNotifyDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";

	private final Class<TChngReqNotify> clazz;

	public Class<TChngReqNotify> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqNotifyDAOImpl() {
		super();
		this.clazz = TChngReqNotify.class;
	}

	private static final String JPAQL = "select tChngReqNotifyentity from TChngReqNotify tChngReqNotifyentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqNotify tChngReqNotifyentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqNotifyentity.tChngReqNotifyId.chngReqId = #{tChngReqNotifyentity.tChngReqNotifyId.getChngReqId}",
			"tChngReqNotifyentity.tChngReqNotifyId.noteSalesHierId = #{tChngReqNotifyentity.tChngReqNotifyId.getNoteSalesHierId}",
			"tChngReqNotifyentity.tChngReqNotifyId.noteSalesPosId = #{tChngReqNotifyentity.tChngReqNotifyId.getNoteSalesPosId}",
			"tChngReqNotifyentity.activeFlag = #{tChngReqNotifyentity.getActiveFlag}",
			"tChngReqNotifyentity.createdBy = #{tChngReqNotifyentity.getCreatedBy}",
			"Date(tChngReqNotifyentity.createDt) = '#{tChngReqNotifyentity.getCreateDt}'",
			"tChngReqNotifyentity.updatedBy = #{tChngReqNotifyentity.getUpdatedBy}",
			"Date(tChngReqNotifyentity.updateDt) = '#{tChngReqNotifyentity.getUpdateDt}'",
			"tChngReqNotifyentity.tenantId = #{tChngReqNotifyentity.getTenantId}",
			"tChngReqNotifyentity.tChngReq.chngReqId = #{tChngReqNotifyentity.tChngReq.getChngReqId}" };

	/**
	 * Stores a new TChngReqNotify entity object in to the persistent store
	 * 
	 * @param tChngReqNotify
	 *            TChngReqNotify Entity object to be persisted
	 * @return tChngReqNotify Persisted TChngReqNotify object
	 */
	public TChngReqNotify createTChngReqNotify(final TChngReqNotify tChngReqNotify) {
		LOGGER.info("=========== Create TChngReqNotify ===========");
		return genericDAO.store(tChngReqNotify);
	}

	/**
	 * Deletes a TChngReqNotify entity object from the persistent store
	 * 
	 * @param tChngReqNotify
	 *            TChngReqNotify Entity object to be deleted
	 */
	public void deleteTChngReqNotify(final TChngReqNotifyId tChngReqNotifyId) {
		LOGGER.info("=========== Delete TChngReqNotify ===========");
		final TChngReqNotify tChngReqNotify = genericDAO.get(clazz, tChngReqNotifyId);
		genericDAO.remove(tChngReqNotify);
	}

	/**
	 * Updates a TChngReqNotify entity object in to the persistent store
	 * 
	 * @param tChngReqNotify
	 *            TChngReqNotify Entity object to be updated
	 * @return tChngReqNotify Persisted TChngReqNotify object
	 */
	public TChngReqNotify updateTChngReqNotify(final TChngReqNotify tChngReqNotify) {
		LOGGER.info("=========== Update TChngReqNotify ===========");
		return genericDAO.update(tChngReqNotify);
	}

	/**
	 * Retrieve an TChngReqNotify object based on given TChngReqNotify TChngReqNotifyId.
	 * 
	 * @param tChngReqNotifyId
	 *            the primary key value of the TChngReqNotify Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqNotify findTChngReqNotifyById(final TChngReqNotifyId tChngReqNotifyId) {
		LOGGER.info("find TChngReqNotify instance with TChngReqNotifyId: " + tChngReqNotifyId);
		return genericDAO.get(clazz, tChngReqNotifyId);
	}

	/**
	 * Retrieve TChngReqNotify based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNotify> list of TChngReqNotify if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqNotify> findTChngReqNotifys(final SearchFilter<TChngReqNotify> searchFilter) {
		LOGGER.info("=========== Find TChngReqNotifys ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqNotify tChngReqNotify = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqNotifyentity", tChngReqNotify);

		if (tChngReqNotify.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}" + tChngReqNotify.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tChngReqNotify.getTChngReq());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqNotifys.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqNotifys(final SearchFilter<TChngReqNotify> searchFilter) {
		LOGGER.info("=========== Count TChngReqNotify ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqNotify tChngReqNotify = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqNotifyentity", tChngReqNotify);

		if (tChngReqNotify.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}" + tChngReqNotify.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tChngReqNotify.getTChngReq());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TChngReqNotify based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqNotify> list of TChngReqNotifys if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TChngReqNotify> getTChngReqNotifysByTChngReq(final SearchFilter<TChngReq> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> tChngReqList = new ArrayList<Object>();
		tChngReqList.add(tChngReq);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTChngReqNotifyByTChngReq", tChngReqList, index, maxresult);
	}

	/**
	 * Count TChngReqNotify based on given search criteria using JPA named Query.
	 * The search criteria is of TChngReq type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTChngReqNotifysByTChngReq(final SearchFilter<TChngReq> searchFilter) {

		final TChngReq tChngReq = searchFilter.getEntityClass();
		List<Object> tChngReqList = new ArrayList<Object>();
		tChngReqList.add(tChngReq);
		return genericDAO.findEntitiesByNamedQuery("CountTChngReqNotifysByTChngReq", tChngReqList);
	}
	
	/*Added By 407986*/
	/**
	 * Find all sales positions by cr id.
	 *
	 * @param CRId the cR id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<Object> findAllSalesPositionsByCRId(Long CRId , Short tenantId) {
		
		List<Object> param = new ArrayList<Object>();
		param.add(CRId);
		param.add(tenantId) ;
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllSalesPositionByCRId", param, 0, -1) ;
	}



	
}
