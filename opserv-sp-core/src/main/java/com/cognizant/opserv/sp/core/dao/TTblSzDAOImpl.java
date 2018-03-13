package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TTblSz;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TTblSzDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tTblSzDAO")
public class TTblSzDAOImpl implements TTblSzDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TTblSzDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TTblSz> clazz;

	public Class<TTblSz> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TTblSzDAOImpl() {
		super();
		this.clazz = TTblSz.class;
	}

	private static final String JPAQL = "select tTblSzentity from TTblSz tTblSzentity";

	private static final String JPACOUNTQL = "select count(tTblSzentity) from TTblSz tTblSzentity";

	private static final String[] RESTRICTIONS = { "tTblSzentity.tblName like '#{tTblSzentity.getTblName}%'",
			"tTblSzentity.allowRowSz = #{tTblSzentity.getAllowRowSz}",
			"tTblSzentity.actRowSz = #{tTblSzentity.getActRowSz}" };

	/**
	 * Stores a new TTblSz entity object in to the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be persisted
	 * @return tTblSz Persisted TTblSz object
	 */
	public TTblSz createTTblSz(final TTblSz tTblSz) {
		LOGGER.info("=========== Create TTblSz ===========");
		return genericDAO.store(tTblSz);
	}

	/**
	 * Deletes a TTblSz entity object from the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be deleted
	 */
	public void deleteTTblSz(final String tblName) {
		LOGGER.info("=========== Delete TTblSz ===========");
		final TTblSz tTblSz = genericDAO.get(clazz, tblName);
		genericDAO.remove(tTblSz);
	}

	/**
	 * Updates a TTblSz entity object in to the persistent store
	 * 
	 * @param tTblSz
	 *            TTblSz Entity object to be updated
	 * @return tTblSz Persisted TTblSz object
	 */
	public TTblSz updateTTblSz(final TTblSz tTblSz) {
		LOGGER.info("=========== Update TTblSz ===========");
		return genericDAO.update(tTblSz);
	}

	/**
	 * Retrieve an TTblSz object based on given TTblSz tblName.
	 * 
	 * @param tTblSzId
	 *            the primary key value of the TTblSz Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TTblSz findTTblSzById(final String tTblSzId) {
		LOGGER.info("find TTblSz instance with tblName: " + tTblSzId);
		return genericDAO.get(clazz, tTblSzId);
	}

	/**
	 * Retrieve TTblSz based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TTblSz> list of TTblSz if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TTblSz> findTTblSzs(final SearchFilter<TTblSz> searchFilter) {
		LOGGER.info("=========== Find TTblSzs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TTblSz tTblSz = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tTblSzentity", tTblSz);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TTblSzs.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTTblSzs(final SearchFilter<TTblSz> searchFilter) {
		LOGGER.info("=========== Count TTblSz ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TTblSz tTblSz = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tTblSzentity", tTblSz);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
