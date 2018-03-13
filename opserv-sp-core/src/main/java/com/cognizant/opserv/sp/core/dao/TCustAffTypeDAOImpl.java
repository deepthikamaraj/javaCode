package com.cognizant.opserv.sp.core.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCustAffType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAffTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAffTypeDAO")
public class TCustAffTypeDAOImpl implements TCustAffTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAffTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TCUSTAFFTYPE = "tCustAffType";

	private final Class<TCustAffType> clazz;

	public Class<TCustAffType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAffTypeDAOImpl() {
		super();
		this.clazz = TCustAffType.class;
	}

	private static final String JPAQL = "select tCustAffTypeentity from TCustAffType tCustAffTypeentity";

	private static final String JPACOUNTQL = "select count(tCustAffTypeentity) from TCustAffType tCustAffTypeentity";

	private static final String[] RESTRICTIONS = {
			"tCustAffTypeentity.custAffTypeId = #{tCustAffTypeentity.getCustAffTypeId}",
			"tCustAffTypeentity.typeName like '#{tCustAffTypeentity.getTypeName}%'",
			"tCustAffTypeentity.typeDesc like '#{tCustAffTypeentity.getTypeDesc}%'",
			"tCustAffTypeentity.activeFlag = #{tCustAffTypeentity.getActiveFlag}",
			"tCustAffTypeentity.createdBy = #{tCustAffTypeentity.getCreatedBy}",
			"Date(tCustAffTypeentity.createDt) = '#{tCustAffTypeentity.getCreateDt}'",
			"tCustAffTypeentity.updatedBy = #{tCustAffTypeentity.getUpdatedBy}",
			"Date(tCustAffTypeentity.updateDt) = '#{tCustAffTypeentity.getUpdateDt}'",
			"tCustAffTypeentity.tenantId = #{tCustAffTypeentity.getTenantId}",
			"tCustAffTypeentity.tCustAffType.custAffTypeId = #{tCustAffTypeentity.tCustAffType.getCustAffTypeId}" };

	/**
	 * Stores a new TCustAffType entity object in to the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be persisted
	 * @return tCustAffType Persisted TCustAffType object
	 */
	public TCustAffType createTCustAffType(final TCustAffType tCustAffType) {
		LOGGER.info("=========== Create TCustAffType ===========");
		return genericDAO.store(tCustAffType);
	}

	/**
	 * Deletes a TCustAffType entity object from the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be deleted
	 */
	public void deleteTCustAffType(final Integer custAffTypeId) {
		LOGGER.info("=========== Delete TCustAffType ===========");
		final TCustAffType tCustAffType = genericDAO.get(clazz, custAffTypeId);
		genericDAO.remove(tCustAffType);
	}

	/**
	 * Updates a TCustAffType entity object in to the persistent store
	 * 
	 * @param tCustAffType
	 *            TCustAffType Entity object to be updated
	 * @return tCustAffType Persisted TCustAffType object
	 */
	public TCustAffType updateTCustAffType(final TCustAffType tCustAffType) {
		LOGGER.info("=========== Update TCustAffType ===========");
		return genericDAO.update(tCustAffType);
	}

	/**
	 * Retrieve an TCustAffType object based on given TCustAffType custAffTypeId.
	 * 
	 * @param tCustAffTypeId
	 *            the primary key value of the TCustAffType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAffType findTCustAffTypeById(final Integer tCustAffTypeId) {
		LOGGER.info("find TCustAffType instance with custAffTypeId: " + tCustAffTypeId);
		return genericDAO.get(clazz, tCustAffTypeId);
	}

	/**
	 * Retrieve TCustAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffType> list of TCustAffType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAffType> findTCustAffTypes(final SearchFilter<TCustAffType> searchFilter) {
		LOGGER.info("=========== Find TCustAffTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAffType tCustAffType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAffTypeentity", tCustAffType);

/*		if (tCustAffType.getTCustAffType() == null) {
			jpaQuery.bind(TCUSTAFFTYPE, new TCustAffType());
		} else {
			LOGGER.info("tCustAffType {}", tCustAffType.getTCustAffType());

			jpaQuery.bind(TCUSTAFFTYPE, tCustAffType.getTCustAffType());
		}
*/		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAffTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAffTypes(final SearchFilter<TCustAffType> searchFilter) {
		LOGGER.info("=========== Count TCustAffType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAffType tCustAffType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAffTypeentity", tCustAffType);

/*		if (tCustAffType.getTCustAffType() == null) {
			jpaCountQuery.bind(TCUSTAFFTYPE, new TCustAffType());
		} else {
			LOGGER.info("tCustAffType {}", tCustAffType.getTCustAffType());

			jpaCountQuery.bind(TCUSTAFFTYPE, tCustAffType.getTCustAffType());
		}
*/
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAffType based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAffType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAffType> list of TCustAffTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
/*	public List<TCustAffType> getTCustAffTypesByTCustAffType(final SearchFilter<TCustAffType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustAffType tCustAffType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTCustAffTypeByTCustAffType", tCustAffType, index, maxresult);
	}*/

	/**
	 * Count TCustAffType based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAffType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
/*	public Object countTCustAffTypesByTCustAffType(final SearchFilter<TCustAffType> searchFilter) {

		final TCustAffType tCustAffType = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTCustAffTypesByTCustAffType", tCustAffType);
	}*/

}
