package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntAff;
import com.cognizant.opserv.sp.core.entity.TCustType;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.JPAQuery;

/**
 * Class provides API implementation for TCustAlgmntAffDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tCustAlgmntAffDAO")
public class TCustAlgmntAffDAOImpl implements TCustAlgmntAffDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TCustAlgmntAffDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	
	private static final String TALGMNTSALESTEAM = "tAlgmntSalesTeam";

	private final Class<TCustAlgmntAff> clazz;

	public Class<TCustAlgmntAff> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TCustAlgmntAffDAOImpl() {
		super();
		this.clazz = TCustAlgmntAff.class;
	}

	private static final String JPAQL = "select tCustAlgmntAffentity from TCustAlgmntAff tCustAlgmntAffentity";

	private static final String JPACOUNTQL = "select count(tCustAlgmntAffentity) from TCustAlgmntAff tCustAlgmntAffentity";

	private static final String[] RESTRICTIONS = {
			"tCustAlgmntAffentity.affTypeId = #{tCustAlgmntAffentity.getAffTypeId}",
			"tCustAlgmntAffentity.activeFlag = #{tCustAlgmntAffentity.getActiveFlag}",
			"tCustAlgmntAffentity.createdBy = #{tCustAlgmntAffentity.getCreatedBy}",
			"Date(tCustAlgmntAffentity.createDt) = '#{tCustAlgmntAffentity.getCreateDt}'",
			"tCustAlgmntAffentity.updatedBy = #{tCustAlgmntAffentity.getUpdatedBy}",
			"Date(tCustAlgmntAffentity.updateDt) = '#{tCustAlgmntAffentity.getUpdateDt}'",
			"tCustAlgmntAffentity.tenantId = #{tCustAlgmntAffentity.getTenantId}",
			"tCustAlgmntAffentity.tCustType.custTypeId = #{tCustAlgmntAffentity.tCustType.getCustTypeId}",
			"tCustAlgmntAffentity.tAlgmntSalesTeam.tAlgmntSalesTeamId = #{tCustAlgmntAffentity.tAlgmntSalesTeam.getTAlgmntSalesTeamId}" };

	/**
	 * Stores a new TCustAlgmntAff entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be persisted
	 * @return tCustAlgmntAff Persisted TCustAlgmntAff object
	 */
	public TCustAlgmntAff createTCustAlgmntAff(final TCustAlgmntAff tCustAlgmntAff) {
		LOGGER.info("=========== Create TCustAlgmntAff ===========");
		return genericDAO.store(tCustAlgmntAff);
	}

	/**
	 * Deletes a TCustAlgmntAff entity object from the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be deleted
	 */
	public void deleteTCustAlgmntAff(final Integer affTypeId) {
		LOGGER.info("=========== Delete TCustAlgmntAff ===========");
		final TCustAlgmntAff tCustAlgmntAff = genericDAO.get(clazz, affTypeId);
		genericDAO.remove(tCustAlgmntAff);
	}

	/**
	 * Updates a TCustAlgmntAff entity object in to the persistent store
	 * 
	 * @param tCustAlgmntAff
	 *            TCustAlgmntAff Entity object to be updated
	 * @return tCustAlgmntAff Persisted TCustAlgmntAff object
	 */
	public TCustAlgmntAff updateTCustAlgmntAff(final TCustAlgmntAff tCustAlgmntAff) {
		LOGGER.info("=========== Update TCustAlgmntAff ===========");
		return genericDAO.update(tCustAlgmntAff);
	}

	/**
	 * Retrieve an TCustAlgmntAff object based on given TCustAlgmntAff affTypeId.
	 * 
	 * @param tCustAlgmntAffId
	 *            the primary key value of the TCustAlgmntAff Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	public TCustAlgmntAff findTCustAlgmntAffById(final Integer tCustAlgmntAffId) {
		LOGGER.info("find TCustAlgmntAff instance with affTypeId: " + tCustAlgmntAffId);
		return genericDAO.get(clazz, tCustAlgmntAffId);
	}

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using Dynamic JPAQL.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAff if it exists against given criteria.
	 *         Returns null if not found
	 */
	public List<TCustAlgmntAff> findTCustAlgmntAffs(final SearchFilter<TCustAlgmntAff> searchFilter) {
		LOGGER.info("=========== Find TCustAlgmntAffs ===========");

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();

		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();
		
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();

		final JPAQuery jpaQuery = new JPAQuery("tCustAlgmntAffentity", tCustAlgmntAff);


		if (tCustAlgmntAff.getTAlgmntSalesTeam() == null) {
			jpaQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCustAlgmntAff.getTAlgmntSalesTeam());

			jpaQuery.bind(TALGMNTSALESTEAM, tCustAlgmntAff.getTAlgmntSalesTeam());
		}
		jpaQuery.setJPAql(JPAQL);
		jpaQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaQuery.setLogicalOperatorEnum(logOpEnum);
		return genericDAO.findEntities(jpaQuery, index, maxresult);
	}

	/**
	 * Fetch count of records for TCustAlgmntAffs.
	 * @param searchFilter
	 * @return an Object if it exists against given primary key. Returns null if
	 *         not found
	 */
	public Object countTCustAlgmntAffs(final SearchFilter<TCustAlgmntAff> searchFilter) {
		LOGGER.info("=========== Count TCustAlgmntAff ===========");

		final OperatorInfo operatorInfo = searchFilter.getOperatorInfo();
		final TCustAlgmntAff tCustAlgmntAff = searchFilter.getEntityClass();
		final LogicalOperatorEnum logOpEnum = operatorInfo.getLogicalOperator();
		final JPAQuery jpaCountQuery = new JPAQuery("tCustAlgmntAffentity", tCustAlgmntAff);

		
		if (tCustAlgmntAff.getTAlgmntSalesTeam() == null) {
			jpaCountQuery.bind(TALGMNTSALESTEAM, new TAlgmntSalesTeam());
		} else {
			LOGGER.info("tAlgmntSalesTeam {}" + tCustAlgmntAff.getTAlgmntSalesTeam());

			jpaCountQuery.bind(TALGMNTSALESTEAM, tCustAlgmntAff.getTAlgmntSalesTeam());
		}

		jpaCountQuery.setJPAql(JPACOUNTQL);
		jpaCountQuery.setRestrictionExpressionStrings(RESTRICTIONS);
		jpaCountQuery.setLogicalOperatorEnum(logOpEnum);

		return genericDAO.findEntities(jpaCountQuery, -1, -1);
	}

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmntAff> getTCustAlgmntAffsByTCustType(final SearchFilter<TCustType> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TCustType tCustType = searchFilter.getEntityClass();
		List<Object> tCustTypeList = new ArrayList<Object>();
		tCustTypeList.add(tCustType);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAffByTCustType", tCustTypeList, index, maxresult);
	}

	/**
	 * Count TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustType type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntAffsByTCustType(final SearchFilter<TCustType> searchFilter) {

		final TCustType tCustType = searchFilter.getEntityClass();
		List<Object> tCustTypeList = new ArrayList<Object>();
		tCustTypeList.add(tCustType);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntAffsByTCustType", tCustTypeList);
	}

	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns null if not found
	 */
	public List<TCustAlgmntAff> getTCustAlgmntAffsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAffByTAlgmntSalesTeam", tAlgmntSalesTeamList, index,
				maxresult);
	}
	/**
	 * Retrieve TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TCustAlgmntAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return List<TCustAlgmntAff> list of TCustAlgmntAffs if it exists against given
	 *         criteria. Returns list if  found
	 */
	
	public List<TCustAlgmntAff> getTCustAlgmntAffsByTAlgmntSalesTeamAndTCustType(final TAlgmntSalesTeam tAlgmntSalesTeam, final TCustType custType,final Short tenantId) {
		List paramList = new ArrayList();
        paramList.add(tAlgmntSalesTeam);
        paramList.add(custType.gettCustTypeId().getCustTypeId());
        paramList.add(tenantId);
       
        List<TCustAlgmntAff> list= genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustAlgmntAffByTAlgmntSalesTeamAndTCustType", paramList, 0, -1);
        return list;
	}

	/**
	 * Count TCustAlgmntAff based on given search criteria using JPA named Query.
	 * The search criteria is of TAlgmntSalesTeam type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return a Object indicating the count
	 */
	public Object countTCustAlgmntAffsByTAlgmntSalesTeam(final SearchFilter<TAlgmntSalesTeam> searchFilter) {

		final TAlgmntSalesTeam tAlgmntSalesTeam = searchFilter.getEntityClass();
		List<Object> tAlgmntSalesTeamList = new ArrayList<Object>();
		tAlgmntSalesTeamList.add(tAlgmntSalesTeam);
		return genericDAO.findEntitiesByNamedQuery("CountTCustAlgmntAffsByTAlgmntSalesTeam", tAlgmntSalesTeamList);
	}
	/**
	 * Retrieve lowestCustAffsHier based on given search criteria using JPA named Query.
	 * The search criteria is of CustAff type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return String of CustAffsHier if it exists against given
	 *         criteria. Returns null if not found
	 */

	public String getLowestLevelInCustAffHier(Long algmntId, Long bussUnitId, Long salesTeamId, Short tenantId,String localeId){
		List queryParam = new ArrayList<>();
		queryParam.add(algmntId);
		queryParam.add(bussUnitId);
		queryParam.add(salesTeamId);
		queryParam.add(tenantId);
		queryParam.add(localeId);
		List<Object> findEntitiesByNamedQueryMultiCond = genericDAO.findEntitiesByNamedQueryMultiCond("getLowestCusttype", queryParam, 0, -1);
		if (findEntitiesByNamedQueryMultiCond!=null && findEntitiesByNamedQueryMultiCond.size()>0)
			return findEntitiesByNamedQueryMultiCond.get(0).toString();
		return null;
	}

}
