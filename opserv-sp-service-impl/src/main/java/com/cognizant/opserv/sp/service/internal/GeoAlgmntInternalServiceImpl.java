package com.cognizant.opserv.sp.service.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.core.dao.TChngreqOfflineDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
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
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAffiliationDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
/**
 * The Class GeoAlgmntInternalServiceImpl.
 */
@Service("geoAlgmntInternalService")
public class GeoAlgmntInternalServiceImpl implements GeoAlgmntInternalService {

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory
			.getOpservLogger(GeoAlgmntInternalServiceImpl.class);

	/** The change request service. */
	@Autowired
	private ChangeRequestIntService changeRequestService;

	/** The geography alignment dao service. */
	@Autowired
	private GeographyAlignmentDAOService geographyAlignmentDAOService;

	/** The sales position service. */
	/*@Autowired
	private SalesPositionService salesPositionService;
*/
	/** The customer alignmnet int service. */
	@Autowired
	private CustomerAlignmnetIntService customerAlignmnetIntService;

	/** The generic publisher. */
	@Autowired
	private GenericPublisher genericPublisher;

	/** changeRequestIntService. */
	@Autowired
	private ChangeRequestIntService changeRequestIntService;

	/** The t sales pos dao. */
	@Autowired
	private TSalesPosDAO tSalesPosDao;

	/** The t chngreq offline dao. */
	@Autowired
	private TChngreqOfflineDAO tChngreqOfflineDAO;

	/** The change request dao service. */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	@Autowired
	private SPAssignmentsViewService sPAssignmentsViewServiceImpl;
	
	@Autowired
	private CustomerAffiliationDAOService customerAffiliationService;
	
	@Autowired
	private CustomerAlignmentDAOService customerAlignmnetDAOService;
	
	@Autowired
	private CustomerAlignmentCommonService customerAlignmentCommonService;
	
	@Autowired
	private ChangeRequestOfflineDAOService changeRequestOfflineDAOService;

	/*
	 * @Autowired GeoAlignmentProcessInternalService
	 * geoAlignmentProcessInternalService
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.opserv.sp.service.internal.GeoAlgmntInternalService#
	 * doZipMovement(com.cognizant.opserv.sp.model.GeographyAlignment,
	 * com.cognizant.opserv.sp.model.GeographyAlignment, java.util.List,
	 * java.lang.String, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	/*@Transactional(rollbackFor = { AlignmentServiceException.class,
			MetricViolationException.class, CustomerServiceException.class,
			AffiliationServiceException.class,
			SalesPositionServiceException.class })
	@Override
	public Long doZipMovement(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException {

		LOGGER.info("*****************INSIDE GeoAlgmntInternalServiceImpl.doZipMovement() Method*****************"
				+ new Date());

		Long crID = 0L;
		ChangeRequest chngRequest = new ChangeRequest();
		SalesPosition sourceSalesPos = sourceGeoAlign.getSalesPosition();
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
		try {
			LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
			Date effStartDate = targetGeoAlign.getStartDate();
			Date effEndDate = targetGeoAlign.getEndDate();
			// Preparing customer list
			CustomerAlignment custAlignForPush = null;
			List<CustomerAlignment> custAlignForPushList = new ArrayList<CustomerAlignment>();

			// find geo id in target sp
			// if it is present then update else create
			// update zipterr table of source
			PostalCode postalCodeForMvmt = sourceGeoAlign.getPostalCode();
			if (sourceSalesPos != null && targetSalesPos != null) {
				// Deactivating the source

				geographyAlignmentDAOService.deactivateZipAlgmnt(
						sourceSalesPos, postalCodeForMvmt, userDetails);

				// Getting geo id for target
				Integer spGeoId = this.fetchGeoID(userDetails, targetSalesPos);
				PostalCode pcTarget = new PostalCode();
				pcTarget.setCode(targetGeoAlign.getPostalCode().getCode());
				pcTarget.setGeoCode(new Long(spGeoId));
				targetGeoAlign.setPostalCode(pcTarget);

				// inserting or updating the target
				Integer geoZipId = geographyAlignmentDAOService
						.insertUpdateZipAlgmnt(targetGeoAlign,
								targetGeoAlign.getPostalCode(), userDetails,
								spGeoId);
				// targetGeoAlign.setId(new Long(geoZipId));
				LOGGER.info("geoZipId :" + geoZipId);

				// Mirrored zip movement
				// Finding mirror details
				List<List<SalesPosition>> mirroredSPList = this
						.getMirroredSPDetails(sourceGeoAlign, targetGeoAlign,
								userDetails);

				List<GeographyAlignment> mirroredGeoAlignments = this
						.performZipMovementForMirroredSPs(mirroredSPList,
								postalCodeForMvmt, userDetails, effStartDate,
								effEndDate);

				// To raise CR for zip mvmt
				LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
				StringBuffer info = new StringBuffer();
				info.append("Source Details::PostalCode -> "
						+ sourceGeoAlign.getPostalCode().getCode()
						+ ",GeoID-> "
						+ sourceGeoAlign.getPostalCode().getGeoCode());
				info.append("SalesPosition ->"
						+ sourceGeoAlign.getSalesPosition().getId()
						+ ",Hier ID ->"
						+ sourceGeoAlign.getSalesPosition().getHierarchy()
								.getId());
				info.append("Target Details::PostalCode -> "
						+ targetGeoAlign.getPostalCode().getCode()
						+ ",GeoID-> "
						+ targetGeoAlign.getPostalCode().getGeoCode());
				info.append("SalesPosition ->"
						+ targetGeoAlign.getSalesPosition().getId()
						+ ",Hier ID ->"
						+ targetGeoAlign.getSalesPosition().getHierarchy()
								.getId());
				LOGGER.info(info.toString());
				LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
				crID = changeRequestService.saveChangeRequestZipAssigmentPush(
						sourceGeoAlign, targetGeoAlign, comment,
						sourceGeoAlign.getSalesPosition(), userDetails);
				chngRequest.setId(crID);

				// Customer movement
				// Preparing target customer data
				if (custAligns != null && !custAligns.isEmpty()) {
					for (CustomerAlignment custAlign : custAligns) {
						custAlignForPush = new CustomerAlignment();
						custAlignForPush = custAlign;
						custAlignForPush.setSalesPosition(targetSalesPos);
						custAlignForPush.setStartDate(effStartDate);
						custAlignForPush.setEndDate(effEndDate);
						// custAlignForPush.setStartDate(DateUtil.getDefaultFormatDate("11-05-2016"));
						// custAlignForPush.setEndDate(DateUtil.getDefaultFormatDate("11-05-2017"));
						custAlignForPushList.add(custAlignForPush);
						// customerAlignmentService.pushCustomer(custAlign,
						// custAlignForPush,comment, userDetails);
						customerAlignmnetIntService
								.pushPullCustSaveUpdateFrZip(custAlign,
										custAlignForPush,
										"Customer moved for ZIP: CRID" + crID,
										userDetails, CommonConstants.PUSH,
										mirroredSPList, CommonConstants.ZIP);

					}
				}

				if (mirroredGeoAlignments != null
						&& !mirroredGeoAlignments.isEmpty()) {
					changeRequestService
							.saveMirrorChangeRequestZipAssigmentPush(
									mirroredGeoAlignments.get(0),
									mirroredGeoAlignments.get(1), comment,
									chngRequest, mirroredGeoAlignments.get(0)
											.getSalesPosition(), userDetails);
				}

			}
		} catch (OpservDataAccessException e) {
			e.printStackTrace();
		}

		try {
			if (chngRequest.getId() != null) {
				LOGGER.info("*****************BEFORE LEAVING GeoAlgmntInternalServiceImpl.doZipMovement() Method***************** +chngRequest.getId() ::"
						+ chngRequest.getId());

				return chngRequest.getId();
			} else {
				LOGGER.info("*****************BEFORE LEAVING GeoAlgmntInternalServiceImpl.doZipMovement() Method*****************+chngRequest.getId() ::"
						+ chngRequest.getId());

				throw new AlignmentServiceException(
						"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
						"Change Request was not raised successfully . ");
			}
		} catch (AlignmentServiceException ex) { // Change the code
			Object[] args = new Object[] {};
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230,
					"Change Request was not raised successfully ", args, ex);
		}
	}
*/

	
	/**
	 * doZipMovementOffline to do Zip Movement Offline.
	 * 
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param custAligns
	 *            the cust aligns
	 * @param comment
	 *            the comment
	 * @param userDetails
	 *            the user details
	 * @return Long cr id
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 * @throws MetricViolationException
	 *             the metric violation exception
	 * @throws CustomerServiceException
	 *             the Customer Service Exception
	 * @throws AffiliationServiceException
	 *             the Affiliation Service Exception
	 * @throws SalesPositionServiceException
	 *             the Sales Position Service Exception
	 * @throws ChangeRequestServiceException
	 *             the Change Request Service Exception
	 * @throws CallPlanServiceException
	 *             the Call Plan Service Exception
	 */
	//@SuppressWarnings("finally")
	@Transactional(rollbackFor = { AlignmentServiceException.class,
			MetricViolationException.class, CustomerServiceException.class,
			AffiliationServiceException.class,
			SalesPositionServiceException.class })
	@Override
	public TChngreqOffline doZipMovementOnline(
			GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException,
			AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException {
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  INSIDE GeoAlgmntInternalServiceImpl.doZipMovementOffline() @@@@@@@@@ -->"
				+ new Date());
		ChangeRequest chngRequest = new ChangeRequest();
		SalesPosition sourceSalesPos = sourceGeoAlign.getSalesPosition();
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
		List<SalesPosition> srcViewUpdateList = new ArrayList<SalesPosition>();
		List<SalesPosition> trgViewUpdateList = new ArrayList<SalesPosition>();
		TChngreqOffline tChngreqOffline = null;
		AlignmentServiceException alignmentServiceException = null;
		AlignmentServiceException exception = null;
		try {

			// find geo id in target sp
			// if it is present then update else create
			// update zipterr table of source
			PostalCode postalCodeForMvmt = sourceGeoAlign.getPostalCode();

			srcViewUpdateList.add(sourceGeoAlign.getSalesPosition());
			trgViewUpdateList.add(targetGeoAlign.getSalesPosition());

			if (sourceSalesPos != null && targetSalesPos != null) {

				// Deactivating the source
				// geographyAlignmentDAOService.deactivateZipAlgmnt(sourceSalesPos,
				// postalCodeForMvmt, userDetails);
				// this.deactivateSource(sourceSalesPos, postalCodeForMvmt,
				// effEndDate, userDetails);
				LOGGER.info("*****************BEFORE CALLING geographyAlignmentDAOService.deactivateZipAlgmntOffline()  *****************");
				LOGGER.info("*****************ZIPMVMT::BEFORE CALLING deactivateZipAlgmnt*****************"
						+ new Date());
				Integer geoId = geographyAlignmentDAOService
						.deactivateZipAlgmntOffline(sourceSalesPos,
								postalCodeForMvmt,StatusType.PENDING_SUBMISSION, CommonConstants.CHAR_YES, userDetails);
				LOGGER.info("geoId :" + geoId);
				// CR Creation
				// To raise CR for zip mvmt
				LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
				StringBuffer info = new StringBuffer();
				info.append("Source Details::PostalCode -> "
						+ sourceGeoAlign.getPostalCode().getCode()
						+ ",GeoID-> "
						+ sourceGeoAlign.getPostalCode().getGeoCode());
				info.append("SalesPosition ->"
						+ sourceGeoAlign.getSalesPosition().getId()
						+ ",Hier ID ->"
						+ sourceGeoAlign.getSalesPosition().getHierarchy()
								.getId());
				info.append("Target Details::PostalCode -> "
						+ targetGeoAlign.getPostalCode().getCode()
						+ ",GeoID-> "
						+ targetGeoAlign.getPostalCode().getGeoCode());
				info.append("SalesPosition ->"
						+ targetGeoAlign.getSalesPosition().getId()
						+ ",Hier ID ->"
						+ targetGeoAlign.getSalesPosition().getHierarchy()
								.getId());
				LOGGER.info(info.toString());
				LOGGER.info("*****************INPUT PARAMETERS FOR CHANGE REQUEST*****************");
				// crID =
				// changeRequestService.saveChangeRequestZipAssigmentPush(sourceGeoAlign,null,
				// comment, sourceGeoAlign.getSalesPosition(), userDetails);
				chngRequest = changeRequestService.findExistingChangeRequestId(
						targetGeoAlign.getSalesPosition(),
						sourceGeoAlign.getSalesPosition(), userDetails,
						ChangeRequestTriggerType.PUSH_ZIP.getId());
				// chngRequest.setId(crID);
				// Changing the status of CR

				// Complete

				// Insert into temporary DB
				if (chngRequest.getId() != null) {
					LOGGER.info("*****************BEFORE CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDb()  *****************-->"
							+ new Date());
					tChngreqOffline = this.insertIntoTempDb(chngRequest,
							sourceGeoAlign, targetGeoAlign, custAligns,
							comment, userDetails);
					LOGGER.debug(
							"*****************AFTER CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDb()  *****************",
							tChngreqOffline);
					LOGGER.debug(
							"*****************AFTER CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDb()  *****************-->",
							new Date());
					if (custAligns != null) {
						for (CustomerAlignment orgCustAlgn : custAligns) {
							try {
								LOGGER.info("*****************BEFORE CALLING customerAlignmnetIntService.executeFrSourceSPOnline()  to change the status of Cust Algmnt*****************");
								customerAlignmnetIntService
										.executeUpdatesFrBaseSrcSP(orgCustAlgn,
												userDetails);
								LOGGER.debug("*****************AFTER CALLING customerAlignmnetIntService.executeFrSourceSPOnline()  *****************");
							} catch (OpservDataAccessException e) {
								Object[] args = new Object[] { orgCustAlgn
										.getCustomer().getId() };
								throw new AlignmentServiceException(
										AlignmentServiceExceptionCode.ALGN_SER_EX_CD_218,
										"Error while updating Customer Alignments",
										args, e);
							}
						}
					}
					LOGGER.debug(
							"*****************AFTER CALLING CUSTOMER UPDATE  *****************",
							new Date());
				}
			}
			LOGGER.info("****************TRY BLOCK COMPLETES IN doZipMovementOffline() *****************"
					+ new Date());

		} catch (OpservDataAccessException e) {
			LOGGER.error(e.getMessage(), e);
			Object[] args = new Object[] {};
			alignmentServiceException = new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_316,
					"Error in zip movement ", args, e);
//			throw new AlignmentServiceException(
//					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_316,
//					"Error in zip movement ", args, e);
		} finally {
			LOGGER.info("****************INSIDE FINALLY BLOCK OF doZipMovementOffline() *****************"
					+ new Date());
			try {
				if (tChngreqOffline != null
						&& tChngreqOffline.getTChngReq().getChngReqId() != null) {
					LOGGER.info("chngRequest.getId()  is not null and chngRequest.getId() == "
							+ chngRequest.getId());
					LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  BEFORE LEAVING GeoAlgmntInternalServiceImpl.doZipMovementOffline() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					// ChangeRequest chngReq = new ChangeRequest();
					ChangeRequest rsData = changeRequestDAOService
							.getChangeRequestStatusByChangeRequestId(chngRequest);

					LOGGER.info("*****************BEFORE CALLING changeRequestIntService.updateChangeRequestStatus()  *****************"
							+ new Date());
					if (!rsData.getStatus().equals(
							ChangeRequestStatusType.WORK_IN_PROGRESS)) {
						changeRequestIntService.updateChangeRequestStatus(
								chngRequest.getId(),
								ChangeRequestStatusType.WORK_IN_PROGRESS
										.getId(), userDetails);
					}
					LOGGER.info("*****************AFTER CALLING changeRequestIntService.updateChangeRequestStatus()  *****************"
							+ new Date());
//					return tChngreqOffline;
				} else {
					LOGGER.info("chngRequest.getId()  is null ");
					throw new AlignmentServiceException( 
							"AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230",
							"Change Request was not raised successfully . ");
				}
			} catch (AlignmentServiceException ex) { // Change the code
				LOGGER.info("AlignmentServiceException occured ");

				Object[] args = new Object[] {};
				exception = new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230,
						"Change Request was not raised successfully ", args, ex);
//				throw new AlignmentServiceException(
//						AlignmentServiceExceptionCode.ALGN_SER_EX_CD_230,
//						"Change Request was not raised successfully ", args, ex);
			}
		}
		
		if(null != exception){
			throw exception;
		}
		if(null != alignmentServiceException){
			throw alignmentServiceException;
		}
		return tChngreqOffline;
	}

	/**
	 * @param changeRequest
	 * @param sourceGeoAlign
	 * @param targetGeoAlign
	 * @param custAligns
	 * @param comment
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 * @throws MetricViolationException
	 * @throws CustomerServiceException
	 * @throws AffiliationServiceException
	 * @throws SalesPositionServiceException
	 * @throws ChangeRequestServiceException
	 * @throws CallPlanServiceException
	 * @throws ViewServiceException 
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { AlignmentServiceException.class, MetricViolationException.class,
			CustomerServiceException.class, AffiliationServiceException.class, SalesPositionServiceException.class })
	@Override
	public OfflineRequestMessage doZipMovementOnline(ChangeRequest changeRequest,
			GeographyAlignment sourceGeoAlign, GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment, UserDetails userDetails) throws AlignmentServiceException,
			MetricViolationException, CustomerServiceException, AffiliationServiceException, SalesPositionServiceException,
			ChangeRequestServiceException, CallPlanServiceException, ViewServiceException {
		
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  INSIDE GeoAlgmntInternalServiceImpl.doZipMovementOffline() @@@@@@@@@ -->" + new Date());
		SalesPosition sourceSalesPos = sourceGeoAlign.getSalesPosition();
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
		List<SalesPosition> srcViewUpdateList = new ArrayList<SalesPosition>();
		List<SalesPosition> trgViewUpdateList = new ArrayList<SalesPosition>();
		OfflineRequestMessage offLineReq = null;
		
		try {
			// find geo id in target sp
			// if it is present then update else create
			// update zipterr table of source
			PostalCode postalCodeForMvmt = sourceGeoAlign.getPostalCode();

			srcViewUpdateList.add(sourceGeoAlign.getSalesPosition());
			trgViewUpdateList.add(targetGeoAlign.getSalesPosition());

			if (sourceSalesPos != null && targetSalesPos != null) {

				// Deactivating the source
				// geographyAlignmentDAOService.deactivateZipAlgmnt(sourceSalesPos,
				// postalCodeForMvmt, userDetails);
				// this.deactivateSource(sourceSalesPos, postalCodeForMvmt,
				// effEndDate, userDetails);
				LOGGER.info("*****************BEFORE CALLING geographyAlignmentDAOService.deactivateZipAlgmntOffline()  *****************");
				LOGGER.info("*****************ZIPMVMT::BEFORE CALLING deactivateZipAlgmnt*****************" + new Date());
				
				Integer geoId = geographyAlignmentDAOService.deactivateZipAlgmntOffline(sourceSalesPos, postalCodeForMvmt,
						StatusType.PENDING_APPROVAL, CommonConstants.CHAR_YES, userDetails);
				LOGGER.info("geoId :" + geoId);
				
				sPAssignmentsViewServiceImpl.markZipSalesPosFlagAsDirty(sourceSalesPos.getId(), postalCodeForMvmt.getCode(), userDetails);
				
				if(null != custAligns && !custAligns.isEmpty()) {
					for(CustomerAlignment alignment : custAligns) {
						sPAssignmentsViewServiceImpl.markCustAlgmntFlagAsDirty(sourceSalesPos.getId(), alignment.getId(), userDetails);
					}
				}

				LOGGER.info("*****************BEFORE CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDB()  *****************-->" + new Date());
				
				offLineReq = this.insertIntoTempDB(changeRequest, sourceGeoAlign, targetGeoAlign, custAligns, comment, userDetails);
				LOGGER.debug("*****************AFTER CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDB()  *****************", offLineReq);
				LOGGER.debug("*****************AFTER CALLING GeoAlgmntInternalServiceImpl.insertIntoTempDB()  *****************-->", new Date());
				
				if (custAligns != null) {			
					for (CustomerAlignment orgCustAlgn : custAligns) {
						LOGGER.info("*****************BEFORE CALLING customerAlignmnetIntService.executeFrSourceSPOnline()  to change the status of Cust Algmnt*****************");
						customerAlignmnetIntService.executeUpdatesFrBaseSrcSP(orgCustAlgn, userDetails);
						LOGGER.debug("*****************AFTER CALLING customerAlignmnetIntService.executeFrSourceSPOnline()  *****************");
					}
				}
			}
			LOGGER.info("****************TRY BLOCK COMPLETES IN doZipMovementOffline() *****************" + new Date());

		} catch (OpservDataAccessException e) {
			LOGGER.error(e.getMessage(), e);
			Object[] args = new Object[] {};
			throw new AlignmentServiceException(AlignmentServiceExceptionCode.ALGN_SER_EX_CD_318,
					"No active Zip found/Zip selected is already submitted for movement", args, e);
		} 
		
		return offLineReq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.opserv.sp.service.internal.GeoAlgmntInternalService#
	 * publishToQueue(com.cognizant.opserv.sp.core.entity.TChngreqOffline,
	 * com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void publishToQueue(TChngreqOffline tChngreqOffline,
			UserDetails userDetails) {
		// TODO Auto-generated method stub

		if (null != tChngreqOffline.getOfflineId()) {
			final OfflineRequestMessage tempCRBlobMessage = new OfflineRequestMessage();
			tempCRBlobMessage.setChngReqID(tChngreqOffline.getTChngReq()
					.getChngReqId());
			tempCRBlobMessage.setOfflineReqId(tChngreqOffline.getOfflineId());
			tempCRBlobMessage.setTenantCode(userDetails.getTenantCode());
			tempCRBlobMessage.setTenantId(tChngreqOffline.getTenantId());
			tempCRBlobMessage.setUserDetails(userDetails);
			tempCRBlobMessage.setTriggerId(tChngreqOffline.getTriggerId());

			// Send the message to the Queue
			genericPublisher.sendObjectToQueue(
					CommonConstants.ONLINE_UPDATE_QUEUE, tempCRBlobMessage);
		}

	}

	/**
	 * Insert into temp db.
	 * 
	 * @param chngRequest
	 *            the chng request
	 * @param sourceGeoAlign
	 *            the source geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param custAligns
	 *            the cust aligns
	 * @param comment
	 *            the comment
	 * @param userDetails
	 *            the user details
	 * @return the t chngreq offline
	 */
	private TChngreqOffline insertIntoTempDb(ChangeRequest chngRequest,
			GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) {

		TChngreqOffline tChngreqOffline = geographyAlignmentDAOService
				.saveGeoAlgmntIntoTempChngReq(chngRequest, sourceGeoAlign,
						targetGeoAlign, custAligns, comment, userDetails);
		return tChngreqOffline;
		// this.publishToQueue(tChngreqOffline,chngRequest.getId(),userDetails);

	}
	
	/**
	 * @param chngRequest
	 * @param sourceGeoAlign
	 * @param targetGeoAlign
	 * @param custAligns
	 * @param comment
	 * @param userDetails
	 * @return
	 */
	private OfflineRequestMessage insertIntoTempDB(ChangeRequest chngRequest,
			GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment,
			UserDetails userDetails) throws AlignmentServiceException {

		OfflineRequestMessage offLineReq = null;
		
		try {
			
			TChngreqOffline tChngreqOffline = geographyAlignmentDAOService.saveGeoAlgmntIntoTempChngReq(chngRequest, sourceGeoAlign,
							targetGeoAlign, custAligns, comment, userDetails);
	
			if(tChngreqOffline != null) {
				offLineReq = new  OfflineRequestMessage();
				offLineReq.setChngReqID(chngRequest.getId());
				offLineReq.setOfflineReqId(tChngreqOffline.getOfflineId());
				offLineReq.setTenantCode(userDetails.getTenantCode());
				offLineReq.setTenantId(tChngreqOffline.getTenantId());
				offLineReq.setUserDetails(userDetails);
				offLineReq.setTriggerId(tChngreqOffline.getTriggerId());
			}

		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while saving push zip offline request into t_chngreq_offline table : " + e.getMessage());
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_318", "ZIP selected is already submitted for movement");
		}
		return offLineReq;
	}

	/*
	 * private void deactivateSource(SalesPosition sourceSalesPos,PostalCode
	 * postalCodeForMvmt,Date effStartDate,UserDetails userDetails){
	 * 
	 * Date currentDate = new Date(); Calendar calendar =
	 * Calendar.getInstance(); calendar.setTime(new Date());
	 * calendar.add(Calendar.DATE, -1); Date dayBefore = calendar.getTime();
	 * 
	 * if(dayBefore.compareTo(effStartDate)>0){
	 * geographyAlignmentDAOService.deactivateZipAlgmnt(sourceSalesPos,
	 * postalCodeForMvmt, userDetails); }else{ //only change effective end date
	 * Integer geoId = (int) (long) postalCodeForMvmt.getGeoCode();
	 * geographyAlignmentDAOService
	 * .updateEndDateForZipAlgmnt(sourceSalesPos,postalCodeForMvmt,
	 * userDetails,geoId,dayBefore); }
	 * 
	 * System.out.println(dayBefore); System.out.println(currentDate); }
	 */

	/**
	 * Fetch geo id.
	 * 
	 * @param userDetails
	 *            the user details
	 * @param targetSalesPos
	 *            the target sales pos
	 * @return the integer
	 */
	/*private Integer fetchGeoID(UserDetails userDetails,
			SalesPosition targetSalesPos) {
		Map<Long, Integer> existingGeoIDs = geographyAlignmentDAOService
				.getExistingGeoIDs(targetSalesPos, userDetails);

		Long destSalePosId = targetSalesPos.getId();

		Integer spGeoId = null;
		if (existingGeoIDs != null && existingGeoIDs.size() > 0) {
			spGeoId = existingGeoIDs.get(destSalePosId);
		}

		if (spGeoId == null || spGeoId == 0) {
			// generate random number
			spGeoId = geographyAlignmentDAOService.getNewGeoID(userDetails);
		}
		return spGeoId;
	}*/

	/**
	 * Perform zip movement for mirrored s ps.
	 * 
	 * @param mirroredSPList
	 *            the mirrored sp list
	 * @param postalCodeForMvmt
	 *            the postal code for mvmt
	 * @param userDetails
	 *            the user details
	 * @param effStartDate
	 *            the eff start date
	 * @param effEndDate
	 *            the eff end date
	 * @return the list< geography alignment>
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	/*private List<GeographyAlignment> performZipMovementForMirroredSPs(
			List<List<SalesPosition>> mirroredSPList,
			PostalCode postalCodeForMvmt, UserDetails userDetails,
			Date effStartDate, Date effEndDate)
			throws AlignmentServiceException {
		LOGGER.info("*****************INSIDE GeoAlgmntInternalServiceImpl.performZipMovementForMirroredSPs()  *****************");

		GeographyAlignment srcMirroredGeoAlign = null;
		GeographyAlignment trgMirroredGeoAlign = null;
		List<GeographyAlignment> geoAlignList = null;
		Integer geoId = null;
		if (mirroredSPList != null && !mirroredSPList.isEmpty()) {
			List<SalesPosition> sourceMirroredSPList = mirroredSPList.get(0);
			List<SalesPosition> targetMirroredSPList = mirroredSPList.get(1);
			geoAlignList = new ArrayList<GeographyAlignment>();
			if (sourceMirroredSPList != null && !sourceMirroredSPList.isEmpty()
					&& targetMirroredSPList != null
					&& !targetMirroredSPList.isEmpty()) {
				SalesPosition sourceSalesPos = sourceMirroredSPList.get(0);
				SalesPosition targetSalesPos = targetMirroredSPList.get(0);

				geoId = geographyAlignmentDAOService.deactivateZipAlgmnt(
						sourceSalesPos, postalCodeForMvmt, userDetails);
				srcMirroredGeoAlign = new GeographyAlignment();
				srcMirroredGeoAlign.setSalesPosition(sourceSalesPos);
				srcMirroredGeoAlign.setPostalCode(postalCodeForMvmt);
				srcMirroredGeoAlign.setId(new Long(geoId));

				Integer spGeoId = this.fetchGeoID(userDetails, targetSalesPos);
				// targetGeoAlign.setId(new Long(spGeoId));
				trgMirroredGeoAlign = new GeographyAlignment();
				trgMirroredGeoAlign.setSalesPosition(targetSalesPos);
				trgMirroredGeoAlign.setPostalCode(postalCodeForMvmt);
				trgMirroredGeoAlign.setId(new Long(geoId));
				trgMirroredGeoAlign.setStartDate(effStartDate);
				trgMirroredGeoAlign.setEndDate(effEndDate);

				if (srcMirroredGeoAlign != null && trgMirroredGeoAlign != null) {
					geoAlignList.add(srcMirroredGeoAlign);
					geoAlignList.add(trgMirroredGeoAlign);
				}
				// inserting or updating the target
				geoId = geographyAlignmentDAOService.insertUpdateZipAlgmnt(
						trgMirroredGeoAlign, postalCodeForMvmt, userDetails,
						spGeoId);
				trgMirroredGeoAlign.getPostalCode().setGeoCode(geoId);

			}
		}
		LOGGER.info("*****************BEFORE LEAVING GeoAlgmntInternalServiceImpl.performZipMovementForMirroredSPs()  *****************");

		return geoAlignList;
	}*/

	/**
	 * Gets the mirrored sp details.
	 * 
	 * @param srcGeoAlign
	 *            the src geo align
	 * @param targetGeoAlign
	 *            the target geo align
	 * @param userDetails
	 *            the user details
	 * @return the mirrored sp details
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	/*private List<List<SalesPosition>> getMirroredSPDetails(
			GeographyAlignment srcGeoAlign, GeographyAlignment targetGeoAlign,
			UserDetails userDetails) throws AlignmentServiceException {

		LOGGER.info("*****************INSIDE GeoAlgmntInternalServiceImpl.getMirroredSPDetails()  *****************");

		List<SalesPosition> sourceMirroredSPList = this.getMirroredSPList(
				srcGeoAlign, userDetails);
		List<SalesPosition> targetMirroredSPList = this.getMirroredSPList(
				targetGeoAlign, userDetails);
		PostalCode postalCodeForMvmt = srcGeoAlign.getPostalCode();
		List<SalesPosition> sourceMirroredSPListForMvmt = null;
		List<SalesPosition> targetMirroredSPListForMvmt = null;
		List<List<SalesPosition>> mirrSPList = null;

		// Finding mirrored sp which contains postal code
		if (sourceMirroredSPList != null && !sourceMirroredSPList.isEmpty()) {
			// Find the zip in the source list
			List<Long> srcSpIdList = null;
			srcSpIdList = new ArrayList<Long>();
			List<SalesPosition> salesPosList = geographyAlignmentDAOService
					.findMirroredSPsWithZip(sourceMirroredSPList,
							postalCodeForMvmt, userDetails);
			sourceMirroredSPListForMvmt = new ArrayList<SalesPosition>();
			System.out.println("salesPosList size>>" + salesPosList.size());
			for (SalesPosition spParent : sourceMirroredSPList) {
				for (SalesPosition spChild : salesPosList) {
					if (spParent.getId().equals(spChild.getId())
							&& spParent.getHierarchy().getId()
									.equals(spChild.getHierarchy().getId())
							&& !srcSpIdList.contains(spChild.getId())) {
						sourceMirroredSPListForMvmt.add(spParent);
						srcSpIdList.add(spChild.getId());
					}
				}
			}
		}

		// Finding the target for mirrored SP movement
		if (targetMirroredSPList != null && !targetMirroredSPList.isEmpty()) {
			// for target
			List<Long> mirrTarSpIdList = null;
			mirrTarSpIdList = new ArrayList<Long>();
			// Find the primary target
			// List<SalesPosition> targetSpList = new
			// ArrayList<SalesPosition>();
			targetMirroredSPListForMvmt = new ArrayList<SalesPosition>();
			for (SalesPosition salesPosition : targetMirroredSPList) {
				if (salesPosition.isPrimaryMirror()) {
					// Primary mirrored sales position
					if (!mirrTarSpIdList.contains(salesPosition.getId())) {
						mirrTarSpIdList.add(salesPosition.getId());
						targetMirroredSPListForMvmt.add(salesPosition);
					}
					// targetMirroredSPListForMvmt.add(salesPosition);
				}
			}
		}

		if (sourceMirroredSPListForMvmt != null
				&& targetMirroredSPListForMvmt != null) {
			// do the zip movement
			mirrSPList = new ArrayList<List<SalesPosition>>();
			mirrSPList.add(sourceMirroredSPListForMvmt);
			mirrSPList.add(targetMirroredSPListForMvmt);
		}

		LOGGER.info("*****************BEFORE LEAVING GeoAlgmntInternalServiceImpl.getMirroredSPDetails()  *****************");

		return mirrSPList;
	}*/

	/**
	 * Gets the mirrored sp list.
	 * 
	 * @param geoAlign
	 *            the geo align
	 * @param userDetails
	 *            the user details
	 * @return the mirrored sp list
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	/*private List<SalesPosition> getMirroredSPList(GeographyAlignment geoAlign,
			UserDetails userDetails) throws AlignmentServiceException {
		LOGGER.info("*****************INSIDE GeoAlgmntInternalServiceImpl.getMirroredSPList()  *****************");

		SalesPosition salesPos = geoAlign.getSalesPosition();
		List<SalesPosition> mirroredSPList = null;
		List<SalesPosition> mirroredSPList = salesPositionService
				.getMirroredSalesPositions(salesPos, userDetails);
		List<SalesPosition> filteredSPList = new ArrayList<SalesPosition>();
		if (mirroredSPList != null && !mirroredSPList.isEmpty()) {
			for (SalesPosition sp : mirroredSPList) {
				if (sp.getAlignmment().getId()
						.equals(salesPos.getAlignmment().getId())
						&& sp.getAlignmment()
								.getSalesTeam()
								.getId()
								.equals(salesPos.getAlignmment().getSalesTeam()
										.getId())
						&& sp.getAlignmment()
								.getSalesTeam()
								.getBusinessUnit()
								.getId()
								.equals(salesPos.getAlignmment().getSalesTeam()
										.getBusinessUnit().getId())) {
					filteredSPList.add(sp);
				}
			}
		}
		LOGGER.info("*****************BEFORE LEAVING GeoAlgmntInternalServiceImpl.getMirroredSPList()  *****************filteredSPList ::"
				+ filteredSPList);
		return filteredSPList;
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.opserv.sp.service.internal.GeoAlgmntInternalService#
	 * updateDocStore(com.cognizant.opserv.sp.model.GeographyAlignment,
	 * com.cognizant.opserv.sp.model.GeographyAlignment, java.util.List,
	 * com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	/*@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void updateDocStore(GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> orgCustAlgnList, UserDetails userDetails)
			throws AlignmentServiceException {
		// TODO Auto-generated method stub

		SalesPosition sourceSalesPos = sourceGeoAlign.getSalesPosition();
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();

		

		List<Long> salesPosList = new ArrayList<Long>();
		if (sourceSalesPos != null) {
			salesPosList.add(sourceSalesPos.getId());
		}

		if (targetSalesPos != null) {
			salesPosList.add(targetSalesPos.getId());
		}

		CustomerAlignment newCustAlgn = null;
		List<CustomerAlignment> custAlignForPushList = new ArrayList<CustomerAlignment>();
		Date effStartDate = targetGeoAlign.getStartDate();
		Date effEndDate = targetGeoAlign.getEndDate();
		List<List<SalesPosition>> mirroredSPList = this.getMirroredSPDetails(
				sourceGeoAlign, targetGeoAlign, userDetails);
		List<SalesPosition> srcMirSPList = null;
		List<SalesPosition> tarMirSPList = null;

		if (mirroredSPList != null && !mirroredSPList.isEmpty()) {
			srcMirSPList = mirroredSPList.get(0);
			tarMirSPList = mirroredSPList.get(1);
		}

		if (srcMirSPList != null && !srcMirSPList.isEmpty()) {
			for (SalesPosition srcSP : srcMirSPList) {
				salesPosList.add(srcSP.getId());
			}
		}

		if (tarMirSPList != null && !tarMirSPList.isEmpty()) {
			for (SalesPosition tarSP : tarMirSPList) {
				salesPosList.add(tarSP.getId());
			}
		}

		for (Long salesPositionId : salesPosList) {
			spAssignmentsService.updateSPAssignmentsDoc(salesPositionId,
					StoreEntity.GEO_ALIGNMENT, userDetails);
		}
		if (orgCustAlgnList != null && !orgCustAlgnList.isEmpty()) {
			for (CustomerAlignment orgCustAlgn : orgCustAlgnList) {
				newCustAlgn = new CustomerAlignment();
				newCustAlgn = orgCustAlgn;
				newCustAlgn.setSalesPosition(targetSalesPos);
				newCustAlgn.setStartDate(effStartDate);
				newCustAlgn.setStartDate(effEndDate);
				custAlignForPushList.add(newCustAlgn);
				customerAlignmnetIntService.updateDocStoreFrPushPull(
						orgCustAlgn, newCustAlgn, userDetails, mirroredSPList);
			}
		}

	}*/


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cognizant.opserv.sp.service.internal.GeoAlgmntInternalService#
	 * updateSourceEffectiveEndDate
	 * (com.cognizant.opserv.sp.model.GeographyAlignment, java.util.Date,
	 * com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateSourceEffectiveEndDate(GeographyAlignment sourceGeoAlign,
			Date newEndDate, UserDetails userDetails) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the zip count.
	 * 
	 * @param salesPosition
	 *            the sales position
	 * @param userDetails
	 *            the user details
	 * @return the zip count
	 * @throws AlignmentServiceException
	 *             the alignment service exception
	 */
	@Override
	public Long getZipCount(SalesPosition salesPosition, UserDetails userDetails)
			throws AlignmentServiceException {
		try {
			if (null != salesPosition && null != salesPosition.getId()
					&& null != salesPosition.getHierarchy()
					&& null != salesPosition.getHierarchy().getId()
					&& salesPosition.getId() != 0
					&& salesPosition.getHierarchy().getId() != 0
					&& null != userDetails && null != userDetails.getTenantId()
					&& userDetails.getTenantId() != 0) {
				return geographyAlignmentDAOService.getZipCount(salesPosition,
						userDetails);
			} else {
				LOGGER.info("*****************INPUT PARAMETER SALESPOSITION OR USER DETAILS IS NULL*****************");
				Object[] args = { "salesPosition is null or userDetails is null" };
				throw new AlignmentServiceException(args);
			}

		} catch (OpservDataAccessException e) {
			Object[] args = new Object[] { salesPosition.getId() };
			throw new AlignmentServiceException(
					AlignmentServiceExceptionCode.ALGN_SER_EX_CD_251,
					"This issue occurred while Fetching ZipCount", args, e);
		}
	}


	/**
	 * @param geoAlign
	 * @param offlineMsg
	 * @param userDetails
	 * @throws AlignmentServiceException
	 * @throws ViewServiceException 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AlignmentServiceException.class)
	public void revertlockZipAndInsertIntoOffline(GeographyAlignment geoAlign,
			List<CustomerAlignment> custAligns, OfflineRequestMessage offlineMsg, UserDetails userDetails) throws AlignmentServiceException, ViewServiceException {

		try {
			// Unlock Zip
			//geographyAlignmentCommonService.unLockGeographyAlignmentOnReject(geoAlign, userDetails);
			Integer geoId = (int) geoAlign.getPostalCode().getGeoCode();
			geographyAlignmentDAOService.updateTTerrZipAlgmntStatus(geoAlign.getSalesPosition().getId(), geoAlign.getPostalCode().getCode(),
					geoAlign.getSalesPosition().getHierarchy().getId(), geoId, StatusType.APPROVED.getId(), CommonConstants.CHAR_YES, userDetails);
			
			// Unlock the customers in that ZIP
			if (custAligns != null) {
				for (CustomerAlignment custAlgmnt : custAligns) {
					customerAlignmentCommonService.unLockCustomerAlignment(custAlgmnt, userDetails);
				}
			}
			// Cancel CR Offline
			changeRequestOfflineDAOService.updateCROfflineStatus(ChangeRequestOfflineStatusType.CANCELLED.getId(),
				offlineMsg.getOfflineReqId(), userDetails.getUserId(), userDetails.getTenantId(), "Internal Error - Message could not be delivered");
				
		} catch (OpservDataAccessException e) {
			LOGGER.error("Exception while updating zip Algmnt/customer offline request : " + e.getMessage());
			throw new AlignmentServiceException("AlignmentServiceExceptionCode.ALGN_SER_EX_CD_323", "Exception while updating CR Offline Status");
		}
			
		}

	}
