package com.cognizant.opserv.sp.service.common.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException.SalesPositionServiceExceptionCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SalesPositionDAOService;
import com.cognizant.opserv.sp.service.common.SalesPositionCommonService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@Service
public class SalesPositionCommonServiceImpl implements
		SalesPositionCommonService {
	
	@Autowired
	private SalesPositionDAOService salesPositionDAOService;
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(SalesPositionCommonServiceImpl.class);

	@Override
	public List<SalesPosition> getMirroredSalesPosition(SalesPosition salesposition, UserDetails userDetails) throws SalesPositionServiceException {
		
		List<SalesPosition> mirSPList = null;
		
		try {
				mirSPList = salesPositionDAOService.getMirrorSalesPositionBasicData(salesposition,userDetails);
		} catch (OpservDataAccessException e) {
			LOGGER.error("Error in fetching Mirrored SalesPosition : " + e.getMessage(), e);
			Object[] args  ={"SalesPosition Id" + salesposition.getId()};
			throw new SalesPositionServiceException(SalesPositionServiceExceptionCode.SP_SER_EX_CD_006, "Error in fetching Mirrored SalesPosition", args, e);
		}
		return mirSPList;
	}

}
