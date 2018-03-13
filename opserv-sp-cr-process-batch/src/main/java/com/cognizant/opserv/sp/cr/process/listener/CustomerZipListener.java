package com.cognizant.opserv.sp.cr.process.listener;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.cr.process.internal.service.EditRequestFacade;
import com.cognizant.opserv.sp.cr.process.internal.service.PullRequestFacade;
import com.cognizant.opserv.sp.cr.process.internal.service.PushRequestFacade;
import com.cognizant.opserv.sp.cr.process.internal.service.ZipMovementRequestFacade;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class RequestProcessEventListener
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 *  @since 01/04/2016
 * ***************************************************************************
 */
/**
 * ****************************************************************************.
 * 
 * @class RequestProcessEventListener
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 01/04/2016
 *        ************************************************************
 *        ***************
 */
@Component("customerZipListener")
@EnableTransactionManagement
public class CustomerZipListener implements MessageListener {
	

	@Autowired 
	private PullRequestFacade pullRequestFacade;
	
	@Autowired 
	private EditRequestFacade editRequestFacade;
		
	@Autowired 
	private PushRequestFacade pushRequestFacade;
	
	@Autowired
	private ZipMovementRequestFacade zipMoveFacade;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerZipListener.class);
	
	private static final OpservLogger WORKLOG = OpservLoggerFactory.getOpservLogger(CommonConstants.CR_PROCESSING_LOG);

	private void setWorkLogContext(OfflineRequestMessage offlineMessage) {
		MDC.put(CommonConstants.CHNG_REQ_ID, offlineMessage.getChngReqID().toString());
		MDC.put(CommonConstants.OFFLINE_REQ_ID, offlineMessage.getOfflineReqId().toString());
		MDC.put(CommonConstants.CR_WORK_LOG_ID, offlineMessage.getChngReqID()+".log");
		MDC.put(CommonConstants.CR_OFFLINE_WORK_LOG_ID, offlineMessage.getChngReqID()+"-"+offlineMessage.getOfflineReqId()+".log");
		WORKLOG.info("Processing of Offline request. Started at "+new Date().toString());
		MDC.put("START_TIME_MS",""+System.currentTimeMillis());
		
	}
	
	private void removeWorkLogContext() {
		WORKLOG.info("Processing of Offline request. Completed at "+new Date().toString());
		long totalTimeInMs = System.currentTimeMillis() -  Long.valueOf(MDC.get("START_TIME_MS")).longValue();
		WORKLOG.info("Time taken in seconds = "+(double)(totalTimeInMs/1000));
		
		MDC.remove(CommonConstants.CHNG_REQ_ID);
		MDC.remove(CommonConstants.OFFLINE_REQ_ID);
		MDC.remove(CommonConstants.CR_WORK_LOG_ID);
		MDC.remove(CommonConstants.CR_OFFLINE_WORK_LOG_ID);
		MDC.remove("START_TIME_MS");
	}
	
	public void onMessage(Message message) {
		LOGGER.info("inside onMessage ");
		if (!(message instanceof ObjectMessage)) {
			return;
		}
		LOGGER.info("msg recieved");
		ObjectMessage objMessage = (ObjectMessage) message;
		OfflineRequestMessage offlineMessage = null;
		try {
			offlineMessage = (OfflineRequestMessage) objMessage.getObject();
			
			if(offlineMessage == null || offlineMessage.getChngReqID() == null || offlineMessage.getOfflineReqId() == null || offlineMessage.getUserDetails() == null) {
				LOGGER.error("The offlineMessage cannot be processed due to either missing Change Req / Offline Request / UserDetails information.");
				message.acknowledge();
				return;
			}
			BatchMultiTenancyUtil.setTenantContext(offlineMessage.getTenantCode());
			setWorkLogContext(offlineMessage);
			message.acknowledge();
			LOGGER.info("message.acknowledge()");

			try {
				if (offlineMessage.getTriggerId().equals(ChangeRequestTriggerType.PUSH_CUSTOMER.getId())) {
					LOGGER.info("=========== Inside PUSH CUSTOMER if-block after the Queue message from CustomerZipListener =========");
					pushRequestFacade.pushCustomer(offlineMessage);
					
				}else if(offlineMessage.getTriggerId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())){
					LOGGER.info("=========== Inside PULL CUSTOMER if-block after the Queue message from CustomerZipListener =========");
					pullRequestFacade.pullCustomer(offlineMessage);
				} else if(offlineMessage.getTriggerId().equals(ChangeRequestTriggerType.PUSH_ZIP.getId())){
					LOGGER.info("Inside PUSH_ZIP if-block");
					zipMoveFacade.moveZip(offlineMessage);
				} else if (offlineMessage.getTriggerId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
					LOGGER.info("Inside EDIT CUSTOMER if-block");
					editRequestFacade.editCustomer(offlineMessage);
				}
				LOGGER.info("AFTER processing CustomerZipListener");
			} catch (AlignmentServiceException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (ChangeRequestServiceException e) {
				LOGGER.error(e.getMessage(), e);
			} catch (MetricExecutionException e) {
				LOGGER.error(e.getMessage(), e);
			}
		} catch (JMSException jmse) {
			LOGGER.error("JMSException "+jmse.getMessage(), jmse);
		}catch(Exception e){
			LOGGER.error("Exception : "+e.getMessage(), e);
		} finally {
			LOGGER.info("Inside finally block");
			removeWorkLogContext();
			BatchMultiTenancyUtil.clearTenantContext();
			
		}
	}
}

