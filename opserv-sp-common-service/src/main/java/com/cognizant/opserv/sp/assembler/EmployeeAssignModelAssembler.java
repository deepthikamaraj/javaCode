package com.cognizant.opserv.sp.assembler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.AllocationType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.EmployeeStatusType;
import com.cognizant.opserv.sp.common.SystemRoleType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesRoleDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TAttrGroup;
import com.cognizant.opserv.sp.core.entity.TAttrGroupMap;
import com.cognizant.opserv.sp.core.entity.TBussObjTmpl;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.core.entity.TEmpType;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
@Component
public class EmployeeAssignModelAssembler {
		
	/** The address model assembler. */
	@Autowired
	AddressModelAssembler addressModelAssembler;
	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAssignModelAssembler.class);
	
	@Autowired
	TAlgmntSalesRoleDAO tAlgmntSalesRoleDAO;
	
	public TEmpAlgmnt convertEmployeeAssigntoTEmpAlgmnt(
			EmployeeAlignment empVO, UserDetails userDetails, TPers tPers) {

		Date curDate = DateUtil.getCurrentDate(); 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		TEmpAlgmnt tEmpAlgmnt = new TEmpAlgmnt();
		 if(null!=tPers){
				
				tEmpAlgmnt.setTPers(tPers);
				//tEmpAlgmnt.setActiveFlag('Y');
				if(null!=empVO.getSalesPosition().getAlignmment().getId()){
				tEmpAlgmnt.setAlgmntId(empVO.getSalesPosition().getAlignmment().getId());
				}
				tEmpAlgmnt.setActiveFlag(CommonConstants.ACTIVE_Y);
				tEmpAlgmnt.setAllocPct(empVO.getAllocPercentage());
				if(null!=empVO.getAllocation().getId()){
				tEmpAlgmnt.setAllocTypeId(empVO.getAllocation().getId());
				}
				if(null!=empVO.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId()){
				tEmpAlgmnt.setBussUnitId(empVO.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId());
				}
				if(null!=empVO.getSalesPosition().getAlignmment().getSalesTeam().getId()){
				tEmpAlgmnt.setSalesTeamId(empVO.getSalesPosition().getAlignmment().getSalesTeam().getId());
				}
				if(null!=userDetails.getUserId()){
				tEmpAlgmnt.setCreatedBy(userDetails.getUserId());
				}
				tEmpAlgmnt.setCreateDt(curDate);
				if(empVO.getEndDate() != null){
				tEmpAlgmnt.setEffEndDt(empVO.getEndDate());

				tEmpAlgmnt.setEffStartDt(empVO.getStartDate());	

				}
				if(empVO.getStartDate().after(curDate)){
					tEmpAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N);
				}
				tEmpAlgmnt.setEffStartDt(curDate);
				if(null!=empVO.getSalesPosition().getHierarchy().getId()){
				tEmpAlgmnt.setSalesHierId(empVO.getSalesPosition().getHierarchy().getId());
				}
				if(null!=empVO.getSalesPosition().getId()){
				tEmpAlgmnt.setSalesPosId(empVO.getSalesPosition().getId());
				}
				if(SystemRoleType.ADMIN==empVO.getSystemRole()){
					tEmpAlgmnt.setSysRoleId(1); //TODO GEt from UM Mgmnt

				}else if(SystemRoleType.HOMEOFFICE==empVO.getSystemRole()){
					tEmpAlgmnt.setSysRoleId(2);
				}else if(SystemRoleType.MANAGER==empVO.getSystemRole()){
					tEmpAlgmnt.setSysRoleId(3);
	
				}else if(SystemRoleType.REPRESENTATIVE==empVO.getSystemRole()){
					tEmpAlgmnt.setSysRoleId(4);

				}
				tEmpAlgmnt.setTenantId(userDetails.getTenantId());
				tEmpAlgmnt.setCreatedBy(userDetails.getUserId());
				
				
				
				
			//	tEmpAlgmnt.setOrgRoleId(alignmentInfo.getOrgRole().getId().intValue());	

		 }
		
		 
		 return tEmpAlgmnt;
		
	
	}

	public List<EmployeeAlignment> convertTEmpDetailsToModel(List<Object[]> list,Short tenantId){
		List<EmployeeAlignment> employeeAlgmntList = new ArrayList<EmployeeAlignment>();
		if(list != null && !list.isEmpty()){
		Date curDate = DateUtil.getCurrentDate();
		for(Object[] obj : list){
			Date stDt = (Date) obj[1];
			EmployeeAlignment empAlgmnt = new EmployeeAlignment();
			Employee emp = new Employee();
			if(stDt.compareTo(curDate) > 0 ){
				emp.setStatus(EmployeeStatusType.ASSIGNED);				
			}else if(stDt.compareTo(curDate) == 0 ){
				emp.setStatus(EmployeeStatusType.ACTIVE);
			}
			empAlgmnt.setEmpAlgmntId((Long)obj[0]);
			emp.setStartDate(stDt);
			
			
			if( obj[2] != null ){
				Date edDt = (Date) obj[2];
				emp.setEndDate(edDt);
				
				if(edDt.compareTo(stDt) < 0){ // FOR TERMINATED EMP ALIGMNETS I.E., EFF ST DT IS AFTER EFF END DATE
					continue ;				  // DO NOT CONSIDER THAT ALIGNMENTS
				}
				
				if(edDt.compareTo(curDate) <= 0){
					emp.setStatus(EmployeeStatusType.INACTIVE);
				}
				if(stDt.compareTo(curDate) <= 0 && edDt.compareTo(curDate) > 0){
					emp.setStatus(EmployeeStatusType.ACTIVE);
				}
			}else{
				//emp.setEndDate(EmployeeConstants.NOT_APPLICABLE);
				if(stDt.compareTo(curDate) < 0){
					emp.setStatus(EmployeeStatusType.ACTIVE);
				}
			}
			
			//Staffid
			
			Integer stafId = (Integer)obj[3];
			emp.setId(stafId.longValue());			
			emp.setCode((String) obj[9]);
			StringBuffer concatCustName = new StringBuffer(CommonConstants.EMPTY);
			//String concatCustName =EmployeeConstants.EMPTY_STRING;
			String empName = null;
			String firstName = null;
			String middleName = null;
			String lastName = null;
			
			if(obj[7] != null){
				empName = (String)obj[7];
			}
			if(obj[4] != null){
				firstName = (String)obj[4];
			}
			if(obj[5] != null){
				middleName = (String)obj[5];
			}
			if(obj[6] != null){
				lastName = (String)obj[6];
			}
			
			if(empName != null && !empName.trim().equals(CommonConstants.EMPTY) &&
					!empName.equals(CommonConstants.NA)){
				emp.setName(empName.trim().toUpperCase());
			} else {
				if(firstName != null && !firstName.trim().equals(CommonConstants.EMPTY) &&
						!firstName.equals("{null}") && 
						!firstName.equals(CommonConstants.NA)){
					concatCustName.append(firstName.toUpperCase().trim());
				}
				if(middleName != null && !middleName.trim().equals(CommonConstants.EMPTY) &&
						!middleName.equals("{null}") &&
						!middleName.equals(CommonConstants.NA)){
					//concatCustName = concatCustName + " " + middleName.toUpperCase().trim();
					concatCustName.append(CommonConstants.EMPTY).append(middleName.toUpperCase().trim());
				}
				if(lastName != null && !lastName.trim().equals(CommonConstants.EMPTY) &&
						!lastName.equals("{null}") &&
						!lastName.equals(CommonConstants.NA)){
					//concatCustName = concatCustName + " " + lastName.toUpperCase().trim();
					concatCustName.append(CommonConstants.EMPTY).append(lastName.toUpperCase().trim());
				}
				emp.setName(concatCustName.toString());
			}
			
			empAlgmnt.setAllocation(AllocationType.getAllocationType((Integer)obj[11]));
			
			if(1==(Integer)obj[8]){				
				emp.setActive(true);
			}
			else{
				emp.setActive(false);
			}			
			List<Integer> staffId = new ArrayList<Integer>();
			staffId.add((Integer) obj[3]);
			/*List<TPersAddr> addList = tPersAddrDAO.findPriAddressForEmp(staffId, tenantId, CommonConstants.ACTIVE_FLAG,CommonConstants.ACTIVE_Y);
			if(addList != null && !addList.isEmpty()){
				Address address = addressModelAssembler.convertTPersAddrToAddressModel(new HashSet((Collection) addList.get(0)));
					emp.setEmployeeAdress(address);
			}*/
			empAlgmnt.setEmployee(emp);
			employeeAlgmntList.add(empAlgmnt);
			}		
			
		
		}

		return employeeAlgmntList; 
}
	
	public List<EmpSearch> convertTEmpTypesToModel(List<TEmpType> typeList){
		
		List<EmpSearch> list = new ArrayList<EmpSearch>();
		if (typeList != null && !typeList.isEmpty()) {
			for (TEmpType tEmpType : typeList) {
				EmpSearch empSrcVO = new EmpSearch();
				empSrcVO.setEmpTypeId(tEmpType.getEmpTypeId());
				empSrcVO.setEmpType(tEmpType.getEmpTypeCd());
				list.add(empSrcVO);
			}
		}
		return list;
		
	}
	
	public List<Employee> convertTPersToEmployee(List<Employee> empList,
			List<Object[]> tPersList, Short tenantId,String localeId,List<TPersAddr> tPersAddrList){
				
			
			Employee empVO;
			Set<TPersAddr> tperSet = new HashSet<TPersAddr>();
			List<Employee> empVOList =  new ArrayList<Employee>();
			if (tPersList != null) {

				for (Object[] obj : tPersList) {
					TPersAddr tPersAddr = new TPersAddr();
					try {
						for(TPersAddr persAddr : tPersAddrList){
							if(persAddr.getStaffId().intValue() == ((Integer)obj[0]).intValue()){
								tPersAddr = persAddr;
								break;
							}
						}
					} catch (Exception e) {
						LOGGER.info("exception is "+e.getMessage());
						throw e ;
					}
					empVO = new Employee();

					empVO.setCode((String)obj[5]);
					StringBuffer concatCustName = new StringBuffer(CommonConstants.EMPTY);
					//String concatCustName =EmployeeConstants.EMPTY_STRING;
					String empName = null;
					String firstName = null;
					String middleName = null;
					String lastName = null;
					
					if(obj[1] != null){
						empName = (String)obj[1];
					}
					if(obj[2] != null){
						firstName = (String)obj[2];
					}
					if(obj[3] != null){
						middleName = (String)obj[3];
					}
					if(obj[4] != null){
						lastName = (String)obj[4];
					}
					
					if(empName != null && !empName.trim().equals(CommonConstants.EMPTY) &&
							!empName.equals(CommonConstants.NA)){
						empVO.setName(empName.trim().toUpperCase());
					} else {
						if(firstName != null && !firstName.trim().equals(CommonConstants.EMPTY) &&
								!firstName.equals("{null}") && 
								!firstName.equals(CommonConstants.NA)){
							concatCustName.append(firstName.toUpperCase().trim());
							//concatCustName = firstName.toUpperCase().trim();
						}
						if(middleName != null && !middleName.trim().equals(CommonConstants.EMPTY) &&
								!middleName.equals("{null}") &&
								!middleName.equals(CommonConstants.NA)){
							//concatCustName = concatCustName + " " + middleName.toUpperCase().trim();
							concatCustName.append(CommonConstants.EMPTY).append(middleName.toUpperCase().trim());
						}
						if(lastName != null && !lastName.trim().equals(CommonConstants.EMPTY) &&
								!lastName.equals("{null}") &&
								!lastName.equals(CommonConstants.NA)){
							//concatCustName = concatCustName + " " + lastName.toUpperCase().trim();
							concatCustName.append(CommonConstants.EMPTY).append(lastName.toUpperCase().trim());
						}
						empVO.setName(concatCustName.toString());
					}
					Integer stafId = (Integer)obj[0];
					empVO.setId(stafId.longValue());
					if (tPersAddr != null) {
						tperSet.add(tPersAddr);
						Address address = addressModelAssembler.convertTPersAddrToAddressModel(tperSet);
						empVO.setEmployeeAdress(address);
						}

					if(obj[7] != null){
						empVO.setStartDate((Date) obj[7]);
					}else{
						empVO.setStartDate(null);
					}
					empVO.setEndDate(null);
					empVO.setActive(CommonConstants.TRUE);
					
					empVOList .add(empVO);
				}

			}
			LOGGER.info("********************** ends convertTPersToEmployee******************************  ");
			return empVOList;
			
		
		
	}
	
public List<AttributeDefinition> convertTAlgmntTmplToAttributeDefn(List<TAlgmntTmpl> tmplList){
	List<Long> atrList = new ArrayList<Long>();
	List<AttributeDefinition> list = new ArrayList<AttributeDefinition>();
	if (!tmplList.isEmpty()) {
		for (TAlgmntTmpl tmpl : tmplList) {
			TBussObjTmpl tBussObjTmpl = tmpl.getTBussObjTmpl();
			Set<TAttrGroup> tmplGroups = tBussObjTmpl.getTAttrGroupss();
			if (!tmplGroups.isEmpty()) {
				for (TAttrGroup tAttrGroup : tmplGroups) {
					Set<TAttrGroupMap> groupMaps = tAttrGroup
							.getTAttrGroupMapss();
					if (!groupMaps.isEmpty()) {
						for (TAttrGroupMap tAttrGroupMap : groupMaps) {
							if (  !atrList.contains(tAttrGroupMap
											.getTAttrDef().getAttrId())
									&& tAttrGroupMap
											.getVisibleFlag()
											.equals(CommonConstants.ACTIVE_FLAG) && tAttrGroupMap
											.getTAttrDef().getDynAttrFlag()
											.equals(CommonConstants.ACTIVE_FLAG) && tAttrGroupMap
											.getActiveFlag()
											.equals(CommonConstants.ACTIVE_FLAG) ) {
								AttributeDefinition attrDef = new AttributeDefinition();
								attrDef.setName(tAttrGroupMap
										.getTAttrDef().getAttrDesc());
								attrDef.setId(tAttrGroupMap
										.getTAttrDef().getAttrId());
								// for group id
								
								/*attrDef.setOrder(tAttrGroupMap
										.getTAttrGroup()
										.getAttrGroupId())*/;
								atrList.add(tAttrGroupMap.getTAttrDef()
										.getAttrId());
								list.add(attrDef);
							}
						}
					}
				}
			}
		}
	}
	
	return list;
}
	
}
