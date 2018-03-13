package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.DashboardServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class GeographyAlignmentDAOService contains all the Dao services for employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface GeographyAlignmentDAOService {

	/**
	 * This method is used to get all postal code by SPID and SHID
	 * @method getAllGeographyAlignments
	 * @param salesPosition
	 * @param userDetails
	 * @return List<GeoCustomerAlignment>
	 * @throws OpservDataAccessException
	 */
	List<GeoCustomerAlignment> getAllGeographyAlignments(SalesPosition salesPosition,UserDetails userDetails) throws  OpservDataAccessException;
	
	/**
	 * This method is used to search postal code by postal code and alignment 
	 * @method getAllPostalCodes 
	 * @param code
	 * @param alignment
	 * @param userDetails
	 * @return List<PostalCode>
	 * @throws OpservDataAccessException
	 */
	List<PostalCode> getAllPostalCodes(String code,Alignment alignment,UserDetails userDetails) throws  OpservDataAccessException;

	/**
	 * This method is used to get SalesPosition Info for a postal code
	 * @method getAlignedSalesPositionInfoForZip
	 * @param postalCode
	 * @param userDetails
	 * @return SalesPosition
	 * @throws OpservDataAccessException
	 */
	SalesPosition getAlignedSalesPositionInfoForZip(PostalCode postalCode,UserDetails userDetails) throws  OpservDataAccessException;
	
    /**
     * getSalesPositionById
     * 
     * @param salesPosId
     * @param tenantId
     * @return SalesPosition
     */
    SalesPosition getSalesPositionById(Long salesPosId, Short tenantId);
    
   // Integer deactivateZipAlgmnt(SalesPosition salesPosition,PostalCode postalCd, UserDetails userDetails);
    Integer insertUpdateZipAlgmnt(GeographyAlignment targetGeoAlign,PostalCode postalCd, UserDetails userDetails,Integer geoID) throws AlignmentServiceException;
    Map<Long,Integer> getExistingGeoIDs(SalesPosition salesPosition,UserDetails userDetails);
    List<SalesPosition> findMirroredSPsWithZip(List<SalesPosition> spList,PostalCode pc,UserDetails userDetails);
    Integer getNewGeoID(UserDetails userDetails);
   // Integer updateEndDateForZipAlgmnt(SalesPosition salesPosition,PostalCode postalCd, UserDetails userDetails,Integer geoId,Date endDate);
    
  //  boolean validateDates(SalesPosition sourceSalesPosition,GeographyAlignment targetGeoAlign,PostalCode postalCd,UserDetails userDetails);
	
		/**
	 * Gets the zip count.
	 *
	 * @param salesPos the sales pos
	 * @param userDetails the user details
	 * @return the zip count
	 */
	Long getZipCount(SalesPosition salesPosition, UserDetails userDetails);

	Integer deactivateZipAlgmntOffline(SalesPosition salesPosition,
			PostalCode postalCd, StatusType status, char flag,UserDetails userDetails) throws OpservDataAccessException;
	
	
	/**
	  * Gets the postal codes by sales position.
	  *
	  * @param salesPosition the sales position
	  * @param userDetails the user details
	  * @return the postal codes by sales position
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	 List<PostalCode> getPostalCodesBySalesPosition(SalesPosition salesPosition, UserDetails userDetails) throws OpservDataAccessException;


	TChngreqOffline saveGeoAlgmntIntoTempChngReq(ChangeRequest chngRequest,
			GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails);

	/**
	 * Gets the zip count.
	 *
	 * @param salesPositions the sales positions
	 * @param userDetails the user details
	 * @return the zip count
	 * @throws DashboardServiceException 
	 */
	Long getZipCountForSpList(List<SalesPosition> salesPositions, UserDetails userDetails) throws DashboardServiceException;
	
	/**
	 * This method is used to get SalesPosition Info for a postal code
	 * @method getSPDataFrPostalCd
	 * @param postalCode
	 * @param spId
     * @param shId
	 * @param userDetails
	 * @return boolean
	 * @throws OpservDataAccessException
	 */
	boolean getSPDataFrPostalCd(String postalCode, Long spId, Long shId,
			UserDetails userDetails) throws OpservDataAccessException;
	void deactivateZipAlgmntOffline(GeographyAlignment geoAlgmnt,
			StatusType status, char flag, UserDetails userDetails)
			throws OpservDataAccessException;
	
	List<SalesPosition> filterActiveSPsContaingZip(
			List<SalesPosition> salesPosList, String postalCode,
			UserDetails userDetails)throws OpservDataAccessException;
	
	Integer getCustomerCategory(Integer custID, UserDetails userDetails)throws OpservDataAccessException;

	boolean updateZipAlgmntStatus(TTerrZipAlgmntChngReqDet zipChangeRequest,
			Integer aprStsId, UserDetails userDetails)throws OpservDataAccessException;

	Integer updateSrcZipAlgmntStatus(TTerrZipAlgmntChngReqDet zipChangeRequest,
			Integer rejctStsId, UserDetails userDetails)throws OpservDataAccessException;
	Integer newTargetGeoID(UserDetails userDetails)throws OpservDataAccessException;
		
	 Integer insertTTerrZipAlgmnt(GeographyAlignment targetGeoAlign,PostalCode postalCd, UserDetails userDetails,Integer geoID) throws OpservDataAccessException;

	GeographyAlignment getSourceMirrorGeographyAlignment(SalesPosition sourceSP, String code,  Short tenantId)throws OpservDataAccessException;

	GeographyAlignment getGeoAlignment(String postalCode,
			Integer geoZipId, long targetSPId, Short tenantId) throws OpservDataAccessException;

	void updateTTerrZipAlgmntStatus(Long salesPosId, String postalCd,
			Long salesHierId, Integer geoId, Integer aprStsId, char flag,
			UserDetails userDetails) throws OpservDataAccessException;;

}
