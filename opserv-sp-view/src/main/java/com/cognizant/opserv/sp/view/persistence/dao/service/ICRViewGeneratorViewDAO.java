package com.cognizant.opserv.sp.view.persistence.dao.service;

import com.cognizant.opserv.sp.common.EventType;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 *****************************************************************************
 * 
 * @class ICRViewGeneratorViewDAO
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 ***************************************************************************
 *        
 */
public interface ICRViewGeneratorViewDAO {

	/**
	 * @param salesPosId
	 * @param entityId
	 * @param entityType
	 * @param userDetails
	 */
	public void generateEntityViewDAO(Long entityId, String entityType, EventType eventType, UserDetails userDetails) throws OpservDataAccessException;

}
