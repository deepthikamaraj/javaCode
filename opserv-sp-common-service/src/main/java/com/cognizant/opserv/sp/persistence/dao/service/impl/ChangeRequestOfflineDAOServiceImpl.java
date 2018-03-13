package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.core.dao.TChngreqOfflineDAO;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.model.OfflineRequest;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.peg.core.common.LogicalOperatorEnum;
import com.cognizant.peg.core.common.OperatorInfo;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Repository
public class ChangeRequestOfflineDAOServiceImpl implements ChangeRequestOfflineDAOService{

	@Autowired
	private TChngreqOfflineDAO tChngreqOfflineDAO;
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(ChangeRequestOfflineDAOServiceImpl.class);

	/**
	 * Fetch temp cr info for offline status Initiated(1).
	 * 
	 * @param tempCRId
	 *            the temp cr id
	 * @param userDetails
	 *            the user details
	 * @return the temp cr
	 */
	@Override
	public OfflineRequest fetchtempCRInfo(Long tempCRId, UserDetails userDetails) throws OpservDataAccessException{
		try{
			LOGGER.info("==================Fetch fetch Temp CR info Started from TChngReqOffline Table==============");
		List<TChngreqOffline> fetchTTempChngReq = tChngreqOfflineDAO.fetchTChngreqOfflineByOfflineId(tempCRId, userDetails.getTenantId());
		LOGGER.info("==================Fetch fetch Temp CR info Ended from TChngReqOffline Table==============");
		if(fetchTTempChngReq !=null){
			for(TChngreqOffline tempChngreq : fetchTTempChngReq){
				OfflineRequest tempCR = new OfflineRequest();
				tempCR.setTempCRId(tempCRId);
				tempCR.setTriggerId(tempChngreq.getTriggerId());
				// todo : put null chck
				if(null != tempChngreq.getTChngReq()){
					tempCR.setCrId(tempChngreq.getTChngReq().getChngReqId());
				}
				tempCR.setJsonString(tempChngreq.getReqDetail());
				return tempCR;
			}
		}
		return null;
		}catch (RuntimeException ex) {
			LOGGER.error("Exception whilefetching Temp CR info "+ex.getMessage());
			throw new OpservDataAccessException(
					"Exception whilefetching Temp CR info",
					ex);
		}
	}

	@Override
	public List<TChngreqOffline> findTTempByChngreqId(ChangeRequest chnageRequest, UserDetails userDetails)throws OpservDataAccessException {
		try{
			return tChngreqOfflineDAO.fetchTChngreqOffline(chnageRequest.getId(), userDetails.getTenantId());
		}catch (RuntimeException ex) {
			LOGGER.error("Exception while fetching TempChangeRequestOffline by crId"+ex.getMessage());
			throw new OpservDataAccessException(
					"Exception while fetching TempChangeRequestOffline by crId",
					ex);
		}
	}
	
	
	/**
	 * check Status Of TChngreqOffline
	 * @param offlineID
	 * @param userDetails
	 * @return boolean
	 */
	@Override
	public boolean checkStatusOfTChngreqOffline(Long offlineID, UserDetails userDetails) throws OpservDataAccessException{
		try {
			Long countOfCustAlgmntByPSStatus = tChngreqOfflineDAO.checkStatusOfTChngreqOffline(offlineID ,userDetails.getTenantId());
			if(countOfCustAlgmntByPSStatus > 0){
				return true;
			}
			return false;
		} catch (RuntimeException ex) {
			LOGGER.error("Exception while checking status of changeRequestOffline"+ex.getMessage());
			throw new OpservDataAccessException(
					"Exception while checking status of changeRequestOffline",ex);
		}
	}

	
	/**
	 * Update CR Offline Status
	 * 
	 * @param stausId
	 *            - staus Id
	 * @param crOfflineId
	 *            -cr Offline Id
	 * @param userDetails
	 *            -user Details
	 * @param failedReason
	 *            -failed Reason
	 */
	@Override
	public void updateCROfflineStatus(Integer stausId, Long crOfflineId, Integer userId,
			Short tenantId, String failedReason) throws OpservDataAccessException{
		try{
			LOGGER.info("=====================update CR Offline Status started=========================");
			tChngreqOfflineDAO.updateTChngreqOfflineByStatusId(stausId, crOfflineId,userId,tenantId, failedReason);
			LOGGER.info("=====================update CR Offline Status ended=========================");
		}catch (RuntimeException ex) {
			LOGGER.error("Exception while updating CR Offline Status "+ex.getMessage());
			throw new OpservDataAccessException(
					"Exception while updating CR Offline Status",
					ex);
		}
				
	}
	
	/**
	 * Checks for pending offline request.
	 *
	 * @param CrId the cr id
	 * @param userDetails the user details
	 * @return true, if successful
	 */
	@Override
	public boolean hasPendingOfflineRequest(Long CrId,  Short tenantId) throws OpservDataAccessException {
		try{
			LOGGER.info("=====================hasPendingOfflineRequest started=========================");
			long countOfOfflineCRStatus = tChngreqOfflineDAO.countOfOfflineCRStatus(CrId, tenantId);
			LOGGER.info("=====================hasPendingOfflineRequest ended=========================");
			if(countOfOfflineCRStatus > 0 ){
				return true;
			}
			return false;
		}catch (RuntimeException ex) {
			LOGGER.error("Exception while checking pending offline request "+ex.getMessage());
				throw new OpservDataAccessException(
						"Exception while checking pending offline request",
						ex);
			}
	}

	@Override
	public Map<Integer, Integer> getOffLineStatuses(ChangeRequest changeRequest, UserDetails userDetails) throws OpservDataAccessException {
		Map<Integer,Integer> statuses = new HashMap<Integer,Integer>();
		try {
			List<TChngreqOffline> offLineItems =  findTTempByChngreqId(changeRequest,userDetails);
			Integer cnt = 1;
			for(TChngreqOffline offLineItem : offLineItems) {
				cnt = 0;
				if(statuses.containsKey(offLineItem.getStatusId())) {
					cnt = statuses.get(offLineItem.getStatusId());
				} 
				++cnt;
				statuses.put(offLineItem.getStatusId(),cnt);
			}
		} catch (RuntimeException ex) {
			LOGGER.error("Exception while getOffLineStatuses : "+ex.getMessage());
			throw new OpservDataAccessException("Exception while fetching offline statuses",ex);
		}
		return statuses;
	}

	@Override
	public List<OfflineRequest> findTempCRsByChangeRequest(ChangeRequest changeRequest,
			UserDetails userDetails) throws OpservDataAccessException {
		 List<OfflineRequest> tempCRs = null;
		 List<TChngreqOffline> chngreqOfflines=tChngreqOfflineDAO.fetchTChngreqOffline(changeRequest.getId(), userDetails.getTenantId());
		if(chngreqOfflines !=null && !chngreqOfflines.isEmpty()){
			tempCRs = new ArrayList<OfflineRequest>();
			for(TChngreqOffline tempChngreq : chngreqOfflines){
				OfflineRequest tempCR = new OfflineRequest();
				tempCR.setTempCRId(tempChngreq.getOfflineId());
				tempCR.setTriggerId(tempChngreq.getTriggerId());
				// todo : put null chck
				if(null != tempChngreq.getTChngReq()){
					tempCR.setCrId(tempChngreq.getTChngReq().getChngReqId());
				}
				ChangeRequestOfflineStatusType changeRequestOfflineStatusType = ChangeRequestOfflineStatusType.getChangeRequestOfflineStatusType(tempChngreq.getStatusId());
				tempCR.setOfflineStatus(changeRequestOfflineStatusType);
				tempCR.setJsonString(tempChngreq.getReqDetail());
				tempCR.setFailedReason(tempChngreq.getFailedReason());
				tempCRs.add(tempCR);
			}
		}
		return tempCRs;
	}

}
