package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EntityTemplate;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class TemplateAlignmentDAOService contains all the services for TemplateAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 04/05/2016
 * ***************************************************************************
 */
public interface TemplateAlignmentDAOService {

	/**
	 * Gets the entity template.
	 *
	 * @param entity the entity
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the entity template
	 * @throws OpservDataAccessException the opserv data access exception
	 * @method getEntityTemplate
	 */
	EntityTemplate getEntityTemplate(EntityType entity,Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;
}
