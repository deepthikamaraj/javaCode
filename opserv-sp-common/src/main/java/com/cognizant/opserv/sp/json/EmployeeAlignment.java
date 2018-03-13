package com.cognizant.opserv.sp.json;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class EmployeeAlignment implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 287106360174506508L;
	
	/** The salespos id. */
	@JsonProperty("spId")
	private Long salesposId;
	
	/** The salespos name. */
	@JsonProperty("spnm")
	private String salesposName;
	
	/** The emp id. */
	@JsonProperty("eId")
	private Long empId;
	
	/** The emp align id. */
	@JsonProperty("eaId")
	private Long empAlignId;
	
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
	
	/** The date of birth. */
	@JsonProperty("dob")
	private String dateOfBirth;
	
	/** The manager code. */
	@JsonProperty("mgr")
	private String managerCode;
	
	/** The client code. */
	@JsonProperty("cli")
	private String clientCode;
	
	/** The gender. */
	@JsonProperty("ge")
	private String gender;
	
	/** The app code. */
	@JsonProperty("ac")
	private String appCode;
	
	/** The qual code. */
	@JsonProperty("qc")
	private String qualCode;
	
	/** The org name. */
	@JsonProperty("on")
	private String orgName;
	
	/** The div code. */
	@JsonProperty("dc")
	private String divCode;
	
	/** The job title. */
	@JsonProperty("jt")
	private String jobTitle;
	
	/** The role name. */
	@JsonProperty("rol")
	private String roleName;
	
	/** The joining date. */
	@JsonProperty("jDt")
	private Date joiningDate;
	
	/** The employee status. */
	@JsonProperty("eSt")
	private String employeeStatus;
	
	/** The employment type. */
	@JsonProperty("eTy")
	private String employmentType;
	
	/** The user identifier. */
	@JsonProperty("uId")
	private Integer userIdentifier;
	
	/** The termination date. */
	@JsonProperty("tDt")
	private Date terminationDate;	

	/** The address line1. */
	@JsonProperty("al1")
	private String addressLine1;
	
	/** The address line2. */
	@JsonProperty("al2")
	private String addressLine2;
	
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
	
	/** The emp ext attrs. */
	@JsonProperty("eEa")
	private List<ExtendedAttribute> empExtAttrs;
	
	/** The emp align ext attrs. */
	@JsonProperty("eaEa")
	private List<ExtendedAttribute> empAlignExtAttrs;
	
	/** The effective start date. */
	@JsonProperty("sDt")
	private Date effectiveStartDate;
	
	/** The effective end date. */
	@JsonProperty("eDt")
	private Date effectiveEndDate;

	/** The sales org role. */
	@JsonProperty("soRl")
	private String salesOrgRole;
	
	/** The system role type. */
	@JsonProperty("sRty")
	private String systemRoleType;
	
	/** The allocation type. */
	@JsonProperty("alTy")
	private String allocationType;
	
	/**
	 * Gets the salespos id.
	 *
	 * @return the salespos id
	 */
	public Long getSalesposId() {
		return salesposId;
	}
	
	/**
	 * Sets the salespos id.
	 *
	 * @param salesposId the new salespos id
	 */
	public void setSalesposId(Long salesposId) {
		this.salesposId = salesposId;
	}
	
	/**
	 * Gets the salespos name.
	 *
	 * @return the salespos name
	 */
	public String getSalesposName() {
		return salesposName;
	}
	
	/**
	 * Sets the salespos name.
	 *
	 * @param salesposName the new salespos name
	 */
	public void setSalesposName(String salesposName) {
		this.salesposName = salesposName;
	}
	
	/**
	 * Gets the emp id.
	 *
	 * @return the emp id
	 */
	public Long getEmpId() {
		return empId;
	}

	/**
	 * Sets the emp id.
	 *
	 * @param empId the empId to set
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	/**
	 * Gets the emp align id.
	 *
	 * @return the empAlignId
	 */
	public Long getEmpAlignId() {
		return empAlignId;
	}

	/**
	 * Sets the emp align id.
	 *
	 * @param empAlignId the empAlignId to set
	 */
	public void setEmpAlignId(Long empAlignId) {
		this.empAlignId = empAlignId;
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
	 * Gets the date of birth.
	 *
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth.
	 *
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the manager code.
	 *
	 * @return the managerCode
	 */
	public String getManagerCode() {
		return managerCode;
	}

	/**
	 * Sets the manager code.
	 *
	 * @param managerCode the managerCode to set
	 */
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	/**
	 * Gets the client code.
	 *
	 * @return the clientCode
	 */
	public String getClientCode() {
		return clientCode;
	}

	/**
	 * Sets the client code.
	 *
	 * @param clientCode the clientCode to set
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the app code.
	 *
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * Sets the app code.
	 *
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * Gets the qual code.
	 *
	 * @return the qualCode
	 */
	public String getQualCode() {
		return qualCode;
	}

	/**
	 * Sets the qual code.
	 *
	 * @param qualCode the qualCode to set
	 */
	public void setQualCode(String qualCode) {
		this.qualCode = qualCode;
	}

	/**
	 * Gets the org name.
	 *
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * Sets the org name.
	 *
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * Gets the div code.
	 *
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * Sets the div code.
	 *
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * Gets the job title.
	 *
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the role name.
	 *
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name.
	 *
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets the joining date.
	 *
	 * @return the joiningDate
	 */
	public Date getJoiningDate() {
		return joiningDate;
	}

	/**
	 * Sets the joining date.
	 *
	 * @param joiningDate the joiningDate to set
	 */
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	/**
	 * Gets the employee status.
	 *
	 * @return the employeeStatus
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * Sets the employee status.
	 *
	 * @param employeeStatus the employeeStatus to set
	 */
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**
	 * Gets the employment type.
	 *
	 * @return the employmentType
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the employmentType to set
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	/**
	 * Gets the user identifier.
	 *
	 * @return the userIdentifier
	 */
	public Integer getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * Sets the user identifier.
	 *
	 * @param userIdentifier the userIdentifier to set
	 */
	public void setUserIdentifier(Integer userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * Gets the termination date.
	 *
	 * @return the terminationDate
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 * Sets the termination date.
	 *
	 * @param terminationDate the terminationDate to set
	 */
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
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
	 * Gets the emp ext attrs.
	 *
	 * @return the empExtAttrs
	 */
	public List<ExtendedAttribute> getEmpExtAttrs() {
		return empExtAttrs;
	}

	/**
	 * Sets the emp ext attrs.
	 *
	 * @param empExtAttrs the empExtAttrs to set
	 */
	public void setEmpExtAttrs(List<ExtendedAttribute> empExtAttrs) {
		this.empExtAttrs = empExtAttrs;
	}

	/**
	 * Gets the emp align ext attrs.
	 *
	 * @return the empAlignExtAttrs
	 */
	public List<ExtendedAttribute> getEmpAlignExtAttrs() {
		return empAlignExtAttrs;
	}

	/**
	 * Sets the emp align ext attrs.
	 *
	 * @param empAlignExtAttrs the empAlignExtAttrs to set
	 */
	public void setEmpAlignExtAttrs(List<ExtendedAttribute> empAlignExtAttrs) {
		this.empAlignExtAttrs = empAlignExtAttrs;
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
	 * Gets the sales org role.
	 *
	 * @return the salesOrgRole
	 */
	public String getSalesOrgRole() {
		return salesOrgRole;
	}

	/**
	 * Sets the sales org role.
	 *
	 * @param salesOrgRole the salesOrgRole to set
	 */
	public void setSalesOrgRole(String salesOrgRole) {
		this.salesOrgRole = salesOrgRole;
	}

	/**
	 * Gets the system role type.
	 *
	 * @return the systemRoleType
	 */
	public String getSystemRoleType() {
		return systemRoleType;
	}

	/**
	 * Sets the system role type.
	 *
	 * @param systemRoleType the systemRoleType to set
	 */
	public void setSystemRoleType(String systemRoleType) {
		this.systemRoleType = systemRoleType;
	}

	/**
	 * Gets the allocation type.
	 *
	 * @return the allocationType
	 */
	public String getAllocationType() {
		return allocationType;
	}

	/**
	 * Sets the allocation type.
	 *
	 * @param allocationType the allocationType to set
	 */
	public void setAllocationType(String allocationType) {
		this.allocationType = allocationType;
	}
	
	
	
	
	
}
