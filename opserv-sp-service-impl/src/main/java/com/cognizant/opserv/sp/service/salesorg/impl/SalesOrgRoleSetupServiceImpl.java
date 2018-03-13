package com.cognizant.opserv.sp.service.salesorg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.exception.SalesOrgServiceException.SalesOrgServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesOrgRoleSetupDAOService;
import com.cognizant.opserv.sp.service.salesorg.SalesOrgRoleSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("salesOrgRoleSetupService")
public class SalesOrgRoleSetupServiceImpl implements SalesOrgRoleSetupService {

	@Autowired
	private SalesOrgRoleSetupDAOService salesOrgRoleSetupDAOService;
	
	@Override
	@Transactional
	public SalesOrgRole createSalesOrgRole(SalesOrgRole salesOrgRole,
			UserDetails userDetails) throws SalesOrgServiceException {
		
		try {
			
			if(null == salesOrgRole || null == salesOrgRole.getName() || salesOrgRole.getName().trim().isEmpty() || null == salesOrgRole.getCreatedBy() || 
					null == salesOrgRole.getCreatedDate() || !salesOrgRole.isActive() || null == userDetails || null == userDetails.getTenantId()) {
				
				String params = "SalesOrgRole ="+ salesOrgRole +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			return salesOrgRoleSetupDAOService.createSalesOrgRole(salesOrgRole, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{salesOrgRole};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_001, "Create_exception", args, dataAccessException);
		}
	}

	@Override
	@Transactional
	public void updateSalesOrgRole(SalesOrgRole salesOrgRole,
			UserDetails userDetails) throws SalesOrgServiceException {
		
		try {
			
			if(null == salesOrgRole || null == salesOrgRole.getId() || null == salesOrgRole.getName() || salesOrgRole.getName().trim().isEmpty() || 
					null == userDetails || null == userDetails.getTenantId()) {
				
				String params = "SalesOrgRole ="+ salesOrgRole +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			salesOrgRoleSetupDAOService.updateSalesOrgRole(salesOrgRole, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{salesOrgRole};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_002, "Update_exception", args, dataAccessException);
		}
	}

	@Override
	@Transactional
	public void inactiveSalesOrgRole(Long salesOrgRoleId,
			UserDetails userDetails) throws SalesOrgServiceException {
		
		try {
			
			if(null == salesOrgRoleId || null == userDetails || null == userDetails.getTenantId()) {
				
				String params = "SalesOrgRoleId ="+ salesOrgRoleId +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			salesOrgRoleSetupDAOService.inactiveSalesOrgRole(salesOrgRoleId, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{salesOrgRoleId};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_003, "Inactive_exception", args, dataAccessException);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public SalesOrgRole getSalesOrgRoleDetails(Long salesOrgRoleId,
			UserDetails userDetails) throws SalesOrgServiceException {
		
		try {
			
			if(null == salesOrgRoleId || null == userDetails || null == userDetails.getTenantId()) {
				
				String params = "SalesOrgRoleId ="+ salesOrgRoleId +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			return salesOrgRoleSetupDAOService.getSalesOrgRoleDetails(salesOrgRoleId, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{salesOrgRoleId};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_004, "Fetch_exception", args, dataAccessException);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalesOrgRole> fetchSalesOrgRoles(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws SalesOrgServiceException {
		
		try {
			
			if(null == searchCriteria || null == userDetails || null == userDetails.getTenantId()) {
				
				String params = "SearchCriteria ="+ searchCriteria +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			return salesOrgRoleSetupDAOService.fetchSalesOrgRoles(searchCriteria, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{searchCriteria};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_005, "FetchAll_exception", args, dataAccessException);
		}
	}
}