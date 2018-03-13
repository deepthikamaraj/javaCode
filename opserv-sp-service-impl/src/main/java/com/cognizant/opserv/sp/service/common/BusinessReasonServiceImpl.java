package com.cognizant.opserv.sp.service.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CustomerCategoryType;
import com.cognizant.opserv.sp.exception.OpservUndefinedException;
import com.cognizant.opserv.sp.exception.OpservUndefinedException.OpservUndefinedExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessReasonDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Service("businessReasonService")
public class BusinessReasonServiceImpl implements BusinessReasonService {

	@Autowired
	private BusinessReasonDAOService businessReasonDAOService;
	
	@Override
	@Transactional(readOnly = true)
	public List<BusinessReason> fetchBusinessReasons(
			Alignment alignment, ChangeRequestTriggerType trType, CustomerCategoryType custCategory, UserDetails userDetails)
			throws OpservUndefinedException {
		
		try {
			
			if(null == alignment || null == alignment.getId() ||  null == alignment.getSalesTeam() ||  null == alignment.getSalesTeam().getId() || 
					null == alignment.getSalesTeam().getBusinessUnit() ||  null == alignment.getSalesTeam().getBusinessUnit().getId() || 
					null == trType || null == trType.getId() || null == custCategory || null == custCategory.getId() || null == userDetails || 
					null == userDetails.getTenantId()) {
				
				String params = "trType ="+ trType +", tenant_id = "+ userDetails.getTenantId();
				Object[] args = new Object[]{params};
				throw new  OpservUndefinedException(args);
			}
			Long algmntId = alignment.getId();
			Long bussUnitId = alignment.getSalesTeam().getBusinessUnit().getId();
			Long salesTeamId = alignment.getSalesTeam().getId();
			Integer trTypeId = trType.getId();
			Integer custCategoryId = custCategory.getId();
			
			return businessReasonDAOService.fetchBusinessReasons(algmntId, bussUnitId, salesTeamId, trTypeId, custCategoryId, userDetails);
			
		} catch(OpservDataAccessException dataAccessException) {
			
			Object[] args = new Object[]{trType};
			throw new OpservUndefinedException(OpservUndefinedExceptionCode.OPSERV_UNDFND_EX_CD_001, "FetchBRCodes_exception", args, dataAccessException);
		}
	}

}
