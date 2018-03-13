package com.cognizant.opserv.sp.service.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.sp.exception.AdvanceSearchServiceException;
import com.cognizant.opserv.sp.exception.AdvanceSearchServiceException.AdvanceSearchServiceExceptionCode;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AdvanceSearchDAOService;
import com.cognizant.opserv.sp.service.search.AdvanceSearchService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("advanceSearchService")
public class AdvanceSearchServiceImpl implements AdvanceSearchService {

	@Autowired
	AdvanceSearchDAOService advanceSearchDAOService;
	
	@Override
	public AdvanceSearchContext getAdvanceSearchContext(String bussinessObjectName, UserDetails userDetails) throws AdvanceSearchServiceException {
		try {
			return advanceSearchDAOService.getAdvanceSearchContext(bussinessObjectName, userDetails);
		} catch(OpservDataAccessException de) {
			de.printStackTrace();
			Object[] args = {bussinessObjectName};
			throw new AdvanceSearchServiceException(AdvanceSearchServiceExceptionCode.ADV_SRCH_SER_EX_CD_001,"AdvanceSearchServiceImpl.getAdvanceSearchContext",args,de);
		}
		
	}

}
