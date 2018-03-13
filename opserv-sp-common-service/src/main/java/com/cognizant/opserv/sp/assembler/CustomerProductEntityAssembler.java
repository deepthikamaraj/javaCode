package com.cognizant.opserv.sp.assembler;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;


@Component
public class CustomerProductEntityAssembler {
	


	
	/**
	 * Map customer alignment model to entity.
	 *
	 * @param custSalesTeam the cust sales team
	 * @param customerAlignment the customer alignment
	 * @param attNameMap the att name map
	 * @param userDetails the user details
	 * @return the t cust sales team
	 */
	public  TCustPrdSalesTeam mapCustomerProductModelToEntity(TCustPrdSalesTeam tCustPrdSalesTeam,  CustomerProductAlignment customerProductAlignment,Map<String, String> attNameMap , UserDetails userDetails){
		if(customerProductAlignment!=null){
			
			tCustPrdSalesTeam.setUpdateDt(DateUtil.getCurrentDate());
			tCustPrdSalesTeam.setUpdatedBy(userDetails.getUserId());
			tCustPrdSalesTeam.setCompElgFlag(customerProductAlignment != null && customerProductAlignment.isCompAvailable() == true ? CommonConstants.ACTIVE_Y
							: CommonConstants.ACTIVE_N);
			
			//Update for Ext Attr
			
			if(customerProductAlignment.getExtdAttributes()!=null || !customerProductAlignment.getExtdAttributes().isEmpty()){
				Iterator entries = attNameMap.entrySet().iterator();
				while (entries.hasNext()) {
				    Map.Entry entry = (Map.Entry) entries.next();
				    String key = (String) entry.getKey();
				    String value = (String) entry.getValue();
				    
				    if(key.equalsIgnoreCase("attr_1")){
				    	tCustPrdSalesTeam.setAttr1(value);
				    }else if (key.equalsIgnoreCase("attr_2")) {
				    	tCustPrdSalesTeam.setAttr2(value);
					}else if (key.equalsIgnoreCase("attr_3")) {
						tCustPrdSalesTeam.setAttr3(value);
					}else if (key.equalsIgnoreCase("attr_4")) {
						tCustPrdSalesTeam.setAttr4(value);
					}else if (key.equalsIgnoreCase("attr_5")) {
						tCustPrdSalesTeam.setAttr5(value);
					}else if (key.equalsIgnoreCase("attr_6")) {
						tCustPrdSalesTeam.setAttr6(value);
					}else if (key.equalsIgnoreCase("attr_7")) {
						tCustPrdSalesTeam.setAttr7(value);
					}else if (key.equalsIgnoreCase("attr_8")) {
						tCustPrdSalesTeam.setAttr8(value);
					}else if (key.equalsIgnoreCase("attr_9")) {
						tCustPrdSalesTeam.setAttr9(value);
					}else if (key.equalsIgnoreCase("attr_10")) {
						tCustPrdSalesTeam.setAttr10(value);
					}else if (key.equalsIgnoreCase("attr_11")) {
						tCustPrdSalesTeam.setAttr11(value);
					}else if (key.equalsIgnoreCase("attr_12")) {
						tCustPrdSalesTeam.setAttr12(value);
					}else if (key.equalsIgnoreCase("attr_13")) {
						tCustPrdSalesTeam.setAttr13(value);
					}else if (key.equalsIgnoreCase("attr_14")) {
						tCustPrdSalesTeam.setAttr14(value);
					}else if (key.equalsIgnoreCase("attr_15")) {
						tCustPrdSalesTeam.setAttr15(value);
					}else if (key.equalsIgnoreCase("attr_16")) {
						tCustPrdSalesTeam.setAttr16(value);
					}else if (key.equalsIgnoreCase("attr_17")) {
						tCustPrdSalesTeam.setAttr17(value);
					}else if (key.equalsIgnoreCase("attr_18")) {
						tCustPrdSalesTeam.setAttr18(value);
					}else if (key.equalsIgnoreCase("attr_19")) {
						tCustPrdSalesTeam.setAttr19(value);
					}else if (key.equalsIgnoreCase("attr_20")) {
						tCustPrdSalesTeam.setAttr20(value);
					}
				}
			}
		}
		return tCustPrdSalesTeam;
	}

}
