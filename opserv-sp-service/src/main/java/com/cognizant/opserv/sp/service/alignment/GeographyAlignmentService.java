package com.cognizant.opserv.sp.service.alignment;

import java.util.List;

import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
/**
 * ****************************************************************************.
 *
 * @class EmployeeAlignmentService contains all the services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface GeographyAlignmentService {
	
	/**
	 * This method is used to get all postal code by SPID and SHID
	 * @method getAllGeographyAlignments
	 * @param salesPosition
	 * @param userDetails
	 * @return List<GeoCustomerAlignment>
	 * @throws AlignmentServiceException
	 */
	List<GeoCustomerAlignment> getAllGeographyAlignments(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * This method is used to get SalesPosition Info for a postal code
	 * @method getAlignedSalesPositionInfoForZip
	 * @param postalCode
	 * @param userDetails
	 * @return SalesPosition
	 * @throws AlignmentServiceException
	 */
	SalesPosition getAlignedSalesPositionInfoForZip(PostalCode postalCode,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * This method is used to search postal code by postal code and alignment 
	 * @method getAllPostalCodes 
	 * @param code
	 * @param alignment
	 * @param userDetails
	 * @return List<PostalCode>
	 * @throws AlignmentServiceException
	 */
	List<PostalCode> getAllPostalCodes(String code,Alignment alignment,UserDetails userDetails) throws AlignmentServiceException;
	/**
	 * 
	 * @method assignPostalCodes
	 * @param salesPos
	 * @param geoAligns
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 */
	//void assignPostalCodes(SalesPosition salesPos,List<GeographyAlignment> geoAligns,UserDetails userDetails) throws AlignmentServiceException,MetricViolationException;
	/**
	 * 
	 * @method unAssignPostalCodes
	 * @param salesPos
	 * @param geoAligns
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 */
//	void unAssignPostalCodes(GeographyAlignment sourceGeoAlign,UserDetails userDetails) throws AlignmentServiceException,MetricViolationException;
	
		
	/**
	 * 
	 * @method	movePostalCodes
	 * @param sourceSalesPos
	 * @param targetSalesPos
	 * @param geoAligns
	 * @param custAligns
	 * @param action
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 */
	//long movePostalCodes(GeographyAlignment sourceGeoAlign,GeographyAlignment targetGeoAlign,List<CustomerAlignment> custAligns,String comment,UserDetails userDetails) throws AlignmentServiceException,MetricViolationException,CustomerServiceException, AffiliationServiceException, SalesPositionServiceException,ChangeRequestServiceException, CallPlanServiceException;
	@Deprecated
	long movePostalCodes(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException,ViewServiceException;
	
	void movePostalCodes(ChangeRequest changeRequest, GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException,ViewServiceException;
	
	/**
	 * Gets the postal codes by sales position.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the postal codes by sales position
	 * @throws AlignmentServiceException the alignment service exception
	 */
	List<PostalCode> getPostalCodesBySalesPosition(SalesPosition salesPosition,UserDetails userDetails) throws AlignmentServiceException;
}
