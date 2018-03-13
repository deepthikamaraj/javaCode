package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TClassType;
import com.cognizant.opserv.sp.core.entity.TClassTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TClassTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tClassTypeDAO")
public class TClassTypeDAOImpl implements TClassTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TClassTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TClassType> clazz;

	public Class<TClassType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TClassTypeDAOImpl() {
		super();
		this.clazz = TClassType.class;
	}

	private static final String JPAQL = "select tClassTypeentity from TClassType tClassTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TClassType tClassTypeentity";

	private static final String[] RESTRICTIONS = {
			"tClassTypeentity.tClassTypeId.localeId like '#{tClassTypeentity.tClassTypeId.getLocaleId}%'",
			"tClassTypeentity.tClassTypeId.classTypeId = #{tClassTypeentity.tClassTypeId.getClassTypeId}",
			"tClassTypeentity.classTypeName like '#{tClassTypeentity.getClassTypeName}%'",
			"tClassTypeentity.classTypeCd like '#{tClassTypeentity.getClassTypeCd}%'",
			"tClassTypeentity.classTypeDesc like '#{tClassTypeentity.getClassTypeDesc}%'",
			"tClassTypeentity.activeFlag = #{tClassTypeentity.getActiveFlag}",
			"tClassTypeentity.createdBy = #{tClassTypeentity.getCreatedBy}",
			"Date(tClassTypeentity.createDt) = '#{tClassTypeentity.getCreateDt}'",
			"tClassTypeentity.updatedBy = #{tClassTypeentity.getUpdatedBy}",
			"Date(tClassTypeentity.updateDt) = '#{tClassTypeentity.getUpdateDt}'",
			"tCustTypeentity.categoryId = #{tCustTypeentity.getCategoryId}",
			"tCustTypeentity.prsFlag = #{tCustTypeentity.getPrsFlag}" ,
			"tClassTypeentity.tenantId = #{tClassTypeentity.getTenantId}" };

	/**
	 * Stores a new TClassType entity object in to the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be persisted
	 * @return tClassType Persisted TClassType object
	 */
	public TClassType createTClassType(final TClassType tClassType) {
		LOGGER.info("=========== Create TClassType ===========");
		return genericDAO.store(tClassType);
	}

	/**
	 * Deletes a TClassType entity object from the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be deleted
	 */
	public void deleteTClassType(final TClassTypeId tClassTypeId) {
		LOGGER.info("=========== Delete TClassType ===========");
		final TClassType tClassType = genericDAO.get(clazz, tClassTypeId);
		genericDAO.remove(tClassType);
	}

	/**
	 * Updates a TClassType entity object in to the persistent store
	 * 
	 * @param tClassType
	 *            TClassType Entity object to be updated
	 * @return tClassType Persisted TClassType object
	 */
	public TClassType updateTClassType(final TClassType tClassType) {
		LOGGER.info("=========== Update TClassType ===========");
		return genericDAO.update(tClassType);
	}

	/**
	 * Retrieve an TClassType object based on given TClassType TClassTypeId.
	 * 
	 * @param tClassTypeId
	 *            the primary key value of the TClassType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TClassType findTClassTypeById(final TClassTypeId tClassTypeId) {
		LOGGER.info("find TClassType instance with TClassTypeId: " + tClassTypeId);
		return genericDAO.get(clazz, tClassTypeId);
	}

	/**
	 * Retrieve TClassType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassType> list of TClassType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TClassType> findTClassTypes(final SearchFilter<TClassType> searchFilter) {
		LOGGER.info("=========== Find TClassTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TClassType tClassType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tClassTypeentity", tClassType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TClassTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTClassTypes(final SearchFilter<TClassType> searchFilter) {
		LOGGER.info("=========== Count TClassType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TClassType tClassType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tClassTypeentity", tClassType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
