package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface AdvanceSearchDAOService {
	
	AdvanceSearchContext getAdvanceSearchContext(String bussinessObjectName, UserDetails userDetails) throws OpservDataAccessException;
}
