package com.cognizant.opserv.sp.service.salesorg.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.exception.SalesOrgServiceException.SalesOrgServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesHierarchyDAOService;
import com.cognizant.opserv.sp.service.salesorg.SalesHierarchyService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class SalesHierarchyServiceimpl contains all the sales hierarchy services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Service("salesHierarchyService")
public class SalesHierarchyServiceImpl  implements  SalesHierarchyService {

	
	@Autowired
	private SalesHierarchyDAOService salesHierarchyDAOService;
	
	/**
	 * Gets the alignment sales hierarchy.
	 *
	 * @method getAlignmentSalesHierarchy
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the alignment sales hierarchy
	 * @throws SalesOrgServiceException the sales org service exception
	 * @method getAlignmentSalesHierarchy
	 */
	@Override
	public List<SalesOrgHierarchy> getAlignmentSalesHierarchy(
			Alignment alignment, UserDetails userDetails)
			throws SalesOrgServiceException {
		
		try{
			if(null == alignment|| null == alignment.getId() || null == alignment.getSalesTeam().getId() || null == alignment.getSalesTeam().getBusinessUnit().getId()  ||null == userDetails ||null == userDetails.getTenantId()){
				String params = "AlignmentId ="+ String.valueOf(alignment.getId()) +", Buss_unit_id = "+ String.valueOf(alignment.getSalesTeam().getBusinessUnit().getId()) +" ,Sales_team_id = "+String.valueOf(alignment.getSalesTeam().getId()) +" ,tenant_id = " +String.valueOf(userDetails.getTenantId());
				Object[] args = new Object[]{params};
				throw new  SalesOrgServiceException(args);
			}
			return salesHierarchyDAOService.getListOfAlignmentSalesHierarchy(alignment, userDetails);
		}catch(OpservDataAccessException dataAccessException){
			Object[] args = new Object[]{alignment.getId()};
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_001, "Fetch_exception", args, dataAccessException);
		}
		
		
	}
	
	/**
	 * Gets the sales hierarchy information.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the sales hierarchy information
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	@Override
	public SalesOrgHierarchy getSalesHierarchyInformation(long salesHierId, UserDetails userDetails)
			throws SalesOrgServiceException {
		try{
			return salesHierarchyDAOService.getSalesHierarchyInformation(salesHierId, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_002, "exception while fetching sales org hierarchy", null, e);
		}
	}
	
	/**
	 * Gets the child sales hierarchy.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the child sales hierarchy
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	@Override
	public List<SalesOrgHierarchy> getChildSalesHierarchy(long salesHierId, UserDetails userDetails)
			throws SalesOrgServiceException {
		try{
			return salesHierarchyDAOService.getChildSalesHierarchy(salesHierId, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_006, "exception while fetching child hierarchy", null, e);
		}
	}

	/**
	 * Gets the zip assignments by alignment.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the zip assignments by alignment
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	@Override
	@Transactional
	public List<SalesOrgHierarchy> getZipAssignmentsByAlignment(
			Alignment alignment, UserDetails userDetails)
			throws SalesOrgServiceException {
		try{
			if(null == alignment){
				throw new SalesOrgServiceException(new Object[]{"alignment"});
			}
			return salesHierarchyDAOService.getZipAssignmentsByAlignment(alignment, userDetails);
		}
		catch(OpservDataAccessException e){
			throw new SalesOrgServiceException(SalesOrgServiceExceptionCode.SALESORG_SER_EX_CD_001, "exception while fetching zip assignment for sales hierarchy", null, e);
		}
	}


}
