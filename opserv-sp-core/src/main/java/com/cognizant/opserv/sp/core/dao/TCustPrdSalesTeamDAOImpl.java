package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.dao.GenericDAO;

/**
 * Class provides API implementation for  TCustPrdSalesTeamDAO.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/04/2016
 */
@Repository("tCustPrdSalesTeamDao")
public class TCustPrdSalesTeamDAOImpl implements  TCustPrdSalesTeamDAO{

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TCustPrdSalesTeamDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
	/**
	 * Gets the sales team by cust id al bu st.
	 *
	 * @param custId the cust id
	 * @param alignmentId the alignment id
	 * @param salesTeamId the sales team id
	 * @param busUnitId the bus unit id
	 * @param prdId the prd id
	 * @param userDetails the user details
	 * @return the sales team by cust id al bu st
	 */
	@Override
		public List<TCustPrdSalesTeam> getSalesTeamByCustIdAlBuStPrdId(Integer custId, Long alignmentId , Long salesTeamId, Long busUnitId ,List<Long> prdIdList, Short tenantId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);
		paramList.add(alignmentId);
		paramList.add(busUnitId);
		paramList.add(salesTeamId);
		paramList.add(prdIdList);
		paramList.add(tenantId);
		List<TCustPrdSalesTeam> tCustPrdSalesTeam =  genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustPrdSalesTeamsByCustIdALBuStIdPrdId",paramList, 0 ,-1);
		
		return tCustPrdSalesTeam;
	}
	/**
	 * Gets the prd list by cust id al bu st.
	 *
	 * @param custId the cust id
	 * @param alignmentId the alignment id
	 * @param salesTeamId the sales team id
	 * @param busUnitId the bus unit id
	 * @param userDetails the user details
	 * @return the prd list by cust id al bu st
	 */
	public List<TCustPrdSalesTeam> getPrdListByCustIdAlBuSt(Integer custId, Long alignmentId , Long salesTeamId, Long busUnitId , UserDetails userDetails){
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(custId);
		paramList.add(alignmentId);
		paramList.add(busUnitId);
		paramList.add(salesTeamId);
		paramList.add(userDetails.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTCustPrdSalesTeamsByCustIdALBuStId",paramList, 0 ,-1);
	}

	/**
	 * Updates a TCustPrdSalesTeam entity object in to the persistent store
	 * 
	 * @param tCustPrdSalesTeam
	 *            tCustPrdSalesTeam Entity object to be updated
	 * @return tCustPrdSalesTeam Persisted TCustSalesTeam object
	 */
	@Override
	public TCustPrdSalesTeam updateTCustSalesTeam(final TCustPrdSalesTeam tCustPrdSalesTeam) {
		LOGGER.info("=========== Update TCustPrdSalesTeam ===========");
		return genericDAO.update(tCustPrdSalesTeam);
	}
	
	

	/**
	 * Updates a TCustPrdSalesTeam 
	 * 
	 * @param tCustPrdSalesTeam
	 *            tCustPrdSalesTeam  object to be updated
	 * @return void
	 */
	@Override
	public void updateTCustPrdSalesTeam(
			CustomerAlignment customerAlignment, Short tenantId) {
		Integer custId =customerAlignment.getCustomer().getId().intValue();
		Long al = customerAlignment.getSalesPosition().getAlignmment().getId();
		Long st = customerAlignment.getSalesPosition().getAlignmment().getSalesTeam().getId();
		Long bu = customerAlignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId();
		
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(CommonConstants.UPDATED);
		paramList.add(DateUtil.getCurrentDate());
		paramList.add(tenantId);
		paramList.add(custId);
		paramList.add(al);
		paramList.add(bu);
		paramList.add(st);
		
		int custPrdSalesTeam = genericDAO.updateEntitiesNamedQuery("UpdateTCustPrdSalesTeam", paramList );
		
	}

}
