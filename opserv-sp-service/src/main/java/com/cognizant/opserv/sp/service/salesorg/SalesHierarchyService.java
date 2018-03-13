package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.SalesOrgServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class SalesHierarchyService contains all the sales hierarchy services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface SalesHierarchyService {

	/**
	 * Gets the alignment sales hierarchy.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the alignment sales hierarchy
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	List<SalesOrgHierarchy> getAlignmentSalesHierarchy(Alignment alignment, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Gets the sales hierarchy information.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the sales hierarchy information
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	SalesOrgHierarchy getSalesHierarchyInformation(long salesHierId, UserDetails userDetails) throws SalesOrgServiceException;
	
	/**
	 * Gets the child sales hierarchy.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the child sales hierarchy
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	List<SalesOrgHierarchy> getChildSalesHierarchy(long salesHierId, UserDetails userDetails) throws SalesOrgServiceException;
	
	
	/**
	 * Gets the zip assignments by alignment.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the zip assignments by alignment
	 * @throws SalesOrgServiceException the sales org service exception
	 */
	List<SalesOrgHierarchy> getZipAssignmentsByAlignment(Alignment alignment,UserDetails userDetails) throws SalesOrgServiceException;

}
