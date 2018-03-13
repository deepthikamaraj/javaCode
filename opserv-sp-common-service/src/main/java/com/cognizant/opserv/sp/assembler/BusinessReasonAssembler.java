package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TBussReason;
import com.cognizant.opserv.sp.model.BusinessReason;

@Component
public class BusinessReasonAssembler {

	/**
	 * mapEntityToBusinessReasonModel.
	 *
	 * @param tBussReason
	 * @return BusinessReason
	 */
	public BusinessReason mapEntityToBusinessReasonModel(TBussReason tBussReason, String chngType, Long chngReqBrId) {
		
		BusinessReason businessReason = new BusinessReason();
		
		businessReason.setId(chngReqBrId);
		businessReason.setBusinessReasonId(tBussReason.getBussReasonId());
		businessReason.setReason(tBussReason.getReason());
		businessReason.setCode(tBussReason.getReasonCd());
		businessReason.setDescription(tBussReason.getReasonDesc());
		
		boolean flag = false;
		Character ch = 'Y';
		if(ch.equals(tBussReason.getActiveFlag()))
		{
			flag = true;
		}
		businessReason.setActive(flag);
		businessReason.setCreatedBy(tBussReason.getCreatedBy());
		businessReason.setCreatedDate(tBussReason.getCreateDt());
		businessReason.setUpdatedBy(tBussReason.getUpdatedBy());
		businessReason.setUpdatedDate(tBussReason.getUpdateDt());
		businessReason.setTenantId(tBussReason.getTenantId());
		businessReason.setChngType(chngType);
		
		return businessReason;
	}

}
