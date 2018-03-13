package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonProperty;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class CustomerAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class CustomerAlignment implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 578754816396454365L;
	
	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;
	
	/** The salespos name. */
	@JsonProperty("spnm")
	private String salesposName;
	
	/** The cust id. */
	@JsonProperty("cId")
	private Long custId;
	
	/** The cust align id. */
	@JsonProperty("caId")
	private Long custAlignId;
	
	/** The cust code. */
	@JsonProperty("ccd")
	private String custCode;
	
	/** The first name. */
	@JsonProperty("fn")
	private String firstName;
	
	/** The middle name. */
	@JsonProperty("mn")
	private String middleName;
	
	/** The last name. */
	@JsonProperty("ln")
	private String lastName;
	
	/** The name prefix. */
	@JsonProperty("pr")
	private String namePrefix;
	
	/** The name suffix. */
	@JsonProperty("su")
	private String nameSuffix;

	/** The address line1. */
	@JsonProperty("al1")
	private String addressLine1;
	
	/** The address line2. */
	@JsonProperty("al2")
	private String addressLine2;
	
	/** The address line3. */
	@JsonProperty("al3")
	private String addressLine3;
	
	/** The address line4. */
	@JsonProperty("al4")
	private String addressLine4;
	
	/** The city. */
	@JsonProperty("ci")
	private String city;
	
	/** The state. */
	@JsonProperty("st")
	private String state;
	
	/** The country. */
	@JsonProperty("cy")
	private String country;
	
	/** The postal code. */
	@JsonProperty("zi")
	private String postalCode;
	
	/** The email. */
	@JsonProperty("em")
	private String email;
	
	/** The phone. */
	@JsonProperty("ph")
	private String phone;
	
	/** The fax. */
	@JsonProperty("fa")
	private String fax;
	
	/** The mobile. */
	@JsonProperty("mo")
	private String mobile;
		
	/** The cust ext attrs. */
	@JsonProperty("cEa")
	private List<ExtendedAttribute> custExtAttrs;
	
	/** The cust align ext attrs. */
	@JsonProperty("caEa")
	private List<ExtendedAttribute> custAlignExtAttrs;
	
	/** The effective start date. */
	@JsonProperty("sDt")
	private Date effectiveStartDate;
	
	/** The effective end date. */
	@JsonProperty("eDt")
	private Date effectiveEndDate;

	/** The state licence id. */
	@JsonProperty("liId")
	private String stateLicenceId;
	
	/** The drug enforce admin id. */
	@JsonProperty("drEid")
	private String drugEnforceAdminId;
	
	/** The comments. */
	@JsonProperty("co")
	private String comments;
	
	/** The customer type. */
	@JsonProperty("cty")
	private String customerType;
	
	/** The priority type. */
	@JsonProperty("pty")
	private String priorityType;
	
	/** The cust category type. */
	@JsonProperty("ccaty")
	private String custCategoryType;
	
	/** The speciality. */
	@JsonProperty("spe")
	private String speciality;
	
	/** The is aff based assignment. */
	@JsonProperty("afas")
	private boolean isAffBasedAssignment;
	
	/** The is customer targeted. */
	@JsonProperty("cuTa")
	private boolean isCustomerTargeted;
	
	/** The is implicit assignment. */
	@JsonProperty("imAs")
	private boolean isImplicitAssignment;
	
	/** The is geo aligned. */
	@JsonProperty("gAl")
	private boolean isGeoAligned;
	
	/** The is comp aligned. */
	@JsonProperty("cAl")
	private boolean isCompAligned;
	
	/** The status id. */
	@JsonProperty("stId")
	private Integer statusId;
	
	/** The customer name. */
	@JsonProperty("cN")
	private String customerName;

	/**
	 * Gets the salespos id.
	 *
	 * @return the salesposId
	 */
	public Long getSalesposId() {
		return salesposId;
	}

	/**
	 * Sets the salespos id.
	 *
	 * @param salesposId the salesposId to set
	 */
	public void setSalesposId(Long salesposId) {
		this.salesposId = salesposId;
	}

	/**
	 * Gets the salespos name.
	 *
	 * @return the salesposName
	 */
	public String getSalesposName() {
		return salesposName;
	}

	/**
	 * Sets the salespos name.
	 *
	 * @param salesposName the salesposName to set
	 */
	public void setSalesposName(String salesposName) {
		this.salesposName = salesposName;
	}

	/**
	 * Gets the cust id.
	 *
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}

	/**
	 * Sets the cust id.
	 *
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}

	/**
	 * Gets the cust align id.
	 *
	 * @return the custAlignId
	 */
	public Long getCustAlignId() {
		return custAlignId;
	}

	/**
	 * Sets the cust align id.
	 *
	 * @param custAlignId the custAlignId to set
	 */
	public void setCustAlignId(Long custAlignId) {
		this.custAlignId = custAlignId;
	}

	/**
	 * Gets the cust code.
	 *
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}

	/**
	 * Sets the cust code.
	 *
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the name prefix.
	 *
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * Sets the name prefix.
	 *
	 * @param namePrefix the namePrefix to set
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * Gets the name suffix.
	 *
	 * @return the nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * Sets the name suffix.
	 *
	 * @param nameSuffix the nameSuffix to set
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * Gets the address line1.
	 *
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * Sets the address line1.
	 *
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the address line2.
	 *
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * Sets the address line2.
	 *
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the postal code.
	 *
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postal code.
	 *
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

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
	 * @param email the email to set
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
	 * @param phone the phone to set
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
	 * @param fax the fax to set
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
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the cust ext attrs.
	 *
	 * @return the custExtAttrs
	 */
	public List<ExtendedAttribute> getCustExtAttrs() {
		return custExtAttrs;
	}

	/**
	 * Sets the cust ext attrs.
	 *
	 * @param custExtAttrs the custExtAttrs to set
	 */
	public void setCustExtAttrs(List<ExtendedAttribute> custExtAttrs) {
		this.custExtAttrs = custExtAttrs;
	}

	/**
	 * Gets the cust align ext attrs.
	 *
	 * @return the custAlignExtAttrs
	 */
	public List<ExtendedAttribute> getCustAlignExtAttrs() {
		return custAlignExtAttrs;
	}

	/**
	 * Sets the cust align ext attrs.
	 *
	 * @param custAlignExtAttrs the custAlignExtAttrs to set
	 */
	public void setCustAlignExtAttrs(List<ExtendedAttribute> custAlignExtAttrs) {
		this.custAlignExtAttrs = custAlignExtAttrs;
	}

	/**
	 * Gets the effective start date.
	 *
	 * @return the effectiveStartDate
	 */
	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	/**
	 * Sets the effective start date.
	 *
	 * @param effectiveStartDate the effectiveStartDate to set
	 */
	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	/**
	 * Gets the effective end date.
	 *
	 * @return the effectiveEndDate
	 */
	public Date getEffectiveEndDate() {
		return effectiveEndDate;
	}

	/**
	 * Sets the effective end date.
	 *
	 * @param effectiveEndDate the effectiveEndDate to set
	 */
	public void setEffectiveEndDate(Date effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * Gets the state licence id.
	 *
	 * @return the stateLicenceId
	 */
	public String getStateLicenceId() {
		return stateLicenceId;
	}

	/**
	 * Sets the state licence id.
	 *
	 * @param stateLicenceId the stateLicenceId to set
	 */
	public void setStateLicenceId(String stateLicenceId) {
		this.stateLicenceId = stateLicenceId;
	}

	/**
	 * Gets the drug enforce admin id.
	 *
	 * @return the drugEnforceAdminId
	 */
	public String getDrugEnforceAdminId() {
		return drugEnforceAdminId;
	}

	/**
	 * Sets the drug enforce admin id.
	 *
	 * @param drugEnforceAdminId the drugEnforceAdminId to set
	 */
	public void setDrugEnforceAdminId(String drugEnforceAdminId) {
		this.drugEnforceAdminId = drugEnforceAdminId;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the customer type.
	 *
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * Sets the customer type.
	 *
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * Gets the priority type.
	 *
	 * @return the priorityType
	 */
	public String getPriorityType() {
		return priorityType;
	}

	/**
	 * Sets the priority type.
	 *
	 * @param priorityType the priorityType to set
	 */
	public void setPriorityType(String priorityType) {
		this.priorityType = priorityType;
	}

	/**
	 * Gets the cust category type.
	 *
	 * @return the custCategoryType
	 */
	public String getCustCategoryType() {
		return custCategoryType;
	}

	/**
	 * Sets the cust category type.
	 *
	 * @param custCategoryType the custCategoryType to set
	 */
	public void setCustCategoryType(String custCategoryType) {
		this.custCategoryType = custCategoryType;
	}

	/**
	 * Gets the speciality.
	 *
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}

	/**
	 * Sets the speciality.
	 *
	 * @param speciality the speciality to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	/**
	 * Checks if is aff based assignment.
	 *
	 * @return the isAffBasedAssignment
	 */
	public boolean isAffBasedAssignment() {
		return isAffBasedAssignment;
	}

	/**
	 * Sets the aff based assignment.
	 *
	 * @param isAffBasedAssignment the isAffBasedAssignment to set
	 */
	public void setAffBasedAssignment(boolean isAffBasedAssignment) {
		this.isAffBasedAssignment = isAffBasedAssignment;
	}

	/**
	 * Checks if is customer targeted.
	 *
	 * @return the isCustomerTargeted
	 */
	public boolean isCustomerTargeted() {
		return isCustomerTargeted;
	}

	/**
	 * Sets the customer targeted.
	 *
	 * @param isCustomerTargeted the isCustomerTargeted to set
	 */
	public void setCustomerTargeted(boolean isCustomerTargeted) {
		this.isCustomerTargeted = isCustomerTargeted;
	}

	/**
	 * Checks if is implicit assignment.
	 *
	 * @return the isImplicitAssignment
	 */
	public boolean isImplicitAssignment() {
		return isImplicitAssignment;
	}

	/**
	 * Sets the implicit assignment.
	 *
	 * @param isImplicitAssignment the isImplicitAssignment to set
	 */
	public void setImplicitAssignment(boolean isImplicitAssignment) {
		this.isImplicitAssignment = isImplicitAssignment;
	}

	/**
	 * Checks if is geo aligned.
	 *
	 * @return the isGeoAligned
	 */
	public boolean isGeoAligned() {
		return isGeoAligned;
	}

	/**
	 * Sets the geo aligned.
	 *
	 * @param isGeoAligned the isGeoAligned to set
	 */
	public void setGeoAligned(boolean isGeoAligned) {
		this.isGeoAligned = isGeoAligned;
	}

	/**
	 * Checks if is comp aligned.
	 *
	 * @return the isCompAligned
	 */
	public boolean isCompAligned() {
		return isCompAligned;
	}

	/**
	 * Sets the comp aligned.
	 *
	 * @param isCompAligned the isCompAligned to set
	 */
	public void setCompAligned(boolean isCompAligned) {
		this.isCompAligned = isCompAligned;
	}

	/**
	 * Gets the status id.
	 *
	 * @return the statusId
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * Sets the status id.
	 *
	 * @param statusId the statusId to set
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the address line3.
	 *
	 * @return the address line3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**
	 * Sets the address line3.
	 *
	 * @param addressLine3 the new address line3
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	/**
	 * Gets the address line4.
	 *
	 * @return the address line4
	 */
	public String getAddressLine4() {
		return addressLine4;
	}

	/**
	 * Sets the address line4.
	 *
	 * @param addressLine4 the new address line4
	 */
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}
	
	
}
