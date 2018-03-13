package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.List;

import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface ProductAlignmentService {
	
	List<ProductAlignment> getProductAlignments(SalesPosition salesPosition,UserDetails userDetails);

}
