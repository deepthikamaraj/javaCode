package com.cognizant.opserv.sp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	 
	@RequestMapping(value = "/home.do")
	public String handlePlanmodelling() {
		return "redirect:/static/home.html";
	}
}
