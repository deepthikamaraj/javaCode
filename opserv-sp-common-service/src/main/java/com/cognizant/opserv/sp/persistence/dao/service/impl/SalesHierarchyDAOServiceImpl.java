package com.cognizant.opserv.sp.persistence.dao.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.SalesHierarchyModelAssembler;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesHierDAO;
import com.cognizant.opserv.sp.core.dao.TAlgmntSalesRoleDAO;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesRole;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesHierarchyDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 *
 * @class SalesHierarchyDAOServiceImpl contains all the DAO sales hierarchy services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
@Component
public class SalesHierarchyDAOServiceImpl  implements  SalesHierarchyDAOService {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesHierarchyDAOServiceImpl.class);
	
	@Autowired
	private TAlgmntSalesHierDAO salesHierDAO;
	
	@Autowired
	private TAlgmntSalesRoleDAO salesRoleDAO;
	
	@Autowired
	private TAlgmntSalesHierDAO tAlgmntSalesHierDAO;
	
	/**
	 * Gets the list of alignment sales hierarchy.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the list of alignment sales hierarchy
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<SalesOrgHierarchy> getListOfAlignmentSalesHierarchy(
			Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			long alignmentId=alignment.getId();
			long buId=alignment.getSalesTeam().getBusinessUnit().getId();
			long salesId=alignment.getSalesTeam().getId();
			short tenantId = userDetails.getTenantId();
			long salesHierId;
			List<TAlgmntSalesHier> alignmentSalesHiers = salesHierDAO.findTAlgmntSalesHierByTAlgmntID(alignmentId, buId, salesId, tenantId);
			
			List<SalesOrgHierarchy> salesOrgHierarchyList = SalesHierarchyModelAssembler.toModelObjectList(alignmentSalesHiers);
			
			for(SalesOrgHierarchy salesHierarchy:salesOrgHierarchyList){
				//getTAlignmentSalesRoleIdBySalesHierId
				salesHierId = salesHierarchy.getId();
				List<TAlgmntSalesRole> algmntSalesRole = salesRoleDAO.getTAlignmentSalesRoleIdBySalesHierId(salesHierId, tenantId);
				salesHierarchy.setOrgRole(algmntSalesRole.get(0).getRoleName());
				salesHierId = 0L;
			}
			return salesOrgHierarchyList;
		}catch(RuntimeException cause){
			LOGGER.error("Error occur during fetching alignment saleshierarchy", cause);
			throw new OpservDataAccessException("Error occur during fetching alignment saleshierarchy", cause);
		}
		
	}
	
	/**
	 * Gets the sales hierarchy information.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the sales hierarchy information
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public SalesOrgHierarchy getSalesHierarchyInformation(long salesHierId, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			TAlgmntSalesHier tAlgmntSalesHier = salesHierDAO.findTAlgmntSalesHierById(salesHierId);
			if(null != tAlgmntSalesHier){
				return SalesHierarchyModelAssembler.toModelObject(tAlgmntSalesHier);
			}
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching sales org hierarchy", e);
			throw new OpservDataAccessException("exception while fetching sales org hierarchy", e);
		}
		return null;
	}
	
	/**
	 * Gets the child sales hierarchy.
	 *
	 * @param salesHierId the sales hier id
	 * @param userDetails the user details
	 * @return the child sales hierarchy
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<SalesOrgHierarchy> getChildSalesHierarchy(long salesHierId, UserDetails userDetails)
			throws OpservDataAccessException {
		List<SalesOrgHierarchy> salesOrgHierarchyList = null;
		try{
			SalesOrgHierarchy salesOrgHierarchy = getSalesHierarchyInformation(salesHierId, userDetails);
			if(null != salesOrgHierarchy){
				salesOrgHierarchyList = new ArrayList<SalesOrgHierarchy>();
				salesOrgHierarchyList.add(salesOrgHierarchy);
				List<TAlgmntSalesHier> tAlgmntSalesHierList = salesHierDAO.findTAlgmntSalesHierByPrn(salesHierId, userDetails.getTenantId());
				if(null != tAlgmntSalesHierList && !tAlgmntSalesHierList.isEmpty()){
					salesOrgHierarchyList.addAll(SalesHierarchyModelAssembler.toModelObjectList(tAlgmntSalesHierList));
				}
			}
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching child hierarchy", e);
			throw new OpservDataAccessException("exception while fetching child hierarchy", e);
		}
		return salesOrgHierarchyList;
	}

	/**
	 * Gets the zip assignments by alignment.
	 *
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the zip assignments by alignment
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	@Override
	public List<SalesOrgHierarchy> getZipAssignmentsByAlignment(
			Alignment alignment, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<SalesOrgHierarchy> salesOrgHierarchy = null;
			Long algmntId = alignment.getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			Long bussUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			List<TAlgmntSalesHier> tAlgmntSalesHierList = tAlgmntSalesHierDAO.findTAlgSalHierById(bussUnitId, salesTeamId, algmntId, userDetails.getTenantId());
			if(null != tAlgmntSalesHierList && !tAlgmntSalesHierList.isEmpty()){
				salesOrgHierarchy = new ArrayList<SalesOrgHierarchy>();
				for(TAlgmntSalesHier algmntSalesHier : tAlgmntSalesHierList){
					salesOrgHierarchy.add(SalesHierarchyModelAssembler.toModelObject(algmntSalesHier));
				}
			}
			return salesOrgHierarchy;
		}
		catch(RuntimeException e){
			LOGGER.error("exception while fetching zip assignments", e);
			throw new OpservDataAccessException("exception while fetching zip assignments", e);
		}
	}
	

}
