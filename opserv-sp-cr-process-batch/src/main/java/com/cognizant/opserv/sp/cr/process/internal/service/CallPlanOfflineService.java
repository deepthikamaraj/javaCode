package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
/**
 * Call Plan service for offline
 * @author 426111
 *
 */
public interface CallPlanOfflineService {

	/**
	 * To create CallPlan
	 * 
	 * @param custCallPlans
	 *            -list of Customer CallPlans
	 * 
	 * @param customerAlignment
	 *            -customer Alignment details
	 * 
	 * @param userDetails
	 * @throws CallPlanServiceException 
	 */
	void createCallPlan(List<CustomerCallPlan> custCallPlans, CustomerAlignment customerAlignment, UserDetails userDetails) throws CallPlanServiceException;
	/**
	 * Gets the CallPlan Details for List of Customer Alignment Id's
	 * 
	 * @param CustomerAlignmentDTO
	 *            -the Customer Alignment DTO
	 * @param userDetails
	 *            -the userDetails
	 * @throws CallPlanServiceException 
	 */
	void populateCallPlan(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws CallPlanServiceException;
	/**
	 * Edit CallPlan Details for  Customer Alignment 
	 * 
	 * @param CustomerAlignmentDTO
	 *            -the Customer Alignment DTO
	 * @param userDetails
	 *            -the userDetails
	 * @throws CallPlanServiceException 
	 */
	void editCustomerCallPlan(CustomerAlignment targetCustomerAlignment, UserDetails userDetails) throws CallPlanServiceException;
	
	
}
