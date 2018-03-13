package com.cognizant.opserv.sp.view.service;

import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************.
 *
 * @class IViewGenerator  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface IViewGenerator {
	
	/**
	 * @param salesPosId
	 * @param entityId
	 * @param entityType
	 * @param userDetails
	 */
	public void generateEntityView(EntityChangeEvent entityChangeEvent, UserDetails userDetails);

}
