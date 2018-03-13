package com.cognizant.opserv.sp.persistence.dao.service;

import java.util.List;

import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.GeoShapePolygon;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.peg.core.exception.OpservDataAccessException;

/**
 * ****************************************************************************.
 *
 * @class SalesPositionService contains all the DAO services for sales position  
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public interface SalesPositionDAOService {
	
	 /**
 	 * Gets the sales pos informations.
 	 *
 	 * @param salesPosId the sales pos id
 	 * @param tenantId the tenant id
 	 * @return the sales pos informations
 	 * @throws AlignmentServiceException the alignment service exception
 	 */
 	SalesPosition getSalesPosInformations(long salesPosId, UserDetails userDetail) throws OpservDataAccessException;
	 
 	/**
 	 * Fetch all sales postions by name.
 	 *
 	 * @param name the name
 	 * @param alignmt the alignmt
 	 * @param tenantId the tenant id
 	 * @return the list
 	 * @throws AlignmentServiceException the alignment service exception
 	 */
 	List<SalesPosition> fetchAllSalesPostionsByName(String name, Alignment alignmt, UserDetails userDetail) throws OpservDataAccessException;
	 
 	/**
 	 * Gets the imd child sales postion.
 	 *
 	 * @param salesPosId the sales pos id
 	 * @param tenantId the tenant id
 	 * @return the imd child sales postion
 	 * @throws AlignmentServiceException the alignment service exception
 	 */
 	List<SalesPosition> getImdChildSalesPostion(long salesPosId, UserDetails userDetail) throws OpservDataAccessException;
	 
 	/**
 	 * Fetcht all sales positions by alignment.
 	 *
 	 * @param alignment the alignment
 	 * @param tenantId the tenant id
 	 * @return the list
 	 * @throws AlignmentServiceException the alignment service exception
 	 */
 	List<SalesPosition> fetchtAllSalesPositionsByAlignment(Alignment alignment, UserDetails userDetails)throws OpservDataAccessException;

 	/**
	  * Gets the mirrored sales positions info.
	  *
	  * @param salesPosition the sales position
	  * @param userDetails the user details
	  * @return the mirrored sales positions info
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	 List<SalesPosition> getMirroredSalesPositionsInfo(SalesPosition salesPosition, UserDetails userDetails)throws OpservDataAccessException;
	 
	 /**
 	 * Gets the parent sales position.
 	 *
 	 * @param salesPosId the sales pos id
 	 * @param userDetail the user detail
 	 * @return the parent sales position
 	 * @throws OpservDataAccessException the opserv data access exception
 	 */
 	SalesPosition getParentSalesPosition(long salesPosId, UserDetails userDetail) throws OpservDataAccessException;
	
 	/**
	  * Gets the shape polygon by sales position.
	  *
	  * @param salesPositions the sales positions
	  * @param userDetails the user details
	  * @return the shape polygon by sales position
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	 List<GeoShapePolygon> getShapePolygonBySalesPosition(List<SalesPosition> salesPositions,UserDetails userDetails) throws OpservDataAccessException;
	/**
	 * Fetch sp names fr sp ids.
	 *
	 * @param spIdList the sp id list
	 * @param userDetails the user details
	 * @return the list
	 */
	List<String> fetchSpNamesFrSpIds(List<Long> spIdList,
			UserDetails userDetails) throws OpservDataAccessException;
 	
	/**
	 * Gets the mirror sales position basic data.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the mirror sales position basic data
	 * @throws OpservDataAccessException the opserv data access exception
	 */
	List<SalesPosition> getMirrorSalesPositionBasicData(
            SalesPosition salesPosition, UserDetails userDetails)
            throws OpservDataAccessException;
	
	 /**
 	 * Checks if is sales team mirrored.
 	 *
 	 * @param algmntId the algmnt id
 	 * @param bussUnitId the buss unit id
 	 * @param salesTeamId the sales team id
 	 * @param tenantId the tenant id
 	 * @return true, if is sales team mirrored
 	 */
 	boolean isSalesTeamMirrored(Long algmntId, Long bussUnitId,
			Long salesTeamId, Short tenantId)throws OpservDataAccessException;
 	
 	/**
 	 * Checks if is sales position is active
 	 * @param salesPosId the sales position id
 	 * @param tenantId  the tenant id
 	 * @return true, if is sales position is active
 	 */
 	boolean isSalesPosActive(Long salesPosId, Short tenantId)throws OpservDataAccessException;
 	
 	/**
	 * To get salespostion name and code for a person
	 * @param staffId
	 * @param tenantId
	 * @return
	 */
	List<Object[]> findSPNameAndCodeForEmp(Integer staffId, Short tenantId) throws OpservDataAccessException;
}
