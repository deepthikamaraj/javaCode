package com.cognizant.opserv.sp.assembler;

import com.cognizant.opserv.sp.core.entity.TOrg;
import com.cognizant.opserv.sp.model.SalesOrg;

public class SalesOrgModelAssembler {
	
	public static SalesOrg convertTOrgToSalesOrgModel(TOrg tOrg) {
		SalesOrg salesOrg =new SalesOrg();
		
		salesOrg.setId(tOrg.getOrgId().longValue());
		salesOrg.setName(tOrg.getOrgName());
		
	
	return salesOrg;
	}

}
