package com.cognizant.opserv.sp.notification.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.model.CRNotificationMessage;
import com.cognizant.opserv.sp.notification.util.CrNotificationAdapter;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 * @class CRNotificationListener ,listener classes for CR notification messages
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
@Component("crNotificationListener")
public class CRNotificationListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(CRNotificationListener.class);
	
	@Autowired
	private CrNotificationAdapter crNotificationAdapter;
	
	/**
	 * @Method onMessage - This method executes the message
	 * @param message - holds message info
	 * @return void	
	 */
	public void onMessage(Message message) {
		
		if(message instanceof ObjectMessage){
			try {
				 CRNotificationMessage notifyMessage = (CRNotificationMessage) (((ObjectMessage) message).getObject());
				 LOGGER.info("Message consumed.chngReqId="+notifyMessage.getChngReqId()+"action="+notifyMessage.getAction());
				 if(null != notifyMessage.getTenantCode()){
						BatchMultiTenancyUtil.setTenantContext(notifyMessage.getTenantCode());
				 }
				crNotificationAdapter
						.triggerCrNotification(notifyMessage.getChngReqId(),
								notifyMessage.getTenantId(),
								notifyMessage.getAction());
				 message.acknowledge();
			}
				catch (JMSException e) {
				LOGGER.error("An JMS Exception occured", e);
			} 
			catch (Exception e) {
				LOGGER.error("An Exception occured in notification", e);
			} 
		 }else{
			 throw new IllegalArgumentException("Message must be of type MapMessage");
		 }
	}
	
}
