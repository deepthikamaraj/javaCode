package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TMirSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TMirSalesPosDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tMirSalesPosDAO")
public class TMirSalesPosDAOImpl implements TMirSalesPosDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TMirSalesPosDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private static final String TSALESPOSBYTMIRSALESPOSIBFK1 = "tSalesPosByTMirSalesPosIbfk1";
	private static final String TSALESPOSBYTMIRSALESPOSIBFK2 = "tSalesPosByTMirSalesPosIbfk2";

	private final Class<TMirSalesPos> clazz;

	public Class<TMirSalesPos> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TMirSalesPosDAOImpl() {
		super();
		this.clazz = TMirSalesPos.class;
	}

	private static final String JPAQL = "select tMirSalesPosentity from TMirSalesPos tMirSalesPosentity";

	private static final String JPACOUNTQL = "select count(tMirSalesPosentity) from TMirSalesPos tMirSalesPosentity";

	private static final String[] RESTRICTIONS = {
			"tMirSalesPosentity.salesPosMirId = #{tMirSalesPosentity.getSalesPosMirId}",
			"tMirSalesPosentity.activeFlag = #{tMirSalesPosentity.getActiveFlag}",
			"Date(tMirSalesPosentity.effStartDt) = '#{tMirSalesPosentity.getEffStartDt}'",
			"Date(tMirSalesPosentity.effEndDt) = '#{tMirSalesPosentity.getEffEndDt}'",
			"tMirSalesPosentity.prMirFlag = #{tMirSalesPosentity.getPrMirFlag}",
			"tMirSalesPosentity.createdBy = #{tMirSalesPosentity.getCreatedBy}",
			"Date(tMirSalesPosentity.createDt) = '#{tMirSalesPosentity.getCreateDt}'",
			"tMirSalesPosentity.updatedBy = #{tMirSalesPosentity.getUpdatedBy}",
			"Date(tMirSalesPosentity.updateDt) = '#{tMirSalesPosentity.getUpdateDt}'",
			"tMirSalesPosentity.tenantId = #{tMirSalesPosentity.getTenantId}",
			"tMirSalesPosentity.tSalesPosByTMirSalesPosIbfk1.tSalesPosId = #{tMirSalesPosentity.tSalesPosByTMirSalesPosIbfk1.getTSalesPosId}",
			"tMirSalesPosentity.tSalesPosByTMirSalesPosIbfk2.tSalesPosId = #{tMirSalesPosentity.tSalesPosByTMirSalesPosIbfk2.getTSalesPosId}" };

	/**
	 * Stores a new TMirSalesPos entity object in to the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be persisted
	 * @return tMirSalesPos Persisted TMirSalesPos object
	 */
	public TMirSalesPos createTMirSalesPos(final TMirSalesPos tMirSalesPos) {
		LOGGER.info("=========== Create TMirSalesPos ===========");
		return genericDAO.store(tMirSalesPos);
	}

	/**
	 * Deletes a TMirSalesPos entity object from the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be deleted
	 */
	public void deleteTMirSalesPos(final Long salesPosMirId) {
		LOGGER.info("=========== Delete TMirSalesPos ===========");
		final TMirSalesPos tMirSalesPos = genericDAO.get(clazz, salesPosMirId);
		genericDAO.remove(tMirSalesPos);
	}

	/**
	 * Updates a TMirSalesPos entity object in to the persistent store
	 * 
	 * @param tMirSalesPos
	 *            TMirSalesPos Entity object to be updated
	 * @return tMirSalesPos Persisted TMirSalesPos object
	 */
	public TMirSalesPos updateTMirSalesPos(final TMirSalesPos tMirSalesPos) {
		LOGGER.info("=========== Update TMirSalesPos ===========");
		return genericDAO.update(tMirSalesPos);
	}

	/**
	 * Retrieve an TMirSalesPos object based on given TMirSalesPos salesPosMirId.
	 * 
	 * @param tMirSalesPosId
	 *            the primary key value of the TMirSalesPos Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TMirSalesPos findTMirSalesPosById(final Long tMirSalesPosId) {
		LOGGER.info("find TMirSalesPos instance with salesPosMirId: " + tMirSalesPosId);
		return genericDAO.get(clazz, tMirSalesPosId);
	}

	/**
	 * Retrieve TMirSalesPos based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPos if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TMirSalesPos> findTMirSalesPoss(final SearchFilter<TMirSalesPos> searchFilter) {
		LOGGER.info("=========== Find TMirSalesPoss ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TMirSalesPos tMirSalesPos = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tMirSalesPosentity", tMirSalesPos);

		if (tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1() == null) {
			jpaQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK1, new TSalesPos());
		} else {
			LOGGER.info("tSalesPosByTMirSalesPosIbfk1 {}", tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1());

			jpaQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK1, tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1());
		}

		if (tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2() == null) {
			jpaQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK2, new TSalesPos());
		} else {
			LOGGER.info("tSalesPosByTMirSalesPosIbfk2 {}", tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2());

			jpaQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK2, tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TMirSalesPoss.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTMirSalesPoss(final SearchFilter<TMirSalesPos> searchFilter) {
		LOGGER.info("=========== Count TMirSalesPos ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TMirSalesPos tMirSalesPos = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tMirSalesPosentity", tMirSalesPos);

		if (tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1() == null) {
			jpaCountQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK1, new TSalesPos());
		} else {
			LOGGER.info("tSalesPosByTMirSalesPosIbfk1 {}", tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1());

			jpaCountQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK1, tMirSalesPos.getTSalesPosByTMirSalesPosIbfk1());
		}

		if (tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2() == null) {
			jpaCountQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK2, new TSalesPos());
		} else {
			LOGGER.info("tSalesPosByTMirSalesPosIbfk2 {}", tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2());

			jpaCountQuery.bind(TSALESPOSBYTMIRSALESPOSIBFK2, tMirSalesPos.getTSalesPosByTMirSalesPosIbfk2());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosByTMirSalesPosIbfk1 type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMirSalesPos> getTMirSalesPossByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tSalesPos);

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMirSalesPosByTSalesPosByTMirSalesPosIbfk1", queryParam, 0,
				-1);
	}

	/**
	 * Count TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMirSalesPossByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tSalesPos);
		return genericDAO.findEntitiesByNamedQuery("CountTMirSalesPossByTSalesPosByTMirSalesPosIbfk1", queryParam);
	}

	/**
	 * Retrieve TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPosByTMirSalesPosIbfk2 type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TMirSalesPos> list of TMirSalesPoss if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TMirSalesPos> getTMirSalesPossByTSalesPosByTMirSalesPosIbfk2(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tSalesPos);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTMirSalesPosByTSalesPosByTMirSalesPosIbfk2", queryParam, 0,
				-1);
	}

	/**
	 * Count TMirSalesPos based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTMirSalesPossByTSalesPosByTMirSalesPosIbfk2(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tSalesPos);
		return genericDAO.findEntitiesByNamedQuery("CountTMirSalesPossByTSalesPosByTMirSalesPosIbfk2", queryParam);
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.core.dao.TMirSalesPosDAO#getPrimarySalesPossByTSalesPos(com.cognizant.peg.core.common.SearchFilter)
	 */
	@Override
	public TMirSalesPos getPrimarySalesPossByTSalesPos(final SearchFilter<TSalesPos> searchFilter) {

		final TSalesPos tSalesPos = searchFilter.getEntityClass();
		List<Object> queryParam = new ArrayList<Object>();
		queryParam.add(tSalesPos);

		List<TMirSalesPos> mirrorSalesPos = genericDAO.findEntitiesByNamedQueryMultiCond("getPrimarySalesPosBySalesPos", queryParam, 0,
				-1);
		
		return (mirrorSalesPos!=null&&mirrorSalesPos.size()>0)?mirrorSalesPos.get(0):null;
	}
	
	/**
	 * Gets the t mir sp by sp id.
	 *
	 * @param salesPosId the sales pos id
	 * @param userDetails the user details
	 * @return the t mir sp by sp id
	 */
	@Override
    public List<Object[]> getTMirSpBySpId(Long salesPosId, Short tenantId) {
           List querParam = new ArrayList<>();
           querParam.add(salesPosId);
           querParam.add(tenantId);
           List<Object[]> mirSpList = genericDAO.findEntitiesByNamedQueryMultiCond("getTMirSalesPossByTSalesPosition", querParam, 0, -1);
           return mirSpList;
    }

}
