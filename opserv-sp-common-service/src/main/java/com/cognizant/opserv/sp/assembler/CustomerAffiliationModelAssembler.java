package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.AffiliationRelationType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAff;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.SalesTeam;

@Component
public class CustomerAffiliationModelAssembler {
	
	/**
	 * Convert TCustAff list to CustomerAffiliation and populate affiliation hierarchy.
	 *
	 * @param custList the custList
	 * @return the customerAff
	 */
	public CustomerAffiliation populateHierarchy(List<TCustAff> custList){
		CustomerAffiliation customerAff =  new CustomerAffiliation();
		for(TCustAff custAff : custList){
			if(custAff.getTCustByCustId().getCustId().equals(custAff.getRootAffCustId())){
				customerAff.setRoot(true);
				Customer customer = convertTCustToCustomer(custAff.getTCustByCustId());
				customerAff.setCustomer(customer);	
				fetchAffiliationDetails(customerAff, custAff);
				customerAff.setId(null);
				break;
			}
		}
		fetchChildren(customerAff,custList);
		return customerAff;
	}
	
	/**
	 * Convert TCust entity to Customer details.
	 *
	 * @param cust - affiliation
	 * @return customer
	 */
	private Customer convertTCustToCustomer(TCust cust){	
		Customer customer = new Customer();
		customer.setId(cust.getCustId().longValue());
		customer.setName(cust.getCustName());
		customer.setFirstName(cust.getCustFirstName());
		customer.setMiddelName(cust.getCustMiddleName());
		customer.setLastName(cust.getCustLastName());
		CustomerCategoryType category = CustomerCategoryType.getCustomerCategoryType(cust.getCategoryId());
		customer.setCategory(category);			
		return customer;		
	}
	
	/**
	 * Convert TCustAff to Customer Affiliation object.
	 *
	 * @param customerAff - customerAff
	 * @param custAff - custAff
	 * @return void
	 */
	private void fetchAffiliationDetails(CustomerAffiliation customerAff, TCustAff custAff){
		customerAff.setId(custAff.getCustAffId().longValue());
		customerAff.setCreatedBy(custAff.getCreatedBy());
		customerAff.setCreatedDate(custAff.getCreateDt());
		customerAff.setStartDate(custAff.getAffStartDt());
		customerAff.setEndDate(custAff.getAffEndDt());
		customerAff.setTenantId(custAff.getTenantId());
		if(CommonConstants.ACTIVE_Y.equals(custAff.getActiveFlag())){
			customerAff.setActive(CommonConstants.TRUE);
		}else {
			customerAff.setActive(CommonConstants.FALSE);
		}		
		if(null != custAff.getPrAffFlag()){
			if(custAff.getPrAffFlag().equals(CommonConstants.ACTIVE_Y)){
				customerAff.setPrimaryAffiliation(CommonConstants.TRUE);
			}else {
				customerAff.setPrimaryAffiliation(CommonConstants.FALSE);
			}
		}
		Alignment alignment = convertTAlgmntSalesTeamToAlignment(custAff.getTAlgmntSalesTeam());
		customerAff.setAlignment(alignment);
		AffiliationRelationType affRelType = null;
		if(null != custAff.getRelTypeId()){
			affRelType = AffiliationRelationType.getAffiliationRelationType(custAff.getRelTypeId());					
		}
		customerAff.setAffRelationType(affRelType);		
	}
	
	/**
	 * Convert TAlgmntSalesTeam to Alignment details.
	 *
	 * @param tAlgmntSalesTeam
	 * @return alignment
	 */
	private Alignment convertTAlgmntSalesTeamToAlignment(TAlgmntSalesTeam tAlgmntSalesTeam){
		Alignment alignment = null;		
		if(null != tAlgmntSalesTeam){
			alignment = new Alignment();
			Long alignId = tAlgmntSalesTeam.getTAlgmnt().getAlgmntId();
			Long buId = tAlgmntSalesTeam.getTSalesTeam().getTSalesTeamId().getBussUnitId();
			Long stId = tAlgmntSalesTeam.getTSalesTeam().getTSalesTeamId().getSalesTeamId();
			
			BusinessUnit businessUnit= new BusinessUnit();
			businessUnit.setId(buId);
			
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(stId);
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setId(alignId);
			alignment.setSalesTeam(salesTeam);
		}
		return alignment;
	}
	
	/**
	 * Convert TCustAff to Affiliation and populate child affiliations hierarchy.
	 *
	 * @param affiliation - affiliation
	 * @param custList - custList
	 * @return void
	 */
	private void fetchChildren(CustomerAffiliation affiliation,List<TCustAff> custList){
		List<CustomerAffiliation> custAffList = new ArrayList<CustomerAffiliation>();
		for(TCustAff aff : custList){
			if(aff.getTCustByCustId().getCustId().equals(affiliation.getCustomer().getId().intValue())){
				CustomerAffiliation custAff = new CustomerAffiliation();
				Customer cust = convertTCustToCustomer(aff.getTCustByAffCustId());
				custAff.setCustomer(cust);
				fetchAffiliationDetails(custAff, aff);
				fetchChildren(custAff,custList);
				custAffList.add(custAff);
			}
		}
		affiliation.setAffiliatedCustomers(custAffList);
	}
	
	/**
	 * Convert TCustAff list to CustomerAffiliation and 
	 * 				populate account child affiliations hierarchy.
	 *
	 * @param custList - custList
	 * @return the customer affiliation
	 */
	public CustomerAffiliation populateAccountAffHierarchy(List<TCustAff> custList){
		CustomerAffiliation accCustomerAff =  new CustomerAffiliation();
		for(TCustAff custAff : custList){
			if(custAff.getTCustByCustId().getCustId().equals(custAff.getRootAffCustId())){
				accCustomerAff.setRoot(true);
				Customer customer = convertTCustToCustomer(custAff.getTCustByCustId());
				accCustomerAff.setCustomer(customer);	
				fetchAffiliationDetails(accCustomerAff, custAff);
				accCustomerAff.setId(null);
				break;
			}
		}
		fetchAccountChildren(accCustomerAff, custList);		
		return accCustomerAff;
	}
	
	/**
	 * Convert TCust to Affiliation if affiliation is of account type 
	 * @param customerAff
	 * @param custList
	 */
	private void fetchAccountChildren(CustomerAffiliation customerAff, List<TCustAff> custList){
		List<CustomerAffiliation> custAffList = new ArrayList<CustomerAffiliation>();
		for(TCustAff aff : custList){
			if(aff.getRelTypeId() != null && (aff.getRelTypeId().equals(CommonConstants.MULTI_DOC) || aff.getRelTypeId().equals(CommonConstants.NON_MULTI_DOC))){
				if(aff.getTCustByCustId().getCustId().equals(customerAff.getCustomer().getId().intValue())){
					CustomerAffiliation custAff = new CustomerAffiliation();
					Customer cust = convertTCustToCustomer(aff.getTCustByAffCustId());
					custAff.setCustomer(cust);
					fetchAffiliationDetails(custAff, aff);
					fetchAccountChildren(custAff, custList);					
					custAffList.add(custAff);
				}
			}
		}
		customerAff.setAffiliatedCustomers(custAffList);
	}
}
