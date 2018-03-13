package com.cognizant.opserv.sp.view.persistence.dao.service;

import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class IRunAlignmentRulesDAO  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface IRunAlignmentRulesDAO {
	
	/**
	 * @param salesPosId
	 * @param entityId
	 * @param entityType
	 * @param userDetails
	 */
	void runAlgmntRules(Long salesPosId, Long entityId, String entityType, UserDetails userDetails);

}
