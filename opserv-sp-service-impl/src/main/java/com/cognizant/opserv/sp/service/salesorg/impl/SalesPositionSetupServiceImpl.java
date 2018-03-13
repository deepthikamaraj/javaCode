package com.cognizant.opserv.sp.service.salesorg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException.SalesPositionServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionSetupDAOService;
import com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesPositionSetupServiceImpl.
 */
@Service("salesPositionSetupService")
public class SalesPositionSetupServiceImpl implements SalesPositionSetupService{

	
	/** The sales position setup dao service. */
	@Autowired
	SalesPositionSetupDAOService salesPositionSetupDAOService;
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService#createSalesPosition(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public SalesPosition createSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws SalesPositionServiceException {
		try{
			if(salesPosition == null || salesPosition.getHierarchy().getId() ==null || 
					salesPosition.getName() == null || salesPosition.getStartDate() == null|| 
					salesPosition.getAlignmment() == null || salesPosition.getAlignmment().getId() == null || 
					salesPosition.getAlignmment().getSalesTeam() == null || 
					salesPosition.getAlignmment().getSalesTeam().getId() == null || 
					salesPosition.getAlignmment().getSalesTeam().getBusinessUnit() == null || 
					salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId() == null || 
					salesPosition.getCreatedBy() == null || salesPosition.getCreatedDate() == null || 
					salesPosition.getSalesPositionType() == null ||salesPosition.getSalesPositionType().getId() == null || 
					salesPosition.getMirrorSalesPositionType() == null || salesPosition.getMirrorSalesPositionType().getId() == null || 
					userDetails == null || userDetails.getTenantId() == null ){
				String params = "Required field is missing";
				Object[] args = new Object[]{params};
				throw new  SalesPositionServiceException(args);
			}
			return salesPositionSetupDAOService.createNewSalesPosition(salesPosition, userDetails);
			
				} catch(OpservDataAccessException e) {
				       Object[] args = new Object[]{"SalesPosition creation failed!!"};
				       throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_003 ,"Fetch_exception",args,e);
				}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService#updateSalesPosition(com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public void updateSalesPosition(SalesPosition salesPosition,
			UserDetails userDetails) throws SalesPositionServiceException {
		try{
		if(salesPosition == null || salesPosition.getHierarchy().getId() ==null || 
				salesPosition.getName() == null || salesPosition.getStartDate() == null || 
				salesPosition.getId() == null || salesPosition.getAlignmment() == null || 
				salesPosition.getAlignmment().getId() == null || salesPosition.getAlignmment().getSalesTeam() == null || 
				salesPosition.getAlignmment().getSalesTeam().getId() == null || 
				salesPosition.getAlignmment().getSalesTeam().getBusinessUnit() == null || 
				salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId() == null || 
				salesPosition.getCreatedBy() == null || salesPosition.getCreatedDate() == null || 
				salesPosition.getSalesPositionType() == null ||salesPosition.getSalesPositionType().getId() == null || 
				salesPosition.getMirrorSalesPositionType() == null || 
				salesPosition.getMirrorSalesPositionType().getId() == null || 
				userDetails == null || userDetails.getTenantId() == null ){
			String params = "Required field is missing";
			Object[] args = new Object[]{params};
			throw new  SalesPositionServiceException(args);
		}
		salesPositionSetupDAOService.updateSalesPositionInfo(salesPosition, userDetails);
			} catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{"SalesPosition updation falied!!"};
			       throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_004 ,"Fetch_exception",args,e);
			}
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService#inactiveSalesPosition(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {SalesPositionServiceException.class})
	public void inactiveSalesPosition(Long salePositionId,
			UserDetails userDetails) throws SalesPositionServiceException {
		try {
			Long spId = null;
			try {
				spId = Long.valueOf(salePositionId);
			} catch(Exception e) {
				spId = null;
			}
			if( null == spId || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing Sales pos id / tenant id";
				Object[] args = new Object[]{params};
				throw new  SalesPositionServiceException(args);
			}
			// Child roll up to inactive those SP
			salesPositionSetupDAOService.softDeletionOfSalesPosition(salePositionId, userDetails);
			} catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{salePositionId};
			       throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_005 ,"Fetch_exception",args,e);
			}
		
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService#getSalesPositionDetails(java.lang.Long, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public SalesPosition getSalesPositionDetails(Long salePositionId,
			UserDetails userDetails) throws SalesPositionServiceException {
		
		try {
			Long spId = null;
			try {
				spId = Long.valueOf(salePositionId);
			} catch(Exception e) {
				spId = null;
			}
			if( null == spId || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing Sales pos id / tenant id";
				Object[] args = new Object[]{params};
				throw new  SalesPositionServiceException(args);
			}
			
			return salesPositionSetupDAOService.getAllDetailsOfSalesPosition(salePositionId, userDetails);
			} catch(OpservDataAccessException e) {
			       Object[] args = new Object[]{salePositionId};
			       throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_006 ,"Fetch_exception",args,e);
			}
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.salesorg.SalesPositionSetupService#fetchSalesPositions(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	@Transactional
	public List<SalesPosition> fetchSalesPositions(
			ISearchCriteria searchCriteria, UserDetails userDetails)
			throws SalesPositionServiceException {
		try{
			if(searchCriteria == null){
				String params = "criteria is missing.";
				Object[] args = new Object[]{params};
				throw new SalesPositionServiceException(args);
			}
			return salesPositionSetupDAOService.fetchSalesPositionsByCriteria(searchCriteria, userDetails);
		}
		catch(OpservDataAccessException e){
			  Object[] args = new Object[]{"Exception while fetching SalesPostions by criteria!!"};
			throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_007, 
					"exception while fetching SalesPosition details", args, e);
		}
	}

}
