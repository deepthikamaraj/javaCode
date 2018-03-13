package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TChngReqApprType;
import com.cognizant.opserv.sp.core.entity.TChngReqApprTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TChngReqApprTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tChngReqApprTypeDAO")
public class TChngReqApprTypeDAOImpl implements TChngReqApprTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TChngReqApprTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TChngReqApprType> clazz;

	public Class<TChngReqApprType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TChngReqApprTypeDAOImpl() {
		super();
		this.clazz = TChngReqApprType.class;
	}

	private static final String JPAQL = "select tChngReqApprTypeentity from TChngReqApprType tChngReqApprTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TChngReqApprType tChngReqApprTypeentity";

	private static final String[] RESTRICTIONS = {
			"tChngReqApprTypeentity.tChngReqApprTypeId.apprTypeId = #{tChngReqApprTypeentity.tChngReqApprTypeId.getApprTypeId}",
			"tChngReqApprTypeentity.tChngReqApprTypeId.localeId like '#{tChngReqApprTypeentity.tChngReqApprTypeId.getLocaleId}%'",
			"tChngReqApprTypeentity.apprTypeCd like '#{tChngReqApprTypeentity.getApprTypeCd}%'",
			"tChngReqApprTypeentity.apprTypeName like '#{tChngReqApprTypeentity.getApprTypeName}%'",
			"tChngReqApprTypeentity.apprTypeDesc like '#{tChngReqApprTypeentity.getApprTypeDesc}%'",
			"tChngReqApprTypeentity.activeFlag = #{tChngReqApprTypeentity.getActiveFlag}",
			"tChngReqApprTypeentity.createdBy = #{tChngReqApprTypeentity.getCreatedBy}",
			"Date(tChngReqApprTypeentity.createDt) = '#{tChngReqApprTypeentity.getCreateDt}'",
			"tChngReqApprTypeentity.updatedBy = #{tChngReqApprTypeentity.getUpdatedBy}",
			"Date(tChngReqApprTypeentity.updateDt) = '#{tChngReqApprTypeentity.getUpdateDt}'",
			"tChngReqApprTypeentity.tenantId = #{tChngReqApprTypeentity.getTenantId}" };

	/**
	 * Stores a new TChngReqApprType entity object in to the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be persisted
	 * @return tChngReqApprType Persisted TChngReqApprType object
	 */
	public TChngReqApprType createTChngReqApprType(final TChngReqApprType tChngReqApprType) {
		LOGGER.info("=========== Create TChngReqApprType ===========");
		return genericDAO.store(tChngReqApprType);
	}

	/**
	 * Deletes a TChngReqApprType entity object from the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be deleted
	 */
	public void deleteTChngReqApprType(final TChngReqApprTypeId tChngReqApprTypeId) {
		LOGGER.info("=========== Delete TChngReqApprType ===========");
		final TChngReqApprType tChngReqApprType = genericDAO.get(clazz, tChngReqApprTypeId);
		genericDAO.remove(tChngReqApprType);
	}

	/**
	 * Updates a TChngReqApprType entity object in to the persistent store
	 * 
	 * @param tChngReqApprType
	 *            TChngReqApprType Entity object to be updated
	 * @return tChngReqApprType Persisted TChngReqApprType object
	 */
	public TChngReqApprType updateTChngReqApprType(final TChngReqApprType tChngReqApprType) {
		LOGGER.info("=========== Update TChngReqApprType ===========");
		return genericDAO.update(tChngReqApprType);
	}

	/**
	 * Retrieve an TChngReqApprType object based on given TChngReqApprType TChngReqApprTypeId.
	 * 
	 * @param tChngReqApprTypeId
	 *            the primary key value of the TChngReqApprType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TChngReqApprType findTChngReqApprTypeById(final TChngReqApprTypeId tChngReqApprTypeId) {
		LOGGER.info("find TChngReqApprType instance with TChngReqApprTypeId: " + tChngReqApprTypeId);
		return genericDAO.get(clazz, tChngReqApprTypeId);
	}

	/**
	 * Retrieve TChngReqApprType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TChngReqApprType> list of TChngReqApprType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TChngReqApprType> findTChngReqApprTypes(final SearchFilter<TChngReqApprType> searchFilter) {
		LOGGER.info("=========== Find TChngReqApprTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TChngReqApprType tChngReqApprType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tChngReqApprTypeentity", tChngReqApprType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TChngReqApprTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTChngReqApprTypes(final SearchFilter<TChngReqApprType> searchFilter) {
		LOGGER.info("=========== Count TChngReqApprType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TChngReqApprType tChngReqApprType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tChngReqApprTypeentity", tChngReqApprType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/**
	 * Find appr type by locale.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<TChngReqApprType> findApprTypeByLocale(short tenantId,String localeId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCRApprType", paramList, 0, -1) ;
	}
	/**
	 * Find t chng req appr type by change req config prapprtypeid.
	 *
	 * @param tenantId the tenant id
	 * @param aprTypeId the apr type id
	 * @return the list
	 */
	@Override
	public List<Object> findTChngReqApprTypeByChangeReqConfigPrapprtypeid(short tenantId, Integer aprTypeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(aprTypeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTChngReqApprTypeByChangeReqConfigPrapprtypeid", paramList, 0, -1) ;
	}
	/**
	 * Find appr types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 */
	@Override
	public List<Object[]> findApprTypes(short tenantId, String localeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findApprTypes", paramList, 0, -1) ;
	}

}
