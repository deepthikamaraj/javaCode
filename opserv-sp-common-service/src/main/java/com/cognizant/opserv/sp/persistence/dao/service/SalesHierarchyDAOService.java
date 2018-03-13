package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class SalesHierarchyDAOService contains all the DAO  sales hierarchy services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface SalesHierarchyDAOService {
	
	/**
	 * Gets the list of alignment sales hierarchy.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the list of alignment sales hierarchy
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<SalesOrgHierarchy> getListOfAlignmentSalesHierarchy(Alignment alignment, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the sales hierarchy information.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the sales hierarchy information
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public SalesOrgHierarchy getSalesHierarchyInformation(long salesHierId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the child sales hierarchy.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the child sales hierarchy
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<SalesOrgHierarchy> getChildSalesHierarchy(long salesHierId, UserDetails userDetails) throws OpservDataAccessException;
	
	/**
	 * Gets the zip assignments by alignment.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the zip assignments by alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	public List<SalesOrgHierarchy> getZipAssignmentsByAlignment(Alignment alignment, UserDetails userDetails) throws OpservDataAccessException;
}
