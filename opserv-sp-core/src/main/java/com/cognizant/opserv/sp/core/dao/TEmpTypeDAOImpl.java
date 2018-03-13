package com.cognizant.opserv.sp.core.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TEmpType;
import com.cognizant.opserv.sp.core.entity.TEmpTypeId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TEmpTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tEmpTypeDAO")
public class TEmpTypeDAOImpl implements TEmpTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TEmpTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TEmpType> clazz;

	public Class<TEmpType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TEmpTypeDAOImpl() {
		super();
		this.clazz = TEmpType.class;
	}

	private static final String JPAQL = "select tEmpTypeentity from TEmpType tEmpTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TEmpType tEmpTypeentity";

	private static final String[] RESTRICTIONS = {
			"tEmpTypeentity.tEmpTypeId.localeId like '#{tEmpTypeentity.tEmpTypeId.getLocaleId}%'",
			"tEmpTypeentity.tEmpTypeId.empTypeId = #{tEmpTypeentity.tEmpTypeId.getEmpTypeId}",
			"tEmpTypeentity.empTypeCd like '#{tEmpTypeentity.getEmpTypeCd}%'",
			"tEmpTypeentity.empTypeDesc like '#{tEmpTypeentity.getEmpTypeDesc}%'",
			"tEmpTypeentity.createdBy = #{tEmpTypeentity.getCreatedBy}",
			"Date(tEmpTypeentity.createDt) = '#{tEmpTypeentity.getCreateDt}'",
			"tEmpTypeentity.updatedBy = #{tEmpTypeentity.getUpdatedBy}",
			"Date(tEmpTypeentity.updateDt) = '#{tEmpTypeentity.getUpdateDt}'",
			"tEmpTypeentity.tenantId = #{tEmpTypeentity.getTenantId}" };

	/**
	 * Stores a new TEmpType entity object in to the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be persisted
	 * @return tEmpType Persisted TEmpType object
	 */
	public TEmpType createTEmpType(final TEmpType tEmpType) {
		LOGGER.info("=========== Create TEmpType ===========");
		return genericDAO.store(tEmpType);
	}

	/**
	 * Deletes a TEmpType entity object from the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be deleted
	 */
	public void deleteTEmpType(final TEmpTypeId tEmpTypeId) {
		LOGGER.info("=========== Delete TEmpType ===========");
		final TEmpType tEmpType = genericDAO.get(clazz, tEmpTypeId);
		genericDAO.remove(tEmpType);
	}

	/**
	 * Updates a TEmpType entity object in to the persistent store
	 * 
	 * @param tEmpType
	 *            TEmpType Entity object to be updated
	 * @return tEmpType Persisted TEmpType object
	 */
	public TEmpType updateTEmpType(final TEmpType tEmpType) {
		LOGGER.info("=========== Update TEmpType ===========");
		return genericDAO.update(tEmpType);
	}

	/**
	 * Retrieve an TEmpType object based on given TEmpType TEmpTypeId.
	 * 
	 * @param tEmpTypeId
	 *            the primary key value of the TEmpType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TEmpType findTEmpTypeById(final TEmpTypeId tEmpTypeId) {
		LOGGER.info("find TEmpType instance with TEmpTypeId: " + tEmpTypeId);
		return genericDAO.get(clazz, tEmpTypeId);
	}

	/**
	 * Retrieve TEmpType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TEmpType> list of TEmpType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TEmpType> findTEmpTypes(final SearchFilter<TEmpType> searchFilter) {
		LOGGER.info("=========== Find TEmpTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TEmpType tEmpType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tEmpTypeentity", tEmpType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TEmpTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTEmpTypes(final SearchFilter<TEmpType> searchFilter) {
		LOGGER.info("=========== Count TEmpType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TEmpType tEmpType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tEmpTypeentity", tEmpType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TEmpTypeDAO#findEmpTypeByLocale(java.lang.Short, java.lang.String)
	 */
	@Override
	public List<TEmpType> findEmpTypeByLocale(Short tenantId, String locale) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(tenantId);
		paramList.add(locale);
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTEmpType", paramList, 0, -1);
	}
	
}
