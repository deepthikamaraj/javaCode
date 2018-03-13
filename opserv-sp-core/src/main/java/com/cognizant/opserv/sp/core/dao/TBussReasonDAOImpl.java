package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussReasonDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussReasonDAO")
public class TBussReasonDAOImpl implements TBussReasonDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TBussReasonDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TBussReason> clazz;

	public Class<TBussReason> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussReasonDAOImpl() {
		super();
		this.clazz = TBussReason.class;
	}

	private static final String JPAQL = "select tBussReasonentity from TBussReason tBussReasonentity";

	private static final String JPACOUNTQL = "select count(tBussReasonentity) from TBussReason tBussReasonentity";

	private static final String[] RESTRICTIONS = {
			"tBussReasonentity.bussReasonId = #{tBussReasonentity.getBussReasonId}",
			"tBussReasonentity.reasonCd like '#{tBussReasonentity.getReasonCd}%'",
			"tBussReasonentity.reason like '#{tBussReasonentity.getReason}%'",
			"tBussReasonentity.reasonDesc like '#{tBussReasonentity.getReasonDesc}%'",
			"tBussReasonentity.activeFlag = #{tBussReasonentity.getActiveFlag}",
			"Date(tBussReasonentity.createDt) = '#{tBussReasonentity.getCreateDt}'",
			"tBussReasonentity.createdBy = #{tBussReasonentity.getCreatedBy}",
			"Date(tBussReasonentity.updateDt) = '#{tBussReasonentity.getUpdateDt}'",
			"tBussReasonentity.updatedBy = #{tBussReasonentity.getUpdatedBy}",
			"tBussReasonentity.tenantId = #{tBussReasonentity.getTenantId}" };

	/**
	 * Stores a new TBussReason entity object in to the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be persisted
	 * @return tBussReason Persisted TBussReason object
	 */
	public TBussReason createTBussReason(final TBussReason tBussReason) {
		LOGGER.info("=========== Create TBussReason ===========");
		return genericDAO.store(tBussReason);
	}

	/**
	 * Deletes a TBussReason entity object from the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be deleted
	 */
	public void deleteTBussReason(final Integer bussReasonId) {
		LOGGER.info("=========== Delete TBussReason ===========");
		final TBussReason tBussReason = genericDAO.get(clazz, bussReasonId);
		genericDAO.remove(tBussReason);
	}

	/**
	 * Updates a TBussReason entity object in to the persistent store
	 * 
	 * @param tBussReason
	 *            TBussReason Entity object to be updated
	 * @return tBussReason Persisted TBussReason object
	 */
	public TBussReason updateTBussReason(final TBussReason tBussReason) {
		LOGGER.info("=========== Update TBussReason ===========");
		return genericDAO.update(tBussReason);
	}

	/**
	 * Retrieve an TBussReason object based on given TBussReason bussReasonId.
	 * 
	 * @param tBussReasonId
	 *            the primary key value of the TBussReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussReason findTBussReasonById(final Integer tBussReasonId) {
		LOGGER.info("find TBussReason instance with bussReasonId: " + tBussReasonId);
		return genericDAO.get(clazz, tBussReasonId);
	}

	/**
	 * Retrieve TBussReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussReason> list of TBussReason if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussReason> findTBussReasons(final SearchFilter<TBussReason> searchFilter) {
		LOGGER.info("=========== Find TBussReasons ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussReason tBussReason = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussReasonentity", tBussReason);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussReasons.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussReasons(final SearchFilter<TBussReason> searchFilter) {
		LOGGER.info("=========== Count TBussReason ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussReason tBussReason = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussReasonentity", tBussReason);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findTBussReasons(final Long algmntId, final Long bussUnitId, final Long salesTeamId, 
			final Integer trTypeId, final Integer custCategoryId, final Short tenantId) {
		LOGGER.info("=========== Find TBusinessReasons by given Criteria===========");
		
		@SuppressWarnings("rawtypes")
		List paramList = new ArrayList();
		paramList.add(algmntId);
		paramList.add(bussUnitId);
		paramList.add(salesTeamId);
		paramList.add(trTypeId);
		paramList.add(custCategoryId);
		paramList.add(tenantId);
		
		List<Object[]> objList = genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussReasonBytrType", paramList, 0, -1);
		return objList;
	}

}