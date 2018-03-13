package com.cognizant.opserv.sp.communication.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.AckStatusType;
import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.common.communication.CommunicationType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAttr;
import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommDoc;
import com.cognizant.opserv.sp.core.entity.TCommDocId;
import com.cognizant.opserv.sp.core.entity.TCommNoteType;
import com.cognizant.opserv.sp.core.entity.TCommNoteTypeId;
import com.cognizant.opserv.sp.core.entity.TCommPublisher;
import com.cognizant.opserv.sp.core.entity.TCommPublisherId;
import com.cognizant.opserv.sp.core.entity.TCommReceipient;
import com.cognizant.opserv.sp.core.entity.TCommReceipientId;
import com.cognizant.opserv.sp.core.entity.TCommStatus;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipientId;
import com.cognizant.opserv.sp.core.entity.TCommType;
import com.cognizant.opserv.sp.core.entity.TPers;
import com.cognizant.opserv.sp.core.entity.TPrefAttrValue;
import com.cognizant.opserv.sp.core.entity.TPubType;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.opserv.sp.core.entity.TRecipientAttrId;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Document;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.opserv.sp.model.communication.RecipientsAttribute;
import com.cognizant.opserv.sp.model.communication.RecipientsSearchParam;

/**
 * ****************************************************************************.
 * 
 * @Interface CommunicationModelAssembler contains method to map data between
 *            model and entity
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 *        ************************************************************
 *        ***************
 */

@Component
public class CommunicationModelAssembler {

	/**
	 * Map to t comm.
	 * 
	 * @param model
	 *            the model
	 * @param userDetails
	 *            the user details
	 * @param statustype
	 *            the statustype
	 * @param commType
	 *            the comm type
	 * @return the t comm
	 */
	public TComm mapToTComm(Announcement announcement) {

		TComm tComm = new TComm();
		tComm.setCommTitle(announcement.getName());
		tComm.setCommDetail(announcement.getDetails());
		tComm.setValidityStartDt(announcement.getStartDate());
		tComm.setValidityEndDt(announcement.getEndDate());
		tComm.setAckRequired(announcement.isAckRequired() ? CommonConstants.CHAR_YES
				: CommonConstants.CHAR_NO);

		// tComm.setActivityEndDt(d);
		// tComm.setActivityStartDt(d);
		tComm.setCreatedBy(announcement.getCreatedBy());

		tComm.setCreateDt(announcement.getCreatedDate());
		tComm.setImportanceFlag(announcement.isPriority() ? CommonConstants.IMP_HEIGH
				: CommonConstants.IMP_LOW);
		tComm.setPublishedDt(announcement.getPublishedDate());
		tComm.setTenantId(announcement.getTenantId());

		tComm.setUpdateDate(announcement.getUpdatedDate());
		tComm.setUpdatedBy(announcement.getUpdatedBy());
		tComm.setDraftDt(announcement.getDraftedDate());
		if (null != announcement.getStatus()) {
			TCommStatus tCommStatus = new TCommStatus();
			tCommStatus.setCommStatusId(announcement.getStatus().getId());
			tComm.setTCommStatus(tCommStatus);
		}

		if (null != announcement.getType()) {
			TCommType tCommType = new TCommType();
			tCommType.setCommTypeId(announcement.getType().getId());
			tComm.setTCommType(tCommType);
		}
		return tComm;
	}

	/**
	 * Map entity to announcement model.
	 *
	 * @param tComm the t comm
	 * @param flag the flag
	 * @return the announcement
	 */
	public Announcement mapEntityToAnnouncementModel(TComm tComm, boolean flag) {
		
		Announcement announcement = new Announcement();
		
		announcement.setId(tComm.getCommId());
		announcement.setName(tComm.getCommTitle());
		announcement.setDetails(tComm.getCommDetail());
		announcement.setDraftedDate(tComm.getDraftDt());
		announcement.setEndDate(tComm.getValidityEndDt());
		announcement.setStartDate(tComm.getValidityStartDt());
		announcement.setPublishedDate(tComm.getPublishedDt());
		announcement.setType(CommunicationType.getCommunicationType(tComm.getTCommType().getCommTypeId()));
		announcement.setStatus(CommunicationStatusType.getCommunicationStatusType(tComm.getTCommStatus().getCommStatusId()));
		
		if (null != tComm.getTCommNoteTypess() && tComm.getTCommNoteTypess().toArray().length > 0) {
			announcement.setNotification(NotificationType.getNotificationType(((TCommNoteType)tComm.getTCommNoteTypess().toArray()[0])
					.getTCommNoteTypeId().getNoteTypeId()));
		}
		
		if (null != tComm.getAckRequired()) {
			announcement.setAckRequired(tComm.getAckRequired().equals(CommonConstants.CHAR_YES) ? true:false);
		}
		
		if (null != tComm.getImportanceFlag()) {
			announcement.setPriority(tComm.getImportanceFlag().equals(CommonConstants.IMP_HEIGH) ? true:false);
		}
		announcement.setCreatedBy(tComm.getCreatedBy());
		announcement.setCreatedDate(tComm.getCreateDt());
		announcement.setUpdatedDate(tComm.getUpdateDate());
		announcement.setUpdatedBy(tComm.getUpdatedBy());
		announcement.setTenantId(tComm.getTenantId());
		if (flag) {
			List<TCommReceipient> recipients = new ArrayList<TCommReceipient>();
			recipients.addAll(tComm.getTCommReceipientss());
			List<Recipient> recipientsList = new ArrayList<Recipient>();
			for (TCommReceipient recipient : recipients) {
				recipientsList.add((Recipient) mapEntityToRecipientModel(recipient).get(1));
			}
			List<TCommTargetRecipient> targetRecipients = new ArrayList<TCommTargetRecipient>();
			targetRecipients.addAll(tComm.getTCommTargetRecipientss());
			
			for (TCommTargetRecipient targetRecipient : targetRecipients) {
				
				for (Recipient recipient : recipientsList) {
					if ((recipient.getRecipientId()).equals(targetRecipient.getTCommTargetRecipientId().getStaffId())) {
						recipient.setTargetRecipient(true);
					}
				}
			}
			announcement.setRecipients(recipientsList);
			if (null != tComm.getTCommDocss() && tComm.getTCommDocss().toArray().length > 0) {
				Document document = new Document();
				document.setDocName(((TCommDoc) tComm.getTCommDocss().toArray()[0]).getDocName());
				document.setDocLocation(((TCommDoc) tComm.getTCommDocss().toArray()[0]).getDocLocation());
				document.setPublishedDate(((TCommDoc) tComm.getTCommDocss().toArray()[0]).getPublishedDt());
				announcement.setDocument(document);
			}
		}
		return announcement;
	}
	
	/**
	 * Map entity to recipient model.
	 *
	 * @param tCommReceipient the t comm receipient
	 * @return the list
	 */
	public List<Object> mapEntityToRecipientModel(TCommReceipient tCommReceipient) {
		
		List<Object> receipientDetails = new ArrayList<Object>();
		
		Communication communication = new Communication();
		Recipient receipient = new Recipient();
		communication.setId(tCommReceipient.getTCommReceipientId().getCommId());
		receipient.setRecipientId(tCommReceipient.getTCommReceipientId().getStaffId());
		receipient.setAckStatus(AckStatusType.getAckStatusType(tCommReceipient.getAckStatusId()));
		receipient.setReadDate(tCommReceipient.getCommReadDt());
		receipient.setAckDate(tCommReceipient.getCommAckDt());
		receipient.setRejectReason(tCommReceipient.getRejectReason());
		if (null != tCommReceipient.getFavFlag()) {
			receipient.setFavourite(tCommReceipient.getFavFlag().equals(CommonConstants.ACTIVE_Y) ? true:false);
		}
		communication.setTenantId(tCommReceipient.getTenantId());
		receipientDetails.add(communication);
		receipientDetails.add(receipient);
		return receipientDetails;
	}

	/**
	 * Map recipient model to entity update.
	 *
	 * @param receipientDetailsDB the receipient details db
	 * @param communication the communication
	 * @param recipient the recipient
	 * @return the t comm receipient
	 */
	public TCommReceipient mapRecipientModelToEntityUpdate(
			List<Object> receipientDetailsDB, Communication communication,
			Recipient recipient) {
		
		Communication communicationDB = (Communication) receipientDetailsDB.get(0);
		Recipient receipientDB = (Recipient) receipientDetailsDB.get(1);
		
		TCommReceipientId tCommReceipientIdDB = new TCommReceipientId();
		
		tCommReceipientIdDB.setCommId(communicationDB.getId());
		tCommReceipientIdDB.setStaffId(receipientDB.getRecipientId());
		
		TCommReceipient tCommReceipient = new TCommReceipient();
		
		tCommReceipient.setTCommReceipientId(tCommReceipientIdDB);
		//If entry in DB - Notified by default
		tCommReceipient.setNoteStatusId(1);
		
		if (AckStatusType.NOTAPPLICABLE.equals(receipientDB.getAckStatus())) {
			tCommReceipient.setAckStatusId(receipientDB.getAckStatus().getId());
		} else {
			tCommReceipient.setAckStatusId(recipient.getAckStatus().getId());
		}

		if (null != receipientDB.getReadDate()) {
			tCommReceipient.setCommReadDt(receipientDB.getReadDate());
		} else {
			tCommReceipient.setCommReadDt(DateUtil.getCurrentDate());
		}
		
		if (AckStatusType.ACCEPTED.equals(recipient.getAckStatus()) || 
				AckStatusType.REJECTED.equals(recipient.getAckStatus())) {
			tCommReceipient.setCommAckDt(DateUtil.getCurrentDate());
		}
		tCommReceipient.setRejectReason(recipient.getRejectReason());
		
		if (recipient.isFavourite()) {
			tCommReceipient.setFavFlag(CommonConstants.ACTIVE_Y);
		}
		tCommReceipient.setTenantId(communicationDB.getTenantId());
		
		return tCommReceipient;
	}

	/**
	 * Map to t comm note type.
	 *
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t comm note type
	 */
	public TCommNoteType mapToTCommNoteType(TComm tComm,
			Announcement announcement) {
		TCommNoteType tCommNoteType = new TCommNoteType();
		tCommNoteType.setActiveFlag(CommonConstants.ACTIVE_Y);
		if (null != announcement.getTenantId()) {
			tCommNoteType.setTenantId(announcement.getTenantId());
		}

		Integer noteTypeId = 0;

		if ((NotificationType.BOTH.getId()).equals(announcement.getNotification()
				.getId())) {
			noteTypeId = NotificationType.BOTH.getId();
		} else if ((NotificationType.EMAIL.getId()).equals(announcement
				.getNotification().getId())) {
			noteTypeId = NotificationType.EMAIL.getId();
		} else if ((NotificationType.DASHBOARD.getId()).equals(announcement
				.getNotification().getId())) {
			noteTypeId = NotificationType.DASHBOARD.getId();

		}

		TCommNoteTypeId commNoteTypeId = new TCommNoteTypeId();
		commNoteTypeId.setNoteTypeId(noteTypeId);
		if (null != tComm.getCommId()) {
			commNoteTypeId.setCommId(tComm.getCommId());
		}
		tCommNoteType.setTCommNoteTypeId(commNoteTypeId);
		if (null != tComm) {
			tCommNoteType.setTComm(tComm);
		}

		return tCommNoteType;

	}

	/**
	 * Map to t comm target recipient.
	 *
	 * @param recipients the recipients
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the list
	 */
	public List<TCommTargetRecipient> mapToTCommTargetRecipient(
			List<Recipient> recipients, TComm tComm, Announcement announcement) {

		List<TCommTargetRecipient> tCommTargetReceipientList = new ArrayList<TCommTargetRecipient>();

		for (Recipient recipient : recipients) {
			TCommTargetRecipient tCommTargetRecipient = new TCommTargetRecipient();

			TPers tPers = new TPers();

			if (null != tComm) {

				TCommTargetRecipientId tCommTargetRecipientId = new TCommTargetRecipientId();
				tCommTargetRecipientId.setStaffId(recipient.getRecipientId());
				tCommTargetRecipientId.setCommId(tComm.getCommId());
				tCommTargetRecipient
						.setTCommTargetRecipientId(tCommTargetRecipientId);
				tCommTargetRecipient.setTComm(tComm);

			}
			tCommTargetRecipient.setActiveFlag(CommonConstants.ACTIVE_Y);

			tCommTargetRecipient.setTenantId(announcement.getTenantId());

			if (null != announcement.getId()) {
				if (null != announcement.getUpdatedBy()) {
					tCommTargetRecipient.setUpdatedBy(announcement
							.getUpdatedBy());
				}
				if (null != announcement.getUpdatedDate()) {
					tCommTargetRecipient.setUpdateDt(announcement
							.getUpdatedDate());
				}

			}
			if (null != announcement.getCreatedDate()) {
				tCommTargetRecipient.setCreateDt(announcement.getCreatedDate());
			}
			if (null != announcement.getCreatedBy()) {
				tCommTargetRecipient.setCreatedBy(announcement.getCreatedBy());
			}
			tPers.setStaffId(recipient.getRecipientId());
			tCommTargetRecipient.setTPers(tPers);

			tCommTargetReceipientList.add(tCommTargetRecipient);
		}

		return tCommTargetReceipientList;
	}

	/**
	 * Map to t comm recipients.
	 *
	 * @param recipients the recipients
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the list
	 */
	public List<TCommReceipient> mapToTCommRecipients(
			List<Recipient> recipients, TComm tComm, Announcement announcement) {

		List<TCommReceipient> tCommReceipientList = new ArrayList<TCommReceipient>();
		for (Recipient recipient : recipients) {

			TCommReceipient tCommRecipient = new TCommReceipient();
			TPers tPers = new TPers();
			if (null != tComm) {

				TCommReceipientId tCommRecipientId = new TCommReceipientId();
				tCommRecipientId.setStaffId(recipient.getRecipientId());
				tCommRecipientId.setCommId(tComm.getCommId());
				tCommRecipient.setTCommReceipientId(tCommRecipientId);
				tCommRecipient.setTComm(tComm);
			}
			if (null != announcement.getTenantId()) {
				tCommRecipient.setTenantId(announcement.getTenantId());
			}
			if (recipient.isFavourite()) {
				tCommRecipient.setFavFlag(CommonConstants.ACTIVE_Y);
			} else {
				tCommRecipient.setFavFlag(CommonConstants.ACTIVE_N);
			}
			if (announcement.isAckRequired()) {
				tCommRecipient.setAckStatusId(AckStatusType.PENDING.getId());
			} else {
				tCommRecipient.setAckStatusId(AckStatusType.NOTAPPLICABLE
						.getId());
			}

			tPers.setStaffId(recipient.getRecipientId());
			tCommRecipient.setTPers(tPers);		
			tCommRecipient.setNoteStatusId(recipient.getNoteStatus().getId());
			tCommReceipientList.add(tCommRecipient);
		}

		return tCommReceipientList;
	}

	/**
	 * Map to t comm doc.
	 *
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t comm doc
	 */
	public TCommDoc mapToTCommDoc(TComm tComm, Announcement announcement) {

		TCommDoc tCommDoc = new TCommDoc();
		if (null != tComm) {

			TCommDocId tCommDocId = new TCommDocId();
			if (null != announcement.getDocument().getId()) {
				tCommDocId.setDocId(announcement.getDocument().getId()
						.intValue());
			}
			tCommDocId.setCommId(tComm.getCommId());
			tCommDoc.setTCommDocId(tCommDocId);
			tCommDoc.setTComm(tComm);
		}

		tCommDoc.setDocLocation(announcement.getDocument().getDocLocation());
		if (null != announcement.getDocument().getPublishedDate()) {
			tCommDoc.setPublishedDt(announcement.getDocument()
					.getPublishedDate());
		}
		tCommDoc.setActiveFlag(CommonConstants.ACTIVE_Y);

		if (null != announcement.getDocument().getTenantId()) {
			tCommDoc.setTenantId(announcement.getDocument().getTenantId());
		}

		tCommDoc.setDocName(announcement.getDocument().getDocName());
		if (null != announcement.getId()) {
			tCommDoc.setUpdateDate(announcement.getDocument().getUpdatedDate());
			if (null != announcement.getDocument().getUpdatedBy()) {
				tCommDoc.setUpdatedBy(announcement.getDocument().getUpdatedBy());
			}

		}
		if (null != announcement.getDocument().getCreatedBy()) {
			tCommDoc.setCreatedBy(announcement.getDocument().getCreatedBy());
		}
		if (null != announcement.getDocument().getCreatedDate()) {
			tCommDoc.setCreateDt(announcement.getDocument().getCreatedDate());
		}

		return tCommDoc;
	}

	/**
	 * Map to t target recipient pref.
	 *
	 * @param recipientsSearchParam the recipients search param
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t target recipient pref
	 */
	public TTargetRecipientPref mapToTTargetRecipientPref(
			RecipientsSearchParam recipientsSearchParam, TComm tComm,
			Announcement announcement) {

		TTargetRecipientPref ttargetRecipientPref = new TTargetRecipientPref();

		ttargetRecipientPref.setActiveFlag(CommonConstants.ACTIVE);
		if (null != announcement.getCreatedBy()) {
			ttargetRecipientPref.setCreatedBy(announcement.getCreatedBy());
		}
		if (null != announcement.getCreatedDate()) {
			ttargetRecipientPref.setCreateDt(announcement.getCreatedDate());
		}
		if (null != announcement.getId()) {
			if (null != announcement.getUpdatedBy()) {
				ttargetRecipientPref.setUpdatedBy(announcement.getUpdatedBy());
			}
			if (null != announcement.getUpdatedDate()) {
				ttargetRecipientPref.setUpdateDt(announcement.getUpdatedDate());
			}
		}
		ttargetRecipientPref.setSrchPrefName(recipientsSearchParam
				.getSearchName());
		ttargetRecipientPref.setSrchPrefDesc(recipientsSearchParam
				.getSearchDesc());
		if (null != announcement.getEndDate()) {
			ttargetRecipientPref.setSrchCritEndDt(announcement.getEndDate());
		}
		if (null != announcement.getStartDate()) {
			ttargetRecipientPref
					.setSrchCritStartDt(announcement.getStartDate());
		}
		if (null != announcement.getTenantId()) {
			ttargetRecipientPref.setTenantId(announcement.getTenantId());
		}

		TPers tPers = new TPers();
		if (null != announcement.getCreatedBy()) {
			tPers.setStaffId(announcement.getCreatedBy());
		}
		if (null != tPers) {
			ttargetRecipientPref.setTPers(tPers);
		}
		// need to be modified
		TPubType tPubType = new TPubType();
		tPubType.setPubTypeId(1);
		ttargetRecipientPref.setTPubType(tPubType);

		return ttargetRecipientPref;
	}

	/**
	 * Map to t comm publisher.
	 *
	 * @param tTargetRecipientPref the t target recipient pref
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t comm publisher
	 */
	public TCommPublisher mapToTCommPublisher(
			TTargetRecipientPref tTargetRecipientPref, TComm tComm,
			Announcement announcement) {

		TComm tComm1 = new TComm();
		TCommPublisher tCommPublisher = new TCommPublisher();
		TCommPublisherId tCommPublisherId = new TCommPublisherId();
		if (null != tComm.getCommId()) {
			tCommPublisherId.setCommId(tComm.getCommId());

			if (null != announcement.getCreatedBy()) {
				tCommPublisherId.setStaffId(announcement.getCreatedBy());
			}
			tCommPublisher.setTCommPublisherId(tCommPublisherId);
			tComm1.setCommId(tComm.getCommId());
			tCommPublisher.setTComm(tComm);
		}
		if (null != tTargetRecipientPref.getRecipientPrefId()) {
			TTargetRecipientPref tTargetRecipientPref1 = new TTargetRecipientPref();
			tTargetRecipientPref1.setRecipientPrefId(tTargetRecipientPref
					.getRecipientPrefId());
			tCommPublisher.setTTargetRecipientPref(tTargetRecipientPref1);
		}

		if (null != announcement.getCreatedBy()) {
			TPers tPers = new TPers();
			tPers.setStaffId(announcement.getCreatedBy());
			tCommPublisher.setTPers(tPers);
		}
		if (null != announcement.getTenantId()) {
			tCommPublisher.setTenantId(announcement.getTenantId());
		}

		return tCommPublisher;
	}

	/**
	 * Map to t recipient attr.
	 *
	 * @param tTargetRecipientPref the t target recipient pref
	 * @param recipientsAttribute the recipients attribute
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t recipient attr
	 */
	public TRecipientAttr mapToTRecipientAttr(
			TTargetRecipientPref tTargetRecipientPref,
			RecipientsAttribute recipientsAttribute, TComm tComm,
			Announcement announcement) {

		TAttr tAttr = new TAttr();
		TTargetRecipientPref targetRecipientPref = new TTargetRecipientPref();
		Integer sequenceId = 0;

		TRecipientAttr tRecipientAttr = new TRecipientAttr();
		if (null != recipientsAttribute.getAttrId()) {
			TRecipientAttrId tRecipientAttrId = new TRecipientAttrId();
			tRecipientAttrId.setAttrId(recipientsAttribute.getAttrId());
			tRecipientAttrId.setRecipientPrefId(tTargetRecipientPref
					.getRecipientPrefId());
			tRecipientAttrId.setSequenceId(sequenceId);
			tRecipientAttr.setTRecipientAttrId(tRecipientAttrId);
		}
		if (null != recipientsAttribute.getCompareFactor()) {
			tRecipientAttr.setComparisonFactor(recipientsAttribute
					.getCompareFactor());
		}
		if (null != announcement.getCreatedBy()) {
			tRecipientAttr.setCreatedBy(announcement.getCreatedBy());
		}
		if (null != announcement.getCreatedDate()) {
			tRecipientAttr.setCreateDt(announcement.getCreatedDate());
		}
		if (null != recipientsAttribute.getLogicalOperator()) {
			tRecipientAttr
					.setOperator(recipientsAttribute.getLogicalOperator());
		}
		if (null != recipientsAttribute.getAttrId()) {
			tAttr.setAttrId(recipientsAttribute.getAttrId());
			tRecipientAttr.setTAttr(tAttr);
		}
		if (null != tTargetRecipientPref.getRecipientPrefId()) {
			targetRecipientPref.setRecipientPrefId(tTargetRecipientPref
					.getRecipientPrefId());
			tRecipientAttr.setTTargetRecipientPref(targetRecipientPref);
			// tRecipientAttr.setTTargetRecipientPref(targetRecipientPref);
		}
		if (null != announcement.getId()) {
			if (null != announcement.getUpdatedBy()) {
				tRecipientAttr.setUpdatedBy(announcement.getUpdatedBy());
			}
			if (null != announcement.getUpdatedDate()) {
				tRecipientAttr.setUpdateDt(announcement.getUpdatedDate());
			}
		}
		if (null != announcement.getTenantId()) {
			tRecipientAttr.setTenantId(announcement.getTenantId());
		}
		sequenceId = sequenceId + 1;

		return tRecipientAttr;
	}

	/**
	 * Map to t pref attr value.
	 *
	 * @param tRecipientAttr the t recipient attr
	 * @param recipientsAttribute the recipients attribute
	 * @param tComm the t comm
	 * @param announcement the announcement
	 * @return the t pref attr value
	 */
	public TPrefAttrValue mapToTPrefAttrValue(
			TRecipientAttr tRecipientAttr,
			RecipientsAttribute recipientsAttribute, TComm tComm,
			Announcement announcement) {

		TPrefAttrValue tPrefAttrValue = new TPrefAttrValue();
		tPrefAttrValue.setValue(recipientsAttribute.getAttrTextValue());
		TRecipientAttr tRecipientAttr1 = new TRecipientAttr();
		TRecipientAttrId tRecipientAttrId = new TRecipientAttrId();
		if (null != tRecipientAttr.getTRecipientAttrId().getAttrId()) {
			tRecipientAttrId.setAttrId(tRecipientAttr.getTRecipientAttrId()
					.getAttrId());
		}
		if (null != tRecipientAttr.getTRecipientAttrId().getRecipientPrefId()) {
			tRecipientAttrId.setRecipientPrefId(tRecipientAttr
					.getTRecipientAttrId().getRecipientPrefId());
		}
		if (null != tRecipientAttr.getTRecipientAttrId().getSequenceId()) {
			tRecipientAttrId.setSequenceId(tRecipientAttr.getTRecipientAttrId()
					.getSequenceId());
			tRecipientAttr1.setTRecipientAttrId(tRecipientAttrId);
			tPrefAttrValue.setTRecipientAttr(tRecipientAttr1);
		}
		if (null != announcement.getCreatedBy()) {
			tPrefAttrValue.setCreatedBy(announcement.getCreatedBy());
		}
		if (null != announcement.getCreatedDate()) {
			tPrefAttrValue.setCreateDt(announcement.getCreatedDate());
		}
		if (null != announcement.getId()) {
			if (null != announcement.getUpdatedBy()) {
				tPrefAttrValue.setUpdatedBy(announcement.getUpdatedBy());
			}
			if (null != announcement.getUpdatedDate()) {
				tPrefAttrValue.setUpdateDt(announcement.getUpdatedDate());
			}
		}
		if (null != announcement.getTenantId()) {
			tPrefAttrValue.setTenantId(announcement.getTenantId());
		}
		return tPrefAttrValue;
	}
	
	/**
	 * Map to recipients search param.
	 *
	 * @param tTargetRecipientPref the t target recipient pref
	 * @return the recipients search param
	 */
	public RecipientsSearchParam mapToRecipientsSearchParam(TTargetRecipientPref tTargetRecipientPref) {
		
		RecipientsSearchParam recipientsSearchParam = new RecipientsSearchParam();
		
		recipientsSearchParam.setSearchName(tTargetRecipientPref.getSrchPrefName());
		recipientsSearchParam.setSearchDesc(tTargetRecipientPref.getSrchPrefDesc());
		recipientsSearchParam.setPubTypeId(tTargetRecipientPref.getPubTypeId());	
		
		List<TRecipientAttr> tRecipientAttrList =  new ArrayList<TRecipientAttr>();
		tRecipientAttrList.addAll(tTargetRecipientPref.getTRecipientAttrss());
		
		List<RecipientsAttribute> recipientsAttributeList = new ArrayList<RecipientsAttribute>();

		for (TRecipientAttr tRecipientAttr : tRecipientAttrList) {
			RecipientsAttribute recipientsAttribute = new RecipientsAttribute();
			
			recipientsAttribute.setAttrId(tRecipientAttr.getTRecipientAttrId().getAttrId());
			recipientsAttribute.setCompareFactor(tRecipientAttr.getComparisonFactor());
			recipientsAttribute.setLogicalOperator(tRecipientAttr.getOperator());
			
			if (null != tRecipientAttr.getTPrefAttrValuess() && tRecipientAttr.getTPrefAttrValuess().toArray().length > 0) {
				recipientsAttribute.setAttrTextValue(((TPrefAttrValue) (tRecipientAttr.getTPrefAttrValuess().toArray()[0])).getValue());
			}
			recipientsAttributeList.add(recipientsAttribute);
		}
		recipientsSearchParam.setRecipientsAttributeList(recipientsAttributeList);
		
		return recipientsSearchParam;
	}
}
