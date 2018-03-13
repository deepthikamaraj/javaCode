package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface SalesPositionCommonService {

	List<SalesPosition> getMirroredSalesPosition(SalesPosition salesposition, UserDetails userDetails) throws SalesPositionServiceException;
}
