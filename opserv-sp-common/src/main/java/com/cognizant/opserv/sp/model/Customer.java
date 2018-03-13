package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;

import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;
/**
 * ****************************************************************************.
 *
 * @class Customer
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Customer extends BaseExtdModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2486847739404144376L;

	/**
	 * firstName is the customer first name
	 */
	private String firstName;

	/**
	 * middleName is the Customer middle name
	 */

	private String middleName;

	/**
	 * Customer is the Customer last name
	 */
	private String lastName;

	/**
	 * primaryAddress is the Customer primary address
	 */
	private Address primaryAddress;

	/**
	 * addresses is the List of Customer addresses
	 */
	private List<Address> addresses;

	/**
	 * primaryContact is the Customer primary contact
	 */
	private Contact primaryContact;

	/**
	 * contacts is the List of Customer contacts
	 */
	private List<Contact> contacts;

	/**
	 * stateLicenceId is the State License Id
	 */
	private String stateLicenceId;

	/**
	 * drugEnforceAdminId is the Drug Enforcement Administration Identifier
	 */
	private String drugEnforceAdminId;

	/**
	 * comments are the comments
	 */
	private String comments;

	/**
	 * type is the customer type
	 */
	private Integer typeId;

	/**
	 * priorityType is the priority type
	 */
	private Integer priorityType;

	/**
	 * category is the customer category
	 */
	private CustomerCategoryType category;

	/**
	 * prefix is the Name prefix
	 */
	private String namePrefix;

	/**
	 * suffix is the Name suffix
	 */
	private String nameSuffix;

	/**
	 * @return the Customer firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the Customer firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the Customer middelName
	 */
	public String getMiddelName() {
		return middleName;
	}

	/**
	 * @param middelName
	 *            the Customer middelName to set
	 */
	public void setMiddelName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the Customer lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the Customer lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the Customer primaryAddress
	 */
	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	/**
	 * @param primaryAddress
	 *            the Customer primaryAddress to set
	 */
	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}


	/**
	 * @return the Customer primaryContact
	 */
	public Contact getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact
	 *            the Customer primaryContact to set
	 */
	public void setPrimaryContact(Contact primaryContact) {
		this.primaryContact = primaryContact;
	}

	/**
	 * @return the Customer contacts
	 */
	public List<Contact> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts
	 *            the Customer contacts to set
	 */
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * @return the Customer middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the Customer middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the Customer addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the Customer addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the Customer stateLicenceId
	 */
	public String getStateLicenceId() {
		return stateLicenceId;
	}

	/**
	 * @param stateLicenceId
	 *            the Customer stateLicenceId to set
	 */
	public void setStateLicenceId(String stateLicenceId) {
		this.stateLicenceId = stateLicenceId;
	}

	/**
	 * @return the Customer drugEnforceAdminId
	 */
	public String getDrugEnforceAdminId() {
		return drugEnforceAdminId;
	}

	/**
	 * @param drugEnforceAdminId
	 *            the Customer drugEnforceAdminId to set
	 */
	public void setDrugEnforceAdminId(String drugEnforceAdminId) {
		this.drugEnforceAdminId = drugEnforceAdminId;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the type
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * @param type
	 *            the type to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix
	 *            the namePrefix to set
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix
	 *            the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return the priority Type
	 */
	public Integer getPriorityType() {
		return priorityType;
	}

	/**
	 * @param priorityType
	 *            the priority Type to set
	 */
	public void setPriorityType(Integer priorityType) {
		this.priorityType = priorityType;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return namePrefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.namePrefix = prefix;
	}

	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return nameSuffix;
	}

	/**
	 * @param suffix
	 *            the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.nameSuffix = suffix;
	}

	/**
	 * @return the category
	 */
	public CustomerCategoryType getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CustomerCategoryType category) {
		this.category = category;
	}

}
