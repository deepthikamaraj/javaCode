package com.cognizant.opserv.sp.model;

import java.io.Serializable;

/**
 * ****************************************************************************.
 *
 * @class Contact
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Contact extends BaseModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2342466542152270753L;

	/** email is the email. */
	private String email;

	/** phone is the phone. */
	private String phone;

	/** fax is the fax. */
	private String fax;

	/** mobile is the mobile. */
	private String mobile;
	
	/** customer is the customer data. */
	private Customer customer;
	
	/** primaryContactFlag is the primary contact flag. */
	private boolean isPrimaryContact;
	
	/** contactExtension is the contact extension. */
	private String contactExtension;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the fax.
	 *
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * Sets the fax.
	 *
	 * @param fax the new fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * Gets the mobile.
	 *
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile.
	 *
	 * @param mobile the new mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Checks if is primary contact.
	 *
	 * @return true, if is primary contact
	 */
	public boolean isPrimaryContact() {
		return isPrimaryContact;
	}

	/**
	 * Sets the primary contact.
	 *
	 * @param isPrimaryContact the new primary contact
	 */
	public void setPrimaryContact(boolean isPrimaryContact) {
		this.isPrimaryContact = isPrimaryContact;
	}

	/**
	 * Gets the contact extension.
	 *
	 * @return the contact extension
	 */
	public String getContactExtension() {
		return contactExtension;
	}

	/**
	 * Sets the contact extension.
	 *
	 * @param contactExtension the new contact extension
	 */
	public void setContactExtension(String contactExtension) {
		this.contactExtension = contactExtension;
	}

}
