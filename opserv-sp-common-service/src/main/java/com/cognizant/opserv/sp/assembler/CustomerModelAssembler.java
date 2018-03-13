package com.cognizant.opserv.sp.assembler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.Contact;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.attrb.ExtdAttribue;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Component
public class CustomerModelAssembler {

	/** The address model assembler. */
	@Autowired
	AddressModelAssembler addressModelAssembler;

	/** The contact model assembler. */
	@Autowired
	ContactModelAssembler contactModelAssembler;

	/** The Constant LOGGER. */
	private final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerModelAssembler.class);

	/**
	 * Convert t cust to model with child entities.
	 *
	 * @param tCust the t cust
	 * @return the customer
	 */
	public Customer convertTCustToModelWithChildEntities(TCust tCust){
		Customer customer = convertTCustToModelWithBasicDetails(tCust);
		if(null != customer){
			if(tCust.getTCustAddrss() != null && !tCust.getTCustAddrss().isEmpty()){
				customer.setAddresses(convertTCustAddrToModel(tCust.getTCustAddrss(), customer));
			}

			if(tCust.getTCustContactss() != null && !tCust.getTCustContactss().isEmpty()){
				customer.setContacts(convertTCustContactToModel(tCust.getTCustContactss(), customer));
			}
		}
		return customer;
	}

	/**
	 * Convert t cust to model for gis.
	 *
	 * @param tCust the t cust
	 * @return the customer
	 */
	public Customer convertTCustToModelWithContactDetails(TCust tCust){
		Customer customer = convertTCustToModelWithBasicDetails(tCust);
		if(null != customer){
			if(tCust.getTCustAddrss() != null && !tCust.getTCustAddrss().isEmpty()){
				customer.setAddresses(convertTCustAddrToModel(tCust.getTCustAddrss(), customer));
			}
		}
		return customer;
	}

	/**
	 * Convert t cust to model with ext attr.
	 *
	 * @param tCust the t cust
	 * @param custExtdAttributes the cust extd attributes
	 * @return the customer
	 */
	public Customer convertTCustToModelWithExtAttr(TCust tCust, Map<Integer, List<ExtdAttribue>> custExtdAttributes){
		Customer customer = convertTCustToModelWithChildEntities(tCust);
		if(null != customer){
			if(custExtdAttributes != null && !custExtdAttributes.isEmpty()){
				if(custExtdAttributes.get(tCust.getCustId()) != null && !custExtdAttributes.get(tCust.getCustId()).isEmpty()){
					LOGGER.info("**********EXTENDED ATTRIBUTES GOT FOR THE CUSTOMER ID = "
							+ tCust.getCustId()
							+ "*******ARE******"
							+ custExtdAttributes.get(tCust.getCustId()));
					customer.setExtdAttributes((custExtdAttributes.get(tCust.getCustId())));
				}
			}
		}
		return customer;
	}

	/**
	 * Convert t cust to model.
	 *
	 * @param tCust the t cust
	 * @return the customer
	 */
	public Customer convertTCustToModelWithBasicDetails(TCust tCust){
		Customer customer = null;
		if(null != tCust){
			customer = new Customer();
			customer.setName(tCust.getCustName());
			customer.setId(tCust.getCustId().longValue());
			customer.setCode(tCust.getCustCd());
			customer.setCreatedBy(tCust.getCreatedBy());
			customer.setCreatedDate(tCust.getCreateDt());
			customer.setUpdatedBy(tCust.getUpdatedBy());
			customer.setUpdatedDate(tCust.getUpdateDt());
			customer.setTenantId(tCust.getTenantId());
			if(tCust.getActiveFlag() == CommonConstants.ACTIVE_Y){
				customer.setActive(true);
			}
			else{
				customer.setActive(false);
			}
			customer.setFirstName(tCust.getCustFirstName());
			customer.setMiddelName(tCust.getCustMiddleName());
			customer.setLastName(tCust.getCustLastName());
			customer.setStateLicenceId(tCust.getStateLicId());
			customer.setDrugEnforceAdminId(tCust.getDeaId());
			customer.setComments(tCust.getComments());
			customer.setTypeId(tCust.getCustTypeId());
			customer.setPriorityType(tCust.getPrtTypeId());
			customer.setCategory(CustomerCategoryType.getCustomerCategoryType(tCust.getCategoryId()));
			customer.setNamePrefix(tCust.getNamePfx());
			customer.setNameSuffix(tCust.getNameSfx());
		}
		return customer;
	}

	/**
	 * Convert t cust addr to model.
	 *
	 * @param tCustAddrs the t cust addrs
	 * @param customer the customer
	 * @return the list
	 */
	private List<Address> convertTCustAddrToModel(Set<TCustAddr> tCustAddrs, Customer customer){
		List<Address> custAddrList = null;
		if(tCustAddrs != null && !tCustAddrs.isEmpty()){
			custAddrList = new ArrayList<Address>();
			for(TCustAddr tCustAddr : tCustAddrs){
				Address address = addressModelAssembler.convertTCustAddrToAddressModel(tCustAddr);
				if(address.isPrimaryAddr()){
					customer.setPrimaryAddress(address);
				}
				custAddrList.add(address);
			}
		}
		return custAddrList;
	}

	/**
	 * Convert t cust contact to model.
	 *
	 * @param tCustContacts the t cust contacts
	 * @param customer the customer
	 * @return the list
	 */
	private List<Contact> convertTCustContactToModel(Set<TCustContact> tCustContacts, Customer customer){
		List<Contact> custContactList = null;
		if(tCustContacts != null && !tCustContacts.isEmpty()){
			custContactList = new ArrayList<Contact>();
			for(TCustContact custContact : tCustContacts){
				Contact contact = contactModelAssembler.convertTCustContactToContactModel(custContact);
				if(contact.isPrimaryContact()){
					customer.setPrimaryContact(contact);
				}
				custContactList.add(contact);
			}
		}
		return custContactList;
	}

}
