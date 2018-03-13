package com.cognizant.opserv.sp.cr.status.service;

import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface CRService {
	
	void cancleCRStatus(short tenantId) throws OpservDataAccessException;
	
	void resendCRMsg(short tenantId) throws OpservDataAccessException;
	
	void deleteVoidCR(short tenantId) throws OpservDataAccessException;

}
