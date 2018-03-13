package com.cognizant.opserv.sp.view.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.MessageType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.event.messages.EntityChangeEvent;
import com.cognizant.opserv.sp.view.service.EntityViewGeneratorService;
import com.cognizant.opserv.sp.view.service.IRunAlignmentRulesService;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class EntityChangeEventListener
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 *  @since 01/04/2016
 * ***************************************************************************
 */
@Component("entityChangeEventListener")
public class EntityChangeEventListener implements MessageListener{
	
	/** The entityViewGeneratorService. */
	@Autowired
	private EntityViewGeneratorService entityViewGeneratorService;
	
	/** The runAlignmentRulesService. */
	@Autowired
	@Qualifier("runAlignmentRulesService")
	private IRunAlignmentRulesService runAlignmentRulesService;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(EntityViewGeneratorService.class);
	
	public void onMessage(Message message) {
		if(message instanceof ObjectMessage) {
			LOGGER.info("Start of listener...");
			ObjectMessage objMessage = (ObjectMessage)message;
			try {
				EntityChangeEvent viewMsg = (EntityChangeEvent) objMessage.getObject();
				if(null != viewMsg && null != viewMsg.getUserDetails() && null != viewMsg.getMessageType()){
					TenantContext.setTenantKey(viewMsg.getUserDetails().getTenantCode());
					if(null != viewMsg.getEventType()){
						LOGGER.info("The Event type is : "+viewMsg.getEventType().toString());
					}
					if(viewMsg.getMessageType().equals(MessageType.VIEW)){
						entityViewGeneratorService.generateView(viewMsg);
					}else if(viewMsg.getMessageType().equals(MessageType.SCR)){
						runAlignmentRulesService.runAlgmntRules(viewMsg);
					}
				}
				message.acknowledge();
				LOGGER.info("Message read");
				
			} catch (JMSException jmse) {
				LOGGER.error(jmse.getMessage(), jmse);
			} catch (AlignmentServiceException ase) {
				LOGGER.error(ase.getMessage(), ase);
			} catch (RuntimeException runEx){
				LOGGER.error(runEx.getMessage(), runEx);
			}
		}
	}

}
