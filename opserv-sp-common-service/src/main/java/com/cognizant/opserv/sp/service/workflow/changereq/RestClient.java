package com.cognizant.opserv.sp.service.workflow.changereq;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqAppr;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
public class RestClient {

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(RestClient.class);
	
	@Value("${activiti.create.process.instance}") 
	private  String createProcessInstanceURL;
	 
	@Value("${activiti.get.tasks}") 
	private  String getTasksURL;
	 
	@Value("${activiti.process.task}") 
	private  String processTaskURL;

	public static HttpHeaders createHeaders(final String username, final String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				final String auth = username + ":" + password;
				final byte[] encodeBase64 = Base64.encodeBase64(auth.getBytes(Charset.forName(CommonConstants.UTF8)));
				final String authHeader = CommonConstants.BASIC + new String(encodeBase64);
				set(CommonConstants.AUTHORIZATION, authHeader);
			}
		};
	}


	
	public static List<String> getTaskListFromWorkFlow(SalesPosition salesPosition, UserDetails userDetails,String uri) {
		List<String> crIdList = new ArrayList<String>();
		TaskRequestInfo taskRequestInfo = new TaskRequestInfo();
		taskRequestInfo.setAssignee(salesPosition.getId().toString());
		taskRequestInfo.setIncludeProcessVariables(true);
		taskRequestInfo.setIncludeTaskLocalVariables(true);
		taskRequestInfo.setTenantId(userDetails.getTenantId().toString());
		taskRequestInfo.setStart(0);
		taskRequestInfo.setSize(10);
		taskRequestInfo.setSort(CommonConstants.CREATE_TIME);
		taskRequestInfo.setOrder(CommonConstants.DESC);
		/*List<ProcessInstanceVariable> processInstanceVariables = new ArrayList<>();
		ProcessInstanceVariable processInstanceVariable = new ProcessInstanceVariable();
		processInstanceVariables.add(processInstanceVariable);
		taskRequestInfo.setProcessInstanceVariables(processInstanceVariables);*/
		LOGGER.info(uri);
		final RestTemplate template = new RestTemplate();
		ResponseEntity<String> exchange2 = null;
		final HttpHeaders headers = createHeaders(CommonConstants.KERMIT, CommonConstants.KERMIT);
		String json = JSONUtil.toJSON(taskRequestInfo);
		LOGGER.info(json);
		headers.setAccept(Collections.singletonList(new MediaType("application", "json")));
		final HttpEntity<TaskRequestInfo> entity = new HttpEntity<>(taskRequestInfo, headers);
		exchange2 = template.exchange(uri, HttpMethod.POST, entity, String.class);
		LOGGER.info("RESPONSE BODY" + exchange2.getStatusCode());
		LOGGER.info("======== createProcessInstanceDAO" + exchange2.getBody());
		TaskResponseInfoList fromJson = JSONUtil.toObject(exchange2.getBody(), TaskResponseInfoList.class);
		LOGGER.info("TaskResponseInfoList Size:" + fromJson.getData());
		final List<TaskResponseInfo> data = fromJson.getData();
		for (TaskResponseInfo taskResponseVO : data) {
			
			for(ProcessVariable processVariable :taskResponseVO.getVariables() )
			{
				
				if(processVariable.getName().equals(ProcessVariableEnum.CHANGE_REQUEST_ID.toString()))
				{
					crIdList.add(processVariable.getValue().toString());
				}
			}
			
			LOGGER.info("Assignee:" + taskResponseVO.getAssignee() + "ID " + taskResponseVO.getId() + "Variables " + taskResponseVO.getVariables());
		}
		return crIdList;
	}

	public static void triggerWorkFlow(TChngReq changeRequest, UserDetails userDetails,String uri,Set<TChngReqAppr> changeRequestApprovals, ChangeRequest chngReq) {
		Map<ProcessVariableEnum, Object> processVariableMap = new HashMap<>();
		createProcessVariableMap(processVariableMap, chngReq, userDetails, changeRequestApprovals);
		List<ProcessVariable> processVariableList = new ArrayList<>();

		Set<ProcessVariableEnum> processVarKeys = processVariableMap.keySet();
		for (ProcessVariableEnum quotaProcessVariableEnum : processVarKeys) {
			ProcessVariable processVariable = new ProcessVariable();
			processVariable.setName(quotaProcessVariableEnum.toString());
			processVariable.setValue(processVariableMap.get(quotaProcessVariableEnum));
			processVariableList.add(processVariable);
		}

		ProcessRequestInfo processRequestInfo = new ProcessRequestInfo();
		processRequestInfo.setProcessDefinitionKey(CommonConstants.CHANGE_REQUEST);
		processRequestInfo.setTenantId(String.valueOf(userDetails.getTenantId()));
		processRequestInfo.setVariables(processVariableList);
		final RestTemplate template = new RestTemplate();
		ResponseEntity<String> exchange2 = null;
		final HttpHeaders headers = createHeaders(CommonConstants.KERMIT, CommonConstants.KERMIT);
		String json = JSONUtil.toJSON(processRequestInfo);
		LOGGER.info(json);
		headers.setAccept(Collections.singletonList(new MediaType("application", "json")));
		final HttpEntity<ProcessRequestInfo> entity = new HttpEntity<>(processRequestInfo, headers);
		exchange2 = template.exchange(uri, HttpMethod.POST, entity, String.class);
		LOGGER.info("RESPONSE BODY" + exchange2.getStatusCode());
		LOGGER.info("======== createProcessInstanceDAO" + exchange2.getBody());
	}

	
	public static void createProcessVariableMap(Map<ProcessVariableEnum, Object> processVariableMap, ChangeRequest changeRequest, UserDetails userDetails,
			Set<TChngReqAppr> changeRequestApprovals) {
		LOGGER.info(ProcessVariableEnum.CHANGE_REQUEST_ID + " : " + changeRequest.getId());
		processVariableMap.put(ProcessVariableEnum.CHANGE_REQUEST_ID, changeRequest.getId().toString());
		LOGGER.info(ProcessVariableEnum.RAISED_SALES_POSITION_ID + " : " + changeRequest.getRaisedSalesPosition().getId());
		processVariableMap.put(ProcessVariableEnum.RAISED_SALES_POSITION_ID, changeRequest.getRaisedSalesPosition().getId().toString());
		LOGGER.info(ProcessVariableEnum.RAISED_SALES_HIEARCHY_ID + " : " + changeRequest.getRaisedSalesPosition().getHierarchy().getId());
		processVariableMap.put(ProcessVariableEnum.RAISED_SALES_HIEARCHY_ID, changeRequest.getRaisedSalesPosition().getHierarchy().getId().toString());
		LOGGER.info(ProcessVariableEnum.REQUESTING_SALES_POSITION_ID + " : " + changeRequest.getRequestedSalesPosition().getId());
		processVariableMap.put(ProcessVariableEnum.REQUESTING_SALES_POSITION_ID, changeRequest.getRequestedSalesPosition().getId().toString());
		LOGGER.info(ProcessVariableEnum.REQUESTING_SALES_HIEARCHY_ID + " : " + changeRequest.getRequestedSalesPosition().getHierarchy().getId());
		processVariableMap.put(ProcessVariableEnum.REQUESTING_SALES_HIEARCHY_ID, changeRequest.getRequestedSalesPosition().getHierarchy().getId().toString());
		LOGGER.info(ProcessVariableEnum.ALIGNMENT_ID + " : " + changeRequest.getRequestedSalesPosition().getAlignmment().getId());
		processVariableMap.put(ProcessVariableEnum.ALIGNMENT_ID, changeRequest.getRequestedSalesPosition().getAlignmment().getId().toString());
		LOGGER.info(ProcessVariableEnum.BUSINESS_UNIT_ID + " : " + changeRequest.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId());
		processVariableMap.put(ProcessVariableEnum.BUSINESS_UNIT_ID, changeRequest.getRequestedSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId().toString());
		LOGGER.info(ProcessVariableEnum.SALES_TEAM_ID + " : " + changeRequest.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId());
		processVariableMap.put(ProcessVariableEnum.SALES_TEAM_ID, changeRequest.getRequestedSalesPosition().getAlignmment().getSalesTeam().getId().toString());
		LOGGER.info(ProcessVariableEnum.TRIGGER_TYPE + " : " + changeRequest.getCrDefinition().getTrigger().getId());
		processVariableMap.put(ProcessVariableEnum.TRIGGER_TYPE, changeRequest.getCrDefinition().getTrigger().getId().toString());
		processVariableMap.put(ProcessVariableEnum.TRIGGER_TYPE_DESC, CommonConstants.EMPTY);
		processVariableMap.put(ProcessVariableEnum.CR_STATUS, CommonConstants.EMPTY);
		Date date = new Date();
		DateTime dtOrg = new DateTime(date);
		DateTime remiderDate = dtOrg.plusDays(1);
		DateFormat iso8601Format = new SimpleDateFormat(CommonConstants.DATE_PATTERN, Locale.getDefault());
		processVariableMap.put(ProcessVariableEnum.EXPIRING_ON, iso8601Format.format(remiderDate.toDate()));
		DateTime dtOrg1 = new DateTime(date);
		DateTime expireDate = dtOrg1.plusDays(changeRequest.getCrDefinition().getReminderDuration());
		processVariableMap.put(ProcessVariableEnum.REMINDER_ON, iso8601Format.format(expireDate.toDate()));
		processVariableMap.put(ProcessVariableEnum.TENANT_ID, userDetails.getTenantId().toString());
		for (TChngReqAppr chngReqAppr : changeRequestApprovals) {
			if (changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_CUSTOMER)
					|| changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PUSH_ZIP)) {
				if (chngReqAppr.getTargetApprFlag().equals('N')) {
					processVariableMap.put(ProcessVariableEnum.SOURCE, chngReqAppr.getApprSalesPosId().toString());
					processVariableMap.put(ProcessVariableEnum.SRC_APPR_HIER, chngReqAppr.getApprSalesHierId().toString());
				}
				if (chngReqAppr.getTargetApprFlag().equals('Y')) {
					processVariableMap.put(ProcessVariableEnum.TARGET, chngReqAppr.getApprSalesPosId().toString());
					processVariableMap.put(ProcessVariableEnum.TRGT_APPR_HIER, chngReqAppr.getApprSalesHierId().toString());
				}
				processVariableMap.put(ProcessVariableEnum.EDIT_FLAG, Boolean.toString(CommonConstants.FALSE));
			}
			if (changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.PULL_CUSTOMER)) {
				if (chngReqAppr.getTargetApprFlag().equals('Y')) {
					processVariableMap.put(ProcessVariableEnum.SOURCE, chngReqAppr.getApprSalesPosId().toString());
					processVariableMap.put(ProcessVariableEnum.SRC_APPR_HIER, chngReqAppr.getApprSalesHierId().toString());
				}
				if (chngReqAppr.getTargetApprFlag().equals('N')) {
					processVariableMap.put(ProcessVariableEnum.TARGET, chngReqAppr.getApprSalesPosId().toString());
					processVariableMap.put(ProcessVariableEnum.TRGT_APPR_HIER, chngReqAppr.getApprSalesHierId().toString());
				}
				processVariableMap.put(ProcessVariableEnum.EDIT_FLAG, Boolean.toString(CommonConstants.FALSE));
			}
			if (changeRequest.getCrDefinition().getTrigger().equals(ChangeRequestTriggerType.EDIT_CUSTOMER)) {
				if (chngReqAppr.getTargetApprFlag().equals('Y')) {
					processVariableMap.put(ProcessVariableEnum.SOURCE, chngReqAppr.getApprSalesPosId().toString());
					processVariableMap.put(ProcessVariableEnum.SRC_APPR_HIER, chngReqAppr.getApprSalesHierId().toString());
					processVariableMap.put(ProcessVariableEnum.TARGET, CommonConstants.EMPTY_STR);
					processVariableMap.put(ProcessVariableEnum.EDIT_FLAG, Boolean.toString(CommonConstants.TRUE));
				}
			}
		}
		processVariableMap.put(ProcessVariableEnum.APPROVER_DECISION, CommonConstants.EMPTY_STR);
		processVariableMap.put(ProcessVariableEnum.ACTION, CommonConstants.EMPTY_STR);
		processVariableMap.put(ProcessVariableEnum.APPROVER_SP_ID, CommonConstants.EMPTY_STR);
		processVariableMap.put(ProcessVariableEnum.APPROVER_HR_ID, CommonConstants.EMPTY_STR);
		processVariableMap.put(ProcessVariableEnum.USER_ID, userDetails.getUserId().toString());
		processVariableMap.put(ProcessVariableEnum.TENANT_ID, userDetails.getTenantId().toString());
		processVariableMap.put(ProcessVariableEnum.TENANT_CODE, userDetails.getTenantCode());
		LOGGER.info(ProcessVariableEnum.AUTO_ACTION + " : " + changeRequest.getCrDefinition().getAutoAction().getAutoAction());
		processVariableMap.put(ProcessVariableEnum.AUTO_ACTION, changeRequest.getCrDefinition().getAutoAction().getAutoAction());
		processVariableMap.put(ProcessVariableEnum.COMMENTS,CommonConstants.EMPTY_STR);

		// Converting time to IST for Activiti
		Calendar cal = Calendar.getInstance();
		Date currentDate = new Date();
		cal.setTime(currentDate);
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		Date srcReminder = cal.getTime();
		Date trgtReminder = cal.getTime();
		cal.add(Calendar.DATE, changeRequest.getCrDefinition().getTaskDuration());
		Date srcExpiry = cal.getTime();
		cal.add(Calendar.DATE, changeRequest.getCrDefinition().getTaskDuration());
		Date trgtExpiry = cal.getTime();
		
		LOGGER.info(ProcessVariableEnum.SOURCE_EXPIRY + " : " + iso8601Format.format(srcExpiry));
		processVariableMap.put(ProcessVariableEnum.SOURCE_EXPIRY,iso8601Format.format(srcExpiry));
		LOGGER.info(ProcessVariableEnum.TARGET_EXPIRY + " : " + iso8601Format.format(trgtExpiry));
		processVariableMap.put(ProcessVariableEnum.TARGET_EXPIRY,iso8601Format.format(trgtExpiry));
		LOGGER.info(ProcessVariableEnum.SOURCE_REMINDER_ON + " : " + iso8601Format.format(srcReminder));
		processVariableMap.put(ProcessVariableEnum.SOURCE_REMINDER_ON,iso8601Format.format(srcReminder));
		LOGGER.info(ProcessVariableEnum.TARGET_REMINDER_ON + " : " + iso8601Format.format(trgtReminder));
		processVariableMap.put(ProcessVariableEnum.TARGET_REMINDER_ON,iso8601Format.format(trgtReminder));
		
		processVariableMap.put(ProcessVariableEnum.REM_DURATION,changeRequest.getCrDefinition().getReminderDuration().toString());
		processVariableMap.put(ProcessVariableEnum.EXP_DURATION,changeRequest.getCrDefinition().getTaskDuration().toString());
		
		
	}
	
	
}
