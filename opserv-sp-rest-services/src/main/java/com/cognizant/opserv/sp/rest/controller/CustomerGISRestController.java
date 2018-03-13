package com.cognizant.opserv.sp.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.rest.util.ModelAssembler;
import com.cognizant.opserv.sp.service.gis.CustomerGISService;
import com.cognizant.peg.core.common.ISearchCriteria;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.rest.common.StatusCode;
import com.cognizant.peg.web.common.ServiceResponse;
import com.cognizant.peg.web.common.ServiceResult;
import com.cognizant.peg.web.common.ServiceStatus;

@Controller
public class CustomerGISRestController {
	
	/*
	 * Instantiate the Logger
	 */	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(CustomerGISRestController.class);
	
	@Autowired
	private CustomerGISService customerGISService;
	
	@RequestMapping(value="/gis/customer", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ServiceResponse<CustomerAlignment> getCustomers(@RequestParam("query") String query,
			@RequestParam("al") Long al, @RequestParam("bu") Long bu,@RequestParam("st") Long st, 
			HttpServletRequest request) {
		Date before = new Date();
		ServiceStatus serviceStatus = null;
		ServiceResponse<CustomerAlignment> serviceResponse = new ServiceResponse<CustomerAlignment>();
		ServiceResult<CustomerAlignment> serviceResult = new ServiceResult<CustomerAlignment>();

		try {
			Alignment alignment = new Alignment();
			alignment.setId(al);
			SalesTeam salesTeam = new SalesTeam();
			salesTeam.setId(st);
			BusinessUnit bussUnit = new BusinessUnit();
			bussUnit.setId(bu);
			salesTeam.setBusinessUnit(bussUnit);
			alignment.setSalesTeam(salesTeam);
			UserDetails userDetails = ModelAssembler.getDefaultUserDetails();
			String[] paramValue = query.split(";");
//			ISearchCriteria mainCriteria = new SearchCriteria();
//			mainCriteria.addEqualTo(paramValue[0].split(":")[0], paramValue[0].split(":")[1]);
			
			IExpressionCriteria mainCriteria = null;
			if(paramValue[0].split(":")[0].equals(CommonConstants.SALES_HIER_ID) || paramValue[0].split(":")[0].equals(CommonConstants.SALES_POS_ID)){
				String[] value = paramValue[0].split(":")[1].split(",");
				List<Long> valueList = new ArrayList<Long>();
				for(int j=0;j<value.length;j++){
					valueList.add(Long.valueOf(value[j]));
				}
				mainCriteria = ExpressionCriteria.createInCriteria(paramValue[0].split(":")[0], valueList);
				if(paramValue.length > 1){
					for(int i=1;i<paramValue.length;i++){
						value = paramValue[i].split(":")[1].split(",");
						valueList.clear();
						for(int j=0;j<value.length;j++){
							valueList.add(Long.valueOf(value[j]));
						}
						IExpressionCriteria criteria = ExpressionCriteria.createInCriteria(paramValue[i].split(":")[0], valueList);
						mainCriteria.and(criteria);
					}
				}
			}
			else if(paramValue[0].split(":")[0].equals(CommonConstants.POSTAL_CD)){
				mainCriteria = ExpressionCriteria.createEqualToCriteria(paramValue[0].split(":")[0], paramValue[0].split(":")[1]);
			}
			else if(paramValue[0].split(":")[0].equals(CommonConstants.CUST_NAME) || paramValue[0].split(":")[0].equals(CommonConstants.CUST_CD)){
				mainCriteria = ExpressionCriteria.createStartsWithCriteria(paramValue[0].split(":")[0], paramValue[0].split(":")[1]);
			}
			IQuery q = new Query().criteria(mainCriteria);
			List<CustomerAlignment> customerAlgmntList = customerGISService.getCustomers(q, alignment, userDetails);
			serviceResult.setList(customerAlgmntList);
			serviceStatus = new ServiceStatus(StatusCode.OK, "200 OK");
			Date after = new Date();
			long diff = after.getTime() - before.getTime();
			LOGGER.info("time taken for the service: "+diff);
		} catch (AlignmentServiceException e) {
			serviceStatus = new ServiceStatus(StatusCode.SERVER_ERROR, "500 ERROR");
		}
		serviceResponse.setStatus(serviceStatus);
		serviceResponse.setResult(serviceResult);
		LOGGER.info(" Returning REST response with Result.."+serviceResult.getList()+" and status "+serviceResponse.getStatus().getCode().getCode()+"-"+serviceResponse.getStatus().getMessage());
		return serviceResponse;
	}	

}
