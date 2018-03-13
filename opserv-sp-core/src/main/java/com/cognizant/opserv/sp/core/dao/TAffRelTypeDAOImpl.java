package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAffRelType;
import com.cognizant.opserv.sp.core.entity.TAffRelTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAffRelTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAffRelTypeDAO")
public class TAffRelTypeDAOImpl implements TAffRelTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAffRelTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TAffRelType> clazz;

	public Class<TAffRelType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAffRelTypeDAOImpl() {
		super();
		this.clazz = TAffRelType.class;
	}

	private static final String JPAQL = "select tAffRelTypeentity from TAffRelType tAffRelTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TAffRelType tAffRelTypeentity";

	private static final String[] RESTRICTIONS = {
			"tAffRelTypeentity.tAffRelTypeId.relTypeId = #{tAffRelTypeentity.tAffRelTypeId.getRelTypeId}",
			"tAffRelTypeentity.tAffRelTypeId.localeId like '#{tAffRelTypeentity.tAffRelTypeId.getLocaleId}%'",
			"tAffRelTypeentity.relTypeCd like '#{tAffRelTypeentity.getRelTypeCd}%'",
			"tAffRelTypeentity.relTypeName like '#{tAffRelTypeentity.getRelTypeName}%'",
			"tAffRelTypeentity.relTypeDesc like '#{tAffRelTypeentity.getRelTypeDesc}%'",
			"tAffRelTypeentity.activeFlag = #{tAffRelTypeentity.getActiveFlag}",
			"tAffRelTypeentity.createdBy = #{tAffRelTypeentity.getCreatedBy}",
			"Date(tAffRelTypeentity.createDt) = '#{tAffRelTypeentity.getCreateDt}'",
			"tAffRelTypeentity.updatedBy = #{tAffRelTypeentity.getUpdatedBy}",
			"Date(tAffRelTypeentity.updateDt) = '#{tAffRelTypeentity.getUpdateDt}'",
			"tAffRelTypeentity.tenantId = #{tAffRelTypeentity.getTenantId}" };

	/**
	 * Stores a new TAffRelType entity object in to the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be persisted
	 * @return tAffRelType Persisted TAffRelType object
	 */
	public TAffRelType createTAffRelType(final TAffRelType tAffRelType) {
		LOGGER.info("=========== Create TAffRelType ===========");
		return genericDAO.store(tAffRelType);
	}

	/**
	 * Deletes a TAffRelType entity object from the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be deleted
	 */
	public void deleteTAffRelType(final TAffRelTypeId tAffRelTypeId) {
		LOGGER.info("=========== Delete TAffRelType ===========");
		final TAffRelType tAffRelType = genericDAO.get(clazz, tAffRelTypeId);
		genericDAO.remove(tAffRelType);
	}

	/**
	 * Updates a TAffRelType entity object in to the persistent store
	 * 
	 * @param tAffRelType
	 *            TAffRelType Entity object to be updated
	 * @return tAffRelType Persisted TAffRelType object
	 */
	public TAffRelType updateTAffRelType(final TAffRelType tAffRelType) {
		LOGGER.info("=========== Update TAffRelType ===========");
		return genericDAO.update(tAffRelType);
	}

	/**
	 * Retrieve an TAffRelType object based on given TAffRelType TAffRelTypeId.
	 * 
	 * @param tAffRelTypeId
	 *            the primary key value of the TAffRelType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAffRelType findTAffRelTypeById(final TAffRelTypeId tAffRelTypeId) {
		LOGGER.info("find TAffRelType instance with TAffRelTypeId: " + tAffRelTypeId);
		return genericDAO.get(clazz, tAffRelTypeId);
	}

	/**
	 * Retrieve TAffRelType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffRelType> list of TAffRelType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAffRelType> findTAffRelTypes(final SearchFilter<TAffRelType> searchFilter) {
		LOGGER.info("=========== Find TAffRelTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAffRelType tAffRelType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAffRelTypeentity", tAffRelType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAffRelTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAffRelTypes(final SearchFilter<TAffRelType> searchFilter) {
		LOGGER.info("=========== Count TAffRelType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAffRelType tAffRelType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAffRelTypeentity", tAffRelType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
