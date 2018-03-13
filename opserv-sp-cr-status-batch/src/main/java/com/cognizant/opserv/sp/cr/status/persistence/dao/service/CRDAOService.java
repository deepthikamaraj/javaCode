package com.cognizant.opserv.sp.cr.status.persistence.dao.service;

import java.util.List;
import java.util.Set;

import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface CRDAOService {
	
	List<ChangeRequest> getChngeRequestForCancellation(List<Integer> statusIds , Short tenantId) throws OpservDataAccessException;
	
	List<TChngreqOffline> getTChngreqOfflineByStatusId(Integer statusId, UserDetails userDetails) throws OpservDataAccessException;
	
	Set<Long> getAllCRIdsInCustAffChngReqDets(UserDetails userDetails) throws OpservDataAccessException;
	
	Set<Long> findCRIdsNotInGivenCRIdList(Set<Long> crIdList, UserDetails userDetails) throws OpservDataAccessException;
	
	void deleteNotRequiredCRs(Set<Long> crIds, UserDetails userDetails);
	
	void deleteNotReqCRIds(Set<Long> crIds, Long timeDiffForInitiate, UserDetails userDetails);

}
