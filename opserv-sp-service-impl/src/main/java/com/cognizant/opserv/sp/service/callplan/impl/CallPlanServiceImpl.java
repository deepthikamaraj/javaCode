package com.cognizant.opserv.sp.service.callplan.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException.CallPlanServiceExceptionCode;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CallPlanDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.impl.CallPlanDAOServiceImpl;
import com.cognizant.opserv.sp.service.callplan.CallPlanService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class CallPlanServiceImpl contains all the services for call plan  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("callPlanService")
public class CallPlanServiceImpl implements CallPlanService {
	
	@Autowired
	private CallPlanDAOService callPlanDAOService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CallPlanDAOServiceImpl.class);


	/**
	 * Gets the customer call plan details.
	 *
	 * @param customer the customer
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the customer call plan details
	 * @throws CallPlanServiceException the call plan service exception
	 */
	@Override
	@Transactional
	public List<CustomerCallPlan> getCustomerCallPlanDetails(Customer customer,SalesPosition salesPos, UserDetails userDetails)throws CallPlanServiceException {
		
		try {
			 if(customer != null && salesPos != null && userDetails != null && customer.getId() != null && salesPos.getId() !=null && salesPos.getAlignmment().getId() !=null && salesPos.getHierarchy().getId() !=null && salesPos.getAlignmment().getSalesTeam().getId() !=null && salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId() != null && userDetails.getTenantId() !=null)
			  {
			return callPlanDAOService.getCustomerCallPlanDetails(customer,salesPos,userDetails);
		}  else{

			   Object[] args = new Object[]{"CustomerId or SalesPosId or SalesHierId or AlignmentId or BusinessUnitId or SalesTeamId or tenantId is null"};
			   throw new CallPlanServiceException(args);
		}
		}catch(OpservDataAccessException e) {
			  LOGGER.error("********************Issue is occurred while Fetching callPlan Details for Customer  ******************  ");
		       Object[] args = new Object[]{customer.getId()};
		       throw new CallPlanServiceException(CallPlanServiceExceptionCode.CALLPLAN_SER_EX_CD_001,"This issue is occurred while Fetching callPlan Details for Customer",args,e);
		
		  }
		
}

	//spbo services commented
	/*@Override
	public List<CallPlanConfiguration> alignmentSearch(Alignment alignment,
			Integer statusId, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallPlanConfiguration callPlanEditOrAddCheck(
			CallPlanConfiguration callPlanConfiguration, UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCallPlanColumnNames(Integer numOfPrds,
			UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> fetchAlgmntStatus(UserDetails userDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAdminCallPlanning(
			CallPlanConfiguration callPlanConfiguration, UserDetails userDetails) {
		// TODO Auto-generated method stub
		
	}
	*/
	
}
