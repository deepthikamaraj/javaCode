package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.Date;

import com.cognizant.opserv.sp.common.EmployeeStatusType;
import com.cognizant.opserv.sp.common.EmploymentType;
import com.cognizant.opserv.sp.common.GenderType;
import com.cognizant.opserv.sp.model.attrb.BaseExtdModel;

/**
 * ****************************************************************************.
 *
 * @class Employee
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class Employee extends BaseExtdModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7210679581280450689L;

	/**
	 * First Name of the Employee
	 */
	private String firstName;

	/**
	 * Middle Name of the Employee
	 */
	private String middleName;

	/**
	 * Last Name of the Employee
	 */
	private String lastName;

	/**
	 * Date Of Birth for Employee
	 */
	private Date dateOfBirth;

	/**
	 * Employee's manager code
	 */
	private long managerCode;

	/**
	 * 
	 */
	private String clientCode;

	/**
	 * Gender for the employee
	 */
	private GenderType gender;

	/**
	 * 
	 */
	private String appCode;

	/**
	 * Qualification of Employee
	 */
	private Integer qualCode;

	/**
	 * Name of the organization of the employee
	 */
	private String orgName;

	/**
	 * Code of Employee's divison
	 */
	private String divCode;

	/**
	 * Job title of Employee
	 */
	private String jobTitle;

	/**
	 * Employee Role
	 */
	private String roleName;

	/**
	 * Employment Date
	 */
	private Date joiningDate;

	/**
	 * Employee status in organization
	 */
	private EmployeeStatusType status;

	/**
	 * To indentify type of employee
	 */
	private EmploymentType employeeIdentifier;

	/**
	 * Employee's unique identification
	 */
	private Integer userIdentifier;

	/**
	 * Employee's title
	 */
	private String namePrefix;

	/**
	 * Employee's name suffix
	 */
	private String nameSuffix;

	/**
	 * Termination date for the employee
	 */
	private Date terminationDate;
	/**
	 * Employee's primary address
	 */
	private Address employeeAdress;
	/**
	 * Employee's contact details
	 */
	private Contact employeeContact;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the managerCode
	 */
	public long getManagerCode() {
		return managerCode;
	}

	/**
	 * @param managerCode
	 *            the managerCode to set
	 */
	public void setManagerCode(long managerCode) {
		this.managerCode = managerCode;
	}

	/**
	 * @return the clientCode
	 */
	public String getClientCode() {
		return clientCode;
	}

	/**
	 * @param clientCode
	 *            the clientCode to set
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	/**
	 * @return the gender
	 */
	public GenderType getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode
	 *            the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the qualCode
	 */
	public Integer getQualCode() {
		return qualCode;
	}

	/**
	 * @param qualCode
	 *            the qualCode to set
	 */
	public void setQualCode(Integer qualCode) {
		this.qualCode = qualCode;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode() {
		return divCode;
	}

	/**
	 * @param divCode
	 *            the divCode to set
	 */
	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the joiningDate
	 */
	public Date getJoiningDate() {
		return joiningDate;
	}

	/**
	 * @param joiningDate
	 *            the joiningDate to set
	 */
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	/**
	 * @return the status
	 */
	public EmployeeStatusType getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(EmployeeStatusType status) {
		this.status = status;
	}

	/**
	 * @return employeeIdentifier
	 */
	public EmploymentType getEmployeeIdentifier() {
		return employeeIdentifier;
	}

	/**
	 * @param employeeIdentifier
	 */
	public void setEmployeeIdentifier(EmploymentType employeeIdentifier) {
		this.employeeIdentifier = employeeIdentifier;
	}

	/**
	 * @return userIdentifier
	 */
	public Integer getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * @param userIdentifier
	 */
	public void setUserIdentifier(Integer userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * @return namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * @return nameSuffix
	 */
	public String getNameSuffix() {
		return nameSuffix;
	}

	/**
	 * @param nameSuffix
	 */
	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	/**
	 * @return terminationDate
	 */
	public Date getTerminationDate() {
		return terminationDate;
	}

	/**
	 * @param terminationDate
	 */
	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * @return employeeAdress
	 */
	public Address getEmployeeAdress() {
		return employeeAdress;
	}

	/**
	 * @param employeeAdress
	 */
	public void setEmployeeAdress(Address employeeAdress) {
		this.employeeAdress = employeeAdress;
	}

	/**
	 * @return employeeContact
	 */
	public Contact getEmployeeContact() {
		return employeeContact;
	}

	/**
	 * @param employeeContact
	 */
	public void setEmployeeContact(Contact employeeContact) {
		this.employeeContact = employeeContact;
	}

}
