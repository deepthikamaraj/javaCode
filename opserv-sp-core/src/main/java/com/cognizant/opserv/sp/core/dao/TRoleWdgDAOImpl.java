package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TRoleWdg;
import com.cognizant.opserv.sp.core.entity.TRoleWdgId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TRoleWdgDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tRoleWdgDAO")
public class TRoleWdgDAOImpl implements TRoleWdgDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(TRoleWdgDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TRoleWdg> clazz;

	public Class<TRoleWdg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TRoleWdgDAOImpl() {
		super();
		this.clazz = TRoleWdg.class;
	}

	private static final String JPAQL = "select tRoleWdgentity from TRoleWdg tRoleWdgentity";

	private static final String JPACOUNTQL = "select count(*) from TRoleWdg tRoleWdgentity";

	private static final String[] RESTRICTIONS = {
			"tRoleWdgentity.tRoleWdgId.wdgId = #{tRoleWdgentity.tRoleWdgId.getWdgId}",
			"tRoleWdgentity.tRoleWdgId.roleId = #{tRoleWdgentity.tRoleWdgId.getRoleId}",
			"tRoleWdgentity.activeFlag = #{tRoleWdgentity.getActiveFlag}",
			"tRoleWdgentity.createdBy = #{tRoleWdgentity.getCreatedBy}",
			"Date(tRoleWdgentity.createDt) = '#{tRoleWdgentity.getCreateDt}'",
			"tRoleWdgentity.updatedBy = #{tRoleWdgentity.getUpdatedBy}",
			"Date(tRoleWdgentity.updateDt) = '#{tRoleWdgentity.getUpdateDt}'",
			"tRoleWdgentity.tenantId = #{tRoleWdgentity.getTenantId}",
			"tRoleWdgentity.mandatoryFlag = #{tRoleWdgentity.getMandatoryFlag}",
			"tRoleWdgentity.orderSeq = #{tRoleWdgentity.getOrderSeq}",
			"tRoleWdgentity.defFlag = #{tRoleWdgentity.getDefFlag}",
			"tRoleWdgentity.tRole.roleId = #{tRoleWdgentity.tRole.getRoleId}" };

	public List<TRoleWdg> findTRoleWdgs(final Integer roleId, Short tenantId) {
		List paramList = new ArrayList<>();
		paramList.add(roleId);
		paramList.add(tenantId);
		List<TRoleWdg> roleWidgetList = genericDAO
				.findEntitiesByNamedQueryMultiCond("FindRoleWids", paramList,
						0, -1);
		return roleWidgetList;

	}

	/**
	 * Stores a new TRoleWdg entity object in to the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be persisted
	 * @return tRoleWdg Persisted TRoleWdg object
	 */
	public TRoleWdg createTRoleWdg(final TRoleWdg tRoleWdg) {
		LOGGER.info("=========== Create TRoleWdg ===========");
		return genericDAO.store(tRoleWdg);
	}

	/**
	 * Deletes a TRoleWdg entity object from the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be deleted
	 */
	public void deleteTRoleWdg(final TRoleWdgId tRoleWdgId) {
		LOGGER.info("=========== Delete TRoleWdg ===========");
		final TRoleWdg tRoleWdg = genericDAO.get(clazz, tRoleWdgId);
		genericDAO.remove(tRoleWdg);
	}

	/**
	 * Updates a TRoleWdg entity object in to the persistent store
	 * 
	 * @param tRoleWdg
	 *            TRoleWdg Entity object to be updated
	 * @return tRoleWdg Persisted TRoleWdg object
	 */
	public TRoleWdg updateTRoleWdg(final TRoleWdg tRoleWdg) {
		LOGGER.info("=========== Update TRoleWdg ===========");
		return genericDAO.update(tRoleWdg);
	}

	/**
	 * Retrieve an TRoleWdg object based on given TRoleWdg TRoleWdgId.
	 * 
	 * @param tRoleWdgId
	 *            the primary key value of the TRoleWdg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TRoleWdg findTRoleWdgById(final TRoleWdgId tRoleWdgId) {
		LOGGER.info("find TRoleWdg instance with TRoleWdgId: " + tRoleWdgId);
		return genericDAO.get(clazz, tRoleWdgId);
	}

	/**
	 * Retrieve TRoleWdg based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TRoleWdg> list of TRoleWdg if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TRoleWdg> findTRoleWdgs(
			final SearchFilter<TRoleWdg> searchFilter) {
		LOGGER.info("=========== Find TRoleWdgs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TRoleWdg tRoleWdg = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tRoleWdgentity", tRoleWdg);

		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TRoleWdgs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTRoleWdgs(final SearchFilter<TRoleWdg> searchFilter) {
		LOGGER.info("=========== Count TRoleWdg ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TRoleWdg tRoleWdg = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tRoleWdgentity", tRoleWdg);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}
	
	/*@Override
	public boolean getMandatoryInfo(TRoleWdg tRoleWdg) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tRoleWdg);
		List<TRoleWdg> roleWidgetList = genericDAO.findEntitiesByNamedQuery(
				"GetMandatoryInfo", queryParam);

		boolean mandatryFlag = false;
		char mandatoryFlag = roleWidgetList.get(0).getMandatoryFlag();
		if (mandatoryFlag == 'Y')
			mandatryFlag = true;
		else
			mandatryFlag = false;

		return mandatryFlag;
	}
*/

	@Override
	public List<TRoleWdg> getMandatoryInfo(List<TRoleWdg> tRoleWdg) {
		List<Object> queryParam = new ArrayList<Object>();
		Character active_flag='Y';
		queryParam.add(active_flag);
		queryParam.add(tRoleWdg.get(0).getTenantId());
		queryParam.add(tRoleWdg.get(0).getTRoleWdgId().getRoleId());
		List<TRoleWdg> roleWidgetList = genericDAO.findEntitiesByNamedQuery(
				"GetMandatoryInfo", queryParam);

		/*boolean mandatryFlag = false;
		char mandatoryFlag = roleWidgetList.get(0).getMandatoryFlag();
		if (mandatoryFlag == 'Y')
			mandatryFlag = true;
		else
			mandatryFlag = false;

		return mandatryFlag;*/
		return roleWidgetList;
	}
	
	
}
