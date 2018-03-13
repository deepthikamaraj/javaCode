package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.exception.OpservDataAccessException;
/**
 * ****************************************************************************.
 *
 * @class AlignmentSetupDAOService contains all the DAO Alignment setup services 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 09/06/2016
 * ***************************************************************************
 */
public interface AlignmentSetupDAOService {
	
   /**
    * Create Alignment service
    * 	
    * @param alignment
    * @param userDetails
    * @return
    * @throws OpservDataAccessException
    */
   Alignment createAlignment(Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Update Alignment Service
	 * 
	 * @param alignment
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void updateAlignment(Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Inactivate Alignment
	 * 
	 * @param alignment
	 * @param userDetails
	 * @throws OpservDataAccessException
	 */
	void inactiveAlignment(Alignment alignment,UserDetails userDetails) throws OpservDataAccessException;	
	/**
	 * 
	 * @param alignmentId
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	Alignment getAlignmentDetails(long alignmentId,UserDetails userDetails) throws OpservDataAccessException;	
	/**
	 * get the Alignment details
	 * 
	 * @param criteria
	 * @param userDetails
	 * @return
	 * @throws OpservDataAccessException
	 */
	List<Alignment> fetchAlignments(ISearchCriteria criteria,UserDetails userDetails) throws OpservDataAccessException;	

	
	

}
