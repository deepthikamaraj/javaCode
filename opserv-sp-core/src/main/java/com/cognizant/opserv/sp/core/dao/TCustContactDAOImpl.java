package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TContactType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.core.entity.TCustContactId;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustContactDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustContactDAO")
public class TCustContactDAOImpl implements TCustContactDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustContactDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUST = "tCust";
	private static final String TCONTACTTYPE = "tContactType";
	//private static final String TADDRTYPE = "tAddrType";

	private final Class<TCustContact> clazz;

	public Class<TCustContact> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustContactDAOImpl() {
		super();
		this.clazz = TCustContact.class;
	}

	private static final String JPAQL = "select tCustContactentity from TCustContact tCustContactentity";

	private static final String JPACOUNTQL = "select count(*) from TCustContact tCustContactentity";

	private static final String[] RESTRICTIONS = {
			"tCustContactentity.tCustContactId.custId = #{tCustContactentity.tCustContactId.getCustId}",
			"tCustContactentity.tCustContactId.custContactId = #{tCustContactentity.tCustContactId.getCustContactId}",
			"tCustContactentity.contactDetail like '#{tCustContactentity.getContactDetail}%'",
			"tCustContactentity.prContactFlag = #{tCustContactentity.getPrContactFlag}",
			"tCustContactentity.activeFlag = #{tCustContactentity.getActiveFlag}",
			"tCustContactentity.createdBy = #{tCustContactentity.getCreatedBy}",
			"Date(tCustContactentity.createDt) = '#{tCustContactentity.getCreateDt}'",
			"tCustContactentity.updatedBy = #{tCustContactentity.getUpdatedBy}",
			"Date(tCustContactentity.updateDt) = '#{tCustContactentity.getUpdateDt}'",
			"tCustContactentity.tenantId = #{tCustContactentity.getTenantId}",
			"tCustContactentity.tCust.custId = #{tCustContactentity.tCust.getCustId}",
			"tCustContactentity.tContactType.contactTypeId = #{tCustContactentity.tContactType.getContactTypeId}",
			"tCustContactentity.tAddrType.addrTypeId = #{tCustContactentity.tAddrType.getAddrTypeId}" };

	/**
	 * Stores a new TCustContact entity object in to the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be persisted
	 * @return tCustContact Persisted TCustContact object
	 */
	public TCustContact createTCustContact(final TCustContact tCustContact) {
		LOGGER.info("=========== Create TCustContact ===========");
		return genericDAO.store(tCustContact);
	}

	/**
	 * Deletes a TCustContact entity object from the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be deleted
	 */
	public void deleteTCustContact(final TCustContactId tCustContactId) {
		LOGGER.info("=========== Delete TCustContact ===========");
		final TCustContact tCustContact = genericDAO.get(clazz, tCustContactId);
		genericDAO.remove(tCustContact);
	}

	/**
	 * Updates a TCustContact entity object in to the persistent store
	 * 
	 * @param tCustContact
	 *            TCustContact Entity object to be updated
	 * @return tCustContact Persisted TCustContact object
	 */
	public TCustContact updateTCustContact(final TCustContact tCustContact) {
		LOGGER.info("=========== Update TCustContact ===========");
		return genericDAO.update(tCustContact);
	}

	/**
	 * Retrieve an TCustContact object based on given TCustContact TCustContactId.
	 * 
	 * @param tCustContactId
	 *            the primary key value of the TCustContact Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustContact findTCustContactById(final TCustContactId tCustContactId) {
		LOGGER.info("find TCustContact instance with TCustContactId: " + tCustContactId);
		return genericDAO.get(clazz, tCustContactId);
	}

	/**
	 * Retrieve TCustContact based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContact if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustContact> findTCustContacts(final SearchFilter<TCustContact> searchFilter) {
		LOGGER.info("=========== Find TCustContacts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustContact tCustContact = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustContactentity", tCustContact);

		if (tCustContact.getTCust() == null) {
			jpaQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}"+ tCustContact.getTCust());

			jpaQuery.bind(TCUST, tCustContact.getTCust());
		}

		if (tCustContact.getTContactType() == null) {
			jpaQuery.bind(TCONTACTTYPE, new TContactType());
		} else {
			LOGGER.info("tContactType {}"+ tCustContact.getTContactType());

			jpaQuery.bind(TCONTACTTYPE, tCustContact.getTContactType());
		}
/*
		if (tCustContact.getTAddrType() == null) {
			jpaQuery.bind(TADDRTYPE, new TAddrType());
		} else {
			LOGGER.info("tAddrType {}", tCustContact.getTAddrType());

			jpaQuery.bind(TADDRTYPE, tCustContact.getTAddrType());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustContacts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustContacts(final SearchFilter<TCustContact> searchFilter) {
		LOGGER.info("=========== Count TCustContact ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustContact tCustContact = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustContactentity", tCustContact);

		if (tCustContact.getTCust() == null) {
			jpaCountQuery.bind(TCUST, new TCust());
		} else {
			LOGGER.info("tCust {}"+ tCustContact.getTCust());

			jpaCountQuery.bind(TCUST, tCustContact.getTCust());
		}

		if (tCustContact.getTContactType() == null) {
			jpaCountQuery.bind(TCONTACTTYPE, new TContactType());
		} else {
			LOGGER.info("tContactType {}"+ tCustContact.getTContactType());

			jpaCountQuery.bind(TCONTACTTYPE, tCustContact.getTContactType());
		}

		/*if (tCustContact.getTAddrType() == null) {
			jpaCountQuery.bind(TADDRTYPE, new TAddrType());
		} else {
			LOGGER.info("tAddrType {}", tCustContact.getTAddrType());

			jpaCountQuery.bind(TADDRTYPE, tCustContact.getTAddrType());
		}
*/
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustContact> getTCustContactsByTCust(final SearchFilter<TCust> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustContactByTCust", tCustList, index, maxresult);
	}

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TCust type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustContactsByTCust(final SearchFilter<TCust> searchFilter) {

		final TCust tCust = searchFilter.getEntityClass();
		List<Object> tCustList = new ArrayList<Object>();
		tCustList.add(tCust);
		return genericDAO.findEntitiesByNamedQuery("CountTCustContactsByTCust", tCustList);
	}

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TContactType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustContact> getTCustContactsByTContactType(final SearchFilter<TContactType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TContactType tContactType = searchFilter.getEntityClass();
		List<Object> tContactTypeList = new ArrayList<Object>();
		tContactTypeList.add(tContactType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustContactByTContactType", tContactTypeList, index, maxresult);
	}

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TContactType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustContactsByTContactType(final SearchFilter<TContactType> searchFilter) {

		final TContactType tContactType = searchFilter.getEntityClass();
		List<Object> tContactTypeList = new ArrayList<Object>();
		tContactTypeList.add(tContactType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustContactsByTContactType", tContactTypeList);
	}

	/**
	 * Retrieve TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustContact> list of TCustContacts if it exists against given
	 *         criteria. Returns null if not found
	 */
	/*public List<TCustContact> getTCustContactsByTAddrType(final SearchFilter<TAddrType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAddrType tAddrType = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQuery("FindTCustContactByTAddrType", tAddrType, index, maxresult);
	}*/

	/**
	 * Count TCustContact based on given search criteria using JPA named Query.
	 * The search criteria is of TAddrType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	/*public Object countTCustContactsByTAddrType(final SearchFilter<TAddrType> searchFilter) {

		final TAddrType tAddrType = searchFilter.getEntityClass();
		return genericDAO.findEntitiesByNamedQuery("CountTCustContactsByTAddrType", tAddrType);
	}*/

}
