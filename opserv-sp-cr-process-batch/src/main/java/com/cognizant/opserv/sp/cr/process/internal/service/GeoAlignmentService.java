package com.cognizant.opserv.sp.cr.process.internal.service;

import java.util.Map;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;


/**
 * The Interface GeoAlignmentService.
 */
public interface GeoAlignmentService {

	
	/**
	 * Lock geography alignment.
	 *
	 * @param geoAlgmnt the geo algmnt
	 * @param userDetails the user details
	 * @return true, if successful
	 * @throws AlignmentServiceException the alignment service exception
	 */
	void lockGeographyAlignment(GeographyAlignment geoAlgmnt, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Un lock geography alignment on approve.
	 *
	 * @param geoAlgmnt the geo algmnt
	 * @param userDetails the user details
	 * @throws AlignmentServiceException 
	 */
	void unLockGeographyAlignmentOnApprove(GeographyAlignment geoAlgmnt, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Un lock geography alignment on reject.
	 *
	 * @param geoAlgmnt the geo algmnt
	 * @param userDetails the user details
	 */
	void unLockGeographyAlignmentOnReject(GeographyAlignment geoAlgmnt, UserDetails userDetails)throws AlignmentServiceException;
	
	
	 /**
 	 * Creates the target geo id.
 	 *
 	 * @param userDetails the user details
 	 * @return the integer
 	 */
 	Integer createTargetGeoID(UserDetails userDetails) throws AlignmentServiceException;
	
	 /**
 	 * Insert target zip algmnt.
 	 *
 	 * @param targetGeoAlign the target geo align
 	 * @param postalCd the postal cd
 	 * @param userDetails the user details
 	 * @param geoID the geo id
 	 * @return the integer
 	 * @throws AlignmentServiceException the alignment service exception
 	 */
 	Integer insertTargetZipAlgmnt(GeographyAlignment targetGeoAlign,PostalCode postalCd, UserDetails userDetails,Integer geoID) throws AlignmentServiceException;
	
	 /**
 	 * Fetch existing geo i ds.
 	 *
 	 * @param salesPosition the sales position
 	 * @param userDetails the user details
 	 * @return the map
	 * @throws AlignmentServiceException 
 	 */
 	Map<Long,Integer> fetchExistingGeoIDs(SalesPosition salesPosition, UserDetails userDetails) throws AlignmentServiceException;

	GeographyAlignment getGeographyAlgmnt(String postalCode,Integer geoZipId, long targetSPId,UserDetails userDetails) throws AlignmentServiceException;

	
}
