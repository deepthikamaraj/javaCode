package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRejectReason;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRejectReasonDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRejectReasonDAO")
public class TRejectReasonDAOImpl implements TRejectReasonDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRejectReasonDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRejectReason> clazz;

	public Class<TRejectReason> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRejectReasonDAOImpl() {
		super();
		this.clazz = TRejectReason.class;
	}

	private static final String JPAQL = "select tRejectReasonentity from TRejectReason tRejectReasonentity";

	private static final String JPACOUNTQL = "select count(tRejectReasonentity) from TRejectReason tRejectReasonentity";

	private static final String[] RESTRICTIONS = {
			"tRejectReasonentity.rejectReasonId = #{tRejectReasonentity.getRejectReasonId}",
			"tRejectReasonentity.rejectReasonCd like '#{tRejectReasonentity.getRejectReasonCd}%'",
			"tRejectReasonentity.rejectReasonDesc like '#{tRejectReasonentity.getRejectReasonDesc}%'",
			"tRejectReasonentity.activeFlag = #{tRejectReasonentity.getActiveFlag}",
			"tRejectReasonentity.createdBy = #{tRejectReasonentity.getCreatedBy}",
			"Date(tRejectReasonentity.createDt) = '#{tRejectReasonentity.getCreateDt}'",
			"tRejectReasonentity.updatedBy = #{tRejectReasonentity.getUpdatedBy}",
			"Date(tRejectReasonentity.updateDate) = '#{tRejectReasonentity.getUpdateDate}'",
			"tRejectReasonentity.tenantId = #{tRejectReasonentity.getTenantId}" };

	/**
	 * Stores a new TRejectReason entity object in to the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be persisted
	 * @return tRejectReason Persisted TRejectReason object
	 */
	public TRejectReason createTRejectReason(final TRejectReason tRejectReason) {
		LOGGER.info("=========== Create TRejectReason ===========");
		return genericDAO.store(tRejectReason);
	}

	/**
	 * Deletes a TRejectReason entity object from the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be deleted
	 */
	public void deleteTRejectReason(final Integer rejectReasonId) {
		LOGGER.info("=========== Delete TRejectReason ===========");
		final TRejectReason tRejectReason = genericDAO.get(clazz, rejectReasonId);
		genericDAO.remove(tRejectReason);
	}

	/**
	 * Updates a TRejectReason entity object in to the persistent store
	 * 
	 * @param tRejectReason
	 *            TRejectReason Entity object to be updated
	 * @return tRejectReason Persisted TRejectReason object
	 */
	public TRejectReason updateTRejectReason(final TRejectReason tRejectReason) {
		LOGGER.info("=========== Update TRejectReason ===========");
		return genericDAO.update(tRejectReason);
	}

	/**
	 * Retrieve an TRejectReason object based on given TRejectReason rejectReasonId.
	 * 
	 * @param tRejectReasonId
	 *            the primary key value of the TRejectReason Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRejectReason findTRejectReasonById(final Integer tRejectReasonId) {
		LOGGER.info("find TRejectReason instance with rejectReasonId: " + tRejectReasonId);
		return genericDAO.get(clazz, tRejectReasonId);
	}

	/**
	 * Retrieve TRejectReason based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRejectReason> list of TRejectReason if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRejectReason> findTRejectReasons(final SearchFilter<TRejectReason> searchFilter) {
		LOGGER.info("=========== Find TRejectReasons ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRejectReason tRejectReason = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRejectReasonentity", tRejectReason);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRejectReasons.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRejectReasons(final SearchFilter<TRejectReason> searchFilter) {
		LOGGER.info("=========== Count TRejectReason ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRejectReason tRejectReason = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRejectReasonentity", tRejectReason);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
