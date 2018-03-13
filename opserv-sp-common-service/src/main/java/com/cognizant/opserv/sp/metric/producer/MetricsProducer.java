package com.cognizant.opserv.sp.metric.producer;

/******************************************************************************
*  
* @class MetricsProducer provides Messaging Functionality.
* @author Cognizant Technology Solutions
* @version OpServ 1.0
* @since 10/29/2014
* COPYRIGHT (C) 2014 Cognizant, all rights reserved.
*****************************************************************************/

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.model.metric.QueueMessage;

/**
 * The Class MetricsProducer.
 */
@Component
public class MetricsProducer {

	/** jmsTemplate - jsm template to send messages. */
	@Autowired
	@Qualifier("metricsTemplate")
	private JmsTemplate jmsTemplate;


	/**
	 * Sets the jms template.
	 *
	 * @param jmsTemplate the new jms template
	 * @return void
	 * @method setJmsTemplate - Sets the jsm template to send messages
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * Gets the jms template.
	 *
	 * @return jmsTemplate - jsm template to send messages
	 * @method getJmsTemplate - Gets the jsm template to send messages
	 */
	public JmsTemplate getJmsTemplate() {
		return this.jmsTemplate;
	}

	/**
	 * Send message.
	 *
	 * @param message the message
	 * @return void
	 * @throws JMSException the jMS exception
	 * @method sendMessage - Send the jms message
	 */
	public void sendMessage(final QueueMessage message) throws JMSException {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage msg = session.createObjectMessage(message);
				msg.setStringProperty("CreatedOn", new Date().toString());
				return msg;
			}
		});
	}
	
	/**
	 * Send message.
	 *
	 * @param session - holds the session info.
	 * @param expression - holds the collection of messages info.
	 * @return void
	 * @throws JMSException the jMS exception
	 * @method sendMessage - Send the jsm message
	 */
	/*public void sendMessage(Session session, final List<QueueMessage> expression)throws JMSException {		
		MessageProducer producer = session.createProducer(jmsTemplate.getDefaultDestination());
		for (int i = 0; i < expression.size(); i++) {			
			ObjectMessage message = session.createObjectMessage(expression.get(i));
			message.setStringProperty("CreatedOn", new Date().toString());
			producer.send(message);			
		}
		//NOTE COMMIT ALL MESSAGES WHICH WE SENT
		session.commit();
	}*/
}
