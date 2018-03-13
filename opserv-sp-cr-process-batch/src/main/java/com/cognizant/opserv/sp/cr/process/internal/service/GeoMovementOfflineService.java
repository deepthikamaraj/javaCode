package com.cognizant.opserv.sp.cr.process.internal.service;

import com.cognizant.opserv.sp.cr.process.dto.GeographyAlignmentDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorCustAlgmtDTO;
import com.cognizant.opserv.sp.cr.process.dto.MirrorGeoAlgmtDTO;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;

/**
 * @author 472731
 * @interface GeoMovementOfflineService
 */
public interface GeoMovementOfflineService {
	
	/**
	 * @param offlineId
	 * @param userDetails
	 */
	void lockRequestForProcessing(Long offlineId, UserDetails userDetails)throws AlignmentServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws AlignmentServiceException 
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 */
	void validate(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws AlignmentServiceException, SalesPositionServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 */
	void lockSourceGeography(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails);
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 */
	void processSourceOnSubmit(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 * @throws AlignmentServiceException 
	 */
	void processTargetOnSubmit(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException, AlignmentServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 */
	boolean handleMetricsOnSubmit(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 */
	void updateChangeRequests(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 */
	void emitPostProcessEvents(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails);
	
	/**
	 * @param parentCR
	 * @param mirrorDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 */
	void createMirrorCR(ChangeRequest parentCR, MirrorGeoAlgmtDTO mirrorDTO, MirrorCustAlgmtDTO mirrorCustAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;

	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 */
	void assignApproversForSourceAndTarget(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails) throws ChangeRequestServiceException;
	
	/**
	 * @param geoAlgmtDTO
	 * @param userDetails
	 * @throws ChangeRequestServiceException
	 */
	void findMirrorCRsForGeoMovement(GeographyAlignmentDTO geoAlgmtDTO, UserDetails userDetails);
}
