package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqUsrType;
import com.cognizant.opserv.sp.core.entity.TChngReqUsrTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqUsrTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqUsrTypeDAO")
public class TChngReqUsrTypeDAOImpl implements TChngReqUsrTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqUsrTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqUsrType> clazz;

	public Class<TChngReqUsrType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqUsrTypeDAOImpl() {
		super();
		this.clazz = TChngReqUsrType.class;
	}

	private static final String JPAQL = "select tChngReqUsrTypeentity from TChngReqUsrType tChngReqUsrTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqUsrType tChngReqUsrTypeentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqUsrTypeentity.tChngReqUsrTypeId.usrTypeId = #{tChngReqUsrTypeentity.tChngReqUsrTypeId.getUsrTypeId}",
			"tChngReqUsrTypeentity.tChngReqUsrTypeId.localeId like '#{tChngReqUsrTypeentity.tChngReqUsrTypeId.getLocaleId}%'",
			"tChngReqUsrTypeentity.usrTypeName like '#{tChngReqUsrTypeentity.getUsrTypeName}%'",
			"tChngReqUsrTypeentity.usrTypeDesc like '#{tChngReqUsrTypeentity.getUsrTypeDesc}%'",
			"tChngReqUsrTypeentity.createdBy = #{tChngReqUsrTypeentity.getCreatedBy}",
			"Date(tChngReqUsrTypeentity.createDt) = '#{tChngReqUsrTypeentity.getCreateDt}'",
			"tChngReqUsrTypeentity.updatedBy = #{tChngReqUsrTypeentity.getUpdatedBy}",
			"Date(tChngReqUsrTypeentity.updateDt) = '#{tChngReqUsrTypeentity.getUpdateDt}'",
			"tChngReqUsrTypeentity.tenantId = #{tChngReqUsrTypeentity.getTenantId}" };

	/**
	 * Stores a new TChngReqUsrType entity object in to the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be persisted
	 * @return tChngReqUsrType Persisted TChngReqUsrType object
	 */
	public TChngReqUsrType createTChngReqUsrType(
			final TChngReqUsrType tChngReqUsrType) {
		LOGGER.info("=========== Create TChngReqUsrType ===========");
		return genericDAO.store(tChngReqUsrType);
	}

	/**
	 * Deletes a TChngReqUsrType entity object from the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be deleted
	 */
	public void deleteTChngReqUsrType(final TChngReqUsrTypeId tChngReqUsrTypeId) {
		LOGGER.info("=========== Delete TChngReqUsrType ===========");
		final TChngReqUsrType tChngReqUsrType = genericDAO.get(clazz,
				tChngReqUsrTypeId);
		genericDAO.remove(tChngReqUsrType);
	}

	/**
	 * Updates a TChngReqUsrType entity object in to the persistent store
	 * 
	 * @param tChngReqUsrType
	 *            TChngReqUsrType Entity object to be updated
	 * @return tChngReqUsrType Persisted TChngReqUsrType object
	 */
	public TChngReqUsrType updateTChngReqUsrType(
			final TChngReqUsrType tChngReqUsrType) {
		LOGGER.info("=========== Update TChngReqUsrType ===========");
		return genericDAO.update(tChngReqUsrType);
	}

	/**
	 * Retrieve an TChngReqUsrType object based on given TChngReqUsrType
	 * TChngReqUsrTypeId.
	 * 
	 * @param tChngReqUsrTypeId
	 *            the primary key value of the TChngReqUsrType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqUsrType findTChngReqUsrTypeById(
			final TChngReqUsrTypeId tChngReqUsrTypeId) {
		LOGGER.info("find TChngReqUsrType instance with TChngReqUsrTypeId: "
				+ tChngReqUsrTypeId);
		return genericDAO.get(clazz, tChngReqUsrTypeId);
	}

	/**
	 * Retrieve TChngReqUsrType based on given search criteria using Dynamic
	 * JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqUsrType> list of TChngReqUsrType if it exists
	 *         against given criteria. Returns null if not found
	 */
	public List<TChngReqUsrType> findTChngReqUsrTypes(
			final SearchFilter<TChngReqUsrType> searchFilter) {
		LOGGER.info("=========== Find TChngReqUsrTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqUsrType tChngReqUsrType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqUsrTypeentity",
				tChngReqUsrType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqUsrTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqUsrTypes(
			final SearchFilter<TChngReqUsrType> searchFilter) {
		LOGGER.info("=========== Count TChngReqUsrType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqUsrType tChngReqUsrType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqUsrTypeentity",
				tChngReqUsrType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/*
	 * Added By 407986 To fetch All the TChngReqUserType
	 */
	/**
	 * Fetch all t chng req usr types by tenants.
	 *
	 * @return the list
	 */
	public List<TChngReqUsrType> fetchAllTChngReqUsrTypesByTenants() {
		return genericDAO.findEntitiesByNamedQuery("FindAllTChngReqUsrTypes");
	}

	/*
	 * Added By 407986 To fetch All the TChngReqUserType
	 */
	/**
	 * Fetch all t chng req usr types by tenants.
	 *
	 * @param tenantId the tenant id
	 * @return the list
	 */
	public List<TChngReqUsrType> fetchAllTChngReqUsrTypesByTenants(
			Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tenantId);

		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"FindAllTChngReqUsrTypesByTenant", queryParam, 0, -1);

	}
	/**
	 * Find t usr desc.
	 *
	 * @param usrTypeId the usr type id
	 * @param userDetails the user details
	 * @return the list
	 */
	@Override
	public List<Object[]> findTUsrDesc(Integer usrTypeId, Short tenantId, String localeId) {
		final List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);
		paramList.add(usrTypeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrDesc", paramList, 0, -1);
	}

}
