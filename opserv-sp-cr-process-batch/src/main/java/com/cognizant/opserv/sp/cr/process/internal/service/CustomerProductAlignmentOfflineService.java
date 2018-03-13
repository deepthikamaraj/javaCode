package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerProductAlignmentOfflineService {

	/**
	 * getCustomerProductAlignments-gets the customer product
	 * 
	 * @param custAlgmtDTO
	 *            -customer Alignment DTO
	 * @param userDetails
	 *            -userDetails
	 * @return List<CustomerProductAlignment> - List of Customer Product
	 *         Alignment
	 */

	List<CustomerProductAlignment> getCustomerProductAlignments(List<CustomerAlignment> cusAlgmnts,UserDetails userDetails);
	
	void populateCustomerProductAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException;
	
}
