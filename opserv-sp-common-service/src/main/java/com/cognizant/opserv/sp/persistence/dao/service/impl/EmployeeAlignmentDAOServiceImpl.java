package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.EmployeeAlignmentModelAssembler;
import com.cognizant.opserv.sp.core.dao.TEmpAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TOrgRoleDAO;
import com.cognizant.opserv.sp.core.entity.TEmpAlgmnt;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignmentServiceDAOImpl contains all the Dao services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class EmployeeAlignmentDAOServiceImpl implements EmployeeAlignmentDAOService {
	
	/** The t emp algmnt dao. */
	@Autowired
	private TEmpAlgmntDAO tEmpAlgmntDAO;
	
	@Autowired
	private TOrgRoleDAO tOrgRoleDAO;
	
//	private Map<Integer,String> orgRoles;
	
	private Map<Integer, Map<Integer,String>> tenantOrgRole;
	
	@Autowired
	private EmployeeAlignmentModelAssembler employeeAlignmentModelAssembler;
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService#getAllEmployeeAlignments(com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<EmployeeAlignment> getAllEmployeeAlignments(
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<TEmpAlgmnt> tEmpAlgmntList = tEmpAlgmntDAO.getTEmpAlgmntsByStaffId(userDetails.getStaffId(), userDetails.getTenantId());
			if((null==tenantOrgRole) || (null!=tenantOrgRole && !tenantOrgRole.containsKey(userDetails.getTenantId().intValue()))){
                fetchOrgRoles(userDetails.getTenantId());
        }
        return employeeAlignmentModelAssembler.convertTEmpAligmentsToModel(tEmpAlgmntList, tenantOrgRole.get(userDetails.getTenantId().intValue()));

		}catch(RuntimeException exception){
			throw new OpservDataAccessException("Error occur during fetching all EmployeeAlignments", exception);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService#getAllPrimaryEmployeeAlignments(com.cognizant.opserv.sp.model.Alignment, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<EmployeeAlignment> getAllPrimaryEmployeeAlignments(
			Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		
		try{
			Long algmntId = alignment.getId();
			Long bussUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			Integer allocTypeId = 1; // allocTypeid = 1 for primary allocation type
			Short tenantId = userDetails.getTenantId();
//			Map<Integer,String> orgRoles=null;
			
			List<TEmpAlgmnt> tEmpAlgmntList = tEmpAlgmntDAO.findPrimaryTEmpAlgmntforAllSp(algmntId, bussUnitId, salesTeamId,  allocTypeId, tenantId);
			if((null==tenantOrgRole) || (null!=tenantOrgRole && !tenantOrgRole.containsKey(userDetails.getTenantId().intValue()))){
                fetchOrgRoles(userDetails.getTenantId());
        }
        return employeeAlignmentModelAssembler.convertTEmpAligmentsToModel(tEmpAlgmntList, tenantOrgRole.get(userDetails.getTenantId().intValue()));

		}catch(RuntimeException exception){
			throw new OpservDataAccessException("Error occur during fetching All Primary EmployeeAlignments", exception);
		}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService#getAllEmployeeAlignmentsBySalesPostion(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public List<EmployeeAlignment> getAllEmployeeAlignmentsBySalesPostion(
			SalesPosition salesPos, UserDetails userDetails)
			throws OpservDataAccessException {
		
		try{
			Long spId = salesPos.getId();
			Long algmntId = salesPos.getAlignmment().getId();
			Long buId = salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId();
			Long stId = salesPos.getAlignmment().getSalesTeam().getId();
			Long spHierId = salesPos.getHierarchy().getId();
			Short tenantId = userDetails.getTenantId();
//			Map<Integer,String> orgRoles=null;
			List<TEmpAlgmnt> tEmpAlgmntList = tEmpAlgmntDAO.findTEmpAlgmntForSalesPosId(spId, spHierId, algmntId, buId, stId, tenantId);
			
			if((null==tenantOrgRole) || (null!=tenantOrgRole && !tenantOrgRole.containsKey(userDetails.getTenantId().intValue()))){
                fetchOrgRoles(userDetails.getTenantId());
        }
			
        return employeeAlignmentModelAssembler.convertTEmpAligmentsToModel(tEmpAlgmntList, tenantOrgRole.get(userDetails.getTenantId().intValue()));

		}catch(RuntimeException exception){
			throw new OpservDataAccessException("Error occur during fetching all EnployeeAlignment from SalesPosition", exception);
		}
	}
	
	
	private void fetchOrgRoles(Short tenantId){
		Map<Integer,String> orgRoles = null;
		List<Object[]> tOrgRoleObjList = tOrgRoleDAO.findRoleIdOrgRoleIdAndName(tenantId);
		if(null!=tOrgRoleObjList){
			orgRoles =  EmployeeAlignmentModelAssembler.convertObjectListToOrgRole(tOrgRoleObjList);
			if(tenantOrgRole ==null){
				tenantOrgRole = new HashMap<Integer, Map<Integer,String>>();
			} 
			tenantOrgRole.put(tenantId.intValue(),orgRoles);
		}
	}

	@Override
	public List<String> fetchEmailIdFrEmp(List<Long> srcTarSpIDList, UserDetails userDetails) {
		Short tenantId = userDetails.getTenantId();
		return tEmpAlgmntDAO.fetchEmailIdFrEmp(srcTarSpIDList, tenantId);
	}
	
	/**
	 * Fetch staff id.
	 *
	 * @param sp the sp
	 * @param userDts the user dts
	 * @return the string
	 */
	@Override
	public List<Integer> fetchStaffIds(List<Long> srcTarSpIDList, UserDetails userDetails) {
		Short tenantId = userDetails.getTenantId();
		return tEmpAlgmntDAO.fetchStaffIds(srcTarSpIDList, tenantId);
	}
	
}
