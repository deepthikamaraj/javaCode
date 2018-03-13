package com.cognizant.opserv.sp.assembler;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.auth.UserDetails;

@Component
public class CustomerEntityAssembler {
	
	/**
	 * Convert customer model to entity.
	 *
	 * @param customer the customer
	 * @return the t cust
	 */
	public TCust convertCustomerModelToEntity(Customer customer){
		
		TCust tCust = new TCust();
		if(customer != null){
			if(customer.isActive()){
				tCust.setActiveFlag(CommonConstants.ACTIVE_Y);
				
			}if(customer.getCategory() != null){
				tCust.setCategoryId(customer.getCategory().getId());
				
			}if(customer.getId() != null){
				tCust.setCustId(customer.getId().intValue());
				
			}if(customer.getName() != null && customer.getName().equals(CommonConstants.EMPTY) ){
				tCust.setCustName(customer.getName());
				
			}if(customer.getCode() != null){
				tCust.setCustCd(customer.getCode());
			}
		}
		
	
		return tCust;
		
	}
	
public TCust mapCustomerModelToEntity(TCust tCust, Customer customer,Map<String, String> attNameMap, UserDetails userDetails){
		
		if(customer!=null && customer.getId()!=null){

			if(customer.getCategory()!=null){
				tCust.setCategoryId(customer.getCategory().getId());
			}
			/*tCust.setActiveFlag(customer.isActive()==true?'Y':'N');
			tCust.setCreatedBy(customer.getCreatedBy());
			tCust.setCreateDt(customer.getCreatedDate());
			tCust.setCustCd(customer.getCode());
			tCust.setTenantId(customer.getTenantId());
			tCust.setCustName(customer.getName());
			tCust.setCustFirstName(customer.getFirstName());
			tCust.setCustMiddleName(customer.getMiddleName());
			tCust.setCustLastName(customer.getLastName());
			tCust.setStateLicId(customer.getStateLicenceId());
			tCust.setDeaId(customer.getDrugEnforceAdminId());
			tCust.setComments(customer.getComments());
			if(customer.getCategory()!=null){
				tCust.setCategoryId(customer.getCategory().getId());	
			}
			tCust.setNamePfx(customer.getNamePrefix());
			tCust.setNameSfx(customer.getNameSuffix());
//			tCust.setPrtTypeId(customer.getPriorityType());			
//			customer.setTypeId(tCust.getCustTypeId());
*/			
			tCust.setUpdateDt(DateUtil.getCurrentDate());
			tCust.setUpdatedBy(userDetails.getUserId());
			if(customer.getExtdAttributes()!=null || !customer.getExtdAttributes().isEmpty()){
				Iterator entries = attNameMap.entrySet().iterator();
				while (entries.hasNext()) {
				    Map.Entry entry = (Map.Entry) entries.next();
				    String key = (String) entry.getKey();
				    String value = (String) entry.getValue();
				    
				    if(key.equalsIgnoreCase("attr_1")){
				    	tCust.setAttr1(value);
				    }else if (key.equalsIgnoreCase("attr_2")) {
				    	tCust.setAttr2(value);
					}else if (key.equalsIgnoreCase("attr_3")) {
				    	tCust.setAttr3(value);
					}else if (key.equalsIgnoreCase("attr_4")) {
				    	tCust.setAttr4(value);
					}else if (key.equalsIgnoreCase("attr_5")) {
				    	tCust.setAttr5(value);
					}else if (key.equalsIgnoreCase("attr_6")) {
				    	tCust.setAttr6(value);
					}else if (key.equalsIgnoreCase("attr_7")) {
				    	tCust.setAttr7(value);
					}else if (key.equalsIgnoreCase("attr_8")) {
				    	tCust.setAttr8(value);
					}else if (key.equalsIgnoreCase("attr_9")) {
				    	tCust.setAttr9(value);
					}else if (key.equalsIgnoreCase("attr_10")) {
				    	tCust.setAttr10(value);
					}else if (key.equalsIgnoreCase("attr_11")) {
				    	tCust.setAttr11(value);
					}else if (key.equalsIgnoreCase("attr_12")) {
				    	tCust.setAttr12(value);
					}else if (key.equalsIgnoreCase("attr_13")) {
				    	tCust.setAttr13(value);
					}else if (key.equalsIgnoreCase("attr_14")) {
				    	tCust.setAttr14(value);
					}else if (key.equalsIgnoreCase("attr_15")) {
				    	tCust.setAttr15(value);
					}else if (key.equalsIgnoreCase("attr_16")) {
				    	tCust.setAttr16(value);
					}else if (key.equalsIgnoreCase("attr_17")) {
				    	tCust.setAttr17(value);
					}else if (key.equalsIgnoreCase("attr_18")) {
				    	tCust.setAttr18(value);
					}else if (key.equalsIgnoreCase("attr_19")) {
				    	tCust.setAttr19(value);
					}else if (key.equalsIgnoreCase("attr_20")) {
				    	tCust.setAttr20(value);
					}else if (key.equalsIgnoreCase("attr_21")) {
				    	tCust.setAttr21(value);
					}else if (key.equalsIgnoreCase("attr_22")) {
				    	tCust.setAttr22(value);
					}else if (key.equalsIgnoreCase("attr_23")) {
				    	tCust.setAttr23(value);
					}else if (key.equalsIgnoreCase("attr_24")) {
				    	tCust.setAttr24(value);
					}else if (key.equalsIgnoreCase("attr_25")) {
				    	tCust.setAttr25(value);
					}else if (key.equalsIgnoreCase("attr_26")) {
				    	tCust.setAttr26(value);
					}else if (key.equalsIgnoreCase("attr_27")) {
				    	tCust.setAttr27(value);
					}else if (key.equalsIgnoreCase("attr_28")) {
				    	tCust.setAttr28(value);
					}else if (key.equalsIgnoreCase("attr_29")) {
				    	tCust.setAttr29(value);
					}else if (key.equalsIgnoreCase("attr_30")) {
				    	tCust.setAttr30(value);
					}else if (key.equalsIgnoreCase("attr_31")) {
				    	tCust.setAttr31(value);
					}else if (key.equalsIgnoreCase("attr_32")) {
				    	tCust.setAttr32(value);
					}else if (key.equalsIgnoreCase("attr_33")) {
				    	tCust.setAttr33(value);
					}else if (key.equalsIgnoreCase("attr_34")) {
				    	tCust.setAttr34(value);
					}else if (key.equalsIgnoreCase("attr_35")) {
				    	tCust.setAttr35(value);
					}else if (key.equalsIgnoreCase("attr_36")) {
				    	tCust.setAttr36(value);
					}else if (key.equalsIgnoreCase("attr_37")) {
				    	tCust.setAttr37(value);
					}else if (key.equalsIgnoreCase("attr_38")) {
				    	tCust.setAttr38(value);
					}else if (key.equalsIgnoreCase("attr_39")) {
				    	tCust.setAttr39(value);
					}else if (key.equalsIgnoreCase("attr_40")) {
				    	tCust.setAttr40(value);
					}else if (key.equalsIgnoreCase("attr_41")) {
				    	tCust.setAttr41(value);
					}else if (key.equalsIgnoreCase("attr_42")) {
				    	tCust.setAttr42(value);
					}else if (key.equalsIgnoreCase("attr_43")) {
				    	tCust.setAttr43(value);
					}else if (key.equalsIgnoreCase("attr_44")) {
				    	tCust.setAttr44(value);
					}else if (key.equalsIgnoreCase("attr_45")) {
				    	tCust.setAttr45(value);
					}else if (key.equalsIgnoreCase("attr_46")) {
				    	tCust.setAttr46(value);
					}else if (key.equalsIgnoreCase("attr_47")) {
				    	tCust.setAttr47(value);
					}else if (key.equalsIgnoreCase("attr_48")) {
				    	tCust.setAttr48(value);
					}else if (key.equalsIgnoreCase("attr_49")) {
				    	tCust.setAttr49(value);
					}else if (key.equalsIgnoreCase("attr_50")) {
				    	tCust.setAttr50(value);
					}
				}
			}
		}
		return tCust;
	}

}
