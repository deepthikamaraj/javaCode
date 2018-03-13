package com.cognizant.opserv.sp.assembler;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.ContactType;
import com.cognizant.opserv.sp.core.entity.TCustContact;
import com.cognizant.opserv.sp.core.entity.TPersContact;
import com.cognizant.opserv.sp.model.Contact;
@Component
public class ContactModelAssembler {

	/** The Constant ACTIVE_FLAG. */
	public static final String ACTIVE_FLAG = "Y";
	
	/**
	 * Convert t perscontact to contact model.
	 *
	 * @param tPersContact the t pers contact
	 * @return the contact
	 */
	public Contact convertTPerscontactToContactModel(Set<TPersContact> tPersContact)
	{
		Contact contacts = new Contact();
		for (TPersContact contact :  tPersContact) {
			if(null !=contact.getPrContactFlag() && contact.getPrContactFlag().equals(CommonConstants.ACTIVE_FLAG))
			{
			contacts.setPrimaryContact(true);
			contacts.setId(contact.getPersContactId().longValue());
			if(ContactType.getContactType(contact.getContactTypeId()).equals(ContactType.EMAIL)){
				contacts.setEmail(contact.getContactDetail());
			}else if(ContactType.getContactType(contact.getContactTypeId()).equals(ContactType.PHONE)){
				contacts.setPhone(contact.getContactDetail());
			}else if(ContactType.getContactType(contact.getContactTypeId()).equals(ContactType.FAX)){
				contacts.setFax(contact.getContactDetail());
				
			}else if(ContactType.getContactType(contact.getContactTypeId()).equals(ContactType.MOBILE)){
				contacts.setMobile(contact.getContactDetail());
			}
			
			}
		}
		return contacts;
		
	}
	
	/**
	 * Convert t cust contact to contact model.
	 *
	 * @param tCustContact the t cust contact
	 * @return the contact
	 */
	public Contact convertTCustContactToContactModel(TCustContact tCustContact){
		Contact contact = null;
		if(tCustContact != null){
			contact = new Contact();
			contact.setId(tCustContact.getTCustContactId().getCustContactId().longValue());
			tCustContact.getCreatedBy();
			tCustContact.getCreateDt();
			tCustContact.getUpdatedBy();
			tCustContact.getUpdateDt();
			if(ContactType.getContactType(tCustContact.getTContactType().getContactTypeId()).equals(ContactType.EMAIL)){
				contact.setEmail(tCustContact.getContactDetail());
			}
			else if(ContactType.getContactType(tCustContact.getTContactType().getContactTypeId()).equals(ContactType.PHONE)){
				contact.setPhone(tCustContact.getContactDetail());
			}
			else if(ContactType.getContactType(tCustContact.getTContactType().getContactTypeId()).equals(ContactType.FAX)){
				contact.setFax(tCustContact.getContactDetail());
			}
			else if(ContactType.getContactType(tCustContact.getTContactType().getContactTypeId()).equals(ContactType.MOBILE)){
				contact.setMobile(tCustContact.getContactDetail());
			}
			contact.setContactExtension(tCustContact.getContactExtn());
			if(tCustContact.getPrContactFlag() == CommonConstants.ACTIVE_Y){
				contact.setPrimaryContact(true);
			}
			else{
				contact.setPrimaryContact(false);
			}
		}
		return contact;
	}
	

}
