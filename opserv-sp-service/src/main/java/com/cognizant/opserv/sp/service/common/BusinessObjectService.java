package com.cognizant.opserv.sp.service.common;

import java.util.List;

import com.cognizant.opserv.sp.common.BusinessFunctionType;
import com.cognizant.opserv.sp.exception.CommonServiceException;
import com.cognizant.opserv.sp.model.BusinessObject;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface BusinessObjectService {
	
	List<BusinessObject> getBusinessObjectsByFunctionType(BusinessFunctionType type,UserDetails userDetails) throws CommonServiceException;

}
