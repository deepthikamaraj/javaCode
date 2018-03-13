package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.exception.OpservUndefinedException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface BusinessReasonService {
	
	/**
	 * Fetch Business Reason.
	 *
	 * @param alignment
	 * @param trType
	 * @param custCategory
	 * @param userDetails
	 * @return the list
	 * @throws OpservUndefinedException
	 */
	List<BusinessReason> fetchBusinessReasons(Alignment alignment, ChangeRequestTriggerType trType, CustomerCategoryType custCategory, 
			UserDetails userDetails) throws OpservUndefinedException;

}
