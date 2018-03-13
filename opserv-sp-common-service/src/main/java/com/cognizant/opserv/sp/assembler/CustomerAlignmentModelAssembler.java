package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.common.PriorityType;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.json.ExtendedAttribute;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Contact;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.CustomerAffiliation;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Component
public class CustomerAlignmentModelAssembler {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CustomerAlignmentModelAssembler.class);
	
	
	@Autowired
	private SalesPositionModelAssembler salesPositionModelAssembler;
	
	@Autowired
	private CustomerModelAssembler customerModelAssembler;
	/**
	 * Convert t cust aligments to model.
	 *
	 * @param tCustAlgmnt the t cust algmnt
	 * @return the list
	 */
	public List<CustomerAlignment> convertTCustAligmentsToModel(
			List<TCustAlgmnt> tCustAlgmnt, Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes,Map<Integer, List<ExtdAttribue>> custExtdAttributes) {

		List<CustomerAlignment> custAlList = null;
		if (null != tCustAlgmnt && !tCustAlgmnt.isEmpty()) {
			custAlList = new ArrayList<CustomerAlignment>();
			for (TCustAlgmnt custAlgmnt : tCustAlgmnt) {
				
				CustomerAlignment customerAlignment = convertTCustAlToModel(custAlgmnt,custAlgnmtExtdAttributes,custExtdAttributes);

				custAlList.add(customerAlignment);
			}
		}
		return custAlList;
	}
	
	/**
	 * Convert entity obj to modelfrcust.
	 *
	 * @param custAlList the cust al list
	 * @param custAlgnmtExtdAttribues 
	 * @param tierList the tier list
	 * @return the list
	 */
	public List<CustomerAlignment> convertEntityObjToModelfrCust(List<Object[]> custAlList,SalesPosition salesPosition,Map<Integer,List<ExtdAttribue>> custExtdAttribues, Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttribues){
		List<CustomerAlignment> customerVOList = new ArrayList<CustomerAlignment>();
		
		
		if (custAlList != null && !custAlList.isEmpty()) {
			
			for (Object[] obj : custAlList) {

				CustomerAlignment customerAlignment = new CustomerAlignment();
				Customer customer = new Customer();
				String concatCustName = "";
				if (obj[0] != null
						&& !obj[0].toString().trim().equals(CommonConstants.EMPTY)
						&& !obj[0].toString().equals(CommonConstants.NUL)
						&& !obj[0].toString().equals(
								CommonConstants.NA)) { 
					customer.setName(obj[0].toString().toUpperCase()
							.trim());
				} else {
					if (obj[10] != null
							&& !obj[10].toString().trim().equals(CommonConstants.EMPTY)
							&& !obj[10].toString().equals(CommonConstants.NUL)
							&& !obj[10].toString().equals(
									CommonConstants.NA)) {
						concatCustName = obj[10].toString().toUpperCase()
								.trim();
					}
					if (obj[11] != null
							&& !obj[11].toString().trim().equals(CommonConstants.EMPTY)
							&& !obj[11].toString().equals(CommonConstants.NUL)
							&& !obj[11].toString().equals(
									CommonConstants.NA)) {
						concatCustName = concatCustName + " "
								+ obj[11].toString().toUpperCase().trim();
					}
					if (obj[12] != null
							&& !obj[12].toString().trim().equals(CommonConstants.EMPTY)
							&& !obj[12].toString().equals(CommonConstants.NUL)
							&& !obj[12].toString().equals(
									CommonConstants.NA)) {
						concatCustName = concatCustName + " "
								+ obj[12].toString().toUpperCase().trim();
					}
					
					customer.setName(concatCustName);
				}
				
				Integer custId= (Integer) obj[1];
				if(null != custId){
					customer.setId(Long.valueOf(custId));
				}
				
				if(obj[2] !=null && !obj[2].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setCode(obj[2].toString());
				}
				
				
				
				if (obj[3] != null && !obj[3].toString().trim().equals(CommonConstants.EMPTY)) {
					customer.setTypeId((Integer) obj[3]);
				} 

				if (obj[4] != null) {
					customer.setPriorityType((Integer)obj[4]);
					PriorityType priorityType = PriorityType.getPriorityType((Integer) obj[4]);
					customerAlignment.setPriority(priorityType);
				}
				
				if(obj[5] != null){
					customerAlignment.setId((Long) obj[5]);
				}
				if (obj[6] != null && !obj[6].toString().trim().equals(CommonConstants.EMPTY)) {
					customerAlignment.setStartDate((Date) obj[6]);
				}
				
				if (obj[7] != null && !obj[7].toString().trim().equals(CommonConstants.EMPTY)) {
					customerAlignment.setEndDate((Date) obj[7]);
				} 
				
				if(obj[8] != null){
					if(((Character) obj[8]).equals(CommonConstants.ACTIVE_Y)){
						customerAlignment.setCustomerTargeted(true);
					} else {
						customerAlignment.setCustomerTargeted(false);
					}
				}
				
			
				if(obj[15] != null){
					Character impFlag = (Character)obj[15];
					Boolean isImplicitAssignment = false;
					if(impFlag.equals(CommonConstants.ACTIVE_Y)){
						isImplicitAssignment = true;
					}
					customerAlignment.setImplicitAssignment(isImplicitAssignment);
					
					if(impFlag.equals(CommonConstants.ACTIVE_Y)){
						if(obj[16] != null){
							Boolean isAffBasedAssignment = false;
							Character affFlag = (Character)obj[16];
							if(affFlag.equals(CommonConstants.ACTIVE_Y)){
								isAffBasedAssignment = true;
							}
							customerAlignment.setAffBasedAssignment(isAffBasedAssignment);
						}
					}
				}
				
				if(obj[19] !=null && !obj[19].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setStateLicenceId(obj[19].toString());
				}
				if(obj[20]!= null && !obj[20].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setDrugEnforceAdminId(obj[20].toString());
				}
				if(obj[21]!=null && !obj[21].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setComments(obj[21].toString());
				}
				if(obj[22]!=null && !obj[22].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setNamePrefix(obj[22].toString());
				}
				if(obj[23]!=null && !obj[23].toString().trim().equals(CommonConstants.EMPTY)){
					customer.setNameSuffix(obj[23].toString());
				}
				
				if(custExtdAttribues != null && !custExtdAttribues.isEmpty()){
					if(custExtdAttribues.get(obj[1]) != null && !custExtdAttribues.get(obj[1]).isEmpty()){
						customer.setExtdAttributes(custExtdAttribues.get(obj[1]));
					}
				}
				
				if (obj[24] != null) {
					customerAlignment.setStatus(ChangeRequestStatusType.getChangeRequestStatusType((Integer) obj[24]));
				}
				if(custAlgnmtExtdAttribues.get(obj[1]) != null && !custAlgnmtExtdAttribues.get(obj[1]).isEmpty()){
					customerAlignment.setExtdAttributes(custAlgnmtExtdAttribues.get(obj[1]));
				}
				customerAlignment.setSalesPosition(salesPosition);
				customerAlignment.setCustomer(customer);
				customerVOList.add(customerAlignment);
			}
		}
		return customerVOList;
	}
	/**
	 * Convert entity obj to modelfrcust addr.
	 *
	 * @param custAdrList the cust adr list
	 * @return the map
	 */
	public Map<Integer, Address> convertEntityObjToModelfrCustAddr(
			List<Object[]> custAdrList) {
		Map<Integer, Address> custIdAdrrMap = null;
		if (custAdrList != null && !custAdrList.isEmpty()) {
			custIdAdrrMap = new HashMap<Integer, Address>();
			for (Object[] object : custAdrList) {

				Address address = new Address();
				if (object[2] != null && !object[2].equals(CommonConstants.EMPTY)) {
					address.setStreetName(object[2].toString());
				}
				if (object[3] != null && !object[3].equals(CommonConstants.EMPTY)) {

					address.setCity(object[3].toString());
				}

				if (object[4] != null && !object[4].equals(CommonConstants.EMPTY)) {
					address.setState(object[4].toString());
				}

				if (object[5] != null && !object[5].equals(CommonConstants.EMPTY)) {
					address.setCountry(object[5].toString());
				}

				if (object[6] != null && !object[6].equals(CommonConstants.EMPTY)) {
					address.setPostalCode(object[6].toString());
				}

				custIdAdrrMap.put((Integer) object[0], address);
			}
		}
		return custIdAdrrMap;
	}
	
	/**
	 * @param jsonList
	 * @param salesPosition
	 * @param alignment
	 * @param mapOfCustAttrDefn
	 * @param mapOfAlgmntAttrDefn
	 * @return
	 */
	public List<CustomerAlignment> assembleJSONToModel(List<com.cognizant.opserv.sp.json.CustomerAlignment> jsonList,
			SalesPosition salesPosition, Alignment alignment, Map<String, AttributeDefinition> mapOfCustAttrDefn, Map<String, AttributeDefinition> mapOfAlgmntAttrDefn){
		
		salesPosition.setAlignmment(alignment);
		List<CustomerAlignment> customerAlignments = new ArrayList<CustomerAlignment>();
		if(null != jsonList && !jsonList.isEmpty()){
			for (com.cognizant.opserv.sp.json.CustomerAlignment jsonObject : jsonList) {
				
				if (null != jsonObject.getCustAlignId() && null != jsonObject.getCustId()){
					CustomerAlignment customerAlignment = new CustomerAlignment();
					customerAlignment.setId(jsonObject.getCustAlignId());
					customerAlignment.setSalesPosition(salesPosition);
					
					customerAlignment.setAffBasedAssignment(jsonObject.isAffBasedAssignment());
			        customerAlignment.setImplicitAssignment(jsonObject.isImplicitAssignment());
			        customerAlignment.setCustomerTargeted(jsonObject.isCustomerTargeted());
			        customerAlignment.setGeoAligned(jsonObject.isGeoAligned());
			        customerAlignment.setCompAligned(jsonObject.isCompAligned());
			        if(null != jsonObject.getPriorityType()){
			        	 customerAlignment.setPriority(PriorityType.getPriorityType(Integer.valueOf(jsonObject.getPriorityType())));
			        }
			        if (null != jsonObject.getEffectiveEndDate()){
			        	customerAlignment.setEndDate(jsonObject.getEffectiveEndDate());
			        }
			        if(null != jsonObject.getEffectiveStartDate()){
			        	customerAlignment.setStartDate(jsonObject.getEffectiveStartDate());
			        }
			        if(jsonObject.getStatusId() != null) {
			        	customerAlignment.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(jsonObject.getStatusId()));
			        }
			        
			        
			        
					
					Customer customer = new Customer();
					customer.setId(jsonObject.getCustId());
		        	
			        customer.setCode(jsonObject.getCustCode() == null ? "" : jsonObject.getCustCode());
			        if(null != jsonObject.getCustomerType()){
			        	customer.setTypeId(Integer.parseInt(jsonObject.getCustomerType()));
			        }
			        if(null != jsonObject.getCustCategoryType()){
			        	if(jsonObject.getCustCategoryType().equalsIgnoreCase(CustomerCategoryType.ACCOUNT.name())){
				               customer.setCategory(CustomerCategoryType.ACCOUNT);
				        }
				        if(jsonObject.getCustCategoryType().equalsIgnoreCase(CustomerCategoryType.PRESCRIBER.name())){
				               customer.setCategory(CustomerCategoryType.PRESCRIBER);
				        }
			        }
			        customer.setComments(jsonObject.getComments() == null ? "" : jsonObject.getComments());
			        customer.setDrugEnforceAdminId(jsonObject.getDrugEnforceAdminId() == null ? "" : jsonObject.getDrugEnforceAdminId());
			        if (null != jsonObject.getEffectiveEndDate()){
			        	 customer.setEndDate(jsonObject.getEffectiveEndDate());
			        }
			        customer.setFirstName(jsonObject.getFirstName() == null ? "" : jsonObject.getFirstName());
			        customer.setMiddelName(jsonObject.getMiddleName() == null ? "" : jsonObject.getMiddleName());
			        customer.setLastName(jsonObject.getLastName() == null ? "" : jsonObject.getLastName());
			        customer.setName(jsonObject.getCustomerName() == null ? "" : jsonObject.getCustomerName());
			        customer.setNamePrefix(jsonObject.getNamePrefix() == null ? "" : jsonObject.getNamePrefix());
			        customer.setNameSuffix(jsonObject.getNameSuffix() == null ? "" : jsonObject.getNameSuffix());
			        customer.setPrefix(jsonObject.getNamePrefix() == null ? "" : jsonObject.getNamePrefix());
			        
			        Address addresses = new Address();
			        addresses.setAddressLine(jsonObject.getAddressLine1() == null ? "" : jsonObject.getAddressLine1());
			        addresses.setAddressLine2(jsonObject.getAddressLine2() == null ? "" : jsonObject.getAddressLine2());
			        addresses.setAddressLine3(jsonObject.getAddressLine3() == null ? "" : jsonObject.getAddressLine3());
			        addresses.setAddressLine4(jsonObject.getAddressLine4() == null ? "" : jsonObject.getAddressLine4());
			        
			        
			        addresses.setCity(jsonObject.getCity() == null ? "" : jsonObject.getCity());
			        addresses.setState(jsonObject.getState() == null ? "" : jsonObject.getState());
			        addresses.setCountry(jsonObject.getCountry() == null ? "" : jsonObject.getCountry());
			        addresses.setPostalCode(jsonObject.getPostalCode() == null ? "" : jsonObject.getPostalCode());
			        addresses.setPrimaryAddr(true);
			        customer.setPrimaryAddress(addresses);
			        
			        Contact primaryContact = new Contact();
			        primaryContact.setEmail(jsonObject.getEmail() == null ? "" : decryptor(jsonObject.getEmail()));
			        primaryContact.setFax(jsonObject.getFax() == null ? "" : decryptor(jsonObject.getFax()));
			        primaryContact.setPhone(jsonObject.getPhone() == null ? "" : decryptor(jsonObject.getPhone()));
			        primaryContact.setMobile(jsonObject.getMobile() == null ? "" : decryptor(jsonObject.getMobile()));
			        customer.setPrimaryContact(primaryContact);
			        
			        customer.setStateLicenceId(jsonObject.getStateLicenceId() == null ? "" : jsonObject.getStateLicenceId());
			        
			        
			        if(null!=jsonObject.getPriorityType()){
			        	customer.setPriorityType(Integer.parseInt(jsonObject.getPriorityType()));
			        }
			        if (null != jsonObject.getEffectiveStartDate()){
			        	customer.setStartDate(jsonObject.getEffectiveStartDate());
			        }
			        
			        /**create extnd attr list of customer 1-50 -- start**/
			        if (null != jsonObject.getCustExtAttrs() && !jsonObject.getCustExtAttrs().isEmpty()){
			        	List<ExtdAttribue> custExtnAttr = new ArrayList<ExtdAttribue>();
			        	for (ExtendedAttribute extnAttr : jsonObject.getCustExtAttrs()){
				        	if (null != extnAttr){
					        	AttributeDefinition attributeDefinition = mapOfCustAttrDefn.get(extnAttr.getName());
					        	if(null != attributeDefinition){
					        		ExtdAttribue extdAttribue = new ExtdAttribue();
						        	extdAttribue.setName(extnAttr.getName());
						        	extdAttribue.setValue(extnAttr.getValue());
						        	extdAttribue.setDefinition(attributeDefinition);
						        	custExtnAttr.add(extdAttribue);
					        	}
				        	}
				        }
			        	customer.setExtdAttributes(custExtnAttr);
			        }
			        /**create extnd attr list of customer 1-50 -- end**/
			        
			        customerAlignment.setCustomer(customer);
			        
			        
			        
			        /**create extnd algnmnt attr list of customer sales team 1-20 -- start**/
			        List<ExtdAttribue> custAlgmntExtnAttr = new ArrayList<ExtdAttribue>();
			        if (null != jsonObject.getCustAlignExtAttrs()){
				        for (ExtendedAttribute extnAttr : jsonObject.getCustAlignExtAttrs()){
				        	if (null != extnAttr){
				        		ExtdAttribue extdAttribue = new ExtdAttribue();
					        	extdAttribue.setName(extnAttr.getName());
					        	extdAttribue.setValue(extnAttr.getValue());
					        	AttributeDefinition attributeDefinition = mapOfAlgmntAttrDefn.get(extnAttr.getName());
					        	extdAttribue.setDefinition(attributeDefinition);
					        	custAlgmntExtnAttr.add(extdAttribue);
				        	}
				        }
			        }
			        customerAlignment.setExtdAttributes(custAlgmntExtnAttr);
			        /**create extnd algnmnt attr list of customer sales team 1-20 -- end**/
			        
			        customerAlignments.add(customerAlignment);
		        }
			}
		}
        return customerAlignments;
	}
	
	/**
	 * Decryptor.
	 *
	 * @param dobJsonEncrypted the dob json encrypted
	 * @return the string
	 */
	private String decryptor(String dobJsonEncrypted) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("password");                     
        encryptor.setAlgorithm("PBEWithMD5AndDES");
		return encryptor.decrypt(dobJsonEncrypted);
	}
	
	/**
	 * @param tCustAlgmnt
	 * @return CustomerAlignment
	 */
	public CustomerAlignment convertTCustAlToModel(
			TCustAlgmnt tCustAlgmnt, boolean populateChildEntities) {
		CustomerAlignment customerAlignment = new CustomerAlignment();
		customerAlignment.setId(tCustAlgmnt.getCustAlgmntId());
		customerAlignment.setActive(tCustAlgmnt.getActiveFlag() != null
				&& tCustAlgmnt.getActiveFlag().equals(
						CommonConstants.ACTIVE_Y) ? true : false);	
		
		Customer customerModel = null;
		if(populateChildEntities){
			customerModel = customerModelAssembler.convertTCustToModelWithChildEntities(tCustAlgmnt.getTCust());
		}else{
			customerModel = customerModelAssembler.convertTCustToModelWithBasicDetails(tCustAlgmnt.getTCust());
		}
		
		customerAlignment.setCustomer(customerModel);		
		customerAlignment.setStartDate(tCustAlgmnt.getEffStartDt());
		customerAlignment.setEndDate(tCustAlgmnt.getEffEndDt());
		
		if(tCustAlgmnt.gettSalesPos() != null){
            SalesPosition salesPosition = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(tCustAlgmnt.gettSalesPos(), tCustAlgmnt.getTenantId()); 
            customerAlignment.setSalesPosition(salesPosition);
		}else{
            SalesPosition salesPosition = new SalesPosition();
            salesPosition.setId(tCustAlgmnt.getSalesPosId());
            SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
            salesOrgHierarchy.setId(tCustAlgmnt.getSalesHierId());
            salesPosition.setHierarchy(salesOrgHierarchy);
            Alignment alignment = new Alignment();
            alignment.setId(tCustAlgmnt.getAlgmntId());
            SalesTeam salesTeam = new SalesTeam();
            BusinessUnit businessUnit = new BusinessUnit();
            businessUnit.setId(tCustAlgmnt.getBussUnitId());
            salesTeam.setBusinessUnit(businessUnit);
            salesTeam.setId(tCustAlgmnt.getSalesTeamId());
            alignment.setSalesTeam(salesTeam);
            salesPosition.setAlignmment(alignment);
            customerAlignment.setSalesPosition(salesPosition);
		}

		customerAlignment.setCreatedBy(tCustAlgmnt.getCreatedBy());
		customerAlignment.setCreatedDate(tCustAlgmnt.getCreateDt());
		customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
		customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
		customerAlignment.setUpdatedBy(tCustAlgmnt.getUpdatedBy());
		customerAlignment.setUpdatedDate(tCustAlgmnt.getUpdateDt());
		customerAlignment
				.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
						&& tCustAlgmnt.getTargetFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment
				.setAffBasedAssignment(tCustAlgmnt.getAffFlag() != null
						&& tCustAlgmnt.getAffFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		if (tCustAlgmnt.getPrtTypeId() != null) {
			customerAlignment.setPriority(PriorityType
					.getPriorityType(tCustAlgmnt.getPrtTypeId()));
		}
		customerAlignment
				.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
						&& tCustAlgmnt.getTargetFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment
				.setImplicitAssignment(tCustAlgmnt.getImplFlag() != null
						&& tCustAlgmnt.getImplFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment.setGeoAligned(tCustAlgmnt.getGeoAlgmntFlag() != null
				&& tCustAlgmnt.getGeoAlgmntFlag().equals(
						CommonConstants.ACTIVE_Y) ? true : false);
		
		customerAlignment.setCompAligned(tCustAlgmnt.getCompAlgmntFlag() != null && tCustAlgmnt.getCompAlgmntFlag().equals(CommonConstants.ACTIVE_Y) ? true : false);
		
		if (tCustAlgmnt.getStatusId() != null) {
			customerAlignment.setStatus(ChangeRequestStatusType
					.getChangeRequestStatusType(tCustAlgmnt.getStatusId()));
		}

		return customerAlignment;
	}
	
	
		/**
		 * Convert cust aff to cust algmntmodel.
		 *
		 * @param affCustomer the aff customer
		 * @param newCustAlgn the new cust algn
		 * @return the customer alignment
		 */
		public CustomerAlignment convertCustAffToCustAlgmntmodel(
				CustomerAffiliation affCustomer, CustomerAlignment newCustAlgn) {
	
			CustomerAlignment customerAlignment = new CustomerAlignment();
			Customer customer = new Customer();
	
			customer.setId(affCustomer.getId());
			customer.setCode(affCustomer.getCode());
			customer.setName(affCustomer.getName());
			customer.setCreatedBy(affCustomer.getCreatedBy());
			customer.setCreatedDate(affCustomer.getCreatedDate());
			customer.setUpdatedBy(affCustomer.getUpdatedBy());
			customer.setUpdatedDate(affCustomer.getUpdatedDate());
			customer.setStartDate(affCustomer.getStartDate());
			customer.setEndDate(affCustomer.getEndDate());
			customer.setTenantCode(affCustomer.getTenantCode());
			customer.setTenantId(affCustomer.getTenantId());
	
			SalesPosition salesPosition = new SalesPosition();
			Alignment alignmment = new Alignment();
			alignmment
					.setId(newCustAlgn.getSalesPosition().getAlignmment().getId());
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(newCustAlgn.getSalesPosition().getAlignmment()
					.getSalesTeam().getId());
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(newCustAlgn.getSalesPosition().getAlignmment()
					.getSalesTeam().getBusinessUnit().getId());
			salesTeam.setBusinessUnit(businessUnit);
	
			alignmment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignmment);
			salesPosition.setId(newCustAlgn.getSalesPosition().getId());
			SalesOrgHierarchy orgHierarchy = new SalesOrgHierarchy();
			orgHierarchy.setId(newCustAlgn.getSalesPosition().getHierarchy()
					.getId());
			salesPosition.setHierarchy(orgHierarchy);
			salesPosition.setStartDate(newCustAlgn.getSalesPosition()
					.getStartDate());
			salesPosition.setEndDate(newCustAlgn.getSalesPosition().getEndDate());
	
			customerAlignment.setCustomer(customer);
			customerAlignment.setSalesPosition(salesPosition);
	
			return customerAlignment;
		}
		
		/**
		 * @param tCustAlgmnt
		 * @param custAlgnmtExtdAttributes 
		 * @return CustomerAlignment
		 */
		public CustomerAlignment convertTCustAlToModel(
				TCustAlgmnt tCustAlgmnt, Map<Integer, List<ExtdAttribue>> custAlgnmtExtdAttributes,Map<Integer, List<ExtdAttribue>> custExtdAttributes) {
			CustomerAlignment customerAlignment = new CustomerAlignment();
			customerAlignment.setId(tCustAlgmnt.getCustAlgmntId());
			customerAlignment.setActive(tCustAlgmnt.getActiveFlag() != null
					&& tCustAlgmnt.getActiveFlag().equals(
							CommonConstants.ACTIVE_Y) ? true : false);		
			Customer customerModel = customerModelAssembler.convertTCustToModelWithExtAttr(tCustAlgmnt.getTCust(), custExtdAttributes);
			customerAlignment.setCustomer(customerModel);		
			customerAlignment.setStartDate(tCustAlgmnt.getEffStartDt());
			customerAlignment.setEndDate(tCustAlgmnt.getEffEndDt());
			
			if(tCustAlgmnt.gettSalesPos() != null){
                SalesPosition salesPosition = salesPositionModelAssembler.mapSPEntityDetailsToSalesPos(tCustAlgmnt.gettSalesPos(), tCustAlgmnt.getTenantId()); 
                customerAlignment.setSalesPosition(salesPosition);
			}else{
                SalesPosition salesPosition = new SalesPosition();
                salesPosition.setId(tCustAlgmnt.getSalesPosId());
                SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
                salesOrgHierarchy.setId(tCustAlgmnt.getSalesHierId());
                salesPosition.setHierarchy(salesOrgHierarchy);
                Alignment alignment = new Alignment();
                alignment.setId(tCustAlgmnt.getAlgmntId());
                SalesTeam salesTeam = new SalesTeam();
                BusinessUnit businessUnit = new BusinessUnit();
                businessUnit.setId(tCustAlgmnt.getBussUnitId());
                salesTeam.setBusinessUnit(businessUnit);
                salesTeam.setId(tCustAlgmnt.getSalesTeamId());
                alignment.setSalesTeam(salesTeam);
                salesPosition.setAlignmment(alignment);
                customerAlignment.setSalesPosition(salesPosition);
			}
			
			customerAlignment.setCreatedBy(tCustAlgmnt.getCreatedBy());
			customerAlignment.setCreatedDate(tCustAlgmnt.getCreateDt());
			customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
			customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
			customerAlignment.setUpdatedBy(tCustAlgmnt.getUpdatedBy());
			customerAlignment.setUpdatedDate(tCustAlgmnt.getUpdateDt());
			customerAlignment
					.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
							&& tCustAlgmnt.getTargetFlag().equals(
									CommonConstants.ACTIVE_Y) ? true : false);
			customerAlignment
					.setAffBasedAssignment(tCustAlgmnt.getAffFlag() != null
							&& tCustAlgmnt.getAffFlag().equals(
									CommonConstants.ACTIVE_Y) ? true : false);
			if (tCustAlgmnt.getPrtTypeId() != null) {
				customerAlignment.setPriority(PriorityType
						.getPriorityType(tCustAlgmnt.getPrtTypeId()));
			}
			customerAlignment
					.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
							&& tCustAlgmnt.getTargetFlag().equals(
									CommonConstants.ACTIVE_Y) ? true : false);
			customerAlignment
					.setImplicitAssignment(tCustAlgmnt.getImplFlag() != null
							&& tCustAlgmnt.getImplFlag().equals(
									CommonConstants.ACTIVE_Y) ? true : false);
			customerAlignment.setGeoAligned(tCustAlgmnt.getGeoAlgmntFlag() != null
					&& tCustAlgmnt.getGeoAlgmntFlag().equals(
							CommonConstants.ACTIVE_Y) ? true : false);
			
			customerAlignment.setCompAligned(tCustAlgmnt.getCompAlgmntFlag() != null && tCustAlgmnt.getCompAlgmntFlag().equals(CommonConstants.ACTIVE_Y) ? true : false);
			
			if (tCustAlgmnt.getStatusId() != null) {
				customerAlignment.setStatus(ChangeRequestStatusType
						.getChangeRequestStatusType(tCustAlgmnt.getStatusId()));
			}

			if(custAlgnmtExtdAttributes != null && !custAlgnmtExtdAttributes.isEmpty()){
				if(custAlgnmtExtdAttributes.get(tCustAlgmnt.getTCust().getCustId()) != null && !custAlgnmtExtdAttributes.get(tCustAlgmnt.getTCust().getCustId()).isEmpty()){
					LOGGER.info("**********EXTENDED ATTRIBUTES GOT FOR THE CUSTOMER ID = "
							+ tCustAlgmnt.getTCust().getCustId()
							+ "*******ARE******"
							+ custAlgnmtExtdAttributes.get(tCustAlgmnt.getTCust()
									.getCustId()));
						customerAlignment.setExtdAttributes((custAlgnmtExtdAttributes.get(tCustAlgmnt.getTCust().getCustId())));
					}
			}
			return customerAlignment;
		}
		
		/**
		 * Convert t cust aligments to model.
		 *
		 * @param tCustAlgmnt the t cust algmnt
		 * @return the list
		 */
		public List<CustomerAlignment> convertTCustAligmentsToModel(
				List<TCustAlgmnt> tCustAlgmnt) {

			List<CustomerAlignment> custAlList = null;
			if (null != tCustAlgmnt && !tCustAlgmnt.isEmpty()) {
				custAlList = new ArrayList<CustomerAlignment>();
				for (TCustAlgmnt custAlgmnt : tCustAlgmnt) {
					
					CustomerAlignment customerAlignment = convertTCustAlToModel(custAlgmnt, true);

					custAlList.add(customerAlignment);
				}
			}
			return custAlList;
		}

		
	public CustomerAlignment convertTCustAlToModelForGIS(TCustAlgmnt tCustAlgmnt) {
		CustomerAlignment customerAlignment = new CustomerAlignment();
		customerAlignment.setId(tCustAlgmnt.getCustAlgmntId());
		customerAlignment
				.setActive(tCustAlgmnt.getActiveFlag() != null
						&& tCustAlgmnt.getActiveFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		Customer customerModel = customerModelAssembler
				.convertTCustToModelWithContactDetails(tCustAlgmnt.getTCust());
		customerAlignment.setCustomer(customerModel);
		customerAlignment.setStartDate(tCustAlgmnt.getEffStartDt());
		customerAlignment.setEndDate(tCustAlgmnt.getEffEndDt());

		if (tCustAlgmnt.gettSalesPos() != null) {
			SalesPosition salesPosition = salesPositionModelAssembler
					.mapSPEntityDetailsToSalesPos(tCustAlgmnt.gettSalesPos(),
							tCustAlgmnt.getTenantId());
			customerAlignment.setSalesPosition(salesPosition);
		} else {
			SalesPosition salesPosition = new SalesPosition();
			salesPosition.setId(tCustAlgmnt.getSalesPosId());
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesOrgHierarchy.setId(tCustAlgmnt.getSalesHierId());
			salesPosition.setHierarchy(salesOrgHierarchy);
			Alignment alignment = new Alignment();
			alignment.setId(tCustAlgmnt.getAlgmntId());
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(tCustAlgmnt.getBussUnitId());
			salesTeam.setBusinessUnit(businessUnit);
			salesTeam.setId(tCustAlgmnt.getSalesTeamId());
			alignment.setSalesTeam(salesTeam);
			salesPosition.setAlignmment(alignment);
			customerAlignment.setSalesPosition(salesPosition);
		}

		customerAlignment.setCreatedBy(tCustAlgmnt.getCreatedBy());
		customerAlignment.setCreatedDate(tCustAlgmnt.getCreateDt());
		customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
		customerAlignment.setTenantId(tCustAlgmnt.getTenantId());
		customerAlignment.setUpdatedBy(tCustAlgmnt.getUpdatedBy());
		customerAlignment.setUpdatedDate(tCustAlgmnt.getUpdateDt());
		customerAlignment
				.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
						&& tCustAlgmnt.getTargetFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment
				.setAffBasedAssignment(tCustAlgmnt.getAffFlag() != null
						&& tCustAlgmnt.getAffFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		if (tCustAlgmnt.getPrtTypeId() != null) {
			customerAlignment.setPriority(PriorityType
					.getPriorityType(tCustAlgmnt.getPrtTypeId()));
		}
		customerAlignment
				.setCustomerTargeted(tCustAlgmnt.getTargetFlag() != null
						&& tCustAlgmnt.getTargetFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment
				.setImplicitAssignment(tCustAlgmnt.getImplFlag() != null
						&& tCustAlgmnt.getImplFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
		customerAlignment.setGeoAligned(tCustAlgmnt.getGeoAlgmntFlag() != null
				&& tCustAlgmnt.getGeoAlgmntFlag().equals(
						CommonConstants.ACTIVE_Y) ? true : false);

		customerAlignment
				.setCompAligned(tCustAlgmnt.getCompAlgmntFlag() != null
						&& tCustAlgmnt.getCompAlgmntFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);

		if (tCustAlgmnt.getStatusId() != null) {
			customerAlignment.setStatus(ChangeRequestStatusType
					.getChangeRequestStatusType(tCustAlgmnt.getStatusId()));
		}

		return customerAlignment;
	}

	/**
	 * @param tSalesPos
	 * @return
	 */
	public SalesPosition convertTSalesPosToSalesPostion(TSalesPos tSalesPos) {
		
		SalesPosition salesPosition=new SalesPosition();
		SalesOrgHierarchy hierarchy=new SalesOrgHierarchy();
		Alignment alignmment=new Alignment();
		SalesTeam salesTeam=new SalesTeam();
		BusinessUnit businessUnit=new BusinessUnit();
		
		salesPosition.setId(tSalesPos.getSalesPosId());
		hierarchy.setId(tSalesPos.getTAlgmntSalesHier().getSalesHierId());
		salesPosition.setHierarchy(hierarchy);
		alignmment.setId(tSalesPos.getTAlgmntSalesTeam().getTAlgmnt().getAlgmntId());
		salesTeam.setId(tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTSalesTeamId().getSalesTeamId());
		businessUnit.setId(tSalesPos.getTAlgmntSalesTeam().getTAlgmnt().getTBussUnit().getBussUnitId());
		alignmment.setSalesTeam(salesTeam);
		salesTeam.setBusinessUnit(businessUnit);
		salesPosition.setAlignmment(alignmment);
	
		return salesPosition;
	
		
	}
		
}
