package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBussFunction;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.core.entity.TBussObjAssoc;
import com.cognizant.opserv.sp.core.entity.TBussObjAssocId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TBussObjAssocDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBussObjAssocDAO")
public class TBussObjAssocDAOImpl implements TBussObjAssocDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TBussObjAssocDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TBUSSFUNCTION = "tBussFunction";
	private static final String TBUSSOBJ = "tBussObj";

	private final Class<TBussObjAssoc> clazz;

	public Class<TBussObjAssoc> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBussObjAssocDAOImpl() {
		super();
		this.clazz = TBussObjAssoc.class;
	}

	private static final String JPAQL = "select tBussObjAssocentity from TBussObjAssoc tBussObjAssocentity";

	private static final String JPACOUNTQL = "select count(*) from TBussObjAssoc tBussObjAssocentity";

	private static final String[] RESTRICTIONS = {
			"tBussObjAssocentity.tBussObjAssocId.bussFunctionId = #{tBussObjAssocentity.tBussObjAssocId.getBussFunctionId}",
			"tBussObjAssocentity.tBussObjAssocId.bussObjId = #{tBussObjAssocentity.tBussObjAssocId.getBussObjId}",
			"tBussObjAssocentity.activeFlag = #{tBussObjAssocentity.getActiveFlag}",
			"tBussObjAssocentity.createdBy = #{tBussObjAssocentity.getCreatedBy}",
			"Date(tBussObjAssocentity.createDt) = '#{tBussObjAssocentity.getCreateDt}'",
			"tBussObjAssocentity.updatedBy = #{tBussObjAssocentity.getUpdatedBy}",
			"Date(tBussObjAssocentity.updateDt) = '#{tBussObjAssocentity.getUpdateDt}'",
			"tBussObjAssocentity.tenantId = #{tBussObjAssocentity.getTenantId}",
			"tBussObjAssocentity.tBussFunction.bussFunctionId = #{tBussObjAssocentity.tBussFunction.getBussFunctionId}",
			"tBussObjAssocentity.tBussObj.bussObjId = #{tBussObjAssocentity.tBussObj.getBussObjId}" };

	/**
	 * Stores a new TBussObjAssoc entity object in to the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be persisted
	 * @return tBussObjAssoc Persisted TBussObjAssoc object
	 */
	public TBussObjAssoc createTBussObjAssoc(final TBussObjAssoc tBussObjAssoc) {
		LOGGER.info("=========== Create TBussObjAssoc ===========");
		return genericDAO.store(tBussObjAssoc);
	}

	/**
	 * Deletes a TBussObjAssoc entity object from the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be deleted
	 */
	public void deleteTBussObjAssoc(final TBussObjAssocId tBussObjAssocId) {
		LOGGER.info("=========== Delete TBussObjAssoc ===========");
		final TBussObjAssoc tBussObjAssoc = genericDAO.get(clazz, tBussObjAssocId);
		genericDAO.remove(tBussObjAssoc);
	}

	/**
	 * Updates a TBussObjAssoc entity object in to the persistent store
	 * 
	 * @param tBussObjAssoc
	 *            TBussObjAssoc Entity object to be updated
	 * @return tBussObjAssoc Persisted TBussObjAssoc object
	 */
	public TBussObjAssoc updateTBussObjAssoc(final TBussObjAssoc tBussObjAssoc) {
		LOGGER.info("=========== Update TBussObjAssoc ===========");
		return genericDAO.update(tBussObjAssoc);
	}

	/**
	 * Retrieve an TBussObjAssoc object based on given TBussObjAssoc TBussObjAssocId.
	 * 
	 * @param tBussObjAssocId
	 *            the primary key value of the TBussObjAssoc Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TBussObjAssoc findTBussObjAssocById(final TBussObjAssocId tBussObjAssocId) {
		LOGGER.info("find TBussObjAssoc instance with TBussObjAssocId: " + tBussObjAssocId);
		return genericDAO.get(clazz, tBussObjAssocId);
	}

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssoc if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TBussObjAssoc> findTBussObjAssocs(final SearchFilter<TBussObjAssoc> searchFilter) {
		LOGGER.info("=========== Find TBussObjAssocs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TBussObjAssoc tBussObjAssoc = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tBussObjAssocentity", tBussObjAssoc);

		if (tBussObjAssoc.getTBussFunction() == null) {
			jpaQuery.bind(TBUSSFUNCTION, new TBussFunction());
		} else {
			LOGGER.info("tBussFunction {}" + tBussObjAssoc.getTBussFunction());

			jpaQuery.bind(TBUSSFUNCTION, tBussObjAssoc.getTBussFunction());
		}

		if (tBussObjAssoc.getTBussObj() == null) {
			jpaQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}"+ tBussObjAssoc.getTBussObj());

			jpaQuery.bind(TBUSSOBJ, tBussObjAssoc.getTBussObj());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TBussObjAssocs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTBussObjAssocs(final SearchFilter<TBussObjAssoc> searchFilter) {
		LOGGER.info("=========== Count TBussObjAssoc ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TBussObjAssoc tBussObjAssoc = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tBussObjAssocentity", tBussObjAssoc);

		if (tBussObjAssoc.getTBussFunction() == null) {
			jpaCountQuery.bind(TBUSSFUNCTION, new TBussFunction());
		} else {
			LOGGER.info("tBussFunction {}"+ tBussObjAssoc.getTBussFunction());

			jpaCountQuery.bind(TBUSSFUNCTION, tBussObjAssoc.getTBussFunction());
		}

		if (tBussObjAssoc.getTBussObj() == null) {
			jpaCountQuery.bind(TBUSSOBJ, new TBussObj());
		} else {
			LOGGER.info("tBussObj {}"+ tBussObjAssoc.getTBussObj());

			jpaCountQuery.bind(TBUSSOBJ, tBussObjAssoc.getTBussObj());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussObjAssoc> getTBussObjAssocsByTBussFunction(final SearchFilter<TBussFunction> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		List<Object> tBussFunctionList = new ArrayList<Object>();
		tBussFunctionList.add(tBussFunction);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussObjAssocByTBussFunction", tBussFunctionList, index, maxresult);
	}

	/**
	 * Count TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussFunction type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussObjAssocsByTBussFunction(final SearchFilter<TBussFunction> searchFilter) {

		final TBussFunction tBussFunction = searchFilter.getEntityClass();
		List<Object> tBussObjTmplList = new ArrayList<Object>();
		tBussObjTmplList.add(tBussFunction);
		return genericDAO.findEntitiesByNamedQuery("CountTBussObjAssocsByTBussFunction", tBussObjTmplList);
	}

	/**
	 * Retrieve TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TBussObjAssoc> list of TBussObjAssocs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TBussObjAssoc> getTBussObjAssocsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTBussObjAssocByTBussObj", tBussObjList, index, maxresult);
	}

	/**
	 * Count TBussObjAssoc based on given search criteria using JPA named Query.
	 * The search criteria is of TBussObj type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTBussObjAssocsByTBussObj(final SearchFilter<TBussObj> searchFilter) {

		final TBussObj tBussObj = searchFilter.getEntityClass();
		List<Object> tBussObjList = new ArrayList<Object>();
		tBussObjList.add(tBussObj);
		return genericDAO.findEntitiesByNamedQuery("CountTBussObjAssocsByTBussObj", tBussObjList);
	}

}
