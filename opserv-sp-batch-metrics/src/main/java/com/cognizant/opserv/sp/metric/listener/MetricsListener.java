package com.cognizant.opserv.sp.metric.listener;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.CannotCreateTransactionException;

import com.cognizant.opserv.sp.common.MetricConstants;
import com.cognizant.opserv.sp.metric.dao.MetricDAO;
import com.cognizant.opserv.sp.metric.producer.ResultsMessageProducer;
import com.cognizant.opserv.sp.model.metric.QueueMessage;
import com.cognizant.opserv.tenant.ctx.TenantContext;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/******************************************************************************
 *  
 * @class MetricsListener executes the metrics info.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 06/01/2016
 *
 *****************************************************************************/
public class MetricsListener implements MessageListener {

	/** The MetricDAO */
	@Autowired
	private MetricDAO metricDAO;

	/** The results producer. */
	@Autowired
	@Qualifier("resultsMessageProducer")
	private ResultsMessageProducer resultsProducer;

	/** The Constant LOGGER. */
	private static final OpservLogger  LOGGER = OpservLoggerFactory.getOpservLogger(MetricsListener.class);

	/**
	 * @method getResultsProducer - the results producer.
	 * @param
	 * @return the results producer
	 */
	public ResultsMessageProducer getResultsProducer() {
		return resultsProducer;
	}

	/**
	 * @method setResultsProducer - Sets the results producer.
	 *
	 * @param resultsProducer the new results producer
	 * @return void
	 */
	public void setResultsProducer(ResultsMessageProducer resultsProducer) {
		this.resultsProducer = resultsProducer;
	}

	/**
	 * @Method onMessage - This method executes the message
	 * @param message - holds message info
	 * @return void	
	 */
	public void onMessage(Message message) {

		if (message instanceof ObjectMessage) {		

			processMessage(message);

		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

	/**
	 * @method processMessage - Process message.
	 *
	 * @param message the message
	 * @return void	
	 */
	private void processMessage(Message message){
		try{
			Long startTime = System.currentTimeMillis();
			LOGGER.info("Received Msg Time===== " +startTime);
			QueueMessage expression = (QueueMessage)(((ObjectMessage) message).getObject());
			Map<String,Object> messageInfo = expression.getMap();

			Long spId =  (Long)messageInfo.get(MetricConstants.SP_ID);
			Integer metricId =  (Integer) messageInfo.get(MetricConstants.MTR_ID); 
			Integer typeId = (Integer)messageInfo.get(MetricConstants.TYPE_ID);
			Integer featureId = (Integer)messageInfo.get(MetricConstants.FEATURE_ID);
			String storedProc =(String) messageInfo.get(MetricConstants.STORE_PROC);				
			Short tenantId =  (Short) messageInfo.get(MetricConstants.TENANT_ID); 
			String passedTenantKey =  (String) messageInfo.get(MetricConstants.TENANT_KEY); 

			TenantContext.setTenantKey(passedTenantKey);
			
			LOGGER.info(" spId = " + spId + " metricId = " + metricId 
					+" typeId = " + typeId +" featureId = " + featureId +" storedProc = " + storedProc +
					" tenantId = " + tenantId + " passedTenantKey = " +passedTenantKey );
			JdbcTemplate jdbcTemplate = metricDAO.getJdbcTemplate();
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(storedProc);
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put(MetricConstants.SP_ID, spId);
			inParamMap.put(MetricConstants.MTR_ID, metricId);
			inParamMap.put(MetricConstants.FEATURE_ID,featureId);
			inParamMap.put(MetricConstants.TYPE_ID, typeId);
			SqlParameterSource input = new MapSqlParameterSource(inParamMap);

			Long stTiem = System.currentTimeMillis();
			Map<String, Object> storeProcRes = simpleJdbcCall.execute(input);
			LOGGER.info("Store Proc Exec Time ==== " +(System.currentTimeMillis() - stTiem)/1000);
			messageInfo.put("results",storeProcRes);
			//NOTE ACKNOWLEDGE THE MESSAGE OTHERWISE LISTENERS NEVER LISTEN OR RECIEVE
			message.acknowledge();
			LOGGER.info("==== sending message ======" + expression);
			resultsProducer.sendMessage(expression);
			LOGGER.info(" Sending Msg ==== " +(System.currentTimeMillis() - startTime)/1000);

		}catch (JMSException ex) {	
			LOGGER.error(" JMSException At Listener  "+ex.getMessage(),ex);			
		}catch(BadSqlGrammarException ex){
			LOGGER.error(" BadSqlGrammarException  "+ex.getMessage(),ex);	
		}catch(JDBCConnectionException ex){
			LOGGER.error(" JDBCConnectionException  "+ex.getMessage(),ex);			 
		}catch(CannotCreateTransactionException ex){
			LOGGER.error(" CannotCreateTransactionException  "+ex.getMessage(),ex);	
		}catch(DataAccessResourceFailureException ex){
			LOGGER.error(" DataAccessResourceFailureException  "+ex.getMessage(),ex);
		}catch(GenericJDBCException ex){
			LOGGER.error(" GenericJDBCException  "+ex.getMessage(),ex);	
		}catch(Exception ex){
			LOGGER.error(" Exception At Listener  "+ex.getMessage(),ex);			
		}finally{
			try {
				TenantContext.clearTenantKey();
				message.acknowledge();
				LOGGER.info("Acknowledged the store message ..");
			} catch (JMSException e) {
				LOGGER.error("Error - on ack store message ",e);
			}
		}
	}
}