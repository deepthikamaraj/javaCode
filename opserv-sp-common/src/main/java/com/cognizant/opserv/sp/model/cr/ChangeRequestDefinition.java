package com.cognizant.opserv.sp.model.cr;

import com.cognizant.opserv.sp.common.ChangeRequestApproverType;
import com.cognizant.opserv.sp.common.ChangeRequestAutoActionType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BaseModel;
/**
 * ****************************************************************************.
 *
 * @class ChangeRequestDefinition
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 11/05/2016
 * ***************************************************************************
 */
public class ChangeRequestDefinition extends BaseModel
{
	private Alignment alignment;
	
	private ChangeRequestTriggerType trigger;
	
	private ChangeRequestApproverType primaryApprover;
	
	private ChangeRequestApproverType secondaryApprover;
	
	private ChangeRequestAutoActionType autoAction;
	
	private int taskDuration;
	
	private int reminderDuration;

	/**
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	/**
	 * @return the trigger
	 */
	public ChangeRequestTriggerType getTrigger() {
		return trigger;
	}

	/**
	 * @param trigger the trigger to set
	 */
	public void setTrigger(ChangeRequestTriggerType trigger) {
		this.trigger = trigger;
	}

	/**
	 * @return the primaryApprover
	 */
	public ChangeRequestApproverType getPrimaryApprover() {
		return primaryApprover;
	}

	/**
	 * @param primaryApprover the primaryApprover to set
	 */
	public void setPrimaryApprover(ChangeRequestApproverType primaryApprover) {
		this.primaryApprover = primaryApprover;
	}

	/**
	 * @return the secondaryApprover
	 */
	public ChangeRequestApproverType getSecondaryApprover() {
		return secondaryApprover;
	}

	/**
	 * @param secondaryApprover the secondaryApprover to set
	 */
	public void setSecondaryApprover(ChangeRequestApproverType secondaryApprover) {
		this.secondaryApprover = secondaryApprover;
	}

	/**
	 * @return the autoAction
	 */
	public ChangeRequestAutoActionType getAutoAction() {
		return autoAction;
	}

	/**
	 * @param autoAction the autoAction to set
	 */
	public void setAutoAction(ChangeRequestAutoActionType autoAction) {
		this.autoAction = autoAction;
	}

	/**
	 * @return the taskDuration
	 */
	public Integer getTaskDuration() {
		return taskDuration;
	}

	/**
	 * @param taskDuration the taskDuration to set
	 */
	public void setTaskDuration(Integer taskDuration) {
		this.taskDuration = taskDuration;
	}

	/**
	 * @return the reminderDuration
	 */
	public Integer getReminderDuration() {
		return reminderDuration;
	}

	/**
	 * @param reminderDuration the reminderDuration to set
	 */
	public void setReminderDuration(Integer reminderDuration) {
		this.reminderDuration = reminderDuration;
	}
		
}
