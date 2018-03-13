package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TNoteStatus;
import com.cognizant.opserv.sp.core.entity.TNoteStatusId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TNoteStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tNoteStatusDAO")
public class TNoteStatusDAOImpl implements TNoteStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TNoteStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TNoteStatus> clazz;

	public Class<TNoteStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TNoteStatusDAOImpl() {
		super();
		this.clazz = TNoteStatus.class;
	}

	private static final String JPAQL = "select tNoteStatusentity from TNoteStatus tNoteStatusentity";

	private static final String JPACOUNTQL = "select count(tNoteStatusentity) from TNoteStatus tNoteStatusentity";

	private static final String[] RESTRICTIONS = {
			"tNoteStatusentity.tNoteStatusId.noteStatusId = #{tNoteStatusentity.tNoteStatusId.getNoteStatusId}",
			"tNoteStatusentity.tNoteStatusId.localeId = #{tNoteStatusentity.tNoteStatusId.getLocaleId}",
			"tNoteStatusentity.noteStatusName like '#{tNoteStatusentity.getNoteStatusName}%'",
			"tNoteStatusentity.noteStatusDesc like '#{tNoteStatusentity.getNoteStatusDesc}%'",
			"tNoteStatusentity.activeFlag = #{tNoteStatusentity.getActiveFlag}",
			"tNoteStatusentity.createdBy = #{tNoteStatusentity.getCreatedBy}",
			"Date(tNoteStatusentity.createDt) = '#{tNoteStatusentity.getCreateDt}'",
			"tNoteStatusentity.updatedBy = #{tNoteStatusentity.getUpdatedBy}",
			"Date(tNoteStatusentity.updateDate) = '#{tNoteStatusentity.getUpdateDate}'",
			"tNoteStatusentity.tenantId = #{tNoteStatusentity.getTenantId}" };

	/**
	 * Stores a new TNoteStatus entity object in to the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be persisted
	 * @return tNoteStatus Persisted TNoteStatus object
	 */
	public TNoteStatus createTNoteStatus(final TNoteStatus tNoteStatus) {
		LOGGER.info("=========== Create TNoteStatus ===========");
		return genericDAO.store(tNoteStatus);
	}

	/**
	 * Deletes a TNoteStatus entity object from the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be deleted
	 */
	public void deleteTNoteStatus(final Integer noteStatusId) {
		LOGGER.info("=========== Delete TNoteStatus ===========");
		final TNoteStatus tNoteStatus = genericDAO.get(clazz, noteStatusId);
		genericDAO.remove(tNoteStatus);
	}

	/**
	 * Updates a TNoteStatus entity object in to the persistent store
	 * 
	 * @param tNoteStatus
	 *            TNoteStatus Entity object to be updated
	 * @return tNoteStatus Persisted TNoteStatus object
	 */
	public TNoteStatus updateTNoteStatus(final TNoteStatus tNoteStatus) {
		LOGGER.info("=========== Update TNoteStatus ===========");
		return genericDAO.update(tNoteStatus);
	}

	/**
	 * Retrieve an TNoteStatus object based on given TNoteStatus noteStatusId.
	 * 
	 * @param tNoteStatusId
	 *            the primary key value of the TNoteStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TNoteStatus findTNoteStatusById(final TNoteStatusId tNoteStatusId) {
		LOGGER.info("find TNoteStatus instance with noteStatusId: " + tNoteStatusId);
		return genericDAO.get(clazz, tNoteStatusId);
	}

	/**
	 * Retrieve TNoteStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNoteStatus> list of TNoteStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TNoteStatus> findTNoteStatuss(final SearchFilter<TNoteStatus> searchFilter) {
		LOGGER.info("=========== Find TNoteStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TNoteStatus tNoteStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tNoteStatusentity", tNoteStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TNoteStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTNoteStatuss(final SearchFilter<TNoteStatus> searchFilter) {
		LOGGER.info("=========== Count TNoteStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TNoteStatus tNoteStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tNoteStatusentity", tNoteStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
