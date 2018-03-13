package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TNlBrick;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TNlBrickDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tNlBrickDAO")
public class TNlBrickDAOImpl implements TNlBrickDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TNlBrickDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TNlBrick> clazz;

	public Class<TNlBrick> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TNlBrickDAOImpl() {
		super();
		this.clazz = TNlBrick.class;
	}

	private static final String JPAQL = "select tNlBrickentity from TNlBrick tNlBrickentity";

	private static final String JPACOUNTQL = "select count(tNlBrickentity) from TNlBrick tNlBrickentity";

	private static final String[] RESTRICTIONS = { "tNlBrickentity.brickId = #{tNlBrickentity.getBrickId}",
			"tNlBrickentity.brickCd like '#{tNlBrickentity.getBrickCd}%'",
			"tNlBrickentity.brickName like '#{tNlBrickentity.getBrickName}%'",
			"tNlBrickentity.countryId = #{tNlBrickentity.getCountryId}" };

	/**
	 * Stores a new TNlBrick entity object in to the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be persisted
	 * @return tNlBrick Persisted TNlBrick object
	 */
	public TNlBrick createTNlBrick(final TNlBrick tNlBrick) {
		LOGGER.info("=========== Create TNlBrick ===========");
		return genericDAO.store(tNlBrick);
	}

	/**
	 * Deletes a TNlBrick entity object from the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be deleted
	 */
	public void deleteTNlBrick(final Integer brickId) {
		LOGGER.info("=========== Delete TNlBrick ===========");
		final TNlBrick tNlBrick = genericDAO.get(clazz, brickId);
		genericDAO.remove(tNlBrick);
	}

	/**
	 * Updates a TNlBrick entity object in to the persistent store
	 * 
	 * @param tNlBrick
	 *            TNlBrick Entity object to be updated
	 * @return tNlBrick Persisted TNlBrick object
	 */
	public TNlBrick updateTNlBrick(final TNlBrick tNlBrick) {
		LOGGER.info("=========== Update TNlBrick ===========");
		return genericDAO.update(tNlBrick);
	}

	/**
	 * Retrieve an TNlBrick object based on given TNlBrick brickId.
	 * 
	 * @param tNlBrickId
	 *            the primary key value of the TNlBrick Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TNlBrick findTNlBrickById(final Integer tNlBrickId) {
		LOGGER.info("find TNlBrick instance with brickId: " + tNlBrickId);
		return genericDAO.get(clazz, tNlBrickId);
	}

	/**
	 * Retrieve TNlBrick based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TNlBrick> list of TNlBrick if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TNlBrick> findTNlBricks(final SearchFilter<TNlBrick> searchFilter) {
		LOGGER.info("=========== Find TNlBricks ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TNlBrick tNlBrick = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tNlBrickentity", tNlBrick);
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TNlBricks.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTNlBricks(final SearchFilter<TNlBrick> searchFilter) {
		LOGGER.info("=========== Count TNlBrick ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TNlBrick tNlBrick = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tNlBrickentity", tNlBrick);

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

}
