package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.BusinessReasonAssembler;
import com.cognizant.opserv.sp.core.dao.TBussReasonDAO;
import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.opserv.sp.model.BusinessReason;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.BusinessReasonDAOService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


@Component
public class BusinessReasonDAOServiceImpl implements
		BusinessReasonDAOService {

	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(BusinessReasonDAOServiceImpl.class);
	
	@Autowired
	TBussReasonDAO tBussReasonDAO;
	
	@Autowired
	BusinessReasonAssembler businessReasonAssembler;
	
	@Override
	public List<BusinessReason> fetchBusinessReasons(Long algmntId,
			Long bussUnitId, Long salesTeamId, Integer trTypeId, Integer custCategoryId,
			UserDetails userDetails) throws OpservDataAccessException {
		
		try {
			
			List<Object[]> tBussReasonList = tBussReasonDAO.findTBussReasons(algmntId, bussUnitId, salesTeamId, trTypeId, 
					custCategoryId, userDetails.getTenantId());
			List<BusinessReason> businessReasonList = new ArrayList<BusinessReason>();
			
			if (null != tBussReasonList) {
				for (Object[] obj : tBussReasonList) {
					TBussReason bussReason = (TBussReason) obj[0];
					String chngType = obj[1].toString();
					Long chngReqBrId = (Long) obj[2];
					BusinessReason businessReason = businessReasonAssembler.mapEntityToBusinessReasonModel(bussReason, chngType, chngReqBrId);
					businessReasonList.add(businessReason);
				}
			}
			
		return businessReasonList;
			
		} catch(RuntimeException cause) {
			LOGGER.error(
					"Error occurred in BusinessReasonDAOServiceImpl method fetchBusinessReasons: "
							+ "algmntId: " + algmntId + ", bussUnitId: "
							+ bussUnitId + ", salesTeamId: " + salesTeamId
							+ ", trTypeId: " + trTypeId + ", custCategoryId: "
							+ custCategoryId, cause);
			throw new OpservDataAccessException("Error occured during fetch business reasons", cause);
		}
	
	}

}
