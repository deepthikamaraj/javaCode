package com.cognizant.opserv.sp.service.impl.test.alignment;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.EntityType;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.alignment.TemplateAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext-servicetest.xml"})*/
public class TemplateAlignmentServiceImplTest extends BaseTest{
	
	
	@Autowired
	TemplateAlignmentService templateAlignmentService;
	
	@Test
	public void getEntityTemplateTest()
			throws AlignmentServiceException{
		
		BusinessUnit businessUnit= new BusinessUnit();
		businessUnit.setId((long) 2);
		
		SalesTeam salesTeam = new SalesTeam();
		salesTeam.setId((long) 2);
		salesTeam.setBusinessUnit(businessUnit);
		
		Alignment alignment = new Alignment();
		alignment.setId((long) 8);
		alignment.setSalesTeam(salesTeam);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		userDetails.setLocaleId("en_US");
		
		templateAlignmentService.getEntityTemplate(EntityType.CUSTOMER,alignment,userDetails);
	}
}
