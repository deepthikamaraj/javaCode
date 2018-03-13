package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerProductAlignmentIntService{

	List<CustomerProductAlignment> updateCustProdAlgnmt(
			CustomerAlignment customerAlignment, UserDetails userDetails)
			throws AlignmentServiceException;
	
	List<CustomerProductAlignment> updateAllocPerc(List<CustomerProductAlignment> custPrdAlign ,UserDetails userDetails) throws AlignmentServiceException;
	
	List<CustomerProductAlignment> insertCustomerProductAlignment(List<CustomerProductAlignment> customerProdAligmnt, UserDetails userDetails) throws AlignmentServiceException;

}
