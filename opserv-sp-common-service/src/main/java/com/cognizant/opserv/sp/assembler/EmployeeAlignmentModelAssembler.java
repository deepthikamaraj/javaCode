package com.cognizant.opserv.sp.assembler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.AllocationType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EmployeeStatusType;
import com.cognizant.opserv.sp.common.EmploymentType;
import com.cognizant.opserv.sp.common.GenderType;
import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.Contact;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Component
public class EmployeeAlignmentModelAssembler {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeAlignmentModelAssembler.class);
	
	@Autowired
	private EmployeeModelAssembler employeeModelAssembler;
	
	public List<EmployeeAlignment> convertTEmpAligmentsToModel(List<TEmpAlgmnt> tEmpAlgmntList, Map<Integer,String> orgRoles){
		List<EmployeeAlignment> employeeAlgmntList = new ArrayList<EmployeeAlignment>();
		
		if(null!=tEmpAlgmntList){
			for(TEmpAlgmnt tEmpAlg : tEmpAlgmntList){

				EmployeeAlignment empAlgmnt = new EmployeeAlignment();
				empAlgmnt = settingEmployeeAlignmentValues(tEmpAlg);
				
				//setting SalesOrgRole Values
				SalesOrgRole salesOrgRole= new SalesOrgRole();
				salesOrgRole.setId(tEmpAlg.getOrgRoleId().longValue());
				salesOrgRole.setActive(true);
				if(orgRoles != null){
					salesOrgRole.setName(orgRoles.get(tEmpAlg.getOrgRoleId()));
				}
				empAlgmnt.setOrgRole(salesOrgRole);
				
				//setting Person values
				Employee emp = employeeModelAssembler.convertTPersDetailsToEmployeeModel(tEmpAlg.getTPers());
				empAlgmnt.setEmployee(emp);
				
				employeeAlgmntList.add(empAlgmnt);
			}
		}
		return employeeAlgmntList; 
}
	
	public static Map<Integer,String> convertObjectListToOrgRole(List<Object[]> objList){
		Map<Integer,String> orgRoles = new HashMap<Integer,String>();

		for(Object[] obj : objList){
			orgRoles.put((Integer)obj[0], (String)obj[1]);
		}
		return orgRoles;
	}
	
	/*public static List<EmployeeAlignment> convertObjcListToTEmpAligments(List<Object[]> objList){
		List<EmployeeAlignment> employeeAlgmntList = new ArrayList<EmployeeAlignment>();

		for(Object[] obj : objList){
			EmployeeAlignment empAlgmnt = new EmployeeAlignment();

			TEmpAlgmnt tEmpAlg = (TEmpAlgmnt) obj[0];
			TSalesPos tSalesPos = (TSalesPos) obj[1];
			TPers tPers = (TPers) obj[2];

			
			//Setting employee Algmnt Values
			empAlgmnt = settingEmployeeAlignmentValues(tEmpAlg);
			
			//Setting Sales Position values
			SalesPosition salesPosition = convertTSalesPosToModel(tSalesPos);
			empAlgmnt.setSalesPosition(salesPosition);

			//setting Person values
			Employee emp = EmployeeModelAssembler.convertTPersDetailsToEmployeeModel(tPers);
			empAlgmnt.setEmployee(emp);
			
			
			employeeAlgmntList.add(empAlgmnt);

		}


		return employeeAlgmntList; 
}
	*/
	/*
	public static SalesPosition convertTSalesPosToModel(TSalesPos tSalesPos){
		
		
		BusinessUnit businessUnit = new BusinessUnit();
		if(tSalesPos.getTAlgmntSalesTeam() != null && tSalesPos.getTAlgmntSalesTeam().getTSalesTeam() != null && tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTSalesTeamId() != null){
			businessUnit.setId(tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTSalesTeamId().getBussUnitId());
			businessUnit.setName(tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTBussUnit().getBussUnitName());
		}
		SalesTeam salesTeam = new SalesTeam();
		if(tSalesPos.getTAlgmntSalesTeam() != null && tSalesPos.getTAlgmntSalesTeam().getTSalesTeam() != null && tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTSalesTeamId() != null){
			
			salesTeam.setId(tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getTSalesTeamId().getSalesTeamId());
			salesTeam.setName(tSalesPos.getTAlgmntSalesTeam().getTSalesTeam().getSalesTeamName());
			salesTeam.setBusinessUnit(businessUnit);
		}
		Alignment alignmment = new Alignment();
		if(tSalesPos.getTAlgmntSalesTeam() != null && tSalesPos.getTAlgmntSalesTeam().getTAlgmnt() != null ){
			alignmment.setId(tSalesPos.getTAlgmntSalesTeam().getTAlgmnt().getAlgmntId());
			alignmment.setName(tSalesPos.getTAlgmntSalesTeam().getTAlgmnt().getAlgmntName());
		}
		alignmment.setSalesTeam(salesTeam);
		
		SalesPosition salesPosition =new SalesPosition();
		salesPosition.setId(tSalesPos.getSalesPosId());
		salesPosition.setName(tSalesPos.getPosName());
		salesPosition.setCode(tSalesPos.getPosCd());
		salesPosition.setCreatedBy(tSalesPos.getCreatedBy());
		salesPosition.setCreatedDate(tSalesPos.getCreateDt());
		salesPosition.setUpdatedDate(tSalesPos.getUpdateDt());
		salesPosition.setUpdatedBy(tSalesPos.getUpdatedBy());
		//parent Sales Pos
		SalesPosition prnSalesPosition =new SalesPosition();
		prnSalesPosition.setId(tSalesPos.getPrnSalesPosId());
		salesPosition.setParentSalesPosition(prnSalesPosition);
		
		salesPosition.setAlignmment(alignmment);
		
		
		
		return salesPosition;
		
		
	}*/
	
	/**
	 * To value object.
	 *
	 * @param EmployeeAlignment json 
	 * @return the EmployeeAlignment Model Object dts
	 */
	public static List<EmployeeAlignment> convertJSONEmployeeAlignmentToModel(List<com.cognizant.opserv.sp.json.EmployeeAlignment> employeeAlignmentJSONList){
		List<EmployeeAlignment> employeeAlgmntList = new ArrayList<EmployeeAlignment>();
		if(null != employeeAlignmentJSONList && !employeeAlignmentJSONList.isEmpty()){
			for(com.cognizant.opserv.sp.json.EmployeeAlignment alignment:employeeAlignmentJSONList){
				EmployeeAlignment employeeAlignment=new EmployeeAlignment();
				if (null != alignment.getEmpAlignId()){
					employeeAlignment.setId(alignment.getEmpAlignId());
					Employee employee=new Employee();
					employee.setId((Long) (alignment.getEmpId()));
					employee.setFirstName(alignment.getFirstName() == null ? "" : alignment.getFirstName());
					employee.setMiddleName(alignment.getMiddleName() == null ? "" : alignment.getMiddleName());
					employee.setLastName(alignment.getLastName() == null ? "" : alignment.getLastName());
					//start -- setting the dob
					if(null != alignment.getDateOfBirth()){
						String decryptedDOB = decryptor(alignment.getDateOfBirth());
						employee.setDateOfBirth(formatDateString(decryptedDOB));
					}
					//employee.setDateOfBirth(alignment.getDateOfBirth());
					//end -- setting the dob 
					if(null != alignment.getManagerCode()){
						employee.setManagerCode(Long.valueOf(alignment.getManagerCode()));
					}
					employee.setClientCode(alignment.getClientCode() == null ? "" : alignment.getClientCode());
					if(null != alignment.getGender()){
						if(null != alignment.getGender()  && alignment.getGender().equals(CommonConstants.VALUE_1)){
						employee.setGender(GenderType.MALE);
						}else{
							employee.setGender(GenderType.FEMALE);
						}
					}
					employee.setAppCode(alignment.getAppCode() == null ? "" : alignment.getAppCode());
					if(null != alignment.getQualCode())
					employee.setQualCode(Integer.valueOf(alignment.getQualCode()));
					employee.setOrgName(alignment.getOrgName() == null ? "" : alignment.getOrgName());
					employee.setDivCode(alignment.getDivCode() == null ? "" : alignment.getDivCode());
					employee.setJobTitle(alignment.getJobTitle() == null ? "" : alignment.getJobTitle());
					employee.setRoleName(alignment.getRoleName() == null ? "" : alignment.getRoleName());
					if (null != alignment.getJoiningDate()) {
						employee.setJoiningDate(alignment.getJoiningDate());
					}
					if (null != alignment.getUserIdentifier()) {
						employee.setUserIdentifier(alignment.getUserIdentifier());
					}
					employee.setNamePrefix(alignment.getNamePrefix() == null ? "" : alignment.getNamePrefix());
					employee.setNameSuffix(alignment.getNameSuffix() == null ? "" : alignment.getNameSuffix());
					if (null != alignment.getTerminationDate()) {
						employee.setTerminationDate(alignment.getTerminationDate());
					}
					if(null != alignment.getEmployeeStatus() && alignment.getEmployeeStatus().equals(CommonConstants.VALUE_1)){
					employee.setStatus(EmployeeStatusType.ACTIVE);
					}else if(null != alignment.getEmployeeStatus() && alignment.getEmployeeStatus().equals(CommonConstants.VALUE_2)){
						employee.setStatus(EmployeeStatusType.INACTIVE);
					}else if(null != alignment.getEmployeeStatus() && alignment.getEmployeeStatus().equals(CommonConstants.VALUE_3)){
						employee.setStatus(EmployeeStatusType.UNASSIGNED);
					}else if(null != alignment.getEmployeeStatus() && alignment.getEmployeeStatus().equals(CommonConstants.VALUE_4)){
						employee.setStatus(EmployeeStatusType.ASSIGNED);
					}else{
						employee.setStatus(EmployeeStatusType.TERMINATED);
					}
					if(null != alignment.getEmploymentType() && alignment.getEmploymentType().equals(CommonConstants.VALUE_1)){
						employee.setEmployeeIdentifier(EmploymentType.PERAMANENT);
					}else if(null != alignment.getEmploymentType() && alignment.getEmploymentType().equals(CommonConstants.VALUE_2)){
						employee.setEmployeeIdentifier(EmploymentType.TEMPORARY);
					}else if(null != alignment.getEmploymentType() && alignment.getEmploymentType().equals(CommonConstants.VALUE_3)){
						employee.setEmployeeIdentifier(EmploymentType.STANDARD);
					}
					Address employeeAdress=new Address();
					//employeeAdress.setCustomer(alignment.get);
					employeeAdress.setDoorNumber(alignment.getAddressLine2() == null ? "" : alignment.getAddressLine2());
					employeeAdress.setAddressLine(alignment.getAddressLine1() == null ? "" : alignment.getAddressLine2());
					employeeAdress.setStreetName(alignment.getCity() == null ? "" : alignment.getCity());
					employeeAdress.setCity(alignment.getCity() == null ? "" : alignment.getCity());
					employeeAdress.setState(alignment.getState() == null ? "" : alignment.getState());
					employeeAdress.setPrimaryAddr(true);
					//employeeAdress.setAddressType();
					employeeAdress.setPostalCode(alignment.getPostalCode());
					//employeeAdress.setPostalExtension();				
					employee.setEmployeeAdress(employeeAdress);
					
					Contact contact=new Contact();
					contact.setEmail(decryptor(alignment.getEmail()));
					contact.setPhone(decryptor(alignment.getPhone()));
					contact.setFax(decryptor(alignment.getFax()));
					contact.setMobile(decryptor(alignment.getMobile()));
					//contact.setCustomer(alignment.get);
					contact.setPrimaryContact(true);
				//	contact.setContactExtension(alignment.getc);
					employee.setEmployeeContact(contact);
					
					employeeAlignment.setEmployee(employee);
					if(null != alignment.getSalesOrgRole()){
						SalesOrgRole salesOrgRole=new SalesOrgRole();
						salesOrgRole.setId(Long.valueOf(alignment.getSalesOrgRole()));		
						employeeAlignment.setOrgRole(salesOrgRole);
					}
					if(null != alignment.getAllocationType() && alignment.getAllocationType().equals(CommonConstants.VALUE_1))
					{
						employeeAlignment.setAllocation(AllocationType.PRIMARY);
					}else{
						employeeAlignment.setAllocation(AllocationType.SECONDARY);
					}
					
					SalesPosition salesPosition=new SalesPosition();
					salesPosition.setId(alignment.getSalesposId());				
					employeeAlignment.setSalesPosition(salesPosition);
					
					employeeAlgmntList.add(employeeAlignment);
				}
			}
			
		}
		return employeeAlgmntList;
		
	}
	
	private static String decryptor(String dobJsonEncrypted) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("password");                     
        encryptor.setAlgorithm("PBEWithMD5AndDES");
		return encryptor.decrypt(dobJsonEncrypted);
	}
	
	private static Date formatDateString(String dobJsonDecrypted) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
		Date dob = null;
		try {
			dob = formatter.parse(dobJsonDecrypted);
		} catch (ParseException e) {
			LOGGER.error("Error in StringToDateParsing", e);
		}
		
	return dob;
	}
	
	public static EmployeeAlignment settingEmployeeAlignmentValues(TEmpAlgmnt tEmpAlg){
		EmployeeAlignment empAlgmnt = new EmployeeAlignment();
		empAlgmnt.setEmpAlgmntId(tEmpAlg.getEmpAlgmntId());

		if(ApplicationConstant.FLAG_YES.equals( tEmpAlg.getActiveFlag()) || ApplicationConstant.FLAG_y.equals(tEmpAlg.getActiveFlag())){
			empAlgmnt.setActive(true);
		}else{
			empAlgmnt.setActive(false);
		}
		if(tEmpAlg.getAllocTypeId() == 1){
			empAlgmnt.setAllocation(AllocationType.PRIMARY);
		}else{
			empAlgmnt.setAllocation(AllocationType.SECONDARY);
		}
		empAlgmnt.setAllocPercentage(tEmpAlg.getAllocPct());
		empAlgmnt.setCreatedBy(tEmpAlg.getCreatedBy());
		empAlgmnt.setEndDate(tEmpAlg.getEffEndDt());
		
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(tEmpAlg.getSalesPosId());
		salesPosition.setActive(true);
		SalesOrgHierarchy salesOrgHier = new SalesOrgHierarchy();
		salesOrgHier.setId(tEmpAlg.getSalesHierId());
		salesOrgHier.setActive(true);
		salesPosition.setHierarchy(salesOrgHier);
		//adding Al, Bu, St ids
		BusinessUnit businessUnit = new BusinessUnit();
		businessUnit.setId(tEmpAlg.getBussUnitId());
		businessUnit.setActive(true);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(tEmpAlg.getSalesTeamId());
		salesTeam.setActive(true);
		salesTeam.setBusinessUnit(businessUnit);
		Alignment alignmment = new Alignment();
		alignmment.setActive(true);
		alignmment.setId(tEmpAlg.getAlgmntId());
		alignmment.setSalesTeam(salesTeam);
		salesPosition.setAlignmment(alignmment);
		empAlgmnt.setSalesPosition(salesPosition);
		
		empAlgmnt.setStartDate(tEmpAlg.getEffStartDt());
		empAlgmnt.setTenantId(tEmpAlg.getTenantId());
		empAlgmnt.setUpdatedBy(tEmpAlg.getUpdatedBy());
		empAlgmnt.setUpdatedDate(tEmpAlg.getUpdateDt());
		
		return empAlgmnt;
	}
}
