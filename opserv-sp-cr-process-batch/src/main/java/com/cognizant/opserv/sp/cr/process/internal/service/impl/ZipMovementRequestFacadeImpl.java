package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.ErrorCode;
import com.cognizant.opserv.sp.cr.process.dto.ErrorDTO;
import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.CustomerPushOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoAlignmentService;
import com.cognizant.opserv.sp.cr.process.internal.service.GeoMovementOfflineService;
import com.cognizant.opserv.sp.cr.process.internal.service.OfflineRequestService;
import com.cognizant.opserv.sp.cr.process.internal.service.ZipMovementRequestFacade;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.service.common.ChangeRequestCommonService;
import com.cognizant.opserv.sp.service.notification.CRTriggerNotificationService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * The Class ZipMovementRequestFacadeImpl.
 */
@Service
public class ZipMovementRequestFacadeImpl implements ZipMovementRequestFacade {
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ZipMovementRequestFacadeImpl.class);
	
	/** The Constant LOGGER. */
	private static final OpservLogger WORKLOG = OpservLoggerFactory.getOpservLogger(CommonConstants.CR_PROCESSING_LOG);

	/** The geo offline service. */
	@Autowired
	private GeoMovementOfflineService geoOfflineService;
		
	/** The offline service. */
	@Autowired
	private OfflineRequestService offlineService;
	
	/** The cust push offline service. */
	@Autowired
	private CustomerPushOfflineService  custPushOfflineService;
	
	@Autowired
	private GeoAlignmentService geoAlignmentService;
	
	@Autowired
	private CRTriggerNotificationService crNotifyService;
	
	@Autowired
	private ChangeRequestOfflineService crOfflineService;
	
	@Autowired
	private ChangeRequestCommonService crCommonService;
	
	/**
	 * Move zip.
	 *
	 * @param message the message
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws ViewServiceException 
	 */
	@Override
	public void moveZip(OfflineRequestMessage message) throws ChangeRequestServiceException, AlignmentServiceException, ViewServiceException {
		
		LOGGER.info("=============== Execute of Zip Movement =========started============ ");
		WORKLOG.info("=============== Execute of Zip Movement =========started============ ");
		GeographyAlignmentDTO geoAlgmtDTO = null;
		try {
			
		UserDetails userDetails = message.getUserDetails();
		
		geoAlgmtDTO = offlineService.getGeoOfflineRequest(message, userDetails);

		
		if(null == geoAlgmtDTO || (null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty())) {
			LOGGER.error("===============Errors after getGeoOfflineRequest() in GeoAlgmntDTO =================== ");
			WORKLOG.error("===============Errors after while fetching geoAlgmtDTO from CR Offline table  =================== ");
			geoAlignmentService.unLockGeographyAlignmentOnReject(geoAlgmtDTO.getSourceGeoAlgmt(),userDetails);
			crNotifyService.triggerCrNotification(message.getChngReqID(), message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);			
			return;
		}

			/*********** Validation **************/
			geoOfflineService.validate(geoAlgmtDTO, userDetails);			
			//handle validation errors if present
			if(null == geoAlgmtDTO || (null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty())) {
				LOGGER.error("===============Errors after validate() in GeoAlgmntDTO =================== ");
				WORKLOG.error("===============Errors occurs while validating geoAlgmtDTO =================== ");
				geoAlignmentService.unLockGeographyAlignmentOnReject(geoAlgmtDTO.getSourceGeoAlgmt(),userDetails);
				crNotifyService.triggerCrNotification(message.getChngReqID(),  message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);			
				return;
			}
		
		//Get mirror CRS and populate geoAlgmtDTO mirrors
		geoOfflineService.findMirrorCRsForGeoMovement(geoAlgmtDTO, userDetails);
		if(null == geoAlgmtDTO || (null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty())) {
			//DOUBT: What to do if we get Data Access Excpetion while fetching mirror CR ??
			LOGGER.error("===============Errors occurs while getting mirror Change request =================== ");
			WORKLOG.error("===============Errors occurs while getting mirror Change request =================== ");
			geoAlignmentService.unLockGeographyAlignmentOnReject(geoAlgmtDTO.getSourceGeoAlgmt(),userDetails);
			crNotifyService.triggerCrNotification(message.getChngReqID(), message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);			
			return;
		}
		
		LOGGER.info("=============== create mirror CR if not exists =========Started========== ");
		//create mirror if not exists
		if (geoAlgmtDTO.getMirrors() != null
				&& !geoAlgmtDTO.getMirrors().isEmpty()) {
			for (MirrorGeoAlgmtDTO mirrorGeoAlgmtDTO : geoAlgmtDTO.getMirrors()) {
				try {
					if(geoAlgmtDTO.getCustAlgmtDTOs() != null && !geoAlgmtDTO.getCustAlgmtDTOs().isEmpty()) {
						for(CustomerAlignmentDTO custAlgmtDTO : geoAlgmtDTO.getCustAlgmtDTOs()) {
							if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()) {
								for (MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()) {
									if (mirrorGeoAlgmtDTO.getChildCR() == null) {
										geoOfflineService.createMirrorCR(geoAlgmtDTO.getMainCR(),mirrorGeoAlgmtDTO,mirrorCustAlgmtDTO,userDetails);
										WORKLOG.info("===============Create Mirror CRs with customer for Zip =================== ");
										LOGGER.info("========= Create Mirror CRs with customer for Zip ---===========");
									}
								}
							}
						}

					} else {
						if (mirrorGeoAlgmtDTO.getChildCR() == null) {
							geoOfflineService.createMirrorCR(geoAlgmtDTO.getMainCR(), mirrorGeoAlgmtDTO,null, userDetails);
							WORKLOG.info("===============Create Mirror CRs without customer for Zip =================== ");
							LOGGER.info("========= Create Mirror CRs without customer for Zip --- ===========");
						}
					}
				} catch (ChangeRequestServiceException chngReqException) {
					LOGGER.error("===============Errors occurs while creating mirrors =================== ");
					WORKLOG.error("===============Errors occurs while creating mirrors =================== ");
					geoAlignmentService.unLockGeographyAlignmentOnReject(geoAlgmtDTO.getSourceGeoAlgmt(), userDetails);
					crNotifyService.triggerCrNotification(message.getChngReqID(), message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);
					return;
				}
			}
		}
	
		try {
			LOGGER.info("=============== Locking Cr Offline ========= Started ========= ");
			offlineService.lockRequestForProcessing(message.getOfflineReqId(), userDetails);//method 1
		} catch(AlignmentServiceException algmntServiceException) {
			if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323.getCode().equals(algmntServiceException.getExceptionCode())){
				LOGGER.error("Exception while updating CR Offline Status");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2025);
				((CustomerAlignmentDTO)geoAlgmtDTO.getCustAlgmtDTOs()).addErrorDTO(errorDTO);
			} 
			else if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_333.getCode().equals(algmntServiceException.getExceptionCode())){
				LOGGER.error("OfflineId or UserDetails is null");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2026);
				((CustomerAlignmentDTO)geoAlgmtDTO.getCustAlgmtDTOs()).addErrorDTO(errorDTO);
			} 
			LOGGER.error("=============== Catch Exception while validating GeoAlgmntDTO or Create Child CR or locking CR offline status",algmntServiceException);
			WORKLOG.error("=============== Catch Exception while validating GeoAlgmntDTO or Create Child CR or locking CR offline status",algmntServiceException);
			handleError(message, geoAlgmtDTO);
			return;
		}
		
		
		
		LOGGER.info("=============== Locking Mirror Source Geography ========= Started ========= ");
		//Locking zip mirrors
		geoOfflineService.lockSourceGeography(geoAlgmtDTO, userDetails);//method 2
		if(null == geoAlgmtDTO || (null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty())) {
			LOGGER.error("=============== calling handleError after  lockSourceGeography()========= Started ========= ");
			WORKLOG.error("=============== calling handleError ,  lock Source Geography failed ");
			handleError(message, geoAlgmtDTO);
			return;
		}
		
		
			//Lock base customer and affiliations for source and mirrors
			// Locking the Root customer at all mirrors.
			LOGGER.info("===============Lock base customer and affiliations for source and mirrors========= Started ========= ");
			LOGGER.info("===============Locking the Root customer at all mirrors========= Started ========= ");
			WORKLOG.info("===============Locking the Root customer at all mirrors========= Started ========= ");
			for(CustomerAlignmentDTO custAlgmtDTO:geoAlgmtDTO.getCustAlgmtDTOs()){
				try{
					if(null!=custAlgmtDTO.getMirrors()){
						Iterator<MirrorCustAlgmtDTO> mirrorIter = custAlgmtDTO.getMirrors().iterator();
						while(mirrorIter.hasNext()) {
							MirrorCustAlgmtDTO mirror = mirrorIter.next();
							boolean isRootCustLocked = false;
							if(null!=mirror.getSourceMirrorCustAlgmts()){
								for(CustomerAlignment srcCustAlgn : mirror.getSourceMirrorCustAlgmts()) {
									if(srcCustAlgn.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
										custPushOfflineService.lockCustomerAlignment(srcCustAlgn,userDetails);//method 3
										srcCustAlgn.setLocked(true);
										isRootCustLocked = true;
										break;
									}
								}
							}
							LOGGER.info("===============If the Root customer cannot be locked for the mirror, then this mirror doesn't make sense to be processed and hence moving it to invalid mirrors.========= Started ========= ");
							WORKLOG.info("===============If the Root customer cannot be locked for the mirror, then this mirror doesn't make sense to be processed and hence moving it to invalid mirrors.========= Started ========= ");
							// If the Root customer cannot be locked for the mirror, then this mirror doesn't make sense to be processed and hence moving it to invalid mirrors.
							if(!isRootCustLocked) {
								custAlgmtDTO.addInvalidMirrorDTO(mirror);
								mirrorIter.remove();
							}
							LOGGER.info("===============If the Root customer cannot be locked for the mirror, then this mirror doesn't make sense to be processed and hence moving it to invalid mirrors.========= Ended ========= ");
						}
					}
					LOGGER.info("===============Locking the valid affliations at the base.========= Started ========= ");
					WORKLOG.info("===============Locking the valid affliations at the base.================== ");
					// Locking the valid affliations at the base.
					if(null!=custAlgmtDTO.getSourceBaseAffCustAlgmts()){
						for(CustomerAlignment affCust : custAlgmtDTO.getSourceBaseAffCustAlgmts()) {
							custPushOfflineService.lockCustomerAlignment(affCust, userDetails);//method 4
							affCust.setLocked(true);
						}
					}
					LOGGER.info("===============Locking the valid affliations at the base.========= Ended ========= ");

					LOGGER.info("===============Locking the affilated customers (non-root) at the mirrors========= Started ========= ");
					WORKLOG.info("===============Locking the affilated customers (non-root) at the mirrors================== ");
					// Locking the affilated customers (non-root) at the mirrors....
					if(null!=custAlgmtDTO.getMirrors()){
						for(MirrorCustAlgmtDTO mirror : custAlgmtDTO.getMirrors()) {
							for(CustomerAlignment srcCustAlgn : mirror.getSourceMirrorCustAlgmts()) {
								if(!srcCustAlgn.getCustomer().getId().equals(custAlgmtDTO.getSourceBaseCustAlgmt().getCustomer().getId())) {
									custPushOfflineService.lockCustomerAlignment(srcCustAlgn, userDetails);//method 5
									srcCustAlgn.setLocked(true);
								}
							}
						}
					}
					LOGGER.info("===============Locking the affilated customers (non-root) at the mirrors========= Ended ========= ");

				}catch(AlignmentServiceException algmntServiceException){
					if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_332.getCode().equals(algmntServiceException.getExceptionCode())){
						LOGGER.error("Customer Algmnt or TenantId or UserId is null");
						WORKLOG.error("Customer Algmnt or TenantId or UserId is null");
						ErrorDTO errorDTO = new ErrorDTO();
						errorDTO.setErrorCode(ErrorCode.ERROR_CP_2033);
						errorDTO.addParam(message.getOfflineReqId().toString(),"Customer Algmnt or TenantId or UserId is null");
						geoAlgmtDTO.addErrorDTO(errorDTO);
					}
					else if(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_257.getCode().equals(algmntServiceException.getExceptionCode())){
						LOGGER.error("Issue occurred while Locking the Customer Alignment");
						WORKLOG.error("Issue occurred while Locking the Customer Alignment");
						ErrorDTO errorDTO = new ErrorDTO();
						errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2024);
						((CustomerAlignmentDTO)geoAlgmtDTO.getCustAlgmtDTOs()).addErrorDTO(errorDTO);
					}
					handleError(message, geoAlgmtDTO);
					return;
				}
			
			}
			
			LOGGER.info("===============Lock base customer and affiliations for source and mirrors========= Ended ========= ");
			LOGGER.info("===============Locking the Root customer at all mirrors========= Ended ========= ");
			
			LOGGER.info("===============Process CR line items for source geo alignments================= ");
			WORKLOG.info("===============Process CR line items for source geo alignments================= ");
			//Process CR line items for source geo alignments
			geoOfflineService.processSourceOnSubmit(geoAlgmtDTO, userDetails);	//method 6			
			if(null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty()) {
				handleError(message, geoAlgmtDTO);
				return;
			}
			
			LOGGER.info("===============Process CR line items for target geo alignments================== ");
			WORKLOG.info("===============Process CR line items for target geo alignments================== ");
			//Process CR line items for target geo alignments
			geoOfflineService.processTargetOnSubmit(geoAlgmtDTO, userDetails);//method 7
			if(null != geoAlgmtDTO && null != geoAlgmtDTO.getErrors() && !geoAlgmtDTO.getErrors().isEmpty()) {
				handleError(message, geoAlgmtDTO);
				return;
			}
			
			LOGGER.info("===============Process CR line items for source & target customers alignments================== ");
			WORKLOG.info("===============Process CR line items for source & target customers alignments================== ");
			//Process CR line items for source & target customers alignments
			for(CustomerAlignmentDTO customerAlgmtDTO:geoAlgmtDTO.getCustAlgmtDTOs()){
				try {			
					LOGGER.info("========= Process Source On Submit for PUSH---===========");
					WORKLOG.info("========= Process Source On Submit for PUSH---===========");
					custPushOfflineService.processSourceOnSubmit(customerAlgmtDTO, userDetails);
					
					LOGGER.info("========= Process Target On Submit for PUSH---===========");
					WORKLOG.info("========= Process Target On Submit for PUSH---===========");
					custPushOfflineService.processTargetOnSubmit(customerAlgmtDTO, userDetails);
					
				} catch(ChangeRequestServiceException changeRequestServiceException) {
					LOGGER.error("Exception while processing customer.");
					WORKLOG.error("Exception while processing customer.");
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2035);
					errorDTO.addParam(message.getOfflineReqId().toString(),"Exception while processing customer.");
					geoAlgmtDTO.addErrorDTO(errorDTO);
					handleError(message, geoAlgmtDTO);
					return;
				}catch(CallPlanServiceException callPlanServiceException){
					LOGGER.error("Exception while processing customer.");
					WORKLOG.error("Exception while processing customer.");
					ErrorDTO errorDTO = new ErrorDTO();
					errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2035);
					errorDTO.addParam(message.getOfflineReqId().toString(),"Exception while processing customer.");
					geoAlgmtDTO.addErrorDTO(errorDTO);
					handleError(message, geoAlgmtDTO);
					return;
				}
			}
						
			
			try{
				LOGGER.info("===============Update offline request========= Started ========= ");
				WORKLOG.info("===============Update offline request================= ");
				//Update offline request			
				offlineService.updateRequestOnSuccess(message.getOfflineReqId(), userDetails);//method 10
				LOGGER.info("===============Update offline request========= Ended ========= ");
			}catch(AlignmentServiceException alignmentServiceException){
				LOGGER.error("Exception while updating CR Offline Status");
				WORKLOG.error("Exception while updating CR Offline Status");
				ErrorDTO errorDTO = new ErrorDTO();
				errorDTO.setErrorCode(ErrorCode.ERROR_ZM_2034);
				errorDTO.addParam(message.getOfflineReqId().toString(),"Exception while updating CR Offline Status");
				geoAlgmtDTO.addErrorDTO(errorDTO);
				handleError(message, geoAlgmtDTO);
				return;
			}
			
			
		}catch(Exception exception) {
			LOGGER.error("========Catch exception occur while locking mirror source geography or locking Root customer with mirrors or processing line items for zip or processing line item for custAlgmnt ==========");
			WORKLOG.error("========Catch exception occur while Zip movement=========="+exception.getMessage());
			//String failedReason ="Error occurs while locking mirror source geography or locking Root customer with mirrors or processing line items for zip or processing line item for custAlgmnt";
			handleError(message, geoAlgmtDTO) ;
			//offlineService.updateRequestOnError(message.getOfflineReqId(), userDetails,failedReason); 
			return;
		}
		
		handlePostProcessing(message, geoAlgmtDTO);
		//TODO : Notification in bulk.  : Notification handled in handlePostProcessing()
		LOGGER.info("=============== Execute of Zip Movement =========ended============ ");
		WORKLOG.info("=============== Execute of Zip Movement =========ended============ ");
	}
	
	private void handlePostProcessing(OfflineRequestMessage message, GeographyAlignmentDTO geoAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException {
		UserDetails userDetails = message.getUserDetails();

		boolean stillToProcess = offlineService.hasPendingRequest(message.getChngReqID(), userDetails);
		LOGGER.info("========= Has Pending Request for PUSH--- ended ===== and the result is " + stillToProcess);
		WORKLOG.info("========= Has Pending Request for PUSH--- ended ===== and the result is " + stillToProcess);
		if(stillToProcess) {
			return;
		}
		ChangeRequest changeRequest = new ChangeRequest();
		changeRequest.setId(message.getChngReqID());
		Map<ChangeRequestOfflineStatusType, Integer> statusMap = offlineService.getOffLineStatuses(changeRequest, userDetails);
		
		if(statusMap.containsKey(ChangeRequestOfflineStatusType.INITIATED) || statusMap.containsKey(ChangeRequestOfflineStatusType.PROCESSING)) {
			// Still something is in progress.
			return;
		}
		
		if(!statusMap.containsKey(ChangeRequestOfflineStatusType.COMPLETED)) {
			// Nothing is completed.
			return;
		}
		
		LOGGER.info("======== Calculate metrics and check for any violations ==== Started ======");
		//Calculate metrics and check for any violations
		

		boolean metricResult = geoOfflineService.handleMetricsOnSubmit(geoAlgmtDTO,userDetails);
		WORKLOG.info("======== Calculated metrics and check for any violations ==== metricResult ======"+metricResult);
		if(metricResult) {
			LOGGER.info("========If no violations, create approvers for source and target ==== Started ======");
			//TODO: check for metric violations. If no violations, proceed with CR update, approvers list population etc.,
			//approvers for source and target
			geoOfflineService.assignApproversForSourceAndTarget(geoAlgmtDTO, userDetails);
			
			LOGGER.info("========update change request and initiate workflow ==========");
			WORKLOG.info("========update change request and initiate workflow =========");
			//update change request and initiate workflow
			geoOfflineService.updateChangeRequests(geoAlgmtDTO, userDetails);	
			LOGGER.info("========unLock Geography Alignment On Approve ==== Started ======");
			
			LOGGER.info("========update Request On Success ==========");
			WORKLOG.info("========update Request On Success ==========");
			//Change the status of offline table
			/** STEP 2 : Update the the status of CR offline as APPROVED(3) **/
			offlineService.updateRequestOnSuccess(message.getOfflineReqId(), message.getUserDetails());
			
			
		} else {
			LOGGER.info("========metric failed - auto Reject Change Request On Submit ==========");
			WORKLOG.info("========metric failed - auto Reject Change Request On Submit ==========");
			crCommonService.autoRejectChangeRequestOnSubmit(geoAlgmtDTO.getMainCR(),userDetails);
		}
		
		
		
		LOGGER.info("========trigger Cr Notification ========");
		WORKLOG.info("========trigger Cr Notification ========");
		crNotifyService.triggerCrNotification(message.getChngReqID(), message.getUserDetails(),CommonConstants.CR_ACTION_SUBMIT);			
		
		//TO-DO : emitPostProcessEvents
		/*LOGGER.info("========Emitting from Zip movement process ==== Started ======");
		geoOfflineService.emitPostProcessEvents(geoAlgmtDTO, userDetails);
		LOGGER.info("========Emitting from Zip movement process ==== Ended ======");
		*/
	
	}
	
	private void handleError(OfflineRequestMessage message, GeographyAlignmentDTO geoAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException, ViewServiceException {
		UserDetails userDetails = message.getUserDetails();
		
		/** STEP1 : Update the the status of CR offline as CANCELLED(4) **/
		offlineService.updateRequestOnError(message.getOfflineReqId(), message.getUserDetails(), geoAlgmtDTO.getErrorCodeString());
		
		
		
		/** STEP2 : Update the status of mirror zip Almnt as CANCELLED for source **/
		//Target mirror is inactive, no need to update status as cancelled
		if(null != geoAlgmtDTO.getMirrors() && !geoAlgmtDTO.getMirrors().isEmpty()){
			for(MirrorGeoAlgmtDTO mirror : geoAlgmtDTO.getMirrors()){
				if(null != mirror.getSourceMirrorGeoAlgmt() && mirror.getSourceMirrorGeoAlgmt().isLocked()){
					geoAlignmentService.unLockGeographyAlignmentOnReject(mirror.getSourceMirrorGeoAlgmt(),userDetails);
				}
			}
		}
		
		/** STEP3 :  **/
		//zip Line Items (Base and Mirror, Source and Target) and Customer Line Items cancelled.
		//To-Do: Check if CR lIne Items are Created
		this.revertCRLineItems(geoAlgmtDTO, userDetails);
		
		/** STEP4 : **/
		//handle Customer Error
		if(null != geoAlgmtDTO.getCustAlgmtDTOs() && !geoAlgmtDTO.getCustAlgmtDTOs().isEmpty()){
			for(CustomerAlignmentDTO custAlgmtDTO : geoAlgmtDTO.getCustAlgmtDTOs()){
				if(null != custAlgmtDTO.getSourceBaseCustAlgmt()){
					handleCustomerError(message, custAlgmtDTO) ;
				}
					
			}
		}
		
		/** STEP4 : Update the status of base zip algmnt as CANCELLED **/
		if(null != geoAlgmtDTO.getSourceGeoAlgmt()){
			geoAlignmentService.unLockGeographyAlignmentOnReject(geoAlgmtDTO.getSourceGeoAlgmt(),userDetails);
		}
		
		/** STEP5 :  **/
		//zipAlgmnt base line items - deactive n status cancelled
		
//		/** STEP6 : DOUBT for Multiple Zip Movement**/
//		//update CR status cancelled
//		changeRequestDAOService.withdrawChangeRequest(geoAlgmtDTO.getMainCR(), userDetails) ;
		
		//TODO : unlockCustomerIfRequires
		//update the failed reason in Offline Table
		offlineService.updateRequestOnError(message.getOfflineReqId(), message.getUserDetails(), geoAlgmtDTO.getErrorCodeString());
		handlePostProcessing(message,geoAlgmtDTO);
	}
	
	private void handleCustomerError(OfflineRequestMessage message, CustomerAlignmentDTO custAlgmtDTO) throws ChangeRequestServiceException, AlignmentServiceException, ViewServiceException {
		//Unlock the Root Customer at base 
		custPushOfflineService.unLockCustomerAlignment(custAlgmtDTO.getSourceBaseCustAlgmt(), message.getUserDetails());
		
		//Unlock the Affiliated Customer at base
		if(custAlgmtDTO.getSourceBaseAffCustAlgmts() != null && !custAlgmtDTO.getSourceBaseAffCustAlgmts().isEmpty()){
			for(CustomerAlignment  affCustomerAlignment : custAlgmtDTO.getSourceBaseAffCustAlgmts()){
				if(affCustomerAlignment.isLocked()) {
					custPushOfflineService.unLockCustomerAlignment(affCustomerAlignment, message.getUserDetails());
				}
			}
		}
		
		//Unlock the Root and Affilaited Customers at Mirrors
		for(MirrorCustAlgmtDTO mirrorCustAlgmtDTO : custAlgmtDTO.getMirrors()){
			if(custAlgmtDTO.getMirrors() != null && !custAlgmtDTO.getMirrors().isEmpty()){
				if(mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts() != null && !mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts().isEmpty()){
					for(CustomerAlignment mirrCustAl : mirrorCustAlgmtDTO.getSourceMirrorCustAlgmts()){
						if(mirrCustAl.isLocked()) {
							custPushOfflineService.unLockCustomerAlignment(mirrCustAl, message.getUserDetails());
						}
					}
				}
			}
		}
	}
	
	private void revertCRLineItems(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) {
		try {
			//zipAlgmnt mirrors and base line items - deactive n status cancelled
			crOfflineService.updateCRLineStatusForZip(geoAlgmtDTO.getMainCR(), ChangeRequestStatusType.CANCELLED.getId(), geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode(), userDetails);
			//zipAlgmnt Mirror line items - deactive n status cancelled
			if(null != geoAlgmtDTO.getMirrors() && !geoAlgmtDTO.getMirrors().isEmpty() ){
				for(MirrorGeoAlgmtDTO mirrorGeoAlgmtDTO : geoAlgmtDTO.getMirrors()){
					crOfflineService.updateCRLineStatusForZip(mirrorGeoAlgmtDTO.getChildCR(), ChangeRequestStatusType.CANCELLED.getId(), geoAlgmtDTO.getSourceGeoAlgmt().getPostalCode(), userDetails);
				}
			}

			for(CustomerAlignmentDTO custAlgmtDTO : geoAlgmtDTO.getCustAlgmtDTOs()){
				if(null != custAlgmtDTO.getCrLineItemsForSource() && custAlgmtDTO.getCrLineItemsForSource().size() > 0){
					for(CustomerAlignmentChangeRequestDetails crSrcLineItem : custAlgmtDTO.getCrLineItemsForSource()) {
						if(crSrcLineItem.getParentChangeRequest() != null && crSrcLineItem.getOldCustomerAlignment() != null){
							crOfflineService.updateCRLineStatusForCustAlgmt(crSrcLineItem.getParentChangeRequest().getId(), crSrcLineItem.getOldCustomerAlignment().getId(), ChangeRequestStatusType.CANCELLED.getId(), userDetails);
						}else{
							LOGGER.error("Mandatory fields missing while reverting the CR line status..");
						}
					}
				}
				if(null != custAlgmtDTO.getCrLineItemsForTarget() && custAlgmtDTO.getCrLineItemsForTarget().size() > 0){
					for(CustomerAlignmentChangeRequestDetails crTargetLineItem : custAlgmtDTO.getCrLineItemsForTarget()) {
						if(crTargetLineItem.getParentChangeRequest() != null && crTargetLineItem.getNewCustomerAlignment() != null){
							crOfflineService.updateCRLineStatusForCustAlgmt(crTargetLineItem.getParentChangeRequest().getId(), crTargetLineItem.getNewCustomerAlignment().getId(), ChangeRequestStatusType.CANCELLED.getId(), userDetails);
						}else{
							LOGGER.error("Mandatory fields missing while reverting the CR line status..");
						}
					}
				}

			}
		} catch(ChangeRequestServiceException e) {
			LOGGER.error("Error in reverting the CR line status..",e);
			WORKLOG.error("Error in reverting the CR line status..",e);
		}
	}
	
	
	
}
