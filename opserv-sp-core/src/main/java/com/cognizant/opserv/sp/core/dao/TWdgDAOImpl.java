package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TWdg;
import com.cognizant.opserv.sp.core.entity.TWdgId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TWdgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tWdgDAO")
public class TWdgDAOImpl implements TWdgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TWdgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TWdg> clazz;

	public Class<TWdg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TWdgDAOImpl() {
		super();
		this.clazz = TWdg.class;
	}

	private static final String JPAQL = "select tWdgentity from TWdg tWdgentity";

	private static final String JPACOUNTQL = "select count(*) from TWdg tWdgentity";

	private static final String[] RESTRICTIONS = { "tWdgentity.tWdgId.wdgId = #{tWdgentity.tWdgId.getWdgId}",
			"tWdgentity.tWdgId.localeId like '#{tWdgentity.tWdgId.getLocaleId}%'",
			"tWdgentity.wdgName like '#{tWdgentity.getWdgName}%'",
			"tWdgentity.wdgDesc like '#{tWdgentity.getWdgDesc}%'",
			"tWdgentity.activeFlag = #{tWdgentity.getActiveFlag}", "tWdgentity.createdBy = #{tWdgentity.getCreatedBy}",
			"Date(tWdgentity.createDt) = '#{tWdgentity.getCreateDt}'",
			"tWdgentity.updatedBy = #{tWdgentity.getUpdatedBy}",
			"Date(tWdgentity.updateDt) = '#{tWdgentity.getUpdateDt}'",
			"tWdgentity.tenantId = #{tWdgentity.getTenantId}" };

	
	
	
	
	@Override
	public List<TWdg> findTWdgsById(List<Integer> wdgId,Short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(wdgId);
		paramList.add(tenantId);
		List<TWdg> widgetList = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTWdgsbyWdgId", paramList, 0,100);
		return widgetList;
	}
	
	
	
	
	
	
	/**
	 * Stores a new TWdg entity object in to the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be persisted
	 * @return tWdg Persisted TWdg object
	 */
	public TWdg createTWdg(final TWdg tWdg) {
		LOGGER.info("=========== Create TWdg ===========");
		return genericDAO.store(tWdg);
	}

	/**
	 * Deletes a TWdg entity object from the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be deleted
	 */
	public void deleteTWdg(final TWdgId tWdgId) {
		LOGGER.info("=========== Delete TWdg ===========");
		final TWdg tWdg = genericDAO.get(clazz, tWdgId);
		genericDAO.remove(tWdg);
	}

	/**
	 * Updates a TWdg entity object in to the persistent store
	 * 
	 * @param tWdg
	 *            TWdg Entity object to be updated
	 * @return tWdg Persisted TWdg object
	 */
	public TWdg updateTWdg(final TWdg tWdg) {
		LOGGER.info("=========== Update TWdg ===========");
		return genericDAO.update(tWdg);
	}

	/**
	 * Retrieve an TWdg object based on given TWdg TWdgId.
	 * 
	 * @param tWdgId
	 *            the primary key value of the TWdg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TWdg findTWdgById(final TWdgId tWdgId) {
		LOGGER.info("find TWdg instance with TWdgId: " + tWdgId);
		return genericDAO.get(clazz, tWdgId);
	}

	/**
	 * Retrieve TWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TWdg> list of TWdg if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TWdg> findTWdgs(final SearchFilter<TWdg> searchFilter) {
		LOGGER.info("=========== Find TWdgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TWdg tWdg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tWdgentity", tWdg);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TWdgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTWdgs(final SearchFilter<TWdg> searchFilter) {
		LOGGER.info("=========== Count TWdg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TWdg tWdg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tWdgentity", tWdg);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
