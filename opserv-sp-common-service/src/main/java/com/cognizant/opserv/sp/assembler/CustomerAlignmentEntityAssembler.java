package com.cognizant.opserv.sp.assembler;

import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustSalesTeam;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

@Component
public class CustomerAlignmentEntityAssembler {

	/**
	 * Map cust align model to entity.
	 * 
	 * @param customerAlignment
	 *            the customer alignment
	 * @return the t cust algmnt
	 */
	public TCustAlgmnt mapCustAlignModelToEntity(CustomerAlignment customerAlignment) {

		TCustAlgmnt tCustAlgmnt = new TCustAlgmnt();
		TCust tCust = new TCust();

	
		
		if (customerAlignment != null) {

			if(customerAlignment.getStartDate() != null){
			tCustAlgmnt.setEffStartDt(customerAlignment.getStartDate());
			}
			tCustAlgmnt.setCustAlgmntId(customerAlignment.getId());
			if(customerAlignment.getCreatedBy()!= null){
			tCustAlgmnt.setCreatedBy(customerAlignment.getCreatedBy());
			}if(customerAlignment.getCreatedDate() != null){
			tCustAlgmnt.setCreateDt(customerAlignment.getCreatedDate());
			}
			tCustAlgmnt.setTenantId(customerAlignment.getTenantId());
			if (null != customerAlignment.getSalesPosition()) {
				tCustAlgmnt.setSalesPosId(customerAlignment.getSalesPosition()
						.getId());
			}
			if (null != customerAlignment.getSalesPosition()
					&& null != customerAlignment.getSalesPosition()
							.getHierarchy()) {
				tCustAlgmnt.setSalesHierId(customerAlignment.getSalesPosition()
						.getHierarchy().getId());
			}
			if (null != customerAlignment.getSalesPosition()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment()) {
				tCustAlgmnt.setAlgmntId(customerAlignment.getSalesPosition()
						.getAlignmment().getId());
			}
			if (null != customerAlignment.getSalesPosition()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment().getSalesTeam()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment().getSalesTeam().getBusinessUnit()) {
				tCustAlgmnt.setBussUnitId(customerAlignment.getSalesPosition()
						.getAlignmment().getSalesTeam().getBusinessUnit()
						.getId());
			}
			if (null != customerAlignment.getSalesPosition()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment()
					&& null != customerAlignment.getSalesPosition()
							.getAlignmment().getSalesTeam()) {
				tCustAlgmnt.setSalesTeamId(customerAlignment.getSalesPosition()
						.getAlignmment().getSalesTeam().getId());
			}
			if(customerAlignment.getStatus() != null){
				tCustAlgmnt.setStatusId(customerAlignment.getStatus().getId());
			}
			
			if (customerAlignment.isActive()) {
				tCustAlgmnt.setActiveFlag('Y');
			} else {
				tCustAlgmnt.setActiveFlag('N');
			}
			if (customerAlignment.isAffBasedAssignment()) {
				tCustAlgmnt.setAffFlag('Y');
			} else {
				tCustAlgmnt.setAffFlag('N');
			}
			if (customerAlignment.isCustomerTargeted()) {
				tCustAlgmnt.setTargetFlag('Y');
			} else {
				tCustAlgmnt.setTargetFlag('N');
			}
			if (customerAlignment.isGeoAligned()) {
				tCustAlgmnt.setGeoAlgmntFlag('Y');
			} else {
				tCustAlgmnt.setGeoAlgmntFlag('N');
			}
			if (customerAlignment.isImplicitAssignment()) {
				tCustAlgmnt.setImplFlag('Y');
			} else {
				tCustAlgmnt.setImplFlag('N');
			}
			if (customerAlignment.isCompAligned()) {
				tCustAlgmnt.setCompAlgmntFlag('Y');
			} else {
				tCustAlgmnt.setCompAlgmntFlag('N');
			}
			
			if(customerAlignment.getEndDate() != null){
				tCustAlgmnt.setEffEndDt(customerAlignment.getEndDate());
			}
			
			if(customerAlignment.getUpdatedBy() != null){
			tCustAlgmnt.setUpdatedBy(customerAlignment.getUpdatedBy());
			}
			tCustAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
			if (null != customerAlignment.getPriority() && null != customerAlignment.getPriority().getId()) {
				tCustAlgmnt.setPrtTypeId(customerAlignment.getPriority().getId());
			}
			if (customerAlignment.getCustomer() != null) {
				if (customerAlignment.getCustomer().getId() != null) {
					tCust.setCustId(customerAlignment.getCustomer().getId().intValue());
				}
				tCust.setTenantId(customerAlignment.getTenantId());
			}
			tCustAlgmnt.setTCust(tCust);
		}
		return tCustAlgmnt;
	}

	/**
	 * Map customer alignment model to entity.
	 *
	 * @param custSalesTeam the cust sales team
	 * @param customerAlignment the customer alignment
	 * @param attNameMap the att name map
	 * @param userDetails the user details
	 * @return the t cust sales team
	 */
	public TCustSalesTeam mapCustomerAlignmentModelToEntity(TCustSalesTeam custSalesTeam,  CustomerAlignment customerAlignment,Map<String, String> attNameMap , UserDetails userDetails){
		if(customerAlignment!=null){
			
//			custSalesTeam.setEffStartDt(customerAlignment.getStartDate());
//			if(customerAlignment.getEndDate()!=null){
//				custSalesTeam.setEffEndDt(customerAlignment.getEndDate());					
//			}
//			custSalesTeam.setActiveFlag(customerAlignment.isActive()==true?'Y':'N');				
			custSalesTeam.setUpdateDt(DateUtil.getCurrentDate());
			custSalesTeam.setUpdatedBy(userDetails.getUserId());
			
			if(customerAlignment.getExtdAttributes()!=null || !customerAlignment.getExtdAttributes().isEmpty()){
				Iterator entries = attNameMap.entrySet().iterator();
				while (entries.hasNext()) {
				    Map.Entry entry = (Map.Entry) entries.next();
				    String key = (String) entry.getKey();
				    String value = (String) entry.getValue();
				    
				    if(key.equalsIgnoreCase("attr_1")){
				    	custSalesTeam.setAttr1(value);
				    }else if (key.equalsIgnoreCase("attr_2")) {
				    	custSalesTeam.setAttr2(value);
					}else if (key.equalsIgnoreCase("attr_3")) {
						custSalesTeam.setAttr3(value);
					}else if (key.equalsIgnoreCase("attr_4")) {
						custSalesTeam.setAttr4(value);
					}else if (key.equalsIgnoreCase("attr_5")) {
						custSalesTeam.setAttr5(value);
					}else if (key.equalsIgnoreCase("attr_6")) {
						custSalesTeam.setAttr6(value);
					}else if (key.equalsIgnoreCase("attr_7")) {
						custSalesTeam.setAttr7(value);
					}else if (key.equalsIgnoreCase("attr_8")) {
						custSalesTeam.setAttr8(value);
					}else if (key.equalsIgnoreCase("attr_9")) {
						custSalesTeam.setAttr9(value);
					}else if (key.equalsIgnoreCase("attr_10")) {
						custSalesTeam.setAttr10(value);
					}else if (key.equalsIgnoreCase("attr_11")) {
						custSalesTeam.setAttr11(value);
					}else if (key.equalsIgnoreCase("attr_12")) {
						custSalesTeam.setAttr12(value);
					}else if (key.equalsIgnoreCase("attr_13")) {
						custSalesTeam.setAttr13(value);
					}else if (key.equalsIgnoreCase("attr_14")) {
						custSalesTeam.setAttr14(value);
					}else if (key.equalsIgnoreCase("attr_15")) {
						custSalesTeam.setAttr15(value);
					}else if (key.equalsIgnoreCase("attr_16")) {
						custSalesTeam.setAttr16(value);
					}else if (key.equalsIgnoreCase("attr_17")) {
						custSalesTeam.setAttr17(value);
					}else if (key.equalsIgnoreCase("attr_18")) {
						custSalesTeam.setAttr18(value);
					}else if (key.equalsIgnoreCase("attr_19")) {
						custSalesTeam.setAttr19(value);
					}else if (key.equalsIgnoreCase("attr_20")) {
						custSalesTeam.setAttr20(value);
					}
				}
			}
		}
		return custSalesTeam;
	}
	
	

	/**
	 * Map cust align model to entity.
	 * 
	 * @param customerAlignment
	 *            the customer alignment
	 * @return the t cust algmnt
	 */
	public TCustAlgmnt mapCustAlignModelToEntityFrmBaseToMirr(CustomerAlignment customerAlignment, SalesPosition salesPosition, Long custAlId) {

		TCustAlgmnt tCustAlgmnt = new TCustAlgmnt();
		TCust tCust = new TCust();

	
		
		if (customerAlignment != null) {

			if(customerAlignment.getStartDate() != null){
			tCustAlgmnt.setEffStartDt(customerAlignment.getStartDate());
			}
			tCustAlgmnt.setCustAlgmntId(custAlId);
			if(customerAlignment.getCreatedBy()!= null){
			tCustAlgmnt.setCreatedBy(customerAlignment.getCreatedBy());
			}if(customerAlignment.getCreatedDate() != null){
			tCustAlgmnt.setCreateDt(customerAlignment.getCreatedDate());
			}
			tCustAlgmnt.setTenantId(customerAlignment.getTenantId());
			if (null != salesPosition) {
				tCustAlgmnt.setSalesPosId(salesPosition
						.getId());
			}
			if (null != salesPosition
					&& null != salesPosition
							.getHierarchy()) {
				tCustAlgmnt.setSalesHierId(salesPosition
						.getHierarchy().getId());
			}
			if (null != salesPosition
					&& null != salesPosition
							.getAlignmment()) {
				tCustAlgmnt.setAlgmntId(salesPosition
						.getAlignmment().getId());
			}
			if (null != salesPosition
					&& null != salesPosition
							.getAlignmment()
					&& null != salesPosition
							.getAlignmment().getSalesTeam()
					&& null != salesPosition
							.getAlignmment().getSalesTeam().getBusinessUnit()) {
				tCustAlgmnt.setBussUnitId(salesPosition
						.getAlignmment().getSalesTeam().getBusinessUnit()
						.getId());
			}
			if (null != salesPosition
					&& null != salesPosition
							.getAlignmment()
					&& null != salesPosition
							.getAlignmment().getSalesTeam()) {
				tCustAlgmnt.setSalesTeamId(salesPosition
						.getAlignmment().getSalesTeam().getId());
			}
			tCustAlgmnt.setStatusId(ChangeRequestStatusType.APPROVED.getId());
			
			if (customerAlignment.isActive()) {
				tCustAlgmnt.setActiveFlag('Y');
			} else {
				tCustAlgmnt.setActiveFlag('N');
			}
			if (customerAlignment.isAffBasedAssignment()) {
				tCustAlgmnt.setAffFlag('Y');
			} else {
				tCustAlgmnt.setAffFlag('N');
			}
			if (customerAlignment.isCustomerTargeted()) {
				tCustAlgmnt.setTargetFlag('Y');
			} else {
				tCustAlgmnt.setTargetFlag('N');
			}
			if (customerAlignment.isGeoAligned()) {
				tCustAlgmnt.setGeoAlgmntFlag('Y');
			} else {
				tCustAlgmnt.setGeoAlgmntFlag('N');
			}
			if (customerAlignment.isImplicitAssignment()) {
				tCustAlgmnt.setImplFlag('Y');
			} else {
				tCustAlgmnt.setImplFlag('N');
			}
			if (customerAlignment.isCompAligned()) {
				tCustAlgmnt.setCompAlgmntFlag('Y');
			} else {
				tCustAlgmnt.setCompAlgmntFlag('N');
			}
			
			if(customerAlignment.getEndDate() != null){
				tCustAlgmnt.setEffEndDt(customerAlignment.getEndDate());
			}
			
			if(customerAlignment.getUpdatedBy() != null){
			tCustAlgmnt.setUpdatedBy(customerAlignment.getUpdatedBy());
			}
			tCustAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
			if (null != customerAlignment.getPriority() && null != customerAlignment.getPriority().getId()) {
				tCustAlgmnt.setPrtTypeId(customerAlignment.getPriority().getId());
			}
			if (customerAlignment.getCustomer() != null) {
				if (customerAlignment.getCustomer().getId() != null) {
					tCust.setCustId(customerAlignment.getCustomer().getId().intValue());
				}
				tCust.setTenantId(customerAlignment.getTenantId());
			}
			tCustAlgmnt.setTCust(tCust);
		}
		return tCustAlgmnt;
	}
}
