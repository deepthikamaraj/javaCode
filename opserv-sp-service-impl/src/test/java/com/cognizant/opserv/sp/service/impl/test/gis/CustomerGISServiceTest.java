package com.cognizant.opserv.sp.service.impl.test.gis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.gis.CustomerGISService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;

public class CustomerGISServiceTest extends BaseTest{
	
	/** The customer gis service. */
	@Autowired
	private CustomerGISService customerGISService;
	
	/**
	 * Gets the customers by criteria test.
	 *
	 * @return the customers by criteria test
	 * @throws AlignmentServiceException the alignment service exception
	 */
	@Test
	public void getCustomersByCriteriaTest() throws AlignmentServiceException{
		Alignment alignment = new Alignment();
		alignment.setId(7l);
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId(7l);
		BusinessUnit bussUnit = new BusinessUnit();
		bussUnit.setId(2l);
		salesTeam.setBusinessUnit(bussUnit);
		alignment.setSalesTeam(salesTeam);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<CustomerAlignment> custAlgmntList = null;
		
		List<Long> spIdList = new ArrayList<Long>();
		spIdList.add(396l);
		spIdList.add(397l);
		spIdList.add(398l);
		spIdList.add(394l);
		
		List<Long> shIdList = new ArrayList<Long>();
		shIdList.add(5l);
		shIdList.add(4l);
		
		IExpressionCriteria salesHierCriteria = ExpressionCriteria.createInCriteria("salesHierId", shIdList);
		IExpressionCriteria salesPosCriteria = ExpressionCriteria.createInCriteria("salesPosId", spIdList);
		salesHierCriteria.and(salesPosCriteria);
		IQuery query = new Query().criteria(salesHierCriteria);
		
//		IExpressionCriteria postalCdCriteria = ExpressionCriteria.createEqualToCriteria("postalCd", "04330");
//		IQuery query = new Query().criteria(postalCdCriteria);
		
//		IExpressionCriteria custNameCriteria = ExpressionCriteria.createStartsWithCriteria("custName", "LISA%");
//		IQuery query = new Query().criteria(custNameCriteria);
		
		Date before = new Date();
		custAlgmntList = customerGISService.getCustomers(query, alignment, userDetails);		
		Date after = new Date();
		double diff = after.getTime() - before.getTime();
		System.out.println("diff "+diff);
		if(null != custAlgmntList && !custAlgmntList.isEmpty()){
			System.out.println("size "+custAlgmntList.size());
			for(CustomerAlignment customerAlignment : custAlgmntList){
				System.out.println("custAlId "+customerAlignment.getId());
			}
		}
//		Assert.notEmpty(custAlgmntList);
	}

}
