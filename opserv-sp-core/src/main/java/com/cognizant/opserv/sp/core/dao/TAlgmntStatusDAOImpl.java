package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TAlgmntStatus;
import com.cognizant.opserv.sp.core.entity.TAlgmntStatusId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAlgmntStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAlgmntStatusDAO")
public class TAlgmntStatusDAOImpl implements TAlgmntStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAlgmntStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAlgmntStatus> clazz;

	public Class<TAlgmntStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAlgmntStatusDAOImpl() {
		super();
		this.clazz = TAlgmntStatus.class;
	}

	private static final String JPAQL = "select tAlgmntStatusentity from TAlgmntStatus tAlgmntStatusentity";

	private static final String JPACOUNTQL = "select count(tAlgmntStatusentity) from TAlgmntStatus tAlgmntStatusentity";

	private static final String[] RESTRICTIONS = { "tAlgmntStatusentity.tAlgmntStatusId.statusId = #{tAlgmntStatusentity.tAlgmntStatusId.getStatusId}",
			"tAlgmntStatusentity.tAlgmntStatusId.localeId = #{tAlgmntStatusentity.tAlgmntStatusId.getLocaleId}",
			"tAlgmntStatusentity.statusDesc like '#{tAlgmntStatusentity.getStatusDesc}%'",
			"tAlgmntStatusentity.activeFlag = #{tAlgmntStatusentity.getActiveFlag}",
			"tAlgmntStatusentity.createdBy = #{tAlgmntStatusentity.getCreatedBy}",
			"Date(tAlgmntStatusentity.createDt) = '#{tAlgmntStatusentity.getCreateDt}'",
			"tAlgmntStatusentity.updatedBy = #{tAlgmntStatusentity.getUpdatedBy}",
			"Date(tAlgmntStatusentity.updateDt) = '#{tAlgmntStatusentity.getUpdateDt}'",
			"tAlgmntStatusentity.tenantId = #{tAlgmntStatusentity.getTenantId}" };

	/**
	 * Stores a new TAlgmntStatus entity object in to the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be persisted
	 * @return tAlgmntStatus Persisted TAlgmntStatus object
	 */
	public TAlgmntStatus createTAlgmntStatus(final TAlgmntStatus tAlgmntStatus) {
		LOGGER.info("=========== Create TAlgmntStatus ===========");
		return genericDAO.store(tAlgmntStatus);
	}

	/**
	 * Deletes a TAlgmntStatus entity object from the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be deleted
	 */
	public void deleteTAlgmntStatus(final Integer statusId) {
		LOGGER.info("=========== Delete TAlgmntStatus ===========");
		final TAlgmntStatus tAlgmntStatus = genericDAO.get(clazz, statusId);
		genericDAO.remove(tAlgmntStatus);
	}

	/**
	 * Updates a TAlgmntStatus entity object in to the persistent store
	 * 
	 * @param tAlgmntStatus
	 *            TAlgmntStatus Entity object to be updated
	 * @return tAlgmntStatus Persisted TAlgmntStatus object
	 */
	public TAlgmntStatus updateTAlgmntStatus(final TAlgmntStatus tAlgmntStatus) {
		LOGGER.info("=========== Update TAlgmntStatus ===========");
		return genericDAO.update(tAlgmntStatus);
	}

	/**
	 * Retrieve an TAlgmntStatus object based on given TAlgmntStatus statusId.
	 * 
	 * @param tAlgmntStatusId
	 *            the primary key value of the TAlgmntStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAlgmntStatus findTAlgmntStatusById(final TAlgmntStatusId tAlgmntStatusId) {
		LOGGER.info("find TAlgmntStatus instance with statusId: " + tAlgmntStatusId);
		return genericDAO.get(clazz, tAlgmntStatusId);
	}

	/**
	 * Retrieve TAlgmntStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAlgmntStatus> list of TAlgmntStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAlgmntStatus> findTAlgmntStatuss(final SearchFilter<TAlgmntStatus> searchFilter) {
		LOGGER.info("=========== Find TAlgmntStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAlgmntStatus tAlgmntStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAlgmntStatusentity", tAlgmntStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAlgmntStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAlgmntStatuss(final SearchFilter<TAlgmntStatus> searchFilter) {
		LOGGER.info("=========== Count TAlgmntStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAlgmntStatus tAlgmntStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAlgmntStatusentity", tAlgmntStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/**
	 * Find t algmnt status by ids.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<TAlgmntStatus> findTAlgmntStatusByIds(Short tenantId,
			String localeId) {
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);
		paramList.add(ApplicationConstant.FLAG_YES);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAlgmntStatus", paramList, 0, -1);
	}

}
