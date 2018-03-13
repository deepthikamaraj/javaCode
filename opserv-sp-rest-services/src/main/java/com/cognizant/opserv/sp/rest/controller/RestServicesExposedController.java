package com.cognizant.opserv.sp.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.opserv.service.test.OpservServiceManager;


@Controller
@RequestMapping(value = "/allServices")
public class RestServicesExposedController {
	
	
	@RequestMapping(value = "/fetchServices.do", method = RequestMethod.GET)
	public ModelAndView displayServiceNames(HttpServletRequest request)	{
		String clean = request.getParameter("clean");
		boolean startClean = (clean == null ? false : (clean.endsWith("true") ? true : false));
		OpservServiceManager.setServiceEnvironment(startClean);
		ModelAndView mav = new ModelAndView("exposingServices/displayServices");
		mav.addObject("serviceInterfaceNames", OpservServiceManager.getServiceInterfaceNames());
		return mav;
	}

}
