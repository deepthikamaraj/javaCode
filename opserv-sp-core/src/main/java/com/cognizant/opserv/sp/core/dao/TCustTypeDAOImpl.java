package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.opserv.sp.core.entity.TCustTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustTypeDAO")
public class TCustTypeDAOImpl implements TCustTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	

	private final Class<TCustType> clazz;

	public Class<TCustType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustTypeDAOImpl() {
		super();
		this.clazz = TCustType.class;
	}

	private static final String JPAQL = "select tCustTypeentity from TCustType tCustTypeentity";

	private static final String JPACOUNTQL = "select count(tCustTypeentity) from TCustType tCustTypeentity";

	private static final String[] RESTRICTIONS = {
			"tCustTypeentity.custTypeId = #{tCustTypeentity.getCustTypeId}",
			"tCustTypeentity.typeName like '#{tCustTypeentity.getTypeName}%'",
			"tCustTypeentity.typeDesc like '#{tCustTypeentity.getTypeDesc}%'",
			"tCustTypeentity.colorCd like '#{tCustTypeentity.getColorCd}%'",
			"tCustTypeentity.activeFlag = #{tCustTypeentity.getActiveFlag}",
			"tCustTypeentity.createdBy = #{tCustTypeentity.getCreatedBy}",
			"Date(tCustTypeentity.createDt) = '#{tCustTypeentity.getCreateDt}'",
			"tCustTypeentity.updatedBy = #{tCustTypeentity.getUpdatedBy}",
			"Date(tCustTypeentity.updateDt) = '#{tCustTypeentity.getUpdateDt}'",
			"tCustTypeentity.tenantId = #{tCustTypeentity.getTenantId}",
		
			};

	/**
	 * Stores a new TCustType entity object in to the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be persisted
	 * @return tCustType Persisted TCustType object
	 */
	public TCustType createTCustType(final TCustType tCustType) {
		LOGGER.info("=========== Create TCustType ===========");
		return genericDAO.store(tCustType);
	}

	/**
	 * Deletes a TCustType entity object from the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be deleted
	 */
	public void deleteTCustType(final TCustTypeId custTypeId) {
		LOGGER.info("=========== Delete TCustType ===========");
		final TCustType tCustType = genericDAO.get(clazz, custTypeId);
		genericDAO.remove(tCustType);
	}

	/**
	 * Updates a TCustType entity object in to the persistent store
	 * 
	 * @param tCustType
	 *            TCustType Entity object to be updated
	 * @return tCustType Persisted TCustType object
	 */
	public TCustType updateTCustType(final TCustType tCustType) {
		LOGGER.info("=========== Update TCustType ===========");
		return genericDAO.update(tCustType);
	}

	/**
	 * Retrieve an TCustType object based on given TCustType custTypeId.
	 * 
	 * @param tCustTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustType findTCustTypeById(final Integer tCustTypeId) {
		LOGGER.info("find TCustType instance with custTypeId: " + tCustTypeId);
		return genericDAO.get(clazz, tCustTypeId);
	}
	
	/**
	 * Retrieve an TCustType object based on given TCustType custTypeId.
	 * 
	 * @param tCustTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustType findTCustTypeByIdUsingTCust(final TCustTypeId tCustTypeId) {
		LOGGER.info("find TCustType instance with custTypeId: " + tCustTypeId);
		return genericDAO.get(clazz, tCustTypeId);
	}
	/**
	 * Retrieve an TCustType object based on given TCustType custTypeId.
	 * 
	 * @param tCustTypeId
	 *            the primary key value of the TCustType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public List<TCustType> findTCustTypeByCustTypeName(final String custTypeName, final Short tenantId){	
		 List<Object> paramList = new ArrayList<Object>();
		 paramList.add(custTypeName);
		 paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustTypeNameByCustTypeName", paramList, 0, -1);
	}

	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustType> list of TCustType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustType> findTCustTypes(final SearchFilter<TCustType> searchFilter) {
		LOGGER.info("=========== Find TCustTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustType tCustType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		
		final JPAQuery jpaQuery = new JPAQuery("tCustTypeentity", tCustType);
		jpaQuery.setCacheable(true);
		

		
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustTypes(final SearchFilter<TCustType> searchFilter) {
		LOGGER.info("=========== Count TCustType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustType tCustType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustTypeentity", tCustType);

		

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of TCustTypes if it exists against given criteria.
	 *        
	 */
	@Override
	public List<Object> findallTypes(Short tenantId) {
		
		
		 List<Object> paramList = new ArrayList<Object>();
		 paramList.add(tenantId);
		
		List<Object> list = genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustTypes", paramList, 0, -1);
		
		return list;
	}
	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<object> list of AllTCustTypesByIds if it exists against given criteria.
	 *        
	 */
	
	@Override
	public List<Object[]> findAllCustTypes(Short tenantId, String localeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(localeId);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustTypesByIds", paramList, 0, -1);		
	}
	/**
	 * Retrieve TCustType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustType> list of AllTCustTypesTenantId if it exists against given criteria.
	 *        
	 */
	@Override
	public List<TCustType> findCustTypeByLocale(Short tenantId, String locale) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(locale);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustTypesTenantId", paramList, 0, -1);
	}
	
	/**
	 * @param tenantId - holds tenant Id
	 * @param locale - holds locale
	 * @return
	 */
	@Override
	public List<Object[]> findAllTCustTypeIdAndName(Short tenantId,
			String locale) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(locale);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTCustTypeIdAndName", paramList, 0, -1);
		
	}
	/**
	 * Gets the all customer type name.
	 *
	 * @param tenantId the tenant id
	 * @param locale the locale
	 * @return the all customer type name
	 */
	@Override
	public List<String> getAllCustomerTypeName(Short tenantId, String locale){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(locale);		
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetAllCustomerTypeName", paramList, 0, -1);
	}
	
}
