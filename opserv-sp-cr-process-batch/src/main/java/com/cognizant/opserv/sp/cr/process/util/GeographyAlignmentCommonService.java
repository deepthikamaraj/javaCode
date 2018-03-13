package com.cognizant.opserv.sp.cr.process.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class GeographyAlignmentCommonService {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeographyAlignmentCommonService.class);
	
	//@Autowired
	//private GeoAlignmentProcessInternalService geoAlignmentProcessInternalService;
	
	@Autowired
	private ChangeRequestOfflineDAOService tempChngreqDAOService;


	//@Override
	@Transactional
	public void saveZipMovementOffline(
			OfflineRequestMessage offlineMessage)
			throws AlignmentServiceException, MetricViolationException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CustomerServiceException,
			CallPlanServiceException {
		
		LOGGER.info("****** zip Movement Offline started*****");
		try{
			LOGGER.info("******Data in offlineMessage Zip Movement coming from the Queue ******==== "+offlineMessage.toString());

			//To check Status Of TChngreqOffline
			boolean checkStatusOfTChngreqOffline = tempChngreqDAOService.checkStatusOfTChngreqOffline(offlineMessage.getOfflineReqId(), offlineMessage.getUserDetails()); 
			LOGGER.info("****** checkStatusOfTChngreqOffline*****"+checkStatusOfTChngreqOffline);
			
			if(checkStatusOfTChngreqOffline){
				
				// Offline status updation of CR to "Processing"  -  New Transaction 1
				//geoAlignmentProcessInternalService.updateCROfflineStatus(offlineMessage);
				LOGGER.info("=========== updated CR Offline Status to Processing for Change Request ID : ===========*****"+ offlineMessage.getChngReqID());

				// Save Zip Line Items -  New Transaction 2
				//geoAlignmentProcessInternalService.insertZipLineItemsForSave(offlineMessage);
				LOGGER.info("=========== inserted Zip Line Items For Save Sucessfully===========*****");

				// Calling CR Status updation  to Pending Submission- New Transaction 3
				//geoAlignmentProcessInternalService.updateCRStatus(offlineMessage);
				LOGGER.info("=========== updated CR Offline Status to Pending Submission for Change Request ID : ===========*****"+ offlineMessage.getChngReqID());

			} else{
				LOGGER.warn("================Status of CR Offline Id "+ offlineMessage.getOfflineReqId() +" is not Initiated, hence couldn't move the zips... =======");
				
				/*geoAlignmentProcessInternalService.updateCROfflineStatus(
						ChangeRequestOfflineStatusType.CANCELLED.getId(),
						offlineMessage.getOfflineReqId(),
						offlineMessage.getUserDetails(), CommonConstants.NOT_INTIATED);*/
				LOGGER.warn("================Status of CR Offline Id "+ offlineMessage.getOfflineReqId() +" is not Initiated, hence cancelling the CR=======");
			}
		} catch (OpservDataAccessException exception) {
			LOGGER.error("EXCEPTION OCCURED"+exception.getMessage());
			/*geoAlignmentProcessInternalService.updateCROfflineStatus(
					ChangeRequestOfflineStatusType.CANCELLED.getId(),
					offlineMessage.getOfflineReqId(),
					offlineMessage.getUserDetails(), exception.getMessage());*/
			LOGGER.error("******To update t_chngreq_offlince table to CANCELLED for ******==== "
					+ offlineMessage.getOfflineReqId());
			LOGGER.error("CR status updated to cancelled because of exception ::"+exception.getMessage());
			throw exception;
		}

	}
	
}
