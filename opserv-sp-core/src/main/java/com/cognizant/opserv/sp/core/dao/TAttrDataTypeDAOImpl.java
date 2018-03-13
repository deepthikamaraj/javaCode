package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TAttrDataTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAttrDataTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAttrDataTypeDAO")
public class TAttrDataTypeDAOImpl implements TAttrDataTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TAttrDataTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAttrDataType> clazz;

	public Class<TAttrDataType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAttrDataTypeDAOImpl() {
		super();
		this.clazz = TAttrDataType.class;
	}

	private static final String JPAQL = "select tAttrDataTypeentity from TAttrDataType tAttrDataTypeentity";

	private static final String JPACOUNTQL = "select count(tAttrDataTypeentity) from TAttrDataType tAttrDataTypeentity";

	private static final String[] RESTRICTIONS = {
			"tAttrDataTypeentity.tAttrDataTypeId.attrDataTypeId = #{tAttrDataTypeentity.tAttrDataTypeId.getAttrDataTypeId}",
			"tAttrDataTypeentity.tAttrDataTypeId.localeId = #{tAttrDataTypeentity.tAttrDataTypeId.getLocaleId}",
			"tAttrDataTypeentity.dataTypeName like '#{tAttrDataTypeentity.getDataTypeName}%'",
			"tAttrDataTypeentity.dataTypeDesc like '#{tAttrDataTypeentity.getDataTypeDesc}%'",
			"tAttrDataTypeentity.activeFlag = #{tAttrDataTypeentity.getActiveFlag}" };

	/**
	 * Stores a new TAttrDataType entity object in to the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be persisted
	 * @return tAttrDataType Persisted TAttrDataType object
	 */
	public TAttrDataType createTAttrDataType(final TAttrDataType tAttrDataType) {
		LOGGER.info("=========== Create TAttrDataType ===========");
		return genericDAO.store(tAttrDataType);
	}

	/**
	 * Deletes a TAttrDataType entity object from the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be deleted
	 */
	public void deleteTAttrDataType(final Integer attrDataTypeId) {
		LOGGER.info("=========== Delete TAttrDataType ===========");
		final TAttrDataType tAttrDataType = genericDAO.get(clazz, attrDataTypeId);
		genericDAO.remove(tAttrDataType);
	}

	/**
	 * Updates a TAttrDataType entity object in to the persistent store
	 * 
	 * @param tAttrDataType
	 *            TAttrDataType Entity object to be updated
	 * @return tAttrDataType Persisted TAttrDataType object
	 */
	public TAttrDataType updateTAttrDataType(final TAttrDataType tAttrDataType) {
		LOGGER.info("=========== Update TAttrDataType ===========");
		return genericDAO.update(tAttrDataType);
	}

	/**
	 * Retrieve an TAttrDataType object based on given TAttrDataType attrDataTypeId.
	 * 
	 * @param tAttrDataTypeId
	 *            the primary key value of the TAttrDataType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAttrDataType findTAttrDataTypeById(final TAttrDataTypeId tAttrDataTypeId) {
		LOGGER.info("find TAttrDataType instance with attrDataTypeId: " + tAttrDataTypeId);
		return genericDAO.get(clazz, tAttrDataTypeId);
	}

	/**
	 * Retrieve TAttrDataType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAttrDataType> list of TAttrDataType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAttrDataType> findTAttrDataTypes(final SearchFilter<TAttrDataType> searchFilter) {
		LOGGER.info("=========== Find TAttrDataTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAttrDataTypeentity", tAttrDataType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAttrDataTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAttrDataTypes(final SearchFilter<TAttrDataType> searchFilter) {
		LOGGER.info("=========== Count TAttrDataType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAttrDataTypeentity", tAttrDataType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	/**
	 * Find t attr data type by id.
	 *
	 * @param attrDataTypeId the attr data type id
	 * @param localeId the locale id
	 * @return the t attr data type
	 */
	@Override
	public TAttrDataType findTAttrDataTypeById(final Integer attrDataTypeId,final String localeId) {
		LOGGER.info("=========== find TAttrDataTypeById ===========");
		TAttrDataType tAttrDataType = new TAttrDataType();
		List list = new ArrayList();
		list.add(attrDataTypeId);
		list.add(""+localeId+"");	
		List<TAttrDataType> tAttrDataTypeList= genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDataTypesByTAttrDataTypeId",list,0,-1);
		if(tAttrDataTypeList!= null){
			tAttrDataType = tAttrDataTypeList.get(0);
		}
		return tAttrDataType;
	}
	/**
	 * Find t attr data types by locale id.
	 *
	 * @param localeId the locale id
	 * @param activeFlag the active flag
	 * @return the list
	 */
	@Override
	public List<Object[]> findTAttrDataTypesByLocaleId(String localeId,Character activeFlag) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(localeId);
		queryParam.add(activeFlag);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTAttrDataTypesByLocaleId", queryParam, 0, -1);
	}	
}
