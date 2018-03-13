package com.cognizant.opserv.sp.cr.status.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.core.dao.TChngreqOfflineDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntChngReqDetDAOImpl;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.cr.status.persistence.dao.service.CRDAOService;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Repository("changeRequestDAOService")
public class CRDAOServiceImpl implements CRDAOService{
	
	@Autowired
	private TChngReqDAO changeRequestDao;
	
	@Autowired
	private TChngreqOfflineDAO chngreqOfflineDAO;
	
	@Autowired
	private TCustAlgmntChngReqDetDAOImpl custAlgmntChngReqDetDAO;


	@Override
	public List<ChangeRequest> getChngeRequestForCancellation(List<Integer> statusIds , Short tenantId) 
			throws OpservDataAccessException {
		List<ChangeRequest> ChangeRequestList = new ArrayList<ChangeRequest>();
		
		List<TChngReq> tChngReqList = changeRequestDao.findChangeRequestByStatusId(statusIds, tenantId);
		if(null != tChngReqList && ! tChngReqList.isEmpty()){
			for(TChngReq tChngReq : tChngReqList){
				if(null != tChngReq.getStatusId() && null != tChngReq.getUpdateDt()){
					ChangeRequest changeRequest = new ChangeRequest();
					changeRequest.setId(tChngReq.getChngReqId());
					changeRequest.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(tChngReq.getStatusId()));
					changeRequest.setUpdatedDate(tChngReq.getUpdateDt());
					ChangeRequestList.add(changeRequest);
				}
			}
		}
		return ChangeRequestList;
	}

	@Override
	public List<TChngreqOffline> getTChngreqOfflineByStatusId(Integer statusId, UserDetails userDetails) throws OpservDataAccessException {
		return chngreqOfflineDAO.getTChngreqOfflineByStatusId(statusId, userDetails.getTenantId());
	}
	
	@Override
	public Set<Long> getAllCRIdsInCustAffChngReqDets(UserDetails userDetails) throws OpservDataAccessException{
		Set<Long> crIdsInDetails = new HashSet<Long>();
		List<Object[]> crIdsList = custAlgmntChngReqDetDAO.getAllCrIdInTCustAffChngReqDets(userDetails.getTenantId());
		if(null != crIdsList){
			for(Object crId : crIdsList){
				if(null != crId){
					crIdsInDetails.add((Long)crId);
				}
			}
		}
		return crIdsInDetails;
	}
	
	@Override
	public Set<Long> findCRIdsNotInGivenCRIdList(Set<Long> crIdList, UserDetails userDetails) throws OpservDataAccessException{
		Set<Long> crIdsNotInDetails = new HashSet<Long>();
		List<Object[]> crIdsList = changeRequestDao.findCRIdsNotInGivenCRIdList(crIdList, userDetails.getTenantId());
		if(null != crIdsList){
			for(Object crId : crIdsList){
				if(null != crId){
					crIdsNotInDetails.add((Long)crId);
				}
			}
		}
		return crIdsNotInDetails;
	}
	
	@Override
	public void deleteNotRequiredCRs(Set<Long> crIds, UserDetails userDetails){
		changeRequestDao.updateFlag(crIds);
	}
	
	@Override
	public void deleteNotReqCRIds(Set<Long> crIds, Long timeDiffForInitiate, UserDetails userDetails){
		changeRequestDao.updateFlagAsInactive(crIds, timeDiffForInitiate, userDetails.getTenantId());
	}
	
}
