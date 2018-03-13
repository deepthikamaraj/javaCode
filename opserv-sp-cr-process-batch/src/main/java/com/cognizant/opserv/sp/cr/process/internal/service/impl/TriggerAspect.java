package com.cognizant.opserv.sp.cr.process.internal.service.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;


@Aspect("triggerAspect")
@Order(30)
public class TriggerAspect {
	
	@After("execution(* com.cognizant.opserv.sp.service.common.CustomerAlignmentCommonService.lockCustomerAlignment(..))")	
	public void sendMessage(JoinPoint joinPoint) {
        System.out.println("From TriggerAspect : Lock Customer alignment is been called..");
        // Send Messsage.
    }	
}