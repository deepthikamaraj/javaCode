/**
 * 
 */
package com.cognizant.opserv.sp.metrics.business.components;

/******************************************************************************
 *  
 * @class ResultsMessageListener for handling Messaging Listener.
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 10/29/2014
 * COPYRIGHT (C) 2014 Cognizant, all rights reserved.
 *****************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.cognizant.opserv.sp.model.metric.QueueMessage;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * The listener interface for receiving resultsMessage events.
 * The class that is interested in processing a resultsMessage
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addResultsMessageListener<code> method. When
 * the resultsMessage event occurs, that object's appropriate
 * method is invoked.
 *
 * @see ResultsMessageEvent
 */
public class ResultsMessageListener implements MessageListener {

	/**
	 * latch - holds the count down value.
	 */
	private final CountDownLatch latch;

	/**
	 * msgs - holds the in coming messages.
	 */
	private List<QueueMessage> msgs;

	/**
	 * tenantId - holds the tenant information.
	 */
	private Short tenantId;

	/**
	 * failedmsgs - holds the collection of failed messages information.
	 */
	private List<Exception> failedmsgs = new ArrayList<Exception>();

	private String subscriberId;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ResultsMessageListener.class);

	/**
	 * Instantiates a new results message listener.
	 *
	 * @param latch the latch
	 * @param msgs the msgs
	 * @param exeIds the exe ids
	 * @param tenantId the tenant id
	 */
	public ResultsMessageListener(CountDownLatch latch, List<QueueMessage> msgs,Short tenantId) {
		this.latch = latch;
		this.msgs = msgs;
		this.tenantId = tenantId;
	}
	
	public ResultsMessageListener(String subscriberId, CountDownLatch latch, List<QueueMessage> msgs,Short tenantId) {
		this.subscriberId = subscriberId;
		this.latch = latch;
		this.msgs = msgs;
		this.tenantId = tenantId;
	}

	/**
	 * Gets the msgs.
	 *
	 * @return msgs - holds the message info.
	 * @method getMsgs - Gets the collection of jsm message.
	 */
	public List<QueueMessage> getMsgs() {
		return msgs;
	}	

	/**
	 * Gets the latch.
	 *
	 * @return latch - holds count down latch value.
	 * @method getLatch - Gets the count down latch value.
	 */
	public CountDownLatch getLatch() {
		return latch;
	}	

	/**
	 * Gets the failedmsgs.
	 *
	 * @return latch - holds count down latch value.
	 * @method getFailedmsgs - Gets the failed messages info.
	 */
	public List<Exception> getFailedmsgs() {
		return failedmsgs;
	}

	/**
	 * On message.
	 *
	 * @param message the message
	 * @return void
	 * @method onMessage - Gets the messages info.
	 */
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			try {
				LOGGER.info("********** GOT a result for metric..........");
				QueueMessage msg = (QueueMessage) (((ObjectMessage) message).getObject());
				Map<String,Object> messageInfo = msg.getMap();
				LOGGER.info("Subscriber == "+subscriberId+" == Received Message == " + msg);
				String resMsgSubscriberId = (String) messageInfo.get("subscriberId");	
				if (resMsgSubscriberId != null && subscriberId != null &&
						resMsgSubscriberId.equals(subscriberId) && tenantId.equals((Short)msg.getValue("tenantId"))) {
					//NOTE STEP_1ADD MESSAGET
					LOGGER.info("===Listener Time === " +System.currentTimeMillis());
					msgs.add(msg); 
					LOGGER.info("Metric Message map : "+msg.getMap());
					//NOTE STEP_ DECREASE THE COUNT2
					latch.countDown();
					LOGGER.info("Latch down countdown...");
				}
			} catch (JMSException ex) {
				failedmsgs.add(ex);
			}catch (Exception e) {
				failedmsgs.add(e);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type ObjectMessage");
		}
	}

}