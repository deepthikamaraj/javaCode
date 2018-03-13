package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CallPlanType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.model.CallPlanItem;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerCallPlan;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.SalesPosition;

/**
 * ***************************************************************************.
 *
 * @class CustomerCallPlanModelAssembler - Mapper class for Customer call plan
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/04/2016
 * ***************************************************************************
 */
@Component
public class CustomerCallPlanModelAssembler {

	/**
	 * Map json to model.
	 *
	 * @param callPlanJSON the call plan json
	 * @return the customer call plan
	 */
	public  List<CustomerCallPlan> mapJSONToModel(com.cognizant.opserv.sp.json.SPTargetedCallPlan spCallPlan){
		List<CustomerCallPlan> customerCallPlans = new ArrayList<CustomerCallPlan>();
		
		if (null != spCallPlan && null != spCallPlan.getCustomerCallPlans()) {
			List<com.cognizant.opserv.sp.json.CustomerCallPlan> callPlanJSONs = spCallPlan.getCustomerCallPlans();
			SalesPosition salesPosition=new SalesPosition();
			if (null != spCallPlan.getSalesposId()) {
				salesPosition.setId(spCallPlan.getSalesposId());
			}

			for(com.cognizant.opserv.sp.json.CustomerCallPlan callPlanJSON : callPlanJSONs){
				CustomerCallPlan customerCallPlan = new CustomerCallPlan();
				//customerCallPlan.setCallPlanItems(mapToCallPlanItemList(callPlanJSON.getCallPlanItems()));
				
				CustomerAlignment customerAlignment = new CustomerAlignment();
				
				customerAlignment.setSalesPosition(salesPosition);
				Customer customer =new Customer();
				if (null != callPlanJSON.getCustomerId()){
					customer.setId(callPlanJSON.getCustomerId());
				}
				customerAlignment.setCustomer(customer);
				if (null != callPlanJSON.getCustAlgmntId()){
					customerAlignment.setId(callPlanJSON.getCustAlgmntId());
				}
				//customerCallPlan.setCustomerAlgn(customerAlignment);
				if (callPlanJSON.getPlannedCalls() < 0){
					customerCallPlan.setPlannedCalls(callPlanJSON.getPlannedCalls());
				}
				if (null != callPlanJSON.getCallPlanType()){
					if(callPlanJSON.getCallPlanType().equalsIgnoreCase(CallPlanType.BASE.name())){
						customerCallPlan.setType(CallPlanType.BASE);
					} else if (callPlanJSON.getCallPlanType().equalsIgnoreCase(CallPlanType.PLANNED.name())) {
						customerCallPlan.setType(CallPlanType.PLANNED);
					}
				}
			
			customerCallPlans.add(customerCallPlan);
			}
		}
		
		return customerCallPlans;
		
	}
	
	/**
	 * Map to call plan item list.
	 *
	 * @param callPlanJSONItems the call plan json items
	 * @return List<CallPlanItem>
	 */
	public List<CallPlanItem> mapToCallPlanItemList( List<com.cognizant.opserv.sp.json.CallPlanItem> callPlanJSONItems){
		
		List<CallPlanItem> callPlanItems = new ArrayList<CallPlanItem>();
		if(null != callPlanJSONItems && !callPlanJSONItems.isEmpty()){
			for (com.cognizant.opserv.sp.json.CallPlanItem callPlanJSONItem : callPlanJSONItems) {
				CallPlanItem callPlanItem = new CallPlanItem();
				Product product = new Product();
				if (null != callPlanJSONItem.getProdId()){
					product.setId(callPlanJSONItem.getProdId());
				}
				callPlanItem.setProduct(product);
				if (callPlanJSONItem.getPlannedCalls() < 0){
					callPlanItem.setPlannedCalls(callPlanJSONItem.getPlannedCalls());
				}
			}
		}
		return callPlanItems;
	}
	
	/**
	 * Convert customer call plan details to model.
	 * 
	 * @param custCallPlanInfo
	 *            the cust call plan info
	 * @return the customer call plan
	 */

	public List<CustomerCallPlan> convertDirBasedCustomerCallPlanDetailsToModel(List<TCustCallPlan> custCallPlanInfo) {
		
		List<CustomerCallPlan> customerCallPlan = new ArrayList<CustomerCallPlan>();
		if (custCallPlanInfo != null && (!custCallPlanInfo.isEmpty())) {
			for (TCustCallPlan tCustCallPlan : custCallPlanInfo) {
				CustomerCallPlan customerCallPlan1 = new CustomerCallPlan();
				customerCallPlan1.setPlannedCalls(tCustCallPlan.getPlannedCalls());
				short id = tCustCallPlan.getTCallPlanType().getCallPlanTypeId();
				CallPlanType callPlanType = CallPlanType.getAttributeType((int) id);
				customerCallPlan1.setType(callPlanType);

				Integer statusId = (Integer) tCustCallPlan.getStatusId();
				ChangeRequestStatusType changeRequestStatusType = ChangeRequestStatusType.getChangeRequestStatusType(statusId);
				customerCallPlan1.setChangeRequestStatusType(changeRequestStatusType);
				customerCallPlan1.setId(tCustCallPlan.getCustCallPlanId().longValue());
		
				customerCallPlan.add(customerCallPlan1);

			}
		}
		return customerCallPlan;

}

	/**
	 * Convert created data to model.
	 * 
	 * @param tCallDirPrdss2
	 * @param tCallDir2
	 * 
	 * @param tCustCallPlanObjNew
	 *            the t cust call plan obj new
	 * @param tCallDir
	 *            the t call dir
	 * @param tCallDirPrdss
	 *            the t call dir prdss
	 * @param callPlanConfig
	 * @return the customer call plan
	 */
public CustomerCallPlan convertSaveUpdateDirBasedCustomerCallPlanToModel(TCustCallPlan custCallPlanInfo) {
		
		
		CustomerCallPlan customerCallPlan=new CustomerCallPlan();
		if(custCallPlanInfo != null){
	      
			customerCallPlan.setPlannedCalls(custCallPlanInfo.getPlannedCalls());
			short id = custCallPlanInfo.getTCallPlanType().getCallPlanTypeId();
			CallPlanType callPlanType = CallPlanType.getAttributeType((int) id);
			customerCallPlan.setType(callPlanType);
			
			Integer statusId = (Integer) custCallPlanInfo.getStatusId();
			ChangeRequestStatusType changeRequestStatusType=ChangeRequestStatusType.getChangeRequestStatusType(statusId);
			customerCallPlan.setChangeRequestStatusType(changeRequestStatusType);
		   if(custCallPlanInfo.getCustCallPlanId() != null){
			customerCallPlan.setId(custCallPlanInfo.getCustCallPlanId().longValue());
			}
		
	       }
		return customerCallPlan;
	}


/**
 * Convert customer call plan details to model.
 * 
 * @param custCallPlanInfo
 *            the cust call plan info
 * @return the customer call plan
 */

public Map<Long, List<CustomerCallPlan>> convertBasedCPDataToModel(List<TCustCallPlan> custCallPlans) {
	
	List<CustomerCallPlan> customerCallPlan = null;
	Map<Long, List<CustomerCallPlan>> custAlCPMap = null;
	
	if (custCallPlans != null && (!custCallPlans.isEmpty())) {
		custAlCPMap = new HashMap<Long, List<CustomerCallPlan>>();
		
		for (TCustCallPlan tCustCallPlan : custCallPlans) {
			CustomerCallPlan customerCallPlan1 = new CustomerCallPlan();
			customerCallPlan1.setPlannedCalls(tCustCallPlan.getPlannedCalls());
			short id = tCustCallPlan.getTCallPlanType().getCallPlanTypeId();
			CallPlanType callPlanType = CallPlanType.getAttributeType((int) id);
			customerCallPlan1.setType(callPlanType);

			Integer statusId = (Integer) tCustCallPlan.getStatusId();
			ChangeRequestStatusType changeRequestStatusType = ChangeRequestStatusType.getChangeRequestStatusType(statusId);
			customerCallPlan1.setChangeRequestStatusType(changeRequestStatusType);
			customerCallPlan1.setId(tCustCallPlan.getCustCallPlanId().longValue());
			
			if(!custAlCPMap.containsKey(tCustCallPlan.getTCustAlgmnt().getCustAlgmntId())){
				customerCallPlan = new ArrayList<CustomerCallPlan>();
				customerCallPlan.add(customerCallPlan1);
				custAlCPMap.put(tCustCallPlan.getTCustAlgmnt().getCustAlgmntId(), customerCallPlan);
			} else {
				List<CustomerCallPlan> callPlans = custAlCPMap.get(tCustCallPlan.getTCustAlgmnt().getCustAlgmntId());
				callPlans.add(customerCallPlan1);
				custAlCPMap.put(tCustCallPlan.getTCustAlgmnt().getCustAlgmntId(), callPlans);
			}
			
		}
	}
	return custAlCPMap;
}
}
