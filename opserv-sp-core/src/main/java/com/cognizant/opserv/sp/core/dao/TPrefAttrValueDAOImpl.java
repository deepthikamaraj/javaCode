package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TPrefAttrValue;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TPrefAttrValueDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tPrefAttrValueDAO")
public class TPrefAttrValueDAOImpl implements TPrefAttrValueDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TPrefAttrValueDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TRECIPIENTATTR = "tRecipientAttr";

	private final Class<TPrefAttrValue> clazz;

	public Class<TPrefAttrValue> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TPrefAttrValueDAOImpl() {
		super();
		this.clazz = TPrefAttrValue.class;
	}

	private static final String JPAQL = "select tPrefAttrValueentity from TPrefAttrValue tPrefAttrValueentity";

	private static final String JPACOUNTQL = "select count(tPrefAttrValueentity) from TPrefAttrValue tPrefAttrValueentity";

	private static final String[] RESTRICTIONS = {
			"tPrefAttrValueentity.attrValueSequenceId = #{tPrefAttrValueentity.getAttrValueSequenceId}",
			"tPrefAttrValueentity.value like '#{tPrefAttrValueentity.getValue}%'",
			"tPrefAttrValueentity.createdBy = #{tPrefAttrValueentity.getCreatedBy}",
			"Date(tPrefAttrValueentity.createDt) = '#{tPrefAttrValueentity.getCreateDt}'",
			"tPrefAttrValueentity.updatedBy = #{tPrefAttrValueentity.getUpdatedBy}",
			"Date(tPrefAttrValueentity.updateDt) = '#{tPrefAttrValueentity.getUpdateDt}'",
			"tPrefAttrValueentity.tenantId = #{tPrefAttrValueentity.getTenantId}",
			"tPrefAttrValueentity.tRecipientAttr.tRecipientAttrId = #{tPrefAttrValueentity.tRecipientAttr.getTRecipientAttrId}" };

	/**
	 * Stores a new TPrefAttrValue entity object in to the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be persisted
	 * @return tPrefAttrValue Persisted TPrefAttrValue object
	 */
	public TPrefAttrValue createTPrefAttrValue(final TPrefAttrValue tPrefAttrValue) {
		LOGGER.info("=========== Create TPrefAttrValue ===========");
		return genericDAO.store(tPrefAttrValue);
	}

	/**
	 * Deletes a TPrefAttrValue entity object from the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be deleted
	 */
	public void deleteTPrefAttrValue(final Integer attrValueSequenceId) {
		LOGGER.info("=========== Delete TPrefAttrValue ===========");
		final TPrefAttrValue tPrefAttrValue = genericDAO.get(clazz, attrValueSequenceId);
		genericDAO.remove(tPrefAttrValue);
	}

	/**
	 * Updates a TPrefAttrValue entity object in to the persistent store
	 * 
	 * @param tPrefAttrValue
	 *            TPrefAttrValue Entity object to be updated
	 * @return tPrefAttrValue Persisted TPrefAttrValue object
	 */
	public TPrefAttrValue updateTPrefAttrValue(final TPrefAttrValue tPrefAttrValue) {
		LOGGER.info("=========== Update TPrefAttrValue ===========");
		return genericDAO.update(tPrefAttrValue);
	}

	/**
	 * Retrieve an TPrefAttrValue object based on given TPrefAttrValue attrValueSequenceId.
	 * 
	 * @param tPrefAttrValueId
	 *            the primary key value of the TPrefAttrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TPrefAttrValue findTPrefAttrValueById(final Integer tPrefAttrValueId) {
		LOGGER.info("find TPrefAttrValue instance with attrValueSequenceId: " + tPrefAttrValueId);
		return genericDAO.get(clazz, tPrefAttrValueId);
	}

	/**
	 * Retrieve TPrefAttrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrefAttrValue> list of TPrefAttrValue if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TPrefAttrValue> findTPrefAttrValues(final SearchFilter<TPrefAttrValue> searchFilter) {
		LOGGER.info("=========== Find TPrefAttrValues ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TPrefAttrValue tPrefAttrValue = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tPrefAttrValueentity", tPrefAttrValue);

		if (tPrefAttrValue.getTRecipientAttr() == null) {
			jpaQuery.bind(TRECIPIENTATTR, new TRecipientAttr());
		} else {
			LOGGER.info("tRecipientAttr {}"+ tPrefAttrValue.getTRecipientAttr());

			jpaQuery.bind(TRECIPIENTATTR, tPrefAttrValue.getTRecipientAttr());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TPrefAttrValues.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTPrefAttrValues(final SearchFilter<TPrefAttrValue> searchFilter) {
		LOGGER.info("=========== Count TPrefAttrValue ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TPrefAttrValue tPrefAttrValue = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tPrefAttrValueentity", tPrefAttrValue);

		if (tPrefAttrValue.getTRecipientAttr() == null) {
			jpaCountQuery.bind(TRECIPIENTATTR, new TRecipientAttr());
		} else {
			LOGGER.info("tRecipientAttr {}"+ tPrefAttrValue.getTRecipientAttr());

			jpaCountQuery.bind(TRECIPIENTATTR, tPrefAttrValue.getTRecipientAttr());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TPrefAttrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TRecipientAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TPrefAttrValue> list of TPrefAttrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TPrefAttrValue> getTPrefAttrValuesByTRecipientAttr(final SearchFilter<TRecipientAttr> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTPrefAttrValueByTRecipientAttr", queryParam, index,
				maxresult);
	}

	/**
	 * Count TPrefAttrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TRecipientAttr type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTPrefAttrValuesByTRecipientAttr(final SearchFilter<TRecipientAttr> searchFilter) {
		List<Object> queryParam=new ArrayList<Object> ();
		queryParam.add(searchFilter.getEntityClass());
		return genericDAO.findEntitiesByNamedQuery("CountTPrefAttrValuesByTRecipientAttr", queryParam);
	}

}
