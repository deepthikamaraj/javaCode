package com.cognizant.opserv.sp.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TCustPrdSalesTeam;
import com.cognizant.opserv.sp.model.CustomerProductSalesTeam;

@Component
public class CustomerProductSalesTeamModelAssembler {
	
	@Autowired
	CustomerModelAssembler customerModelAssembler;
	
	@Autowired
	private ProductModelAssembler productModelAssembler;

	/**
	 * Gets the customerproducts of customer.
	 * 
	 * @method convertTCustPrdSalesToCustomerProduct
	 * @param tCustPrdList
	 *            the customerproduct list
	 * @return the customerproducts of customer
	 */
	public CustomerProductSalesTeam convertTCustPrdSalesTeamToCustomerProductSalesTeam(
			TCustPrdSalesTeam tCustPrdSalesTeam) {

		CustomerProductSalesTeam  customerProductSalesTeam = null;

		if (null != tCustPrdSalesTeam) {
				if (null != tCustPrdSalesTeam.getTCust() && null != tCustPrdSalesTeam.getTPrd()) {
					customerProductSalesTeam = new CustomerProductSalesTeam();
					customerProductSalesTeam.setCustomer(customerModelAssembler.convertTCustToModelWithBasicDetails(tCustPrdSalesTeam.getTCust()));
					customerProductSalesTeam.setProduct(productModelAssembler
							.convertTProdDtsToProdModel(tCustPrdSalesTeam.getTPrd()));
					customerProductSalesTeam
							.setCompEligible(tCustPrdSalesTeam.getCompElgFlag() != null && tCustPrdSalesTeam
									.getCompElgFlag().equals(
											CommonConstants.CHAR_YES) ? true
									: false);
					customerProductSalesTeam.setActive(tCustPrdSalesTeam
							.getActiveFlag() != null
							&& tCustPrdSalesTeam.getActiveFlag().equals(
									CommonConstants.CHAR_YES) ? true : false);
			}


		}
		return customerProductSalesTeam;
	}

}
