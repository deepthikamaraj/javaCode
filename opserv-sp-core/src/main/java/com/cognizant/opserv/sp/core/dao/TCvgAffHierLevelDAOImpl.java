package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TCvgAffHierLevel;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCvgAffHierLevelDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCvgAffHierLevelDAO")
public class TCvgAffHierLevelDAOImpl implements TCvgAffHierLevelDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCvgAffHierLevelDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TCvgAffHierLevel> clazz;

	public Class<TCvgAffHierLevel> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCvgAffHierLevelDAOImpl() {
		super();
		this.clazz = TCvgAffHierLevel.class;
	}

	private static final String JPAQL = "select tCvgAffHierLevelentity from TCvgAffHierLevel tCvgAffHierLevelentity";

	private static final String JPACOUNTQL = "select count(*) from TCvgAffHierLevel tCvgAffHierLevelentity";

	private static final String[] RESTRICTIONS = {
			"tCvgAffHierLevelentity.tCvgAffHierLevelId.affHierLevelId = #{tCvgAffHierLevelentity.tCvgAffHierLevelId.getAffHierLevelId}",
			"tCvgAffHierLevelentity.tCvgAffHierLevelId.localeId like '#{tCvgAffHierLevelentity.tCvgAffHierLevelId.getLocaleId}%'",
			"tCvgAffHierLevelentity.hierLevelName like '#{tCvgAffHierLevelentity.getHierLevelName}%'",
			"tCvgAffHierLevelentity.hierLevelDesc like '#{tCvgAffHierLevelentity.getHierLevelDesc}%'",
			"tCvgAffHierLevelentity.activeFlag = #{tCvgAffHierLevelentity.getActiveFlag}",
			"tCvgAffHierLevelentity.createdBy = #{tCvgAffHierLevelentity.getCreatedBy}",
			"Date(tCvgAffHierLevelentity.createDt) = '#{tCvgAffHierLevelentity.getCreateDt}'",
			"tCvgAffHierLevelentity.updatedBy = #{tCvgAffHierLevelentity.getUpdatedBy}",
			"Date(tCvgAffHierLevelentity.updateDt) = '#{tCvgAffHierLevelentity.getUpdateDt}'",
			"tCvgAffHierLevelentity.tenantId = #{tCvgAffHierLevelentity.getTenantId}" };

	/**
	 * Stores a new TCvgAffHierLevel entity object in to the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be persisted
	 * @return tCvgAffHierLevel Persisted TCvgAffHierLevel object
	 */
	public TCvgAffHierLevel createTCvgAffHierLevel(final TCvgAffHierLevel tCvgAffHierLevel) {
		LOGGER.info("=========== Create TCvgAffHierLevel ===========");
		return genericDAO.store(tCvgAffHierLevel);
	}

	/**
	 * Deletes a TCvgAffHierLevel entity object from the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be deleted
	 */
	/*public void deleteTCvgAffHierLevel(final TCvgAffHierLevelId tCvgAffHierLevelId) {
		LOGGER.info("=========== Delete TCvgAffHierLevel ===========");
		final TCvgAffHierLevel tCvgAffHierLevel = genericDAO.get(clazz, tCvgAffHierLevelId);
		genericDAO.remove(tCvgAffHierLevel);
	}
*/
	/**
	 * Updates a TCvgAffHierLevel entity object in to the persistent store
	 * 
	 * @param tCvgAffHierLevel
	 *            TCvgAffHierLevel Entity object to be updated
	 * @return tCvgAffHierLevel Persisted TCvgAffHierLevel object
	 */
	public TCvgAffHierLevel updateTCvgAffHierLevel(final TCvgAffHierLevel tCvgAffHierLevel) {
		LOGGER.info("=========== Update TCvgAffHierLevel ===========");
		return genericDAO.update(tCvgAffHierLevel);
	}

	/**
	 * Retrieve an TCvgAffHierLevel object based on given TCvgAffHierLevel TCvgAffHierLevelId.
	 * 
	 * @param tCvgAffHierLevelId
	 *            the primary key value of the TCvgAffHierLevel Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	/*public TCvgAffHierLevel findTCvgAffHierLevelById(final TCvgAffHierLevelId tCvgAffHierLevelId) {
		LOGGER.info("find TCvgAffHierLevel instance with TCvgAffHierLevelId: " + tCvgAffHierLevelId);
		return genericDAO.get(clazz, tCvgAffHierLevelId);
	}*/

	/**
	 * Retrieve TCvgAffHierLevel based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCvgAffHierLevel> list of TCvgAffHierLevel if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgAffHierLevel> findTCvgAffHierLevels(final SearchFilter<TCvgAffHierLevel> searchFilter) {
		LOGGER.info("=========== Find TCvgAffHierLevels ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCvgAffHierLevel tCvgAffHierLevel = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCvgAffHierLevelentity", tCvgAffHierLevel);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCvgAffHierLevels.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCvgAffHierLevels(final SearchFilter<TCvgAffHierLevel> searchFilter) {
		LOGGER.info("=========== Count TCvgAffHierLevel ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCvgAffHierLevel tCvgAffHierLevel = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCvgAffHierLevelentity", tCvgAffHierLevel);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Fetchiing the All the TCvg Aff Hier Level dts based on the Tenant Id and Locale Id
	 * @return List<TCvgAffHierLevel> list of TCvgAffHierLevel if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCvgAffHierLevel> findTCvgAffHierLevelsByTntIdLocaleId(
			Short tenantId, String localeId) {
		List paramList = new ArrayList();
		paramList.add(tenantId);
		paramList.add(localeId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FetchTCvgAffHierLevelsByTntIdLocaleId",paramList, 0, -1);
	}

}
