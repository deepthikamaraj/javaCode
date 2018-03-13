package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestApproverType;
import com.cognizant.opserv.sp.common.ChangeRequestAutoActionType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.JSONUtil;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqAppr;
import com.cognizant.opserv.sp.core.entity.TChngReqBussReason;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.core.entity.TCustAlgmntChngReqDet;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.opserv.sp.json.CustomerBlobDetails;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.CustomerProductAlignment;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.model.cr.ChangeRequestApproverDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequestBusinessReason;
import com.cognizant.opserv.sp.model.cr.ChangeRequestDefinition;
import com.cognizant.opserv.sp.model.cr.CustomerAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.model.cr.ZipAlignmentChangeRequestDetails;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestOfflineDAOService;
import com.cognizant.opserv.sp.changerequest.json.ChangeRequestDetailsBlob;
import com.cognizant.opserv.sp.changerequest.json.ChangeRequestZipAlignmentDetailsBlob;

/**
 * The Class ChangeRequestAssembler.
 */
@Component
public class ChangeRequestAssembler {

	/**
	 * tempChngreqDAOService
	 */
	@Autowired
	private ChangeRequestOfflineDAOService tempChngreqDAOService;
	
	/**
	 * Convert t chng req to change request model.
	 *
	 * @param changeRequestList the change request list
	 * @return the list
	 */
	public List<ChangeRequest> convertTChngReqToChangeRequestModel(List<TChngReq> changeRequestList) {
		List<ChangeRequest> changeRequestModelList = new ArrayList<ChangeRequest>();
		for (TChngReq req : changeRequestList) {
			ChangeRequest changeReq = new ChangeRequest();
			changeReq.setId(req.getChngReqId());
			changeReq.setCreatedDate(req.getCreateDt());
			changeReq.setCreatedBy(req.getCreatedBy());
			changeReq.setTenantId(req.getTenantId());
			changeReq.setUpdatedDate(req.getUpdateDt());
			changeReq.setUpdatedBy(req.getUpdatedBy());
			if (req.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
				changeReq.setActive(CommonConstants.TRUE);
			} else {
				changeReq.setActive(CommonConstants.FALSE);
			}
			changeReq.setBusinessReason(req.getBussReason());
			changeReq.setComments(req.getComments());
			if (null != req.getChngReqNum()) {
				changeReq.setCode(req.getChngReqNum().toString());
				changeReq.setName(req.getChngReqNum().toString());
			}
			Alignment alignmment = new Alignment();
			alignmment.setId(req.getTChngReqConfig().getAlignmentId());
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(req.getTChngReqConfig().getSalesTeamId());
			alignmment.setSalesTeam(salesTeam);
			BusinessUnit busniessUnit = new BusinessUnit();
			busniessUnit.setId(req.getTChngReqConfig().getBussUnitId());
			alignmment.getSalesTeam().setBusinessUnit(busniessUnit);
			SalesPosition raisedSalesPostion = new SalesPosition();
			raisedSalesPostion.setId(req.getRaiseSalesPosId());
			SalesOrgHierarchy salesOrgHierarchyRaised = new SalesOrgHierarchy();
			salesOrgHierarchyRaised.setId(req.getRaiseSalesHierId());
			raisedSalesPostion.setHierarchy(salesOrgHierarchyRaised);
			raisedSalesPostion.setAlignmment(alignmment);
			changeReq.setRaisedSalesPosition(raisedSalesPostion);
			SalesPosition reqestedSalesPostion = new SalesPosition();
			reqestedSalesPostion.setId(req.getReqSalesPosId());
			SalesOrgHierarchy salesOrgHierarchyRequest = new SalesOrgHierarchy();
			salesOrgHierarchyRequest.setId(req.getReqSalesHierId());
			reqestedSalesPostion.setHierarchy(salesOrgHierarchyRequest);
			reqestedSalesPostion.setAlignmment(alignmment);
			changeReq.setRequestedSalesPosition(reqestedSalesPostion);
			changeReq.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(req.getStatusId()));
			ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
			if (null != req.gettChngReqConfig()) {
				if (null != req.gettChngReqConfig().getPrApprTypeId()) {
					changeRequestDefinition.setPrimaryApprover(ChangeRequestApproverType.getChangeRequestApproverType(req.gettChngReqConfig().getPrApprTypeId()));
				}
				if (null != req.gettChngReqConfig().getSecApprTypeId()) {
					changeRequestDefinition.setSecondaryApprover(ChangeRequestApproverType.getChangeRequestApproverType(req.gettChngReqConfig().getSecApprTypeId()));
				}
				changeRequestDefinition.setTaskDuration(req.gettChngReqConfig().getDuration());
				if (null != req.gettChngReqConfig().getTChngReqTrigger()) {
					changeRequestDefinition.setTrigger(ChangeRequestTriggerType.getChangeRequestTriggerType(req.gettChngReqConfig().getTChngReqTrigger().getTriggerId()));
				}
				changeRequestDefinition.setCreatedBy(req.gettChngReqConfig().getCreatedBy());
				changeRequestDefinition.setCreatedDate(req.gettChngReqConfig().getCreateDt());
				changeRequestDefinition.setUpdatedBy(req.gettChngReqConfig().getUpdatedBy());
				changeRequestDefinition.setUpdatedDate(req.gettChngReqConfig().getUpdateDt());
				changeRequestDefinition.setReminderDuration(req.gettChngReqConfig().getDuration());
				if (null != req.gettChngReqConfig().getChngReqConfigId()) {
					changeRequestDefinition.setId(req.gettChngReqConfig().getChngReqConfigId().longValue());
				}
				if (req.gettChngReqConfig().getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
					changeRequestDefinition.setActive(CommonConstants.TRUE);
				} else {
					changeRequestDefinition.setActive(CommonConstants.FALSE);
				}

			}
			changeReq.setCrDefinition(changeRequestDefinition);
			changeRequestModelList.add(changeReq);

		}

		return changeRequestModelList;
	}

	/**
	 * Convert t chng req to change request model.
	 *
	 * @param changeRequest the change request
	 * @return the change request
	 */
	public ChangeRequest convertTChngReqToChangeRequestModel(TChngReq changeRequest) {

		ChangeRequest changeReq = new ChangeRequest();
		if(null != changeRequest){
			
			changeReq.setId(changeRequest.getChngReqId());
			changeReq.setCreatedDate(changeRequest.getCreateDt());
			changeReq.setCreatedBy(changeRequest.getCreatedBy());
			changeReq.setTenantId(changeRequest.getTenantId());
			changeReq.setStartDate(changeRequest.getSubmitDtTm());
			if(null!=changeRequest.getUpdateDt()){
				changeReq.setUpdatedDate(changeRequest.getUpdateDt());
			}
			if(null!=changeRequest.getUpdatedBy()){
				changeReq.setUpdatedBy(changeRequest.getUpdatedBy());
			}
			
			if (changeRequest.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
				changeReq.setActive(CommonConstants.TRUE);
			} else {
				changeReq.setActive(CommonConstants.FALSE);
			}
			if(null!=changeRequest.getBussReason()){
				changeReq.setBusinessReason(changeRequest.getBussReason());
			}
			if(null!=changeRequest.getComments()){
				changeReq.setComments(changeRequest.getComments());	
			}
			if (null != changeRequest.getChngReqNum()) {
				changeReq.setCode(changeRequest.getChngReqNum().toString());
				changeReq.setName(changeRequest.getChngReqNum().toString());
			}
			Alignment alignmment = new Alignment();
			alignmment.setId(changeRequest.getTChngReqConfig().getAlignmentId());
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(changeRequest.getTChngReqConfig().getSalesTeamId());
			alignmment.setSalesTeam(salesTeam);
			BusinessUnit busniessUnit = new BusinessUnit();
			busniessUnit.setId(changeRequest.getTChngReqConfig().getBussUnitId());
			alignmment.getSalesTeam().setBusinessUnit(busniessUnit);
			SalesPosition raisedSalesPostion = new SalesPosition();
			raisedSalesPostion.setId(changeRequest.getRaiseSalesPosId());
			raisedSalesPostion.setAlignmment(alignmment);
			SalesOrgHierarchy salesOrgHierarchyRaised = new SalesOrgHierarchy();
			salesOrgHierarchyRaised.setId(changeRequest.getRaiseSalesHierId());
			raisedSalesPostion.setHierarchy(salesOrgHierarchyRaised);
			changeReq.setRaisedSalesPosition(raisedSalesPostion);
			SalesPosition reqestedSalesPostion = new SalesPosition();
			reqestedSalesPostion.setId(changeRequest.getReqSalesPosId());
			reqestedSalesPostion.setAlignmment(alignmment);
			SalesOrgHierarchy salesOrgHierarchyRequest = new SalesOrgHierarchy();
			salesOrgHierarchyRequest.setId(changeRequest.getReqSalesHierId());
			reqestedSalesPostion.setHierarchy(salesOrgHierarchyRequest);
			changeReq.setRequestedSalesPosition(reqestedSalesPostion);
			changeReq.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(changeRequest.getStatusId()));
			ChangeRequestDefinition changeRequestDefinition = new ChangeRequestDefinition();
			if (null != changeRequest.gettChngReqConfig()) {
				if (null != changeRequest.gettChngReqConfig().getPrApprTypeId()) {
					changeRequestDefinition.setPrimaryApprover(ChangeRequestApproverType.getChangeRequestApproverType(changeRequest.gettChngReqConfig().getPrApprTypeId()));
				}
				if (null != changeRequest.gettChngReqConfig().getSecApprTypeId()) {
					changeRequestDefinition.setSecondaryApprover(ChangeRequestApproverType.getChangeRequestApproverType(changeRequest.gettChngReqConfig().getSecApprTypeId()));
				}
				changeRequestDefinition.setTaskDuration(changeRequest.gettChngReqConfig().getDuration());
				if (null != changeRequest.gettChngReqConfig().getTChngReqTrigger()) {
					changeRequestDefinition.setTrigger(ChangeRequestTriggerType.getChangeRequestTriggerType(changeRequest.gettChngReqConfig().getTChngReqTrigger().getTriggerId()));
				}
				changeRequestDefinition.setCreatedBy(changeRequest.gettChngReqConfig().getCreatedBy());
				changeRequestDefinition.setCreatedDate(changeRequest.gettChngReqConfig().getCreateDt());
				changeRequestDefinition.setUpdatedBy(changeRequest.gettChngReqConfig().getUpdatedBy());
				changeRequestDefinition.setUpdatedDate(changeRequest.gettChngReqConfig().getUpdateDt());
				changeRequestDefinition.setReminderDuration(changeRequest.gettChngReqConfig().getDuration());
				changeRequestDefinition.setAutoAction(ChangeRequestAutoActionType.getChangeRequestAutoActionType(changeRequest.gettChngReqConfig().getAutoAction()));
				if (null != changeRequest.gettChngReqConfig().getChngReqConfigId()) {
					changeRequestDefinition.setId(changeRequest.gettChngReqConfig().getChngReqConfigId().longValue());
				}
				if (changeRequest.gettChngReqConfig().getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
					changeRequestDefinition.setActive(CommonConstants.TRUE);
				} else {
					changeRequestDefinition.setActive(CommonConstants.FALSE);
				}

			}
			changeReq.setCrDefinition(changeRequestDefinition);
		}
		return changeReq;
	}
	
	
	/**
	 * Convert t chng req to change request model.
	 *
	 * @param changeRequest the change request
	 * @return the change request
	 */
	public ChangeRequest convertTChngReqToBasicChangeRequestModel(TChngReq changeRequest) {

		ChangeRequest changeReq = new ChangeRequest();
		if(null != changeRequest){
			changeReq.setId(changeRequest.getChngReqId());
			changeReq.setCreatedDate(changeRequest.getCreateDt());
			changeReq.setCreatedBy(changeRequest.getCreatedBy());
			changeReq.setTenantId(changeRequest.getTenantId());
			if(null!=changeRequest.getUpdateDt()){
				changeReq.setUpdatedDate(changeRequest.getUpdateDt());
			}
			if(null!=changeRequest.getUpdatedBy()){
				changeReq.setUpdatedBy(changeRequest.getUpdatedBy());
			}
			
			if (changeRequest.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
				changeReq.setActive(CommonConstants.TRUE);
			} else {
				changeReq.setActive(CommonConstants.FALSE);
			}
			if(null!=changeRequest.getBussReason()){
				changeReq.setBusinessReason(changeRequest.getBussReason());
			}
			if(null!=changeRequest.getComments()){
				changeReq.setComments(changeRequest.getComments());	
			}
			if (null != changeRequest.getChngReqNum()) {
				changeReq.setCode(changeRequest.getChngReqNum().toString());
				changeReq.setName(changeRequest.getChngReqNum().toString());
			}
			SalesPosition raisedSalesPostion = new SalesPosition();
			raisedSalesPostion.setId(changeRequest.getRaiseSalesPosId());
			SalesOrgHierarchy salesOrgHierarchyRaised = new SalesOrgHierarchy();
			salesOrgHierarchyRaised.setId(changeRequest.getRaiseSalesHierId());
			raisedSalesPostion.setHierarchy(salesOrgHierarchyRaised);
			changeReq.setRaisedSalesPosition(raisedSalesPostion);
			SalesPosition reqestedSalesPostion = new SalesPosition();
			reqestedSalesPostion.setId(changeRequest.getReqSalesPosId());
			SalesOrgHierarchy salesOrgHierarchyRequest = new SalesOrgHierarchy();
			salesOrgHierarchyRequest.setId(changeRequest.getReqSalesHierId());
			reqestedSalesPostion.setHierarchy(salesOrgHierarchyRequest);
			changeReq.setRequestedSalesPosition(reqestedSalesPostion);
			changeReq.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(changeRequest.getStatusId()));
		}
		return changeReq;
	}

	/**
	 * Convert t cust align chng req to customer alignment change request model.
	 *
	 * @param changeRequest the change request
	 * @return the list
	 */
	public List<CustomerAlignmentChangeRequestDetails> convertTCustAlignChngReqToCustomerAlignmentChangeRequestModel(TChngReq changeRequest) {
		CustomerAlignmentChangeRequestDetails customerAlignment;
		List<CustomerAlignmentChangeRequestDetails> customerAlignmentList = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		if (null != changeRequest) {
			for (TCustAlgmntChngReqDet chngReqDts : changeRequest.gettCustAlgmntChngReqDetss()) {
				customerAlignment = new CustomerAlignmentChangeRequestDetails();
				customerAlignment.setId(chngReqDts.getTCustAlgmntChngReqDetId().getCustAlgmntId());
				customerAlignment.setCreatedBy(chngReqDts.getCreatedBy());
				customerAlignment.setCreatedDate(chngReqDts.getCreateDt());
				customerAlignment.setUpdatedBy(chngReqDts.getUpdatedBy());
				customerAlignment.setUpdatedDate(chngReqDts.getUpdateDt());
				customerAlignment.setTenantId(chngReqDts.getTenantId());
				ChangeRequestDetailsBlob changeRequestDetailsBlob = JSONUtil.toObject(chngReqDts.getReqDetails(), ChangeRequestDetailsBlob.class);
				customerAlignment.setNewCustomerAlignment(changeRequestDetailsBlob.getUpdatedValue());
				customerAlignment.setOldCustomerAlignment(changeRequestDetailsBlob.getPreviousValue());
				ChangeRequest parentChangeRequest = convertTChngReqToChangeRequestModel(chngReqDts.getTChngReq());
				customerAlignment.setParentChangeRequest(parentChangeRequest);
				customerAlignmentList.add(customerAlignment);

			}
		}
		return customerAlignmentList;
	}
	
	/**
	 * Convert listof t cust align chng req to listof customer alignment change request model.
	 *
	 * @param tChngReq the t chng req
	 * @return the list
	 */
	public List<CustomerAlignmentChangeRequestDetails> convertListofTCustAlignChngReqToListofCustomerAlignmentChangeRequestModel(TChngReq tChngReq) {
		List<CustomerAlignmentChangeRequestDetails> customerAlignmentList = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		if (null != tChngReq.gettCustAlgmntChngReqDetss()) {
			for (TCustAlgmntChngReqDet tCustAlgmntChngReqDet : tChngReq.gettCustAlgmntChngReqDetss()) {
				CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = new CustomerAlignmentChangeRequestDetails();
				ChangeRequestDetailsBlob changeRequestDetailsBlob = JSONUtil.toObject(tCustAlgmntChngReqDet.getReqDetails(), ChangeRequestDetailsBlob.class);
				customerAlignmentChangeRequestDetails.setOldCustomerAlignment(changeRequestDetailsBlob.getPreviousValue());
				customerAlignmentChangeRequestDetails.setNewCustomerAlignment(changeRequestDetailsBlob.getUpdatedValue());
				customerAlignmentChangeRequestDetails.setCreatedBy(tCustAlgmntChngReqDet.getCreatedBy());
				customerAlignmentChangeRequestDetails.setCreatedDate(tCustAlgmntChngReqDet.getCreateDt());
				customerAlignmentChangeRequestDetails.setId(tCustAlgmntChngReqDet.getTCustAlgmntChngReqDetId().getCustAlgmntId());
				customerAlignmentChangeRequestDetails.setTenantId(tCustAlgmntChngReqDet.getTenantId());
				customerAlignmentChangeRequestDetails.setUpdatedBy(tCustAlgmntChngReqDet.getUpdatedBy());
				customerAlignmentChangeRequestDetails.setUpdatedDate(tCustAlgmntChngReqDet.getUpdateDt());
				customerAlignmentChangeRequestDetails.setComments(tCustAlgmntChngReqDet.getComments());
				ChangeRequest chngReq = new ChangeRequest();
				chngReq.setId(tChngReq.getChngReqId());
				UserDetails userDetails = new UserDetails();
				userDetails.setTenantId(tChngReq.getTenantId());
				List<TChngreqOffline> tTempChngreqList = tempChngreqDAOService.findTTempByChngreqId(chngReq, userDetails);
				CustomerBlobDetails customerBlobDetails = null;
				List<CustomerProductAlignment>  newCustomerProductAlignments = null;
				List<CustomerProductAlignment>  oldCustomerProductAlignments = null;
				if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
					for (TChngreqOffline tChngreqOffline : tTempChngreqList) {
						customerBlobDetails = JSONUtil.toObject(tChngreqOffline.getReqDetail(), CustomerBlobDetails.class);
						if (null != tCustAlgmntChngReqDet.gettCustAlgmnt() && null != customerBlobDetails.getTarCustomerAlignment()
								&& null != tCustAlgmntChngReqDet.gettCustAlgmnt().getCustAlgmntId() && null != customerBlobDetails.getTarCustomerAlignment().getId()
								&& tCustAlgmntChngReqDet.gettCustAlgmnt().getCustAlgmntId().equals(customerBlobDetails.getTarCustomerAlignment().getId())) {
							newCustomerProductAlignments = customerBlobDetails.getNewCustomerProductAlignments();
							oldCustomerProductAlignments = customerBlobDetails.getOldCustomerProductAlignments();
						}
					}
				} else if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
					for (TChngreqOffline tChngreqOffline : tTempChngreqList) {
						customerBlobDetails = JSONUtil.toObject(tChngreqOffline.getReqDetail(), CustomerBlobDetails.class);
						if (null != tCustAlgmntChngReqDet.gettCustAlgmnt() && null != customerBlobDetails.getTarCustomerAlignment()
								&& null != tCustAlgmntChngReqDet.gettCustAlgmnt().getCustAlgmntId() && null != customerBlobDetails.getSrcCustomerAlignment().getId()
								&& tCustAlgmntChngReqDet.gettCustAlgmnt().getCustAlgmntId().equals(customerBlobDetails.getSrcCustomerAlignment().getId())) {
							newCustomerProductAlignments = customerBlobDetails.getNewCustomerProductAlignments();
							oldCustomerProductAlignments = customerBlobDetails.getOldCustomerProductAlignments();
						}
					}
				}
				customerAlignmentChangeRequestDetails.setNewCustomerProductAlignments(newCustomerProductAlignments);
				customerAlignmentChangeRequestDetails.setOldCustomerProductAlignments(oldCustomerProductAlignments);
				customerAlignmentChangeRequestDetails.setChangeRequestBusinessReason(convertTChnageRequestBusineesReasonEntityToChangeRequestBusinessReasonModel(tCustAlgmntChngReqDet));
				customerAlignmentList.add(customerAlignmentChangeRequestDetails);

			}
		}
		return customerAlignmentList;

	}
	
	
	/**
	 * Convert listof t cust align chng req to listof customer alignment change request model.
	 *
	 * @param tChngReq the t chng req
	 * @return the list
	 */
	public List<CustomerAlignmentChangeRequestDetails> convertListofTCustAlignmentChngReqToListofCustomerAlignmentChangeRequestModel(TChngReq tChngReq) {

		List<CustomerAlignmentChangeRequestDetails> customerAlignmentList = new ArrayList<CustomerAlignmentChangeRequestDetails>();
		if (null != tChngReq.gettCustAlgmntChngReqDetss()) {
			for (TCustAlgmntChngReqDet tCustAlgmntChngReqDet : tChngReq.gettCustAlgmntChngReqDetss()) {
				CustomerAlignmentChangeRequestDetails customerAlignmentChangeRequestDetails = new CustomerAlignmentChangeRequestDetails();
				ChangeRequestDetailsBlob changeRequestDetailsBlob = JSONUtil.toObject(tCustAlgmntChngReqDet.getReqDetails(), ChangeRequestDetailsBlob.class);
				customerAlignmentChangeRequestDetails.setOldCustomerAlignment(changeRequestDetailsBlob.getPreviousValue());
				customerAlignmentChangeRequestDetails.setNewCustomerAlignment(changeRequestDetailsBlob.getUpdatedValue());
				customerAlignmentChangeRequestDetails.setCreatedBy(tCustAlgmntChngReqDet.getCreatedBy());
				customerAlignmentChangeRequestDetails.setCreatedDate(tCustAlgmntChngReqDet.getCreateDt());
				customerAlignmentChangeRequestDetails.setId(tCustAlgmntChngReqDet.getTCustAlgmntChngReqDetId().getCustAlgmntId());
				customerAlignmentChangeRequestDetails.setTenantId(tCustAlgmntChngReqDet.getTenantId());
				customerAlignmentChangeRequestDetails.setUpdatedBy(tCustAlgmntChngReqDet.getUpdatedBy());
				customerAlignmentChangeRequestDetails.setUpdatedDate(tCustAlgmntChngReqDet.getUpdateDt());
				customerAlignmentChangeRequestDetails.setComments(tCustAlgmntChngReqDet.getComments());
				ChangeRequest chngReq = new ChangeRequest();
				chngReq.setId(tChngReq.getChngReqId());
				UserDetails userDetails = new UserDetails();
				userDetails.setTenantId(tChngReq.getTenantId());
				ChangeRequestDetailsBlob blob = JSONUtil.toObject(tCustAlgmntChngReqDet.getReqDetails(), ChangeRequestDetailsBlob.class);
				List<CustomerProductAlignment> newCustomerProductAlignments = null;
				List<CustomerProductAlignment> oldCustomerProductAlignments = null;
				if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.EDIT_CUSTOMER.getId())) {
					if (null != blob && null != blob.getNewCustomerProductAlignments()) {
						newCustomerProductAlignments = blob.getNewCustomerProductAlignments();
					}
					else if (null != blob && null != blob.getOldCustomerProductAlignments()) {
						oldCustomerProductAlignments = blob.getOldCustomerProductAlignments();
					}

				}
				else if (tChngReq.getTriggerId().equals(ChangeRequestTriggerType.PULL_CUSTOMER.getId())) {
					if (null != blob && null != blob.getNewCustomerProductAlignments()) {
						newCustomerProductAlignments = blob.getNewCustomerProductAlignments();
					}
					else if (null != blob && null != blob.getOldCustomerProductAlignments()) {
						oldCustomerProductAlignments = blob.getOldCustomerProductAlignments();
					}
				}
				customerAlignmentChangeRequestDetails.setNewCustomerProductAlignments(newCustomerProductAlignments);
				customerAlignmentChangeRequestDetails.setOldCustomerProductAlignments(oldCustomerProductAlignments);
				customerAlignmentChangeRequestDetails.setChangeRequestBusinessReason(convertTChnageRequestBusineesReasonEntityToChangeRequestBusinessReasonModel(tCustAlgmntChngReqDet));
				customerAlignmentList.add(customerAlignmentChangeRequestDetails);

			}
		}
		return customerAlignmentList;

	}
	
	/**
	 * Convert listof t cust align chng req to listof zip alignment change request model.
	 *
	 * @param tChngReq the t chng req
	 * @return the list
	 */
	public List<ZipAlignmentChangeRequestDetails> convertListofTCustAlignChngReqToListofZipAlignmentChangeRequestModel(TChngReq tChngReq) {
		List<ZipAlignmentChangeRequestDetails> zipAlignmentList = new ArrayList<ZipAlignmentChangeRequestDetails>();
		if (null != tChngReq.gettTerrZipAlgmntChngReqDetss()) {
			for (TTerrZipAlgmntChngReqDet tTerrZipAlgmntChngReqDet : tChngReq.gettTerrZipAlgmntChngReqDetss()) {
				ZipAlignmentChangeRequestDetails zipAlignmentChangeRequestDetails = new ZipAlignmentChangeRequestDetails();
				ChangeRequestZipAlignmentDetailsBlob changeRequestDetailsBlob = JSONUtil.toObject(tTerrZipAlgmntChngReqDet.getReqDetail(),
						ChangeRequestZipAlignmentDetailsBlob.class);
				zipAlignmentChangeRequestDetails.setOldGeographyAlignmentObject(changeRequestDetailsBlob.getPreviousValue());
				zipAlignmentChangeRequestDetails.setNewGeographyAlignmentObject(changeRequestDetailsBlob.getUpdatedValue());
				zipAlignmentChangeRequestDetails.setCreatedBy(tTerrZipAlgmntChngReqDet.getCreatedBy());
				zipAlignmentChangeRequestDetails.setId(tTerrZipAlgmntChngReqDet.getZipChngReqId().longValue());
				zipAlignmentChangeRequestDetails.setTenantId(tTerrZipAlgmntChngReqDet.getTenantId());
				zipAlignmentChangeRequestDetails.setUpdatedBy(tTerrZipAlgmntChngReqDet.getUpdatedBy());
				zipAlignmentChangeRequestDetails.setUpdatedDate(tTerrZipAlgmntChngReqDet.getUpdateDt());
				zipAlignmentChangeRequestDetails.setCreatedDate(tTerrZipAlgmntChngReqDet.getCreateDt());
				zipAlignmentChangeRequestDetails.setName(tTerrZipAlgmntChngReqDet.getPostalCd());
				zipAlignmentChangeRequestDetails.setComments(tTerrZipAlgmntChngReqDet.getComments());
				zipAlignmentChangeRequestDetails.setChangeRequestBusinessReason(convertTChnageRequestBusineesReasonEntityToChangeRequestBusinessReasonModel(tTerrZipAlgmntChngReqDet));
				zipAlignmentList.add(zipAlignmentChangeRequestDetails);

			}
		}
		return zipAlignmentList;

	}
	
	/**
	 * Convert t chnage request businees reason entity to change request business reason model.
	 *
	 * @param tCustAlgmntChngReqDet the t cust algmnt chng req det
	 * @return the change request business reason
	 */
	public ChangeRequestBusinessReason convertTChnageRequestBusineesReasonEntityToChangeRequestBusinessReasonModel(TCustAlgmntChngReqDet tCustAlgmntChngReqDet) {
		ChangeRequestBusinessReason changeRequestBusinessReason = new ChangeRequestBusinessReason();
		TChngReqBussReason tChngReqBussReason = tCustAlgmntChngReqDet.getTChngReqBussReason();
		if (null != tChngReqBussReason) {
			changeRequestBusinessReason.setId(tChngReqBussReason.getChngReqBrId());
			SalesPosition salesPostion = new SalesPosition();
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesPostion.setHierarchy(salesOrgHierarchy);
			Alignment alignment = new Alignment();
			alignment.setId(tChngReqBussReason.getAlgmntId());
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(tChngReqBussReason.getSalesTeamId());
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(tChngReqBussReason.getBussUnitId());
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPostion.setAlignmment(alignment);
			changeRequestBusinessReason.setSalesPosition(salesPostion);
			if (tChngReqBussReason.getActiveFlag().equals('Y')) {
				changeRequestBusinessReason.setActive(true);
			} else {
				changeRequestBusinessReason.setActive(false);
			}
			changeRequestBusinessReason.setBusinessReason(tChngReqBussReason.getTBussReason().getBussReasonId());
			changeRequestBusinessReason.setChangeRequestType(tChngReqBussReason.getChngType());
			changeRequestBusinessReason.setCreatedBy(tChngReqBussReason.getCreatedBy());
			changeRequestBusinessReason.setCreatedDate(tChngReqBussReason.getCreateDt());
			changeRequestBusinessReason.setTenantId(tChngReqBussReason.getTenantId());
			if (null != tChngReqBussReason.getTChngReqTrigger()) {
				changeRequestBusinessReason.setTrigger(ChangeRequestTriggerType.getChangeRequestTriggerType(tChngReqBussReason.getTChngReqTrigger().getTriggerId()));
			}
			changeRequestBusinessReason.setUpdatedBy(tChngReqBussReason.getUpdatedBy());
			changeRequestBusinessReason.setUpdatedDate(tChngReqBussReason.getUpdateDt());
			changeRequestBusinessReason.setCategoryId(tChngReqBussReason.getCategoryId());
		}
		return changeRequestBusinessReason;
	}
	
	
	/**
	 * Convert t chnage request businees reason entity to change request business reason model.
	 *
	 * @param tCustAlgmntChngReqDet the t cust algmnt chng req det
	 * @return the change request business reason
	 */
	public ChangeRequestBusinessReason convertTChnageRequestBusineesReasonEntityToChangeRequestBusinessReasonModel(TTerrZipAlgmntChngReqDet tCustAlgmntChngReqDet) {
		ChangeRequestBusinessReason changeRequestBusinessReason = new ChangeRequestBusinessReason();
		TChngReqBussReason tChngReqBussReason = tCustAlgmntChngReqDet.getTChngReqBussReason();
		if (null != tChngReqBussReason) {
			changeRequestBusinessReason.setId(tChngReqBussReason.getChngReqBrId());
			SalesPosition salesPostion = new SalesPosition();
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			salesPostion.setHierarchy(salesOrgHierarchy);
			Alignment alignment = new Alignment();
			alignment.setId(tChngReqBussReason.getAlgmntId());
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(tChngReqBussReason.getSalesTeamId());
			BusinessUnit businessUnit = new BusinessUnit();
			businessUnit.setId(tChngReqBussReason.getBussUnitId());
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			salesPostion.setAlignmment(alignment);
			changeRequestBusinessReason.setSalesPosition(salesPostion);
			if (tChngReqBussReason.getActiveFlag().equals('Y')) {
				changeRequestBusinessReason.setActive(true);
			} else {
				changeRequestBusinessReason.setActive(false);
			}
			changeRequestBusinessReason.setBusinessReason(tChngReqBussReason.getTBussReason().getBussReasonId());
			changeRequestBusinessReason.setChangeRequestType(tChngReqBussReason.getChngType());
			changeRequestBusinessReason.setCreatedBy(tChngReqBussReason.getCreatedBy());
			changeRequestBusinessReason.setCreatedDate(tChngReqBussReason.getCreateDt());
			changeRequestBusinessReason.setTenantId(tChngReqBussReason.getTenantId());
			if (null != tChngReqBussReason.getTChngReqTrigger()) {
				changeRequestBusinessReason.setTrigger(ChangeRequestTriggerType.getChangeRequestTriggerType(tChngReqBussReason.getTChngReqTrigger().getTriggerId()));
			}
			changeRequestBusinessReason.setUpdatedBy(tChngReqBussReason.getUpdatedBy());
			changeRequestBusinessReason.setUpdatedDate(tChngReqBussReason.getUpdateDt());
			changeRequestBusinessReason.setCategoryId(tChngReqBussReason.getCategoryId());
		}
		return changeRequestBusinessReason;
	}

	
	/**
	 * @param tChngReqAppr
	 * @param userDetails
	 * @return
	 */
	public ChangeRequestApproverDetails convertTChngApprToChangeReqApprModel(TChngReqAppr tChngReqAppr, UserDetails userDetails) {
		ChangeRequestApproverDetails changeRequestApproverDetails = new ChangeRequestApproverDetails();
		if (null != tChngReqAppr) {

			changeRequestApproverDetails.setChngReqId(tChngReqAppr.gettChngReq().getChngReqId());
			if (null != tChngReqAppr.getApprSalesPosId()) {
				changeRequestApproverDetails.setApprSalesPosId(tChngReqAppr.getApprSalesPosId());
			}
			if (null != tChngReqAppr.getApprSalesHierId()) {
				changeRequestApproverDetails.setApprSalesHierId(tChngReqAppr.getApprSalesHierId());
			}
			changeRequestApproverDetails.setTargetApprFlag(tChngReqAppr.getTargetApprFlag());
			changeRequestApproverDetails.setActionDtTm(tChngReqAppr.getActionDtTm());
			if (null != tChngReqAppr.getActionBy()) {
				changeRequestApproverDetails.setActionBy(tChngReqAppr.getActionBy());
			}
			changeRequestApproverDetails.setCreatedDate(tChngReqAppr.getCreateDt());
			changeRequestApproverDetails.setCreatedBy(tChngReqAppr.getCreatedBy());
			if (null != tChngReqAppr.getUpdateDt()) {
				changeRequestApproverDetails.setUpdatedDate(tChngReqAppr.getUpdateDt());
			}
			if (null != tChngReqAppr.getUpdatedBy()) {
				changeRequestApproverDetails.setUpdatedBy(tChngReqAppr.getUpdatedBy());
			}
			changeRequestApproverDetails.setTenantId(userDetails.getTenantId());
			if (null != tChngReqAppr.getComments()) {
				changeRequestApproverDetails.setComments(tChngReqAppr.getComments());
			}
			if (null != tChngReqAppr.getApprId()) {
				changeRequestApproverDetails.setId(tChngReqAppr.getApprId().longValue());
			}
			changeRequestApproverDetails.setActive(tChngReqAppr.getActiveFlag() == 'Y' ? true : false);
			if (null != tChngReqAppr.getStatusId()) {
				changeRequestApproverDetails.setChangeRequestStatusType(ChangeRequestStatusType.getChangeRequestStatusType(tChngReqAppr.getStatusId()));
			}
			if (null != tChngReqAppr.getApprTypeId()) {
				changeRequestApproverDetails.setChangeRequestApproverType(ChangeRequestApproverType.getChangeRequestApproverType(tChngReqAppr.getApprTypeId()));
			}
		}
		return changeRequestApproverDetails;
	}
	
	/**
	 * Convert t chng req to change request model basic data.
	 *
	 * @param changeRequest the change request
	 * @return the change request
	 */
	public ChangeRequest convertTChngReqToChangeRequestModelBasicData(TChngReq changeRequest) {

		ChangeRequest changeReq = new ChangeRequest();
		changeReq.setId(changeRequest.getChngReqId());
		changeReq.setCreatedDate(changeRequest.getCreateDt());
		changeReq.setCreatedBy(changeRequest.getCreatedBy());
		changeReq.setTenantId(changeRequest.getTenantId());
		changeReq.setUpdatedDate(changeRequest.getUpdateDt());
		changeReq.setUpdatedBy(changeRequest.getUpdatedBy());
		if (changeRequest.getActiveFlag().equals(CommonConstants.ACTIVE_Y)) {
			changeReq.setActive(CommonConstants.TRUE);
		} else {
			changeReq.setActive(CommonConstants.FALSE);
		}
		changeReq.setBusinessReason(changeRequest.getBussReason());
		changeReq.setComments(changeRequest.getComments());
		if (null != changeRequest.getChngReqNum()) {
			changeReq.setCode(changeRequest.getChngReqNum().toString());
			changeReq.setName(changeRequest.getChngReqNum().toString());
		}
		changeReq.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(changeRequest.getStatusId()));
		return changeReq;
	}
	
}
