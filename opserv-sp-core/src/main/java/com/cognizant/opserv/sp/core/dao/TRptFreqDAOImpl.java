package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRptFreq;
import com.cognizant.opserv.sp.core.entity.TRptFreqId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptFreqDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptFreqDAO")
public class TRptFreqDAOImpl implements TRptFreqDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptFreqDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRptFreq> clazz;

	public Class<TRptFreq> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptFreqDAOImpl() {
		super();
		this.clazz = TRptFreq.class;
	}

	private static final String JPAQL = "select tRptFreqentity from TRptFreq tRptFreqentity";

	private static final String JPACOUNTQL = "select count(tRptFreqentity) from TRptFreq tRptFreqentity";

	private static final String[] RESTRICTIONS = { "tRptFreqentity.tRptFreqId.rptFreqId = #{tRptFreqentity.tRptFreqId.getRptFreqId}",
			"tRptFreqentity.freqType like '#{tRptFreqentity.getFreqType}%'",
			"tRptFreqentity.activeFlag = #{tRptFreqentity.getActiveFlag}",
			"tRptFreqentity.createdBy = #{tRptFreqentity.getCreatedBy}",
			"Date(tRptFreqentity.createDt) = '#{tRptFreqentity.getCreateDt}'",
			"tRptFreqentity.updatedBy = #{tRptFreqentity.getUpdatedBy}",
			"Date(tRptFreqentity.updateDate) = '#{tRptFreqentity.getUpdateDate}'",
			"tRptFreqentity.tenantId = #{tRptFreqentity.getTenantId}" };

	/**
	 * Stores a new TRptFreq entity object in to the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be persisted
	 * @return tRptFreq Persisted TRptFreq object
	 */
	public TRptFreq createTRptFreq(final TRptFreq tRptFreq) {
		LOGGER.info("=========== Create TRptFreq ===========");
		return genericDAO.store(tRptFreq);
	}

	/**
	 * Deletes a TRptFreq entity object from the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be deleted
	 */
	public void deleteTRptFreq(final Integer rptFreqId) {
		LOGGER.info("=========== Delete TRptFreq ===========");
		final TRptFreq tRptFreq = genericDAO.get(clazz, rptFreqId);
		genericDAO.remove(tRptFreq);
	}

	/**
	 * Updates a TRptFreq entity object in to the persistent store
	 * 
	 * @param tRptFreq
	 *            TRptFreq Entity object to be updated
	 * @return tRptFreq Persisted TRptFreq object
	 */
	public TRptFreq updateTRptFreq(final TRptFreq tRptFreq) {
		LOGGER.info("=========== Update TRptFreq ===========");
		return genericDAO.update(tRptFreq);
	}

	/**
	 * Retrieve an TRptFreq object based on given TRptFreq rptFreqId.
	 * 
	 * @param tRptFreqId
	 *            the primary key value of the TRptFreq Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptFreq findTRptFreqById(final TRptFreqId tRptFreqId) {
		LOGGER.info("find TRptFreq instance with rptFreqId: " + tRptFreqId);
		return genericDAO.get(clazz, tRptFreqId);
	}

	/**
	 * Retrieve TRptFreq based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptFreq> list of TRptFreq if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptFreq> findTRptFreqs(final SearchFilter<TRptFreq> searchFilter) {
		LOGGER.info("=========== Find TRptFreqs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptFreq tRptFreq = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptFreqentity", tRptFreq);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptFreqs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptFreqs(final SearchFilter<TRptFreq> searchFilter) {
		LOGGER.info("=========== Count TRptFreq ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptFreq tRptFreq = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptFreqentity", tRptFreq);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
