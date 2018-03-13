package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.EmployeeModelAssembler;
import com.cognizant.opserv.sp.core.dao.TPersAddrDAO;
import com.cognizant.opserv.sp.core.dao.TPersContactDAO;
import com.cognizant.opserv.sp.core.dao.TPersDAO;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.core.entity.TPersContact;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class EmployeeDAOServiceImpl contains all the DAO services of employee
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class EmployeeDAOServiceImpl implements EmployeeDAOService {
	
	@Autowired
	private TPersDAO tPersDAO;
    
	@Autowired
	private TPersContactDAO tPersContactDAO;
	
	@Autowired
	private TPersAddrDAO tPersAddrDAO;
	
	@Autowired
	private EmployeeModelAssembler employeeModelAssembler;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeDAOServiceImpl.class);

	/**
	 * @method getEmployeeDetails
	 * @param staffId
	 * @param tenentId
	 * @return Employee
	 * @throws OpservDataAccessException
	 */
	@Override
	public Employee getEmployeeDetails(Integer staffId , short tenentId) throws OpservDataAccessException {
		Employee employee = new Employee();
		
		try{
		   TPers tPers	=tPersDAO.findTPersByStaffId(staffId ,tenentId);
		    employee	=employeeModelAssembler.convertTPersToEmployeeModel(tPers);
				 
	    }catch (RuntimeException re) {
	    LOGGER.error("This issue is occurred while Fetching  Employee Details Fails."+re.getMessage());
		throw new OpservDataAccessException(				
				"This issue is occurred while Fetching  Employee Details Fails.", re);
		
	} 
		return employee;
	}

	  /**
	   * Update.
	   *
	   * @param employee the employee
	   * @param userDetails the user details
	   * @return the employee
	   */
	@Override
	public Employee updateTEmployee(Employee employee, UserDetails userDetails) {
		Employee tMpemployee = new Employee();
		TPersAddr tPersAddr =null;
		TPersContact persContact=null;
		TPers pers=null;
		try {
			if(null!=employee.getId()){
			TPers tPersEmp=tPersDAO.findTPersById(employee.getId().intValue());
			
			TPers tPers=employeeModelAssembler.updateEmployeeModelToTPers(employee,tPersEmp,userDetails);
			
			pers=tPersDAO.updateTPers(tPers);
			}
			if(null!=employee.getEmployeeAdress()){
			 TPersAddr persAddr=employeeModelAssembler.convertEmployeeModelToPersAddress(employee, pers, userDetails);
			  tPersAddr=tPersAddrDAO.updateTPersAddr(persAddr);			 
			}
			if(null!=employee.getEmployeeContact()){				
				TPersContact tPersContact=employeeModelAssembler.convertEmployeeModelToPersContactDetails(employee, pers, userDetails);
				 persContact=tPersContactDAO.updateTPersContact(tPersContact);
			}
				/*Set<TPersContact> tpersContactSet = new HashSet<TPersContact>();
				tpersContactSet.add(persContact);
				pers.setTPersContactss(tpersContactSet);				
				Set<TPersAddr> tpersAddSet = new HashSet<TPersAddr>();
				tpersAddSet.add(tPersAddr);
				pers.setTPersAddrss(tpersAddSet);*/
			
			//tMpemployee=EmployeeModelAssembler.convertTPersToEmployeeModel(pers);
			
			
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException("IOException", e);
			}
		
		return tMpemployee;
	}

  /**
	 * Creates Employee.
	 *
	 * @param employee the employee
	 * @param userDetails the user details
	 * @return the employee
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public Employee createEmployee(Employee employee, UserDetails userDetails)
			throws OpservDataAccessException {
		TPers tpers = null;
		TPersContact tPContact = null;
		TPersAddr tpersAddress = null;
		Employee emp = null;
		try {
			TPers tpersMaped =  employeeModelAssembler.convertEmployeeModelToPersDetails(employee, userDetails);
			tpers = tPersDAO.createTPers(tpersMaped);	
			if(null!= employee.getEmployeeContact()){
			TPersContact tPersContact = employeeModelAssembler.convertEmployeeModelToPersContactDetails(employee,tpers,userDetails);
			/*for(TPersContact tPersContact:tPersContactlist){
				TPersContact tPContact = new TPersContact();
				if(null!=tPersContact.getContactDetail() && !tPersContact.getContactDetail().isEmpty()){*/
					tPContact = tPersContactDAO.createTPersContact(tPersContact);
				/*}
			}*/
			}
			if(null!=employee.getEmployeeAdress() ){
			TPersAddr tpersAdd = employeeModelAssembler.convertEmployeeModelToPersAddress(employee,tpers,userDetails);
			tpersAddress = tPersAddrDAO.createTPersAddr(tpersAdd);
			}
			if(null!=tPContact){
			Set<TPersContact> tpersContactSet = new HashSet<TPersContact>();
			tpersContactSet.add(tPContact);
			tpers.setTPersContactss(tpersContactSet);
			}
			if(null!=tpersAddress){
			Set<TPersAddr> tpersAddSet = new HashSet<TPersAddr>();
			tpersAddSet.add(tpersAddress);
			tpers.setTPersAddrss(tpersAddSet);
			}
			emp = employeeModelAssembler.convertTPersToEmployeeModel(tpers);
			
		} catch (IOException e) {
			throw new OpservDataAccessException(				
					"IOException", e);
		}
		catch (RuntimeException re) {
			throw new OpservDataAccessException(				
					"Issue while saving.", re);
		} 
		return emp;
	}

	/**
	 * @method getEmployeeDetailsByUser
	 * @param userid
	 * @param tenentId
	 * @return Employee
	 * @throws OpservDataAccessException
	 */
	@Override
	public Employee getEmployeeDetailsByUser(Integer userid, short tenentId)
			throws OpservDataAccessException {
		Employee employee = new Employee();
		try{
		   TPers tPers	=tPersDAO.findTPersByUserId(userid ,tenentId);
		  
		    employee	=employeeModelAssembler.convertTPersToEmployeeModel(tPers);
				 
	    }catch (RuntimeException re) {
	    	LOGGER.error("This issue is occurred while Fetching  Employee Details Fails."+re.getMessage());	
	    	throw new OpservDataAccessException(				
				"This issue is occurred while Fetching  Employee Details Fails.", re);
	    } 
		return employee;
	}
	/**
	 * Fetch chang req approvers.
	 *
	 * @param chngReqId the chng req id
	 * @param apprTypeId the appr type id
	 * @param tenantId the tenant id
	 * @param allocTypeId the alloc type id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<Employee> fetchChangReqApprovers(Long chngReqId,
			Character targetAprFlg, Short tenantId, Integer allocTypeId)
			throws OpservDataAccessException {
		List<Employee> approverList = null;
		try {
			List<TPers> tPerList = tPersDAO.findChngReqApprovers(chngReqId,targetAprFlg, tenantId, allocTypeId);
			if(null != tPerList && !tPerList.isEmpty()){
				approverList = new ArrayList<Employee>();
				for (TPers tPers : tPerList) {
					approverList.add(employeeModelAssembler.convertTPersToEmployeeModel(tPers));
				}
			}
		} catch (Exception e) {
			LOGGER.error("This issue is occurred while Fetching  change request approvers."+e.getMessage());	
	    	throw new OpservDataAccessException(				
				"This issue is occurred while Fetching  change request approvers.", e);
		}
		return approverList;
	}
	/**
	 * Gets the emp name by user id.
	 *
	 * @param userid the userid
	 * @param tenentId the tenent id
	 * @return the emp name by user id
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public String getEmpNameByUserId(Integer userid, short tenentId)
			throws OpservDataAccessException {
		List<Object> params= new ArrayList<Object>();
		params.add(userid);
		params.add(tenentId);
		return tPersDAO.findTPersByUserIdSingleFrComm(params);
	}
	/**
	 * Find empployee by sales pos id.
	 *
	 * @param salesPosId the sales pos id
	 * @param allocTypeId the alloc type id
	 * @param tenantId the tenant id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<Employee> findEmpployeeBySalesPosId(Long salesPosId,
			Integer allocTypeId, Short tenantId)
			throws OpservDataAccessException {
			List<Employee> empList = null;
			try {
				List<TPers> tPerList = tPersDAO.findTPersBySalesPosId(salesPosId, allocTypeId, tenantId);
				if(null != tPerList && !tPerList.isEmpty()){
					empList = new ArrayList<Employee>();
					for (TPers tPers : tPerList) {
						empList.add(employeeModelAssembler.convertTPersToEmployeeModel(tPers));
					}
				}
			} catch (Exception e) {
				LOGGER.error("This issue is occurred while Fetching  change request approvers."+e.getMessage());	
		    	throw new OpservDataAccessException(				
					"This issue is occurred while Fetching  change request approvers.", e);
			}
		return empList;
	}
}



