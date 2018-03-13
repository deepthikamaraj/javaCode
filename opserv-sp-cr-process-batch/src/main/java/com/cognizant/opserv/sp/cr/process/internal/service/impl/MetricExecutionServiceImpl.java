
package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.MetricTriggerType;
import com.cognizant.opserv.sp.cr.process.internal.service.MetricExecutionService;
import com.cognizant.opserv.sp.exception.MetricExecutionException;
import com.cognizant.opserv.sp.metric.producer.MetricsProducer;
import com.cognizant.opserv.sp.metrics.business.components.ResultsMessageListener;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.metric.QueueMessage;
import com.cognizant.opserv.sp.persistence.dao.service.MetricDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class MetricExecutionServiceImpl contains the metric execution services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/09/2016
 * ***************************************************************************
 */
@Service
public class MetricExecutionServiceImpl implements MetricExecutionService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(MetricExecutionServiceImpl.class);

	/** The Constant SP. */
	private final String SP = "SP"; 

	/** The Metrics Producer. */
	@Autowired(required=false)
	private MetricsProducer metricsProducer;

	/** The connection factory. */
	@Autowired
	@Qualifier("metricConnectionFactory")
	private CachingConnectionFactory connectionFactory;

	/** The Constant ACTIVEMQ_METRIC_RESULT_TOPIC. */
	public static final String ACTIVEMQ_METRIC_RESULT_TOPIC = "metricsResultsTopic";

	/** The Constant connectionTimeout. */
	@Value("${sp.metric.result.listener.wait.time:45}")
	private Integer waitingTime;

	/**
	 * SalesPositionDAOService has the services to fetch sales position details.
	 */
	@Autowired
	private SalesPositionDAOService salPosDAOServ;

	/**
	 * MetricDAOService has the services to fetch metric details.
	 */
	@Autowired
	private MetricDAOService metricDAOService;

	/**
	 * Process the metrics
	 * @method processCalculativeMetrics
	 * @param salespos the sales position
	 * @param mtrTrgType the metric trigger type
	 * @param userDetails the user details
	 * @return void
	 * @throws MetricExecutionException the metric execution exception
	 */
	@Override
	public void processCalculativeMetrics(SalesPosition salesPosition,
			MetricTriggerType mtrTrgType, UserDetails userDetails) throws MetricExecutionException {
		try {
			boolean isInpValid = checkInputs(salesPosition,mtrTrgType);
			if(isInpValid){
				boolean isSPActive =  salPosDAOServ.isSalesPosActive(salesPosition.getId(), userDetails.getTenantId());
				if(isSPActive){
					String messageId = String.format("%d:%d:%d:%d",userDetails.getUserId(),mtrTrgType.getId(),salesPosition.getHierarchy().getId(),System.nanoTime());
					Map<Integer,String> mtrDataMap = metricDAOService.getMtrData(salesPosition.getAlignmment().getId(), 
							salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId(), salesPosition.getAlignmment().getSalesTeam().getId(),
							salesPosition.getHierarchy().getId(), salesPosition.getId(), mtrTrgType.getId(), userDetails.getTenantId());
					Map<String,Integer> featureTypeMap = metricDAOService.getFeatureAndType(mtrTrgType.getId(), userDetails.getTenantId());

					if(mtrDataMap != null && !mtrDataMap.isEmpty()){
						List<QueueMessage> outMsgs = buildQueueMessages(salesPosition.getId(), mtrDataMap, featureTypeMap, messageId, userDetails.getTenantCode(), userDetails.getTenantId());

						if(!outMsgs.isEmpty()){
							sendMessages(outMsgs, messageId, userDetails.getTenantId());
						}

					}
				}else{
					LOGGER.warn("Sales Pos Not Active");
				}
			}else{
				LOGGER.error("Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id/Trigger Type Id");
				throw new MetricExecutionException(new Object[]{"Missing Alignment Id / Buss unit id / Sales team id /Sales Pos Id/Sales Hier Id"});
			}
		}catch (JMSException e) {
			LOGGER.error(" JMSException in processCalculativeMetrics "+e.getMessage());	
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId()};
			throw new MetricExecutionException(MetricExecutionException.MetricExecutionExceptionCode.MTREXEC_SER_EX_CD_001,
					"Exception in processCalculativeMetric",args,e);
		}catch (InterruptedException e) {
			LOGGER.error(" InterruptedException in processCalculativeMetrics "+e.getMessage());	
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId()};
			throw new MetricExecutionException(MetricExecutionException.MetricExecutionExceptionCode.MTREXEC_SER_EX_CD_001,
					"Exception in processCalculativeMetric",args,e);
		}catch (RuntimeException e) {
			LOGGER.error(" RuntimeException in processCalculativeMetrics "+e.getMessage());	
			Object[] args = new Object[]{salesPosition.getId(),salesPosition.getHierarchy().getId()};
			throw new MetricExecutionException(MetricExecutionException.MetricExecutionExceptionCode.MTREXEC_SER_EX_CD_001,
					"Exception in processCalculativeMetric",args,e);
		}
	}

	/**
	 * @method buildQueueMessages - builds the list of QueueMessage
	 * @param spId the sales position id
	 * @param mtrMap the metric map
	 * @param map the map
	 * @param messageId the message id
	 * @param tenantCd the tenant code
	 * @param tenantId the tenant Id
	 * @return List<QueueMessage>
	 */
	private List<QueueMessage> buildQueueMessages(Long spId,Map<Integer,String> mtrMap, Map<String,Integer> map,String messageId, String tenantCd, 
			Short tenantId){
		List<QueueMessage> outMsgs = new ArrayList<QueueMessage>();
		for(Map.Entry<Integer,String> mtr :mtrMap.entrySet()){
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("spId", spId);
			data.put("mtrId", mtr.getKey());
			data.put("featureId", map.get("featureId"));
			data.put("typeId", map.get("typeId"));
			data.put("storeProc", mtr.getValue());
			data.put("tenantId",tenantId);
			data.put("subscriberId", messageId);
			data.put("tenantKey", tenantCd);

			QueueMessage message = new QueueMessage();
			message.setMap(data);
			outMsgs.add(message);
		}
		return outMsgs;
	}

	/**
	 * @method sendMessages - sends the QueueMessages
	 * @param outMsgs - the list of Queue Messages
	 * @param messageId - the unique message id
	 * @param tenantId - the tenant Id
	 * @return void
	 * @throws JMSException - if we have violation or any issue.
	 * @throws InterruptedException - if we have violation or any issue.
	 */
	private void sendMessages(List<QueueMessage> outMsgs,String messageId,Short tenantId) throws JMSException, InterruptedException{
		List<QueueMessage> resMsgs = new ArrayList<QueueMessage>();
		LOGGER.info("Before listener start");
		CountDownLatch cdLatch = new CountDownLatch(outMsgs.size());
		ResultsMessageListener rMsgLstnr = new ResultsMessageListener(messageId, cdLatch, resMsgs,tenantId);

		//NOTE NOW CREATE SPRING LISTENER AND PASS ALL
		DefaultMessageListenerContainer resLisContr = new DefaultMessageListenerContainer();
		resLisContr.setConnectionFactory(connectionFactory);
		resLisContr.setDestinationName(ACTIVEMQ_METRIC_RESULT_TOPIC);
		//NOTE WE ARE USING TOPIC SO ENABLE PUBLISH AND SUBSCRIBE
		resLisContr.setPubSubDomain(true);	
		resLisContr.setMessageListener(rMsgLstnr);
		resLisContr.initialize();
		resLisContr.start();

		Long strtTime = System.currentTimeMillis();
		LOGGER.info("== Started Sending Messages to Queue ===" +strtTime);
		LOGGER.info("== Sending Messages to Queue ===" + outMsgs);
		for(QueueMessage outMsg: outMsgs){
			metricsProducer.sendMessage(outMsg);
		}
		LOGGER.info("After Sending Message === " + (System.currentTimeMillis() - strtTime)/1000);
		LOGGER.info("== Completed Sending Messages to Queue ===");

		//NOTE STOP THE MAIN THREAD TO COLLECT ALL MESSAGES WITH IN TIMEOUT VALUE
		LOGGER.warn(" ActiveMQ WAITING TIME Value Is "+waitingTime);
		cdLatch.await(Long.valueOf(waitingTime), TimeUnit.SECONDS);
		//NOTE STOP THE LISTENER AND THEN CLOSE IT
		resLisContr.stop();
		resLisContr.shutdown();
		resLisContr.destroy();
		LOGGER.info("After listener End..");
		LOGGER.info(" ==== Total time to send and receive messages ==== " + (System.currentTimeMillis()-strtTime)/1000);
		if(outMsgs.size() > resMsgs.size()){
			LOGGER.warn("Metrics Execution incomplete ");
			LOGGER.warn(" outMsgs are ::"+outMsgs.toString());
			LOGGER.warn(" resMsgs are ::"+resMsgs.toString());
		}
	}

	/**
	 * @method checkInputs - to check input data
	 * @param salesPosition - the sales position
	 * @param metricTriggerType - the metric trigger type
	 * @return boolean true if inputs are valid
	 * @throws MetricExecutionException - if we have violation or any issue.
	 */
	private boolean checkInputs(SalesPosition salesPosition,MetricTriggerType metricTriggerType) throws MetricExecutionException{
		boolean result = false;
		boolean isInpValid = true;
		if(salesPosition==null){
			isInpValid = false;
		}else{

			if(salesPosition.getAlignmment() == null){
				isInpValid = false;
			}else{
				Long alignmentId = salesPosition.getAlignmment().getId();
				Long salesPosId = salesPosition.getId();

				if(alignmentId==null){
					isInpValid = false;
				}else if(salesPosId==null){
					isInpValid = false;
				}

				if(salesPosition.getAlignmment().getSalesTeam() == null ){
					isInpValid = false;
				}else{
					Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
					if(salesTeamId==null){
						isInpValid = false;
					}

					if(salesPosition.getAlignmment().getSalesTeam().getBusinessUnit() == null){
						isInpValid = false;
					}else{
						Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
						if(bussUnitId==null){
							isInpValid = false;
						}
					}
				}
			}

			if(salesPosition.getHierarchy() == null){
				isInpValid = false;
			}else{
				Long salesHierId = salesPosition.getHierarchy().getId();
				if(salesHierId==null){
					isInpValid = false;
				}
			}
		}

		boolean isTrgValid= checkTriggerTypes(metricTriggerType,SP);
		if(isTrgValid && isInpValid){
			result = true;
		}
		return result;
	}

	/**
	 * @method checkTriggerTypes - check if trigger types are correct or not
	 * @param metricTriggerType - the metric trigger type
	 * @param mod - the module name
	 * @return boolean true if the inputs are valid
	 * @throws MetricExecutionException - if we have violation or any issue
	 */
	private boolean checkTriggerTypes(MetricTriggerType metricTriggerType,String mod) throws MetricExecutionException{
		boolean value = false;
		if(mod.equalsIgnoreCase(SP) && (metricTriggerType.equals(MetricTriggerType.ASSIGN_CUSTOMER) || 
				metricTriggerType.equals(MetricTriggerType.UNASSIGN_CUSTOMER) || (metricTriggerType.equals(MetricTriggerType.EDIT_CUSTOMER)))){
			value = true;
		}
		return value;
	}
}