package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TWkflwStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TWkflwStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tWkflwStatusDAO")
public class TWkflwStatusDAOImpl implements TWkflwStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TWkflwStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TWkflwStatus> clazz;

	public Class<TWkflwStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TWkflwStatusDAOImpl() {
		super();
		this.clazz = TWkflwStatus.class;
	}

	private static final String JPAQL = "select tWkflwStatusentity from TWkflwStatus tWkflwStatusentity";

	private static final String JPACOUNTQL = "select count(tWkflwStatusentity) from TWkflwStatus tWkflwStatusentity";

	private static final String[] RESTRICTIONS = {
			"tWkflwStatusentity.statusId = #{tWkflwStatusentity.getStatusId}",
			"tWkflwStatusentity.statusName like '#{tWkflwStatusentity.getStatusName}%'",
			"tWkflwStatusentity.statusDesc like '#{tWkflwStatusentity.getStatusDesc}%'",
			"tWkflwStatusentity.activeFlag = #{tWkflwStatusentity.getActiveFlag}",
			"tWkflwStatusentity.createdBy = #{tWkflwStatusentity.getCreatedBy}",
			"Date(tWkflwStatusentity.createDt) = '#{tWkflwStatusentity.getCreateDt}'",
			"tWkflwStatusentity.updatedBy = #{tWkflwStatusentity.getUpdatedBy}",
			"Date(tWkflwStatusentity.updateDt) = '#{tWkflwStatusentity.getUpdateDt}'",
			"tWkflwStatusentity.tenantId = #{tWkflwStatusentity.getTenantId}" };

	/**
	 * Stores a new TWkflwStatus entity object in to the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be persisted
	 * @return tWkflwStatus Persisted TWkflwStatus object
	 */
	public TWkflwStatus createTWkflwStatus(final TWkflwStatus tWkflwStatus) {
		LOGGER.info("=========== Create TWkflwStatus ===========");
		return genericDAO.store(tWkflwStatus);
	}

	/**
	 * Deletes a TWkflwStatus entity object from the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be deleted
	 */
	public void deleteTWkflwStatus(final Integer statusId) {
		LOGGER.info("=========== Delete TWkflwStatus ===========");
		final TWkflwStatus tWkflwStatus = genericDAO.get(clazz, statusId);
		genericDAO.remove(tWkflwStatus);
	}

	/**
	 * Updates a TWkflwStatus entity object in to the persistent store
	 * 
	 * @param tWkflwStatus
	 *            TWkflwStatus Entity object to be updated
	 * @return tWkflwStatus Persisted TWkflwStatus object
	 */
	public TWkflwStatus updateTWkflwStatus(final TWkflwStatus tWkflwStatus) {
		LOGGER.info("=========== Update TWkflwStatus ===========");
		return genericDAO.update(tWkflwStatus);
	}

	/**
	 * Retrieve an TWkflwStatus object based on given TWkflwStatus statusId.
	 * 
	 * @param tWkflwStatusId
	 *            the primary key value of the TWkflwStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TWkflwStatus findTWkflwStatusById(final Integer tWkflwStatusId) {
		LOGGER.info("find TWkflwStatus instance with statusId: "
				+ tWkflwStatusId);
		return genericDAO.get(clazz, tWkflwStatusId);
	}

	/**
	 * Retrieve TWkflwStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TWkflwStatus> list of TWkflwStatus if it exists against
	 *         given criteria. Returns null if not found
	 */
	public List<TWkflwStatus> findTWkflwStatuss(
			final SearchFilter<TWkflwStatus> searchFilter) {
		LOGGER.info("=========== Find TWkflwStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TWkflwStatus tWkflwStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tWkflwStatusentity",
				tWkflwStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TWkflwStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTWkflwStatuss(
			final SearchFilter<TWkflwStatus> searchFilter) {
		LOGGER.info("=========== Count TWkflwStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TWkflwStatus tWkflwStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tWkflwStatusentity",
				tWkflwStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public List<TWkflwStatus> findAllTWkflwStatus() {
		return genericDAO.findEntitiesByNamedQuery("FindAllTWkflwStatuss");
	}

	@Override
	public List<TWkflwStatus> findAllTWkflwStatusByTenantID() {
		return genericDAO
				.findEntitiesByNamedQuery("FindAllTWkflwStatusByTenantID");
	}

	
	
	@Override
	public List<TWkflwStatus> findAllTWkflwStatusByTenantID(Short tenantId) {
		
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTWkflwStatusByLoggedTenantId", params, 0, -1);
	}
	@Override
	public List<TWkflwStatus> FindAllTWkflwStatus(Short tenantId,String localeId) {
		
		List<Object> params = new ArrayList<Object>();
		params.add(tenantId);
		params.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTWkflwStatus", params, 0, -1);
	}
	
	
	
}
