package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.MetricValueType;
import com.cognizant.opserv.sp.core.entity.TGeoMtrValue;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TGeoMtrValueDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tGeoMtrValueDAO")
public class TGeoMtrValueDAOImpl implements TGeoMtrValueDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TGeoMtrValueDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TGeoMtrValue> clazz;

	public Class<TGeoMtrValue> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TGeoMtrValueDAOImpl() {
		super();
		this.clazz = TGeoMtrValue.class;
	}

	private static final String JPAQL = "select tGeoMtrValueentity from TGeoMtrValue tGeoMtrValueentity";

	private static final String JPACOUNTQL = "select count(tGeoMtrValueentity) from TGeoMtrValue tGeoMtrValueentity";

	private static final String[] RESTRICTIONS = { "tGeoMtrValueentity.seqNumber = #{tGeoMtrValueentity.getSeqNumber}",
			"Date(tGeoMtrValueentity.execDtTm) = '#{tGeoMtrValueentity.getExecDtTm}'",
			"tGeoMtrValueentity.activeFlag = #{tGeoMtrValueentity.getActiveFlag}",
			"tGeoMtrValueentity.mtrValue = #{tGeoMtrValueentity.getMtrValue}",
			"tGeoMtrValueentity.execId = #{tGeoMtrValueentity.getExecId}",
			"tGeoMtrValueentity.configId = #{tGeoMtrValueentity.getConfigId}",
			"tGeoMtrValueentity.createdBy = #{tGeoMtrValueentity.getCreatedBy}",
			"Date(tGeoMtrValueentity.createDt) = '#{tGeoMtrValueentity.getCreateDt}'",
			"tGeoMtrValueentity.updatedBy = #{tGeoMtrValueentity.getUpdatedBy}",
			"Date(tGeoMtrValueentity.updateDt) = '#{tGeoMtrValueentity.getUpdateDt}'",
			"tGeoMtrValueentity.tenantId = #{tGeoMtrValueentity.getTenantId}",
			"tGeoMtrValueentity.mtrId = #{tGeoMtrValueentity.getMtrId}",
			"tGeoMtrValueentity.mtrValueTypeId = #{tGeoMtrValueentity.getMtrValueTypeId}",
			"tGeoMtrValueentity.tTerrZipAlgmnt.tTerrZipAlgmntId = #{tGeoMtrValueentity.tTerrZipAlgmnt.getTTerrZipAlgmntId}" };

	/**
	 * Stores a new TGeoMtrValue entity object in to the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be persisted
	 * @return tGeoMtrValue Persisted TGeoMtrValue object
	 */
	public TGeoMtrValue createTGeoMtrValue(final TGeoMtrValue tGeoMtrValue) {
		LOGGER.info("=========== Create TGeoMtrValue ===========");
		return genericDAO.store(tGeoMtrValue);
	}

	/**
	 * Deletes a TGeoMtrValue entity object from the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be deleted
	 */
	public void deleteTGeoMtrValue(final Integer seqNumber) {
		LOGGER.info("=========== Delete TGeoMtrValue ===========");
		final TGeoMtrValue tGeoMtrValue = genericDAO.get(clazz, seqNumber);
		genericDAO.remove(tGeoMtrValue);
	}

	/**
	 * Updates a TGeoMtrValue entity object in to the persistent store
	 * 
	 * @param tGeoMtrValue
	 *            TGeoMtrValue Entity object to be updated
	 * @return tGeoMtrValue Persisted TGeoMtrValue object
	 */
	public TGeoMtrValue updateTGeoMtrValue(final TGeoMtrValue tGeoMtrValue) {
		LOGGER.info("=========== Update TGeoMtrValue ===========");
		return genericDAO.update(tGeoMtrValue);
	}

	/**
	 * Retrieve an TGeoMtrValue object based on given TGeoMtrValue seqNumber.
	 * 
	 * @param tGeoMtrValueId
	 *            the primary key value of the TGeoMtrValue Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TGeoMtrValue findTGeoMtrValueById(final Integer tGeoMtrValueId) {
		LOGGER.info("find TGeoMtrValue instance with seqNumber: " + tGeoMtrValueId);
		return genericDAO.get(clazz, tGeoMtrValueId);
	}

	/**
	 * Retrieve TGeoMtrValue based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoMtrValue> list of TGeoMtrValue if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TGeoMtrValue> findTGeoMtrValues(final SearchFilter<TGeoMtrValue> searchFilter) {
		LOGGER.info("=========== Find TGeoMtrValues ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TGeoMtrValue tGeoMtrValue = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		//int maxresult = 3;
		//int index=0;
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tGeoMtrValueentity", tGeoMtrValue);

		/*if (tGeoMtrValue.getTTerrZipAlgmnt() == null) {
			jpaQuery.bind(TTERRZIPALGMNT, new TTerrZipAlgmnt());
		} else {
			LOGGER.info("tTerrZipAlgmnt {}", tGeoMtrValue.getTTerrZipAlgmnt());

			jpaQuery.bind(TTERRZIPALGMNT, tGeoMtrValue.getTTerrZipAlgmnt());
		}*/
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TGeoMtrValues.
	 * 
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTGeoMtrValues(final SearchFilter<TGeoMtrValue> searchFilter) {
		LOGGER.info("=========== Count TGeoMtrValue ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TGeoMtrValue tGeoMtrValue = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tGeoMtrValueentity", tGeoMtrValue);

		/*if (tGeoMtrValue.getTTerrZipAlgmnt() == null) {
			jpaCountQuery.bind(TTERRZIPALGMNT, new TTerrZipAlgmnt());
		} else {
			LOGGER.info("tTerrZipAlgmnt {}", tGeoMtrValue.getTTerrZipAlgmnt());

			jpaCountQuery.bind(TTERRZIPALGMNT, tGeoMtrValue.getTTerrZipAlgmnt());
		}
*/
		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TGeoMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TGeoMtrValue> list of TGeoMtrValues if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TGeoMtrValue> getTGeoMtrValuesByTTerrZipAlgmnt(final SearchFilter<TTerrZipAlgmnt> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		List<Object> tTerrZipAlgmntList = new ArrayList<Object>();
		tTerrZipAlgmntList.add(tTerrZipAlgmnt);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO
				.findEntitiesByNamedQueryMultiCond("FindTGeoMtrValueByTTerrZipAlgmnt", tTerrZipAlgmntList, index, maxresult);
	}

	/**
	 * Count TGeoMtrValue based on given search criteria using JPA named Query.
	 * The search criteria is of TTerrZipAlgmnt type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTGeoMtrValuesByTTerrZipAlgmnt(final SearchFilter<TTerrZipAlgmnt> searchFilter) {

		final TTerrZipAlgmnt tTerrZipAlgmnt = searchFilter.getEntityClass();
		List<Object> tTerrZipAlgmntList = new ArrayList<Object>();
		tTerrZipAlgmntList.add(tTerrZipAlgmntList);
		return genericDAO.findEntitiesByNamedQuery("CountTGeoMtrValuesByTTerrZipAlgmnt", tTerrZipAlgmntList);
	}

	@Override
	public List<TGeoMtrValue> findTGeoMtrValueByIDs(Long salesPosId, Long salesHierId,int mtrId, String postalCd,
			short tenantId) {
		
		List paramList = new ArrayList();
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(mtrId);
		paramList.add(postalCd);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTGeoMtrValueByIDs", paramList, 0, -1);
	}

	@Override
	public List<TGeoMtrValue> findTGeoMtrValueByPosCodesAndMtrId(int mtrId, List<String> postalCds, Short tenantId) {
		List paramList = new ArrayList();
		//paramList.add(salesPId);
		//paramList.add(salesHier);
		paramList.add(mtrId);
		paramList.add(postalCds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTGeoMtrValueByPosCodesAndMtrId", paramList, 0, -1);
	}

	@Override
	public List<Object[]> findTGeoMtrValueByIDsForHeatMap(int mtrId, List<String> postalCd, short tenantId) {
		List paramList = new ArrayList();
		paramList.add(mtrId);
		paramList.add(postalCd);
		paramList.add(MetricValueType.CURRENT.getId());
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findTGeoMtrValueByIDsForHeatMap", paramList, 0, -1);
	}

	@Override
	public List<Object[]> findTGeoMtrValueByPostalCd(List<String> postalCd,Short tenantId) {
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(postalCd);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTGeoMtrValueByPostalCd", paramList, 0, -1);
	}
	
	/**
	 * Find min and max value by mtr id.
	 *
	 * @param mtrId the mtr id
	 * @param tenantId the tenant id
	 * @return the list
	 */
	@Override
	public List<Object[]> findMinAndMaxValueByMtrId(Long mtrId, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(mtrId.intValue());
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("findMinAndMaxGeoMtrValueByMtrId", paramList, 0, -1);
	}

	@Override
	public List<Object[]> getMtrValueByPostalCodesAndSP(List<Integer> mtrIds,
			String postalCd, Long spId, List<Integer> mtrValueTypeIds, Short tenantId) {
		List paramList = new ArrayList();
		paramList.add(mtrIds);
		paramList.add(postalCd);
		paramList.add(spId);
		paramList.add(mtrValueTypeIds);
		paramList.add(tenantId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("getMtrValueByPostalCodesAndSP", paramList, 0, -1);
	}
	
}
