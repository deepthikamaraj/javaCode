package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAllocType;
import com.cognizant.opserv.sp.core.entity.TAllocTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAllocTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAllocTypeDAO")
public class TAllocTypeDAOImpl implements TAllocTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAllocTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAllocType> clazz;

	public Class<TAllocType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAllocTypeDAOImpl() {
		super();
		this.clazz = TAllocType.class;
	}

	private static final String JPAQL = "select tAllocTypeentity from TAllocType tAllocTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TAllocType tAllocTypeentity";

	private static final String[] RESTRICTIONS = {
			"tAllocTypeentity.tAllocTypeId.localeId like '#{tAllocTypeentity.tAllocTypeId.getLocaleId}%'",
			"tAllocTypeentity.tAllocTypeId.allocTypeId = #{tAllocTypeentity.tAllocTypeId.getAllocTypeId}",
			"tAllocTypeentity.allocTypeCd like '#{tAllocTypeentity.getAllocTypeCd}%'",
			"tAllocTypeentity.allocTypeDesc like '#{tAllocTypeentity.getAllocTypeDesc}%'",
			"tAllocTypeentity.activeFlag = #{tAllocTypeentity.getActiveFlag}",
			"tAllocTypeentity.createdBy = #{tAllocTypeentity.getCreatedBy}",
			"Date(tAllocTypeentity.createDt) = '#{tAllocTypeentity.getCreateDt}'",
			"tAllocTypeentity.updatedBy = #{tAllocTypeentity.getUpdatedBy}",
			"Date(tAllocTypeentity.updateDt) = '#{tAllocTypeentity.getUpdateDt}'",
			"tAllocTypeentity.tenantId = #{tAllocTypeentity.getTenantId}" };

	/**
	 * Stores a new TAllocType entity object in to the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be persisted
	 * @return tAllocType Persisted TAllocType object
	 */
	public TAllocType createTAllocType(final TAllocType tAllocType) {
		LOGGER.info("=========== Create TAllocType ===========");
		return genericDAO.store(tAllocType);
	}

	/**
	 * Deletes a TAllocType entity object from the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be deleted
	 */
	public void deleteTAllocType(final TAllocTypeId tAllocTypeId) {
		LOGGER.info("=========== Delete TAllocType ===========");
		final TAllocType tAllocType = genericDAO.get(clazz, tAllocTypeId);
		genericDAO.remove(tAllocType);
	}

	/**
	 * Updates a TAllocType entity object in to the persistent store
	 * 
	 * @param tAllocType
	 *            TAllocType Entity object to be updated
	 * @return tAllocType Persisted TAllocType object
	 */
	public TAllocType updateTAllocType(final TAllocType tAllocType) {
		LOGGER.info("=========== Update TAllocType ===========");
		return genericDAO.update(tAllocType);
	}

	/**
	 * Retrieve an TAllocType object based on given TAllocType TAllocTypeId.
	 * 
	 * @param tAllocTypeId
	 *            the primary key value of the TAllocType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAllocType findTAllocTypeById(final TAllocTypeId tAllocTypeId) {
		LOGGER.info("find TAllocType instance with TAllocTypeId: " + tAllocTypeId);
		return genericDAO.get(clazz, tAllocTypeId);
	}

	/**
	 * Retrieve TAllocType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAllocType> list of TAllocType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAllocType> findTAllocTypes(final SearchFilter<TAllocType> searchFilter) {
		LOGGER.info("=========== Find TAllocTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAllocType tAllocType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAllocTypeentity", tAllocType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAllocTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAllocTypes(final SearchFilter<TAllocType> searchFilter) {
		LOGGER.info("=========== Count TAllocType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAllocType tAllocType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAllocTypeentity", tAllocType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find t alloc types by tenant and locale.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findTAllocTypesByTenantAndLocale(Short tenantId,
			String localeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAllocTypesByTenantAndLocale",paramList,0,-1);
	}

}
