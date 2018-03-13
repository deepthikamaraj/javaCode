package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.AddressModelAssembler;
import com.cognizant.opserv.sp.assembler.EmployeeAssignModelAssembler;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesRoleDAO;
import com.cognizant.opserv.sp.core.dao.TAlgmntTmplDAO;
import com.cognizant.opserv.sp.core.dao.TEmpAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TEmpTypeDAO;
import com.cognizant.opserv.sp.core.dao.TPersAddrDAO;
import com.cognizant.opserv.sp.core.dao.TPersDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRole;
import com.cognizant.opserv.sp.core.entity.TAlgmntTmpl;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.core.entity.TEmpType;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPersAddr;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.model.Address;
import com.cognizant.opserv.sp.model.EmpSearch;
import com.cognizant.opserv.sp.model.Employee;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.attrb.AttributeDefinition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeAssignDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Component
public class EmployeeAssignDAOServiceImpl implements EmployeeAssignDAOService {
	@Autowired
	private TPersDAO tPersDAO;
	
	@Autowired
	private TAlgmntSalesRoleDAO tAlgmntSalesRoleDAO;
	
	@Autowired
	private TEmpAlgmntDAO tEmpAlgmntDAO;

    @Autowired
	private TPersAddrDAO tPersAddrDAO;
	
	@Autowired
	private TEmpTypeDAO tEmpTypeDAO;
		
	@Autowired
	TSalesPosDAO tSalesPosDAO;
	
	@Autowired
	TAlgmntTmplDAO tAlgmntTmplDAO;
	
	/** The address model assembler. */
	@Autowired
	AddressModelAssembler addressModelAssembler;
	
	/** The employee assign model assembler. */
	@Autowired
	EmployeeAssignModelAssembler employeeAssignModelAssembler;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EmployeeAssignDAOServiceImpl.class);

	/**
	 * Assgin employee emp.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the integer
	 */
	@Override
	public Integer assgEmp(List<EmployeeAlignment> empList,
			UserDetails userDetails) {
		TEmpAlgmnt empAlgmnt = null;
		int empAlgmntId = 0;
		try {
			Date curDate = DateUtil.getCurrentDate();
			if (empList != null && !empList.isEmpty()) {
				for (EmployeeAlignment empVO : empList) {
					
					TEmpAlgmnt tEmpAlgmnt=new TEmpAlgmnt();

					TPers tPers = tPersDAO.findTPersById(empVO.getEmployee().getId().intValue());
					tEmpAlgmnt=employeeAssignModelAssembler.convertEmployeeAssigntoTEmpAlgmnt(empVO,userDetails, tPers);
					List<TAlgmntSalesRole> list = tAlgmntSalesRoleDAO.getActiveTAlgmntSalesRole(empVO.getSalesPosition().getAlignmment().getId(),empVO.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(), empVO.getSalesPosition().getAlignmment().getSalesTeam().getId(),
							empVO.getSalesPosition().getHierarchy().getId(), userDetails.getTenantId(), CommonConstants.ACTIVE_Y);
					Integer orgRoleId = 0;
					if(list != null && !list.isEmpty()){
						for(TAlgmntSalesRole tAlgmntSalesRole : list ){
							orgRoleId = tAlgmntSalesRole.getTAlgmntSalesRoleId().getOrgRoleId();
							tEmpAlgmnt.setOrgRoleId(orgRoleId);		
						}
					
					}
					empAlgmnt = tEmpAlgmntDAO.createTEmpAlgmnt(tEmpAlgmnt);
					if(null!=empAlgmnt){
					empAlgmntId=empAlgmnt.getEmpAlgmntId().intValue();
					}
					
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		return empAlgmntId;
	}



	/**
	 * Validate t emp assg.
	 *
	 * @param empList the emp list
	 * @param userDetails the user details
	 * @return the map
	 * @throws ParseException the parse exception
	 */
	@Override
	public Map<String, String> validateTEmpAssg(List<EmployeeAlignment> empList,
			UserDetails userDetails) {
		
		Map<String, String>map=new HashMap<String, String>();
		StringBuffer buffer=new StringBuffer();
		//Validation For Secondary Assignments 

		if(null!=empList && !empList.isEmpty()){
		for(EmployeeAlignment eAssinmt:empList){			
			List<Object[]>almntList=tEmpAlgmntDAO.findEmpAlgmntByIds(eAssinmt.getEmployee().getId(), eAssinmt.getSalesPosition().getAlignmment().getId(), 
					eAssinmt.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(), eAssinmt.getSalesPosition().getAlignmment().getSalesTeam().getId(), eAssinmt.getSalesPosition().getId(), eAssinmt.getSalesPosition().getHierarchy().getId(), userDetails.getTenantId());
				if (null != almntList && !almntList.isEmpty()) {
					for (Object[] obj : almntList) {
						if (!(eAssinmt.getId() != null && eAssinmt.getId()
								.equals(obj[0]))) {
							if (eAssinmt.getEmployee().getId().intValue() == ((Integer) obj[1])
									.intValue()) {
								if (eAssinmt.getStartDate().compareTo(
										(Date) obj[2]) == 0
										|| eAssinmt.getEndDate().compareTo(
												(Date) obj[3]) == 0
										|| eAssinmt.getStartDate().compareTo(
												(Date) obj[3]) == 0
										|| eAssinmt.getEndDate().compareTo(
												(Date) obj[2]) == 0) {
									map.put("code", "350");
									buffer.append(eAssinmt.getEmployee()
											.getFirstName()
											+ " is already assigned to this sales position from "
											+ (Date) obj[2]
											+ " to "
											+ (Date) obj[3]
											+ ". Please change the start & end dates. ");

								} else if (eAssinmt.getStartDate().compareTo(
										(Date) obj[2]) < 0) {
									if (eAssinmt.getEndDate().compareTo(
											(Date) obj[2]) > 0) {
										map.put("code", "350");
										buffer.append(eAssinmt.getEmployee()
												.getFirstName()
												+ " is already assigned to this sales position from "
												+ (Date) obj[2]
												+ " to "
												+ (Date) obj[3]
												+ ". Please change the start & end dates. ");
									}

								} else if (eAssinmt.getStartDate().compareTo(
										(Date) obj[2]) > 0) {
									if (eAssinmt.getStartDate().compareTo(
											(Date) obj[3]) < 0
											|| eAssinmt.getEndDate().compareTo(
													(Date) obj[3]) < 0) {
										map.put("code", "350");
										buffer.append(eAssinmt.getEmployee()
												.getFirstName()
												+ " is already assigned to this sales position from "
												+ (Date) obj[2]
												+ " to "
												+ (Date) obj[3]
												+ ". Please change the start & end dates. ");
									}
								}
							}
						}
					}
				}
				System.out.println("Secondary Assignments " + almntList.size());
			}		
		}
		
		
		
		
		
		//Validation For Primary Assignments
		if (null != empList && !empList.isEmpty()) {
			for (EmployeeAlignment eAssinmt : empList) {
				if(eAssinmt.getAllocation().getId()== CommonConstants.PRIMARY_ALLOC_ID){
					List<Object[]> primList = tEmpAlgmntDAO.findPrimaryTEmpAlgmnt(eAssinmt.getSalesPosition().getAlignmment().getId(),eAssinmt.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId(),
							eAssinmt.getSalesPosition().getAlignmment().getSalesTeam().getId(),eAssinmt.getSalesPosition().getId(),eAssinmt.getSalesPosition().getHierarchy().getId(),
							CommonConstants.PRIMARY_ALLOC_ID,CommonConstants.ACTIVE,userDetails.getTenantId());
					
					if (null != primList && !primList.isEmpty()) {
						for (Object[] obj : primList) {
							if (!(eAssinmt.getId() != null && eAssinmt.getId()
									.equals(obj[0]))) {
								if (eAssinmt.getEmployee().getId().intValue() == ((Integer) obj[1]).intValue()) {
									if (eAssinmt.getStartDate().compareTo(
											(Date) obj[2]) == 0
											|| eAssinmt.getEndDate().compareTo(
													(Date) obj[3]) == 0
											|| eAssinmt.getStartDate().compareTo(
													(Date) obj[3]) == 0
											|| eAssinmt.getEndDate().compareTo(
													(Date) obj[2]) == 0) {
										map.put("code", "350");
										buffer.append(eAssinmt.getEmployee()
												.getFirstName()
												+ " Primary Assignment from  "
												+ (Date) obj[2]
												+ " to "
												+ (Date) obj[3]
												+ ". Please change the start & end dates. ");

									} else if (eAssinmt.getStartDate().compareTo(
											(Date) obj[2]) < 0) {
										if (eAssinmt.getEndDate().compareTo(
												(Date) obj[2]) > 0) {
											map.put("code", "350");
											buffer.append(eAssinmt.getEmployee()
													.getFirstName()
													+ "Primary Assignment from "
													+ (Date) obj[2]
													+ " to "
													+ (Date) obj[3]
													+ ". Please change the start & end dates. ");
										}

									} else if (eAssinmt.getStartDate().compareTo(
											(Date) obj[2]) > 0) {
										if (eAssinmt.getStartDate().compareTo(
												(Date) obj[3]) < 0
												|| eAssinmt.getEndDate().compareTo(
														(Date) obj[3]) < 0) {
											map.put("code", "350");
											buffer.append(eAssinmt.getEmployee()
													.getFirstName()
													+ "Primary Assignment from "
													+ (Date) obj[2]
													+ " to "
													+ (Date) obj[3]
													+ ". Please change the start & end dates. ");
										}
									}
								}
							}
						}
					}
			}
		}
		}
		
		if(!map.isEmpty()){
			map.put("msg", buffer.toString());
		}else{
			map.put("code", "250");
		}
		return map;
		
	}

   /**
	 * Gets the emp dtls.
	 *
	 * @param index the index
	 * @param noOfResults the no of results
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the emp dtls
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<EmployeeAlignment> getEmpDtls(int index, int noOfResults,
			SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<EmployeeAlignment>  empAlignmentList = new ArrayList<EmployeeAlignment>();
			//List<EmployeeAlignment>  empAlignList = new ArrayList<EmployeeAlignment>();
			Long spId = salesPos.getId();
			Long algmntId = salesPos.getAlignmment().getId();
			Long buId = salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId();
			Long stId = salesPos.getAlignmment().getSalesTeam().getId();
			Long spHierId = salesPos.getHierarchy().getId();
			Short tenantId = userDetails.getTenantId();
			String localeID = userDetails.getLocaleId();
			List<Object[]> list = tEmpAlgmntDAO.findEmpDetails(algmntId, buId, stId,
					spId, spHierId,tenantId,localeID,index, noOfResults);
			empAlignmentList = employeeAssignModelAssembler.convertTEmpDetailsToModel(list,tenantId);
			
			for (EmployeeAlignment empAlign : empAlignmentList){
				Employee emp = new  Employee();
				emp = empAlign.getEmployee();
				List<Integer> staffId = new ArrayList<Integer>();
				if (null!=emp){		
					Set<TPersAddr> tperSet = new HashSet<TPersAddr>();
					staffId.add(emp.getId().intValue());
					List<TPersAddr> addList = tPersAddrDAO.findPriAddressForEmp(staffId, tenantId, CommonConstants.ACTIVE_FLAG,CommonConstants.ACTIVE_Y);
					tperSet.add(addList.get(0));
					if(addList != null && !addList.isEmpty()){
						Address address = addressModelAssembler.convertTPersAddrToAddressModel(tperSet);
							emp.setEmployeeAdress(address);
					}
				}
				
				empAlign.setEmployee(emp);
			
			}
			return empAlignmentList;
		}catch(RuntimeException exception){
			throw new OpservDataAccessException("Error occur during fetching all EnployeeAlignment from SalesPosition", exception);
		}
		
	}


	/**
	 * Gets the emp types.
	 *
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the emp types
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<EmpSearch> getEmpTypes(Short tenantId, String localeId)
			throws OpservDataAccessException {
		try {
			List<TEmpType> typeList = tEmpTypeDAO.findEmpTypeByLocale(tenantId,
					localeId);
			List<EmpSearch>  empTypeList = new ArrayList<EmpSearch>();
			empTypeList = employeeAssignModelAssembler.convertTEmpTypesToModel(typeList);
			return empTypeList;
		} catch(RuntimeException exception){
			throw new OpservDataAccessException("Error occur during fetching all EnployeeAlignment from SalesPosition", exception);
		}
	}

	/**
	 * Search employees.
	 *
	 * @param empSearchVO the emp search vo
	 * @param start the start
	 * @param results the results
	 * @param tenantId the tenant id
	 * @param localeId the locale id
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<Employee> searchEmployees(EmpSearch empSearchVO, int start,
			int results, Short tenantId, String localeId
			) throws OpservDataAccessException {
		try {

			List<Employee> empList = new ArrayList<Employee>();
			
			// add EMP TYPE DAO HERE AND FIND TYPE ID w.r.t type & locale
			Integer type = -1 ; //
			if(empSearchVO.getType() != null && !empSearchVO.getType().isEmpty()){
				type = Integer.parseInt(empSearchVO.getType());
			}
			
			List<Object[]> tPersList = tPersDAO.buildQueryFromWhereClause(empSearchVO.getName(),empSearchVO.getCode(), empSearchVO.getZip(), empSearchVO.getCity(),
					type,	empSearchVO.getState(), empSearchVO.getAttrMap(), CommonConstants.ACTIVE_INT, start, results, tenantId);

			if(tPersList != null && !tPersList.isEmpty()){
				List<Integer> staffIds = new ArrayList<>();
				for(Object[] obj : tPersList){
					staffIds.add((Integer)obj[0]) ;
				}
				
				List<TPersAddr> tPersAddrList = tPersAddrDAO.findPriAddressForEmp(staffIds, tenantId, CommonConstants.ACTIVE_FLAG, CommonConstants.ACTIVE_Y);
				empList = employeeAssignModelAssembler.convertTPersToEmployee(empList, tPersList,
						 tenantId,localeId, tPersAddrList);
				
				return empList;
			}else{
				return null;
			}
		} catch (OpservDataAccessException da) {
			throw new OpservDataAccessException("Error occur during search Employee", da);
		} catch (Exception ex) {
			throw new OpservDataAccessException("Error occur during search Employee", ex);
		}
		
		
	}

	/**
	 * Show advanced search.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the list
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<AttributeDefinition> showAdvSrch(SalesPosition salesPosition,
			UserDetails userDetails) throws OpservDataAccessException {
		List<AttributeDefinition> attrDeflist = new ArrayList<AttributeDefinition>();
		try {
			TSalesPos tSalesPos = tSalesPosDAO.findTSalesPosBySalesPosId(
					salesPosition.getId(), userDetails.getTenantId());		

			long algmntId = tSalesPos.getTAlgmntSalesTeam().getTAlgmnt()
					.getAlgmntId();
			long bussUnitId = tSalesPos.getTAlgmntSalesTeam().getTSalesTeam()
					.getTSalesTeamId().getBussUnitId();
			long salesTeamId = tSalesPos.getTAlgmntSalesTeam().getTSalesTeam()
					.getTSalesTeamId().getSalesTeamId();
			
			List<TAlgmntTmpl> tmplList = tAlgmntTmplDAO
					.findTAlgnmtTmplIdByAlBuSTBussObjTenant(algmntId,
							bussUnitId, salesTeamId,
							CommonConstants.EMPLOYEE,
							userDetails.getTenantId());
			attrDeflist = employeeAssignModelAssembler.convertTAlgmntTmplToAttributeDefn(tmplList);
			return attrDeflist;
		} catch (OpservDataAccessException da) {
			throw new OpservDataAccessException("Error occur during fetching all EnployeeAlignment from SalesPosition", da);
		} catch (Exception ex) {
			throw new OpservDataAccessException("Error occur during fetching all EnployeeAlignment from SalesPosition", ex);
		}
	}





}
