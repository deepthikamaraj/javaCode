package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussObjDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussObjDAO")
public class TBussObjDAOImpl implements TBussObjDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussObjDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TBussObj> clazz;

	public Class<TBussObj> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussObjDAOImpl() {
		super();
		this.clazz = TBussObj.class;
	}

	private static final String JPAQL = "select tBussObjentity from TBussObj tBussObjentity";

	private static final String JPACOUNTQL = "select count(tBussObjentity) from TBussObj tBussObjentity";

	private static final String[] RESTRICTIONS = {
			"tBussObjentity.bussObjId = #{tBussObjentity.getBussObjId}",
			"tBussObjentity.bussObjName like '#{tBussObjentity.getBussObjName}%'",
			"tBussObjentity.createdBy = #{tBussObjentity.getCreatedBy}",
			"Date(tBussObjentity.createDt) = '#{tBussObjentity.getCreateDt}'",
			"tBussObjentity.updatedBy = #{tBussObjentity.getUpdatedBy}",
			"Date(tBussObjentity.updateDt) = '#{tBussObjentity.getUpdateDt}'",
			"tBussObjentity.tenantId = #{tBussObjentity.getTenantId}",
			"tBussObjentity.bussFunctionType like '#{tBussObjentity.getBussFunctionType}%'" };

	/**
	 * Stores a new TBussObj entity object in to the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be persisted
	 * @return tBussObj Persisted TBussObj object
	 */
	public TBussObj createTBussObj(final TBussObj tBussObj) {
		LOGGER.info("=========== Create TBussObj ===========");
		return genericDAO.store(tBussObj);
	}

	/**
	 * Deletes a TBussObj entity object from the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be deleted
	 */
	public void deleteTBussObj(final Integer bussObjId) {
		LOGGER.info("=========== Delete TBussObj ===========");
		final TBussObj tBussObj = genericDAO.get(clazz, bussObjId);
		genericDAO.remove(tBussObj);
	}

	/**
	 * Updates a TBussObj entity object in to the persistent store
	 * 
	 * @param tBussObj
	 *            TBussObj Entity object to be updated
	 * @return tBussObj Persisted TBussObj object
	 */
	public TBussObj updateTBussObj(final TBussObj tBussObj) {
		LOGGER.info("=========== Update TBussObj ===========");
		return genericDAO.update(tBussObj);
	}

	/**
	 * Retrieve an TBussObj object based on given TBussObj bussObjId.
	 * 
	 * @param tBussObjId
	 *            the primary key value of the TBussObj Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussObj findTBussObjById(final Integer tBussObjId) {
		LOGGER.info("find TBussObj instance with bussObjId: " + tBussObjId);
		return genericDAO.get(clazz, tBussObjId);
	}

	/**
	 * Retrieve TBussObj based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObj> list of TBussObj if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussObj> findTBussObjs(
			final SearchFilter<TBussObj> searchFilter) {
		LOGGER.info("=========== Find TBussObjs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussObj tBussObj = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		// int maxresult = 3;
		// int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussObjentity", tBussObj);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussObjs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussObjs(final SearchFilter<TBussObj> searchFilter) {
		LOGGER.info("=========== Count TBussObj ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussObj tBussObj = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussObjentity", tBussObj);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * fetchBussObjByFunctionId
	 * 
	 * @param busfId
	 * @param tenantId
	 * @return
	 */
	public List<TBussObj> fetchBussObjByFunctionId(Integer busfId,
			Short tenantId) {
		List<Object> list = new ArrayList<Object>();
		list.add(busfId);
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findTBussObjByBussAssoc", list, 0, -1);
	}

	/**
	 * getAttributeBussFuncTypeInTBussObjs
	 * 
	 * @param tenantId
	 * @return
	 */
	@Override
	public List<TBussObj> getAttributeBussFuncTypeInTBussObjs(Short tenantId) {
		LOGGER.info("------find TBussObj instance with bussFuncTye ----");
		List<Object> list = new ArrayList<Object>();
		list.add("AttributeManagement");
		list.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findTBussObjByBussFuncType", list, 0, -1);
		// return
		// genericDAO.findEntitiesByNamedQuery("findTBussObjByBussFuncType","AttributeManagement");
	}

	/**
	 * findTBussObjByBussFuncTypeBussNameAndTenant
	 * 
	 * @param SPName
	 * @param tenantId
	 * @return
	 */
	@Override
	public List<TBussObj> findTBussObjByBussFuncTypeBussNameAndTenant(
			String SPName, Short tenantId) {
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add("AttributeManagement");
		queryParam.add(SPName);
		queryParam.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond(
				"findTBussObjByBussFuncTypeBussNameAndTenant", queryParam, 0,
				-1);
	}
}
