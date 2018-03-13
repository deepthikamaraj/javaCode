package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRpt;
import com.cognizant.opserv.sp.core.entity.TRptDoc;
import com.cognizant.opserv.sp.core.entity.TRptDocId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRptDocDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRptDocDAO")
public class TRptDocDAOImpl implements TRptDocDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TRptDocDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TRPT = "tRpt";

	private final Class<TRptDoc> clazz;

	public Class<TRptDoc> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRptDocDAOImpl() {
		super();
		this.clazz = TRptDoc.class;
	}

	private static final String JPAQL = "select tRptDocentity from TRptDoc tRptDocentity";

	private static final String JPACOUNTQL = "select count(*) from TRptDoc tRptDocentity";

	private static final String[] RESTRICTIONS = {
			"tRptDocentity.tRptDocId.docId = #{tRptDocentity.tRptDocId.getDocId}",
			"tRptDocentity.tRptDocId.rptId = #{tRptDocentity.tRptDocId.getRptId}",
			"tRptDocentity.docName like '#{tRptDocentity.getDocName}%'",
			"tRptDocentity.activeFlag = #{tRptDocentity.getActiveFlag}",
			"tRptDocentity.createdBy = #{tRptDocentity.getCreatedBy}",
			"Date(tRptDocentity.createDt) = '#{tRptDocentity.getCreateDt}'",
			"tRptDocentity.updatedBy = #{tRptDocentity.getUpdatedBy}",
			"Date(tRptDocentity.updateDate) = '#{tRptDocentity.getUpdateDate}'",
			"tRptDocentity.tenantId = #{tRptDocentity.getTenantId}",
			"tRptDocentity.tRpt.rptId = #{tRptDocentity.tRpt.getRptId}" };

	/**
	 * Stores a new TRptDoc entity object in to the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be persisted
	 * @return tRptDoc Persisted TRptDoc object
	 */
	public TRptDoc createTRptDoc(final TRptDoc tRptDoc) {
		LOGGER.info("=========== Create TRptDoc ===========");
		return genericDAO.store(tRptDoc);
	}

	/**
	 * Deletes a TRptDoc entity object from the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be deleted
	 */
	public void deleteTRptDoc(final TRptDocId tRptDocId) {
		LOGGER.info("=========== Delete TRptDoc ===========");
		final TRptDoc tRptDoc = genericDAO.get(clazz, tRptDocId);
		genericDAO.remove(tRptDoc);
	}

	/**
	 * Updates a TRptDoc entity object in to the persistent store
	 * 
	 * @param tRptDoc
	 *            TRptDoc Entity object to be updated
	 * @return tRptDoc Persisted TRptDoc object
	 */
	public TRptDoc updateTRptDoc(final TRptDoc tRptDoc) {
		LOGGER.info("=========== Update TRptDoc ===========");
		return genericDAO.update(tRptDoc);
	}

	/**
	 * Retrieve an TRptDoc object based on given TRptDoc TRptDocId.
	 * 
	 * @param tRptDocId
	 *            the primary key value of the TRptDoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRptDoc findTRptDocById(final TRptDocId tRptDocId) {
		LOGGER.info("find TRptDoc instance with TRptDocId: " + tRptDocId);
		return genericDAO.get(clazz, tRptDocId);
	}

	/**
	 * Retrieve TRptDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDoc> list of TRptDoc if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TRptDoc> findTRptDocs(final SearchFilter<TRptDoc> searchFilter) {
		LOGGER.info("=========== Find TRptDocs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRptDoc tRptDoc = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRptDocentity", tRptDoc);

		if (tRptDoc.getTRpt() == null) {
			jpaQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptDoc.getTRpt());

			jpaQuery.bind(TRPT, tRptDoc.getTRpt());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRptDocs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRptDocs(final SearchFilter<TRptDoc> searchFilter) {
		LOGGER.info("=========== Count TRptDoc ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRptDoc tRptDoc = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRptDocentity", tRptDoc);

		if (tRptDoc.getTRpt() == null) {
			jpaCountQuery.bind(TRPT, new TRpt());
		} else {
			LOGGER.info("tRpt {}"+ tRptDoc.getTRpt());

			jpaCountQuery.bind(TRPT, tRptDoc.getTRpt());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TRptDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRptDoc> list of TRptDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRptDoc> getTRptDocsByTRpt(final SearchFilter<TRpt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTRptDocByTRpt", queryParam, index, maxresult);
	}

	/**
	 * Count TRptDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TRpt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTRptDocsByTRpt(final SearchFilter<TRpt> searchFilter) {

		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTRptDocsByTRpt", queryParam);
	}

}
