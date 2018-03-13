package com.cognizant.opserv.sp.service.callplan;

import java.util.List;

import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class CallPlanService contains all the services for call plan  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CallPlanService {
	
	
	//call plan information for given customer and sales position

	/**
	 * Gets the customer call plan details.
	 *
	 * @param customer the customer
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the customer call plan details
	 * @throws CallPlanServiceException the call plan service exception
	 */
	List<CustomerCallPlan> getCustomerCallPlanDetails(Customer customer,SalesPosition salesPos,UserDetails userDetails) throws CallPlanServiceException;
	
	//spbo services commented
/*	List<CallPlanConfiguration> alignmentSearch(Alignment alignment, Integer statusId, UserDetails userDetails);
	
	CallPlanConfiguration callPlanEditOrAddCheck(CallPlanConfiguration callPlanConfiguration, UserDetails userDetails);

	List<String> getCallPlanColumnNames(Integer numOfPrds, UserDetails userDetails);

	Map<Integer,String> fetchAlgmntStatus(UserDetails userDetails);
	
	void saveAdminCallPlanning(CallPlanConfiguration callPlanConfiguration,UserDetails userDetails);*/



	
	
}
