package com.cognizant.opserv.sp.view.service;

import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;

/**
 * ****************************************************************************.
 *
 * @class IRunAlignmentRulesService contains all the services to run alignment rules
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface IRunAlignmentRulesService {
	
	/**
	 * @param entityChangeEvent
	 * @throws AlignmentServiceException
	 */
	void runAlgmntRules(EntityChangeEvent entityChangeEvent) throws AlignmentServiceException;

}
