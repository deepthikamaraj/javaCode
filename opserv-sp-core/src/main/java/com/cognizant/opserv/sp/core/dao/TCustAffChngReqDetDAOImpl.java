package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.opserv.sp.core.entity.TCustAffChngReqDet;
import com.cognizant.opserv.sp.core.entity.TCustAffChngReqDetId;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAffChngReqDetDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAffChngReqDetDAO")
public class TCustAffChngReqDetDAOImpl implements TCustAffChngReqDetDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAffChngReqDetDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTAFF = "tCustAff";
	private static final String TCUSTALGMNT = "tCustAlgmnt";
	private static final String TCHNGREQ = "tChngReq";
	private static final String TCHNGREQBUSSREASON = "tChngReqBussReason";

	private final Class<TCustAffChngReqDet> clazz;

	public Class<TCustAffChngReqDet> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAffChngReqDetDAOImpl() {
		super();
		this.clazz = TCustAffChngReqDet.class;
	}

	private static final String JPAQL = "select tCustAffChngReqDetentity from TCustAffChngReqDet tCustAffChngReqDetentity";

	private static final String JPACOUNTQL = "select count(*) from TCustAffChngReqDet tCustAffChngReqDetentity";

	private static final String[] RESTRICTIONS = {
			"tCustAffChngReqDetentity.tCustAffChngReqDetId.chngReqId = #{tCustAffChngReqDetentity.tCustAffChngReqDetId.getChngReqId}",
			"tCustAffChngReqDetentity.tCustAffChngReqDetId.chngReqDetailId = #{tCustAffChngReqDetentity.tCustAffChngReqDetId.getChngReqDetailId}",
			"tCustAffChngReqDetentity.tCustAffChngReqDetId.custAffId = #{tCustAffChngReqDetentity.tCustAffChngReqDetId.getCustAffId}",
			"tCustAffChngReqDetentity.statusId = #{tCustAffChngReqDetentity.getStatusId}",
			"tCustAffChngReqDetentity.createdBy = #{tCustAffChngReqDetentity.getCreatedBy}",
			"Date(tCustAffChngReqDetentity.createDt) = '#{tCustAffChngReqDetentity.getCreateDt}'",
			"tCustAffChngReqDetentity.updatedBy = #{tCustAffChngReqDetentity.getUpdatedBy}",
			"Date(tCustAffChngReqDetentity.updateDt) = '#{tCustAffChngReqDetentity.getUpdateDt}'",
			"tCustAffChngReqDetentity.tenantId = #{tCustAffChngReqDetentity.getTenantId}",
			"tCustAffChngReqDetentity.tCustAff.custAffId = #{tCustAffChngReqDetentity.tCustAff.getCustAffId}",
			"tCustAffChngReqDetentity.tCustAlgmnt.custAlgmntId = #{tCustAffChngReqDetentity.tCustAlgmnt.getCustAlgmntId}",
			"tCustAffChngReqDetentity.tChngReq.chngReqId = #{tCustAffChngReqDetentity.tChngReq.getChngReqId}" ,
			"tCustAffChngReqDetentity.tChngReqBussReason.chngReqBrId = #{tCustAffChngReqDetentity.tChngReqBussReason.getChngReqBrId}" };

	/**
	 * Stores a new TCustAffChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be persisted
	 * @return tCustAffChngReqDet Persisted TCustAffChngReqDet object
	 */
	public TCustAffChngReqDet createTCustAffChngReqDet(final TCustAffChngReqDet tCustAffChngReqDet) {
		LOGGER.info("=========== Create TCustAffChngReqDet ===========");
		return genericDAO.store(tCustAffChngReqDet);
	}

	/**
	 * Deletes a TCustAffChngReqDet entity object from the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be deleted
	 */
	public void deleteTCustAffChngReqDet(final TCustAffChngReqDetId tCustAffChngReqDetId) {
		LOGGER.info("=========== Delete TCustAffChngReqDet ===========");
		final TCustAffChngReqDet tCustAffChngReqDet = genericDAO.get(clazz, tCustAffChngReqDetId);
		genericDAO.remove(tCustAffChngReqDet);
	}

	/**
	 * Updates a TCustAffChngReqDet entity object in to the persistent store
	 * 
	 * @param tCustAffChngReqDet
	 *            TCustAffChngReqDet Entity object to be updated
	 * @return tCustAffChngReqDet Persisted TCustAffChngReqDet object
	 */
	public TCustAffChngReqDet updateTCustAffChngReqDet(final TCustAffChngReqDet tCustAffChngReqDet) {
		LOGGER.info("=========== Update TCustAffChngReqDet ===========");
		return genericDAO.update(tCustAffChngReqDet);
	}
	
	public List<TCustAffChngReqDet> updateTCustAffChngReqDet(final List<TCustAffChngReqDet> tCustAffChngReqDetList) {
		LOGGER.info("=========== Update TCustAffChngReqDet ===========");
		return genericDAO.update(tCustAffChngReqDetList);
	}

	/**
	 * Retrieve an TCustAffChngReqDet object based on given TCustAffChngReqDet TCustAffChngReqDetId.
	 * 
	 * @param tCustAffChngReqDetId
	 *            the primary key value of the TCustAffChngReqDet Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAffChngReqDet findTCustAffChngReqDetById(final TCustAffChngReqDetId tCustAffChngReqDetId) {
		LOGGER.info("find TCustAffChngReqDet instance with TCustAffChngReqDetId: " + tCustAffChngReqDetId);
		return genericDAO.get(clazz, tCustAffChngReqDetId);
	}

	/**
	 * Retrieve TCustAffChngReqDet based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDet if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAffChngReqDet> findTCustAffChngReqDets(final SearchFilter<TCustAffChngReqDet> searchFilter) {
		LOGGER.info("=========== Find TCustAffChngReqDets ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAffChngReqDet tCustAffChngReqDet = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAffChngReqDetentity", tCustAffChngReqDet);

		if (tCustAffChngReqDet.getTCustAff() == null) {
			jpaQuery.bind(TCUSTAFF, new TCustAff());
		} else {
			LOGGER.info("tCustAff {}", tCustAffChngReqDet.getTCustAff());

			jpaQuery.bind(TCUSTAFF, tCustAffChngReqDet.getTCustAff());
		}

		if (tCustAffChngReqDet.getTCustAlgmnt() == null) {
			jpaQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}", tCustAffChngReqDet.getTCustAlgmnt());

			jpaQuery.bind(TCUSTALGMNT, tCustAffChngReqDet.getTCustAlgmnt());
		}

		if (tCustAffChngReqDet.getTChngReq() == null) {
			jpaQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustAffChngReqDet.getTChngReq());

			jpaQuery.bind(TCHNGREQ, tCustAffChngReqDet.getTChngReq());
		}
		if (tCustAffChngReqDet.getTChngReqBussReason() == null) {
			jpaQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustAffChngReqDet.getTChngReqBussReason());

			jpaQuery.bind(TCHNGREQBUSSREASON, tCustAffChngReqDet.getTChngReqBussReason());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAffChngReqDets.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAffChngReqDets(final SearchFilter<TCustAffChngReqDet> searchFilter) {
		LOGGER.info("=========== Count TCustAffChngReqDet ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAffChngReqDet tCustAffChngReqDet = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAffChngReqDetentity", tCustAffChngReqDet);

		if (tCustAffChngReqDet.getTCustAff() == null) {
			jpaCountQuery.bind(TCUSTAFF, new TCustAff());
		} else {
			LOGGER.info("tCustAff {}", tCustAffChngReqDet.getTCustAff());

			jpaCountQuery.bind(TCUSTAFF, tCustAffChngReqDet.getTCustAff());
		}

		if (tCustAffChngReqDet.getTCustAlgmnt() == null) {
			jpaCountQuery.bind(TCUSTALGMNT, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmnt {}", tCustAffChngReqDet.getTCustAlgmnt());

			jpaCountQuery.bind(TCUSTALGMNT, tCustAffChngReqDet.getTCustAlgmnt());
		}

		if (tCustAffChngReqDet.getTChngReq() == null) {
			jpaCountQuery.bind(TCHNGREQ, new TChngReq());
		} else {
			LOGGER.info("tChngReq {}", tCustAffChngReqDet.getTChngReq());

			jpaCountQuery.bind(TCHNGREQ, tCustAffChngReqDet.getTChngReq());
		}
		
		if (tCustAffChngReqDet.getTChngReqBussReason() == null) {
			jpaCountQuery.bind(TCHNGREQBUSSREASON, new TChngReqBussReason());
		} else {
			LOGGER.info("tChngReqBussReason {}", tCustAffChngReqDet.getTChngReqBussReason());

			jpaCountQuery.bind(TCHNGREQBUSSREASON, tCustAffChngReqDet.getTChngReqBussReason());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAffChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDets if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAffChngReqDet> getTCustAffChngReqDetsByTCustAff(final SearchFilter<TCustAff> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAff tCustAff = searchFilter.getEntityClass();
		List<Object> tCustAffList = new ArrayList<Object>();
		tCustAffList.add(tCustAff);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffChngReqDetByTCustAff", tCustAffList, index, maxresult);
	}

	/**
	 * Count TCustAffChngReqDet based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAffChngReqDetsByTCustAff(final SearchFilter<TCustAff> searchFilter) {

		final TCustAff tCustAff = searchFilter.getEntityClass();
		List<Object> tCustAffList = new ArrayList<Object>();
		tCustAffList.add(tCustAff);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffChngReqDetsByTCustAff", tCustAffList);
	}

//	/**
//	 * Retrieve TCustAffChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return List<TCustAffChngReqDet> list of TCustAffChngReqDets if it exists against given
//	 *         criteria. Returns null if not found
//	 */
//	public List<TCustAffChngReqDet> getTCustAffChngReqDetsByTChngReqDetail(
//			final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		final int maxresult = paginationInfo.getMaxRows();
//		final int index = paginationInfo.getStartIndex();
//
//		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffChngReqDetByTChngReqDetail", tChngReqDetailList, index,
//				maxresult);
//	}
//
//	/**
//	 * Count TCustAffChngReqDet based on given search criteria using JPA named Query.
//	 * The search criteria is of TChngReqDetail type.
//	 * 
//	 * @param searchFilter
//	 *            The query criteria and search filter conditions are set
//	 * @return a Object indicating the count
//	 */
//	public Object countTCustAffChngReqDetsByTChngReqDetail(final SearchFilter<TChngReqDetail> searchFilter) {
//
//		final TChngReqDetail tChngReqDetail = searchFilter.getEntityClass();
//		List<Object> tChngReqDetailList = new ArrayList<Object>();
//		tChngReqDetailList.add(tChngReqDetail);
//		return genericDAO.findEntitiesByNamedQuery("CountTCustAffChngReqDetsByTChngReqDetail", tChngReqDetailList);
//	}

	public 	List<TCustAffChngReqDet> fetchDetailList(List<Object> params,String Query){
	return genericDAO.findEntitiesByNamedQueryMultiCond(Query, params, 0, -1);
	}

	public 	List<TCustAffChngReqDet> getTCustAffChngReqDetsByCustAffId(Integer custAffId, Short tenantId){
		List<Object> params = new ArrayList<Object>();
		params.add(custAffId);
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAffChngReqDetByCustAffId", params , 0, -1);
		}
	
	
	/*Added By 407986 To fetch all CustomerAffiliationReqDetails by ChngReqId  */
    /**
     * Gets the customer affiliation req details.
     *
     * @param params the params
     * @return the customer affiliation req details
     */
  
    public List<Object> getCustomerAffiliationReqDetails(List<Object> params){
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetCustomerAffiliationReqDetails", params, 0, -1) ;
	}
    /**
     * Gets the cust aff count.
     *
     * @param params the params
     * @param query the query
     * @return the cust aff count
     */
    public List<Object[]> getCustAffCount(List<Object> params,String query){
		
		return genericDAO.findEntitiesByNamedQueryMultiCond(query, params, 0, -1) ;
	}
	
	/**
	 * 
	 * @author 409793 to fetch customer id, if CR forAdd/Remove Customer
	 *         Affiliation is there on same SP for pending submission OR
	 *         approval status and Customer is present in that CR.
	 */
	@Override
	public List<Object[]> getCustPendingCR(List<Long> salesPositionId,
			List<Long> salesHiearchyID, List<Integer> triggerList,
			List<Integer> statusList, List<Integer> custId,Short tenantId) {

		List queryParam = new ArrayList();
		queryParam.add(salesPositionId);
		queryParam.add(salesHiearchyID);
		queryParam.add(triggerList);
		queryParam.add(statusList);
		queryParam.add(custId);
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"CheckForCustLockedInCustAff", queryParam, 0, -1);

	}
	
}
