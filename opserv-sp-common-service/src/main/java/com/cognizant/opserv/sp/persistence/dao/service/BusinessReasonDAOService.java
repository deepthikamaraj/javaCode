package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface BusinessReasonDAOService {
	
	/**
	 * Fetch Business Reason.
	 *
	 * @param algmntId, bussUnitId, salesTeamId
	 * @param trTypeId
	 * @param custCategoryId
	 * @param userDetails
	 * @return the list
	 * @throws OpservDataAccessException
	 */
	List<BusinessReason> fetchBusinessReasons(Long algmntId, Long bussUnitId, Long salesTeamId, Integer trTypeId, Integer custCategoryId, 
			UserDetails userDetails) throws OpservDataAccessException;

}
