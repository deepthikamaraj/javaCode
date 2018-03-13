package com.cognizant.opserv.sp.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cognizant.opserv.service.test.OpservServiceManager;
import com.cognizant.opserv.service.test.Service;
import com.cognizant.opserv.service.test.ServiceMethod;
import com.cognizant.opserv.sp.rest.ajax.AjaxResponseUtil;
import com.cognizant.opserv.sp.service.alignment.impl.EmployeeAlignmentServiceImpl;

@Controller
@RequestMapping(value = "/allServices/exposingRestServices")
public class RestServicesExposedAjaxController {
	
	@Autowired
	EmployeeAlignmentServiceImpl employeeAlignmentServiceImpl;
	
	@RequestMapping(value = "/displayMethods.do", method = RequestMethod.GET, produces ="application/json")
	@ResponseBody
	public  Map<String, String> displayMethodNames(@RequestParam("serviceName") String serviceInterfaceName, HttpServletRequest request) 
	{
		Map<String, String> methodsName = new HashMap<String, String>();
		boolean startClean = false;
		OpservServiceManager.setServiceEnvironment(startClean);	
		Map<String,Service> serviceMetadata = OpservServiceManager.getServicesInformation();
		Service service = serviceMetadata.get(serviceInterfaceName);
		List<ServiceMethod> methods = service.getServiceMethods();
		for(ServiceMethod serviceMethod : methods){
            methodsName.put(serviceMethod.getFolderName(), serviceMethod.getDisplayName());
		}
		return methodsName;
		
		
	}
		
	
	
	@RequestMapping(value = "/invokeMethod.do", method = RequestMethod.GET, produces ="application/json")
	@ResponseBody
	public String  displayMethodNames(@RequestParam("serviceName") String serviceName, @RequestParam("methodFolderName") String methodFolderName, HttpServletRequest request) 
	{
		String resultingJson = "";
		try {
			Map<String,Service> serviceMetadata = OpservServiceManager.getServicesInformation();
			if(serviceMetadata != null) {
				Service service = serviceMetadata.get(serviceName);
				if(service != null) {
					ServiceMethod serviceMethod = service.getServiceMethod(methodFolderName);
					if(serviceMethod != null) {
						Object returnObj = OpservServiceManager.invoke(serviceName.trim(),serviceMethod.getFolderName());
						resultingJson = AjaxResponseUtil.convertObjectToPrettyJson(returnObj);
					} else {
						resultingJson = "No service method :"+methodFolderName+" found for service : "+serviceName;
					}
				} else {
					resultingJson = "No service found by Name :"+serviceName;
				}
			} else {
				resultingJson = "Service Metadata not available to invoke services";
			}
			return resultingJson;
		} catch(Exception e) {
			return "Error in invocation of service. Please check";
		}
	}
		
	
}
