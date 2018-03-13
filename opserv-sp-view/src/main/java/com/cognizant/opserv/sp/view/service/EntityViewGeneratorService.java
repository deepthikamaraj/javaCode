/**
 * 
 */
package com.cognizant.opserv.sp.view.service;

import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;

/**
 * ****************************************************************************.
 *
 * @class ISPDocumentCreator contains all the services for SP Document  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */

public interface EntityViewGeneratorService {
	
	/**
	 * @param entityChangeEvent
	 * @throws AlignmentServiceException
	 */
	void generateView(EntityChangeEvent entityChangeEvent) throws AlignmentServiceException;

}
