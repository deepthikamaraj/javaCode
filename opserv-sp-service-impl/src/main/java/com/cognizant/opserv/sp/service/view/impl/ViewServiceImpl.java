package com.cognizant.opserv.sp.service.view.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.sp.common.BussObj;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException.ViewServiceExceptionCode;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.SPViewDAOService;
import com.cognizant.opserv.sp.service.view.ViewService;
import com.cognizant.peg.core.common.JSONUtil;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class ViewServiceImpl contains all the view service
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 11/05/2016
 * 
 * ***************************************************************************
 */
@Service("viewService")
public class ViewServiceImpl implements ViewService {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ViewServiceImpl.class);
	
	@Autowired
	private SPViewDAOService spViewDAOService;

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.view.ViewService#getCustomerAlignmentViewHeader(com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public ViewHeader getCustomerAlignmentViewHeader(UserDetails userDetails) throws ViewServiceException {
		ViewHeader viewHeader = null;
		String bussObjectName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		viewHeader = getEntityBasedViewHeader(userDetails, viewHeader, bussObjectName, bussFunctionType);
		return viewHeader;
	}

	/**
	 * @param userDetails
	 * @param viewHeader
	 * @param entityId
	 * @param bussObjectName 
	 * @param bussFunctionType 
	 * @return ViewHeader
	 * @throws ViewServiceException
	 */
	private ViewHeader getEntityBasedViewHeader(UserDetails userDetails, ViewHeader viewHeader, String bussObjectName, String bussFunctionType) throws ViewServiceException {
		try{
			viewHeader = spViewDAOService.getEntityAlignmentViewHeader(userDetails, bussObjectName, bussFunctionType);
		} catch (OpservDataAccessException odae) {
			LOGGER.error("Exception in getCustomerAlignmentViewHearder in ViewServiceImpl :: ",odae);
				Object[] args = new Object[4];
				args[0] = CommonConstants.SPVIEW;
				args[1] = bussObjectName;
				throw new ViewServiceException(
						ViewServiceExceptionCode.VIEW_SER_EX_CD_001,
						CommonConstants.SPVIEW,
						args, odae);
		}
		return viewHeader;
	}

	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.view.ViewService#getCustomerAlignments(com.cognizant.peg.core.common.ISearchCriteria, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public ViewData getCustomerAlignments(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		String bussObjectName = BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		ViewData viewData = getEntityBasedViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		LOGGER.info("ViewDataList :: "+ JSONUtil.toJSON(viewData));
		return viewData;
	}

	/**
	 * @param criteria
	 * @param userDetails
	 * @param entityId 
	 * @param bussFunctionType 
	 * @return List<ViewData>
	 * @throws ViewServiceException
	 */
	private ViewData getEntityBasedViewData(IQuery queryStructure, UserDetails userDetails, String bussObjectName, String bussFunctionType) throws ViewServiceException {
		ViewData viewData;
		try{
			viewData = spViewDAOService.getEntityAlignmentViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		} catch (OpservDataAccessException odae) {
			LOGGER.error("Exception in getEntityBasedViewData in ViewServiceImpl :: ",odae);
			Object[] args = new Object[4];
			args[0] = CommonConstants.SPVIEW;
			args[1] = userDetails.getTenantId();
			args[2] = bussObjectName;
			throw new ViewServiceException(
					ViewServiceExceptionCode.VIEW_SER_EX_CD_002,
					CommonConstants.SPVIEW,
					args, odae);
		}
		return viewData;
	}
	
	
	/* (non-Javadoc)
	 * @see com.cognizant.opserv.sp.service.view.ViewService#getResultCountBasedOnSearchCriteria(com.cognizant.opserv.query.IQuery, com.cognizant.opserv.sp.model.auth.UserDetails)
	 */
	@Override
	public int getResultCountForCustomerAlgnmntView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("Inside getResultCountBasedOnSearchCriteria in ViewServiceImpl ");
		
		String bussObjectName =BussObj.CUSTOMER_ALIGNMENT_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		return getResultCountOnSearchCriteria(bussObjectName, bussFunctionType, queryStructure, userDetails);
	}
	
	@Override
	public int getResultCountForChangeRequestView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("Inside getResultCountForChangeRequestView in ViewServiceImpl ");
		
		String bussObjectName = BussObj.CHANGE_REQUEST_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		return getResultCountOnSearchCriteria(bussObjectName, bussFunctionType, queryStructure, userDetails);
	}

	
	private int getResultCountOnSearchCriteria(String bussObjectName, String bussFunctionType, IQuery queryStructure, UserDetails userDetails) throws ViewServiceException{
		int count = 0;
		try{
			count = spViewDAOService.getResultCountBasedOnSearchCriteria(queryStructure, userDetails, bussObjectName, bussFunctionType);
			LOGGER.info("=======The count in getResultCountOnSearchCriteria() of ViewServiceImpl class  is" +count);
		} catch (OpservDataAccessException odae) {
			LOGGER.error("Exception in getResultCountOnSearchCriteria in ViewServiceImpl :: ",odae);
			Object[] args = new Object[4];
			args[0] = CommonConstants.SPVIEW;
			args[1] = userDetails.getTenantId();
			args[2] = bussObjectName;
			throw new ViewServiceException(
					ViewServiceExceptionCode.VIEW_SER_EX_CD_002,
					CommonConstants.SPVIEW,
					args, odae);
		}
		return count;
	}
	
	@Override
	public ViewHeader getChangeRequestViewHeader(UserDetails userDetails) throws ViewServiceException {
		ViewHeader viewHeader = null;
		String bussObjectName = BussObj.CHANGE_REQUEST_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		viewHeader = getEntityBasedViewHeader(userDetails, viewHeader, bussObjectName, bussFunctionType);
		return viewHeader;
	}

	@Override
	public ViewData getChangeRequests(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		String bussObjectName = BussObj.CHANGE_REQUEST_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		ViewData viewData = getEntityBasedViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		return viewData;
	}

	@Override
	public ViewHeader getChangeRequestApproverViewHeader(UserDetails userDetails) throws ViewServiceException {
		ViewHeader viewHeader = null;
		String bussObjectName = CommonConstants.CHANGE_REQUEST_APPROVER_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		viewHeader = getEntityBasedViewHeader(userDetails, viewHeader, bussObjectName, bussFunctionType);
		return viewHeader;
	}

	@Override
	public ViewData getChangeRequestApprovers(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		String bussObjectName = CommonConstants.CHANGE_REQUEST_APPROVER_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		ViewData viewData = getEntityBasedViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		return viewData;
	}

	@Override
	public int getResultCountForChangeRequestApproverView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("Inside getResultCountForChangeRequestApproverView in ViewServiceImpl ");
		
		String bussObjectName = CommonConstants.CHANGE_REQUEST_APPROVER_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		return getResultCountOnSearchCriteria(bussObjectName, bussFunctionType, queryStructure, userDetails);
	}

	@Override
	public ViewHeader getCustomerUniverseViewHeader(UserDetails userDetails) throws ViewServiceException {
		ViewHeader viewHeader = null;
		String bussObjectName = CommonConstants.CUSTOMER_UNIVERSE_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		viewHeader = getEntityBasedViewHeader(userDetails, viewHeader, bussObjectName, bussFunctionType);
		return viewHeader;
	}

	@Override
	public ViewData getCustomerUniverse(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		String bussObjectName = CommonConstants.CUSTOMER_UNIVERSE_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		ViewData viewData = getEntityBasedViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		return viewData;
	}

	@Override
	public int getResultCountForCustomerUniverseView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("Inside getResultCountForCustomerUniverseView in ViewServiceImpl ");
		
		String bussObjectName = CommonConstants.CUSTOMER_UNIVERSE_VIEW;
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		return getResultCountOnSearchCriteria(bussObjectName, bussFunctionType, queryStructure, userDetails);
	}
	
	
	
	@Override
	public ViewHeader getZipSalesPosViewHeader(UserDetails userDetails) throws ViewServiceException {
		ViewHeader viewHeader = null;
		String bussObjectName = BussObj.ZIP_SALESPOS_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		viewHeader = getEntityBasedViewHeader(userDetails, viewHeader, bussObjectName, bussFunctionType);
		return viewHeader;
	}
	
	@Override
	public ViewData getZipSalesPos(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		String bussObjectName = BussObj.ZIP_SALESPOS_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		ViewData viewData = getEntityBasedViewData(queryStructure, userDetails, bussObjectName, bussFunctionType);
		LOGGER.info("ViewDataList :: "+ JSONUtil.toJSON(viewData));
		return viewData;
	}
	
	@Override
	public int getResultCountForZipSalesPosView(IQuery queryStructure, UserDetails userDetails) throws ViewServiceException {
		LOGGER.info("Inside getResultCountForZipSalesPosView in ViewServiceImpl ");
		
		String bussObjectName = BussObj.ZIP_SALESPOS_VIEW.getBussObjName();
		String bussFunctionType = CommonConstants.MATERIALIZED_VIEW;
		return getResultCountOnSearchCriteria(bussObjectName, bussFunctionType, queryStructure, userDetails);
	}

}
