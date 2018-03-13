package com.cognizant.opserv.sp.metric.listener;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.CannotCreateTransactionException;

import com.cognizant.opserv.sp.common.MetricConstants;
import com.cognizant.opserv.sp.metric.dao.MetricDAO;
import com.cognizant.opserv.sp.model.metric.QueueMessage;
import com.cognizant.opserv.tenant.ctx.TenantContext;
/******************************************************************************
 *  
 * @class MetricStaticBaseValueListener executes the metric base value info.
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 04/01/2016
 *
 *****************************************************************************/
public class MetricStaticBaseValueListener implements MessageListener {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MetricStaticBaseValueListener.class);

	/** The MetricDAO */
	@Autowired
	private MetricDAO metricDAO;

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
			QueueMessage queueinfo = (QueueMessage)(((ObjectMessage) message).getObject());
			Map<String,Object> messageInfo = queueinfo.getMap();
			
			Integer metricId =  (Integer) messageInfo.get(MetricConstants.MTR_ID); 
			Integer typeId = (Integer)messageInfo.get(MetricConstants.TYPE_ID);
			Integer featureId = (Integer)messageInfo.get(MetricConstants.FEATURE_ID);
			Long salesPosId = (Long) messageInfo.get("salesPosId");
			String storedProc =(String) messageInfo.get("storedproc");
			String passedTenantKey =  (String) messageInfo.get(MetricConstants.TENANT_KEY); 

			TenantContext.setTenantKey(passedTenantKey);
			
			LOGGER.info(" salesPosId = " + salesPosId + " metricId = " + metricId 
					+" typeId = " + typeId +" featureId = " + featureId +" storedProc = " + storedProc +
					" passedTenantKey = " +passedTenantKey );
			
			JdbcTemplate jdbcTemplate = metricDAO.getJdbcTemplate();
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(storedProc);
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put(MetricConstants.SP_ID, salesPosId);
			inParamMap.put(MetricConstants.MTR_ID, metricId);
			inParamMap.put(MetricConstants.FEATURE_ID, featureId);
			inParamMap.put(MetricConstants.TYPE_ID, typeId);
			SqlParameterSource input = new MapSqlParameterSource(inParamMap);
			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(input);
			LOGGER.info("simpleJdbcCallResult=========================="+simpleJdbcCallResult);
			//NOTE ACKNOWLEDGE THE MESSAGE OTHERWISE LISTENERS NEVER LISTEN OR RECIEVE
			message.acknowledge();
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