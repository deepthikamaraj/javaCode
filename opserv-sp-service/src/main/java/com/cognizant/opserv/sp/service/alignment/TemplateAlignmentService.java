package com.cognizant.opserv.sp.service.alignment;

import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class TemplateAlignmentService contains all the services for TemplateAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 04/05/2016
 * ***************************************************************************
 */
public interface TemplateAlignmentService {
	
	/**
	 * @method getEntityTemplate : used to get Entity Info
	 * @param entity : entity name
	 * @param alignment : alignment Details
	 * @param userDetails : User details
	 * @return Returns Entity Info
	 * @throws AlignmentServiceException
	 */
	EntityTemplate getEntityTemplate(EntityType entity,Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;

}
