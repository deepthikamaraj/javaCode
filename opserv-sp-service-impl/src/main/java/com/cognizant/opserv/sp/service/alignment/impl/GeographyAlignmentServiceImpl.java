package com.cognizant.opserv.sp.service.alignment.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.event.messages.OfflineRequestMessage;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.messaging.GenericPublisher;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.service.alignment.CustomerAlignmentService;
import com.cognizant.opserv.sp.service.alignment.GeographyAlignmentService;
import com.cognizant.opserv.sp.service.alignment.SalesPositionService;
import com.cognizant.opserv.sp.service.internal.CustomerAlignmnetIntService;
import com.cognizant.opserv.sp.service.internal.GeoAlgmntInternalService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class EmployeeAlignmentServiceImpl contains all the services for employee
 *        alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Service("geographyAlignmentService")
public class GeographyAlignmentServiceImpl implements GeographyAlignmentService {

	/** The geography alignment dao service. */
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeographyAlignmentServiceImpl.class);

	/** The customer alignment service. */
	@Autowired
	private CustomerAlignmentService customerAlignmentService;

	/** The sales position service. */
	@Autowired
	private SalesPositionService salesPositionService;

	/** The customer alignmnet int service. */
	@Autowired
	private CustomerAlignmnetIntService customerAlignmnetIntService;

	/** The geo algmnt internal service. */
	@Autowired
	private GeoAlgmntInternalService geoAlgmntInternalService;
	
	@Autowired
	private GenericPublisher genericPublisher;

	/**
	 * This method is used to get all postal code by SPID and SHID.
	 *
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return List<GeoCustomerAlignment>
	 * @throws AlignmentServiceException the alignment service exception
	 * @method getAllGeographyAlignments
	 */
	@Transactional
	@Override
	public List<GeoCustomerAlignment> getAllGeographyAlignments(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		List<GeoCustomerAlignment> geoCustomerAlignment = new ArrayList<GeoCustomerAlignment>();
		try {

			if (null != salesPosition && null != salesPosition.getId()
					&& salesPosition.getId() != 0
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getHierarchy().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				geoCustomerAlignment = geographyAlignmentDAOService
						.getAllGeographyAlignments(salesPosition, userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "SalesPosition Data is null" };
				throw new AlignmentServiceException(args);
			}

		} catch (OpservDataAccessException e) {
			LOGGER.info("*****************ISSUE IS OCCURRED WHILE FETCHING GEOGRAPHY ALIGNMENTS FOR A SALES POSITION*****************");
			Object[] args = new Object[1];
			args[0] = salesPosition.getId();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_100,
					"This issue is occurred while Fetching Geography Alignments for a Sales Position",
					args, e);
		}
		return geoCustomerAlignment;

	}

	/**
	 * This method is used to get SalesPosition Info for a postal code.
	 *
	 * @param postalCode the postal code
	 * @param userDetails the user details
	 * @return SalesPosition
	 * @throws AlignmentServiceException the alignment service exception
	 * @method getAlignedSalesPositionInfoForZip
	 */
	@Transactional
	@Override
	public SalesPosition getAlignedSalesPositionInfoForZip(
			PostalCode postalCode, UserDetails userDetails)
			throws AlignmentServiceException {
		SalesPosition salesPosition = new SalesPosition();

		try {
			if (postalCode != null && postalCode.getCode() != null
					&& userDetails != null && userDetails.getTenantId() != 0) {
				salesPosition = geographyAlignmentDAOService
						.getAlignedSalesPositionInfoForZip(postalCode,
								userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER POSTALCODE OR USER DETAILS IS NULL*****************");
				Object[] args = { "PostalCode is null or tenantId is 0" };
				throw new AlignmentServiceException(args);
			}
		} catch (OpservDataAccessException e) {
			LOGGER.info("*****************ISSUE IS OCCURRED WHILE FETCHING POSTALCODES FOR ALIGNED SALESPOSITION*****************");
			Object[] args = new Object[1];
			args[0] = postalCode.getCode();
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_102,
					"This issue is occurred while Fetching PostalCodes for Aligned SalesPosition",
					args, e);

		}
		return salesPosition;

	}

	/**
	 * This method is used to search postal code by postal code and alignment.
	 *
	 * @param code the code
	 * @param alignment the alignment
	 * @param userDetails the user details
	 * @return List<PostalCode>
	 * @throws AlignmentServiceException the alignment service exception
	 * @method getAllPostalCodes
	 */
	@Transactional
	@Override
	public List<PostalCode> getAllPostalCodes(String code, Alignment alignment,
			UserDetails userDetails) throws AlignmentServiceException {
		List<PostalCode> codes = new ArrayList<PostalCode>();
		try {
			if (code != null
					&& alignment != null
					&& alignment.getId() != null
					&& alignment.getSalesTeam().getId() != null
					&& alignment.getSalesTeam().getBusinessUnit().getId() != null
					&& userDetails != null && userDetails.getTenantId() != null) {
				codes = geographyAlignmentDAOService.getAllPostalCodes(code,
						alignment, userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER CODE OR ALIGNMENT DETAILS OR USER DETAILS IS NULL*****************");
				Object[] args = { "Code is null or Alignment Data is null" };
				throw new AlignmentServiceException(args);
			}
		} catch (OpservDataAccessException e) {
			LOGGER.info("***************** ISSUE OCCURED WHILE FETCHING POSTAL CODES*****************");
			Object[] args = new Object[] { code };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_101,
					"This Issue occured while Fetching Postal Codes", args, e);
		}
		return codes;

	}

	/**
	 * Assign postal codes.
	 *
	 * @param salesPos the sales pos
	 * @param geoAligns the geo aligns
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws MetricViolationException the metric violation exception
	 * @method assignPostalCodes
	 */
	/*@Override
	public void assignPostalCodes(SalesPosition salesPos,
			List<GeographyAlignment> geoAligns, UserDetails userDetails)
			throws AlignmentServiceException, MetricViolationException {
		// TODO Auto-generated method stub

	}*/

	/**
	 * Un assign postal codes.
	 *
	 * @param sourceGeoAlign the source geo align
	 * @param userDetails the user details
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws MetricViolationException the metric violation exception
	 * @method unAssignPostalCodes
	 */
	/*@Override
	public void unAssignPostalCodes(GeographyAlignment sourceGeoAlign,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException {
		// TODO Auto-generated method stub

	}*/
	
	/**
	 * Move postal codes.
	 *
	 * @param sourceGeoAlign the source geo align
	 * @param targetGeoAlign the target geo align
	 * @param custAligns the cust aligns
	 * @param comment the comment
	 * @param userDetails the user details
	 * @return the long
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws MetricViolationException the metric violation exception
	 * @throws CustomerServiceException the customer service exception
	 * @throws AffiliationServiceException the affiliation service exception
	 * @throws SalesPositionServiceException the sales position service exception
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws CallPlanServiceException the call plan service exception
	 * @method movePostalCodes
	 */

	@Override
	@Deprecated
	public long movePostalCodes(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException {
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ INSIDE GeographyAlignmentServiceImpl.movePostalCodes() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		// TODO Auto-generated method stub

		// Things to do
		// ZipMovementFlag Check -- not required... instead check if both the
		// sp's are at the same alignment
		// Assign the postal codes to the new sp
		// Remove the postal code from old sp
		// Mirroring Logic
		// Push customer to the new sp
		// Pull customer from the old sp
		// create CR

		LOGGER.info("Source GeographyAlignment Json : "
				+ JSONUtil.toJSON(sourceGeoAlign));
		LOGGER.info("Target GeographyAlignment Json : "
				+ JSONUtil.toJSON(targetGeoAlign));
		LOGGER.info("custAligns Json : " + JSONUtil.toJSON(custAligns));
		LOGGER.info("UserDetails Json : " + JSONUtil.toJSON(userDetails));
		SalesPosition sourceSalesPos = sourceGeoAlign.getSalesPosition();
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
		if (null == comment || "".equals(comment.trim())) {
			comment = "N/A";
		}
		Alignment srcAlignment = sourceSalesPos.getAlignmment();
		Alignment trgAlignment = targetSalesPos.getAlignmment();

		if (sourceGeoAlign == null
				|| sourceSalesPos == null
				|| null == srcAlignment
				|| null == srcAlignment.getId()
				|| null == srcAlignment.getSalesTeam().getBusinessUnit()
						.getId() || null == srcAlignment.getSalesTeam().getId()
				|| null == userDetails || null == userDetails.getTenantId()) {
			// String params = "AlignmentId ="+
			// String.valueOf(srcAlignment.getId()) +", Buss_unit_id = "+
			// String.valueOf(srcAlignment.getSalesTeam().getBusinessUnit().getId())
			// +" ,Sales_team_id = "+String.valueOf(srcAlignment.getSalesTeam().getId())
			// +" ,tenant_id = " +String.valueOf(userDetails.getTenantId());
			LOGGER.info("Mandatory fields missing :  sourceGeoAlign, sourceSalesPos, srcAlignment, srcAlignment.getId(), srcAlignment.getSalesTeam().getBusinessUnit().getId(), "
					+ "srcAlignment.getSalesTeam().getId(), userDetails, userDetails.getTenantId()  ");
			Object[] args = new Object[] { "SourceAlignmentId",
					"src_Buss_unit_id", "src_Sales_team_id", "tenant_id" };
			throw new AlignmentServiceException(args);
		}

		if (targetGeoAlign == null
				|| targetSalesPos == null
				|| null == trgAlignment
				|| null == trgAlignment.getId()
				|| null == trgAlignment.getSalesTeam().getBusinessUnit()
						.getId() || null == trgAlignment.getSalesTeam().getId()) {
			LOGGER.info("Mandatory fields missing :  targetGeoAlign, targetSalesPos ,trgAlignment,trgAlignment.getId() , trgAlignment.getSalesTeam().getBusinessUnit().getId() ,trgAlignment.getSalesTeam().getId()");
			// String params = "AlignmentId ="+
			// String.valueOf(trgAlignment.getId()) +", Buss_unit_id = "+
			// String.valueOf(trgAlignment.getSalesTeam().getBusinessUnit().getId())
			// +" ,Sales_team_id = "+String.valueOf(trgAlignment.getSalesTeam().getId());
			Object[] args = new Object[] { "TargetAlignmentId",
					"target_Buss_unit_id", "trgt_Sales_team_id", "tenant_id" };
			throw new AlignmentServiceException(args);
		}

		if (targetGeoAlign == null || targetGeoAlign.getStartDate() == null
				|| targetGeoAlign.getEndDate() == null) {
			LOGGER.info(" VALIDATION FAILED:::   targetGeoAlign Start  or End Date is null");

			Object[] args = new Object[] { "targetGeoAlign Start  or End Date is null" };
			LOGGER.info("targetGeoAlign Start  or End Date is null");
			throw new AlignmentServiceException(args);
		}

		PostalCode srcPostalCode = sourceGeoAlign.getPostalCode();

		PostalCode trgPostalCode = targetGeoAlign.getPostalCode();

		if (srcPostalCode == null || srcPostalCode.getCode() == null
				|| 0L == srcPostalCode.getGeoCode()) {
			LOGGER.info(" VALIDATION FAILED:::   SOURCE Postal code is null");

			Object[] args = new Object[] { "SOURCE Postal code is null" };
			LOGGER.info("SOURCE Postal code is null");
			throw new AlignmentServiceException(args);
		}

		if (trgPostalCode == null || trgPostalCode.getCode() == null) {
			LOGGER.info(" VALIDATION FAILED:::   TARGET Postal code is null");

			Object[] args = new Object[] { "TARGET Postal code is null" };
			LOGGER.info("TARGET Postal code is null");
			throw new AlignmentServiceException(args);
		}

		if (!this.verifySPAlignment(sourceSalesPos, targetSalesPos)) {
			LOGGER.info("VALIDATION FAILED::: Source and target belong to different Alignment BusinessUnit and Sales team");

			Object[] args = new Object[] { "" };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_312,
					"Source and target belong to different Alignment BusinessUnit and Sales team",
					args, null);
		}

		if (!this.verifySalesPosition(sourceSalesPos, targetSalesPos)) {
			LOGGER.info("VALIDATION FAILED::: The source and target sales Position are same");

			Object[] args = new Object[] { "" };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_313,
					"The source and target sales Position are same", args, null);
		}

		/*
		 * if(!this.validateEffectiveDates(sourceGeoAlign,targetGeoAlign,userDetails
		 * )){ Object[] args = new Object[] { "" }; throw new
		 * AlignmentServiceException(
		 * AlignmentServiceExceptionCode.ALGN_SER_EX_CD_101,
		 * "The effective start dates are not valid", args, null); }
		 */

		// assigning the pointZipFlag and CountryId in targetGeoAlign
		LOGGER.info("CountryCode : " + sourceGeoAlign.getCountryCode());
		targetGeoAlign.setCountryCode(sourceGeoAlign.getCountryCode());
		LOGGER.info("PointZipFlag : " + sourceGeoAlign.isPointZipFlag());
		targetGeoAlign.setPointZipFlag(sourceGeoAlign.isPointZipFlag());

		LOGGER.info("*****************INPUT PARAMETERS FOR ZIP MOVEMENT*****************");
		StringBuffer info = new StringBuffer();
		info.append("Source Details::PostalCode -> "
				+ sourceGeoAlign.getPostalCode().getCode() + ",GeoID-> "
				+ sourceGeoAlign.getPostalCode().getGeoCode());
		info.append("SalesPosition ->"
				+ sourceGeoAlign.getSalesPosition().getId() + ",Hier ID ->"
				+ sourceGeoAlign.getSalesPosition().getHierarchy().getId());
		info.append("Target Details::PostalCode -> "
				+ targetGeoAlign.getPostalCode().getCode() + ",GeoID-> "
				+ targetGeoAlign.getPostalCode().getGeoCode());
		info.append("SalesPosition ->"
				+ targetGeoAlign.getSalesPosition().getId() + ",Hier ID ->"
				+ targetGeoAlign.getSalesPosition().getHierarchy().getId());
		LOGGER.info(info.toString());
		LOGGER.info("*****************INPUT PARAMETERS FOR ZIP MOVEMENT*****************");

		LOGGER.info("*****************BEFORE CALLING geoAlgmntInternalService.doZipMovementOffline()*****************");
		TChngreqOffline tChngreqOffline = geoAlgmntInternalService
				.doZipMovementOnline(sourceGeoAlign, targetGeoAlign, custAligns, comment, userDetails);
		LOGGER.debug(
				"*****************AFTER CALLING geoAlgmntInternalService.doZipMovementOffline()***************** ::",
				tChngreqOffline);
		// Code for Metrics execution

		// geoAlgmntInternalService.metricsUpdateForZip(sourceSalesPos,
		// targetSalesPos, userDetails);

		// Publishing to queue
		LOGGER.info("*****************BEFORE CALLING geoAlgmntInternalService.publishToQueue()*****************");
		geoAlgmntInternalService.publishToQueue(tChngreqOffline, userDetails);
		LOGGER.debug("*****************AFTER CALLING geoAlgmntInternalService.publishToQueue()***************** ::");

		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  BEFORE LEAVING GeographyAlignmentServiceImpl.movePostalCodes()  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
				+ tChngreqOffline.getTChngReq().getChngReqId());

		return tChngreqOffline.getTChngReq().getChngReqId();
	}

	/**
	 * Move postal codes.
	 *
	 * @param sourceGeoAlign the source geo align
	 * @param targetGeoAlign the target geo align
	 * @param custAligns the cust aligns
	 * @param comment the comment
	 * @param userDetails the user details
	 * @return the long
	 * @throws AlignmentServiceException the alignment service exception
	 * @throws MetricViolationException the metric violation exception
	 * @throws CustomerServiceException the customer service exception
	 * @throws AffiliationServiceException the affiliation service exception
	 * @throws SalesPositionServiceException the sales position service exception
	 * @throws ChangeRequestServiceException the change request service exception
	 * @throws CallPlanServiceException the call plan service exception
	 * @throws ViewServiceException 
	 * @method movePostalCodes
	 */

	@Override
	public void movePostalCodes(ChangeRequest changeRequest, GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException, ViewServiceException {
		
		if (null == comment || "".equals(comment.trim())) {
			comment = "N/A";
		}
		
		if(null == changeRequest || null == changeRequest.getId() || null == sourceGeoAlign || null == targetGeoAlign) {
			LOGGER.info("Mandatory fields missing :  changeRequest/changeRequestId/sourceGeoAlign/targetGeoAlign ");
			Object[] args = new Object[] { "changeRequestId"};
			throw new AlignmentServiceException(args);
		}
		
		targetGeoAlign.setCountryCode(sourceGeoAlign.getCountryCode());
		targetGeoAlign.setPointZipFlag(sourceGeoAlign.isPointZipFlag());

		OfflineRequestMessage offLineReq = geoAlgmntInternalService.doZipMovementOnline(changeRequest, sourceGeoAlign, targetGeoAlign, custAligns, comment, userDetails);
		
		LOGGER.info("*****************AFTER CALLING geoAlgmntInternalService.doZipMovementOffline()***************** :: " + offLineReq);
		// Send the message to the Queue
		if (null != offLineReq) {
			boolean msgStatus = genericPublisher.sendObjectToQueue(CommonConstants.ONLINE_UPDATE_QUEUE, offLineReq);
			if (!msgStatus) {
				// Revert the changes if failed to send message to the Queue
				geoAlgmntInternalService.revertlockZipAndInsertIntoOffline(sourceGeoAlign, custAligns, offLineReq, userDetails);
				throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_316", "Error occurred in pushing Zip");
			}
		} else {
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_318", "ZIP selected is already submitted for movement");
		}
	}

	/**
	 * Gets the postal codes by sales position.
	 * 
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the postal codes by sales position
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	@Override
	@Transactional
	public List<PostalCode> getPostalCodesBySalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if (null == salesPosition) {
				throw new AlignmentServiceException(
						new Object[] { "salesPosition" });
			}
			return geographyAlignmentDAOService.getPostalCodesBySalesPosition(
					salesPosition, userDetails);
		} catch (OpservDataAccessException e) {
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_010,
					"exception while fetching postal code", null, e);
		}
	}

	/**
	 * Validate effective dates.
	 *
	 * @param sourceGeoAlign the source geo align
	 * @param targetGeoAlign the target geo align
	 * @param userDetails the user details
	 * @return true, if validate effective dates
	 * @throws AlignmentServiceException the alignment service exception
	 */
	/*private boolean validateEffectiveDates(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign, UserDetails userDetails)
			throws AlignmentServiceException {
		boolean flag = false;
		Date newStartDate = targetGeoAlign.getStartDate();
		Date newEndDate = targetGeoAlign.getEndDate();

		Date currentDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		Date dayBefore = calendar.getTime();

		if (newStartDate == null || newEndDate == null) {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_246",
					"Start date/end date of target cannot be null");
		}

		if (newStartDate.compareTo(newEndDate) >= 0) {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_246",
					"Target END date cannot be prior to target start date");
		}

		if (currentDate.compareTo(newStartDate) <= 0) {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_246",
					"The ZIP is already assigned to this SalesPosition in this time period. Please change the Start/End dates");
		}

		if (!geographyAlignmentDAOService.validateDates(
				sourceGeoAlign.getSalesPosition(), targetGeoAlign,
				sourceGeoAlign.getPostalCode(), userDetails)) {
			throw new AlignmentServiceException(
					"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_246",
					"The ZIP is already assigned to this SalesPosition in this time period. Please change the Start/End dates");
		}
		return flag;
	}
*/
	/*
	 * public Long getRandomNumber(Long algmntId, Long bussUnitId, Long
	 * salesTeamId,Long salesPosId,Short tenantId) throws GisException { try{
	 * 
	 * Long ranDomNumber = genericDAO.generateID("geo_id_grp",
	 * ApplicationConstant.TABLE,
	 * ApplicationConstant.VALUE_COL,ApplicationConstant.PRIM_KEY_COL,"1");
	 * 
	 * 
	 * return ranDomNumber; }catch(DataAccessException dae){ throw new
	 * GisException("IAGIS058", "Connetion Failure", dae);
	 * 
	 * } catch(Exception e){ throw new GisException("IAGIS002",
	 * "Creating Geo ID",e); } }
	 */

	/**
	 * Verify sp alignment.
	 *
	 * @param sourceSalesPos the source sales pos
	 * @param targetSalesPos the target sales pos
	 * @return true, if verify sp alignment
	 */
	private boolean verifySPAlignment(SalesPosition sourceSalesPos,
			SalesPosition targetSalesPos) {
		Long srcAlgmntId;
		Long srcBussUnitId;
		Long srcSalesTeamId;

		Long targetAlgmntId;
		Long targetBussUnitId;
		Long targetSalesTeamId;

		Alignment srcAlgmnt = sourceSalesPos.getAlignmment();
		srcAlgmntId = srcAlgmnt.getId();
		srcBussUnitId = srcAlgmnt.getSalesTeam().getBusinessUnit().getId();
		srcSalesTeamId = srcAlgmnt.getSalesTeam().getId();

		Alignment targetAlgmnt = targetSalesPos.getAlignmment();
		targetAlgmntId = targetAlgmnt.getId();
		targetBussUnitId = targetAlgmnt.getSalesTeam().getBusinessUnit()
				.getId();
		targetSalesTeamId = targetAlgmnt.getSalesTeam().getId();

		if (srcAlgmntId.equals(targetAlgmntId) && srcBussUnitId.equals(targetBussUnitId)
				&& srcSalesTeamId.equals(targetSalesTeamId)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Verify sales position.
	 *
	 * @param sourceSalesPos the source sales pos
	 * @param targetSalesPos the target sales pos
	 * @return true, if verify sales position
	 */
	private boolean verifySalesPosition(SalesPosition sourceSalesPos,
			SalesPosition targetSalesPos) {
		Long srcSPId = sourceSalesPos.getId();

		Long targetSPId = targetSalesPos.getId();

		if (srcSPId.equals(targetSPId)) {
			return false;
		} else {
			return true;
		}
	}

}
