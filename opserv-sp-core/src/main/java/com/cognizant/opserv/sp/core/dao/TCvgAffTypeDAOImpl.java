package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgAffType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgAffTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgAffTypeDAO")
public class TCvgAffTypeDAOImpl implements TCvgAffTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCvgAffTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCvgAffType> clazz;

	public Class<TCvgAffType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgAffTypeDAOImpl() {
		super();
		this.clazz = TCvgAffType.class;
	}

	private static final String JPAQL = "select tCvgAffTypeentity from TCvgAffType tCvgAffTypeentity";

	private static final String JPACOUNTQL = "select count(*) from TCvgAffType tCvgAffTypeentity";

	private static final String[] RESTRICTIONS = {
			"tCvgAffTypeentity.tCvgAffTypeId.affTypeId = #{tCvgAffTypeentity.tCvgAffTypeId.getAffTypeId}",
			"tCvgAffTypeentity.tCvgAffTypeId.localeId like '#{tCvgAffTypeentity.tCvgAffTypeId.getLocaleId}%'",
			"tCvgAffTypeentity.affTypeName like '#{tCvgAffTypeentity.getAffTypeName}%'",
			"tCvgAffTypeentity.affTypeDesc like '#{tCvgAffTypeentity.getAffTypeDesc}%'",
			"tCvgAffTypeentity.activeFlag = #{tCvgAffTypeentity.getActiveFlag}",
			"tCvgAffTypeentity.createdBy = #{tCvgAffTypeentity.getCreatedBy}",
			"Date(tCvgAffTypeentity.createDt) = '#{tCvgAffTypeentity.getCreateDt}'",
			"tCvgAffTypeentity.updatedBy = #{tCvgAffTypeentity.getUpdatedBy}",
			"Date(tCvgAffTypeentity.updateDt) = '#{tCvgAffTypeentity.getUpdateDt}'",
			"tCvgAffTypeentity.tenantId = #{tCvgAffTypeentity.getTenantId}" };

	/**
	 * Stores a new TCvgAffType entity object in to the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be persisted
	 * @return tCvgAffType Persisted TCvgAffType object
	 */
	public TCvgAffType createTCvgAffType(final TCvgAffType tCvgAffType) {
		LOGGER.info("=========== Create TCvgAffType ===========");
		return genericDAO.store(tCvgAffType);
	}

	/**
	 * Deletes a TCvgAffType entity object from the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be deleted
	 */
	/*public void deleteTCvgAffType(final TCvgAffTypeId tCvgAffTypeId) {
		LOGGER.info("=========== Delete TCvgAffType ===========");
		final TCvgAffType tCvgAffType = genericDAO.get(clazz, tCvgAffTypeId);
		genericDAO.remove(tCvgAffType);
	}*/

	/**
	 * Updates a TCvgAffType entity object in to the persistent store
	 * 
	 * @param tCvgAffType
	 *            TCvgAffType Entity object to be updated
	 * @return tCvgAffType Persisted TCvgAffType object
	 */
	public TCvgAffType updateTCvgAffType(final TCvgAffType tCvgAffType) {
		LOGGER.info("=========== Update TCvgAffType ===========");
		return genericDAO.update(tCvgAffType);
	}

	/**
	 * Retrieve an TCvgAffType object based on given TCvgAffType TCvgAffTypeId.
	 * 
	 * @param tCvgAffTypeId
	 *            the primary key value of the TCvgAffType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	/*public TCvgAffType findTCvgAffTypeById(final TCvgAffTypeId tCvgAffTypeId) {
		LOGGER.info("find TCvgAffType instance with TCvgAffTypeId: " + tCvgAffTypeId);
		return genericDAO.get(clazz, tCvgAffTypeId);
	}*/

	/**
	 * Retrieve TCvgAffType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgAffType> list of TCvgAffType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgAffType> findTCvgAffTypes(final SearchFilter<TCvgAffType> searchFilter) {
		LOGGER.info("=========== Find TCvgAffTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgAffType tCvgAffType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgAffTypeentity", tCvgAffType);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgAffTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgAffTypes(final SearchFilter<TCvgAffType> searchFilter) {
		LOGGER.info("=========== Count TCvgAffType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgAffType tCvgAffType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgAffTypeentity", tCvgAffType);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Fetching the TCvg Aff Types by Tenantid and LocaleId
	 * @return List<TCvgAffType> list of TCvgAffType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgAffType> findTCvgAffTypesByTnIdLocaleId(Short tenantId,
			String localeId) {
			List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchTCvgAffTypesByTenantIdLocalId",paramList, 0, -1);
	}

}
