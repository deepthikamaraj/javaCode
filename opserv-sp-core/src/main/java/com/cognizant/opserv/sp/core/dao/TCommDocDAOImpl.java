package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommDoc;
import com.cognizant.opserv.sp.core.entity.TCommDocId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCommDocDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCommDocDAO")
public class TCommDocDAOImpl implements TCommDocDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCommDocDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCOMM = "tComm";

	private final Class<TCommDoc> clazz;

	public Class<TCommDoc> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCommDocDAOImpl() {
		super();
		this.clazz = TCommDoc.class;
	}

	private static final String JPAQL = "select tCommDocentity from TCommDoc tCommDocentity";

	private static final String JPACOUNTQL = "select count(*) from TCommDoc tCommDocentity";

	private static final String[] RESTRICTIONS = {
			"tCommDocentity.tCommDocId.docId = #{tCommDocentity.tCommDocId.getDocId}",
			"tCommDocentity.tCommDocId.commId = #{tCommDocentity.tCommDocId.getCommId}",
			"tCommDocentity.docLocation like '#{tCommDocentity.getDocLocation}%'",
			"Date(tCommDocentity.publishedDt) = '#{tCommDocentity.getPublishedDt}'",
			"tCommDocentity.activeFlag = #{tCommDocentity.getActiveFlag}",
			"tCommDocentity.createdBy = #{tCommDocentity.getCreatedBy}",
			"Date(tCommDocentity.createDt) = '#{tCommDocentity.getCreateDt}'",
			"tCommDocentity.updatedBy = #{tCommDocentity.getUpdatedBy}",
			"Date(tCommDocentity.updateDate) = '#{tCommDocentity.getUpdateDate}'",
			"tCommDocentity.tenantId = #{tCommDocentity.getTenantId}",
			"tCommDocentity.docName like '#{tCommDocentity.getDocName}%'",
			"tCommDocentity.tComm.commId = #{tCommDocentity.tComm.getCommId}" };

	/**
	 * Stores a new TCommDoc entity object in to the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be persisted
	 * @return tCommDoc Persisted TCommDoc object
	 */
	public TCommDoc createTCommDoc(final TCommDoc tCommDoc) {
		LOGGER.info("=========== Create TCommDoc ===========");
		return genericDAO.store(tCommDoc);
	}

	/**
	 * Deletes a TCommDoc entity object from the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be deleted
	 */
	public void deleteTCommDoc(final TCommDocId tCommDocId) {
		LOGGER.info("=========== Delete TCommDoc ===========");
		final TCommDoc tCommDoc = genericDAO.get(clazz, tCommDocId);
		genericDAO.remove(tCommDoc);
	}

	/**
	 * Updates a TCommDoc entity object in to the persistent store
	 * 
	 * @param tCommDoc
	 *            TCommDoc Entity object to be updated
	 * @return tCommDoc Persisted TCommDoc object
	 */
	public TCommDoc updateTCommDoc(final TCommDoc tCommDoc) {
		LOGGER.info("=========== Update TCommDoc ===========");
		return genericDAO.update(tCommDoc);
	}

	/**
	 * Retrieve an TCommDoc object based on given TCommDoc TCommDocId.
	 * 
	 * @param tCommDocId
	 *            the primary key value of the TCommDoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCommDoc findTCommDocById(final TCommDocId tCommDocId) {
		LOGGER.info("find TCommDoc instance with TCommDocId: " + tCommDocId);
		return genericDAO.get(clazz, tCommDocId);
	}

	/**
	 * Retrieve TCommDoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommDoc> list of TCommDoc if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCommDoc> findTCommDocs(final SearchFilter<TCommDoc> searchFilter) {
		LOGGER.info("=========== Find TCommDocs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCommDoc tCommDoc = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCommDocentity", tCommDoc);

		if (tCommDoc.getTComm() == null) {
			jpaQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommDoc.getTComm());

			jpaQuery.bind(TCOMM, tCommDoc.getTComm());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCommDocs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCommDocs(final SearchFilter<TCommDoc> searchFilter) {
		LOGGER.info("=========== Count TCommDoc ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCommDoc tCommDoc = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCommDocentity", tCommDoc);

		if (tCommDoc.getTComm() == null) {
			jpaCountQuery.bind(TCOMM, new TComm());
		} else {
			LOGGER.info("tComm {}" + tCommDoc.getTComm());

			jpaCountQuery.bind(TCOMM, tCommDoc.getTComm());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCommDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCommDoc> list of TCommDocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCommDoc> getTCommDocsByTComm(final SearchFilter<TComm> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TComm tComm = searchFilter.getEntityClass();
		List<Object> tCommList = new ArrayList<Object>();
		tCommList.add(tComm);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCommDocByTComm", tCommList, index, maxresult);
	}

	/**
	 * Count TCommDoc based on given search criteria using JPA named Query.
	 * The search criteria is of TComm type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCommDocsByTComm(final SearchFilter<TComm> searchFilter) {

		final TComm tComm = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tComm);
		return genericDAO.countEntitiesByNamedQueryMultiCond("CountTCommDocsByTComm", queryParam, 0, -1);
	}
	/**
	 * Count t comm docs by t comm ids.
	 *
	 * @param commids the commids
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> countTCommDocsByTCommIds(List commids, short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(commids);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("CountTCommDocsByTCommids", queryParam, 0, -1);
	}

}
