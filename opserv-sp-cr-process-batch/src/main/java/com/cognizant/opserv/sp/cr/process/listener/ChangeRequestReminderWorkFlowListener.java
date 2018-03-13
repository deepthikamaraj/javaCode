package com.cognizant.opserv.sp.cr.process.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cognizant.opserv.sp.cr.process.internal.service.ChangeRequestOfflineService;
import com.cognizant.opserv.sp.event.messages.ChangeRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

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
@Component("changeRequestReminderWorkflowListener")
@EnableTransactionManagement
public class ChangeRequestReminderWorkFlowListener implements MessageListener {

	@Autowired
	private ChangeRequestOfflineService changeRequestProcessInternalService;

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestReminderWorkFlowListener.class);

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			LOGGER.info("msg recieved");
			ObjectMessage objMessage = (ObjectMessage) message;

			try {
				ChangeRequestMessage changeRequestMessage = (ChangeRequestMessage) objMessage.getObject();
				BatchMultiTenancyUtil.setTenantContext(changeRequestMessage.getUserDetails().getTenantCode());
				message.acknowledge();
				ChangeRequest changeRequest = new ChangeRequest();
				changeRequest.setId(changeRequestMessage.getChngReqID());
				SalesPosition salesPosition = new SalesPosition();
				SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
				salesOrgHierarchy.setId(changeRequestMessage.getSalesHierID());
				salesPosition.setId(changeRequestMessage.getSalesPosID());
				salesPosition.setHierarchy(salesOrgHierarchy);
				try {
					changeRequestProcessInternalService.rejectChangeRequest(changeRequest, changeRequestMessage.getUserDetails(), salesPosition);
				} catch (ChangeRequestServiceException e) {
				} catch (AffiliationServiceException e) {
				} catch (SalesPositionServiceException e) {
				} catch (CustomerServiceException e) {
				} catch (CallPlanServiceException e) {
				}
				
			} catch (JMSException jmse) {
				LOGGER.error(jmse.getMessage(), jmse);
			} finally {
				BatchMultiTenancyUtil.clearTenantContext();
			}
		}
	}
}