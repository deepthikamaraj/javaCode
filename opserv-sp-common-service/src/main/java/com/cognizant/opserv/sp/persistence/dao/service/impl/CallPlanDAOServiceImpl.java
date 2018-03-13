package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CallPlanEntityAssembler;
import com.cognizant.opserv.sp.assembler.CustomerCallPlanModelAssembler;
import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustCallPlanDAO;
import com.cognizant.opserv.sp.core.entity.TCallPlanType;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CallPlanDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * ****************************************************************************.
 *
 * @class CallPlanDAOServiceImpl contains all the DAO services for call plan  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class CallPlanDAOServiceImpl implements CallPlanDAOService {
	/** The t cust call plan dao. */
	@Autowired
	private TCustCallPlanDAO tCustCallPlanDAO;

    /** The t cust Algmnt dao. */
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDAO;
	
	  /** The CustomerCallPlanModelAssembler. */
	@Autowired
	private CustomerCallPlanModelAssembler customerCallPlanModelAssembler;

	  /** The CallPlanEntityAssembler. */
	@Autowired
	private CallPlanEntityAssembler callPlanEntityAssembler;

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CallPlanDAOServiceImpl.class);

	/**
	 * Gets the customer call plan details.
	 * 
	 * @param customer
	 *            the customer
	 * @param salesPos
	 *            the sales pos
	 * @param userDetails
	 *            the user details
	 * @return the customer call plan details
	 * @throws OpservDataAccessException
	 *             the call plan service exception
	 */
	@Override
	public List<CustomerCallPlan> getCustomerCallPlanDetails(Customer customer,
			SalesPosition salesPos, UserDetails userDetails) throws OpservDataAccessException {

		try {
			LOGGER.debug("********************get customer CallPlan Details started  ******************  ");
			CustomerAlignment custalignment = new CustomerAlignment();
			custalignment.setSalesPosition(salesPos);
			custalignment.setCustomer(customer);
			custalignment.setTenantId(userDetails.getTenantId());

			TCustCallPlan tCustCallPlan = callPlanEntityAssembler.mapCustCallPlanInfoEntity(custalignment);
			LOGGER.debug("******************** DB calls to get customer CallPlan Information started  ******************  ");
			List<TCustCallPlan> custCallPlanInfo = tCustCallPlanDAO.getCustomerCallPlanDetails(tCustCallPlan);
			LOGGER.debug("********************DB calls to get customer CallPlan Information Ended  ******************  ");
			if (tCustCallPlan != null) {
				if (custCallPlanInfo != null && !(custCallPlanInfo.isEmpty())) {
					return customerCallPlanModelAssembler
							.convertDirBasedCustomerCallPlanDetailsToModel(custCallPlanInfo);

				}

			}

			LOGGER.debug("********************get customer CallPlan Details Ended  ******************  ");
		           
		  }catch (RuntimeException re) {
		   LOGGER.error("********************Issue is occurred while Fetching callPlan Details for Customer  ******************  ");
		throw new OpservDataAccessException(
				"This issue is occurred while Fetching callPlan Details for Customer", re);
	}	

		return null;
	}

	
	/**
	 * Creates call plan.
	 * 
	 * @param List
	 *            <CustomerCallPlan> the customer call plan details
	 * @param alignmentInfo
	 *            the alignment info
	 * @param userDetails
	 *            the userDetails
	 * @return the customer call plan
	 * @throws OpservDataAccessException
	 *             the OpservDataAccess exception
	 */
	@Override
	public void createCallPlan(List<CustomerCallPlan> customerCallPlanDetails,CustomerAlignment savedCuAl, UserDetails userDetails) throws OpservDataAccessException {
		
		try{
			LOGGER.debug("********************Create Direction Based CallPlan started  ******************  ");
			Long custAlgnId=savedCuAl.getId();
			if(custAlgnId != null && null != customerCallPlanDetails && !customerCallPlanDetails.isEmpty()){
			List<TCustCallPlan> tCustCallPlanList=new ArrayList<TCustCallPlan>();
		            for(CustomerCallPlan customerCallPlan:customerCallPlanDetails){
					LOGGER.debug("********************Insert into TCustCallPlan started  ******************  ");
					TCustCallPlan tCustCallPlan=new TCustCallPlan();
					tCustCallPlan.setActiveFlag(CommonConstants.ACTIVE_N);
					TCustAlgmnt tCustAlgmnt=new TCustAlgmnt();
					tCustAlgmnt.setCustAlgmntId(custAlgnId);
					tCustCallPlan.setTCustAlgmnt(tCustAlgmnt);
					tCustCallPlan.setEffStartDt(DateUtil.getCurrentDate());
					tCustCallPlan.setCreateDt(DateUtil.getCurrentDate());
					tCustCallPlan.setCreatedBy(userDetails.getUserId());
					tCustCallPlan.setTenantId(userDetails.getTenantId());
					tCustCallPlan.setCustId(savedCuAl.getCustomer().getId().intValue());
					tCustCallPlan.setStatusId(StatusType.APPROVED.getId());
					TCallPlanType tCallPlanType = new TCallPlanType();
					tCallPlanType.setCallPlanTypeId(customerCallPlan.getType().getId().shortValue());
					tCustCallPlan.setTCallPlanType(tCallPlanType);
					tCustCallPlan.setPlannedCalls((short) customerCallPlan.getPlannedCalls());
					
					tCustCallPlanList.add(tCustCallPlan);
					LOGGER.debug("********************Insert into TCustCallPlan Ended  ******************  ");
										
						}
		            tCustCallPlanDAO.createTCustCallPlan(tCustCallPlanList);
		}							
			 LOGGER.debug("********************Create Direction Based CallPlan Ended  ******************  ");
			
		}catch (RuntimeException re) {
			 LOGGER.error("********************Issue occurred while Creating callPlan  ******************  ");
			throw new OpservDataAccessException(
					"This issue occurred while creating callDiretion based callPlan", re);
		}
	}

	@Override
	public void updateCallPlanCRStatusFrChngReqApprove(Long custcallPlanId,Integer statusId,Character activeFlag, UserDetails userDetails) throws OpservDataAccessException {

		try{
			LOGGER.debug("********************Update statusId and activeFlag Started  ******************  ");
			
			if(custcallPlanId !=null){
				TCustCallPlan tCustCallPlan=tCustCallPlanDAO.findTCustCallPlanById(custcallPlanId.intValue());
				tCustCallPlan.setUpdatedBy(userDetails.getUserId());
				tCustCallPlan.setUpdateDt(DateUtil.getCurrentDate());
				//tCustCallPlan.setStatusId(statusId);
				if(activeFlag != null){
					tCustCallPlan.setActiveFlag(activeFlag);
				}else{
					tCustCallPlan.setActiveFlag(tCustCallPlan.getActiveFlag());
				}
						
			    tCustCallPlanDAO.updateTCustCallPlan(tCustCallPlan);
				LOGGER.debug("********************Update statusId and activeFlag Ended  ******************  ");
				
			}//end of custCallPlanId check
	
			
		}catch (RuntimeException re) {
			LOGGER.error("********************Issue occured while updating CR Status or activeFlag for CallPlan  ******************  ");
			throw new OpservDataAccessException(
					"This issue occurred while updating CR Status or activeFlag for CallPlan", re);
		}
	
		
	}
	/**
	 * @param CustAlId
	 * @param userDetails
	 * @return list of CustomerCallPlan
	 * @throws CallPlanServiceException
	 */
	@Override
	public List<CustomerCallPlan> getCallPlanByCustAlIdForChngReq(Long customerAlId,UserDetails userDetails) throws OpservDataAccessException{
		try {
			LOGGER.debug("********************get details for callPlan Started  ******************  ");

			CustomerAlignment custalignment=new CustomerAlignment();
			custalignment.setTenantId(userDetails.getTenantId());
			
			  LOGGER.debug("******DB Calls to get cust callPlan Information Started*******");
				 List<TCustCallPlan> custCallPlanInfo= tCustCallPlanDAO.FindCallPlanByCustAlId(customerAlId, userDetails.getTenantId());
				  LOGGER.debug("******DB Calls to get cust callPlan Information Ended*******");
			
					if (custCallPlanInfo != null && !(custCallPlanInfo.isEmpty())) {
					
                       return customerCallPlanModelAssembler.convertDirBasedCustomerCallPlanDetailsToModel(custCallPlanInfo);
						
						
			}
			LOGGER.debug("********************get details for callPlan Ended  ******************  ");
		}catch (RuntimeException re) {
			LOGGER.error("*******************Issue occurred while Fetching callPlan Details for Customer  ******************  ");
			throw new OpservDataAccessException(
					"This issue is occurred while Fetching callPlan Details for Customer by custAlId for change Request", re);
		}
		return null;
		
	}
	/**
	 * @param CustAlId
	 * @param userDetails
	 * @return CustomerCallPlan
	 * @throws OpservDataAccessException
	 */
	@Override
	public CustomerCallPlan getCallPlanByCustAlIdForChngReqEdit(Long customerAlId, UserDetails userDetails) throws OpservDataAccessException{
		try {
			LOGGER.debug("********************get details for callPlan Started  ******************  ");

			CustomerAlignment custalignment=new CustomerAlignment();
			custalignment.setTenantId(userDetails.getTenantId());
			
			  LOGGER.debug("******DB Calls to get cust callPlan Information Started*******");
				 List<TCustCallPlan> custCallPlanInfo= tCustCallPlanDAO.FindCallPlanByCustAlIdForEdit(customerAlId,userDetails.getTenantId());
				  LOGGER.debug("******DB Calls to get cust callPlan Information Ended*******");
			
					if (custCallPlanInfo != null && !(custCallPlanInfo.isEmpty())) {
						for (TCustCallPlan custCallPlan : custCallPlanInfo) {
                       return customerCallPlanModelAssembler.convertSaveUpdateDirBasedCustomerCallPlanToModel(custCallPlan);
						
						}
			}
			LOGGER.debug("********************get details for callPlan Ended  ******************  ");
		}catch (RuntimeException re) {
			LOGGER.error("*******************Issue occurred while Fetching callPlan Details for Customer  ******************  ");
			throw new OpservDataAccessException(
					"This issue occurred while Fetching CallPlan Details by custAlId for change Request in Edit", re);
		}
		return null;
		
	}
	/**
	 * @param custAlIdList
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */

	@Override
	public Map<Long, List<CustomerCallPlan>> getActiveCustCallPlanByCustAlId(List<Long> custAlIdList, UserDetails userDetails)throws OpservDataAccessException {
		LOGGER.info("**************************Fetch Acive CallPlan by CustAlId started******************************");
		List<TCustCallPlan> tCustCallPlanList = new ArrayList<TCustCallPlan>();
		try {
			if (custAlIdList != null && !custAlIdList.isEmpty()) {
				tCustCallPlanList = tCustCallPlanDAO
						.findActiveCustCallPlansByCustAlgmntId(custAlIdList,
								userDetails.getTenantId());
				if(tCustCallPlanList != null && !tCustCallPlanList.isEmpty()){
				return customerCallPlanModelAssembler
						.convertBasedCPDataToModel(tCustCallPlanList);
				}
			}
			LOGGER.info("**************************Fetch Acive CallPlan by CustAlId Ended******************************");
		} catch (RuntimeException re) {
			LOGGER.error("********************Issue occurred while fetching CallPlan Details from List of Cust Alignment Id's  ******************  ");
			throw new OpservDataAccessException(
					"This issue occurred whileIssue occurred while fetching CallPlan Details from List of Cust Alignment Id's",
					re);
		}

		return null;
	}
	/**
	 * Edit CallPlan Details for  Customer Alignment 
	 * 
	 * @param CustomerAlignment
	 *            -the Customer Alignment 
	 * @param userDetails
	 *            -the userDetails
	 * @throws CallPlanServiceException 
	 */
	@Override
	public void editCustomerCallPlan(CustomerAlignment customerAlignment,UserDetails userDetails) throws OpservDataAccessException {
		try{
			LOGGER.info("********************Execute of editCustomerCallPlan --- Started  ******************");
			
			if (customerAlignment.getCustomerCallPlan() != null && customerAlignment.getId() != null) {

				LOGGER.info("********************Fetch CallPlan Started******************"
						+ customerAlignment.getId());
				List<TCustCallPlan> custCallPlanList = tCustCallPlanDAO
						.findPlannedCustomerCallPlanObject(CallPlanType.PLANNED
								.getId().shortValue(), customerAlignment
								.getId(), userDetails.getTenantId());
				LOGGER.info("********************Fetch CallPlan Ended ******************");

				if (custCallPlanList != null && !custCallPlanList.isEmpty()) {
					LOGGER.info("********************Update of Planned Call Plan for Base SP -- started******************");
					TCustCallPlan existingPlannedCallPlan = custCallPlanList
							.get(0);
					existingPlannedCallPlan
							.setPlannedCalls((short) (customerAlignment
									.getCustomerCallPlan().getPlannedCalls()));
					existingPlannedCallPlan.setUpdatedBy(userDetails
							.getUserId());
					existingPlannedCallPlan.setUpdateDt(DateUtil
							.getCurrentDate());
					tCustCallPlanDAO
							.updateTCustCallPlan(existingPlannedCallPlan);
					LOGGER.info("********************Update of Planned Call Plan for Base SP -- ended******************");

				}
			 else {
				if(customerAlignment.isCustomerTargeted()){
					LOGGER.info("********************Create of Planned Call Plan for Base SP --- started ******************");
	
					TCustCallPlan tCustCallPlanObj = new TCustCallPlan();
	
					tCustCallPlanObj.setCustId(customerAlignment.getCustomer()
							.getId().intValue());
					tCustCallPlanObj.setEffStartDt(DateUtil.getCurrentDate());
	
					tCustCallPlanObj.setPlannedCalls((short) customerAlignment
							.getCustomerCallPlan().getPlannedCalls());
					tCustCallPlanObj.setTenantId(userDetails.getTenantId());
	
					tCustCallPlanObj.setCreatedBy(userDetails.getUserId());
					tCustCallPlanObj.setCreateDt(DateUtil.getCurrentDate());
					tCustCallPlanObj.setActiveFlag(CommonConstants.ACTIVE_Y);
					TCallPlanType tCallPlanType = new TCallPlanType();
					tCallPlanType.setCallPlanTypeId(CommonConstants.TWO);
					tCustCallPlanObj.setTCallPlanType(tCallPlanType);
	
					TCustAlgmnt tCustAlgmnt = tCustAlgmntDAO
							.findTCustAlgmntById(customerAlignment.getId());
					tCustCallPlanObj.setTCustAlgmnt(tCustAlgmnt);
	
					tCustCallPlanDAO.createTCustCallPlan(tCustCallPlanObj);
	
					LOGGER.info("********************Create of Planned Call Plan for Base SP --- ended ******************");
				 }
			 }
			}
		}catch (RuntimeException re) {
			LOGGER.error("********************Issue occured while editing PlannedCalls  ******************  ");
			throw new OpservDataAccessException("This issue occurred while editing planned Calls for the customer", re);

		}

		LOGGER.info("********************Execute of editCustomerCallPlan --- ended  ******************");
	
	}

	}
