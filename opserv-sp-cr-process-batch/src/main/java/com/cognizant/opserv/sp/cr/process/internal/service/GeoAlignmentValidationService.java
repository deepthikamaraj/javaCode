package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;

public interface GeoAlignmentValidationService {
	
	/**
	 * Validate Affiliated Customer Alignments
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AlignmentServiceException 
	 * @throws AffiliationServiceException 
	 */
	void validateAffiliatedGeoCustomerAlignment(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Validate Mirrors for the Customer Alignment
	 * @param custAlgmtDTO
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws SalesPositionServiceException
	 */
	void validateMirrorsForGeoAlignment(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException;
	

}
