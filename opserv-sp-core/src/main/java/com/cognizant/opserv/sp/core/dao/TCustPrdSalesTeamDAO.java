package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * Interface represents API's of TCustPrdSalesTeam DAO.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/04/2016
 */
public interface TCustPrdSalesTeamDAO {
  
	/**
	 * Gets the sales team by cust id al bu st.
	 *
	 * @param custId the cust id
	 * @param alignmentId the alignment id
	 * @param salesTeamId the sales team id
	 * @param busUnitId the bus unit id
	 * @param prdId the product Id
	 * @param userDetails the user details
	 * @return the sales team by cust id al bu st
	 */
	List<TCustPrdSalesTeam> getSalesTeamByCustIdAlBuStPrdId(Integer custId, Long alignmentId , Long salesTeamId, Long busUnitId , List<Long> prdId ,Short tenantId);
	
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
	List<TCustPrdSalesTeam> getPrdListByCustIdAlBuSt(Integer custId, Long alignmentId , Long salesTeamId, Long busUnitId , UserDetails userDetails);

	/**
	 * @param customerAlignment
	 * @param userDetails
	 * @return List<TCustSalesTeam> list of TCustSalesTeams 
	 */
	void updateTCustPrdSalesTeam(CustomerAlignment customerAlignment,
			Short tenantId);

	TCustPrdSalesTeam updateTCustSalesTeam(TCustPrdSalesTeam tCustPrdSalesTeam);
	
}
