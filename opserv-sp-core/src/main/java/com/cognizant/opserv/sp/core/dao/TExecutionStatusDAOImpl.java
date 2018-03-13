package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TExecutionStatus;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TExecutionStatusDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tExecutionStatusDAO")
public class TExecutionStatusDAOImpl implements TExecutionStatusDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TExecutionStatusDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TExecutionStatus> clazz;

	public Class<TExecutionStatus> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TExecutionStatusDAOImpl() {
		super();
		this.clazz = TExecutionStatus.class;
	}

	private static final String JPAQL = "select tExecutionStatusentity from TExecutionStatus tExecutionStatusentity";

	private static final String JPACOUNTQL = "select count(tExecutionStatusentity) from TExecutionStatus tExecutionStatusentity";

	private static final String[] RESTRICTIONS = {
			"tExecutionStatusentity.executionStatusId = #{tExecutionStatusentity.getExecutionStatusId}",
			"tExecutionStatusentity.statusDesc like '#{tExecutionStatusentity.getStatusDesc}%'",
			"tExecutionStatusentity.activeFlag like '#{tExecutionStatusentity.getActiveFlag}%'",
			"tExecutionStatusentity.createdBy = #{tExecutionStatusentity.getCreatedBy}",
			"Date(tExecutionStatusentity.createDt) = '#{tExecutionStatusentity.getCreateDt}'",
			"tExecutionStatusentity.updatedBy = #{tExecutionStatusentity.getUpdatedBy}",
			"Date(tExecutionStatusentity.updateDt) = '#{tExecutionStatusentity.getUpdateDt}'",
			"tExecutionStatusentity.tenantId = #{tExecutionStatusentity.getTenantId}" };

	/**
	 * Stores a new TExecutionStatus entity object in to the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be persisted
	 * @return tExecutionStatus Persisted TExecutionStatus object
	 */
	public TExecutionStatus createTExecutionStatus(final TExecutionStatus tExecutionStatus) {
		LOGGER.info("=========== Create TExecutionStatus ===========");
		return genericDAO.store(tExecutionStatus);
	}

	/**
	 * Deletes a TExecutionStatus entity object from the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be deleted
	 */
	public void deleteTExecutionStatus(final Integer executionStatusId) {
		LOGGER.info("=========== Delete TExecutionStatus ===========");
		final TExecutionStatus tExecutionStatus = genericDAO.get(clazz, executionStatusId);
		genericDAO.remove(tExecutionStatus);
	}

	/**
	 * Updates a TExecutionStatus entity object in to the persistent store
	 * 
	 * @param tExecutionStatus
	 *            TExecutionStatus Entity object to be updated
	 * @return tExecutionStatus Persisted TExecutionStatus object
	 */
	public TExecutionStatus updateTExecutionStatus(final TExecutionStatus tExecutionStatus) {
		LOGGER.info("=========== Update TExecutionStatus ===========");
		return genericDAO.update(tExecutionStatus);
	}

	/**
	 * Retrieve an TExecutionStatus object based on given TExecutionStatus executionStatusId.
	 * 
	 * @param tExecutionStatusId
	 *            the primary key value of the TExecutionStatus Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TExecutionStatus findTExecutionStatusById(final Integer tExecutionStatusId) {
		LOGGER.info("find TExecutionStatus instance with executionStatusId: " + tExecutionStatusId);
		return genericDAO.get(clazz, tExecutionStatusId);
	}

	/**
	 * Retrieve TExecutionStatus based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TExecutionStatus> list of TExecutionStatus if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TExecutionStatus> findTExecutionStatuss(final SearchFilter<TExecutionStatus> searchFilter) {
		LOGGER.info("=========== Find TExecutionStatuss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tExecutionStatusentity", tExecutionStatus);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TExecutionStatuss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTExecutionStatuss(final SearchFilter<TExecutionStatus> searchFilter) {
		LOGGER.info("=========== Count TExecutionStatus ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TExecutionStatus tExecutionStatus = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tExecutionStatusentity", tExecutionStatus);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@Override
	public TExecutionStatus findTExecutionStatusById(final Integer executionStatusId,final Short tenantId) {
		LOGGER.info("=========== find TExecutionStatusById ===========");
		TExecutionStatus tExecutionStatus = new TExecutionStatus();
		List list = new ArrayList();
		list.add(executionStatusId);
		list.add(tenantId);	
		List<TExecutionStatus> tExecStList= genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTExecutionStatusById", list,0,-1);
		if(tExecStList!= null){
			tExecutionStatus = tExecStList.get(0);
		}
		return tExecutionStatus;
	}

}
