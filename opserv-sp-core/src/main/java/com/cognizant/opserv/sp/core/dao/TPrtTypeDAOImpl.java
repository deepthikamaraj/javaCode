package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrtType;
import com.cognizant.opserv.sp.core.entity.TPrtTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrtTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrtTypeDAO")
public class TPrtTypeDAOImpl implements TPrtTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrtTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPrtType> clazz;

	public Class<TPrtType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrtTypeDAOImpl() {
		super();
		this.clazz = TPrtType.class;
	}

	private static final String JPAQL = "select tPrtTypeentity from TPrtType tPrtTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TPrtType tPrtTypeentity";

	private static final String[] RESTRICTIONS = {
			"tPrtTypeentity.tPrtTypeId.prtTypeId = #{tPrtTypeentity.tPrtTypeId.getPrtTypeId}",
			"tPrtTypeentity.tPrtTypeId.localeId like '#{tPrtTypeentity.tPrtTypeId.getLocaleId}%'",
			"tPrtTypeentity.prtTypeDesc like '#{tPrtTypeentity.getPrtTypeDesc}%'",
			"tPrtTypeentity.activeFlag = #{tPrtTypeentity.getActiveFlag}",
			"tPrtTypeentity.createdBy = #{tPrtTypeentity.getCreatedBy}",
			"Date(tPrtTypeentity.createDt) = '#{tPrtTypeentity.getCreateDt}'",
			"tPrtTypeentity.updatedBy = #{tPrtTypeentity.getUpdatedBy}",
			"Date(tPrtTypeentity.updateDt) = '#{tPrtTypeentity.getUpdateDt}'",
			"tPrtTypeentity.tenantId = #{tPrtTypeentity.getTenantId}",
			"tPrtTypeentity.prtTypeName like '#{tPrtTypeentity.getPrtTypeName}%'" };

	/**
	 * Stores a new TPrtType entity object in to the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be persisted
	 * @return tPrtType Persisted TPrtType object
	 */
	public TPrtType createTPrtType(final TPrtType tPrtType) {
		LOGGER.info("=========== Create TPrtType ===========");
		return genericDAO.store(tPrtType);
	}

	/**
	 * Deletes a TPrtType entity object from the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be deleted
	 */
	public void deleteTPrtType(final TPrtTypeId tPrtTypeId) {
		LOGGER.info("=========== Delete TPrtType ===========");
		final TPrtType tPrtType = genericDAO.get(clazz, tPrtTypeId);
		genericDAO.remove(tPrtType);
	}

	/**
	 * Updates a TPrtType entity object in to the persistent store
	 * 
	 * @param tPrtType
	 *            TPrtType Entity object to be updated
	 * @return tPrtType Persisted TPrtType object
	 */
	public TPrtType updateTPrtType(final TPrtType tPrtType) {
		LOGGER.info("=========== Update TPrtType ===========");
		return genericDAO.update(tPrtType);
	}

	/**
	 * Retrieve an TPrtType object based on given TPrtType TPrtTypeId.
	 * 
	 * @param tPrtTypeId
	 *            the primary key value of the TPrtType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrtType findTPrtTypeById(final TPrtTypeId tPrtTypeId) {
		LOGGER.info("find TPrtType instance with TPrtTypeId: " + tPrtTypeId);
		return genericDAO.get(clazz, tPrtTypeId);
	}

	/**
	 * Retrieve TPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrtType> list of TPrtType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrtType> findTPrtTypes(final SearchFilter<TPrtType> searchFilter) {
		LOGGER.info("=========== Find TPrtTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrtType tPrtType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrtTypeentity", tPrtType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrtTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrtTypes(final SearchFilter<TPrtType> searchFilter) {
		LOGGER.info("=========== Count TPrtType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrtType tPrtType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrtTypeentity", tPrtType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve an object based on given ActiveTypesForLocale.
	 * 
	 * @param localeId
	 * @param tenantId
	 * 
	 * @return an ObjectActiveTypesForLocale
	 */
	@Override
	public List<Object[]> findActiveTypesForLocale(String localeId,
			Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(localeId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTypesForLocale",paramList,0,-1);
	}
	/**
	 * Retrieve String based on PrtNameForLocale.
	 * 
	 * @param localeId
	 * @param prtTypeID
	 * 
	 * @return String of PrtNameForLocale
	 */
	@Override
	public String findPrtNameForLocale(Integer prtTypeID, String localeId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(prtTypeID);
		paramList.add(localeId);
		List<Object> list =  genericDAO.findEntitiesByNamedQueryMultiCond("FindPrtNameForLocale",paramList,0,-1);
		return list.get(0).toString();
	}
	/**
	 * Retrieve an object based on given ActiveTypesWithDesc
	 * 
	 * @param localeId
	 * @param tenantId
	 * @param Desc
	 * 
	 * @return an ActiveTypesWithDesc
	 */
	@Override
	public List<Object[]> FindActiveTypesWithDesc(String Desc,String localeId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Desc);
		paramList.add(localeId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTypesWithDesc",paramList,0,-1);
	}

	/**
	 * Retrieve an object based on given ActiveTypesWithDescFrView.
	 * 
	 * @param localeId
	 * @param tenantId
	 * @param Desc
	 * @return an ActiveTypesWithDesc
	 */
	@Override
	public List<Object[]> FindActiveTypesWithDescFrView(String Desc,String localeId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add("%"+Desc+"%");
		paramList.add(localeId);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindActiveTypesWithDesc",paramList,0,-1);

	}
	

}
