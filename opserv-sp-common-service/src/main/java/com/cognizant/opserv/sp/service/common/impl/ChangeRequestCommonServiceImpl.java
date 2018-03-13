package com.cognizant.opserv.sp.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException.ChangeRequestServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class ChangeRequestCommonServiceImpl contains all the common services for change
 *        request
 * @author Cognizant Technology Solutions
 *        ************************************************************
 *        ***************
 */
@Service
@Transactional
public class ChangeRequestCommonServiceImpl implements ChangeRequestCommonService{

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestCommonServiceImpl.class);
	
	/** The Change request dao service. */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	/**
	 * Generates a change request for push and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	@Override
	public ChangeRequest generatePushCustomerChangeRequest(
			ChangeRequest parentCR, SalesPosition sourceSP,
			SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException {
		
		ChangeRequest changeRequest = null;
		try {
			if (null == sourceSP || null == sourceSP.getId()
					|| null == userDetails
					|| null == userDetails.getTenantId()
					|| null == sourceSP.getAlignmment()
					|| null == sourceSP.getAlignmment().getId()
					|| null == sourceSP.getHierarchy().getId()
					|| null == sourceSP.getHierarchy()
					|| null == sourceSP.getAlignmment().getSalesTeam()
					|| null == sourceSP.getAlignmment().getSalesTeam().getId()
					|| null == sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId()) {
				LOGGER.error("Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
			}
			if (sourceSP.getId() < 0
					|| sourceSP.getAlignmment().getId() < 0
					|| sourceSP.getHierarchy().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId() < 0) {
							LOGGER.error(" targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
		
			}
			
			if(targetSP != null && targetSP.getAlignmment() != null && !sourceSP.getAlignmment().getId().equals(targetSP.getAlignmment().getId())){
				LOGGER.error("Source and target sales postion should be in same Alignment");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_021","Source and target sales postion should be in same Alignment");
			}
			
			LOGGER.info("********************************Source and destination SP cannot be same validation -- Started ********************************");
			if(targetSP != null && targetSP.getId() != null){
				if (sourceSP.getId().equals(targetSP.getId())
						&& sourceSP.getHierarchy().getId()
								.equals(targetSP.getHierarchy().getId())) {
					throw new AlignmentServiceException(
							"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_255",
							"Source and Destination SalesPosition cannot be same");
				}
				LOGGER.info("********************************Source and destination SP cannot be same validation -- Ended ********************************");
			}
			
		
			if (null == parentCR || null == parentCR.getId()) {
			changeRequest = changeRequestDAOService.createChangeRequest(
					sourceSP, targetSP, userDetails,
					ChangeRequestTriggerType.PUSH_CUSTOMER.getId());
		} else {
			changeRequest = changeRequestDAOService.createMirrorChangeRequest(
					parentCR, sourceSP, targetSP, userDetails,
					ChangeRequestTriggerType.PUSH_CUSTOMER.getId());
		}
		return changeRequest;
		} catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during generating new CR for push customer ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_007 ,"create_exception",args,e);
		}
		
	}

	/**
	 * Generates a change request for pull and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	@Override
	public ChangeRequest generatePullCustomerChangeRequest(
			ChangeRequest parentCR, SalesPosition sourceSP,
			SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException{
		
		ChangeRequest changeRequest = null;
		try {
			if (null == sourceSP || null == sourceSP.getId()
					|| null == userDetails
					|| null == userDetails.getTenantId()
					|| null == sourceSP.getAlignmment()
					|| null == sourceSP.getAlignmment().getId()
					|| null == sourceSP.getHierarchy().getId()
					|| null == sourceSP.getHierarchy()
					|| null == sourceSP.getAlignmment().getSalesTeam()
					|| null == sourceSP.getAlignmment().getSalesTeam().getId()
					|| null == sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId()) {
				LOGGER.error("Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
			}
			if (sourceSP.getId() < 0
					|| sourceSP.getAlignmment().getId() < 0
					|| sourceSP.getHierarchy().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId() < 0) {
							LOGGER.error(" targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
		
			}
			LOGGER.info("********************************Source and destination SP cannot be same validation -- Started ********************************");
			if (targetSP != null && targetSP.getId() != null) {
				if (sourceSP.getId().equals(targetSP.getId()) && sourceSP.getHierarchy().getId().equals(targetSP.getHierarchy().getId())) {
					throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_255",
							"Source and Destination SalesPosition cannot be same");
				}

				LOGGER.info("********************************Source and destination SP cannot be same validation -- Ended ********************************");
			}
			
			if(targetSP != null && targetSP.getAlignmment() != null && !sourceSP.getAlignmment().getId().equals(targetSP.getAlignmment().getId())){
				LOGGER.error("Source and target sales postion should be in same Alignment");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_021","Source and target sales postion should be in same Alignment");
			}
			
			if (null == parentCR || null == parentCR.getId()) {
				changeRequest = changeRequestDAOService.createChangeRequest(
						sourceSP, targetSP, userDetails,
						ChangeRequestTriggerType.PULL_CUSTOMER.getId());
			} else {
				changeRequest = changeRequestDAOService.createMirrorChangeRequest(
						parentCR, sourceSP, targetSP, userDetails,
						ChangeRequestTriggerType.PULL_CUSTOMER.getId());
			}
			return changeRequest;
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during generating new CR for pull customer ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_008 ,"create_exception",args,e);
		}
		
		
	}
	
	/**
	 * Generates a change request for pull and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException 
	 */
	@Override
	public ChangeRequest generatePullCustomerChangeRequestSubmit(
			ChangeRequest parentCR, SalesPosition sourceSP,
			SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException{
		
		ChangeRequest changeRequest = null;
		try {
			if (null == sourceSP || null == sourceSP.getId()
					|| null == userDetails
					|| null == userDetails.getTenantId()
					|| null == sourceSP.getAlignmment()
					|| null == sourceSP.getAlignmment().getId()
					|| null == sourceSP.getHierarchy().getId()
					|| null == sourceSP.getHierarchy()
					|| null == sourceSP.getAlignmment().getSalesTeam()
					|| null == sourceSP.getAlignmment().getSalesTeam().getId()
					|| null == sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId()) {
				LOGGER.error("Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","Missing targetSP / sourceSP / tenant id / alId / buId / stId /spId / ShId");
			}
			if (sourceSP.getId() < 0
					|| sourceSP.getAlignmment().getId() < 0
					|| sourceSP.getHierarchy().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam().getId() < 0
					|| sourceSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId() < 0) {
							LOGGER.error(" targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","targetSP / sourceSP / alId / buId / stId /spId / ShId should be greater than Zero");
		
			}
			LOGGER.info("********************************Source and destination SP cannot be same validation -- Started ********************************");
			
			if(targetSP != null && targetSP.getAlignmment() != null && !sourceSP.getAlignmment().getId().equals(targetSP.getAlignmment().getId())){
				LOGGER.error("Source and target sales postion should be in same Alignment");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_021","Source and target sales postion should be in same Alignment");
			}
			
			if (targetSP != null && targetSP.getId() != null) {
				if (sourceSP.getId().equals(targetSP.getId()) && sourceSP.getHierarchy().getId().equals(targetSP.getHierarchy().getId())) {
					throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_255",
							"Source and Destination SalesPosition cannot be same");
				}

				LOGGER.info("********************************Source and destination SP cannot be same validation -- Ended ********************************");
			}
			if (null == parentCR || null == parentCR.getId()) {
				changeRequest = changeRequestDAOService.createChangeRequest(
						targetSP, sourceSP, userDetails,
						ChangeRequestTriggerType.PULL_CUSTOMER.getId());
			} else {
				changeRequest = changeRequestDAOService.createMirrorChangeRequest(
						parentCR, targetSP, sourceSP, userDetails,
						ChangeRequestTriggerType.PULL_CUSTOMER.getId());
			}
			return changeRequest;
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during generating new CR for pull customer ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_008 ,"create_exception",args,e);
		}
		
		
	}	

	/**
	 * Generates a change request for edit and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param customerSP the customer sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	@Override
	public ChangeRequest generateEditCustomerAlignmentChangeRequest(
			ChangeRequest parentCR, SalesPosition customerSP,
			UserDetails userDetails) throws ChangeRequestServiceException{
		ChangeRequest changeRequest = null;
		try {
			if (null == customerSP || null == customerSP.getId()
					|| null == userDetails
					|| null == userDetails.getTenantId()
					|| null == customerSP.getAlignmment()
					|| null == customerSP.getAlignmment().getId()
					|| null == customerSP.getHierarchy().getId()
					|| null == customerSP.getHierarchy()
					|| null == customerSP.getAlignmment().getSalesTeam()
					|| null == customerSP.getAlignmment().getSalesTeam().getId()
					|| null == customerSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId()) {
				LOGGER.error("Missing customerSP/ tenant id / alId / buId / stId /spId / ShId");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","Missing customerSP / tenant id / alId / buId / stId /spId / ShId");
			}
			if (customerSP.getId() < 0
					|| customerSP.getAlignmment().getId() < 0
					|| customerSP.getHierarchy().getId() < 0
					|| customerSP.getAlignmment().getSalesTeam().getId() < 0
					|| customerSP.getAlignmment().getSalesTeam()
							.getBusinessUnit().getId() < 0) {
							LOGGER.error(" customerSP / buId / stId /spId / ShId should be greater than Zero");
				throw new  ChangeRequestServiceException("CR_SER_EX_CD_001","Missing customerSP / alId / buId / stId /spId / ShId should be greater than Zero");
		
			}
		if (null == parentCR || null == parentCR.getId()) {
			changeRequest = changeRequestDAOService.createChangeRequest(
					customerSP, customerSP, userDetails,
					ChangeRequestTriggerType.EDIT_CUSTOMER.getId());
		} else {
			changeRequest = changeRequestDAOService.createMirrorChangeRequest(
					parentCR, customerSP, customerSP, userDetails,
					ChangeRequestTriggerType.EDIT_CUSTOMER.getId());
		}
		return changeRequest;
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during editing customerAlignment ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_009 ,"edit_exception",args,e);
		}
		
		
	}


	/**
	 * Generates a change request for zip and associates with the parent CR if provided.
	 *
	 * @param parentCR the parent cr
	 * @param sourceSP the source sp
	 * @param targetSP the target sp
	 * @param userDetails the user details
	 * @return the change request
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	@Override
	public ChangeRequest generateZipMovementChangeRequest(
			ChangeRequest parentCR, SalesPosition sourceSP,
			SalesPosition targetSP, UserDetails userDetails) throws ChangeRequestServiceException {
		
		ChangeRequest changeRequest = null;
		try {
			if(null == sourceSP || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing sourceSP / targetSP / tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing sourceSP / targetSP / tenant id");
				throw new  ChangeRequestServiceException(args);
			}
		if (null == parentCR || null == parentCR.getId()) {
			changeRequest = changeRequestDAOService.createChangeRequest(
					 sourceSP, targetSP, userDetails,
					ChangeRequestTriggerType.PUSH_ZIP.getId());
		} else {
			changeRequest = changeRequestDAOService.createMirrorChangeRequest(
					parentCR,  sourceSP, targetSP, userDetails,
					ChangeRequestTriggerType.PUSH_ZIP.getId());
		}
		return changeRequest;
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during generating CR for zip movement ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_010 ,"create_exception",args,e);
		}
		
	}

	/**
	 * Find mirror c rs.
	 *
	 * @param parentCR the parent cr
	 * @param userDetails the user details
	 * @return the list
	 * @throws ChangeRequestServiceException 
	 */
	@Override
	public List<ChangeRequest> findMirrorCRs(
		ChangeRequest parentCR, UserDetails userDetails) throws ChangeRequestServiceException{
		try {
			if(null == parentCR || null == userDetails || null == userDetails.getTenantId()){
				String params = "Missing parentCR /  tenant id";
				Object[] args = new Object[]{params};
				LOGGER.error("Missing parentCR /  tenant id");
				throw new  ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_020 ,"Missing parentCR /  tenant id",args,null);
			}
		return changeRequestDAOService.findChildChangeRequestByParentChangeRequest(parentCR);
		
		}catch(OpservDataAccessException e) {
			Object[] args = new Object[]{parentCR != null?parentCR.getId():null};
			Long parentCrId = parentCR != null?parentCR.getId():null;
			LOGGER.error("Error during fetching mirror CR ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_011 ,"data access exception while fetching mirror CR",args,e);
		}
	}

	/**
	 * Auto reject change request on submit.
	 *
	 * @param changeReq the change req
	 * @param userDetails the user details
	 * @return true, if successful
	 */
	@Override
	@Transactional
	public boolean autoRejectChangeRequestOnSubmit(ChangeRequest changeReq, UserDetails userDetails) throws ChangeRequestServiceException{

		try {
			changeRequestDAOService.autoRejectForBaseAndMirrorChangeRequests(
				changeReq, userDetails, ChangeRequestStatusType.REJECTED.getId(), ChangeRequestStatusType.AUTO_REJECTED.getId());
		}
		catch (OpservDataAccessException e) {
			Object[] args = new Object[]{changeReq != null?changeReq.getId():null};
			Long parentCrId = changeReq != null?changeReq.getId():null;
			LOGGER.error("Error during autoRejectChangeRequestOnSubmit ( ChangeRequest Id: "+ parentCrId +").");
		    throw new ChangeRequestServiceException(ChangeRequestServiceExceptionCode.CR_SER_EX_CD_011 ,"data access exception while autoRejectChangeRequestOnSubmit ",args,e);

		}
		return false;
	}
	

}
