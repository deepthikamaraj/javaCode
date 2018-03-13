package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAffCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAffCustAlgmntId;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TAffCustAlgmntDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tAffCustAlgmntDAO")
public class TAffCustAlgmntDAOImpl implements TAffCustAlgmntDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAffCustAlgmntDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TCUSTALGMNTBYAFFCUSTALGMNTID = "tCustAlgmntByAffCustAlgmntId";
	private static final String TCUSTALGMNTBYCUSTALGMNTID = "tCustAlgmntByCustAlgmntId";

	private final Class<TAffCustAlgmnt> clazz;

	public Class<TAffCustAlgmnt> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TAffCustAlgmntDAOImpl() {
		super();
		this.clazz = TAffCustAlgmnt.class;
	}

	private static final String JPAQL = "select tAffCustAlgmntentity from TAffCustAlgmnt tAffCustAlgmntentity";

	private static final String JPACOUNTQL = "select count(*) from TAffCustAlgmnt tAffCustAlgmntentity";

	private static final String[] RESTRICTIONS = {
			"tAffCustAlgmntentity.tAffCustAlgmntId.affCustAlgmntId = #{tAffCustAlgmntentity.tAffCustAlgmntId.getAffCustAlgmntId}",
			"tAffCustAlgmntentity.tAffCustAlgmntId.custAlgmntId = #{tAffCustAlgmntentity.tAffCustAlgmntId.getCustAlgmntId}",
			"tAffCustAlgmntentity.activeFlag = #{tAffCustAlgmntentity.getActiveFlag}",
			"tAffCustAlgmntentity.createdBy = #{tAffCustAlgmntentity.getCreatedBy}",
			"Date(tAffCustAlgmntentity.createDt) = '#{tAffCustAlgmntentity.getCreateDt}'",
			"tAffCustAlgmntentity.updatedBy = #{tAffCustAlgmntentity.getUpdatedBy}",
			"Date(tAffCustAlgmntentity.updateDt) = '#{tAffCustAlgmntentity.getUpdateDt}'",
			"tAffCustAlgmntentity.tenantId = #{tAffCustAlgmntentity.getTenantId}",
			"tAffCustAlgmntentity.tCustAlgmntByAffCustAlgmntId.custAlgmntId = #{tAffCustAlgmntentity.tCustAlgmntByAffCustAlgmntId.getCustAlgmntId}",
			"tAffCustAlgmntentity.tCustAlgmntByCustAlgmntId.custAlgmntId = #{tAffCustAlgmntentity.tCustAlgmntByCustAlgmntId.getCustAlgmntId}" };

	/**
	 * Stores a new TAffCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be persisted
	 * @return tAffCustAlgmnt Persisted TAffCustAlgmnt object
	 */
	public TAffCustAlgmnt createTAffCustAlgmnt(final TAffCustAlgmnt tAffCustAlgmnt) {
		LOGGER.info("=========== Create TAffCustAlgmnt ===========");
		return genericDAO.store(tAffCustAlgmnt);
	}

	/**
	 * Deletes a TAffCustAlgmnt entity object from the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be deleted
	 */
	public void deleteTAffCustAlgmnt(final TAffCustAlgmntId tAffCustAlgmntId) {
		LOGGER.info("=========== Delete TAffCustAlgmnt ===========");
		final TAffCustAlgmnt tAffCustAlgmnt = genericDAO.get(clazz, tAffCustAlgmntId);
		genericDAO.remove(tAffCustAlgmnt);
	}

	/**
	 * Updates a TAffCustAlgmnt entity object in to the persistent store
	 * 
	 * @param tAffCustAlgmnt
	 *            TAffCustAlgmnt Entity object to be updated
	 * @return tAffCustAlgmnt Persisted TAffCustAlgmnt object
	 */
	public TAffCustAlgmnt updateTAffCustAlgmnt(final TAffCustAlgmnt tAffCustAlgmnt) {
		LOGGER.info("=========== Update TAffCustAlgmnt ===========");
		return genericDAO.update(tAffCustAlgmnt);
	}

	/**
	 * Retrieve an TAffCustAlgmnt object based on given TAffCustAlgmnt TAffCustAlgmntId.
	 * 
	 * @param tAffCustAlgmntId
	 *            the primary key value of the TAffCustAlgmnt Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TAffCustAlgmnt findTAffCustAlgmntById(final TAffCustAlgmntId tAffCustAlgmntId) {
		LOGGER.info("find TAffCustAlgmnt instance with TAffCustAlgmntId: " + tAffCustAlgmntId);
		return genericDAO.get(clazz, tAffCustAlgmntId);
	}

	/**
	 * Retrieve TAffCustAlgmnt based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TAffCustAlgmnt> list of TAffCustAlgmnt if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TAffCustAlgmnt> findTAffCustAlgmnts(final SearchFilter<TAffCustAlgmnt> searchFilter) {
		LOGGER.info("=========== Find TAffCustAlgmnts ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TAffCustAlgmnt tAffCustAlgmnt = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tAffCustAlgmntentity", tAffCustAlgmnt);

		if (tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId() == null) {
			jpaQuery.bind(TCUSTALGMNTBYAFFCUSTALGMNTID, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmntByAffCustAlgmntId {}", tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId());

			jpaQuery.bind(TCUSTALGMNTBYAFFCUSTALGMNTID, tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId());
		}

		if (tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId() == null) {
			jpaQuery.bind(TCUSTALGMNTBYCUSTALGMNTID, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmntByCustAlgmntId {}", tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId());

			jpaQuery.bind(TCUSTALGMNTBYCUSTALGMNTID, tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TAffCustAlgmnts.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTAffCustAlgmnts(final SearchFilter<TAffCustAlgmnt> searchFilter) {
		LOGGER.info("=========== Count TAffCustAlgmnt ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TAffCustAlgmnt tAffCustAlgmnt = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tAffCustAlgmntentity", tAffCustAlgmnt);

		if (tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId() == null) {
			jpaCountQuery.bind(TCUSTALGMNTBYAFFCUSTALGMNTID, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmntByAffCustAlgmntId {}", tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId());

			jpaCountQuery.bind(TCUSTALGMNTBYAFFCUSTALGMNTID, tAffCustAlgmnt.getTCustAlgmntByAffCustAlgmntId());
		}

		if (tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId() == null) {
			jpaCountQuery.bind(TCUSTALGMNTBYCUSTALGMNTID, new TCustAlgmnt());
		} else {
			LOGGER.info("tCustAlgmntByCustAlgmntId {}", tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId());

			jpaCountQuery.bind(TCUSTALGMNTBYCUSTALGMNTID, tAffCustAlgmnt.getTCustAlgmntByCustAlgmntId());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Gets the aff cust algmnt ids by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 * @return the aff cust algmnt ids by algmnt ids
	 */
	@Override
	public List<Long> getAffCustAlgmntIdsByAlgmntIds(List<Long> custAlgmntIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntIds);				
		paramList.add(tenantId);
		List<Long> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetAffCustAlgmntIdsByAlgmntIds", paramList, 0, -1);
		return list;
	}
	/**
	 * Inactivate aff cust algmnts by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 */
	@Override
	public void inactivateAffCustAlgmntsByAlgmntIds(List<Long> custAlgmntIds, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntIds);				
		paramList.add(tenantId);
		genericDAO.updateEntitiesByNamedQueryMultiCond("InactivateAffCustAlgmntsByAlgmntIds", paramList,0,-1);
	}
	/**
	 * Gets the aff cust algmnts by algmnt ids.
	 *
	 * @param custAlgmntIds the cust algmnt ids
	 * @param tenantId the tenant id
	 * @param flag the flag
	 * @return the aff cust algmnts by algmnt ids
	 */	
	@Override
	public List<TAffCustAlgmnt> getAffCustAlgmntsByAlgmntIds(List<Long> custAlgmntIds, Short tenantId,Character flag) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custAlgmntIds);				
		paramList.add(tenantId);
		paramList.add(flag);
		List<TAffCustAlgmnt> list = genericDAO.findEntitiesByNamedQueryMultiCond("GetAffCustAlgmntsByAlgmntIds", paramList, 0, -1);
		return list;
	}
	
	

}
