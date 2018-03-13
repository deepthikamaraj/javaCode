package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @class SalesPositionService contains all the services for sales position  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface SalesPositionService {
	/**
	 * 
	 * @method getAllSalesPositionsByAlignment
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<SalesPosition> getAllSalesPositionsByAlignment(Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * 
	 * @method getAllChildSalesPositions
	 * @param salesPosId
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<SalesPosition> getAllChildSalesPositions(long salesPosId,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * 
	 * @method getSalesPositionInformation
	 * @param salesPosId
	 * @param userDetail
	 * @return
	 * @throws AlignmentServiceException
	 */
	SalesPosition getSalesPositionInformation(long salesPosId,UserDetails userDetail)throws AlignmentServiceException;
	/**
	 * 
	 * @method getAllSalesPositionsByName
	 * @param name
	 * @param alignment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	List<SalesPosition> getAllSalesPositionsByName(String name,Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;

	/**
	 * Gets the mirrored sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirrored sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<SalesPosition> getMirroredSalesPositions(SalesPosition salesPosition, UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Gets the parent sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the parent sales position
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	public SalesPosition getParentSalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException;
	
	/**
	 * Gets the shape polygon by sales position.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the shape polygon by sales position
	 * @throws SalesPositionServiceException the sales position service exception
	 */
	List<GeoShapePolygon> getShapePolygonBySalesPosition(List<SalesPosition> salesPositions, UserDetails userDetails) throws SalesPositionServiceException;

	/**
	 * Checks if is sales position transient.
	 *
	 * @param spId the sp id
	 * @param userDetails the user details
	 * @return true, if is sales position transient
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws ChangeRequestServiceException the change request service exception
	 */
	boolean isSalesPositionTransient(Long spId, UserDetails userDetails) throws AlignmentServiceException, ChangeRequestServiceException; 
	
	/**
	 * Gets the mirrored sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirrored sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<SalesPosition> getMirrorSalesPositionBasicData(SalesPosition salesPosition, UserDetails userDetails) throws AlignmentServiceException;
}
