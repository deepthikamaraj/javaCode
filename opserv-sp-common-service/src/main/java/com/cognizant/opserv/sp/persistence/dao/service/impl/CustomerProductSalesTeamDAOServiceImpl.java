package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.assembler.CustomerProductSalesTeamModelAssembler;
import com.cognizant.opserv.sp.core.dao.TCustPrdSalesTeamDAO;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerProductSalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerProductSalesTeamDAOService;

@Component
public class CustomerProductSalesTeamDAOServiceImpl implements CustomerProductSalesTeamDAOService{
	
	@Autowired
	private TCustPrdSalesTeamDAO tCustPrdSalesTeamDAO;
	
	@Autowired
	private CustomerProductSalesTeamModelAssembler customerProductSalesTeamModelAssembler;
	
	/**
	 * Gets the prd fr cust st.
	 *
	 * @param customerId the customer id
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return the prd fr cust st
	 */
	public List<CustomerProductSalesTeam> getPrdFrCustST(Long customerId, Alignment alignment,
			UserDetails userDetails) {
		List<TCustPrdSalesTeam> prdListByCustIdAlBuSt = tCustPrdSalesTeamDAO.getPrdListByCustIdAlBuSt(customerId.intValue(),
				alignment.getId(), alignment.getSalesTeam().getId(), alignment
						.getSalesTeam().getBusinessUnit().getId(), userDetails);
		
		List<CustomerProductSalesTeam> custPrdSTList = new ArrayList<CustomerProductSalesTeam>();
		if(prdListByCustIdAlBuSt != null && !prdListByCustIdAlBuSt.isEmpty()){
			
			for(TCustPrdSalesTeam tCustPrdSalesTeam : prdListByCustIdAlBuSt){
				CustomerProductSalesTeam customerProductSalesTeam = customerProductSalesTeamModelAssembler.convertTCustPrdSalesTeamToCustomerProductSalesTeam(tCustPrdSalesTeam);
				custPrdSTList.add(customerProductSalesTeam);
			}
		}
		return custPrdSTList;
	}

}
