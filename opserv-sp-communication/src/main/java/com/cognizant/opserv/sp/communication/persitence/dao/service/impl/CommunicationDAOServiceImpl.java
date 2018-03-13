package com.cognizant.opserv.sp.communication.persitence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.communication.assembler.CommunicationModelAssembler;
import com.cognizant.opserv.sp.communication.persitence.dao.service.CommunicationDAOService;
import com.cognizant.opserv.sp.core.dao.TCommDAO;
import com.cognizant.opserv.sp.core.dao.TCommDocDAO;
import com.cognizant.opserv.sp.core.dao.TCommNoteTypeDAO;
import com.cognizant.opserv.sp.core.dao.TCommPublisherDAO;
import com.cognizant.opserv.sp.core.dao.TCommReceipientDAO;
import com.cognizant.opserv.sp.core.dao.TCommTargetRecipientDAO;
import com.cognizant.opserv.sp.core.dao.TPersDAO;
import com.cognizant.opserv.sp.core.dao.TPrefAttrValueDAO;
import com.cognizant.opserv.sp.core.dao.TRecipientAttrDAO;
import com.cognizant.opserv.sp.core.dao.TTargetRecipientPrefDAO;
import com.cognizant.opserv.sp.core.entity.TComm;
import com.cognizant.opserv.sp.core.entity.TCommDoc;
import com.cognizant.opserv.sp.core.entity.TCommNoteType;
import com.cognizant.opserv.sp.core.entity.TCommPublisher;
import com.cognizant.opserv.sp.core.entity.TCommReceipient;
import com.cognizant.opserv.sp.core.entity.TCommReceipientId;
import com.cognizant.opserv.sp.core.entity.TCommTargetRecipient;
import com.cognizant.opserv.sp.core.entity.TPrefAttrValue;
import com.cognizant.opserv.sp.core.entity.TRecipientAttr;
import com.cognizant.opserv.sp.core.entity.TTargetRecipientPref;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.opserv.sp.model.communication.RecipientsAttribute;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * ****************************************************************************.
 * @Interface CommunicationDAOServiceImpl contains implementation for CommunicationDAOService
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
@Component("communicationDAOService")
public class CommunicationDAOServiceImpl implements CommunicationDAOService {

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(CommunicationDAOServiceImpl.class);

	/**
	 * @Autowired tCommDAO has DAO calls to TComm table
	 */
	@Autowired
	private TCommDAO tCommDAO;
	
	/**
	 * @Autowired tCommReceipientDAO
	 */
	@Autowired
	private TCommReceipientDAO tCommReceipientDAO;
	
	/**
	 * @Autowired tPersDAO
	 */
	@Autowired
	private TPersDAO tPersDAO;

	
	@Autowired
	private TCommNoteTypeDAO tCommNoteTypeDAO;
	
	@Autowired
	private TCommTargetRecipientDAO tCommTargetRecipientDAO;
	@Autowired
	TCommDocDAO tCommDocDAO;
	

	
	@Autowired
	private TTargetRecipientPrefDAO tTargetRecipientPrefDAO;
	
	@Autowired
	private TCommPublisherDAO tCommPublisherDAO;
	
	@Autowired
	private TRecipientAttrDAO tRecipientAttrDAO;
	
	@Autowired
	private TPrefAttrValueDAO tPrefAttrValueDAO;
	
	@Autowired
	private CommunicationModelAssembler communicationModelAssembler;
	
	/**
	 * To publish an announcement
	 * @param announcement
	 * @throws OpservDataAccessException
	 */
	@Override
	public Announcement createAnnouncement(Announcement announcement)
			throws OpservDataAccessException {

		try {
			TComm tComm = communicationModelAssembler.mapToTComm(announcement);
			Long commId = announcement.getId();
			List<Recipient> targetRecipients = new ArrayList<Recipient>();

			if (commId != null) {
				tComm.setCommId(commId);
				tComm = tCommDAO.updateTComm(tComm);

				TCommNoteType tCommNoteType = communicationModelAssembler
						.mapToTCommNoteType(tComm, announcement);
				tCommNoteTypeDAO.updateTCommNoteType(tCommNoteType);

				// To save recipients
				if (null != announcement.getRecipients()
						&& announcement.getRecipients().size() > 0) {
					// Saving all recipatnts to t_comm_reciptant
					List<TCommReceipient> tCommReceipientLists = communicationModelAssembler
							.mapToTCommRecipients(announcement.getRecipients(),
									tComm, announcement);
					tCommReceipientDAO
							.updateTCommReceipients(tCommReceipientLists);
					// Filtering specfic reciptants and saving to
					// t_comm_target_reciptant
					for (Recipient recipient : announcement.getRecipients()) {
						if (recipient.isTargetRecipient()) {
							targetRecipients.add(recipient);
						}
					}
					if (null != targetRecipients) {
						List<TCommTargetRecipient> tCommTargetReceipientLists = communicationModelAssembler
								.mapToTCommTargetRecipient(targetRecipients,
										tComm, announcement);
						tCommTargetRecipientDAO
								.updateTCommTargetReceipients(tCommTargetReceipientLists);
					}
				}
				if (null != announcement.getDocument()) {
					TCommDoc tCommDoc = communicationModelAssembler
							.mapToTCommDoc(tComm, announcement);
					tCommDocDAO.updateTCommDoc(tCommDoc);
				}
				// To update t_target_recipient_pref

				if (null != announcement.getRecipientsSearchParams()) {
					TTargetRecipientPref ttargetRecipientPreference = communicationModelAssembler
							.mapToTTargetRecipientPref(
									announcement.getRecipientsSearchParams(),
									tComm, announcement);
					ttargetRecipientPreference = tTargetRecipientPrefDAO
							.updateTTargetRecipientPref(ttargetRecipientPreference);

					TCommPublisher tCommPublisher = communicationModelAssembler
							.mapToTCommPublisher(ttargetRecipientPreference,
									tComm, announcement);
					tCommPublisherDAO.updateTCommPublisher(tCommPublisher);

					if (null != announcement.getRecipientsSearchParams()
							.getRecipientsAttributeList()) {

						for (RecipientsAttribute recipientsAttribute : announcement
								.getRecipientsSearchParams()
								.getRecipientsAttributeList()) {

							TRecipientAttr tRecipientAttr = communicationModelAssembler
									.mapToTRecipientAttr(
											ttargetRecipientPreference,
											recipientsAttribute, tComm,
											announcement);
							tRecipientAttr = tRecipientAttrDAO
									.updateTRecipientAttr(tRecipientAttr);
							TPrefAttrValue tPrefAttrValue = communicationModelAssembler
									.mapToTPrefAttrValue(tRecipientAttr,
											recipientsAttribute, tComm,
											announcement);
							tPrefAttrValueDAO
									.updateTPrefAttrValue(tPrefAttrValue);
						}

					}

				}
			} else {
				tComm = tCommDAO.createTComm(tComm);

				announcement.setId(tComm.getCommId());
				// To save t_com_note_type
				TCommNoteType tCommNoteType = communicationModelAssembler
						.mapToTCommNoteType(tComm, announcement);
				tCommNoteTypeDAO.createTCommNoteType(tCommNoteType);
				// To save recipients
				createRecipitants(announcement, tComm);
				// To save t_com_doc
				if (null != announcement.getDocument()) {
					TCommDoc tCommDoc = communicationModelAssembler
							.mapToTCommDoc(tComm, announcement);
					tCommDocDAO.createTCommDoc(tCommDoc);
				}

				// To save t_target_recipient_pref

				if (null != announcement.getRecipientsSearchParams()) {
					TTargetRecipientPref ttargetRecipientPreference = communicationModelAssembler
							.mapToTTargetRecipientPref(
									announcement.getRecipientsSearchParams(),
									tComm, announcement);
					ttargetRecipientPreference = tTargetRecipientPrefDAO
							.createTTargetRecipientPref(ttargetRecipientPreference);

					TCommPublisher tCommPublisher = communicationModelAssembler
							.mapToTCommPublisher(ttargetRecipientPreference,
									tComm, announcement);
					tCommPublisherDAO.createTCommPublisher(tCommPublisher);

					if (null != announcement.getRecipientsSearchParams()
							.getRecipientsAttributeList()) {

						for (RecipientsAttribute recipientsAttribute : announcement
								.getRecipientsSearchParams()
								.getRecipientsAttributeList()) {

							TRecipientAttr tRecipientAttr = communicationModelAssembler
									.mapToTRecipientAttr(
											ttargetRecipientPreference,
											recipientsAttribute, tComm,
											announcement);
							tRecipientAttr = tRecipientAttrDAO
									.createTRecipientAttr(tRecipientAttr);
							TPrefAttrValue tPrefAttrValue = communicationModelAssembler
									.mapToTPrefAttrValue(tRecipientAttr,
											recipientsAttribute, tComm,
											announcement);
							tPrefAttrValueDAO
									.createTPrefAttrValue(tPrefAttrValue);
						}

					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
		return announcement;
	}

	
	/**
	 * To publish an announcement
	 * @param announcement
	 * @throws BusinessException
	 */
	@Override
	public void publishAnnouncement(Long communicationId, Date publishedDate,Integer userId,int status)
			throws OpservDataAccessException {
		try {
			if(null == publishedDate)
				publishedDate= new Date();
			tCommDAO.piblishAnnouncement(communicationId, publishedDate, userId,status);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
		
	}
	
	/**
	 * To get announcements by recipient
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<Announcement> getAnnouncementsByUser(
			UserDetails userDetails) throws OpservDataAccessException {

		List<Announcement> announcements = new ArrayList<Announcement>();
		
		try {
			List<TComm> tCommList = tCommDAO.getAnnouncementsByUser(userDetails.getUserId(), userDetails.getTenantId());
			
			if (null != tCommList) {
				for (TComm tComm : tCommList) {
					Announcement announcement = communicationModelAssembler.mapEntityToAnnouncementModel(tComm, false);
					announcements.add(announcement);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
		
		return announcements;
	}
	
	/**
	 * To acknowledge announcement by recipient
	 * @param communication
	 * @param UserDetails
	 * @param status
	 * @param rejectReason
	 * @throws OpservDataAccessException
	 */
	@Override
	public void acknowledgeAnnouncementByUser(Communication communication,
			Recipient recipient) throws OpservDataAccessException {
		
		try {
			TCommReceipientId tCommReceipientId = new TCommReceipientId();
			tCommReceipientId.setCommId(communication.getId());
			tCommReceipientId.setStaffId(recipient.getRecipientId());
			
			TCommReceipient tCommReceipient = tCommReceipientDAO.findTCommReceipientById(tCommReceipientId);
			
			if (null != tCommReceipient) {
				List<Object> receipientDetailsDB = communicationModelAssembler.mapEntityToRecipientModel(tCommReceipient);
				
				tCommReceipientDAO.updateTCommReceipient(communicationModelAssembler
						.mapRecipientModelToEntityUpdate(receipientDetailsDB, communication, recipient));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
	}
	
	/**
	 * To get announcements by publisher
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<Announcement> getAnnouncementsByPublisher(UserDetails userDetails, CommunicationStatusType type, 
			PaginationInfo paginInfo) throws OpservDataAccessException {

		List<Announcement> announcements = new ArrayList<Announcement>();
		
		try {
			List<TComm> tCommList = tCommDAO.getAnnouncementsByPublisher(userDetails.getUserId(), type.getId(), paginInfo, userDetails.getTenantId());
			
			if (null != tCommList) {
				for (TComm tComm : tCommList) {
					Announcement announcement = communicationModelAssembler.mapEntityToAnnouncementModel(tComm, true);
					
					if (null != tComm.getTCommPublisherss() && tComm.getTCommPublisherss().toArray().length > 0) {
						TTargetRecipientPref tTargetRecipientPref = tTargetRecipientPrefDAO
								.findTTargetRecipientPrefById(((TCommPublisher) tComm.getTCommPublisherss().toArray()[0]).getTTargetRecipientPref()
										.getRecipientPrefId());
						if (null != tTargetRecipientPref) {
							announcement.setRecipientsSearchParams(communicationModelAssembler.mapToRecipientsSearchParam(tTargetRecipientPref));
						}
					}
					
					announcements.add(announcement);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new OpservDataAccessException(e.getMessage());
		}
		
		return announcements;
	}
	
	private void createRecipitants(Announcement announcement, TComm tComm) {
		List<Recipient> targetRecipients = new ArrayList<Recipient>();
		// To save recipients
		if (null != announcement.getRecipients()
				&& announcement.getRecipients().size() > 0) {
			// Saving all recipatnts to t_comm_reciptant
			List<TCommReceipient> tCommReceipientLists = communicationModelAssembler.mapToTCommRecipients(announcement.getRecipients(), tComm,
							announcement);
			tCommReceipientDAO.createTCommReceipients(tCommReceipientLists);
			// Filtering specfic reciptants and saving to
			// t_comm_target_reciptant
			for (Recipient recipient : announcement.getRecipients()) {

				if (recipient.isTargetRecipient()) {
					targetRecipients.add(recipient);
				}

			}
			
			if (null != targetRecipients) {
				List<TCommTargetRecipient> tCommTargetReceipientLists = communicationModelAssembler.mapToTCommTargetRecipient(targetRecipients, tComm,
								announcement);
				tCommTargetRecipientDAO
						.createTCommTargetRecipients(tCommTargetReceipientLists);
			}
		}

	}
	
}
