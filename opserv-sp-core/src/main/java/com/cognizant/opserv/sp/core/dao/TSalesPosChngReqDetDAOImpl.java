package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TSalesPosChngReqDet;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosChngReqDetDAO")
public class TSalesPosChngReqDetDAOImpl implements TSalesPosChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TSalesPosChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCHNGREQ = "tChngReq";

	private final Class<TSalesPosChngReqDet> clazz;

	public Class<TSalesPosChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosChngReqDetDAOImpl() {
		super();
		this.clazz = TSalesPosChngReqDet.class;
	}

	private static final String JPAQL = "select tSalesPosChngReqDetentity from TSalesPosChngReqDet tSalesPosChngReqDetentity";

	private static final String JPACOUNTQL = "select count(tSalesPosChngReqDetentity) from TSalesPosChngReqDet tSalesPosChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosChngReqDetentity.spChngReqId = #{tSalesPosChngReqDetentity.getSpChngReqId}",
			"tSalesPosChngReqDetentity.salesHierId = #{tSalesPosChngReqDetentity.getSalesHierId}",
			"tSalesPosChngReqDetentity.salesPosId = #{tSalesPosChngReqDetentity.getSalesPosId}",
			"tSalesPosChngReqDetentity.statusId = #{tSalesPosChngReqDetentity.getStatusId}",
			"tSalesPosChngReqDetentity.createdBy = #{tSalesPosChngReqDetentity.getCreatedBy}",
			"Date(tSalesPosChngReqDetentity.createDt) = '#{tSalesPosChngReqDetentity.getCreateDt}'",
			"tSalesPosChngReqDetentity.updatedBy = #{tSalesPosChngReqDetentity.getUpdatedBy}",
			"Date(tSalesPosChngReqDetentity.updateDt) = '#{tSalesPosChngReqDetentity.getUpdateDt}'",
			"tSalesPosChngReqDetentity.tenantId = #{tSalesPosChngReqDetentity.getTenantId}",
			"tSalesPosChngReqDetentity.tChngReq.chngReqId = #{tSalesPosChngReqDetentity.tChngReq.getChngReqId}" };

	/**
	 * Stores a new TSalesPosChngReqDet entity object in to the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be persisted
	 * @return tSalesPosChngReqDet Persisted TSalesPosChngReqDet object
	 */
	public TSalesPosChngReqDet createTSalesPosChngReqDet(final TSalesPosChngReqDet tSalesPosChngReqDet) {
		LOGGER.info("=========== Create TSalesPosChngReqDet ===========");
		return genericDAO.store(tSalesPosChngReqDet);
	}

	/**
	 * Deletes a TSalesPosChngReqDet entity object from the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be deleted
	 */
	public void deleteTSalesPosChngReqDet(final Long spChngReqId) {
		LOGGER.info("=========== Delete TSalesPosChngReqDet ===========");
		final TSalesPosChngReqDet tSalesPosChngReqDet = genericDAO.get(clazz, spChngReqId);
		genericDAO.remove(tSalesPosChngReqDet);
	}

	/**
	 * Updates a TSalesPosChngReqDet entity object in to the persistent store
	 * 
	 * @param tSalesPosChngReqDet
	 *            TSalesPosChngReqDet Entity object to be updated
	 * @return tSalesPosChngReqDet Persisted TSalesPosChngReqDet object
	 */
	public TSalesPosChngReqDet updateTSalesPosChngReqDet(final TSalesPosChngReqDet tSalesPosChngReqDet) {
		LOGGER.info("=========== Update TSalesPosChngReqDet ===========");
		return genericDAO.update(tSalesPosChngReqDet);
	}
	
	public List<TSalesPosChngReqDet> updateTSalesPosChngReqDet(final List<TSalesPosChngReqDet> tSalesPosChngReqDetList) {
		LOGGER.info("=========== Update TSalesPosChngReqDet ===========");
		return genericDAO.update(tSalesPosChngReqDetList);
	}

	/**
	 * Retrieve an TSalesPosChngReqDet object based on given TSalesPosChngReqDet spChngReqId.
	 * 
	 * @param tSalesPosChngReqDetId
	 *            the primary key value of the TSalesPosChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosChngReqDet findTSalesPosChngReqDetById(final Long tSalesPosChngReqDetId) {
		LOGGER.info("find TSalesPosChngReqDet instance with spChngReqId: " + tSalesPosChngReqDetId);
		return genericDAO.get(clazz, tSalesPosChngReqDetId);
	}

	/**
	 * Retrieve TSalesPosChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosChngReqDet> list of TSalesPosChngReqDet if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosChngReqDet> findTSalesPosChngReqDets(final SearchFilter<TSalesPosChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TSalesPosChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosChngReqDet tSalesPosChngReqDet = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosChngReqDetentity", tSalesPosChngReqDet);

		if (tSalesPosChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tSalesPosChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tSalesPosChngReqDet.getTChngReq());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosChngReqDets(final SearchFilter<TSalesPosChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TSalesPosChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosChngReqDet tSalesPosChngReqDet = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosChngReqDetentity", tSalesPosChngReqDet);


		if (tSalesPosChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tSalesPosChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tSalesPosChngReqDet.getTChngReq());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

//	/**
//	 * Retrieve TSalesPosChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TSalesPosChngReqDet> list of TSalesPosChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	public List<TSalesPosChngReqDet> getTSalesPosChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		List<Object> queryParam=new ArrayList<Object> ();
//		queryParam.add(searchFilter.getEntityClass());final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTSalesPosChngReqDetByTChngReqDetail", queryParam, index,
//				maxresult);
//	}
//
//	/**
//	 * Count TSalesPosChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTSalesPosChngReqDetsByTChngReqDetail(final SearchFilter<TChngReqDetail> searchFilter) {
//
//		List<Object> queryParam=new ArrayList<Object> ();
//		queryParam.add(searchFilter.getEntityClass());
//		return genericDAO.findEntitiesByNamedQuery("CountTSalesPosChngReqDetsByTChngReqDetail", queryParam);
//	}
	
	 /*Added By 407986*/
		/**
		 * Gets the sales position req details.
		 *
		 * @param params the params
		 * @return the sales position req details
		 */
	@Override
	public List<Object> getSalesPositionReqDetails(List<Object> params){
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetSalePositionReqDetails", params, 0, -1) ;
		
	}
	public 	List<TSalesPosChngReqDet> fetchDetailList(List<Object> params,String Query){
		return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0, -1);
	}
	
	//Added For SP CR Locking
	@Override
    public Object getSPPendingCR(Long salesPositionId, Long salesHiearchyID,
                  List<Integer> triggerList, List<Integer> statusList,
                  String queryName , Short tenantId) {

           List<Object> queryParam = new ArrayList<>();
           queryParam.add(salesPositionId);
           queryParam.add(salesHiearchyID);
           queryParam.add(triggerList);
           queryParam.add(statusList);
           queryParam.add(tenantId) ;

           return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
                        queryParam, 0, -1);

    }
	
	//Added For SP CR Locking
	@Override
    public Object getLockedSP(Long salesPositionId, Long salesHiearchyID,
                  List<Integer> triggerList, List<Integer> statusList,
                  String queryName ,Short tenantId) {
           
           List <Object>queryParam = new ArrayList<>();
           queryParam.add(salesPositionId);
           queryParam.add(salesHiearchyID);
           queryParam.add(triggerList);
           queryParam.add(statusList);
           queryParam.add(tenantId) ;

           return genericDAO.countEntitiesByNamedQueryMultiCond(queryName,
                        queryParam, 0, -1);
           
    }
	
	// Added For CR Locking
	@Override
    public List<Object[]> getSPPendingCRForZip(List<Long> salesPositionIds, List<Long> salesHiearchyIds,
                  List<Integer> triggerList, List<Integer> statusList,
                  String queryName , Short tenantId) {

           List<Object> queryParam = new ArrayList<>();
           queryParam.add(salesPositionIds);
           queryParam.add(salesHiearchyIds);
           queryParam.add(triggerList);
           queryParam.add(statusList);
           queryParam.add(tenantId) ;

           return genericDAO.findEntitiesByNamedQueryMultiCond(queryName, queryParam, 0, -1);
    }

}
