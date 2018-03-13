package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussFunctionDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussFunctionDAO")
public class TBussFunctionDAOImpl implements TBussFunctionDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussFunctionDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TBussFunction> clazz;

	public Class<TBussFunction> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussFunctionDAOImpl() {
		super();
		this.clazz = TBussFunction.class;
	}

	private static final String JPAQL = "select tBussFunctionentity from TBussFunction tBussFunctionentity";

	private static final String JPACOUNTQL = "select count(tBussFunctionentity) from TBussFunction tBussFunctionentity";

	private static final String[] RESTRICTIONS = {
			"tBussFunctionentity.bussFunctionId = #{tBussFunctionentity.getBussFunctionId}",
			"tBussFunctionentity.activeFlag = #{tBussFunctionentity.getActiveFlag}",
			"tBussFunctionentity.bussFunctionName like '#{tBussFunctionentity.getBussFunctionName}%'",
			"tBussFunctionentity.bussFunctionDesc like '#{tBussFunctionentity.getBussFunctionDesc}%'",
			"tBussFunctionentity.createdBy = #{tBussFunctionentity.getCreatedBy}",
			"Date(tBussFunctionentity.createDt) = '#{tBussFunctionentity.getCreateDt}'",
			"tBussFunctionentity.updatedBy = #{tBussFunctionentity.getUpdatedBy}",
			"Date(tBussFunctionentity.updateDt) = '#{tBussFunctionentity.getUpdateDt}'",
			"tBussFunctionentity.tenantId = #{tBussFunctionentity.getTenantId}" };

	/**
	 * Stores a new TBussFunction entity object in to the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be persisted
	 * @return tBussFunction Persisted TBussFunction object
	 */
	public TBussFunction createTBussFunction(final TBussFunction tBussFunction) {
		LOGGER.info("=========== Create TBussFunction ===========");
		return genericDAO.store(tBussFunction);
	}

	/**
	 * Deletes a TBussFunction entity object from the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be deleted
	 */
	public void deleteTBussFunction(final Integer bussFunctionId) {
		LOGGER.info("=========== Delete TBussFunction ===========");
		final TBussFunction tBussFunction = genericDAO.get(clazz, bussFunctionId);
		genericDAO.remove(tBussFunction);
	}

	/**
	 * Updates a TBussFunction entity object in to the persistent store
	 * 
	 * @param tBussFunction
	 *            TBussFunction Entity object to be updated
	 * @return tBussFunction Persisted TBussFunction object
	 */
	public TBussFunction updateTBussFunction(final TBussFunction tBussFunction) {
		LOGGER.info("=========== Update TBussFunction ===========");
		return genericDAO.update(tBussFunction);
	}

	/**
	 * Retrieve an TBussFunction object based on given TBussFunction bussFunctionId.
	 * 
	 * @param tBussFunctionId
	 *            the primary key value of the TBussFunction Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussFunction findTBussFunctionById(final Integer tBussFunctionId) {
		LOGGER.info("find TBussFunction instance with bussFunctionId: " + tBussFunctionId);
		return genericDAO.get(clazz, tBussFunctionId);
	}

	/**
	 * Retrieve TBussFunction based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussFunction> list of TBussFunction if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussFunction> findTBussFunctions(final SearchFilter<TBussFunction> searchFilter) {
		LOGGER.info("=========== Find TBussFunctions ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		
		final int maxresult = paginationInfo.getMaxRows();		
		final int index = paginationInfo.getStartIndex();
		
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussFunctionentity", tBussFunction);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(searchFilter.isCacheable());
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussFunctions.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussFunctions(final SearchFilter<TBussFunction> searchFilter) {
		LOGGER.info("=========== Count TBussFunction ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussFunctionentity", tBussFunction);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find buss fun entities by tenant id.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<TBussFunction> findBussFunEntitiesByTenantId(Short tenantId) {
		List list = new ArrayList<>();
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTBussFuncByTenantId", list,0,-1);
	}

}
