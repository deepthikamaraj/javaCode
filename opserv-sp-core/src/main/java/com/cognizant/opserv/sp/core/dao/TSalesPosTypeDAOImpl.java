package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TSalesPosType;
import com.cognizant.opserv.sp.core.entity.TSalesPosTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TSalesPosTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tSalesPosTypeDAO")
public class TSalesPosTypeDAOImpl implements TSalesPosTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TSalesPosTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TSalesPosType> clazz;

	public Class<TSalesPosType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TSalesPosTypeDAOImpl() {
		super();
		this.clazz = TSalesPosType.class;
	}

	private static final String JPAQL = "select tSalesPosTypeentity from TSalesPosType tSalesPosTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TSalesPosType tSalesPosTypeentity";

	private static final String[] RESTRICTIONS = {
			"tSalesPosTypeentity.tSalesPosTypeId.salesPosTypeId = #{tSalesPosTypeentity.tSalesPosTypeId.getSalesPosTypeId}",
			"tSalesPosTypeentity.tSalesPosTypeId.localeId like '#{tSalesPosTypeentity.tSalesPosTypeId.getLocaleId}%'",
			"tSalesPosTypeentity.typeCd like '#{tSalesPosTypeentity.getTypeCd}%'",
			"tSalesPosTypeentity.typeName like '#{tSalesPosTypeentity.getTypeName}%'",
			"tSalesPosTypeentity.typeDesc like '#{tSalesPosTypeentity.getTypeDesc}%'",
			"tSalesPosTypeentity.createdBy = #{tSalesPosTypeentity.getCreatedBy}",
			"Date(tSalesPosTypeentity.createDt) = '#{tSalesPosTypeentity.getCreateDt}'",
			"tSalesPosTypeentity.updatedBy = #{tSalesPosTypeentity.getUpdatedBy}",
			"Date(tSalesPosTypeentity.updateDt) = '#{tSalesPosTypeentity.getUpdateDt}'",
			"tSalesPosTypeentity.tenantId = #{tSalesPosTypeentity.getTenantId}",
			"tSalesPosTypeentity.activeFlag = #{tSalesPosTypeentity.getActiveFlag}" };

	/**
	 * Stores a new TSalesPosType entity object in to the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be persisted
	 * @return tSalesPosType Persisted TSalesPosType object
	 */
	public TSalesPosType createTSalesPosType(final TSalesPosType tSalesPosType) {
		LOGGER.info("=========== Create TSalesPosType ===========");
		return genericDAO.store(tSalesPosType);
	}

	/**
	 * Deletes a TSalesPosType entity object from the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be deleted
	 */
	public void deleteTSalesPosType(final TSalesPosTypeId tSalesPosTypeId) {
		LOGGER.info("=========== Delete TSalesPosType ===========");
		final TSalesPosType tSalesPosType = genericDAO.get(clazz, tSalesPosTypeId);
		genericDAO.remove(tSalesPosType);
	}

	/**
	 * Updates a TSalesPosType entity object in to the persistent store
	 * 
	 * @param tSalesPosType
	 *            TSalesPosType Entity object to be updated
	 * @return tSalesPosType Persisted TSalesPosType object
	 */
	public TSalesPosType updateTSalesPosType(final TSalesPosType tSalesPosType) {
		LOGGER.info("=========== Update TSalesPosType ===========");
		return genericDAO.update(tSalesPosType);
	}

	/**
	 * Retrieve an TSalesPosType object based on given TSalesPosType TSalesPosTypeId.
	 * 
	 * @param tSalesPosTypeId
	 *            the primary key value of the TSalesPosType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TSalesPosType findTSalesPosTypeById(final TSalesPosTypeId tSalesPosTypeId) {
		LOGGER.info("find TSalesPosType instance with TSalesPosTypeId: " + tSalesPosTypeId);
		return genericDAO.get(clazz, tSalesPosTypeId);
	}

	/**
	 * Retrieve TSalesPosType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TSalesPosType> list of TSalesPosType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TSalesPosType> findTSalesPosTypes(final SearchFilter<TSalesPosType> searchFilter) {
		LOGGER.info("=========== Find TSalesPosTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TSalesPosType tSalesPosType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tSalesPosTypeentity", tSalesPosType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TSalesPosTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTSalesPosTypes(final SearchFilter<TSalesPosType> searchFilter) {
		LOGGER.info("=========== Count TSalesPosType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TSalesPosType tSalesPosType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tSalesPosTypeentity", tSalesPosType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
