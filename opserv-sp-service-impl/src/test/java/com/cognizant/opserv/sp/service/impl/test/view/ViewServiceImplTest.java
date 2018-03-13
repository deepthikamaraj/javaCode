/**
 * 
 */
package com.cognizant.opserv.sp.service.impl.test.view;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.sp.core.dao.TBussEntityDAO;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.ViewData;
import com.cognizant.opserv.sp.model.ViewHeader;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.view.ViewService;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * @author 397318
 *
 */
public class ViewServiceImplTest extends BaseTest{
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ViewServiceImplTest.class);
	
	@Autowired
	private ViewService viewService;
	
	@Autowired
	private TBussEntityDAO bussEntityDAO;
	

	@Test
	public void testGetCustomerAlignmentViewHeaderViewService() throws ViewServiceException {
		UserDetails userDetails  = new UserDetails();
		userDetails.setTenantId((short) 1);
		ViewHeader viewHeader = viewService.getCustomerAlignmentViewHeader(userDetails);
		System.out.println(viewHeader);
		LOGGER.info("View Header :: "+viewHeader);
		Assert.assertNotNull(viewHeader);

	}
	
	@Test
	public void testGetCustomerAlignmentsViewService() throws ViewServiceException {
		UserDetails userDetails  = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		
		/*IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria("SalesPositionID",407);
		//IExpressionCriteria c3 = ExpressionCriteria.createEqualToCriteria("CustomerName","MARK ALLAN CLINE");
		
		//IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria("SalesPostionID",396);
		//IExpressionCriteria c3 = ExpressionCriteria.createEqualToCriteria("CustomerName","\"VED\"");
		
		IExpressionCriteria h = c1;
		PaginationInfo paginationInfo = new PaginationInfo(0, 10);
		IQuery q1 = new Query().criteria(h).pagination(paginationInfo);
		
		List<ViewData> objList = viewService.getCustomerAlignments(q1, userDetails);

		LOGGER.info("objList :: "+objList);
		Assert.assertNotNull(objList);*/
		
		IExpressionCriteria expressionSP = ExpressionCriteria.createEqualToCriteria("SalesPostionID", 398);
        //IExpressionCriteria expressionTGT;
        
        IExpressionCriteria expressionTargetCustomer = expressionSP;
        IQuery iqueryTargetCustomer = new Query().criteria(expressionTargetCustomer);
        
        //int resultCountForCustomerAlgnmntView = viewService.getResultCountForCustomerAlgnmntView(iqueryTargetCustomer, userDetails);    
        //This will return count 65
        
		PaginationInfo page = new PaginationInfo(20, 65);
        ViewData customerListData = viewService.getCustomerAlignments(iqueryTargetCustomer.pagination(page), userDetails);

        
        /*List<ViewData> objList = viewService.getCustomerAlignments(q1, userDetails);*/

        LOGGER.info("objList :: "+customerListData);
        Assert.assertNotNull(customerListData);


	}
	
	@Test
	public void testGetTbussEntityByBussObject(){
		String bussFunctionType = "Materialized View";
		String bussObjectName = "Customer Alignment view";
		short tenantId = 1;
		List<TBussEntity> bussEntity = bussEntityDAO.findTBussEntitiesByBussObjType(bussFunctionType, tenantId, bussObjectName);
		System.out.println(bussEntity);
	}
	
	@Test
	public void testGetResultCountBasedOnSearchCriteria() throws ViewServiceException{
		
		UserDetails userDetails  = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria("SalesPostionID",398);
		
		IExpressionCriteria h = c1;
		
		IQuery q1 = new Query().criteria(h);
		
		int count = viewService.getResultCountForCustomerAlgnmntView(q1, userDetails);
		System.out.println(count);
		Assert.assertNotNull(count);
	}
	
	@Test
	public void testGetChangeRequestHeaderViewService() throws ViewServiceException {
		UserDetails userDetails  = new UserDetails();
		userDetails.setTenantId((short) 1);
		ViewHeader viewHeader = viewService.getChangeRequestViewHeader(userDetails);
		System.out.println(viewHeader);
		LOGGER.info("View Header :: "+viewHeader);
		Assert.assertNotNull(viewHeader);

	}
	
	
	@Test
	public void testGetChangeRequestsViewService() throws ViewServiceException {
		UserDetails userDetails  = new UserDetails();
		userDetails.setTenantId((short) 1);
		
		
		IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria("chng_req_id",14);
		//IExpressionCriteria c3 = ExpressionCriteria.createEqualToCriteria("CustomerName","MARK ALLAN CLINE");
		
		//IExpressionCriteria c1 = ExpressionCriteria.createEqualToCriteria("SalesPostionID",396);
		//IExpressionCriteria c3 = ExpressionCriteria.createEqualToCriteria("CustomerName","\"VED\"");
		
//		IExpressionCriteria h = c1.or(c3);
		IExpressionCriteria h = c1;
		
		IQuery q1 = new Query().select("chng_req_id","comments").from("v_chng_req_list;").criteria(h);
		
//		IQuery q1 = new Query();
		
		ViewData objList = viewService.getChangeRequests(q1, userDetails);

		System.out.println(objList);
		Assert.assertNotNull(objList);

	}

}
