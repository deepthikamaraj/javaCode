package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;

public interface AlignmentDAOService {

	public List<Alignment> fetchAlignments(ISearchCriteria criteria,
			UserDetails userDetails);

}
