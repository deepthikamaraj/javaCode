/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/

package com.cognizant.opserv.sp.service.workflow.changereq;
// TODO: Auto-generated Javadoc
/**
 * The Enum ProcessVariableEnum.
 */
public enum ProcessVariableEnum {
	
	SOURCE("source"), TARGET("target"),
	/** The change request id. */
	CHANGE_REQUEST_ID("crId"), /** The change request num. */
	CHANGE_REQUEST_NUM("crNum"), /** The updated by. */
	UPDATED_BY("updatedBy"), /** The update date. */
	UPDATE_DATE("updateDate"),
	/** The created by. */
	CREATED_BY("createdBy"),
	/** The created date. */
	CREATED_DATE("createdDate"),
	/** The trigger type. */
	TRIGGER_TYPE("triggerType"),
	/** The trigger type desc. */
	TRIGGER_TYPE_DESC("triggerTypeDesc"),
	/** The raised sales position id. */
	RAISED_SALES_POSITION_ID("raiseSalesPosId"),
	/** The raised sales hiearchy id. */
	RAISED_SALES_HIEARCHY_ID("raiseSalesHieId"),
	/** The requesting sales position id. */
	REQUESTING_SALES_POSITION_ID("reqSalesPosId"),
	/** The requesting sales hiearchy id. */
	REQUESTING_SALES_HIEARCHY_ID("reqSalesHieId"),
	/** The alignment id. */
	ALIGNMENT_ID("alignmentId"),
	/** The business unit id. */
	BUSINESS_UNIT_ID("bussUnitId"),
	/** The sales team id. */
	SALES_TEAM_ID("salesTeamId"),
	/** The cr status. */
	CR_STATUS("status"),
	/** The approver decision. */
	APPROVER_DECISION("decision"),
	/** The approver decision approve. */
	APPROVER_DECISION_APPROVE("approve"),
	/** The approver decision reject. */
	APPROVER_DECISION_REJECT("reject"),
	/** The approver decision delegate. */
	APPROVER_DECISION_DELEGATE("delegate"),
	/** The decision submitted. */
	DECISION_SUBMITTED("submitted"),
	/** The decision assigned. */
	DECISION_ASSIGNED("assigned"),
	/** The decision notify. */
	DECISION_NOTIFY("notify"),
	/** The source approver list. */
	SOURCE_APPROVER_LIST("sourceApproverList"),
	/** The target approver list. */
	TARGET_APPROVER_LIST("targetApproverList"),
	/** The approver list. */
	APPROVER_LIST("approverList"),
	/** The cr workflow type. */
	CR_WORKFLOW_TYPE("workFlowType"),
	/** The secondary configure. */
	SECONDARY_CONFIGURE("secondaryConfig"),
	/** The execution place. */
	EXECUTION_PLACE("executionPlace"),
	/** The target sales pos. */
	TARGET_SALES_POS("target"),
	/** The source sales pos. */
	SOURCE_SALES_POS("source"),
	/** The approver. */
	APPROVER("approver"),
	/** The approver auto completion action. */
	APPROVER_AUTO_COMPLETION_ACTION("apprvrAutoCompleteAction"),
	/** The feature name. */
	FEATURE_NAME("featureName"),
	/** The sub proc decision. */
	SUB_PROC_DECISION("subProcDecision"),
	/** The secondary configuration. */
	SECONDARY_CONFIGURATION("secondaryConfiguration"),
	/** The trigger id. */
	TRIGGER_ID("triggerId"),
	/** The sale pos code. */
	SALE_POS_CODE("salePosCode"),
	/** The raise sale pos code. */
	RAISE_SALE_POS_CODE("raiseSalePosCode"),
	/** The first name. */
	FIRST_NAME("fName"),
	/** The middle name. */
	MIDDLE_NAME("mName"),
	/** The last name. */
	LAST_NAME("lName"),
	/** The submitted dt tm. */
	SUBMITTED_DT_TM("submittedDTTM"),
	/** The expiring on. */
	EXPIRING_ON("expiringOn"),
	/** The cr elapsed time. */
	CR_ELAPSED_TIME("crElapsedTime"),
	/** The tenant id. */
	TENANT_ID("tenantId"),
	/** The auto action comments. */
	AUTO_ACTION_COMMENTS("Auto Action"),
	/** The nation apprvr auto action. */
	NATION_APPRVR_AUTO_ACTION("nationApprvrAutoAction"),
	/** The task expiring on. */
	TASK_EXPIRING_ON("taskExpOn"),
	/** The delegate exp date. */
	DELEGATE_EXP_DATE("delegateExpDt"),
	/** The reminder flag. */
	REMINDER_FLAG("reminderFlag"),
	/** The sp end dt. */
	SP_END_DT("salesPosEndDt"),
	/** The approver sp code. */
	APPROVER_SP_CODE("approverSPCode"),
	/** The approver name. */
	APPROVER_NAME("approverName"),
	/** The delegated sp code. */
	DELEGATED_SP_CODE("delegatedSPCode"),
	/** The requestor cr submit mail template. */
	REQUESTOR_CR_SUBMIT_MAIL_TEMPLATE("reqCRSubmitMailTemplt"),
	/** The approver cr assign mail template. */
	APPROVER_CR_ASSIGN_MAIL_TEMPLATE("apprvrCRAssignMailTemplt"),
	/** The requestor cr approve action mail template. */
	REQUESTOR_CR_APPROVE_ACTION_MAIL_TEMPLATE("reqCRApproveActionMailTemplt"),
	/** The requestor cr reject action mail template. */
	REQUESTOR_CR_REJECT_ACTION_MAIL_TEMPLATE("reqCRRejectActionMailTemplt"),
	/** The approver cr approve action mail template. */
	APPROVER_CR_APPROVE_ACTION_MAIL_TEMPLATE("apprvrCRApproveActionMailTemplt"),
	/** The approver cr reject action mail template. */
	APPROVER_CR_REJECT_ACTION_MAIL_TEMPLATE("apprvrCRRejectActionMailTemplt"),
	/** The requestor cr delegate action mail template. */
	REQUESTOR_CR_DELEGATE_ACTION_MAIL_TEMPLATE("reqCRDelegateActionMailTemplt"),
	/** The approver cr delegate action mail template. */
	APPROVER_CR_DELEGATE_ACTION_MAIL_TEMPLATE("apprvrCRDelegateActionMailTemplt"),
	/** The approver cr reminder action mail template. */
	APPROVER_CR_REMINDER_ACTION_MAIL_TEMPLATE("apprvrCRReminderActionMailTemplt"),
	/** The users approve flag. */
	USERS_APPROVE_FLAG("usersApproveFlag"),
	/** The users reject flag. */
	USERS_REJECT_FLAG("usersRejectFlag"),
	/** The all approver cr approve action mail template. */
	ALL_APPROVER_CR_APPROVE_ACTION_MAIL_TEMPLATE("allApprvrCRApproveActionMailTemplt"),
	/** The all approver cr reject action mail template. */
	ALL_APPROVER_CR_REJECT_ACTION_MAIL_TEMPLATE("allApprvrCRRejectActionMailTemplt"),
	/** The email note type flag. */
	EMAIL_NOTE_TYPE_FLAG("emailNoteTypeFlag"),
	/** The email dash note type flag. */
	EMAIL_DASH_NOTE_TYPE_FLAG("emailDashNoteTypeFlag"), APPROVER_SP_ID("salesPositionId"), APPROVER_HR_ID("salesHierharcyId"), USER_ID("userId"), TENANT_CODE("tenantCode"),ACTION("action"),REMINDER_ON("reminderOn"),AUTO_ACTION("autoAction"),EDIT_FLAG("editFlag"),COMMENTS("comments"),SOURCE_EXPIRY("sourceExpiringOn"),
	TARGET_EXPIRY("targetExpiringOn"),
	SOURCE_REMINDER_ON("sourceReminderOn"),
	TARGET_REMINDER_ON("targetReminderOn"), REM_DURATION("reminderDuration"), EXP_DURATION("expDuration"), SRC_APPR_HIER("srcApprHierId"), TRGT_APPR_HIER("trgtApprHierId");

	/** The process variable name. */
	String processVariableName;

	/**
	 * The Constructor.
	 *
	 * @param processVariableName the process variable name
	 */
	private ProcessVariableEnum(String processVariableName) {
		this.processVariableName = processVariableName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.processVariableName;
	}

	/**
	 * Gets the enum from string.
	 *
	 * @param processVariableName the process variable name
	 * @return the enum from string
	 */
	public static ProcessVariableEnum getEnumFromString(
			String processVariableName) {

		if (processVariableName != null) {
			for (ProcessVariableEnum processVariableEnum : ProcessVariableEnum
					.values()) {
				if (processVariableName
						.equalsIgnoreCase(processVariableEnum.processVariableName)) {
					return processVariableEnum;

				}

			}
			return ProcessVariableEnum.valueOf(processVariableName);

		}

		return null;
	}

}
