package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrdPrtType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrdPrtTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrdPrtTypeDAO")
public class TPrdPrtTypeDAOImpl implements TPrdPrtTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrdPrtTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TPrdPrtType> clazz;

	public Class<TPrdPrtType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrdPrtTypeDAOImpl() {
		super();
		this.clazz = TPrdPrtType.class;
	}

	private static final String JPAQL = "select tPrdPrtTypeentity from TPrdPrtType tPrdPrtTypeentity";

	private static final String JPACOUNTQL = "select count(tPrdPrtTypeentity) from TPrdPrtType tPrdPrtTypeentity";

	private static final String[] RESTRICTIONS = {
			"tPrdPrtTypeentity.prdPrtTypeId = #{tPrdPrtTypeentity.getPrdPrtTypeId}",
			"tPrdPrtTypeentity.prdPrtTypeDesc like '#{tPrdPrtTypeentity.getPrdPrtTypeDesc}%'",
			"tPrdPrtTypeentity.activeFlag = #{tPrdPrtTypeentity.getActiveFlag}",
			"tPrdPrtTypeentity.createdBy = #{tPrdPrtTypeentity.getCreatedBy}",
			"Date(tPrdPrtTypeentity.createDt) = '#{tPrdPrtTypeentity.getCreateDt}'",
			"tPrdPrtTypeentity.updatedBy = #{tPrdPrtTypeentity.getUpdatedBy}",
			"Date(tPrdPrtTypeentity.updateDt) = '#{tPrdPrtTypeentity.getUpdateDt}'",
			"tPrdPrtTypeentity.tenantId = #{tPrdPrtTypeentity.getTenantId}",
			"tPrdPrtTypeentity.localeId like '#{tPrdPrtTypeentity.getLocaleId}%'" };

	/**
	 * Stores a new TPrdPrtType entity object in to the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be persisted
	 * @return tPrdPrtType Persisted TPrdPrtType object
	 */
	public TPrdPrtType createTPrdPrtType(final TPrdPrtType tPrdPrtType) {
		LOGGER.info("=========== Create TPrdPrtType ===========");
		return genericDAO.store(tPrdPrtType);
	}

	/**
	 * Deletes a TPrdPrtType entity object from the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be deleted
	 */
	public void deleteTPrdPrtType(final Integer prdPrtTypeId) {
		LOGGER.info("=========== Delete TPrdPrtType ===========");
		final TPrdPrtType tPrdPrtType = genericDAO.get(clazz, prdPrtTypeId);
		genericDAO.remove(tPrdPrtType);
	}

	/**
	 * Updates a TPrdPrtType entity object in to the persistent store
	 * 
	 * @param tPrdPrtType
	 *            TPrdPrtType Entity object to be updated
	 * @return tPrdPrtType Persisted TPrdPrtType object
	 */
	public TPrdPrtType updateTPrdPrtType(final TPrdPrtType tPrdPrtType) {
		LOGGER.info("=========== Update TPrdPrtType ===========");
		return genericDAO.update(tPrdPrtType);
	}

	/**
	 * Retrieve an TPrdPrtType object based on given TPrdPrtType prdPrtTypeId.
	 * 
	 * @param tPrdPrtTypeId
	 *            the primary key value of the TPrdPrtType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrdPrtType findTPrdPrtTypeById(final Integer tPrdPrtTypeId) {
		LOGGER.info("find TPrdPrtType instance with prdPrtTypeId: "
				+ tPrdPrtTypeId);
		return genericDAO.get(clazz, tPrdPrtTypeId);
	}

	/**
	 * Retrieve TPrdPrtType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrdPrtType> list of TPrdPrtType if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrdPrtType> findTPrdPrtTypes(
			final SearchFilter<TPrdPrtType> searchFilter) {
		LOGGER.info("=========== Find TPrdPrtTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrdPrtTypeentity", tPrdPrtType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrdPrtTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrdPrtTypes(final SearchFilter<TPrdPrtType> searchFilter) {
		LOGGER.info("=========== Count TPrdPrtType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrdPrtType tPrdPrtType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrdPrtTypeentity",
				tPrdPrtType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Fetch Product Priority Description for given PrdPrtTypesID.
	 * 
	 * @return
	 */
	public List<Object[]> findPrdPrtTypeDesc(TPrdPrtType tPrdPrtType) {
		LOGGER.info("=========== find PrdPrtTypeDesc ===========");
		
		List paramList = new ArrayList();
		paramList.add(tPrdPrtType.gettPrdPrtTypeId().getPrdPrtTypeId());
		paramList.add(tPrdPrtType.getTenantId());
		
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrdPrtTypesDesc",paramList,0,-1);

		
	}

	

}
