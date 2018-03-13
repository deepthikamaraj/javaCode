package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.EmployeeAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.EmployeeAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.EmployeeAlignmentService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignmentServiceImpl contains all the services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("employeeAlignmentService")
public class EmployeeAlignmentServiceImpl implements EmployeeAlignmentService {
	
	/** The employee alignment dao service. */
	@Autowired
	private EmployeeAlignmentDAOService employeeAlignmentDAOService;
	
	/**
	 * 
	 * @method getAllEmployeeAlignments
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	public List<EmployeeAlignment> getAllEmployeeAlignments(
			UserDetails userDetails) throws AlignmentServiceException {

		try{
			if(null!=userDetails && null!=userDetails.getTenantId() && userDetails.getStaffId()!= null){ 
				
			return employeeAlignmentDAOService.getAllEmployeeAlignments(userDetails);
			}else{
				Object[] args = new Object[]{"staffId","tenantId"};
					throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{userDetails.getStaffId()};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_007, "Fetch_exception", args, dataAccessException);
		}
	}

	/**
	 * 
	 * @method getAllPrimaryEmployeeAlignments
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	public List<EmployeeAlignment> getAllPrimaryEmployeeAlignments(
			Alignment alignment, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(null!=userDetails && null != alignment && null!=userDetails.getTenantId() 
					&& alignment.getId() != null && alignment.getSalesTeam().getId() != null 
					&&  alignment.getSalesTeam().getBusinessUnit().getId() != null){ 
				
				return employeeAlignmentDAOService.getAllPrimaryEmployeeAlignments(alignment, userDetails);
			}else{
				Object[] args = new Object[]{"tenantId","algmntId", "BussUnitId", "salesTeamId"};
					throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{alignment.getId()};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_008, "Fetch_exception", args, dataAccessException);
		}
	}

	/**
	 * Gets the all employee alignments by sales postion.
	 *
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the all employee alignments by sales postion
	 * @throws AlignmentServiceException the alignment service exception
	 * @method getAllEmployeeAlignmentsBySalesPostion
	 */
	@Override
	public List<EmployeeAlignment> getAllEmployeeAlignmentsBySalesPostion(
			SalesPosition salesPos, UserDetails userDetails)
			throws AlignmentServiceException {
		try{
			if(null!=userDetails && null != salesPos && null!=userDetails.getTenantId() 
					&& salesPos.getId() != null && salesPos.getHierarchy().getId() != null
					&& salesPos.getAlignmment().getId() != null && salesPos.getAlignmment().getSalesTeam().getId() != null 
					&& salesPos.getAlignmment().getSalesTeam().getBusinessUnit().getId() != null){ 
				
			return employeeAlignmentDAOService.getAllEmployeeAlignmentsBySalesPostion(salesPos, userDetails);
			}else{
				Object[] args = new Object[]{"tenantId","algmntId", "BussUnitId", "salesTeamId", "salesHierId"};
					throw new AlignmentServiceException(args);
			}
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{salesPos.getId()};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_009, "Fetch_exception", args, dataAccessException);
		}
	}
	
	
}
