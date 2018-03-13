package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMirType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMirTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMirTypeDAO")
public class TMirTypeDAOImpl implements TMirTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TMirTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TMirType> clazz;

	public Class<TMirType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMirTypeDAOImpl() {
		super();
		this.clazz = TMirType.class;
	}

	private static final String JPAQL = "select tMirTypeentity from TMirType tMirTypeentity";

	private static final String JPACOUNTQL = "select count(tMirTypeentity) from TMirType tMirTypeentity";

	private static final String[] RESTRICTIONS = { "tMirTypeentity.mirTypeId = #{tMirTypeentity.getMirTypeId}",
			"tMirTypeentity.typeCd like '#{tMirTypeentity.getTypeCd}%'",
			"tMirTypeentity.typeName like '#{tMirTypeentity.getTypeName}%'",
			"tMirTypeentity.typeDesc like '#{tMirTypeentity.getTypeDesc}%'",
			"tMirTypeentity.createdBy = #{tMirTypeentity.getCreatedBy}",
			"Date(tMirTypeentity.createDt) = '#{tMirTypeentity.getCreateDt}'",
			"tMirTypeentity.updatedBy = #{tMirTypeentity.getUpdatedBy}",
			"Date(tMirTypeentity.updateDt) = '#{tMirTypeentity.getUpdateDt}'",
			"tMirTypeentity.tenantId = #{tMirTypeentity.getTenantId}",
			"tMirTypeentity.activeFlag = #{tMirTypeentity.getActiveFlag}" };

	/**
	 * Stores a new TMirType entity object in to the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be persisted
	 * @return tMirType Persisted TMirType object
	 */
	public TMirType createTMirType(final TMirType tMirType) {
		LOGGER.info("=========== Create TMirType ===========");
		return genericDAO.store(tMirType);
	}

	/**
	 * Deletes a TMirType entity object from the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be deleted
	 */
	public void deleteTMirType(final Integer mirTypeId) {
		LOGGER.info("=========== Delete TMirType ===========");
		final TMirType tMirType = genericDAO.get(clazz, mirTypeId);
		genericDAO.remove(tMirType);
	}

	/**
	 * Updates a TMirType entity object in to the persistent store
	 * 
	 * @param tMirType
	 *            TMirType Entity object to be updated
	 * @return tMirType Persisted TMirType object
	 */
	public TMirType updateTMirType(final TMirType tMirType) {
		LOGGER.info("=========== Update TMirType ===========");
		return genericDAO.update(tMirType);
	}

	/**
	 * Retrieve an TMirType object based on given TMirType mirTypeId.
	 * 
	 * @param tMirTypeId
	 *            the primary key value of the TMirType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMirType findTMirTypeById(final Integer tMirTypeId) {
		LOGGER.info("find TMirType instance with mirTypeId: " + tMirTypeId);
		return genericDAO.get(clazz, tMirTypeId);
	}

	/**
	 * Retrieve TMirType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirType> list of TMirType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMirType> findTMirTypes(final SearchFilter<TMirType> searchFilter) {
		LOGGER.info("=========== Find TMirTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMirType tMirType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMirTypeentity", tMirType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMirTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMirTypes(final SearchFilter<TMirType> searchFilter) {
		LOGGER.info("=========== Count TMirType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMirType tMirType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMirTypeentity", tMirType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
