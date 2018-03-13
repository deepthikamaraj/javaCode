package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.cr.process.internal.service.ProductAlignmentService;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ProductAlignmentDAOService;

@Service
public class ProductAlignmentServiceImpl implements ProductAlignmentService {
	
	@Autowired
	private ProductAlignmentDAOService productAlignmentDAOService;

	@Override
	public List<ProductAlignment> getProductAlignments(
			SalesPosition salesPosition, UserDetails userDetails) {
		List<ProductAlignment> productAlignmentList = productAlignmentDAOService.getAllProductAlignmentsBySalesPosition(salesPosition, userDetails);
		return productAlignmentList;
	}

}
