package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TClassType;
import com.cognizant.opserv.sp.core.entity.TClassValue;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TClassValueDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tClassValueDAO")
public class TClassValueDAOImpl implements TClassValueDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TClassValueDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCLASSTYPE = "tClassType";

	private final Class<TClassValue> clazz;

	public Class<TClassValue> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TClassValueDAOImpl() {
		super();
		this.clazz = TClassValue.class;
	}

	private static final String JPAQL = "select tClassValueentity from TClassValue tClassValueentity";

	private static final String JPACOUNTQL = "select count(tClassValueentity) from TClassValue tClassValueentity";

	private static final String[] RESTRICTIONS = {
			"tClassValueentity.classValueId = #{tClassValueentity.getClassValueId}",
			"tClassValueentity.classValue like '#{tClassValueentity.getClassValue}%'",
			"tClassValueentity.activeFlag = #{tClassValueentity.getActiveFlag}",
			"tClassValueentity.createdBy = #{tClassValueentity.getCreatedBy}",
			"Date(tClassValueentity.createDt) = '#{tClassValueentity.getCreateDt}'",
			"tClassValueentity.updatedBy = #{tClassValueentity.getUpdatedBy}",
			"Date(tClassValueentity.updateDt) = '#{tClassValueentity.getUpdateDt}'",
			"tClassValueentity.tenantId = #{tClassValueentity.getTenantId}",
			"tClassValueentity.tClassType.tClassTypeId = #{tClassValueentity.tClassType.getTClassTypeId}" };

	/**
	 * Stores a new TClassValue entity object in to the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be persisted
	 * @return tClassValue Persisted TClassValue object
	 */
	public TClassValue createTClassValue(final TClassValue tClassValue) {
		LOGGER.info("=========== Create TClassValue ===========");
		return genericDAO.store(tClassValue);
	}

	/**
	 * Deletes a TClassValue entity object from the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be deleted
	 */
	public void deleteTClassValue(final Integer classValueId) {
		LOGGER.info("=========== Delete TClassValue ===========");
		final TClassValue tClassValue = genericDAO.get(clazz, classValueId);
		genericDAO.remove(tClassValue);
	}

	/**
	 * Updates a TClassValue entity object in to the persistent store
	 * 
	 * @param tClassValue
	 *            TClassValue Entity object to be updated
	 * @return tClassValue Persisted TClassValue object
	 */
	public TClassValue updateTClassValue(final TClassValue tClassValue) {
		LOGGER.info("=========== Update TClassValue ===========");
		return genericDAO.update(tClassValue);
	}

	/**
	 * Retrieve an TClassValue object based on given TClassValue classValueId.
	 * 
	 * @param tClassValueId
	 *            the primary key value of the TClassValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TClassValue findTClassValueById(final Integer tClassValueId) {
		LOGGER.info("find TClassValue instance with classValueId: " + tClassValueId);
		return genericDAO.get(clazz, tClassValueId);
	}

	/**
	 * Retrieve TClassValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassValue> list of TClassValue if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TClassValue> findTClassValues(final SearchFilter<TClassValue> searchFilter) {
		LOGGER.info("=========== Find TClassValues ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TClassValue tClassValue = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tClassValueentity", tClassValue);

		if (tClassValue.getTClassType() == null) {
			jpaQuery.bind(TCLASSTYPE, new TClassType());
		} else {
			LOGGER.info("tClassType {}", tClassValue.getTClassType());

			jpaQuery.bind(TCLASSTYPE, tClassValue.getTClassType());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TClassValues.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTClassValues(final SearchFilter<TClassValue> searchFilter) {
		LOGGER.info("=========== Count TClassValue ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TClassValue tClassValue = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tClassValueentity", tClassValue);

		if (tClassValue.getTClassType() == null) {
			jpaCountQuery.bind(TCLASSTYPE, new TClassType());
		} else {
			LOGGER.info("tClassType {}", tClassValue.getTClassType());

			jpaCountQuery.bind(TCLASSTYPE, tClassValue.getTClassType());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TClassValue based on given search criteria using JPA named Query.
	 * The search criteria is of TClassType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TClassValue> list of TClassValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TClassValue> getTClassValuesByTClassType(final SearchFilter<TClassType> searchFilter) {

		final TClassType tClassType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(tClassType);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTClassValueByTClassType", queryParam, 0, -1);
	}

	/**
	 * Count TClassValue based on given search criteria using JPA named Query.
	 * The search criteria is of TClassType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTClassValuesByTClassType(final SearchFilter<TClassType> searchFilter) {

		final TClassType tClassType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object>();
        queryParam.add(tClassType);
		return genericDAO.findEntitiesByNamedQueryMultiCond("CountTClassValuesByTClassType", queryParam,0,-1);
	}

}
