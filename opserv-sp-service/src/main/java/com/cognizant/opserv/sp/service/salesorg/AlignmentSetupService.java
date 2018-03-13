package com.cognizant.opserv.sp.service.salesorg;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

public interface AlignmentSetupService {
	
	Alignment createAlignment(Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	
	void updateAlignment(Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	
	void inactivateAlignment(Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;	
	
	Alignment getAlignmentDetails(long alignmentId,UserDetails userDetails) throws AlignmentServiceException;	
	
	List<Alignment> fetchAlignments(ISearchCriteria criteria,UserDetails userDetails) throws AlignmentServiceException;	

}
