package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class CallPlanDAOService contains all the DAO services for call plan  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface CallPlanDAOService {

	/**
	 * Gets the customer call plan details.
	 * 
	 * @param customer
	 *            the customer
	 * @param salesPos
	 *            the sales pos
	 * @param userDetails
	 *            the userDetails
	 * @return the customer call plan details
	 * @throws OpservDataAccessException
	 *             the call plan service exception
	 */
	List<CustomerCallPlan> getCustomerCallPlanDetails(Customer customer,SalesPosition salesPos, UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * Creates the dir based call plan.
	 * 
	 * @param List
	 *            <CustomerCallPlan> the customer call plan details
	 * @param alignmentInfo
	 *            the alignment info
	 * 
	 * @param userDetails
	 *            the userDetails
	 * @return the customer call plan
	 * @throws OpservDataAccessException
	 *             the OpservDataAccess exception
	 */
	void createCallPlan(List<CustomerCallPlan> customerCallPlanDetails,CustomerAlignment savedCuAl,UserDetails userDetails) throws OpservDataAccessException;

	/**
	 * @param custCallPlanId
	 * @param statusId
	 * @param activeFlag
	 * 
	 * @param userDetails
	 *            the userDetails
	 * @throws OpservDataAccessException
	 */
	void updateCallPlanCRStatusFrChngReqApprove(Long custCallPlanId,Integer statusId,Character activeFlag , UserDetails userDetails)throws OpservDataAccessException;
	/**
	 * @param CustAlId
	 * @param userDetails
	 * @return List of CustomerCallPlan
	 * @throws OpservDataAccessException
	 */
	List<CustomerCallPlan> getCallPlanByCustAlIdForChngReq(Long customerALId, UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * @param CustAlId
	 * @param userDetails
	 * @return CustomerCallPlan
	 * @throws OpservDataAccessException
	 */
	CustomerCallPlan getCallPlanByCustAlIdForChngReqEdit(Long customerAlId,UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * @param custAlIdList
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	Map<Long, List<CustomerCallPlan>> getActiveCustCallPlanByCustAlId(List<Long> custAlIdList,UserDetails userDetails)throws OpservDataAccessException;

	/**
	 * Edits the CallPlan
	 * @param sourceBaseCustAlgmt
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void editCustomerCallPlan(CustomerAlignment sourceBaseCustAlgmt,UserDetails userDetails)throws OpservDataAccessException;

	
}