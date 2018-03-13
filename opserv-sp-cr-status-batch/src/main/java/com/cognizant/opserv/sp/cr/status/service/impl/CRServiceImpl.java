package com.cognizant.opserv.sp.cr.status.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.cr.status.persistence.dao.service.CRDAOService;
import com.cognizant.opserv.sp.cr.status.service.CRService;
import com.cognizant.opserv.sp.event.messages.ChangeRequestMessage;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("cancelCRStatusService")
@Transactional
public class CRServiceImpl implements CRService{
	
	@Autowired
	@Qualifier("changeRequestDAOService")
	private CRDAOService changeRequestDAOService;
	
	@Value("${time.diff.for.cancelling.pending.submission}")
	private Long timeDiffForCancelPS;
	
	@Value("${time.diff.for.cancelling.wip}")
	private Long timeDiffForCancelWIP;
	
	@Value("${time.diff.for.initiate:600000}")
	private Long timeDiffForInitiate;
	
	@Autowired
	private GenericPublisher genericPublisher;
	
	public void cancleCRStatus(short tenantId) throws OpservDataAccessException{
		
		//create a userDetail obj
		UserDetails userDetails = createUserObj(tenantId);
		
		// get List of CR where status in Pending Submission / WIP
		List<Integer> statusForCancellation = new ArrayList<Integer>();
		statusForCancellation.add(ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId());
		statusForCancellation.add(ChangeRequestStatusType.WORK_IN_PROGRESS.getId());
		List<ChangeRequest> changeRequestsList = changeRequestDAOService.getChngeRequestForCancellation(statusForCancellation, tenantId); 
		
		// check the time difference
		if(null != changeRequestsList && !changeRequestsList.isEmpty()){
			for(ChangeRequest changeRequest : changeRequestsList){
				if(changeRequest.getStatus().equals(ChangeRequestStatusType.PENDING_FOR_SUBMISSION) &&
						((new Date()).getTime() - changeRequest.getUpdatedDate().getTime()) >  timeDiffForCancelPS){
					// push msg in the queue to cancle CR
					ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
					changeRequestMessage.setChngReqID(changeRequest.getId());
					changeRequestMessage.setUserDetails(userDetails);
					
					genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE, changeRequestMessage);
					
				}else if(changeRequest.getStatus().equals(ChangeRequestStatusType.WORK_IN_PROGRESS) &&
						((new Date()).getTime() - changeRequest.getUpdatedDate().getTime()) >  timeDiffForCancelWIP){
					// push msg in the queue to cancle CR
					ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
					changeRequestMessage.setChngReqID(changeRequest.getId());
					changeRequestMessage.setUserDetails(userDetails);
					
					genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE, changeRequestMessage);
					
				}
			}
		}
	}

	@Override
	public void resendCRMsg(short tenantId) throws OpservDataAccessException {
		
		//create a userDetail obj
		UserDetails userDetails = createUserObj(tenantId);	
				
		//fetch cr offline with status as initiated
		Integer statusId = 1;
		List<TChngreqOffline> chngreqOfflineList = changeRequestDAOService.getTChngreqOfflineByStatusId(statusId, userDetails);
		for(TChngreqOffline chngreqOffline : chngreqOfflineList){
			
			Date lastUpdatedDt = null;
			if(null != chngreqOffline.getUpdateDt()){
				lastUpdatedDt = chngreqOffline.getUpdateDt();
			}else if(null != chngreqOffline.getCreateDt()){
				lastUpdatedDt = chngreqOffline.getCreateDt();
			}
			//compare the time diff
			if(null != lastUpdatedDt && 
					(new Date()).getTime() - lastUpdatedDt.getTime() >  timeDiffForCancelPS){
				
				if(null != chngreqOffline && null != chngreqOffline.getTChngReq() && null != chngreqOffline.getTChngReq().getChngReqId()){
					// push msg in the queue to process CR
					ChangeRequestMessage changeRequestMessage = new ChangeRequestMessage();
					changeRequestMessage.setChngReqID(chngreqOffline.getTChngReq().getChngReqId());
					changeRequestMessage.setUserDetails(userDetails);
					
					genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_CHNAGE_REQUEST_WITHDRAWL_QUEUE, changeRequestMessage);
				}
			}
		}
	}
	
	
	@Override
	public void deleteVoidCR(short tenantId) throws OpservDataAccessException {
		
		//create a userDetail obj
		UserDetails userDetails = createUserObj(tenantId);
		
		//Fetch all the CR Ids from t_chng_req table that donot have a corresponding entry in t_cust_algmnt_chng_req_det
		Set<Long> crIdFrmDetails =  changeRequestDAOService.getAllCRIdsInCustAffChngReqDets(userDetails);
		
		Set<Long> crIdsToDelete = null;
		if(null != crIdFrmDetails && crIdFrmDetails.size() != 0){
			changeRequestDAOService.deleteNotReqCRIds(crIdFrmDetails, timeDiffForInitiate, userDetails);
			////////crIdsToDelete = changeRequestDAOService.findCRIdsNotInGivenCRIdList(crIdFrmDetails, userDetails);
		}
		
		////////Delete cr_id from t_chng_req 
		///////changeRequestDAOService.deleteNotRequiredCRs(crIdsToDelete, userDetails);
	}
	
	private UserDetails createUserObj(short tenantId){
		//create a userDetail obj
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(80);
		userDetails.setTenantId(tenantId);
		return userDetails;
	}


}
