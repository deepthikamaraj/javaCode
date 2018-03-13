package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAttrDataType;
import com.cognizant.opserv.sp.core.entity.TValType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TValTypeDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tValTypeDAO")
public class TValTypeDAOImpl implements TValTypeDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TValTypeDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	//private static final String TATTRDATATYPE = "tAttrDataType";

	private final Class<TValType> clazz;

	public Class<TValType> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TValTypeDAOImpl() {
		super();
		this.clazz = TValType.class;
	}

	private static final String JPAQL = "select tValTypeentity from TValType tValTypeentity";

	private static final String JPACOUNTQL = "select count(tValTypeentity) from TValType tValTypeentity";

	private static final String[] RESTRICTIONS = { "tValTypeentity.valTypeId = #{tValTypeentity.getValTypeId}",
			"tValTypeentity.valName like '#{tValTypeentity.getValName}%'",
			"tValTypeentity.valDesc like '#{tValTypeentity.getValDesc}%'",
			"tValTypeentity.activeFlag = #{tValTypeentity.getActiveFlag}",
			"tValTypeentity.tAttrDataType.attrDataTypeId = #{tValTypeentity.tAttrDataType.getAttrDataTypeId}",
			"tValTypeentity.localeId = #{tValTypeentity.getLocaleId}"
	};
	
	/**
	 * Stores a new TValType entity object in to the persistent store
	 * 
	 * @param tValType
	 *            TValType Entity object to be persisted
	 * @return tValType Persisted TValType object
	 */
	public TValType createTValType(final TValType tValType) {
		LOGGER.info("=========== Create TValType ===========");
		return genericDAO.store(tValType);
	}

	/**
	 * Deletes a TValType entity object from the persistent store
	 * 
	 * @param tValType
	 *            TValType Entity object to be deleted
	 */
	public void deleteTValType(final Integer valTypeId) {
		LOGGER.info("=========== Delete TValType ===========");
		final TValType tValType = genericDAO.get(clazz, valTypeId);
		genericDAO.remove(tValType);
	}

	/**
	 * Updates a TValType entity object in to the persistent store
	 * 
	 * @param tValType
	 *            TValType Entity object to be updated
	 * @return tValType Persisted TValType object
	 */
	public TValType updateTValType(final TValType tValType) {
		LOGGER.info("=========== Update TValType ===========");
		return genericDAO.update(tValType);
	}

	/**
	 * Retrieve an TValType object based on given TValType valTypeId.
	 * 
	 * @param tValTypeId
	 *            the primary key value of the TValType Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TValType findTValTypeById(final Integer tValTypeId) {
		LOGGER.info("find TValType instance with valTypeId: " + tValTypeId);
		return genericDAO.get(clazz, tValTypeId);
	}

	/**
	 * Retrieve TValType based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValType> list of TValType if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TValType> findTValTypes(final SearchFilter<TValType> searchFilter) {
		LOGGER.info("=========== Find TValTypes ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TValType tValType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tValTypeentity", tValType);

		/*if (tValType.getTAttrDataType() == null) {
			jpaQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tValType.getTAttrDataType());

			jpaQuery.bind(TATTRDATATYPE, tValType.getTAttrDataType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TValTypes.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTValTypes(final SearchFilter<TValType> searchFilter) {
		LOGGER.info("=========== Count TValType ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TValType tValType = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tValTypeentity", tValType);

		/*if (tValType.getTAttrDataType() == null) {
			jpaCountQuery.bind(TATTRDATATYPE, new TAttrDataType());
		} else {
			LOGGER.info("tAttrDataType {}", tValType.getTAttrDataType());

			jpaCountQuery.bind(TATTRDATATYPE, tValType.getTAttrDataType());
		}*/

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TValType based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TValType> list of TValTypes if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TValType> getTValTypesByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object>();
		queryParam.add(tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTValTypeByTAttrDataType", queryParam, index, maxresult);
	}

	/**
	 * Count TValType based on given search criteria using JPA named Query.
	 * The search criteria is of TAttrDataType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTValTypesByTAttrDataType(final SearchFilter<TAttrDataType> searchFilter) {

		final TAttrDataType tAttrDataType = searchFilter.getEntityClass();
		List<Object> queryParam=new ArrayList<Object>();
		queryParam.add(tAttrDataType.gettAttrDataTypeId().getAttrDataTypeId());
		return genericDAO.findEntitiesByNamedQuery("CountTValTypesByTAttrDataType", queryParam);
	}

}
