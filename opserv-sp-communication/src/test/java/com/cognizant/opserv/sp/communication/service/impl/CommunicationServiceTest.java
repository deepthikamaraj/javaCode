package com.cognizant.opserv.sp.communication.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.NoteStatus;
import com.cognizant.opserv.sp.common.NotificationType;
import com.cognizant.opserv.sp.common.communication.AckStatusType;
import com.cognizant.opserv.sp.common.communication.CommunicationStatusType;
import com.cognizant.opserv.sp.common.communication.CommunicationType;
import com.cognizant.opserv.sp.communication.service.CommunicationService;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.communication.Announcement;
import com.cognizant.opserv.sp.model.communication.Communication;
import com.cognizant.opserv.sp.model.communication.Document;
import com.cognizant.opserv.sp.model.communication.Recipient;
import com.cognizant.opserv.sp.model.communication.RecipientsAttribute;
import com.cognizant.opserv.sp.model.communication.RecipientsSearchParam;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


/**
 * ****************************************************************************.
 *
 * @class CommunicationServiceTest 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 23/06/2016
 * ***************************************************************************
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-Communication-test.xml" })
public class CommunicationServiceTest {
	
private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CommunicationServiceTest.class);
	
	@Autowired
	private CommunicationService communicationService ;
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("opserv.config.file","C:/opserv/config/opserv-config.properties");
	}
	
	@Test
	public void testCreateAnnouncement() {
		LOGGER.info("----Started testGetAllMetricResults------");
	
		//SalesPosition sp = getSalesPosition2();
		//UserDetails userDetails = getUserDetails();
		try {
            //Communication commun = new  Communication();
            List<RecipientsAttribute> recipientsAttributeList = new ArrayList<RecipientsAttribute>();
            RecipientsAttribute recipientsAttribute = new RecipientsAttribute();
            RecipientsSearchParam recipientsSearchParam = new RecipientsSearchParam();
            Recipient  recipient = new  Recipient();
            Recipient  recipient1 = new  Recipient();
            List<Recipient> recipentlist = new ArrayList<Recipient>();
        
            Announcement model= new Announcement();
            
            Document document=new Document();
            document.setId(125l);
            document.setDocLocation("location");
            document.setDocName("kochi uu docName");
            document.setPublishedDate(new Date());
            document.setCreatedBy(1);
            document.setUpdatedBy(2);
            document.setCreatedDate(new Date());
            document.setUpdatedDate(new Date());
            document.setTenantId(new Short("1"));
            model.setDocument(document);
            
            model.setAckRequired(true);
     //     model.setId(55l);//false
            model.setDetails("update 26 Announsement");// email content
            model.setName(" Announsement Name");// email subject
            model.setCreatedDate(new Date());
            model.setCreatedBy(1);
            model.setPriority(true);
            model.setUpdatedBy(2);
            model.setUpdatedDate(new Date());
            
            model.setStartDate( new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-12"));
            model.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-13"));
//          model.set("01/01/2016");//current date
//          model.setFromDate("06/31/2016");//15 days from Cdate
            model.setStatus(CommunicationStatusType.DRAFT);
            model.setType(CommunicationType.ACTIVITY);
            model.setTenantId(new Short("1"));
            
            model.setNotification(NotificationType.BOTH);
            recipient.setRecipientId(708);
            recipient.setTargetRecipient(true) ;   
            recipient.setNoteStatus(NoteStatus.NOTIFIED);
            recipentlist.add(recipient);
            recipient1.setRecipientId(709);
            recipient1.setTargetRecipient(true)  ;    
            recipient1.setNoteStatus(NoteStatus.NOTIFIED);
            recipentlist.add(recipient1);
            
            recipientsAttribute.setAttrId(1);
            recipientsAttribute.setAttrTextValue("-1");
            recipientsAttribute.setCompareFactor("NOT EQUALS");
            recipientsSearchParam.setSearchDesc("update 25");
            recipientsSearchParam.setSearchName("update 25");
            recipientsAttribute.setLogicalOperator("&");
            recipientsAttributeList.add(recipientsAttribute);
            recipientsSearchParam.setRecipientsAttributeList(recipientsAttributeList);
            
            model.setRecipients(recipentlist);
            model.setRecipientsSearchParams(recipientsSearchParam);
            
            communicationService.createAnnouncement(model);

		} catch (Exception e) {
			LOGGER.error("Error");
		} 
	}
	
	/*private UserDetails getUserDetails(){
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		userDetails.setUserId(1);
		return userDetails;
	}*/
	
	@Test
	public void testpublishAnnouncement() {
		LOGGER.info("----Started testGetAllMetricResults------");
	
		try {
			
			Announcement announcement= new Announcement();
			announcement.setId(Long.valueOf(33));
			announcement.setUpdatedBy(1);
			announcement.setPublishedDate(new SimpleDateFormat("yyyy-MM-dd").parse("2015-06-26"));
			announcement.setNotification(NotificationType.BOTH);
			announcement.setTenantId(Short.valueOf("1"));
			Recipient recipient=new Recipient();
			recipient.setRecipientId(2663);
			List<Recipient> recipients = new ArrayList<Recipient>();
			recipients.add(recipient);
			announcement.setRecipients(recipients);
			communicationService.publishAnnouncement(announcement);
		} catch (Exception e) {
			LOGGER.error("Error");
		} 
	}

	@Test
	public void getAnnouncementsByUser() throws BusinessException {
			
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(400);
		userDetails.setTenantId((short)1);
		communicationService.getAnnouncementsByUser(userDetails);
	}
	
	@Test
	public void acknowledgeAnnouncementByUser() throws BusinessException {
			
		Communication communication = new Communication();
		Recipient recipient = new Recipient();

		communication.setId(2L);
		recipient.setRecipientId(397);
		recipient.setAckStatus(AckStatusType.REJECTED);
		recipient.setReadDate(new Date());
		recipient.setAckDate(new Date());
		recipient.setRejectReason("Rejecting");
		communication.setTenantId((short)1);

		communicationService.acknowledgeAnnouncementByUser(communication, recipient);
	}
	
	@Test
	public void getAnnouncementsByPublisher() throws BusinessException {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(2250);
		userDetails.setTenantId((short)1);
		
		CommunicationStatusType type = CommunicationStatusType.PUBLISHED;
		
		PaginationInfo pageInfo = new PaginationInfo(0, 5);
		
		communicationService.getAnnouncementsByPublisher(userDetails, type, pageInfo);
	}
	
}
