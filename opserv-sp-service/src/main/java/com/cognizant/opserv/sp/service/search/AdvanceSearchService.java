package com.cognizant.opserv.sp.service.search;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.sp.exception.AdvanceSearchServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;


public interface AdvanceSearchService {

	AdvanceSearchContext getAdvanceSearchContext(String bussinessObjectName, UserDetails userDetails) throws AdvanceSearchServiceException;
}
