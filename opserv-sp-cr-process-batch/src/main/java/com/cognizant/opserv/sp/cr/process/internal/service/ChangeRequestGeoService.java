package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;

public interface ChangeRequestGeoService {
	
	void processLineItemForAddOnSubmit(ZipAlignmentChangeRequestDetails crLineItem, UserDetails userDetails);
	
	void processLineItemForRemoveOnSubmit(ZipAlignmentChangeRequestDetails crLineItem, UserDetails userDetails);
	
	void updateCRandInitiateWorkflow(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails);

}
