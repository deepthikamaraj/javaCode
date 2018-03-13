package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.cr.process.dto.CustomerAlignmentDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface CustomerAlignmentValidationService {

	
	/**
	 * Validate Affiliated Customer Alignments -This method will populate the
	 * Source Base Affiliated Customers and set on the customer Alignment DTO
	 * 
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AffiliationServiceException 
	 * @throws AlignmentServiceException 
	 */
	void validateAffiliatedCustomerAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AffiliationServiceException, AlignmentServiceException;
	
	/**
	 * Validate Mirrors for the Customer Alignment Based on the affliated
	 * customers obtained in the customer AlignmentDTO, finding mirrors for
	 * Source and Target. For those source mirror SPs, validating which
	 * customers (base + affiliated customers) can be used.
	 * 
	 * This method will create the mirror DTO based on the customer alignment
	 * DTO
	 * 
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws SalesPositionServiceException
	 */
	void validateMirrorsForCustomerAlignment(CustomerAlignmentDTO custAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException;
	
}
