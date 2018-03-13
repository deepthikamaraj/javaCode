package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMtrValueType;
import com.cognizant.opserv.sp.core.entity.TMtrValueTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMtrValueTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMtrValueTypeDAO")
public class TMtrValueTypeDAOImpl implements TMtrValueTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TMtrValueTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TMtrValueType> clazz;

	public Class<TMtrValueType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMtrValueTypeDAOImpl() {
		super();
		this.clazz = TMtrValueType.class;
	}

	private static final String JPAQL = "select tMtrValueTypeentity from TMtrValueType tMtrValueTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TMtrValueType tMtrValueTypeentity";

	private static final String[] RESTRICTIONS = {
			"tMtrValueTypeentity.tMtrValueTypeId.localeId like '#{tMtrValueTypeentity.tMtrValueTypeId.getLocaleId}%'",
			"tMtrValueTypeentity.tMtrValueTypeId.mtrValueTypeId = #{tMtrValueTypeentity.tMtrValueTypeId.getMtrValueTypeId}",
			"tMtrValueTypeentity.typeName like '#{tMtrValueTypeentity.getTypeName}%'",
			"tMtrValueTypeentity.typeDesc like '#{tMtrValueTypeentity.getTypeDesc}%'",
			"tMtrValueTypeentity.activeFlag = #{tMtrValueTypeentity.getActiveFlag}",
			"tMtrValueTypeentity.typeCd like '#{tMtrValueTypeentity.getTypeCd}%'",
			"tMtrValueTypeentity.createdBy = #{tMtrValueTypeentity.getCreatedBy}",
			"Date(tMtrValueTypeentity.createDt) = '#{tMtrValueTypeentity.getCreateDt}'",
			"tMtrValueTypeentity.updatedBy = #{tMtrValueTypeentity.getUpdatedBy}",
			"Date(tMtrValueTypeentity.updateDt) = '#{tMtrValueTypeentity.getUpdateDt}'",
			"tMtrValueTypeentity.tenantId = #{tMtrValueTypeentity.getTenantId}" };

	/**
	 * Stores a new TMtrValueType entity object in to the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be persisted
	 * @return tMtrValueType Persisted TMtrValueType object
	 */
	public TMtrValueType createTMtrValueType(final TMtrValueType tMtrValueType) {
		LOGGER.info("=========== Create TMtrValueType ===========");
		return genericDAO.store(tMtrValueType);
	}

	/**
	 * Deletes a TMtrValueType entity object from the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be deleted
	 */
	public void deleteTMtrValueType(final TMtrValueTypeId tMtrValueTypeId) {
		LOGGER.info("=========== Delete TMtrValueType ===========");
		final TMtrValueType tMtrValueType = genericDAO.get(clazz, tMtrValueTypeId);
		genericDAO.remove(tMtrValueType);
	}

	/**
	 * Updates a TMtrValueType entity object in to the persistent store
	 * 
	 * @param tMtrValueType
	 *            TMtrValueType Entity object to be updated
	 * @return tMtrValueType Persisted TMtrValueType object
	 */
	public TMtrValueType updateTMtrValueType(final TMtrValueType tMtrValueType) {
		LOGGER.info("=========== Update TMtrValueType ===========");
		return genericDAO.update(tMtrValueType);
	}

	/**
	 * Retrieve an TMtrValueType object based on given TMtrValueType TMtrValueTypeId.
	 * 
	 * @param tMtrValueTypeId
	 *            the primary key value of the TMtrValueType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMtrValueType findTMtrValueTypeById(final TMtrValueTypeId tMtrValueTypeId) {
		LOGGER.info("find TMtrValueType instance with TMtrValueTypeId: " + tMtrValueTypeId);
		return genericDAO.get(clazz, tMtrValueTypeId);
	}

	/**
	 * Retrieve TMtrValueType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMtrValueType> list of TMtrValueType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMtrValueType> findTMtrValueTypes(final SearchFilter<TMtrValueType> searchFilter) {
		LOGGER.info("=========== Find TMtrValueTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMtrValueType tMtrValueType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMtrValueTypeentity", tMtrValueType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMtrValueTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMtrValueTypes(final SearchFilter<TMtrValueType> searchFilter) {
		LOGGER.info("=========== Count TMtrValueType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMtrValueType tMtrValueType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMtrValueTypeentity", tMtrValueType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
