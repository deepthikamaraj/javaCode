package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TDsStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TDsStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tDsStatusDAO")
public class TDsStatusDAOImpl implements TDsStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TDsStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TDsStatus> clazz;

	public Class<TDsStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TDsStatusDAOImpl() {
		super();
		this.clazz = TDsStatus.class;
	}

	private static final String JPAQL = "select tDsStatusentity from TDsStatus tDsStatusentity";

	private static final String JPACOUNTQL = "select count(tDsStatusentity) from TDsStatus tDsStatusentity";

	private static final String[] RESTRICTIONS = { "tDsStatusentity.statusId = #{tDsStatusentity.getStatusId}",
			"tDsStatusentity.statusCd like '#{tDsStatusentity.getStatusCd}%'",
			"tDsStatusentity.statusDesc like '#{tDsStatusentity.getStatusDesc}%'",
			"tDsStatusentity.createdBy = #{tDsStatusentity.getCreatedBy}",
			"Date(tDsStatusentity.createDt) = '#{tDsStatusentity.getCreateDt}'",
			"tDsStatusentity.updatedBy = #{tDsStatusentity.getUpdatedBy}",
			"Date(tDsStatusentity.updateDt) = '#{tDsStatusentity.getUpdateDt}'",
			"tDsStatusentity.tenantId = #{tDsStatusentity.getTenantId}" };

	/**
	 * Stores a new TDsStatus entity object in to the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be persisted
	 * @return tDsStatus Persisted TDsStatus object
	 */
	public TDsStatus createTDsStatus(final TDsStatus tDsStatus) {
		LOGGER.info("=========== Create TDsStatus ===========");
		return genericDAO.store(tDsStatus);
	}

	/**
	 * Deletes a TDsStatus entity object from the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be deleted
	 */
	public void deleteTDsStatus(final Integer statusId) {
		LOGGER.info("=========== Delete TDsStatus ===========");
		final TDsStatus tDsStatus = genericDAO.get(clazz, statusId);
		genericDAO.remove(tDsStatus);
	}

	/**
	 * Updates a TDsStatus entity object in to the persistent store
	 * 
	 * @param tDsStatus
	 *            TDsStatus Entity object to be updated
	 * @return tDsStatus Persisted TDsStatus object
	 */
	public TDsStatus updateTDsStatus(final TDsStatus tDsStatus) {
		LOGGER.info("=========== Update TDsStatus ===========");
		return genericDAO.update(tDsStatus);
	}

	/**
	 * Retrieve an TDsStatus object based on given TDsStatus statusId.
	 * 
	 * @param tDsStatusId
	 *            the primary key value of the TDsStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TDsStatus findTDsStatusById(final Integer tDsStatusId) {
		LOGGER.info("find TDsStatus instance with statusId: " + tDsStatusId);
		return genericDAO.get(clazz, tDsStatusId);
	}
	
	
	/**
	 * Retrieve an TDsStatus object based on given TDsStatus statusId.
	 * 
	 * @param tDsStatusId
	 *            the primary key value of the TDsStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public List<TDsStatus> findTDsStatusByIdAndTenantId(final Integer tDsStatusId, final String locale, final Short tDsTenantId) {
		List paramList = new ArrayList();
		paramList.add(tDsStatusId);
		paramList.add(locale);
		paramList.add(tDsTenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTDsStatusByStsIdAndTntId",paramList, 0, -1);
	}

	/**
	 * Retrieve TDsStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TDsStatus> list of TDsStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TDsStatus> findTDsStatuss(final SearchFilter<TDsStatus> searchFilter) {
		LOGGER.info("=========== Find TDsStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TDsStatus tDsStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tDsStatusentity", tDsStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TDsStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTDsStatuss(final SearchFilter<TDsStatus> searchFilter) {
		LOGGER.info("=========== Count TDsStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TDsStatus tDsStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tDsStatusentity", tDsStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/**
	 * Find t ds created by id.
	 *
	 * @param createdById the created by id
	 * @return the list
	 */
	@Override
	public List<TDsStatus> findTDsCreatedById(Integer createdById) {
		List<Object> createdByIdList = new ArrayList<Object>();
		createdByIdList.add(createdById);
		List<TDsStatus> tDsStatus = genericDAO.findEntitiesByNamedQuery("FindTDsStatussByCreatedBy", createdByIdList);
	
		return tDsStatus;
	}

}
