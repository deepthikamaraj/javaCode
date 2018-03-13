/* COPYRIGHT (C) 2014 Cognizant, all rights reserved.*/
package com.cognizant.opserv.sp.service.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.dao.TChngReqApprDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqApprHierDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqApprTypeDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqConfigDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.core.dao.TChngReqTriggerDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntChngReqDetDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntChngReqDetDAO;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngReqConfig;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.ChangeRequestDAOService;
import com.cognizant.opserv.sp.service.common.SPAssignmentsViewService;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************
 * Class Name: ChangeRequestIntServiceImpl.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 15/04/2016
 * *****************************************************************************
 */
@Service
public class ChangeRequestIntServiceImpl implements ChangeRequestIntService {

	/**
	 * LOGGER
	 */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ChangeRequestIntServiceImpl.class);
	/**
	 * changeRequestDao
	 */
	@Autowired
	private TChngReqDAO changeRequestDao;

	/**
	 * changeRequestConfigDAO
	 */
	@Autowired
	private TChngReqConfigDAO changeRequestConfigDAO;

	/**
	 * customerAlignmentDAO
	 */
	@Autowired
	private TCustAlgmntDAO customerAlignmentDAO;

	/**
	 * changeRequestTriggerDAO
	 */
	@Autowired
	private TChngReqTriggerDAO changeRequestTriggerDAO;

	/**
	 * changeRequestApproverTypeDAO
	 */
	@Autowired
	private TChngReqApprTypeDAO changeRequestApproverTypeDAO;

	/**
	 * salesPositionDAO
	 */
	@Autowired
	private TSalesPosDAO salesPositionDAO;

	/**
	 * changeRequestApproverHierarchyDAO
	 */
	@Autowired
	private TChngReqApprHierDAO changeRequestApproverHierarchyDAO;
	
	/**
	 * changeRequestApproverDAO
	 */
	@Autowired
	private TChngReqApprDAO changeRequestApproverDAO;
	
	/**
	 * customerAligmentChnageRequestDetailsDAO
	 */
	@Autowired
	private TCustAlgmntChngReqDetDAO customerAligmentChnageRequestDetailsDAO;
	
	/**
	 * changeRequestDAOService
	 */
	@Autowired
	private ChangeRequestDAOService changeRequestDAOService;
	
	/**
	 * metricService
	 *//*
	@Autowired
	private MetricService metricService;*/
	
	
	/**
	 * tTerrZipAlgmntChngReqDetDAO
	 */
	@Autowired
	private TTerrZipAlgmntChngReqDetDAO tTerrZipAlgmntChngReqDetDAO;
	
	/**
	 * spAssignmentsViewService
	 */
	@Autowired
	private SPAssignmentsViewService spAssignmentsViewService;

	/** (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#findExistingChangeRequest(com.cognizant.opserv.sp.model.CustomerAlignment, com.cognizant.opserv.sp.model.SalesPosition, com.cognizant.opserv.sp.model.auth.UserDetails, java.lang.Integer)
	 */
	@Override
	public ChangeRequest findExistingChangeRequestId(SalesPosition targetSalesPostion, SalesPosition sourceSalesPostion, UserDetails userDetails, Integer triggerId) {
		LOGGER.info("Inside ChangeRequestIntServiceImpl --> findExistingChangeRequestId");
		ChangeRequest changeRequest = null;
		TChngReq tChngReq = null;
		TChngReqConfig tChngReqConfig = null;
		List<Long> changeRequestList = changeRequestDao.getChangeRequestIdByStatus(sourceSalesPostion.getId(), sourceSalesPostion.getHierarchy().getId(),targetSalesPostion.getId(),targetSalesPostion.getHierarchy().getId(),
				ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId(), triggerId, userDetails.getTenantId());
		if (changeRequestList != null && changeRequestList.size() > 0) {
			Long chnageRequestId = changeRequestList.get(0);
			changeRequest = new ChangeRequest();
			changeRequest.setId(chnageRequestId);
		} else {

			List<TChngReqConfig> changeRequestConfigList = changeRequestConfigDAO.getTChngReqConfig(sourceSalesPostion.getAlignmment().getSalesTeam().getId(),
					sourceSalesPostion.getAlignmment().getId(), sourceSalesPostion.getAlignmment().getSalesTeam().getBusinessUnit().getId(),
					triggerId, userDetails.getTenantId());
			if (changeRequestConfigList != null && changeRequestConfigList.size() > 0) {
				if (changeRequestConfigList != null && changeRequestConfigList.size() == 1) {
					tChngReqConfig = changeRequestConfigList.get(0);
					tChngReq = new TChngReq();
					if(null!=tChngReqConfig.getChngReqConfigId()){
					tChngReq.setChngReqConfigId(tChngReqConfig.getChngReqConfigId());
					}
					tChngReq.setChngReqNum((long) 1000000);
					if(null!=sourceSalesPostion.getId()){
					tChngReq.setReqSalesPosId(sourceSalesPostion.getId());
					}
					if(null!=sourceSalesPostion.getHierarchy() && null!=sourceSalesPostion.getHierarchy().getId()){
					tChngReq.setReqSalesHierId(sourceSalesPostion.getHierarchy().getId());
					}
					if(null!=targetSalesPostion && null!=targetSalesPostion.getId()){
					tChngReq.setRaiseSalesPosId(targetSalesPostion.getId());
					}
					if(null!=targetSalesPostion && null!= targetSalesPostion.getHierarchy() && null!= targetSalesPostion.getHierarchy().getId()){
					tChngReq.setRaiseSalesHierId(targetSalesPostion.getHierarchy().getId());
					}
					if(null!=ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId()){
					tChngReq.setStatusId(ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId());
					}
					tChngReq.setActiveFlag(CommonConstants.ACTIVE_Y);
					if(null!=userDetails.getUserId()){
					tChngReq.setCreatedBy(userDetails.getUserId());
					tChngReq.setActionBy(userDetails.getUserId());
					}
					tChngReq.setCreateDt(DateUtil.getCurrentDate());
					tChngReq.setTenantId(userDetails.getTenantId());
					tChngReq.setTriggerId(triggerId);
					tChngReq.setLastSalesPosId(targetSalesPostion.getId());
					tChngReq.setLastSalesHierId(targetSalesPostion.getHierarchy().getId());
					if(null!=userDetails.getUserId()){
					tChngReq.setUpdatedBy(userDetails.getUserId());
					}
					tChngReq.setUpdateDt(DateUtil.getCurrentDate());
					changeRequestDao.createTChngReq(tChngReq);
					
				}
			}
		}
		if(changeRequest == null){
			changeRequest = new ChangeRequest();
			changeRequest.setId(tChngReq.getChngReqId());
		}
		
		return changeRequest;
	}
	
	/**
	 * @param metricService the metricService to set
	 */
	/*public void setMetricExecutionService(
			MetricService metricService) {
		this.metricService = metricService;
	}*/
	
	/**
	 * @param changeRequestConfigDAO the changeRequestConfigDAO to set
	 */
	public void setChangeRequestConfigDAO(TChngReqConfigDAO changeRequestConfigDAO) {
		this.changeRequestConfigDAO = changeRequestConfigDAO;
	}

	/**
	 * @param customerAlignmentDAO the customerAlignmentDAO to set
	 */
	public void setCustomerAlignmentDAO(TCustAlgmntDAO customerAlignmentDAO) {
		this.customerAlignmentDAO = customerAlignmentDAO;
	}

	/**
	 * @param changeRequestTriggerDAO the changeRequestTriggerDAO to set
	 */
	public void setChangeRequestTriggerDAO(TChngReqTriggerDAO changeRequestTriggerDAO) {
		this.changeRequestTriggerDAO = changeRequestTriggerDAO;
	}

	/**
	 * @param changeRequestApproverTypeDAO the changeRequestApproverTypeDAO to set
	 */
	public void setChangeRequestApproverTypeDAO(TChngReqApprTypeDAO changeRequestApproverTypeDAO) {
		this.changeRequestApproverTypeDAO = changeRequestApproverTypeDAO;
	}

	/**
	 * @param salesPositionDAO the salesPositionDAO to set
	 */
	public void setSalesPositionDAO(TSalesPosDAO salesPositionDAO) {
		this.salesPositionDAO = salesPositionDAO;
	}

	/**
	 * @param changeRequestApproverHierarchyDAO the changeRequestApproverHierarchyDAO to set
	 */
	public void setChangeRequestApproverHierarchyDAO(TChngReqApprHierDAO changeRequestApproverHierarchyDAO) {
		this.changeRequestApproverHierarchyDAO = changeRequestApproverHierarchyDAO;
	}

	/**
	 * @param changeRequestApproverDAO the changeRequestApproverDAO to set
	 */
	public void setChangeRequestApproverDAO(TChngReqApprDAO changeRequestApproverDAO) {
		this.changeRequestApproverDAO = changeRequestApproverDAO;
	}

	/**
	 * @param customerAligmentChnageRequestDetailsDAO the customerAligmentChnageRequestDetailsDAO to set
	 */
	public void setCustomerAligmentChnageRequestDetailsDAO(TCustAlgmntChngReqDetDAO customerAligmentChnageRequestDetailsDAO) {
		this.customerAligmentChnageRequestDetailsDAO = customerAligmentChnageRequestDetailsDAO;
	}

	/**
	 * @param changeRequestDAOService the changeRequestDAOService to set
	 */
	public void setChangeRequestDAOService(ChangeRequestDAOService changeRequestDAOService) {
		this.changeRequestDAOService = changeRequestDAOService;
	}

	/**
	 * @param tTerrZipAlgmntChngReqDetDAO the tTerrZipAlgmntChngReqDetDAO to set
	 */
	public void settTerrZipAlgmntChngReqDetDAO(TTerrZipAlgmntChngReqDetDAO tTerrZipAlgmntChngReqDetDAO) {
		this.tTerrZipAlgmntChngReqDetDAO = tTerrZipAlgmntChngReqDetDAO;
	}


	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.internal.ChangeRequestIntService#updateChangeRequestStatus(long, java.lang.Integer, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public void updateChangeRequestStatus(long changeRequestId, Integer statusId, UserDetails userDetails)
			throws ChangeRequestServiceException {
		
		try {
			if(null!=statusId && null!=userDetails){
				changeRequestDAOService.updateChangeRequestStatus(changeRequestId, statusId, userDetails);	
			}
		} catch (OpservDataAccessException exception) {
			Object[] args = new Object[]{"CR Status updation failed!!"};
			throw new ChangeRequestServiceException(ChangeRequestServiceException.ChangeRequestServiceExceptionCode.CR_SER_EX_CD_001, "updation_exception", args, exception);
		}
		
	}

	

	
}
