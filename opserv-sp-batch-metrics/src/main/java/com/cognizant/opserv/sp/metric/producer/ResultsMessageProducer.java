package com.cognizant.opserv.sp.metric.producer;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.model.metric.QueueMessage;

/******************************************************************************
 *  
 * @class ResultsMessageProducer sends  the metric info messages back.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 06/01/2016
 *
 *****************************************************************************/
@Component
public class ResultsMessageProducer {

	/** The results producer template. */
	@Autowired
	@Qualifier("resProdTemplate")
	private JmsTemplate resultsProducerTemplate;	

	/**
	 * @Method sendMessage - Gets the results producer template.
	 * @param
	 * @return the results producer template
	 */
	public JmsTemplate getResultsProducerTemplate() {
		return resultsProducerTemplate;
	}

	/**
	 * @Method sendMessage - Sets the results producer template.
	 *
	 * @param resultsProducerTemplate the new results producer template
	 * @return void
	 */
	public void setResultsProducerTemplate(JmsTemplate resultsProducerTemplate) {
		this.resultsProducerTemplate = resultsProducerTemplate;
	}

	/**
	 * @Method sendMessage - This method sends the message
	 * @param message - holds message info
	 * @return void	
	 */
	public void sendMessage(final QueueMessage result) throws JMSException {
		resultsProducerTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage resultObj = session.createObjectMessage(result);
				resultObj.setStringProperty("CreatedOn", new Date().toString());
				return resultObj;
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
	public void sendMessage(Session session, final List<QueueMessage> expression)throws JMSException {	
		MessageProducer producer = session.createProducer(resultsProducerTemplate.getDefaultDestination());
		for (int i = 0; i < expression.size(); i++) {			
			ObjectMessage message = session.createObjectMessage(expression.get(i));
			message.setStringProperty("CreatedOn", new Date().toString());
			producer.send(message);			
		}
		//NOTE COMMIT ALL MESSAGES WHICH WE SENT
		session.commit();
	}
}
