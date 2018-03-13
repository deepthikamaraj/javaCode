package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.ProductAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.internal.ProductAlignmentIntService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class ProductAlignmentServiceImplTest extends BaseTest {
	
	private static final OpservLogger LOGGER= OpservLoggerFactory.getOpservLogger(ProductAlignmentServiceImplTest.class);
	@Autowired
	ProductAlignmentService productAlignmentService;
	
	@Autowired 
	ProductAlignmentIntService productAlignmentIntService;
	
	
	@Test
	public void getPrdAlign() throws AlignmentServiceException {
		
		//Start Prod alignment
		SalesPosition sp = new SalesPosition();
		Alignment  alignment = new Alignment();
		SalesTeam  salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		sp.setId(1l);
		alignment.setId(7l);
		salesTeam.setId(1l);
		businessUnit.setId(1l);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		sp.setAlignmment(alignment);
		sp.setActive(true);
		
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		List<ProductAlignment> list = productAlignmentService.getAllProductAlignmentsBySalesPosition(sp, userDts);
		if(null!=list && list.size()>0){
		for(ProductAlignment pa : list){
			LOGGER.debug(pa.getId().toString());
			LOGGER.debug(pa.getSalesPosition().getId().toString());
			LOGGER.debug(pa.getStartDate().toString());
			LOGGER.debug(pa.getEndDate().toString());
			
			Assert.assertNotNull(list);
			
			
		}
		}
	}
	
	@Test
	public void getProductCountForSalesPosition() throws AlignmentServiceException {
		
		//Start Prod alignment
		SalesPosition sp = new SalesPosition();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		Alignment  alignment = new Alignment();
		SalesTeam  salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		salesOrgHierarchy.setId(5L);
		sp.setId(590L);
		sp.setHierarchy(salesOrgHierarchy);
		alignment.setId(7L);
		salesTeam.setId(7L);
		businessUnit.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		sp.setAlignmment(alignment);
		
		
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		long prdCount = productAlignmentIntService.getProductCountForSalesPosition(sp, userDts);
		System.out.println("++++++++++++++++++++++++++++Product Count ++++" +prdCount);
	}
	
	@Test
	public void getProductNamesForSalesPosition() throws AlignmentServiceException {
		
		//Start Prod alignment
		SalesPosition sp = new SalesPosition();
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		Alignment  alignment = new Alignment();
		SalesTeam  salesTeam = new SalesTeam();
		BusinessUnit businessUnit = new BusinessUnit();
		salesOrgHierarchy.setId(5L);
		sp.setId(590L);
		sp.setHierarchy(salesOrgHierarchy);
		alignment.setId(7L);
		salesTeam.setId(7L);
		businessUnit.setId(2L);
		salesTeam.setBusinessUnit(businessUnit);
		alignment.setSalesTeam(salesTeam);
		sp.setAlignmment(alignment);
		
		
		UserDetails userDts = new UserDetails();
		userDts.setTenantId(Short.parseShort("1"));
		List<String> prdName = productAlignmentIntService.getProductNamesForSalesPosition(sp, userDts);
		System.out.println("++++++++++++++++++++++++++++Product Count ++++" +prdName.get(0));
		
		
	}

}
