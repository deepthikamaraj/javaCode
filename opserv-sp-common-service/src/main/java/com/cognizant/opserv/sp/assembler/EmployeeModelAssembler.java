package com.cognizant.opserv.sp.assembler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.model.auth.UserDetails;

import com.cognizant.opserv.sp.common.EmployeeStatusType;
import com.cognizant.opserv.sp.common.EmploymentType;
import com.cognizant.opserv.sp.common.GenderType;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.core.entity.TPersContact;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.Contact;
import com.cognizant.opserv.sp.model.Employee;

@Component
public class EmployeeModelAssembler {
	
	/** The address model assembler. */
	@Autowired
	AddressModelAssembler addressModelAssembler;

	/** The contact model assembler. */
	@Autowired
	ContactModelAssembler contactModelAssembler;
	/**
	 * Convert t pers to employee model.
	 * 
	 * @param tPers
	 *            the t pers
	 * @return the employee
	 */
	public Employee convertTPersToEmployeeModel(TPers tPers) {

		Employee employee = new Employee();

		if (null != tPers) {

			if (tPers.getBirthDt() != null && !tPers.getBirthDt().equals("")) {
				employee.setDateOfBirth(tPers.getBirthDt());
			}

			employee.setId(tPers.getStaffId().longValue());
			employee.setFirstName(tPers.getFirstName());
			employee.setMiddleName(tPers.getMiddleName());
			employee.setLastName(tPers.getLastName());
			employee.setName(tPers.getEmpName());
			employee.setCode(tPers.getIattainId());
			employee.setGender(GenderType.getGenderType(tPers.getGender()));
			employee.setOrgName(tPers.getOrgName());
			employee.setClientCode(tPers.getClientId());
			employee.setDivCode(tPers.getDivCd());
			employee.setEndDate(tPers.getEmpDt());
			employee.setJobTitle(tPers.getJobTitle());
			employee.setOrgName(tPers.getOrgName());
			employee.setRoleName(tPers.getRoleName());
			employee.setQualCode(tPers.getQualId());
			employee.setNamePrefix(tPers.getNamePfx());
			employee.setNameSuffix(tPers.getNameSfx());
			employee.setTerminationDate(tPers.getTermDt());
			employee.setJoiningDate(tPers.getEmpDt());
			employee.setCreatedBy(tPers.getCreatedBy());
			employee.setCreatedDate(tPers.getCreateDt());
			employee.setUpdatedBy(tPers.getUpdatedBy());

			if (null != tPers.getManagerId()) {
				employee.setManagerCode(tPers.getManagerId());
			}
			employee.setEmployeeIdentifier(EmploymentType
					.getEmploymentType(tPers.getEmpTypeId()));
			employee.setStatus(EmployeeStatusType.getEmployeeStatusType(tPers
					.getStatusId()));

			Set<TPersAddr> tPersAddr = tPers.getTPersAddrss();
			if (null != tPersAddr && tPersAddr.size() > 0) {
				Address address = addressModelAssembler
						.convertTPersAddrToAddressModel(tPersAddr);
				employee.setEmployeeAdress(address);
			}
			Set<TPersContact> tPersContact = tPers.getTPersContactss();
			if (null != tPersContact && tPersContact.size() > 0) {
				Contact contact = contactModelAssembler
						.convertTPerscontactToContactModel(tPersContact);
				employee.setEmployeeContact(contact);
			}
		}

		return employee;

	}

	/**
	 * Convert t pers details to employee model.
	 * 
	 * @param tPers
	 *            the t pers
	 * @return the employee
	 */
	public Employee convertTPersDetailsToEmployeeModel(TPers tPers) {

		Employee employee = new Employee();
		if (null != tPers) {
			employee.setId(tPers.getStaffId().longValue());
			employee.setFirstName(tPers.getFirstName());
			employee.setMiddleName(tPers.getMiddleName());
			employee.setLastName(tPers.getLastName());
			employee.setName(tPers.getFirstName() + " " + tPers.getLastName());
			employee.setOrgName(tPers.getOrgName());
			employee.setClientCode(tPers.getClientId());
			employee.setDivCode(tPers.getDivCd());
			employee.setEndDate(tPers.getEmpDt());
			employee.setJobTitle(tPers.getJobTitle());
			employee.setOrgName(tPers.getOrgName());
			if (null != tPers.getManagerId()) {
				employee.setManagerCode(tPers.getManagerId());
			}

			if (tPers.getEmpTypeId() == 1) {
				employee.setEmployeeIdentifier(EmploymentType.PERAMANENT);
			} else if (tPers.getEmpTypeId() == 2) {
				employee.setEmployeeIdentifier(EmploymentType.TEMPORARY);
			} else if (tPers.getEmpTypeId() == 3) {
				employee.setEmployeeIdentifier(EmploymentType.STANDARD);
			}
			if (tPers.getStatusId() == 1) {
				employee.setStatus(EmployeeStatusType.ACTIVE);
				employee.setActive(true);
			} else if (tPers.getStatusId() == 2) {
				employee.setStatus(EmployeeStatusType.INACTIVE);
			} else if (tPers.getStatusId() == 3) {
				employee.setStatus(EmployeeStatusType.UNASSIGNED);
			} else if (tPers.getStatusId() == 4) {
				employee.setStatus(EmployeeStatusType.ASSIGNED);
			} else if (tPers.getStatusId() == 5) {
				employee.setStatus(EmployeeStatusType.TERMINATED);
			}

		}

		return employee;

	}

	public TPers convertEmployeeModelToPersDetails(Employee employee,
			UserDetails userDetails) throws IOException {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		ByteArrayOutputStream baos = null;
		ObjectOutputStream objOstream = null;
		byte[] bArray = null;
		TPers tpers = new TPers();
		if (null != employee.getDateOfBirth()) {
			tpers.setBirthDtStr(df.format(employee.getDateOfBirth()));
		}
		// tpers.setStaffId(employee.getId().intValue());
		tpers.setClientId(employee.getClientCode());
		tpers.setCreatedBy(employee.getCreatedBy());
		tpers.setCreateDt(employee.getCreatedDate());
		tpers.setDivCd(employee.getDivCode());
		tpers.setEmpDt(employee.getCreatedDate());
		if (null != employee.getExtdAttributes()
				&& employee.getExtdAttributes().size() > 0) {
			baos = new ByteArrayOutputStream();
			objOstream = new ObjectOutputStream(baos);
			objOstream.writeObject(employee.getExtdAttributes());
			bArray = baos.toByteArray();
			tpers.setEmpExtAttrs(bArray);
		}	
		tpers.setEmpName(employee.getName());
		tpers.setEmpTypeId(employee.getEmployeeIdentifier().getId());
		tpers.setFirstName(employee.getFirstName());
		tpers.setGender(employee.getGender().getGender());
		tpers.setIattainId(employee.getCode());
		tpers.setJobTitle(employee.getJobTitle());
		tpers.setLastName(employee.getLastName());
		tpers.setManagerId((int) employee.getManagerCode());
		tpers.setMiddleName(employee.getMiddleName());
		tpers.setNamePfx(employee.getNamePrefix());
		tpers.setNameSfx(employee.getNameSuffix());
		tpers.setOrgName(employee.getOrgName());
		tpers.setQualId(employee.getQualCode());
		tpers.setRoleName(employee.getRoleName());
		tpers.setStatusId(employee.getStatus().getId());
		tpers.setTenantId(userDetails.getTenantId());
		tpers.setTermDt(employee.getTerminationDate());
		tpers.setTitle(employee.getJobTitle());
		tpers.setUserId(userDetails.getUserId());
		return tpers;
	}

	public TPersContact convertEmployeeModelToPersContactDetails(
			Employee employee, TPers tpers, UserDetails userDetails) {

		Contact empContact = employee.getEmployeeContact();
		// List<TPersContact> tperContList = new ArrayList<TPersContact>();
		Date date = new Date();
		TPersContact tPersContact = new TPersContact();
		tPersContact.setActiveFlag('Y');
		if (empContact.getEmail() != null) {
			tPersContact.setContactDetail(empContact.getEmail());
			tPersContact.setContactTypeId(1);
		} else if (empContact.getFax() != null) {
			tPersContact.setContactDetail(empContact.getFax());
			tPersContact.setContactTypeId(3);
		} else if (empContact.getPhone() != null) {
			tPersContact.setContactDetail(empContact.getPhone());
			tPersContact.setContactTypeId(2);
		} else {
			tPersContact.setContactDetail(empContact.getMobile());
			tPersContact.setContactTypeId(4);
		}
		tPersContact.setContactExtn(empContact.getContactExtension());

		tPersContact.setCreatedBy(empContact.getCreatedBy());
		tPersContact.setCreateDt(date);
		tPersContact.setStaffId(tpers.getStaffId());
		if(empContact.isPrimaryContact()){
		tPersContact.setPrContactFlag("Y");
		}
		else{
			tPersContact.setPrContactFlag("N");
		}
				
		tPersContact.setTenantId(userDetails.getTenantId());

		return tPersContact;
	}

	public TPersAddr convertEmployeeModelToPersAddress(
			Employee employee, TPers tpers, UserDetails userDetails) {
		Date date = new Date();
		Address address = employee.getEmployeeAdress();
		TPersAddr tpersAddr = new TPersAddr();
		tpersAddr.setActiveFlag('Y');
		tpersAddr.setAddrLine(address.getAddressLine());
		tpersAddr.setAddrLine2(address.getAddressLine2());
		tpersAddr.setAddrLine3(address.getAddressLine3());
		tpersAddr.setAddrLine4(address.getAddressLine4());
		tpersAddr.setCity(address.getCity());
		tpersAddr.setCntry(address.getCountry());
		tpersAddr.setCreatedBy(address.getCreatedBy());
		tpersAddr.setCreateDt(date);
		tpersAddr.setDoorNumber(address.getDoorNumber());
		tpersAddr.setPostalCd(address.getPostalCode());
		tpersAddr.setPostalExtn(address.getPostalExtension());
		if(address.isPrimaryAddr()){		
		tpersAddr.setPrAddrFlag("Y");
		}
		else{
			tpersAddr.setPrAddrFlag("N");
		}
		
		tpersAddr.setStaffId(tpers.getStaffId());
		tpersAddr.setAddrTypeId(address.getAddressType());
		tpersAddr.setState(address.getState());
		tpersAddr.setStreetName(address.getStreetName());
		tpersAddr.setTenantId(userDetails.getTenantId());
		return tpersAddr;

	}
	
	public TPers updateEmployeeModelToTPers(Employee employee,
		TPers tPers, UserDetails userDetails) throws IOException {
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	Date date = new Date();
	if (null != employee.getId()) {
		tPers.setStaffId(employee.getId().intValue());
		tPers.setFirstName(employee.getFirstName());
		tPers.setMiddleName(employee.getMiddleName());
		tPers.setLastName(employee.getLastName());
		 tPers.setTitle(employee.getJobTitle());
		if (null != employee.getDateOfBirth()) {
			tPers.setBirthDt(employee.getDateOfBirth());
			tPers.setBirthDtStr(df.format(employee.getDateOfBirth()));
		}

		tPers.setManagerId((int) employee.getManagerCode());
		/*if (null != employee.getUpdatedBy()) {
			tPers.setUpdatedBy(employee.getUpdatedBy());
		}*/
		if (null != employee.getUpdatedDate()) {
			tPers.setUpdateDt(employee.getUpdatedDate());
		}
		if (null != employee.getTenantId()) {
			tPers.setTenantId(employee.getTenantId());
		}
		if (null != employee.getClientCode()) {
			tPers.setClientId(employee.getClientCode());
		}
		// tPers.setIattainId(employee.get)
		if (GenderType.MALE == employee.getGender()) {
			tPers.setGender("1");
		}
		if (GenderType.FEMALE == employee.getGender()) {
			tPers.setGender("2");
		}
		if (null != employee.getQualCode()) {
			tPers.setQualId(employee.getQualCode());
		}
		tPers.setOrgName(employee.getOrgName());
		tPers.setDivCd(employee.getDivCode());
		tPers.setJobTitle(employee.getJobTitle());
		tPers.setRoleName(employee.getRoleName());
		if(null!=employee.getTerminationDate()){
		tPers.setEmpDt(employee.getTerminationDate());
		}
		if (null != employee.getStatus()) {
			tPers.setStatusId(employee.getStatus().getId());
		}
		if (null != employee.getEmployeeIdentifier()) {
			tPers.setEmpTypeId(employee.getEmployeeIdentifier().getId());
		}
		tPers.setEmpName(employee.getName());
		if (null != userDetails.getUserId()) {
			tPers.setUserId(userDetails.getUserId());
		}
		tPers.setNamePfx(employee.getNamePrefix());
		tPers.setNameSfx(employee.getNameSuffix());
		if(null!=employee.getTerminationDate()){
		tPers.setTermDt(employee.getTerminationDate());
		}
		//if(null!=tPers.getCreatedBy()){
			tPers.setUpdatedBy(userDetails.getUserId());
			tPers.setUpdateDt(date);
		//}else{
			tPers.setCreatedBy(userDetails.getUserId());
			tPers.setCreateDt(date);
		//}
		ByteArrayOutputStream baos = null;
		ObjectOutputStream objOstream = null;
		byte[] bArray = null;
		if (null!=employee.getExtdAttributes() && employee.getExtdAttributes().size() > 0) {
			baos = new ByteArrayOutputStream();
			objOstream = new ObjectOutputStream(baos);
			objOstream.writeObject(employee.getExtdAttributes());
			bArray = baos.toByteArray();
			tPers.setEmpExtAttrs(bArray);
		}

	}
	return tPers;
}
}
