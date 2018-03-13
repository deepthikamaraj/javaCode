package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAddrType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAddrTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAddrTypeDAO")
public class TAddrTypeDAOImpl implements TAddrTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAddrTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAddrType> clazz;

	public Class<TAddrType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAddrTypeDAOImpl() {
		super();
		this.clazz = TAddrType.class;
	}

	private static final String JPAQL = "select tAddrTypeentity from TAddrType tAddrTypeentity";

	private static final String JPACOUNTQL = "select count(tAddrTypeentity) from TAddrType tAddrTypeentity";

	private static final String[] RESTRICTIONS = { "tAddrTypeentity.addrTypeId = #{tAddrTypeentity.getAddrTypeId}",
			"tAddrTypeentity.addrTypeDesc like '#{tAddrTypeentity.getAddrTypeDesc}%'",
			"tAddrTypeentity.activeFlag = #{tAddrTypeentity.getActiveFlag}",
			"tAddrTypeentity.createdBy = #{tAddrTypeentity.getCreatedBy}",
			"Date(tAddrTypeentity.createDt) = '#{tAddrTypeentity.getCreateDt}'",
			"tAddrTypeentity.updatedBy = #{tAddrTypeentity.getUpdatedBy}",
			"Date(tAddrTypeentity.updateDt) = '#{tAddrTypeentity.getUpdateDt}'",
			"tAddrTypeentity.tAddrTypeId.localeId = '#{tAddrTypeentity.tAddrTypeId.getLocaleId}'",
			"tAddrTypeentity.tenantId = #{tAddrTypeentity.getTenantId}"
			};

	/**
	 * Stores a new TAddrType entity object in to the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be persisted
	 * @return tAddrType Persisted TAddrType object
	 */
	public TAddrType createTAddrType(final TAddrType tAddrType) {
		LOGGER.info("=========== Create TAddrType ===========");
		return genericDAO.store(tAddrType);
	}

	/**
	 * Deletes a TAddrType entity object from the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be deleted
	 */
	public void deleteTAddrType(final Integer addrTypeId) {
		LOGGER.info("=========== Delete TAddrType ===========");
		final TAddrType tAddrType = genericDAO.get(clazz, addrTypeId);
		genericDAO.remove(tAddrType);
	}

	/**
	 * Updates a TAddrType entity object in to the persistent store
	 * 
	 * @param tAddrType
	 *            TAddrType Entity object to be updated
	 * @return tAddrType Persisted TAddrType object
	 */
	public TAddrType updateTAddrType(final TAddrType tAddrType) {
		LOGGER.info("=========== Update TAddrType ===========");
		return genericDAO.update(tAddrType);
	}

	/**
	 * Retrieve an TAddrType object based on given TAddrType addrTypeId.
	 * 
	 * @param tAddrTypeId
	 *            the primary key value of the TAddrType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAddrType findTAddrTypeById(final Integer tAddrTypeId) {
		LOGGER.info("find TAddrType instance with addrTypeId: " + tAddrTypeId);
		return genericDAO.get(clazz, tAddrTypeId);
	}

	/**
	 * Retrieve TAddrType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAddrType> list of TAddrType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAddrType> findTAddrTypes(final SearchFilter<TAddrType> searchFilter) {
		LOGGER.info("=========== Find TAddrTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAddrType tAddrType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();		
		
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAddrTypeentity", tAddrType);
		jpaQuery.setCacheable(searchFilter.isCacheable());
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		jpaQuery.setCacheable(true);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAddrTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAddrTypes(final SearchFilter<TAddrType> searchFilter) {
		LOGGER.info("=========== Count TAddrType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAddrType tAddrType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAddrTypeentity", tAddrType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
