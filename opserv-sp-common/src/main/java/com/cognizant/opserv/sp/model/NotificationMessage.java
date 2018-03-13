package com.cognizant.opserv.sp.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.common.NotificationType;

public class NotificationMessage implements Serializable   {
	

		private static final long serialVersionUID = -2067695530408969615L;
		private Integer clientCode;
		private String msgTemplate;
		private NotificationType notifyType;
		private List<Integer> recipients;
		private List<Integer> bccs;
		private List<Integer> ccs;

		private List<String> attachFiles;
		private String subject;
		private Map<String,String> params;
		private String content;
		
		public Integer getClientCode() {
			return clientCode;
		}

		public List<Integer> getRecipients() {
			return recipients;
		}
		public void setRecipients(List<Integer> recipients) {
			this.recipients = recipients;
		}
		public void setClientCode(Integer clientCode) {
			this.clientCode = clientCode;
		}
		public Map<String, String> getParams() {
			return params;
		}

		public void setParams(Map<String, String> params) {
			this.params = params;
		}

		public List<String> getAttachFiles() {
			return attachFiles;
		}

		public void setAttachFiles(List<String> attachFiles) {
			this.attachFiles = attachFiles;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public List<Integer> getBccs() {
			return bccs;
		}

		public void setBccs(List<Integer> bccs) {
			this.bccs = bccs;
		}

		public List<Integer> getCcs() {
			return ccs;
		}

		public void setCcs(List<Integer> ccs) {
			this.ccs = ccs;
		}

		

		/**
		 * @return the msgTemplate
		 */
		public String getMsgTemplate() {
			return msgTemplate;
		}

		/**
		 * @param msgTemplate the msgTemplate to set
		 */
		public void setMsgTemplate(String msgTemplate) {
			this.msgTemplate = msgTemplate;
		}

		public NotificationType getNotifyType() {
			return notifyType;
		}

		public void setNotifyType(NotificationType notifyType) {
			this.notifyType = notifyType;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		

	


}
