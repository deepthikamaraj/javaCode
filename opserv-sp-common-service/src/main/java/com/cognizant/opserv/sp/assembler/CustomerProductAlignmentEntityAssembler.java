package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustPrdAlgmntId;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;

@Component
public class CustomerProductAlignmentEntityAssembler {
	


	public  TCustPrdAlgmnt converCustProdAlgModelToTCustPrdAlg(
			CustomerProductAlignment custPrdAlgmnt, Long custAlgmntId, Long prdAlgmntId) {

		TCustPrdAlgmnt tCustPrdAlgmnt = new TCustPrdAlgmnt();
		if (null != custPrdAlgmnt) {
			
			TCustPrdAlgmntId tCustPrdAlgmntId = new TCustPrdAlgmntId();
			
			tCustPrdAlgmnt.setActiveFlag('Y');
			tCustPrdAlgmnt.setTenantId(custPrdAlgmnt.getTenantId());
			tCustPrdAlgmnt.setAllocPct(custPrdAlgmnt.getAllocationPercentage());
			tCustPrdAlgmnt.setCreatedBy(custPrdAlgmnt.getCreatedBy());
			tCustPrdAlgmnt.setCreateDt(custPrdAlgmnt.getCreatedDate());
			tCustPrdAlgmntId.setCustAlgmntId(custAlgmntId);
			tCustPrdAlgmntId.setPrdAlgmntId(prdAlgmntId);
			tCustPrdAlgmnt.setTCustPrdAlgmntId(tCustPrdAlgmntId);
			tCustPrdAlgmnt.setCustId((Integer)custPrdAlgmnt.getCustomer().getId().intValue());
			tCustPrdAlgmnt.setPrdId(custPrdAlgmnt.getProduct().getId());
			tCustPrdAlgmnt.setAllocPct(custPrdAlgmnt.getAllocationPercentage());
			tCustPrdAlgmnt.setUpdatedBy(custPrdAlgmnt.getUpdatedBy());
			tCustPrdAlgmnt.setUpdateDt(custPrdAlgmnt.getUpdatedDate());
			//to be asked
			tCustPrdAlgmnt.setEffStartDt(custPrdAlgmnt.getStartDate());
			tCustPrdAlgmnt.setEffEndDt(custPrdAlgmnt.getEndDate());

	}
		return tCustPrdAlgmnt;
	}

}
