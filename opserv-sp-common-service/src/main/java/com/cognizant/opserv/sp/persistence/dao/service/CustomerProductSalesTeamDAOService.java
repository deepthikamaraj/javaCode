package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerProductSalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerProductSalesTeamDAOService {

	public List<CustomerProductSalesTeam> getPrdFrCustST(Long customerId,
			Alignment alignment, UserDetails userDetails);
}
